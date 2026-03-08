package com.amap.api.mapcore.util;

import android.location.Location;
import com.amap.api.maps.LocationSource;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: compiled from: AMapOnLocationChangedListener.java */
/* JADX INFO: loaded from: classes.dex */
class g implements LocationSource.OnLocationChangedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Location f1004a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAMapDelegate f1005b;

    g(IAMapDelegate iAMapDelegate) {
        this.f1005b = iAMapDelegate;
    }

    @Override // com.amap.api.maps.LocationSource.OnLocationChangedListener
    public void onLocationChanged(Location location) {
        this.f1004a = location;
        try {
            if (this.f1005b.isMyLocationEnabled()) {
                this.f1005b.showMyLocationOverlay(location);
            }
        } catch (Throwable th) {
            hn.c(th, "AMapOnLocationChangedListener", "onLocationChanged");
            th.printStackTrace();
        }
    }
}