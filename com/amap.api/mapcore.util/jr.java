package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.js;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ThreadPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class jr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static jr f1483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ExecutorService f1484b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ConcurrentHashMap<js, Future<?>> f1485c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private js.a f1486d = new js.a() { // from class: com.amap.api.mapcore.util.jr.1
        @Override // com.amap.api.mapcore.util.js.a
        public void a(js jsVar) {
        }

        @Override // com.amap.api.mapcore.util.js.a
        public void b(js jsVar) {
            jr.this.a(jsVar, false);
        }

        @Override // com.amap.api.mapcore.util.js.a
        public void c(js jsVar) {
            jr.this.a(jsVar, true);
        }
    };

    public static synchronized jr a(int i) {
        if (f1483a == null) {
            f1483a = new jr(i);
        }
        return f1483a;
    }

    public static jr b(int i) {
        return new jr(i);
    }

    private jr(int i) {
        try {
            this.f1484b = new ThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(256));
        } catch (Throwable th) {
            hn.c(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    public void a(js jsVar) throws gh {
        try {
            if (!b(jsVar) && this.f1484b != null && !this.f1484b.isShutdown()) {
                jsVar.f1488e = this.f1486d;
                try {
                    Future<?> futureSubmit = this.f1484b.submit(jsVar);
                    if (futureSubmit == null) {
                        return;
                    }
                    a(jsVar, futureSubmit);
                } catch (RejectedExecutionException unused) {
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            hn.c(th, "TPool", "addTask");
            throw new gh("thread pool has exception");
        }
    }

    public static synchronized void a() {
        try {
            if (f1483a != null) {
                f1483a.b();
                f1483a = null;
            }
        } finally {
        }
    }

    private void b() {
        try {
            Iterator<Map.Entry<js, Future<?>>> it = this.f1485c.entrySet().iterator();
            while (it.hasNext()) {
                Future<?> future = this.f1485c.get(it.next().getKey());
                if (future != null) {
                    try {
                        future.cancel(true);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            this.f1485c.clear();
            this.f1484b.shutdown();
        } catch (Throwable th) {
            hn.c(th, "TPool", "destroy");
            th.printStackTrace();
        }
    }

    private synchronized boolean b(js jsVar) {
        boolean zContainsKey;
        zContainsKey = false;
        try {
            zContainsKey = this.f1485c.containsKey(jsVar);
        } catch (Throwable th) {
            hn.c(th, "TPool", "contain");
            th.printStackTrace();
        }
        return zContainsKey;
    }

    private synchronized void a(js jsVar, Future<?> future) {
        try {
            this.f1485c.put(jsVar, future);
        } catch (Throwable th) {
            hn.c(th, "TPool", "addQueue");
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(js jsVar, boolean z) {
        try {
            Future<?> futureRemove = this.f1485c.remove(jsVar);
            if (z && futureRemove != null) {
                futureRemove.cancel(true);
            }
        } finally {
        }
    }
}