package com.ido.life.util;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.ble.protocol.model.HeartRateSmartMode;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.NightTemperatureMonitoringPara;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.ScheduleReminderSwitch;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.ble.protocol.model.WashHandReminder;
import com.ido.ble.protocol.model.WeatherInfo;
import com.ido.ble.protocol.model.WeatherInfoV3;
import com.ido.ble.protocol.model.WeatherSunTime;
import com.ido.common.IdoApp;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.DeviceExceptionModel;
import com.ido.life.bean.LatLngBean;
import com.ido.life.bean.QuickApp;
import com.ido.life.bean.QuickMsgBean;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.data.api.entity.DeviceInfo;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.api.entity.RemoteLanguage;
import com.ido.life.data.health.local.UserTargetPreference;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.DeviceWhiteListEntity;
import com.ido.life.database.model.ServerMenstrual;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WeatherEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SPHelper {
    private static final String ACTIVATED_DEVICE_LIST = "activated_device_list";
    private static final String AGPS_OFFLINE_UPGRADE_TIME = "agps_offline_upgrade_time";
    private static final String AGPS_ONLINE_UPGRADE_TIME = "agps_online_upgrade_time";
    private static final String AIR_WEATHER_DATA = "air_weather_data";
    private static final String ALARM_INTERVAL_MINUTE = "alarm_interval_minute";
    private static final String ALARM_REPEAT_COUNT = "alarm_repeat_count";
    private static final String ALEXA_ALARM_NO_REMINDE = "ALEXA_ALARM_NO_REMINDE";
    private static final String APP_UPDATE_RECENT_DATE = "app_update_recent_date";
    private static final String AUTO_SHOW_HISTORY_SYNC_STATE = "auto_show_history_sync_state";
    private static final String BIND_NEW_DEVICE = "bind_new_device";
    private static final String BODY_TEMPERATURE_MODE = "body_temperature_";
    private static final String BUILT_IN_DIAL_LIST = "built_in_dial_list_";
    private static final String CURRENT_COUNTRY_CODE = "current_country_code";
    private static final String DATA_SLEEP_SYNC_SUCCESS_TIMESTAMP = "data_sleep_sync_success_timestamp_";
    private static final String DATA_SPORT_RECORD_SYNC_SUCCESS_TIMESTAMP = "data_sport_record_sync_success_timestamp_";
    private static final String DATA_SYNC_TIMESTAMP = "data_sync_timestamp_";
    private static final String DEVICE_DISCONNECT_TIMESTAMP = "device_disconnect_timestamp_";
    private static final String DEVICE_EXCEPTION_LOG_CACHE = "device_exception_log_cache";
    private static final String DEVICE_HELP = "device_help";
    private static final String DEVICE_INFO_LIST = "device_info_list";
    private static final String DEVICE_LIST = "device_list";
    private static final String DEVICE_THIRD_VERSION = "devive_third_version";
    private static final String DEVICE_UNREAD_REMINDER = "device_unread_reminder_";
    private static final String DEVICE_WHITE_LIST = "devices_white_list";
    private static final String DFU_OTA_CACHE_LOG_LIST = "dfu_ota_cache_log_list";
    private static final String DIAL_UPDATE_DATE = "dial_update_date";
    private static final String FIRST_AJUST_PRESSURE = "first_ajust_pressure";
    private static final String FITNESS_GUIDANCE_REMINDER = "fitness_guidance_reminder";
    private static final String FLASH_CACHE_LOG_LIST = "flash_cache_log_list";
    private static final String GOOGLE_FIT_SWITCH = "google_fit_switch";
    private static final String HEART_RATE_MODE = "heart_rate_mode";
    private static final String IS_CHINA = "is_china";
    private static final String IS_SHOW_UP_UPDATE_DIALOG = "is_show_update_dialog";
    private static final String JOIN_USER_EXPERIENCE = "join_user_experience";
    private static final String KEY_CITIES_IN_DEVICE = "key_cities_in_device_";
    private static final String LANGUAGE_CACHE_LOG_LIST = "language_cache_log_list";
    private static final String LANGUAGE_FOLLOW_SYS = "language_follow_sys";
    private static final String LAST_FLASH_REMINDER_DATE = "last_flash_reminder_date";
    private static final String LAST_OTA_REMINDER_DATE = "last_ota_reminder_date";
    private static final String LAST_REMIND_FIRMWARE_VERSION = "last_remind_firmware_version";
    private static final String LAST_REMIND_LANGUAGE_INFO = "last_remind_language_info";
    private static final String LAST_SYNCED_CONFIG_VERSION = "last_synced_config_version";
    private static final String LAST_SYS_REMINDER_DATE = "last_sys_reminder_date";
    private static final String LOCATION_LONG_LAT = "location_long_lat";
    private static final String MAIN_CALLPERMMISION = "main_callpermmision";
    private static final String MAX_HEART_RATE = "max_heart_rate";
    private static final String MEMBER_LIST_LOAD_SUCCESS = "member_list_load_success";
    private static final String MENSTRUAL_CYCLE_DATA = "menstrual_cycle_data";
    private static final String MENSTRUAL_LIST = "menstrual_list";
    private static final String MENU_LISTS = "menu_lists";
    public static final int MODE_FOLLOW_SYSTEM = 0;
    private static final String MOTION_ICON_TRANS_COMPLETE = "motion_icon_trans_success_";
    private static final String MOTION_RESOURCE_VERSION = "motion_resource_version";
    private static final String NOISE_SWITCH = "noise_switch_";
    private static final String OTA_CACHE_LOG_LIST = "ota_cache_log_list";
    private static final String PRO_EMAILS_DATA_FOR_DIALOG = "proEmailsDataForDialog";
    private static final String PRO_USER_ID = "proUserId";
    private static final String QUICK_MSG_BEAN_LIST = "msg_bean_list";
    private static final String QUICK_MSG_LIST = "msg_list";
    private static final String QUICK_MSG_REPLY_SWITCH = "quick_msg_reply_switch";
    private static final String RECENT_LOGIN_ACCOUNT = "recent_login_account";
    private static final String SCHEDULE_REMINDER_SWITCH = "schedulere_minder_switch_";
    private static final String SMART_HEART_RATE_MODE = "smart_heart_rate_mode_";
    private static final String SWITCH_STATUS = "switch_status";
    private static final String SYNCED_CONFIG = "synced_config";
    private static final String SYNC_TIME = "sync_time";
    private static final String TAG = SPHelper.class.getSimpleName();
    private static final String THREE_DAY_WEATHER_SUNTIME = "three_day_weather_suntime";
    private static final String TIMESTAMP_OF_BATTERY_LOG = "timestamp_of_battery_log";
    private static final String TIME_FOLLOW_SYS = "time_follow_sys";
    private static final String TIME_TO_BACKGROUND = "time_to_background";
    private static final String TIME_TO_SENDWEATHER = "time_to_sendweather";
    private static final String UN_READ_MESSAGE_COUNT = "un_read_message_count";
    private static final String USER_DEVICE = "user_device";
    private static final String USER_INFO_CHANGED = "user_info_changed";
    private static final String WALK_REMINDER = "walk_reminder";
    private static final String WASH_HAND_REMINDER = "wash_hand_reminder_";
    private static final String WEAR_REMINDER_NOTICE = "wear_reminder_notice";
    private static final String WEATHER_DATA = "weather_data";
    private static final String WEATHER_DATA_V3 = "weather_data_v3";
    private static final String WEATHER_SUNTIME = "weather_suntime";

    private static <T> T getObject(String str, Class<T> cls) {
        try {
            String string = getString(str);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return (T) GsonUtil.fromJson(string, cls);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static <T> T getObjectWithMac(String str, Class<T> cls) {
        return (T) getObject(keyWithMac(str), cls);
    }

    private static String getString(String str) {
        try {
            return (String) SPUtils.get(str, "");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String getStringWithMac(String str) {
        try {
            return getString(keyWithMac(str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static void putString(String str, String str2) {
        try {
            SPUtils.put(str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void putStringWithMac(String str, String str2) {
        try {
            putString(keyWithMac(str), str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void putBoolWithMac(String str, Boolean bool) {
        try {
            putBool(keyWithMac(str), bool);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void putBool(String str, Boolean bool) {
        try {
            SPUtils.put(str, bool);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static boolean getBoolWithMac(String str, boolean z) {
        try {
            return getBool(keyWithMac(str), z);
        } catch (Exception e2) {
            e2.printStackTrace();
            return z;
        }
    }

    private static boolean getBool(String str, boolean z) {
        try {
            return ((Boolean) SPUtils.get(str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return z;
        }
    }

    private static void putJson(String str, Object obj) {
        try {
            putString(str, GsonUtil.toJson(obj));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void putJsonWithMac(String str, Object obj) {
        try {
            putStringWithMac(str, GsonUtil.toJson(obj));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static String keyWithMac(String str) {
        if (str.endsWith("_")) {
            return str + getCurrMac();
        }
        return str + "_" + getCurrMac();
    }

    private static String keyWithMac(String str, String str2) {
        if (str.endsWith("_")) {
            return str + str2;
        }
        return str + "_" + str2;
    }

    private static String keyWithUser(String str) {
        long userId = RunTimeUtil.getInstance().getUserId();
        String str2 = "";
        if (userId != 0) {
            str2 = userId + "";
        }
        if (str.endsWith("_")) {
            return str + str2;
        }
        return str + "_" + str2;
    }

    private static String getCurrMac() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        return currentDeviceInfo != null ? currentDeviceInfo.mDeviceAddress : "";
    }

    public static boolean isHideDeviceHelp() {
        return ((Boolean) SPUtils.get(DEVICE_HELP, false)).booleanValue();
    }

    public static void hideDeviceHelp() {
        SPUtils.put(DEVICE_HELP, true);
    }

    public static boolean isLogin() {
        return AccountRepository.getInstance().isUserSignIn();
    }

    public static void setLastSyncedConfigVersion(int i) {
        SPUtils.put(LAST_SYNCED_CONFIG_VERSION, Integer.valueOf(i));
    }

    public static boolean isCurrentVersionSyncedConfig() {
        return AppUtil.getVersionCode(IdoApp.getAppContext()) == ((Integer) SPUtils.get(LAST_SYNCED_CONFIG_VERSION, 0)).intValue();
    }

    public static void setConfigSynced(boolean z) {
        SPUtils.put(SYNCED_CONFIG, Boolean.valueOf(z));
        if (z) {
            setLastSyncedConfigVersion(AppUtil.getVersionCode(IdoApp.getAppContext()));
        }
    }

    public static boolean isConfigSynced() {
        return ((Boolean) SPUtils.get(SYNCED_CONFIG, false)).booleanValue();
    }

    public static String getToken() {
        return AuthorizationPreference.getToken(IdoApp.getAppContext());
    }

    public static void saveDevice(DeviceListEntity.DeviceInfo deviceInfo) {
        removeDevice(deviceInfo);
        List<DeviceListEntity.DeviceInfo> deviceList = getDeviceList();
        deviceList.add(0, deviceInfo);
        SPUtils.put(DEVICE_LIST, GsonUtil.toJson(deviceList));
    }

    public static List<DeviceListEntity.DeviceInfo> getDeviceList() {
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) SPUtils.get(DEVICE_LIST, ""), DeviceListEntity.DeviceInfo.class);
        return listAnalysisJsonObjectToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonObjectToList);
    }

    public static void removeDevice(DeviceListEntity.DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        List<DeviceListEntity.DeviceInfo> deviceList = getDeviceList();
        int i = 0;
        while (i < deviceList.size()) {
            DeviceListEntity.DeviceInfo deviceInfo2 = deviceList.get(i);
            if (deviceInfo.getMac().equals(deviceInfo2.getMac())) {
                deviceList.remove(deviceInfo2);
                i--;
            }
            i++;
        }
        SPUtils.put(DEVICE_LIST, GsonUtil.toJson(deviceList));
    }

    public static SwitchStatus getSwitchStatus() {
        SwitchStatus switchStatus = (SwitchStatus) GsonUtil.fromJson((String) SPUtils.get(SWITCH_STATUS, ""), SwitchStatus.class);
        return switchStatus == null ? new SwitchStatus() : switchStatus;
    }

    public static void setSwitchStatus(SwitchStatus switchStatus) {
        if (switchStatus == null) {
            switchStatus = new SwitchStatus();
        }
        SPUtils.put(SWITCH_STATUS, GsonUtil.toJson(switchStatus));
    }

    public static SwitchStatus.NotificationSwitch getNotificationStatus() {
        SwitchStatus.NotificationSwitch notificationSwitch = getSwitchStatus().notificationSwitch;
        return notificationSwitch == null ? new SwitchStatus.NotificationSwitch() : notificationSwitch;
    }

    public static void setNotificationStatus(SwitchStatus.NotificationSwitch notificationSwitch) {
        SwitchStatus switchStatus = getSwitchStatus();
        switchStatus.notificationSwitch = notificationSwitch;
        setSwitchStatus(switchStatus);
    }

    public static boolean isLanguageFollowSys() {
        return ((Boolean) SPUtils.get(LANGUAGE_FOLLOW_SYS, true)).booleanValue();
    }

    public static void setLanguageMode(boolean z) {
        SPUtils.put(LANGUAGE_FOLLOW_SYS, Boolean.valueOf(z));
    }

    public static void saveWeatherDataV3(WeatherInfoV3 weatherInfoV3) {
        SPUtils.put(WEATHER_DATA_V3, GsonUtil.toJson(weatherInfoV3));
    }

    public static boolean isTimeFollowSys() {
        return ((Boolean) SPUtils.get(TIME_FOLLOW_SYS, true)).booleanValue();
    }

    public static void setTimeFormat(int i) {
        SPUtils.put(TIME_FOLLOW_SYS, Boolean.valueOf(i == 0));
    }

    public static void setMaxHeartRate(int i) {
        SPUtils.put(MAX_HEART_RATE, Integer.valueOf(i));
    }

    public static void saveMenuLists(List<QuickApp> list) {
        SPUtils.put(MENU_LISTS, GsonUtil.toJson(list));
    }

    public static void addMenuList(QuickApp quickApp) {
        List<QuickApp> menuLists = getMenuLists();
        menuLists.add(quickApp);
        saveMenuLists(menuLists);
    }

    public static void removeMenuList(int i) {
        List<QuickApp> menuLists = getMenuLists();
        if (i >= menuLists.size() || i < 0) {
            return;
        }
        menuLists.remove(i);
    }

    public static List<QuickApp> getMenuLists() {
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) SPUtils.get(MENU_LISTS, ""), QuickApp.class);
        return listAnalysisJsonObjectToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonObjectToList);
    }

    public static void saveAirWeatherData(WeatherEntity.ServerWeather.WeatherQualityInfo weatherQualityInfo) {
        SPUtils.put(AIR_WEATHER_DATA, GsonUtil.toJson(weatherQualityInfo));
    }

    public static WeatherEntity.ServerWeather.WeatherQualityInfo getAirWeatherData() {
        return (WeatherEntity.ServerWeather.WeatherQualityInfo) GsonUtil.fromJson((String) SPUtils.get(AIR_WEATHER_DATA, ""), WeatherEntity.ServerWeather.WeatherQualityInfo.class);
    }

    public static void saveWeatherData(WeatherInfo weatherInfo) {
        SPUtils.put(WEATHER_DATA, GsonUtil.toJson(weatherInfo));
    }

    public static WeatherInfo getWeatherInfo() {
        return (WeatherInfo) GsonUtil.fromJson((String) SPUtils.get(WEATHER_DATA, ""), WeatherInfo.class);
    }

    public static void saveTimeOfApp2Background(long j) {
        SPUtils.put(TIME_TO_BACKGROUND, Long.valueOf(j));
    }

    public static long getTimeOfApp2Background() {
        return ((Long) SPUtils.get(TIME_TO_BACKGROUND, 0L)).longValue();
    }

    public static UserTargetNew getUserTarget() {
        return UserTargetPreference.getUserTarget();
    }

    public static WeatherInfoV3 getWeatherInfoV3() {
        return (WeatherInfoV3) GsonUtil.fromJson((String) SPUtils.get(WEATHER_DATA_V3, ""), WeatherInfoV3.class);
    }

    public static void saveLocation(double d2, double d3) {
        SPUtils.put(LOCATION_LONG_LAT, GsonUtil.toJson(new LatLngBean(d2, d3)));
    }

    public static LatLngBean getLocation() {
        return (LatLngBean) GsonUtil.fromJson((String) SPUtils.get(LOCATION_LONG_LAT, ""), LatLngBean.class);
    }

    public static void saveDeviceWhiteList(List<DeviceWhiteListEntity.DeviceInfo> list) {
        SPUtils.put(DEVICE_WHITE_LIST, GsonUtil.toJson(list));
    }

    public static List<DeviceWhiteListEntity.DeviceInfo> getDeviceWhiteList() {
        List<DeviceWhiteListEntity.DeviceInfo> listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) SPUtils.get(DEVICE_WHITE_LIST, ""), DeviceWhiteListEntity.DeviceInfo.class);
        return listAnalysisJsonObjectToList == null ? new ArrayList() : listAnalysisJsonObjectToList;
    }

    public static void saveGoogleFitSwitchStatus(boolean z) {
        SPUtils.put(GOOGLE_FIT_SWITCH, Boolean.valueOf(z));
    }

    public static boolean isGoogleFitSwitchOn() {
        return ((Boolean) SPUtils.get(GOOGLE_FIT_SWITCH, false)).booleanValue();
    }

    public static void saveMenstrualData(Menstrual menstrual) {
        List<Menstrual> menstrualList = getMenstrualList();
        menstrualList.clear();
        menstrualList.add(menstrual);
        Collections.sort(menstrualList, new Comparator() { // from class: com.ido.life.util.-$$Lambda$SPHelper$AuDPSnoZsb_POSiqdQ_Z9PWleKc
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return SPHelper.lambda$saveMenstrualData$0((Menstrual) obj, (Menstrual) obj2);
            }
        });
        SPUtils.put(MENSTRUAL_LIST, GsonUtil.toJson(menstrualList));
    }

    static /* synthetic */ int lambda$saveMenstrualData$0(Menstrual menstrual, Menstrual menstrual2) {
        if (menstrual == null || menstrual2 == null) {
            return 0;
        }
        return DateUtil.getDate(menstrual2.last_menstrual_year, menstrual2.last_menstrual_month, menstrual2.last_menstrual_day).compareTo(DateUtil.getDate(menstrual.last_menstrual_year, menstrual.last_menstrual_month, menstrual.last_menstrual_day));
    }

    public static List<Menstrual> getMenstrualList() {
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) SPUtils.get(MENSTRUAL_LIST, ""), Menstrual.class);
        return listAnalysisJsonObjectToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonObjectToList);
    }

    public static void saveMenstrualCycleData(ServerMenstrual serverMenstrual) {
        SPUtils.put(MENSTRUAL_CYCLE_DATA, serverMenstrual == null ? "" : GsonUtil.toJson(serverMenstrual));
    }

    public static ServerMenstrual getMenstrualCycleData() {
        return (ServerMenstrual) GsonUtil.fromJson((String) SPUtils.get(MENSTRUAL_CYCLE_DATA, ""), ServerMenstrual.class);
    }

    public static void clearMenstrualList() {
        SPUtils.put(MENSTRUAL_LIST, "");
    }

    public static void saveQuickMsgList(List<String> list) {
        if (ListUtils.INSTANCE.isNotEmpty(list)) {
            putStringWithMac(QUICK_MSG_LIST, GsonUtil.toJson(list));
        }
    }

    public static List<String> getQuickMsgList() {
        List listAnalysisJsonArrayToList;
        getStringWithMac(QUICK_MSG_LIST);
        String stringWithMac = getStringWithMac(QUICK_MSG_LIST);
        if (TextUtils.isEmpty(stringWithMac)) {
            listAnalysisJsonArrayToList = Arrays.asList(ResourceUtil.getStringArray(R.array.quick_reply_default_msg));
            saveQuickMsgList(listAnalysisJsonArrayToList);
        } else {
            listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(stringWithMac, String[].class);
        }
        return listAnalysisJsonArrayToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonArrayToList);
    }

    public static void removeQuickMsgList(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(keyWithMac(QUICK_MSG_LIST));
    }

    public static void removeQuickMsgBeanList(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(keyWithMac(QUICK_MSG_BEAN_LIST));
    }

    public static void saveQuickMsgBeanList(List<QuickMsgBean> list) {
        if (ListUtils.INSTANCE.isNotEmpty(list)) {
            putStringWithMac(QUICK_MSG_BEAN_LIST, GsonUtil.toJson(list));
        }
    }

    public static List<QuickMsgBean> getQuickMsgBeanList() {
        List listAnalysisJsonArrayToList;
        getStringWithMac(QUICK_MSG_BEAN_LIST);
        String stringWithMac = getStringWithMac(QUICK_MSG_BEAN_LIST);
        if (TextUtils.isEmpty(stringWithMac)) {
            List listAsList = Arrays.asList(ResourceUtil.getStringArray(R.array.quick_reply_default_msg));
            listAnalysisJsonArrayToList = new ArrayList();
            int i = 0;
            while (i < listAsList.size()) {
                int i2 = i + 1;
                listAnalysisJsonArrayToList.add(new QuickMsgBean(i2, (String) listAsList.get(i)));
                i = i2;
            }
            saveQuickMsgBeanList(listAnalysisJsonArrayToList);
        } else {
            listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(stringWithMac, QuickMsgBean[].class);
        }
        return listAnalysisJsonArrayToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonArrayToList);
    }

    public static void saveDeviceSyncTime(long j) {
        SPUtils.put(SYNC_TIME, Long.valueOf(j));
    }

    public static long getDeviceSyncTime() {
        return ((Long) SPUtils.get(SYNC_TIME, 0L)).longValue();
    }

    public static void saveWalkReminder(WalkReminder walkReminder) {
        SPUtils.put(WALK_REMINDER, GsonUtil.toJson(walkReminder));
    }

    public static WalkReminder getWalkReminder() {
        WalkReminder walkReminder = (WalkReminder) GsonUtil.fromJson((String) SPUtils.get(WALK_REMINDER, ""), WalkReminder.class);
        if (walkReminder != null) {
            return walkReminder;
        }
        WalkReminder walkReminder2 = new WalkReminder();
        walkReminder2.setOnOff(0);
        walkReminder2.setWeeks(new boolean[]{true, true, true, true, true, false, false});
        walkReminder2.setStartHour(9);
        walkReminder2.setStartMinute(0);
        walkReminder2.setEndHour(18);
        walkReminder2.setEndMinute(0);
        walkReminder2.setGoalStep(100);
        return walkReminder2;
    }

    public static int getAlarmIntervalMinute() {
        return ((Integer) SPUtils.get(ALARM_INTERVAL_MINUTE, 10)).intValue();
    }

    public static void saveAlarmIntervalMinute(int i) {
        SPUtils.put(ALARM_INTERVAL_MINUTE, Integer.valueOf(i));
    }

    public static int getAlarmRepeatCount() {
        return ((Integer) SPUtils.get(ALARM_REPEAT_COUNT, 1)).intValue();
    }

    public static void saveAlarmRepeatCount(int i) {
        SPUtils.put(ALARM_REPEAT_COUNT, Integer.valueOf(i));
    }

    public static void saveLastRemindLanguageInfo(RemoteLanguage.LanguageInfo languageInfo) {
        SPUtils.put(LAST_REMIND_LANGUAGE_INFO, languageInfo == null ? "" : GsonUtil.toJson(languageInfo));
    }

    public static RemoteLanguage.LanguageInfo getLastRemindLanguageInfo() {
        return (RemoteLanguage.LanguageInfo) GsonUtil.fromJson((String) SPUtils.get(LAST_REMIND_LANGUAGE_INFO, ""), RemoteLanguage.LanguageInfo.class);
    }

    public static void saveHeartRateMode(int i) {
        SPUtils.put(HEART_RATE_MODE, Integer.valueOf(i));
    }

    public static int getHeartRateMode() {
        return ((Integer) SPUtils.get(HEART_RATE_MODE, 153)).intValue();
    }

    public static void saveLastRemindFirmwareVersion(int i) {
        SPUtils.put(LAST_REMIND_FIRMWARE_VERSION, Integer.valueOf(i));
    }

    public static int getLastRemindFirmwareVersion() {
        return ((Integer) SPUtils.get(LAST_REMIND_FIRMWARE_VERSION, 0)).intValue();
    }

    public static void saveLastOtaReminderDate(String str) {
        SPUtils.put(LAST_OTA_REMINDER_DATE, str);
    }

    public static String getLastOtaReminderDate() {
        return (String) SPUtils.get(LAST_OTA_REMINDER_DATE, "");
    }

    public static void saveLastFlashReminderDate(String str) {
        SPUtils.put(LAST_FLASH_REMINDER_DATE, str);
    }

    public static String getLastFlashReminderDate() {
        return (String) SPUtils.get(LAST_FLASH_REMINDER_DATE, "");
    }

    public static void saveAgpsOnlineUpgradeTime(long j) {
        SPUtils.put(AGPS_ONLINE_UPGRADE_TIME, Long.valueOf(j));
    }

    public static long getLastAgpsOnlineUpgradeTime() {
        return ((Long) SPUtils.get(AGPS_ONLINE_UPGRADE_TIME, 0L)).longValue();
    }

    public static void saveAgpsOfflineUpgradeTime(long j) {
        SPUtils.put(AGPS_OFFLINE_UPGRADE_TIME, Long.valueOf(j));
    }

    public static long getLastAgpsOfflineUpgradeTime() {
        return ((Long) SPUtils.get(AGPS_OFFLINE_UPGRADE_TIME, 0L)).longValue();
    }

    public static void saveDeviceInfoList(List<DeviceInfo> list) {
        if (list == null || list.isEmpty()) {
            SPUtils.put(DEVICE_INFO_LIST, "");
        } else {
            SPUtils.put(DEVICE_INFO_LIST, GsonUtil.toJson(list));
        }
    }

    public static List<DeviceInfo> getDeviceInfoList() {
        List<DeviceInfo> listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) SPUtils.get(DEVICE_INFO_LIST, ""), DeviceInfo.class);
        return listAnalysisJsonObjectToList == null ? new ArrayList() : listAnalysisJsonObjectToList;
    }

    public static boolean autoShowHistoryDataPullState() {
        return ((Boolean) SPUtils.get(AUTO_SHOW_HISTORY_SYNC_STATE, false)).booleanValue();
    }

    public static void saveAutoShowHistoryDataPullState(boolean z) {
        SPUtils.put(AUTO_SHOW_HISTORY_SYNC_STATE, Boolean.valueOf(z));
    }

    public static void saveBuiltInDialList(int i, List<MyDialListEntity.DialInfo> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        SPUtils.put(BUILT_IN_DIAL_LIST.concat(String.valueOf(i)), GsonUtil.toJson(list));
    }

    public static List<MyDialListEntity.DialInfo> getBuiltInDialList(int i) {
        List<MyDialListEntity.DialInfo> listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) SPUtils.get(BUILT_IN_DIAL_LIST.concat(String.valueOf(i)), ""), MyDialListEntity.DialInfo.class);
        return listAnalysisJsonObjectToList == null ? new ArrayList() : listAnalysisJsonObjectToList;
    }

    public static void saveUserDevice(BLEDevice bLEDevice) {
        if (bLEDevice == null) {
            return;
        }
        SPUtils.put(USER_DEVICE, GsonUtil.toJson(bLEDevice));
    }

    public static BLEDevice getUserDevice() {
        return (BLEDevice) GsonUtil.fromJson((String) SPUtils.get(USER_DEVICE, ""), BLEDevice.class);
    }

    public static void saveActivatedDevice(BLEDevice bLEDevice) {
        if (bLEDevice == null) {
            return;
        }
        List<BLEDevice> activatedDeviceList = getActivatedDeviceList();
        if (activatedDeviceList == null) {
            activatedDeviceList = new ArrayList();
        }
        if (!activatedDeviceList.isEmpty()) {
            for (BLEDevice bLEDevice2 : activatedDeviceList) {
                if (bLEDevice2 != null && TextUtils.equals(bLEDevice.mDeviceAddress, bLEDevice2.mDeviceAddress)) {
                    return;
                }
            }
        }
        activatedDeviceList.add(bLEDevice);
        SPUtils.put(ACTIVATED_DEVICE_LIST, GsonUtil.toJson(activatedDeviceList));
    }

    public static void removeActivatedDevice(BLEDevice bLEDevice) {
        List<BLEDevice> activatedDeviceList;
        if (bLEDevice == null || TextUtils.isEmpty(bLEDevice.mDeviceAddress) || (activatedDeviceList = getActivatedDeviceList()) == null || activatedDeviceList.isEmpty()) {
            return;
        }
        Iterator<BLEDevice> it = activatedDeviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BLEDevice next = it.next();
            if (next != null && TextUtils.equals(bLEDevice.mDeviceAddress, next.mDeviceAddress)) {
                activatedDeviceList.remove(next);
                break;
            }
        }
        SPUtils.put(ACTIVATED_DEVICE_LIST, GsonUtil.toJson(activatedDeviceList));
    }

    public static List<BLEDevice> getActivatedDeviceList() {
        List<BLEDevice> listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) SPUtils.get(ACTIVATED_DEVICE_LIST, ""), BLEDevice.class);
        return listAnalysisJsonObjectToList == null ? new ArrayList() : listAnalysisJsonObjectToList;
    }

    public static void saveBatteryLogTimestamp(long j) {
        SPUtils.put(TIMESTAMP_OF_BATTERY_LOG, Long.valueOf(j));
    }

    public static long getBatteryLogTimestamp() {
        return ((Long) SPUtils.get(TIMESTAMP_OF_BATTERY_LOG, 0L)).longValue();
    }

    public static void setNoReminderForAlexaAlarmTip(boolean z) {
        SPUtils.put(ALEXA_ALARM_NO_REMINDE, Boolean.valueOf(z));
    }

    public static boolean getNoReminderForAlexaAlarmTip() {
        return ((Boolean) SPUtils.get(ALEXA_ALARM_NO_REMINDE, false)).booleanValue();
    }

    public static void setIsChina(boolean z) {
        SPUtils.put(IS_CHINA, Boolean.valueOf(z));
    }

    public static boolean getIsChina() {
        return ((Boolean) SPUtils.get(IS_CHINA, true)).booleanValue();
    }

    public static String getCurrentCountryCode() {
        return (String) SPUtils.get(CURRENT_COUNTRY_CODE, "");
    }

    public static void setCurrentCountryCode(String str) {
        SPUtils.put(CURRENT_COUNTRY_CODE, str);
    }

    public static void saveOtaCacheLog(String str) {
        List<String> otaCacheLogList = getOtaCacheLogList();
        otaCacheLogList.add(str);
        SPUtils.put(OTA_CACHE_LOG_LIST, GsonUtil.toJson(otaCacheLogList));
    }

    public static List<String> getOtaCacheLogList() {
        List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList((String) SPUtils.get(OTA_CACHE_LOG_LIST, ""), String[].class);
        return listAnalysisJsonArrayToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonArrayToList);
    }

    public static void removeOtaCacheLog(String str, boolean z) {
        List<String> otaCacheLogList = getOtaCacheLogList();
        if (otaCacheLogList.contains(str)) {
            if (z) {
                int size = otaCacheLogList.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    if (TextUtils.equals(otaCacheLogList.get(size), str)) {
                        otaCacheLogList.remove(size);
                        break;
                    }
                    size--;
                }
            } else {
                otaCacheLogList.remove(str);
            }
            SPUtils.put(OTA_CACHE_LOG_LIST, GsonUtil.toJson(otaCacheLogList));
        }
    }

    public static void saveDfuOtaCacheLog(String str) {
        List<String> dfuOtaCacheLogList = getDfuOtaCacheLogList();
        dfuOtaCacheLogList.add(str);
        SPUtils.put(DFU_OTA_CACHE_LOG_LIST, GsonUtil.toJson(dfuOtaCacheLogList));
    }

    public static List<String> getDfuOtaCacheLogList() {
        List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList((String) SPUtils.get(DFU_OTA_CACHE_LOG_LIST, ""), String[].class);
        return listAnalysisJsonArrayToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonArrayToList);
    }

    public static void removeDfuOtaCacheLog(String str, boolean z) {
        List<String> dfuOtaCacheLogList = getDfuOtaCacheLogList();
        if (dfuOtaCacheLogList.contains(str)) {
            if (z) {
                int size = dfuOtaCacheLogList.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    if (TextUtils.equals(dfuOtaCacheLogList.get(size), str)) {
                        dfuOtaCacheLogList.remove(size);
                        break;
                    }
                    size--;
                }
            } else {
                dfuOtaCacheLogList.remove(str);
            }
            SPUtils.put(DFU_OTA_CACHE_LOG_LIST, GsonUtil.toJson(dfuOtaCacheLogList));
        }
    }

    public static void saveFlashCacheLog(String str) {
        List<String> flashCacheLogList = getFlashCacheLogList();
        flashCacheLogList.add(str);
        SPUtils.put(FLASH_CACHE_LOG_LIST, GsonUtil.toJson(flashCacheLogList));
    }

    public static List<String> getFlashCacheLogList() {
        List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList((String) SPUtils.get(FLASH_CACHE_LOG_LIST, ""), String[].class);
        return listAnalysisJsonArrayToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonArrayToList);
    }

    public static void removeFlashCacheLog(String str, boolean z) {
        List<String> flashCacheLogList = getFlashCacheLogList();
        if (flashCacheLogList.contains(str)) {
            if (z) {
                int size = flashCacheLogList.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    if (TextUtils.equals(flashCacheLogList.get(size), str)) {
                        flashCacheLogList.remove(size);
                        break;
                    }
                    size--;
                }
            } else {
                flashCacheLogList.remove(str);
            }
            SPUtils.put(FLASH_CACHE_LOG_LIST, GsonUtil.toJson(flashCacheLogList));
        }
    }

    public static void saveLanguageCacheLog(String str) {
        List<String> flashCacheLogList = getFlashCacheLogList();
        flashCacheLogList.add(str);
        SPUtils.put(LANGUAGE_CACHE_LOG_LIST, GsonUtil.toJson(flashCacheLogList));
    }

    public static List<String> getLanguageCacheLogList() {
        List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList((String) SPUtils.get(LANGUAGE_CACHE_LOG_LIST, ""), String[].class);
        return listAnalysisJsonArrayToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonArrayToList);
    }

    public static void removeLanguageCacheLog(String str, boolean z) {
        List<String> languageCacheLogList = getLanguageCacheLogList();
        if (languageCacheLogList.contains(str)) {
            if (z) {
                int size = languageCacheLogList.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    if (TextUtils.equals(languageCacheLogList.get(size), str)) {
                        languageCacheLogList.remove(size);
                        break;
                    }
                    size--;
                }
            } else {
                languageCacheLogList.remove(str);
            }
            SPUtils.put(LANGUAGE_CACHE_LOG_LIST, GsonUtil.toJson(languageCacheLogList));
        }
    }

    public static void saveDeviceExceptionLog(DeviceExceptionModel deviceExceptionModel) {
        List<DeviceExceptionModel> deviceExceptionLogCache = getDeviceExceptionLogCache();
        deviceExceptionLogCache.add(deviceExceptionModel);
        SPUtils.put(DEVICE_EXCEPTION_LOG_CACHE, GsonUtil.toJson(deviceExceptionLogCache));
    }

    public static List<DeviceExceptionModel> getDeviceExceptionLogCache() {
        List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(getDeviceExceptionLogCacheJson(), DeviceExceptionModel[].class);
        return listAnalysisJsonArrayToList == null ? new ArrayList() : new ArrayList(listAnalysisJsonArrayToList);
    }

    public static String getDeviceExceptionLogCacheJson() {
        return (String) SPUtils.get(DEVICE_EXCEPTION_LOG_CACHE, "");
    }

    public static void clearDeviceExceptionLogCache() {
        SPUtils.put(DEVICE_EXCEPTION_LOG_CACHE, "");
    }

    public static void updateMemberListLoadState(boolean z) {
        SPUtils.put(MEMBER_LIST_LOAD_SUCCESS, Boolean.valueOf(z));
    }

    public static boolean memberListLoadSuccess() {
        return ((Boolean) SPUtils.get(MEMBER_LIST_LOAD_SUCCESS, false)).booleanValue();
    }

    public static void updateUnReadMessageCount(int i) {
        SPUtils.put(UN_READ_MESSAGE_COUNT, Integer.valueOf(i));
    }

    public static int getUnReadMessageCount() {
        return ((Integer) SPUtils.get(UN_READ_MESSAGE_COUNT, 0)).intValue();
    }

    public static List<Integer> getDeviceWorldTimeCityIds() {
        return GsonUtil.analysisJsonArrayToList(getStringWithMac(KEY_CITIES_IN_DEVICE), Integer[].class);
    }

    public static void setDeviceWorldTimeCityIds(List<Integer> list) {
        putJsonWithMac(KEY_CITIES_IN_DEVICE, list);
    }

    public static void removeDeviceWorldTimeCityIds(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(KEY_CITIES_IN_DEVICE + str);
    }

    public static List<WorldTimeCity> getDeviceWorldTimeCity() {
        return GsonUtil.analysisJsonArrayToList(getStringWithMac(KEY_CITIES_IN_DEVICE + LanguageUtil.getSystemLanguage()), WorldTimeCity[].class);
    }

    public static void setDeviceWorldTimeCity(List<WorldTimeCity> list) {
        putJsonWithMac(KEY_CITIES_IN_DEVICE + LanguageUtil.getSystemLanguage(), list);
    }

    public static void removeDeviceWorldTimeCity(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(KEY_CITIES_IN_DEVICE + LanguageUtil.getSystemLanguage() + "_" + str);
    }

    public static String getDeviceWorldTimeCityCacheJson(String str) {
        return (String) SPUtils.get(str, "");
    }

    public static int getMotionResourceVersion() {
        return ((Integer) SPUtils.get(MOTION_RESOURCE_VERSION, -1)).intValue();
    }

    public static void setMotionResourceVersion(int i) {
        SPUtils.put(MOTION_RESOURCE_VERSION, Integer.valueOf(i));
    }

    public static void setQuickMsgReplySwitchStatus(boolean z) {
        SPUtils.put(keyWithMac(QUICK_MSG_REPLY_SWITCH), Boolean.valueOf(z));
    }

    public static boolean isQuickMsgReplySwitchOpened() {
        return ((Boolean) SPUtils.get(keyWithMac(QUICK_MSG_REPLY_SWITCH), false)).booleanValue();
    }

    public static NoisePara getNoiseMode(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = (String) SPUtils.get(NOISE_SWITCH + str, "");
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return (NoisePara) GsonUtil.fromJson(str2, NoisePara.class);
    }

    public static void setNoiseMode(String str, NoisePara noisePara) {
        if (TextUtils.isEmpty(str) || noisePara == null) {
            return;
        }
        SPUtils.put(NOISE_SWITCH + str, GsonUtil.toJson(noisePara));
    }

    public static void removeNoiseMode(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(NOISE_SWITCH + str);
    }

    public static NightTemperatureMonitoringPara getBodyTemperatureMode(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = (String) SPUtils.get(BODY_TEMPERATURE_MODE + str, "");
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return (NightTemperatureMonitoringPara) GsonUtil.fromJson(str2, NightTemperatureMonitoringPara.class);
    }

    public static void setBodyTemperature(String str, NightTemperatureMonitoringPara nightTemperatureMonitoringPara) {
        if (TextUtils.isEmpty(str) || nightTemperatureMonitoringPara == null) {
            return;
        }
        SPUtils.put(BODY_TEMPERATURE_MODE + str, GsonUtil.toJson(nightTemperatureMonitoringPara));
    }

    public static void removeBodyTemperature(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(BODY_TEMPERATURE_MODE + str);
    }

    public static void setDeviceUnreadReminder(DeviceUnreadReminder deviceUnreadReminder) {
        putJsonWithMac(DEVICE_UNREAD_REMINDER, deviceUnreadReminder);
    }

    public static DeviceUnreadReminder getDeviceUnreadReminder() {
        String stringWithMac = getStringWithMac(DEVICE_UNREAD_REMINDER);
        if (TextUtils.isEmpty(stringWithMac)) {
            DeviceUnreadReminder deviceUnreadReminder = new DeviceUnreadReminder();
            deviceUnreadReminder.on_off = 170;
            return deviceUnreadReminder;
        }
        return (DeviceUnreadReminder) GsonUtil.fromJson(stringWithMac, DeviceUnreadReminder.class);
    }

    public static void removeDeviceUnreadReminder(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(DEVICE_UNREAD_REMINDER + str);
    }

    public static WashHandReminder getWashHandReminder() {
        return (WashHandReminder) getObjectWithMac(WASH_HAND_REMINDER, WashHandReminder.class);
    }

    public static void saveWashHandReminder(WashHandReminder washHandReminder) {
        putJsonWithMac(WASH_HAND_REMINDER, washHandReminder);
    }

    public static void removeWashHandReminder(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(WASH_HAND_REMINDER + str);
    }

    public static ScheduleReminderSwitch getScheduleReminderSwitch() {
        ScheduleReminderSwitch scheduleReminderSwitch = (ScheduleReminderSwitch) getObjectWithMac(SCHEDULE_REMINDER_SWITCH, ScheduleReminderSwitch.class);
        if (scheduleReminderSwitch != null) {
            return scheduleReminderSwitch;
        }
        ScheduleReminderSwitch scheduleReminderSwitch2 = new ScheduleReminderSwitch();
        scheduleReminderSwitch2.notify_flag = 1;
        return scheduleReminderSwitch2;
    }

    public static void saveScheduleReminderSwitch(ScheduleReminderSwitch scheduleReminderSwitch) {
        putJsonWithMac(SCHEDULE_REMINDER_SWITCH, scheduleReminderSwitch);
    }

    public static void removeScheduleReminderSwitch(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(SCHEDULE_REMINDER_SWITCH + str);
    }

    public static void saveMotionIconTransStatus(boolean z) {
        putBoolWithMac(MOTION_ICON_TRANS_COMPLETE, Boolean.valueOf(z));
    }

    public static boolean isMotionIconTransComplete() {
        return getBoolWithMac(MOTION_ICON_TRANS_COMPLETE, true);
    }

    public static void removeMotionIconTransStatus(String str) {
        try {
            SPUtils.remove(keyWithMac(MOTION_ICON_TRANS_COMPLETE, str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setBindNewDevice(boolean z) {
        SPUtils.put(BIND_NEW_DEVICE, Boolean.valueOf(z));
    }

    public static boolean isBindNewDevice() {
        return ((Boolean) SPUtils.get(BIND_NEW_DEVICE, false)).booleanValue();
    }

    public static long getDialUpdateDate() {
        return ((Long) SPUtils.get(DIAL_UPDATE_DATE, Long.valueOf(System.currentTimeMillis()))).longValue();
    }

    public static void setDialUpdateDate(long j) {
        SPUtils.put(DIAL_UPDATE_DATE, Long.valueOf(j));
    }

    public static void saveLastSysReminderDate(String str) {
        SPUtils.put(LAST_SYS_REMINDER_DATE, str);
    }

    public static void saveWeatherSunTimeData(WeatherSunTime weatherSunTime) {
        SPUtils.put(WEATHER_SUNTIME, GsonUtil.toJson(weatherSunTime));
    }

    public static void saveTimeOfWeatherSend(long j) {
        SPUtils.put(TIME_TO_SENDWEATHER, Long.valueOf(j));
    }

    public static List<WeatherSunTime> getThreeDayWeatherSunTime() {
        List<WeatherSunTime> listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList((String) SPUtils.get(THREE_DAY_WEATHER_SUNTIME, ""), WeatherSunTime.class);
        return listAnalysisJsonObjectToList == null ? new ArrayList() : listAnalysisJsonObjectToList;
    }

    public static void saveThreeDayWeatherSunTimeData(List<WeatherSunTime> list) {
        SPUtils.put(THREE_DAY_WEATHER_SUNTIME, GsonUtil.toJson(list));
    }

    public static WeatherSunTime getWeatherSunTime() {
        return (WeatherSunTime) GsonUtil.fromJson((String) SPUtils.get(WEATHER_SUNTIME, ""), WeatherSunTime.class);
    }

    public static String getDeviceThirdVersion() {
        return (String) SPUtils.get(DEVICE_THIRD_VERSION, "");
    }

    public static void saveDeviceThirdVersion(String str) {
        SPUtils.put(DEVICE_THIRD_VERSION, str);
    }

    public static long getTimeOfWeatherSend() {
        return ((Long) SPUtils.get(TIME_TO_SENDWEATHER, 0L)).longValue();
    }

    public static void saveFitnessGuidance(FitnessGuidance fitnessGuidance) {
        SPUtils.put(FITNESS_GUIDANCE_REMINDER, GsonUtil.toJson(fitnessGuidance));
    }

    public static FitnessGuidance getFitnessGuidance() {
        FitnessGuidance fitnessGuidance = (FitnessGuidance) GsonUtil.fromJson((String) SPUtils.get(FITNESS_GUIDANCE_REMINDER, ""), FitnessGuidance.class);
        if (fitnessGuidance != null) {
            return fitnessGuidance;
        }
        FitnessGuidance fitnessGuidance2 = new FitnessGuidance();
        fitnessGuidance2.mode = 170;
        fitnessGuidance2.notify_flag = 1;
        fitnessGuidance2.go_mode = 170;
        fitnessGuidance2.setWeeks(new boolean[]{true, true, true, true, true, true, true});
        fitnessGuidance2.start_hour = 9;
        fitnessGuidance2.start_minute = 0;
        fitnessGuidance2.end_hour = 21;
        fitnessGuidance2.end_minute = 0;
        fitnessGuidance2.notify_flag = 1;
        fitnessGuidance2.target_steps = 100;
        return fitnessGuidance2;
    }

    public static void saveSmartHeartRateMode(HeartRateSmartMode heartRateSmartMode) {
        putJsonWithMac(SMART_HEART_RATE_MODE, heartRateSmartMode);
    }

    public static HeartRateSmartMode getSmartHeartRateMode() {
        String stringWithMac = getStringWithMac(SMART_HEART_RATE_MODE);
        if (TextUtils.isEmpty(stringWithMac)) {
            HeartRateSmartMode heartRateSmartMode = new HeartRateSmartMode();
            heartRateSmartMode.mode = 170;
            heartRateSmartMode.notify_flag = 1;
            return heartRateSmartMode;
        }
        return (HeartRateSmartMode) GsonUtil.fromJson(stringWithMac, HeartRateSmartMode.class);
    }

    public static void removeSmartHeartRateMode(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SPUtils.remove(SMART_HEART_RATE_MODE + str);
    }
}