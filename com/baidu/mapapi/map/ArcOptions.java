package com.baidu.mapapi.map;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class ArcOptions extends OverlayOptions {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2763d = ArcOptions.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2764a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bundle f2766c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private LatLng f2769g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private LatLng f2770h;
    private LatLng i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2767e = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2768f = 5;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2765b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    Overlay a() {
        Arc arc = new Arc();
        arc.A = this.f2765b;
        arc.z = this.f2764a;
        arc.B = this.f2766c;
        arc.f2758a = this.f2767e;
        arc.f2759b = this.f2768f;
        arc.f2760c = this.f2769g;
        arc.f2761d = this.f2770h;
        arc.f2762e = this.i;
        return arc;
    }

    public ArcOptions color(int i) {
        this.f2767e = i;
        return this;
    }

    public ArcOptions extraInfo(Bundle bundle) {
        this.f2766c = bundle;
        return this;
    }

    public int getColor() {
        return this.f2767e;
    }

    public LatLng getEndPoint() {
        return this.i;
    }

    public Bundle getExtraInfo() {
        return this.f2766c;
    }

    public LatLng getMiddlePoint() {
        return this.f2770h;
    }

    public LatLng getStartPoint() {
        return this.f2769g;
    }

    public int getWidth() {
        return this.f2768f;
    }

    public int getZIndex() {
        return this.f2764a;
    }

    public boolean isVisible() {
        return this.f2765b;
    }

    public ArcOptions points(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng == null || latLng2 == null || latLng3 == null) {
            throw new IllegalArgumentException("BDMapSDKException: start and middle and end points can not be null");
        }
        if (latLng == latLng2 || latLng == latLng3 || latLng2 == latLng3) {
            throw new IllegalArgumentException("BDMapSDKException: start and middle and end points can not be same");
        }
        this.f2769g = latLng;
        this.f2770h = latLng2;
        this.i = latLng3;
        return this;
    }

    public ArcOptions visible(boolean z) {
        this.f2765b = z;
        return this;
    }

    public ArcOptions width(int i) {
        if (i > 0) {
            this.f2768f = i;
        }
        return this;
    }

    public ArcOptions zIndex(int i) {
        this.f2764a = i;
        return this;
    }
}