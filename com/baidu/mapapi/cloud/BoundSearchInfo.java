package com.baidu.mapapi.cloud;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;

/* JADX INFO: loaded from: classes.dex */
public class BoundSearchInfo extends BaseCloudSearchInfo {
    public String bound;

    public BoundSearchInfo() {
        this.f2720a = HttpClient.isHttpsEnable ? "https://api.map.baidu.com/geosearch/v2/bound" : "http://api.map.baidu.com/geosearch/v2/bound";
    }

    @Override // com.baidu.mapapi.cloud.BaseCloudSearchInfo, com.baidu.mapapi.cloud.BaseSearchInfo
    String a() {
        StringBuilder sb = new StringBuilder();
        if (super.a() != null) {
            sb.append(super.a());
            String str = this.bound;
            if (str != null && !str.equals("")) {
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    try {
                        String[] strArrSplit = this.bound.split(";");
                        String[] strArrSplit2 = strArrSplit[0].split(AppInfo.DELIM);
                        String[] strArrSplit3 = strArrSplit[1].split(AppInfo.DELIM);
                        LatLng latLng = new LatLng(Double.parseDouble(strArrSplit2[1]), Double.parseDouble(strArrSplit2[0]));
                        LatLng latLng2 = new LatLng(Double.parseDouble(strArrSplit3[1]), Double.parseDouble(strArrSplit3[0]));
                        LatLng latLngGcjToBaidu = CoordTrans.gcjToBaidu(latLng);
                        LatLng latLngGcjToBaidu2 = CoordTrans.gcjToBaidu(latLng2);
                        this.bound = latLngGcjToBaidu.longitude + AppInfo.DELIM + latLngGcjToBaidu.latitude + ";" + latLngGcjToBaidu2.longitude + AppInfo.DELIM + latLngGcjToBaidu2.latitude;
                    } catch (Exception unused) {
                    }
                }
                sb.append("&");
                sb.append("bounds");
                sb.append("=");
                sb.append(this.bound);
                return sb.toString();
            }
        }
        return null;
    }
}