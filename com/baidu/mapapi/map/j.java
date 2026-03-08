package com.baidu.mapapi.map;

/* JADX INFO: loaded from: classes.dex */
class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f3046a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f3047b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ int f3048c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ HeatMap f3049d;

    j(HeatMap heatMap, int i, int i2, int i3) {
        this.f3049d = heatMap;
        this.f3046a = i;
        this.f3047b = i2;
        this.f3048c = i3;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3049d.b(this.f3046a, this.f3047b, this.f3048c);
    }
}