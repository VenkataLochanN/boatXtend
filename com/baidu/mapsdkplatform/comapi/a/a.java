package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.Marker;

/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Animator f3414a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f3415b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f3416c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Animation.AnimationListener f3417d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3418e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3419f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float[] f3420g;

    public a(float... fArr) {
        this.f3420g = fArr;
    }

    ObjectAnimator a(Marker marker) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(marker, "alpha", this.f3420g);
        if (objectAnimatorOfFloat != null) {
            objectAnimatorOfFloat.setRepeatCount(this.f3419f);
            objectAnimatorOfFloat.setRepeatMode(c());
            objectAnimatorOfFloat.setDuration(this.f3415b);
            Interpolator interpolator = this.f3416c;
            if (interpolator != null) {
                objectAnimatorOfFloat.setInterpolator(interpolator);
            }
        }
        return objectAnimatorOfFloat;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a() {
        Animator animator = this.f3414a;
        if (animator == null) {
            return;
        }
        animator.start();
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(int i) {
        this.f3418e = i;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.f3415b = j;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    protected void a(Animator animator) {
        if (animator == null) {
            return;
        }
        animator.addListener(new b(this));
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Interpolator interpolator) {
        this.f3416c = interpolator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Animation.AnimationListener animationListener) {
        this.f3417d = animationListener;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Marker marker, Animation animation) {
        this.f3414a = a(marker);
        a(this.f3414a);
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void b() {
        Animator animator = this.f3414a;
        if (animator != null) {
            animator.cancel();
            this.f3414a = null;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void b(int i) {
        if (i > 0 || i == -1) {
            this.f3419f = i;
        }
    }

    public int c() {
        return this.f3418e;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void c(int i) {
    }
}