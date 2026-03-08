package com.amap.api.maps.model;

import android.graphics.Bitmap;
import com.autonavi.ae.gmap.gloverlay.AVectorCrossAttr;

/* JADX INFO: loaded from: classes.dex */
public class CrossOverlayOptions {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    AVectorCrossAttr f1856a = null;
    private Bitmap bitmapDescriptor = null;

    public AVectorCrossAttr getAttribute() {
        return this.f1856a;
    }

    public CrossOverlayOptions setAttribute(AVectorCrossAttr aVectorCrossAttr) {
        this.f1856a = aVectorCrossAttr;
        return this;
    }

    public CrossOverlayOptions setRes(Bitmap bitmap) {
        this.bitmapDescriptor = bitmap;
        return this;
    }

    public Bitmap getRes() {
        return this.bitmapDescriptor;
    }
}