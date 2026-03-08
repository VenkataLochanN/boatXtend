package com.baidu.location.d;

import com.baidu.location.BDLocation;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
class i implements Callable<BDLocation> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String[] f2347a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ h f2348b;

    i(h hVar, String[] strArr) {
        this.f2348b = hVar;
        this.f2347a = strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0088 A[EXC_TOP_SPLITTER, PHI: r0 r2
  0x0088: PHI (r0v10 android.database.Cursor) = (r0v6 android.database.Cursor), (r0v9 android.database.Cursor), (r0v15 android.database.Cursor) binds: [B:25:0x005c, B:16:0x004e, B:40:0x0086] A[DONT_GENERATE, DONT_INLINE]
  0x0088: PHI (r2v6 com.baidu.location.BDLocation) = (r2v13 com.baidu.location.BDLocation), (r2v5 com.baidu.location.BDLocation), (r2v15 com.baidu.location.BDLocation) binds: [B:25:0x005c, B:16:0x004e, B:40:0x0086] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.location.BDLocation call() throws java.lang.Throwable {
        /*
            r11 = this;
            com.baidu.location.BDLocation r0 = new com.baidu.location.BDLocation
            r0.<init>()
            java.lang.String[] r1 = r11.f2347a
            int r1 = r1.length
            if (r1 <= 0) goto L9b
            com.baidu.location.d.h r0 = r11.f2348b
            com.baidu.location.d.f r0 = com.baidu.location.d.h.a(r0)
            java.lang.String[] r0 = r0.o()
            r1 = 0
            r2 = 0
            r3 = r1
            r4 = r2
        L18:
            int r5 = r0.length
            if (r3 >= r5) goto L31
            android.content.Context r4 = com.baidu.location.d.h.p()     // Catch: java.lang.Exception -> L2a
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch: java.lang.Exception -> L2a
            r5 = r0[r3]     // Catch: java.lang.Exception -> L2a
            android.content.pm.ProviderInfo r4 = r4.resolveContentProvider(r5, r1)     // Catch: java.lang.Exception -> L2a
            goto L2b
        L2a:
            r4 = r2
        L2b:
            if (r4 == 0) goto L2e
            goto L31
        L2e:
            int r3 = r3 + 1
            goto L18
        L31:
            if (r4 == 0) goto L5f
            android.content.Context r0 = com.baidu.location.d.h.p()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L5b
            android.content.ContentResolver r5 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L5b
            java.lang.String r0 = r4.authority     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L5b
            android.net.Uri r6 = com.baidu.location.d.h.b(r0)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L5b
            java.lang.String[] r7 = r11.f2347a     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L5b
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r0 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L5b
            com.baidu.location.BDLocation r2 = com.baidu.location.d.j.a(r0)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L5c
            if (r0 == 0) goto L8b
        L50:
            goto L88
        L51:
            r1 = move-exception
            r2 = r0
            goto L55
        L54:
            r1 = move-exception
        L55:
            if (r2 == 0) goto L5a
            r2.close()     // Catch: java.lang.Exception -> L5a
        L5a:
            throw r1
        L5b:
            r0 = r2
        L5c:
            if (r0 == 0) goto L8b
            goto L50
        L5f:
            com.baidu.location.d.j$a r0 = new com.baidu.location.d.j$a
            java.lang.String[] r1 = r11.f2347a
            r0.<init>(r1)
            com.baidu.location.d.h r1 = r11.f2348b     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L85
            com.baidu.location.d.c r1 = com.baidu.location.d.h.b(r1)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L85
            android.database.Cursor r0 = r1.a(r0)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L85
            com.baidu.location.BDLocation r1 = com.baidu.location.d.j.a(r0)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L86
            if (r0 == 0) goto L79
            r0.close()     // Catch: java.lang.Exception -> L79
        L79:
            r0 = r1
            goto L8c
        L7b:
            r1 = move-exception
            r2 = r0
            goto L7f
        L7e:
            r1 = move-exception
        L7f:
            if (r2 == 0) goto L84
            r2.close()     // Catch: java.lang.Exception -> L84
        L84:
            throw r1
        L85:
            r0 = r2
        L86:
            if (r0 == 0) goto L8b
        L88:
            r0.close()     // Catch: java.lang.Exception -> L8b
        L8b:
            r0 = r2
        L8c:
            if (r0 == 0) goto L9b
            int r1 = r0.getLocType()
            r2 = 67
            if (r1 == r2) goto L9b
            r1 = 66
            r0.setLocType(r1)
        L9b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.i.call():com.baidu.location.BDLocation");
    }
}