package com.ido.ntf.Utils;

import android.text.format.Time;

/* JADX INFO: loaded from: classes3.dex */
public class TimeUtil {
    public static int ExceptionTime = 120;

    public static int getExceptionTime() {
        return ExceptionTime;
    }

    public static void setExceptionTime(int i) {
        ExceptionTime = i;
    }

    public static Time getTime() {
        Time time = new Time(Time.getCurrentTimezone());
        time.setToNow();
        return time;
    }
}