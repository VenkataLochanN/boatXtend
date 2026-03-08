package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.l;

/* JADX INFO: loaded from: classes.dex */
public class PoiSearch extends l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3208b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.baidu.platform.core.c.a f3207a = new com.baidu.platform.core.c.f();

    PoiSearch() {
    }

    public static PoiSearch newInstance() {
        BMapManager.init();
        return new PoiSearch();
    }

    public void destroy() {
        if (this.f3208b) {
            return;
        }
        this.f3208b = true;
        this.f3207a.a();
        BMapManager.destroy();
    }

    public boolean searchInBound(PoiBoundSearchOption poiBoundSearchOption) {
        if (this.f3207a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiBoundSearchOption == null || poiBoundSearchOption.mBound == null || poiBoundSearchOption.mKeyword == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or bound or keyworld can not be null");
        }
        return this.f3207a.a(poiBoundSearchOption);
    }

    public boolean searchInCity(PoiCitySearchOption poiCitySearchOption) {
        if (this.f3207a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiCitySearchOption == null || poiCitySearchOption.mCity == null || poiCitySearchOption.mKeyword == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or city or keyworld can not be null");
        }
        return this.f3207a.a(poiCitySearchOption);
    }

    public boolean searchNearby(PoiNearbySearchOption poiNearbySearchOption) {
        if (this.f3207a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiNearbySearchOption == null || poiNearbySearchOption.mLocation == null || poiNearbySearchOption.mKeyword == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or location or keyworld can not be null");
        }
        if (poiNearbySearchOption.mRadius <= 0) {
            return false;
        }
        return this.f3207a.a(poiNearbySearchOption);
    }

    public boolean searchPoiDetail(PoiDetailSearchOption poiDetailSearchOption) {
        if (this.f3207a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiDetailSearchOption == null || poiDetailSearchOption.getUid() == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or uid can not be null");
        }
        return this.f3207a.a(poiDetailSearchOption);
    }

    public boolean searchPoiIndoor(PoiIndoorOption poiIndoorOption) {
        if (this.f3207a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (poiIndoorOption == null || poiIndoorOption.bid == null || poiIndoorOption.wd == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or indoor bid or keyword can not be null");
        }
        return this.f3207a.a(poiIndoorOption);
    }

    public void setOnGetPoiSearchResultListener(OnGetPoiSearchResultListener onGetPoiSearchResultListener) {
        com.baidu.platform.core.c.a aVar = this.f3207a;
        if (aVar == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (onGetPoiSearchResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        aVar.a(onGetPoiSearchResultListener);
    }
}