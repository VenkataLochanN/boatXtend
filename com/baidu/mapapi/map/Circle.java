package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

/* JADX INFO: loaded from: classes.dex */
public final class Circle extends Overlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    LatLng f2791a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f2792b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f2793c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Stroke f2794d;

    Circle() {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.circle;
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a(Bundle bundle) {
        super.a(bundle);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f2791a);
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius(this.f2791a, this.f2793c));
        Overlay.a(this.f2792b, bundle);
        if (this.f2794d == null) {
            bundle.putInt("has_stroke", 0);
        } else {
            bundle.putInt("has_stroke", 1);
            bundle.putBundle("stroke", this.f2794d.a(new Bundle()));
        }
        return bundle;
    }

    public LatLng getCenter() {
        return this.f2791a;
    }

    public int getFillColor() {
        return this.f2792b;
    }

    public int getRadius() {
        return this.f2793c;
    }

    public Stroke getStroke() {
        return this.f2794d;
    }

    public void setCenter(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: circle center can not be null");
        }
        this.f2791a = latLng;
        this.listener.b(this);
    }

    public void setFillColor(int i) {
        this.f2792b = i;
        this.listener.b(this);
    }

    public void setRadius(int i) {
        this.f2793c = i;
        this.listener.b(this);
    }

    public void setStroke(Stroke stroke) {
        this.f2794d = stroke;
        this.listener.b(this);
    }
}