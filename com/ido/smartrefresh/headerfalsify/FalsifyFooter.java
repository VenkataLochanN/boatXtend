package com.ido.smartrefresh.headerfalsify;

import android.content.Context;
import android.util.AttributeSet;
import com.ido.smartrefresh.headerfalsify.falsify.FalsifyAbstract;
import com.ido.smartrefresh.layoutkernel.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.api.RefreshKernel;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;

/* JADX INFO: loaded from: classes3.dex */
public class FalsifyFooter extends FalsifyAbstract implements RefreshFooter {
    public FalsifyFooter(Context context) {
        this(context, null);
    }

    public FalsifyFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    @Override // com.ido.smartrefresh.headerfalsify.falsify.FalsifyAbstract, com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onInitialized(RefreshKernel refreshKernel, int i, int i2) {
        this.mRefreshKernel = refreshKernel;
        refreshKernel.getRefreshLayout().setEnableAutoLoadMore(false);
    }

    @Override // com.ido.smartrefresh.headerfalsify.falsify.FalsifyAbstract, com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onReleased(RefreshLayout refreshLayout, int i, int i2) {
        if (this.mRefreshKernel != null) {
            refreshLayout.closeHeaderOrFooter();
        }
    }
}