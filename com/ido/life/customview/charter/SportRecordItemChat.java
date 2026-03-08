package com.ido.life.customview.charter;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportRecordItemChat extends HomeSportRecordChat {
    private static final String TAG = "HomeSportRecordChat";

    public SportRecordItemChat(Context context) {
        this(context, null);
    }

    public SportRecordItemChat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SportRecordItemChat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ido.life.customview.charter.HomeSportRecordChat, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i2);
        int iMin = Math.min(size, size);
        setMeasuredDimension(iMin, iMin);
    }

    public void setList(List<PointF> list, List<Integer> list2) {
        this.mColorList = list2;
        this.mList = list;
    }
}