package com.ido.ble.dfu.e.b;

import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static d f4339g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4340a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4341b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4342c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Timer f4343d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TimerTask f4344e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f4345f;

    class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            d.this.f();
        }
    }

    public interface b {
        void a(int i);
    }

    public static d b() {
        if (f4339g == null) {
            f4339g = new d();
        }
        return f4339g;
    }

    private void c() {
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[TempProgressUpdateTask] release");
        this.f4340a = false;
        this.f4345f = null;
        this.f4341b = 0;
    }

    private void d() {
        e();
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[TempProgressUpdateTask] startTimer");
        this.f4343d = new Timer();
        this.f4344e = new a();
        this.f4343d.schedule(this.f4344e, 0L, 1000L);
    }

    private void e() {
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[TempProgressUpdateTask] stopTimer");
        TimerTask timerTask = this.f4344e;
        if (timerTask != null) {
            timerTask.cancel();
        }
        Timer timer = this.f4343d;
        if (timer != null) {
            timer.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f4341b++;
        int i = this.f4341b;
        int i2 = i != 5 ? i == 10 ? 2 : i == 20 ? 3 : i == 35 ? 4 : i == 55 ? 5 : i == 80 ? 6 : i == 110 ? 7 : i == 145 ? 8 : i == 185 ? 9 : i == 230 ? 10 : this.f4342c : 1;
        b bVar = this.f4345f;
        if (bVar == null || !this.f4340a || i2 == this.f4342c) {
            return;
        }
        this.f4342c = i2;
        bVar.a(this.f4342c);
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[TempProgressUpdateTask] updateProgress , secondCount = " + this.f4341b + ", tmpProgress=" + this.f4342c);
    }

    public void a() {
        if (this.f4340a) {
            LogTool.d(com.ido.ble.dfu.a.f4245a, "[TempProgressUpdateTask] stop");
            e();
            c();
        }
    }

    public void a(b bVar) {
        if (this.f4340a) {
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[TempProgressUpdateTask] start ,listener = " + bVar);
        this.f4341b = 0;
        this.f4340a = true;
        this.f4345f = bVar;
        d();
    }
}