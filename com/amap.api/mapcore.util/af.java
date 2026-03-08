package com.amap.api.mapcore.util;

import android.graphics.Point;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* JADX INFO: compiled from: AbstractCameraScrollMessage.java */
/* JADX INFO: loaded from: classes.dex */
public class af extends AbstractCameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
    }

    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public void runCameraUpdate(IGLMapState iGLMapState) {
        float f2 = this.xPixel;
        float f3 = (this.width / 2.0f) + f2;
        float f4 = (this.height / 2.0f) + this.yPixel;
        a(iGLMapState, (int) f3, (int) f4, new Point());
        iGLMapState.setMapGeoCenter(r1.x, r1.y);
    }

    public void a(IGLMapState iGLMapState, int i, int i2, Point point) {
        iGLMapState.screenToP20Point(i, i2, point);
    }
}