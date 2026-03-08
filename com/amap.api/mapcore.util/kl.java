package com.amap.api.mapcore.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.realsil.sdk.dfu.model.DfuConfig;

/* JADX INFO: compiled from: MapGpsLocation.java */
/* JADX INFO: loaded from: classes.dex */
public final class kl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f1556a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    LocationManager f1557b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Object f1562g;
    boolean i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile long f1558c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    volatile boolean f1559d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f1560e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile Inner_3dMap_location f1561f = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f1563h = false;
    LocationListener j = new LocationListener() { // from class: com.amap.api.mapcore.util.kl.1
        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            if (location == null) {
                return;
            }
            try {
                Inner_3dMap_location inner_3dMap_location = new Inner_3dMap_location(location);
                inner_3dMap_location.setProvider("gps");
                inner_3dMap_location.setLocationType(1);
                Bundle extras = location.getExtras();
                inner_3dMap_location.setSatellites(extras != null ? extras.getInt("satellites") : 0);
                inner_3dMap_location.setTime(kh.a(inner_3dMap_location.getTime(), System.currentTimeMillis()));
                kl.this.f1561f = inner_3dMap_location;
                kl.this.f1558c = kk.b();
                kl.this.f1559d = true;
            } catch (Throwable th) {
                kg.a(th, "MAPGPSLocation", "onLocationChanged");
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
            try {
                if ("gps".equals(str)) {
                    kl.this.f1559d = false;
                }
            } catch (Throwable th) {
                kg.a(th, "MAPGPSLocation", "onProviderDisabled");
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    };

    public kl(Context context) {
        this.f1562g = null;
        this.i = false;
        if (context == null) {
            return;
        }
        this.f1556a = context;
        e();
        try {
            if (this.f1562g == null && !this.i) {
                this.f1562g = this.f1563h ? Class.forName("com.amap.api.maps.CoordinateConverter").getConstructor(Context.class).newInstance(context) : Class.forName("com.amap.api.maps2d.CoordinateConverter").getConstructor(new Class[0]).newInstance(new Object[0]);
                if (context == null) {
                    this.i = true;
                }
            }
        } catch (Throwable unused) {
        }
        if (this.f1557b == null) {
            this.f1557b = (LocationManager) this.f1556a.getSystemService(FirebaseAnalytics.Param.LOCATION);
        }
    }

    private void e() {
        try {
            if (Class.forName("com.amap.api.maps.CoordinateConverter") != null) {
                this.f1563h = true;
            }
        } catch (Throwable unused) {
        }
    }

    private void f() {
        try {
            Looper looperMyLooper = Looper.myLooper();
            if (looperMyLooper == null) {
                looperMyLooper = this.f1556a.getMainLooper();
            }
            Looper looper = looperMyLooper;
            try {
                this.f1557b.sendExtraCommand("gps", "force_xtra_injection", new Bundle());
            } catch (Throwable unused) {
            }
            this.f1557b.requestLocationUpdates("gps", 800L, 0.0f, this.j, looper);
        } catch (SecurityException unused2) {
        } catch (Throwable th) {
            kg.a(th, "MAPGPSLocation", "requestLocationUpdates");
        }
    }

    private void g() {
        this.f1559d = false;
        this.f1558c = 0L;
        this.f1561f = null;
    }

    public final void a() {
        if (this.f1560e) {
            return;
        }
        f();
        this.f1560e = true;
    }

    public final void b() {
        LocationListener locationListener;
        this.f1560e = false;
        g();
        LocationManager locationManager = this.f1557b;
        if (locationManager == null || (locationListener = this.j) == null) {
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    public final boolean c() {
        if (!this.f1559d) {
            return false;
        }
        if (kk.b() - this.f1558c <= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
            return true;
        }
        this.f1561f = null;
        return false;
    }

    public final Inner_3dMap_location d() {
        double[] dArrA;
        Object objA;
        Object objNewInstance;
        if (this.f1561f == null) {
            return null;
        }
        Inner_3dMap_location inner_3dMap_locationM14clone = this.f1561f.m14clone();
        if (inner_3dMap_locationM14clone != null && inner_3dMap_locationM14clone.getErrorCode() == 0) {
            try {
                if (this.f1562g != null) {
                    if (kg.a(inner_3dMap_locationM14clone.getLatitude(), inner_3dMap_locationM14clone.getLongitude())) {
                        Object[] objArr = {"GPS"};
                        Class[] clsArr = {String.class};
                        if (this.f1563h) {
                            objA = ki.a("com.amap.api.maps.CoordinateConverter$CoordType", "valueOf", objArr, (Class<?>[]) clsArr);
                            objNewInstance = Class.forName("com.amap.api.maps.model.LatLng").getConstructor(Double.TYPE, Double.TYPE).newInstance(Double.valueOf(inner_3dMap_locationM14clone.getLatitude()), Double.valueOf(inner_3dMap_locationM14clone.getLongitude()));
                        } else {
                            objA = ki.a("com.amap.api.maps2d.CoordinateConverter$CoordType", "valueOf", objArr, (Class<?>[]) clsArr);
                            objNewInstance = Class.forName("com.amap.api.maps2d.model.LatLng").getConstructor(Double.TYPE, Double.TYPE).newInstance(Double.valueOf(inner_3dMap_locationM14clone.getLatitude()), Double.valueOf(inner_3dMap_locationM14clone.getLongitude()));
                        }
                        ki.a(this.f1562g, "coord", objNewInstance);
                        ki.a(this.f1562g, "from", objA);
                        Object objA2 = ki.a(this.f1562g, "convert", new Object[0]);
                        double dDoubleValue = ((Double) objA2.getClass().getDeclaredField("latitude").get(objA2)).doubleValue();
                        double dDoubleValue2 = ((Double) objA2.getClass().getDeclaredField("longitude").get(objA2)).doubleValue();
                        inner_3dMap_locationM14clone.setLatitude(dDoubleValue);
                        inner_3dMap_locationM14clone.setLongitude(dDoubleValue2);
                    }
                } else if (this.i && kg.a(inner_3dMap_locationM14clone.getLatitude(), inner_3dMap_locationM14clone.getLongitude()) && (dArrA = jy.a(inner_3dMap_locationM14clone.getLongitude(), inner_3dMap_locationM14clone.getLatitude())) != null) {
                    inner_3dMap_locationM14clone.setLatitude(dArrA[1]);
                    inner_3dMap_locationM14clone.setLongitude(dArrA[0]);
                }
            } catch (Throwable unused) {
            }
        }
        return inner_3dMap_locationM14clone;
    }
}