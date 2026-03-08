package com.ido.smartrefresh.layoutkernel.api;

import android.view.View;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;
import com.ido.smartrefresh.layoutkernel.listener.OnStateChangedListener;

/* JADX INFO: loaded from: classes3.dex */
public interface RefreshComponent extends OnStateChangedListener {
    SpinnerStyle getSpinnerStyle();

    View getView();

    boolean isSupportHorizontalDrag();

    int onFinish(RefreshLayout refreshLayout, boolean z);

    void onHorizontalDrag(float f2, int i, int i2);

    void onInitialized(RefreshKernel refreshKernel, int i, int i2);

    void onMoving(boolean z, float f2, int i, int i2, int i3);

    void onReleased(RefreshLayout refreshLayout, int i, int i2);

    void onStartAnimator(RefreshLayout refreshLayout, int i, int i2);

    void setPrimaryColors(int... iArr);
}