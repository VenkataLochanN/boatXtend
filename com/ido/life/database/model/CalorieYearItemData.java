package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class CalorieYearItemData {
    private int avgActivityCalorie;
    private int avgCalorie;
    private int days;
    private Long id;
    private String month;
    private int totalActivityCalorie;
    private int totalCalorie;
    private long userId;

    public CalorieYearItemData(Long l, long j, String str, int i, int i2, int i3, int i4, int i5) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.month = str;
        this.days = i;
        this.avgCalorie = i2;
        this.totalCalorie = i3;
        this.avgActivityCalorie = i4;
        this.totalActivityCalorie = i5;
    }

    public CalorieYearItemData() {
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

    public int getAvgCalorie() {
        return this.avgCalorie;
    }

    public void setAvgCalorie(int i) {
        this.avgCalorie = i;
    }

    public int getTotalCalorie() {
        return this.totalCalorie;
    }

    public void setTotalCalorie(int i) {
        this.totalCalorie = i;
    }

    public int getAvgActivityCalorie() {
        return this.avgActivityCalorie;
    }

    public void setAvgActivityCalorie(int i) {
        this.avgActivityCalorie = i;
    }

    public int getTotalActivityCalorie() {
        return this.totalActivityCalorie;
    }

    public void setTotalActivityCalorie(int i) {
        this.totalActivityCalorie = i;
    }

    public String toString() {
        return "CalorieYearItemData{id=" + this.id + ", userId=" + this.userId + ", month='" + this.month + "', days=" + this.days + ", avgCalorie=" + this.avgCalorie + ", totalCalorie=" + this.totalCalorie + ", avgActivityCalorie=" + this.avgActivityCalorie + ", totalActivityCalorie=" + this.totalActivityCalorie + '}';
    }
}