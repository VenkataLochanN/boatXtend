package com.ido.smartrefresh.layout.footer;

import android.content.Context;
import android.util.AttributeSet;
import com.ido.smartrefresh.layout.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;

/* JADX INFO: loaded from: classes3.dex */
public class BallPulseFooter extends com.ido.smartrefresh.footer.BallPulseFooter implements RefreshFooter {
    public BallPulseFooter(Context context) {
        this(context, null);
    }

    public BallPulseFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.ido.smartrefresh.footer.BallPulseFooter
    public BallPulseFooter setSpinnerStyle(SpinnerStyle spinnerStyle) {
        super.setSpinnerStyle(spinnerStyle);
        return this;
    }

    @Override // com.ido.smartrefresh.footer.BallPulseFooter
    public BallPulseFooter setNormalColor(int i) {
        super.setNormalColor(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footer.BallPulseFooter
    public BallPulseFooter setAnimatingColor(int i) {
        super.setAnimatingColor(i);
        return this;
    }
}