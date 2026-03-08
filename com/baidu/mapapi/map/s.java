package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.map.SwipeDismissTouchListener;

/* JADX INFO: loaded from: classes.dex */
class s implements SwipeDismissTouchListener.DismissCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ SwipeDismissView f3067a;

    s(SwipeDismissView swipeDismissView) {
        this.f3067a = swipeDismissView;
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public boolean canDismiss(Object obj) {
        return true;
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public void onDismiss(View view, Object obj) {
        if (this.f3067a.f2971a == null) {
            return;
        }
        this.f3067a.f2971a.onDismiss();
    }

    @Override // com.baidu.mapapi.map.SwipeDismissTouchListener.DismissCallbacks
    public void onNotify() {
        if (this.f3067a.f2971a == null) {
            return;
        }
        this.f3067a.f2971a.onNotify();
    }
}