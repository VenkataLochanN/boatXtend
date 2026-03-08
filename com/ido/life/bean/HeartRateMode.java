package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateMode implements Serializable {
    public static final int SMART_HEART_RATE_MODE = 221;
    public static final int UNIT_TYPE_MINUTE = 2;
    public static final int UNIT_TYPE_SECOND = 1;
    public int interval;
    public int unitType;

    public HeartRateMode() {
    }

    public HeartRateMode(int i, int i2) {
        this.interval = i;
        this.unitType = i2;
    }

    public String toString() {
        return "HeartRateMode{interval=" + this.interval + ", unitType=" + this.unitType + '}';
    }
}