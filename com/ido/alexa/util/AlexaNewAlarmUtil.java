package com.ido.alexa.util;

import android.text.TextUtils;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.alexa.R;
import com.ido.alexa.ResponseHandler;
import com.ido.alexa.bean.AlexaV3Alarm;
import com.ido.alexa.bean.AlexaV3AlarmItem;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.data.AlexaAlarmBean;
import com.ido.alexa.data.AvsAlarmItem;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.protocol.model.VoiceToText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaNewAlarmUtil implements DeviceResponseCommonCallBack.ICallBack {
    private static final String ALARM_TOKEN_PREFIX = "amzn1.as-ct.v1.Domain:Application:Alarms#DNID#";
    private static AlexaNewAlarmUtil mInstance;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private static List<AlexaAlarmBean> alexaAccountOnAlarms = new ArrayList();

    private AlexaNewAlarmUtil() {
    }

    public static AlexaNewAlarmUtil getInstance() {
        if (mInstance == null) {
            synchronized (AlexaNewAlarmUtil.class) {
                if (mInstance == null) {
                    mInstance = new AlexaNewAlarmUtil();
                }
            }
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notice(String str) {
        AlexaApi.handlerAudioResult(str);
        AlexaLogUtil.printAndSave(str);
    }

    private void replyDevice(String str) {
        VoiceToText voiceToText = new VoiceToText();
        voiceToText.title = "";
        voiceToText.text_content = str;
        BLEManager.setVoiceToText(voiceToText);
    }

    public ArrayList<AlexaV3AlarmItem> getOldAlexaAlarmsV3(String str) {
        String str2 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_ALARMS, "");
        AlexaLogUtil.printAndSave("getOldAlexaAlarmsV3= " + str2);
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList(str2, AlexaAlarmBean.class);
        ArrayList<AlexaV3AlarmItem> arrayList = new ArrayList<>();
        if (listAnalysisJsonObjectToList == null || listAnalysisJsonObjectToList.size() == 0) {
            return arrayList;
        }
        Iterator it = listAnalysisJsonObjectToList.iterator();
        int i = 1;
        while (it.hasNext()) {
            AlexaAlarmBean alexaAlarmBean = (AlexaAlarmBean) it.next();
            if ((isWeekRepeat(alexaAlarmBean.getWeekRepeat()) || alexaAlarmBean.getScheduledTimeMillis() > System.currentTimeMillis()) && !TextUtils.equals(alexaAlarmBean.getToken(), str)) {
                arrayList.add(initAlexaAlarmV3(i, alexaAlarmBean.getYear(), alexaAlarmBean.getMonth(), alexaAlarmBean.getDay(), alexaAlarmBean.getHour(), alexaAlarmBean.getMinute(), 85, alexaAlarmBean.getWeekRepeat(), alexaAlarmBean.isOn_off()));
                i++;
            } else {
                it.remove();
                AlexaLogUtil.printAndSave("remove expired alexa v3 alarm= " + alexaAlarmBean.toString());
            }
        }
        AlexaLogUtil.printAndSave("after getOldAlexaAlarmsV3= " + GsonUtil.toJson(listAnalysisJsonObjectToList));
        Util.put(AlexaCustomSkillConstant.ALEXA_ALARMS, GsonUtil.toJson(listAnalysisJsonObjectToList));
        return arrayList;
    }

    private boolean isWeekRepeat(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    private boolean isDaily(boolean[] zArr) {
        for (boolean z : zArr) {
            if (!z) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean[] getRepeatWeek(String str, List<String> list) {
        boolean[] zArr = new boolean[7];
        if (TextUtils.equals(str, AlexaCustomSkillConstant.Frequency.DAILY.name())) {
            return new boolean[]{true, true, true, true, true, true, true};
        }
        for (String str2 : list) {
            if (TextUtils.equals(AlexaCustomSkillConstant.WeekRepeat.MO.name(), str2)) {
                zArr[0] = true;
            } else if (TextUtils.equals(AlexaCustomSkillConstant.WeekRepeat.TU.name(), str2)) {
                zArr[1] = true;
            } else if (TextUtils.equals(AlexaCustomSkillConstant.WeekRepeat.WE.name(), str2)) {
                zArr[2] = true;
            } else if (TextUtils.equals(AlexaCustomSkillConstant.WeekRepeat.TH.name(), str2)) {
                zArr[3] = true;
            } else if (TextUtils.equals(AlexaCustomSkillConstant.WeekRepeat.FR.name(), str2)) {
                zArr[4] = true;
            } else if (TextUtils.equals(AlexaCustomSkillConstant.WeekRepeat.SA.name(), str2)) {
                zArr[5] = true;
            } else if (TextUtils.equals(AlexaCustomSkillConstant.WeekRepeat.SU.name(), str2)) {
                zArr[6] = true;
            }
        }
        return zArr;
    }

    public void setAlexaAlarmV3(final String str, final Date date, final long j) {
        final ArrayList<AlexaV3AlarmItem> oldAlexaAlarmsV3 = getOldAlexaAlarmsV3(str);
        if (oldAlexaAlarmsV3.size() >= 10) {
            ResponseHandler.getInstance().handlerRangeControlerResult();
            notice("alarms count =" + oldAlexaAlarmsV3.size());
            replyDevice(String.format(Locale.CHINA, AlexaApp.getAppContext().getString(R.string.lib_alexa_alarms_full_tips), 10));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("start set alarm v3 ");
        sb.append(date != null ? date.toString() : "null");
        notice(sb.toString());
        boolean z = j == 0;
        if (z) {
            sendAlexaAlarmV3Cmd(true, str, date, j, null, oldAlexaAlarmsV3);
        } else {
            final boolean z2 = z;
            getWeekRepeatAlarm(str, date, oldAlexaAlarmsV3.size() + 1, new ICallBack<AlexaV3AlarmItem>() { // from class: com.ido.alexa.util.AlexaNewAlarmUtil.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.ido.alexa.util.AlexaNewAlarmUtil.ICallBack
                public void onCallBack(AlexaV3AlarmItem alexaV3AlarmItem) {
                    oldAlexaAlarmsV3.add(alexaV3AlarmItem);
                    AlexaNewAlarmUtil.this.sendAlexaAlarmV3Cmd(z2, str, date, j, alexaV3AlarmItem.getWeekRepeat(), oldAlexaAlarmsV3);
                }
            });
        }
    }

    private void getWeekRepeatAlarm(String str, final Date date, final int i, final ICallBack<AlexaV3AlarmItem> iCallBack) {
        AlexaApi.getAlarmByToken(str.replace(ALARM_TOKEN_PREFIX, ""), new ImplAsyncCallback<AvsAlarmItem.AlexaAlarmsBean, Throwable>() { // from class: com.ido.alexa.util.AlexaNewAlarmUtil.2
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(AvsAlarmItem.AlexaAlarmsBean alexaAlarmsBean) {
                super.success(alexaAlarmsBean);
                if (alexaAlarmsBean == null) {
                    return;
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int i2 = calendar.get(1);
                int i3 = 1 + calendar.get(2);
                int i4 = calendar.get(5);
                int i5 = calendar.get(11);
                int i6 = calendar.get(12);
                boolean[] repeatWeek = new boolean[7];
                if (alexaAlarmsBean.getTrigger() != null && alexaAlarmsBean.getTrigger().getRecurrence() != null) {
                    repeatWeek = AlexaNewAlarmUtil.getRepeatWeek(alexaAlarmsBean.getTrigger().getRecurrence().getFreq(), alexaAlarmsBean.getTrigger().getRecurrence().getByDay());
                }
                boolean[] zArr = repeatWeek;
                AlexaV3AlarmItem alexaV3AlarmItemInitAlexaAlarmV3 = AlexaNewAlarmUtil.this.initAlexaAlarmV3(i, i2, i3, i4, i5, i6, 85, zArr, true);
                ICallBack iCallBack2 = iCallBack;
                if (iCallBack2 != null) {
                    iCallBack2.onCallBack(alexaV3AlarmItemInitAlexaAlarmV3);
                }
                AlexaLogUtil.printAndSave("start alexa setting alarm v3 , time= " + i2 + "-" + i3 + "-" + i4 + " " + i5 + ":" + i6 + "  " + Arrays.toString(zArr));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAlexaAlarmV3Cmd(final boolean z, final String str, final Date date, final long j, final boolean[] zArr, ArrayList<AlexaV3AlarmItem> arrayList) {
        int size = arrayList.size();
        while (size < 10) {
            size++;
            arrayList.add(initAlexaAlarmV3(size, 0, 0, 0, 0, 0, 170, new boolean[7], false));
        }
        BLEManager.registerDeviceResponseCommonCallBack(new DeviceResponseCommonCallBack.ICallBack() { // from class: com.ido.alexa.util.AlexaNewAlarmUtil.3
            @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
            public void onResponse(int i, String str2) {
                if (i != 5034) {
                    return;
                }
                BLEManager.unregisterDeviceResponseCommonCallBack(this);
                if (AlexaNewAlarmUtil.this.isOperateSuccess(str2)) {
                    if (z) {
                        AlexaNewAlarmUtil.this.notice("delete alexa alarm v3 success");
                        return;
                    } else {
                        AlexaNewAlarmUtil.this.saveAlarmsV3(str, date, j, zArr);
                        AlexaNewAlarmUtil.this.notice("set alexa alarm v3 success");
                        return;
                    }
                }
                if (z) {
                    AlexaNewAlarmUtil.this.notice("delete alexa alarm v3 failed");
                } else {
                    AlexaNewAlarmUtil.this.notice("set alexa alarm v3 failed");
                }
            }
        });
        AlexaV3Alarm alexaV3Alarm = new AlexaV3Alarm();
        alexaV3Alarm.setItem(arrayList);
        String json = GsonUtil.toJson(alexaV3Alarm);
        AlexaLogUtil.printAndSave("发送设置闹钟指令=" + json);
        BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_V3_ALEXA_STE_ALARM2, json);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAlarmsV3(String str, Date date, long j, boolean[] zArr) {
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) Util.get(AlexaCustomSkillConstant.ALEXA_ALARMS, ""), AlexaAlarmBean.class);
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
        alexaAlarmBean.setWeekRepeat(zArr);
        alexaAlarmBean.setOn_off(true);
        listAnalysisJsonObjectToList.add(alexaAlarmBean);
        String json = GsonUtil.toJson(listAnalysisJsonObjectToList);
        AlexaLogUtil.printAndSave("local save alexa v3 alarms =" + json);
        Util.put(AlexaCustomSkillConstant.ALEXA_ALARMS, json);
    }

    public void syncAlexaAccountAlarms() {
        AlexaApi.getAllAlarms(new ImplAsyncCallback<AvsAlarmItem, Throwable>() { // from class: com.ido.alexa.util.AlexaNewAlarmUtil.4
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(AvsAlarmItem avsAlarmItem) {
                super.success(avsAlarmItem);
                if (avsAlarmItem == null || avsAlarmItem.getAlarms() == null) {
                    return;
                }
                List<AvsAlarmItem.AlexaAlarmsBean> alarms = avsAlarmItem.getAlarms();
                AlexaNewAlarmUtil.alexaAccountOnAlarms.clear();
                for (AvsAlarmItem.AlexaAlarmsBean alexaAlarmsBean : alarms) {
                    try {
                        Date date = AlexaNewAlarmUtil.simpleDateFormat.parse(alexaAlarmsBean.getTrigger().getScheduledTime());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        int i = calendar.get(1);
                        int i2 = calendar.get(2) + 1;
                        int i3 = calendar.get(5);
                        int i4 = calendar.get(11);
                        int i5 = calendar.get(12);
                        boolean[] repeatWeek = new boolean[7];
                        if (alexaAlarmsBean.getTrigger() != null && alexaAlarmsBean.getTrigger().getRecurrence() != null) {
                            repeatWeek = AlexaNewAlarmUtil.getRepeatWeek(alexaAlarmsBean.getTrigger().getRecurrence().getFreq(), alexaAlarmsBean.getTrigger().getRecurrence().getByDay());
                        }
                        boolean[] zArr = repeatWeek;
                        try {
                            AlexaAlarmBean alexaAlarmBeanInitAlexaAlarm = AlexaNewAlarmUtil.this.initAlexaAlarm(AlexaNewAlarmUtil.alexaAccountOnAlarms.size() + 1, i, i2, i3, i4, i5, date.getTime(), 85, zArr, alexaAlarmsBean.isOn(), AlexaNewAlarmUtil.ALARM_TOKEN_PREFIX + alexaAlarmsBean.getAlarmToken());
                            if (alexaAlarmBeanInitAlexaAlarm.isOn_off()) {
                                AlexaNewAlarmUtil.alexaAccountOnAlarms.add(alexaAlarmBeanInitAlexaAlarm);
                            }
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                }
                AlexaLogUtil.printAndSave("get alexa account alexa  alarms = " + AlexaNewAlarmUtil.alexaAccountOnAlarms.size() + "  " + GsonUtil.toJson(AlexaNewAlarmUtil.alexaAccountOnAlarms));
            }
        });
    }

    public void getDeviceAlexaAlarm() {
        BLEManager.unregisterDeviceResponseCommonCallBack(this);
        BLEManager.registerDeviceResponseCommonCallBack(this);
        BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_VOICE_ALARM, "{}");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isOperateSuccess(String str) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt("is_success");
        } catch (JSONException unused) {
            AlexaLogUtil.e("AlexaSendCmdToDevice", "V3_ALEXA_STE_ALARM2 JSONException");
            iOptInt = 0;
        }
        return iOptInt == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlexaAlarmBean initAlexaAlarm(int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, boolean[] zArr, boolean z, String str) {
        AlexaAlarmBean alexaAlarmBean = new AlexaAlarmBean();
        alexaAlarmBean.setAlarm_id(i);
        alexaAlarmBean.setYear(i2);
        alexaAlarmBean.setMonth(i3);
        alexaAlarmBean.setDay(i4);
        alexaAlarmBean.setHour(i5);
        alexaAlarmBean.setMinute(i6);
        alexaAlarmBean.setScheduledTimeMillis(j);
        alexaAlarmBean.setStatus(i7);
        alexaAlarmBean.setWeekRepeat(zArr);
        alexaAlarmBean.setOn_off(z);
        alexaAlarmBean.setToken(str);
        return alexaAlarmBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlexaV3AlarmItem initAlexaAlarmV3(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean[] zArr, boolean z) {
        AlexaV3AlarmItem alexaV3AlarmItem = new AlexaV3AlarmItem();
        alexaV3AlarmItem.setAlarm_id(i);
        alexaV3AlarmItem.setYear(i2);
        alexaV3AlarmItem.setMonth(i3);
        alexaV3AlarmItem.setDay(i4);
        alexaV3AlarmItem.setHour(i5);
        alexaV3AlarmItem.setMinute(i6);
        alexaV3AlarmItem.setStatus(i7);
        alexaV3AlarmItem.setWeekRepeat(zArr);
        alexaV3AlarmItem.setOn_off(z);
        return alexaV3AlarmItem;
    }

    public void handlerDeviceAlexaAlarm(String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        ArrayList arrayList;
        AlexaV3AlarmItem alexaV3AlarmItem;
        String str6;
        String str7 = "-";
        try {
            AlexaLogUtil.printAndSave("获取设备的alexa闹钟 " + str);
            AlexaV3Alarm alexaV3Alarm = (AlexaV3Alarm) GsonUtil.fromJson(str, AlexaV3Alarm.class);
            ArrayList<AlexaV3AlarmItem> arrayList2 = new ArrayList();
            String str8 = "";
            String str9 = AlexaCustomSkillConstant.ALEXA_ALARMS;
            if (alexaV3Alarm != null && alexaV3Alarm.getItem() != null) {
                arrayList2.addAll(alexaV3Alarm.getItem());
                String str10 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_ALARMS, "");
                AlexaLogUtil.printAndSave("getOldAlexaAlarmsV3= " + str10);
                List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList(str10, AlexaAlarmBean.class);
                if (listAnalysisJsonObjectToList == null) {
                    listAnalysisJsonObjectToList = new ArrayList();
                }
                List<AlexaAlarmBean> list = listAnalysisJsonObjectToList;
                ArrayList arrayList3 = new ArrayList();
                for (AlexaV3AlarmItem alexaV3AlarmItem2 : arrayList2) {
                    if (alexaV3AlarmItem2.getStatus() == 85) {
                        boolean z = false;
                        Iterator it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                str2 = str10;
                                alexaV3AlarmItem = alexaV3AlarmItem2;
                                str3 = str9;
                                str4 = str8;
                                str6 = str7;
                                arrayList = arrayList3;
                                break;
                            }
                            AlexaAlarmBean alexaAlarmBean = (AlexaAlarmBean) it.next();
                            if (isSameDateAlarm(alexaAlarmBean, alexaV3AlarmItem2)) {
                                str2 = str10;
                                alexaV3AlarmItem = alexaV3AlarmItem2;
                                str6 = str7;
                                arrayList = arrayList3;
                                str3 = str9;
                                str4 = str8;
                                arrayList.add(initAlexaAlarm(arrayList3.size() + 1, alexaV3AlarmItem2.getYear(), alexaV3AlarmItem2.getMonth(), alexaV3AlarmItem2.getDay(), alexaV3AlarmItem2.getHour(), alexaV3AlarmItem2.getMinute(), alexaAlarmBean.getScheduledTimeMillis(), alexaV3AlarmItem2.getStatus(), alexaV3AlarmItem2.getWeekRepeat(), alexaV3AlarmItem2.isOn_off(), alexaAlarmBean.getToken()));
                                it.remove();
                                z = true;
                                break;
                            }
                        }
                        if (z) {
                            str5 = str6;
                        } else {
                            AlexaAlarmBean alexaAlarmBean2 = getAlexaAlarmBean(alexaAccountOnAlarms, alexaV3AlarmItem);
                            if (alexaAlarmBean2 == null) {
                                SimpleDateFormat simpleDateFormat3 = simpleDateFormat2;
                                StringBuilder sb = new StringBuilder();
                                sb.append(alexaV3AlarmItem.getYear());
                                String str11 = str6;
                                sb.append(str11);
                                sb.append(alexaV3AlarmItem.getMonth());
                                sb.append(str11);
                                sb.append(alexaV3AlarmItem.getDay());
                                sb.append(" ");
                                sb.append(alexaV3AlarmItem.getHour());
                                sb.append(":");
                                sb.append(alexaV3AlarmItem.getMinute());
                                str5 = str11;
                                alexaAlarmBean2 = initAlexaAlarm(arrayList.size() + 1, alexaV3AlarmItem.getYear(), alexaV3AlarmItem.getMonth(), alexaV3AlarmItem.getDay(), alexaV3AlarmItem.getHour(), alexaV3AlarmItem.getMinute(), simpleDateFormat3.parse(sb.toString()).getTime(), alexaV3AlarmItem.getStatus(), alexaV3AlarmItem.getWeekRepeat(), alexaV3AlarmItem.isOn_off(), "");
                            } else {
                                str5 = str6;
                            }
                            arrayList.add(alexaAlarmBean2);
                        }
                    } else {
                        str2 = str10;
                        str3 = str9;
                        str4 = str8;
                        str5 = str7;
                        arrayList = arrayList3;
                    }
                    str10 = str2;
                    arrayList3 = arrayList;
                    str7 = str5;
                    str9 = str3;
                    str8 = str4;
                }
                String str12 = str8;
                Util.put(str9, GsonUtil.toJson(arrayList3));
                AlexaLogUtil.printAndSave("同步设备的alexa闹钟 alarms= " + str10);
                for (AlexaAlarmBean alexaAlarmBean3 : list) {
                    String str13 = str12;
                    AlexaApi.deleteAlarm(alexaAlarmBean3.getToken().replace(ALARM_TOKEN_PREFIX, str13), null);
                    AlexaLogUtil.printAndSave("同步设备的alexa闹钟，delete alexa account 的alarm ==" + alexaAlarmBean3.toString());
                    str12 = str13;
                }
                return;
            }
            AlexaApi.deleteAllAlarms(null);
            Util.put(AlexaCustomSkillConstant.ALEXA_ALARMS, "");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private AlexaAlarmBean getAlexaAlarmBean(List<AlexaAlarmBean> list, AlexaV3AlarmItem alexaV3AlarmItem) {
        for (AlexaAlarmBean alexaAlarmBean : list) {
            if (isSameDateAlarm(alexaAlarmBean, alexaV3AlarmItem)) {
                alexaAlarmBean.setToken(alexaAlarmBean.getToken());
                return alexaAlarmBean;
            }
        }
        AlexaLogUtil.printAndSave("找不到token= " + alexaV3AlarmItem.toString());
        return null;
    }

    private boolean isSameDateAlarm(AlexaAlarmBean alexaAlarmBean, AlexaV3AlarmItem alexaV3AlarmItem) {
        return Arrays.equals(alexaV3AlarmItem.getWeekRepeat(), alexaAlarmBean.getWeekRepeat()) && alexaV3AlarmItem.getDay() == alexaAlarmBean.getDay() && alexaV3AlarmItem.getHour() == alexaAlarmBean.getHour() && alexaV3AlarmItem.getMinute() == alexaAlarmBean.getMinute() && alexaV3AlarmItem.getYear() == alexaAlarmBean.getYear() && alexaV3AlarmItem.getMonth() == alexaAlarmBean.getMonth();
    }

    @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
    public void onResponse(int i, String str) {
        if (i == 5033) {
            BLEManager.unregisterDeviceResponseCommonCallBack(this);
            AlexaLogUtil.printAndSave("设备返回的闹钟=" + str);
            handlerDeviceAlexaAlarm(str);
        }
    }

    abstract class ICallBack<T> {
        public abstract void onCallBack(T t);

        ICallBack() {
        }
    }
}