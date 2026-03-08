package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PoiResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<PoiResult> CREATOR = new f();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3201c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<PoiInfo> f3203e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3204f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<PoiAddrInfo> f3205g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<CityInfo> f3206h;

    public PoiResult() {
        this.f3199a = 0;
        this.f3200b = 0;
        this.f3201c = 0;
        this.f3202d = 0;
        this.f3204f = false;
    }

    protected PoiResult(Parcel parcel) {
        super(parcel);
        this.f3199a = 0;
        this.f3200b = 0;
        this.f3201c = 0;
        this.f3202d = 0;
        this.f3204f = false;
        this.f3199a = parcel.readInt();
        this.f3200b = parcel.readInt();
        this.f3201c = parcel.readInt();
        this.f3202d = parcel.readInt();
        this.f3203e = parcel.createTypedArrayList(PoiInfo.CREATOR);
        this.f3204f = parcel.readByte() != 0;
        this.f3206h = parcel.createTypedArrayList(CityInfo.CREATOR);
    }

    public PoiResult(SearchResult.ERRORNO errorno) {
        super(errorno);
        this.f3199a = 0;
        this.f3200b = 0;
        this.f3201c = 0;
        this.f3202d = 0;
        this.f3204f = false;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<PoiAddrInfo> getAllAddr() {
        return this.f3205g;
    }

    public List<PoiInfo> getAllPoi() {
        return this.f3203e;
    }

    public int getCurrentPageCapacity() {
        return this.f3201c;
    }

    public int getCurrentPageNum() {
        return this.f3199a;
    }

    public List<CityInfo> getSuggestCityList() {
        return this.f3206h;
    }

    public int getTotalPageNum() {
        return this.f3200b;
    }

    public int getTotalPoiNum() {
        return this.f3202d;
    }

    public boolean isHasAddrInfo() {
        return this.f3204f;
    }

    public void setAddrInfo(List<PoiAddrInfo> list) {
        this.f3205g = list;
    }

    public void setCurrentPageCapacity(int i) {
        this.f3201c = i;
    }

    public void setCurrentPageNum(int i) {
        this.f3199a = i;
    }

    public void setHasAddrInfo(boolean z) {
        this.f3204f = z;
    }

    public void setPoiInfo(List<PoiInfo> list) {
        this.f3203e = list;
    }

    public void setSuggestCityList(List<CityInfo> list) {
        this.f3206h = list;
    }

    public void setTotalPageNum(int i) {
        this.f3200b = i;
    }

    public void setTotalPoiNum(int i) {
        this.f3202d = i;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f3199a);
        parcel.writeInt(this.f3200b);
        parcel.writeInt(this.f3201c);
        parcel.writeInt(this.f3202d);
        parcel.writeTypedList(this.f3203e);
        parcel.writeByte(this.f3204f ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.f3206h);
    }
}