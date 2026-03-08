package com.baidu.mapapi.utils;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Context f3370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f3371b;

    f(Context context, int i) {
        this.f3370a = context;
        this.f3371b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        do {
            if (System.currentTimeMillis() - jCurrentTimeMillis > 3000) {
                b.a(this.f3370a);
                b.a(this.f3371b, this.f3370a);
            }
        } while (!b.v.isInterrupted());
    }
}