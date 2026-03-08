package com.baidu.mapsdkplatform.comapi.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.AlphaAnimation;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.RotateAnimation;
import com.baidu.mapapi.animation.ScaleAnimation;
import com.baidu.mapapi.animation.SingleScaleAnimation;
import com.baidu.mapapi.animation.Transformation;
import com.baidu.mapapi.map.Marker;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class d extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Animator f3422a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f3423b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f3424c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Animation.AnimationListener f3425d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3426e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<Animation> f3427f = new ArrayList<>();

    private ObjectAnimator b(Marker marker, Animation animation) {
        if (animation instanceof AlphaAnimation) {
            return ((a) animation.bdAnimation).a(marker);
        }
        if (animation instanceof RotateAnimation) {
            return ((f) animation.bdAnimation).a(marker);
        }
        if (animation instanceof Transformation) {
            return ((l) animation.bdAnimation).a(marker);
        }
        if (animation instanceof ScaleAnimation) {
            return ((h) animation.bdAnimation).a(marker);
        }
        if (animation instanceof SingleScaleAnimation) {
            return ((j) animation.bdAnimation).a(marker);
        }
        return null;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a() {
        Animator animator = this.f3422a;
        if (animator == null) {
            return;
        }
        animator.start();
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(int i) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.f3423b = j;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    protected void a(Animator animator) {
        if (animator == null) {
            return;
        }
        animator.addListener(new e(this));
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Interpolator interpolator) {
        this.f3424c = interpolator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Animation.AnimationListener animationListener) {
        this.f3425d = animationListener;
    }

    public void a(Animation animation) {
        if (this.f3427f.contains(animation)) {
            return;
        }
        this.f3427f.add(animation);
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void a(Marker marker, Animation animation) {
        ObjectAnimator objectAnimatorB;
        this.f3422a = new AnimatorSet();
        ArrayList<Animation> arrayList = this.f3427f;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            Animation animation2 = arrayList.get(i);
            if (animation2 != null && (objectAnimatorB = b(marker, animation2)) != null) {
                arrayList2.add(objectAnimatorB);
            }
        }
        long j = this.f3423b;
        if (j != 0) {
            this.f3422a.setDuration(j);
        }
        Interpolator interpolator = this.f3424c;
        if (interpolator != null) {
            this.f3422a.setInterpolator(interpolator);
        }
        if (arrayList2.size() != 0) {
            int i2 = this.f3426e;
            if (i2 == 0) {
                ((AnimatorSet) this.f3422a).playTogether(arrayList2);
            } else if (i2 == 1) {
                ((AnimatorSet) this.f3422a).playSequentially(arrayList2);
            }
        }
        a(this.f3422a);
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void b() {
        Animator animator = this.f3422a;
        if (animator != null) {
            animator.cancel();
            this.f3422a = null;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void b(int i) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.a.c
    public void c(int i) {
        this.f3426e = i;
    }
}