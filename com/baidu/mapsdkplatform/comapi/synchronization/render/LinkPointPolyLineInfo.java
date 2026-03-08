package com.baidu.mapsdkplatform.comapi.synchronization.render;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class LinkPointPolyLineInfo implements Parcelable {
    public static final Parcelable.Creator<LinkPointPolyLineInfo> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f3767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LatLng f3768b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LatLng f3769c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3770d;

    public LinkPointPolyLineInfo() {
        this.f3767a = 0L;
        this.f3770d = 0;
        this.f3767a = 0L;
        this.f3768b = null;
        this.f3769c = null;
        this.f3770d = 0;
    }

    protected LinkPointPolyLineInfo(Parcel parcel) {
        this.f3767a = 0L;
        this.f3770d = 0;
        this.f3767a = parcel.readLong();
        this.f3768b = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.f3769c = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.f3770d = parcel.readInt();
    }

    public LatLng a() {
        return this.f3768b;
    }

    public void a(int i) {
        this.f3770d = i;
    }

    public void a(LatLng latLng) {
        this.f3768b = latLng;
    }

    public LatLng b() {
        return this.f3769c;
    }

    public void b(LatLng latLng) {
        this.f3769c = latLng;
    }

    public int c() {
        return this.f3770d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f3767a);
        parcel.writeParcelable(this.f3768b, i);
        parcel.writeParcelable(this.f3769c, i);
        parcel.writeInt(this.f3770d);
    }
}