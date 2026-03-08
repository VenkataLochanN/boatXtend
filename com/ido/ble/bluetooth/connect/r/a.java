package com.ido.ble.bluetooth.connect.r;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.common.m;
import com.ido.ble.event.stat.one.d;
import com.ido.life.util.DateUtil;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f4108a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f4109b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.ido.ble.event.stat.one.b f4110c = new com.ido.ble.event.stat.one.b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4111d = -1;

    /* JADX INFO: renamed from: com.ido.ble.bluetooth.connect.r.a$a, reason: collision with other inner class name */
    class C0058a implements m.b {
        C0058a() {
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            com.ido.ble.event.stat.one.c.a(false);
            a.this.f4111d = -1;
        }
    }

    private void a(String str) {
        this.f4110c.a(str);
    }

    private String h() {
        com.ido.ble.event.stat.one.b bVar = this.f4110c;
        return bVar != null ? bVar.b() : "null";
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void a() {
        if (m.a(this.f4111d)) {
            com.ido.ble.event.stat.one.c.a(true);
            this.f4111d = -1;
        }
        com.ido.ble.event.stat.one.c.a((System.currentTimeMillis() - this.f4109b) / 1000, h(), d.R, "" + System.currentTimeMillis() + "--" + this.f4109b);
        this.f4108a = System.currentTimeMillis();
        com.ido.ble.g.a.c.d.a();
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void a(int i, int i2) {
        String str = "(" + i + AppInfo.DELIM + i2 + ")";
        com.ido.ble.event.stat.one.c.d(d.W + str);
        a(d.W + str);
        com.ido.ble.g.a.c.d.a(d.W + str);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void b() {
        this.f4109b = System.currentTimeMillis();
        this.f4110c.a();
        this.f4111d = m.a(new C0058a(), DateUtil.MINUTE);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void b(int i, int i2) {
        com.ido.ble.event.stat.one.c.b("status=" + i + ",newState=" + i2);
        com.ido.ble.event.stat.one.c.a(((System.currentTimeMillis() - this.f4108a) / 1000) / 60);
        if (System.currentTimeMillis() - this.f4108a < BootloaderScanner.TIMEOUT) {
            com.ido.ble.g.a.c.d.b("status=" + i + ",newState=" + i2);
        }
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void c() {
        com.ido.ble.g.a.c.d.b();
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void d() {
        com.ido.ble.event.stat.one.c.d(d.T);
        a(d.T);
        com.ido.ble.g.a.c.d.a(d.T);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void e() {
        com.ido.ble.event.stat.one.c.d(d.V);
        a(d.V);
        com.ido.ble.g.a.c.d.a(d.V);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void f() {
        com.ido.ble.event.stat.one.c.d(d.U);
        a(d.U);
        com.ido.ble.g.a.c.d.a(d.U);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void g() {
        com.ido.ble.event.stat.one.c.d(d.Y);
        a(d.Y);
    }
}