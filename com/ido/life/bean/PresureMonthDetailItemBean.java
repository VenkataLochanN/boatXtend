package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes2.dex */
public class PresureMonthDetailItemBean {

    @SerializedName("days")
    private int mDays;

    @SerializedName("maxValue")
    private int mMaxValue;

    @SerializedName("minValue")
    private int mMinValue;

    @SerializedName("month")
    private String mMonth;

    public int getMinValue() {
        return this.mMinValue;
    }

    public void setMinValue(int i) {
        this.mMinValue = i;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public void setMaxValue(int i) {
        this.mMaxValue = i;
    }

    public String getMonth() {
        return this.mMonth;
    }

    public void setMonth(String str) {
        this.mMonth = str;
    }

    public int getDays() {
        return this.mDays;
    }

    public void setDays(int i) {
        this.mDays = i;
    }
}