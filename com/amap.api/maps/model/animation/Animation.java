package com.amap.api.maps.model.animation;

import android.view.animation.Interpolator;
import com.autonavi.amap.mapcore.animation.GLAnimation;

/* JADX INFO: loaded from: classes.dex */
public abstract class Animation {
    public static final int FILL_MODE_BACKWARDS = 1;
    public static final int FILL_MODE_FORWARDS = 0;
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1876a = 0;
    public GLAnimation glAnimation;

    public interface AnimationListener {
        void onAnimationEnd();

        void onAnimationStart();
    }

    public abstract void setDuration(long j);

    public abstract void setInterpolator(Interpolator interpolator);

    public Animation() {
        this.glAnimation = null;
        this.glAnimation = new GLAnimation();
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.glAnimation.setAnimationListener(animationListener);
    }

    public void setFillMode(int i) {
        this.f1876a = i;
        if (this.f1876a == 0) {
            b(true);
            c(false);
            a(false);
        } else {
            b(false);
            a(true);
            c(true);
        }
    }

    public int getFillMode() {
        return this.f1876a;
    }

    private void a(boolean z) {
        GLAnimation gLAnimation = this.glAnimation;
        if (gLAnimation != null) {
            gLAnimation.setFillEnabled(z);
        }
    }

    private void b(boolean z) {
        GLAnimation gLAnimation = this.glAnimation;
        if (gLAnimation != null) {
            gLAnimation.setFillAfter(z);
        }
    }

    private void c(boolean z) {
        GLAnimation gLAnimation = this.glAnimation;
        if (gLAnimation != null) {
            gLAnimation.setFillBefore(z);
        }
    }

    public void setRepeatCount(int i) {
        GLAnimation gLAnimation = this.glAnimation;
        if (gLAnimation != null) {
            gLAnimation.setRepeatCount(i);
        }
    }

    public void setRepeatMode(int i) {
        GLAnimation gLAnimation = this.glAnimation;
        if (gLAnimation != null) {
            gLAnimation.setRepeatMode(i);
        }
    }

    public int getRepeatMode() {
        GLAnimation gLAnimation = this.glAnimation;
        if (gLAnimation != null) {
            return gLAnimation.getRepeatMode();
        }
        return 1;
    }

    public int getRepeatCount() {
        GLAnimation gLAnimation = this.glAnimation;
        if (gLAnimation != null) {
            return gLAnimation.getRepeatCount();
        }
        return 0;
    }
}