package com.baidu.location.e;

import com.baidu.location.e.b;

/* JADX INFO: loaded from: classes.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ b.a f2402a;

    c(b.a aVar) {
        this.f2402a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b.this.k();
        } catch (Exception unused) {
        }
        com.baidu.location.b.b.a().e();
    }
}