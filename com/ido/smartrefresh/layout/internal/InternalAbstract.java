package com.ido.smartrefresh.layout.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.ido.smartrefresh.layout.api.RefreshInternal;
import com.ido.smartrefresh.layoutkernel.simple.SimpleComponent;

/* JADX INFO: loaded from: classes3.dex */
public abstract class InternalAbstract extends SimpleComponent {
    protected InternalAbstract(View view) {
        super(view);
    }

    protected InternalAbstract(View view, RefreshInternal refreshInternal) {
        super(view, refreshInternal);
    }

    protected InternalAbstract(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}