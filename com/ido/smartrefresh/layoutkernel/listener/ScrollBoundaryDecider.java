package com.ido.smartrefresh.layoutkernel.listener;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public interface ScrollBoundaryDecider {
    boolean canLoadMore(View view);

    boolean canRefresh(View view);
}