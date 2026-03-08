package com.lzy.imagepicker.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;

/* JADX INFO: loaded from: classes3.dex */
public class SuperCheckBox extends AppCompatCheckBox {
    public SuperCheckBox(Context context) {
        super(context);
    }

    public SuperCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SuperCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.CompoundButton, android.view.View
    public boolean performClick() {
        boolean zPerformClick = super.performClick();
        if (!zPerformClick) {
            playSoundEffect(0);
        }
        return zPerformClick;
    }
}