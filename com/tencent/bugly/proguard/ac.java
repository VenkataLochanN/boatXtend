package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ac implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Handler f5599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.tencent.bugly.crashreport.crash.anr.c> f5600b = new LinkedList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f5601c = BootloaderScanner.TIMEOUT;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final long f5602d = BootloaderScanner.TIMEOUT;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f5603e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f5604f;

    ac(Handler handler, String str, long j) {
        this.f5599a = handler;
    }

    public final void a() {
        if (this.f5603e) {
            this.f5603e = false;
            this.f5604f = SystemClock.uptimeMillis();
            this.f5599a.post(this);
        }
    }

    public final boolean b() {
        return !this.f5603e && SystemClock.uptimeMillis() >= this.f5604f + this.f5601c;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f5603e = true;
        this.f5601c = this.f5602d;
    }

    public final void a(long j) {
        this.f5601c = BootloaderScanner.TIMEOUT;
    }

    public final long c() {
        return SystemClock.uptimeMillis() - this.f5604f;
    }

    public final List<com.tencent.bugly.crashreport.crash.anr.c> b(long j) {
        ArrayList arrayList;
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (this.f5600b) {
            arrayList = new ArrayList(this.f5600b.size());
            for (int i = 0; i < this.f5600b.size(); i++) {
                com.tencent.bugly.crashreport.crash.anr.c cVar = this.f5600b.get(i);
                if (!cVar.d() && jCurrentTimeMillis - cVar.c() < 200000) {
                    arrayList.add(cVar);
                    cVar.a(true);
                }
            }
        }
        return arrayList;
    }

    public final void d() {
        StringBuilder sb = new StringBuilder(1024);
        System.nanoTime();
        try {
            StackTraceElement[] stackTrace = this.f5599a.getLooper().getThread().getStackTrace();
            if (stackTrace.length == 0) {
                sb.append("Thread does not have stack trace.\n");
            } else {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    sb.append(stackTraceElement);
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
            }
        } catch (SecurityException e2) {
            sb.append("getStackTrace() encountered:\n");
            sb.append(e2.getMessage());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            y.a(e2);
        }
        System.nanoTime();
        com.tencent.bugly.crashreport.crash.anr.c cVar = new com.tencent.bugly.crashreport.crash.anr.c(sb.toString(), System.currentTimeMillis());
        cVar.a(this.f5599a.getLooper().getThread().getName());
        synchronized (this.f5600b) {
            while (this.f5600b.size() >= 32) {
                this.f5600b.remove(0);
            }
            this.f5600b.add(cVar);
        }
    }
}