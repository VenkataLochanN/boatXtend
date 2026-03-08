package com.baidu.mapapi.map;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class CircleOptions extends OverlayOptions {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2795d = CircleOptions.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2796a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bundle f2798c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LatLng f2799e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2801g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Stroke f2802h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2800f = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2797b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    Overlay a() {
        Circle circle = new Circle();
        circle.A = this.f2797b;
        circle.z = this.f2796a;
        circle.B = this.f2798c;
        circle.f2792b = this.f2800f;
        circle.f2791a = this.f2799e;
        circle.f2793c = this.f2801g;
        circle.f2794d = this.f2802h;
        return circle;
    }

    public CircleOptions center(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: circle center can not be null");
        }
        this.f2799e = latLng;
        return this;
    }

    public CircleOptions extraInfo(Bundle bundle) {
        this.f2798c = bundle;
        return this;
    }

    public CircleOptions fillColor(int i) {
        this.f2800f = i;
        return this;
    }

    public LatLng getCenter() {
        return this.f2799e;
    }

    public Bundle getExtraInfo() {
        return this.f2798c;
    }

    public int getFillColor() {
        return this.f2800f;
    }

    public int getRadius() {
        return this.f2801g;
    }

    public Stroke getStroke() {
        return this.f2802h;
    }

    public int getZIndex() {
        return this.f2796a;
    }

    public boolean isVisible() {
        return this.f2797b;
    }

    public CircleOptions radius(int i) {
        this.f2801g = i;
        return this;
    }

    public CircleOptions stroke(Stroke stroke) {
        this.f2802h = stroke;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.f2797b = z;
        return this;
    }

    public CircleOptions zIndex(int i) {
        this.f2796a = i;
        return this;
    }
}