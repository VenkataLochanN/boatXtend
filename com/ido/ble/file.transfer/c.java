package com.ido.ble.file.transfer;

import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class c implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f4496a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Timer f4497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TimerTask f4498c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f4499d;

    class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.this.c();
        }
    }

    public interface b {
        void onTimeOut();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f4496a;
        LogTool.d(com.ido.ble.file.transfer.a.f4469a, "[check no response state]------------check-----" + (jCurrentTimeMillis / 1000));
        if (jCurrentTimeMillis > 70000) {
            LogTool.b(com.ido.ble.file.transfer.a.f4469a, "[check no response state]--------------time--out-----------");
            f();
            this.f4499d.onTimeOut();
        }
    }

    private void d() {
        e();
    }

    private void e() {
        TimerTask timerTask = this.f4498c;
        if (timerTask != null) {
            timerTask.cancel();
        }
        Timer timer = this.f4497b;
        if (timer != null) {
            timer.cancel();
        }
    }

    private void f() {
        this.f4496a = 0L;
        e();
    }

    private void g() {
        this.f4498c = new a();
        this.f4497b.schedule(this.f4498c, 0L, 1000L);
    }

    @Override // com.ido.ble.file.transfer.d
    public void a() {
        this.f4496a = System.currentTimeMillis();
    }

    @Override // com.ido.ble.file.transfer.d
    public void a(b bVar) {
        d();
        this.f4497b = new Timer();
        this.f4499d = bVar;
        this.f4496a = System.currentTimeMillis();
        g();
    }

    @Override // com.ido.ble.file.transfer.d
    public void b() {
        LogTool.d(com.ido.ble.file.transfer.a.f4469a, "[check no response state]------------end-----------");
        f();
    }
}