package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapsdkplatform.comapi.map.ab;

/* JADX INFO: loaded from: classes.dex */
class z implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ WearMapView f3078a;

    z(WearMapView wearMapView) {
        this.f3078a = wearMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ab abVarE = this.f3078a.f3023f.a().E();
        abVarE.f3518a += 1.0f;
        this.f3078a.f3023f.a().a(abVarE, 300);
    }
}