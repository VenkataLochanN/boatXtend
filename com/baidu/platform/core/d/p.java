package com.baidu.platform.core.d;

import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.ido.life.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
public class p extends com.baidu.platform.base.e {
    public p(WalkingRoutePlanOption walkingRoutePlanOption) {
        a(walkingRoutePlanOption);
    }

    private void a(WalkingRoutePlanOption walkingRoutePlanOption) {
        this.f3903a.a("qt", "walk2");
        this.f3903a.a("sn", a(walkingRoutePlanOption.mFrom));
        this.f3903a.a("en", a(walkingRoutePlanOption.mTo));
        if (walkingRoutePlanOption.mFrom != null) {
            this.f3903a.a("sc", walkingRoutePlanOption.mFrom.getCity());
        }
        if (walkingRoutePlanOption.mTo != null) {
            this.f3903a.a("ec", walkingRoutePlanOption.mTo.getCity());
        }
        this.f3903a.a("ie", "utf-8");
        this.f3903a.a("lrn", "20");
        this.f3903a.a("version", Constants.DIALDEFNED_VERSION_CONNECT);
        this.f3903a.a("rp_format", "json");
        this.f3903a.a("rp_filter", "mobile");
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.k();
    }
}