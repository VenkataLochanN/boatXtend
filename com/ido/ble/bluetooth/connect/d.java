package com.ido.ble.bluetooth.connect;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.handler.u;

/* JADX INFO: loaded from: classes2.dex */
abstract class d extends c implements h {
    i z;

    d(i iVar) {
        this.z = iVar;
    }

    @Override // com.ido.ble.bluetooth.connect.c, com.ido.ble.bluetooth.connect.b
    protected void a(int i, int i2) {
        super.a(i, i2);
        u.b(com.veryfit.multi.nativeprotocol.b.j5, 2);
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice) {
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public void a(BLEDevice bLEDevice, long j) {
    }

    public void a(String str, boolean z) {
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public void a(byte[] bArr) {
        a(bArr, false);
    }

    public String b() {
        BLEDevice bLEDeviceP = p();
        return bLEDeviceP == null ? "" : bLEDeviceP.mDeviceAddress;
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void b(BLEDevice bLEDevice) {
        com.ido.ble.bluetooth.b.b(bLEDevice);
        this.z.c(bLEDevice.mDeviceAddress);
    }

    @Override // com.ido.ble.bluetooth.connect.b
    protected void c(BLEDevice bLEDevice) {
        this.z.c(bLEDevice);
    }

    @Override // com.ido.ble.bluetooth.connect.h
    public boolean c() {
        return s();
    }

    public void d() {
        u.b(com.veryfit.multi.nativeprotocol.b.j5, 2);
        o();
    }
}