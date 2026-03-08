package com.baidu.mapapi.search.busline;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.l;
import com.baidu.platform.core.busline.IBusLineSearch;
import com.baidu.platform.core.busline.c;

/* JADX INFO: loaded from: classes.dex */
public class BusLineSearch extends l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3106b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    IBusLineSearch f3105a = new c();

    BusLineSearch() {
    }

    public static BusLineSearch newInstance() {
        BMapManager.init();
        return new BusLineSearch();
    }

    public void destroy() {
        if (this.f3106b) {
            return;
        }
        this.f3106b = true;
        this.f3105a.a();
        BMapManager.destroy();
    }

    public boolean searchBusLine(BusLineSearchOption busLineSearchOption) {
        if (this.f3105a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (busLineSearchOption == null || busLineSearchOption.mCity == null || busLineSearchOption.mUid == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or city or uid can not be null");
        }
        return this.f3105a.a(busLineSearchOption);
    }

    public void setOnGetBusLineSearchResultListener(OnGetBusLineSearchResultListener onGetBusLineSearchResultListener) {
        IBusLineSearch iBusLineSearch = this.f3105a;
        if (iBusLineSearch == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (onGetBusLineSearchResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        iBusLineSearch.a(onGetBusLineSearchResultListener);
    }
}