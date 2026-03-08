package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WalkingRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<WalkingRouteResult> CREATOR = new t();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<WalkingRouteLine> f3298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TaxiInfo f3299b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SuggestAddrInfo f3300c;

    public WalkingRouteResult() {
    }

    protected WalkingRouteResult(Parcel parcel) {
        this.f3298a = new ArrayList();
        parcel.readList(this.f3298a, WalkingRouteLine.class.getClassLoader());
        this.f3299b = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f3300c = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<WalkingRouteLine> getRouteLines() {
        return this.f3298a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3300c;
    }

    public TaxiInfo getTaxiInfo() {
        return this.f3299b;
    }

    public void setRouteLines(List<WalkingRouteLine> list) {
        this.f3298a = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3300c = suggestAddrInfo;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f3299b = taxiInfo;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f3298a);
        parcel.writeParcelable(this.f3299b, 1);
        parcel.writeParcelable(this.f3300c, 1);
    }
}