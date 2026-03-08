package com.amap.api.maps.model.animation;

import android.view.animation.Interpolator;
import com.autonavi.amap.mapcore.animation.GLRotateAnimation;

/* JADX INFO: loaded from: classes.dex */
public class RotateAnimation extends Animation {
    public RotateAnimation(float f2, float f3, float f4, float f5, float f6) {
        this.glAnimation = new GLRotateAnimation(f2, f3, f4, f5, f6);
    }

    public RotateAnimation(float f2, float f3) {
        this.glAnimation = new GLRotateAnimation(f2, f3, 0.0f, 0.0f, 0.0f);
    }

    @Override // com.amap.api.maps.model.animation.Animation
    public void setDuration(long j) {
        this.glAnimation.setDuration(j);
    }

    @Override // com.amap.api.maps.model.animation.Animation
    public void setInterpolator(Interpolator interpolator) {
        this.glAnimation.setInterpolator(interpolator);
    }
}