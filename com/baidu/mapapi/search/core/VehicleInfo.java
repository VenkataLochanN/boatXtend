package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class VehicleInfo implements Parcelable {
    public static final Parcelable.Creator<VehicleInfo> CREATOR = new q();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3154c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3156e;

    public VehicleInfo() {
    }

    protected VehicleInfo(Parcel parcel) {
        this.f3152a = parcel.readString();
        this.f3153b = parcel.readInt();
        this.f3154c = parcel.readString();
        this.f3155d = parcel.readInt();
        this.f3156e = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getPassStationNum() {
        return this.f3153b;
    }

    public String getTitle() {
        return this.f3154c;
    }

    public int getTotalPrice() {
        return this.f3156e;
    }

    public String getUid() {
        return this.f3152a;
    }

    public int getZonePrice() {
        return this.f3155d;
    }

    public void setPassStationNum(int i) {
        this.f3153b = i;
    }

    public void setTitle(String str) {
        this.f3154c = str;
    }

    public void setTotalPrice(int i) {
        this.f3156e = i;
    }

    public void setUid(String str) {
        this.f3152a = str;
    }

    public void setZonePrice(int i) {
        this.f3155d = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3152a);
        parcel.writeInt(this.f3153b);
        parcel.writeString(this.f3154c);
        parcel.writeInt(this.f3155d);
        parcel.writeInt(this.f3156e);
    }
}