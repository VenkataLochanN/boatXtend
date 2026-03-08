package com.baidu.mapapi.utils.poi;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class PoiParaOption {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f3373a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f3374b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LatLng f3375c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f3376d;

    public PoiParaOption center(LatLng latLng) {
        this.f3375c = latLng;
        return this;
    }

    public LatLng getCenter() {
        return this.f3375c;
    }

    public String getKey() {
        return this.f3374b;
    }

    public int getRadius() {
        return this.f3376d;
    }

    public String getUid() {
        return this.f3373a;
    }

    public PoiParaOption key(String str) {
        this.f3374b = str;
        return this;
    }

    public PoiParaOption radius(int i) {
        this.f3376d = i;
        return this;
    }

    public PoiParaOption uid(String str) {
        this.f3373a = str;
        return this;
    }
}