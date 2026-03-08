package com.amap.api.maps.model.animation;

import android.view.animation.Interpolator;
import com.autonavi.amap.mapcore.animation.GLAlphaAnimation;

/* JADX INFO: loaded from: classes.dex */
public class AlphaAnimation extends Animation {
    public AlphaAnimation(float f2, float f3) {
        this.glAnimation = new GLAlphaAnimation(f2, f3);
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