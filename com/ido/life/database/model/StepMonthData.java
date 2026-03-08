package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class StepMonthData {
    private int avgSteps;
    private Long id;
    private String month;
    private int totalSteps;
    private long userId;

    public StepMonthData(Long l, long j, int i, int i2, String str) {
        this.id = l;
        this.userId = j;
        this.totalSteps = i;
        this.avgSteps = i2;
        this.month = str;
    }

    public StepMonthData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }

    public void setTotalSteps(int i) {
        this.totalSteps = i;
    }

    public int getAvgSteps() {
        return this.avgSteps;
    }

    public void setAvgSteps(int i) {
        this.avgSteps = i;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public String toString() {
        return "ServerStepMonthData{id=" + this.id + ", userId=" + this.userId + ", totalSteps=" + this.totalSteps + ", avgSteps=" + this.avgSteps + ", month='" + this.month + "'}";
    }
}