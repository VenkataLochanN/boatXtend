package com.ido.ntf;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import com.ido.ntf.Utils.CommUtils;
import com.ido.ntf.callback.ICallPhoneInfoBack;
import com.ido.ntf.callback.INotificationBack;
import com.ido.ntf.callback.INotificationExceptionListener;
import com.ido.ntf.exception.ExceptionHandle;
import com.ido.ntf.exception.ServiceManager;
import com.ido.ntf.log.LogBack;
import com.ido.ntf.log.LogHandle;
import com.ido.ntf.notification.NotificationHandle;
import com.ido.ntf.phone.PhoneStateHandle;

/* JADX INFO: loaded from: classes3.dex */
public class NotificationAndCallManager {
    public static void init(Context context) {
        CommUtils.mContext = context;
    }

    public static void filterNotifications(StatusBarNotification statusBarNotification, boolean z, INotificationBack iNotificationBack) {
        if (CommUtils.mContext == null) {
            LogHandle.getInstance().printLog(" sdk 未初始化 mContext==null");
        } else {
            NotificationHandle.getInstance(CommUtils.mContext).filterNotifications(statusBarNotification, 0, z, iNotificationBack);
        }
    }

    public static void filterNotifications(StatusBarNotification statusBarNotification, INotificationBack iNotificationBack) {
        filterNotifications(statusBarNotification, true, iNotificationBack);
    }

    public static void filterCallNotification(StatusBarNotification statusBarNotification) {
        if (CommUtils.mContext == null) {
            LogHandle.getInstance().printLog(" sdk 未初始化 mContext==null");
        } else {
            NotificationHandle.getInstance(CommUtils.mContext).filterNotifications(statusBarNotification, 1, false, null);
        }
    }

    public static void notificationExceptionListener(StatusBarNotification statusBarNotification) {
        if (CommUtils.mContext == null) {
            LogHandle.getInstance().printLog(" sdk 未初始化 mContext==null");
        } else {
            NotificationHandle.getInstance(CommUtils.mContext).filterNotifications(statusBarNotification, 2, false, null);
        }
    }

    public static void setNotificationExceptionListener(int i, boolean z, INotificationExceptionListener iNotificationExceptionListener) {
        if (CommUtils.mContext == null) {
            LogHandle.getInstance().printLog(" sdk 未初始化 mContext==null");
            return;
        }
        ServiceManager.StartAlarmManager();
        CommUtils.isOpenExceptionListener = z;
        ExceptionHandle.getInstance().initExceptionListener(i, iNotificationExceptionListener);
    }

    public static void setCallStateRingingStateListener(int i, String str, ICallPhoneInfoBack iCallPhoneInfoBack) {
        if (CommUtils.mContext == null) {
            LogHandle.getInstance().printLog(" sdk 未初始化 mContext==null");
        } else {
            CommUtils.isOpenPhoneStateListener = true;
            PhoneStateHandle.getInstance().initPhoneStateListener(i, str, iCallPhoneInfoBack);
        }
    }

    public static void setCallStateOffHook(int i) {
        if (CommUtils.mContext == null) {
            LogHandle.getInstance().printLog(" sdk 未初始化 mContext==null");
        } else {
            PhoneStateHandle.getInstance().initPhoneStateListener(i, null, null);
        }
    }

    public static void setCallStateIdle(int i) {
        if (CommUtils.mContext == null) {
            return;
        }
        PhoneStateHandle.getInstance().initPhoneStateListener(i, null, null);
    }

    public static void setLogListener(LogBack logBack) {
        if (CommUtils.mContext == null) {
            return;
        }
        LogHandle.getInstance().initLogListener(logBack);
    }

    public static void setExceptionSwitch(boolean z) {
        if (CommUtils.mContext == null) {
            return;
        }
        CommUtils.isOpenExceptionListener = z;
        if (z) {
            ServiceManager.StartAlarmManager();
        } else {
            ServiceManager.StopAlarmManager();
        }
    }
}