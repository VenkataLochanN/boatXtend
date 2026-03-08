package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.life.module.device.view.IAlarmView;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.TimeUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockPresenter extends BaseMonitoringPresenter<IAlarmView> {
    private String mDefaultAlarmListStr;
    private final Handler mHandler = new Handler();

    public List<Alarm> getAlarmList() {
        List<Alarm> alarm = LocalDataManager.getAlarm();
        ArrayList arrayList = new ArrayList();
        if (alarm != null) {
            for (Alarm alarm2 : alarm) {
                if (alarm2.getAlarmStatus() == 85) {
                    arrayList.add(alarm2);
                }
            }
        }
        this.mDefaultAlarmListStr = formatList2String(arrayList);
        return arrayList;
    }

    public int getMaxAlarmCount() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            return 10;
        }
        return supportFunctionInfo.alarmCount;
    }

    public String formatAlarmTime(int i, int i2) {
        return TimeUtil.formatTime(i, i2, TimeUtil.is24Hour());
    }

    public String formatList2String(List<Alarm> list) {
        String strConcat = "";
        if (list != null && !list.isEmpty()) {
            for (Alarm alarm : list) {
                if (alarm != null) {
                    strConcat = strConcat.concat(alarm.toString());
                }
            }
        }
        return strConcat;
    }

    public boolean isDataChanged(List<Alarm> list) {
        String list2String = formatList2String(list);
        if (TextUtils.isEmpty(this.mDefaultAlarmListStr) && TextUtils.isEmpty(list2String)) {
            return false;
        }
        if (TextUtils.isEmpty(this.mDefaultAlarmListStr) || TextUtils.isEmpty(list2String)) {
            return true;
        }
        return !TextUtils.equals(list2String, this.mDefaultAlarmListStr);
    }

    public void sendAlarmClock2Device(List<Alarm> list) {
        for (int i = 0; i < list.size(); i++) {
            Alarm alarm = list.get(i);
            if (alarm != null) {
                alarm.setAlarmId(i + 1);
            }
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$AlarmClockPresenter$ms6Ueum_rJMqlX56y9oPfRRn6_0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$sendAlarmClock2Device$0$AlarmClockPresenter();
            }
        }, 30000L);
        BLEManager.setAlarm(list);
    }

    public /* synthetic */ void lambda$sendAlarmClock2Device$0$AlarmClockPresenter() {
        if (isAttachView()) {
            ((IAlarmView) getView()).onSetAlarmFailed();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.ALARM) {
            this.mHandler.removeCallbacksAndMessages(null);
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_ALARM_CLOCK_FAILURE, "", null);
            if (isAttachView()) {
                ((IAlarmView) getView()).onSetAlarmFailed();
            }
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter, com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.ALARM) {
            this.mHandler.removeCallbacksAndMessages(null);
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_ALARM_CLOCK_SUCCESS, "", null);
            if (isAttachView()) {
                ((IAlarmView) getView()).onsetAlarmSuccess();
            }
        }
    }
}