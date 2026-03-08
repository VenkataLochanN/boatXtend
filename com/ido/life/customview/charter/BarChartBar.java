package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.ido.life.R;
import com.ido.life.bean.BarChartPoint;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class BarChartBar<T extends BarChartPoint> extends CustomChatBar<T> {
    protected RectF mAnimatorRectf;
    public int mBarSelectedColor;
    public float mBarSpaceRadius;
    protected float mBarWidth;
    protected boolean mHasRadius;
    protected int mRadius;
    public int mSelectedIndex;
    private ArrayList<Integer> noDataIndexs;

    public BarChartBar(Context context) {
        this(context, null);
    }

    public BarChartBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public BarChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBarWidth = 0.0f;
        this.mBarSpaceRadius = 0.5f;
        this.mHasRadius = false;
        this.mSelectedIndex = -1;
        this.noDataIndexs = new ArrayList<>();
        initAttrs(attributeSet);
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.BarChartBar);
        this.mBarSpaceRadius = typedArrayObtainStyledAttributes.getFloat(3, this.mBarSpaceRadius);
        this.mHasRadius = typedArrayObtainStyledAttributes.getBoolean(0, false);
        this.mRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.mBarSelectedColor = typedArrayObtainStyledAttributes.getColor(2, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.mAnimatorRectf = new RectF();
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
        int size = this.mCircleRegion.size();
        for (int i = 0; i < size; i++) {
            BarChartPoint barChartPoint = (BarChartPoint) this.mList.get(i);
            RectF rectF3 = this.mCircleRegion.get(i);
            if (this.mBarSelectedColor != 0 && this.mSelectedIndex == i) {
                this.mPaint.setColor(this.mBarSelectedColor);
            } else if (barChartPoint.getBarColor() != -1) {
                this.mPaint.setColor(barChartPoint.getBarColor());
            } else {
                this.mPaint.setColor(this.mLineColor);
            }
            if (this.mHasRadius) {
                int iSave2 = canvas.save();
                canvas.clipRect(rectF3.left, rectF3.top, rectF3.right, rectF3.bottom - this.mRadius);
                float f2 = rectF3.left;
                float f3 = rectF3.top;
                float f4 = rectF3.right;
                float f5 = rectF3.bottom;
                int i2 = this.mRadius;
                canvas.drawRoundRect(f2, f3, f4, f5, i2, i2, this.mPaint);
                canvas.restoreToCount(iSave2);
                canvas.drawRect(rectF3.left, rectF3.bottom - this.mRadius, rectF3.right, rectF3.bottom, this.mPaint);
            } else {
                canvas.drawRect(rectF3.left, rectF3.top, rectF3.right, rectF3.bottom, this.mPaint);
            }
        }
        if (iSave != -1) {
            canvas.restoreToCount(iSave);
        }
        this.mSelectedIndex = -1;
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        this.mCircleRegion.clear();
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        this.noDataIndexs.clear();
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
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
        if (i > 0) {
            width /= i;
            this.mBarWidth = this.mBarSpaceRadius * width;
        }
        float f2 = this.mYMaxValue - this.mYMinValue > 0.0f ? (float) ((((double) height2) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        int size = this.mList.size();
        for (int i2 = 0; i2 < size; i2++) {
            PointF pointF = (PointF) this.mList.get(i2);
            if (pointF != null) {
                if (pointF.y == getDefaultHeight()) {
                    this.noDataIndexs.add(Integer.valueOf(i2));
                }
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
    public void onChartClick(int i) {
        super.onChartClick(i);
        if (this.mClickListener == null || this.mCircleRegion == null || this.mCircleRegion.size() < i + 1 || i < 0 || this.noDataIndexs.contains(Integer.valueOf(i))) {
            return;
        }
        this.mClickListener.onChartClick(this, this.mCircleRegion.get(i), i);
        this.mSelectedIndex = i;
        refreshChart(false);
    }
}