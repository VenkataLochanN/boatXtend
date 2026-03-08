package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes2.dex */
public class HeartItemBean {

    @SerializedName("hr_value")
    private int mHrValue;

    @SerializedName("offset")
    private int mOffset;

    public HeartItemBean(int i, int i2) {
        this.mHrValue = i;
        this.mOffset = i2;
    }

    public int getHrValue() {
        return this.mHrValue;
    }

    public void setHrValue(int i) {
        this.mHrValue = i;
    }

    public int getOffset() {
        return this.mOffset;
    }

    public void setOffset(int i) {
        this.mOffset = i;
    }
}