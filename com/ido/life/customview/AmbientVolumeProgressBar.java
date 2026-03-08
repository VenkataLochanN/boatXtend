package com.ido.life.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.view.ViewCompat;
import com.ido.life.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmbientVolumeProgressBar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010;\u001a\u00020<H\u0002J\b\u0010=\u001a\u00020\u0011H\u0002J\b\u0010>\u001a\u00020\bH\u0002J\u0012\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0014J\u0018\u0010C\u001a\u00020@2\u0006\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020\nH\u0014J\u000e\u0010F\u001a\u00020@2\u0006\u0010G\u001a\u00020<J\b\u0010H\u001a\u00020@H\u0002J\b\u0010I\u001a\u00020@H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0012\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\r\"\u0004\b\u0014\u0010\u000fR&\u0010\u0015\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR&\u0010\u0019\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\u00188G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR&\u0010\u001e\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010\u000fR&\u0010!\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\r\"\u0004\b#\u0010\u000fR&\u0010$\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\r\"\u0004\b&\u0010\u000fR&\u0010'\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\r\"\u0004\b)\u0010\u000fR&\u0010*\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\r\"\u0004\b,\u0010\u000fR&\u0010-\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\r\"\u0004\b/\u0010\u000fR&\u00100\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\r\"\u0004\b2\u0010\u000fR\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R&\u00105\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\r\"\u0004\b7\u0010\u000fR&\u00108\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\r\"\u0004\b:\u0010\u000f¨\u0006J"}, d2 = {"Lcom/ido/life/customview/AmbientVolumeProgressBar;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mAnimator", "Landroid/animation/ValueAnimator;", "<set-?>", "", "mAnimatorDuration", "getAnimatorDuration", "()I", "setAnimatorDuration", "(I)V", "mAnimatorPercent", "", "mBackgroundColor", "getBackGroundColor", "setBackGroundColor", "mCurrentProgress", "getProgress", "setProgress", "", "mLeftLabel", "getLeftLabel", "()Ljava/lang/String;", "setLeftLabel", "(Ljava/lang/String;)V", "mLeftLabelColor", "getLeftLabelColor", "setLeftLabelColor", "mLeftLabelPaddingBottom", "getLeftLabelPaddingBottom", "setLeftLabelPaddingBottom", "mLeftLabelPaddingLeft", "getLeftLabelPaddingLeft", "setLeftLabelPaddingLeft", "mLeftLabelPaddingRight", "getLeftLabelPaddingRight", "setLeftLabelPaddingRight", "mLeftLabelPaddingTop", "getLeftLabelPaddingTop", "setLeftLabelPaddingTop", "mLeftLabelSize", "getLeftLabelSize", "setLeftLabelSize", "mMaxProgress", "getMaxProgress", "setMaxProgress", "mPaint", "Landroid/graphics/Paint;", "mProgressColor", "getProgressColor", "setProgressColor", "mProgressRadius", "getProgressRadius", "setProgressRadius", "animatorRunning", "", "caluteAnimatorDuration", "getAnimator", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "refresh", "animator", "startAnimator", "stopAnimator", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeProgressBar extends View {
    private HashMap _$_findViewCache;
    private ValueAnimator mAnimator;
    private int mAnimatorDuration;
    private float mAnimatorPercent;
    private int mBackgroundColor;
    private int mCurrentProgress;
    private String mLeftLabel;
    private int mLeftLabelColor;
    private int mLeftLabelPaddingBottom;
    private int mLeftLabelPaddingLeft;
    private int mLeftLabelPaddingRight;
    private int mLeftLabelPaddingTop;
    private int mLeftLabelSize;
    private int mMaxProgress;
    private Paint mPaint;
    private int mProgressColor;
    private int mProgressRadius;

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
    public AmbientVolumeProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.mProgressRadius = 5;
        this.mBackgroundColor = -3355444;
        this.mProgressColor = -65536;
        this.mLeftLabel = "";
        this.mLeftLabelSize = 12;
        this.mLeftLabelColor = ViewCompat.MEASURED_STATE_MASK;
        this.mMaxProgress = 100;
        this.mLeftLabelPaddingLeft = 4;
        this.mLeftLabelPaddingRight = 4;
        this.mLeftLabelPaddingTop = 4;
        this.mLeftLabelPaddingBottom = 4;
        this.mAnimatorDuration = 2000;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AmbientVolumeProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…AmbientVolumeProgressBar)");
        this.mProgressRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, this.mProgressRadius);
        this.mBackgroundColor = typedArrayObtainStyledAttributes.getColor(1, this.mBackgroundColor);
        this.mProgressColor = typedArrayObtainStyledAttributes.getColor(2, this.mProgressColor);
        String string = typedArrayObtainStyledAttributes.getString(5);
        String str = string;
        if (!(str == null || str.length() == 0)) {
            this.mLeftLabel = string;
        }
        this.mLeftLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.mLeftLabelSize);
        this.mLeftLabelColor = typedArrayObtainStyledAttributes.getColor(6, this.mLeftLabelColor);
        this.mMaxProgress = typedArrayObtainStyledAttributes.getInteger(12, this.mMaxProgress);
        this.mCurrentProgress = typedArrayObtainStyledAttributes.getInteger(3, this.mCurrentProgress);
        this.mLeftLabelPaddingLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, this.mLeftLabelPaddingLeft);
        this.mLeftLabelPaddingRight = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, this.mLeftLabelPaddingRight);
        this.mLeftLabelPaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, this.mLeftLabelPaddingTop);
        this.mLeftLabelPaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, this.mLeftLabelPaddingBottom);
        this.mAnimatorDuration = typedArrayObtainStyledAttributes.getInteger(0, this.mAnimatorDuration);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mPaint.setDither(true);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        setLayerType(1, this.mPaint);
    }

    /* JADX INFO: renamed from: getProgressRadius, reason: from getter */
    public final int getMProgressRadius() {
        return this.mProgressRadius;
    }

    public final void setProgressRadius(int i) {
        this.mProgressRadius = i;
    }

    /* JADX INFO: renamed from: getBackGroundColor, reason: from getter */
    public final int getMBackgroundColor() {
        return this.mBackgroundColor;
    }

    public final void setBackGroundColor(int i) {
        this.mBackgroundColor = i;
    }

    /* JADX INFO: renamed from: getProgressColor, reason: from getter */
    public final int getMProgressColor() {
        return this.mProgressColor;
    }

    public final void setProgressColor(int i) {
        this.mProgressColor = i;
    }

    /* JADX INFO: renamed from: getLeftLabel, reason: from getter */
    public final String getMLeftLabel() {
        return this.mLeftLabel;
    }

    public final void setLeftLabel(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mLeftLabel = str;
    }

    /* JADX INFO: renamed from: getLeftLabelSize, reason: from getter */
    public final int getMLeftLabelSize() {
        return this.mLeftLabelSize;
    }

    public final void setLeftLabelSize(int i) {
        this.mLeftLabelSize = i;
    }

    /* JADX INFO: renamed from: getLeftLabelColor, reason: from getter */
    public final int getMLeftLabelColor() {
        return this.mLeftLabelColor;
    }

    public final void setLeftLabelColor(int i) {
        this.mLeftLabelColor = i;
    }

    /* JADX INFO: renamed from: getMaxProgress, reason: from getter */
    public final int getMMaxProgress() {
        return this.mMaxProgress;
    }

    public final void setMaxProgress(int i) {
        this.mMaxProgress = i;
    }

    /* JADX INFO: renamed from: getProgress, reason: from getter */
    public final int getMCurrentProgress() {
        return this.mCurrentProgress;
    }

    public final void setProgress(int i) {
        this.mCurrentProgress = i;
    }

    /* JADX INFO: renamed from: getLeftLabelPaddingLeft, reason: from getter */
    public final int getMLeftLabelPaddingLeft() {
        return this.mLeftLabelPaddingLeft;
    }

    public final void setLeftLabelPaddingLeft(int i) {
        this.mLeftLabelPaddingLeft = i;
    }

    /* JADX INFO: renamed from: getLeftLabelPaddingRight, reason: from getter */
    public final int getMLeftLabelPaddingRight() {
        return this.mLeftLabelPaddingRight;
    }

    public final void setLeftLabelPaddingRight(int i) {
        this.mLeftLabelPaddingRight = i;
    }

    /* JADX INFO: renamed from: getLeftLabelPaddingTop, reason: from getter */
    public final int getMLeftLabelPaddingTop() {
        return this.mLeftLabelPaddingTop;
    }

    public final void setLeftLabelPaddingTop(int i) {
        this.mLeftLabelPaddingTop = i;
    }

    /* JADX INFO: renamed from: getLeftLabelPaddingBottom, reason: from getter */
    public final int getMLeftLabelPaddingBottom() {
        return this.mLeftLabelPaddingBottom;
    }

    public final void setLeftLabelPaddingBottom(int i) {
        this.mLeftLabelPaddingBottom = i;
    }

    /* JADX INFO: renamed from: getAnimatorDuration, reason: from getter */
    public final int getMAnimatorDuration() {
        return this.mAnimatorDuration;
    }

    public final void setAnimatorDuration(int i) {
        this.mAnimatorDuration = i;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setColor(this.mBackgroundColor);
        if (canvas != null) {
            float width = getWidth();
            float height = getHeight();
            int i = this.mProgressRadius;
            canvas.drawRoundRect(0.0f, 0.0f, width, height, i, i, this.mPaint);
        }
        Integer numValueOf = Integer.MIN_VALUE;
        if (animatorRunning() && this.mAnimatorPercent < 1.0f) {
            numValueOf = canvas != null ? Integer.valueOf(canvas.getSaveCount()) : null;
            if (canvas != null) {
                canvas.clipRect(0.0f, 0.0f, (((getWidth() * this.mCurrentProgress) * 1.0f) / this.mMaxProgress) * this.mAnimatorPercent, getHeight());
            }
        }
        this.mPaint.setColor(this.mProgressColor);
        if (canvas != null) {
            float height2 = getHeight();
            int i2 = this.mProgressRadius;
            canvas.drawRoundRect(0.0f, 0.0f, ((this.mCurrentProgress * 1.0f) / this.mMaxProgress) * getWidth(), height2, i2, i2, this.mPaint);
        }
        if (this.mLeftLabel.length() > 0) {
            this.mPaint.setColor(this.mLeftLabelColor);
            this.mPaint.setTextSize(this.mLeftLabelSize);
            Rect rect = new Rect();
            this.mPaint.getFontMetrics();
            Paint paint = this.mPaint;
            String str = this.mLeftLabel;
            paint.getTextBounds(str, 0, str.length(), rect);
            if (canvas != null) {
                canvas.drawText(this.mLeftLabel, this.mLeftLabelPaddingLeft, getHeight() - this.mLeftLabelPaddingBottom, this.mPaint);
            }
        }
        if ((numValueOf != null && numValueOf.intValue() == Integer.MIN_VALUE) || canvas == null) {
            return;
        }
        if (numValueOf == null) {
            Intrinsics.throwNpe();
        }
        canvas.restoreToCount(numValueOf.intValue());
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode2 != 1073741824) {
            this.mPaint.setTextSize(this.mLeftLabelSize);
            Rect rect = new Rect();
            this.mPaint.getTextBounds("sample", 0, 6, rect);
            size2 = rect.height() + this.mLeftLabelPaddingTop + this.mLeftLabelPaddingBottom;
        }
        if (mode != 1073741824) {
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            size = resources.getDisplayMetrics().widthPixels;
        }
        setMeasuredDimension(size, size2);
    }

    public final void refresh(boolean animator) {
        stopAnimator();
        this.mAnimatorPercent = 1.0f;
        if (animator) {
            startAnimator();
        } else {
            invalidate();
        }
    }

    private final void startAnimator() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null) {
            this.mAnimator = getAnimator();
        } else if (valueAnimator != null) {
            valueAnimator.setDuration((long) caluteAnimatorDuration());
        }
        ValueAnimator valueAnimator2 = this.mAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.start();
        }
    }

    private final void stopAnimator() {
        if (animatorRunning()) {
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator.cancel();
        }
    }

    private final boolean animatorRunning() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            if (valueAnimator == null) {
                Intrinsics.throwNpe();
            }
            if (!valueAnimator.isRunning()) {
                ValueAnimator valueAnimator2 = this.mAnimator;
                if (valueAnimator2 == null) {
                    Intrinsics.throwNpe();
                }
                if (valueAnimator2.isStarted()) {
                }
            }
            return true;
        }
        return false;
    }

    private final ValueAnimator getAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration((long) caluteAnimatorDuration());
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.AmbientVolumeProgressBar.getAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                AmbientVolumeProgressBar ambientVolumeProgressBar = AmbientVolumeProgressBar.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue != null) {
                    ambientVolumeProgressBar.mAnimatorPercent = ((Float) animatedValue).floatValue();
                    AmbientVolumeProgressBar.this.invalidate();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
            }
        });
        return valueAnimator;
    }

    private final float caluteAnimatorDuration() {
        return ((this.mCurrentProgress * 1.0f) / this.mMaxProgress) * this.mAnimatorDuration;
    }
}