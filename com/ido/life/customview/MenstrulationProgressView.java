package com.ido.life.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.ido.life.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenstrulationProgressView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\bH\u0002J\u0010\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020/H\u0014J\u0018\u00100\u001a\u00020+2\u0006\u00101\u001a\u00020\n2\u0006\u00102\u001a\u00020\nH\u0014J\u000e\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020)J\b\u00105\u001a\u00020+H\u0002J\b\u00106\u001a\u00020+H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R&\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R&\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010R&\u0010\u001f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000e\"\u0004\b!\u0010\u0010R&\u0010\"\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/ido/life/customview/MenstrulationProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mAnimator", "Landroid/animation/ValueAnimator;", "mAnimatorValue", "", "<set-?>", "mBackgroundColor", "getBgColor", "()I", "setBgColor", "(I)V", "mBackgroundRadius", "getBackgroundRadius", "setBackgroundRadius", "mCurrProgress", "getProgress", "setProgress", "mMaxProgress", "getMaxProgress", "setMaxProgress", "mPaint", "Landroid/graphics/Paint;", "mProgressColor", "getProgressColor", "setProgressColor", "mProgressRadius", "getProgressRadius", "setProgressRadius", "mProgressSpace", "getProgressSpace", "setProgressSpace", "mProgressWidth", "", "mSingleProgressAnimatorDuration", "animatorRunning", "", "caluteProgressWidth", "", "getProgressAnimator", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "refreshProgress", "animator", "startAnimator", "stopAnimator", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstrulationProgressView extends View {
    private HashMap _$_findViewCache;
    private ValueAnimator mAnimator;
    private int mAnimatorValue;
    private int mBackgroundColor;
    private int mBackgroundRadius;
    private int mCurrProgress;
    private int mMaxProgress;
    private Paint mPaint;
    private int mProgressColor;
    private int mProgressRadius;
    private int mProgressSpace;
    private float mProgressWidth;
    private final int mSingleProgressAnimatorDuration;

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
    public MenstrulationProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mSingleProgressAnimatorDuration = 200;
        this.mMaxProgress = 100;
        this.mBackgroundColor = -3355444;
        this.mProgressColor = -65281;
        this.mProgressSpace = 2;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MenstrulationProgressView);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…enstrulationProgressView)");
        this.mMaxProgress = typedArrayObtainStyledAttributes.getInt(2, this.mMaxProgress);
        this.mCurrProgress = typedArrayObtainStyledAttributes.getInt(3, this.mCurrProgress);
        this.mBackgroundColor = typedArrayObtainStyledAttributes.getColor(0, this.mBackgroundColor);
        this.mProgressColor = typedArrayObtainStyledAttributes.getColor(4, this.mProgressColor);
        this.mBackgroundRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mBackgroundRadius);
        this.mProgressRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.mProgressRadius);
        this.mProgressSpace = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.mProgressSpace);
        this.mPaint = new Paint();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setDither(true);
        this.mPaint.setAntiAlias(true);
    }

    /* JADX INFO: renamed from: getMaxProgress, reason: from getter */
    public final int getMMaxProgress() {
        return this.mMaxProgress;
    }

    public final void setMaxProgress(int i) {
        this.mMaxProgress = i;
    }

    /* JADX INFO: renamed from: getProgress, reason: from getter */
    public final int getMCurrProgress() {
        return this.mCurrProgress;
    }

    public final void setProgress(int i) {
        this.mCurrProgress = i;
    }

    /* JADX INFO: renamed from: getBgColor, reason: from getter */
    public final int getMBackgroundColor() {
        return this.mBackgroundColor;
    }

    public final void setBgColor(int i) {
        this.mBackgroundColor = i;
    }

    /* JADX INFO: renamed from: getProgressColor, reason: from getter */
    public final int getMProgressColor() {
        return this.mProgressColor;
    }

    public final void setProgressColor(int i) {
        this.mProgressColor = i;
    }

    /* JADX INFO: renamed from: getBackgroundRadius, reason: from getter */
    public final int getMBackgroundRadius() {
        return this.mBackgroundRadius;
    }

    public final void setBackgroundRadius(int i) {
        this.mBackgroundRadius = i;
    }

    /* JADX INFO: renamed from: getProgressRadius, reason: from getter */
    public final int getMProgressRadius() {
        return this.mProgressRadius;
    }

    public final void setProgressRadius(int i) {
        this.mProgressRadius = i;
    }

    /* JADX INFO: renamed from: getProgressSpace, reason: from getter */
    public final int getMProgressSpace() {
        return this.mProgressSpace;
    }

    public final void setProgressSpace(int i) {
        this.mProgressSpace = i;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        this.mPaint.setColor(this.mBackgroundColor);
        float width = getWidth();
        float height = getHeight();
        int i = this.mBackgroundRadius;
        canvas.drawRoundRect(0.0f, 0.0f, width, height, i, i, this.mPaint);
        this.mPaint.setColor(this.mProgressColor);
        int i2 = 0;
        if (animatorRunning()) {
            int i3 = this.mAnimatorValue;
            while (i2 < i3) {
                float f2 = this.mProgressWidth;
                int i4 = this.mProgressSpace;
                float f3 = i2;
                float f4 = (i4 + f2) * f3;
                float f5 = ((i4 + f2) * f3) + f2;
                float height2 = getHeight();
                int i5 = this.mProgressRadius;
                canvas.drawRoundRect(f4, 0.0f, f5, height2, i5, i5, this.mPaint);
                i2++;
            }
            return;
        }
        int i6 = this.mCurrProgress;
        while (i2 < i6) {
            float f6 = this.mProgressWidth;
            int i7 = this.mProgressSpace;
            float f7 = i2;
            float f8 = (i7 + f6) * f7;
            float f9 = ((i7 + f6) * f7) + f6;
            float height3 = getHeight();
            int i8 = this.mProgressRadius;
            canvas.drawRoundRect(f8, 0.0f, f9, height3, i8, i8, this.mPaint);
            i2++;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
        caluteProgressWidth();
    }

    public final void refreshProgress(boolean animator) {
        stopAnimator();
        if (animator) {
            startAnimator();
        } else {
            invalidate();
        }
    }

    private final void caluteProgressWidth() {
        if (this.mMaxProgress <= 0) {
            this.mProgressWidth = getMeasuredWidth() - this.mProgressSpace;
        }
        int measuredWidth = getMeasuredWidth();
        int i = this.mProgressSpace;
        this.mProgressWidth = ((measuredWidth - (i * (r2 - 1))) * 1.0f) / this.mMaxProgress;
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

    private final void startAnimator() {
        if (this.mAnimator == null) {
            this.mAnimator = getProgressAnimator();
        }
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void stopAnimator() {
        /*
            r1 = this;
            android.animation.ValueAnimator r0 = r1.mAnimator
            if (r0 == 0) goto L23
            if (r0 != 0) goto L9
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L9:
            boolean r0 = r0.isStarted()
            if (r0 != 0) goto L1c
            android.animation.ValueAnimator r0 = r1.mAnimator
            if (r0 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L16:
            boolean r0 = r0.isRunning()
            if (r0 == 0) goto L23
        L1c:
            android.animation.ValueAnimator r0 = r1.mAnimator
            if (r0 == 0) goto L23
            r0.cancel()
        L23:
            r0 = 0
            android.animation.ValueAnimator r0 = (android.animation.ValueAnimator) r0
            r1.mAnimator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.MenstrulationProgressView.stopAnimator():void");
    }

    private final ValueAnimator getProgressAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setRepeatCount(0);
        valueAnimator.setDuration(((long) this.mSingleProgressAnimatorDuration) * ((long) this.mCurrProgress));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setIntValues(0, this.mCurrProgress);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.MenstrulationProgressView.getProgressAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                MenstrulationProgressView menstrulationProgressView = MenstrulationProgressView.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue != null) {
                    menstrulationProgressView.mAnimatorValue = ((Integer) animatedValue).intValue();
                    MenstrulationProgressView.this.invalidate();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        });
        return valueAnimator;
    }
}