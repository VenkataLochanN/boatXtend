package com.loc;

import java.util.HashMap;

/* JADX INFO: compiled from: CellAgeEstimator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class du {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<Long, dv> f5058a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f5059b = 0;

    private static long a(int i, int i2) {
        return (((long) i2) & 65535) | ((((long) i) & 65535) << 32);
    }

    public final long a(dv dvVar) {
        int i;
        int i2;
        long jA;
        if (dvVar == null || !dvVar.p) {
            return 0L;
        }
        HashMap<Long, dv> map = this.f5058a;
        int i3 = dvVar.k;
        if (i3 == 1) {
            i = dvVar.f5062c;
            i2 = dvVar.f5063d;
            jA = a(i, i2);
        } else if (i3 != 2) {
            if (i3 != 3 && i3 != 4) {
                jA = 0;
            }
            i = dvVar.f5062c;
            i2 = dvVar.f5063d;
            jA = a(i, i2);
        } else {
            i = dvVar.f5067h;
            i2 = dvVar.i;
            jA = a(i, i2);
        }
        dv dvVar2 = map.get(Long.valueOf(jA));
        if (dvVar2 == null) {
            dvVar.m = ep.b();
            map.put(Long.valueOf(jA), dvVar);
            return 0L;
        }
        if (dvVar2.j != dvVar.j) {
            dvVar.m = ep.b();
            map.put(Long.valueOf(jA), dvVar);
            return 0L;
        }
        dvVar.m = dvVar2.m;
        map.put(Long.valueOf(jA), dvVar);
        return (ep.b() - dvVar2.m) / 1000;
    }

    public final void a() {
        this.f5058a.clear();
        this.f5059b = 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
    
        if (r13 != 4) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0080, code lost:
    
        if (r12 != 4) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0062 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.util.ArrayList<? extends com.loc.dv> r15) {
        /*
            r14 = this;
            if (r15 == 0) goto L9c
            long r0 = com.loc.ep.b()
            long r2 = r14.f5059b
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L18
            long r2 = r0 - r2
            r6 = 60000(0xea60, double:2.9644E-319)
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 >= 0) goto L18
            return
        L18:
            java.util.HashMap<java.lang.Long, com.loc.dv> r2 = r14.f5058a
            int r3 = r15.size()
            r6 = 0
            r7 = r4
            r4 = r6
        L21:
            r5 = 4
            r9 = 3
            r10 = 2
            r11 = 1
            if (r4 >= r3) goto L65
            java.lang.Object r12 = r15.get(r4)
            com.loc.dv r12 = (com.loc.dv) r12
            boolean r13 = r12.p
            if (r13 == 0) goto L62
            int r13 = r12.k
            if (r13 == r11) goto L41
            if (r13 == r10) goto L3c
            if (r13 == r9) goto L41
            if (r13 == r5) goto L41
            goto L49
        L3c:
            int r5 = r12.f5067h
            int r7 = r12.i
            goto L45
        L41:
            int r5 = r12.f5062c
            int r7 = r12.f5063d
        L45:
            long r7 = a(r5, r7)
        L49:
            java.lang.Long r5 = java.lang.Long.valueOf(r7)
            java.lang.Object r5 = r2.get(r5)
            com.loc.dv r5 = (com.loc.dv) r5
            if (r5 == 0) goto L62
            int r9 = r5.j
            int r10 = r12.j
            if (r9 != r10) goto L60
            long r9 = r5.m
            r12.m = r9
            goto L62
        L60:
            r12.m = r0
        L62:
            int r4 = r4 + 1
            goto L21
        L65:
            r2.clear()
            int r3 = r15.size()
        L6c:
            if (r6 >= r3) goto L9a
            java.lang.Object r4 = r15.get(r6)
            com.loc.dv r4 = (com.loc.dv) r4
            boolean r12 = r4.p
            if (r12 == 0) goto L97
            int r12 = r4.k
            if (r12 == r11) goto L88
            if (r12 == r10) goto L83
            if (r12 == r9) goto L88
            if (r12 == r5) goto L88
            goto L90
        L83:
            int r7 = r4.f5067h
            int r8 = r4.i
            goto L8c
        L88:
            int r7 = r4.f5062c
            int r8 = r4.f5063d
        L8c:
            long r7 = a(r7, r8)
        L90:
            java.lang.Long r12 = java.lang.Long.valueOf(r7)
            r2.put(r12, r4)
        L97:
            int r6 = r6 + 1
            goto L6c
        L9a:
            r14.f5059b = r0
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.du.a(java.util.ArrayList):void");
    }
}