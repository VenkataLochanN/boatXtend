package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
class q extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ViewGroup.LayoutParams f3062a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f3063b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ SwipeDismissTouchListener f3064c;

    q(SwipeDismissTouchListener swipeDismissTouchListener, ViewGroup.LayoutParams layoutParams, int i) {
        this.f3064c = swipeDismissTouchListener;
        this.f3062a = layoutParams;
        this.f3063b = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f3064c.f2968f.onDismiss(this.f3064c.f2967e, this.f3064c.l);
        this.f3064c.f2967e.setTranslationX(0.0f);
        this.f3062a.height = this.f3063b;
        this.f3064c.f2967e.setLayoutParams(this.f3062a);
    }
}