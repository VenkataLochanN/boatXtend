package com.autonavi.amap.mapcore.animation;

/* JADX INFO: loaded from: classes.dex */
public class GLScaleAnimation extends GLAnimation {
    private float mFromX;
    private float mFromY;
    private float mPivotX = 0.0f;
    private float mPivotY = 0.0f;
    private float mToX;
    private float mToY;

    public GLScaleAnimation(float f2, float f3, float f4, float f5) {
        this.mFromX = f2;
        this.mToX = f3;
        this.mFromY = f4;
        this.mToY = f5;
    }

    @Override // com.autonavi.amap.mapcore.animation.GLAnimation
    protected void applyTransformation(float f2, GLTransformation gLTransformation) {
        float f3;
        float f4 = 1.0f;
        if (this.mFromX == 1.0f && this.mToX == 1.0f) {
            f3 = 1.0f;
        } else {
            float f5 = this.mFromX;
            f3 = f5 + ((this.mToX - f5) * f2);
        }
        if (this.mFromY != 1.0f || this.mToY != 1.0f) {
            float f6 = this.mFromY;
            f4 = f6 + ((this.mToY - f6) * f2);
        }
        if (this.mPivotX == 0.0f && this.mPivotY == 0.0f) {
            gLTransformation.scaleX = f3;
            gLTransformation.scaleY = f4;
        } else {
            gLTransformation.scaleX = f3;
            gLTransformation.scaleY = f4;
        }
    }
}