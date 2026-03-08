package com.baidu.location.e;

import com.baidu.location.a.l;
import com.baidu.location.a.t;
import com.baidu.location.a.x;
import com.baidu.location.e.i;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f2436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ i.a f2437b;

    j(i.a aVar, boolean z) {
        this.f2437b = aVar;
        this.f2436a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!i.this.j) {
            i.this.j = this.f2436a;
        }
        i.this.t();
        l.c().i();
        if (com.baidu.location.indoor.g.a().e()) {
            com.baidu.location.indoor.g.a().f2530a.obtainMessage(41).sendToTarget();
        }
        if (System.currentTimeMillis() - t.b() <= BootloaderScanner.TIMEOUT) {
            x.a().c();
        }
    }
}