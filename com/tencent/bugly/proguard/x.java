package com.tencent.bugly.proguard;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicInteger f5774a = new AtomicInteger(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static x f5775b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ScheduledExecutorService f5776c;

    protected x() {
        this.f5776c = null;
        this.f5776c = Executors.newScheduledThreadPool(3, new ThreadFactory(this) { // from class: com.tencent.bugly.proguard.x.1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BuglyThread-" + x.f5774a.getAndIncrement());
                return thread;
            }
        });
        ScheduledExecutorService scheduledExecutorService = this.f5776c;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            y.d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    public static synchronized x a() {
        if (f5775b == null) {
            f5775b = new x();
        }
        return f5775b;
    }

    public final synchronized boolean a(Runnable runnable, long j) {
        if (!c()) {
            y.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        }
        if (runnable == null) {
            y.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        }
        if (j <= 0) {
            j = 0;
        }
        y.c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
        try {
            this.f5776c.schedule(runnable, j, TimeUnit.MILLISECONDS);
            return true;
        } catch (Throwable th) {
            if (com.tencent.bugly.b.f5373c) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public final synchronized boolean a(Runnable runnable) {
        if (!c()) {
            y.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        }
        if (runnable == null) {
            y.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        }
        y.c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
        try {
            this.f5776c.execute(runnable);
            return true;
        } catch (Throwable th) {
            if (com.tencent.bugly.b.f5373c) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public final synchronized void b() {
        if (this.f5776c != null && !this.f5776c.isShutdown()) {
            y.c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.f5776c.shutdownNow();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean c() {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.concurrent.ScheduledExecutorService r0 = r1.f5776c     // Catch: java.lang.Throwable -> L12
            if (r0 == 0) goto L10
            java.util.concurrent.ScheduledExecutorService r0 = r1.f5776c     // Catch: java.lang.Throwable -> L12
            boolean r0 = r0.isShutdown()     // Catch: java.lang.Throwable -> L12
            if (r0 != 0) goto L10
            r0 = 1
        Le:
            monitor-exit(r1)
            return r0
        L10:
            r0 = 0
            goto Le
        L12:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.x.c():boolean");
    }
}