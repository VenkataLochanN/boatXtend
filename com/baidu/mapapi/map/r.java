package com.baidu.mapapi.map;

import android.animation.ValueAnimator;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
class r implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ViewGroup.LayoutParams f3065a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ SwipeDismissTouchListener f3066b;

    r(SwipeDismissTouchListener swipeDismissTouchListener, ViewGroup.LayoutParams layoutParams) {
        this.f3066b = swipeDismissTouchListener;
        this.f3065a = layoutParams;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f3065a.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f3066b.f2967e.setLayoutParams(this.f3065a);
    }
}