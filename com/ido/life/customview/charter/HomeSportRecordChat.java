package com.ido.life.customview.charter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.ido.life.R;
import com.ido.life.bean.LatLngBean;
import com.ido.life.location.MapHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HomeSportRecordChat extends View {
    protected ValueAnimator mAnimator;
    protected int mChatLineWidth;
    protected List<Integer> mColorList;
    protected int mEndCircleBorderColor;
    protected int mEndCircleBorderWidth;
    protected int mEndCircleRadius;
    protected int mEndCircleSolidColor;
    protected int mLabelXCount;
    protected int mLabelXItemWidth;
    protected float mLabelXMaxValue;
    protected float mLabelXMinValue;
    protected int mLabelYCount;
    protected float mLabelYMaxValue;
    protected float mLabelYMinValue;
    protected List<LatLngBean> mLatLngList;
    protected List<PointF> mList;
    protected Paint mPaint;
    protected int mStartCircleBorderColor;
    protected int mStartCircleBorderWidth;
    protected int mStartCircleRadius;
    protected int mStartCircleSolidColor;
    protected List<PointF> mViewPointList;

    public HomeSportRecordChat(Context context) {
        this(context, null);
    }

    public HomeSportRecordChat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HomeSportRecordChat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mColorList = new ArrayList();
        this.mLatLngList = new ArrayList();
        this.mStartCircleRadius = 8;
        this.mStartCircleBorderWidth = 2;
        this.mStartCircleBorderColor = -1;
        this.mStartCircleSolidColor = Color.parseColor("#53D316");
        this.mEndCircleRadius = 8;
        this.mEndCircleBorderWidth = 2;
        this.mEndCircleBorderColor = -1;
        this.mEndCircleSolidColor = Color.parseColor("#F60202");
        this.mViewPointList = new ArrayList();
        this.mChatLineWidth = 8;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.HomeSportRecordChat);
        this.mLabelXMaxValue = typedArrayObtainStyledAttributes.getFloat(6, this.mLabelXMaxValue);
        this.mLabelXMinValue = typedArrayObtainStyledAttributes.getFloat(7, this.mLabelXMinValue);
        this.mLabelYMaxValue = typedArrayObtainStyledAttributes.getFloat(9, this.mLabelYMaxValue);
        this.mLabelYMinValue = typedArrayObtainStyledAttributes.getFloat(10, this.mLabelYMinValue);
        this.mLabelXCount = typedArrayObtainStyledAttributes.getInt(5, this.mLabelXCount);
        this.mLabelYCount = typedArrayObtainStyledAttributes.getInt(8, this.mLabelYCount);
        this.mStartCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, this.mStartCircleRadius);
        this.mStartCircleBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, this.mStartCircleBorderWidth);
        this.mStartCircleBorderColor = typedArrayObtainStyledAttributes.getColor(11, this.mStartCircleBorderColor);
        this.mEndCircleBorderColor = typedArrayObtainStyledAttributes.getColor(1, this.mEndCircleBorderColor);
        this.mStartCircleSolidColor = typedArrayObtainStyledAttributes.getColor(14, this.mStartCircleSolidColor);
        this.mEndCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mEndCircleRadius);
        this.mEndCircleBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.mEndCircleBorderWidth);
        this.mEndCircleSolidColor = typedArrayObtainStyledAttributes.getInt(4, this.mEndCircleSolidColor);
        this.mChatLineWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, this.mChatLineWidth);
        if (this.mLabelXCount > 1) {
            this.mLabelXItemWidth = getResources().getDisplayMetrics().widthPixels / (this.mLabelXCount - 1);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
    }

    protected void ajustData() {
        List<PointF> list = this.mList;
        if (list == null || list.size() == 0) {
            return;
        }
        int size = this.mList.size();
        if (this.mLabelYCount < size) {
            this.mLabelYCount = size;
        }
        if (this.mLabelXCount < 1) {
            this.mLabelXCount = this.mLabelYCount;
        }
        int iMax = Math.max(this.mStartCircleRadius + this.mStartCircleBorderWidth, this.mEndCircleRadius + this.mEndCircleBorderWidth) * 2;
        float fMin = Float.MAX_VALUE;
        float fMax = Float.MIN_VALUE;
        float fMin2 = Float.MAX_VALUE;
        float fMin3 = Float.MAX_VALUE;
        float fMax2 = Float.MIN_VALUE;
        float fMax3 = Float.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            PointF pointF = this.mList.get(i);
            fMax2 = Math.max(fMax2, pointF.x);
            fMin2 = Math.min(fMin2, pointF.x);
            fMax3 = Math.max(fMax3, pointF.y);
            fMin3 = Math.min(fMin3, pointF.y);
        }
        this.mLabelXMaxValue = fMax2;
        this.mLabelXMinValue = fMin2;
        this.mLabelYMaxValue = fMax3;
        this.mLabelYMinValue = fMin3;
        float fMin4 = Math.min(this.mLabelXMaxValue > this.mLabelXMinValue ? ((getWidth() - iMax) * 1.0f) / (this.mLabelXMaxValue - this.mLabelXMinValue) : 0.0f, this.mLabelYMaxValue > this.mLabelYMinValue ? ((getHeight() - iMax) * 1.0f) / (this.mLabelYMaxValue - this.mLabelYMinValue) : 0.0f);
        float fMin5 = Float.MAX_VALUE;
        float fMin6 = Float.MAX_VALUE;
        float fMax4 = Float.MIN_VALUE;
        float fMax5 = Float.MIN_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            PointF pointF2 = this.mList.get(i2);
            PointF pointF3 = new PointF(pointF2.x, pointF2.y);
            pointF3.x *= fMin4;
            pointF3.y *= fMin4;
            fMax4 = Math.max(fMax4, pointF3.x);
            fMin5 = Math.min(fMin5, pointF3.x);
            fMax5 = Math.max(fMax5, pointF3.y);
            fMin6 = Math.min(fMin6, pointF3.y);
            this.mViewPointList.add(pointF3);
        }
        int i3 = iMax / 2;
        float width = fMax4 > ((float) (getWidth() - i3)) ? i3 + (fMax4 - getWidth()) : 0.0f;
        float height = fMax5 > ((float) (getHeight() - i3)) ? i3 + (fMax5 - getHeight()) : 0.0f;
        float fMin7 = Float.MAX_VALUE;
        float fMax6 = Float.MIN_VALUE;
        for (int i4 = 0; i4 < size; i4++) {
            PointF pointF4 = this.mViewPointList.get(i4);
            pointF4.x = Math.max(0.0f, pointF4.x - width);
            pointF4.y = Math.max(0.0f, pointF4.y - height);
            fMax = Math.max(fMax, pointF4.x);
            fMin = Math.min(fMin, pointF4.x);
            fMax6 = Math.max(fMax6, pointF4.y);
            fMin7 = Math.min(fMin7, pointF4.y);
        }
        this.mLabelXMaxValue = fMax;
        this.mLabelXMinValue = fMin;
        this.mLabelYMaxValue = fMax6;
        this.mLabelYMinValue = fMin7;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int iIntValue;
        super.onDraw(canvas);
        this.mPaint.reset();
        List<PointF> list = this.mList;
        if (list == null || list.size() <= 0) {
            return;
        }
        if (this.mViewPointList.size() == 0) {
            ajustData();
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mChatLineWidth);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        int size = this.mViewPointList.size();
        List<Integer> list2 = this.mColorList;
        int size2 = list2 == null ? 0 : list2.size();
        if (size2 == 0) {
            Path path = new Path();
            for (int i = 0; i < size; i++) {
                PointF pointF = this.mViewPointList.get(i);
                if (i == 0) {
                    path.moveTo(pointF.x, pointF.y);
                } else {
                    path.lineTo(pointF.x, pointF.y);
                }
            }
            this.mPaint.setColor(MapHelper.Standard_Color);
            canvas.drawPath(path, this.mPaint);
        } else {
            int i2 = -1;
            int i3 = -1;
            Path path2 = null;
            int i4 = 0;
            while (i4 < size) {
                PointF pointF2 = this.mViewPointList.get(i4);
                if (size2 > i4) {
                    iIntValue = this.mColorList.get(i4).intValue();
                } else {
                    iIntValue = this.mColorList.get(size2 - 1).intValue();
                }
                if (iIntValue != i3) {
                    if (path2 != null) {
                        path2.lineTo(pointF2.x, pointF2.y);
                        this.mPaint.setColor(i3);
                        canvas.drawPath(path2, this.mPaint);
                    }
                    path2 = new Path();
                    path2.moveTo(pointF2.x, pointF2.y);
                    i3 = iIntValue;
                } else if (path2 != null) {
                    path2.lineTo(pointF2.x, pointF2.y);
                }
                i4++;
                i2 = iIntValue;
            }
            if (path2 != null) {
                this.mPaint.setColor(i2);
                canvas.drawPath(path2, this.mPaint);
            }
        }
        int iMax = Math.max(this.mStartCircleRadius + this.mStartCircleBorderWidth, this.mEndCircleRadius + this.mEndCircleBorderWidth);
        if (size > 1) {
            PointF pointF3 = this.mViewPointList.get(0);
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setDither(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mStartCircleBorderColor);
            this.mPaint.setShadowLayer(5.0f, 0.0f, 2.0f, -3355444);
            float f2 = iMax;
            float fMax = Math.max(f2, Math.min(pointF3.x - f2, getWidth() - iMax));
            float fMax2 = Math.max(f2, Math.min(pointF3.y - f2, getWidth() - iMax));
            canvas.drawCircle(fMax, fMax2, this.mStartCircleBorderWidth + this.mStartCircleRadius, this.mPaint);
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setDither(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mStartCircleSolidColor);
            canvas.drawCircle(fMax, fMax2, this.mStartCircleRadius, this.mPaint);
            PointF pointF4 = this.mViewPointList.get(size - 1);
            float fMax3 = Math.max(f2, Math.min(pointF4.x - f2, getWidth() - iMax));
            float fMax4 = Math.max(f2, Math.min(pointF4.y - f2, getWidth() - iMax));
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setDither(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mEndCircleBorderColor);
            this.mPaint.setShadowLayer(5.0f, 0.0f, 2.0f, -3355444);
            canvas.drawCircle(fMax3, fMax4, this.mEndCircleRadius + this.mEndCircleBorderWidth, this.mPaint);
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setDither(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mEndCircleSolidColor);
            canvas.drawCircle(fMax3, fMax4, this.mEndCircleRadius, this.mPaint);
            return;
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mEndCircleBorderColor);
        this.mPaint.setShadowLayer(5.0f, 0.0f, 2.0f, -3355444);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.mStartCircleRadius + this.mStartCircleBorderWidth, this.mPaint);
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mEndCircleSolidColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.mStartCircleRadius, this.mPaint);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0028  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            super.onMeasure(r6, r7)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            float r0 = r5.mLabelXMaxValue
            float r1 = r5.mLabelXMinValue
            float r2 = r0 - r1
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L28
            float r2 = r5.mLabelYMaxValue
            float r4 = r5.mLabelYMinValue
            float r2 = r2 - r4
            float r0 = r0 - r1
            float r2 = r2 / r0
            int r0 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r0 <= 0) goto L28
            float r6 = (float) r6
            float r2 = r2 * r6
            int r6 = java.lang.Math.round(r2)
            goto L29
        L28:
            r6 = r7
        L29:
            int r6 = java.lang.Math.min(r7, r6)
            r5.setMeasuredDimension(r6, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.HomeSportRecordChat.onMeasure(int, int):void");
    }

    public void setLabelXMaxValue(int i) {
        this.mLabelXMaxValue = i;
    }

    public void setLabelXMinValue(int i) {
        this.mLabelXMinValue = i;
    }

    public void setLabelYMaxValue(int i) {
        this.mLabelYMaxValue = i;
    }

    public void setLabelYMinValue(int i) {
        this.mLabelYMinValue = i;
    }

    public void setLabelXCount(int i) {
        this.mLabelXCount = i;
    }

    public void setLabelYCount(int i) {
        this.mLabelYCount = i;
    }

    public void setList(List<PointF> list, List<LatLngBean> list2, int i, int i2, int i3) {
        this.mList = list;
        this.mLatLngList.clear();
        this.mViewPointList.clear();
        if (list2 != null && list2.size() > 0) {
            this.mLatLngList.addAll(list2);
        }
        culSpeedColors(i, i2, i3);
    }

    private void culSpeedColors(int i, int i2, int i3) {
        if (this.mLatLngList.size() < 4) {
            return;
        }
        this.mColorList.clear();
        List<Integer> listCompleteColorByMile = MapHelper.completeColorByMile(this.mLatLngList, i, i2, i3, this.mColorList, null, true);
        if (listCompleteColorByMile == null || listCompleteColorByMile.size() <= 0) {
            return;
        }
        int size = listCompleteColorByMile.size();
        List<Integer> list = this.mColorList;
        int size2 = list == null ? 0 : list.size();
        for (int i4 = 0; i4 < size; i4++) {
            int iIntValue = listCompleteColorByMile.get(i4).intValue();
            if (size2 > iIntValue && iIntValue >= 0) {
                this.mColorList.set(iIntValue, 1295532630);
            }
        }
    }

    public void invalideWithAnimator(boolean z) {
        if (z) {
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null && (valueAnimator.isRunning() || this.mAnimator.isStarted())) {
                this.mAnimator.cancel();
                this.mAnimator = null;
            }
            this.mAnimator = getAnimator();
            this.mAnimator.start();
            return;
        }
        invalidate();
    }

    private ValueAnimator getAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setTarget(this);
        valueAnimator.setDuration(1500L);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setIntValues(100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.charter.-$$Lambda$HomeSportRecordChat$K0a4SZYKKl2oevycRd6Tep6YieE
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getAnimator$0$HomeSportRecordChat(valueAnimator2);
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getAnimator$0$HomeSportRecordChat(ValueAnimator valueAnimator) {
        invalidate();
    }
}