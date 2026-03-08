package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PresureDayDetailBean<T> {

    @SerializedName("endDate")
    private String mEndDate;

    @SerializedName("higherRatio")
    private int mHigherRatio;

    @SerializedName("datas")
    private List<T> mList;

    @SerializedName("maxValue")
    private int mMaxValue;

    @SerializedName("mediumRatio")
    private int mMediumRatio;

    @SerializedName("minValue")
    private int mMinValue;

    @SerializedName("normalRatio")
    private int mNormalRatio;

    @SerializedName("relaxRatio")
    private int mRelaxRatio;

    @SerializedName("startDate")
    private String mStartDate;

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

    public int getRelaxRatio() {
        return this.mRelaxRatio;
    }

    public void setRelaxRatio(int i) {
        this.mRelaxRatio = i;
    }

    public int getNormalRatio() {
        return this.mNormalRatio;
    }

    public void setNormalRatio(int i) {
        this.mNormalRatio = i;
    }

    public int getMediumRatio() {
        return this.mMediumRatio;
    }

    public void setMediumRatio(int i) {
        this.mMediumRatio = i;
    }

    public int getHigherRatio() {
        return this.mHigherRatio;
    }

    public void setHigherRatio(int i) {
        this.mHigherRatio = i;
    }

    public String getStartDate() {
        return this.mStartDate;
    }

    public void setStartDate(String str) {
        this.mStartDate = str;
    }

    public String getEndDate() {
        return this.mEndDate;
    }

    public void setEndDate(String str) {
        this.mEndDate = str;
    }

    public List<T> getList() {
        return this.mList;
    }

    public void setList(List<T> list) {
        this.mList = list;
    }
}