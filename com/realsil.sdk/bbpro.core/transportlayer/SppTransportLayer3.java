package com.realsil.sdk.bbpro.core.transportlayer;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.base.BaseThread;
import com.realsil.sdk.core.bluetooth.channel.IChannelCallback;
import com.realsil.sdk.core.bluetooth.channel.SppChannelImpl;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class SppTransportLayer3 {
    public static SppTransportLayer3 mInstance;
    public volatile int fa;
    public volatile int ga;
    public int ha;
    public ThreadTx ia;
    public ThreadRx ja;
    public SppChannelImpl da = null;
    public Object ea = new Object();
    public IChannelCallback ka = new IChannelCallback() { // from class: com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer3.1
        @Override // com.realsil.sdk.core.bluetooth.channel.IChannelCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            if (!z || i == 0) {
                SppTransportLayer3.this.m();
            } else {
                ZLogger.v(true, "status: " + z + ", newState: " + i);
            }
            try {
                synchronized (SppTransportLayer3.this.mCallbacks) {
                    if (SppTransportLayer3.this.mCallbacks != null && SppTransportLayer3.this.mCallbacks.size() > 0) {
                        Iterator it = SppTransportLayer3.this.mCallbacks.iterator();
                        while (it.hasNext()) {
                            ((TransportLayerCallback) it.next()).onConnectionStateChanged(bluetoothDevice, z, i);
                        }
                    }
                }
            } catch (Exception e2) {
                ZLogger.e(e2.toString());
            }
        }

        @Override // com.realsil.sdk.core.bluetooth.channel.IChannelCallback
        public void onDataReceive(byte[] bArr) {
            if (SppTransportLayer3.this.ja == null || bArr == null) {
                return;
            }
            SppTransportLayer3.this.ja.addQueue(bArr);
        }
    };
    public List<TransportLayerCallback> mCallbacks = new ArrayList();

    private class ThreadRx extends BaseThread<byte[]> {
        public ThreadRx() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.d(true, "RxThread is running");
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                byte[] bArrTake = take();
                if (bArrTake != null) {
                    SppTransportLayer3.this.a(bArrTake);
                }
            }
            ZLogger.d(true, "RxThread stopped");
        }
    }

    private class ThreadTx extends BaseThread<byte[]> {
        public ThreadTx() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.v(true, "TxThread is running");
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                byte[] bArrTake = take();
                if (bArrTake != null) {
                    SppTransportLayer3.this.ha = 0;
                    if (SppTransportLayer3.this.getChannel().sendPacket(bArrTake, false)) {
                        continue;
                    } else {
                        while (SppTransportLayer3.this.ha < 3) {
                            if (isCanceled()) {
                                return;
                            }
                            SppTransportLayer3.b(SppTransportLayer3.this);
                            ZLogger.v(true, "<< Retrans " + SppTransportLayer3.this.ha + ", data: " + DataConverter.bytes2Hex(bArrTake));
                            if (SppTransportLayer3.this.getChannel().sendPacket(bArrTake, false)) {
                                break;
                            } else if (SppTransportLayer3.this.ha >= 3) {
                                ZLogger.w(">> ERR_TRANSPORT_RETRAINS_EXCEED_MAX_TIMES");
                                SppTransportLayer3.this.notifyError(64);
                            }
                        }
                    }
                }
            }
            ZLogger.d(true, "TxThread stopped");
        }
    }

    public SppTransportLayer3() {
        getChannel();
    }

    public static /* synthetic */ int b(SppTransportLayer3 sppTransportLayer3) {
        int i = sppTransportLayer3.ha;
        sppTransportLayer3.ha = i + 1;
        return i;
    }

    public static SppTransportLayer3 getInstance() {
        if (mInstance == null) {
            initialize();
        }
        return mInstance;
    }

    public static synchronized void initialize() {
        if (mInstance == null) {
            synchronized (SppTransportLayer3.class) {
                if (mInstance == null) {
                    mInstance = new SppTransportLayer3();
                }
            }
        }
    }

    public final void a(byte[] bArr) {
        try {
            int length = bArr.length;
            int packetLength = 0;
            do {
                int i = length - packetLength;
                if (i <= 0) {
                    return;
                }
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, packetLength, bArr2, 0, i);
                TransportLayerPacket transportLayerPacketBuilderPacket = TransportLayerPacket.builderPacket(bArr2);
                if (transportLayerPacketBuilderPacket == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("error packet : ");
                    sb.append(DataConverter.bytes2Hex(bArr));
                    ZLogger.d(sb.toString());
                    return;
                }
                int opcode = transportLayerPacketBuilderPacket.getOpcode();
                transportLayerPacketBuilderPacket.getPayload();
                byte[] parameters = transportLayerPacketBuilderPacket.getParameters();
                if (transportLayerPacketBuilderPacket.getSeqNum() == this.ga) {
                    ZLogger.d(String.format(Locale.US, "dumplicate packet, [0x%2X 0x%04X >>] %s", Byte.valueOf(transportLayerPacketBuilderPacket.getSeqNum()), Integer.valueOf(opcode), DataConverter.bytes2HexWithSeparate(parameters)));
                    return;
                }
                this.ga = transportLayerPacketBuilderPacket.getSeqNum();
                ZLogger.v(String.format(Locale.US, "[0x%02X 0x%04X >>] %s", Byte.valueOf(transportLayerPacketBuilderPacket.getSeqNum()), Integer.valueOf(opcode), DataConverter.bytes2HexWithSeparate(parameters)));
                if (opcode != 0) {
                    sendAck(opcode, (byte) 0);
                    synchronized (this.mCallbacks) {
                        if (this.mCallbacks != null && this.mCallbacks.size() > 0) {
                            Iterator<TransportLayerCallback> it = this.mCallbacks.iterator();
                            while (it.hasNext()) {
                                it.next().onDataReceive(transportLayerPacketBuilderPacket);
                            }
                        }
                    }
                } else {
                    AckPacket ackPacketBuilder = AckPacket.builder(parameters);
                    if (ackPacketBuilder != null) {
                        getChannel().notifyAck();
                        synchronized (this.mCallbacks) {
                            if (this.mCallbacks != null && this.mCallbacks.size() > 0) {
                                Iterator<TransportLayerCallback> it2 = this.mCallbacks.iterator();
                                while (it2.hasNext()) {
                                    it2.next().onAckReceive(ackPacketBuilder);
                                }
                            }
                        }
                    }
                }
                packetLength += transportLayerPacketBuilderPacket.getPacketLength();
            } while (packetLength < length);
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
        }
    }

    public boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        this.fa = 1;
        this.ha = 0;
        this.ga = 0;
        p();
        o();
        return getChannel().connect(bluetoothDevice, bluetoothSocket, this.ka);
    }

    public void destory() {
        ZLogger.v(true, "destory");
        synchronized (this.mCallbacks) {
            if (this.mCallbacks != null) {
                this.mCallbacks.clear();
            }
        }
        q();
        r();
        SppChannelImpl sppChannelImpl = this.da;
        if (sppChannelImpl != null) {
            sppChannelImpl.destroy();
        }
    }

    public void disconnect() {
        ZLogger.v(true, "disconnect");
        q();
        r();
        SppChannelImpl sppChannelImpl = this.da;
        if (sppChannelImpl != null) {
            sppChannelImpl.disconnect();
        }
    }

    public final SppChannelImpl getChannel() {
        if (this.da == null) {
            this.da = SppChannelImpl.getInstance();
        }
        return this.da;
    }

    public final void m() {
        ZLogger.v("closePassive");
        q();
        r();
    }

    public final void n() {
        if (this.fa != 255) {
            this.fa++;
        } else {
            this.fa = 1;
        }
    }

    public final void notifyError(int i) {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks != null && this.mCallbacks.size() > 0) {
                Iterator<TransportLayerCallback> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onError(64);
                }
            }
        }
    }

    public final void o() {
        ZLogger.v(true, "startRxSchedule.");
        ThreadRx threadRx = this.ja;
        if (threadRx != null) {
            threadRx.cancel(true);
        }
        this.ja = new ThreadRx();
        this.ja.start();
    }

    public final void p() {
        ThreadTx threadTx = this.ia;
        if (threadTx != null) {
            threadTx.cancel(true);
        }
        ZLogger.v(true, "startTxSchedule.");
        this.ia = new ThreadTx();
        this.ia.start();
    }

    public final void q() {
        ZLogger.v(true, "stopRxSchedule.");
        ThreadRx threadRx = this.ja;
        if (threadRx != null) {
            threadRx.clearQueue();
            this.ja.cancel(true);
        }
    }

    public final void r() {
        if (this.ia != null) {
            ZLogger.v(true, "stopTxSchedule.");
            this.ia.clearQueue();
            this.ia.cancel(true);
            getChannel().notifyAck();
        }
    }

    public void register(TransportLayerCallback transportLayerCallback) {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new ArrayList();
            }
            if (!this.mCallbacks.contains(transportLayerCallback)) {
                this.mCallbacks.add(transportLayerCallback);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("callback's size=");
            sb.append(this.mCallbacks.size());
            ZLogger.v(sb.toString());
        }
    }

    public boolean sendAck(int i, byte b2) {
        byte[] bArrEncode;
        synchronized (this.ea) {
            bArrEncode = TransportLayerPacket.encode(this.fa, AckPacket.encode(i, b2));
            ZLogger.v(String.format("[<<0x%02X] ACK to 0x%04x", Integer.valueOf(this.fa), Integer.valueOf(i)));
            n();
        }
        return getChannel().sendPacket(bArrEncode, true);
    }

    public boolean sendCmd(short s, byte[] bArr) {
        byte[] bArrEncode;
        synchronized (this.ea) {
            bArrEncode = TransportLayerPacket.encode(this.fa, s, bArr);
            ZLogger.v(true, String.format(Locale.US, "<< 0x%02x 0x%04x", Integer.valueOf(this.fa), Short.valueOf(s)));
            n();
        }
        ThreadTx threadTx = this.ia;
        if (threadTx != null) {
            threadTx.addQueue(bArrEncode);
        }
        return true;
    }

    public boolean sendCmd(byte[] bArr) {
        byte[] bArrEncode;
        synchronized (this.ea) {
            bArrEncode = TransportLayerPacket.encode(this.fa, bArr);
            ZLogger.v(true, String.format(Locale.US, "<< 0x%02x ", Integer.valueOf(this.fa)));
            n();
        }
        ThreadTx threadTx = this.ia;
        if (threadTx != null) {
            threadTx.addQueue(bArrEncode);
        }
        return true;
    }

    public boolean sendCmdSync(short s, byte[] bArr) {
        byte[] bArrEncode;
        synchronized (this.ea) {
            bArrEncode = TransportLayerPacket.encode(this.fa, s, bArr);
            ZLogger.v(true, String.format(Locale.US, "<< 0x%02x 0x%04x", Integer.valueOf(this.fa), Short.valueOf(s)));
            n();
        }
        return getChannel().sendPacket(bArrEncode, false);
    }

    public void unregister(TransportLayerCallback transportLayerCallback) {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks != null) {
                this.mCallbacks.remove(transportLayerCallback);
            }
        }
    }
}