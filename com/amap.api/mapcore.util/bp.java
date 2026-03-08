package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: OfflineDBOperation.java */
/* JADX INFO: loaded from: classes.dex */
public class bp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile bp f288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static hr f289b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f290c;

    public static bp a(Context context) {
        if (f288a == null) {
            synchronized (bp.class) {
                if (f288a == null) {
                    f288a = new bp(context);
                }
            }
        }
        return f288a;
    }

    private bp(Context context) {
        this.f290c = context;
        f289b = b(this.f290c);
    }

    private hr b(Context context) {
        try {
            return new hr(context, bo.a());
        } catch (Throwable th) {
            hn.c(th, "OfflineDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    private boolean b() {
        if (f289b == null) {
            f289b = b(this.f290c);
        }
        return f289b != null;
    }

    public ArrayList<bk> a() {
        ArrayList<bk> arrayList = new ArrayList<>();
        if (!b()) {
            return arrayList;
        }
        Iterator it = f289b.b("", bk.class).iterator();
        while (it.hasNext()) {
            arrayList.add((bk) it.next());
        }
        return arrayList;
    }

    public synchronized bk a(String str) {
        if (!b()) {
            return null;
        }
        List listB = f289b.b(bk.e(str), bk.class);
        if (listB.size() <= 0) {
            return null;
        }
        return (bk) listB.get(0);
    }

    public synchronized void a(bk bkVar) {
        if (b()) {
            f289b.a(bkVar, bk.f(bkVar.i()));
            a(bkVar.f(), bkVar.b());
        }
    }

    private void a(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        String strA = bm.a(str);
        if (f289b.b(strA, bm.class).size() > 0) {
            f289b.a(strA, bm.class);
        }
        String[] strArrSplit = str2.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str3 : strArrSplit) {
            arrayList.add(new bm(str, str3));
        }
        f289b.a((List) arrayList);
    }

    public synchronized List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        if (!b()) {
            return arrayList;
        }
        arrayList.addAll(a(f289b.b(bm.a(str), bm.class)));
        return arrayList;
    }

    private List<String> a(List<bm> list) {
        ArrayList arrayList = new ArrayList();
        if (list.size() > 0) {
            Iterator<bm> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().a());
            }
        }
        return arrayList;
    }

    public synchronized void c(String str) {
        if (b()) {
            f289b.a(bn.e(str), bn.class);
            f289b.a(bm.a(str), bm.class);
            f289b.a(bl.a(str), bl.class);
        }
    }

    public synchronized void b(bk bkVar) {
        if (b()) {
            f289b.a(bn.f(bkVar.i()), bn.class);
            f289b.a(bm.a(bkVar.f()), bm.class);
            f289b.a(bl.a(bkVar.f()), bl.class);
        }
    }

    public void a(String str, int i, long j, long j2, long j3) {
        if (b()) {
            a(str, i, j, new long[]{j2, 0, 0, 0, 0}, new long[]{j3, 0, 0, 0, 0});
        }
    }

    public synchronized void a(String str, int i, long j, long[] jArr, long[] jArr2) {
        if (b()) {
            f289b.a(new bl(str, j, i, jArr[0], jArr2[0]), bl.a(str));
        }
    }

    public synchronized String d(String str) {
        if (!b()) {
            return null;
        }
        List listB = f289b.b(bn.f(str), bn.class);
        return listB.size() > 0 ? ((bn) listB.get(0)).e() : null;
    }
}