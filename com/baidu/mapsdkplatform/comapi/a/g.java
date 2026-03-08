package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;

/* JADX INFO: loaded from: classes.dex */
class g implements Animator.AnimatorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ f f3436a;

    g(f fVar) {
        this.f3436a = fVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        if (this.f3436a.f3432d != null) {
            this.f3436a.f3432d.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.f3436a.f3432d != null) {
            this.f3436a.f3432d.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        if (this.f3436a.f3432d != null) {
            this.f3436a.f3432d.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (this.f3436a.f3432d != null) {
            this.f3436a.f3432d.onAnimationStart();
        }
    }
}