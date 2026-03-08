package com.realsil.sdk.bbpro.core.transportlayer;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.base.BaseThread;
import com.realsil.sdk.core.bluetooth.channel.IChannelCallback;
import com.realsil.sdk.core.bluetooth.channel.SppChannel;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes3.dex */
public class SppTransportLayer {
    public static SppTransportLayer mInstance;
    public volatile int fa;
    public volatile int ga;
    public int ha;
    public ThreadTx ia;
    public ThreadRx ja;
    public SppChannel la;
    public volatile boolean ma;
    public Object ea = new Object();
    public final Object na = new Object();
    public final int oa = 5000;
    public IChannelCallback ka = new IChannelCallback() { // from class: com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer.1
        @Override // com.realsil.sdk.core.bluetooth.channel.IChannelCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            ZLogger.v(true, String.format(Locale.US, "%s status: %b %d", bluetoothDevice != null ? bluetoothDevice.getAddress() : null, Boolean.valueOf(z), Integer.valueOf(i)));
            if (!z || i == 0) {
                SppTransportLayer.this.m();
            }
            try {
                synchronized (SppTransportLayer.this.mCallbacks) {
                    if (SppTransportLayer.this.mCallbacks != null && SppTransportLayer.this.mCallbacks.size() > 0) {
                        Iterator it = SppTransportLayer.this.mCallbacks.iterator();
                        while (it.hasNext()) {
                            ((TransportLayerCallback) it.next()).onConnectionStateChanged(bluetoothDevice, z, i);
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                ZLogger.e(e2.toString());
            }
        }

        @Override // com.realsil.sdk.core.bluetooth.channel.IChannelCallback
        public void onDataReceive(byte[] bArr) {
            if (SppTransportLayer.this.ja == null || bArr == null) {
                return;
            }
            SppTransportLayer.this.ja.addQueue(bArr);
        }
    };
    public List<TransportLayerCallback> mCallbacks = new CopyOnWriteArrayList();

    private class ThreadRx extends BaseThread<byte[]> {
        public ThreadRx() {
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
                    if (transportLayerPacketBuilderPacket.getSeqNum() == SppTransportLayer.this.ga) {
                        ZLogger.d(String.format(Locale.US, "dumplicate packet, [0x%2X 0x%04X >>] %s", Byte.valueOf(transportLayerPacketBuilderPacket.getSeqNum()), Integer.valueOf(opcode), DataConverter.bytes2HexWithSeparate(parameters)));
                        return;
                    }
                    SppTransportLayer.this.ga = transportLayerPacketBuilderPacket.getSeqNum();
                    ZLogger.d(String.format(Locale.US, "[0x%02X 0x%04X >>] %s", Byte.valueOf(transportLayerPacketBuilderPacket.getSeqNum()), Integer.valueOf(opcode), DataConverter.bytes2HexWithSeparate(parameters)));
                    if (opcode != 0) {
                        SppTransportLayer.this.sendAck(opcode, (byte) 0);
                        synchronized (SppTransportLayer.this.mCallbacks) {
                            if (SppTransportLayer.this.mCallbacks != null && SppTransportLayer.this.mCallbacks.size() > 0) {
                                Iterator it = SppTransportLayer.this.mCallbacks.iterator();
                                while (it.hasNext()) {
                                    ((TransportLayerCallback) it.next()).onDataReceive(transportLayerPacketBuilderPacket);
                                }
                            }
                        }
                    } else {
                        AckPacket ackPacketBuilder = AckPacket.builder(parameters);
                        if (ackPacketBuilder != null) {
                            SppTransportLayer.this.notifyAck();
                            synchronized (SppTransportLayer.this.mCallbacks) {
                                if (SppTransportLayer.this.mCallbacks != null && SppTransportLayer.this.mCallbacks.size() > 0) {
                                    Iterator it2 = SppTransportLayer.this.mCallbacks.iterator();
                                    while (it2.hasNext()) {
                                        ((TransportLayerCallback) it2.next()).onAckReceive(ackPacketBuilder);
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

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.d(true, "RxThread is running");
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                byte[] bArrTake = take();
                if (bArrTake != null) {
                    a(bArrTake);
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
                    SppTransportLayer.this.ha = 0;
                    if (SppTransportLayer.this.sendPacket(bArrTake, false)) {
                        continue;
                    } else {
                        while (SppTransportLayer.this.ha < 3) {
                            if (isCanceled()) {
                                return;
                            }
                            SppTransportLayer.b(SppTransportLayer.this);
                            ZLogger.v(true, "<< Retrans " + SppTransportLayer.this.ha + ", data: " + DataConverter.bytes2Hex(bArrTake));
                            if (SppTransportLayer.this.sendPacket(bArrTake, false)) {
                                break;
                            }
                            if (isCanceled()) {
                                ZLogger.d(">> tx thread already canceled");
                            } else if (SppTransportLayer.this.ha >= 3) {
                                ZLogger.w(">> ERR_TRANSPORT_RETRAINS_EXCEED_MAX_TIMES");
                                SppTransportLayer.this.notifyError(64);
                            }
                        }
                    }
                }
            }
            ZLogger.d(true, "TxThread stopped");
        }
    }

    public SppTransportLayer() {
        getChannel();
    }

    public static /* synthetic */ int b(SppTransportLayer sppTransportLayer) {
        int i = sppTransportLayer.ha;
        sppTransportLayer.ha = i + 1;
        return i;
    }

    public static SppTransportLayer getInstance() {
        if (mInstance == null) {
            initialize();
        }
        return mInstance;
    }

    public static synchronized void initialize() {
        if (mInstance == null) {
            synchronized (SppTransportLayer.class) {
                if (mInstance == null) {
                    mInstance = new SppTransportLayer();
                }
            }
        }
    }

    public boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        if (bluetoothDevice == null) {
            return false;
        }
        this.fa = 1;
        this.ha = 0;
        this.ga = 0;
        p();
        o();
        if (getConnectionState() == 512) {
            BluetoothDevice device = getChannel().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                this.ka.onConnectionStateChanged(bluetoothDevice, true, 512);
                return true;
            }
            ZLogger.d("current connected device is conflict with the connecting device");
        }
        return getChannel().connect(bluetoothDevice, bluetoothSocket);
    }

    public void destory() {
        ZLogger.v(true, "destory");
        synchronized (this.mCallbacks) {
            if (this.mCallbacks != null) {
                this.mCallbacks.clear();
            }
        }
        disconnect();
    }

    public void disconnect() {
        ZLogger.v(true, "disconnect");
        q();
        r();
        SppChannel sppChannel = this.la;
        if (sppChannel != null) {
            sppChannel.stop();
        }
    }

    public final SppChannel getChannel() {
        if (this.la == null) {
            this.la = new SppChannel(this.ka);
        }
        return this.la;
    }

    public int getConnectionState() {
        return getChannel().getConnectionState();
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

    public void notifyAck() {
        synchronized (this.na) {
            this.ma = true;
            this.na.notifyAll();
        }
    }

    public final void notifyError(int i) {
        ZLogger.w(String.format("notifyError: 0x%04X", Integer.valueOf(i)));
        synchronized (this.mCallbacks) {
            if (this.mCallbacks != null && this.mCallbacks.size() > 0) {
                Iterator<TransportLayerCallback> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onError(i);
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
            notifyAck();
        }
    }

    public void register(TransportLayerCallback transportLayerCallback) {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new CopyOnWriteArrayList();
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
        return sendPacket(bArrEncode, true);
    }

    public boolean sendCmd(short s, byte[] bArr) {
        byte[] bArrEncode;
        synchronized (this.ea) {
            bArrEncode = TransportLayerPacket.encode(this.fa, s, bArr);
            n();
        }
        ThreadTx threadTx = this.ia;
        if (threadTx == null) {
            return true;
        }
        threadTx.addQueue(bArrEncode);
        return true;
    }

    public boolean sendCmd(byte[] bArr) {
        byte[] bArrEncode;
        synchronized (this.ea) {
            bArrEncode = TransportLayerPacket.encode(this.fa, bArr);
            n();
        }
        ThreadTx threadTx = this.ia;
        if (threadTx == null) {
            return true;
        }
        threadTx.addQueue(bArrEncode);
        return true;
    }

    public boolean sendCmdSync(short s, byte[] bArr) {
        byte[] bArrEncode;
        synchronized (this.ea) {
            bArrEncode = TransportLayerPacket.encode(this.fa, s, bArr);
            ZLogger.v(true, String.format(Locale.US, "<< 0x%02x 0x%04x", Integer.valueOf(this.fa), Short.valueOf(s)));
            n();
        }
        return sendPacket(bArrEncode, false);
    }

    public boolean sendPacket(byte[] bArr, boolean z) {
        if (bArr == null) {
            ZLogger.d("data == null");
            return false;
        }
        if (!z) {
            this.ma = false;
        }
        if (!getChannel().write(bArr)) {
            ZLogger.w("send spp data failed");
            return false;
        }
        if (z) {
            return true;
        }
        synchronized (this.na) {
            if (this.ma) {
                return true;
            }
            try {
                this.na.wait(BootloaderScanner.TIMEOUT);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (!this.ma) {
                ZLogger.d("no ack receive");
            }
            return this.ma;
        }
    }

    public void unregister(TransportLayerCallback transportLayerCallback) {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks != null) {
                this.mCallbacks.remove(transportLayerCallback);
            }
        }
    }
}