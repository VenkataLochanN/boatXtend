package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class GradientBarPoint extends BarChartPoint {
    private float mAlpha;
    private int mGradientEndColor;
    private int mGradientStartColor;
    private boolean mHasRoundAngle;
    private float mRoundAnglePercent;
    private float mScaleRadius;

    public GradientBarPoint() {
        this.mHasRoundAngle = true;
        this.mGradientStartColor = -1;
        this.mGradientEndColor = -1;
        this.mScaleRadius = 1.0f;
        this.mAlpha = 1.0f;
    }

    public GradientBarPoint(int i, float f2, float f3, int i2) {
        super(i, f2, f3, i2);
        this.mHasRoundAngle = true;
        this.mGradientStartColor = -1;
        this.mGradientEndColor = -1;
        this.mScaleRadius = 1.0f;
        this.mAlpha = 1.0f;
    }

    public GradientBarPoint(int i, float f2, float f3, float f4, int i2, float f5) {
        super(i, f2, f3, f4, i2);
        this.mHasRoundAngle = true;
        this.mGradientStartColor = -1;
        this.mGradientEndColor = -1;
        this.mScaleRadius = 1.0f;
        this.mAlpha = 1.0f;
        this.mRoundAnglePercent = f5;
        this.mHasRoundAngle = true;
    }

    public GradientBarPoint(int i, float f2, float f3, float f4) {
        super(i, f2, f3, f4);
        this.mHasRoundAngle = true;
        this.mGradientStartColor = -1;
        this.mGradientEndColor = -1;
        this.mScaleRadius = 1.0f;
        this.mAlpha = 1.0f;
    }

    public GradientBarPoint(int i, float f2, float f3, float f4, float f5) {
        super(i, f2, f3, f4);
        this.mHasRoundAngle = true;
        this.mGradientStartColor = -1;
        this.mGradientEndColor = -1;
        this.mScaleRadius = 1.0f;
        this.mAlpha = 1.0f;
        this.mRoundAnglePercent = f5;
        this.mHasRoundAngle = true;
    }

    public GradientBarPoint(int i, float f2, float f3, float f4, int i2, int i3, float f5) {
        super(i, f2, f3, f4);
        this.mHasRoundAngle = true;
        this.mGradientStartColor = -1;
        this.mGradientEndColor = -1;
        this.mScaleRadius = 1.0f;
        this.mAlpha = 1.0f;
        this.mGradientStartColor = i2;
        this.mGradientEndColor = i3;
        this.mRoundAnglePercent = f5;
        this.mHasRoundAngle = true;
    }

    public int getGradientStartColor() {
        return this.mGradientStartColor;
    }

    public void setGradientStartColor(int i) {
        this.mGradientStartColor = i;
    }

    public int getGradientEndColor() {
        return this.mGradientEndColor;
    }

    public void setGradientEndColor(int i) {
        this.mGradientEndColor = i;
    }

    public float getScaleRadius() {
        return this.mScaleRadius;
    }

    public void setScaleRadius(float f2) {
        this.mScaleRadius = f2;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(float f2) {
        this.mAlpha = f2;
    }

    public boolean hasRoundAngle() {
        return this.mHasRoundAngle;
    }

    public void setHasRoundAngle(boolean z) {
        this.mHasRoundAngle = z;
    }

    public float getRoundAnglePercent() {
        return this.mRoundAnglePercent;
    }

    public void setRoundAnglePercent(float f2) {
        this.mRoundAnglePercent = f2;
    }
}