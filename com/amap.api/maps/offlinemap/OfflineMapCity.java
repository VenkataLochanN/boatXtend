package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class OfflineMapCity extends City {
    public static final Parcelable.Creator<OfflineMapCity> CREATOR = new Parcelable.Creator<OfflineMapCity>() { // from class: com.amap.api.maps.offlinemap.OfflineMapCity.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public OfflineMapCity createFromParcel(Parcel parcel) {
            return new OfflineMapCity(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public OfflineMapCity[] newArray(int i) {
            return new OfflineMapCity[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f1900b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1901c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1902d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1903e;

    @Override // com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OfflineMapCity() {
        this.f1899a = "";
        this.f1900b = 0L;
        this.f1901c = 6;
        this.f1902d = "";
        this.f1903e = 0;
    }

    public String getUrl() {
        return this.f1899a;
    }

    public void setUrl(String str) {
        this.f1899a = str;
    }

    public long getSize() {
        return this.f1900b;
    }

    public void setSize(long j) {
        this.f1900b = j;
    }

    public int getState() {
        return this.f1901c;
    }

    public void setState(int i) {
        this.f1901c = i;
    }

    public String getVersion() {
        return this.f1902d;
    }

    public void setVersion(String str) {
        this.f1902d = str;
    }

    public int getcompleteCode() {
        return this.f1903e;
    }

    public void setCompleteCode(int i) {
        this.f1903e = i;
    }

    @Override // com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1899a);
        parcel.writeLong(this.f1900b);
        parcel.writeInt(this.f1901c);
        parcel.writeString(this.f1902d);
        parcel.writeInt(this.f1903e);
    }

    public OfflineMapCity(Parcel parcel) {
        super(parcel);
        this.f1899a = "";
        this.f1900b = 0L;
        this.f1901c = 6;
        this.f1902d = "";
        this.f1903e = 0;
        this.f1899a = parcel.readString();
        this.f1900b = parcel.readLong();
        this.f1901c = parcel.readInt();
        this.f1902d = parcel.readString();
        this.f1903e = parcel.readInt();
    }
}