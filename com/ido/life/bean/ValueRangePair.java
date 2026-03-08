package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class ValueRangePair {
    public int mBarColor;
    public float maxValue;
    public float minValue;

    public ValueRangePair() {
        this.mBarColor = 0;
    }

    public ValueRangePair(float f2) {
        this.mBarColor = 0;
        this.maxValue = f2;
    }

    public ValueRangePair(float f2, float f3, int i) {
        this.mBarColor = 0;
        this.maxValue = f2;
        this.minValue = f3;
        this.mBarColor = i;
    }

    public ValueRangePair(float f2, float f3) {
        this.mBarColor = 0;
        this.maxValue = f2;
        this.minValue = f3;
    }

    public ValueRangePair cloneObj() {
        return new ValueRangePair(this.maxValue, this.minValue);
    }

    public String toString() {
        return "ValueRangePair{maxValue=" + this.maxValue + ", minValue=" + this.minValue + '}';
    }
}