package com.baidu.location.d;

import com.baidu.location.a.v;
import com.baidu.location.d.f;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
class g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ f.a f2326a;

    g(f.a aVar) {
        this.f2326a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ExecutorService executorServiceC = v.a().c();
        if (executorServiceC != null) {
            this.f2326a.a(executorServiceC, "https://ofloc.map.baidu.com/offline_loc");
        } else {
            this.f2326a.c("https://ofloc.map.baidu.com/offline_loc");
        }
    }
}