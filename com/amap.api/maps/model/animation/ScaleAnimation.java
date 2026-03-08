package com.amap.api.maps.model.animation;

import android.view.animation.Interpolator;
import com.autonavi.amap.mapcore.animation.GLScaleAnimation;

/* JADX INFO: loaded from: classes.dex */
public class ScaleAnimation extends Animation {
    public ScaleAnimation(float f2, float f3, float f4, float f5) {
        this.glAnimation = new GLScaleAnimation(f2, f3, f4, f5);
        setFillMode(1);
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