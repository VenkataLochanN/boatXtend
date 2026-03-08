package com.ido.life.customview.charter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.ido.life.bean.GoalLineInfo;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class WeightLineChartBar extends LineChartBar {
    public WeightLineChartBar(Context context) {
        super(context);
    }

    public WeightLineChartBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WeightLineChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawGoalLabelLine(Canvas canvas) {
        PointF pointF;
        String[] strArr;
        float f2;
        float fMax;
        boolean z;
        float f3;
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
        ArrayList arrayList = new ArrayList();
        int i = 0;
        PointF pointF2 = new PointF();
        int i2 = 0;
        float f4 = 0.0f;
        while (i2 < size) {
            GoalLineInfo goalLineInfo = this.mGoalLineList.get(i2);
            String goalLabel = goalLineInfo.getGoalLabel();
            arrayList.clear();
            if (TextUtils.isEmpty(goalLabel)) {
                pointF = pointF2;
                strArr = null;
                f2 = 0.0f;
                fMax = f4;
            } else {
                if (goalLabel.contains("/")) {
                    String[] strArrSplit = goalLabel.split("/");
                    int length = strArrSplit.length;
                    PointF pointF3 = pointF2;
                    fMax = f4;
                    int i3 = i;
                    while (i3 < length) {
                        String str = strArrSplit[i3];
                        PointF pointF4 = new PointF();
                        this.mPaint.getTextBounds(str, i, str.length(), new Rect());
                        pointF4.y = r9.height();
                        pointF4.x = Math.max(r9.width(), this.mPaint.measureText(str));
                        fMax = Math.max(fMax, pointF4.y);
                        arrayList.add(pointF4);
                        i3++;
                        pointF3 = pointF4;
                        strArrSplit = strArrSplit;
                    }
                    pointF = pointF3;
                    strArr = strArrSplit;
                } else {
                    PointF pointF5 = new PointF();
                    this.mPaint.getTextBounds(goalLabel, i, goalLabel.length(), new Rect());
                    pointF5.y = r2.height();
                    pointF5.x = Math.max(r2.width(), this.mPaint.measureText(goalLabel));
                    fMax = pointF5.x;
                    pointF = pointF5;
                    strArr = null;
                }
                f2 = 0.0f;
            }
            float fMeasureLeftLineToLeftDistance = fMax == f2 ? measureLeftLineToLeftDistance() : fMax;
            if (goalLineInfo.getLineOrientation() == 0) {
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setColor(this.mGoalLineColor);
                canvas.drawLine(measureLeftLineToLeftDistance(), goalLineInfo.getPosition(), (getWidth() - fMeasureLeftLineToLeftDistance) - this.mXGoalLabelLineDistance, goalLineInfo.getPosition(), this.mPaint);
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setColor(this.mGoalXLabelTextColor);
                if (strArr != null && strArr.length > 0) {
                    z = true;
                    int length2 = strArr.length - 1;
                    float f5 = f2;
                    for (int i4 = length2; i4 >= 0; i4--) {
                        if (i4 == length2) {
                            f3 = f5 - (((PointF) arrayList.get(i4)).y / 2.0f);
                        } else {
                            f3 = f5 + ((PointF) arrayList.get(i4)).y;
                        }
                        canvas.drawText(strArr[i4], (getWidth() - fMeasureLeftLineToLeftDistance) + ((fMeasureLeftLineToLeftDistance - ((PointF) arrayList.get(i4)).x) / 2.0f), goalLineInfo.getPosition() - f3, this.mPaint);
                        f5 = f3 + 4.0f;
                    }
                } else {
                    z = true;
                    if (!TextUtils.isEmpty(goalLabel)) {
                        canvas.drawText(goalLabel, getWidth() - fMeasureLeftLineToLeftDistance, goalLineInfo.getPosition() + (pointF.y / 2.0f), this.mPaint);
                    }
                }
            } else {
                z = true;
                float fMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
                if (this.mDrawXGridLine) {
                    fMeasureBottomLineToBottomDistance += this.mYGridBottomLineDistance;
                }
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setColor(this.mGoalLineColor);
                canvas.drawLine(goalLineInfo.getPosition(), fMeasureBottomLineToBottomDistance, goalLineInfo.getPosition() - (this.mGoalLineWidth / 2), getHeight(), this.mPaint);
            }
            i2++;
            pointF2 = pointF;
            f4 = fMeasureLeftLineToLeftDistance;
            i = 0;
        }
    }
}