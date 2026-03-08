package com.ido.life.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;

/* JADX INFO: loaded from: classes2.dex */
public class RegularCheckBox extends AppCompatCheckBox {
    public RegularCheckBox(Context context) {
        this(context, null);
    }

    public RegularCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RegularCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "font/MetricTest-Regular.otf"));
    }
}