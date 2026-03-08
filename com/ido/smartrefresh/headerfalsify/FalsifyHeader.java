package com.ido.smartrefresh.headerfalsify;

import android.content.Context;
import android.util.AttributeSet;
import com.ido.smartrefresh.headerfalsify.falsify.FalsifyAbstract;
import com.ido.smartrefresh.layoutkernel.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;

/* JADX INFO: loaded from: classes3.dex */
public class FalsifyHeader extends FalsifyAbstract implements RefreshHeader {
    public FalsifyHeader(Context context) {
        this(context, null);
    }

    public FalsifyHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    @Override // com.ido.smartrefresh.headerfalsify.falsify.FalsifyAbstract, com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onReleased(RefreshLayout refreshLayout, int i, int i2) {
        if (this.mRefreshKernel != null) {
            refreshLayout.closeHeaderOrFooter();
        }
    }
}