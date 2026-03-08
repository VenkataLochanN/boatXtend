package com.ido.smartrefresh.layoutkernel.listener;

import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;

/* JADX INFO: loaded from: classes3.dex */
public interface OnStateChangedListener {
    void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2);
}