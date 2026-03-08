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
public class RemoteService extends Service {
    private ServiceConnection conn;
    private RemoteBinder mRemoteBinder;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        KeepLiveLog.saveLog("RemoteService进程开启");
        init();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        KeepLiveLog.saveLog("RemoteService进程启动");
        Intent intent2 = new Intent();
        intent2.setClass(this, LocalService.class);
        bindService(intent2, this.conn, 1);
        return 1;
    }

    private void init() {
        if (this.conn == null) {
            this.conn = new RemoteConnection();
        }
        this.mRemoteBinder = new RemoteBinder();
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
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mRemoteBinder;
    }

    static class RemoteBinder extends KeepLiveServer.Stub {
        @Override // com.ido.life.keeplive.KeepLiveServer
        public String getName() {
            return "RemoteService";
        }

        RemoteBinder() {
        }
    }

    class RemoteConnection implements ServiceConnection {
        RemoteConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            KeepLiveLog.saveLog("LocalService onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            KeepLiveLog.saveLog("LocalService onServiceDisconnected");
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    KeepLiveLog.saveLog("服务断开连接(RemoteService),检测到当前系统版本不低于Android8。");
                    RemoteService.this.startForegroundService(new Intent(RemoteService.this, (Class<?>) LocalService.class));
                } else {
                    RemoteService.this.startService(new Intent(RemoteService.this, (Class<?>) LocalService.class));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            KeepLiveLog.saveLog("LocalService onBindingDied ");
        }
    }
}