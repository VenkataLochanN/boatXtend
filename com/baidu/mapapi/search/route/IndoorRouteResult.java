package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class IndoorRouteResult extends SearchResult {
    public static final Parcelable.Creator<IndoorRouteResult> CREATOR = new h();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<IndoorRouteLine> f3246a;

    public IndoorRouteResult() {
    }

    protected IndoorRouteResult(Parcel parcel) {
        super(parcel);
        this.f3246a = parcel.createTypedArrayList(IndoorRouteLine.CREATOR);
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<IndoorRouteLine> getRouteLines() {
        return this.f3246a;
    }

    public void setRouteLines(List<IndoorRouteLine> list) {
        this.f3246a = list;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f3246a);
    }
}