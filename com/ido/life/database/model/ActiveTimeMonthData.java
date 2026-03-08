package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveTimeMonthData {
    private int avgSeconds;
    private int days;
    private Long id;
    private String month;
    private int totalSeconds;
    private long userId;

    public ActiveTimeMonthData(Long l, long j, int i, int i2, String str, int i3) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.avgSeconds = i;
        this.totalSeconds = i2;
        this.month = str;
        this.days = i3;
    }

    public ActiveTimeMonthData() {
        this.userId = -1L;
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

    public int getAvgSeconds() {
        return this.avgSeconds;
    }

    public void setAvgSeconds(int i) {
        this.avgSeconds = i;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int i) {
        this.days = i;
    }

    public int getTotalSeconds() {
        return this.totalSeconds;
    }

    public void setTotalSeconds(int i) {
        this.totalSeconds = i;
    }

    public String toString() {
        return "ActiveTimeMonthData{id=" + this.id + ", userId=" + this.userId + ", avgSeconds=" + this.avgSeconds + ", totalSeconds=" + this.totalSeconds + ", month='" + this.month + "', days=" + this.days + '}';
    }
}