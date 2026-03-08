package com.autonavi.amap.mapcore.interfaces;

/* JADX INFO: loaded from: classes.dex */
public interface IMapConfig {
    int getAbroadState();

    int getAnchorX();

    int getAnchorY();

    int getMapHeight();

    int getMapWidth();

    float getMapZoomScale();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    double getSX();

    double getSY();

    float getSZ();

    boolean isAbroadEnable();

    void setAbroadEnable(boolean z);

    void setAbroadState(int i);
}