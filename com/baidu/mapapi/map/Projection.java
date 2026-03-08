package com.baidu.mapapi.map;

import android.graphics.Point;
import android.graphics.PointF;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.ab;

/* JADX INFO: loaded from: classes.dex */
public final class Projection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.map.e f2959a;

    Projection(com.baidu.mapsdkplatform.comapi.map.e eVar) {
        this.f2959a = eVar;
    }

    public LatLng fromScreenLocation(Point point) {
        com.baidu.mapsdkplatform.comapi.map.e eVar;
        if (point == null || (eVar = this.f2959a) == null) {
            return null;
        }
        return CoordUtil.mc2ll(eVar.b(point.x, point.y));
    }

    public float metersToEquatorPixels(float f2) {
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        return (float) (((double) f2) / this.f2959a.K());
    }

    public PointF toOpenGLLocation(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLng);
        ab abVar = mapStatus.f2869a;
        return new PointF((float) (geoPointLl2mc.getLongitudeE6() - abVar.f3521d), (float) (geoPointLl2mc.getLatitudeE6() - abVar.f3522e));
    }

    public PointF toOpenGLNormalization(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLng);
        ab.a aVar = mapStatus.f2869a.k;
        return new PointF((float) ((((geoPointLl2mc.getLongitudeE6() - aVar.f3526a) * 2.0d) / Math.abs(aVar.f3527b - aVar.f3526a)) - 1.0d), (float) ((((geoPointLl2mc.getLatitudeE6() - aVar.f3529d) * 2.0d) / Math.abs(aVar.f3528c - aVar.f3529d)) - 1.0d));
    }

    public Point toScreenLocation(LatLng latLng) {
        if (latLng == null || this.f2959a == null) {
            return null;
        }
        return this.f2959a.a(CoordUtil.ll2mc(latLng));
    }
}