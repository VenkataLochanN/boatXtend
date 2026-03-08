package com.baidu.mapapi.cloud;

import com.baidu.mapapi.http.HttpClient;

/* JADX INFO: loaded from: classes.dex */
public class DetailSearchInfo extends BaseSearchInfo {
    public String poiId;
    public int uid;

    public DetailSearchInfo() {
        this.f2720a = HttpClient.isHttpsEnable ? "https://api.map.baidu.com/geosearch/v2/detail/" : "http://api.map.baidu.com/geosearch/v2/detail/";
    }

    @Override // com.baidu.mapapi.cloud.BaseSearchInfo
    String a() {
        String str;
        if (this.uid == 0 && ((str = this.poiId) == null || str.equals(""))) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2720a);
        String str2 = this.poiId;
        if (str2 == null || str2.equals("")) {
            sb.append(this.uid);
        } else {
            sb.append(this.poiId);
        }
        sb.append('?');
        if (this.ak != null && !this.ak.equals("") && this.ak.length() <= 50) {
            sb.append("ak");
            sb.append("=");
            sb.append(this.ak);
            if (this.geoTableId != 0) {
                sb.append("&");
                sb.append("geotable_id");
                sb.append("=");
                sb.append(this.geoTableId);
                if (this.sn != null && !this.sn.equals("") && this.sn.length() <= 50) {
                    sb.append("&");
                    sb.append("sn");
                    sb.append("=");
                    sb.append(this.sn);
                }
                return sb.toString();
            }
        }
        return null;
    }
}