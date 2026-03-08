package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.alexa.bean.AlexaV3Alarm;
import com.ido.alexa.bean.AlexaV3AlarmItem;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.bean.AlarmBean;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.module.device.view.IAlarmV3View;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.TimeUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockV3Presenter extends BaseCmdPresenter<IAlarmV3View> implements DeviceParaChangedCallBack.ICallBack, DeviceResponseCommonCallBack.ICallBack {
    private boolean deviceDataChanged;
    private final Handler mHandler = new Handler();
    private final Runnable mAlarmRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$AlarmClockV3Presenter$gyCUcF9Mm_D5UYAUhDQjlLKE0vU
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$AlarmClockV3Presenter();
        }
    };
    private final Runnable mAlexaAlarmRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$AlarmClockV3Presenter$j15HwvUuQZfsTvWB8xMcKJlPxLk
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$AlarmClockV3Presenter();
        }
    };
    private final BaseDeviceParaCallBack mICallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.AlarmClockV3Presenter.1
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetAlarmV3(List<AlarmV3> list) {
            AlarmClockV3Presenter.this.mHandler.removeCallbacks(AlarmClockV3Presenter.this.mAlarmRunnable);
            if (AlarmClockV3Presenter.this.isAttachView()) {
                ArrayList arrayList = new ArrayList();
                if (list != null) {
                    for (AlarmV3 alarmV3 : list) {
                        if (alarmV3 != null && alarmV3.status == 85) {
                            arrayList.add(alarmV3);
                        }
                    }
                }
                ((IAlarmV3View) AlarmClockV3Presenter.this.getView()).onGetAlarmList(arrayList);
            }
        }
    };
    AlexaV3Alarm alexaV3Alarm = new AlexaV3Alarm();

    public /* synthetic */ void lambda$new$0$AlarmClockV3Presenter() {
        if (isAttachView()) {
            ((IAlarmV3View) getView()).onGetAlarmList(new ArrayList());
        }
    }

    public /* synthetic */ void lambda$new$1$AlarmClockV3Presenter() {
        if (isAttachView()) {
            ((IAlarmV3View) getView()).onGetAlexaAlarm(new ArrayList());
            getAlarmList();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerDeviceParaChangedCallBack(this);
    }

    @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
    public void onChanged(DeviceChangedPara deviceChangedPara) {
        if ((deviceChangedPara.notifyType & 1) == 1) {
            this.deviceDataChanged = true;
        }
    }

    public void getAlarmList() {
        this.mHandler.postDelayed(this.mAlarmRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        BLEManager.unregisterGetDeviceParaCallBack(this.mICallBack);
        BLEManager.registerGetDeviceParaCallBack(this.mICallBack);
        BLEManager.getAlarmV3();
    }

    public void getAlexaAlarmList() {
        if (DeviceConfigHelper.getSupportFunctionInfo().V3_alexa_set_get_alexa_alarm) {
            this.mHandler.postDelayed(this.mAlexaAlarmRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
            BLEManager.unregisterDeviceResponseCommonCallBack(this);
            BLEManager.registerDeviceResponseCommonCallBack(this);
            BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_VOICE_ALARM, "{}");
            return;
        }
        getAlarmList();
    }

    public List<AlarmBean> AlarmV3ToAlarmBean(List<AlarmV3> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<AlarmV3> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(AlarmV3ToAlarmBean(it.next()));
            }
        }
        return arrayList;
    }

    public AlarmBean AlarmV3ToAlarmBean(AlarmV3 alarmV3) {
        AlarmBean alarmBean = new AlarmBean();
        alarmBean.alarm_id = alarmV3.alarm_id;
        alarmBean.status = alarmV3.status;
        alarmBean.type = alarmV3.type;
        alarmBean.hour = alarmV3.hour;
        alarmBean.minute = alarmV3.minute;
        alarmBean.setOn_off(alarmV3.isOn_off());
        alarmBean.setWeekRepeat(alarmV3.getWeekRepeat());
        alarmBean.tsnooze_duration = alarmV3.tsnooze_duration;
        alarmBean.delay_min = alarmV3.delay_min;
        alarmBean.name = alarmV3.name;
        alarmBean.shock_on_off = alarmV3.shock_on_off;
        alarmBean.repeat_times = alarmV3.repeat_times;
        alarmBean.alarmType = 0;
        return alarmBean;
    }

    public List<AlarmBean> AlexaAlarmToAlarmBean(List<AlexaV3AlarmItem> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (AlexaV3AlarmItem alexaV3AlarmItem : list) {
                if (alexaV3AlarmItem.getStatus() == 85) {
                    AlarmBean alarmBean = new AlarmBean();
                    alarmBean.alarm_id = alexaV3AlarmItem.getAlarm_id();
                    alarmBean.status = alexaV3AlarmItem.getStatus();
                    alarmBean.setOn_off(alexaV3AlarmItem.isOn_off());
                    alarmBean.year = alexaV3AlarmItem.getYear();
                    alarmBean.month = alexaV3AlarmItem.getMonth();
                    alarmBean.day = alexaV3AlarmItem.getDay();
                    alarmBean.hour = alexaV3AlarmItem.getHour();
                    alarmBean.minute = alexaV3AlarmItem.getMinute();
                    alarmBean.setWeekRepeat(alexaV3AlarmItem.getWeekRepeat());
                    alarmBean.alarmType = 1;
                    arrayList.add(alarmBean);
                }
            }
        }
        return arrayList;
    }

    public AlexaV3AlarmItem toAlexaV3AlarmItem(AlarmBean alarmBean) {
        AlexaV3AlarmItem alexaV3AlarmItem = new AlexaV3AlarmItem();
        alexaV3AlarmItem.setAlarm_id(alarmBean.alarm_id);
        alexaV3AlarmItem.setStatus(alarmBean.status);
        alexaV3AlarmItem.setOn_off(alarmBean.isOn_off());
        alexaV3AlarmItem.setYear(alarmBean.year);
        alexaV3AlarmItem.setMonth(alarmBean.month);
        alexaV3AlarmItem.setDay(alarmBean.day);
        alexaV3AlarmItem.setHour(alarmBean.hour);
        alexaV3AlarmItem.setMinute(alarmBean.minute);
        alexaV3AlarmItem.setWeekRepeat(alarmBean.getWeekRepeat());
        return alexaV3AlarmItem;
    }

    public String formatAlarmTime(int i, int i2) {
        return TimeUtil.formatTime(i, i2, TimeUtil.is24Hour());
    }

    public int getMaxAlarmCount() {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            return 10;
        }
        return supportFunctionInfo.alarmCount;
    }

    public int getIntervalMinute() {
        return SPHelper.getAlarmIntervalMinute();
    }

    public int getRepeatCount() {
        return SPHelper.getAlarmRepeatCount();
    }

    public String formatList2String(List<AlarmV3> list) {
        String strConcat = "";
        if (list != null && !list.isEmpty()) {
            for (AlarmV3 alarmV3 : list) {
                if (alarmV3 != null) {
                    alarmV3.isOn_off();
                    strConcat = strConcat.concat(alarmV3.toString());
                }
            }
        }
        return strConcat;
    }

    public String alexaFormatList2String(List<AlexaV3AlarmItem> list) {
        String strConcat = "";
        if (list != null && !list.isEmpty()) {
            for (AlexaV3AlarmItem alexaV3AlarmItem : list) {
                if (alexaV3AlarmItem != null) {
                    alexaV3AlarmItem.isOn_off();
                    strConcat = strConcat.concat(alexaV3AlarmItem.toString());
                }
            }
        }
        return strConcat;
    }

    public boolean isDataChanged(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return true;
        }
        return !str.equals(str2);
    }

    public void sendAlarmClock2Device(List<AlarmV3> list) {
        if (this.deviceDataChanged) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AlarmV3 alarmV3 = list.get(i);
            if (alarmV3 != null) {
                alarmV3.alarm_id = i + 1;
            }
        }
        if (size < getMaxAlarmCount()) {
            while (size < getMaxAlarmCount()) {
                AlarmV3 alarmV32 = new AlarmV3();
                alarmV32.status = 170;
                size++;
                alarmV32.alarm_id = size;
                list.add(alarmV32);
            }
        }
        BLEManager.setAlarmV3(list);
    }

    public void sendAlexaAlarm2Device(ArrayList<AlexaV3AlarmItem> arrayList) {
        ArrayList<AlexaV3AlarmItem> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            AlexaV3AlarmItem alexaV3AlarmItem = arrayList.get(i2);
            if (alexaV3AlarmItem != null && alexaV3AlarmItem.getStatus() != 170) {
                i++;
                alexaV3AlarmItem.setAlarm_id(i);
                arrayList2.add(alexaV3AlarmItem);
            }
        }
        if (i < 10) {
            while (i < getMaxAlarmCount()) {
                AlexaV3AlarmItem alexaV3AlarmItem2 = new AlexaV3AlarmItem();
                alexaV3AlarmItem2.setStatus(170);
                i++;
                alexaV3AlarmItem2.setAlarm_id(i);
                arrayList2.add(alexaV3AlarmItem2);
            }
        }
        this.alexaV3Alarm.setItem(arrayList2);
        BLEManager.unregisterDeviceResponseCommonCallBack(this);
        BLEManager.registerDeviceResponseCommonCallBack(this);
        String json = GsonUtil.toJson(this.alexaV3Alarm);
        CommonLogUtil.d("set alexa alarm =" + json);
        BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_V3_ALEXA_STE_ALARM2, json);
    }

    public String formatWeekRepeat(boolean[] zArr) {
        int[] iArr = Constants.WEEK_START_MONDAY;
        int weekStart = RunTimeUtil.getInstance().getWeekStart();
        int i = 0;
        String languageText = "";
        int i2 = 0;
        while (true) {
            if (i >= zArr.length) {
                break;
            }
            if (zArr[i]) {
                i2++;
                if (TextUtils.isEmpty(languageText)) {
                    languageText = languageText + LanguageUtil.getLanguageText(iArr[i]);
                } else if (weekStart == 2) {
                    languageText = languageText + "  " + LanguageUtil.getLanguageText(iArr[i]);
                } else if (weekStart == 7) {
                    if (i == zArr.length - 2) {
                        int i3 = i + 1;
                        if (zArr[i3]) {
                            languageText = LanguageUtil.getLanguageText(iArr[i3]) + "  " + languageText;
                            i2++;
                        }
                        languageText = LanguageUtil.getLanguageText(iArr[i]) + "  " + languageText;
                    } else if (i == zArr.length - 1) {
                        languageText = LanguageUtil.getLanguageText(iArr[i]) + "  " + languageText;
                    } else {
                        languageText = languageText + "  " + LanguageUtil.getLanguageText(iArr[i]);
                    }
                } else if (i == zArr.length - 1) {
                    languageText = LanguageUtil.getLanguageText(iArr[i]) + "  " + languageText;
                } else {
                    languageText = languageText + "  " + LanguageUtil.getLanguageText(iArr[i]);
                }
            }
            i++;
        }
        if (TextUtils.isEmpty(languageText)) {
            languageText = LanguageUtil.getLanguageText(R.string.device_without_repetition);
        }
        return i2 == 7 ? LanguageUtil.getLanguageText(R.string.device_everyday) : languageText;
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.ALARM_V3) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_ALARM_CLOCK_FAILURE, "", null);
            if (isAttachView()) {
                ((IAlarmV3View) getView()).onSetAlarmFailed();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.ALARM_V3) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_ALARM_CLOCK_SUCCESS, "", null);
            if (isAttachView()) {
                ((IAlarmV3View) getView()).onSetAlarmSuccess();
            }
        }
    }

    @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
    public void onResponse(int i, String str) {
        if (i == 5033) {
            this.mHandler.removeCallbacks(this.mAlexaAlarmRunnable);
            BLEManager.unregisterDeviceResponseCommonCallBack(this);
            if (isAttachView()) {
                AlexaV3Alarm alexaV3Alarm = (AlexaV3Alarm) GsonUtil.fromJson(str, AlexaV3Alarm.class);
                if (alexaV3Alarm != null && alexaV3Alarm.getItem() != null) {
                    ArrayList arrayList = new ArrayList();
                    for (AlexaV3AlarmItem alexaV3AlarmItem : alexaV3Alarm.getItem()) {
                        if (alexaV3AlarmItem != null && alexaV3AlarmItem.getStatus() == 85) {
                            arrayList.add(alexaV3AlarmItem);
                        }
                    }
                    ((IAlarmV3View) getView()).onGetAlexaAlarm(arrayList);
                } else {
                    ((IAlarmV3View) getView()).onGetAlexaAlarm(new ArrayList());
                }
                getAlarmList();
                return;
            }
            return;
        }
        if (i == 5034 && isAttachView()) {
            if (isOperateSuccess(str)) {
                CommonLogUtil.d("set alexa alarm success");
                ((IAlarmV3View) getView()).onSetAlexaAlarmSuccess(this.alexaV3Alarm);
            } else {
                CommonLogUtil.d("set alexa alarm failed");
                ((IAlarmV3View) getView()).onSetAlexaAlarmFailed();
            }
        }
    }

    private boolean isOperateSuccess(String str) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt("is_success");
        } catch (JSONException e2) {
            e2.printStackTrace();
            iOptInt = 0;
        }
        return iOptInt == 1;
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        this.mHandler.removeCallbacks(this.mAlarmRunnable);
        BLEManager.unregisterGetDeviceParaCallBack(this.mICallBack);
        BLEManager.unregisterDeviceParaChangedCallBack(this);
        BLEManager.unregisterDeviceResponseCommonCallBack(this);
    }
}