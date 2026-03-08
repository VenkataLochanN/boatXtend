package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateChartPoint extends FloatBarPoint {
    private int mEndHour;
    private int mStartHour;

    public HeartRateChartPoint(int i, float f2, float f3, float f4, float f5, int i2, int i3) {
        super(i, f2, f3, f4, f5);
        this.mStartHour = i2;
        this.mEndHour = i3;
    }

    public int getStartHour() {
        return this.mStartHour;
    }

    public void setStartHour(int i) {
        this.mStartHour = i;
    }

    public int getEndHour() {
        return this.mEndHour;
    }

    public void setEndHour(int i) {
        this.mEndHour = i;
    }
}