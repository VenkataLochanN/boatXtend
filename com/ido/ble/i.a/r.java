package com.ido.ble.i.a;

import com.ido.ble.callback.BindCallBack;
import com.ido.ble.logs.LogTool;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f4605a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final long f4606b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final long f4607c = 20000;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f4608d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static b f4609e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static BindCallBack.ICallBack f4610f = new a();

    static class a implements BindCallBack.ICallBack {
        a() {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onCancel() {
            boolean unused = r.f4608d = true;
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onFailed(BindCallBack.BindFailedError bindFailedError) {
            boolean unused = r.f4608d = true;
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onNeedAuth(int i) {
            boolean unused = r.f4608d = true;
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onReject() {
            boolean unused = r.f4608d = true;
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onSuccess() {
            boolean unused = r.f4608d = true;
        }
    }

    private static class b extends TimerTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Timer f4611a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4612b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f4613c = true;

        public b(Timer timer) {
            this.f4611a = timer;
        }

        private void b() {
            BindCallBack.b();
            com.ido.ble.event.stat.one.c.a("error:13");
            LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] out of time, bind failed");
        }

        private void c() {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] (BindTimerTask) task canceled.");
            this.f4613c = false;
            this.f4611a.cancel();
            this.f4611a = null;
            com.ido.ble.callback.b.K().b(r.f4610f);
        }

        private void d() {
            com.ido.ble.i.a.a.j0();
        }

        public Timer a() {
            return this.f4611a;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (!this.f4613c) {
                LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] (BindTimerTask) isDoing is false.");
                return;
            }
            if (!com.ido.ble.bluetooth.a.h()) {
                LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] (BindTimerTask) disconnect.");
                c();
                return;
            }
            if (r.f4608d) {
                LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] (BindTimerTask) isRespond is true.");
                c();
                return;
            }
            int i = this.f4612b;
            if (i < 1) {
                this.f4612b = i + 1;
                d();
            } else {
                LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] (BindTimerTask) out of max retry times.");
                c();
                b();
            }
        }
    }

    public static void c() {
        b bVar = f4609e;
        if (bVar != null && bVar.a() != null) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] (BindTimerTask ing) ...");
            return;
        }
        f4608d = false;
        com.ido.ble.callback.b.K().a(f4610f);
        Timer timer = new Timer();
        f4609e = new b(timer);
        timer.schedule(f4609e, 0L, f4607c);
    }
}