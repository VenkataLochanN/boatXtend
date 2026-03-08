package com.ido.alexa.bean;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaV3AlarmItem {
    public static final int STATUS_DISPLAY = 85;
    public static final int STATUS_NOT_DISPLAY = 170;
    private int alarm_id;
    private int day;
    private int hour;
    private int minute;
    private int month;
    private boolean on_off;
    private int repeat;
    private int status;
    private boolean[] weekRepeat = new boolean[7];
    private int year;

    public int getAlarm_id() {
        return this.alarm_id;
    }

    public void setAlarm_id(int i) {
        this.alarm_id = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
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

    public boolean[] getWeekRepeat() {
        byteToWeekRepeat();
        return this.weekRepeat;
    }

    public void setWeekRepeat(boolean[] zArr) {
        this.weekRepeat = zArr;
        this.repeat = weekRepeatToByte(zArr, this.on_off);
    }

    private void byteToWeekRepeat() {
        this.weekRepeat = new boolean[7];
        int i = 0;
        while (i < 7) {
            int i2 = i + 1;
            if ((this.repeat & (1 << i2)) != 0) {
                this.weekRepeat[i] = true;
            } else {
                this.weekRepeat[i] = false;
            }
            i = i2;
        }
        if ((this.repeat & 1) == 1) {
            this.on_off = true;
        } else {
            this.on_off = false;
        }
    }

    private int weekRepeatToByte(boolean[] zArr, boolean z) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return z ? i | 1 : i;
    }

    public boolean isOn_off() {
        byteToWeekRepeat();
        return this.on_off;
    }

    public void setOn_off(boolean z) {
        this.on_off = z;
        this.repeat = weekRepeatToByte(this.weekRepeat, z);
    }

    public String toString() {
        return "AlexaV3AlarmItem{alarm_id=" + this.alarm_id + ", status=" + this.status + ", on_off=" + this.on_off + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", repeat=" + this.repeat + ", weekRepeat=" + Arrays.toString(this.weekRepeat) + '}';
    }
}