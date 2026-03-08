package com.ido.life.customview.charter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.ido.life.R;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HomeStepCharterBar extends View {
    private ValueAnimator mAnimator;
    private int mAnimatorValue;
    private int mBarEndColor;
    private int mBarRadius;
    private int mBarStartColor;
    private float mBarWidth;
    private float mLabelTextSpace;
    private Paint mPaint;
    private List<Point> mValues;
    private CharSequence[] mXLabelArray;
    private int mXLabelCount;
    private int mXLabelLineColor;
    private float mXLabelLineRadius;
    private int mXLabelTextColor;
    private float mXLabelTextSize;
    private int mYLabelCount;
    private int mYLabelMaxValue;
    private int mYLabelMinValue;

    public HomeStepCharterBar(Context context) {
        this(context, null);
    }

    public HomeStepCharterBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HomeStepCharterBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.HomeStepCharterBar);
        this.mXLabelArray = typedArrayObtainStyledAttributes.getTextArray(4);
        this.mXLabelTextColor = typedArrayObtainStyledAttributes.getColor(12, -7829368);
        this.mXLabelTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 24);
        this.mXLabelCount = typedArrayObtainStyledAttributes.getInt(5, 24);
        this.mXLabelLineColor = typedArrayObtainStyledAttributes.getColor(6, -7829368);
        this.mXLabelLineRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 5);
        this.mBarWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 6);
        this.mBarStartColor = typedArrayObtainStyledAttributes.getColor(2, Color.parseColor("#FF5158"));
        this.mBarEndColor = typedArrayObtainStyledAttributes.getColor(0, Color.parseColor("#EE1E26"));
        this.mBarRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 5);
        this.mLabelTextSpace = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, 5);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLabelXLine(canvas);
        drawLabelText(canvas);
        drawBar(canvas);
    }

    private void drawBar(Canvas canvas) {
        int iSave;
        List<Point> list = this.mValues;
        if (list == null || list.size() == 0) {
            return;
        }
        int size = this.mValues.size();
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mBarStartColor);
        this.mPaint.setAntiAlias(true);
        float height = ((getHeight() - getPaddingBottom()) - measureTextHeight()) - this.mLabelTextSpace;
        float f2 = height / (this.mYLabelMaxValue - this.mYLabelMinValue);
        float f3 = 2.0f;
        float width = (getWidth() - ((this.mXLabelLineRadius * 2.0f) * this.mXLabelCount)) / (r4 - 1);
        this.mPaint.setShader(new LinearGradient(0.0f, 0.0f, getWidth(), getHeight() - getPaddingBottom(), this.mBarStartColor, this.mBarEndColor, Shader.TileMode.CLAMP));
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            iSave = Integer.MIN_VALUE;
        } else {
            canvas.clipRect(0.0f, height - ((this.mAnimatorValue * height) / 100.0f), getWidth(), height);
            iSave = canvas.save();
        }
        int i = 0;
        while (i < size) {
            Point point = this.mValues.get(i);
            float f4 = this.mXLabelLineRadius;
            float f5 = (((f4 * f3) + width) * i) - ((this.mBarWidth - (f4 * f3)) / f3);
            float f6 = height - (point.y * f2);
            float f7 = f5 + this.mBarWidth;
            int i2 = this.mBarRadius;
            canvas.drawRoundRect(f5, f6, f7, height, i2, i2, this.mPaint);
            i++;
            iSave = iSave;
            f3 = 2.0f;
        }
        int i3 = iSave;
        if (i3 != Integer.MIN_VALUE) {
            canvas.restoreToCount(i3);
        }
    }

    private void drawLabelXLine(Canvas canvas) {
        if (this.mXLabelCount <= 0) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mXLabelLineColor);
        this.mPaint.setAntiAlias(true);
        float f2 = this.mBarWidth - (this.mXLabelLineRadius * 2.0f);
        float width = ((getWidth() - ((this.mXLabelLineRadius * 2.0f) * this.mXLabelCount)) - f2) / (r5 - 1);
        float fMeasureTextHeight = measureTextHeight();
        for (int i = 0; i < this.mXLabelCount; i++) {
            float f3 = this.mXLabelLineRadius;
            float f4 = (f2 / 2.0f) + (((f3 * 2.0f) + width) * i) + f3;
            float height = getHeight() - getPaddingBottom();
            float f5 = this.mXLabelLineRadius;
            canvas.drawCircle(f4, ((height - f5) - fMeasureTextHeight) - this.mLabelTextSpace, f5, this.mPaint);
        }
    }

    private float measureTextHeight() {
        Paint paint = new Paint();
        paint.setTextSize(this.mXLabelTextSize);
        paint.getTextBounds("12:00", 0, 5, new Rect());
        return r1.height();
    }

    private float measureTextWidth(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0f;
        }
        return this.mPaint.measureText(str);
    }

    private void drawLabelText(Canvas canvas) {
        CharSequence[] charSequenceArr = this.mXLabelArray;
        if (charSequenceArr == null || charSequenceArr.length == 0) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mXLabelTextColor);
        this.mPaint.setTextSize(this.mXLabelTextSize);
        this.mPaint.setTextAlign(Paint.Align.LEFT);
        int length = this.mXLabelArray.length;
        measureTextHeight();
        if (length == 1) {
            String string = this.mXLabelArray[0].toString();
            canvas.drawText(string, (getWidth() / 2) - (measureTextWidth(string) / 2.0f), getHeight() - getPaddingBottom(), this.mPaint);
            return;
        }
        int i = length - 1;
        float width = getWidth() / i;
        for (int i2 = 0; i2 < length; i2++) {
            String string2 = this.mXLabelArray[i2].toString();
            float fMeasureTextWidth = measureTextWidth(string2);
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(com.boat.Xtend.two.R.dimen.sw_dp_2);
            if (i2 == 0) {
                canvas.drawText(string2, 0.0f, (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
            } else if (i2 == i) {
                canvas.drawText(string2, getWidth() - fMeasureTextWidth, (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
            } else {
                canvas.drawText(string2, (i2 * width) - (fMeasureTextWidth / 2.0f), (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            size = (int) ((this.mBarWidth * (r0 - 1)) + (this.mXLabelCount * this.mXLabelLineRadius * 2.0f));
        }
        setMeasuredDimension(size, size2);
    }

    public void refreshChart(boolean z) {
        releaseAnimator();
        if (z) {
            this.mAnimatorValue = 0;
            this.mAnimator = getAnimator();
            this.mAnimator.start();
            return;
        }
        invalidate();
    }

    private void releaseAnimator() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null && (valueAnimator.isStarted() || this.mAnimator.isRunning())) {
            this.mAnimator.cancel();
        }
        this.mAnimator = null;
    }

    public void setXLabelArray(String[] strArr) {
        this.mXLabelArray = strArr;
    }

    public void setXLabelTextColor(int i) {
        this.mXLabelTextColor = i;
    }

    public void setXLabelTextSize(float f2) {
        this.mXLabelTextSize = f2;
    }

    public void setXLabelCount(int i) {
        this.mXLabelCount = i;
    }

    public void setXLabelLineColor(int i) {
        this.mXLabelLineColor = i;
    }

    public void setXLabelLineRadius(float f2) {
        this.mXLabelLineRadius = f2;
    }

    public void setBarWidth(float f2) {
        this.mBarWidth = f2;
    }

    public void setBarStartColor(int i) {
        this.mBarStartColor = i;
    }

    public void setBarEndColor(int i) {
        this.mBarEndColor = i;
    }

    public void setBarRadius(int i) {
        this.mBarRadius = i;
    }

    public void setYLabelMaxValue(int i) {
        this.mYLabelMaxValue = i;
    }

    public void setYLabelMinValue(int i) {
        this.mYLabelMinValue = i;
    }

    public void setYLabelCount(int i) {
        this.mYLabelCount = i;
    }

    public void setValues(List<Point> list) {
        this.mValues = list;
    }

    public List<Point> getValues() {
        return this.mValues;
    }

    private ValueAnimator getAnimator() {
        final ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(500L);
        valueAnimator.setTarget(this);
        valueAnimator.setIntValues(100);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.charter.-$$Lambda$HomeStepCharterBar$sPc-Y0U03RhnQ7zw7jEgZYPy08c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getAnimator$0$HomeStepCharterBar(valueAnimator, valueAnimator2);
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getAnimator$0$HomeStepCharterBar(ValueAnimator valueAnimator, ValueAnimator valueAnimator2) {
        this.mAnimatorValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        invalidate();
    }
}