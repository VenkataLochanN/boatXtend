package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class City implements Parcelable {
    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() { // from class: com.amap.api.maps.offlinemap.City.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public City createFromParcel(Parcel parcel) {
            return new City(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public City[] newArray(int i) {
            return new City[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1880a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1881b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1882c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1883d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f1884e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public City() {
        this.f1880a = "";
        this.f1881b = "";
        this.f1884e = "";
    }

    public void setCity(String str) {
        this.f1880a = str;
    }

    public String getCity() {
        return this.f1880a;
    }

    public void setCode(String str) {
        if (str == null || "[]".equals(str)) {
            return;
        }
        this.f1881b = str;
    }

    public String getCode() {
        return this.f1881b;
    }

    public String getJianpin() {
        return this.f1882c;
    }

    public void setJianpin(String str) {
        this.f1882c = str;
    }

    public String getPinyin() {
        return this.f1883d;
    }

    public void setPinyin(String str) {
        this.f1883d = str;
    }

    public String getAdcode() {
        return this.f1884e;
    }

    public void setAdcode(String str) {
        this.f1884e = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1880a);
        parcel.writeString(this.f1881b);
        parcel.writeString(this.f1882c);
        parcel.writeString(this.f1883d);
        parcel.writeString(this.f1884e);
    }

    public City(Parcel parcel) {
        this.f1880a = "";
        this.f1881b = "";
        this.f1884e = "";
        this.f1880a = parcel.readString();
        this.f1881b = parcel.readString();
        this.f1882c = parcel.readString();
        this.f1883d = parcel.readString();
        this.f1884e = parcel.readString();
    }
}