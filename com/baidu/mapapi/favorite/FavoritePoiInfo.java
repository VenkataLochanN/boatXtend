package com.baidu.mapapi.favorite;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class FavoritePoiInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2736a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f2737b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LatLng f2738c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f2739d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f2740e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    String f2741f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    long f2742g;

    public FavoritePoiInfo addr(String str) {
        this.f2739d = str;
        return this;
    }

    public FavoritePoiInfo cityName(String str) {
        this.f2740e = str;
        return this;
    }

    public String getAddr() {
        return this.f2739d;
    }

    public String getCityName() {
        return this.f2740e;
    }

    public String getID() {
        return this.f2736a;
    }

    public String getPoiName() {
        return this.f2737b;
    }

    public LatLng getPt() {
        return this.f2738c;
    }

    public long getTimeStamp() {
        return this.f2742g;
    }

    public String getUid() {
        return this.f2741f;
    }

    public FavoritePoiInfo poiName(String str) {
        this.f2737b = str;
        return this;
    }

    public FavoritePoiInfo pt(LatLng latLng) {
        this.f2738c = latLng;
        return this;
    }

    public FavoritePoiInfo uid(String str) {
        this.f2741f = str;
        return this;
    }
}