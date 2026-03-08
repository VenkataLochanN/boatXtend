package com.ido.ble.dfu.d.a;

import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class b implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f4265a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Timer f4266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TimerTask f4267c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private InterfaceC0064b f4268d;

    class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            b.this.c();
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.dfu.d.a.b$b, reason: collision with other inner class name */
    public interface InterfaceC0064b {
        void onTimeOut();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[check no response state]------------check-----------");
        if (System.currentTimeMillis() - this.f4265a > 45000) {
            LogTool.b(com.ido.ble.dfu.a.f4245a, "[check no response state]--------------time--out-----------");
            f();
            this.f4268d.onTimeOut();
        }
    }

    private void d() {
        e();
    }

    private void e() {
        TimerTask timerTask = this.f4267c;
        if (timerTask != null) {
            timerTask.cancel();
        }
        Timer timer = this.f4266b;
        if (timer != null) {
            timer.cancel();
        }
    }

    private void f() {
        this.f4265a = 0L;
        e();
    }

    private void g() {
        this.f4267c = new a();
        this.f4266b.schedule(this.f4267c, 0L, 1000L);
    }

    @Override // com.ido.ble.dfu.d.a.d
    public void a() {
        this.f4265a = System.currentTimeMillis();
    }

    @Override // com.ido.ble.dfu.d.a.d
    public void a(InterfaceC0064b interfaceC0064b) {
        d();
        this.f4266b = new Timer();
        this.f4268d = interfaceC0064b;
        this.f4265a = System.currentTimeMillis();
        g();
    }

    @Override // com.ido.ble.dfu.d.a.d
    public void b() {
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[check no response state]------------end-----------");
        f();
    }
}