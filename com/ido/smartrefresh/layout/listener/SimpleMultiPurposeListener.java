package com.ido.smartrefresh.layout.listener;

import com.ido.smartrefresh.layout.api.RefreshFooter;
import com.ido.smartrefresh.layout.api.RefreshHeader;
import com.ido.smartrefresh.layout.api.RefreshLayout;
import com.ido.smartrefresh.layout.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.listener.OnMultiListener;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleMultiPurposeListener implements OnMultiPurposeListener, OnMultiListener {
    private OnMultiPurposeListener listener;
    private RefreshLayout refreshLayout;

    public SimpleMultiPurposeListener() {
    }

    public SimpleMultiPurposeListener(OnMultiPurposeListener onMultiPurposeListener, RefreshLayout refreshLayout) {
        this.listener = onMultiPurposeListener;
        this.refreshLayout = refreshLayout;
    }

    @Override // com.ido.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onHeaderMoving(RefreshHeader refreshHeader, boolean z, float f2, int i, int i2, int i3) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onHeaderMoving(refreshHeader, z, f2, i, i2, i3);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onHeaderReleased(RefreshHeader refreshHeader, int i, int i2) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onHeaderReleased(refreshHeader, i, i2);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onHeaderStartAnimator(RefreshHeader refreshHeader, int i, int i2) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onHeaderStartAnimator(refreshHeader, i, i2);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onHeaderFinish(RefreshHeader refreshHeader, boolean z) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onHeaderFinish(refreshHeader, z);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onFooterMoving(RefreshFooter refreshFooter, boolean z, float f2, int i, int i2, int i3) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onFooterMoving(refreshFooter, z, f2, i, i2, i3);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onFooterReleased(RefreshFooter refreshFooter, int i, int i2) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onFooterReleased(refreshFooter, i, i2);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onFooterStartAnimator(RefreshFooter refreshFooter, int i, int i2) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onFooterStartAnimator(refreshFooter, i, i2);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnMultiPurposeListener
    public void onFooterFinish(RefreshFooter refreshFooter, boolean z) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onFooterFinish(refreshFooter, z);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnRefreshListener
    public void onRefresh(RefreshLayout refreshLayout) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onRefresh(refreshLayout);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnLoadMoreListener
    public void onLoadMore(RefreshLayout refreshLayout) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onLoadMore(refreshLayout);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        OnMultiPurposeListener onMultiPurposeListener = this.listener;
        if (onMultiPurposeListener != null) {
            onMultiPurposeListener.onStateChanged(refreshLayout, refreshState, refreshState2);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onHeaderMoving(com.ido.smartrefresh.layoutkernel.api.RefreshHeader refreshHeader, boolean z, float f2, int i, int i2, int i3) {
        RefreshLayout refreshLayout = this.refreshLayout;
        if (refreshLayout != null) {
            onHeaderMoving(refreshLayout.getRefreshHeader(), z, f2, i, i2, i3);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onHeaderReleased(com.ido.smartrefresh.layoutkernel.api.RefreshHeader refreshHeader, int i, int i2) {
        RefreshLayout refreshLayout = this.refreshLayout;
        if (refreshLayout != null) {
            onHeaderReleased(refreshLayout.getRefreshHeader(), i, i2);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onHeaderStartAnimator(com.ido.smartrefresh.layoutkernel.api.RefreshHeader refreshHeader, int i, int i2) {
        RefreshLayout refreshLayout = this.refreshLayout;
        if (refreshLayout != null) {
            onHeaderStartAnimator(refreshLayout.getRefreshHeader(), i, i2);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onHeaderFinish(com.ido.smartrefresh.layoutkernel.api.RefreshHeader refreshHeader, boolean z) {
        RefreshLayout refreshLayout = this.refreshLayout;
        if (refreshLayout != null) {
            onHeaderFinish(refreshLayout.getRefreshHeader(), z);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onFooterMoving(com.ido.smartrefresh.layoutkernel.api.RefreshFooter refreshFooter, boolean z, float f2, int i, int i2, int i3) {
        RefreshLayout refreshLayout = this.refreshLayout;
        if (refreshLayout != null) {
            onFooterMoving(refreshLayout.getRefreshFooter(), z, f2, i, i2, i3);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onFooterReleased(com.ido.smartrefresh.layoutkernel.api.RefreshFooter refreshFooter, int i, int i2) {
        RefreshLayout refreshLayout = this.refreshLayout;
        if (refreshLayout != null) {
            onFooterReleased(refreshLayout.getRefreshFooter(), i, i2);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onFooterStartAnimator(com.ido.smartrefresh.layoutkernel.api.RefreshFooter refreshFooter, int i, int i2) {
        RefreshLayout refreshLayout = this.refreshLayout;
        if (refreshLayout != null) {
            onFooterStartAnimator(refreshLayout.getRefreshFooter(), i, i2);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnMultiListener
    public void onFooterFinish(com.ido.smartrefresh.layoutkernel.api.RefreshFooter refreshFooter, boolean z) {
        RefreshLayout refreshLayout = this.refreshLayout;
        if (refreshLayout != null) {
            onFooterFinish(refreshLayout.getRefreshFooter(), z);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnLoadMoreListener
    public void onLoadMore(com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
        RefreshLayout refreshLayout2 = this.refreshLayout;
        if (refreshLayout2 != null) {
            onLoadMore(refreshLayout2);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnRefreshListener
    public void onRefresh(com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
        RefreshLayout refreshLayout2 = this.refreshLayout;
        if (refreshLayout2 != null) {
            onRefresh(refreshLayout2);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.OnStateChangedListener
    public void onStateChanged(com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout, com.ido.smartrefresh.layoutkernel.constant.RefreshState refreshState, com.ido.smartrefresh.layoutkernel.constant.RefreshState refreshState2) {
        RefreshLayout refreshLayout2 = this.refreshLayout;
        if (refreshLayout2 != null) {
            onStateChanged(refreshLayout2, RefreshState.from(refreshState), RefreshState.from(refreshState2));
        }
    }
}