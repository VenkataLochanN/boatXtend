package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class BusInfo extends TransitBaseInfo {
    public static final Parcelable.Creator<BusInfo> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3108b;

    public BusInfo() {
    }

    protected BusInfo(Parcel parcel) {
        super(parcel);
        this.f3107a = parcel.readInt();
        this.f3108b = parcel.readInt();
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getStopNum() {
        return this.f3108b;
    }

    public int getType() {
        return this.f3107a;
    }

    public void setStopNum(int i) {
        this.f3108b = i;
    }

    public void setType(int i) {
        this.f3107a = i;
    }

    @Override // com.baidu.mapapi.search.core.TransitBaseInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f3107a);
        parcel.writeInt(this.f3108b);
    }
}