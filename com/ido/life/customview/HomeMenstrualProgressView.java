package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.ido.life.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeMenstrualProgressView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\bH\u0002J\u0010\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020-H\u0014J\u0018\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u00020\bH\u0014J\u000e\u00101\u001a\u00020(2\u0006\u00102\u001a\u00020\bJ\u000e\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020\bJ\u000e\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/ido/life/customview/HomeMenstrualProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mBgCircleRadius", "", "mBitMapMatrix", "Landroid/graphics/Matrix;", "mCircleColor", "mCircleGirth", "", "mCircleRadius", "<set-?>", "mCurrentProgress", "getProgress", "()I", "setProgress", "(I)V", "mCycleLength", "mFertileLength", "mFertileProgressColor", "mIconScale", "mMenstrualIcon", "Landroid/graphics/Bitmap;", "mMenstrualIconHeight", "mMenstrualLength", "mMenstrualProgressColor", "mMenstrualProgressRadius", "mPaint", "Landroid/graphics/Paint;", "mPerAngle", "mPerAngleHeight", "mPreditColor", "mProgressBgColor", "mProgressSpaceAngle", "mProgressWidth", "initAttributeSet", "", "initBitmap", "iconResId", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setCycleLength", "cycleLength", "setFertileLength", "fertileLength", "setMenstrualLength", "menstrualLength", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HomeMenstrualProgressView extends View {
    private HashMap _$_findViewCache;
    private int mBgCircleRadius;
    private final Matrix mBitMapMatrix;
    private int mCircleColor;
    private float mCircleGirth;
    private int mCircleRadius;
    private int mCurrentProgress;
    private int mCycleLength;
    private int mFertileLength;
    private int mFertileProgressColor;
    private float mIconScale;
    private Bitmap mMenstrualIcon;
    private int mMenstrualIconHeight;
    private int mMenstrualLength;
    private int mMenstrualProgressColor;
    private int mMenstrualProgressRadius;
    private Paint mPaint;
    private float mPerAngle;
    private float mPerAngleHeight;
    private int mPreditColor;
    private int mProgressBgColor;
    private int mProgressSpaceAngle;
    private int mProgressWidth;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeMenstrualProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        this.mCycleLength = 30;
        this.mMenstrualLength = 7;
        this.mFertileLength = 10;
        this.mMenstrualProgressColor = -65536;
        this.mMenstrualProgressRadius = 10;
        this.mProgressSpaceAngle = 5;
        this.mCircleColor = ViewCompat.MEASURED_STATE_MASK;
        this.mFertileProgressColor = -16711681;
        this.mProgressBgColor = -65281;
        this.mPreditColor = -65536;
        this.mProgressWidth = 10;
        this.mPaint = new Paint();
        this.mIconScale = 1.0f;
        this.mBitMapMatrix = new Matrix();
        initAttributeSet(attrs);
        this.mPaint.setDither(true);
        this.mPaint.setAntiAlias(true);
        int i = this.mProgressSpaceAngle;
        this.mPerAngle = (360.0f - (i * (r5 - 1))) / this.mCycleLength;
        this.mCircleGirth = (float) (((double) this.mBgCircleRadius) * 6.283185307179586d);
        this.mPerAngleHeight = this.mCircleGirth / 360.0f;
        Bitmap bitmap = this.mMenstrualIcon;
        if (bitmap != null) {
            if (bitmap == null) {
                Intrinsics.throwNpe();
            }
            int height = bitmap.getHeight();
            int i2 = this.mMenstrualIconHeight;
            if (height > i2) {
                float f2 = i2;
                if (this.mMenstrualIcon == null) {
                    Intrinsics.throwNpe();
                }
                this.mIconScale = f2 / r5.getHeight();
            }
        }
    }

    /* JADX INFO: renamed from: getProgress, reason: from getter */
    public final int getMCurrentProgress() {
        return this.mCurrentProgress;
    }

    public final void setProgress(int i) {
        this.mCurrentProgress = i;
    }

    private final void initAttributeSet(AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.HomeMenstrualProgressView);
        this.mCycleLength = typedArrayObtainStyledAttributes.getInt(3, this.mCycleLength);
        this.mMenstrualLength = typedArrayObtainStyledAttributes.getInt(7, this.mMenstrualLength);
        this.mMenstrualIconHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.mMenstrualIconHeight);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(5, -1);
        if (resourceId != -1) {
            initBitmap(resourceId);
        }
        this.mMenstrualProgressColor = typedArrayObtainStyledAttributes.getColor(10, this.mMenstrualProgressColor);
        this.mMenstrualProgressRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.mMenstrualProgressRadius);
        this.mProgressSpaceAngle = typedArrayObtainStyledAttributes.getInt(12, this.mProgressSpaceAngle);
        this.mCircleColor = typedArrayObtainStyledAttributes.getColor(1, this.mCircleColor);
        this.mFertileProgressColor = typedArrayObtainStyledAttributes.getColor(4, this.mFertileProgressColor);
        this.mProgressBgColor = typedArrayObtainStyledAttributes.getColor(9, this.mProgressBgColor);
        this.mProgressWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, this.mProgressWidth);
        this.mBgCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, this.mBgCircleRadius);
        this.mCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.mCircleRadius);
        this.mPreditColor = typedArrayObtainStyledAttributes.getColor(8, this.mPreditColor);
        typedArrayObtainStyledAttributes.recycle();
    }

    private final void initBitmap(int iconResId) {
        try {
            this.mMenstrualIcon = BitmapFactory.decodeResource(getResources(), iconResId);
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mProgressBgColor);
        this.mPaint.setStrokeWidth(this.mProgressWidth);
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, this.mBgCircleRadius, this.mPaint);
        if (this.mFertileLength > 0) {
            if (this.mCurrentProgress > this.mCycleLength - 9) {
                this.mPaint.setColor(this.mPreditColor);
                this.mPaint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc((getWidth() / 2.0f) - this.mBgCircleRadius, (getHeight() / 2.0f) - this.mBgCircleRadius, this.mBgCircleRadius + (getWidth() / 2.0f), this.mBgCircleRadius + (getHeight() / 2.0f), -90.0f, this.mMenstrualLength * (this.mPerAngle + this.mProgressSpaceAngle), false, this.mPaint);
            }
        } else if (this.mCurrentProgress > this.mMenstrualLength) {
            this.mPaint.setColor(this.mPreditColor);
            this.mPaint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawArc((getWidth() / 2.0f) - this.mBgCircleRadius, (getHeight() / 2.0f) - this.mBgCircleRadius, this.mBgCircleRadius + (getWidth() / 2.0f), this.mBgCircleRadius + (getHeight() / 2.0f), -90.0f, this.mMenstrualLength * (this.mPerAngle + this.mProgressSpaceAngle), false, this.mPaint);
        }
        int i = 1;
        if (this.mFertileLength > 0) {
            this.mPaint.setColor(this.mFertileProgressColor);
            this.mPaint.setStrokeCap(Paint.Cap.ROUND);
            float f2 = this.mPerAngle;
            int i2 = this.mProgressSpaceAngle;
            canvas.drawArc((getWidth() / 2.0f) - this.mBgCircleRadius, (getHeight() / 2.0f) - this.mBgCircleRadius, this.mBgCircleRadius + (getWidth() / 2.0f), this.mBgCircleRadius + (getHeight() / 2.0f), (-90) + ((this.mCycleLength - 18) * (i2 + f2)), (this.mFertileLength - 1) * (f2 + i2), false, this.mPaint);
        }
        int i3 = this.mMenstrualLength;
        int i4 = this.mCurrentProgress;
        if (1 <= i4 && i3 >= i4) {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mMenstrualProgressColor);
            int i5 = this.mCurrentProgress;
            if (1 <= i5) {
                while (true) {
                    float f3 = -((-90) + ((i - 1) * (this.mPerAngle + this.mProgressSpaceAngle)));
                    canvas.rotate(-f3, getWidth() / 2.0f, getHeight() / 2.0f);
                    if (i == this.mCurrentProgress) {
                        float f4 = 2;
                        float height = (getHeight() / 2.0f) - ((this.mPerAngle / f4) * this.mPerAngleHeight);
                        float width = getWidth();
                        float height2 = ((this.mPerAngle / f4) * this.mPerAngleHeight) + (getHeight() / 2.0f);
                        int i6 = this.mMenstrualProgressRadius;
                        canvas.drawRoundRect(((getWidth() / 2.0f) + this.mBgCircleRadius) - (this.mProgressWidth / 2.0f), height, width, height2, i6, i6, this.mPaint);
                    } else {
                        float f5 = 2;
                        float height3 = ((this.mPerAngle / f5) * this.mPerAngleHeight) + (getHeight() / 2.0f);
                        int i7 = this.mMenstrualProgressRadius;
                        canvas.drawRoundRect(((getWidth() / 2.0f) + this.mBgCircleRadius) - (this.mProgressWidth / 2.0f), (getHeight() / 2.0f) - ((this.mPerAngle / f5) * this.mPerAngleHeight), (this.mProgressWidth / 2.0f) + (getWidth() / 2.0f) + this.mBgCircleRadius, height3, i7, i7, this.mPaint);
                    }
                    canvas.rotate(f3, getWidth() / 2.0f, getHeight() / 2.0f);
                    if (i == i5) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        } else {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mCircleColor);
            float f6 = -((-90) + (this.mCurrentProgress * (this.mPerAngle + this.mProgressSpaceAngle)));
            canvas.rotate(-f6, getWidth() / 2.0f, getHeight() / 2.0f);
            canvas.drawCircle((getWidth() / 2.0f) + this.mBgCircleRadius, getHeight() / 2.0f, this.mCircleRadius, this.mPaint);
            canvas.rotate(f6, getWidth() / 2.0f, getHeight() / 2.0f);
        }
        if (this.mMenstrualIcon != null) {
            this.mBitMapMatrix.reset();
            Matrix matrix = this.mBitMapMatrix;
            float width2 = getWidth() / 2.0f;
            if (this.mMenstrualIcon == null) {
                Intrinsics.throwNpe();
            }
            float f7 = 2;
            float width3 = width2 - ((r3.getWidth() * this.mIconScale) / f7);
            float height4 = getHeight() / 2.0f;
            if (this.mMenstrualIcon == null) {
                Intrinsics.throwNpe();
            }
            matrix.postTranslate(width3, height4 - ((r5.getHeight() * this.mIconScale) / f7));
            Matrix matrix2 = this.mBitMapMatrix;
            float f8 = this.mIconScale;
            matrix2.postScale(f8, f8);
            Bitmap bitmap = this.mMenstrualIcon;
            if (bitmap == null) {
                Intrinsics.throwNpe();
            }
            canvas.drawBitmap(bitmap, this.mBitMapMatrix, this.mPaint);
        }
    }

    public final void setCycleLength(int cycleLength) {
        if (this.mCycleLength == cycleLength) {
            return;
        }
        this.mCycleLength = cycleLength;
        this.mPerAngle = (360.0f - (this.mProgressSpaceAngle * (cycleLength - 1))) / cycleLength;
    }

    public final void setMenstrualLength(int menstrualLength) {
        this.mMenstrualLength = menstrualLength;
    }

    public final void setFertileLength(int fertileLength) {
        this.mFertileLength = fertileLength;
    }
}