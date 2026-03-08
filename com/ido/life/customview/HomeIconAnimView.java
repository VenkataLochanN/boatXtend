package com.ido.life.customview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.boat.Xtend.two.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeIconAnimView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u001aB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\bJ\u0006\u0010\u0016\u001a\u00020\u0012J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\u0006\u0010\u0018\u001a\u00020\u0012J\b\u0010\u0019\u001a\u00020\u0012H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/ido/life/customview/HomeIconAnimView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DURATION_ALPHA", "", "DURATION_ROLATION", "TAG", "", "kotlin.jvm.PlatformType", "mAlphaAnimator", "Landroid/animation/ValueAnimator;", "mAnimatorRepeatCount", "mRolationAnimator", "initAlphaAnimator", "", "initRolationAnimator", "setAnimatorIcon", "drawableResId", "startAnimator", "startRolationAnimator", "stopAnimator", "stopRolationAnimator", "MyAnimatorListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HomeIconAnimView extends LinearLayout {
    private final int DURATION_ALPHA;
    private final int DURATION_ROLATION;
    private final String TAG;
    private HashMap _$_findViewCache;
    private ValueAnimator mAlphaAnimator;
    private int mAnimatorRepeatCount;
    private ValueAnimator mRolationAnimator;

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
    public HomeIconAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.TAG = HomeIconAnimView.class.getSimpleName();
        this.DURATION_ROLATION = 400;
        this.DURATION_ALPHA = 1000;
        initAlphaAnimator();
        initRolationAnimator();
        LinearLayout.inflate(context, R.layout.home_icon_anim_layout, this);
    }

    private final void initRolationAnimator() {
        this.mRolationAnimator = new ValueAnimator();
        ValueAnimator valueAnimator = this.mRolationAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator.setDuration(this.DURATION_ROLATION);
        ValueAnimator valueAnimator2 = this.mRolationAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator2.setInterpolator(new LinearInterpolator());
        ValueAnimator valueAnimator3 = this.mRolationAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator3.setRepeatMode(1);
        ValueAnimator valueAnimator4 = this.mRolationAnimator;
        if (valueAnimator4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator4.setRepeatCount(2);
        ValueAnimator valueAnimator5 = this.mRolationAnimator;
        if (valueAnimator5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator5.setTarget((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_anim_bg));
        ValueAnimator valueAnimator6 = this.mRolationAnimator;
        if (valueAnimator6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator6.setFloatValues(0.0f, 360.0f);
        ValueAnimator valueAnimator7 = this.mRolationAnimator;
        if (valueAnimator7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.HomeIconAnimView.initRolationAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                }
                float fFloatValue = ((Float) animatedValue).floatValue();
                ImageView img_anim_bg = (ImageView) HomeIconAnimView.this._$_findCachedViewById(com.ido.life.R.id.img_anim_bg);
                Intrinsics.checkExpressionValueIsNotNull(img_anim_bg, "img_anim_bg");
                img_anim_bg.setRotation(fFloatValue);
                if (HomeIconAnimView.this.mAnimatorRepeatCount == 0) {
                    ImageView img_anim_bg2 = (ImageView) HomeIconAnimView.this._$_findCachedViewById(com.ido.life.R.id.img_anim_bg);
                    Intrinsics.checkExpressionValueIsNotNull(img_anim_bg2, "img_anim_bg");
                    img_anim_bg2.setAlpha(fFloatValue / 360.0f);
                } else {
                    ImageView img_anim_bg3 = (ImageView) HomeIconAnimView.this._$_findCachedViewById(com.ido.life.R.id.img_anim_bg);
                    Intrinsics.checkExpressionValueIsNotNull(img_anim_bg3, "img_anim_bg");
                    img_anim_bg3.setAlpha(1.0f);
                }
            }
        });
        ValueAnimator valueAnimator8 = this.mRolationAnimator;
        if (valueAnimator8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator8.addListener(new MyAnimatorListener() { // from class: com.ido.life.customview.HomeIconAnimView.initRolationAnimator.2
            {
                super();
            }

            @Override // com.ido.life.customview.HomeIconAnimView.MyAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                HomeIconAnimView.this.mAnimatorRepeatCount = 0;
            }

            @Override // com.ido.life.customview.HomeIconAnimView.MyAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                HomeIconAnimView.this.mAnimatorRepeatCount = 1;
            }

            @Override // com.ido.life.customview.HomeIconAnimView.MyAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                HomeIconAnimView.this.setVisibility(8);
            }

            @Override // com.ido.life.customview.HomeIconAnimView.MyAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                HomeIconAnimView.this.setVisibility(8);
            }
        });
    }

    private final void initAlphaAnimator() {
        this.mAlphaAnimator = new ValueAnimator();
        ValueAnimator valueAnimator = this.mAlphaAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAlphaAnimator");
        }
        valueAnimator.setDuration(this.DURATION_ALPHA);
        ValueAnimator valueAnimator2 = this.mAlphaAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAlphaAnimator");
        }
        valueAnimator2.setInterpolator(new LinearInterpolator());
        ValueAnimator valueAnimator3 = this.mAlphaAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAlphaAnimator");
        }
        valueAnimator3.setRepeatMode(1);
        ValueAnimator valueAnimator4 = this.mAlphaAnimator;
        if (valueAnimator4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAlphaAnimator");
        }
        valueAnimator4.setFloatValues(0.0f, 1.0f);
        ValueAnimator valueAnimator5 = this.mAlphaAnimator;
        if (valueAnimator5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAlphaAnimator");
        }
        valueAnimator5.setTarget((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_anim_icon));
        ValueAnimator valueAnimator6 = this.mAlphaAnimator;
        if (valueAnimator6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAlphaAnimator");
        }
        valueAnimator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.HomeIconAnimView.initAlphaAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object animatedValue = it.getAnimatedValue();
                if (animatedValue == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                }
                float fFloatValue = ((Float) animatedValue).floatValue();
                ImageView img_anim_icon = (ImageView) HomeIconAnimView.this._$_findCachedViewById(com.ido.life.R.id.img_anim_icon);
                Intrinsics.checkExpressionValueIsNotNull(img_anim_icon, "img_anim_icon");
                img_anim_icon.setAlpha(fFloatValue);
            }
        });
        ValueAnimator valueAnimator7 = this.mAlphaAnimator;
        if (valueAnimator7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAlphaAnimator");
        }
        valueAnimator7.addListener(new MyAnimatorListener() { // from class: com.ido.life.customview.HomeIconAnimView.initAlphaAnimator.2
            {
                super();
            }

            @Override // com.ido.life.customview.HomeIconAnimView.MyAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                HomeIconAnimView.this.startRolationAnimator();
            }
        });
    }

    public final void startAnimator() {
        stopAnimator();
        ValueAnimator valueAnimator = this.mAlphaAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAlphaAnimator");
        }
        valueAnimator.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void stopAnimator() {
        /*
            r2 = this;
            android.animation.ValueAnimator r0 = r2.mAlphaAnimator
            java.lang.String r1 = "mAlphaAnimator"
            if (r0 != 0) goto L9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L9:
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L1c
            android.animation.ValueAnimator r0 = r2.mAlphaAnimator
            if (r0 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L16:
            boolean r0 = r0.isStarted()
            if (r0 == 0) goto L26
        L1c:
            android.animation.ValueAnimator r0 = r2.mAlphaAnimator
            if (r0 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L23:
            r0.cancel()
        L26:
            android.animation.ValueAnimator r0 = r2.mRolationAnimator
            java.lang.String r1 = "mRolationAnimator"
            if (r0 != 0) goto L2f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L2f:
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L42
            android.animation.ValueAnimator r0 = r2.mRolationAnimator
            if (r0 != 0) goto L3c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L3c:
            boolean r0 = r0.isStarted()
            if (r0 == 0) goto L4c
        L42:
            android.animation.ValueAnimator r0 = r2.mRolationAnimator
            if (r0 != 0) goto L49
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L49:
            r0.cancel()
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.HomeIconAnimView.stopAnimator():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRolationAnimator() {
        ValueAnimator valueAnimator = this.mRolationAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator.start();
    }

    private final void stopRolationAnimator() {
        ValueAnimator valueAnimator = this.mRolationAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        if (!valueAnimator.isStarted()) {
            ValueAnimator valueAnimator2 = this.mRolationAnimator;
            if (valueAnimator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
            }
            if (!valueAnimator2.isRunning()) {
                return;
            }
        }
        ValueAnimator valueAnimator3 = this.mRolationAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRolationAnimator");
        }
        valueAnimator3.cancel();
    }

    public final void setAnimatorIcon(int drawableResId) {
        ((ImageView) _$_findCachedViewById(com.ido.life.R.id.img_anim_icon)).setBackgroundResource(drawableResId);
    }

    /* JADX INFO: compiled from: HomeIconAnimView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/ido/life/customview/HomeIconAnimView$MyAnimatorListener;", "Landroid/animation/Animator$AnimatorListener;", "(Lcom/ido/life/customview/HomeIconAnimView;)V", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "app_release"}, k = 1, mv = {1, 1, 16})
    public class MyAnimatorListener implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
        }

        public MyAnimatorListener() {
        }
    }
}