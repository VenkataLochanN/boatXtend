package com.ido.alexa.bean;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaReminder {
    public List<V3ReminderItem> item;

    public String toString() {
        return "AlexaReminder{item=" + this.item + '}';
    }

    public static class V3ReminderItem {
        public static final int STATUS_OFF = 170;
        public static final int STATUS_ON = 85;
        public int day;
        public int hour;
        public int minute;
        public int month;
        public int reminder_id;
        public String reminder_string;
        public int sec;
        public int status;
        public int year;

        public String toString() {
            return "V3ReminderItem{reminder_id=" + this.reminder_id + ", status=" + this.status + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", sec=" + this.sec + ", reminder_string='" + this.reminder_string + "'}";
        }
    }
}