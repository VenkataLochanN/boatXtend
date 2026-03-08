package com.realsil.sdk.core.base;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.IBinder;
import android.view.Display;
import com.realsil.sdk.core.logger.ZLogger;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseService extends Service {
    public DisplayManager i;
    public PendingIntent j;
    public IBinder k;
    public Context mContext;
    public NotificationManager mNotificationManager;
    public int notificationId = 1230;
    public boolean l = false;
    public final DisplayManager.DisplayListener m = new DisplayManager.DisplayListener() { // from class: com.realsil.sdk.core.base.BaseService.1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }
    };

    public final String a(String str, String str2) {
        NotificationChannel notificationChannel = new NotificationChannel(str, str2, 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return str;
    }

    public Notification buildNotification(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i < 16) {
            return new Notification();
        }
        Notification.Builder builder = i >= 26 ? new Notification.Builder(getApplicationContext(), getChannelId()) : new Notification.Builder(getApplicationContext());
        builder.setContentText("GuardService").setWhen(System.currentTimeMillis());
        return builder.build();
    }

    public String getChannelId() {
        return "rtk_channel_id";
    }

    public String getChannelName() {
        return "rtk_channel_name";
    }

    public int getNotificationId() {
        return this.notificationId;
    }

    public boolean isScreenOn() {
        Display[] displays;
        DisplayManager displayManager = this.i;
        if (displayManager == null || (displays = displayManager.getDisplays()) == null) {
            return false;
        }
        for (Display display : displays) {
            if (Build.VERSION.SDK_INT >= 20 && display.getState() == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isServiceRunningInForeground(Context context) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (getClass().getName().equals(runningServiceInfo.service.getClassName()) && runningServiceInfo.foreground) {
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ZLogger.w("in onBind()");
        this.l = false;
        return this.k;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.l = true;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        this.i = (DisplayManager) this.mContext.getSystemService("display");
        DisplayManager displayManager = this.i;
        if (displayManager != null) {
            displayManager.registerDisplayListener(this.m, null);
        }
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            a(getChannelId(), getChannelName());
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        DisplayManager displayManager = this.i;
        if (displayManager != null) {
            displayManager.unregisterDisplayListener(this.m);
        }
        stopForeground(true);
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        ZLogger.w("in onRebind()");
        this.l = false;
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        ZLogger.w("Last client unbound from service");
        if (this.l) {
            return true;
        }
        ZLogger.w("Starting foreground service");
        return true;
    }

    public void setNotificationId(int i) {
        this.notificationId = i;
    }

    public void showNotification(String str, String str2, PendingIntent pendingIntent, int i, int i2) {
        Notification notification;
        this.j = pendingIntent;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 16) {
            Notification.Builder builder = i3 >= 26 ? new Notification.Builder(getApplicationContext(), getChannelId()) : new Notification.Builder(getApplicationContext());
            if (pendingIntent != null) {
                builder.setContentIntent(pendingIntent);
            }
            if (i != -1) {
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), i));
            }
            if (i2 != -1) {
                builder.setSmallIcon(i2);
            }
            builder.setContentTitle(str).setContentText(str2).setWhen(System.currentTimeMillis());
            notification = builder.build();
        } else {
            notification = new Notification();
        }
        if (notification != null) {
            startForeground(this.notificationId, notification);
        }
    }

    public void showNotification(String str, String str2, ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        showNotification(str, str2, PendingIntent.getActivity(this, 0, intent, 0), -1, -1);
    }
}