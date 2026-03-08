package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class WeightSeekBar extends AppCompatSeekBar {
    private static final int STYLE_SOLID = 1;
    private static final int STYLE_SOLID_STROKE = 3;
    private static final int STYLE_STROKE = 2;
    private static final String TAG = WeightSeekBar.class.getSimpleName();
    private int mBgRadius;
    private int mCurProgress;
    private float mDownX;
    private float mDownY;
    private boolean mDragable;
    private boolean mDraging;
    private int mInnerCircleColor;
    private int mInnerRadius;
    private boolean mIsThumb;
    private int mMaxProgress;
    private float mMoveX;
    private float mMoveY;
    private int mNormalBgColor;
    private int mOuterCicleColor;
    private int mOuterRadius;
    private Paint mPaint;
    private int mProgressColor;
    private int mProgressHeight;
    private int mScreenWidth;
    private int mThumbLeftPosition;
    private RectF mThumbRect;
    private int mThumbStyle;
    private int mTouchOffset;
    private onRangeListener onRangeListener;

    public interface onRangeListener {
        void onRange(float f2);
    }

    public WeightSeekBar(Context context) {
        this(context, null);
    }

    public WeightSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WeightSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBgRadius = 20;
        this.mNormalBgColor = Color.parseColor("#EFF0F3");
        this.mProgressColor = Color.parseColor("#00CFFF");
        this.mThumbStyle = 3;
        this.mIsThumb = true;
        this.mOuterRadius = 36;
        this.mInnerRadius = 18;
        this.mOuterCicleColor = Color.parseColor("#00CFFF");
        this.mInnerCircleColor = Color.parseColor("#EFF0F3");
        this.mMaxProgress = 100;
        this.mCurProgress = 0;
        this.mDragable = true;
        this.mThumbLeftPosition = 0;
        this.mTouchOffset = 50;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.WeightSeekBar);
        this.mBgRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.mBgRadius);
        this.mNormalBgColor = typedArrayObtainStyledAttributes.getColor(0, this.mNormalBgColor);
        this.mProgressColor = typedArrayObtainStyledAttributes.getColor(6, this.mProgressColor);
        this.mThumbStyle = typedArrayObtainStyledAttributes.getInt(12, this.mThumbStyle);
        this.mIsThumb = typedArrayObtainStyledAttributes.getBoolean(2, true);
        this.mOuterRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, this.mOuterRadius);
        this.mInnerRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.mInnerRadius);
        this.mOuterCicleColor = typedArrayObtainStyledAttributes.getColor(8, this.mOuterCicleColor);
        this.mInnerCircleColor = typedArrayObtainStyledAttributes.getColor(4, this.mInnerCircleColor);
        this.mMaxProgress = typedArrayObtainStyledAttributes.getInt(7, this.mMaxProgress);
        this.mCurProgress = typedArrayObtainStyledAttributes.getInt(10, this.mCurProgress);
        this.mDragable = typedArrayObtainStyledAttributes.getBoolean(1, true);
        this.mProgressHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, (this.mOuterRadius * 2) / 3);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        if (this.mThumbStyle == 1) {
            this.mOuterRadius = 0;
        }
        this.mThumbRect = new RectF();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            size = Math.max(size, Math.min(size, this.mScreenWidth));
        }
        if (mode2 != 1073741824) {
            size2 = this.mOuterRadius * 2;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mNormalBgColor);
        RectF rectF = new RectF();
        rectF.left = this.mOuterRadius;
        rectF.top = (getHeight() - this.mProgressHeight) / 2;
        rectF.right = getWidth() - this.mOuterRadius;
        rectF.bottom = (getHeight() + this.mProgressHeight) / 2;
        int i = this.mBgRadius;
        canvas.drawRoundRect(rectF, i, i, this.mPaint);
        CommonLogUtil.d(TAG, "mCurProgress=" + this.mCurProgress);
        int iMax = Math.max(this.mOuterRadius, this.mInnerRadius);
        if (this.mCurProgress > 0) {
            this.mPaint.reset();
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(this.mProgressColor);
            RectF rectF2 = new RectF();
            rectF2.left = iMax;
            rectF2.top = (getHeight() - this.mProgressHeight) / 2;
            rectF2.right = (float) (((double) iMax) + ((((double) ((getWidth() - (iMax * 2)) * this.mCurProgress)) * 1.0d) / ((double) this.mMaxProgress)));
            rectF2.bottom = (getHeight() + this.mProgressHeight) / 2;
            int i2 = this.mBgRadius;
            canvas.drawRoundRect(rectF2, i2, i2, this.mPaint);
        }
        this.mThumbLeftPosition = Math.max(0, this.mThumbLeftPosition);
        this.mThumbLeftPosition = Math.min(this.mThumbLeftPosition, getWidth() - iMax);
        RectF rectF3 = this.mThumbRect;
        rectF3.left = this.mThumbLeftPosition - this.mTouchOffset;
        rectF3.top = (getHeight() / 2) - this.mTouchOffset;
        this.mThumbRect.bottom = (getHeight() / 2) + this.mTouchOffset;
        RectF rectF4 = this.mThumbRect;
        rectF4.right = rectF4.left + (Math.max(this.mOuterRadius, this.mInnerRadius) * 2) + this.mTouchOffset;
        this.mPaint.reset();
        if (this.mIsThumb) {
            int i3 = this.mThumbStyle;
            if (i3 == 1) {
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setAntiAlias(true);
                this.mPaint.setColor(this.mInnerCircleColor);
                canvas.drawCircle((float) (((double) (this.mThumbLeftPosition + this.mInnerRadius)) + ((((double) ((getWidth() - (this.mInnerRadius * 2)) * this.mCurProgress)) * 1.0d) / ((double) this.mMaxProgress))), getHeight() / 2, this.mInnerRadius, this.mPaint);
                return;
            }
            if (i3 == 2 || i3 == 3) {
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setAntiAlias(true);
                this.mPaint.setColor(this.mOuterCicleColor);
                canvas.drawCircle((float) (((double) (this.mThumbLeftPosition + this.mOuterRadius)) + ((((double) ((getWidth() - (this.mOuterRadius * 2)) * this.mCurProgress)) * 1.0d) / ((double) this.mMaxProgress))), getHeight() / 2, this.mOuterRadius, this.mPaint);
                this.mPaint.setColor(this.mInnerCircleColor);
                canvas.drawCircle((float) (((double) (this.mThumbLeftPosition + this.mOuterRadius)) + ((((double) ((getWidth() - (this.mOuterRadius * 2)) * this.mCurProgress)) * 1.0d) / ((double) this.mMaxProgress))), getHeight() / 2, this.mInnerRadius, this.mPaint);
            }
        }
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mDragable) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mDownX = motionEvent.getX();
                this.mDownY = motionEvent.getY();
                this.mDraging = this.mThumbRect.contains(this.mDownX, this.mDownY);
                CommonLogUtil.d(TAG, "mThumbRect=" + this.mThumbRect.toShortString() + "mDraging=" + this.mDraging + ",mDownX=" + this.mDownX + ",mDownY=" + this.mDownY);
            } else if ((action == 1 || action == 2) && this.mDraging) {
                this.mMoveX = motionEvent.getX();
                this.mThumbLeftPosition = (int) this.mMoveX;
                postInvalidate();
            }
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnRangeListener(onRangeListener onrangelistener) {
        this.onRangeListener = onrangelistener;
    }

    public void setMaxProgress(int i) {
        this.mMaxProgress = i;
        invalidate();
    }

    public void setCurProgress(int i) {
        this.mCurProgress = i;
        invalidate();
    }
}