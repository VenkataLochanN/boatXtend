package com.ido.ntf.exception;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import com.ido.ntf.R;
import com.ido.ntf.Utils.AlarmManagerUtils;
import com.ido.ntf.Utils.CommUtils;
import com.ido.ntf.Utils.TimeUtil;
import com.ido.ntf.log.LogHandle;

/* JADX INFO: loaded from: classes3.dex */
public class NotificationExceptionService extends IntentService {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";
    private static final String TAG = " NotificationSDK NotificationExceptionService";
    public static Handler clearCallHandler = new Handler();
    public static Handler callHandler = new Handler();

    public NotificationExceptionService() {
        super("NotificationExceptionService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        try {
            long jLongValue = ((Long) CommUtils.get(getApplicationContext(), "notification_time", 1L)).longValue();
            long jCurrentTimeMillis = System.currentTimeMillis();
            LogHandle.getInstance().printLog(" NotificationSDK NotificationExceptionService 通知判断服务在启动");
            if (jCurrentTimeMillis - jLongValue <= TimeUtil.getExceptionTime() * 60 * 1000 || !CommUtils.isOpenExceptionListener) {
                return;
            }
            CommUtils.put(getApplicationContext(), "notification_time", Long.valueOf(System.currentTimeMillis()));
            CommUtils.setIsNotificationException(false);
            LogHandle.getInstance().printLog(" NotificationSDK NotificationExceptionService 超过" + TimeUtil.ExceptionTime + "分钟，给自己发一次通知，如果未收到，提醒用户设置权限");
            final NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, 2));
            }
            notificationManager.notify(12121212, new NotificationCompat.Builder(CommUtils.mContext, PUSH_CHANNEL_ID).setSmallIcon(R.mipmap.veryfit_logo).setContentTitle("服务").setContentText("    ").setPriority(0).build());
            callHandler.postDelayed(new Runnable() { // from class: com.ido.ntf.exception.-$$Lambda$NotificationExceptionService$6_Z-6Aw7I3a85dEmhLcKIozI_Oo
                @Override // java.lang.Runnable
                public final void run() {
                    NotificationExceptionService.lambda$onHandleIntent$0();
                }
            }, 3000L);
            clearCallHandler.postDelayed(new Runnable() { // from class: com.ido.ntf.exception.-$$Lambda$NotificationExceptionService$NFjLieFJI7Ol1An-DUypN_eo4bA
                @Override // java.lang.Runnable
                public final void run() {
                    NotificationExceptionService.lambda$onHandleIntent$1(notificationManager);
                }
            }, 500L);
        } catch (Exception unused) {
            LogHandle.getInstance().printLog(" NotificationSDK NotificationExceptionService 通知判断服务异常");
        }
    }

    static /* synthetic */ void lambda$onHandleIntent$0() {
        LogHandle.getInstance().printLog(" NotificationSDK NotificationExceptionService 3秒判断通知服务" + CommUtils.isNotificationException);
        if (CommUtils.isIsNotificationException()) {
            return;
        }
        CommUtils.setIsNotificationException(true);
        CommUtils.put(CommUtils.mContext, "idontf_time", Long.valueOf(System.currentTimeMillis()));
        LogHandle.getInstance().printLog(" NotificationSDK NotificationExceptionService 3秒判断通知是异常" + CommUtils.isNotificationException);
        ExceptionHandle.getInstance().mNotificationExceptionListener.onNotificationException(true);
    }

    static /* synthetic */ void lambda$onHandleIntent$1(NotificationManager notificationManager) {
        notificationManager.cancel(12121212);
        LogHandle.getInstance().printLog(" NotificationSDK NotificationExceptionService 清除通知");
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogHandle.getInstance().printLog(" NotificationSDK NotificationExceptionService oncommond");
        AlarmManagerUtils.getInstance(getApplicationContext()).getUpAlarmManagerWorkOnOthers();
        return super.onStartCommand(intent, i, i2);
    }
}