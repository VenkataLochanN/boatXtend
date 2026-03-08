package com.ido.common.widget.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.TypefaceCompat;
import com.ido.common.R;

/* JADX INFO: loaded from: classes2.dex */
public class NumberTextView extends AppCompatTextView {
    private boolean isBold;

    public NumberTextView(Context context) {
        super(context);
        this.isBold = true;
        init(context, null);
    }

    public NumberTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isBold = true;
        init(context, attributeSet);
    }

    public NumberTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isBold = true;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MediumTextType);
            this.isBold = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MediumTextType_bold, false);
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.isBold) {
            getPaint().setFakeBoldText(true);
        }
        setIncludeFontPadding(false);
        setTypeface(TypefaceCompat.createFromResourcesFontFile(context, getResources(), R.font.metrictest_medium, "", -1));
    }
}