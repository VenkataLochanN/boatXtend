package com.baidu.mapapi.utils.route;

import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class RouteParaOption {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    LatLng f3381a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    LatLng f3382b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f3383c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f3384d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f3385e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    EBusStrategyType f3386f = EBusStrategyType.bus_recommend_way;

    public enum EBusStrategyType {
        bus_time_first,
        bus_transfer_little,
        bus_walk_little,
        bus_no_subway,
        bus_recommend_way
    }

    public RouteParaOption busStrategyType(EBusStrategyType eBusStrategyType) {
        this.f3386f = eBusStrategyType;
        return this;
    }

    public RouteParaOption cityName(String str) {
        this.f3385e = str;
        return this;
    }

    public RouteParaOption endName(String str) {
        this.f3384d = str;
        return this;
    }

    public RouteParaOption endPoint(LatLng latLng) {
        this.f3382b = latLng;
        return this;
    }

    public EBusStrategyType getBusStrategyType() {
        return this.f3386f;
    }

    public String getCityName() {
        return this.f3385e;
    }

    public String getEndName() {
        return this.f3384d;
    }

    public LatLng getEndPoint() {
        return this.f3382b;
    }

    public String getStartName() {
        return this.f3383c;
    }

    public LatLng getStartPoint() {
        return this.f3381a;
    }

    public RouteParaOption startName(String str) {
        this.f3383c = str;
        return this;
    }

    public RouteParaOption startPoint(LatLng latLng) {
        this.f3381a = latLng;
        return this;
    }
}