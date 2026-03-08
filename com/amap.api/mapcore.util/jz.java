package com.amap.api.mapcore.util;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;

/* JADX INFO: compiled from: LocationListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class jz implements AMapLocationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Inner_3dMap_locationListener f1494a = null;

    public final void a(Inner_3dMap_locationListener inner_3dMap_locationListener) {
        this.f1494a = inner_3dMap_locationListener;
    }

    @Override // com.amap.api.location.AMapLocationListener
    public final void onLocationChanged(AMapLocation aMapLocation) {
        try {
            Inner_3dMap_location inner_3dMap_locationA = km.a(aMapLocation);
            if (kv.a(inner_3dMap_locationA)) {
                km.f1565a = inner_3dMap_locationA;
            }
            if (this.f1494a != null) {
                this.f1494a.onLocationChanged(inner_3dMap_locationA);
            }
        } catch (Throwable th) {
            kg.a(th, "LocationListener", "onLocationChanged");
        }
    }
}