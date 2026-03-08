package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TrainInfo extends TransitBaseInfo {
    public static final Parcelable.Creator<TrainInfo> CREATOR = new n();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double f3141a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3142b;

    public TrainInfo() {
    }

    protected TrainInfo(Parcel parcel) {
        super(parcel);
        this.f3141a = parcel.readDouble();
        this.f3142b = parcel.readString();
    }

    public void a(double d2) {
        this.f3141a = d2;
    }

    public void a(String str) {
        this.f3142b = str;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.f3141a);
        parcel.writeString(this.f3142b);
    }
}