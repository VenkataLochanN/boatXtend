package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.connect.q.b;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.e.a;
import com.ido.ble.logs.LogTool;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
abstract class b {
    private static final long i = 3000;
    private static final long j = 35000;
    private static final long k = 10000;
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BluetoothGatt f4010d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.ido.ble.bluetooth.connect.j f4011e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BLEDevice f4013g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4007a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4008b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4009c = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Handler f4012f = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4014h = true;

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothGatt f4015a;

        a(BluetoothGatt bluetoothGatt) {
            this.f4015a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.ido.ble.bluetooth.e.a.a(this.f4015a, true)) {
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] enablePeerDeviceNotifyHealth reEnable ok");
            } else {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] enablePeerDeviceNotifyHealth reEnable failed");
                b.this.A();
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.bluetooth.connect.b$b, reason: collision with other inner class name */
    class RunnableC0052b implements Runnable {
        RunnableC0052b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(0L);
            b.this.x();
            b.this.m();
        }
    }

    class c implements b.InterfaceC0057b {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.x();
                b.this.g();
            }
        }

        c() {
        }

        @Override // com.ido.ble.bluetooth.connect.q.b.InterfaceC0057b
        public void onFailed() {
            if (b.this.f4007a == 1) {
                return;
            }
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] encrypted failed.");
            b.this.f4012f.post(new a());
        }

        @Override // com.ido.ble.bluetooth.connect.q.b.InterfaceC0057b
        public void onSuccess() {
            if (b.this.f4007a == 1) {
                return;
            }
            b.this.f4008b = true;
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] connect success");
            b bVar = b.this;
            bVar.b(bVar.f4013g);
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(3000L);
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.v();
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.u();
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.z();
        }
    }

    class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothGatt f4024a;

        h(BluetoothGatt bluetoothGatt) {
            this.f4024a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4024a.discoverServices()) {
                return;
            }
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] discover services failed again");
            b.this.f4011e.c();
            b.this.z();
        }
    }

    class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(0L);
            b.this.x();
            b.this.l();
        }
    }

    class j implements a.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothGatt f4027a;

        j(BluetoothGatt bluetoothGatt) {
            this.f4027a = bluetoothGatt;
        }

        @Override // com.ido.ble.e.a.d
        public void a(String str) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] create bond finished.");
            b.this.c(this.f4027a);
        }
    }

    class k implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothGatt f4029a;

        k(BluetoothGatt bluetoothGatt) {
            this.f4029a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.c(this.f4029a);
        }
    }

    class l implements Runnable {
        l() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.x();
            b bVar = b.this;
            bVar.c(bVar.f4013g);
        }
    }

    class m implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothGatt f4032a;

        m(BluetoothGatt bluetoothGatt) {
            this.f4032a = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.ido.ble.bluetooth.e.a.b(this.f4032a, true)) {
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] enablePeerDeviceNotifyNormal reEnable ok");
            } else {
                b.this.B();
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] enablePeerDeviceNotifyNormal reEnable failed");
            }
        }
    }

    class n implements Runnable {
        n() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(0L);
            b.this.x();
            b.this.n();
        }
    }

    class o extends BluetoothGattCallback {

        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ BluetoothGatt f4036a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f4037b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f4038c;

            a(BluetoothGatt bluetoothGatt, int i, int i2) {
                this.f4036a = bluetoothGatt;
                this.f4037b = i;
                this.f4038c = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.a(this.f4036a, this.f4037b, this.f4038c);
            }
        }

        o() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            b.this.a(bluetoothGatt, bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            b.this.a(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            b.this.f4011e.b();
            b.this.f4011e.a();
            if (b.this.f4014h) {
                com.ido.ble.common.e.a(new a(bluetoothGatt, i, i2));
            } else {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:onConnectionStateChange()] onConnectionStateChange is called, but mIsNeedHandGattCallback is false");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (!com.ido.ble.bluetooth.e.f.f4137c.equals(bluetoothGattDescriptor.getUuid())) {
                    return;
                }
                if (bluetoothGattDescriptor.getValue()[0] == 1) {
                    if (com.ido.ble.bluetooth.e.f.j.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                        b.this.b(bluetoothGatt);
                        return;
                    } else {
                        if (com.ido.ble.bluetooth.e.f.l.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                            b.this.y();
                            return;
                        }
                        return;
                    }
                }
            }
            b.this.n();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            b.this.f4011e.c();
            if (i == 0) {
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:servicesDiscovered()] discoverServices ok!");
                b.this.a(bluetoothGatt);
            } else {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:servicesDiscovered()] discoverServices failed");
                b.this.z();
            }
        }
    }

    b() {
        D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        com.ido.ble.common.e.a(new RunnableC0052b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        com.ido.ble.common.e.a(new n());
    }

    private void C() {
        com.ido.ble.common.e.a(new l());
    }

    private void D() {
        this.f4011e = new com.ido.ble.bluetooth.connect.o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j2) {
        w();
        if (this.f4010d == null) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] disconnect failed, mBluetoothGatt is null.");
            v();
            return;
        }
        if (j2 != 0) {
            this.f4014h = true;
            this.f4011e.a(new e(), j2);
        } else {
            this.f4014h = false;
        }
        this.f4010d.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothGatt bluetoothGatt) {
        if (d(bluetoothGatt)) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] device in dfu mode");
            C();
            return;
        }
        if (e(bluetoothGatt)) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] device  dw02 in dfu mode");
            c(this.f4013g);
        }
        if (!com.ido.ble.e.a.a(bluetoothGatt)) {
            this.f4012f.postDelayed(new k(bluetoothGatt), 100L);
        } else {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] is need create bond");
            com.ido.ble.e.a.c().a(bluetoothGatt.getDevice(), new j(bluetoothGatt));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothGatt bluetoothGatt, int i2, int i3) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] status = " + i2 + ",newState = " + i3);
        if (i2 == 0) {
            if (i3 == 2) {
                b(bluetoothGatt, i2, i3);
                return;
            }
        } else if (this.f4007a != 3) {
            d(i2, i3);
            return;
        }
        c(i2, i3);
    }

    private void a(String str) {
        BluetoothDevice bluetoothDeviceA = com.ido.ble.bluetooth.e.e.a(str);
        if (bluetoothDeviceA == null) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] printPhoneEnvInfo, not paired!");
        } else {
            boolean zA = com.ido.ble.bluetooth.e.e.a(bluetoothDeviceA);
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] printPhoneEnvInfo, has paired, isConnectedByPhone=" + zA);
            if (CustomConfig.getConfig().isNeedRemoveBondBeforeConnect() && !com.ido.ble.bluetooth.e.e.b()) {
                boolean zB = com.ido.ble.bluetooth.e.e.b(bluetoothDeviceA);
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] printPhoneEnvInfo, remove bond status is =" + zB);
            }
        }
        String strB = com.ido.ble.common.l.b();
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] printPhoneEnvInfo, " + strB);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothGatt bluetoothGatt) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] start to enablePeerDeviceNotifyHealth...");
        if (com.ido.ble.bluetooth.e.a.a(bluetoothGatt, true)) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] enablePeerDeviceNotifyHealth ok");
        } else {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] enablePeerDeviceNotifyHealth failed, retry...");
            this.f4012f.postDelayed(new a(bluetoothGatt), 50L);
        }
    }

    private void b(BluetoothGatt bluetoothGatt, int i2, int i3) {
        if (this.f4007a == 3) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] in connected state, not do next steps!");
            return;
        }
        this.f4007a = 3;
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] gatt connected.");
        g(bluetoothGatt);
    }

    private void c(int i2, int i3) {
        if (this.f4007a != 3) {
            x();
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] connect failed");
            b(i2, i3);
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] connect break");
        x();
        a(i2, i3);
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] bluetooth is open");
        } else {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] user close bluetooth");
            com.ido.ble.callback.d.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothGatt bluetoothGatt) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] start to enablePeerDeviceNotifyNormal...");
        if (com.ido.ble.bluetooth.e.a.b(bluetoothGatt, true)) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] enablePeerDeviceNotifyNormal ok");
        } else {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] enablePeerDeviceNotifyNormal failed, retry...");
            this.f4012f.postDelayed(new m(bluetoothGatt), 50L);
        }
    }

    private void d(int i2, int i3) {
        x();
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] connect failed");
        b(i2, i3);
    }

    private boolean d(BluetoothGatt bluetoothGatt) {
        return (bluetoothGatt.getService(com.ido.ble.bluetooth.e.f.f4138d) == null && bluetoothGatt.getService(com.ido.ble.bluetooth.e.f.f4139e) == null) ? false : true;
    }

    private boolean e(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.getService(com.ido.ble.bluetooth.e.f.f4140f) != null;
    }

    private void f(BluetoothGatt bluetoothGatt) {
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            try {
                Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
                if (method != null) {
                    method.invoke(bluetoothGatt, new Object[0]);
                }
            } catch (Exception e2) {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, e2.toString());
            }
        }
    }

    private void g(BluetoothGatt bluetoothGatt) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] start to discoverServices...");
        this.f4011e.b(new g(), 10000L);
        if (bluetoothGatt.discoverServices()) {
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect:connectionStateChange()] discover services failed, retry...");
        this.f4012f.postDelayed(new h(bluetoothGatt), 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] callConnectMethodSystemNoRespond()");
        x();
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] callDisconnectMethodSystemNoRespond()");
        x();
        a(255, 255);
    }

    private void w() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("you should call this method on Main-Thread.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] close()");
        w();
        com.ido.ble.bluetooth.connect.j jVar = this.f4011e;
        if (jVar != null) {
            jVar.b();
            this.f4011e.a();
            this.f4011e.c();
        }
        this.f4007a = 1;
        this.f4008b = false;
        this.f4009c = false;
        this.f4012f.removeCallbacksAndMessages(null);
        BluetoothGatt bluetoothGatt = this.f4010d;
        if (bluetoothGatt != null) {
            f(bluetoothGatt);
            this.f4010d.close();
            this.f4010d = null;
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        this.f4009c = true;
        com.ido.ble.bluetooth.connect.q.b.b().a(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        com.ido.ble.common.e.a(new i());
    }

    protected abstract void a(int i2, int i3);

    protected abstract void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    protected abstract void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2);

    protected abstract void b(int i2, int i3);

    protected abstract void b(BLEDevice bLEDevice);

    protected void b(BLEDevice bLEDevice, long j2) {
        a(bLEDevice.mDeviceAddress);
        if (j2 < j) {
            j2 = 35000;
        }
        this.f4013g = bLEDevice;
        if (bLEDevice.mIsInDfuMode) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] device in dfu mode, not to connect , address is " + bLEDevice.mDeviceAddress);
            C();
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] connect() , address is " + bLEDevice.mDeviceAddress);
        w();
        if (!BluetoothAdapter.checkBluetoothAddress(bLEDevice.mDeviceAddress)) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] connect() is refused, address is invalid");
            h();
            return;
        }
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] connect() is refused, bluetooth is closed");
            f();
            return;
        }
        int i2 = this.f4007a;
        if (i2 == 2 || i2 == 3) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] connect() is refused, state = " + this.f4007a);
            k();
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] start to connect " + bLEDevice.mDeviceAddress);
        i();
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bLEDevice.mDeviceAddress);
        this.f4011e.c(new f(), j2);
        this.f4014h = true;
        this.f4010d = (Build.VERSION.SDK_INT < 23 || remoteDevice.getType() != 3) ? remoteDevice.connectGatt(com.ido.ble.common.e.a(), false, new o()) : remoteDevice.connectGatt(com.ido.ble.common.e.a(), false, new o(), 2);
        this.f4007a = 2;
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] connecting " + bLEDevice.mDeviceAddress);
        k();
    }

    protected abstract void c(BLEDevice bLEDevice);

    protected void d(BLEDevice bLEDevice) {
        b(bLEDevice, j);
    }

    protected abstract void e();

    protected abstract void f();

    protected abstract void g();

    protected abstract void h();

    protected abstract void i();

    protected abstract void j();

    protected abstract void k();

    protected abstract void l();

    protected abstract void m();

    protected abstract void n();

    protected void o() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[BaseConnect] to disconnect.");
        this.f4008b = false;
        com.ido.ble.common.e.a(new d());
    }

    protected BLEDevice p() {
        return this.f4013g;
    }

    protected BluetoothGatt q() {
        return this.f4010d;
    }

    protected boolean r() {
        return this.f4009c && this.f4010d != null;
    }

    protected boolean s() {
        return this.f4008b && this.f4010d != null;
    }

    protected boolean t() {
        int i2;
        return this.f4010d != null && ((i2 = this.f4007a) == 3 || i2 == 2);
    }
}