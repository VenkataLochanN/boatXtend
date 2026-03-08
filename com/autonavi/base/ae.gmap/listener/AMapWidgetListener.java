package com.autonavi.base.ae.gmap.listener;

/* JADX INFO: loaded from: classes.dex */
public interface AMapWidgetListener {
    void invalidateCompassView();

    void invalidateScaleView();

    void invalidateZoomController(float f2);

    void setFrontViewVisibility(boolean z);
}