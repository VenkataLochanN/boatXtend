package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;

/* JADX INFO: loaded from: classes.dex */
class i implements Animator.AnimatorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f3444a;

    i(h hVar) {
        this.f3444a = hVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        if (this.f3444a.f3440d != null) {
            this.f3444a.f3440d.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.f3444a.f3440d != null) {
            this.f3444a.f3440d.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        if (this.f3444a.f3440d != null) {
            this.f3444a.f3440d.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (this.f3444a.f3440d != null) {
            this.f3444a.f3440d.onAnimationStart();
        }
    }
}