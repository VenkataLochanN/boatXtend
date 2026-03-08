package com.realsil.sdk.core.bluetooth.channel;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class MultiSppChannel {
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 4;
    public static final int STATE_LISTEN = 1;
    public static final int STATE_NONE = 0;
    public static final UUID UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public int U;
    public BluetoothDevice ia;
    public boolean initialized;
    public BluetoothAdapter mAdapter;
    public UUID va;
    public ConnectThread wa;
    public ConnectedThread xa;
    public AcceptThread ya;
    public IChannelCallback za;

    private class AcceptThread extends Thread {
        public final BluetoothServerSocket ic;

        public AcceptThread() {
            BluetoothServerSocket bluetoothServerSocketListenUsingRfcommWithServiceRecord;
            try {
                bluetoothServerSocketListenUsingRfcommWithServiceRecord = MultiSppChannel.this.mAdapter.listenUsingRfcommWithServiceRecord("Serial Port Protocol", MultiSppChannel.this.va);
            } catch (IOException e2) {
                ZLogger.e("listen() failed:" + e2);
                bluetoothServerSocketListenUsingRfcommWithServiceRecord = null;
            }
            this.ic = bluetoothServerSocketListenUsingRfcommWithServiceRecord;
            MultiSppChannel.this.b(1);
        }

        public void cancel() {
            ZLogger.d("cancel AcceptThread");
            try {
                this.ic.close();
            } catch (IOException e2) {
                ZLogger.e("close() of server failed： " + e2);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r5 = this;
                java.lang.String r0 = "BEGIN mAcceptThread"
                com.realsil.sdk.core.logger.ZLogger.d(r0)
                java.lang.String r0 = "AcceptThread:SppChannel"
                r5.setName(r0)
            La:
                com.realsil.sdk.core.bluetooth.channel.MultiSppChannel r0 = com.realsil.sdk.core.bluetooth.channel.MultiSppChannel.this
                int r0 = com.realsil.sdk.core.bluetooth.channel.MultiSppChannel.c(r0)
                r1 = 3
                if (r0 == r1) goto L6c
                android.bluetooth.BluetoothServerSocket r0 = r5.ic     // Catch: java.io.IOException -> L57
                android.bluetooth.BluetoothSocket r0 = r0.accept()     // Catch: java.io.IOException -> L57
                if (r0 == 0) goto La
                com.realsil.sdk.core.bluetooth.channel.MultiSppChannel r2 = com.realsil.sdk.core.bluetooth.channel.MultiSppChannel.this
                monitor-enter(r2)
                com.realsil.sdk.core.bluetooth.channel.MultiSppChannel r3 = com.realsil.sdk.core.bluetooth.channel.MultiSppChannel.this     // Catch: java.lang.Throwable -> L54
                int r3 = com.realsil.sdk.core.bluetooth.channel.MultiSppChannel.c(r3)     // Catch: java.lang.Throwable -> L54
                if (r3 == 0) goto L39
                r4 = 1
                if (r3 == r4) goto L2f
                r4 = 2
                if (r3 == r4) goto L2f
                if (r3 == r1) goto L39
                goto L52
            L2f:
                com.realsil.sdk.core.bluetooth.channel.MultiSppChannel r1 = com.realsil.sdk.core.bluetooth.channel.MultiSppChannel.this     // Catch: java.lang.Throwable -> L54
                android.bluetooth.BluetoothDevice r3 = r0.getRemoteDevice()     // Catch: java.lang.Throwable -> L54
                r1.connected(r0, r3)     // Catch: java.lang.Throwable -> L54
                goto L52
            L39:
                r0.close()     // Catch: java.io.IOException -> L3d java.lang.Throwable -> L54
                goto L52
            L3d:
                r0 = move-exception
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L54
                r1.<init>()     // Catch: java.lang.Throwable -> L54
                java.lang.String r3 = "Could not close unwanted socket： "
                r1.append(r3)     // Catch: java.lang.Throwable -> L54
                r1.append(r0)     // Catch: java.lang.Throwable -> L54
                java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L54
                com.realsil.sdk.core.logger.ZLogger.e(r0)     // Catch: java.lang.Throwable -> L54
            L52:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L54
                goto La
            L54:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L54
                throw r0
            L57:
                r0 = move-exception
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "accept() failed"
                r1.append(r2)
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                com.realsil.sdk.core.logger.ZLogger.e(r0)
            L6c:
                java.lang.String r0 = "END AcceptThread"
                com.realsil.sdk.core.logger.ZLogger.i(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.channel.MultiSppChannel.AcceptThread.run():void");
        }
    }

    private class ConnectThread extends Thread {
        public final BluetoothSocket jc;
        public final BluetoothDevice kc;

        public ConnectThread(BluetoothDevice bluetoothDevice) {
            BluetoothSocket bluetoothSocketCreateRfcommSocketToServiceRecord;
            this.kc = bluetoothDevice;
            try {
                bluetoothSocketCreateRfcommSocketToServiceRecord = bluetoothDevice.createRfcommSocketToServiceRecord(MultiSppChannel.this.va);
            } catch (IOException e2) {
                ZLogger.e("createRfcommSocketToServiceRecord failed: " + e2.toString());
                bluetoothSocketCreateRfcommSocketToServiceRecord = null;
            }
            this.jc = bluetoothSocketCreateRfcommSocketToServiceRecord;
            MultiSppChannel.this.b(2);
        }

        public void cancel() {
            try {
                this.jc.close();
            } catch (IOException e2) {
                ZLogger.e("close() of connect socket failed: " + e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.i("BEGIN mConnectThread");
            setName("ConnectThread:SppChannel");
            MultiSppChannel.this.mAdapter.cancelDiscovery();
            try {
                try {
                    this.jc.connect();
                    synchronized (MultiSppChannel.this) {
                        MultiSppChannel.this.wa = null;
                    }
                    MultiSppChannel.this.connected(this.jc, this.kc);
                } catch (IOException unused) {
                    this.jc.close();
                    MultiSppChannel.this.g();
                }
            } catch (IOException e2) {
                ZLogger.e("unable to close() socket during connection failure: " + e2);
                MultiSppChannel.this.g();
            }
        }
    }

    private class ConnectedThread extends Thread {
        public final BluetoothSocket jc;
        public BufferedInputStream lc;
        public BufferedOutputStream mc;

        public ConnectedThread(BluetoothSocket bluetoothSocket) {
            BufferedInputStream bufferedInputStream;
            BufferedOutputStream bufferedOutputStream = null;
            this.lc = null;
            this.mc = null;
            ZLogger.d("create ConnectedThread");
            this.jc = bluetoothSocket;
            try {
                bufferedInputStream = new BufferedInputStream(bluetoothSocket.getInputStream());
                try {
                    bufferedOutputStream = new BufferedOutputStream(bluetoothSocket.getOutputStream());
                } catch (IOException e2) {
                    e = e2;
                    ZLogger.e("temp sockets not created: " + e);
                }
            } catch (IOException e3) {
                e = e3;
                bufferedInputStream = null;
            }
            this.lc = bufferedInputStream;
            this.mc = bufferedOutputStream;
            MultiSppChannel.this.b(3);
        }

        public void cancel() {
            try {
                this.jc.close();
            } catch (IOException e2) {
                ZLogger.e("close() of connect socket failed: " + e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.i("BEGIN mConnectedThread");
            byte[] bArr = new byte[1024];
            while (MultiSppChannel.this.U == 3) {
                try {
                    int i = this.lc.read(bArr);
                    if (i > 0) {
                        byte[] bArr2 = new byte[i];
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        StringBuilder sb = new StringBuilder();
                        sb.append("[RX >>] (");
                        sb.append(bArr2.length);
                        sb.append(") ");
                        sb.append(DataConverter.bytes2HexWithSeparate(bArr2));
                        ZLogger.d(sb.toString());
                        if (MultiSppChannel.this.za != null) {
                            MultiSppChannel.this.za.onDataReceive(bArr);
                        }
                    }
                } catch (IOException e2) {
                    ZLogger.e("disconnected:" + e2);
                    MultiSppChannel.this.h();
                    return;
                }
            }
        }

        public boolean write(byte[] bArr) {
            try {
                this.mc.write(bArr);
                this.mc.flush();
                return true;
            } catch (IOException e2) {
                ZLogger.e("Exception during write： " + e2);
                return false;
            }
        }
    }

    public MultiSppChannel(IChannelCallback iChannelCallback) {
        this(UUID_SECURE, iChannelCallback);
    }

    public MultiSppChannel(UUID uuid, IChannelCallback iChannelCallback) {
        this.va = UUID_SECURE;
        this.ia = null;
        this.U = 0;
        this.va = uuid;
        this.za = iChannelCallback;
        this.U = 0;
        initialize();
    }

    public final synchronized void b(int i) {
        this.U = i;
        ZLogger.v(">> ConnectionState=" + this.U);
        IChannelCallback iChannelCallback = this.za;
        if (iChannelCallback != null) {
            iChannelCallback.onConnectionStateChanged(this.ia, true, this.U);
        }
    }

    public synchronized void connect(BluetoothDevice bluetoothDevice) {
        ConnectThread connectThread;
        if (!this.initialized) {
            initialize();
        }
        ZLogger.d("connect to: " + bluetoothDevice);
        if (this.U == 2 && (connectThread = this.wa) != null) {
            connectThread.cancel();
            this.wa = null;
        }
        ConnectedThread connectedThread = this.xa;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.xa = null;
        }
        this.wa = new ConnectThread(bluetoothDevice);
        this.wa.start();
    }

    public synchronized void connected(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice) {
        ZLogger.d("BluetoothSocket connected");
        this.ia = bluetoothDevice;
        ConnectThread connectThread = this.wa;
        if (connectThread != null) {
            connectThread.cancel();
            this.wa = null;
        }
        ConnectedThread connectedThread = this.xa;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.xa = null;
        }
        AcceptThread acceptThread = this.ya;
        if (acceptThread != null) {
            acceptThread.cancel();
            this.ya = null;
        }
        this.xa = new ConnectedThread(bluetoothSocket);
        this.xa.start();
    }

    public final void g() {
        b(0);
        start();
    }

    public synchronized int getConnectionState() {
        return this.U;
    }

    public final void h() {
        b(0);
        start();
    }

    public final void initialize() {
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter == null) {
            ZLogger.w("BluetoothAdapter not initialized ");
            this.initialized = false;
        } else if (bluetoothAdapter.isEnabled()) {
            this.initialized = true;
        } else {
            ZLogger.w("Bluetooth is disabled ");
            this.initialized = false;
        }
    }

    public synchronized void start() {
        ZLogger.d("start");
        ConnectThread connectThread = this.wa;
        if (connectThread != null) {
            connectThread.cancel();
            this.wa = null;
        }
        ConnectedThread connectedThread = this.xa;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.xa = null;
        }
        if (this.ya == null) {
            this.ya = new AcceptThread();
            this.ya.start();
        }
    }

    public synchronized void stop() {
        ZLogger.d("stop");
        ConnectThread connectThread = this.wa;
        if (connectThread != null) {
            connectThread.cancel();
            this.wa = null;
        }
        ConnectedThread connectedThread = this.xa;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.xa = null;
        }
        AcceptThread acceptThread = this.ya;
        if (acceptThread != null) {
            acceptThread.cancel();
            this.ya = null;
        }
        b(0);
    }

    public void write(byte[] bArr) {
        synchronized (this) {
            if (this.U != 3) {
                return;
            }
            this.xa.write(bArr);
        }
    }
}