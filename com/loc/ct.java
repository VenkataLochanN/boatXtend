package com.loc;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: RssiManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ct {

    /* JADX INFO: compiled from: RssiManager.java */
    public static class a implements cr {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f4932a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4933b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f4934c;

        a(int i, int i2, int i3) {
            this.f4932a = i;
            this.f4933b = i2;
            this.f4934c = i3;
        }

        @Override // com.loc.cr
        public final long a() {
            return ct.a(this.f4932a, this.f4933b);
        }

        @Override // com.loc.cr
        public final int b() {
            return this.f4934c;
        }
    }

    /* JADX INFO: compiled from: RssiManager.java */
    public static class b implements cr {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private long f4935a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4936b;

        b(long j, int i) {
            this.f4935a = j;
            this.f4936b = i;
        }

        @Override // com.loc.cr
        public final long a() {
            return this.f4935a;
        }

        @Override // com.loc.cr
        public final int b() {
            return this.f4936b;
        }
    }

    public static long a(int i, int i2) {
        return (((long) i2) & 4294967295L) | ((((long) i) & 4294967295L) << 32);
    }

    public static synchronized short a(long j) {
        return cs.a().a(j);
    }

    public static synchronized void a(List<cw> list) {
        a aVar;
        if (list != null) {
            if (!list.isEmpty()) {
                ArrayList arrayList = new ArrayList(list.size());
                for (cw cwVar : list) {
                    if (cwVar instanceof cy) {
                        cy cyVar = (cy) cwVar;
                        aVar = new a(cyVar.j, cyVar.k, cyVar.f4941c);
                    } else if (cwVar instanceof cz) {
                        cz czVar = (cz) cwVar;
                        aVar = new a(czVar.j, czVar.k, czVar.f4941c);
                    } else if (cwVar instanceof da) {
                        da daVar = (da) cwVar;
                        aVar = new a(daVar.j, daVar.k, daVar.f4941c);
                    } else if (cwVar instanceof cx) {
                        cx cxVar = (cx) cwVar;
                        aVar = new a(cxVar.k, cxVar.l, cxVar.f4941c);
                    }
                    arrayList.add(aVar);
                }
                cs.a().a(arrayList);
            }
        }
    }

    public static synchronized short b(long j) {
        return cs.a().b(j);
    }

    public static synchronized void b(List<dd> list) {
        if (list != null) {
            if (!list.isEmpty()) {
                ArrayList arrayList = new ArrayList(list.size());
                for (dd ddVar : list) {
                    arrayList.add(new b(ddVar.f4968a, ddVar.f4970c));
                }
                cs.a().b(arrayList);
            }
        }
    }
}