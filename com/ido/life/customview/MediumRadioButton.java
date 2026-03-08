package com.ido.life.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class MediumRadioButton extends AppCompatRadioButton {
    public MediumRadioButton(Context context) {
        super(context);
        init(context, null);
    }

    public MediumRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public MediumRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "font/MetricTest-Medium.otf"));
    }
}