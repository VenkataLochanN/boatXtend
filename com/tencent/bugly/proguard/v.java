package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static v f5754a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f5756c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f5758e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f5759f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Map<Integer, Long> f5757d = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private LinkedBlockingQueue<Runnable> f5760g = new LinkedBlockingQueue<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private LinkedBlockingQueue<Runnable> f5761h = new LinkedBlockingQueue<>();
    private final Object i = new Object();
    private int j = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final o f5755b = o.a();

    static /* synthetic */ int b(v vVar) {
        int i = vVar.j - 1;
        vVar.j = i;
        return i;
    }

    private v(Context context) {
        this.f5756c = context;
    }

    public static synchronized v a(Context context) {
        if (f5754a == null) {
            f5754a = new v(context);
        }
        return f5754a;
    }

    public static synchronized v a() {
        return f5754a;
    }

    public final void a(int i, an anVar, String str, String str2, u uVar, long j, boolean z) {
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            a(new w(this.f5756c, i, anVar.f5640g, a.a((Object) anVar), str, str2, uVar, true, z), true, true, j);
        } catch (Throwable th2) {
            th = th2;
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    public final void a(int i, an anVar, String str, String str2, u uVar, boolean z) {
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            a(new w(this.f5756c, i, anVar.f5640g, a.a((Object) anVar), str, str2, uVar, 0, 0, false, null), z, false, 0L);
        } catch (Throwable th2) {
            th = th2;
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    public final long a(boolean z) {
        long jB;
        long jB2 = ab.b();
        int i = z ? 5 : 3;
        List<q> listA = this.f5755b.a(i);
        if (listA != null && listA.size() > 0) {
            jB = 0;
            try {
                q qVar = listA.get(0);
                if (qVar.f5733e >= jB2) {
                    jB = ab.b(qVar.f5735g);
                    if (i == 3) {
                        this.f5758e = jB;
                    } else {
                        this.f5759f = jB;
                    }
                    listA.remove(qVar);
                }
            } catch (Throwable th) {
                y.a(th);
            }
            if (listA.size() > 0) {
                this.f5755b.a(listA);
            }
        } else {
            jB = z ? this.f5759f : this.f5758e;
        }
        y.c("[UploadManager] Local network consume: %d KB", Long.valueOf(jB / 1024));
        return jB;
    }

    protected final synchronized void a(long j, boolean z) {
        int i = z ? 5 : 3;
        q qVar = new q();
        qVar.f5730b = i;
        qVar.f5733e = ab.b();
        qVar.f5731c = "";
        qVar.f5732d = "";
        qVar.f5735g = ab.c(j);
        this.f5755b.b(i);
        this.f5755b.a(qVar);
        if (z) {
            this.f5759f = j;
        } else {
            this.f5758e = j;
        }
        y.c("[UploadManager] Network total consume: %d KB", Long.valueOf(j / 1024));
    }

    public final synchronized void a(int i, long j) {
        if (i < 0) {
            y.e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i));
            return;
        }
        this.f5757d.put(Integer.valueOf(i), Long.valueOf(j));
        q qVar = new q();
        qVar.f5730b = i;
        qVar.f5733e = j;
        qVar.f5731c = "";
        qVar.f5732d = "";
        qVar.f5735g = new byte[0];
        this.f5755b.b(i);
        this.f5755b.a(qVar);
        y.c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i), ab.a(j));
    }

    public final synchronized long a(int i) {
        if (i >= 0) {
            Long l = this.f5757d.get(Integer.valueOf(i));
            if (l != null) {
                return l.longValue();
            }
        } else {
            y.e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i));
        }
        return 0L;
    }

    public final boolean b(int i) {
        if (com.tencent.bugly.b.f5373c) {
            y.c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - a(i);
        y.c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf(jCurrentTimeMillis / 1000), Integer.valueOf(i));
        if (jCurrentTimeMillis >= 30000) {
            return true;
        }
        y.a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
        return false;
    }

    private void c(int i) {
        x xVarA = x.a();
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        final LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
        synchronized (this.i) {
            y.c("[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            int size = this.f5760g.size();
            final int size2 = this.f5761h.size();
            if (size == 0 && size2 == 0) {
                y.c("[UploadManager] There is no upload task in queue.", new Object[0]);
                return;
            }
            if (xVarA == null || !xVarA.c()) {
                size2 = 0;
            }
            a(this.f5760g, linkedBlockingQueue, size);
            a(this.f5761h, linkedBlockingQueue2, size2);
            a(size, linkedBlockingQueue);
            if (size2 > 0) {
                y.c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(size2), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            }
            x xVarA2 = x.a();
            if (xVarA2 != null) {
                xVarA2.a(new Runnable(this) { // from class: com.tencent.bugly.proguard.v.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Runnable runnable;
                        for (int i2 = 0; i2 < size2 && (runnable = (Runnable) linkedBlockingQueue2.poll()) != null; i2++) {
                            runnable.run();
                        }
                    }
                });
            }
        }
    }

    private static void a(LinkedBlockingQueue<Runnable> linkedBlockingQueue, LinkedBlockingQueue<Runnable> linkedBlockingQueue2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            Runnable runnablePeek = linkedBlockingQueue.peek();
            if (runnablePeek == null) {
                return;
            }
            try {
                linkedBlockingQueue2.put(runnablePeek);
                linkedBlockingQueue.poll();
            } catch (Throwable th) {
                y.e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th.getMessage());
            }
        }
    }

    private void a(int i, LinkedBlockingQueue<Runnable> linkedBlockingQueue) {
        x xVarA = x.a();
        if (i > 0) {
            y.c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(i), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        }
        for (int i2 = 0; i2 < i; i2++) {
            final Runnable runnablePoll = linkedBlockingQueue.poll();
            if (runnablePoll == null) {
                return;
            }
            synchronized (this.i) {
                if (this.j >= 2 && xVarA != null) {
                    xVarA.a(runnablePoll);
                } else {
                    y.a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
                    if (ab.a(new Runnable() { // from class: com.tencent.bugly.proguard.v.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            runnablePoll.run();
                            synchronized (v.this.i) {
                                v.b(v.this);
                            }
                        }
                    }, "BUGLY_ASYNC_UPLOAD") != null) {
                        synchronized (this.i) {
                            this.j++;
                        }
                    } else {
                        y.d("[UploadManager] Failed to start a thread to execute asynchronous upload task,will try again next time.", new Object[0]);
                        a(runnablePoll, true);
                    }
                }
            }
        }
    }

    private boolean a(Runnable runnable, boolean z) {
        if (runnable == null) {
            y.a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            y.c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.i) {
                if (z) {
                    this.f5760g.put(runnable);
                } else {
                    this.f5761h.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            y.e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    private void a(Runnable runnable, boolean z, boolean z2, long j) {
        if (runnable == null) {
            y.d("[UploadManager] Upload task should not be null", new Object[0]);
        }
        y.c("[UploadManager] Add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (!z2) {
            a(runnable, z);
            c(0);
            return;
        }
        if (runnable == null) {
            y.d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        y.c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread threadA = ab.a(runnable, "BUGLY_SYNC_UPLOAD");
        if (threadA == null) {
            y.e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            a(runnable, true);
            return;
        }
        try {
            threadA.join(j);
        } catch (Throwable th) {
            y.e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
            a(runnable, true);
            c(0);
        }
    }
}