package com.ido.ble.bluetooth.connect;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
class n extends d {
    private static h C;
    private com.ido.ble.bluetooth.connect.r.b A;
    private boolean B;

    private n(i iVar) {
        super(iVar);
        this.B = false;
        this.A = new com.ido.ble.bluetooth.connect.r.c();
    }

    public static h a(i iVar) {
        if (C == null) {
            C = new n(iVar);
        }
        return C;
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    protected void a(int i, int i2) {
        super.a(i, i2);
        this.z.a(i, i2, b());
        this.A.b(i, i2);
        if (!com.ido.ble.bluetooth.a.g() || this.B || !CustomConfig.getConfig().isAutoConnectIfBreak()) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[StraightConnectPresenter] connection break, will not to connect auto " + com.ido.ble.bluetooth.a.d());
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[StraightConnectPresenter] connection break, auto to connect " + com.ido.ble.bluetooth.a.d());
        com.ido.ble.bluetooth.a.a();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[StraightConnectPresenter] to connect device, bleDevice is " + bLEDevice.toString());
        d(bLEDevice);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice, long j) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[StraightConnectPresenter] to connect device, bleDevice is " + bLEDevice.toString());
        b(bLEDevice, j);
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public boolean a() {
        return t();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void b(int i, int i2) {
        this.z.a(i, i2);
        this.A.a(i, i2);
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.b
    protected void b(BLEDevice bLEDevice) {
        super.b(bLEDevice);
        this.A.a();
    }

    @Override // com.ido.ble.bluetooth.connect.d, com.ido.ble.bluetooth.connect.h
    public void d() {
        this.B = true;
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[StraightConnectPresenter] to disconnect.");
        super.d();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void f() {
        this.z.c();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void g() {
        this.z.d();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void h() {
        this.z.j();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void i() {
        this.B = false;
        this.z.onConnectStart(b());
        this.A.b();
    }

    @Override // com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    protected void j() {
        super.j();
        this.z.l();
        this.A.c();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void k() {
        this.z.onConnecting(b());
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void l() {
        this.z.h();
        this.A.d();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void m() {
        this.z.f();
        this.A.e();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void n() {
        this.z.f();
        this.A.f();
    }
}