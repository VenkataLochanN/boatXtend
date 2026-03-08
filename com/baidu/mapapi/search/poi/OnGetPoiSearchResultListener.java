package com.baidu.mapapi.search.poi;

/* JADX INFO: loaded from: classes.dex */
public interface OnGetPoiSearchResultListener {
    void onGetPoiDetailResult(PoiDetailResult poiDetailResult);

    void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult);

    void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult);

    void onGetPoiResult(PoiResult poiResult);
}