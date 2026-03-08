package com.baidu.mapapi.search.route;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class IndoorPlanNode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LatLng f3235a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3236b;

    public IndoorPlanNode(LatLng latLng, String str) {
        this.f3235a = null;
        this.f3236b = null;
        this.f3235a = latLng;
        this.f3236b = str;
    }

    public String getFloor() {
        return this.f3236b;
    }

    public LatLng getLocation() {
        return this.f3235a;
    }
}