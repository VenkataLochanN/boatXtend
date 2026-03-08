package com.amap.api.maps.model;

import com.amap.api.mapcore.util.er;
import com.autonavi.amap.mapcore.DPoint;

/* JADX INFO: loaded from: classes.dex */
public class WeightedLatLng {
    public static final double DEFAULT_INTENSITY = 1.0d;
    public final double intensity;
    public final LatLng latLng;
    private DPoint mPoint;

    public WeightedLatLng(LatLng latLng, double d2) {
        if (latLng == null) {
            throw new IllegalArgumentException("latLng can not null");
        }
        this.latLng = latLng;
        this.mPoint = er.a(latLng);
        if (d2 >= 0.0d) {
            this.intensity = d2;
        } else {
            this.intensity = 1.0d;
        }
    }

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    protected DPoint getPoint() {
        return this.mPoint;
    }
}