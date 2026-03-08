package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;

/* JADX INFO: loaded from: classes.dex */
class e implements Animator.AnimatorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f3428a;

    e(d dVar) {
        this.f3428a = dVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        if (this.f3428a.f3425d != null) {
            this.f3428a.f3425d.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.f3428a.f3425d != null) {
            this.f3428a.f3425d.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        if (this.f3428a.f3425d != null) {
            this.f3428a.f3425d.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (this.f3428a.f3425d != null) {
            this.f3428a.f3425d.onAnimationStart();
        }
    }
}