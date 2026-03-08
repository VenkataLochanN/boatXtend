package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

/* JADX INFO: loaded from: classes.dex */
public final class Dot extends Overlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    LatLng f2803a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f2804b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f2805c;

    Dot() {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.dot;
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a(Bundle bundle) {
        super.a(bundle);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f2803a);
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("radius", this.f2805c);
        Overlay.a(this.f2804b, bundle);
        return bundle;
    }

    public LatLng getCenter() {
        return this.f2803a;
    }

    public int getColor() {
        return this.f2804b;
    }

    public int getRadius() {
        return this.f2805c;
    }

    public void setCenter(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: dot center can not be null");
        }
        this.f2803a = latLng;
        this.listener.b(this);
    }

    public void setColor(int i) {
        this.f2804b = i;
        this.listener.b(this);
    }

    public void setRadius(int i) {
        if (i > 0) {
            this.f2805c = i;
            this.listener.b(this);
        }
    }
}