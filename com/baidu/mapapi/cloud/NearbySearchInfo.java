package com.baidu.mapapi.cloud;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes.dex */
public class NearbySearchInfo extends BaseCloudSearchInfo {
    public String location;
    public int radius;

    public NearbySearchInfo() {
        this.f2720a = HttpClient.isHttpsEnable ? "https://api.map.baidu.com/geosearch/v2/nearby" : "http://api.map.baidu.com/geosearch/v2/nearby";
        this.radius = 1000;
    }

    @Override // com.baidu.mapapi.cloud.BaseCloudSearchInfo, com.baidu.mapapi.cloud.BaseSearchInfo
    String a() {
        StringBuilder sb = new StringBuilder();
        if (super.a() != null) {
            sb.append(super.a());
            String str = this.location;
            if (str != null && !str.equals("")) {
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    String[] strArrSplit = this.location.split(AppInfo.DELIM);
                    try {
                        LatLng latLngGcjToBaidu = CoordTrans.gcjToBaidu(new LatLng(Double.parseDouble(strArrSplit[1]), Double.parseDouble(strArrSplit[0])));
                        this.location = latLngGcjToBaidu.longitude + AppInfo.DELIM + latLngGcjToBaidu.latitude;
                    } catch (Exception unused) {
                    }
                }
                sb.append("&");
                sb.append(FirebaseAnalytics.Param.LOCATION);
                sb.append("=");
                sb.append(this.location);
                if (this.radius >= 0) {
                    sb.append("&");
                    sb.append("radius");
                    sb.append("=");
                    sb.append(this.radius);
                }
                return sb.toString();
            }
        }
        return null;
    }
}