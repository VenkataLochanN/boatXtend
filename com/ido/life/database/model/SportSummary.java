package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class SportSummary {
    private int numSports;
    private int totalCalories;
    private int totalDistances;
    private int totalSteps;
    private int totalTimes;

    public SportSummary(int i, int i2, int i3, int i4, int i5) {
        this.numSports = i;
        this.totalCalories = i2;
        this.totalSteps = i3;
        this.totalTimes = i4;
        this.totalDistances = i5;
    }

    public int getNumSports() {
        return this.numSports;
    }

    public void setNumSports(int i) {
        this.numSports = i;
    }

    public int getTotalCalories() {
        return this.totalCalories;
    }

    public void setTotalCalories(int i) {
        this.totalCalories = i;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }

    public void setTotalSteps(int i) {
        this.totalSteps = i;
    }

    public int getTotalTimes() {
        return this.totalTimes;
    }

    public void setTotalTimes(int i) {
        this.totalTimes = i;
    }

    public int getTotalDistances() {
        return this.totalDistances;
    }

    public void setTotalDistances(int i) {
        this.totalDistances = i;
    }

    public String toString() {
        return "SportSummary{numSports=" + this.numSports + ", totalCalories=" + this.totalCalories + ", totalSteps=" + this.totalSteps + ", totalTimes=" + this.totalTimes + ", totalDistances=" + this.totalDistances + '}';
    }
}