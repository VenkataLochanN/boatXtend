package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class TransitResultNode implements Parcelable {
    public static final Parcelable.Creator<TransitResultNode> CREATOR = new p();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3149b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LatLng f3150c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3151d;

    public TransitResultNode(int i, String str, LatLng latLng, String str2) {
        this.f3149b = null;
        this.f3150c = null;
        this.f3151d = null;
        this.f3148a = i;
        this.f3149b = str;
        this.f3150c = latLng;
        this.f3151d = str2;
    }

    protected TransitResultNode(Parcel parcel) {
        this.f3149b = null;
        this.f3150c = null;
        this.f3151d = null;
        this.f3148a = parcel.readInt();
        this.f3149b = parcel.readString();
        this.f3150c = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3151d = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCityId() {
        return this.f3148a;
    }

    public String getCityName() {
        return this.f3149b;
    }

    public LatLng getLocation() {
        return this.f3150c;
    }

    public String getSearchWord() {
        return this.f3151d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3148a);
        parcel.writeString(this.f3149b);
        parcel.writeValue(this.f3150c);
        parcel.writeString(this.f3151d);
    }
}