package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: ThreadTask.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class js implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    a f1488e;

    /* JADX INFO: compiled from: ThreadTask.java */
    interface a {
        void a(js jsVar);

        void b(js jsVar);

        void c(js jsVar);
    }

    public abstract void runTask();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.f1488e != null) {
                this.f1488e.a(this);
            }
            if (Thread.interrupted()) {
                return;
            }
            runTask();
            if (Thread.interrupted() || this.f1488e == null) {
                return;
            }
            this.f1488e.b(this);
        } catch (Throwable th) {
            hn.c(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }

    public final void cancelTask() {
        try {
            if (this.f1488e != null) {
                this.f1488e.c(this);
            }
        } catch (Throwable th) {
            hn.c(th, "ThreadTask", "cancelTask");
            th.printStackTrace();
        }
    }
}