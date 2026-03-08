package com.ido.life.module.sport.setting;

/* JADX INFO: loaded from: classes2.dex */
public class SportSetting {
    private int category;
    private int distance;
    private int distanceInterval;
    private boolean isDistanceVoice;
    private boolean isRateWarning;
    private boolean isSportRemindOff;
    private boolean isSportTarget;
    private int rateWarning;
    private int step;
    private int time;

    public boolean isSportTarget() {
        return this.isSportTarget;
    }

    public void setSportTarget(boolean z) {
        this.isSportTarget = z;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int i) {
        this.category = i;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int i) {
        this.step = i;
    }

    public boolean isDistanceVoice() {
        return this.isDistanceVoice;
    }

    public void setDistanceVoice(boolean z) {
        this.isDistanceVoice = z;
    }

    public int getDistanceInterval() {
        return this.distanceInterval;
    }

    public void setDistanceInterval(int i) {
        this.distanceInterval = i;
    }

    public boolean isRateWarning() {
        return this.isRateWarning;
    }

    public void setRateWarning(boolean z) {
        this.isRateWarning = z;
    }

    public int getRateWarning() {
        return this.rateWarning;
    }

    public void setRateWarning(int i) {
        this.rateWarning = i;
    }

    public boolean isSportRemindOff() {
        return this.isSportRemindOff;
    }

    public void setSportRemindOff(boolean z) {
        this.isSportRemindOff = z;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int i) {
        this.time = i;
    }

    public String toString() {
        return "SportSetting{isSportTarget=" + this.isSportTarget + ", isSportRemindOff=" + this.isSportRemindOff + ", distance=" + this.distance + ", category=" + this.category + ", step=" + this.step + ", time=" + this.time + ", isDistanceVoice=" + this.isDistanceVoice + ", distanceInterval=" + this.distanceInterval + ", isRateWarning=" + this.isRateWarning + ", rateWarning=" + this.rateWarning + '}';
    }
}