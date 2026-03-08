package com.loc;

import android.os.SystemClock;
import android.util.LongSparseArray;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: RssiInfoManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cs {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static volatile cs f4921g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static Object f4922h = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Object f4927e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Object f4928f = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LongSparseArray<a> f4923a = new LongSparseArray<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LongSparseArray<a> f4924b = new LongSparseArray<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LongSparseArray<a> f4925c = new LongSparseArray<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LongSparseArray<a> f4926d = new LongSparseArray<>();

    /* JADX INFO: compiled from: RssiInfoManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f4929a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f4930b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f4931c;

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    private cs() {
    }

    public static cs a() {
        if (f4921g == null) {
            synchronized (f4922h) {
                if (f4921g == null) {
                    f4921g = new cs();
                }
            }
        }
        return f4921g;
    }

    private static short a(LongSparseArray<a> longSparseArray, long j) {
        synchronized (longSparseArray) {
            a aVar = longSparseArray.get(j);
            if (aVar == null) {
                return (short) 0;
            }
            short sMax = (short) Math.max(1L, Math.min(32767L, (SystemClock.elapsedRealtime() - aVar.f4930b) / 1000));
            if (!aVar.f4931c) {
                sMax = (short) (-sMax);
            }
            return sMax;
        }
    }

    private static void a(List<cr> list, LongSparseArray<a> longSparseArray, LongSparseArray<a> longSparseArray2) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int size = longSparseArray.size();
        byte b2 = 0;
        Iterator<cr> it = list.iterator();
        if (size == 0) {
            while (it.hasNext()) {
                cr next = it.next();
                a aVar = new a(b2);
                aVar.f4929a = next.b();
                aVar.f4930b = jElapsedRealtime;
                aVar.f4931c = false;
                longSparseArray2.put(next.a(), aVar);
            }
            return;
        }
        while (it.hasNext()) {
            cr next2 = it.next();
            long jA = next2.a();
            a aVar2 = longSparseArray.get(jA);
            if (aVar2 == null) {
                aVar2 = new a(b2);
            } else {
                if (aVar2.f4929a != next2.b()) {
                }
                longSparseArray2.put(jA, aVar2);
            }
            aVar2.f4929a = next2.b();
            aVar2.f4930b = jElapsedRealtime;
            aVar2.f4931c = true;
            longSparseArray2.put(jA, aVar2);
        }
    }

    final short a(long j) {
        return a(this.f4923a, j);
    }

    final void a(List<cr> list) {
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.f4927e) {
            a(list, this.f4923a, this.f4924b);
            LongSparseArray<a> longSparseArray = this.f4923a;
            this.f4923a = this.f4924b;
            this.f4924b = longSparseArray;
            this.f4924b.clear();
        }
    }

    final short b(long j) {
        return a(this.f4925c, j);
    }

    final void b(List<cr> list) {
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.f4928f) {
            a(list, this.f4925c, this.f4926d);
            LongSparseArray<a> longSparseArray = this.f4925c;
            this.f4925c = this.f4926d;
            this.f4926d = longSparseArray;
            this.f4926d.clear();
        }
    }
}