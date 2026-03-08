package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BikingRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<BikingRouteResult> CREATOR = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<BikingRouteLine> f3216a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SuggestAddrInfo f3217b;

    public BikingRouteResult() {
    }

    protected BikingRouteResult(Parcel parcel) {
        this.f3216a = new ArrayList();
        parcel.readList(this.f3216a, BikingRouteLine.class.getClassLoader());
        this.f3217b = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<BikingRouteLine> getRouteLines() {
        return this.f3216a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3217b;
    }

    public void setRouteLines(List<BikingRouteLine> list) {
        this.f3216a = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3217b = suggestAddrInfo;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f3216a);
        parcel.writeParcelable(this.f3217b, 1);
    }
}