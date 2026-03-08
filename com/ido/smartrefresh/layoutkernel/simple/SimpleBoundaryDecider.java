package com.ido.smartrefresh.layoutkernel.simple;

import android.graphics.PointF;
import android.view.View;
import com.ido.smartrefresh.layoutkernel.listener.ScrollBoundaryDecider;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleBoundaryDecider implements ScrollBoundaryDecider {
    public ScrollBoundaryDecider boundary;
    public PointF mActionEvent;
    public boolean mEnableLoadMoreWhenContentNotFull = true;

    @Override // com.ido.smartrefresh.layoutkernel.listener.ScrollBoundaryDecider
    public boolean canRefresh(View view) {
        ScrollBoundaryDecider scrollBoundaryDecider = this.boundary;
        if (scrollBoundaryDecider != null) {
            return scrollBoundaryDecider.canRefresh(view);
        }
        return SmartUtil.canRefresh(view, this.mActionEvent);
    }

    @Override // com.ido.smartrefresh.layoutkernel.listener.ScrollBoundaryDecider
    public boolean canLoadMore(View view) {
        ScrollBoundaryDecider scrollBoundaryDecider = this.boundary;
        if (scrollBoundaryDecider != null) {
            return scrollBoundaryDecider.canLoadMore(view);
        }
        return SmartUtil.canLoadMore(view, this.mActionEvent, this.mEnableLoadMoreWhenContentNotFull);
    }
}