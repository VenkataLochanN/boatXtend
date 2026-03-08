package com.amap.api.mapcore.util;

import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* JADX INFO: compiled from: AbstractCameraPositionMessage.java */
/* JADX INFO: loaded from: classes.dex */
public class ae extends AbstractCameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public void runCameraUpdate(IGLMapState iGLMapState) {
        normalChange(iGLMapState);
    }

    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        abstractCameraUpdateMessage.geoPoint = this.geoPoint == null ? abstractCameraUpdateMessage.geoPoint : this.geoPoint;
        abstractCameraUpdateMessage.zoom = Float.isNaN(this.zoom) ? abstractCameraUpdateMessage.zoom : this.zoom;
        abstractCameraUpdateMessage.bearing = Float.isNaN(this.bearing) ? abstractCameraUpdateMessage.bearing : this.bearing;
        abstractCameraUpdateMessage.tilt = Float.isNaN(this.tilt) ? abstractCameraUpdateMessage.tilt : this.tilt;
    }
}