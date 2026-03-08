package com.amap.api.maps.model;

/* JADX INFO: loaded from: classes.dex */
public class MultiPointOverlayOptions {
    private float anchorU = 0.5f;
    private float anchorV = 0.5f;
    private BitmapDescriptor bitmapDescriptor;

    public MultiPointOverlayOptions anchor(float f2, float f3) {
        this.anchorU = f2;
        this.anchorV = f3;
        return this;
    }

    public float getAnchorU() {
        return this.anchorU;
    }

    public float getAnchorV() {
        return this.anchorV;
    }

    public MultiPointOverlayOptions icon(BitmapDescriptor bitmapDescriptor) {
        this.bitmapDescriptor = bitmapDescriptor;
        return this;
    }

    public BitmapDescriptor getIcon() {
        return this.bitmapDescriptor;
    }
}