package com.ido.ble.common;

import android.text.TextUtils;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f4219a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4220b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f4221c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f4222d = 3;

    private static String a() {
        long j;
        String str = com.ido.ble.f.a.f.b.e().c().mDeviceAddress;
        if (TextUtils.isEmpty(str)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] getCurrentDeviceUniqueId:macAddress is null.");
            return "";
        }
        try {
            j = Long.parseLong(str.replaceAll("[a-zA-Z:]", ""));
        } catch (Exception e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.f4635c + e2.getMessage());
            j = -1L;
        }
        if (j == -1) {
            return "";
        }
        return j + "";
    }

    public static void a(int i, int i2) {
        if (h.a(i)) {
            return;
        }
        com.ido.ble.f.a.f.a.c0().a(com.ido.ble.f.a.f.a.c0().j() + i2);
    }

    public static void b() {
        if (!e.c()) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] is not support fast sync, reset all offset!");
            f();
            g();
            return;
        }
        if (c()) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] isNeedRestOffset = true, reset all offset!");
            f();
        }
        g();
        if (d()) {
            com.ido.ble.i.a.a.n0();
        }
        com.ido.ble.f.a.f.a.c0().a(System.currentTimeMillis());
        com.ido.ble.f.a.f.a.c0().d(a());
    }

    public static void b(int i, int i2) {
        if (h.a(i)) {
            return;
        }
        com.ido.ble.f.a.f.a.c0().b(com.ido.ble.f.a.f.a.c0().z() + i2);
    }

    public static void c(int i, int i2) {
        if (h.a(i)) {
            return;
        }
        com.ido.ble.f.a.f.a.c0().d(com.ido.ble.f.a.f.a.c0().U() + i2);
    }

    private static boolean c() {
        long jB = com.ido.ble.f.a.f.a.c0().B();
        return (e() && jB != 0 && TimeUtil.isSameDay(jB, System.currentTimeMillis())) ? false : true;
    }

    private static boolean d() {
        if (System.currentTimeMillis() - com.ido.ble.f.a.f.a.c0().B() <= 7200000) {
            return false;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] The time space of last sync health data was more than 2 hours");
        return true;
    }

    private static boolean e() {
        String strA = com.ido.ble.f.a.f.a.c0().A();
        return !TextUtils.isEmpty(strA) && strA.equals(a());
    }

    private static void f() {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] reset all offset!");
        com.ido.ble.f.a.f.a.c0().d(0);
        com.ido.ble.f.a.f.a.c0().b(0);
        com.ido.ble.f.a.f.a.c0().a(0);
    }

    private static void g() {
        com.ido.ble.i.a.a.a(0, com.ido.ble.f.a.f.a.c0().U());
        com.ido.ble.i.a.a.a(2, com.ido.ble.f.a.f.a.c0().z());
        com.ido.ble.i.a.a.a(3, com.ido.ble.f.a.f.a.c0().j());
    }
}