package com.ido.life.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import com.ido.life.boatservice.WeatherSendCheckService;

/* JADX INFO: loaded from: classes3.dex */
public class AlarmManagerUtils {
    private static int MINUTE = 60000;
    public static AlarmManager am;
    private static AlarmManagerUtils instance;
    public static PendingIntent pendingIntent;
    private Context context;

    private AlarmManagerUtils(Context context) {
        this.context = context;
    }

    public static AlarmManagerUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (AlarmManagerUtils.class) {
                if (instance == null) {
                    instance = new AlarmManagerUtils(context);
                }
            }
        }
        return instance;
    }

    public void createGetUpAlarmManager() {
        am = (AlarmManager) this.context.getSystemService("alarm");
        pendingIntent = PendingIntent.getService(this.context, 0, new Intent(this.context, (Class<?>) WeatherSendCheckService.class), 0);
    }

    public void getUpAlarmManagerStartWork() {
        long jElapsedRealtime = SystemClock.elapsedRealtime() + ((long) MINUTE);
        if (am == null) {
            createGetUpAlarmManager();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            am.setExactAndAllowWhileIdle(0, jElapsedRealtime, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= 19) {
            am.setExact(0, jElapsedRealtime, pendingIntent);
        } else {
            am.setRepeating(0, jElapsedRealtime, MINUTE, pendingIntent);
        }
    }

    public void getUpAlarmManagerWorkOnOthers() {
        if (am == null) {
            createGetUpAlarmManager();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            am.setExactAndAllowWhileIdle(0, System.currentTimeMillis() + ((long) MINUTE), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= 19) {
            am.setExact(0, System.currentTimeMillis() + ((long) MINUTE), pendingIntent);
        }
    }
}