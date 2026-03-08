package com.ido.ble.event.stat.one;

import android.content.pm.PackageManager;
import android.os.Build;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static String a() {
        PackageManager packageManager = com.ido.ble.common.e.a().getPackageManager();
        try {
            String string = packageManager.getApplicationLabel(packageManager.getApplicationInfo(com.ido.ble.common.e.a().getPackageName(), 128)).toString();
            return string.replace(" ", "_") + "_" + packageManager.getPackageInfo(com.ido.ble.common.e.a().getPackageName(), 0).versionName;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void a(com.ido.ble.g.a.a aVar) {
        d(aVar);
        c(aVar);
        b(aVar);
    }

    public static String b() {
        return com.ido.ble.bluetooth.a.h() ? "[连接:正常]" : "[连接:断线]";
    }

    private static void b(com.ido.ble.g.a.a aVar) {
        aVar.a(d.j, a());
        aVar.a(d.k, "IDoBLELib-2.65.89");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void c(com.ido.ble.g.a.a r7) {
        /*
            com.ido.ble.f.a.f.b r0 = com.ido.ble.f.a.f.b.e()
            com.ido.ble.bluetooth.device.BLEDevice r0 = r0.c()
            java.lang.String r1 = ""
            java.lang.String r2 = "device_id"
            java.lang.String r3 = "device_mac_address"
            java.lang.String r4 = "device_name"
            java.lang.String r5 = "null"
            if (r0 == 0) goto L44
            java.lang.String r6 = r0.mDeviceAddress
            r7.a(r3, r6)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r6 = r0.mDeviceId
            r3.append(r6)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            r7.a(r2, r3)
            java.lang.String r2 = r0.mDeviceName
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L36
            goto L4a
        L36:
            java.lang.String r0 = r0.mDeviceName
            java.lang.String r2 = " "
            java.lang.String r3 = "_"
            java.lang.String r0 = r0.replace(r2, r3)
            r7.a(r4, r0)
            goto L4d
        L44:
            r7.a(r3, r5)
            r7.a(r2, r5)
        L4a:
            r7.a(r4, r5)
        L4d:
            com.ido.ble.f.a.f.a r0 = com.ido.ble.f.a.f.a.c0()
            com.ido.ble.protocol.model.BasicInfo r0 = r0.h()
            java.lang.String r2 = "device_version"
            if (r0 == 0) goto L6e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r0 = r0.firmwareVersion
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            r7.a(r2, r0)
            goto L71
        L6e:
            r7.a(r2, r5)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.event.stat.one.a.c(com.ido.ble.g.a.a):void");
    }

    private static void d(com.ido.ble.g.a.a aVar) {
        aVar.a(d.f4392b, "android");
        aVar.a(d.f4393c, Build.VERSION.RELEASE);
        aVar.a(d.f4394d, Build.MODEL.replace(" ", "_"));
        aVar.a(d.f4395e, TimeZone.getDefault().getID());
    }
}