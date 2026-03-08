package com.baidu.location.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ExecutorService f2179a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ExecutorService f2180b;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static v f2181a = new v();
    }

    private v() {
    }

    public static v a() {
        return a.f2181a;
    }

    public synchronized ExecutorService b() {
        if (this.f2179a == null || this.f2179a.isShutdown()) {
            this.f2179a = null;
            this.f2179a = Executors.newSingleThreadExecutor();
        }
        return this.f2179a;
    }

    public synchronized ExecutorService c() {
        if (this.f2180b == null || this.f2180b.isShutdown()) {
            this.f2180b = null;
            this.f2180b = Executors.newFixedThreadPool(2);
        }
        return this.f2180b;
    }

    public void d() {
        ExecutorService executorService = this.f2179a;
        if (executorService != null) {
            executorService.shutdown();
        }
        ExecutorService executorService2 = this.f2180b;
        if (executorService2 != null) {
            executorService2.shutdown();
        }
    }
}