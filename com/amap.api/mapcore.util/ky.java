package com.amap.api.mapcore.util;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: RssiManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class ky {

    /* JADX INFO: compiled from: RssiManager.java */
    public static class a implements kw {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1626a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1627b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1628c;

        a(int i, int i2, int i3) {
            this.f1626a = i;
            this.f1627b = i2;
            this.f1628c = i3;
        }

        @Override // com.amap.api.mapcore.util.kw
        public final long a() {
            return ky.a(this.f1626a, this.f1627b);
        }

        @Override // com.amap.api.mapcore.util.kw
        public final int b() {
            return this.f1628c;
        }
    }

    public static long a(int i, int i2) {
        return (((long) i2) & 4294967295L) | ((((long) i) & 4294967295L) << 32);
    }

    public static synchronized void a(List<la> list) {
        a aVar;
        if (list != null) {
            if (!list.isEmpty()) {
                ArrayList arrayList = new ArrayList(list.size());
                for (la laVar : list) {
                    if (laVar instanceof lc) {
                        lc lcVar = (lc) laVar;
                        aVar = new a(lcVar.j, lcVar.k, lcVar.f1645c);
                    } else if (laVar instanceof ld) {
                        ld ldVar = (ld) laVar;
                        aVar = new a(ldVar.j, ldVar.k, ldVar.f1645c);
                    } else if (laVar instanceof le) {
                        le leVar = (le) laVar;
                        aVar = new a(leVar.j, leVar.k, leVar.f1645c);
                    } else if (laVar instanceof lb) {
                        lb lbVar = (lb) laVar;
                        aVar = new a(lbVar.k, lbVar.l, lbVar.f1645c);
                    }
                    arrayList.add(aVar);
                }
                kx.a().a(arrayList);
            }
        }
    }
}