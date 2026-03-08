package com.ido.smartrefresh.layout.header;

import android.content.Context;
import android.util.AttributeSet;
import com.ido.smartrefresh.headertwolevel.listener.OnTwoLevelListener;
import com.ido.smartrefresh.layout.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;

/* JADX INFO: loaded from: classes3.dex */
public class TwoLevelHeader extends com.ido.smartrefresh.headertwolevel.TwoLevelHeader implements RefreshHeader {
    public TwoLevelHeader(Context context) {
        this(context, null);
    }

    public TwoLevelHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setOnTwoLevelListener(final OnTwoLevelListener onTwoLevelListener) {
        super.setOnTwoLevelListener(new OnTwoLevelListener() { // from class: com.ido.smartrefresh.layout.header.TwoLevelHeader.1
            @Override // com.ido.smartrefresh.headertwolevel.listener.OnTwoLevelListener
            public boolean onTwoLevel(RefreshLayout refreshLayout) {
                return onTwoLevelListener.onTwoLevel(refreshLayout);
            }
        });
        return this;
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setRefreshHeader(com.ido.smartrefresh.layoutkernel.api.RefreshHeader refreshHeader) {
        super.setRefreshHeader(refreshHeader);
        return this;
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setRefreshHeader(com.ido.smartrefresh.layoutkernel.api.RefreshHeader refreshHeader, int i, int i2) {
        super.setRefreshHeader(refreshHeader, i, i2);
        return this;
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setMaxRate(float f2) {
        super.setMaxRate(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setEnablePullToCloseTwoLevel(boolean z) {
        super.setEnablePullToCloseTwoLevel(z);
        return this;
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setFloorRate(float f2) {
        super.setFloorRate(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setRefreshRate(float f2) {
        super.setRefreshRate(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setEnableTwoLevel(boolean z) {
        super.setEnableTwoLevel(z);
        return this;
    }

    @Override // com.ido.smartrefresh.headertwolevel.TwoLevelHeader
    public TwoLevelHeader setFloorDuration(int i) {
        super.setFloorDuration(i);
        return this;
    }
}