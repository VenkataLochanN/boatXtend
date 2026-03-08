package com.ido.smartrefresh.layout.api;

import com.ido.smartrefresh.layout.listener.OnLoadMoreListener;
import com.ido.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.ido.smartrefresh.layout.listener.OnRefreshListener;
import com.ido.smartrefresh.layoutkernel.listener.OnRefreshLoadMoreListener;

/* JADX INFO: loaded from: classes3.dex */
public interface RefreshLayout extends com.ido.smartrefresh.layoutkernel.api.RefreshLayout {
    RefreshFooter getRefreshFooter();

    RefreshHeader getRefreshHeader();

    RefreshLayout setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener);

    RefreshLayout setOnMultiPurposeListener(OnMultiPurposeListener onMultiPurposeListener);

    RefreshLayout setOnRefreshListener(OnRefreshListener onRefreshListener);

    RefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener);

    RefreshLayout setRefreshFooter(RefreshFooter refreshFooter);

    RefreshLayout setRefreshFooter(RefreshFooter refreshFooter, int i, int i2);

    RefreshLayout setRefreshHeader(RefreshHeader refreshHeader);

    RefreshLayout setRefreshHeader(RefreshHeader refreshHeader, int i, int i2);

    RefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider);

    /* JADX INFO: renamed from: com.ido.smartrefresh.layout.api.RefreshLayout$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}