package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.ido.life.R;
import com.ido.life.bean.FloatBarPoint;

/* JADX INFO: loaded from: classes2.dex */
public class FloatBarChartBar<T extends FloatBarPoint> extends CustomChatBar<T> {
    private static final String TAG = FloatBarChartBar.class.getSimpleName();
    private RectF mAnimatorRectf;
    private int mBarRadius;
    private float mBarWidth;
    private boolean mEnabledRadius;

    public FloatBarChartBar(Context context) {
        this(context, null);
    }

    public FloatBarChartBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public FloatBarChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBarWidth = 20.0f;
        this.mBarRadius = 0;
        this.mEnabledRadius = false;
        initAttrs(attributeSet);
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.FloatBarChartBar);
        this.mBarWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, (int) this.mBarWidth);
        this.mBarRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, this.mBarRadius);
        this.mEnabledRadius = typedArrayObtainStyledAttributes.getBoolean(1, this.mEnabledRadius);
        this.mAnimatorRectf = new RectF();
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
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
            RectF rectF = this.mAnimatorRectf;
            rectF.left = fMeasureLeftLineToLeftDistance;
            rectF.right = getWidth();
            this.mAnimatorRectf.bottom = getHeight() - iMeasureBottomLineToBottomDistance;
            RectF rectF2 = this.mAnimatorRectf;
            rectF2.top = (float) (((double) rectF2.bottom) - ((((double) ((getHeight() - iMeasureBottomLineToBottomDistance) * this.mAnimatorRadius)) * 1.0d) / 100.0d));
            canvas.clipRect(this.mAnimatorRectf);
            iSave = canvas.save();
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mLineColor);
        int size = this.mCircleRegion.size();
        for (int i = 0; i < size; i++) {
            RectF rectF3 = this.mCircleRegion.get(i);
            if (this.mEnabledRadius && this.mBarRadius > 0) {
                float f2 = rectF3.left;
                float f3 = rectF3.top;
                float f4 = rectF3.right;
                float f5 = rectF3.bottom;
                int i2 = this.mBarRadius;
                canvas.drawRoundRect(f2, f3, f4, f5, i2, i2, this.mPaint);
            } else {
                canvas.drawRect(rectF3.left, rectF3.top, rectF3.right, rectF3.bottom, this.mPaint);
            }
        }
        if (iSave != -1) {
            canvas.restoreToCount(iSave);
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        float width;
        int i;
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
        float f2 = 0.0f;
        float fHeight = this.mYMaxValue - this.mYMinValue > 0.0f ? (float) ((((double) (height - (rect.height() / 2))) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        if (this.mXMaxValue - this.mXMinValue > 0) {
            if (this.mBottomLabelCenter) {
                width = getWidth() - fMeasureLeftLineToLeftDistance;
                i = (this.mXMaxValue - this.mXMinValue) + 1;
            } else {
                width = getWidth() - fMeasureLeftLineToLeftDistance;
                i = this.mXMaxValue - this.mXMinValue;
            }
            f2 = width / i;
        }
        float f3 = 0.8f * f2;
        if (this.mBarWidth > f3) {
            this.mBarWidth = f3;
        }
        int size = this.mList.size();
        for (int i2 = 0; i2 < size; i2++) {
            FloatBarPoint floatBarPoint = (FloatBarPoint) this.mList.get(i2);
            float f4 = height;
            float fMax = f4 - ((Math.max(floatBarPoint.getMaxValue(), this.mYMinValue) - this.mYMinValue) * fHeight);
            float fMax2 = f4 - ((Math.max(floatBarPoint.getMinValue(), this.mYMinValue) - this.mYMinValue) * fHeight);
            if (this.mBottomLabelCenter) {
                float f5 = ((((floatBarPoint.x - this.mXMinValue) * f2) + fMeasureLeftLineToLeftDistance) + (f2 / 2.0f)) - (this.mBarWidth / 2.0f);
                this.mCircleRegion.add(new RectF(f5, fMax, this.mBarWidth + f5, fMax2));
            } else {
                float f6 = ((floatBarPoint.x - this.mXMinValue) * f2) + fMeasureLeftLineToLeftDistance;
                this.mCircleRegion.add(new RectF(f6, fMax, this.mBarWidth + f6, fMax2));
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected float measureLeftLineToLeftDistance() {
        float fMeasureLeftLabelMaxWidth = 0.0f;
        float f2 = this.mLeftLineEnabled ? this.mLeftLineWidth + 0.0f : 0.0f;
        if (this.mLeftLabelEnabled && this.mLabelYLeftList != null && this.mLabelYLeftList.size() > 0) {
            fMeasureLeftLabelMaxWidth = measureLeftLabelMaxWidth() + this.mLeftLabelLineDistance;
        }
        return f2 + fMeasureLeftLabelMaxWidth;
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void onChartClick(int i) {
        super.onChartClick(i);
        if (this.mCircleRegion == null || this.mCircleRegion.size() < i + 1 || i < 0) {
            return;
        }
        this.mClickListener.onChartClick(this, this.mCircleRegion.get(i), i);
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void adjustLabelMaxineValue() {
        super.adjustLabelMaxineValue();
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        if (isAutoXMaxinValue() || isAutoYMaxinValue()) {
            int i = Integer.MAX_VALUE;
            int iMax = Integer.MIN_VALUE;
            int iMin = Integer.MAX_VALUE;
            int i2 = Integer.MIN_VALUE;
            for (T t : this.mList) {
                iMax = (int) Math.max(this.mXMaxValue, t.x);
                int iMin2 = (int) Math.min(this.mXMinValue, t.x);
                int iMax2 = (int) Math.max(this.mYMaxValue, t.getMaxValue());
                iMin = (int) Math.min(this.mYMinValue, t.getMinValue());
                i = iMin2;
                i2 = iMax2;
            }
            if (isAutoXMaxinValue()) {
                this.mXMaxValue = iMax;
                this.mXMinValue = i;
            }
            if (isAutoYMaxinValue()) {
                this.mYMaxValue = i2;
                this.mYMinValue = iMin;
            }
        }
    }

    public void setBarWidth(float f2) {
        this.mBarWidth = f2;
    }
}