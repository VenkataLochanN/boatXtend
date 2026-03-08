package com.ido.life.module.device.presenter;

import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.AlarmType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmTypePresenter extends BasePresenter {
    public List<AlarmType> getSupportAlarmType() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        ArrayList arrayList = new ArrayList();
        if (supportFunctionInfo == null) {
            return arrayList;
        }
        if (supportFunctionInfo.alarmWakeUp) {
            arrayList.add(new AlarmType(0, R.string.alarm_get_up));
        }
        if (supportFunctionInfo.alarmMedicine) {
            arrayList.add(new AlarmType(3, R.string.alarm_medication));
        }
        if (supportFunctionInfo.alarmSleep) {
            arrayList.add(new AlarmType(1, R.string.alarm_sleep));
        }
        if (supportFunctionInfo.alarmParty) {
            arrayList.add(new AlarmType(5, R.string.alarm_party));
        }
        if (supportFunctionInfo.alarmDating) {
            arrayList.add(new AlarmType(4, R.string.alarm_dating));
        }
        if (supportFunctionInfo.alarmSport) {
            arrayList.add(new AlarmType(2, R.string.alarm_exercise));
        }
        if (supportFunctionInfo.alarmMetting) {
            arrayList.add(new AlarmType(6, R.string.alarm_meeting));
        }
        return arrayList;
    }
}