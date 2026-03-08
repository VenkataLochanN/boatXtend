package com.ido.life.customview.charter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.ido.life.bean.BaseCharBean;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class CubicSportChartBar<T extends BaseCharBean> extends CubicChartBar<T> {
    public CubicSportChartBar(Context context) {
        this(context, null);
    }

    public CubicSportChartBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CubicSportChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawRightLabel(Canvas canvas) {
        super.drawRightLabel(canvas);
    }

    @Override // com.ido.life.customview.charter.CubicChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        ArrayList arrayList;
        if (this.mRegionList == null) {
            this.mRegionList = new ArrayList();
        } else {
            this.mRegionList.clear();
        }
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        adjustLabelMaxineValue();
        this.mMaxTop = 2.1474836E9f;
        this.mPaint.reset();
        this.mPaint.setTextSize(this.mLeftLabelSize);
        this.mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int height = (getHeight() - measureBottomLineToBottomDistance()) - (rect.height() / 2);
        if (this.mDrawXGridLine) {
            height -= this.mYGridBottomLineDistance;
        }
        int width = getWidth() - measureRightLineToLeftDistance();
        int iHeight = (rect.height() / 2) + height;
        float f2 = this.mXMaxValue - this.mXMinValue > 0 ? (float) ((((double) width) * 1.0d) / ((double) (this.mXMaxValue - this.mXMinValue))) : 0.0f;
        float f3 = this.mYMaxValue - this.mYMinValue > 0.0f ? (float) ((((double) height) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        loop0: while (true) {
            arrayList = null;
            for (T t : this.mList) {
                if (((BaseCharBean) t).x == 0.0f && ((BaseCharBean) t).y == 0.0f) {
                    if (arrayList != null && arrayList.size() > 0) {
                        this.mRegionList.add(arrayList);
                    }
                } else if (((BaseCharBean) t).x >= this.mXMinValue && ((BaseCharBean) t).x <= this.mXMaxValue && ((BaseCharBean) t).y >= this.mYMinValue && ((BaseCharBean) t).y <= this.mYMaxValue) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    float f4 = 0 + ((((BaseCharBean) t).x - this.mXMinValue) * f2);
                    float f5 = iHeight;
                    float f6 = f5 - ((((BaseCharBean) t).y - this.mYMinValue) * f3);
                    this.mMaxTop = Math.min(this.mMaxTop, f6);
                    arrayList.add(new RectF(f4, f6, f4, f5));
                }
            }
            break loop0;
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.mRegionList.add(arrayList);
    }

    @Override // com.ido.life.customview.charter.CustomChatBar, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}