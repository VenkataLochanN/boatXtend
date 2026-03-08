package com.baidu.platform.core.d;

import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.ido.life.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
public class n extends com.baidu.platform.base.e {
    public n(TransitRoutePlanOption transitRoutePlanOption) {
        a(transitRoutePlanOption);
    }

    private void a(TransitRoutePlanOption transitRoutePlanOption) {
        this.f3903a.a("qt", "bus");
        this.f3903a.a("sy", transitRoutePlanOption.mPolicy.getInt() + "");
        this.f3903a.a("ie", "utf-8");
        this.f3903a.a("lrn", "20");
        this.f3903a.a("version", Constants.DIALDEFNED_VERSION_CONNECT);
        this.f3903a.a("rp_format", "json");
        this.f3903a.a("rp_filter", "mobile");
        this.f3903a.a("ic_info", "2");
        this.f3903a.a("exptype", "depall");
        this.f3903a.a("sn", a(transitRoutePlanOption.mFrom));
        this.f3903a.a("en", a(transitRoutePlanOption.mTo));
        if (transitRoutePlanOption.mCityName != null) {
            this.f3903a.a("c", transitRoutePlanOption.mCityName);
        }
        if (TransitRoutePlanOption.TransitPolicy.EBUS_NO_SUBWAY == transitRoutePlanOption.mPolicy) {
            this.f3903a.a("f", "[0,2,4,7,5,8,9,10,11]");
        }
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.h();
    }
}