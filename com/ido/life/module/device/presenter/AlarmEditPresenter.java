package com.ido.life.module.device.presenter;

import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.life.util.DateUtil;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmEditPresenter extends BaseMonitoringPresenter {
    public Alarm initAlarm() {
        Alarm alarm = new Alarm();
        int[] currentHM = DateUtil.getCurrentHM();
        alarm.setAlarmHour(currentHM[0]);
        alarm.setAlarmMinute(currentHM[1]);
        alarm.setWeekRepeat(new boolean[7]);
        alarm.setAlarmStatus(85);
        alarm.setOn_off(true);
        alarm.setAlarmType(-1);
        return alarm;
    }

    public Alarm cloneAlarm(Alarm alarm) {
        Alarm alarm2 = new Alarm();
        alarm2.setAlarmId(alarm.getAlarmId());
        alarm2.setAlarmStatus(alarm.getAlarmStatus());
        alarm2.setAlarmType(alarm.getAlarmType());
        alarm2.setAlarmHour(alarm.getAlarmHour());
        alarm2.setAlarmMinute(alarm.getAlarmMinute());
        alarm2.setWeekRepeat(alarm.getWeekRepeat());
        alarm2.setAlarmSnoozeDuration(alarm.getAlarmSnoozeDuration());
        alarm2.setOn_off(alarm.getOn_off());
        return alarm2;
    }

    public boolean isSupportAlarmType() {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        return supportFunctionInfo.alarmWakeUp || supportFunctionInfo.alarmMedicine || supportFunctionInfo.alarmSleep || supportFunctionInfo.alarmParty || supportFunctionInfo.alarmDating || supportFunctionInfo.alarmSport || supportFunctionInfo.alarmMetting;
    }
}