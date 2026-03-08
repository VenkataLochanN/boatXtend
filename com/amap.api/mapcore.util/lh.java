package com.amap.api.mapcore.util;

import java.util.HashMap;

/* JADX INFO: compiled from: CellAgeEstimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class lh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<Long, li> f1662a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f1663b = 0;

    private static long a(int i, int i2) {
        return (((long) i2) & 65535) | ((((long) i) & 65535) << 32);
    }

    public final long a(li liVar) {
        int iA;
        int iB;
        long jA;
        if (liVar == null || !liVar.p) {
            return 0L;
        }
        HashMap<Long, li> map = this.f1662a;
        int i = liVar.k;
        if (i == 1) {
            iA = liVar.a();
            iB = liVar.b();
            jA = a(iA, iB);
        } else if (i != 2) {
            if (i != 3 && i != 4) {
                jA = 0;
            }
            iA = liVar.a();
            iB = liVar.b();
            jA = a(iA, iB);
        } else {
            iA = liVar.c();
            iB = liVar.d();
            jA = a(iA, iB);
        }
        li liVar2 = map.get(Long.valueOf(jA));
        if (liVar2 == null) {
            liVar.m = kk.b();
            map.put(Long.valueOf(jA), liVar);
            return 0L;
        }
        if (liVar2.e() != liVar.e()) {
            liVar.m = kk.b();
            map.put(Long.valueOf(jA), liVar);
            return 0L;
        }
        liVar.m = liVar2.m;
        map.put(Long.valueOf(jA), liVar);
        return (kk.b() - liVar2.m) / 1000;
    }

    public final void a() {
        this.f1662a.clear();
        this.f1663b = 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
    
        if (r13 != 4) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0084, code lost:
    
        if (r12 != 4) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0066 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.util.ArrayList<? extends com.amap.api.mapcore.util.li> r15) {
        /*
            r14 = this;
            if (r15 == 0) goto La8
            long r0 = com.amap.api.mapcore.util.kk.b()
            long r2 = r14.f1663b
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L18
            long r2 = r0 - r2
            r6 = 60000(0xea60, double:2.9644E-319)
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 >= 0) goto L18
            return
        L18:
            java.util.HashMap<java.lang.Long, com.amap.api.mapcore.util.li> r2 = r14.f1662a
            int r3 = r15.size()
            r6 = 0
            r7 = r4
            r4 = r6
        L21:
            r5 = 4
            r9 = 3
            r10 = 2
            r11 = 1
            if (r4 >= r3) goto L69
            java.lang.Object r12 = r15.get(r4)
            com.amap.api.mapcore.util.li r12 = (com.amap.api.mapcore.util.li) r12
            boolean r13 = r12.p
            if (r13 == 0) goto L66
            int r13 = r12.k
            if (r13 == r11) goto L41
            if (r13 == r10) goto L3c
            if (r13 == r9) goto L41
            if (r13 == r5) goto L41
            goto L49
        L3c:
            int r5 = r12.f1671h
            int r7 = r12.i
            goto L45
        L41:
            int r5 = r12.f1666c
            int r7 = r12.f1667d
        L45:
            long r7 = a(r5, r7)
        L49:
            java.lang.Long r5 = java.lang.Long.valueOf(r7)
            java.lang.Object r5 = r2.get(r5)
            com.amap.api.mapcore.util.li r5 = (com.amap.api.mapcore.util.li) r5
            if (r5 == 0) goto L66
            int r9 = r5.e()
            int r10 = r12.e()
            if (r9 != r10) goto L64
            long r9 = r5.m
            r12.m = r9
            goto L66
        L64:
            r12.m = r0
        L66:
            int r4 = r4 + 1
            goto L21
        L69:
            r2.clear()
            int r3 = r15.size()
        L70:
            if (r6 >= r3) goto La6
            java.lang.Object r4 = r15.get(r6)
            com.amap.api.mapcore.util.li r4 = (com.amap.api.mapcore.util.li) r4
            boolean r12 = r4.p
            if (r12 == 0) goto La3
            int r12 = r4.k
            if (r12 == r11) goto L90
            if (r12 == r10) goto L87
            if (r12 == r9) goto L90
            if (r12 == r5) goto L90
            goto L9c
        L87:
            int r7 = r4.c()
            int r8 = r4.d()
            goto L98
        L90:
            int r7 = r4.a()
            int r8 = r4.b()
        L98:
            long r7 = a(r7, r8)
        L9c:
            java.lang.Long r12 = java.lang.Long.valueOf(r7)
            r2.put(r12, r4)
        La3:
            int r6 = r6 + 1
            goto L70
        La6:
            r14.f1663b = r0
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.lh.a(java.util.ArrayList):void");
    }
}