package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: AMapRecallLogUpdateStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class hg extends jp {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f1213g = 10000000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f1214a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected long f1215b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1216d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1217e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1218f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f1219h;

    @Override // com.amap.api.mapcore.util.jp
    public int a() {
        return 320000;
    }

    public hg(boolean z, int i, jp jpVar, long j, int i2) {
        super(jpVar);
        this.f1216d = false;
        this.f1217e = false;
        this.f1218f = f1213g;
        this.f1219h = 0L;
        this.f1216d = z;
        this.f1214a = i;
        this.f1219h = j;
        this.f1218f = i2;
    }

    public void a(boolean z) {
        this.f1217e = z;
    }

    public void a(int i) {
        if (i <= 0) {
            return;
        }
        this.f1219h += (long) i;
    }

    public long b() {
        return this.f1219h;
    }

    @Override // com.amap.api.mapcore.util.jp
    protected boolean c() {
        if (this.f1217e && this.f1219h <= this.f1218f) {
            return true;
        }
        if (!this.f1216d || this.f1219h >= this.f1218f) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.f1215b < this.f1214a) {
            return false;
        }
        this.f1215b = jCurrentTimeMillis;
        return true;
    }
}