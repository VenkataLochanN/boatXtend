package com.baidu.platform.core.d;

import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.PlanNode;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.baidu.platform.base.e {
    d(DrivingRoutePlanOption drivingRoutePlanOption) {
        a(drivingRoutePlanOption);
    }

    private void a(DrivingRoutePlanOption drivingRoutePlanOption) {
        this.f3903a.a("qt", "cars");
        this.f3903a.a("sy", drivingRoutePlanOption.mPolicy.getInt() + "");
        this.f3903a.a("ie", "utf-8");
        this.f3903a.a("lrn", "20");
        this.f3903a.a("version", "6");
        this.f3903a.a("extinfo", "32");
        this.f3903a.a("mrs", "1");
        this.f3903a.a("rp_format", "json");
        this.f3903a.a("rp_filter", "mobile");
        this.f3903a.a("route_traffic", drivingRoutePlanOption.mtrafficPolicy.getInt() + "");
        this.f3903a.a("sn", a(drivingRoutePlanOption.mFrom));
        this.f3903a.a("en", a(drivingRoutePlanOption.mTo));
        if (drivingRoutePlanOption.mCityName != null) {
            this.f3903a.a("c", drivingRoutePlanOption.mCityName);
        }
        if (drivingRoutePlanOption.mFrom != null) {
            this.f3903a.a("sc", drivingRoutePlanOption.mFrom.getCity());
        }
        if (drivingRoutePlanOption.mTo != null) {
            this.f3903a.a("ec", drivingRoutePlanOption.mTo.getCity());
        }
        List<PlanNode> list = drivingRoutePlanOption.mWayPoints;
        String str = new String();
        String str2 = new String();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                PlanNode planNode = list.get(i);
                if (planNode != null) {
                    str = str + a(planNode);
                    str2 = str2 + planNode.getCity();
                    if (i != list.size() - 1) {
                        str2 = str2 + "|";
                        str = str + "|";
                    }
                }
            }
            this.f3903a.a("wp", str);
            this.f3903a.a("wpc", str2);
        }
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.i();
    }
}