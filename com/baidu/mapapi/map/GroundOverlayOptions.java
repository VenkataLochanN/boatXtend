package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

/* JADX INFO: loaded from: classes.dex */
public final class GroundOverlayOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2827a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bundle f2829c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BitmapDescriptor f2830d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LatLng f2831e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2832f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2833g;
    private LatLngBounds j;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f2834h = 0.5f;
    private float i = 0.5f;
    private float k = 1.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2828b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    Overlay a() {
        LatLngBounds latLngBounds;
        int i;
        LatLng latLng;
        int i2;
        GroundOverlay groundOverlay = new GroundOverlay();
        groundOverlay.A = this.f2828b;
        groundOverlay.z = this.f2827a;
        groundOverlay.B = this.f2829c;
        BitmapDescriptor bitmapDescriptor = this.f2830d;
        if (bitmapDescriptor == null) {
            throw new IllegalStateException("BDMapSDKException: when you add ground overlay, you must set the image");
        }
        groundOverlay.f2820b = bitmapDescriptor;
        if (this.j == null && (latLng = this.f2831e) != null) {
            int i3 = this.f2832f;
            if (i3 <= 0 || (i2 = this.f2833g) <= 0) {
                throw new IllegalArgumentException("BDMapSDKException: when you add ground overlay, the width and height must greater than 0");
            }
            groundOverlay.f2821c = latLng;
            groundOverlay.f2824f = this.f2834h;
            groundOverlay.f2825g = this.i;
            groundOverlay.f2822d = i3;
            groundOverlay.f2823e = i2;
            i = 2;
        } else {
            if (this.f2831e != null || (latLngBounds = this.j) == null) {
                throw new IllegalStateException("BDMapSDKException: when you add ground overlay, you must set one of position or bounds");
            }
            groundOverlay.f2826h = latLngBounds;
            i = 1;
        }
        groundOverlay.f2819a = i;
        groundOverlay.i = this.k;
        return groundOverlay;
    }

    public GroundOverlayOptions anchor(float f2, float f3) {
        if (f2 >= 0.0f && f2 <= 1.0f && f3 >= 0.0f && f3 <= 1.0f) {
            this.f2834h = f2;
            this.i = f3;
        }
        return this;
    }

    public GroundOverlayOptions dimensions(int i) {
        this.f2832f = i;
        this.f2833g = Integer.MAX_VALUE;
        return this;
    }

    public GroundOverlayOptions dimensions(int i, int i2) {
        this.f2832f = i;
        this.f2833g = i2;
        return this;
    }

    public GroundOverlayOptions extraInfo(Bundle bundle) {
        this.f2829c = bundle;
        return this;
    }

    public float getAnchorX() {
        return this.f2834h;
    }

    public float getAnchorY() {
        return this.i;
    }

    public LatLngBounds getBounds() {
        return this.j;
    }

    public Bundle getExtraInfo() {
        return this.f2829c;
    }

    public int getHeight() {
        int i = this.f2833g;
        return i == Integer.MAX_VALUE ? (int) ((this.f2832f * this.f2830d.f2787a.getHeight()) / this.f2830d.f2787a.getWidth()) : i;
    }

    public BitmapDescriptor getImage() {
        return this.f2830d;
    }

    public LatLng getPosition() {
        return this.f2831e;
    }

    public float getTransparency() {
        return this.k;
    }

    public int getWidth() {
        return this.f2832f;
    }

    public int getZIndex() {
        return this.f2827a;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("BDMapSDKException: image can not be null");
        }
        this.f2830d = bitmapDescriptor;
        return this;
    }

    public boolean isVisible() {
        return this.f2828b;
    }

    public GroundOverlayOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: position can not be null");
        }
        this.f2831e = latLng;
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("BDMapSDKException: bounds can not be null");
        }
        this.j = latLngBounds;
        return this;
    }

    public GroundOverlayOptions transparency(float f2) {
        if (f2 <= 1.0f && f2 >= 0.0f) {
            this.k = f2;
        }
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.f2828b = z;
        return this;
    }

    public GroundOverlayOptions zIndex(int i) {
        this.f2827a = i;
        return this;
    }
}