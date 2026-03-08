package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.Marker;

/* JADX INFO: loaded from: classes.dex */
public class j extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Animator f3445a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f3446b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f3447c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Animation.AnimationListener f3448d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3449e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3450f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float[] f3451g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3452h;

    public j(int i, float... fArr) {
        this.f3452h = 1;
        this.f3451g = fArr;
        this.f3452h = i;
    }

    ObjectAnimator a(Marker marker) {
        int i = this.f3452h;
        ObjectAnimator objectAnimatorOfFloat = i == 1 ? ObjectAnimator.ofFloat(marker, "scaleX", this.f3451g) : i == 2 ? ObjectAnimator.ofFloat(marker, "scaleY", this.f3451g) : null;
        if (objectAnimatorOfFloat != null) {
            objectAnimatorOfFloat.setRepeatCount(this.f3450f);
            objectAnimatorOfFloat.setRepeatMode(c());
            objectAnimatorOfFloat.setDuration(this.f3446b);
            Interpolator interpolator = this.f3447c;
            if (interpolator != null) {
                objectAnimatorOfFloat.setInterpolator(interpolator);
            }
        }
        return objectAnimatorOfFloat;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a() {
        Animator animator = this.f3445a;
        if (animator == null) {
            return;
        }
        animator.start();
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(int i) {
        this.f3449e = i;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.f3446b = j;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    protected void a(Animator animator) {
        if (animator == null) {
            return;
        }
        animator.addListener(new k(this));
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Interpolator interpolator) {
        this.f3447c = interpolator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Animation.AnimationListener animationListener) {
        this.f3448d = animationListener;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Marker marker, Animation animation) {
        this.f3445a = a(marker);
        a(this.f3445a);
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void b() {
        Animator animator = this.f3445a;
        if (animator != null) {
            animator.cancel();
            this.f3445a = null;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void b(int i) {
        if (i > 0 || i == -1) {
            this.f3450f = i;
        }
    }

    public int c() {
        return this.f3449e;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void c(int i) {
    }
}