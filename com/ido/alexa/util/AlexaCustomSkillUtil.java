package com.ido.alexa.util;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.alexa.bean.AlexaJumpSportUi;
import com.ido.alexa.bean.AlexaJumpUi;
import com.ido.alexa.bean.AlexaOnOffMode;
import com.ido.alexa.bean.AlexaOperationMode;
import com.ido.alexa.bean.AlexaSetWeatherData;
import com.ido.alexa.bean.AlexaTimerCacheBean;
import com.ido.alexa.bean.AlexaTimerItem;
import com.ido.alexa.bean.AlexaWeatherItem;
import com.ido.alexa.callbacks.AlexaSettingCallBack;
import com.ido.alexa.data.Directive;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.protocol.model.VoiceToText;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaCustomSkillUtil {
    private static final Gson mGson = new Gson();

    static void setNewTimer(String str, int i) {
        int size;
        AlexaTimerCacheBean alexaTimerCacheBean = new AlexaTimerCacheBean();
        alexaTimerCacheBean.setToken(str);
        alexaTimerCacheBean.setScheduledTimeMillis(System.currentTimeMillis() + ((long) (i * 1000)));
        String str2 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_TIMERS, "");
        AlexaLogUtil.printAndSave("setNewTimer--getOldTimer= " + str2);
        if (!TextUtils.isEmpty(str2) && str2.contains(str)) {
            AlexaLogUtil.printAndSave("setNewTimer--重复的timer");
            return;
        }
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList(str2, AlexaTimerCacheBean.class);
        if (listAnalysisJsonObjectToList == null) {
            listAnalysisJsonObjectToList = new ArrayList();
            listAnalysisJsonObjectToList.add(alexaTimerCacheBean);
            size = 0;
        } else if (listAnalysisJsonObjectToList.size() < 3) {
            size = listAnalysisJsonObjectToList.size();
            listAnalysisJsonObjectToList.add(alexaTimerCacheBean);
        } else {
            int size2 = listAnalysisJsonObjectToList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size2) {
                    i2 = -1;
                    break;
                }
                AlexaTimerCacheBean alexaTimerCacheBean2 = (AlexaTimerCacheBean) listAnalysisJsonObjectToList.get(i2);
                if (!TextUtils.isEmpty(alexaTimerCacheBean2.getToken()) && System.currentTimeMillis() > alexaTimerCacheBean2.getScheduledTimeMillis()) {
                    alexaTimerCacheBean2.setToken("");
                    alexaTimerCacheBean2.setScheduledTimeMillis(0L);
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                i2 = 0;
            }
            listAnalysisJsonObjectToList.set(i2, alexaTimerCacheBean);
            size = i2;
        }
        String json = GsonUtil.toJson(listAnalysisJsonObjectToList);
        AlexaLogUtil.printAndSave("setNewTimer--saveTimer= " + json);
        Util.put(AlexaCustomSkillConstant.ALEXA_TIMERS, json);
        AlexaTimerItem alexaTimerItem = new AlexaTimerItem();
        alexaTimerItem.setIndex(size);
        alexaTimerItem.setDelay_time(i);
        alexaTimerItem.setOperate_timer_flag(0);
        AlexaLogUtil.printAndSave("设置timer=" + alexaTimerItem.toString());
        BLEManager.setParaToDeviceByTypeAndJson(7602, mGson.toJson(alexaTimerItem));
    }

    static void delAllTimer() {
        Util.put(AlexaCustomSkillConstant.ALEXA_TIMERS, "");
        AlexaTimerItem alexaTimerItem = new AlexaTimerItem();
        alexaTimerItem.setIndex(0);
        alexaTimerItem.setDelay_time(0L);
        alexaTimerItem.setOperate_timer_flag(255);
        AlexaLogUtil.printAndSave("取消所有timer=" + alexaTimerItem.toString());
        BLEManager.setParaToDeviceByTypeAndJson(7602, mGson.toJson(alexaTimerItem));
    }

    static void delNewTimer(String str) {
        String str2 = (String) Util.get(AlexaCustomSkillConstant.ALEXA_TIMERS, "");
        AlexaLogUtil.printAndSave("delNewTimer--getOldTimer= " + str2);
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList(str2, AlexaTimerCacheBean.class);
        if (listAnalysisJsonObjectToList == null) {
            listAnalysisJsonObjectToList = new ArrayList();
        }
        int i = 0;
        int size = listAnalysisJsonObjectToList.size();
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            }
            AlexaTimerCacheBean alexaTimerCacheBean = (AlexaTimerCacheBean) listAnalysisJsonObjectToList.get(i);
            if (TextUtils.equals(alexaTimerCacheBean.getToken(), str)) {
                alexaTimerCacheBean.setToken("");
                alexaTimerCacheBean.setScheduledTimeMillis(0L);
                break;
            }
            i++;
        }
        if (i == -1) {
            AlexaLogUtil.printAndSave("取消timer= 未找到token = " + str);
            return;
        }
        String json = GsonUtil.toJson(listAnalysisJsonObjectToList);
        AlexaLogUtil.printAndSave("delNewTimer--saveTimer= " + json);
        Util.put(AlexaCustomSkillConstant.ALEXA_TIMERS, json);
        AlexaTimerItem alexaTimerItem = new AlexaTimerItem();
        alexaTimerItem.setIndex(i);
        alexaTimerItem.setDelay_time(0L);
        alexaTimerItem.setOperate_timer_flag(1);
        AlexaLogUtil.printAndSave("取消timer=" + alexaTimerItem.toString());
        BLEManager.setParaToDeviceByTypeAndJson(7602, mGson.toJson(alexaTimerItem));
    }

    public static void handlerToggleCmd(boolean z, String str, String str2) {
        if (TextUtils.equals(str, AlexaCustomSkillConstant.EVENT_START_SPORT)) {
            startSport(z, str2);
        } else if (TextUtils.equals(str, "5")) {
            openDeviceView(str2);
        }
    }

    public static void handlerStopCmd(String str) {
        int iIntValue = parseInt(str).intValue();
        AlexaOnOffMode alexaOnOffMode = new AlexaOnOffMode();
        alexaOnOffMode.setType(iIntValue);
        alexaOnOffMode.setOn_off(85);
        AlexaLogUtil.printAndSave("发送停止指令=" + alexaOnOffMode.toString());
        if (iIntValue >= 0) {
            BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_VOICE_SET_ON_OFF_TYPE, mGson.toJson(alexaOnOffMode));
            if (iIntValue == 18) {
                AlexaSettingCallBack.onCallback(AlexaSettingCallBack.AlexaSettingType.STOP_FIND_PHONE, "");
            }
        }
    }

    public static void handlerWakeupGesture(boolean z) {
        AlexaOperationMode alexaOperationMode = new AlexaOperationMode();
        alexaOperationMode.setUi_type(3);
        alexaOperationMode.setOperation_type(!z ? 1 : 0);
        AlexaLogUtil.printAndSave("抬腕亮屏控制=" + alexaOperationMode.toString());
        BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_VOICE_OPERATION, mGson.toJson(alexaOperationMode));
    }

    public static void brightnessControl(AlexaOperationMode alexaOperationMode) {
        AlexaLogUtil.printAndSave("亮度调节=" + alexaOperationMode.toString());
        BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_VOICE_OPERATION, mGson.toJson(alexaOperationMode));
    }

    public static void openDeviceView(String str) {
        int iIntValue = parseInt(str).intValue();
        if (iIntValue == 8 && !LocalDataManager.getFindPhoneSwitch()) {
            AlexaSettingCallBack.onCallback(AlexaSettingCallBack.AlexaSettingType.FIND_PHONE, "");
        }
        AlexaLogUtil.printAndSave("界面跳转=" + iIntValue);
        AlexaJumpUi alexaJumpUi = new AlexaJumpUi();
        alexaJumpUi.setType(iIntValue);
        BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_VOICE_JUMP, mGson.toJson(alexaJumpUi));
    }

    public static void startSport(boolean z, String str) {
        AlexaJumpSportUi alexaJumpSportUi = new AlexaJumpSportUi();
        if (z) {
            alexaJumpSportUi.setSports_type(parseInt(str).intValue());
        } else {
            alexaJumpSportUi.setSports_type(42);
            alexaJumpSportUi.setV2_sport_type(parseInt(str).intValue());
        }
        AlexaLogUtil.printAndSave("发送运动=" + alexaJumpSportUi.toString());
        BLEManager.setParaToDeviceByTypeAndJson(7601, mGson.toJson(alexaJumpSportUi));
    }

    public static void sendWeather(Directive.Payload payload, final byte[] bArr, final AudioPlayer audioPlayer) {
        if (payload == null) {
            return;
        }
        AlexaSetWeatherData alexaSetWeatherData = new AlexaSetWeatherData();
        alexaSetWeatherData.setVersion(1);
        if (payload.getTitle() != null) {
            alexaSetWeatherData.setLocation(payload.getTitle().getMainTitle());
        }
        alexaSetWeatherData.setCur_weather(payload.getCurrentWeather());
        if (payload.getCurrentWeatherIcon() != null) {
            alexaSetWeatherData.setToday_weather_state(payload.getCurrentWeatherIcon().getContentDescription());
        }
        if (payload.getLowTemperature() != null && payload.getHighTemperature() != null) {
            alexaSetWeatherData.setToday_min_max_weather(payload.getLowTemperature().getValue() + "/" + payload.getHighTemperature().getValue());
        }
        ArrayList<AlexaWeatherItem> arrayList = new ArrayList<>();
        if (payload.getWeatherForecast() != null && payload.getWeatherForecast().size() > 0) {
            for (Directive.Payload.WeatherForecast weatherForecast : payload.getWeatherForecast()) {
                if (weatherForecast != null) {
                    AlexaWeatherItem alexaWeatherItem = new AlexaWeatherItem();
                    alexaWeatherItem.setDate(weatherForecast.getDay());
                    alexaWeatherItem.setWeather_state(weatherForecast.getImage().getContentDescription());
                    alexaWeatherItem.setMin_max_weather(weatherForecast.getLowTemperature() + "/" + weatherForecast.getHighTemperature());
                    arrayList.add(alexaWeatherItem);
                }
            }
        }
        alexaSetWeatherData.setFuture_weather_len(arrayList.size());
        alexaSetWeatherData.setFuture_weather(arrayList);
        AlexaLogUtil.printAndSave("发送七天天气=" + alexaSetWeatherData.toString());
        BLEManager.registerDeviceResponseCommonCallBack(new DeviceResponseCommonCallBack.ICallBack() { // from class: com.ido.alexa.util.AlexaCustomSkillUtil.1
            @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
            public void onResponse(int i, String str) {
                AudioPlayer audioPlayer2;
                if (i != 5032) {
                    return;
                }
                BLEManager.unregisterDeviceResponseCommonCallBack(this);
                AlexaLogUtil.printAndSave("发送七天天气===完成，发语音");
                byte[] bArr2 = bArr;
                if (bArr2 == null || (audioPlayer2 = audioPlayer) == null) {
                    return;
                }
                audioPlayer2.saveAudio(bArr2);
            }
        });
        BLEManager.setParaToDeviceByTypeAndJson(AlexaCustomSkillConstant.VoiceProtocolEvent.EVT_VOICE_WEATHER, mGson.toJson(alexaSetWeatherData));
    }

    private static void replyDevice(String str) {
        VoiceToText voiceToText = new VoiceToText();
        voiceToText.title = "";
        voiceToText.text_content = str;
        BLEManager.setVoiceToText(voiceToText);
    }

    public static Integer parseInt(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception unused) {
            return 0;
        }
    }
}