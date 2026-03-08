package com.ido.ntf.exception;

import com.ido.ntf.Utils.AlarmManagerUtils;
import com.ido.ntf.Utils.CommUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ServiceManager {
    public static void StartAlarmManager() {
        AlarmManagerUtils alarmManagerUtils = AlarmManagerUtils.getInstance(CommUtils.mContext);
        alarmManagerUtils.createGetUpAlarmManager();
        alarmManagerUtils.getUpAlarmManagerStartWork();
    }

    public static void StopAlarmManager() {
        AlarmManagerUtils.getInstance(CommUtils.mContext).stopAlarmManager();
    }
}