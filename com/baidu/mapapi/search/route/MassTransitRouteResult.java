package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.TransitResultNode;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MassTransitRouteResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<MassTransitRouteResult> CREATOR = new l();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TransitResultNode f3265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TransitResultNode f3266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TaxiInfo f3267c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3268d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<MassTransitRouteLine> f3269e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SuggestAddrInfo f3270f;

    public MassTransitRouteResult() {
    }

    MassTransitRouteResult(Parcel parcel) {
        this.f3265a = (TransitResultNode) parcel.readParcelable(TransitResultNode.class.getClassLoader());
        this.f3266b = (TransitResultNode) parcel.readParcelable(TransitResultNode.class.getClassLoader());
        this.f3267c = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.f3268d = parcel.readInt();
        this.f3269e = new ArrayList();
        parcel.readList(this.f3269e, MassTransitRouteLine.class.getClassLoader());
        this.f3270f = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransitResultNode getDestination() {
        return this.f3266b;
    }

    public TransitResultNode getOrigin() {
        return this.f3265a;
    }

    public List<MassTransitRouteLine> getRouteLines() {
        return this.f3269e;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f3270f;
    }

    public TaxiInfo getTaxiInfo() {
        return this.f3267c;
    }

    public int getTotal() {
        return this.f3268d;
    }

    public void setDestination(TransitResultNode transitResultNode) {
        this.f3266b = transitResultNode;
    }

    public void setOrigin(TransitResultNode transitResultNode) {
        this.f3265a = transitResultNode;
    }

    public void setRoutelines(List<MassTransitRouteLine> list) {
        this.f3269e = list;
    }

    public void setSuggestAddrInfo(SuggestAddrInfo suggestAddrInfo) {
        this.f3270f = suggestAddrInfo;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.f3267c = taxiInfo;
    }

    public void setTotal(int i) {
        this.f3268d = i;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3265a, 1);
        parcel.writeParcelable(this.f3266b, 1);
        parcel.writeParcelable(this.f3267c, 1);
        parcel.writeInt(this.f3268d);
        parcel.writeList(this.f3269e);
        parcel.writeParcelable(this.f3270f, 1);
    }
}