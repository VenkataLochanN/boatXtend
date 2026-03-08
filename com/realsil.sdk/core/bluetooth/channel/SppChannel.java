package com.realsil.sdk.core.bluetooth.channel;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class SppChannel extends Channel {
    public static final int ROLE_CLIENT = 1;
    public static final int ROLE_SERVER = 2;
    public static final UUID UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public boolean u;
    public int ua;
    public UUID va;
    public ConnectThread wa;
    public ConnectedThread xa;
    public AcceptThread ya;

    private class AcceptThread extends Thread {
        public final BluetoothServerSocket ic;

        public AcceptThread() {
            BluetoothServerSocket bluetoothServerSocketListenUsingRfcommWithServiceRecord;
            try {
                bluetoothServerSocketListenUsingRfcommWithServiceRecord = SppChannel.this.B.listenUsingRfcommWithServiceRecord("Realtek Secure SPP Server", SppChannel.this.va);
            } catch (IOException e2) {
                ZLogger.e("listen() failed:" + e2);
                bluetoothServerSocketListenUsingRfcommWithServiceRecord = null;
            }
            this.ic = bluetoothServerSocketListenUsingRfcommWithServiceRecord;
            SppChannel.this.a(257);
        }

        public void cancel() {
            ZLogger.d("cancel AcceptThread");
            try {
                this.ic.close();
            } catch (IOException e2) {
                ZLogger.e("close() of server failed： " + e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.d("BEGIN mAcceptThread");
            setName("AcceptThread:SppChannel");
            while (SppChannel.this.U != 512) {
                try {
                    BluetoothSocket bluetoothSocketAccept = this.ic.accept();
                    if (bluetoothSocketAccept != null) {
                        synchronized (SppChannel.this) {
                            int i = SppChannel.this.U;
                            if (i == 0 || i == 512) {
                                try {
                                    bluetoothSocketAccept.close();
                                } catch (IOException e2) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("Could not close unwanted socket： ");
                                    sb.append(e2);
                                    ZLogger.e(sb.toString());
                                }
                            } else if (i == 256 || i == 257) {
                                SppChannel.this.connected(bluetoothSocketAccept, bluetoothSocketAccept.getRemoteDevice());
                            }
                        }
                    }
                } catch (IOException e3) {
                    ZLogger.e("accept() failed" + e3);
                }
            }
            ZLogger.d("END AcceptThread");
        }
    }

    private class ConnectThread extends Thread {
        public final BluetoothSocket jc;
        public final BluetoothDevice kc;

        public ConnectThread(BluetoothDevice bluetoothDevice) {
            BluetoothSocket bluetoothSocketCreateRfcommSocketToServiceRecord;
            this.kc = bluetoothDevice;
            try {
                boolean z = SppChannel.this.u;
                StringBuilder sb = new StringBuilder();
                sb.append("connect to: ");
                sb.append(bluetoothDevice);
                ZLogger.d(z, sb.toString());
                bluetoothSocketCreateRfcommSocketToServiceRecord = bluetoothDevice.createRfcommSocketToServiceRecord(SppChannel.this.va);
            } catch (Exception e2) {
                ZLogger.e("createRfcommSocketToServiceRecord failed: " + e2.toString());
                bluetoothSocketCreateRfcommSocketToServiceRecord = null;
            }
            for (int i = 0; i < 10 && bluetoothSocketCreateRfcommSocketToServiceRecord == null; i++) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e3) {
                    ZLogger.e(e3.toString());
                }
            }
            this.jc = bluetoothSocketCreateRfcommSocketToServiceRecord;
            SppChannel.this.a(256);
        }

        public void cancel() {
            try {
                this.jc.close();
            } catch (IOException e2) {
                ZLogger.e("close socket failed: " + e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.d("BEGIN mConnectThread");
            setName("ConnectThread:SppChannel");
            if (this.jc == null) {
                ZLogger.w("get BluetoothSocket fail, connect fail");
                SppChannel.this.a(0);
                return;
            }
            BluetoothAdapter bluetoothAdapter = SppChannel.this.B;
            if (bluetoothAdapter != null) {
                bluetoothAdapter.cancelDiscovery();
            }
            try {
                if (this.jc.isConnected()) {
                    ZLogger.d("socket already connected");
                }
                ZLogger.d("connect socket ...");
                this.jc.connect();
                synchronized (SppChannel.this) {
                    SppChannel.this.wa = null;
                }
                SppChannel.this.connected(this.jc, this.kc);
            } catch (IOException e2) {
                ZLogger.e(e2.toString());
                try {
                    this.jc.close();
                } catch (IOException e3) {
                    ZLogger.e("unable to close socket during connection failure: " + e3);
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
                SppChannel.this.g();
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
        }

        public void cancel() {
            try {
                this.jc.close();
            } catch (IOException e2) {
                ZLogger.e("close socket failed: " + e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ZLogger.d("BEGIN mConnectedThread");
            byte[] bArr = new byte[1024];
            SppChannel.this.a(512);
            while (SppChannel.this.U == 512) {
                try {
                    int i = this.lc.read(bArr);
                    if (i > 0) {
                        byte[] bArr2 = new byte[i];
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        if (SppChannel.this.u) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("[RX >>] (");
                            sb.append(bArr2.length);
                            sb.append(") ");
                            sb.append(DataConverter.bytes2HexWithSeparate(bArr2));
                            ZLogger.v(sb.toString());
                        }
                        if (SppChannel.this.mCallback != null) {
                            SppChannel.this.mCallback.onDataReceive(bArr2);
                        }
                    }
                } catch (IOException e2) {
                    ZLogger.e("disconnected:" + e2.toString());
                    SppChannel.this.h();
                    return;
                }
            }
        }

        public boolean write(byte[] bArr) {
            try {
                if (SppChannel.this.u) {
                    ZLogger.v(String.format(Locale.US, "<< (%d) %s", Integer.valueOf(bArr.length), DataConverter.bytes2Hex(bArr)));
                }
                this.mc.write(bArr);
                this.mc.flush();
                return true;
            } catch (IOException e2) {
                ZLogger.e("Exception during write： " + e2);
                return false;
            }
        }
    }

    public SppChannel(int i, UUID uuid, IChannelCallback iChannelCallback) {
        super(iChannelCallback);
        this.u = false;
        this.ua = 1;
        this.va = UUID_SECURE;
        this.ua = i;
        this.va = uuid;
        this.U = 0;
        this.u = RtkCore.DEBUG;
        initialize();
    }

    public SppChannel(IChannelCallback iChannelCallback) {
        this(1, UUID_SECURE, iChannelCallback);
    }

    public SppChannel(UUID uuid, IChannelCallback iChannelCallback) {
        this(1, uuid, iChannelCallback);
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice) {
        boolean z;
        ConnectThread connectThread;
        if (bluetoothDevice == null) {
            z = false;
        } else {
            if (!this.initialized) {
                initialize();
            }
            this.ia = bluetoothDevice;
            if (this.U == 256 && (connectThread = this.wa) != null) {
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
            z = true;
        }
        return z;
    }

    public synchronized boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        if (!this.initialized) {
            initialize();
        }
        if (bluetoothSocket != null) {
            connected(bluetoothSocket, bluetoothDevice);
            return true;
        }
        return connect(bluetoothDevice);
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
        ZLogger.v("connectionFailed");
        this.ia = null;
        a(0);
        start();
    }

    public final void h() {
        ZLogger.v("connectionLost");
        this.ia = null;
        a(0);
        start();
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
        if ((this.ua & 2) == 2 && this.ya == null) {
            this.ya = new AcceptThread();
            this.ya.start();
        }
    }

    public synchronized void stop() {
        ZLogger.v("stop");
        this.ia = null;
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
        a(0);
    }

    public boolean write(byte[] bArr) {
        synchronized (this) {
            if (this.U != 512) {
                ZLogger.d("not connected");
                return false;
            }
            ConnectedThread connectedThread = this.xa;
            if (connectedThread != null) {
                return connectedThread.write(bArr);
            }
            ZLogger.d("ConnectedThread not created");
            return false;
        }
    }
}