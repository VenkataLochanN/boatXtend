package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: NamedThreadFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class ee implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicInteger f723a = new AtomicInteger(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AtomicInteger f724b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f725c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f726d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ThreadGroup f727e;

    public ee() {
        this("amap-threadpool-" + f723a.getAndIncrement(), false);
    }

    public ee(String str) {
        this(str, false);
    }

    public ee(String str, boolean z) {
        String str2;
        this.f724b = new AtomicInteger(1);
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = str + "-thread-";
        }
        this.f725c = str2;
        this.f726d = z;
        SecurityManager securityManager = System.getSecurityManager();
        this.f727e = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f727e, runnable, this.f725c + this.f724b.getAndIncrement(), 0L);
        thread.setDaemon(this.f726d);
        return thread;
    }
}