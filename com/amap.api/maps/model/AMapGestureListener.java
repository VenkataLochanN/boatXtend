package com.amap.api.maps.model;

/* JADX INFO: loaded from: classes.dex */
public interface AMapGestureListener {
    void onDoubleTap(float f2, float f3);

    void onDown(float f2, float f3);

    void onFling(float f2, float f3);

    void onLongPress(float f2, float f3);

    void onMapStable();

    void onScroll(float f2, float f3);

    void onSingleTap(float f2, float f3);

    void onUp(float f2, float f3);
}