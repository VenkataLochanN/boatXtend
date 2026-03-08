package com.ido.ble.dfu.e.b;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.dfu.e.b.c;
import com.ido.ble.logs.LogTool;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f4309g = 2;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int f4310h = 0;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 3;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f4311a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4312b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4313c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4314d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler f4315e = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4316f = 0;

    /* JADX INFO: renamed from: com.ido.ble.dfu.e.b.a$a, reason: collision with other inner class name */
    class C0071a implements c.d {

        /* JADX INFO: renamed from: com.ido.ble.dfu.e.b.a$a$a, reason: collision with other inner class name */
        class RunnableC0072a implements Runnable {
            RunnableC0072a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.e();
            }
        }

        /* JADX INFO: renamed from: com.ido.ble.dfu.e.b.a$a$b */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.e();
            }
        }

        C0071a() {
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void a() {
            a.this.f4316f = 3;
            a.this.d().postDelayed(new RunnableC0072a(), BootloaderScanner.TIMEOUT);
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void a(BLEDevice bLEDevice) {
            a.this.f4316f = 1;
            a.this.c();
            if (a.this.f4311a != null) {
                a.this.f4311a.c();
            }
            a.this.f4311a = null;
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void b(BLEDevice bLEDevice) {
            a.this.f4316f = 2;
            a.this.d().postDelayed(new b(), BootloaderScanner.TIMEOUT);
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void c(BLEDevice bLEDevice) {
            a.this.f4316f = 1;
            a.this.c();
            if (a.this.f4311a != null) {
                a.this.f4311a.c();
            }
            a.this.f4311a = null;
        }
    }

    public interface b {
        void a();

        void b();

        void c();
    }

    private void b() {
        new c().a(new C0071a(), this.f4312b, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[CheckDFUResultTask] finished");
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Handler d() {
        if (this.f4315e == null) {
            this.f4315e = new Handler(Looper.getMainLooper());
        }
        return this.f4315e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.f4313c < 2) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[CheckDFUResultTask] rechecking...");
            this.f4313c++;
            b();
            return;
        }
        LogTool.b(com.ido.ble.dfu.a.f4246b, "[CheckDFUResultTask] out of max retry times, failed!");
        c();
        b bVar = this.f4311a;
        if (bVar != null) {
            int i2 = this.f4316f;
            if (3 == i2) {
                bVar.b();
            } else if (2 == i2) {
                bVar.a();
            }
        }
        this.f4311a = null;
    }

    private void f() {
        Handler handler = this.f4315e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.f4315e = null;
        this.f4313c = 0;
        this.f4314d = false;
    }

    public void a() {
        if (this.f4314d) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[CheckDFUResultTask] stop task");
            f();
        }
    }

    public void a(b bVar, String str) {
        if (this.f4314d) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[CheckDFUResultTask] is in doing state, ignore this action");
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[CheckDFUResultTask] start");
        this.f4311a = bVar;
        this.f4312b = str;
        b();
        this.f4314d = true;
    }
}