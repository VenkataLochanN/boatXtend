package com.loc;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CellCollector.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private cw f4886a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private cw f4887b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private dc f4888c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f4889d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<cw> f4890e = new ArrayList(3);

    /* JADX INFO: compiled from: CellCollector.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte f4891a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4892b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public cw f4893c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public cw f4894d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public cw f4895e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public List<cw> f4896f = new ArrayList();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public List<cw> f4897g = new ArrayList();

        public static boolean a(cw cwVar, cw cwVar2) {
            if (cwVar == null || cwVar2 == null) {
                return (cwVar == null) == (cwVar2 == null);
            }
            if ((cwVar instanceof cy) && (cwVar2 instanceof cy)) {
                cy cyVar = (cy) cwVar;
                cy cyVar2 = (cy) cwVar2;
                return cyVar.j == cyVar2.j && cyVar.k == cyVar2.k;
            }
            if ((cwVar instanceof cx) && (cwVar2 instanceof cx)) {
                cx cxVar = (cx) cwVar;
                cx cxVar2 = (cx) cwVar2;
                return cxVar.l == cxVar2.l && cxVar.k == cxVar2.k && cxVar.j == cxVar2.j;
            }
            if ((cwVar instanceof cz) && (cwVar2 instanceof cz)) {
                cz czVar = (cz) cwVar;
                cz czVar2 = (cz) cwVar2;
                return czVar.j == czVar2.j && czVar.k == czVar2.k;
            }
            if ((cwVar instanceof da) && (cwVar2 instanceof da)) {
                da daVar = (da) cwVar;
                da daVar2 = (da) cwVar2;
                if (daVar.j == daVar2.j && daVar.k == daVar2.k) {
                    return true;
                }
            }
            return false;
        }

        public final void a() {
            this.f4891a = (byte) 0;
            this.f4892b = "";
            this.f4893c = null;
            this.f4894d = null;
            this.f4895e = null;
            this.f4896f.clear();
            this.f4897g.clear();
        }

        public final String toString() {
            return "CellInfo{radio=" + ((int) this.f4891a) + ", operator='" + this.f4892b + "', mainCell=" + this.f4893c + ", mainOldInterCell=" + this.f4894d + ", mainNewInterCell=" + this.f4895e + ", cells=" + this.f4896f + ", historyMainCellList=" + this.f4897g + '}';
        }
    }

    final a a(dc dcVar, boolean z, byte b2, String str, List<cw> list) {
        List list2;
        if (z) {
            this.f4889d.a();
            return null;
        }
        a aVar = this.f4889d;
        aVar.a();
        aVar.f4891a = b2;
        aVar.f4892b = str;
        if (list != null) {
            aVar.f4896f.addAll(list);
            for (cw cwVar : aVar.f4896f) {
                if (!cwVar.i && cwVar.f4946h) {
                    aVar.f4894d = cwVar;
                } else if (cwVar.i && cwVar.f4946h) {
                    aVar.f4895e = cwVar;
                }
            }
        }
        aVar.f4893c = aVar.f4894d == null ? aVar.f4895e : aVar.f4894d;
        if (this.f4889d.f4893c == null) {
            return null;
        }
        boolean z2 = true;
        if (this.f4888c != null) {
            if (!(dcVar.a(this.f4888c) > ((double) ((dcVar.f4966g > 10.0f ? 1 : (dcVar.f4966g == 10.0f ? 0 : -1)) > 0 ? 2000.0f : (dcVar.f4966g > 2.0f ? 1 : (dcVar.f4966g == 2.0f ? 0 : -1)) > 0 ? 500.0f : 100.0f))) && a.a(this.f4889d.f4894d, this.f4886a) && a.a(this.f4889d.f4895e, this.f4887b)) {
                z2 = false;
            }
        }
        if (!z2) {
            return null;
        }
        this.f4886a = this.f4889d.f4894d;
        this.f4887b = this.f4889d.f4895e;
        this.f4888c = dcVar;
        ct.a(this.f4889d.f4896f);
        a aVar2 = this.f4889d;
        synchronized (this.f4890e) {
            for (cw cwVar2 : aVar2.f4896f) {
                if (cwVar2 != null && cwVar2.f4946h) {
                    cw cwVarClone = cwVar2.clone();
                    cwVarClone.f4943e = SystemClock.elapsedRealtime();
                    int size = this.f4890e.size();
                    if (size == 0) {
                        list2 = this.f4890e;
                    } else {
                        long jMin = Long.MAX_VALUE;
                        int i = -1;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= size) {
                                break;
                            }
                            cw cwVar3 = this.f4890e.get(i2);
                            if (cwVarClone.equals(cwVar3)) {
                                if (cwVarClone.f4941c != cwVar3.f4941c) {
                                    cwVar3.f4943e = cwVarClone.f4941c;
                                    cwVar3.f4941c = cwVarClone.f4941c;
                                }
                                i = -1;
                            } else {
                                jMin = Math.min(jMin, cwVar3.f4943e);
                                if (jMin == cwVar3.f4943e) {
                                    i = i2;
                                }
                                i2++;
                            }
                        }
                        if (i >= 0) {
                            if (size < 3) {
                                list2 = this.f4890e;
                            } else if (cwVarClone.f4943e > jMin && i < size) {
                                this.f4890e.remove(i);
                                list2 = this.f4890e;
                            }
                        }
                    }
                    list2.add(cwVarClone);
                }
            }
            this.f4889d.f4897g.clear();
            this.f4889d.f4897g.addAll(this.f4890e);
        }
        return this.f4889d;
    }
}