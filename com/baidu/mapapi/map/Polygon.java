package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Polygon extends Overlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Stroke f2934a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f2935b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    List<LatLng> f2936c;

    Polygon() {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.polygon;
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a(Bundle bundle) {
        super.a(bundle);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f2936c.get(0));
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        Overlay.a(this.f2936c, bundle);
        Overlay.a(this.f2935b, bundle);
        if (this.f2934a == null) {
            bundle.putInt("has_stroke", 0);
        } else {
            bundle.putInt("has_stroke", 1);
            bundle.putBundle("stroke", this.f2934a.a(new Bundle()));
        }
        return bundle;
    }

    public int getFillColor() {
        return this.f2935b;
    }

    public List<LatLng> getPoints() {
        return this.f2936c;
    }

    public Stroke getStroke() {
        return this.f2934a;
    }

    public void setFillColor(int i) {
        this.f2935b = i;
        this.listener.b(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
        }
        if (list.size() <= 2) {
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than three");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
        }
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                if (list.get(i) == list.get(i3)) {
                    throw new IllegalArgumentException("BDMapSDKException: points list can not has same points");
                }
            }
            i = i2;
        }
        this.f2936c = list;
        this.listener.b(this);
    }

    public void setStroke(Stroke stroke) {
        this.f2934a = stroke;
        this.listener.b(this);
    }
}