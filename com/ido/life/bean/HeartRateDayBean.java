package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateDayBean extends FloatBarPoint {
    private int mEndSecond;
    private int mStartSecond;

    public HeartRateDayBean(int i, float f2, float f3, float f4, float f5, int i2, int i3) {
        super(i, f2, f3, f4, f5);
        this.mStartSecond = i2;
        this.mEndSecond = i3;
    }

    public int getStartSecond() {
        return this.mStartSecond;
    }

    public void setStartSecond(int i) {
        this.mStartSecond = i;
    }

    public int getEndSecond() {
        return this.mEndSecond;
    }

    public void setEndSecond(int i) {
        this.mEndSecond = i;
    }
}