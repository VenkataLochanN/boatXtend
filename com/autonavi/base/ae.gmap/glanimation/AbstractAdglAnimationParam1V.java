package com.autonavi.base.ae.gmap.glanimation;

/* JADX INFO: loaded from: classes.dex */
public class AbstractAdglAnimationParam1V extends AbstractAdglAnimationParam {
    private float fromValue;
    private float toValue;

    public AbstractAdglAnimationParam1V() {
        reset();
    }

    @Override // com.autonavi.base.ae.gmap.glanimation.AbstractAdglAnimationParam
    public void reset() {
        super.reset();
        this.fromValue = 0.0f;
        this.toValue = 0.0f;
    }

    public void setFromValue(float f2) {
        this.fromValue = f2;
        this.hasFromValue = true;
        this.hasCheckedParam = false;
    }

    public void setToValue(float f2) {
        this.toValue = f2;
        this.hasToValue = true;
        this.hasCheckedParam = false;
    }

    public float getFromValue() {
        return this.fromValue;
    }

    public float getToValue() {
        return this.toValue;
    }

    public float getCurValue() {
        float f2 = this.fromValue;
        return f2 + ((this.toValue - f2) * this.mult);
    }

    @Override // com.autonavi.base.ae.gmap.glanimation.AbstractAdglAnimationParam
    public void checkParam() {
        this.needToCaculate = false;
        if (this.hasFromValue && this.hasToValue && Math.abs(this.toValue - this.fromValue) > 1.0E-4d) {
            this.needToCaculate = true;
        }
        this.hasCheckedParam = true;
    }
}