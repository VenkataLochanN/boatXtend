package com.amap.api.mapcore.util;

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
/* JADX INFO: loaded from: classes.dex */
public class hn extends hk implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static ExecutorService f1252e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static WeakReference<Context> f1254g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f1256d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Set<Integer> f1253f = Collections.synchronizedSet(new HashSet());

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final ThreadFactory f1255h = new ThreadFactory() { // from class: com.amap.api.mapcore.util.hn.2

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f1260a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.f1260a.getAndIncrement()) { // from class: com.amap.api.mapcore.util.hn.2.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        super.run();
                    } catch (Throwable unused) {
                    }
                }
            };
        }
    };

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            f1254g = new WeakReference<>(context.getApplicationContext());
        } catch (Throwable unused) {
        }
    }

    public static synchronized hn a(Context context, gs gsVar) throws gh {
        try {
            if (gsVar == null) {
                throw new gh("sdk info is null");
            }
            if (gsVar.a() == null || "".equals(gsVar.a())) {
                throw new gh("sdk name is invalid");
            }
            try {
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (!f1253f.add(Integer.valueOf(gsVar.hashCode()))) {
                return (hn) hk.f1238a;
            }
            if (hk.f1238a == null) {
                hk.f1238a = new hn(context, gsVar);
            } else {
                hk.f1238a.f1240c = false;
            }
            hk.f1238a.a(context, gsVar, hk.f1238a.f1240c);
            return (hn) hk.f1238a;
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public static synchronized void b() {
        try {
            if (f1252e != null) {
                f1252e.shutdown();
            }
            ig.a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            if (hk.f1238a != null && Thread.getDefaultUncaughtExceptionHandler() == hk.f1238a && hk.f1238a.f1239b != null) {
                Thread.setDefaultUncaughtExceptionHandler(hk.f1238a.f1239b);
            }
            hk.f1238a = null;
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @Override // com.amap.api.mapcore.util.hk
    protected void a(gs gsVar, String str, String str2) {
        ho.a(gsVar, this.f1256d, str2, str);
    }

    @Override // com.amap.api.mapcore.util.hk
    protected void a(Throwable th, int i, String str, String str2) {
        ho.a(this.f1256d, th, i, str, str2);
    }

    public static void a(Context context, gs gsVar, String str, String str2, String str3) {
        ho.a(context, gsVar, str, 0, str2, str3);
    }

    public static void b(Context context, gs gsVar, String str, String str2, String str3) {
        ho.a(context, gsVar, str, 1, str2, str3);
    }

    @Override // com.amap.api.mapcore.util.hk
    protected void a() {
        hl.a(this.f1256d);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (th == null) {
            return;
        }
        a(th, 0, null, null);
        if (this.f1239b != null) {
            try {
                Thread.setDefaultUncaughtExceptionHandler(this.f1239b);
            } catch (Throwable unused) {
            }
            this.f1239b.uncaughtException(thread, th);
        }
    }

    @Override // com.amap.api.mapcore.util.hk
    protected void a(Context context, final gs gsVar, final boolean z) {
        try {
            ExecutorService executorServiceD = d();
            if (executorServiceD != null && !executorServiceD.isShutdown()) {
                executorServiceD.submit(new Runnable() { // from class: com.amap.api.mapcore.util.hn.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                hl.a(gsVar);
                            }
                            if (z) {
                                ho.a(hn.this.f1256d);
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

    public void b(Throwable th, String str, String str2) {
        if (th == null) {
            return;
        }
        try {
            a(th, 1, str, str2);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private hn(Context context, gs gsVar) {
        this.f1256d = context;
        f();
    }

    private void f() {
        try {
            this.f1239b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.f1239b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f1240c = true;
                return;
            }
            String string = this.f1239b.toString();
            if (!string.startsWith("com.amap.apis.utils.core.dynamiccore") && (string.indexOf("com.amap.api") != -1 || string.indexOf("com.loc") != -1)) {
                this.f1240c = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f1240c = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void c() {
        WeakReference<Context> weakReference = f1254g;
        if (weakReference != null && weakReference.get() != null) {
            hl.a(f1254g.get());
        } else if (hk.f1238a != null) {
            hk.f1238a.a();
        }
    }

    public static void c(Throwable th, String str, String str2) {
        try {
            if (hk.f1238a != null) {
                hk.f1238a.a(th, 1, str, str2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(gs gsVar, String str, String str2, String str3, String str4, String str5) {
        try {
            if (hk.f1238a != null) {
                hk.f1238a.a(gsVar, "path:" + str + ",type:" + str2 + ",gsid:" + str3 + ",csid:" + str4 + ",code:" + str5, "networkError");
            }
        } catch (Throwable unused) {
        }
    }

    public static void b(gs gsVar, String str, String str2) {
        try {
            if (hk.f1238a != null) {
                hk.f1238a.a(gsVar, str, str2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(gs gsVar, String str, gh ghVar) {
        if (ghVar != null) {
            a(gsVar, str, ghVar.c(), ghVar.d(), ghVar.e(), ghVar.b());
        }
    }

    public static synchronized ExecutorService d() {
        try {
            if (f1252e == null || f1252e.isShutdown()) {
                f1252e = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(256), f1255h);
            }
        } catch (Throwable unused) {
        }
        return f1252e;
    }

    public static synchronized hn e() {
        return (hn) hk.f1238a;
    }
}