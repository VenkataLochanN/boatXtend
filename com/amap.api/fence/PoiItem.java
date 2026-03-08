package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PoiItem implements Parcelable {
    public static final Parcelable.Creator<PoiItem> CREATOR = new Parcelable.Creator<PoiItem>() { // from class: com.amap.api.fence.PoiItem.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ PoiItem createFromParcel(Parcel parcel) {
            return new PoiItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ PoiItem[] newArray(int i) {
            return new PoiItem[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f43a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f44b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f45c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f46d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f47e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private double f48f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private double f49g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f50h;
    private String i;
    private String j;
    private String k;

    public PoiItem() {
        this.f43a = "";
        this.f44b = "";
        this.f45c = "";
        this.f46d = "";
        this.f47e = "";
        this.f48f = 0.0d;
        this.f49g = 0.0d;
        this.f50h = "";
        this.i = "";
        this.j = "";
        this.k = "";
    }

    protected PoiItem(Parcel parcel) {
        this.f43a = "";
        this.f44b = "";
        this.f45c = "";
        this.f46d = "";
        this.f47e = "";
        this.f48f = 0.0d;
        this.f49g = 0.0d;
        this.f50h = "";
        this.i = "";
        this.j = "";
        this.k = "";
        this.f43a = parcel.readString();
        this.f44b = parcel.readString();
        this.f45c = parcel.readString();
        this.f46d = parcel.readString();
        this.f47e = parcel.readString();
        this.f48f = parcel.readDouble();
        this.f49g = parcel.readDouble();
        this.f50h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
    }

    public static Parcelable.Creator<PoiItem> getCreator() {
        return CREATOR;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.f47e;
    }

    public String getAdname() {
        return this.k;
    }

    public String getCity() {
        return this.j;
    }

    public double getLatitude() {
        return this.f48f;
    }

    public double getLongitude() {
        return this.f49g;
    }

    public String getPoiId() {
        return this.f44b;
    }

    public String getPoiName() {
        return this.f43a;
    }

    public String getPoiType() {
        return this.f45c;
    }

    public String getProvince() {
        return this.i;
    }

    public String getTel() {
        return this.f50h;
    }

    public String getTypeCode() {
        return this.f46d;
    }

    public void setAddress(String str) {
        this.f47e = str;
    }

    public void setAdname(String str) {
        this.k = str;
    }

    public void setCity(String str) {
        this.j = str;
    }

    public void setLatitude(double d2) {
        this.f48f = d2;
    }

    public void setLongitude(double d2) {
        this.f49g = d2;
    }

    public void setPoiId(String str) {
        this.f44b = str;
    }

    public void setPoiName(String str) {
        this.f43a = str;
    }

    public void setPoiType(String str) {
        this.f45c = str;
    }

    public void setProvince(String str) {
        this.i = str;
    }

    public void setTel(String str) {
        this.f50h = str;
    }

    public void setTypeCode(String str) {
        this.f46d = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f43a);
        parcel.writeString(this.f44b);
        parcel.writeString(this.f45c);
        parcel.writeString(this.f46d);
        parcel.writeString(this.f47e);
        parcel.writeDouble(this.f48f);
        parcel.writeDouble(this.f49g);
        parcel.writeString(this.f50h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
    }
}