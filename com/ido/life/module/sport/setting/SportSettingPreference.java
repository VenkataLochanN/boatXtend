package com.ido.life.module.sport.setting;

import com.ido.common.IdoApp;
import com.ido.common.base.BasePreference;

/* JADX INFO: loaded from: classes2.dex */
public class SportSettingPreference extends BasePreference {
    private static final String NAME = "sport_setting_preference";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_DISTANCE = "distance";
    private static final String TAG_DISTANCE_INTERVAL = "distanceInterval";
    private static final String TAG_IS_DISTANCE_VOICE = "isDistanceVoice";
    private static final String TAG_IS_RATE_WARMING = "isRateWarning";
    private static final String TAG_IS_SPORT_SOUND_ON_OR_OFF = "isSoundOnOrOff";
    private static final String TAG_IS_SPORT_TARGET = "isSportTarget";
    private static final String TAG_RATE_WARNING = "rateWarning";
    private static final String TAG_STEP = "step";
    private static final String TAG_TIME = "time";

    public static void clear() {
    }

    public static SportSetting getSportSetting() {
        SportSetting sportSetting = new SportSetting();
        sportSetting.setSportTarget(getBoolean(IdoApp.getAppContext(), NAME, TAG_IS_SPORT_TARGET, false));
        sportSetting.setDistance(getInt(IdoApp.getAppContext(), NAME, TAG_DISTANCE));
        sportSetting.setCategory(getInt(IdoApp.getAppContext(), NAME, TAG_CATEGORY));
        sportSetting.setStep(getInt(IdoApp.getAppContext(), NAME, TAG_STEP));
        sportSetting.setTime(getInt(IdoApp.getAppContext(), NAME, "time"));
        sportSetting.setDistanceVoice(getBoolean(IdoApp.getAppContext(), NAME, TAG_IS_DISTANCE_VOICE, false));
        sportSetting.setDistanceInterval(getInt(IdoApp.getAppContext(), NAME, TAG_DISTANCE_INTERVAL));
        sportSetting.setRateWarning(getBoolean(IdoApp.getAppContext(), NAME, TAG_IS_RATE_WARMING, false));
        sportSetting.setRateWarning(getInt(IdoApp.getAppContext(), NAME, TAG_RATE_WARNING));
        sportSetting.setSportRemindOff(getBoolean(IdoApp.getAppContext(), NAME, TAG_IS_SPORT_SOUND_ON_OR_OFF, false));
        return sportSetting;
    }

    public static void saveSportSetting(SportSetting sportSetting) {
        if (sportSetting == null) {
            sportSetting = new SportSetting();
        }
        saveBoolean(IdoApp.getAppContext(), NAME, TAG_IS_SPORT_TARGET, sportSetting.isSportTarget());
        saveInt(IdoApp.getAppContext(), NAME, TAG_DISTANCE, sportSetting.getDistance());
        saveInt(IdoApp.getAppContext(), NAME, TAG_CATEGORY, sportSetting.getCategory());
        saveInt(IdoApp.getAppContext(), NAME, TAG_STEP, sportSetting.getStep());
        saveInt(IdoApp.getAppContext(), NAME, "time", sportSetting.getTime());
        saveBoolean(IdoApp.getAppContext(), NAME, TAG_IS_DISTANCE_VOICE, sportSetting.isDistanceVoice());
        saveInt(IdoApp.getAppContext(), NAME, TAG_DISTANCE_INTERVAL, sportSetting.getDistanceInterval());
        saveBoolean(IdoApp.getAppContext(), NAME, TAG_IS_RATE_WARMING, sportSetting.isRateWarning());
        saveInt(IdoApp.getAppContext(), NAME, TAG_RATE_WARNING, sportSetting.getRateWarning());
        saveBoolean(IdoApp.getAppContext(), NAME, TAG_IS_SPORT_SOUND_ON_OR_OFF, sportSetting.isSportRemindOff());
    }

    public static void clearDistance() {
        SportSetting sportSetting = getSportSetting();
        sportSetting.setSportTarget(false);
        sportSetting.setDistanceVoice(false);
        sportSetting.setDistanceInterval(0);
        sportSetting.setDistance(0);
        saveSportSetting(sportSetting);
    }
}