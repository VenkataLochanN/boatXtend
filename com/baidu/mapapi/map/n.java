package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapsdkplatform.comapi.map.ab;

/* JADX INFO: loaded from: classes.dex */
class n implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MapView f3052a;

    n(MapView mapView) {
        this.f3052a = mapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        float f2 = this.f3052a.f2892e.a().f3561a;
        ab abVarE = this.f3052a.f2892e.a().E();
        abVarE.f3518a += 1.0f;
        if (abVarE.f3518a <= f2) {
            f2 = abVarE.f3518a;
        }
        abVarE.f3518a = f2;
        BaiduMap.mapStatusReason |= 16;
        this.f3052a.f2892e.a().a(abVarE, 300);
    }
}