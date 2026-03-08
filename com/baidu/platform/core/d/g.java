package com.baidu.platform.core.d;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.route.IndoorRoutePlanOption;

/* JADX INFO: loaded from: classes.dex */
public class g extends com.baidu.platform.base.e {
    g(IndoorRoutePlanOption indoorRoutePlanOption) {
        a(indoorRoutePlanOption);
    }

    private void a(IndoorRoutePlanOption indoorRoutePlanOption) {
        this.f3903a.a("qt", "indoornavi");
        this.f3903a.a("rp_format", "json");
        this.f3903a.a("version", "1");
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(indoorRoutePlanOption.mFrom.getLocation());
        if (geoPointLl2mc != null) {
            this.f3903a.a("sn", (String.format("%f,%f", Double.valueOf(geoPointLl2mc.getLongitudeE6()), Double.valueOf(geoPointLl2mc.getLatitudeE6())) + "|" + indoorRoutePlanOption.mFrom.getFloor()).replaceAll(" ", ""));
        }
        GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(indoorRoutePlanOption.mTo.getLocation());
        if (geoPointLl2mc2 != null) {
            this.f3903a.a("en", (String.format("%f,%f", Double.valueOf(geoPointLl2mc2.getLongitudeE6()), Double.valueOf(geoPointLl2mc2.getLatitudeE6())) + "|" + indoorRoutePlanOption.mTo.getFloor()).replaceAll(" ", ""));
        }
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.l();
    }
}