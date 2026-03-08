package com.ido.life.module.device.presenter;

import com.ido.ble.protocol.model.AlarmV3;
import com.ido.life.util.DateUtil;
import com.ido.life.util.SPHelper;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmV3EditPresenter extends BaseMonitoringPresenter {
    public AlarmV3 initAlarm() {
        AlarmV3 alarmV3 = new AlarmV3();
        int[] currentHM = DateUtil.getCurrentHM();
        alarmV3.hour = currentHM[0];
        alarmV3.minute = currentHM[1];
        alarmV3.setWeekRepeat(new boolean[7]);
        alarmV3.status = 85;
        alarmV3.setOn_off(true);
        alarmV3.delay_min = SPHelper.getAlarmIntervalMinute();
        alarmV3.repeat_times = SPHelper.getAlarmRepeatCount();
        alarmV3.name = "";
        return alarmV3;
    }

    public AlarmV3 cloneAlarm(AlarmV3 alarmV3) {
        AlarmV3 alarmV32 = new AlarmV3();
        alarmV32.alarm_id = alarmV3.alarm_id;
        alarmV32.status = alarmV3.status;
        alarmV32.type = alarmV3.type;
        alarmV32.hour = alarmV3.hour;
        alarmV32.minute = alarmV3.minute;
        alarmV32.setWeekRepeat(alarmV3.getWeekRepeat());
        alarmV32.tsnooze_duration = alarmV3.tsnooze_duration;
        alarmV32.setOn_off(alarmV3.isOn_off());
        alarmV32.name = alarmV3.name;
        return alarmV32;
    }
}