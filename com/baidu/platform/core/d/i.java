package com.baidu.platform.core.d;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes.dex */
public class i extends com.baidu.platform.base.e {
    public i(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        a(massTransitRoutePlanOption);
    }

    private void a(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        LatLng location = massTransitRoutePlanOption.mFrom.getLocation();
        if (location != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location = CoordTrans.gcjToBaidu(location);
            }
            this.f3903a.a("origin", location.latitude + AppInfo.DELIM + location.longitude);
        } else {
            this.f3903a.a("origin", massTransitRoutePlanOption.mFrom.getName());
        }
        if (massTransitRoutePlanOption.mFrom.getCity() != null) {
            this.f3903a.a("origin_region", massTransitRoutePlanOption.mFrom.getCity());
        }
        LatLng location2 = massTransitRoutePlanOption.mTo.getLocation();
        if (location2 != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location2 = CoordTrans.gcjToBaidu(location2);
            }
            this.f3903a.a(FirebaseAnalytics.Param.DESTINATION, location2.latitude + AppInfo.DELIM + location2.longitude);
        } else {
            this.f3903a.a(FirebaseAnalytics.Param.DESTINATION, massTransitRoutePlanOption.mTo.getName());
        }
        if (massTransitRoutePlanOption.mTo.getCity() != null) {
            this.f3903a.a("destination_region", massTransitRoutePlanOption.mTo.getCity());
        }
        this.f3903a.a("tactics_incity", massTransitRoutePlanOption.mTacticsIncity.getInt() + "");
        this.f3903a.a("tactics_intercity", massTransitRoutePlanOption.mTacticsIntercity.getInt() + "");
        this.f3903a.a("trans_type_intercity", massTransitRoutePlanOption.mTransTypeIntercity.getInt() + "");
        this.f3903a.a("page_index", massTransitRoutePlanOption.mPageIndex + "");
        this.f3903a.a("page_size", massTransitRoutePlanOption.mPageSize + "");
        this.f3903a.a("coord_type", massTransitRoutePlanOption.mCoordType);
        this.f3903a.a("output", "json");
        this.f3903a.a("from", "android_map_sdk");
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.g();
    }
}