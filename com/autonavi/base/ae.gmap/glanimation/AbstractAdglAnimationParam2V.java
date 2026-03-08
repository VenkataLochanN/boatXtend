package com.autonavi.base.ae.gmap.glanimation;

/* JADX INFO: loaded from: classes.dex */
public class AbstractAdglAnimationParam2V extends AbstractAdglAnimationParam {
    public double fromXValue;
    public double fromYValue;
    public double toXValue;
    public double toYValue;

    public AbstractAdglAnimationParam2V() {
        reset();
    }

    @Override // com.autonavi.base.ae.gmap.glanimation.AbstractAdglAnimationParam
    public void reset() {
        super.reset();
        this.fromXValue = 0.0d;
        this.toXValue = 0.0d;
        this.fromYValue = 0.0d;
        this.toYValue = 0.0d;
    }

    public void setFromValue(double d2, double d3) {
        this.fromXValue = d2;
        this.fromYValue = d3;
        this.hasFromValue = true;
        this.hasCheckedParam = false;
    }

    public void setToValue(double d2, double d3) {
        this.toXValue = d2;
        this.toYValue = d3;
        this.hasToValue = true;
        this.hasCheckedParam = false;
    }

    public double getFromXValue() {
        return this.fromXValue;
    }

    public double getFromYValue() {
        return this.fromYValue;
    }

    public double getToXValue() {
        return this.toXValue;
    }

    public double getToYValue() {
        return this.toYValue;
    }

    public double getCurXValue() {
        double d2 = this.fromXValue;
        return d2 + ((this.toXValue - d2) * ((double) this.mult));
    }

    public double getCurYValue() {
        double d2 = this.fromYValue;
        return d2 + ((this.toYValue - d2) * ((double) this.mult));
    }

    @Override // com.autonavi.base.ae.gmap.glanimation.AbstractAdglAnimationParam
    public void checkParam() {
        this.needToCaculate = false;
        if (this.hasFromValue && this.hasToValue) {
            double d2 = this.toXValue - this.fromXValue;
            double d3 = this.toYValue - this.fromYValue;
            if (Math.abs(d2) > 1.0E-4d || Math.abs(d3) > 1.0E-4d) {
                this.needToCaculate = true;
            }
        }
        this.hasCheckedParam = true;
    }
}