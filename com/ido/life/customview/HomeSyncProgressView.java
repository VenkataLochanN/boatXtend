package com.ido.life.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.ConnectionResult;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: HomeSyncProgressView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0018\u001a\u00020\u000bH\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u001aH\u0002J\u0012\u0010\u001c\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0018\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000bH\u0014J\u000e\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u000bJ\u000e\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u000bJ\u000e\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u000bJ\u000e\u0010(\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u000bJ\u000e\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020\u000bJ\b\u0010+\u001a\u00020\u001aH\u0002J\b\u0010,\u001a\u00020\u001aH\u0002R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u000b8CX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/ido/life/customview/HomeSyncProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attributes", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TAG", "", "kotlin.jvm.PlatformType", "mCurProgress", "", "mMaxProgress", "mPaint", "Landroid/graphics/Paint;", "mPreProgress", "mProgress", "mProgressAnimator", "Landroid/animation/ValueAnimator;", "mProgressAnimatorDuration", "mProgressBackgroundColor", "mProgressCenterColor", "mProgressColor", "mProgressWidth", "caluteProgressAnimatorDuration", "destroy", "", "initProgressAnimator", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setMaxProgress", "max", "setProgress", NotificationCompat.CATEGORY_PROGRESS, "setProgressBackgroundColor", "color", "setProgressColor", "setProgressWidth", "width", "startProgressAnimator", "stopProgressAnimator", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HomeSyncProgressView extends View {
    private final String TAG;
    private HashMap _$_findViewCache;
    private int mCurProgress;
    private int mMaxProgress;
    private Paint mPaint;
    private int mPreProgress;
    private int mProgress;
    private ValueAnimator mProgressAnimator;
    private final int mProgressAnimatorDuration;
    private int mProgressBackgroundColor;
    private int mProgressCenterColor;
    private int mProgressColor;
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
    public HomeSyncProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = HomeSyncProgressView.class.getSimpleName();
        this.mProgressBackgroundColor = Color.parseColor("#14FFFFFF");
        this.mProgressColor = -1;
        this.mProgressWidth = 3;
        this.mMaxProgress = 100;
        this.mPaint = new Paint();
        this.mProgressAnimatorDuration = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HomeSyncProgressView);
            Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…ble.HomeSyncProgressView)");
            this.mProgressBackgroundColor = typedArrayObtainStyledAttributes.getColor(1, this.mProgressBackgroundColor);
            this.mProgressColor = typedArrayObtainStyledAttributes.getColor(3, this.mProgressColor);
            this.mProgressWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.mProgressWidth);
            this.mProgressCenterColor = typedArrayObtainStyledAttributes.getColor(2, this.mProgressCenterColor);
            this.mMaxProgress = typedArrayObtainStyledAttributes.getInt(4, this.mMaxProgress);
            this.mProgress = typedArrayObtainStyledAttributes.getInt(0, this.mProgress);
            typedArrayObtainStyledAttributes.recycle();
        }
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mMaxProgress <= 0) {
            return;
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mProgressWidth);
        if (canvas != null) {
            canvas.rotate(-90.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        }
        this.mPaint.setColor(this.mProgressColor);
        float f2 = (this.mProgress * 360.0f) / this.mMaxProgress;
        if (canvas != null) {
            int i = this.mProgressWidth;
            canvas.drawArc(i, i, getWidth() - this.mProgressWidth, getHeight() - this.mProgressWidth, 0.0f, f2, false, this.mPaint);
        }
        this.mPaint.setColor(this.mProgressBackgroundColor);
        if (canvas != null) {
            int i2 = this.mProgressWidth;
            canvas.drawArc(i2, i2, getWidth() - this.mProgressWidth, getHeight() - this.mProgressWidth, f2, 360.0f, false, this.mPaint);
        }
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mProgressCenterColor);
        if (canvas != null) {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, getWidth() / 2.0f, this.mPaint);
        }
        if (canvas != null) {
            canvas.rotate(90.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iMin = Math.min(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(iMin, iMin);
    }

    public final void setProgressBackgroundColor(int color) {
        this.mProgressBackgroundColor = color;
        invalidate();
    }

    public final void setProgressColor(int color) {
        this.mProgressColor = color;
        invalidate();
    }

    public final void setProgressWidth(int width) {
        this.mProgressWidth = width;
        invalidate();
    }

    public final void setMaxProgress(int max) {
        this.mMaxProgress = max;
        invalidate();
    }

    public final void setProgress(int progress) {
        CommonLogUtil.d(this.TAG, "mCurProgress=" + this.mCurProgress + ",progress=" + progress);
        if (progress == this.mCurProgress || progress < 0) {
            return;
        }
        stopProgressAnimator();
        this.mCurProgress = progress;
        if (progress == 0) {
            this.mProgress = progress;
            this.mCurProgress = progress;
            this.mPreProgress = progress;
            invalidate();
        }
        startProgressAnimator();
    }

    private final void stopProgressAnimator() {
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator != null) {
            if (valueAnimator == null) {
                Intrinsics.throwNpe();
            }
            if (!valueAnimator.isRunning()) {
                ValueAnimator valueAnimator2 = this.mProgressAnimator;
                if (valueAnimator2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!valueAnimator2.isStarted()) {
                    return;
                }
            }
            ValueAnimator valueAnimator3 = this.mProgressAnimator;
            if (valueAnimator3 == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator3.cancel();
        }
    }

    private final void startProgressAnimator() {
        int iCaluteProgressAnimatorDuration = caluteProgressAnimatorDuration();
        CommonLogUtil.d(this.TAG, "开始执行进度条更新动画 duration=" + iCaluteProgressAnimatorDuration);
        if (iCaluteProgressAnimatorDuration <= 0) {
            return;
        }
        if (this.mProgressAnimator == null) {
            initProgressAnimator();
        }
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator.setDuration(iCaluteProgressAnimatorDuration);
        ValueAnimator valueAnimator2 = this.mProgressAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator2.setIntValues(this.mProgress, this.mCurProgress);
        ValueAnimator valueAnimator3 = this.mProgressAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator3.start();
    }

    private final void initProgressAnimator() {
        this.mProgressAnimator = new ValueAnimator();
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator.setTarget(this);
        ValueAnimator valueAnimator2 = this.mProgressAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator2.setInterpolator(new LinearInterpolator());
        ValueAnimator valueAnimator3 = this.mProgressAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator3.setRepeatCount(0);
        ValueAnimator valueAnimator4 = this.mProgressAnimator;
        if (valueAnimator4 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator4.setIntValues(this.mProgress, this.mCurProgress);
        ValueAnimator valueAnimator5 = this.mProgressAnimator;
        if (valueAnimator5 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.HomeSyncProgressView.initProgressAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                HomeSyncProgressView homeSyncProgressView = HomeSyncProgressView.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue != null) {
                    homeSyncProgressView.mProgress = ((Integer) animatedValue).intValue();
                    HomeSyncProgressView.this.invalidate();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        });
    }

    public final void destroy() {
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.mProgressAnimator = (ValueAnimator) null;
    }

    private final int caluteProgressAnimatorDuration() {
        int i = this.mMaxProgress;
        if (i <= 0) {
            return 0;
        }
        return MathKt.roundToInt(((((double) (this.mCurProgress - this.mProgress)) * 1.0d) / ((double) i)) * ((double) this.mProgressAnimatorDuration));
    }
}