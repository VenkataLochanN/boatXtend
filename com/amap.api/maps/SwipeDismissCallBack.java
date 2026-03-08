package com.amap.api.maps;

import android.view.View;
import com.amap.api.maps.SwipeDismissTouchListener;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissCallBack implements SwipeDismissTouchListener.DismissCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    SwipeDismissView f1841a;

    @Override // com.amap.api.maps.SwipeDismissTouchListener.DismissCallbacks
    public boolean canDismiss(Object obj) {
        return true;
    }

    public SwipeDismissCallBack(SwipeDismissView swipeDismissView) {
        this.f1841a = swipeDismissView;
    }

    @Override // com.amap.api.maps.SwipeDismissTouchListener.DismissCallbacks
    public void onDismiss(View view, Object obj) {
        if (this.f1841a.onDismissCallback != null) {
            this.f1841a.onDismissCallback.onDismiss();
        }
    }

    @Override // com.amap.api.maps.SwipeDismissTouchListener.DismissCallbacks
    public void onNotifySwipe() {
        if (this.f1841a.onDismissCallback != null) {
            this.f1841a.onDismissCallback.onNotifySwipe();
        }
    }
}