package com.autonavi.amap.mapcore.animation;

/* JADX INFO: loaded from: classes.dex */
public class GLAlphaAnimation extends GLAnimation {
    public float mCurAlpha = 0.0f;
    public float mFromAlpha;
    public float mToAlpha;

    public GLAlphaAnimation(float f2, float f3) {
        this.mFromAlpha = 0.0f;
        this.mToAlpha = 1.0f;
        this.mFromAlpha = f2;
        this.mToAlpha = f3;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    protected void applyTransformation(float f2, GLTransformation gLTransformation) {
        float f3 = this.mFromAlpha;
        this.mCurAlpha = f3 + ((this.mToAlpha - f3) * f2);
        gLTransformation.alpha = this.mCurAlpha;
    }
}