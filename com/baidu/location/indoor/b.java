package com.baidu.location.indoor;

import com.baidu.location.a.v;

/* JADX INFO: loaded from: classes.dex */
class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f2514a;

    b(a aVar) {
        this.f2514a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f2514a.s != null) {
            a aVar = this.f2514a;
            aVar.f2513f = aVar.s;
            this.f2514a.b(v.a().c());
        }
    }
}