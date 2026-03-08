package com.baidu.platform.core.a;

import com.baidu.mapapi.search.district.DistrictSearchOption;
import com.baidu.mapapi.search.district.OnGetDistricSearchResultListener;
import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.baidu.platform.base.a implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private OnGetDistricSearchResultListener f3931b = null;

    @Override // com.baidu.platform.core.a.e
    public void a() {
        this.f3890a.lock();
        this.f3931b = null;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.a.e
    public void a(OnGetDistricSearchResultListener onGetDistricSearchResultListener) {
        this.f3890a.lock();
        this.f3931b = onGetDistricSearchResultListener;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.a.e
    public boolean a(DistrictSearchOption districtSearchOption) {
        b bVar = new b();
        bVar.a(SearchType.DISTRICT_SEARCH);
        return a(new a(districtSearchOption), this.f3931b, bVar);
    }
}