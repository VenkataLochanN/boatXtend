package com.amap.api.maps.model;

/* JADX INFO: loaded from: classes.dex */
public class HeatMapItem {
    private LatLng center;
    private int[] indexes;
    private double intensity;

    public LatLng getCenter() {
        return this.center;
    }

    public void setCenter(double d2, double d3) {
        this.center = new LatLng(d2, d3);
    }

    public double getIntensity() {
        return this.intensity;
    }

    public void setIntensity(double d2) {
        this.intensity = d2;
    }

    public int[] getIndexes() {
        return this.indexes;
    }

    public void setIndexes(int[] iArr) {
        this.indexes = iArr;
    }
}