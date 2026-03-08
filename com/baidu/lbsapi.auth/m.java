package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
class m extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Handler f2015a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Object f2016b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2017c;

    m() {
        this.f2015a = null;
        this.f2016b = new Object();
        this.f2017c = false;
    }

    m(String str) {
        super(str);
        this.f2015a = null;
        this.f2016b = new Object();
        this.f2017c = false;
    }

    public void a() {
        if (a.f1989a) {
            a.a("Looper thread quit()");
        }
        this.f2015a.getLooper().quit();
    }

    public void b() {
        synchronized (this.f2016b) {
            try {
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (!this.f2017c) {
                this.f2016b.wait();
            }
        }
    }

    public void c() {
        synchronized (this.f2016b) {
            this.f2017c = true;
            this.f2016b.notifyAll();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.f2015a = new Handler();
        if (a.f1989a) {
            a.a("new Handler() finish!!");
        }
        Looper.loop();
        if (a.f1989a) {
            a.a("LooperThread run() thread id:" + String.valueOf(Thread.currentThread().getId()));
        }
    }
}