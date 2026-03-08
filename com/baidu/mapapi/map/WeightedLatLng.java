package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.map.o;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

/* JADX INFO: loaded from: classes.dex */
public class WeightedLatLng extends o.a {
    public static final double DEFAULT_INTENSITY = 1.0d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Point f3030a;
    public final double intensity;
    public final LatLng latLng;

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    public WeightedLatLng(LatLng latLng, double d2) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: latLng can not be null");
        }
        this.latLng = latLng;
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLng);
        this.f3030a = new Point((int) geoPointLl2mc.getLongitudeE6(), (int) geoPointLl2mc.getLatitudeE6());
        if (d2 > 0.0d) {
            this.intensity = d2;
        } else {
            this.intensity = 1.0d;
        }
    }

    @Override // com.baidu.mapapi.map.o.a
    Point a() {
        return this.f3030a;
    }
}