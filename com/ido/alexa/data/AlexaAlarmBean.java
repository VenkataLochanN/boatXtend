package com.ido.alexa.data;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaAlarmBean {
    private int alarm_id;
    private int day;
    private int hour;
    private int minute;
    private int month;
    private boolean on_off;
    private String reminder_name;
    private long scheduledTimeMillis;
    private int sec;
    private int status;
    private String token;
    private boolean[] weekRepeat = new boolean[7];
    private int year;

    public void setOn_off(boolean z) {
        this.on_off = z;
    }

    public boolean isOn_off() {
        return this.on_off;
    }

    public boolean[] getWeekRepeat() {
        return this.weekRepeat;
    }

    public void setWeekRepeat(boolean[] zArr) {
        this.weekRepeat = zArr;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public long getScheduledTimeMillis() {
        return this.scheduledTimeMillis;
    }

    public void setScheduledTimeMillis(long j) {
        this.scheduledTimeMillis = j;
    }

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

    public int getSec() {
        return this.sec;
    }

    public void setSec(int i) {
        this.sec = i;
    }

    public String getReminder_name() {
        return this.reminder_name;
    }

    public void setReminder_name(String str) {
        this.reminder_name = str;
    }

    public String toString() {
        return "AlexaAlarmBean{token='" + this.token + "', scheduledTimeMillis=" + this.scheduledTimeMillis + ", alarm_id=" + this.alarm_id + ", status=" + this.status + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", sec=" + this.sec + ", reminder_name='" + this.reminder_name + "', on_off=" + this.on_off + ", weekRepeat=" + Arrays.toString(this.weekRepeat) + '}';
    }
}