package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PlaneInfo extends TransitBaseInfo {
    public static final Parcelable.Creator<PlaneInfo> CREATOR = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double f3113a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3114b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f3115c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3116d;

    public PlaneInfo() {
    }

    protected PlaneInfo(Parcel parcel) {
        super(parcel);
        this.f3113a = parcel.readDouble();
        this.f3114b = parcel.readString();
        this.f3115c = parcel.readDouble();
        this.f3116d = parcel.readString();
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAirlines() {
        return this.f3114b;
    }

    public String getBooking() {
        return this.f3116d;
    }

    public double getDiscount() {
        return this.f3113a;
    }

    public double getPrice() {
        return this.f3115c;
    }

    public void setAirlines(String str) {
        this.f3114b = str;
    }

    public void setBooking(String str) {
        this.f3116d = str;
    }

    public void setDiscount(double d2) {
        this.f3113a = d2;
    }

    public void setPrice(double d2) {
        this.f3115c = d2;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f3113a);
        parcel.writeString(this.f3114b);
        parcel.writeDouble(this.f3115c);
        parcel.writeString(this.f3116d);
    }
}