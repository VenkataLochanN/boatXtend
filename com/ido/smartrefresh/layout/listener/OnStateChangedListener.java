package com.ido.smartrefresh.layout.listener;

import com.ido.smartrefresh.layout.api.RefreshLayout;
import com.ido.smartrefresh.layout.constant.RefreshState;

/* JADX INFO: loaded from: classes3.dex */
public interface OnStateChangedListener {
    void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2);
}