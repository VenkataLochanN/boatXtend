package com.ido.life.database.model;

import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMenstrualItem {
    private int hour;
    private int lastMenstrualDay;
    private int lastMenstrualMonth;
    private int lastMenstrualYear;
    private int menstrualCycle;
    private int menstrualLength;
    private int menstrualRemindBeforeDays;
    private int minute;
    private int ovulationIntervalDay;
    private int ovulationRemindBeforeDays;

    public ServerMenstrualItem() {
        this.ovulationIntervalDay = 14;
    }

    public ServerMenstrualItem(Menstrual menstrual, MenstrualRemind menstrualRemind) {
        this.ovulationIntervalDay = 14;
        this.lastMenstrualDay = menstrual.last_menstrual_day;
        this.lastMenstrualMonth = menstrual.last_menstrual_month;
        this.lastMenstrualYear = menstrual.last_menstrual_year;
        this.menstrualCycle = menstrual.menstrual_cycle;
        this.menstrualLength = menstrual.menstrual_length;
        this.ovulationIntervalDay = 14;
        this.menstrualRemindBeforeDays = menstrualRemind.start_day;
        this.ovulationRemindBeforeDays = menstrualRemind.ovulation_day;
        this.hour = menstrualRemind.hour;
        this.minute = menstrualRemind.minute;
    }

    public int getLastMenstrualDay() {
        return this.lastMenstrualDay;
    }

    public void setLastMenstrualDay(int i) {
        this.lastMenstrualDay = i;
    }

    public int getLastMenstrualMonth() {
        return this.lastMenstrualMonth;
    }

    public void setLastMenstrualMonth(int i) {
        this.lastMenstrualMonth = i;
    }

    public int getLastMenstrualYear() {
        return this.lastMenstrualYear;
    }

    public void setLastMenstrualYear(int i) {
        this.lastMenstrualYear = i;
    }

    public int getMenstrualCycle() {
        return this.menstrualCycle;
    }

    public void setMenstrualCycle(int i) {
        this.menstrualCycle = i;
    }

    public int getMenstrualLength() {
        return this.menstrualLength;
    }

    public void setMenstrualLength(int i) {
        this.menstrualLength = i;
    }

    public int getOvulationRemindBeforeDays() {
        return this.ovulationRemindBeforeDays;
    }

    public void setOvulationRemindBeforeDays(int i) {
        this.ovulationRemindBeforeDays = i;
    }

    public int getMenstrualRemindBeforeDays() {
        return this.menstrualRemindBeforeDays;
    }

    public void setMenstrualRemindBeforeDays(int i) {
        this.menstrualRemindBeforeDays = i;
    }

    public int getOvulationIntervalDay() {
        return this.ovulationIntervalDay;
    }

    public void setOvulationIntervalDay(int i) {
        this.ovulationIntervalDay = i;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public String toString() {
        return "ServerMenstrualItem{lastMenstrualDay=" + this.lastMenstrualDay + ", lastMenstrualMonth=" + this.lastMenstrualMonth + ", lastMenstrualYear=" + this.lastMenstrualYear + ", menstrualCycle=" + this.menstrualCycle + ", menstrualLength=" + this.menstrualLength + ", ovulationRemindBeforeDays=" + this.ovulationRemindBeforeDays + ", menstrualRemindBeforeDays=" + this.menstrualRemindBeforeDays + ", ovulationIntervalDay=" + this.ovulationIntervalDay + ", hour=" + this.hour + ", minute=" + this.minute + '}';
    }
}