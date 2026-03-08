package com.ido.alexa.callbacks;

import com.ido.alexa.AlexaApi;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaSettingCallBack {

    public enum AlexaSettingEventType {
        START,
        PAUSE,
        STOP,
        PREVIOUS,
        NEXT,
        VOLUME_UP,
        VOLUME_DOWN
    }

    public enum AlexaSettingType {
        ALEXA_UN_LOGIN,
        ALEXA_HELP,
        MUSIC,
        FIND_PHONE,
        STOP_FIND_PHONE,
        BRIGHTNESS,
        HEARTRATE,
        BLOOD_OXYGEN,
        SLEEP_SCORE,
        STEP_COUNT,
        LAST_WEEKLY_AVG_HR,
        LAST_MONTHLY_AVG_HR,
        LAST_YEARLY_AVG_HR,
        TODAY_WORKOUT_COUNT,
        TODAY_SWIMMING_COUNT,
        TODAY_RUNNING_COUNT,
        TODAY_CALORIE_STATISTICS,
        TODAY_KILOMETER_STATISTICST,
        TODAY_SWIMMING_STATISTICS,
        WORKOUT_HISTORY
    }

    public interface ICallBack {
        void onSet(AlexaSettingType alexaSettingType, Object obj);
    }

    public static final void onCallback(AlexaSettingType alexaSettingType, Object obj) {
        Iterator<ICallBack> it = AlexaApi.getAlexaSettingCallBackList().iterator();
        while (it.hasNext()) {
            it.next().onSet(alexaSettingType, obj);
        }
    }
}