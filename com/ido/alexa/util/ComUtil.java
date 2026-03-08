package com.ido.alexa.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.android.material.timepicker.TimeModel;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class ComUtil {
    public static String getStartdayThisWeek(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        int i3 = (-(calendar.get(7) - (i2 == 7 ? -1 : i2 == 2 ? 1 : 0))) + 1;
        if (i3 > 0) {
            i3 -= 7;
        }
        if (i3 <= -7) {
            i3 = 0;
        }
        calendar.add(5, i3 + (i * 7));
        return format(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
    }

    public static String getEnddayThisWeek(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        int i3 = 7 - (calendar.get(7) - (i2 == 7 ? -1 : i2 == 2 ? 1 : 0));
        if (i3 < 0) {
            i3 = 6;
        }
        if (i3 >= 7) {
            i3 = 0;
        }
        calendar.add(5, i3 + (i * 7));
        return format(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
    }

    private static String format(int i, int i2, int i3) {
        return i + "-" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)) + "-" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3));
    }

    public static void startService(Context context, Class cls) {
        startService(context, new Intent(context, (Class<?>) cls));
    }

    public static void startService(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    private static boolean isRunningForeground(Context context) {
        String packageName;
        ComponentName componentName = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity;
        return (componentName == null || (packageName = componentName.getPackageName()) == null || !packageName.equals(context.getPackageName())) ? false : true;
    }
}