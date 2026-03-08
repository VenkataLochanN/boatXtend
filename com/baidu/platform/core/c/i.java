package com.baidu.platform.core.c;

import android.text.TextUtils;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.env.LanguagePreference;
import com.tencent.bugly.Bugly;

/* JADX INFO: loaded from: classes.dex */
public class i extends com.baidu.platform.base.e {
    i(PoiBoundSearchOption poiBoundSearchOption) {
        a(poiBoundSearchOption);
    }

    i(PoiCitySearchOption poiCitySearchOption) {
        a(poiCitySearchOption);
    }

    i(PoiNearbySearchOption poiNearbySearchOption) {
        a(poiNearbySearchOption);
    }

    private void a(PoiBoundSearchOption poiBoundSearchOption) {
        this.f3903a.a(SearchIntents.EXTRA_QUERY, poiBoundSearchOption.mKeyword);
        this.f3903a.a("tag", poiBoundSearchOption.mTag);
        this.f3903a.a("bounds", poiBoundSearchOption.mBound.southwest.latitude + AppInfo.DELIM + poiBoundSearchOption.mBound.southwest.longitude + AppInfo.DELIM + poiBoundSearchOption.mBound.northeast.latitude + AppInfo.DELIM + poiBoundSearchOption.mBound.northeast.longitude);
        this.f3903a.a("output", "json");
        com.baidu.platform.util.a aVar = this.f3903a;
        StringBuilder sb = new StringBuilder();
        sb.append(poiBoundSearchOption.mScope);
        sb.append("");
        aVar.a(AuthorizationResponseParser.SCOPE, sb.toString());
        this.f3903a.a("page_num", poiBoundSearchOption.mPageNum + "");
        this.f3903a.a("page_size", poiBoundSearchOption.mPageCapacity + "");
        if (poiBoundSearchOption.mScope != 2 || poiBoundSearchOption.mPoiFilter == null || TextUtils.isEmpty(poiBoundSearchOption.mPoiFilter.toString())) {
            return;
        }
        this.f3903a.a("filter", poiBoundSearchOption.mPoiFilter.toString());
    }

    private void a(PoiCitySearchOption poiCitySearchOption) {
        com.baidu.platform.util.a aVar;
        String str;
        this.f3903a.a(SearchIntents.EXTRA_QUERY, poiCitySearchOption.mKeyword);
        this.f3903a.a(LanguagePreference.Language_Region, poiCitySearchOption.mCity);
        this.f3903a.a("output", "json");
        this.f3903a.a("page_num", poiCitySearchOption.mPageNum + "");
        this.f3903a.a("page_size", poiCitySearchOption.mPageCapacity + "");
        this.f3903a.a(AuthorizationResponseParser.SCOPE, poiCitySearchOption.mScope + "");
        this.f3903a.a("tag", poiCitySearchOption.mTag);
        if (poiCitySearchOption.mIsCityLimit) {
            aVar = this.f3903a;
            str = "true";
        } else {
            aVar = this.f3903a;
            str = Bugly.SDK_IS_DEV;
        }
        aVar.a("city_limit", str);
        if (poiCitySearchOption.mScope != 2 || poiCitySearchOption.mPoiFilter == null || TextUtils.isEmpty(poiCitySearchOption.mPoiFilter.toString())) {
            return;
        }
        this.f3903a.a("filter", poiCitySearchOption.mPoiFilter.toString());
    }

    private void a(PoiNearbySearchOption poiNearbySearchOption) {
        com.baidu.platform.util.a aVar;
        String str;
        this.f3903a.a(SearchIntents.EXTRA_QUERY, poiNearbySearchOption.mKeyword);
        this.f3903a.a(FirebaseAnalytics.Param.LOCATION, poiNearbySearchOption.mLocation.latitude + AppInfo.DELIM + poiNearbySearchOption.mLocation.longitude);
        this.f3903a.a("radius", poiNearbySearchOption.mRadius + "");
        this.f3903a.a("output", "json");
        this.f3903a.a("page_num", poiNearbySearchOption.mPageNum + "");
        this.f3903a.a("page_size", poiNearbySearchOption.mPageCapacity + "");
        this.f3903a.a(AuthorizationResponseParser.SCOPE, poiNearbySearchOption.mScope + "");
        this.f3903a.a("tag", poiNearbySearchOption.mTag);
        if (poiNearbySearchOption.mRadiusLimit) {
            aVar = this.f3903a;
            str = "true";
        } else {
            aVar = this.f3903a;
            str = Bugly.SDK_IS_DEV;
        }
        aVar.a("radius_limit", str);
        if (poiNearbySearchOption.mScope != 2 || poiNearbySearchOption.mPoiFilter == null || TextUtils.isEmpty(poiNearbySearchOption.mPoiFilter.toString())) {
            return;
        }
        this.f3903a.a("filter", poiNearbySearchOption.mPoiFilter.toString());
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.a();
    }
}