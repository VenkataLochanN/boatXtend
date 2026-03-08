package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;
import java.util.List;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ad extends Thread {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ac f5609e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f5610f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f5605a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f5606b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f5607c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f5608d = 1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f5611g = true;

    /* JADX INFO: compiled from: BUGLY */
    public interface a {
        void a(boolean z, long j);
    }

    public final void a(boolean z) {
        this.f5611g = z;
        y.c("set record stack trace enable:" + z, new Object[0]);
    }

    public final boolean a() {
        this.f5605a = true;
        if (!isAlive()) {
            return false;
        }
        try {
            interrupt();
        } catch (Exception e2) {
            y.b(e2);
        }
        y.d("MainHandlerChecker is reset to null.", new Object[0]);
        this.f5609e = null;
        return true;
    }

    public final boolean b() {
        Handler handler = new Handler(Looper.getMainLooper());
        ac acVar = this.f5609e;
        if (acVar != null) {
            acVar.a(BootloaderScanner.TIMEOUT);
        } else {
            this.f5609e = new ac(handler, handler.getLooper().getThread().getName(), BootloaderScanner.TIMEOUT);
        }
        if (isAlive()) {
            return false;
        }
        try {
            start();
            return true;
        } catch (Exception e2) {
            y.b(e2);
            return false;
        }
    }

    public final void a(a aVar) {
        this.f5610f = aVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (!this.f5605a) {
            try {
                boolean z = false;
                if (this.f5609e == null) {
                    y.c("Main handler checker is null. Stop thread monitor.", new Object[0]);
                    return;
                }
                ac acVar = this.f5609e;
                acVar.a();
                a(acVar);
                boolean z2 = true;
                if (this.f5611g && this.f5606b) {
                    long jC = acVar.c();
                    if (jC > 1510 && jC < 199990) {
                        if (jC <= 5010) {
                            this.f5608d = 1;
                            y.c("timeSinceMsgSent in [2s, 5s], record stack", new Object[0]);
                        } else {
                            this.f5608d++;
                            if ((this.f5608d & (this.f5608d - 1)) != 0) {
                                z2 = false;
                            }
                            if (z2) {
                                y.c("timeSinceMsgSent in (5s, 200s), should record stack:true", new Object[0]);
                            }
                        }
                        z = z2;
                    }
                }
                if (z) {
                    acVar.d();
                }
                if (this.f5610f != null && this.f5606b) {
                    this.f5610f.a(acVar.b(), acVar.c());
                }
                ab.b(500 - ((System.currentTimeMillis() - jCurrentTimeMillis) % 500));
            } catch (Exception e2) {
                y.b(e2);
            } catch (OutOfMemoryError e3) {
                y.b(e3);
            }
        }
    }

    public final List<com.tencent.bugly.crashreport.crash.anr.c> c() {
        return this.f5609e.b(200000L);
    }

    private synchronized void a(ac acVar) {
        if (this.f5606b) {
            return;
        }
        if (this.f5607c && !acVar.b()) {
            y.c("Restart getting main stack trace.", new Object[0]);
            this.f5606b = true;
            this.f5607c = false;
        }
    }

    public final synchronized void d() {
        this.f5606b = false;
        y.c("Record stack trace is disabled.", new Object[0]);
    }

    public final synchronized void b(boolean z) {
        this.f5607c = true;
    }
}