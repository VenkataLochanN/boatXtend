package com.ido.alexa.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class AvsSetAlertItem extends AvsItem {
    public static final String ALARM = "ALARM";
    public static final String REMINDER = "REMINDER";
    public static final String TIMER = "TIMER";
    private String reminderEventName;
    private String scheduledTime;
    private String type;

    public AvsSetAlertItem(String str, String str2, String str3) {
        super(str);
        this.type = str2;
        this.scheduledTime = str3;
    }

    public AvsSetAlertItem(String str, String str2, String str3, String str4) {
        this(str, str2, str3);
        this.reminderEventName = str4;
    }

    public String getReminderEventName() {
        return this.reminderEventName;
    }

    public void setReminderEventName(String str) {
        this.reminderEventName = str;
    }

    public String getScheduledTime() {
        return this.scheduledTime;
    }

    public void setScheduledTime(String str) {
        this.scheduledTime = str;
    }

    public long getScheduledTimeMillis() throws ParseException {
        return getDate().getTime();
    }

    public int getHour() throws ParseException {
        return getDate().getHours();
    }

    public int getMinutes() throws ParseException {
        return getDate().getMinutes();
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.parse(this.scheduledTime);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isTimer() {
        return this.type.equals(TIMER);
    }

    public boolean isAlarm() {
        return this.type.equals(ALARM);
    }

    public boolean isReminder() {
        return this.type.equals(REMINDER);
    }

    @Override // com.ido.alexa.data.AvsItem
    public String toString() {
        return "AvsSetAlertItem{type='" + this.type + "', scheduledTime='" + this.scheduledTime + "'}";
    }
}