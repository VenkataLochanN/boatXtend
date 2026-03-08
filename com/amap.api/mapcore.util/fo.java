package com.amap.api.mapcore.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import com.amap.api.location.AMapLocationClient;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationManagerBase;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* JADX INFO: compiled from: AMapLocationClient.java */
/* JADX INFO: loaded from: classes.dex */
public class fo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f920a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Inner_3dMap_locationManagerBase f921b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Object f922c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f923d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    km f924e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    gs f925f;

    public fo(Context context) {
        this.f924e = null;
        this.f925f = null;
        try {
            this.f925f = kv.a();
        } catch (Throwable unused) {
        }
        this.f924e = new km();
        a(context);
    }

    private void a(Context context) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f920a = context.getApplicationContext();
            try {
                Class<?> cls = Class.forName("com.amap.api.location.AMapLocationClient");
                ServiceInfo serviceInfo = null;
                try {
                    serviceInfo = this.f920a.getPackageManager().getServiceInfo(new ComponentName(this.f920a, "com.amap.api.location.APSService"), 128);
                } catch (Throwable unused) {
                }
                if (cls != null && serviceInfo != null) {
                    this.f923d = true;
                }
            } catch (Throwable unused2) {
                this.f923d = false;
            }
            if (this.f923d) {
                this.f922c = new AMapLocationClient(this.f920a);
            } else {
                this.f921b = b(this.f920a);
            }
        } catch (Throwable th) {
            kg.a(th, "AMapLocationClient", "AMapLocationClient 1");
        }
    }

    private static Inner_3dMap_locationManagerBase b(Context context) {
        return new ko(context);
    }

    public void a() {
        try {
            if (this.f923d) {
                ((AMapLocationClient) this.f922c).startLocation();
            } else {
                this.f921b.startLocation();
            }
        } catch (Throwable th) {
            kg.a(th, "AMapLocationClient", "startLocation");
        }
    }

    public void a(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        try {
            if (inner_3dMap_locationListener == null) {
                throw new IllegalArgumentException("listener参数不能为null");
            }
            if (this.f923d) {
                this.f924e.a(this.f922c, inner_3dMap_locationListener);
            } else {
                this.f921b.setLocationListener(inner_3dMap_locationListener);
            }
        } catch (Throwable th) {
            kg.a(th, "AMapLocationClient", "setLocationListener");
        }
    }

    public void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        try {
            if (inner_3dMap_locationOption == null) {
                throw new IllegalArgumentException("LocationManagerOption参数不能为null");
            }
            if (this.f923d) {
                km.a(this.f922c, inner_3dMap_locationOption);
            } else {
                this.f921b.setLocationOption(inner_3dMap_locationOption);
            }
        } catch (Throwable th) {
            kg.a(th, "AMapLocationClient", "setLocationOption");
        }
    }

    public void b() {
        try {
            if (this.f923d) {
                ((AMapLocationClient) this.f922c).stopLocation();
            } else {
                this.f921b.stopLocation();
            }
        } catch (Throwable th) {
            kg.a(th, "AMapLocationClient", "stopLocation");
        }
    }

    public void c() {
        try {
            if (this.f923d) {
                ((AMapLocationClient) this.f922c).onDestroy();
            } else {
                this.f921b.destroy();
            }
            if (this.f924e != null) {
                this.f924e = null;
            }
        } catch (Throwable th) {
            kg.a(th, "AMapLocationClient", "onDestroy");
        }
    }
}