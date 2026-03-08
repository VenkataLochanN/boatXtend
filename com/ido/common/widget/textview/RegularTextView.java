package com.ido.common.widget.textview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes2.dex */
public class RegularTextView extends AppCompatTextView {
    private void init(Context context, AttributeSet attributeSet) {
    }

    public RegularTextView(Context context) {
        super(context);
        init(context, null);
    }

    public RegularTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public RegularTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }
}