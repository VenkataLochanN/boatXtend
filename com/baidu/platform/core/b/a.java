package com.baidu.platform.core.b;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.platform.base.SearchType;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.baidu.platform.base.a implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    OnGetGeoCoderResultListener f3932b = null;

    @Override // com.baidu.platform.core.b.d
    public void a() {
        this.f3890a.lock();
        this.f3932b = null;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.b.d
    public void a(OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        this.f3890a.lock();
        this.f3932b = onGetGeoCoderResultListener;
        this.f3890a.unlock();
    }

    @Override // com.baidu.platform.core.b.d
    public boolean a(GeoCodeOption geoCodeOption) {
        b bVar = new b();
        com.baidu.platform.base.e cVar = new c(geoCodeOption);
        bVar.a(SearchType.GEO_CODER);
        if (geoCodeOption != null) {
            bVar.b(geoCodeOption.getAddress());
        }
        return a(cVar, this.f3932b, bVar);
    }

    @Override // com.baidu.platform.core.b.d
    public boolean a(ReverseGeoCodeOption reverseGeoCodeOption) {
        e eVar = new e();
        f fVar = new f(reverseGeoCodeOption);
        eVar.a(SearchType.REVERSE_GEO_CODER);
        return a(fVar, this.f3932b, eVar);
    }
}