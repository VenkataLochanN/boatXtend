package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.ido.life.R;
import com.ido.life.bean.BaseCharBean;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HealthReportLineBar extends LineChartBar {
    private List<String> mRightYLabelList;
    private int mRightYLabelToLineDistance;
    private int mValueTextColor;
    private int mValueTextSize;

    public HealthReportLineBar(Context context) {
        this(context, null);
    }

    public HealthReportLineBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HealthReportLineBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRightYLabelToLineDistance = 0;
        initAttr(attributeSet);
    }

    protected void initAttr(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.HealthReportLineBar);
        this.mRightYLabelToLineDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mRightYLabelToLineDistance);
        this.mValueTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.mValueTextColor = typedArrayObtainStyledAttributes.getColor(0, -3355444);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // com.ido.life.customview.charter.LineChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void drawChat(Canvas canvas) {
        super.drawChat(canvas);
        if (this.mCircleRegion == null || this.mList == null || this.mCircleRegion.size() != this.mList.size() || this.mList.size() <= 0) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mValueTextColor);
        this.mPaint.setTextSize(this.mValueTextSize);
        int size = this.mCircleRegion.size();
        Rect rect = new Rect();
        for (int i = 0; i < size; i++) {
            RectF rectF = this.mCircleRegion.get(i);
            String strValueOf = String.valueOf(Math.round(((BaseCharBean) this.mList.get(i)).y));
            this.mPaint.getTextBounds(strValueOf, 0, strValueOf.length(), rect);
            canvas.drawText(strValueOf, rectF.centerX() - (rect.width() / 2), Math.max(rect.height() + 4, (rectF.top - rect.height()) - 4.0f), this.mPaint);
        }
    }

    @Override // com.ido.life.customview.charter.LineChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        float f2;
        float f3;
        double d2;
        int i;
        this.mCircleRegion = new ArrayList();
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        adjustLabelMaxineValue();
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        int circleSize = getCircleSize();
        int height = getHeight() - measureBottomLineToBottomDistance();
        if (this.mDrawXGridLine) {
            height -= this.mYGridBottomLineDistance;
        }
        int i2 = circleSize * 2;
        int i3 = height - i2;
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
        if (!this.mBottomLabelCenter) {
            fMeasureLeftLineToLeftDistance += circleSize;
            width -= i2;
        }
        if (this.mXMaxValue - this.mXMinValue > 0) {
            if (this.mBottomLabelCenter) {
                d2 = ((double) width) * 1.0d;
                i = (this.mXMaxValue - this.mXMinValue) + 1;
            } else {
                d2 = ((double) width) * 1.0d;
                i = this.mXMaxValue - this.mXMinValue;
            }
            f2 = (float) (d2 / ((double) i));
        } else {
            f2 = 0.0f;
        }
        float f4 = this.mYMaxValue - this.mYMinValue > 0.0f ? (float) ((((double) i3) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        if (this.mList == null || this.mList.size() <= 0) {
            return;
        }
        for (T t : this.mList) {
            if (this.mBottomLabelCenter) {
                f3 = (float) (((double) fMeasureLeftLineToLeftDistance) + (((double) f2) / 2.0d) + ((double) ((t.x - this.mXMinValue) * f2)));
            } else {
                f3 = ((t.x - this.mXMinValue) * f2) + fMeasureLeftLineToLeftDistance;
            }
            float f5 = height - ((t.y - this.mYMinValue) * f4);
            float f6 = circleSize;
            this.mCircleRegion.add(new RectF(f3 - f6, f5 - f6, f3 + f6, f5 + f6));
        }
    }

    public void setRightYLabelList(List<String> list) {
        this.mRightYLabelList = list;
        List<String> list2 = this.mRightYLabelList;
        if (list2 != null) {
            this.mYRightCount = list2.size();
        } else {
            this.mYRightCount = 0;
        }
    }
}