package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class BaiduMapOptions implements Parcelable {
    public static final Parcelable.Creator<BaiduMapOptions> CREATOR = new g();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    MapStatus f2779a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f2780b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f2781c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f2782d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f2783e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f2784f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f2785g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f2786h;
    boolean i;
    LogoPosition j;
    Point k;
    Point l;

    public BaiduMapOptions() {
        this.f2779a = new MapStatus(0.0f, new LatLng(39.914935d, 116.403119d), 0.0f, 12.0f, null, null);
        this.f2780b = true;
        this.f2781c = 1;
        this.f2782d = true;
        this.f2783e = true;
        this.f2784f = true;
        this.f2785g = true;
        this.f2786h = true;
        this.i = true;
    }

    protected BaiduMapOptions(Parcel parcel) {
        this.f2779a = new MapStatus(0.0f, new LatLng(39.914935d, 116.403119d), 0.0f, 12.0f, null, null);
        this.f2780b = true;
        this.f2781c = 1;
        this.f2782d = true;
        this.f2783e = true;
        this.f2784f = true;
        this.f2785g = true;
        this.f2786h = true;
        this.i = true;
        this.f2779a = (MapStatus) parcel.readParcelable(MapStatus.class.getClassLoader());
        this.f2780b = parcel.readByte() != 0;
        this.f2781c = parcel.readInt();
        this.f2782d = parcel.readByte() != 0;
        this.f2783e = parcel.readByte() != 0;
        this.f2784f = parcel.readByte() != 0;
        this.f2785g = parcel.readByte() != 0;
        this.f2786h = parcel.readByte() != 0;
        this.i = parcel.readByte() != 0;
        this.k = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.l = (Point) parcel.readParcelable(Point.class.getClassLoader());
    }

    com.baidu.mapsdkplatform.comapi.map.z a() {
        return new com.baidu.mapsdkplatform.comapi.map.z().a(this.f2779a.c()).a(this.f2780b).a(this.f2781c).b(this.f2782d).c(this.f2783e).d(this.f2784f).e(this.f2785g);
    }

    public BaiduMapOptions compassEnabled(boolean z) {
        this.f2780b = z;
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaiduMapOptions logoPosition(LogoPosition logoPosition) {
        this.j = logoPosition;
        return this;
    }

    public BaiduMapOptions mapStatus(MapStatus mapStatus) {
        if (mapStatus != null) {
            this.f2779a = mapStatus;
        }
        return this;
    }

    public BaiduMapOptions mapType(int i) {
        this.f2781c = i;
        return this;
    }

    public BaiduMapOptions overlookingGesturesEnabled(boolean z) {
        this.f2784f = z;
        return this;
    }

    public BaiduMapOptions rotateGesturesEnabled(boolean z) {
        this.f2782d = z;
        return this;
    }

    public BaiduMapOptions scaleControlEnabled(boolean z) {
        this.i = z;
        return this;
    }

    public BaiduMapOptions scaleControlPosition(Point point) {
        this.k = point;
        return this;
    }

    public BaiduMapOptions scrollGesturesEnabled(boolean z) {
        this.f2783e = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2779a, i);
        parcel.writeByte(this.f2780b ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f2781c);
        parcel.writeByte(this.f2782d ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2783e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2784f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2785g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2786h ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.k, i);
        parcel.writeParcelable(this.l, i);
    }

    public BaiduMapOptions zoomControlsEnabled(boolean z) {
        this.f2786h = z;
        return this;
    }

    public BaiduMapOptions zoomControlsPosition(Point point) {
        this.l = point;
        return this;
    }

    public BaiduMapOptions zoomGesturesEnabled(boolean z) {
        this.f2785g = z;
        return this;
    }
}