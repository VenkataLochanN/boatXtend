package com.baidu.platform.core.c;

import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
public class f extends com.baidu.platform.base.a implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private OnGetPoiSearchResultListener f3938b = null;

    @Override // com.baidu.platform.core.c.a
    public void a() {
        this.f3890a.lock();
        this.f3938b = null;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.c.a
    public void a(OnGetPoiSearchResultListener onGetPoiSearchResultListener) {
        this.f3890a.lock();
        this.f3938b = onGetPoiSearchResultListener;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.c.a
    public boolean a(PoiBoundSearchOption poiBoundSearchOption) {
        g gVar = new g(poiBoundSearchOption.mPageNum, poiBoundSearchOption.mPageCapacity);
        gVar.a(SearchType.POI_IN_BOUND_SEARCH);
        return a(new i(poiBoundSearchOption), this.f3938b, gVar);
    }

    @Override // com.baidu.platform.core.c.a
    public boolean a(PoiCitySearchOption poiCitySearchOption) {
        g gVar = new g(poiCitySearchOption.mPageNum, poiCitySearchOption.mPageCapacity);
        gVar.a(SearchType.POI_IN_CITY_SEARCH);
        return a(new i(poiCitySearchOption), this.f3938b, gVar);
    }

    @Override // com.baidu.platform.core.c.a
    public boolean a(PoiDetailSearchOption poiDetailSearchOption) {
        d dVar = new d();
        if (poiDetailSearchOption != null) {
            dVar.a(poiDetailSearchOption.isSearchByUids());
        }
        dVar.a(SearchType.POI_DETAIL_SEARCH);
        return a(new e(poiDetailSearchOption), this.f3938b, dVar);
    }

    @Override // com.baidu.platform.core.c.a
    public boolean a(PoiIndoorOption poiIndoorOption) {
        b bVar = new b();
        bVar.a(SearchType.INDOOR_POI_SEARCH);
        return a(new c(poiIndoorOption), this.f3938b, bVar);
    }

    @Override // com.baidu.platform.core.c.a
    public boolean a(PoiNearbySearchOption poiNearbySearchOption) {
        g gVar = new g(poiNearbySearchOption.mPageNum, poiNearbySearchOption.mPageCapacity);
        gVar.a(SearchType.POI_NEAR_BY_SEARCH);
        return a(new i(poiNearbySearchOption), this.f3938b, gVar);
    }
}