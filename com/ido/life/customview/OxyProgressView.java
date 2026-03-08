package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class OxyProgressView extends View {
    private int mCurProgress;
    private int mIndeterminateColor;
    private int mIndeterminateHeight;
    private int mIndeterminateWidth;
    private int mLeftColor;
    private int mMaxProgress;
    private int mPadding;
    private Paint mPaint;
    private int mPercentLeft;
    private int mPercentRight;
    private int mProgressHeight;
    private int mRadiusX;
    private int mRadiusY;
    private int mRightColor;
    private int mScreenWidth;

    public OxyProgressView(Context context) {
        this(context, null);
    }

    public OxyProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public OxyProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRadiusX = 20;
        this.mRadiusY = 20;
        this.mLeftColor = Color.parseColor("#FC5D68");
        this.mRightColor = Color.parseColor("#33E9F2");
        this.mProgressHeight = 20;
        this.mIndeterminateColor = this.mRightColor;
        this.mIndeterminateWidth = 30;
        this.mIndeterminateHeight = 30;
        this.mMaxProgress = 100;
        this.mPadding = 5;
        init(attributeSet);
    }

    public void setProgress(int i) {
        if (i == this.mCurProgress) {
            return;
        }
        if (i < 0) {
            i = 0;
        } else {
            int i2 = this.mMaxProgress;
            if (i > i2) {
                i = i2;
            }
        }
        this.mCurProgress = i;
        invalidate();
    }

    public int getProgress() {
        return this.mCurProgress;
    }

    public void setMaxProgress(int i) {
        if (i == this.mMaxProgress || i < 0) {
            return;
        }
        this.mMaxProgress = i;
        invalidate();
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.OxyProgressView);
        this.mRadiusX = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.mRadiusX);
        this.mRadiusY = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, this.mRadiusY);
        this.mLeftColor = typedArrayObtainStyledAttributes.getColor(0, this.mLeftColor);
        this.mRightColor = typedArrayObtainStyledAttributes.getColor(1, this.mRightColor);
        this.mProgressHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, this.mProgressHeight);
        this.mIndeterminateColor = typedArrayObtainStyledAttributes.getColor(2, this.mIndeterminateColor);
        this.mIndeterminateWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.mIndeterminateWidth);
        this.mIndeterminateHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mIndeterminateHeight);
        this.mCurProgress = typedArrayObtainStyledAttributes.getInt(9, this.mCurProgress);
        this.mMaxProgress = typedArrayObtainStyledAttributes.getInt(5, this.mMaxProgress);
        this.mPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.mPadding);
        this.mPercentLeft = typedArrayObtainStyledAttributes.getInt(7, 75);
        this.mPercentRight = typedArrayObtainStyledAttributes.getInt(8, 25);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        setLayerType(1, null);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f2 = (float) ((((double) this.mCurProgress) * 1.0d) / ((double) this.mMaxProgress));
        this.mPaint.setColor(this.mIndeterminateColor);
        Path path = new Path();
        int width = getWidth();
        int i = this.mIndeterminateWidth;
        path.moveTo(((width - i) * f2) + (i / 2), this.mProgressHeight + this.mPadding);
        int width2 = getWidth();
        path.lineTo(((width2 - r5) * f2) + this.mIndeterminateWidth, this.mProgressHeight + this.mPadding + this.mIndeterminateHeight);
        path.lineTo((getWidth() - this.mIndeterminateWidth) * f2, this.mProgressHeight + this.mPadding + this.mIndeterminateHeight);
        path.close();
        canvas.drawPath(path, this.mPaint);
        this.mPaint.reset();
        Path path2 = new Path();
        path2.addRoundRect(this.mIndeterminateWidth / 2, 0.0f, getWidth() - (this.mIndeterminateWidth / 2), this.mProgressHeight, this.mRadiusX, this.mRadiusY, Path.Direction.CW);
        canvas.clipPath(path2);
        int iSave = canvas.save();
        float f3 = (float) ((((double) this.mPercentLeft) * 1.0d) / 100.0d);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mLeftColor);
        int i2 = this.mIndeterminateWidth;
        canvas.drawRect(i2 / 2, 0.0f, (i2 / 2) + ((getWidth() - this.mIndeterminateWidth) * f3), this.mProgressHeight, this.mPaint);
        this.mPaint.setColor(this.mRightColor);
        canvas.drawRect((this.mIndeterminateWidth / 2) + ((getWidth() - this.mIndeterminateWidth) * f3), 0.0f, getWidth() - (this.mIndeterminateWidth / 2), this.mProgressHeight, this.mPaint);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = this.mScreenWidth;
        }
        if (mode2 != 1073741824) {
            size2 = this.mProgressHeight + this.mPadding + this.mIndeterminateHeight;
        }
        setMeasuredDimension(size, size2);
    }
}