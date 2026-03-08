package com.loc;

import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.ArrayList;

/* JADX INFO: compiled from: Req.java */
/* JADX INFO: loaded from: classes3.dex */
public final class eh {
    protected static String J;
    protected static String L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5142a = "1";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected short f5143b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String f5144c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected String f5145d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected String f5146e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected String f5147f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected String f5148g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f5149h = null;
    public String i = null;
    protected String j = null;
    protected String k = null;
    protected String l = null;
    protected String m = null;
    protected String n = null;
    protected String o = null;
    protected String p = null;
    protected String q = null;
    protected String r = null;
    protected String s = null;
    protected String t = null;
    protected String u = null;
    protected String v = null;
    protected String w = null;
    protected String x = null;
    protected String y = null;
    protected int z = 0;
    protected String A = null;
    protected String B = null;
    protected ArrayList<dv> C = new ArrayList<>();
    protected String D = null;
    protected String E = null;
    protected ArrayList<ScanResult> F = new ArrayList<>();
    protected String G = null;
    protected String H = null;
    protected byte[] I = null;
    private byte[] O = null;
    private int P = 0;
    protected String K = null;
    protected String M = null;
    protected String N = null;

    private static int a(String str, byte[] bArr, int i) {
        try {
        } catch (Throwable th) {
            ej.a(th, "Req", "copyContentWithByteLen");
            bArr[i] = 0;
        }
        if (TextUtils.isEmpty(str)) {
            bArr[i] = 0;
            return i + 1;
        }
        byte[] bytes = str.getBytes("GBK");
        int length = bytes.length;
        if (length > 127) {
            length = 127;
        }
        bArr[i] = (byte) length;
        int i2 = i + 1;
        System.arraycopy(bytes, 0, bArr, i2, length);
        return i2 + length;
    }

    private String a(String str, int i) {
        String[] strArrSplit = this.B.split("\\*")[i].split(AppInfo.DELIM);
        if ("lac".equals(str)) {
            return strArrSplit[0];
        }
        if ("cellid".equals(str)) {
            return strArrSplit[1];
        }
        if ("signal".equals(str)) {
            return strArrSplit[2];
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000f A[Catch: all -> 0x003e, TryCatch #0 {all -> 0x003e, blocks: (B:4:0x000c, B:10:0x001c, B:12:0x001f, B:14:0x0028, B:15:0x0030, B:6:0x000f, B:8:0x0014), top: B:20:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = ":"
            java.lang.String[] r0 = r7.split(r0)
            r1 = 6
            byte[] r2 = new byte[r1]
            r3 = 0
            if (r0 == 0) goto Lf
            int r4 = r0.length     // Catch: java.lang.Throwable -> L3e
            if (r4 == r1) goto L1b
        Lf:
            java.lang.String[] r0 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L3e
            r4 = r3
        L12:
            if (r4 >= r1) goto L1b
            java.lang.String r5 = "0"
            r0[r4] = r5     // Catch: java.lang.Throwable -> L3e
            int r4 = r4 + 1
            goto L12
        L1b:
            r1 = r3
        L1c:
            int r4 = r0.length     // Catch: java.lang.Throwable -> L3e
            if (r1 >= r4) goto L58
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            int r4 = r4.length()     // Catch: java.lang.Throwable -> L3e
            r5 = 2
            if (r4 <= r5) goto L30
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            java.lang.String r4 = r4.substring(r3, r5)     // Catch: java.lang.Throwable -> L3e
            r0[r1] = r4     // Catch: java.lang.Throwable -> L3e
        L30:
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            r5 = 16
            int r4 = java.lang.Integer.parseInt(r4, r5)     // Catch: java.lang.Throwable -> L3e
            byte r4 = (byte) r4     // Catch: java.lang.Throwable -> L3e
            r2[r1] = r4     // Catch: java.lang.Throwable -> L3e
            int r1 = r1 + 1
            goto L1c
        L3e:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getMacBa "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            java.lang.String r1 = "Req"
            com.loc.ej.a(r0, r1, r7)
            java.lang.String r7 = "00:00:00:00:00:00"
            byte[] r2 = r6.a(r7)
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eh.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        if (TextUtils.isEmpty(this.A)) {
            return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        if (!this.A.contains(str + ">")) {
            return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        return this.A.substring(this.A.indexOf(str + ">") + str.length() + 1, this.A.indexOf("</" + str));
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.content.Context r23, boolean r24, boolean r25, com.loc.dw r26, com.loc.dx r27, android.net.ConnectivityManager r28, java.lang.String r29) {
        /*
            Method dump skipped, instruction units count: 857
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eh.a(android.content.Context, boolean, boolean, com.loc.dw, com.loc.dx, android.net.ConnectivityManager, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0439  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x045b A[PHI: r14
  0x045b: PHI (r14v66 int) = (r14v65 int), (r14v74 int) binds: [B:102:0x0361, B:128:0x0437] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04ed  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x05bf  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x05c2 A[Catch: all -> 0x05d5, TryCatch #2 {all -> 0x05d5, blocks: (B:204:0x05b4, B:208:0x05c2, B:209:0x05c7), top: B:251:0x05b4 }] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x05c7 A[Catch: all -> 0x05d5, TRY_LEAVE, TryCatch #2 {all -> 0x05d5, blocks: (B:204:0x05b4, B:208:0x05c2, B:209:0x05c7), top: B:251:0x05b4 }] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x05e8 A[Catch: all -> 0x060a, TRY_LEAVE, TryCatch #8 {all -> 0x060a, blocks: (B:214:0x05e0, B:216:0x05e8), top: B:261:0x05e0 }] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0622  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0625  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0634  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x05fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02a1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0334 A[PHI: r0
  0x0334: PHI (r0v68 int) = (r0v67 int), (r0v67 int), (r0v128 int) binds: [B:87:0x0316, B:89:0x031d, B:246:0x0334] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] a() {
        /*
            Method dump skipped, instruction units count: 1671
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eh.a():byte[]");
    }
}