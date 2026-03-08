package com.ido.life.module.sport.bean;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GpsAlgSmoothDataMode {
    private List<Double> lat;
    private int len;
    private List<Double> lon;

    public List<Double> getLat() {
        return this.lat;
    }

    public void setLat(List<Double> list) {
        this.lat = list;
    }

    public List<Double> getLon() {
        return this.lon;
    }

    public void setLon(List<Double> list) {
        this.lon = list;
    }

    public int getLen() {
        return this.len;
    }

    public void setLen(int i) {
        this.len = i;
    }

    public String toString() {
        return "GpsAlgSmoothDataMode{lat=" + this.lat + ", lon=" + this.lon + ", len=" + this.len + '}';
    }
}