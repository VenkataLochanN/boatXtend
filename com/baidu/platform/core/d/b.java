package com.baidu.platform.core.d;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.module.device.activity.HeartRateMonitoringActivity;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.baidu.platform.base.e {
    public b(BikingRoutePlanOption bikingRoutePlanOption) {
        a(bikingRoutePlanOption);
    }

    private void a(BikingRoutePlanOption bikingRoutePlanOption) {
        this.f3903a.a(HeartRateMonitoringActivity.MODE, "riding");
        LatLng location = bikingRoutePlanOption.mFrom.getLocation();
        if (location != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location = CoordTrans.gcjToBaidu(location);
            }
            this.f3903a.a("origin", location.latitude + AppInfo.DELIM + location.longitude);
        } else {
            this.f3903a.a("origin", bikingRoutePlanOption.mFrom.getName());
        }
        LatLng location2 = bikingRoutePlanOption.mTo.getLocation();
        if (location2 != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location2 = CoordTrans.gcjToBaidu(location2);
            }
            this.f3903a.a(FirebaseAnalytics.Param.DESTINATION, location2.latitude + AppInfo.DELIM + location2.longitude);
        } else {
            this.f3903a.a(FirebaseAnalytics.Param.DESTINATION, bikingRoutePlanOption.mTo.getName());
        }
        this.f3903a.a("origin_region", bikingRoutePlanOption.mFrom.getCity());
        this.f3903a.a("destination_region", bikingRoutePlanOption.mTo.getCity());
        if (bikingRoutePlanOption.mRidingType == 1) {
            this.f3903a.a("riding_type", String.valueOf(bikingRoutePlanOption.mRidingType));
        }
        this.f3903a.a("output", "json");
        this.f3903a.a("from", "android_map_sdk");
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.j();
    }
}