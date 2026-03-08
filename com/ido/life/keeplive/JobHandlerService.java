package com.ido.life.keeplive;

import android.app.ActivityManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public class JobHandlerService extends JobService {
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        KeepLiveLog.saveLog("JobHandlerService服务被创建");
        return 1;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        KeepLiveLog.saveLog("JobHandlerService服务启动，开始工作");
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        if (!isServiceStop(LocalService.class.getName()) && !isServiceStop(RemoteService.class.getName())) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            KeepLiveLog.saveLog("JobHandlerService服务启动，停止工作，同时系统版本不低于Android O。");
            startForegroundService(new Intent(this, (Class<?>) LocalService.class));
            return false;
        }
        KeepLiveLog.saveLog("JobHandlerService服务启动，停止工作，同时系统版本低于Android O。");
        startService(new Intent(this, (Class<?>) LocalService.class));
        return false;
    }

    public boolean isServiceStop(String str) {
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService("activity");
            if (activityManager == null) {
                return true;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                System.out.println(runningAppProcessInfo.processName);
                if (runningAppProcessInfo.processName.equals(str)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}