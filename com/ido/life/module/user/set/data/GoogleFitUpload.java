package com.ido.life.module.user.set.data;

/* JADX INFO: loaded from: classes3.dex */
public class GoogleFitUpload {
    private float height;
    private float lastCal;
    private float lastDistance;
    private int lastStepCount;
    private long lastSysTime;
    private long sleepStartTime;
    private boolean state;
    private float weight;

    public boolean isState() {
        return this.state;
    }

    public void setState(boolean z) {
        this.state = z;
    }

    public int getLastStepCount() {
        return this.lastStepCount;
    }

    public void setLastStepCount(int i) {
        this.lastStepCount = i;
    }

    public float getLastDistance() {
        return this.lastDistance;
    }

    public void setLastDistance(float f2) {
        this.lastDistance = f2;
    }

    public float getLastCal() {
        return this.lastCal;
    }

    public void setLastCal(float f2) {
        this.lastCal = f2;
    }

    public long getLastSysTime() {
        return this.lastSysTime;
    }

    public void setLastSysTime(long j) {
        this.lastSysTime = j;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float f2) {
        this.height = f2;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float f2) {
        this.weight = f2;
    }

    public long getSleepStartTime() {
        return this.sleepStartTime;
    }

    public void setSleepStartTime(long j) {
        this.sleepStartTime = j;
    }

    public String toString() {
        return "-----------------------------------------GoogleFitUpload{State=" + this.state + ", lastStepCount=" + this.lastStepCount + ", lastDistance=" + this.lastDistance + ", lastCal=" + this.lastCal + ", height=" + this.height + ", weight=" + this.weight + ", sleepStartTime=" + this.sleepStartTime + ", lastSysTime=" + this.lastSysTime + "}-----------------------------------------";
    }
}