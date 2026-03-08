package com.amap.api.mapcore.util;

import android.util.Pair;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.IPoint;

/* JADX INFO: compiled from: AbstractCameraBoundsMessage.java */
/* JADX INFO: loaded from: classes.dex */
public class ad extends AbstractCameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
    }

    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public void runCameraUpdate(IGLMapState iGLMapState) {
        Pair<Float, IPoint> pairA = er.a(this, iGLMapState, this.mapConfig);
        if (pairA == null) {
            return;
        }
        iGLMapState.setMapZoomer(((Float) pairA.first).floatValue());
        iGLMapState.setMapGeoCenter(((IPoint) pairA.second).x, ((IPoint) pairA.second).y);
        iGLMapState.setCameraDegree(0.0f);
        iGLMapState.setMapAngle(0.0f);
    }
}