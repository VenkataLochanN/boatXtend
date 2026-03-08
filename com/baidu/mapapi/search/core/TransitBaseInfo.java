package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TransitBaseInfo implements Parcelable {
    public static final Parcelable.Creator<TransitBaseInfo> CREATOR = new o();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3143a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3144b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3146d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3147e;

    public TransitBaseInfo() {
    }

    protected TransitBaseInfo(Parcel parcel) {
        this.f3143a = parcel.readString();
        this.f3144b = parcel.readString();
        this.f3145c = parcel.readString();
        this.f3146d = parcel.readString();
        this.f3147e = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getArriveStation() {
        return this.f3145c;
    }

    public String getArriveTime() {
        return this.f3147e;
    }

    public String getDepartureStation() {
        return this.f3144b;
    }

    public String getDepartureTime() {
        return this.f3146d;
    }

    public String getName() {
        return this.f3143a;
    }

    public void setArriveStation(String str) {
        this.f3145c = str;
    }

    public void setArriveTime(String str) {
        this.f3147e = str;
    }

    public void setDepartureStation(String str) {
        this.f3144b = str;
    }

    public void setDepartureTime(String str) {
        this.f3146d = str;
    }

    public void setName(String str) {
        this.f3143a = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3143a);
        parcel.writeString(this.f3144b);
        parcel.writeString(this.f3145c);
        parcel.writeString(this.f3146d);
        parcel.writeString(this.f3147e);
    }
}