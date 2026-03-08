package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class TransitRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<TransitRouteResult> CREATOR = new q();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TaxiInfo f3290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<TransitRouteLine> f3291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SuggestAddrInfo f3292c;

    public TransitRouteResult() {
    }

    protected TransitRouteResult(Parcel parcel) {
        this.f3290a = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f3291b = new ArrayList();
        parcel.readList(this.f3291b, TransitRouteLine.class.getClassLoader());
        this.f3292c = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<TransitRouteLine> getRouteLines() {
        return this.f3291b;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3292c;
    }

    public TaxiInfo getTaxiInfo() {
        return this.f3290a;
    }

    public void setRoutelines(List<TransitRouteLine> list) {
        this.f3291b = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3292c = suggestAddrInfo;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f3290a = taxiInfo;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3290a, 1);
        parcel.writeList(this.f3291b);
        parcel.writeParcelable(this.f3292c, 1);
    }
}