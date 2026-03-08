package com.baidu.platform.core.b;

import com.baidu.mapapi.search.geocode.GeoCodeOption;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.baidu.platform.base.e {
    public c(GeoCodeOption geoCodeOption) {
        a(geoCodeOption);
    }

    private void a(GeoCodeOption geoCodeOption) {
        this.f3903a.a("city", geoCodeOption.mCity);
        this.f3903a.a("address", geoCodeOption.mAddress);
        this.f3903a.a("output", "json");
        this.f3903a.a("ret_coordtype", "bd09ll");
        this.f3903a.a("from", "android_map_sdk");
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.f();
    }
}