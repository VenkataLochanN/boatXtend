package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;

/* JADX INFO: loaded from: classes.dex */
class k implements Animator.AnimatorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ j f3453a;

    k(j jVar) {
        this.f3453a = jVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        if (this.f3453a.f3448d != null) {
            this.f3453a.f3448d.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.f3453a.f3448d != null) {
            this.f3453a.f3448d.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        if (this.f3453a.f3448d != null) {
            this.f3453a.f3448d.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (this.f3453a.f3448d != null) {
            this.f3453a.f3448d.onAnimationStart();
        }
    }
}