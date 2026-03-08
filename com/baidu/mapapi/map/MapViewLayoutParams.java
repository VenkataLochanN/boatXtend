package com.baidu.mapapi.map;

import android.graphics.Point;
import android.view.ViewGroup;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class MapViewLayoutParams extends ViewGroup.LayoutParams {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    LatLng f2896a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Point f2897b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ELayoutMode f2898c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f2899d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f2900e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f2901f;

    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2902a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2903b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private LatLng f2904c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Point f2905d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private ELayoutMode f2906e = ELayoutMode.absoluteMode;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f2907f = 4;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f2908g = 16;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f2909h;

        public Builder align(int i, int i2) {
            if (i == 1 || i == 2 || i == 4) {
                this.f2907f = i;
            }
            if (i2 == 8 || i2 == 16 || i2 == 32) {
                this.f2908g = i2;
            }
            return this;
        }

        public MapViewLayoutParams build() {
            boolean z = true;
            if (this.f2906e != ELayoutMode.mapMode ? this.f2906e != ELayoutMode.absoluteMode || this.f2905d != null : this.f2904c != null) {
                z = false;
            }
            if (z) {
                throw new IllegalStateException("BDMapSDKException: if it is map mode, you must supply position info; else if it is absolute mode, you must supply the point info");
            }
            return new MapViewLayoutParams(this.f2902a, this.f2903b, this.f2904c, this.f2905d, this.f2906e, this.f2907f, this.f2908g, this.f2909h);
        }

        public Builder height(int i) {
            this.f2903b = i;
            return this;
        }

        public Builder layoutMode(ELayoutMode eLayoutMode) {
            this.f2906e = eLayoutMode;
            return this;
        }

        public Builder point(Point point) {
            this.f2905d = point;
            return this;
        }

        public Builder position(LatLng latLng) {
            this.f2904c = latLng;
            return this;
        }

        public Builder width(int i) {
            this.f2902a = i;
            return this;
        }

        public Builder yOffset(int i) {
            this.f2909h = i;
            return this;
        }
    }

    public enum ELayoutMode {
        mapMode,
        absoluteMode
    }

    MapViewLayoutParams(int i, int i2, LatLng latLng, Point point, ELayoutMode eLayoutMode, int i3, int i4, int i5) {
        super(i, i2);
        this.f2896a = latLng;
        this.f2897b = point;
        this.f2898c = eLayoutMode;
        if (i3 == 1) {
            this.f2899d = 0.0f;
        } else if (i3 != 2) {
            this.f2899d = 0.5f;
        } else {
            this.f2899d = 1.0f;
        }
        if (i4 == 8) {
            this.f2900e = 0.0f;
        } else if (i4 == 16 || i4 != 32) {
            this.f2900e = 1.0f;
        } else {
            this.f2900e = 0.5f;
        }
        this.f2901f = i5;
    }
}