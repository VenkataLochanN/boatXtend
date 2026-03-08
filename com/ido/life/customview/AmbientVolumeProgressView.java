package com.ido.life.customview;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmbientVolumeProgressView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010B\u001a\u00020CH\u0002J\b\u0010D\u001a\u00020\bH\u0002J\u000e\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020CJ\b\u0010H\u001a\u00020\nH\u0002J\b\u0010I\u001a\u00020FH\u0002J\b\u0010J\u001a\u00020FH\u0002J\u0012\u0010K\u001a\u00020F2\b\u0010L\u001a\u0004\u0018\u00010MH\u0014J\u0018\u0010N\u001a\u00020F2\u0006\u0010O\u001a\u00020\n2\u0006\u0010P\u001a\u00020\nH\u0014J\b\u0010Q\u001a\u00020FH\u0002J\b\u0010R\u001a\u00020FH\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0012\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\r\"\u0004\b\u0014\u0010\u000fR&\u0010\u0015\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR&\u0010\u0018\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\r\"\u0004\b\u001a\u0010\u000fR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u001f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\u000fR&\u0010\"\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\r\"\u0004\b$\u0010\u000fR&\u0010%\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R&\u0010*\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010'\"\u0004\b,\u0010)R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010/\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\r\"\u0004\b1\u0010\u000fR&\u00102\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\r\"\u0004\b4\u0010\u000fR&\u00105\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\r\"\u0004\b7\u0010\u000fR&\u00108\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u00118G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010'\"\u0004\b:\u0010)R&\u0010;\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\r\"\u0004\b=\u0010\u000fR\u000e\u0010>\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010?\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\r\"\u0004\bA\u0010\u000f¨\u0006S"}, d2 = {"Lcom/ido/life/customview/AmbientVolumeProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attributes", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mAnimator", "Landroid/animation/ValueAnimator;", "<set-?>", "", "mAnimatorDuration", "getAnimatorDuration", "()I", "setAnimatorDuration", "(I)V", "mAnimatorProgress", "", "mCurrentProgress", "getCurrProgress", "setCurrProgress", "mDefaultColor", "getDefaultColor", "setDefaultColor", "mLabelDistance", "getLabelDistance", "setLabelDistance", "mLabelList", "", "", "mLabelMaxHeight", "mLabelTextColor", "getLabelColor", "setLabelColor", "mLabelTextSize", "getLabelTextSize", "setLabelTextSize", "mNormalProgressHeight", "getNormalProgressHeight", "()F", "setNormalProgressHeight", "(F)V", "mNormalProminentHeightPercent", "getNormalProminentHeightPercent", "setNormalProminentHeightPercent", "mPaint", "Landroid/graphics/Paint;", "mProgressColor", "getProgressColor", "setProgressColor", "mProgressMax", "getMaxProgress", "setMaxProgress", "mProgressSpace", "getProgressSpace", "setProgressSpace", "mProgressWidth", "getProgressWidth", "setProgressWidth", "mProminentProgress", "getProminentProgress", "setProminentProgress", "mProminentProgressHeight", "mRadius", "getRadius", "setRadius", "animatorStarting", "", "getAnimator", "invalidateWithAnimator", "", "animator", "measureLabelMaxHeight", "measureProgressHeight", "measureProgressWidth", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "startAnimator", "stopAnimator", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeProgressView extends View {
    private HashMap _$_findViewCache;
    private ValueAnimator mAnimator;
    private int mAnimatorDuration;
    private float mAnimatorProgress;
    private int mCurrentProgress;
    private int mDefaultColor;
    private int mLabelDistance;
    private List<String> mLabelList;
    private int mLabelMaxHeight;
    private int mLabelTextColor;
    private int mLabelTextSize;
    private float mNormalProgressHeight;
    private float mNormalProminentHeightPercent;
    private Paint mPaint;
    private int mProgressColor;
    private int mProgressMax;
    private int mProgressSpace;
    private float mProgressWidth;
    private int mProminentProgress;
    private float mProminentProgressHeight;
    private int mRadius;

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
    /* JADX WARN: Removed duplicated region for block: B:11:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AmbientVolumeProgressView(android.content.Context r7, android.util.AttributeSet r8) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.AmbientVolumeProgressView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* JADX INFO: renamed from: getProgressColor, reason: from getter */
    public final int getMProgressColor() {
        return this.mProgressColor;
    }

    public final void setProgressColor(int i) {
        this.mProgressColor = i;
    }

    /* JADX INFO: renamed from: getDefaultColor, reason: from getter */
    public final int getMDefaultColor() {
        return this.mDefaultColor;
    }

    public final void setDefaultColor(int i) {
        this.mDefaultColor = i;
    }

    /* JADX INFO: renamed from: getMaxProgress, reason: from getter */
    public final int getMProgressMax() {
        return this.mProgressMax;
    }

    public final void setMaxProgress(int i) {
        this.mProgressMax = i;
    }

    /* JADX INFO: renamed from: getCurrProgress, reason: from getter */
    public final int getMCurrentProgress() {
        return this.mCurrentProgress;
    }

    public final void setCurrProgress(int i) {
        this.mCurrentProgress = i;
    }

    /* JADX INFO: renamed from: getRadius, reason: from getter */
    public final int getMRadius() {
        return this.mRadius;
    }

    public final void setRadius(int i) {
        this.mRadius = i;
    }

    /* JADX INFO: renamed from: getProminentProgress, reason: from getter */
    public final int getMProminentProgress() {
        return this.mProminentProgress;
    }

    public final void setProminentProgress(int i) {
        this.mProminentProgress = i;
    }

    /* JADX INFO: renamed from: getProgressSpace, reason: from getter */
    public final int getMProgressSpace() {
        return this.mProgressSpace;
    }

    public final void setProgressSpace(int i) {
        this.mProgressSpace = i;
    }

    /* JADX INFO: renamed from: getLabelDistance, reason: from getter */
    public final int getMLabelDistance() {
        return this.mLabelDistance;
    }

    public final void setLabelDistance(int i) {
        this.mLabelDistance = i;
    }

    /* JADX INFO: renamed from: getLabelTextSize, reason: from getter */
    public final int getMLabelTextSize() {
        return this.mLabelTextSize;
    }

    public final void setLabelTextSize(int i) {
        this.mLabelTextSize = i;
    }

    /* JADX INFO: renamed from: getLabelColor, reason: from getter */
    public final int getMLabelTextColor() {
        return this.mLabelTextColor;
    }

    public final void setLabelColor(int i) {
        this.mLabelTextColor = i;
    }

    /* JADX INFO: renamed from: getAnimatorDuration, reason: from getter */
    public final int getMAnimatorDuration() {
        return this.mAnimatorDuration;
    }

    public final void setAnimatorDuration(int i) {
        this.mAnimatorDuration = i;
    }

    /* JADX INFO: renamed from: getNormalProgressHeight, reason: from getter */
    public final float getMNormalProgressHeight() {
        return this.mNormalProgressHeight;
    }

    public final void setNormalProgressHeight(float f2) {
        this.mNormalProgressHeight = f2;
    }

    /* JADX INFO: renamed from: getProgressWidth, reason: from getter */
    public final float getMProgressWidth() {
        return this.mProgressWidth;
    }

    public final void setProgressWidth(float f2) {
        this.mProgressWidth = f2;
    }

    /* JADX INFO: renamed from: getNormalProminentHeightPercent, reason: from getter */
    public final float getMNormalProminentHeightPercent() {
        return this.mNormalProminentHeightPercent;
    }

    public final void setNormalProminentHeightPercent(float f2) {
        this.mNormalProminentHeightPercent = f2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int iSave;
        int i;
        super.onDraw(canvas);
        if (this.mProgressMax <= 0) {
            return;
        }
        if (!animatorStarting() || this.mAnimatorProgress >= 1.0f) {
            iSave = Integer.MIN_VALUE;
        } else {
            Path path = new Path();
            path.addRect(0.0f, this.mProminentProgressHeight, getWidth(), getHeight(), Path.Direction.CW);
            path.addCircle(0.0f, this.mProminentProgressHeight / 2, getWidth() * this.mAnimatorProgress, Path.Direction.CW);
            if (canvas != null) {
                canvas.clipPath(path);
            }
            if (canvas == null) {
                Intrinsics.throwNpe();
            }
            iSave = canvas.save();
        }
        int i2 = this.mProgressMax;
        int i3 = 0;
        while (i3 < i2) {
            if (i3 < this.mCurrentProgress) {
                this.mPaint.setColor(this.mProgressColor);
            } else {
                this.mPaint.setColor(this.mDefaultColor);
            }
            if (canvas != null) {
                float f2 = i3;
                float f3 = (this.mProgressWidth + this.mProgressSpace) * f2;
                float f4 = i3 == this.mProminentProgress ? 0.0f : (this.mProminentProgressHeight - this.mNormalProgressHeight) / 2;
                float f5 = this.mProgressWidth;
                float f6 = f5 + (f2 * (this.mProgressSpace + f5));
                float f7 = i3 == this.mProminentProgress ? this.mProminentProgressHeight : (this.mProminentProgressHeight + this.mNormalProgressHeight) / 2;
                int i4 = this.mRadius;
                i = i3;
                canvas.drawRoundRect(f3, f4, f6, f7, i4, i4, this.mPaint);
            } else {
                i = i3;
            }
            i3 = i + 1;
        }
        List<String> list = this.mLabelList;
        if (!(list == null || list.isEmpty())) {
            this.mPaint.setColor(this.mLabelTextColor);
            this.mPaint.setTextSize(this.mLabelTextSize);
            int size = this.mLabelList.size();
            Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
            for (int i5 = 0; i5 < size; i5++) {
                String str = this.mLabelList.get(i5);
                this.mPaint.getTextBounds(str, 0, str.length(), new Rect());
                if (i5 == 0) {
                    if (canvas != null) {
                        canvas.drawText(str, 0.0f, getHeight() - Math.abs(fontMetrics.bottom), this.mPaint);
                    }
                } else if (i5 == size - 1) {
                    if (canvas != null) {
                        canvas.drawText(str, (getWidth() - r5.width()) - 2, getHeight() - Math.abs(fontMetrics.bottom), this.mPaint);
                    }
                } else if (canvas != null) {
                    float f8 = this.mProgressWidth;
                    canvas.drawText(str, ((i5 * (this.mProgressSpace + f8)) + (f8 / 2)) - (r5.width() / 2), getHeight() - Math.abs(fontMetrics.bottom), this.mPaint);
                }
            }
        }
        if (iSave == Integer.MIN_VALUE || canvas == null) {
            return;
        }
        canvas.restore();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
        measureProgressHeight();
        measureProgressWidth();
    }

    private final int measureLabelMaxHeight() {
        List<String> list = this.mLabelList;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        Rect rect = new Rect();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(this.mLabelTextSize);
        int iMax = 0;
        for (String str : this.mLabelList) {
            if (!(str.length() == 0)) {
                this.mPaint.getTextBounds(str, 0, str.length(), rect);
                iMax = Math.max(iMax, rect.height());
            }
        }
        return iMax;
    }

    private final void measureProgressHeight() {
        this.mProminentProgressHeight = 0.0f;
        this.mNormalProgressHeight = 0.0f;
        this.mProminentProgressHeight = (getMeasuredHeight() - this.mLabelDistance) - this.mLabelMaxHeight;
        this.mNormalProgressHeight = this.mProminentProgressHeight * this.mNormalProminentHeightPercent;
    }

    private final void measureProgressWidth() {
        this.mProgressWidth = 0.0f;
        if (this.mProgressMax == 1) {
            this.mProgressWidth = getMeasuredWidth();
        }
        if (this.mProgressMax > 1) {
            int measuredWidth = getMeasuredWidth();
            int i = this.mProgressSpace;
            this.mProgressWidth = ((measuredWidth - (i * (r2 - 1))) * 1.0f) / this.mProgressMax;
        }
    }

    public final void invalidateWithAnimator(boolean animator) {
        if (animator) {
            stopAnimator();
            startAnimator();
        } else {
            invalidate();
        }
    }

    private final boolean animatorStarting() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            if (valueAnimator == null) {
                Intrinsics.throwNpe();
            }
            if (!valueAnimator.isStarted()) {
                ValueAnimator valueAnimator2 = this.mAnimator;
                if (valueAnimator2 == null) {
                    Intrinsics.throwNpe();
                }
                if (valueAnimator2.isRunning()) {
                }
            }
            return true;
        }
        return false;
    }

    private final void startAnimator() {
        if (this.mAnimator == null) {
            this.mAnimator = getAnimator();
        }
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    private final void stopAnimator() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    private final ValueAnimator getAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setTarget(this);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.setDuration(this.mAnimatorDuration);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.AmbientVolumeProgressView.getAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                AmbientVolumeProgressView ambientVolumeProgressView = AmbientVolumeProgressView.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue != null) {
                    ambientVolumeProgressView.mAnimatorProgress = ((Float) animatedValue).floatValue();
                    AmbientVolumeProgressView.this.invalidate();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
            }
        });
        return valueAnimator;
    }
}