package com.amap.api.maps;

import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* JADX INFO: loaded from: classes.dex */
public final class CameraUpdate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    AbstractCameraUpdateMessage f1838a;

    CameraUpdate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        this.f1838a = abstractCameraUpdateMessage;
    }

    public AbstractCameraUpdateMessage getCameraUpdateFactoryDelegate() {
        return this.f1838a;
    }
}