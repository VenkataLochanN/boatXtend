package com.ido.alexa.util;

import android.content.Intent;
import android.text.TextUtils;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.alexa.R;
import com.ido.alexa.ResponseHandler;
import com.ido.alexa.bean.AlexaReminder;
import com.ido.alexa.callbacks.BaseVoiceCallBack;
import com.ido.alexa.data.AlexaAlarmBean;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.manager.AlexaAudioEventManger;
import com.ido.alexa.manager.AlexaManager;
import com.ido.alexa.service.AlexaDownChannelService;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.callback.VoiceCallBack;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.VoiceControlAlarm;
import com.ido.ble.protocol.model.VoiceNotifyState;
import com.ido.ble.protocol.model.VoiceStopWatch;
import com.ido.ble.protocol.model.VoiceToText;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaSendCmdToDeviceUtil {
    private static int lastConnectDeviceId;

    /* JADX INFO: Access modifiers changed from: private */
    public static void notice(String str) {
        AlexaApi.handlerAudioResult(str);
        AlexaLogUtil.printAndSave(str);
    }

    public static void setTimer(String str, final int i) {
        if (getSupportFunctionInfo().V3_alexa_time_new) {
            AlexaCustomSkillUtil.setNewTimer(str, i);
            return;
        }
        notice("start set timer " + i);
        VoiceStopWatch voiceStopWatch = new VoiceStopWatch();
        voiceStopWatch.delay_time = i;
        BLEManager.registerVoiceCallBack(new BaseVoiceCallBack() { // from class: com.ido.alexa.util.AlexaSendCmdToDeviceUtil.1
            @Override // com.ido.alexa.callbacks.BaseVoiceCallBack, com.ido.ble.callback.VoiceCallBack.ICallBack
            public void onControlResult(VoiceCallBack.VoiceControlType voiceControlType, boolean z) {
                if (voiceControlType == VoiceCallBack.VoiceControlType.VOICE_CONTROL_STOPWATCH) {
                    BLEManager.unregisterVoiceCallBack(this);
                    if (z) {
                        AlexaSendCmdToDeviceUtil.notice("set alexa timer success , delay= " + i);
                        return;
                    }
                    AlexaSendCmdToDeviceUtil.notice("set alexa timer failed , delay= " + i);
                }
            }
        });
        BLEManager.voiceControlStopWatch(voiceStopWatch);
    }

    private static VoiceControlAlarm.AlarmItem initAlexaAlarm(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        VoiceControlAlarm.AlarmItem alarmItem = new VoiceControlAlarm.AlarmItem();
        alarmItem.year = i2;
        alarmItem.month = i3;
        alarmItem.day = i4;
        alarmItem.hour = i5;
        alarmItem.minute = i6;
        alarmItem.status = i7;
        alarmItem.alarm_id = i;
        return alarmItem;
    }

    private static List<VoiceControlAlarm.AlarmItem> getOldAlexaAlarms(String str) {
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) Util.get(AlexaCustomSkillConstant.ALEXA_ALARMS, ""), AlexaAlarmBean.class);
        ArrayList arrayList = new ArrayList();
        if (listAnalysisJsonObjectToList != null && listAnalysisJsonObjectToList.size() != 0) {
            Iterator it = listAnalysisJsonObjectToList.iterator();
            int i = 1;
            while (it.hasNext()) {
                AlexaAlarmBean alexaAlarmBean = (AlexaAlarmBean) it.next();
                if ((isWeekRepeat(alexaAlarmBean.getWeekRepeat()) || alexaAlarmBean.getScheduledTimeMillis() > System.currentTimeMillis()) && !TextUtils.equals(alexaAlarmBean.getToken(), str)) {
                    arrayList.add(initAlexaAlarm(i, alexaAlarmBean.getYear(), alexaAlarmBean.getMonth(), alexaAlarmBean.getDay(), alexaAlarmBean.getHour(), alexaAlarmBean.getMinute(), 85));
                    i++;
                } else {
                    it.remove();
                    AlexaLogUtil.printAndSave("remove expired alexa alarm= " + alexaAlarmBean.toString());
                }
            }
            Util.put(AlexaCustomSkillConstant.ALEXA_ALARMS, GsonUtil.toJson(listAnalysisJsonObjectToList));
        }
        return arrayList;
    }

    private static boolean isWeekRepeat(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static void setAlexaAlarm(final String str, final Date date, final long j) {
        if (getSupportFunctionInfo().V3_alexa_set_get_alexa_alarm) {
            AlexaNewAlarmUtil.getInstance().setAlexaAlarmV3(str, date, j);
            return;
        }
        List<VoiceControlAlarm.AlarmItem> oldAlexaAlarms = getOldAlexaAlarms(str);
        if (oldAlexaAlarms.size() >= 10) {
            ResponseHandler.getInstance().handlerRangeControlerResult();
            notice("alarms count =" + oldAlexaAlarms.size());
            replyDevice(String.format(Locale.CHINA, AlexaApp.getAppContext().getString(R.string.lib_alexa_alarms_full_tips), 10));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("start set alarm ");
        sb.append(date != null ? date.toString() : "null");
        notice(sb.toString());
        final boolean z = j == 0;
        if (!z && date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int i = calendar.get(1);
            int i2 = calendar.get(2) + 1;
            int i3 = calendar.get(5);
            int i4 = calendar.get(11);
            int i5 = calendar.get(12);
            AlexaLogUtil.printAndSave("start alexa setting alarm , time= " + i + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5);
            oldAlexaAlarms.add(initAlexaAlarm(oldAlexaAlarms.size() + 1, i, i2, i3, i4, i5, 85));
        }
        int size = oldAlexaAlarms.size();
        while (size < 10) {
            size++;
            oldAlexaAlarms.add(initAlexaAlarm(size, 0, 0, 0, 0, 0, 170));
        }
        BLEManager.registerVoiceCallBack(new BaseVoiceCallBack() { // from class: com.ido.alexa.util.AlexaSendCmdToDeviceUtil.2
            @Override // com.ido.alexa.callbacks.BaseVoiceCallBack, com.ido.ble.callback.VoiceCallBack.ICallBack
            public void onControlResult(VoiceCallBack.VoiceControlType voiceControlType, boolean z2) {
                if (voiceControlType == VoiceCallBack.VoiceControlType.VOICE_CONTROL_ALARM) {
                    BLEManager.unregisterVoiceCallBack(this);
                    if (z2) {
                        if (z) {
                            AlexaSendCmdToDeviceUtil.notice("delete alexa alarm success");
                            return;
                        } else {
                            AlexaSendCmdToDeviceUtil.saveAlarmsOrReminders(str, date, j, "");
                            AlexaSendCmdToDeviceUtil.notice("set alexa alarm success");
                            return;
                        }
                    }
                    if (z) {
                        AlexaSendCmdToDeviceUtil.notice("delete alexa alarm failed");
                    } else {
                        AlexaSendCmdToDeviceUtil.notice("set alexa alarm failed");
                    }
                }
            }
        });
        VoiceControlAlarm voiceControlAlarm = new VoiceControlAlarm();
        voiceControlAlarm.item = oldAlexaAlarms;
        BLEManager.voiceControlAlarm(voiceControlAlarm);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void saveAlarmsOrReminders(String str, Date date, long j, String str2) {
        boolean zIsEmpty = TextUtils.isEmpty(str2);
        String str3 = AlexaCustomSkillConstant.ALEXA_ALARMS;
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) Util.get(zIsEmpty ? AlexaCustomSkillConstant.ALEXA_ALARMS : AlexaCustomSkillConstant.ALEXA_REMINDERS, ""), AlexaAlarmBean.class);
        if (listAnalysisJsonObjectToList == null) {
            listAnalysisJsonObjectToList = new ArrayList();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        AlexaAlarmBean alexaAlarmBean = new AlexaAlarmBean();
        alexaAlarmBean.setToken(str);
        alexaAlarmBean.setScheduledTimeMillis(j);
        alexaAlarmBean.setYear(calendar.get(1));
        alexaAlarmBean.setMonth(calendar.get(2) + 1);
        alexaAlarmBean.setDay(calendar.get(5));
        alexaAlarmBean.setHour(calendar.get(11));
        alexaAlarmBean.setMinute(calendar.get(12));
        alexaAlarmBean.setSec(calendar.get(13));
        alexaAlarmBean.setReminder_name(str2);
        listAnalysisJsonObjectToList.add(alexaAlarmBean);
        String json = GsonUtil.toJson(listAnalysisJsonObjectToList);
        StringBuilder sb = new StringBuilder();
        sb.append("local save alexa ");
        sb.append(zIsEmpty ? "alarms" : "reminders");
        sb.append("=");
        sb.append(json);
        AlexaLogUtil.printAndSave(sb.toString());
        if (!zIsEmpty) {
            str3 = AlexaCustomSkillConstant.ALEXA_REMINDERS;
        }
        Util.put(str3, json);
    }

    public static void deletAlerts(List<String> list) {
        String str = (String) Util.get(AlexaCustomSkillConstant.ALEXA_ALARMS, "");
        String str2 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_REMINDERS, "");
        String str3 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_TIMERS, "");
        if (list == null || list.size() <= 0) {
            return;
        }
        for (String str4 : list) {
            if (!TextUtils.isEmpty(str4)) {
                if (!TextUtils.isEmpty(str) && str.contains(str4)) {
                    deleteAllAlarms(list);
                    return;
                }
                if (!TextUtils.isEmpty(str2) && str2.contains(str4)) {
                    deleteAllReminders(list);
                    return;
                } else if (!TextUtils.isEmpty(str3) && str3.contains(str4)) {
                    AlexaCustomSkillUtil.delAllTimer();
                    return;
                }
            }
        }
    }

    private static void deleteAllAlarms(List<String> list) {
        notice("deleteAllAlarms");
        Util.put(AlexaCustomSkillConstant.ALEXA_ALARMS, "");
        setAlexaAlarm(GsonUtil.toJson(list), null, 0L);
    }

    private static void deleteAllReminders(List<String> list) {
        notice("deleteAllReminders");
        Util.put(AlexaCustomSkillConstant.ALEXA_REMINDERS, "");
        setReminder(GsonUtil.toJson(list), null, 0L, "");
    }

    public static void deletAlert(String str) {
        String str2 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_ALARMS, "");
        String str3 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_REMINDERS, "");
        String str4 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_TIMERS, "");
        AlexaLogUtil.printAndSave("getoldAlarms= " + str2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!TextUtils.isEmpty(str2) && str2.contains(str)) {
            deleteAlarm(str);
            return;
        }
        if (!TextUtils.isEmpty(str3) && str3.contains(str)) {
            deleteReminder(str);
            return;
        }
        if (!TextUtils.isEmpty(str4) && str4.contains(str)) {
            AlexaCustomSkillUtil.delNewTimer(str);
            return;
        }
        AlexaLogUtil.printAndSave("local cache no have token =" + str);
    }

    private static void deleteAlarm(String str) {
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) Util.get(AlexaCustomSkillConstant.ALEXA_ALARMS, ""), AlexaAlarmBean.class);
        if (listAnalysisJsonObjectToList == null) {
            listAnalysisJsonObjectToList = new ArrayList();
        }
        boolean z = false;
        Date time = null;
        Iterator it = listAnalysisJsonObjectToList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AlexaAlarmBean alexaAlarmBean = (AlexaAlarmBean) it.next();
            if (TextUtils.equals(alexaAlarmBean.getToken(), str)) {
                Calendar calendar = Calendar.getInstance(Locale.CANADA);
                calendar.set(1, alexaAlarmBean.getYear());
                calendar.set(2, alexaAlarmBean.getMonth() - 1);
                calendar.set(5, alexaAlarmBean.getDay());
                calendar.set(10, alexaAlarmBean.getHour());
                calendar.set(12, alexaAlarmBean.getMinute());
                time = calendar.getTime();
                AlexaLogUtil.printAndSave("delete cache alexa Alarm is time= " + alexaAlarmBean.getYear() + "-" + alexaAlarmBean.getMonth() + "-" + alexaAlarmBean.getDay() + " " + alexaAlarmBean.getHour() + ":" + alexaAlarmBean.getMinute());
                listAnalysisJsonObjectToList.remove(alexaAlarmBean);
                z = true;
                break;
            }
        }
        if (z) {
            String json = GsonUtil.toJson(listAnalysisJsonObjectToList);
            AlexaLogUtil.printAndSave("after delete alexa Alarms=" + json);
            Util.put(AlexaCustomSkillConstant.ALEXA_ALARMS, json);
            setAlexaAlarm(str, time, 0L);
            return;
        }
        AlexaLogUtil.printAndSave("delete alexa alarm success");
    }

    private static void deleteReminder(String str) {
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) Util.get(AlexaCustomSkillConstant.ALEXA_REMINDERS, ""), AlexaAlarmBean.class);
        if (listAnalysisJsonObjectToList == null) {
            listAnalysisJsonObjectToList = new ArrayList();
        }
        boolean z = false;
        Iterator it = listAnalysisJsonObjectToList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AlexaAlarmBean alexaAlarmBean = (AlexaAlarmBean) it.next();
            if (TextUtils.equals(alexaAlarmBean.getToken(), str)) {
                AlexaLogUtil.printAndSave("delete cache alexa reminder is time= " + alexaAlarmBean.getYear() + "-" + alexaAlarmBean.getMonth() + "-" + alexaAlarmBean.getDay() + " " + alexaAlarmBean.getHour() + ":" + alexaAlarmBean.getMinute());
                z = true;
                listAnalysisJsonObjectToList.remove(alexaAlarmBean);
                break;
            }
        }
        if (z) {
            String json = GsonUtil.toJson(listAnalysisJsonObjectToList);
            AlexaLogUtil.printAndSave("after delete alexa reminder=" + json);
            Util.put(AlexaCustomSkillConstant.ALEXA_REMINDERS, json);
            setReminder(str, null, 0L, "");
            return;
        }
        AlexaLogUtil.printAndSave("delete alexa reminder success");
    }

    private static AlexaReminder.V3ReminderItem initAlexaReminder(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str) {
        AlexaReminder.V3ReminderItem v3ReminderItem = new AlexaReminder.V3ReminderItem();
        v3ReminderItem.year = i2;
        v3ReminderItem.month = i3;
        v3ReminderItem.day = i4;
        v3ReminderItem.hour = i5;
        v3ReminderItem.minute = i6;
        v3ReminderItem.sec = i7;
        v3ReminderItem.reminder_string = str;
        v3ReminderItem.status = i8;
        v3ReminderItem.reminder_id = i;
        return v3ReminderItem;
    }

    private static List<AlexaReminder.V3ReminderItem> getOldAlexaReminders(String str) {
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) Util.get(AlexaCustomSkillConstant.ALEXA_REMINDERS, ""), AlexaAlarmBean.class);
        ArrayList arrayList = new ArrayList();
        if (listAnalysisJsonObjectToList != null && listAnalysisJsonObjectToList.size() != 0) {
            Iterator it = listAnalysisJsonObjectToList.iterator();
            int i = 1;
            while (it.hasNext()) {
                AlexaAlarmBean alexaAlarmBean = (AlexaAlarmBean) it.next();
                if (alexaAlarmBean.getScheduledTimeMillis() > System.currentTimeMillis() && !TextUtils.equals(alexaAlarmBean.getToken(), str)) {
                    arrayList.add(initAlexaReminder(i, alexaAlarmBean.getYear(), alexaAlarmBean.getMonth(), alexaAlarmBean.getDay(), alexaAlarmBean.getHour(), alexaAlarmBean.getMinute(), alexaAlarmBean.getSec(), 85, alexaAlarmBean.getReminder_name()));
                    i++;
                } else {
                    it.remove();
                    AlexaLogUtil.printAndSave("remove expired alexa reminder= " + alexaAlarmBean.toString());
                }
            }
            Util.put(AlexaCustomSkillConstant.ALEXA_REMINDERS, GsonUtil.toJson(listAnalysisJsonObjectToList));
        }
        return arrayList;
    }

    private static void sort(List<AlexaReminder.V3ReminderItem> list) {
        int size = list.size();
        if (size <= 0 || size >= 5) {
            return;
        }
        for (int i = 0; i < size; i++) {
            list.get(i).reminder_id = (size - i) + 1;
        }
    }

    public static void setReminder(final String str, final Date date, final long j, final String str2) {
        List<AlexaReminder.V3ReminderItem> oldAlexaReminders = getOldAlexaReminders(str);
        if (oldAlexaReminders.size() >= 5) {
            ResponseHandler.getInstance().handlerRangeControlerResult();
            notice("reminders count =" + oldAlexaReminders.size());
            replyDevice(String.format(Locale.CHINA, AlexaApp.getAppContext().getString(R.string.lib_alexa_reminders_full_tips), 5));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("start set reminder ");
        sb.append(date != null ? date.toString() : "null");
        notice(sb.toString());
        final boolean z = j == 0;
        if (!z && date != null) {
            sort(oldAlexaReminders);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int i = calendar.get(1);
            int i2 = calendar.get(2) + 1;
            int i3 = calendar.get(5);
            int i4 = calendar.get(11);
            int i5 = calendar.get(12);
            int i6 = calendar.get(13);
            AlexaLogUtil.printAndSave("start alexa setting reminder , time= " + i + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5 + ":" + i6 + ", lable=" + str2);
            oldAlexaReminders.add(initAlexaReminder(1, i, i2, i3, i4, i5, i6, 85, str2));
        }
        int size = oldAlexaReminders.size();
        while (size < 5) {
            size++;
            oldAlexaReminders.add(initAlexaReminder(size, 0, 0, 0, 0, 0, 0, 170, ""));
        }
        BLEManager.registerDeviceResponseCommonCallBack(new DeviceResponseCommonCallBack.ICallBack() { // from class: com.ido.alexa.util.AlexaSendCmdToDeviceUtil.3
            @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
            public void onResponse(int i7, String str3) {
                if (i7 != 5029) {
                    return;
                }
                BLEManager.unregisterDeviceResponseCommonCallBack(this);
                if (AlexaSendCmdToDeviceUtil.isOperateSuccess(str3)) {
                    if (!z) {
                        AlexaSendCmdToDeviceUtil.notice("set alexa reminder success");
                        AlexaSendCmdToDeviceUtil.saveAlarmsOrReminders(str, date, j, str2);
                        return;
                    } else {
                        AlexaLogUtil.printAndSave("delete alexa reminder success");
                        return;
                    }
                }
                if (!z) {
                    AlexaSendCmdToDeviceUtil.notice("set alexa reminder failed");
                } else {
                    AlexaLogUtil.printAndSave("delete alexa reminder failed");
                }
            }
        });
        AlexaReminder alexaReminder = new AlexaReminder();
        alexaReminder.item = oldAlexaReminders;
        String json = GsonUtil.toJson(alexaReminder);
        AlexaLogUtil.printAndSave("发送设置提醒指令=" + json);
        BLEManager.setParaToDeviceByTypeAndJson(5029, json);
    }

    public static void switchDeviceForAlexa(boolean z) {
        BLEDevice deviceInfo = getDeviceInfo();
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        AlexaLogUtil.printAndSave("处理Alexa切换设备 deviceId= " + lastConnectDeviceId + " currentDeviceId=" + deviceInfo.mDeviceId + "  isSupportAlexa=" + supportFunctionInfo.ex_table_main7_voice_transmission + " ,isBind=" + z + " ,是否支持二期技能=" + supportFunctionInfo.V3_alexa_set_jump_ui);
        if (supportFunctionInfo.ex_table_main7_voice_transmission) {
            if (z) {
                resetData();
            }
            if (lastConnectDeviceId == 0 || (deviceInfo.mDeviceId != 0 && deviceInfo.mDeviceId != lastConnectDeviceId)) {
                AlexaLogUtil.printAndSave("退出Alexa =" + lastConnectDeviceId);
                offline();
                lastConnectDeviceId = deviceInfo.mDeviceId;
                AlexaAudioEventManger.getInstance().deleteEndpoint(null);
                AlexaManager.getInstance().logout(AlexaApp.getAppContext(), null);
                return;
            }
            setNotifyStatus(true);
            Intent intent = new Intent(AlexaApp.getAppContext(), (Class<?>) AlexaDownChannelService.class);
            intent.putExtra(AlexaDownChannelService.IS_SYNC_NOTIFY_STATUS, true);
            ComUtil.startService(AlexaApp.getAppContext(), intent);
        }
    }

    public static BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
        }
        return currentDeviceInfo;
    }

    public static void updateDeviceId() {
        if (lastConnectDeviceId == 0) {
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo == null) {
                currentDeviceInfo = new BLEDevice();
            }
            lastConnectDeviceId = currentDeviceInfo.mDeviceId;
            AlexaLogUtil.printAndSave("updateDeviceId =" + lastConnectDeviceId);
        }
    }

    public static SupportFunctionInfo getSupportFunctionInfo() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo == null ? new SupportFunctionInfo() : supportFunctionInfo;
    }

    public static void offline() {
        AlexaLogUtil.printAndSave("offline");
        Intent intent = new Intent(AlexaApp.getAppContext(), (Class<?>) AlexaDownChannelService.class);
        intent.putExtra(AlexaDownChannelService.OFFLINE_STATUS, true);
        ComUtil.startService(AlexaApp.getAppContext(), intent);
    }

    private static void resetData() {
        AlexaLogUtil.printAndSave("swtich device deleteAlarmsAndRemindes");
        Util.put(AlexaCustomSkillConstant.ALEXA_ALARMS, "");
        Util.put(AlexaCustomSkillConstant.ALEXA_REMINDERS, "");
        Util.put(AlexaCustomSkillConstant.ALEXA_TIMERS, "");
        AlexaAudioEventManger.getInstance().lastSyncStateTime = 0L;
    }

    public static void setNotifyStatus(boolean z) {
        VoiceNotifyState voiceNotifyState = new VoiceNotifyState();
        voiceNotifyState.notify_flag = z ? 2 : 1;
        BLEManager.setVoiceNotifyState(voiceNotifyState);
        AlexaLogUtil.printAndSave("setVoiceNotifyState= " + voiceNotifyState.toString());
    }

    private static void replyDevice(String str) {
        VoiceToText voiceToText = new VoiceToText();
        voiceToText.title = "";
        voiceToText.text_content = str;
        BLEManager.setVoiceToText(voiceToText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isOperateSuccess(String str) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt("is_success");
        } catch (JSONException unused) {
            AlexaLogUtil.e("AlexaSendCmdToDevice", "V3_ALEXA_STE_REMINDER JSONException");
            iOptInt = 0;
        }
        return iOptInt == 1;
    }
}