package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;

/* JADX INFO: loaded from: classes.dex */
class b implements Animator.AnimatorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f3421a;

    b(a aVar) {
        this.f3421a = aVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        if (this.f3421a.f3417d != null) {
            this.f3421a.f3417d.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.f3421a.f3417d != null) {
            this.f3421a.f3417d.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        if (this.f3421a.f3417d != null) {
            this.f3421a.f3417d.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (this.f3421a.f3417d != null) {
            this.f3421a.f3417d.onAnimationStart();
        }
    }
}