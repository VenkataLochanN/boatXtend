package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class FloatBarPoint extends BaseCharBean {
    private float mMaxValue;
    private float mMinValue;

    public FloatBarPoint(int i, float f2, float f3, float f4, float f5) {
        super(i, f4, f5);
        this.mMaxValue = f2;
        this.mMinValue = f3;
    }

    public float getMaxValue() {
        return this.mMaxValue;
    }

    public void setMaxValue(float f2) {
        this.mMaxValue = f2;
    }

    public float getMinValue() {
        return this.mMinValue;
    }

    public void setMinValue(float f2) {
        this.mMinValue = f2;
    }
}