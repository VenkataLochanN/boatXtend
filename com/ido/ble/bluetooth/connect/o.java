package com.ido.ble.bluetooth.connect;

import com.ido.ble.common.m;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class o implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4082a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4083b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4084c = -1;

    class a implements m.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f4085a;

        /* JADX INFO: renamed from: com.ido.ble.bluetooth.connect.o$a$a, reason: collision with other inner class name */
        class RunnableC0055a implements Runnable {
            RunnableC0055a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] connect task time out, task id = " + o.this.f4084c);
                a.this.f4085a.run();
                o.this.f4082a = -1;
            }
        }

        a(Runnable runnable) {
            this.f4085a = runnable;
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            if (o.this.f4082a < 0) {
                return;
            }
            com.ido.ble.common.e.a(new RunnableC0055a());
        }
    }

    class b implements m.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f4088a;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] disconnect task time out, task id = " + o.this.f4084c);
                b.this.f4088a.run();
                o.this.f4083b = -1;
            }
        }

        b(Runnable runnable) {
            this.f4088a = runnable;
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            if (o.this.f4083b < 0) {
                return;
            }
            com.ido.ble.common.e.a(new a());
        }
    }

    class c implements m.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f4091a;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] discover services task time out, task id = " + o.this.f4084c);
                c.this.f4091a.run();
                o.this.f4084c = -1;
            }
        }

        c(Runnable runnable) {
            this.f4091a = runnable;
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            if (o.this.f4084c < 0) {
                return;
            }
            com.ido.ble.common.e.a(new a());
        }
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void a() {
        if (this.f4083b < 0) {
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] stop disconnect timeout task , task id = " + this.f4083b);
        com.ido.ble.common.m.a(this.f4083b);
        this.f4083b = -1;
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void a(Runnable runnable, long j) {
        if (this.f4083b >= 0) {
            return;
        }
        this.f4083b = com.ido.ble.common.m.a(new b(runnable), j);
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] start disconnect timeout task , task id = " + this.f4083b);
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void b() {
        if (this.f4082a < 0) {
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] stop connect timeout task , task id = " + this.f4082a);
        com.ido.ble.common.m.a(this.f4082a);
        this.f4082a = -1;
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void b(Runnable runnable, long j) {
        if (this.f4084c >= 0) {
            return;
        }
        this.f4084c = com.ido.ble.common.m.a(new c(runnable), j);
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] start discover services timeout task , task id = " + this.f4084c);
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void c() {
        if (this.f4084c < 0) {
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] stop discover services timeout task , task id = " + this.f4084c);
        com.ido.ble.common.m.a(this.f4084c);
        this.f4084c = -1;
    }

    @Override // com.ido.ble.bluetooth.connect.j
    public void c(Runnable runnable, long j) {
        if (this.f4082a >= 0) {
            return;
        }
        this.f4082a = com.ido.ble.common.m.a(new a(runnable), j);
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[TimeOutPresenter] start connect timeout task , task id = " + this.f4082a);
    }
}