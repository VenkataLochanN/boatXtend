package com.ido.life.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: HomeHistoryProgress.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\nH\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\nR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/ido/life/customview/HomeHistoryProgress;", "Landroid/widget/ProgressBar;", "context", "Landroid/content/Context;", "attributes", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mAnimator", "Landroid/animation/ValueAnimator;", "mAnimatorDuration", "", "mCurProgress", "getMCurProgress", "()I", "setMCurProgress", "(I)V", "mMaxProgress", "getMMaxProgress", "setMMaxProgress", "mProgress", "getMProgress", "setMProgress", "caluteAnimatorDuration", "initAnimator", "", "startAnimator", "stopAnimator", "updateProgress", NotificationCompat.CATEGORY_PROGRESS, "app_release"}, k = 1, mv = {1, 1, 16})
public final class HomeHistoryProgress extends ProgressBar {
    private HashMap _$_findViewCache;
    private ValueAnimator mAnimator;
    private final int mAnimatorDuration;
    private int mCurProgress;
    private int mMaxProgress;
    private int mProgress;

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
    public HomeHistoryProgress(Context context, AttributeSet attributes) {
        super(context, attributes);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributes, "attributes");
        this.mAnimatorDuration = 2000;
        this.mMaxProgress = 100;
    }

    public final int getMMaxProgress() {
        return this.mMaxProgress;
    }

    public final void setMMaxProgress(int i) {
        this.mMaxProgress = i;
    }

    public final int getMCurProgress() {
        return this.mCurProgress;
    }

    public final void setMCurProgress(int i) {
        this.mCurProgress = i;
    }

    public final int getMProgress() {
        return this.mProgress;
    }

    public final void setMProgress(int i) {
        this.mProgress = i;
    }

    public final void updateProgress(int progress) {
        if (this.mCurProgress == progress) {
            return;
        }
        this.mCurProgress = progress;
        stopAnimator();
        startAnimator();
    }

    private final void startAnimator() {
        if (this.mAnimator == null) {
            initAnimator();
        }
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator.setIntValues(this.mProgress, this.mCurProgress);
        ValueAnimator valueAnimator2 = this.mAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator2.setDuration(caluteAnimatorDuration());
        ValueAnimator valueAnimator3 = this.mAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.start();
        }
    }

    private final void stopAnimator() {
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
                if (!valueAnimator2.isRunning()) {
                    return;
                }
            }
            ValueAnimator valueAnimator3 = this.mAnimator;
            if (valueAnimator3 == null) {
                Intrinsics.throwNpe();
            }
            valueAnimator3.cancel();
        }
    }

    private final void initAnimator() {
        this.mAnimator = new ValueAnimator();
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator.setIntValues(this.mProgress, this.mCurProgress);
        ValueAnimator valueAnimator2 = this.mAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator2.setTarget(this);
        ValueAnimator valueAnimator3 = this.mAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator3.setInterpolator(new LinearInterpolator());
        ValueAnimator valueAnimator4 = this.mAnimator;
        if (valueAnimator4 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator4.setRepeatCount(0);
        ValueAnimator valueAnimator5 = this.mAnimator;
        if (valueAnimator5 == null) {
            Intrinsics.throwNpe();
        }
        valueAnimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.HomeHistoryProgress.initAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                HomeHistoryProgress homeHistoryProgress = HomeHistoryProgress.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                homeHistoryProgress.setMProgress(((Integer) animatedValue).intValue());
                HomeHistoryProgress homeHistoryProgress2 = HomeHistoryProgress.this;
                homeHistoryProgress2.setProgress(homeHistoryProgress2.getMProgress());
            }
        });
    }

    private final int caluteAnimatorDuration() {
        if (getMax() <= 0) {
            return 0;
        }
        return MathKt.roundToInt(((((double) (this.mCurProgress - this.mProgress)) * 1.0d) / ((double) getMax())) * ((double) this.mAnimatorDuration));
    }
}