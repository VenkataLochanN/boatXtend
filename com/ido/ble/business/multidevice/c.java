package com.ido.ble.business.multidevice;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static c f4148g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4149a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ICommonListener f4150b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4151c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Handler f4152d = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.ido.ble.business.multidevice.a f4153e = new a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.ido.ble.business.multidevice.a f4154f = new b();

    class a extends com.ido.ble.business.multidevice.a {
        a() {
        }

        @Override // com.ido.ble.business.multidevice.a, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            c.this.f();
        }

        @Override // com.ido.ble.business.multidevice.a, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            c.this.g();
        }

        @Override // com.ido.ble.business.multidevice.a, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            c.this.f();
        }
    }

    class b extends com.ido.ble.business.multidevice.a {
        b() {
        }

        @Override // com.ido.ble.business.multidevice.a, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            c.this.a();
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.business.multidevice.c$c, reason: collision with other inner class name */
    class RunnableC0060c implements Runnable {
        RunnableC0060c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f();
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.bluetooth.a.b(c.this.f4149a);
        }
    }

    private c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        com.ido.ble.callback.b.K().b(this.f4154f);
        i();
    }

    public static c b() {
        if (f4148g == null) {
            f4148g = new c();
        }
        return f4148g;
    }

    private void c() {
        this.f4150b = null;
        this.f4151c = false;
        this.f4152d.removeCallbacksAndMessages(null);
    }

    private void d() {
        this.f4152d.postDelayed(new RunnableC0060c(), 45000L);
    }

    private void e() {
        if (this.f4151c) {
            return;
        }
        this.f4151c = true;
        if (com.ido.ble.bluetooth.a.h()) {
            h();
        } else {
            com.ido.ble.bluetooth.a.b();
            i();
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        com.ido.ble.callback.b.K().b(this.f4153e);
        this.f4150b.onFailed(this.f4149a);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        com.ido.ble.callback.b.K().b(this.f4153e);
        this.f4150b.onSuccess(this.f4149a);
        c();
    }

    private void h() {
        com.ido.ble.callback.b.K().a(this.f4154f);
        com.ido.ble.bluetooth.a.b();
    }

    private void i() {
        com.ido.ble.callback.b.K().a(this.f4153e);
        this.f4152d.postDelayed(new d(), BootloaderScanner.TIMEOUT);
    }

    public void a(String str, ICommonListener iCommonListener) {
        this.f4149a = str;
        this.f4150b = iCommonListener;
        e();
    }
}