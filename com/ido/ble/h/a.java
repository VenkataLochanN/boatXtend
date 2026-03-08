package com.ido.ble.h;

import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ControlGps;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static int a() {
        return b.a();
    }

    public static int a(int i) {
        return b.a(i);
    }

    public static int a(byte[] bArr, int i) {
        return b.a(bArr, i);
    }

    public static void a(ConfigGPS configGPS) {
        b.a(configGPS);
    }

    public static void a(ConnParam connParam) {
        b.a(connParam);
    }

    public static void a(ControlGps controlGps) {
        b.a(controlGps);
    }

    public static void a(GpsHotStartParam gpsHotStartParam) {
        b.a(gpsHotStartParam);
    }

    public static void b() {
        b.b();
    }

    public static void c() {
        b.c();
    }

    public static void d() {
        b.d();
    }

    public static boolean e() {
        return b.e();
    }

    public static void f() {
        ConfigGPS configGPSU = com.ido.ble.f.a.f.a.c0().u();
        if (configGPSU == null) {
            configGPSU = new ConfigGPS();
            configGPSU.startMode = 2;
            configGPSU.operationMode = 1;
            configGPSU.cycleMS = 1000;
            configGPSU.gnsValue = 1;
        }
        a(configGPSU);
    }

    public static void g() {
        LogTool.d(com.ido.ble.logs.a.j, "start sync gps data...");
        GpsCallBack.g();
        if (b.f() != 0) {
            GpsCallBack.e();
        }
    }

    public static int h() {
        return b.g();
    }

    public static void i() {
        LogTool.d(com.ido.ble.logs.a.j, "stop sync gps data...");
        u.w();
    }

    public static int j() {
        return b.h();
    }

    public static int k() {
        return b.i();
    }
}