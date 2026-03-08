package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.ido.life.R;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.bean.GoalLineInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HealthReportChartBar<T extends BarChartPoint> extends BarChartBar<T> {
    private static final String TAG = HealthReportChartBar.class.getSimpleName();
    private List<String> mRightYLabelList;
    private int mRightYLabelToLineDistance;

    public HealthReportChartBar(Context context) {
        this(context, null);
    }

    public HealthReportChartBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HealthReportChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRightYLabelToLineDistance = 0;
        initAttr(attributeSet);
    }

    protected void initAttr(AttributeSet attributeSet) {
        super.init(attributeSet);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.HealthReportChartBar);
        this.mRightYLabelToLineDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mRightYLabelToLineDistance);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // com.ido.life.customview.charter.BarChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        this.mCircleRegion.clear();
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        adjustLabelMaxineValue();
        int iMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
        if (this.mDrawXGridLine) {
            iMeasureBottomLineToBottomDistance += this.mYGridBottomLineDistance;
        }
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        int height = getHeight() - iMeasureBottomLineToBottomDistance;
        int height2 = getHeight() - iMeasureBottomLineToBottomDistance;
        int i = (this.mXMaxValue - this.mXMinValue) + 1;
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
        if (i > 0) {
            width /= i;
            this.mBarWidth = this.mBarSpaceRadius * width;
        }
        float f2 = this.mYMaxValue - this.mYMinValue > 0.0f ? (float) ((((double) height2) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        int size = this.mList.size();
        for (int i2 = 0; i2 < size; i2++) {
            PointF pointF = (PointF) this.mList.get(i2);
            float f3 = height;
            float f4 = f3 - ((pointF.y - this.mYMinValue) * f2);
            if (this.mBottomLabelCenter) {
                float f5 = ((width - this.mBarWidth) / 2.0f) + fMeasureLeftLineToLeftDistance + ((pointF.x - this.mXMinValue) * width);
                this.mCircleRegion.add(new RectF(f5, f4, this.mBarWidth + f5, f3));
            } else {
                float f6 = ((pointF.x - this.mXMinValue) * width) + fMeasureLeftLineToLeftDistance;
                this.mCircleRegion.add(new RectF(f6, f4, this.mBarWidth + f6, f3));
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawGoalLabelLine(Canvas canvas) {
        Rect rect;
        float f2;
        String[] strArr;
        float fHeight;
        calculateGoalLinePosition();
        if (this.mGoalLineList == null || this.mGoalLineList.size() == 0 || this.mXCount == 0) {
            return;
        }
        if (this.mYLeftCount == 0 && this.mYRightCount == 0) {
            return;
        }
        int size = this.mGoalLineList.size();
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mGoalXLabelTextColor);
        this.mPaint.setStrokeWidth(this.mGoalLineWidth);
        this.mPaint.setTextSize(this.mGoalXLabelTextSize);
        Rect rect2 = new Rect();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        float fWidth = 0.0f;
        while (i2 < size) {
            GoalLineInfo goalLineInfo = this.mGoalLineList.get(i2);
            String goalLabel = goalLineInfo.getGoalLabel();
            arrayList.clear();
            if (TextUtils.isEmpty(goalLabel)) {
                rect = rect2;
                f2 = fWidth;
                strArr = null;
            } else if (goalLabel.contains("/")) {
                String[] strArrSplit = goalLabel.split("/");
                int length = strArrSplit.length;
                Rect rect3 = rect2;
                int i3 = i;
                while (i3 < length) {
                    String str = strArrSplit[i3];
                    Rect rect4 = new Rect();
                    this.mPaint.getTextBounds(str, i, str.length(), rect4);
                    fWidth = Math.max(fWidth, rect4.width());
                    arrayList.add(rect4);
                    i3++;
                    rect3 = rect4;
                }
                f2 = fWidth;
                strArr = strArrSplit;
                rect = rect3;
            } else {
                this.mPaint.getTextBounds(goalLabel, i, goalLabel.length(), rect2);
                fWidth = rect2.width();
                rect = rect2;
                f2 = fWidth;
                strArr = null;
            }
            if (goalLineInfo.getLineOrientation() == 0) {
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setColor(this.mGoalLineColor);
                this.mPaint.setPathEffect(new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f));
                canvas.drawLine(this.mXGoalLabelLineDistance, goalLineInfo.getPosition(), getWidth(), goalLineInfo.getPosition(), this.mPaint);
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setColor(this.mGoalXLabelTextColor);
                this.mPaint.setPathEffect(null);
                if (strArr != null && strArr.length > 0) {
                    int length2 = strArr.length - 1;
                    float f3 = 0.0f;
                    for (int i4 = length2; i4 >= 0; i4--) {
                        if (i4 == length2) {
                            fHeight = f3 - (((Rect) arrayList.get(i4)).height() / 2);
                        } else {
                            fHeight = f3 + ((Rect) arrayList.get(i4)).height();
                        }
                        canvas.drawText(strArr[i4], getWidth() - f2, goalLineInfo.getPosition() - fHeight, this.mPaint);
                        f3 = fHeight + 4.0f;
                    }
                } else if (!TextUtils.isEmpty(goalLabel)) {
                    canvas.drawText(goalLabel, getWidth() - f2, goalLineInfo.getPosition() + (rect.height() / 2), this.mPaint);
                }
            } else {
                float fMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
                if (this.mDrawXGridLine) {
                    fMeasureBottomLineToBottomDistance += this.mYGridBottomLineDistance;
                }
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setColor(this.mGoalLineColor);
                this.mPaint.setPathEffect(new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f));
                canvas.drawLine(goalLineInfo.getPosition(), fMeasureBottomLineToBottomDistance, goalLineInfo.getPosition() - (this.mGoalLineWidth / 2), getHeight(), this.mPaint);
            }
            i2++;
            fWidth = f2;
            rect2 = rect;
            i = 0;
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