package com.ido.ble.bluetooth.connect.r;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.event.stat.one.d;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f4113a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f4114b = 0;

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void a() {
        com.ido.ble.event.stat.one.c.a((System.currentTimeMillis() - this.f4114b) / 1000, "", d.S, "" + System.currentTimeMillis() + "--" + this.f4114b);
        this.f4113a = System.currentTimeMillis();
        this.f4114b = 0L;
        com.ido.ble.g.a.c.d.c();
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void a(int i, int i2) {
        String str = "(" + i + AppInfo.DELIM + i2 + ")";
        com.ido.ble.event.stat.one.c.d(d.W + str);
        com.ido.ble.event.stat.one.c.c(d.S);
        com.ido.ble.g.a.c.d.c(d.W + str);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void b() {
        this.f4114b = System.currentTimeMillis();
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void b(int i, int i2) {
        com.ido.ble.event.stat.one.c.b("status=" + i + ",newState=" + i2);
        com.ido.ble.event.stat.one.c.a(((System.currentTimeMillis() - this.f4113a) / 1000) / 60);
        if (System.currentTimeMillis() - this.f4113a < BootloaderScanner.TIMEOUT) {
            com.ido.ble.g.a.c.d.d("status=" + i + ",newState=" + i2);
        }
        this.f4113a = 0L;
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void c() {
        com.ido.ble.g.a.c.d.d();
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void d() {
        com.ido.ble.event.stat.one.c.d(d.T);
        com.ido.ble.event.stat.one.c.c(d.S);
        com.ido.ble.g.a.c.d.c(d.T);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void e() {
        com.ido.ble.event.stat.one.c.d(d.V);
        com.ido.ble.event.stat.one.c.c(d.S);
        com.ido.ble.g.a.c.d.c(d.V);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void f() {
        com.ido.ble.event.stat.one.c.d(d.U);
        com.ido.ble.event.stat.one.c.c(d.S);
        com.ido.ble.g.a.c.d.c(d.U);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void g() {
    }
}