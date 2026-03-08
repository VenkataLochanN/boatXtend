package com.amap.api.maps;

import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.interfaces.IInfoWindowManager;

/* JADX INFO: loaded from: classes.dex */
public class InfoWindowAnimationManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    IInfoWindowManager f1840a;

    public InfoWindowAnimationManager(IInfoWindowManager iInfoWindowManager) {
        this.f1840a = null;
        this.f1840a = iInfoWindowManager;
    }

    public void setInfoWindowAnimation(Animation animation, Animation.AnimationListener animationListener) {
        this.f1840a.setInfoWindowAnimation(animation, animationListener);
    }

    public void setInfoWindowAppearAnimation(Animation animation) {
        this.f1840a.setInfoWindowAppearAnimation(animation);
    }

    public void setInfoWindowBackColor(int i) {
        this.f1840a.setInfoWindowBackColor(i);
    }

    public void setInfoWindowBackEnable(boolean z) {
        this.f1840a.setInfoWindowBackEnable(z);
    }

    public void setInfoWindowBackScale(float f2, float f3) {
        this.f1840a.setInfoWindowBackScale(f2, f3);
    }

    public void setInfoWindowDisappearAnimation(Animation animation) {
        this.f1840a.setInfoWindowDisappearAnimation(animation);
    }

    public void setInfoWindowMovingAnimation(Animation animation) {
        this.f1840a.setInfoWindowMovingAnimation(animation);
    }

    public void startAnimation() {
        this.f1840a.startAnimation();
    }
}