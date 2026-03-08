package com.baidu.mapapi.search.share;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.l;
import com.baidu.mapapi.search.share.RouteShareURLOption;
import com.baidu.platform.core.e.h;

/* JADX INFO: loaded from: classes.dex */
public class ShareUrlSearch extends l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3306b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    com.baidu.platform.core.e.a f3305a = new h();

    ShareUrlSearch() {
    }

    private boolean a(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static ShareUrlSearch newInstance() {
        BMapManager.init();
        return new ShareUrlSearch();
    }

    public void destroy() {
        if (this.f3306b) {
            return;
        }
        this.f3306b = true;
        this.f3305a.a();
        BMapManager.destroy();
    }

    public boolean requestLocationShareUrl(LocationShareURLOption locationShareURLOption) {
        if (this.f3305a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher has been destroyed");
        }
        if (locationShareURLOption == null || locationShareURLOption.mLocation == null || locationShareURLOption.mName == null || locationShareURLOption.mSnippet == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or name or snippet  can not be null");
        }
        return this.f3305a.a(locationShareURLOption);
    }

    public boolean requestPoiDetailShareUrl(PoiDetailShareURLOption poiDetailShareURLOption) {
        if (this.f3305a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher has been destroyed");
        }
        if (poiDetailShareURLOption == null || poiDetailShareURLOption.mUid == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or uid can not be null");
        }
        return this.f3305a.a(poiDetailShareURLOption);
    }

    public boolean requestRouteShareUrl(RouteShareURLOption routeShareURLOption) {
        if (this.f3305a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher has been destroyed");
        }
        if (routeShareURLOption == null) {
            throw new IllegalArgumentException("BDMapSDKException: option is null");
        }
        if (routeShareURLOption.getmMode().ordinal() < 0) {
            return false;
        }
        if (routeShareURLOption.mFrom == null || routeShareURLOption.mTo == null) {
            throw new IllegalArgumentException("BDMapSDKException: start or end point can not be null");
        }
        if (routeShareURLOption.mMode == RouteShareURLOption.RouteShareMode.BUS_ROUTE_SHARE_MODE) {
            if ((routeShareURLOption.mFrom.getLocation() == null || routeShareURLOption.mTo.getLocation() == null) && routeShareURLOption.mCityCode < 0) {
                throw new IllegalArgumentException("BDMapSDKException: city code can not be null if don't set start or end point");
            }
        } else {
            if (routeShareURLOption.mFrom.getLocation() == null && !a(routeShareURLOption.mFrom.getCity())) {
                throw new IllegalArgumentException("BDMapSDKException: start cityCode must be set if not set start location");
            }
            if (routeShareURLOption.mTo.getLocation() == null && !a(routeShareURLOption.mTo.getCity())) {
                throw new IllegalArgumentException("BDMapSDKException: end cityCode must be set if not set end location");
            }
        }
        return this.f3305a.a(routeShareURLOption);
    }

    public void setOnGetShareUrlResultListener(OnGetShareUrlResultListener onGetShareUrlResultListener) {
        com.baidu.platform.core.e.a aVar = this.f3305a;
        if (aVar == null) {
            throw new IllegalStateException("BDMapSDKException: searcher has been destroyed");
        }
        if (onGetShareUrlResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        aVar.a(onGetShareUrlResultListener);
    }
}