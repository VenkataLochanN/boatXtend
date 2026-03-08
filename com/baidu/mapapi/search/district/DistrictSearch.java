package com.baidu.mapapi.search.district;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.l;
import com.baidu.platform.core.a.d;
import com.baidu.platform.core.a.e;

/* JADX INFO: loaded from: classes.dex */
public class DistrictSearch extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private e f3157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3158b = false;

    DistrictSearch() {
        this.f3157a = null;
        this.f3157a = new d();
    }

    public static DistrictSearch newInstance() {
        BMapManager.init();
        return new DistrictSearch();
    }

    public void destroy() {
        if (this.f3158b) {
            return;
        }
        this.f3158b = true;
        this.f3157a.a();
        BMapManager.destroy();
    }

    public boolean searchDistrict(DistrictSearchOption districtSearchOption) {
        if (this.f3157a == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (districtSearchOption == null || districtSearchOption.mCityName == null || districtSearchOption.mCityName.equals("")) {
            throw new IllegalArgumentException("BDMapSDKException: option or city name can not be null or empty.");
        }
        return this.f3157a.a(districtSearchOption);
    }

    public void setOnDistrictSearchListener(OnGetDistricSearchResultListener onGetDistricSearchResultListener) {
        e eVar = this.f3157a;
        if (eVar == null) {
            throw new IllegalStateException("BDMapSDKException: searcher is null, please call newInstance first.");
        }
        if (onGetDistricSearchResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        eVar.a(onGetDistricSearchResultListener);
    }
}