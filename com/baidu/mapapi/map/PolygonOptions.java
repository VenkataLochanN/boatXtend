package com.baidu.mapapi.map;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PolygonOptions extends OverlayOptions {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f2937a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Bundle f2939c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Stroke f2940d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<LatLng> f2942f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2941e = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2938b = true;

    @Override // com.baidu.mapapi.map.OverlayOptions
    Overlay a() {
        Polygon polygon = new Polygon();
        polygon.A = this.f2938b;
        polygon.z = this.f2937a;
        polygon.B = this.f2939c;
        List<LatLng> list = this.f2942f;
        if (list == null || list.size() < 2) {
            throw new IllegalStateException("BDMapSDKException: when you add polyline, you must at least supply 2 points");
        }
        polygon.f2936c = this.f2942f;
        polygon.f2935b = this.f2941e;
        polygon.f2934a = this.f2940d;
        return polygon;
    }

    public PolygonOptions extraInfo(Bundle bundle) {
        this.f2939c = bundle;
        return this;
    }

    public PolygonOptions fillColor(int i) {
        this.f2941e = i;
        return this;
    }

    public Bundle getExtraInfo() {
        return this.f2939c;
    }

    public int getFillColor() {
        return this.f2941e;
    }

    public List<LatLng> getPoints() {
        return this.f2942f;
    }

    public Stroke getStroke() {
        return this.f2940d;
    }

    public int getZIndex() {
        return this.f2937a;
    }

    public boolean isVisible() {
        return this.f2938b;
    }

    public PolygonOptions points(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
        }
        if (list.size() <= 2) {
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than three");
        }
        if (list.contains(null)) {
            throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
        }
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                if (list.get(i) == list.get(i3)) {
                    throw new IllegalArgumentException("BDMapSDKException: points list can not has same points");
                }
            }
            i = i2;
        }
        this.f2942f = list;
        return this;
    }

    public PolygonOptions stroke(Stroke stroke) {
        this.f2940d = stroke;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.f2938b = z;
        return this;
    }

    public PolygonOptions zIndex(int i) {
        this.f2937a = i;
        return this;
    }
}