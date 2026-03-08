package com.amap.api.mapcore.util;

import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* JADX INFO: compiled from: AbstractCameraZoomMessage.java */
/* JADX INFO: loaded from: classes.dex */
public class ag extends AbstractCameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public void runCameraUpdate(IGLMapState iGLMapState) {
        this.zoom = iGLMapState.getMapZoomer() + this.amount;
        this.zoom = er.a(this.mapConfig, this.zoom);
        normalChange(iGLMapState);
    }

    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        abstractCameraUpdateMessage.zoom += this.amount;
    }
}