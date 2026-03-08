package com.ido.alexa.bean;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaHrSkillBean {
    public static final int DATA_AVG = 1;
    public static final int DATA_MAX = 2;
    public static final int DATA_MIN = 3;
    public static final int TYPE_MONTH = 3;
    public static final int TYPE_TODAY = 1;
    public static final int TYPE_WEEK = 2;
    public static final int TYPE_YEAR = 4;
    private int dataType;
    private int timeType;

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public int getTimeType() {
        return this.timeType;
    }

    public void setTimeType(int i) {
        this.timeType = i;
    }
}