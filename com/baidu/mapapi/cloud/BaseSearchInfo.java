package com.baidu.mapapi.cloud;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSearchInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2720a;
    public String ak;
    public int geoTableId;
    public String sn;

    String a() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2720a);
        sb.append("?");
        String str = this.ak;
        if (str != null && !str.equals("") && this.ak.length() <= 50) {
            sb.append("ak");
            sb.append("=");
            sb.append(this.ak);
            if (this.geoTableId != 0) {
                sb.append("&");
                sb.append("geotable_id");
                sb.append("=");
                sb.append(this.geoTableId);
                String str2 = this.sn;
                if (str2 != null && !str2.equals("") && this.sn.length() <= 50) {
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