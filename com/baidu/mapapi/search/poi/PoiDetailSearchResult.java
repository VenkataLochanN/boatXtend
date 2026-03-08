package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PoiDetailSearchResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<PoiDetailSearchResult> CREATOR = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<PoiDetailInfo> f3182a;

    public PoiDetailSearchResult() {
    }

    protected PoiDetailSearchResult(Parcel parcel) {
        super(parcel);
        this.f3182a = parcel.createTypedArrayList(PoiDetailInfo.CREATOR);
    }

    public PoiDetailSearchResult(SearchResult.ERRORNO errorno) {
        super(errorno);
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<PoiDetailInfo> getPoiDetailInfoList() {
        return this.f3182a;
    }

    public void setPoiDetailInfoList(List<PoiDetailInfo> list) {
        this.f3182a = list;
    }

    public String toString() {
        List<PoiDetailInfo> list = this.f3182a;
        if (list == null || list.isEmpty()) {
            return "PoiDetailSearchResult is null";
        }
        StringBuffer stringBuffer = new StringBuffer("PoiDetailSearchResult:");
        for (int i = 0; i < this.f3182a.size(); i++) {
            stringBuffer.append(" ");
            stringBuffer.append(i);
            stringBuffer.append(" ");
            PoiDetailInfo poiDetailInfo = this.f3182a.get(i);
            stringBuffer.append(poiDetailInfo != null ? poiDetailInfo.toString() : "null");
        }
        return stringBuffer.toString();
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f3182a);
    }
}