package com.amap.api.mapcore.util;

import android.os.SystemClock;
import android.util.LongSparseArray;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: RssiInfoManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class kx {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static volatile kx f1615g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static Object f1616h = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object f1621e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Object f1622f = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LongSparseArray<a> f1617a = new LongSparseArray<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LongSparseArray<a> f1618b = new LongSparseArray<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LongSparseArray<a> f1619c = new LongSparseArray<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LongSparseArray<a> f1620d = new LongSparseArray<>();

    /* JADX INFO: compiled from: RssiInfoManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1623a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f1624b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1625c;

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    private kx() {
    }

    public static kx a() {
        if (f1615g == null) {
            synchronized (f1616h) {
                if (f1615g == null) {
                    f1615g = new kx();
                }
            }
        }
        return f1615g;
    }

    private static void a(List<kw> list, LongSparseArray<a> longSparseArray, LongSparseArray<a> longSparseArray2) {
        long jB = b();
        int size = longSparseArray.size();
        byte b2 = 0;
        Iterator<kw> it = list.iterator();
        if (size == 0) {
            while (it.hasNext()) {
                kw next = it.next();
                a aVar = new a(b2);
                aVar.f1623a = next.b();
                aVar.f1624b = jB;
                aVar.f1625c = false;
                longSparseArray2.put(next.a(), aVar);
            }
            return;
        }
        while (it.hasNext()) {
            kw next2 = it.next();
            long jA = next2.a();
            a aVar2 = longSparseArray.get(jA);
            if (aVar2 == null) {
                aVar2 = new a(b2);
            } else {
                if (aVar2.f1623a != next2.b()) {
                }
                longSparseArray2.put(jA, aVar2);
            }
            aVar2.f1623a = next2.b();
            aVar2.f1624b = jB;
            aVar2.f1625c = true;
            longSparseArray2.put(jA, aVar2);
        }
    }

    private static long b() {
        return SystemClock.elapsedRealtime();
    }

    final void a(List<kw> list) {
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.f1621e) {
            a(list, this.f1617a, this.f1618b);
            LongSparseArray<a> longSparseArray = this.f1617a;
            this.f1617a = this.f1618b;
            this.f1618b = longSparseArray;
            this.f1618b.clear();
        }
    }
}