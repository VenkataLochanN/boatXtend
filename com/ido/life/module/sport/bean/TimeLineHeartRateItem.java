package com.ido.life.module.sport.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class TimeLineHeartRateItem implements Serializable {
    private int hrTime;
    private int hrValue;

    public int getHrTime() {
        return this.hrTime;
    }

    public void setHrTime(int i) {
        this.hrTime = i;
    }

    public int getHrValue() {
        return this.hrValue;
    }

    public void setHrValue(int i) {
        this.hrValue = i;
    }

    public String toString() {
        return "TimeLineHeartRateItem{hrTime=" + this.hrTime + ", hrValue=" + this.hrValue + '}';
    }
}