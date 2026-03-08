package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class RouteNode implements Parcelable {
    public static final Parcelable.Creator<RouteNode> CREATOR = new j();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3129a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LatLng f3130b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3131c;

    public RouteNode() {
    }

    protected RouteNode(Parcel parcel) {
        this.f3129a = parcel.readString();
        this.f3130b = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.f3131c = parcel.readString();
    }

    public static RouteNode location(LatLng latLng) {
        RouteNode routeNode = new RouteNode();
        routeNode.setLocation(latLng);
        return routeNode;
    }

    public static RouteNode titleAndLocation(String str, LatLng latLng) {
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(str);
        routeNode.setLocation(latLng);
        return routeNode;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLng getLocation() {
        return this.f3130b;
    }

    public String getTitle() {
        return this.f3129a;
    }

    public String getUid() {
        return this.f3131c;
    }

    public void setLocation(LatLng latLng) {
        this.f3130b = latLng;
    }

    public void setTitle(String str) {
        this.f3129a = str;
    }

    public void setUid(String str) {
        this.f3131c = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3129a);
        parcel.writeValue(this.f3130b);
        parcel.writeString(this.f3131c);
    }
}