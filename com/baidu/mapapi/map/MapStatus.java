package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.ab;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MapStatus implements Parcelable {
    public static final Parcelable.Creator<MapStatus> CREATOR = new k();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ab f2869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double f2870b;
    public final LatLngBounds bound;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f2871c;
    public final float overlook;
    public final float rotate;
    public final LatLng target;
    public final Point targetScreen;
    public WinRound winRound;
    public final float zoom;

    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private float f2872a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private LatLng f2873b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f2874c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f2875d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Point f2876e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private LatLngBounds f2877f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private double f2878g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private double f2879h;
        private final float i;

        public Builder() {
            this.f2872a = -2.1474836E9f;
            this.f2873b = null;
            this.f2874c = -2.1474836E9f;
            this.f2875d = -2.1474836E9f;
            this.f2876e = null;
            this.f2877f = null;
            this.f2878g = 0.0d;
            this.f2879h = 0.0d;
            this.i = 15.0f;
        }

        public Builder(MapStatus mapStatus) {
            this.f2872a = -2.1474836E9f;
            this.f2873b = null;
            this.f2874c = -2.1474836E9f;
            this.f2875d = -2.1474836E9f;
            this.f2876e = null;
            this.f2877f = null;
            this.f2878g = 0.0d;
            this.f2879h = 0.0d;
            this.i = 15.0f;
            this.f2872a = mapStatus.rotate;
            this.f2873b = mapStatus.target;
            this.f2874c = mapStatus.overlook;
            this.f2875d = mapStatus.zoom;
            this.f2876e = mapStatus.targetScreen;
            this.f2878g = mapStatus.a();
            this.f2879h = mapStatus.b();
        }

        private float a(float f2) {
            if (15.0f == f2) {
                return 15.5f;
            }
            return f2;
        }

        public MapStatus build() {
            return new MapStatus(this.f2872a, this.f2873b, this.f2874c, this.f2875d, this.f2876e, this.f2877f);
        }

        public Builder overlook(float f2) {
            this.f2874c = f2;
            return this;
        }

        public Builder rotate(float f2) {
            this.f2872a = f2;
            return this;
        }

        public Builder target(LatLng latLng) {
            this.f2873b = latLng;
            return this;
        }

        public Builder targetScreen(Point point) {
            this.f2876e = point;
            return this;
        }

        public Builder zoom(float f2) {
            this.f2875d = a(f2);
            return this;
        }
    }

    MapStatus(float f2, LatLng latLng, float f3, float f4, Point point, double d2, double d3, LatLngBounds latLngBounds) {
        this.rotate = f2;
        this.target = latLng;
        this.overlook = f3;
        this.zoom = f4;
        this.targetScreen = point;
        this.f2870b = d2;
        this.f2871c = d3;
        this.bound = latLngBounds;
    }

    MapStatus(float f2, LatLng latLng, float f3, float f4, Point point, LatLngBounds latLngBounds) {
        this.rotate = f2;
        this.target = latLng;
        this.overlook = f3;
        this.zoom = f4;
        this.targetScreen = point;
        LatLng latLng2 = this.target;
        if (latLng2 != null) {
            this.f2870b = CoordUtil.ll2mc(latLng2).getLongitudeE6();
            this.f2871c = CoordUtil.ll2mc(this.target).getLatitudeE6();
        }
        this.bound = latLngBounds;
    }

    MapStatus(float f2, LatLng latLng, float f3, float f4, Point point, ab abVar, double d2, double d3, LatLngBounds latLngBounds, WinRound winRound) {
        this.rotate = f2;
        this.target = latLng;
        this.overlook = f3;
        this.zoom = f4;
        this.targetScreen = point;
        this.f2869a = abVar;
        this.f2870b = d2;
        this.f2871c = d3;
        this.bound = latLngBounds;
        this.winRound = winRound;
    }

    protected MapStatus(Parcel parcel) {
        this.rotate = parcel.readFloat();
        this.target = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.overlook = parcel.readFloat();
        this.zoom = parcel.readFloat();
        this.targetScreen = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.bound = (LatLngBounds) parcel.readParcelable(LatLngBounds.class.getClassLoader());
        this.f2870b = parcel.readDouble();
        this.f2871c = parcel.readDouble();
    }

    static MapStatus a(ab abVar) {
        if (abVar == null) {
            return null;
        }
        float f2 = abVar.f3519b;
        double d2 = abVar.f3522e;
        double d3 = abVar.f3521d;
        LatLng latLngMc2ll = CoordUtil.mc2ll(new GeoPoint(d2, d3));
        float f3 = abVar.f3520c;
        float f4 = abVar.f3518a;
        Point point = new Point(abVar.f3523f, abVar.f3524g);
        LatLng latLngMc2ll2 = CoordUtil.mc2ll(new GeoPoint(abVar.k.f3530e.y, abVar.k.f3530e.x));
        LatLng latLngMc2ll3 = CoordUtil.mc2ll(new GeoPoint(abVar.k.f3531f.y, abVar.k.f3531f.x));
        LatLng latLngMc2ll4 = CoordUtil.mc2ll(new GeoPoint(abVar.k.f3533h.y, abVar.k.f3533h.x));
        LatLng latLngMc2ll5 = CoordUtil.mc2ll(new GeoPoint(abVar.k.f3532g.y, abVar.k.f3532g.x));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLngMc2ll2);
        builder.include(latLngMc2ll3);
        builder.include(latLngMc2ll4);
        builder.include(latLngMc2ll5);
        return new MapStatus(f2, latLngMc2ll, f3, f4, point, abVar, d3, d2, builder.build(), abVar.j);
    }

    double a() {
        return this.f2870b;
    }

    double b() {
        return this.f2871c;
    }

    ab b(ab abVar) {
        if (abVar == null) {
            return null;
        }
        float f2 = this.rotate;
        if (f2 != -2.1474836E9f) {
            abVar.f3519b = (int) f2;
        }
        float f3 = this.zoom;
        if (f3 != -2.1474836E9f) {
            abVar.f3518a = f3;
        }
        float f4 = this.overlook;
        if (f4 != -2.1474836E9f) {
            abVar.f3520c = (int) f4;
        }
        if (this.target != null) {
            abVar.f3521d = this.f2870b;
            abVar.f3522e = this.f2871c;
        }
        Point point = this.targetScreen;
        if (point != null) {
            abVar.f3523f = point.x;
            abVar.f3524g = this.targetScreen.y;
        }
        return abVar;
    }

    ab c() {
        return b(new ab());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.target != null) {
            sb.append("target lat: " + this.target.latitude + IOUtils.LINE_SEPARATOR_UNIX);
            sb.append("target lng: " + this.target.longitude + IOUtils.LINE_SEPARATOR_UNIX);
        }
        if (this.targetScreen != null) {
            sb.append("target screen x: " + this.targetScreen.x + IOUtils.LINE_SEPARATOR_UNIX);
            sb.append("target screen y: " + this.targetScreen.y + IOUtils.LINE_SEPARATOR_UNIX);
        }
        sb.append("zoom: " + this.zoom + IOUtils.LINE_SEPARATOR_UNIX);
        sb.append("rotate: " + this.rotate + IOUtils.LINE_SEPARATOR_UNIX);
        sb.append("overlook: " + this.overlook + IOUtils.LINE_SEPARATOR_UNIX);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.rotate);
        parcel.writeParcelable(this.target, i);
        parcel.writeFloat(this.overlook);
        parcel.writeFloat(this.zoom);
        parcel.writeParcelable(this.targetScreen, i);
        parcel.writeParcelable(this.bound, i);
        parcel.writeDouble(this.f2870b);
        parcel.writeDouble(this.f2871c);
    }
}