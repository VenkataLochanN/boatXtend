package com.ido.ble.bluetooth.connect;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.logs.LogTool;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
class k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static k f4053e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f4054f = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f4057c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4055a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4056b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Handler f4058d = new Handler(Looper.getMainLooper());

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (k.f4053e == null) {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[ReconnectTask] mTask = null");
            } else if (k.this.f4056b) {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[ReconnectTask] isStopTask = true");
            } else {
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ReconnectTask] to try");
                k.this.f4057c.a(k.this.f4055a);
            }
        }
    }

    public interface b {
        void a();

        void a(int i);
    }

    private k() {
    }

    private void a(b bVar) {
        this.f4057c = bVar;
    }

    public static void b(b bVar) {
        if (f4053e == null) {
            f4053e = new k();
        }
        f4053e.a(bVar);
        f4053e.d();
    }

    public static boolean b() {
        k kVar = f4053e;
        if (kVar == null) {
            return false;
        }
        kVar.c();
        return true;
    }

    private void c() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ReconnectTask] cancelDelayTimer. ");
        this.f4058d.removeCallbacksAndMessages(null);
        b bVar = this.f4057c;
        if (bVar != null) {
            bVar.a(this.f4055a);
        }
    }

    private void d() {
        this.f4058d.removeCallbacksAndMessages(null);
        this.f4055a++;
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ReconnectTask] startTask(), mReTryTimes = " + this.f4055a);
        int i = this.f4055a;
        long j = i < 10 ? BootloaderScanner.TIMEOUT : i < 15 ? 15000L : 30000L;
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ReconnectTask] will start after " + j + "ms ");
        this.f4058d.postDelayed(new a(), j);
    }

    public static void e() {
        k kVar = f4053e;
        if (kVar != null) {
            kVar.f();
            f4053e = null;
        }
    }

    private void f() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ReconnectTask] stopTask()");
        this.f4056b = true;
        this.f4055a = 0;
        this.f4058d.removeCallbacksAndMessages(null);
    }
}