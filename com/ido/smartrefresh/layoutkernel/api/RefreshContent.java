package com.ido.smartrefresh.layoutkernel.api;

import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import com.ido.smartrefresh.layoutkernel.listener.ScrollBoundaryDecider;

/* JADX INFO: loaded from: classes3.dex */
public interface RefreshContent {
    boolean canLoadMore();

    boolean canRefresh();

    View getScrollableView();

    View getView();

    void moveSpinner(int i, int i2, int i3);

    void onActionDown(MotionEvent motionEvent);

    ValueAnimator.AnimatorUpdateListener scrollContentWhenFinished(int i);

    void setEnableLoadMoreWhenContentNotFull(boolean z);

    void setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider);

    void setUpComponent(RefreshKernel refreshKernel, View view, View view2);
}