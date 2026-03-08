package com.baidu.mapapi.model.inner;

/* JADX INFO: loaded from: classes.dex */
public class GeoPoint {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double f3086a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double f3087b;

    public GeoPoint(double d2, double d3) {
        this.f3086a = d2;
        this.f3087b = d3;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) obj;
        return this.f3086a == geoPoint.f3086a && this.f3087b == geoPoint.f3087b;
    }

    public double getLatitudeE6() {
        return this.f3086a;
    }

    public double getLongitudeE6() {
        return this.f3087b;
    }

    public void setLatitudeE6(double d2) {
        this.f3086a = d2;
    }

    public void setLongitudeE6(double d2) {
        this.f3087b = d2;
    }

    public String toString() {
        return "GeoPoint: Latitude: " + this.f3086a + ", Longitude: " + this.f3087b;
    }
}