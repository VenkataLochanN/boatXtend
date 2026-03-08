package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.baidu.mapapi.synchronization.DisplayOptions;
import com.baidu.mapapi.synchronization.RoleOptions;
import com.ido.life.util.DateUtil;
import java.lang.Thread;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static g f3725c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Thread f3726d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private c f3730f;
    private boolean k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3723a = d.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f3724b = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static volatile boolean f3727e = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static volatile long f3728g = BootloaderScanner.TIMEOUT;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static long f3729h = BootloaderScanner.TIMEOUT;
    private static volatile boolean i = false;
    private static int j = 1000;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final d f3731a = new d();
    }

    private static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f3732a;

        b(String str) {
            this.f3732a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!d.f3727e) {
                if (d.f3725c != null) {
                    d.f3725c.a(d.f3724b, d.i);
                    boolean unused = d.i = false;
                }
                try {
                    Thread.sleep(d.f3728g);
                } catch (InterruptedException unused2) {
                    Thread.currentThread().interrupt();
                }
                int iD = d.f3725c != null ? d.f3725c.d() : 0;
                if (iD >= 3) {
                    long j = (iD / 3) + 1;
                    long j2 = d.f3729h * j;
                    long j3 = DateUtil.MINUTE;
                    if (j2 < DateUtil.MINUTE) {
                        j3 = d.f3729h * j;
                    }
                    long unused3 = d.f3728g = j3;
                } else {
                    long unused4 = d.f3728g = d.f3729h;
                }
            }
        }
    }

    private static class c extends Handler {
        c() {
        }

        private void a(int i) {
            if (d.j == i) {
                boolean unused = d.i = false;
            } else {
                boolean unused2 = d.i = true;
                int unused3 = d.j = i;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(d.f3723a, "The order state is: " + message.what);
            a(message.what);
            int i = message.what;
            if (i != 0) {
                if (i == 1 || i == 2 || i == 3 || i == 4) {
                    d.q();
                    return;
                } else if (i != 5) {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(d.f3723a, "The order state is undefined");
                    return;
                }
            }
            d.p();
        }
    }

    private d() {
        this.k = true;
    }

    static d a() {
        return a.f3731a;
    }

    private void o() {
        f3727e = true;
        Thread thread = f3726d;
        if (thread != null) {
            thread.interrupt();
            f3726d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void p() {
        f3727e = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void q() {
        if (f3726d == null) {
            return;
        }
        f3727e = false;
        if (Thread.State.NEW == f3726d.getState()) {
            f3726d.start();
        }
        if (Thread.State.TERMINATED == f3726d.getState()) {
            f3726d = null;
            f3726d = new Thread(new b(Thread.currentThread().getName()));
            f3726d.start();
        }
    }

    synchronized void a(int i2) {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.c(f3723a, "The order state = " + i2);
        f3724b = i2;
        if (this.f3730f == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3723a, "SyncDataRequestHandler is null");
            return;
        }
        Message messageObtainMessage = this.f3730f.obtainMessage();
        messageObtainMessage.what = i2;
        this.f3730f.sendMessage(messageObtainMessage);
    }

    void a(View view) {
        g gVar = f3725c;
        if (gVar != null) {
            gVar.a(view);
        }
    }

    void a(DisplayOptions displayOptions) {
        g gVar = f3725c;
        if (gVar != null) {
            gVar.a(displayOptions);
        }
    }

    void a(RoleOptions roleOptions) {
        g gVar = f3725c;
        if (gVar != null) {
            gVar.a(roleOptions);
        }
    }

    public void a(RoleOptions roleOptions, DisplayOptions displayOptions) {
        f3725c = g.a();
        g gVar = f3725c;
        if (gVar != null) {
            gVar.b();
            f3725c.a(roleOptions);
            f3725c.a(displayOptions);
        }
        f3726d = new Thread(new b(Thread.currentThread().getName()));
        this.f3730f = new c();
    }

    void a(k kVar) {
        g gVar = f3725c;
        if (gVar != null) {
            gVar.a(kVar);
        }
    }

    public void b() {
        if (this.k) {
            this.k = false;
        } else {
            q();
        }
    }

    void b(int i2) {
        long j2 = i2 * 1000;
        f3729h = j2;
        f3728g = j2;
    }

    void b(View view) {
        g gVar = f3725c;
        if (gVar != null) {
            gVar.b(view);
        }
    }

    public void c() {
        p();
    }

    void c(View view) {
        g gVar = f3725c;
        if (gVar != null) {
            gVar.c(view);
        }
    }

    public void d() {
        o();
        this.f3730f.removeCallbacksAndMessages(null);
        f3724b = 0;
        f3729h = BootloaderScanner.TIMEOUT;
        i = false;
        j = 1000;
        this.k = true;
        g gVar = f3725c;
        if (gVar != null) {
            gVar.h();
        }
    }
}