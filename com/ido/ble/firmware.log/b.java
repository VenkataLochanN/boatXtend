package com.ido.ble.firmware.log;

import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f4524a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Timer f4525b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TimerTask f4526c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private InterfaceC0084b f4527d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4528e;

    class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            b.this.c();
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.firmware.log.b$b, reason: collision with other inner class name */
    public interface InterfaceC0084b {
        void onTimeOut();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (System.currentTimeMillis() - this.f4524a > this.f4528e * 1000) {
            LogTool.b("DEVICE_REBOOT_LOG", "[check no response state]--------------time--out-----------");
            f();
            this.f4527d.onTimeOut();
        }
    }

    private void d() {
        e();
    }

    private void e() {
        TimerTask timerTask = this.f4526c;
        if (timerTask != null) {
            timerTask.cancel();
        }
        Timer timer = this.f4525b;
        if (timer != null) {
            timer.cancel();
        }
    }

    private void f() {
        this.f4524a = 0L;
        e();
    }

    private void g() {
        this.f4526c = new a();
        this.f4525b.schedule(this.f4526c, 0L, 1000L);
    }

    public void a() {
        f();
    }

    public void a(InterfaceC0084b interfaceC0084b, int i) {
        d();
        this.f4525b = new Timer();
        this.f4527d = interfaceC0084b;
        this.f4524a = System.currentTimeMillis();
        this.f4528e = i;
        g();
    }

    public void b() {
        this.f4524a = System.currentTimeMillis();
    }
}