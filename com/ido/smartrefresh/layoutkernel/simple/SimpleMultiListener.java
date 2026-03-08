package com.ido.smartrefresh.layoutkernel.simple;

import com.ido.smartrefresh.layoutkernel.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.listener.OnMultiListener;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleMultiListener implements OnMultiListener {
    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onFooterFinish(RefreshFooter refreshFooter, boolean z) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onFooterMoving(RefreshFooter refreshFooter, boolean z, float f2, int i, int i2, int i3) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onFooterReleased(RefreshFooter refreshFooter, int i, int i2) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onFooterStartAnimator(RefreshFooter refreshFooter, int i, int i2) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onHeaderFinish(RefreshHeader refreshHeader, boolean z) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onHeaderMoving(RefreshHeader refreshHeader, boolean z, float f2, int i, int i2, int i3) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onHeaderReleased(RefreshHeader refreshHeader, int i, int i2) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onHeaderStartAnimator(RefreshHeader refreshHeader, int i, int i2) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnLoadMoreListener
    public void onLoadMore(RefreshLayout refreshLayout) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnRefreshListener
    public void onRefresh(RefreshLayout refreshLayout) {
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
    }
}