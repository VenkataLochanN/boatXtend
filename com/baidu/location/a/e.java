package com.baidu.location.a;

import android.location.Location;

/* JADX INFO: loaded from: classes.dex */
class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Location f2088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ d f2089b;

    e(d dVar, Location location) {
        this.f2089b = dVar;
        this.f2088a = location;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f2089b.b(this.f2088a);
    }
}