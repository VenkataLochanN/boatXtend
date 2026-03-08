package com.ido.ble.bluetooth.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.text.TextUtils;
import android.util.Log;
import com.ido.ble.bluetooth.bt.d;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
class f {
    public static f j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BluetoothSocket f3987a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ISPPConnectStateListener f3988b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private DataInputStream f3991e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3989c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Executor f3990d = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3992f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ConcurrentLinkedQueue<byte[]> f3993g = new ConcurrentLinkedQueue<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Lock f3994h = new ReentrantLock();
    private final Condition i = this.f3994h.newCondition();

    class a implements d.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ISPPConnectStateListener f3995a;

        a(ISPPConnectStateListener iSPPConnectStateListener) {
            this.f3995a = iSPPConnectStateListener;
        }

        @Override // com.ido.ble.bluetooth.bt.d.b
        public void a(String str) {
            if (TextUtils.isEmpty(str)) {
                f.this.d();
            } else {
                f.this.a(str, this.f3995a);
            }
        }
    }

    class b implements e {

        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ BluetoothDevice f3998a;

            a(BluetoothDevice bluetoothDevice) {
                this.f3998a = bluetoothDevice;
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.a(this.f3998a);
            }
        }

        b() {
        }

        @Override // com.ido.ble.bluetooth.bt.e
        public void a() {
            f.this.d();
        }

        @Override // com.ido.ble.bluetooth.bt.e
        public void a(BluetoothDevice bluetoothDevice) {
            f.this.f3990d.execute(new a(bluetoothDevice));
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothSocket f4000a;

        c(BluetoothSocket bluetoothSocket) {
            this.f4000a = bluetoothSocket;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.a(this.f4000a);
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothSocket f4002a;

        d(BluetoothSocket bluetoothSocket) {
            this.f4002a = bluetoothSocket;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.b(this.f4002a);
        }
    }

    private f() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] connect " + bluetoothDevice.getName());
        try {
            BluetoothSocket bluetoothSocketCreateRfcommSocketToServiceRecord = bluetoothDevice.createRfcommSocketToServiceRecord(com.ido.ble.bluetooth.e.f.f4135a);
            if (!bluetoothSocketCreateRfcommSocketToServiceRecord.isConnected()) {
                bluetoothSocketCreateRfcommSocketToServiceRecord.connect();
            }
            c(bluetoothSocketCreateRfcommSocketToServiceRecord);
        } catch (IOException e2) {
            LogTool.b(com.ido.ble.logs.a.q, "[SPPConnectManager] connect. " + e2.getMessage());
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothSocket bluetoothSocket) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] startReadThread. max receiver size =" + bluetoothSocket.getMaxReceivePacketSize());
        this.f3989c = true;
        while (this.f3989c) {
            try {
                this.f3991e = new DataInputStream(bluetoothSocket.getInputStream());
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = this.f3991e.read(bArr);
                    if (i != -1) {
                        a(bArr, i);
                    }
                }
            } catch (IOException e2) {
                LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] startReadThread. " + e2.getMessage());
                b();
            }
        }
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] exit read thread.");
    }

    private void a(String str) {
        com.ido.ble.bluetooth.bt.b.b().a(str, new b());
    }

    private void a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] receive <= " + com.ido.ble.common.c.b(bArr2));
        u.b(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothSocket bluetoothSocket) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] startWriteThread. max send size =" + bluetoothSocket.getMaxTransmitPacketSize());
        this.f3992f = true;
        while (true) {
            this.f3994h.lock();
            try {
                try {
                    if (this.f3993g.isEmpty()) {
                        this.i.await();
                    }
                } catch (InterruptedException e2) {
                    Log.e(com.ido.ble.logs.a.q, e2.getMessage(), e2);
                }
                if (!this.f3992f) {
                    this.f3994h.unlock();
                    LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] exit write thread. ");
                    return;
                } else {
                    b(this.f3993g.poll());
                    this.f3994h.unlock();
                }
            } catch (Throwable th) {
                this.f3994h.unlock();
                throw th;
            }
        }
    }

    private boolean b(byte[] bArr) {
        String str;
        if (bArr == null) {
            return false;
        }
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] send[" + bArr.length + "] => " + com.ido.ble.common.c.b(bArr));
        if (a()) {
            try {
                this.f3987a.getOutputStream().write(bArr);
                this.f3987a.getOutputStream().flush();
                u.A();
                return true;
            } catch (IOException e2) {
                str = "[SPPConnectManager] write() " + e2.getMessage();
            }
        } else {
            str = "[SPPConnectManager] write(). not connected.";
        }
        LogTool.b(com.ido.ble.logs.a.q, str);
        return false;
    }

    private void c(BluetoothSocket bluetoothSocket) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] success.");
        this.f3987a = bluetoothSocket;
        ISPPConnectStateListener iSPPConnectStateListener = this.f3988b;
        if (iSPPConnectStateListener != null) {
            iSPPConnectStateListener.onSuccess();
        }
        this.f3990d.execute(new c(bluetoothSocket));
        this.f3990d.execute(new d(bluetoothSocket));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogTool.b(com.ido.ble.logs.a.q, "[SPPConnectManager] failed.");
        ISPPConnectStateListener iSPPConnectStateListener = this.f3988b;
        if (iSPPConnectStateListener != null) {
            iSPPConnectStateListener.onFailed();
        }
        f();
    }

    public static f e() {
        if (j == null) {
            j = new f();
        }
        return j;
    }

    private void f() {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] release. ");
        DataInputStream dataInputStream = this.f3991e;
        if (dataInputStream != null) {
            try {
                dataInputStream.close();
            } catch (IOException unused) {
            }
        }
        this.f3993g.clear();
        this.f3992f = false;
        this.f3991e = null;
        this.f3989c = false;
        this.f3987a = null;
        this.f3988b = null;
        this.f3994h.lock();
        this.i.signal();
        this.f3994h.unlock();
    }

    public void a(ISPPConnectStateListener iSPPConnectStateListener) {
        com.ido.ble.bluetooth.bt.d.b().a(new a(iSPPConnectStateListener));
    }

    public void a(String str, ISPPConnectStateListener iSPPConnectStateListener) {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] connect " + str);
        this.f3989c = false;
        this.f3988b = iSPPConnectStateListener;
        ISPPConnectStateListener iSPPConnectStateListener2 = this.f3988b;
        if (iSPPConnectStateListener2 != null) {
            iSPPConnectStateListener2.onStart();
        }
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            a(str);
        } else {
            LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] connect, bluetooth is disEnable ");
            d();
        }
    }

    public void a(byte[] bArr) {
        this.f3994h.lock();
        this.f3993g.add(bArr);
        this.i.signal();
        this.f3994h.unlock();
    }

    public boolean a() {
        BluetoothSocket bluetoothSocket = this.f3987a;
        return bluetoothSocket != null && bluetoothSocket.isConnected();
    }

    public void b() {
        LogTool.b(com.ido.ble.logs.a.q, "[SPPConnectManager] onBreak. ");
        ISPPConnectStateListener iSPPConnectStateListener = this.f3988b;
        if (iSPPConnectStateListener != null) {
            iSPPConnectStateListener.onBreak();
        }
        f();
    }

    public void c() {
        LogTool.d(com.ido.ble.logs.a.q, "[SPPConnectManager] toDisconnect. ");
        BluetoothSocket bluetoothSocket = this.f3987a;
        if (bluetoothSocket != null) {
            try {
                bluetoothSocket.close();
            } catch (IOException e2) {
                LogTool.b(com.ido.ble.logs.a.q, "[SPPConnectManager] disconnect. " + e2.getMessage());
            }
        }
    }
}