package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SuggestAddrInfo implements Parcelable {
    public static final Parcelable.Creator<SuggestAddrInfo> CREATOR = new n();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<PoiInfo> f3276a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<PoiInfo> f3277b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<List<PoiInfo>> f3278c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<CityInfo> f3279d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<CityInfo> f3280e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<List<CityInfo>> f3281f;

    public SuggestAddrInfo() {
    }

    SuggestAddrInfo(Parcel parcel) {
        this.f3276a = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f3277b = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f3278c = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f3279d = parcel.readArrayList(CityInfo.class.getClassLoader());
        this.f3280e = parcel.readArrayList(CityInfo.class.getClassLoader());
        this.f3281f = parcel.readArrayList(CityInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<CityInfo> getSuggestEndCity() {
        return this.f3280e;
    }

    public List<PoiInfo> getSuggestEndNode() {
        return this.f3277b;
    }

    public List<CityInfo> getSuggestStartCity() {
        return this.f3279d;
    }

    public List<PoiInfo> getSuggestStartNode() {
        return this.f3276a;
    }

    public List<List<CityInfo>> getSuggestWpCity() {
        return this.f3281f;
    }

    public List<List<PoiInfo>> getSuggestWpNode() {
        return this.f3278c;
    }

    public void setSuggestEndCity(List<CityInfo> list) {
        this.f3280e = list;
    }

    public void setSuggestEndNode(List<PoiInfo> list) {
        this.f3277b = list;
    }

    public void setSuggestStartCity(List<CityInfo> list) {
        this.f3279d = list;
    }

    public void setSuggestStartNode(List<PoiInfo> list) {
        this.f3276a = list;
    }

    public void setSuggestWpCity(List<List<CityInfo>> list) {
        this.f3281f = list;
    }

    public void setSuggestWpNode(List<List<PoiInfo>> list) {
        this.f3278c = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f3276a);
        parcel.writeList(this.f3277b);
        parcel.writeList(this.f3278c);
        parcel.writeList(this.f3279d);
        parcel.writeList(this.f3280e);
        parcel.writeList(this.f3281f);
    }
}