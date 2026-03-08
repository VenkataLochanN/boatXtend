package com.baidu.platform.core.f;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.e;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.env.LanguagePreference;
import com.tencent.bugly.Bugly;

/* JADX INFO: loaded from: classes.dex */
public class d extends e {
    public d(SuggestionSearchOption suggestionSearchOption) {
        a(suggestionSearchOption);
    }

    private void a(SuggestionSearchOption suggestionSearchOption) {
        com.baidu.platform.util.a aVar;
        String str;
        this.f3903a.a("q", suggestionSearchOption.mKeyword);
        this.f3903a.a(LanguagePreference.Language_Region, suggestionSearchOption.mCity);
        if (suggestionSearchOption.mLocation != null) {
            LatLng latLng = new LatLng(suggestionSearchOption.mLocation.latitude, suggestionSearchOption.mLocation.longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            this.f3903a.a(FirebaseAnalytics.Param.LOCATION, latLng.latitude + AppInfo.DELIM + latLng.longitude);
        }
        if (suggestionSearchOption.mCityLimit.booleanValue()) {
            aVar = this.f3903a;
            str = "true";
        } else {
            aVar = this.f3903a;
            str = Bugly.SDK_IS_DEV;
        }
        aVar.a("city_limit", str);
        this.f3903a.a("from", "android_map_sdk");
        this.f3903a.a("output", "json");
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.d();
    }
}