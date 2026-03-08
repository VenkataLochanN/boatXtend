package com.baidu.platform.core.e;

import com.baidu.mapapi.search.share.LocationShareURLOption;
import com.baidu.mapapi.search.share.OnGetShareUrlResultListener;
import com.baidu.mapapi.search.share.PoiDetailShareURLOption;
import com.baidu.mapapi.search.share.RouteShareURLOption;
import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
public class h extends com.baidu.platform.base.a implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    OnGetShareUrlResultListener f3948b = null;

    @Override // com.baidu.platform.core.e.a
    public void a() {
        this.f3890a.lock();
        this.f3948b = null;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.e.a
    public void a(OnGetShareUrlResultListener onGetShareUrlResultListener) {
        this.f3890a.lock();
        this.f3948b = onGetShareUrlResultListener;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.e.a
    public boolean a(LocationShareURLOption locationShareURLOption) {
        f fVar = new f();
        fVar.a(SearchType.LOCATION_SEARCH_SHARE);
        return a(new b(locationShareURLOption), this.f3948b, fVar);
    }

    @Override // com.baidu.platform.core.e.a
    public boolean a(PoiDetailShareURLOption poiDetailShareURLOption) {
        f fVar = new f();
        fVar.a(SearchType.POI_DETAIL_SHARE);
        return a(new c(poiDetailShareURLOption), this.f3948b, fVar);
    }

    @Override // com.baidu.platform.core.e.a
    public boolean a(RouteShareURLOption routeShareURLOption) {
        d dVar = new d();
        dVar.a(SearchType.ROUTE_PLAN_SHARE);
        return a(new e(routeShareURLOption), this.f3948b, dVar);
    }
}