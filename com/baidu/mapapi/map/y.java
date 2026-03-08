package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapsdkplatform.comapi.map.ab;

/* JADX INFO: loaded from: classes.dex */
class y implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ WearMapView f3077a;

    y(WearMapView wearMapView) {
        this.f3077a = wearMapView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ab abVarE = this.f3077a.f3023f.a().E();
        abVarE.f3518a -= 1.0f;
        this.f3077a.f3023f.a().a(abVarE, 300);
    }
}