package com.tencent.bugly.proguard;

import android.content.Context;
import android.util.Pair;
import com.bumptech.glide.load.Key;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class w implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f5766a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f5767b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f5768c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f5769d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final byte[] f5770e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.tencent.bugly.crashreport.common.info.a f5771f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final com.tencent.bugly.crashreport.common.strategy.a f5772g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final s f5773h;
    private final v i;
    private final int j;
    private final u k;
    private final u l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;

    public w(Context context, int i, int i2, byte[] bArr, String str, String str2, u uVar, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, uVar, 2, 30000, z2, null);
    }

    public w(Context context, int i, int i2, byte[] bArr, String str, String str2, u uVar, int i3, int i4, boolean z, Map<String, String> map) {
        this.f5766a = 2;
        this.f5767b = 30000;
        this.m = null;
        this.p = 0;
        this.q = 0L;
        this.r = 0L;
        this.s = false;
        this.f5768c = context;
        this.f5771f = com.tencent.bugly.crashreport.common.info.a.a(context);
        this.f5770e = bArr;
        this.f5772g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.f5773h = s.a(context);
        this.i = v.a();
        this.j = i;
        this.m = str;
        this.n = str2;
        this.k = uVar;
        v vVar = this.i;
        this.l = null;
        this.f5769d = i2;
        if (i3 > 0) {
            this.f5766a = i3;
        }
        if (i4 > 0) {
            this.f5767b = i4;
        }
        this.s = z;
        this.o = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(com.tencent.bugly.proguard.ao r5, boolean r6, int r7, java.lang.String r8) {
        /*
            r4 = this;
            int r5 = r4.f5769d
            r0 = 630(0x276, float:8.83E-43)
            if (r5 == r0) goto L1a
            r0 = 640(0x280, float:8.97E-43)
            if (r5 == r0) goto L17
            r0 = 830(0x33e, float:1.163E-42)
            if (r5 == r0) goto L1a
            r0 = 840(0x348, float:1.177E-42)
            if (r5 == r0) goto L17
            java.lang.String r5 = java.lang.String.valueOf(r5)
            goto L1c
        L17:
            java.lang.String r5 = "userinfo"
            goto L1c
        L1a:
            java.lang.String r5 = "crash"
        L1c:
            r0 = 1
            r1 = 0
            if (r6 == 0) goto L2a
            java.lang.Object[] r7 = new java.lang.Object[r0]
            r7[r1] = r5
            java.lang.String r5 = "[Upload] Success: %s"
            com.tencent.bugly.proguard.y.a(r5, r7)
            goto L3d
        L2a:
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r2[r1] = r7
            r2[r0] = r5
            r5 = 2
            r2[r5] = r8
            java.lang.String r5 = "[Upload] Failed to upload(%d) %s: %s"
            com.tencent.bugly.proguard.y.e(r5, r2)
        L3d:
            long r0 = r4.q
            long r2 = r4.r
            long r0 = r0 + r2
            r2 = 0
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 <= 0) goto L5d
            com.tencent.bugly.proguard.v r5 = r4.i
            boolean r7 = r4.s
            long r0 = r5.a(r7)
            long r2 = r4.q
            long r0 = r0 + r2
            long r2 = r4.r
            long r0 = r0 + r2
            com.tencent.bugly.proguard.v r5 = r4.i
            boolean r7 = r4.s
            r5.a(r0, r7)
        L5d:
            com.tencent.bugly.proguard.u r5 = r4.k
            if (r5 == 0) goto L6a
            int r7 = r4.f5769d
            long r0 = r4.q
            long r0 = r4.r
            r5.a(r6, r8)
        L6a:
            com.tencent.bugly.proguard.u r5 = r4.l
            if (r5 == 0) goto L77
            int r7 = r4.f5769d
            long r0 = r4.q
            long r0 = r4.r
            r5.a(r6, r8)
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.a(com.tencent.bugly.proguard.ao, boolean, int, java.lang.String):void");
    }

    private static boolean a(ao aoVar, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        if (aoVar == null) {
            y.d("resp == null!", new Object[0]);
            return false;
        }
        if (aoVar.f5642a != 0) {
            y.e("resp result error %d", Byte.valueOf(aoVar.f5642a));
            return false;
        }
        try {
            if (!ab.a(aoVar.f5646e) && !com.tencent.bugly.crashreport.common.info.a.b().m().equals(aoVar.f5646e)) {
                o.a().a(com.tencent.bugly.crashreport.common.strategy.a.f5433a, "device", aoVar.f5646e.getBytes(Key.STRING_CHARSET_NAME), (n) null, true);
                aVar.f(aoVar.f5646e);
            }
        } catch (Throwable th) {
            y.a(th);
        }
        aVar.f5421h = aoVar.f5645d;
        if (aoVar.f5643b == 510) {
            if (aoVar.f5644c == null) {
                y.e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(aoVar.f5643b));
                return false;
            }
            aq aqVar = (aq) a.a(aoVar.f5644c, aq.class);
            if (aqVar == null) {
                y.e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(aoVar.f5643b));
                return false;
            }
            aVar2.a(aqVar);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x01f9 A[LOOP:0: B:43:0x00df->B:75:0x01f9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01fd A[SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 527
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.run():void");
    }

    private Pair<Boolean, Boolean> a(byte[] bArr, Map<String, String> map) {
        if (bArr == null) {
            y.e("[Upload] Failed to upload(%d): %s", 1, "Failed to upload for no response!");
            return new Pair<>(false, true);
        }
        y.c("[Upload] Received %d bytes", Integer.valueOf(bArr.length));
        if (bArr.length == 0) {
            a(null, false, 1, "response data from server is empty");
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    y.c("[Upload] HTTP headers from server: key = %s, value = %s", entry.getKey(), entry.getValue());
                }
            }
            return new Pair<>(false, false);
        }
        return new Pair<>(true, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.util.Pair<java.lang.Boolean, java.lang.Boolean> a(java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.a(java.util.Map):android.util.Pair");
    }

    public final void a(long j) {
        this.p++;
        this.q += j;
    }

    public final void b(long j) {
        this.r += j;
    }

    private static String a(String str) {
        if (ab.a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", str, UUID.randomUUID().toString());
        } catch (Throwable th) {
            y.a(th);
            return str;
        }
    }
}