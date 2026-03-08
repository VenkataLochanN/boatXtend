package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;

/* JADX INFO: loaded from: classes.dex */
public final class GroundOverlay extends Overlay {
    private static final String j = GroundOverlay.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2819a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    BitmapDescriptor f2820b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LatLng f2821c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    double f2822d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    double f2823e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f2824f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f2825g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    LatLngBounds f2826h;
    float i;

    GroundOverlay() {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.ground;
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a(Bundle bundle) {
        super.a(bundle);
        bundle.putBundle("image_info", this.f2820b.b());
        if (this.f2819a == 1) {
            GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f2826h.southwest);
            double longitudeE6 = geoPointLl2mc.getLongitudeE6();
            double latitudeE6 = geoPointLl2mc.getLatitudeE6();
            GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(this.f2826h.northeast);
            double longitudeE62 = geoPointLl2mc2.getLongitudeE6();
            double latitudeE62 = geoPointLl2mc2.getLatitudeE6();
            this.f2822d = longitudeE62 - longitudeE6;
            this.f2823e = latitudeE62 - latitudeE6;
            this.f2821c = CoordUtil.mc2ll(new GeoPoint(latitudeE6 + (this.f2823e / 2.0d), longitudeE6 + (this.f2822d / 2.0d)));
            this.f2824f = 0.5f;
            this.f2825g = 0.5f;
        }
        double d2 = this.f2822d;
        if (d2 <= 0.0d || this.f2823e <= 0.0d) {
            throw new IllegalStateException("BDMapSDKException: when you add ground overlay, the width and height must greater than 0");
        }
        bundle.putDouble("x_distance", d2);
        if (this.f2823e == 2.147483647E9d) {
            this.f2823e = (int) ((this.f2822d * ((double) this.f2820b.f2787a.getHeight())) / ((double) this.f2820b.f2787a.getWidth()));
        }
        bundle.putDouble("y_distance", this.f2823e);
        GeoPoint geoPointLl2mc3 = CoordUtil.ll2mc(this.f2821c);
        bundle.putDouble("location_x", geoPointLl2mc3.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc3.getLatitudeE6());
        bundle.putFloat("anchor_x", this.f2824f);
        bundle.putFloat("anchor_y", this.f2825g);
        bundle.putFloat("transparency", this.i);
        return bundle;
    }

    public float getAnchorX() {
        return this.f2824f;
    }

    public float getAnchorY() {
        return this.f2825g;
    }

    public LatLngBounds getBounds() {
        return this.f2826h;
    }

    public double getHeight() {
        return this.f2823e;
    }

    public BitmapDescriptor getImage() {
        return this.f2820b;
    }

    public LatLng getPosition() {
        return this.f2821c;
    }

    public float getTransparency() {
        return this.i;
    }

    public double getWidth() {
        return this.f2822d;
    }

    public void setAnchor(float f2, float f3) {
        if (f2 < 0.0f || f2 > 1.0f || f3 < 0.0f || f3 > 1.0f) {
            return;
        }
        this.f2824f = f2;
        this.f2825g = f3;
        this.listener.b(this);
    }

    public void setDimensions(int i) {
        this.f2822d = i;
        this.f2823e = 2.147483647E9d;
        this.listener.b(this);
    }

    public void setDimensions(int i, int i2) {
        this.f2822d = i;
        this.f2823e = i2;
        this.listener.b(this);
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("BDMapSDKException: image can not be null");
        }
        this.f2820b = bitmapDescriptor;
        this.listener.b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: position can not be null");
        }
        this.f2819a = 2;
        this.f2821c = latLng;
        this.listener.b(this);
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("BDMapSDKException: bounds can not be null");
        }
        this.f2819a = 1;
        this.f2826h = latLngBounds;
        this.listener.b(this);
    }

    public void setTransparency(float f2) {
        if (f2 > 1.0f || f2 < 0.0f) {
            return;
        }
        this.i = f2;
        this.listener.b(this);
    }
}