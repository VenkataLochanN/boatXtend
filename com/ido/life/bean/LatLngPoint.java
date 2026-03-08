package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class LatLngPoint implements Comparable<LatLngPoint> {
    public int id;
    public LatLngBean latLng;

    public LatLngPoint(int i, LatLngBean latLngBean) {
        this.id = i;
        this.latLng = latLngBean;
    }

    @Override // java.lang.Comparable
    public int compareTo(LatLngPoint latLngPoint) {
        int i = this.id;
        int i2 = latLngPoint.id;
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }
}