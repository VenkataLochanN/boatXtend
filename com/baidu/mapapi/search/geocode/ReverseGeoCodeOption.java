package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class ReverseGeoCodeOption {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3166a = 10;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3167b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LatLng f3168c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3169d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3170e = 1000;

    public int getLatestAdmin() {
        return this.f3169d;
    }

    public LatLng getLocation() {
        return this.f3168c;
    }

    public int getPageNum() {
        return this.f3167b;
    }

    public int getPageSize() {
        return this.f3166a;
    }

    public int getRadius() {
        return this.f3170e;
    }

    public ReverseGeoCodeOption location(LatLng latLng) {
        this.f3168c = latLng;
        return this;
    }

    public ReverseGeoCodeOption newVersion(int i) {
        this.f3169d = i;
        return this;
    }

    public ReverseGeoCodeOption pageNum(int i) {
        if (i < 0) {
            i = 0;
        }
        this.f3167b = i;
        return this;
    }

    public ReverseGeoCodeOption pageSize(int i) {
        if (i > 0) {
            if (i > 100) {
                this.f3166a = 100;
            }
            return this;
        }
        i = 10;
        this.f3166a = i;
        return this;
    }

    public ReverseGeoCodeOption radius(int i) {
        if (i >= 0) {
            if (i > 1000) {
                this.f3170e = 1000;
            }
            return this;
        }
        i = 0;
        this.f3170e = i;
        return this;
    }
}