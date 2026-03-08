package com.autonavi.base.ae.gmap.glanimation;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractAdglAnimationParam {
    protected boolean hasCheckedParam;
    protected float mult;
    protected boolean needToCaculate;
    protected float normalizedTime;
    protected int interpolationType = 0;
    protected float factor = 1.0f;
    protected boolean hasFromValue = false;
    protected boolean hasToValue = false;

    static float bounce(float f2) {
        return f2 * f2 * 8.0f;
    }

    public abstract void checkParam();

    public void reset() {
        this.hasCheckedParam = false;
        this.needToCaculate = false;
        this.interpolationType = 0;
        this.factor = 1.0f;
        this.hasCheckedParam = false;
        this.needToCaculate = false;
        this.hasFromValue = false;
        this.hasToValue = false;
    }

    public boolean needToCaculate() {
        if (!this.hasCheckedParam) {
            checkParam();
        }
        return this.hasCheckedParam && this.needToCaculate;
    }

    public float getCurMult() {
        return this.mult;
    }

    public void setNormalizedTime(float f2) {
        double dPow;
        float fBounce;
        float f3;
        this.normalizedTime = f2;
        switch (this.interpolationType) {
            case 0:
                break;
            case 1:
                dPow = Math.pow(f2, this.factor * 2.0f);
                f2 = (float) dPow;
                break;
            case 2:
                if (this.factor != 1.0f) {
                    f2 = (float) (1.0d - Math.pow(1.0f - f2, r0 * 2.0f));
                } else {
                    float f4 = 1.0f - f2;
                    f2 = 1.0f - (f4 * f4);
                }
                break;
            case 3:
                dPow = (Math.cos(((double) (f2 + 1.0f)) * 3.141592653589793d) / 2.0d) + 0.5d;
                f2 = (float) dPow;
                break;
            case 4:
                float f5 = f2 * 1.1226f;
                if (f5 >= 0.3535f) {
                    if (f5 < 0.7408f) {
                        fBounce = bounce(f5 - 0.54719f);
                        f3 = 0.7f;
                    } else if (f5 < 0.9644f) {
                        fBounce = bounce(f5 - 0.8526f);
                        f3 = 0.9f;
                    } else {
                        fBounce = bounce(f5 - 1.0435f);
                        f3 = 0.95f;
                    }
                    f2 = fBounce + f3;
                } else {
                    f2 = bounce(f5);
                }
                break;
            case 5:
                float f6 = f2 - 1.0f;
                f2 = (f6 * f6 * ((3.0f * f6) + 2.0f)) + 1.0f;
                break;
            case 6:
                f2 = f2 >= 0.0f ? f2 >= 0.25f ? f2 >= 0.5f ? f2 >= 0.75f ? f2 > 1.0f ? 0.0f : 4.0f - (f2 * 4.0f) : (f2 * 4.0f) - 2.0f : 2.0f - (f2 * 4.0f) : f2 * 4.0f : 0.0f;
                break;
            default:
                f2 = 0.0f;
                break;
        }
        this.mult = f2;
    }

    public void setInterpolatorType(int i, float f2) {
        this.interpolationType = i;
        this.factor = f2;
    }

    public int getInterpolatorType() {
        return this.interpolationType;
    }

    public AbstractAdglAnimationParam() {
        this.hasCheckedParam = false;
        this.needToCaculate = false;
        this.hasCheckedParam = false;
        this.needToCaculate = false;
    }
}