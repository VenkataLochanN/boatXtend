package com.ido.ble.dfu;

import com.ido.ble.protocol.model.BasicInfo;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static void a() {
        BasicInfo basicInfoH = com.ido.ble.f.a.f.a.c0().h();
        if (basicInfoH == null || basicInfoH.platform != 10) {
            com.ido.ble.dfu.e.a.d().a();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0019, code lost:
    
        if (r0.platform == 10) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(com.ido.ble.dfu.BleDFUConfig r3) {
        /*
            int r0 = r3.getPlatform()
            r1 = 10
            r2 = -1
            if (r0 != r2) goto L1c
            java.lang.String r0 = r3.getMacAddress()
            com.ido.ble.f.a.f.a r0 = com.ido.ble.f.a.f.a.f(r0)
            com.ido.ble.protocol.model.BasicInfo r0 = r0.h()
            if (r0 == 0) goto L22
            int r0 = r0.platform
            if (r0 != r1) goto L22
            goto L30
        L1c:
            int r0 = r3.getPlatform()
            if (r0 != 0) goto L2a
        L22:
            com.ido.ble.dfu.e.a r0 = com.ido.ble.dfu.e.a.d()
            r0.a(r3)
            goto L37
        L2a:
            int r0 = r3.getPlatform()
            if (r0 != r1) goto L37
        L30:
            com.ido.ble.dfu.f.a r0 = com.ido.ble.dfu.f.a.b()
            r0.a(r3)
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.dfu.c.a(com.ido.ble.dfu.BleDFUConfig):void");
    }

    public static boolean b() {
        return com.ido.ble.dfu.e.a.d().b();
    }
}