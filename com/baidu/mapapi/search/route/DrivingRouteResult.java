package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DrivingRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<DrivingRouteResult> CREATOR = new f();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<DrivingRouteLine> f3231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<TaxiInfo> f3232b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TaxiInfo f3233c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SuggestAddrInfo f3234d;

    public DrivingRouteResult() {
    }

    protected DrivingRouteResult(Parcel parcel) {
        this.f3231a = new ArrayList();
        parcel.readTypedList(this.f3231a, DrivingRouteLine.CREATOR);
        this.f3232b = new ArrayList();
        parcel.readTypedList(this.f3232b, TaxiInfo.CREATOR);
        this.f3234d = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<DrivingRouteLine> getRouteLines() {
        return this.f3231a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3234d;
    }

    @Deprecated
    public TaxiInfo getTaxiInfo() {
        return this.f3233c;
    }

    public List<TaxiInfo> getTaxiInfos() {
        return this.f3232b;
    }

    public void setRouteLines(List<DrivingRouteLine> list) {
        this.f3231a = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3234d = suggestAddrInfo;
    }

    public void setTaxiInfos(List<TaxiInfo> list) {
        this.f3232b = list;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f3231a);
        parcel.writeTypedList(this.f3232b);
        parcel.writeParcelable(this.f3234d, 1);
    }
}