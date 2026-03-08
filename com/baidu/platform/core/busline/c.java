package com.baidu.platform.core.busline;

import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.baidu.platform.base.a implements IBusLineSearch {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    OnGetBusLineSearchResultListener f3935b = null;

    @Override // com.baidu.platform.core.busline.IBusLineSearch
    public void a() {
        this.f3890a.lock();
        this.f3935b = null;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.busline.IBusLineSearch
    public void a(OnGetBusLineSearchResultListener onGetBusLineSearchResultListener) {
        this.f3890a.lock();
        this.f3935b = onGetBusLineSearchResultListener;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.busline.IBusLineSearch
    public boolean a(BusLineSearchOption busLineSearchOption) {
        a aVar = new a();
        aVar.a(SearchType.BUS_LINE_DETAIL);
        return a(new b(busLineSearchOption), this.f3935b, aVar);
    }
}