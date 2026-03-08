package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class OfflineMapProvince extends Province {
    public static final Parcelable.Creator<OfflineMapProvince> CREATOR = new Parcelable.Creator<OfflineMapProvince>() { // from class: com.amap.api.maps.offlinemap.OfflineMapProvince.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public OfflineMapProvince createFromParcel(Parcel parcel) {
            return new OfflineMapProvince(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public OfflineMapProvince[] newArray(int i) {
            return new OfflineMapProvince[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1923a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1924b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f1925c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1926d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1927e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<OfflineMapCity> f1928f;

    @Override // com.amap.api.maps.offlinemap.Province, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OfflineMapProvince() {
        this.f1924b = 6;
        this.f1927e = 0;
    }

    public String getUrl() {
        return this.f1923a;
    }

    public void setUrl(String str) {
        this.f1923a = str;
    }

    public int getState() {
        return this.f1924b;
    }

    public void setState(int i) {
        this.f1924b = i;
    }

    public long getSize() {
        return this.f1925c;
    }

    public void setSize(long j) {
        this.f1925c = j;
    }

    public String getVersion() {
        return this.f1926d;
    }

    public void setVersion(String str) {
        this.f1926d = str;
    }

    public int getcompleteCode() {
        return this.f1927e;
    }

    public void setCompleteCode(int i) {
        this.f1927e = i;
    }

    @Override // com.amap.api.maps.offlinemap.Province, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1923a);
        parcel.writeInt(this.f1924b);
        parcel.writeLong(this.f1925c);
        parcel.writeString(this.f1926d);
        parcel.writeInt(this.f1927e);
        parcel.writeTypedList(this.f1928f);
    }

    public ArrayList<OfflineMapCity> getCityList() {
        ArrayList<OfflineMapCity> arrayList = this.f1928f;
        return arrayList == null ? new ArrayList<>() : arrayList;
    }

    public ArrayList<OfflineMapCity> getDownloadedCityList() {
        ArrayList<OfflineMapCity> arrayList = new ArrayList<>();
        ArrayList<OfflineMapCity> arrayList2 = this.f1928f;
        if (arrayList2 == null) {
            return arrayList;
        }
        for (OfflineMapCity offlineMapCity : arrayList2) {
            if (offlineMapCity.getState() != 6) {
                arrayList.add(offlineMapCity);
            }
        }
        return arrayList;
    }

    public void setCityList(ArrayList<OfflineMapCity> arrayList) {
        this.f1928f = arrayList;
    }

    public OfflineMapProvince(Parcel parcel) {
        super(parcel);
        this.f1924b = 6;
        this.f1927e = 0;
        this.f1923a = parcel.readString();
        this.f1924b = parcel.readInt();
        this.f1925c = parcel.readLong();
        this.f1926d = parcel.readString();
        this.f1927e = parcel.readInt();
        this.f1928f = parcel.createTypedArrayList(OfflineMapCity.CREATOR);
    }
}