package com.amap.api.mapcore.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ThreadUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class eq {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile eq f759c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BlockingQueue<Runnable> f760a = new LinkedBlockingQueue();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ExecutorService f761b;

    public static eq a() {
        if (f759c == null) {
            synchronized (eq.class) {
                if (f759c == null) {
                    f759c = new eq();
                }
            }
        }
        return f759c;
    }

    public static void b() {
        if (f759c != null) {
            try {
                f759c.f761b.shutdownNow();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            f759c.f761b = null;
            f759c = null;
        }
    }

    private eq() {
        this.f761b = null;
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        this.f761b = new ThreadPoolExecutor(iAvailableProcessors, iAvailableProcessors * 2, 10, TimeUnit.SECONDS, this.f760a, new ee("AMapThreadUtil"), new ThreadPoolExecutor.AbortPolicy());
    }

    public void a(Runnable runnable) {
        ExecutorService executorService = this.f761b;
        if (executorService != null) {
            try {
                executorService.execute(runnable);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}