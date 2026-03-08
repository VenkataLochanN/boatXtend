package com.ido.ble.h;

import com.ido.ble.common.k;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ControlGps;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.protocol.handler.u;

/* JADX INFO: loaded from: classes2.dex */
class b {
    b() {
    }

    static int a() {
        return u.b();
    }

    static int a(int i) {
        return u.c(i);
    }

    static int a(ConfigGPS configGPS) {
        return u.b(com.ido.ble.common.c.b(k.a(configGPS)), 155);
    }

    static int a(ConnParam connParam) {
        return u.b(com.ido.ble.common.c.b(k.a(connParam)), 157);
    }

    static int a(ControlGps controlGps) {
        return u.b(com.ido.ble.common.c.b(k.a(controlGps)), 156);
    }

    static int a(GpsHotStartParam gpsHotStartParam) {
        return u.b(com.ido.ble.common.c.b(k.a(gpsHotStartParam)), 158);
    }

    static int a(byte[] bArr, int i) {
        return u.a(bArr, i);
    }

    static int b() {
        return u.b(com.veryfit.multi.nativeprotocol.b.k5, com.veryfit.multi.nativeprotocol.b.X0);
    }

    static int c() {
        return u.b(com.veryfit.multi.nativeprotocol.b.k5, com.veryfit.multi.nativeprotocol.b.W0);
    }

    static int d() {
        return u.b(com.veryfit.multi.nativeprotocol.b.k5, com.veryfit.multi.nativeprotocol.b.Y0);
    }

    static boolean e() {
        return u.e();
    }

    static int f() {
        return u.p();
    }

    static int g() {
        return u.r();
    }

    static int h() {
        return u.y();
    }

    static int i() {
        return u.B();
    }
}