package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: MapLocationManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class ko implements Inner_3dMap_locationManagerBase {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f1573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<Inner_3dMap_locationListener> f1574b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Object f1575c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Handler f1576d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    a f1577e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Handler f1578f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Inner_3dMap_locationOption f1579g = new Inner_3dMap_locationOption();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    ks f1580h = null;
    Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode i = Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Hight_Accuracy;
    boolean j = false;

    /* JADX INFO: compiled from: MapLocationManager.java */
    static class a extends HandlerThread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ko f1581a;

        public a(String str, ko koVar) {
            super(str);
            this.f1581a = koVar;
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                this.f1581a.f1580h = new ks(this.f1581a.f1573a, this.f1581a.f1576d);
                super.onLooperPrepared();
            } catch (Throwable unused) {
            }
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    public ko(Context context) {
        this.f1573a = null;
        if (context == null) {
            throw new IllegalArgumentException("Context参数不能为null");
        }
        this.f1573a = context.getApplicationContext();
        e();
    }

    private Handler a(Looper looper) {
        Handler handler;
        synchronized (this.f1575c) {
            this.f1578f = new kp(looper, this);
            handler = this.f1578f;
        }
        return handler;
    }

    private void a(int i) {
        synchronized (this.f1575c) {
            if (this.f1578f != null) {
                this.f1578f.removeMessages(i);
            }
        }
    }

    private void a(int i, Object obj, long j) {
        synchronized (this.f1575c) {
            if (this.f1578f != null) {
                Message messageObtain = Message.obtain();
                messageObtain.what = i;
                messageObtain.obj = obj;
                this.f1578f.sendMessageDelayed(messageObtain, j);
            }
        }
    }

    private void e() {
        try {
            this.f1576d = Looper.myLooper() == null ? new kq(this.f1573a.getMainLooper(), this) : new kq(this);
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "initResultHandler");
        }
        try {
            this.f1577e = new a("locaitonClientActionThread", this);
            this.f1577e.setPriority(5);
            this.f1577e.start();
            this.f1578f = a(this.f1577e.getLooper());
        } catch (Throwable th2) {
            kg.a(th2, "MapLocationManager", "initActionThreadAndActionHandler");
        }
    }

    private void f() {
        synchronized (this.f1575c) {
            if (this.f1578f != null) {
                this.f1578f.removeCallbacksAndMessages(null);
            }
            this.f1578f = null;
        }
    }

    final void a() {
        try {
            if (this.j) {
                return;
            }
            this.j = true;
            a(1005, null, 0L);
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "doStartLocation");
        }
    }

    final void a(Inner_3dMap_location inner_3dMap_location) {
        if (inner_3dMap_location != null) {
            try {
                if (kv.a(inner_3dMap_location)) {
                    km.f1565a = inner_3dMap_location;
                }
            } catch (Throwable th) {
                kg.a(th, "MapLocationManager", "callBackLocation");
                return;
            }
        }
        if (this.j) {
            if (!"gps".equalsIgnoreCase(inner_3dMap_location.getProvider())) {
                inner_3dMap_location.setProvider("lbs");
            }
            inner_3dMap_location.setAltitude(kk.b(inner_3dMap_location.getAltitude()));
            inner_3dMap_location.setBearing(kk.a(inner_3dMap_location.getBearing()));
            inner_3dMap_location.setSpeed(kk.a(inner_3dMap_location.getSpeed()));
            Iterator<Inner_3dMap_locationListener> it = this.f1574b.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onLocationChanged(inner_3dMap_location);
                } catch (Throwable unused) {
                }
            }
        }
        if (this.f1579g.isOnceLocation()) {
            c();
        }
    }

    final void a(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            if (inner_3dMap_locationListener == null) {
                throw new IllegalArgumentException("listener参数不能为null");
            }
            if (this.f1574b == null) {
                this.f1574b = new ArrayList<>();
            }
            if (this.f1574b.contains(inner_3dMap_locationListener)) {
                return;
            }
            this.f1574b.add(inner_3dMap_locationListener);
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "doSetLocationListener");
        }
    }

    final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.f1579g = inner_3dMap_locationOption;
        if (this.f1579g == null) {
            this.f1579g = new Inner_3dMap_locationOption();
        }
        ks ksVar = this.f1580h;
        if (ksVar != null) {
            ksVar.a(this.f1579g);
        }
        if (this.j && !this.i.equals(inner_3dMap_locationOption.getLocationMode())) {
            c();
            a();
        }
        this.i = this.f1579g.getLocationMode();
    }

    final void b() {
        try {
            if (this.f1580h != null) {
                this.f1580h.a();
            }
        } catch (Throwable th) {
            try {
                kg.a(th, "MapLocationManager", "doGetLocation");
                if (this.f1579g.isOnceLocation()) {
                    return;
                }
                a(1005, null, this.f1579g.getInterval() >= 1000 ? this.f1579g.getInterval() : 1000L);
            } finally {
                if (!this.f1579g.isOnceLocation()) {
                    a(1005, null, this.f1579g.getInterval() >= 1000 ? this.f1579g.getInterval() : 1000L);
                }
            }
        }
    }

    final void b(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        if (inner_3dMap_locationListener != null) {
            try {
                if (!this.f1574b.isEmpty() && this.f1574b.contains(inner_3dMap_locationListener)) {
                    this.f1574b.remove(inner_3dMap_locationListener);
                }
            } catch (Throwable th) {
                kg.a(th, "MapLocationManager", "doUnregisterListener");
                return;
            }
        }
        if (this.f1574b.isEmpty()) {
            c();
        }
    }

    final void c() {
        try {
            this.j = false;
            a(1004);
            a(1005);
            if (this.f1580h != null) {
                this.f1580h.c();
            }
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "doStopLocation");
        }
    }

    final void d() {
        c();
        ks ksVar = this.f1580h;
        if (ksVar != null) {
            ksVar.d();
        }
        ArrayList<Inner_3dMap_locationListener> arrayList = this.f1574b;
        if (arrayList != null) {
            arrayList.clear();
            this.f1574b = null;
        }
        f();
        if (this.f1577e != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                try {
                    ki.a(this.f1577e, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable unused) {
                    this.f1577e.quit();
                }
            } else {
                this.f1577e.quit();
            }
        }
        this.f1577e = null;
        Handler handler = this.f1576d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f1576d = null;
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public final void destroy() {
        try {
            a(1007, null, 0L);
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "stopLocation");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public final Inner_3dMap_location getLastKnownLocation() {
        return km.f1565a;
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public final void setLocationListener(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            a(1002, inner_3dMap_locationListener, 0L);
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "setLocationListener");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public final void setLocationOption(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        try {
            a(1001, inner_3dMap_locationOption, 0L);
        } catch (Throwable th) {
            kg.a(th, "LocationClientManager", "setLocationOption");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public final void startLocation() {
        try {
            a(1004, null, 0L);
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "startLocation");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public final void stopLocation() {
        try {
            a(1006, null, 0L);
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "stopLocation");
        }
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase
    public final void unRegisterLocationListener(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            a(1003, inner_3dMap_locationListener, 0L);
        } catch (Throwable th) {
            kg.a(th, "MapLocationManager", "stopLocation");
        }
    }
}