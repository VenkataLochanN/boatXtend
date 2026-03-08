package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class PressureBarChartPoint extends BarChartPoint {
    private int mEndMinute;
    private int mStartMinute;

    public PressureBarChartPoint(int i, float f2, float f3, int i2, int i3, int i4) {
        super(i, f2, f3, i2);
        this.mStartMinute = i3;
        this.mEndMinute = i4;
    }

    public PressureBarChartPoint(int i, float f2, float f3, float f4, int i2, int i3, int i4) {
        super(i, f2, f3, f4, i2);
        this.mStartMinute = i3;
        this.mEndMinute = i4;
    }

    public PressureBarChartPoint(int i, float f2, float f3, float f4, int i2, int i3) {
        super(i, f2, f3, f4);
        this.mStartMinute = i2;
        this.mEndMinute = i3;
    }

    public PressureBarChartPoint(int i, float f2, float f3, int i2, int i3) {
        super(i, f2, f3);
        this.mStartMinute = i2;
        this.mEndMinute = i3;
    }

    public int getStartMinute() {
        return this.mStartMinute;
    }

    public void setStartMinute(int i) {
        this.mStartMinute = i;
    }

    public int getEndMinute() {
        return this.mEndMinute;
    }

    public void setEndMinute(int i) {
        this.mEndMinute = i;
    }
}