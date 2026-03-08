package com.baidu.location.a;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Object f2196a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static x f2197b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HandlerThread f2198c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Handler f2199d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2200e = false;

    x() {
    }

    public static x a() {
        x xVar;
        synchronized (f2196a) {
            if (f2197b == null) {
                f2197b = new x();
            }
            xVar = f2197b;
        }
        return xVar;
    }

    public void a(Location location, int i) {
        if (!this.f2200e || location == null) {
            return;
        }
        try {
            if (this.f2199d != null) {
                Message messageObtainMessage = this.f2199d.obtainMessage(1);
                Bundle bundle = new Bundle();
                bundle.putParcelable("loc", new Location(location));
                bundle.putInt("satnum", i);
                messageObtainMessage.setData(bundle);
                messageObtainMessage.sendToTarget();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b() {
        if (this.f2200e) {
            try {
                if (this.f2199d != null) {
                    this.f2199d.obtainMessage(3).sendToTarget();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void c() {
        if (this.f2200e) {
            try {
                if (this.f2199d != null) {
                    this.f2199d.obtainMessage(2).sendToTarget();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void d() {
        if (this.f2200e) {
            try {
                if (this.f2199d != null) {
                    this.f2199d.obtainMessage(7).sendToTarget();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void e() {
        if (this.f2200e) {
            return;
        }
        this.f2200e = true;
        if (this.f2198c == null) {
            this.f2198c = new HandlerThread("LocUploadThreadManager");
            this.f2198c.start();
            this.f2199d = new y(this, this.f2198c.getLooper());
        }
        try {
            if (this.f2199d != null) {
                this.f2199d.obtainMessage(5).sendToTarget();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (this.f2199d != null) {
                this.f2199d.sendEmptyMessageDelayed(4, com.baidu.location.g.k.R);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void f() {
        if (this.f2200e) {
            d.a().b();
            try {
                if (this.f2199d != null) {
                    this.f2199d.removeCallbacksAndMessages(null);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.f2199d = null;
            try {
                if (this.f2198c != null) {
                    this.f2198c.quit();
                    this.f2198c.interrupt();
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.f2198c = null;
            this.f2200e = false;
        }
    }
}