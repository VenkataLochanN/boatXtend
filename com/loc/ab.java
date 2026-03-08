package com.loc;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: SDKLogHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ab extends y implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static ExecutorService f4708e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static WeakReference<Context> f4710g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f4712d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Set<Integer> f4709f = Collections.synchronizedSet(new HashSet());

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final ThreadFactory f4711h = new ThreadFactory() { // from class: com.loc.ab.2

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f4716a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.f4716a.getAndIncrement()) { // from class: com.loc.ab.2.1
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    try {
                        super.run();
                    } catch (Throwable unused) {
                    }
                }
            };
        }
    };

    private ab(Context context) {
        this.f4712d = context;
        try {
            this.f5341b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.f5341b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f5342c = true;
                return;
            }
            String string = this.f5341b.toString();
            if (!string.startsWith("com.amap.apis.utils.core.dynamiccore") && (string.indexOf("com.amap.api") != -1 || string.indexOf("com.loc") != -1)) {
                this.f5342c = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f5342c = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized ab a(Context context, t tVar) throws j {
        try {
            if (tVar == null) {
                throw new j("sdk info is null");
            }
            if (tVar.a() == null || "".equals(tVar.a())) {
                throw new j("sdk name is invalid");
            }
            try {
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (!f4709f.add(Integer.valueOf(tVar.hashCode()))) {
                return (ab) y.f5340a;
            }
            if (y.f5340a == null) {
                y.f5340a = new ab(context);
            } else {
                y.f5340a.f5342c = false;
            }
            y.f5340a.a(tVar, y.f5340a.f5342c);
            return (ab) y.f5340a;
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public static void a(t tVar, String str, j jVar) {
        if (jVar != null) {
            a(tVar, str, jVar.c(), jVar.d(), jVar.e(), jVar.b());
        }
    }

    public static void a(t tVar, String str, String str2, String str3, String str4) {
        a(tVar, str, str2, str3, "", str4);
    }

    public static void a(t tVar, String str, String str2, String str3, String str4, String str5) {
        try {
            if (y.f5340a != null) {
                y.f5340a.a(tVar, "path:" + str + ",type:" + str2 + ",gsid:" + str3 + ",csid:" + str4 + ",code:" + str5, "networkError");
            }
        } catch (Throwable unused) {
        }
    }

    public static synchronized void b() {
        try {
            if (f4708e != null) {
                f4708e.shutdown();
            }
            ao.a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            if (y.f5340a != null && Thread.getDefaultUncaughtExceptionHandler() == y.f5340a && y.f5340a.f5341b != null) {
                Thread.setDefaultUncaughtExceptionHandler(y.f5340a.f5341b);
            }
            y.f5340a = null;
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static void b(t tVar, String str, String str2) {
        try {
            if (y.f5340a != null) {
                y.f5340a.a(tVar, str, str2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void b(Throwable th, String str, String str2) {
        try {
            if (y.f5340a != null) {
                y.f5340a.a(th, 1, str, str2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void c() {
        WeakReference<Context> weakReference = f4710g;
        if (weakReference != null && weakReference.get() != null) {
            z.a(f4710g.get());
        } else if (y.f5340a != null) {
            y.f5340a.a();
        }
    }

    public static synchronized ExecutorService d() {
        try {
            if (f4708e == null || f4708e.isShutdown()) {
                f4708e = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(256), f4711h);
            }
        } catch (Throwable unused) {
        }
        return f4708e;
    }

    @Override // com.loc.y
    protected final void a() {
        z.a(this.f4712d);
    }

    @Override // com.loc.y
    protected final void a(t tVar, String str, String str2) {
        ac.a(tVar, this.f4712d, str2, str);
    }

    @Override // com.loc.y
    protected final void a(final t tVar, final boolean z) {
        try {
            ExecutorService executorServiceD = d();
            if (executorServiceD != null && !executorServiceD.isShutdown()) {
                executorServiceD.submit(new Runnable() { // from class: com.loc.ab.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                z.a(tVar);
                            }
                            if (z) {
                                ac.a(ab.this.f4712d);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException unused) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.loc.y
    protected final void a(Throwable th, int i, String str, String str2) {
        ac.a(this.f4712d, th, i, str, str2);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        if (th == null) {
            return;
        }
        a(th, 0, null, null);
        if (this.f5341b != null) {
            try {
                Thread.setDefaultUncaughtExceptionHandler(this.f5341b);
            } catch (Throwable unused) {
            }
            this.f5341b.uncaughtException(thread, th);
        }
    }
}