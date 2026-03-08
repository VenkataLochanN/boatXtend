package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapsdkplatform.comapi.map.ab;

/* JADX INFO: loaded from: classes.dex */
class u implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ TextureMapView f3069a;

    u(TextureMapView textureMapView) {
        this.f3069a = textureMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        float f2 = this.f3069a.f2992b.b().f3562b;
        ab abVarE = this.f3069a.f2992b.b().E();
        abVarE.f3518a -= 1.0f;
        if (abVarE.f3518a >= f2) {
            f2 = abVarE.f3518a;
        }
        abVarE.f3518a = f2;
        BaiduMap.mapStatusReason |= 16;
        this.f3069a.f2992b.b().a(abVarE, 300);
    }
}