package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: AbstractAsyncTask.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class dq<Params, Progress, Result> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Executor f643b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Executor f644c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final c f647f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static volatile Executor f648g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final ThreadFactory f645d = new ThreadFactory() { // from class: com.amap.api.mapcore.util.dq.1

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f650a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AbstractAsyncTask #" + this.f650a.getAndIncrement());
        }
    };

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final BlockingQueue<Runnable> f646e = new LinkedBlockingQueue(10);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Executor f642a = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, f646e, f645d, new ThreadPoolExecutor.DiscardOldestPolicy());
    private volatile e j = e.PENDING;
    private final AtomicBoolean k = new AtomicBoolean();
    private final AtomicBoolean l = new AtomicBoolean();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final a<Params, Result> f649h = new a<Params, Result>() { // from class: com.amap.api.mapcore.util.dq.2
        @Override // java.util.concurrent.Callable
        public Result call() throws Exception {
            dq.this.l.set(true);
            dq dqVar = dq.this;
            return (Result) dqVar.d(dqVar.a((Object[]) this.f654b));
        }
    };
    private final FutureTask<Result> i = new FutureTask<Result>(this.f649h) { // from class: com.amap.api.mapcore.util.dq.3
        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                dq.this.c(dq.this.i.get());
            } catch (InterruptedException e2) {
                Log.w("AbstractAsyncTask", e2);
            } catch (CancellationException unused) {
                dq.this.c((Object) null);
            } catch (ExecutionException e3) {
                throw new RuntimeException("An error occured while executing doInBackground()", e3.getCause());
            }
        }
    };

    /* JADX INFO: compiled from: AbstractAsyncTask.java */
    public enum e {
        PENDING,
        RUNNING,
        FINISHED
    }

    protected abstract Result a(Params... paramsArr);

    protected void a(Result result) {
    }

    protected void b() {
    }

    protected void b(Progress... progressArr) {
    }

    protected void c() {
    }

    static {
        f643b = er.c() ? new d() : new ThreadPoolExecutor(1, 2, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ee("AMapSERIAL_EXECUTOR"), new ThreadPoolExecutor.AbortPolicy());
        f644c = new ThreadPoolExecutor(2, 2, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ee("AMapDUAL_THREAD_EXECUTOR"), new ThreadPoolExecutor.AbortPolicy());
        f647f = new c(Looper.getMainLooper());
        f648g = f643b;
    }

    /* JADX INFO: compiled from: AbstractAsyncTask.java */
    private static class d implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ArrayDeque<Runnable> f657a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Runnable f658b;

        private d() {
            this.f657a = new ArrayDeque<>();
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(final Runnable runnable) {
            this.f657a.offer(new Runnable() { // from class: com.amap.api.mapcore.util.dq.d.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        d.this.a();
                    }
                }
            });
            if (this.f658b == null) {
                a();
            }
        }

        protected synchronized void a() {
            Runnable runnablePoll = this.f657a.poll();
            this.f658b = runnablePoll;
            if (runnablePoll != null) {
                dq.f642a.execute(this.f658b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Result result) {
        if (this.l.get()) {
            return;
        }
        d(result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Result d(Result result) {
        f647f.obtainMessage(1, new b(this, result)).sendToTarget();
        return result;
    }

    public final e a() {
        return this.j;
    }

    protected void b(Result result) {
        c();
    }

    public final boolean d() {
        return this.k.get();
    }

    public final boolean a(boolean z) {
        this.k.set(true);
        return this.i.cancel(z);
    }

    public final dq<Params, Progress, Result> c(Params... paramsArr) {
        return a(f648g, paramsArr);
    }

    /* JADX INFO: renamed from: com.amap.api.mapcore.util.dq$4, reason: invalid class name */
    /* JADX INFO: compiled from: AbstractAsyncTask.java */
    static /* synthetic */ class AnonymousClass4 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f653a = new int[e.values().length];

        static {
            try {
                f653a[e.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f653a[e.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public final dq<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.j != e.PENDING) {
            int i = AnonymousClass4.f653a[this.j.ordinal()];
            if (i == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.j = e.RUNNING;
        b();
        this.f649h.f654b = paramsArr;
        executor.execute(this.i);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Result result) {
        if (d()) {
            b(result);
        } else {
            a(result);
        }
        this.j = e.FINISHED;
    }

    /* JADX INFO: compiled from: AbstractAsyncTask.java */
    private static class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.obj == null || !(message.obj instanceof b)) {
                return;
            }
            b bVar = (b) message.obj;
            int i = message.what;
            if (i == 1) {
                bVar.f655a.e(bVar.f656b[0]);
            } else {
                if (i != 2) {
                    return;
                }
                bVar.f655a.b((Object[]) bVar.f656b);
            }
        }
    }

    /* JADX INFO: compiled from: AbstractAsyncTask.java */
    private static abstract class a<Params, Result> implements Callable<Result> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Params[] f654b;

        private a() {
        }
    }

    /* JADX INFO: compiled from: AbstractAsyncTask.java */
    private static class b<Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final dq f655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Data[] f656b;

        b(dq dqVar, Data... dataArr) {
            this.f655a = dqVar;
            this.f656b = dataArr;
        }
    }
}