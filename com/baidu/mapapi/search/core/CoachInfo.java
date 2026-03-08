package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class CoachInfo extends TransitBaseInfo {
    public static final Parcelable.Creator<CoachInfo> CREATOR = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double f3109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3110b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3112d;

    public CoachInfo() {
    }

    protected CoachInfo(Parcel parcel) {
        super(parcel);
        this.f3109a = parcel.readDouble();
        this.f3110b = parcel.readString();
        this.f3111c = parcel.readString();
        this.f3112d = parcel.readString();
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBooking() {
        return this.f3110b;
    }

    public double getPrice() {
        return this.f3109a;
    }

    public String getProviderName() {
        return this.f3111c;
    }

    public String getProviderUrl() {
        return this.f3112d;
    }

    public void setBooking(String str) {
        this.f3110b = str;
    }

    public void setPrice(double d2) {
        this.f3109a = d2;
    }

    public void setProviderName(String str) {
        this.f3111c = str;
    }

    public void setProviderUrl(String str) {
        this.f3112d = str;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f3109a);
        parcel.writeString(this.f3110b);
        parcel.writeString(this.f3111c);
        parcel.writeString(this.f3112d);
    }
}