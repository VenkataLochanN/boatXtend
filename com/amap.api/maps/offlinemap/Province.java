package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Province implements Parcelable {
    public static final Parcelable.Creator<Province> CREATOR = new Parcelable.Creator<Province>() { // from class: com.amap.api.maps.offlinemap.Province.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Province createFromParcel(Parcel parcel) {
            return new Province(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Province[] newArray(int i) {
            return new Province[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1929a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1930b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1931c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1932d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Province() {
        this.f1929a = "";
        this.f1932d = "";
    }

    public String getProvinceName() {
        return this.f1929a;
    }

    public String getJianpin() {
        return this.f1930b;
    }

    public String getPinyin() {
        return this.f1931c;
    }

    public void setProvinceName(String str) {
        this.f1929a = str;
    }

    public void setJianpin(String str) {
        this.f1930b = str;
    }

    public void setPinyin(String str) {
        this.f1931c = str;
    }

    public void setProvinceCode(String str) {
        this.f1932d = str;
    }

    public String getProvinceCode() {
        return this.f1932d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1929a);
        parcel.writeString(this.f1930b);
        parcel.writeString(this.f1931c);
        parcel.writeString(this.f1932d);
    }

    public Province(Parcel parcel) {
        this.f1929a = "";
        this.f1932d = "";
        this.f1929a = parcel.readString();
        this.f1930b = parcel.readString();
        this.f1931c = parcel.readString();
        this.f1932d = parcel.readString();
    }
}