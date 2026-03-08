package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class BarChartPoint extends BaseCharBean {
    public static final int Default_Color = -1;
    private int mBarColor;

    public BarChartPoint() {
        this.mBarColor = -1;
    }

    public BarChartPoint(int i, float f2, float f3, int i2) {
        super(i, f2, f3);
        this.mBarColor = -1;
        this.mBarColor = i2;
    }

    public BarChartPoint(int i, float f2, float f3, float f4, int i2) {
        super(f2, f3, i, f4);
        this.mBarColor = -1;
        this.mBarColor = i2;
    }

    public BarChartPoint(int i, float f2, float f3, float f4) {
        super(f2, f3, i, f4);
        this.mBarColor = -1;
    }

    public BarChartPoint(int i, float f2, float f3) {
        super(i, f2, f3);
        this.mBarColor = -1;
    }

    public int getBarColor() {
        return this.mBarColor;
    }

    public void setBarColor(int i) {
        this.mBarColor = i;
    }
}