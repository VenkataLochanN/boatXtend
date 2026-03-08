package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PriceInfo implements Parcelable {
    public static final Parcelable.Creator<PriceInfo> CREATOR = new i();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3118a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double f3119b;

    public PriceInfo() {
    }

    protected PriceInfo(Parcel parcel) {
        this.f3118a = parcel.readInt();
        this.f3119b = parcel.readDouble();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double getTicketPrice() {
        return this.f3119b;
    }

    public int getTicketType() {
        return this.f3118a;
    }

    public void setTicketPrice(double d2) {
        this.f3119b = d2;
    }

    public void setTicketType(int i) {
        this.f3118a = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3118a);
        parcel.writeDouble(this.f3119b);
    }
}