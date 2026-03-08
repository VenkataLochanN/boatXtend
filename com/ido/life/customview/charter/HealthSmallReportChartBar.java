package com.ido.life.customview.charter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.ido.life.bean.BarChartPoint;

/* JADX INFO: loaded from: classes2.dex */
public class HealthSmallReportChartBar<T extends BarChartPoint> extends BarChartBar<T> {
    public HealthSmallReportChartBar(Context context) {
        super(context);
    }

    public HealthSmallReportChartBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthSmallReportChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ido.life.customview.charter.BarChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void drawChat(Canvas canvas) {
        int iSave;
        super.drawChat(canvas);
        if (this.mList == null || this.mList.size() == 0 || this.mCircleRegion == null || this.mCircleRegion.size() == 0 || this.mList.size() != this.mCircleRegion.size()) {
            return;
        }
        if (this.mAnimator == null || !this.mAnimator.isRunning() || this.mAnimatorRadius < 1) {
            iSave = -1;
        } else {
            float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
            int iMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance() + this.mYGridBottomLineDistance;
            this.mAnimatorRectf.left = fMeasureLeftLineToLeftDistance;
            this.mAnimatorRectf.right = getWidth() - measureRightLabelMaxWidth();
            this.mAnimatorRectf.bottom = getHeight() - iMeasureBottomLineToBottomDistance;
            this.mAnimatorRectf.top = (float) (((double) this.mAnimatorRectf.bottom) - ((((double) ((getHeight() - iMeasureBottomLineToBottomDistance) * this.mAnimatorRadius)) * 1.0d) / 100.0d));
            canvas.clipRect(this.mAnimatorRectf);
            iSave = canvas.save();
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        int size = this.mCircleRegion.size();
        for (int i = 0; i < size; i++) {
            BarChartPoint barChartPoint = (BarChartPoint) this.mList.get(i);
            RectF rectF = this.mCircleRegion.get(i);
            if (this.mBarSelectedColor != 0 && this.mSelectedIndex == i) {
                this.mPaint.setColor(this.mBarSelectedColor);
            } else if (barChartPoint.getBarColor() != -1) {
                this.mPaint.setColor(barChartPoint.getBarColor());
            } else {
                this.mPaint.setColor(this.mLineColor);
            }
            if (this.mHasRadius) {
                int iSave2 = canvas.save();
                canvas.clipRect(rectF.left, rectF.top, rectF.right, rectF.bottom - this.mRadius);
                canvas.drawRoundRect(rectF.left, rectF.top, rectF.right, rectF.bottom, this.mRadius, this.mRadius, this.mPaint);
                canvas.restoreToCount(iSave2);
                canvas.drawRect(rectF.left, rectF.bottom - this.mRadius, rectF.right, rectF.bottom, this.mPaint);
            } else {
                canvas.drawRect(rectF.left, rectF.top, rectF.right, rectF.bottom, this.mPaint);
            }
        }
        if (iSave != -1) {
            canvas.restoreToCount(iSave);
        }
        this.mSelectedIndex = -1;
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
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextSize(this.mLeftLabelSize);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int height2 = (getHeight() - iMeasureBottomLineToBottomDistance) - (rect.height() / 2);
        int i = (this.mXMaxValue - this.mXMinValue) + 1;
        float width = (getWidth() - fMeasureLeftLineToLeftDistance) - measureRightLabelMaxWidth();
        if (i > 0) {
            width /= i;
            this.mBarWidth = this.mBarSpaceRadius * width;
        }
        float f2 = this.mYMaxValue - this.mYMinValue > 0.0f ? (float) ((((double) height2) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        int size = this.mList.size();
        for (int i2 = 0; i2 < size; i2++) {
            PointF pointF = (PointF) this.mList.get(i2);
            if (pointF != null) {
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
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawXGridLineByPer(Canvas canvas) {
        if (this.mDrawXGridLine) {
            if (this.mYLeftCount >= 1 || this.mYRightCount >= 1) {
                this.mPaint.reset();
                this.mPaint.setTextSize(this.mLeftLabelSize);
                this.mPaint.setStyle(Paint.Style.FILL);
                Rect rect = new Rect();
                this.mPaint.getTextBounds("AA", 0, 2, rect);
                int iMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
                float height = ((getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance) - (rect.height() / 2);
                int iMax = Math.max(this.mYLeftCount, this.mYRightCount);
                if (iMax > 1) {
                    height = (float) ((((double) height) * 1.0d) / ((double) (iMax - 1)));
                }
                this.mPaint.reset();
                this.mPaint.setAntiAlias(true);
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setPathEffect(new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f));
                this.mPaint.setColor(this.mGridXColor);
                this.mPaint.setStrokeWidth(this.mGridXHeight);
                float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
                float height2 = (getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance;
                float width = getWidth() - measureRightLabelMaxWidth();
                for (int i = 0; i < iMax; i++) {
                    float f2 = height2 - (i * height);
                    canvas.drawLine(fMeasureLeftLineToLeftDistance, f2, width, f2, this.mPaint);
                }
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawBottomLabel(Canvas canvas) {
        float width;
        if (!this.mBottomLabelEnabled || this.mLabelXList == null || this.mXCount != this.mLabelXList.size() || this.mXCount == 0) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mBottomLabelColor);
        this.mPaint.setTextSize(this.mBottomLabelSize);
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        int size = this.mLabelXList.size();
        Rect rect = new Rect();
        if (this.mXCount == 1) {
            String str = this.mLabelXList.get(0);
            this.mPaint.getTextBounds(str, 0, str.length(), rect);
            canvas.drawText(str, ((((getWidth() - fMeasureLeftLineToLeftDistance) - measureRightLabelMaxWidth()) / 2.0f) + fMeasureLeftLineToLeftDistance) - (rect.width() / 2), getHeight() - getPaddingBottom(), this.mPaint);
        } else {
            if (this.mBottomLabelCenter) {
                width = ((getWidth() - measureRightLabelMaxWidth()) - fMeasureLeftLineToLeftDistance) / this.mXCount;
            } else {
                width = ((getWidth() - measureRightLabelMaxWidth()) - fMeasureLeftLineToLeftDistance) / (this.mXCount - 1);
            }
            for (int i = 0; i < size; i++) {
                String str2 = this.mLabelXList.get(i);
                this.mPaint.getTextBounds(str2, 0, str2.length(), rect);
                if (this.mBottomLabelCenter) {
                    canvas.drawText(str2, (((i + 0.5f) * width) + fMeasureLeftLineToLeftDistance) - (rect.width() / 2), getHeight() - getPaddingBottom(), this.mPaint);
                } else if (i == 0) {
                    canvas.drawText(str2, fMeasureLeftLineToLeftDistance, getHeight() - getPaddingBottom(), this.mPaint);
                } else if (i == size - 1) {
                    canvas.drawText(str2, (getWidth() - measureRightLabelMaxWidth()) - rect.width(), getHeight() - getPaddingBottom(), this.mPaint);
                } else {
                    canvas.drawText(str2, (fMeasureLeftLineToLeftDistance - (rect.width() / 2)) + (i * width), getHeight() - getPaddingBottom(), this.mPaint);
                }
            }
        }
        if (TextUtils.isEmpty(this.mBottomTitle)) {
            return;
        }
        Rect rectMeasureBottomLableTitleRect = measureBottomLableTitleRect();
        if (this.mBottomTitleSize > 0) {
            this.mPaint.setTextSize(this.mBottomTitleSize);
        }
        if (this.mBottomTitleColor != -1) {
            this.mPaint.setColor(this.mBottomTitleColor);
        }
        canvas.drawText(this.mBottomTitle, (fMeasureLeftLineToLeftDistance - rectMeasureBottomLableTitleRect.width()) - this.mBottomTitleToYDistance, getHeight() - getPaddingBottom(), this.mPaint);
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawBottomLine(Canvas canvas) {
        if (this.mBottomLineEnabled) {
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(this.mBottomLineColor);
            this.mPaint.setStrokeWidth(this.mBottomLineHeight);
            this.mPaint.setStyle(Paint.Style.STROKE);
            float height = getHeight() - measureBottomLineToBottomDistance();
            canvas.drawLine(measureLeftLineToLeftDistance(), height, getWidth() - measureRightLabelMaxWidth(), height, this.mPaint);
        }
    }
}