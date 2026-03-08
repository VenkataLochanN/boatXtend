package com.ido.life.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class RegularRadioButton extends AppCompatRadioButton {
    public RegularRadioButton(Context context) {
        super(context);
        init(context, null);
    }

    public RegularRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public RegularRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "font/MetricTest-Regular.otf"));
    }
}