package com.baidu.mapapi.map;

/* JADX INFO: loaded from: classes.dex */
public final class UiSettings {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.map.e f3017a;

    UiSettings(com.baidu.mapsdkplatform.comapi.map.e eVar) {
        this.f3017a = eVar;
    }

    public boolean isCompassEnabled() {
        return this.f3017a.r();
    }

    public boolean isOverlookingGesturesEnabled() {
        return this.f3017a.z();
    }

    public boolean isRotateGesturesEnabled() {
        return this.f3017a.y();
    }

    public boolean isScrollGesturesEnabled() {
        return this.f3017a.w();
    }

    public boolean isZoomGesturesEnabled() {
        return this.f3017a.x();
    }

    public void setAllGesturesEnabled(boolean z) {
        setRotateGesturesEnabled(z);
        setScrollGesturesEnabled(z);
        setOverlookingGesturesEnabled(z);
        setZoomGesturesEnabled(z);
    }

    public void setCompassEnabled(boolean z) {
        this.f3017a.k(z);
    }

    public void setEnlargeCenterWithDoubleClickEnable(boolean z) {
        this.f3017a.r(z);
    }

    public void setOverlookingGesturesEnabled(boolean z) {
        this.f3017a.t(z);
    }

    public void setRotateGesturesEnabled(boolean z) {
        this.f3017a.s(z);
    }

    public void setScrollGesturesEnabled(boolean z) {
        this.f3017a.p(z);
    }

    public void setZoomGesturesEnabled(boolean z) {
        this.f3017a.q(z);
    }
}