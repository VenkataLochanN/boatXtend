package com.realsil.sdk.core.bluetooth.channel;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class SppChannelImpl {
    public static UUID UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static volatile SppChannelImpl mInstance;
    public SppChannel Aa;
    public volatile boolean Ba;
    public final Object Ca = new Object();
    public final int Da = 5000;
    public IChannelCallback za = new IChannelCallback() { // from class: com.realsil.sdk.core.bluetooth.channel.SppChannelImpl.1
        @Override // com.realsil.sdk.core.bluetooth.channel.IChannelCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            synchronized (SppChannelImpl.this.mCallbacks) {
                if (SppChannelImpl.this.mCallbacks != null && SppChannelImpl.this.mCallbacks.size() > 0) {
                    Iterator it = SppChannelImpl.this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((IChannelCallback) it.next()).onConnectionStateChanged(bluetoothDevice, z, i);
                    }
                }
            }
        }

        @Override // com.realsil.sdk.core.bluetooth.channel.IChannelCallback
        public void onDataReceive(byte[] bArr) {
            super.onDataReceive(bArr);
            synchronized (SppChannelImpl.this.mCallbacks) {
                if (SppChannelImpl.this.mCallbacks != null && SppChannelImpl.this.mCallbacks.size() > 0) {
                    Iterator it = SppChannelImpl.this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((IChannelCallback) it.next()).onDataReceive(bArr);
                    }
                }
            }
        }
    };
    public List<IChannelCallback> mCallbacks = new CopyOnWriteArrayList();

    public SppChannelImpl() {
        getChannel();
    }

    public static SppChannelImpl getInstance() {
        return mInstance;
    }

    public static synchronized void initialize() {
        if (mInstance == null) {
            synchronized (SppChannelImpl.class) {
                if (mInstance == null) {
                    mInstance = new SppChannelImpl();
                }
            }
        }
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, IChannelCallback iChannelCallback) {
        register(iChannelCallback);
        if (getConnectionState() == 512) {
            BluetoothDevice device = getChannel().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                iChannelCallback.onConnectionStateChanged(bluetoothDevice, true, 512);
                return true;
            }
            ZLogger.d("current connected device is conflict with the connecting device");
        }
        boolean zConnect = getChannel().connect(bluetoothDevice, bluetoothSocket);
        if (!zConnect) {
            unregister(iChannelCallback);
        }
        return zConnect;
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, IChannelCallback iChannelCallback) {
        register(iChannelCallback);
        if (getConnectionState() == 512) {
            BluetoothDevice device = getChannel().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                ZLogger.d("connection already connected");
                iChannelCallback.onConnectionStateChanged(bluetoothDevice, true, 512);
                return true;
            }
            ZLogger.d("current connected device is conflict with the connecting device");
        }
        boolean zConnect = getChannel().connect(bluetoothDevice);
        if (!zConnect) {
            unregister(iChannelCallback);
        }
        return zConnect;
    }

    public void destroy() {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks != null) {
                this.mCallbacks.clear();
            }
        }
        disconnect();
    }

    public void disconnect() {
        getChannel().stop();
    }

    public final SppChannel getChannel() {
        if (this.Aa == null) {
            this.Aa = new SppChannel(UUID_SECURE, this.za);
        }
        return this.Aa;
    }

    public int getConnectionState() {
        return getChannel().getConnectionState();
    }

    public void notifyAck() {
        synchronized (this.Ca) {
            this.Ba = true;
            this.Ca.notifyAll();
        }
    }

    public void register(IChannelCallback iChannelCallback) {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new CopyOnWriteArrayList();
            }
            if (!this.mCallbacks.contains(iChannelCallback)) {
                this.mCallbacks.add(iChannelCallback);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("callback's size=");
            sb.append(this.mCallbacks.size());
            ZLogger.v(sb.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0014 A[Catch: all -> 0x003e, TRY_LEAVE, TryCatch #2 {, blocks: (B:8:0x0008, B:9:0x000a, B:11:0x0014, B:18:0x0020, B:19:0x0022, B:35:0x003d, B:20:0x0023, B:22:0x0027, B:26:0x0033, B:27:0x0035, B:30:0x0038, B:25:0x0030), top: B:42:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean sendPacket(byte[] r3, boolean r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            if (r3 != 0) goto L6
            monitor-exit(r2)
            return r0
        L6:
            if (r4 != 0) goto La
            r2.Ba = r0     // Catch: java.lang.Throwable -> L3e
        La:
            com.realsil.sdk.core.bluetooth.channel.SppChannel r1 = r2.getChannel()     // Catch: java.lang.Throwable -> L3e
            boolean r3 = r1.write(r3)     // Catch: java.lang.Throwable -> L3e
            if (r3 != 0) goto L1b
            java.lang.String r3 = "send spp data failed"
            com.realsil.sdk.core.logger.ZLogger.w(r3)     // Catch: java.lang.Throwable -> L3e
            monitor-exit(r2)
            return r0
        L1b:
            r3 = 1
            if (r4 == 0) goto L20
            monitor-exit(r2)
            return r3
        L20:
            java.lang.Object r4 = r2.Ca     // Catch: java.lang.Throwable -> L3e
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L3e
            boolean r0 = r2.Ba     // Catch: java.lang.Throwable -> L3b
            if (r0 != 0) goto L38
            java.lang.Object r3 = r2.Ca     // Catch: java.lang.InterruptedException -> L2f java.lang.Throwable -> L3b
            r0 = 5000(0x1388, double:2.4703E-320)
            r3.wait(r0)     // Catch: java.lang.InterruptedException -> L2f java.lang.Throwable -> L3b
            goto L33
        L2f:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L3b
        L33:
            boolean r3 = r2.Ba     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r2)
            return r3
        L38:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r2)
            return r3
        L3b:
            r3 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3b
            throw r3     // Catch: java.lang.Throwable -> L3e
        L3e:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.channel.SppChannelImpl.sendPacket(byte[], boolean):boolean");
    }

    public void unregister(IChannelCallback iChannelCallback) {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks != null) {
                this.mCallbacks.remove(iChannelCallback);
            }
        }
    }

    public boolean write(byte[] bArr) {
        return getChannel().write(bArr);
    }
}