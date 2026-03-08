package com.baidu.mapapi.navi;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class NaviParaOption {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    LatLng f3093a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f3094b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LatLng f3095c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f3096d;

    public NaviParaOption endName(String str) {
        this.f3096d = str;
        return this;
    }

    public NaviParaOption endPoint(LatLng latLng) {
        this.f3095c = latLng;
        return this;
    }

    public String getEndName() {
        return this.f3096d;
    }

    public LatLng getEndPoint() {
        return this.f3095c;
    }

    public String getStartName() {
        return this.f3094b;
    }

    public LatLng getStartPoint() {
        return this.f3093a;
    }

    public NaviParaOption startName(String str) {
        this.f3094b = str;
        return this;
    }

    public NaviParaOption startPoint(LatLng latLng) {
        this.f3093a = latLng;
        return this;
    }
}