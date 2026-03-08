package com.baidu.location.a;

/* JADX INFO: loaded from: classes.dex */
class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f2170a;

    p(o oVar) {
        this.f2170a = oVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (com.baidu.location.e.i.j() || this.f2170a.a(com.baidu.location.f.getServiceContext())) {
            this.f2170a.a(v.a().c());
        }
    }
}