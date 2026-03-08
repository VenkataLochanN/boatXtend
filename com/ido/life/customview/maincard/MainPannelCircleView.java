package com.ido.life.customview.maincard;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class MainPannelCircleView extends View {
    private static final String TAG = MainPannelCircleView.class.getSimpleName();
    private int mBackGroundColor;
    private Bitmap mBitmap;
    private int mCurProgress;
    private int mIconPaddingBottom;
    private int mIconPaddingLeft;
    private int mIconPaddingRight;
    private int mIconPaddingTop;
    private int mIconResId;
    private int mIndeterMinateColor;
    private int mIndeterMinateWidth;
    private int mMaxProgress;
    private ObjectAnimator mObjectAnimator;
    private Paint mPaint;
    private int mProgressColor;
    private int mProgressEndColor;
    private boolean mProgressShaderEnabled;
    private int mProgressStartColor;
    private int mProgressWidth;
    private int mScreenHeight;
    private int mScreenWidth;

    public MainPannelCircleView(Context context) {
        this(context, null);
    }

    public MainPannelCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public MainPannelCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurProgress = 0;
        this.mMaxProgress = 100;
        this.mIconResId = -1;
        this.mProgressColor = -7829368;
        this.mIndeterMinateColor = -65536;
        this.mProgressWidth = 2;
        this.mIndeterMinateWidth = 3;
        this.mIconPaddingLeft = 2;
        this.mIconPaddingTop = 2;
        this.mIconPaddingRight = 2;
        this.mIconPaddingBottom = 2;
        this.mProgressStartColor = Color.parseColor("#FF5158");
        this.mProgressEndColor = Color.parseColor("#EE1E26");
        this.mProgressShaderEnabled = false;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MainPannelCircleView);
        this.mCurProgress = typedArrayObtainStyledAttributes.getInt(6, this.mCurProgress);
        this.mMaxProgress = typedArrayObtainStyledAttributes.getInt(11, this.mMaxProgress);
        this.mIconResId = typedArrayObtainStyledAttributes.getResourceId(1, this.mIconResId);
        this.mProgressColor = typedArrayObtainStyledAttributes.getColor(7, this.mProgressColor);
        this.mIndeterMinateColor = typedArrayObtainStyledAttributes.getColor(9, this.mIndeterMinateColor);
        this.mProgressWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(14, this.mProgressWidth);
        this.mIndeterMinateWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, this.mIndeterMinateWidth);
        this.mIconPaddingLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mIconPaddingLeft);
        this.mIconPaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.mIconPaddingTop);
        this.mIconPaddingRight = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.mIconPaddingRight);
        this.mIconPaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.mIconPaddingBottom);
        this.mProgressStartColor = typedArrayObtainStyledAttributes.getColor(13, this.mProgressStartColor);
        this.mProgressEndColor = typedArrayObtainStyledAttributes.getColor(8, this.mProgressEndColor);
        this.mProgressShaderEnabled = typedArrayObtainStyledAttributes.getBoolean(12, this.mProgressShaderEnabled);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        this.mScreenHeight = getResources().getDisplayMetrics().heightPixels;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mProgressColor);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(this.mProgressWidth);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, (getWidth() / 2) - this.mProgressWidth, this.mPaint);
        if (this.mCurProgress > 0) {
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.mIndeterMinateWidth);
            if (this.mProgressShaderEnabled) {
                this.mPaint.setShader(new SweepGradient(getWidth() / 2, getHeight() / 2, this.mProgressStartColor, this.mProgressEndColor));
                this.mPaint.setColor(this.mProgressStartColor);
            } else {
                this.mPaint.setColor(this.mIndeterMinateColor);
            }
            int i = this.mIndeterMinateWidth;
            canvas.drawArc(i, i, getWidth() - this.mIndeterMinateWidth, getHeight() - this.mIndeterMinateWidth, 270.0f, (this.mCurProgress * 360.0f) / this.mMaxProgress, false, this.mPaint);
        }
        if (this.mBitmap == null) {
            this.mBitmap = getBitmap(this.mIconResId, getWidth(), getHeight());
        }
        if (this.mBitmap != null) {
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            Path path = new Path();
            path.addCircle(getWidth() / 2, getWidth() / 2, (((getWidth() - (Math.max(this.mProgressWidth, this.mIndeterMinateWidth) * 2)) - this.mIconPaddingLeft) - this.mIconPaddingRight) / 2, Path.Direction.CW);
            canvas.clipPath(path);
            canvas.save();
            Matrix matrix = new Matrix();
            float fSqrt = (float) Math.sqrt(((r1 * 4) * r1) / 2);
            if (this.mBitmap.getWidth() > fSqrt && fSqrt < this.mBitmap.getWidth()) {
                float width = fSqrt / this.mBitmap.getWidth();
                matrix.postScale(width, width);
                float f2 = fSqrt / 2.0f;
                matrix.postTranslate((getWidth() / 2) - f2, (getHeight() / 2) - f2);
            } else {
                matrix.postTranslate((getWidth() / 2) - (this.mBitmap.getWidth() / 2), (getHeight() / 2) - (this.mBitmap.getHeight() / 2));
            }
            canvas.drawBitmap(this.mBitmap, matrix, this.mPaint);
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        Bitmap bitmap = getBitmap(this.mIconResId, size, size2);
        if (mode != 1073741824) {
            size = bitmap.getWidth() + (Math.max(this.mProgressWidth, this.mIndeterMinateWidth) * 2) + this.mIconPaddingLeft + this.mIconPaddingRight;
        }
        if (mode2 != 1073741824) {
            size2 = bitmap.getHeight() + (Math.max(this.mProgressWidth, this.mIndeterMinateWidth) * 2) + this.mIconPaddingTop + this.mIconPaddingBottom;
        }
        int iMax = Math.max(size, size2);
        setMeasuredDimension(iMax, iMax);
    }

    private Bitmap getBitmap(int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i4 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), i, options);
        while (true) {
            if (options.outWidth / i4 <= i2 && options.outHeight / i4 <= i3) {
                break;
            }
            i4 *= 2;
        }
        if (i4 > 2) {
            i4 /= 2;
        }
        options.inSampleSize = i4;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), i, options);
    }

    public void setCenterImageRes(int i) {
        if (this.mIconResId == i) {
            return;
        }
        this.mIconResId = i;
        this.mBitmap = getBitmap(i, this.mScreenWidth, this.mScreenHeight);
        invalidate();
    }

    public void setProgressWidth(int i) {
        if (i < 0 || this.mProgressWidth == i) {
            return;
        }
        this.mProgressWidth = i;
        invalidate();
    }

    public void setIndeterminateWidth(int i) {
        if (i < 0 || this.mIndeterMinateWidth == i) {
            return;
        }
        this.mIndeterMinateWidth = i;
        invalidate();
    }

    public int getProgressWidth() {
        return this.mProgressWidth;
    }

    public int getIndeterminateWidth() {
        return this.mIndeterMinateWidth;
    }

    public void setProgress(int i) {
        if (i == this.mCurProgress) {
            return;
        }
        int i2 = this.mMaxProgress;
        if (i <= i2) {
            this.mCurProgress = i;
        } else {
            this.mCurProgress = i2;
        }
        invalidate();
    }

    public void setMaxProgress(int i) {
        if (i < 1 || i == this.mMaxProgress) {
            return;
        }
        this.mMaxProgress = i;
        int i2 = this.mCurProgress;
        int i3 = this.mMaxProgress;
        if (i2 > i3) {
            this.mCurProgress = i3;
        }
        invalidate();
    }

    public void setScaleXY(float f2) {
        setScaleX(f2);
        setScaleY(f2);
    }

    public void startAnim() {
        ObjectAnimator objectAnimator = this.mObjectAnimator;
        if (objectAnimator != null && (objectAnimator.isRunning() || this.mObjectAnimator.isStarted())) {
            this.mObjectAnimator.cancel();
        }
        if (this.mObjectAnimator == null) {
            this.mObjectAnimator = getObjectAnimator();
        }
        this.mObjectAnimator.start();
    }

    private ObjectAnimator getObjectAnimator() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "scaleXY", 1.0f, 1.2f, 1.0f);
        objectAnimatorOfFloat.setDuration(300L);
        return objectAnimatorOfFloat;
    }

    public int getProgress() {
        return this.mCurProgress;
    }

    public int getMaxProgress() {
        return this.mMaxProgress;
    }
}