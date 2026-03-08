package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class LifeCycleTipBean {
    int mensesStartDay;
    int ovulationDay;
    int pregnancyDayBeforeRemind;
    String reminderTime;

    public String toString() {
        return "LifeCycleTipBean{mensesStartDay=" + this.mensesStartDay + ", ovulationDay=" + this.ovulationDay + ", pregnancyDayBeforeRemind=" + this.pregnancyDayBeforeRemind + ", reminderTime='" + this.reminderTime + "'}";
    }

    public int getMensesStartDay() {
        return this.mensesStartDay;
    }

    public void setMensesStartDay(int i) {
        this.mensesStartDay = i;
    }

    public int getOvulationDay() {
        return this.ovulationDay;
    }

    public void setOvulationDay(int i) {
        this.ovulationDay = i;
    }

    public int getPregnancyDayBeforeRemind() {
        return this.pregnancyDayBeforeRemind;
    }

    public void setPregnancyDayBeforeRemind(int i) {
        this.pregnancyDayBeforeRemind = i;
    }

    public String getReminderTime() {
        return this.reminderTime;
    }

    public void setReminderTime(String str) {
        this.reminderTime = str;
    }
}