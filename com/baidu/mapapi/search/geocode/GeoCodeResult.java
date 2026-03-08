package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;

/* JADX INFO: loaded from: classes.dex */
public class GeoCodeResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<GeoCodeResult> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LatLng f3159a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3160b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3161c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3162d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3163e;

    public GeoCodeResult() {
    }

    protected GeoCodeResult(Parcel parcel) {
        this.f3159a = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3160b = parcel.readString();
        this.f3161c = parcel.readInt();
        this.f3162d = parcel.readInt();
        this.f3163e = parcel.readString();
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public String getAddress() {
        return this.f3160b;
    }

    public int getConfidence() {
        return this.f3162d;
    }

    public String getLevel() {
        return this.f3163e;
    }

    public LatLng getLocation() {
        return this.f3159a;
    }

    public int getPrecise() {
        return this.f3161c;
    }

    @Deprecated
    public void setAddress(String str) {
        this.f3160b = str;
    }

    public void setConfidence(int i) {
        this.f3162d = i;
    }

    public void setLevel(String str) {
        this.f3163e = str;
    }

    public void setLocation(LatLng latLng) {
        this.f3159a = latLng;
    }

    public void setPrecise(int i) {
        this.f3161c = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GeoCodeResult: \n");
        stringBuffer.append("location = ");
        stringBuffer.append(this.f3159a);
        stringBuffer.append("; precise = ");
        stringBuffer.append(this.f3161c);
        stringBuffer.append("; confidence = ");
        stringBuffer.append(this.f3162d);
        stringBuffer.append("; level = ");
        stringBuffer.append(this.f3163e);
        return stringBuffer.toString();
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f3159a);
        parcel.writeString(this.f3160b);
        parcel.writeInt(this.f3161c);
        parcel.writeInt(this.f3162d);
        parcel.writeString(this.f3163e);
    }
}