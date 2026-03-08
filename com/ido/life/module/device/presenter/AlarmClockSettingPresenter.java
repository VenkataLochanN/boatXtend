package com.ido.life.module.device.presenter;

import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockSettingPresenter extends BaseCmdPresenter {
    public int formIndex2ParamChecked(int i, int i2) {
        if (i != 10) {
            return i2;
        }
        if (i2 != 1) {
            return i2 != 2 ? 5 : 15;
        }
        return 10;
    }

    public int formParamChecked2Index(int i, int i2) {
        if (i != 10) {
            return i2;
        }
        if (i2 != 10) {
            return i2 != 15 ? 0 : 2;
        }
        return 1;
    }

    public int getIntervalMinute() {
        return SPHelper.getAlarmIntervalMinute();
    }

    public int getRepeatCount() {
        return SPHelper.getAlarmRepeatCount();
    }

    public void saveIntervalMinute(int i) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), "AlarmClockSettingPresenter", "saveIntervalMinute : " + i);
        SPHelper.saveAlarmIntervalMinute(i);
        EventBusHelper.post(600);
    }

    public void saveRepeatCount(int i) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), "AlarmClockSettingPresenter", "saveRepeatCount : " + i);
        SPHelper.saveAlarmRepeatCount(i);
        EventBusHelper.post(600);
    }
}