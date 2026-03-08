package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RouteStep implements Parcelable {
    public static final Parcelable.Creator<RouteStep> CREATOR = new k();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f3132a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f3133b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f3134c;
    protected List<LatLng> mWayPoints;

    protected RouteStep() {
    }

    protected RouteStep(Parcel parcel) {
        this.f3132a = parcel.readInt();
        this.f3133b = parcel.readInt();
        this.f3134c = parcel.readString();
        this.mWayPoints = new ArrayList();
        parcel.readList(this.mWayPoints, LatLng.class.getClassLoader());
        if (this.mWayPoints.size() == 0) {
            this.mWayPoints = null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getDistance() {
        return this.f3132a;
    }

    public int getDuration() {
        return this.f3133b;
    }

    public String getName() {
        return this.f3134c;
    }

    public List<LatLng> getWayPoints() {
        return this.mWayPoints;
    }

    public void setDistance(int i) {
        this.f3132a = i;
    }

    public void setDuration(int i) {
        this.f3133b = i;
    }

    public void setName(String str) {
        this.f3134c = str;
    }

    public void setWayPoints(List<LatLng> list) {
        this.mWayPoints = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3132a);
        parcel.writeInt(this.f3133b);
        parcel.writeString(this.f3134c);
        parcel.writeList(this.mWayPoints);
    }
}