package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class Arc extends Overlay {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f2757f = Arc.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2758a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f2759b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LatLng f2760c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    LatLng f2761d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    LatLng f2762e;

    Arc() {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.arc;
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a(Bundle bundle) {
        super.a(bundle);
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add(this.f2760c);
        arrayList.add(this.f2761d);
        arrayList.add(this.f2762e);
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc((LatLng) arrayList.get(0));
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("width", this.f2759b);
        Overlay.a(arrayList, bundle);
        Overlay.a(this.f2758a, bundle);
        return bundle;
    }

    public int getColor() {
        return this.f2758a;
    }

    public LatLng getEndPoint() {
        return this.f2762e;
    }

    public LatLng getMiddlePoint() {
        return this.f2761d;
    }

    public LatLng getStartPoint() {
        return this.f2760c;
    }

    public int getWidth() {
        return this.f2759b;
    }

    public void setColor(int i) {
        this.f2758a = i;
        this.listener.b(this);
    }

    public void setPoints(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng == null || latLng2 == null || latLng3 == null) {
            throw new IllegalArgumentException("BDMapSDKException:start and middle and end points can not be null");
        }
        if (latLng == latLng2 || latLng == latLng3 || latLng2 == latLng3) {
            throw new IllegalArgumentException("BDMapSDKException: start and middle and end points can not be same");
        }
        this.f2760c = latLng;
        this.f2761d = latLng2;
        this.f2762e = latLng3;
        this.listener.b(this);
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f2759b = i;
            this.listener.b(this);
        }
    }
}