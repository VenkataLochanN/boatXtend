package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class PlanNode implements Parcelable {
    public static final Parcelable.Creator<PlanNode> CREATOR = new m();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LatLng f3271a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3272b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3273c;

    protected PlanNode(Parcel parcel) {
        this.f3271a = null;
        this.f3272b = null;
        this.f3273c = null;
        this.f3271a = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3272b = parcel.readString();
        this.f3273c = parcel.readString();
    }

    PlanNode(LatLng latLng, String str, String str2) {
        this.f3271a = null;
        this.f3272b = null;
        this.f3273c = null;
        this.f3271a = latLng;
        this.f3272b = str;
        this.f3273c = str2;
    }

    public static PlanNode withCityCodeAndPlaceName(int i, String str) {
        return new PlanNode(null, String.valueOf(i), str);
    }

    public static PlanNode withCityNameAndPlaceName(String str, String str2) {
        return new PlanNode(null, str, str2);
    }

    public static PlanNode withLocation(LatLng latLng) {
        return new PlanNode(latLng, null, null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCity() {
        return this.f3272b;
    }

    public LatLng getLocation() {
        return this.f3271a;
    }

    public String getName() {
        return this.f3273c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.f3271a);
        parcel.writeString(this.f3272b);
        parcel.writeString(this.f3273c);
    }
}