package com.baidu.location.a;

/* JADX INFO: loaded from: classes.dex */
class s extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f2173a;

    s(o oVar) {
        this.f2173a = oVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        if (this.f2173a.g()) {
            this.f2173a.h();
        }
    }
}