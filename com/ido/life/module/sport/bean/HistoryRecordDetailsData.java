package com.ido.life.module.sport.bean;

/* JADX INFO: loaded from: classes2.dex */
public class HistoryRecordDetailsData {
    private long date;
    private String distance;
    private boolean isFaster;
    private int paceSpeed;
    public String paceSpeedStr;
    private long useTime;

    public HistoryRecordDetailsData() {
    }

    public HistoryRecordDetailsData(String str, int i, long j) {
        this.distance = str;
        this.paceSpeed = i;
        this.useTime = j;
    }

    public HistoryRecordDetailsData(long j, String str, int i, String str2, long j2, boolean z) {
        this.date = j;
        this.distance = str;
        this.paceSpeed = i;
        this.paceSpeedStr = str2;
        this.useTime = j2;
        this.isFaster = z;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long j) {
        this.date = j;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String str) {
        this.distance = str;
    }

    public int getPaceSpeed() {
        return this.paceSpeed;
    }

    public void setPaceSpeed(int i) {
        this.paceSpeed = i;
    }

    public String getPaceSpeedStr() {
        return this.paceSpeedStr;
    }

    public void setPaceSpeedStr(String str) {
        this.paceSpeedStr = str;
    }

    public long getUseTime() {
        return this.useTime;
    }

    public void setUseTime(long j) {
        this.useTime = j;
    }

    public boolean isFaster() {
        return this.isFaster;
    }

    public void setFaster(boolean z) {
        this.isFaster = z;
    }

    public String toString() {
        return "HistoryRecordDetailsData{date=" + this.date + ", distance='" + this.distance + "', paceSpeed=" + this.paceSpeed + ", paceSpeedStr='" + this.paceSpeedStr + "', useTime=" + this.useTime + ", isFaster=" + this.isFaster + '}';
    }
}