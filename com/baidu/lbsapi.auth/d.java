package com.baidu.lbsapi.auth;

/* JADX INFO: loaded from: classes.dex */
class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f1994a;

    d(c cVar) {
        this.f1994a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        a.a("postWithHttps start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        this.f1994a.a(new g(this.f1994a.f1991a).a(this.f1994a.f1992b));
    }
}