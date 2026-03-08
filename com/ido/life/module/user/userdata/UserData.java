package com.ido.life.module.user.userdata;

/* JADX INFO: loaded from: classes3.dex */
public class UserData {
    private int day;
    private int hegiht;
    private int heightUnit;
    private int month;
    private int sex;
    private int weight;
    private int weightUnit;
    private int year;

    public int getSex() {
        return this.sex;
    }

    public void setSex(int i) {
        this.sex = i;
    }

    public int getHegiht() {
        return this.hegiht;
    }

    public void setHegiht(int i) {
        this.hegiht = i;
    }

    public int getHeightUnit() {
        return this.heightUnit;
    }

    public void setHeightUnit(int i) {
        this.heightUnit = i;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public int getWeightUnit() {
        return this.weightUnit;
    }

    public void setWeightUnit(int i) {
        this.weightUnit = i;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public String toString() {
        return "UserData{sex=" + this.sex + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hegiht=" + this.hegiht + ", heightUnit=" + this.heightUnit + ", weight=" + this.weight + ", weightUnit=" + this.weightUnit + '}';
    }
}