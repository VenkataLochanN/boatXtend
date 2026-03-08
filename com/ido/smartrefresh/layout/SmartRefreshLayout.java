package com.ido.smartrefresh.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.ido.smartrefresh.footer.BallPulseFooter;
import com.ido.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.ido.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.ido.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.ido.smartrefresh.layout.api.RefreshFooter;
import com.ido.smartrefresh.layout.api.RefreshHeader;
import com.ido.smartrefresh.layout.api.RefreshLayout;
import com.ido.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.ido.smartrefresh.layout.header.BezierRadarHeader;
import com.ido.smartrefresh.layout.listener.OnLoadMoreListener;
import com.ido.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.ido.smartrefresh.layout.listener.OnRefreshListener;
import com.ido.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.ido.smartrefresh.layoutkernel.listener.OnRefreshLoadMoreListener;

/* JADX INFO: loaded from: classes3.dex */
public class SmartRefreshLayout extends com.ido.smartrefresh.layoutkernel.SmartRefreshLayout implements RefreshLayout {
    public SmartRefreshLayout(Context context) {
        this(context, null);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshHeader(RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, -1, -2);
    }

    @Override // com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshHeader(RefreshHeader refreshHeader, int i, int i2) {
        super.setRefreshHeader((com.ido.smartrefresh.layoutkernel.api.RefreshHeader) refreshHeader, i, i2);
        return this;
    }

    @Override // com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshFooter(RefreshFooter refreshFooter) {
        return setRefreshFooter(refreshFooter, -1, -2);
    }

    @Override // com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setRefreshFooter(RefreshFooter refreshFooter, int i, int i2) {
        super.setRefreshFooter((com.ido.smartrefresh.layoutkernel.api.RefreshFooter) refreshFooter, i, i2);
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.SmartRefreshLayout, com.ido.smartrefresh.layoutkernel.api.RefreshLayout, com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshFooter getRefreshFooter() {
        if (this.mRefreshFooter instanceof RefreshFooter) {
            return (RefreshFooter) this.mRefreshFooter;
        }
        return null;
    }

    @Override // com.ido.smartrefresh.layoutkernel.SmartRefreshLayout, com.ido.smartrefresh.layoutkernel.api.RefreshLayout, com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshHeader getRefreshHeader() {
        if (this.mRefreshHeader instanceof RefreshHeader) {
            return (RefreshHeader) this.mRefreshHeader;
        }
        return null;
    }

    public /* synthetic */ void lambda$setOnRefreshListener$0$SmartRefreshLayout(OnRefreshListener onRefreshListener, com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
        onRefreshListener.onRefresh(this);
    }

    @Override // com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setOnRefreshListener(final OnRefreshListener onRefreshListener) {
        super.setOnRefreshListener(new com.ido.smartrefresh.layoutkernel.listener.OnRefreshListener() { // from class: com.ido.smartrefresh.layout.-$$Lambda$SmartRefreshLayout$h8xVEGE0ZHsO6IZ1x2B8ppvPqn4
            @Override // com.ido.smartrefresh.layoutkernel.listener.OnRefreshListener
            public final void onRefresh(com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
                this.f$0.lambda$setOnRefreshListener$0$SmartRefreshLayout(onRefreshListener, refreshLayout);
            }
        });
        return this;
    }

    @Override // com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setOnLoadMoreListener(final OnLoadMoreListener onLoadMoreListener) {
        super.setOnLoadMoreListener(new com.ido.smartrefresh.layoutkernel.listener.OnLoadMoreListener() { // from class: com.ido.smartrefresh.layout.SmartRefreshLayout.1
            @Override // com.ido.smartrefresh.layoutkernel.listener.OnLoadMoreListener
            public void onLoadMore(com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
                onLoadMoreListener.onLoadMore(SmartRefreshLayout.this);
            }
        });
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.SmartRefreshLayout, com.ido.smartrefresh.layoutkernel.api.RefreshLayout, com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setOnRefreshLoadMoreListener(final OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        super.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() { // from class: com.ido.smartrefresh.layout.SmartRefreshLayout.2
            @Override // com.ido.smartrefresh.layoutkernel.listener.OnLoadMoreListener
            public void onLoadMore(com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
                onRefreshLoadMoreListener.onLoadMore(SmartRefreshLayout.this);
            }

            @Override // com.ido.smartrefresh.layoutkernel.listener.OnRefreshListener
            public void onRefresh(com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
                onRefreshLoadMoreListener.onRefresh(SmartRefreshLayout.this);
            }
        });
        return this;
    }

    @Override // com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setOnMultiPurposeListener(OnMultiPurposeListener onMultiPurposeListener) {
        super.setOnMultiListener(new SimpleMultiPurposeListener(onMultiPurposeListener, this));
        return this;
    }

    @Override // com.ido.smartrefresh.layout.api.RefreshLayout
    public RefreshLayout setScrollBoundaryDecider(final ScrollBoundaryDecider scrollBoundaryDecider) {
        super.setScrollBoundaryDecider((com.ido.smartrefresh.layoutkernel.listener.ScrollBoundaryDecider) new ScrollBoundaryDecider() { // from class: com.ido.smartrefresh.layout.SmartRefreshLayout.3
            @Override // com.ido.smartrefresh.layoutkernel.listener.ScrollBoundaryDecider
            public boolean canRefresh(View view) {
                return scrollBoundaryDecider.canRefresh(view);
            }

            @Override // com.ido.smartrefresh.layoutkernel.listener.ScrollBoundaryDecider
            public boolean canLoadMore(View view) {
                return scrollBoundaryDecider.canLoadMore(view);
            }
        });
        return this;
    }

    public static void setDefaultRefreshHeaderCreator(final DefaultRefreshHeaderCreator defaultRefreshHeaderCreator) {
        com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.setDefaultRefreshHeaderCreator(new com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshHeaderCreator() { // from class: com.ido.smartrefresh.layout.SmartRefreshLayout.4
            @Override // com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshHeaderCreator
            public com.ido.smartrefresh.layoutkernel.api.RefreshHeader createRefreshHeader(Context context, com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
                if (refreshLayout instanceof RefreshLayout) {
                    return defaultRefreshHeaderCreator.createRefreshHeader(context, (RefreshLayout) refreshLayout);
                }
                return new BezierRadarHeader(context);
            }
        });
    }

    public static void setDefaultRefreshFooterCreator(final DefaultRefreshFooterCreator defaultRefreshFooterCreator) {
        com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.setDefaultRefreshFooterCreator(new com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshFooterCreator() { // from class: com.ido.smartrefresh.layout.SmartRefreshLayout.5
            @Override // com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshFooterCreator
            public com.ido.smartrefresh.layoutkernel.api.RefreshFooter createRefreshFooter(Context context, com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
                if (refreshLayout instanceof RefreshLayout) {
                    return defaultRefreshFooterCreator.createRefreshFooter(context, (RefreshLayout) refreshLayout);
                }
                return new BallPulseFooter(context);
            }
        });
    }

    public static void setDefaultRefreshInitializer(final DefaultRefreshInitializer defaultRefreshInitializer) {
        com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.setDefaultRefreshInitializer(new com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshInitializer() { // from class: com.ido.smartrefresh.layout.SmartRefreshLayout.6
            @Override // com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshInitializer
            public void initialize(Context context, com.ido.smartrefresh.layoutkernel.api.RefreshLayout refreshLayout) {
                if (refreshLayout instanceof RefreshLayout) {
                    defaultRefreshInitializer.initialize(context, (RefreshLayout) refreshLayout);
                }
            }
        });
    }
}