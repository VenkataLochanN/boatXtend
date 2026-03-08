package com.baidu.location.f;

import android.util.Log;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ WeakReference f2450a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ a f2451b;

    b(a aVar, WeakReference weakReference) {
        this.f2451b = aVar;
        this.f2450a = weakReference;
    }

    @Override // java.lang.Runnable
    public void run() {
        a aVar = (a) this.f2450a.get();
        if (aVar == null || aVar.f2448h != 3) {
            return;
        }
        Log.d("baidu_location_service", "baidu location service force stopped ...");
        aVar.d();
    }
}