package com.autonavi.base.amap.api.mapcore.overlays;

import android.graphics.Rect;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IOverlayImage;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.interfaces.IAnimation;

/* JADX INFO: loaded from: classes.dex */
public interface IOverlayImageDelegate extends IOverlayImage {
    boolean calFPoint();

    boolean checkInBounds();

    void drawMarker(IAMapDelegate iAMapDelegate);

    void drawMarker(IAMapDelegate iAMapDelegate, float[] fArr, int i, float f2);

    IAnimation getIAnimation();

    IMarkerAction getIMarkerAction();

    Rect getRect();

    int getTextureId();

    boolean isAllowLow();

    boolean isBelowMaskLayer();

    boolean isInfoWindowShown();

    boolean isOnTap();

    void loadTexture(IAMapDelegate iAMapDelegate);

    void reLoadTexture();

    void setOnTap(boolean z);
}