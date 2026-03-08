package com.amap.api.mapcore.util;

import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.ArrayList;

/* JADX INFO: compiled from: Req.java */
/* JADX INFO: loaded from: classes.dex */
public final class ke {
    protected static String J;
    protected static String L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1526a = "1";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected short f1527b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String f1528c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected String f1529d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected String f1530e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected String f1531f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected String f1532g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f1533h = null;
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
    protected ArrayList<li> C = new ArrayList<>();
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
            kg.a(th, "Req", "copyContentWithByteLen");
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
            com.amap.api.mapcore.util.kg.a(r0, r1, r7)
            java.lang.String r7 = "00:00:00:00:00:00"
            byte[] r2 = r6.a(r7)
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ke.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        if (!this.A.contains(str + ">")) {
            return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        return this.A.substring(this.A.indexOf(str + ">") + str.length() + 1, this.A.indexOf("</" + str));
    }

    private void b() {
        String[] strArr = new String[28];
        strArr[0] = this.f1526a;
        strArr[1] = this.f1528c;
        strArr[2] = this.f1529d;
        strArr[3] = this.f1530e;
        strArr[4] = this.f1531f;
        strArr[5] = this.f1532g;
        strArr[6] = this.f1533h;
        strArr[7] = this.i;
        strArr[8] = this.l;
        strArr[9] = this.m;
        strArr[10] = this.n;
        strArr[11] = this.o;
        strArr[12] = this.p;
        strArr[13] = this.q;
        strArr[14] = this.r;
        strArr[15] = this.s;
        strArr[16] = this.t;
        strArr[17] = this.u;
        strArr[18] = this.v;
        strArr[19] = this.w;
        strArr[20] = this.x;
        strArr[21] = this.A;
        strArr[22] = this.B;
        strArr[23] = this.E;
        strArr[24] = this.G;
        strArr[25] = this.H;
        strArr[26] = J;
        strArr[27] = this.N;
        for (int i = 0; i < 28; i++) {
            if (TextUtils.isEmpty(strArr[i])) {
                strArr[i] = "";
            }
        }
        if (TextUtils.isEmpty(this.j) || (!AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(this.j) && !"2".equals(this.j))) {
            this.j = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        if (TextUtils.isEmpty(this.k) || (!AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(this.k) && !"1".equals(this.k))) {
            this.k = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        if (TextUtils.isEmpty(this.y) || (!"1".equals(this.y) && !"2".equals(this.y))) {
            this.y = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        if (!lj.a(this.z)) {
            this.z = 0;
        }
        if (this.I == null) {
            this.I = new byte[0];
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.content.Context r23, boolean r24, boolean r25, com.amap.api.mapcore.util.lj r26, com.amap.api.mapcore.util.ka r27, android.net.ConnectivityManager r28, java.lang.String r29) {
        /*
            Method dump skipped, instruction units count: 857
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ke.a(android.content.Context, boolean, boolean, com.amap.api.mapcore.util.lj, com.amap.api.mapcore.util.ka, android.net.ConnectivityManager, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0335 A[PHI: r14
  0x0335: PHI (r14v59 int) = (r14v58 int), (r14v67 int) binds: [B:66:0x023d, B:92:0x0311] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x048d A[Catch: all -> 0x04a0, TryCatch #0 {all -> 0x04a0, blocks: (B:166:0x047f, B:170:0x048d, B:171:0x0492), top: B:209:0x047f }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0492 A[Catch: all -> 0x04a0, TRY_LEAVE, TryCatch #0 {all -> 0x04a0, blocks: (B:166:0x047f, B:170:0x048d, B:171:0x0492), top: B:209:0x047f }] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x04b3 A[Catch: all -> 0x04d5, TRY_LEAVE, TryCatch #4 {all -> 0x04d5, blocks: (B:176:0x04ab, B:178:0x04b3), top: B:216:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x04ed  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x04c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0182 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0210 A[PHI: r0
  0x0210: PHI (r0v48 int) = (r0v47 int), (r0v47 int), (r0v108 int) binds: [B:51:0x01f4, B:53:0x01f9, B:208:0x0210] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0313  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] a() {
        /*
            Method dump skipped, instruction units count: 1361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ke.a():byte[]");
    }
}