package com.autonavi.base.amap.api.mapcore.overlays;

import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.interfaces.IArc;

/* JADX INFO: loaded from: classes.dex */
public interface IArcDelegate extends IArc, IOverlayDelegate {
    void setEnd(LatLng latLng);

    void setPassed(LatLng latLng);

    void setStart(LatLng latLng);
}