package com.ido.life.enums;

import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public enum WeightBmiEnum {
    WEIGHT(18.4f, Float.MIN_VALUE, R.string.weight_bmi_weight),
    NORMAL(23.9f, 18.5f, R.string.weight_bmi_normal),
    OVER(27.9f, 24.0f, R.string.weight_bmi_over),
    FAT(Float.MAX_VALUE, 28.0f, R.string.weight_bmi_fat);

    private float mMaxValue;
    private float mMinValue;
    private int mStateResId;

    public static float caluteBmi(float f2, float f3) {
        float f4 = f3 / 100.0f;
        if (f4 <= 0.0f) {
            return 0.0f;
        }
        return f2 / (f4 * f4);
    }

    WeightBmiEnum(float f2, float f3, int i) {
        this.mMaxValue = f2;
        this.mMinValue = f3;
        this.mStateResId = i;
    }

    public float getMaxValue() {
        return this.mMaxValue;
    }

    public float getMinValue() {
        return this.mMinValue;
    }

    public int getStateResId() {
        return this.mStateResId;
    }

    public static WeightBmiEnum getWeightEnum(float f2) {
        return f2 <= WEIGHT.getMaxValue() ? WEIGHT : f2 <= NORMAL.getMaxValue() ? NORMAL : f2 <= OVER.getMaxValue() ? OVER : FAT;
    }
}