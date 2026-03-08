package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.l;

/* JADX INFO: loaded from: classes.dex */
public class GeoCoder extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.baidu.platform.core.b.d f3164a = new com.baidu.platform.core.b.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3165b;

    private GeoCoder() {
    }

    public static GeoCoder newInstance() {
        BMapManager.init();
        return new GeoCoder();
    }

    public void destroy() {
        if (this.f3165b) {
            return;
        }
        this.f3165b = true;
        this.f3164a.a();
        BMapManager.destroy();
    }

    public boolean geocode(GeoCodeOption geoCodeOption) {
        if (this.f3164a == null) {
            throw new IllegalStateException("BDMapSDKException: GeoCoder is null, please call newInstance() first.");
        }
        if (geoCodeOption == null || geoCodeOption.mAddress == null || geoCodeOption.mCity == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or address or city can not be null");
        }
        return this.f3164a.a(geoCodeOption);
    }

    public boolean reverseGeoCode(ReverseGeoCodeOption reverseGeoCodeOption) {
        if (this.f3164a == null) {
            throw new IllegalStateException("BDMapSDKException: GeoCoder is null, please call newInstance() first.");
        }
        if (reverseGeoCodeOption == null || reverseGeoCodeOption.getLocation() == null) {
            throw new IllegalArgumentException("BDMapSDKException: option or mLocation can not be null");
        }
        return this.f3164a.a(reverseGeoCodeOption);
    }

    public void setOnGetGeoCodeResultListener(OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        com.baidu.platform.core.b.d dVar = this.f3164a;
        if (dVar == null) {
            throw new IllegalStateException("BDMapSDKException: GeoCoder is null, please call newInstance() first.");
        }
        if (onGetGeoCoderResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        dVar.a(onGetGeoCoderResultListener);
    }
}