package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: MapLocationService.java */
/* JADX INFO: loaded from: classes.dex */
public final class ks {
    private static int m = 200;
    private static boolean n = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f1592a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Handler f1597f;
    Inner_3dMap_locationOption i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    kl f1593b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    kt f1594c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    b f1595d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Handler f1596e = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f1598g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f1599h = false;
    final int j = 500;
    final int k = 30;
    private JSONArray o = null;
    Object l = new Object();

    /* JADX INFO: compiled from: MapLocationService.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            ks.this.b();
        }
    }

    /* JADX INFO: compiled from: MapLocationService.java */
    static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            super.onLooperPrepared();
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    public ks(Context context, Handler handler) {
        this.f1592a = null;
        this.f1597f = null;
        this.i = null;
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f1592a = context.getApplicationContext();
            this.f1597f = handler;
            this.i = new Inner_3dMap_locationOption();
            f();
            e();
        } catch (Throwable th) {
            kg.a(th, "LocationService", "<init>");
        }
    }

    private void a(Inner_3dMap_location inner_3dMap_location) {
        try {
            if (n && inner_3dMap_location != null && inner_3dMap_location.getErrorCode() == 0 && inner_3dMap_location.getLocationType() == 1) {
                if (this.o == null) {
                    this.o = new JSONArray();
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("lon", inner_3dMap_location.getLongitude());
                jSONObject.put("lat", inner_3dMap_location.getLatitude());
                jSONObject.put("type", 0);
                jSONObject.put("timestamp", kk.a());
                this.o = this.o.put(jSONObject);
                if (this.o.length() >= m) {
                    h();
                }
            }
        } catch (Throwable th) {
            kg.a(th, "LocationService", "recordOfflineLocLog");
        }
    }

    private void e() {
        this.f1595d = new b("locServiceAction");
        this.f1595d.setPriority(5);
        this.f1595d.start();
        this.f1596e = new a(this.f1595d.getLooper());
    }

    private void f() {
        try {
            if (this.i == null) {
                this.i = new Inner_3dMap_locationOption();
            }
            if (this.f1599h) {
                return;
            }
            this.f1593b = new kl(this.f1592a);
            this.f1594c = new kt(this.f1592a);
            this.f1594c.a(this.i);
            g();
            this.f1599h = true;
        } catch (Throwable th) {
            kg.a(th, "LocationService", com.ido.ble.event.stat.one.d.m);
        }
    }

    private void g() {
        try {
            n = kj.b(this.f1592a, "maploc", "ue");
            int iA = kj.a(this.f1592a, "maploc", "opn");
            m = iA;
            if (iA > 500) {
                m = 500;
            }
            if (m < 30) {
                m = 30;
            }
        } catch (Throwable th) {
            kg.a(th, "LocationService", "getSPConfig");
        }
    }

    private synchronized void h() {
        try {
            if (this.o != null && this.o.length() > 0) {
                iz.a(new iy(this.f1592a, kg.c(), this.o.toString()), this.f1592a);
                this.o = null;
            }
        } catch (Throwable th) {
            kg.a(th, "LocationService", "writeOfflineLog");
        }
    }

    private void i() {
        synchronized (this.l) {
            if (this.f1596e != null) {
                this.f1596e.removeCallbacksAndMessages(null);
            }
            this.f1596e = null;
        }
    }

    private void j() {
        synchronized (this.l) {
            if (this.f1596e != null) {
                this.f1596e.removeMessages(1);
            }
        }
    }

    public final void a() {
        try {
            f();
            if (!this.i.getLocationMode().equals(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Battery_Saving) && !this.f1598g) {
                this.f1598g = true;
                this.f1593b.a();
            }
            if (this.f1596e != null) {
                this.f1596e.sendEmptyMessage(1);
            }
        } catch (Throwable th) {
            kg.a(th, "LocationService", "getLocation");
        }
    }

    public final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.i = inner_3dMap_locationOption;
        if (this.i == null) {
            this.i = new Inner_3dMap_locationOption();
        }
        kt ktVar = this.f1594c;
        if (ktVar != null) {
            ktVar.a(inner_3dMap_locationOption);
        }
    }

    final void b() {
        Inner_3dMap_location inner_3dMap_locationA = null;
        try {
            if (this.i.getLocationMode().equals(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Battery_Saving) && this.f1598g) {
                this.f1593b.b();
                this.f1598g = false;
            }
            if (this.f1593b.c()) {
                inner_3dMap_locationA = this.f1593b.d();
            } else if (!this.i.getLocationMode().equals(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Device_Sensors)) {
                inner_3dMap_locationA = this.f1594c.a();
            }
            if (this.f1597f != null && inner_3dMap_locationA != null) {
                Message messageObtain = Message.obtain();
                messageObtain.obj = inner_3dMap_locationA;
                messageObtain.what = 1;
                this.f1597f.sendMessage(messageObtain);
            }
            a(inner_3dMap_locationA);
        } catch (Throwable th) {
            kg.a(th, "LocationService", "doGetLocation");
        }
    }

    public final void c() {
        this.f1598g = false;
        try {
            j();
            if (this.f1593b != null) {
                this.f1593b.b();
            }
        } catch (Throwable th) {
            kg.a(th, "LocationService", "stopLocation");
        }
    }

    public final void d() {
        b bVar;
        try {
            c();
            i();
            if (this.f1595d != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    try {
                        ki.a(this.f1595d, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                    } catch (Throwable unused) {
                        bVar = this.f1595d;
                        bVar.quit();
                    }
                } else {
                    bVar = this.f1595d;
                }
                bVar.quit();
            }
            this.f1595d = null;
            this.f1594c.b();
            this.f1598g = false;
            this.f1599h = false;
            h();
        } catch (Throwable th) {
            kg.a(th, "LocationService", "destroy");
        }
    }
}