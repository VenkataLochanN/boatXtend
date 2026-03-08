package com.ido.life.keeplive;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.boat.Xtend.two.R;
import com.ido.life.keeplive.KeepLiveServer;

/* JADX INFO: loaded from: classes2.dex */
public class LocalService extends Service {
    public static final int ID_NOTIFICATION = 1;
    private ServiceConnection conn;
    private LocalBinder mLocalBinder;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mLocalBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getPackageName());
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager == null) {
                return;
            }
            NotificationChannel notificationChannel = new NotificationChannel(getPackageName(), getString(R.string.app_name), 3);
            notificationChannel.setSound(null, null);
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId(getPackageName());
            startForeground(1, builder.build());
        }
        if (this.conn == null) {
            this.conn = new LocalServiceConnection();
        }
        this.mLocalBinder = new LocalBinder();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        KeepLiveLog.saveLog("LocalService启动");
        Intent intent2 = new Intent();
        intent2.setClass(this, RemoteService.class);
        bindService(intent2, this.conn, 1);
        return 1;
    }

    static class LocalBinder extends KeepLiveServer.Stub {
        @Override // com.ido.life.keeplive.KeepLiveServer
        public String getName() {
            return "LocalService";
        }

        LocalBinder() {
        }
    }

    class LocalServiceConnection implements ServiceConnection {
        LocalServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            KeepLiveLog.saveLog("RemoteService onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            KeepLiveLog.saveLog("RemoteService onServiceDisconnected");
            try {
                KeepLiveLog.saveLog("低版本启动服务(LocalService)，直接通过startService来启动");
                if (Build.VERSION.SDK_INT >= 26) {
                    LocalService.this.startForegroundService(new Intent(LocalService.this, (Class<?>) RemoteService.class));
                } else {
                    LocalService.this.startService(new Intent(LocalService.this, (Class<?>) RemoteService.class));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            KeepLiveLog.saveLog("RemoteService onBindingDied ");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        KeepLiveLog.saveLog("LocalService onDestroy ");
    }
}