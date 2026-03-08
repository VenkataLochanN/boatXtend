package com.baidu.mapapi.map;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class DotOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2806a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bundle f2808c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LatLng f2809d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2810e = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2811f = 5;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2807b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    Overlay a() {
        Dot dot = new Dot();
        dot.A = this.f2807b;
        dot.z = this.f2806a;
        dot.B = this.f2808c;
        dot.f2804b = this.f2810e;
        dot.f2803a = this.f2809d;
        dot.f2805c = this.f2811f;
        return dot;
    }

    public DotOptions center(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: dot center can not be null");
        }
        this.f2809d = latLng;
        return this;
    }

    public DotOptions color(int i) {
        this.f2810e = i;
        return this;
    }

    public DotOptions extraInfo(Bundle bundle) {
        this.f2808c = bundle;
        return this;
    }

    public LatLng getCenter() {
        return this.f2809d;
    }

    public int getColor() {
        return this.f2810e;
    }

    public Bundle getExtraInfo() {
        return this.f2808c;
    }

    public int getRadius() {
        return this.f2811f;
    }

    public int getZIndex() {
        return this.f2806a;
    }

    public boolean isVisible() {
        return this.f2807b;
    }

    public DotOptions radius(int i) {
        if (i > 0) {
            this.f2811f = i;
        }
        return this;
    }

    public DotOptions visible(boolean z) {
        this.f2807b = z;
        return this;
    }

    public DotOptions zIndex(int i) {
        this.f2806a = i;
        return this;
    }
}