package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TaxiInfo implements Parcelable {
    public static final Parcelable.Creator<TaxiInfo> CREATOR = new m();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f3135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3136b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3137c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3138d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f3139e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f3140f;

    public TaxiInfo() {
    }

    TaxiInfo(Parcel parcel) {
        this.f3135a = parcel.readFloat();
        this.f3136b = parcel.readString();
        this.f3137c = parcel.readInt();
        this.f3138d = parcel.readInt();
        this.f3139e = parcel.readFloat();
        this.f3140f = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDesc() {
        return this.f3136b;
    }

    public int getDistance() {
        return this.f3137c;
    }

    public int getDuration() {
        return this.f3138d;
    }

    public float getPerKMPrice() {
        return this.f3139e;
    }

    public float getStartPrice() {
        return this.f3140f;
    }

    public float getTotalPrice() {
        return this.f3135a;
    }

    public void setDesc(String str) {
        this.f3136b = str;
    }

    public void setDistance(int i) {
        this.f3137c = i;
    }

    public void setDuration(int i) {
        this.f3138d = i;
    }

    public void setPerKMPrice(float f2) {
        this.f3139e = f2;
    }

    public void setStartPrice(float f2) {
        this.f3140f = f2;
    }

    public void setTotalPrice(float f2) {
        this.f3135a = f2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f3135a);
        parcel.writeString(this.f3136b);
        parcel.writeInt(this.f3137c);
        parcel.writeInt(this.f3138d);
        parcel.writeFloat(this.f3139e);
        parcel.writeFloat(this.f3140f);
    }
}