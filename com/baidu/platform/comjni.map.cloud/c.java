package com.baidu.platform.comjni.map.cloud;

/* JADX INFO: loaded from: classes.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ b f3928b;

    c(b bVar, String str) {
        this.f3928b = bVar;
        this.f3927a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3928b.f3926a.f(this.f3927a);
    }
}