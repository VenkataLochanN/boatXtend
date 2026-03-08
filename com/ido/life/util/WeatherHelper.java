package com.ido.life.util;

import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.WeatherInfo;
import com.ido.ble.protocol.model.WeatherInfoV3;
import com.ido.ble.protocol.model.WeatherSunTime;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.constants.Constants;
import com.ido.life.constants.LanguageCodeHelper;
import com.ido.life.data.cache.AppNameLanguageManager;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.database.model.WeatherEntity;
import com.ido.life.util.AsyncTaskUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class WeatherHelper {
    protected static final int CMD_TIMEOUT = 10000;
    private static WeatherInfo mWeatherInfo;
    private static final String TAG = WeatherHelper.class.getSimpleName();
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static final Runnable mTimeoutRunnable = new Runnable() { // from class: com.ido.life.util.-$$Lambda$WeatherHelper$sw-FsxaB0hnQCzBy7vidhJ_UMEI
        @Override // java.lang.Runnable
        public final void run() {
            WeatherHelper.getLocalLanguage();
        }
    };
    private static final BaseDeviceInfoCallback mICallBack = new BaseDeviceInfoCallback() { // from class: com.ido.life.util.WeatherHelper.3
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetCanDownloadLangInfo(CanDownLangInfo canDownLangInfo) {
            super.onGetCanDownloadLangInfo(canDownLangInfo);
            RemoteLanguageHelper.saveLanguageLog("onGetCanDownloadLangInfo ：" + canDownLangInfo);
            WeatherHelper.mHandler.removeCallbacks(WeatherHelper.mTimeoutRunnable);
            if (canDownLangInfo == null) {
                WeatherHelper.sendCityNameToDevice(WeatherHelper.getLocalLanguage());
            } else {
                WeatherHelper.sendCityNameToDevice(canDownLangInfo.useLang);
            }
        }

        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetCanDownloadLangInfoV3(CanDownLangInfoV3 canDownLangInfoV3) {
            super.onGetCanDownloadLangInfoV3(canDownLangInfoV3);
            RemoteLanguageHelper.saveLanguageLog("onGetCanDownloadLangInfoV3 ：" + canDownLangInfoV3);
            WeatherHelper.mHandler.removeCallbacks(WeatherHelper.mTimeoutRunnable);
            if (canDownLangInfoV3 == null) {
                WeatherHelper.sendCityNameToDevice(WeatherHelper.getLocalLanguage());
            } else {
                WeatherHelper.sendCityNameToDevice(canDownLangInfoV3.use_lang);
            }
        }
    };

    public static boolean isSupportWeather() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && (supportFunctionInfo.weather || supportFunctionInfo.V3_support_set_v3_weather);
    }

    public static void requestWeatherFromServer() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null || (!supportFunctionInfo.weather && !supportFunctionInfo.V3_support_set_v3_weather)) {
            printLog("requestWeatherFromServer，不支持天气功能、或者天气开关未打开，不发送请求");
            return;
        }
        LatLngBean location = SPHelper.getLocation();
        String str = Constants.USA_CODE;
        if (location != null) {
            String addressCountry = getAddressCountry(location.latitude, location.longitude, "en");
            ArrayList arrayList = new ArrayList();
            arrayList.add("86");
            arrayList.add(Constants.USA_CODE);
            arrayList.add("33");
            arrayList.add("49");
            arrayList.add("44");
            arrayList.add(Constants.INDIA_CODE);
            arrayList.add("34");
            arrayList.add("52");
            if (!TextUtils.isEmpty(addressCountry) && arrayList.contains(addressCountry)) {
                str = addressCountry;
            }
        }
        DeviceManager.requestWeatherFromServer(str, new DeviceManager.OnDeviceCallback<WeatherEntity.ServerWeather>() { // from class: com.ido.life.util.WeatherHelper.1
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(WeatherEntity.ServerWeather serverWeather) {
                WeatherHelper.printLog("requestWeatherFromServer onSuccess ：" + GsonUtil.toJson(serverWeather));
                if (serverWeather == null) {
                    WeatherHelper.printLog("requestWeatherFromServer onSuccess ：null");
                    return;
                }
                WeatherInfo weatherInfoServerWeather2WeatherInfo = WeatherHelper.serverWeather2WeatherInfo(serverWeather);
                WeatherSunTime weatherSunTimeServerWeather2WeatherSunTime = WeatherHelper.serverWeather2WeatherSunTime(serverWeather);
                List listSaveThreeDayServerWeather2WeatherSunTime = WeatherHelper.saveThreeDayServerWeather2WeatherSunTime(serverWeather);
                WeatherInfoV3 weatherInfoV3ServerWeather2WeatherV3 = WeatherHelper.serverWeather2WeatherV3(serverWeather);
                WeatherHelper.printLog("requestWeatherFromServer weatherInfo ：" + GsonUtil.toJson(weatherInfoServerWeather2WeatherInfo));
                WeatherHelper.printLog("requestWeatherFromServer serverWeather2WeatherSunTime  ：" + GsonUtil.toJson(weatherSunTimeServerWeather2WeatherSunTime));
                WeatherHelper.printLog("requestWeatherFromServer weatherInfov3 ：" + GsonUtil.toJson(weatherInfoV3ServerWeather2WeatherV3));
                WeatherHelper.printLog("requestWeatherFromServer threeDayWeatherSunTime ：" + GsonUtil.toJson(listSaveThreeDayServerWeather2WeatherSunTime));
                WeatherHelper.sendWeather2Device();
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str2) {
                WeatherHelper.printLog("requestWeatherFromServer onFailed，code = " + i + "，message = " + str2);
            }
        });
    }

    private static String getAddressCountry(double d2, double d3, String str) {
        printLog(d2 + AppInfo.DELIM + d3 + AppInfo.DELIM + str);
        try {
            List<Address> fromLocation = new Geocoder(IdoApp.getAppContext(), new Locale(str)).getFromLocation(d2, d3, 1);
            return (fromLocation == null || fromLocation.size() <= 0) ? "" : fromLocation.get(0).getCountryCode();
        } catch (IOException e2) {
            printLog(d2 + AppInfo.DELIM + d3 + e2.toString());
            e2.printStackTrace();
            return "";
        }
    }

    public static void sendWeather2Device() {
        if (LocalDataManager.getSupportFunctionInfo() == null) {
            return;
        }
        WeatherInfo weatherInfo = SPHelper.getWeatherInfo();
        WeatherSunTime weatherSunTime = SPHelper.getWeatherSunTime();
        SPHelper.getThreeDayWeatherSunTime();
        SPHelper.getWeatherInfoV3();
        printLog("sendWeather2Device，local data ===> " + GsonUtil.toJson(weatherInfo));
        if (weatherInfo == null) {
            requestWeatherFromServer();
            return;
        }
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            return;
        }
        mWeatherInfo = weatherInfo;
        if (LocalDataManager.getSupportFunctionInfo().weather_city || supportFunctionInfo.V3_support_set_v3_weather) {
            sendWeatherNameToDevice();
            return;
        }
        if (BLEManager.isConnected()) {
            if (weatherSunTime != null && supportFunctionInfo.V3_support_set_weather_sun_time) {
                printLog("sendWeather2Device，设置日出日落到设备 ===> " + GsonUtil.toJson(weatherSunTime));
                BLEManager.setWeatherSunTime(weatherSunTime);
            }
            updateWeatherSetTime();
            BLEManager.setWeatherData(mWeatherInfo);
        }
    }

    private static void sendWeatherNameToDevice() {
        BLEManager.unregisterGetDeviceInfoCallBack(mICallBack);
        BLEManager.registerGetDeviceInfoCallBack(mICallBack);
        getDeviceLanguageInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getAddress(double d2, double d3, String str) {
        printLog(d2 + AppInfo.DELIM + d3 + AppInfo.DELIM + str);
        try {
            List<Address> fromLocation = new Geocoder(IdoApp.getAppContext(), new Locale(str)).getFromLocation(d2, d3, 1);
            return (fromLocation == null || fromLocation.size() <= 0) ? "" : fromLocation.get(0).getLocality();
        } catch (IOException e2) {
            printLog(d2 + AppInfo.DELIM + d3 + e2.toString());
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WeatherInfo serverWeather2WeatherInfo(WeatherEntity.ServerWeather serverWeather) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.humidity = serverWeather.getHumidity();
        weatherInfo.type = serverWeather.getType();
        weatherInfo.max_temp = Math.max(serverWeather.getMaxTemperature(), serverWeather.getTemperature());
        weatherInfo.min_temp = Math.min(serverWeather.getMinTemperature(), serverWeather.getTemperature());
        weatherInfo.temp = serverWeather.getTemperature();
        weatherInfo.future = new WeatherInfo.WeatherFutureInfo[3];
        for (int i = 0; i < weatherInfo.future.length; i++) {
            List<WeatherEntity.ServerWeather.FutureWeatherInfo> futureWeatherInfo = serverWeather.getFutureWeatherInfo();
            if (futureWeatherInfo != null && i < futureWeatherInfo.size()) {
                WeatherEntity.ServerWeather.FutureWeatherInfo futureWeatherInfo2 = futureWeatherInfo.get(i);
                WeatherInfo.WeatherFutureInfo weatherFutureInfo = new WeatherInfo.WeatherFutureInfo();
                weatherFutureInfo.max_temp = futureWeatherInfo2.getMaxTemperature();
                weatherFutureInfo.min_temp = futureWeatherInfo2.getMinTemperature();
                weatherFutureInfo.type = futureWeatherInfo2.getType();
                weatherInfo.future[i] = weatherFutureInfo;
            }
        }
        try {
            WeatherEntity.ServerWeather.WeatherQualityInfo weatherQualityInfo = serverWeather.getWeatherQualityInfo();
            if (weatherQualityInfo != null) {
                String airQualityIndex = weatherQualityInfo.getAirQualityIndex();
                if (!TextUtils.isEmpty(airQualityIndex) && TextUtils.isDigitsOnly(airQualityIndex)) {
                    weatherInfo.aqi = Integer.parseInt(airQualityIndex);
                }
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        SPHelper.saveWeatherData(weatherInfo);
        return weatherInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WeatherSunTime serverWeather2WeatherSunTime(WeatherEntity.ServerWeather serverWeather) {
        WeatherEntity.ServerWeather.FutureWeatherInfo futureWeatherInfo;
        WeatherSunTime weatherSunTime = new WeatherSunTime();
        for (int i = 0; i < serverWeather.getFutureWeatherInfo().size(); i++) {
            List<WeatherEntity.ServerWeather.FutureWeatherInfo> futureWeatherInfo2 = serverWeather.getFutureWeatherInfo();
            if (futureWeatherInfo2 != null && i == 0 && (futureWeatherInfo = futureWeatherInfo2.get(i)) != null && !TextUtils.isEmpty(futureWeatherInfo.getSunriseTimeLocal())) {
                weatherSunTime.sunrise_hour = DateUtil.getDateHour(futureWeatherInfo.getSunriseTimeLocal(), "yyyy-MM-dd HH:mm:ss");
                weatherSunTime.sunrise_min = DateUtil.getDateMinute(futureWeatherInfo.getSunriseTimeLocal(), "yyyy-MM-dd HH:mm:ss");
                weatherSunTime.sunset_hour = DateUtil.getDateHour(futureWeatherInfo.getSunsetTimeLocal(), "yyyy-MM-dd HH:mm:ss");
                weatherSunTime.sunset_min = DateUtil.getDateMinute(futureWeatherInfo.getSunsetTimeLocal(), "yyyy-MM-dd HH:mm:ss");
            }
        }
        SPHelper.saveWeatherSunTimeData(weatherSunTime);
        return weatherSunTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<WeatherSunTime> saveThreeDayServerWeather2WeatherSunTime(WeatherEntity.ServerWeather serverWeather) {
        WeatherEntity.ServerWeather.FutureWeatherInfo futureWeatherInfo;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            WeatherSunTime weatherSunTime = new WeatherSunTime();
            List<WeatherEntity.ServerWeather.FutureWeatherInfo> futureWeatherInfo2 = serverWeather.getFutureWeatherInfo();
            if (futureWeatherInfo2 != null && (futureWeatherInfo = futureWeatherInfo2.get(i)) != null && !TextUtils.isEmpty(futureWeatherInfo.getSunriseTimeLocal())) {
                weatherSunTime.sunrise_hour = DateUtil.getDateHour(futureWeatherInfo.getSunriseTimeLocal(), "yyyy-MM-dd HH:mm:ss");
                weatherSunTime.sunrise_min = DateUtil.getDateMinute(futureWeatherInfo.getSunriseTimeLocal(), "yyyy-MM-dd HH:mm:ss");
                weatherSunTime.sunset_hour = DateUtil.getDateHour(futureWeatherInfo.getSunsetTimeLocal(), "yyyy-MM-dd HH:mm:ss");
                weatherSunTime.sunset_min = DateUtil.getDateMinute(futureWeatherInfo.getSunsetTimeLocal(), "yyyy-MM-dd HH:mm:ss");
            }
            arrayList.add(weatherSunTime);
        }
        SPHelper.saveThreeDayWeatherSunTimeData(arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WeatherInfoV3 serverWeather2WeatherV3(WeatherEntity.ServerWeather serverWeather) {
        WeatherInfoV3 weatherInfoV3 = new WeatherInfoV3();
        if (serverWeather == null) {
            return weatherInfoV3;
        }
        Date date = new Date();
        weatherInfoV3.version = 1;
        weatherInfoV3.month = DateUtil.getCurrentMonth();
        weatherInfoV3.day = DateUtil.getCurrentDay();
        weatherInfoV3.hour = DateUtil.getCurrentHour();
        weatherInfoV3.min = DateUtil.getCurrentMin();
        weatherInfoV3.sec = DateUtil.getCurrentSecond();
        weatherInfoV3.week = DateUtil.getDataWeekDay(date);
        weatherInfoV3.sunrise_hour = DateUtil.getDateHour(serverWeather.getSunriseTimeLocal(), "yyyy-MM-dd HH:mm:ss");
        weatherInfoV3.sunrise_min = DateUtil.getDateMinute(serverWeather.getSunriseTimeLocal(), "yyyy-MM-dd HH:mm:ss");
        weatherInfoV3.sunset_hour = DateUtil.getDateHour(serverWeather.getSunsetTimeLocal(), "yyyy-MM-dd HH:mm:ss");
        weatherInfoV3.sunset_min = DateUtil.getDateMinute(serverWeather.getSunsetTimeLocal(), "yyyy-MM-dd HH:mm:ss");
        weatherInfoV3.wind_speed = serverWeather.getWindSpeed();
        weatherInfoV3.today_uv_intensity = serverWeather.getUvIndex();
        weatherInfoV3.precipitation_probability = serverWeather.getPrecipChance();
        weatherInfoV3.humidity = serverWeather.getHumidity();
        weatherInfoV3.weather_type = serverWeather.getType();
        weatherInfoV3.today_max_temp = Math.max(serverWeather.getMaxTemperature(), serverWeather.getTemperature()) + 100;
        weatherInfoV3.today_min_temp = Math.min(serverWeather.getMinTemperature(), serverWeather.getTemperature()) + 100;
        weatherInfoV3.today_tmp = serverWeather.getTemperature() + 100;
        weatherInfoV3.future_items = new ArrayList<>();
        if (serverWeather.getFutureWeatherInfo() != null) {
            for (int i = 0; i < serverWeather.getFutureWeatherInfo().size(); i++) {
                List<WeatherEntity.ServerWeather.FutureWeatherInfo> futureWeatherInfo = serverWeather.getFutureWeatherInfo();
                if (futureWeatherInfo != null && i < futureWeatherInfo.size()) {
                    WeatherEntity.ServerWeather.FutureWeatherInfo futureWeatherInfo2 = futureWeatherInfo.get(i);
                    WeatherInfoV3.Future future = new WeatherInfoV3.Future();
                    future.max_temp = futureWeatherInfo2.getMaxTemperature() + 100;
                    future.min_temp = futureWeatherInfo2.getMinTemperature() + 100;
                    future.weather_type = futureWeatherInfo2.getType();
                    weatherInfoV3.future_items.add(future);
                }
            }
        }
        if (serverWeather.getHour48WeatherInfos() != null) {
            weatherInfoV3.hours_weather_items = new ArrayList<>();
            for (WeatherEntity.ServerWeather.Hour48WeatherInfos hour48WeatherInfos : serverWeather.getHour48WeatherInfos()) {
                WeatherInfoV3.Hour24 hour24 = new WeatherInfoV3.Hour24();
                hour24.weather_type = hour48WeatherInfos.getType();
                hour24.temperature = hour48WeatherInfos.getTemperature() + 100;
                hour24.probability = hour48WeatherInfos.getPrecipChance();
                weatherInfoV3.hours_weather_items.add(hour24);
            }
        }
        try {
            WeatherEntity.ServerWeather.WeatherQualityInfo weatherQualityInfo = serverWeather.getWeatherQualityInfo();
            if (weatherQualityInfo != null) {
                String airQualityIndex = weatherQualityInfo.getAirQualityIndex();
                if (!TextUtils.isEmpty(airQualityIndex) && TextUtils.isDigitsOnly(airQualityIndex)) {
                    weatherInfoV3.air_quality = Integer.parseInt(airQualityIndex);
                }
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        SPHelper.saveWeatherDataV3(weatherInfoV3);
        return weatherInfoV3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getWeatherLogPath(), TAG, str);
    }

    public static void getDeviceLanguageInfo() {
        mHandler.removeCallbacks(mTimeoutRunnable);
        mHandler.postDelayed(mTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        printLog("getDeviceLanguageInfo");
        if (getSupportFunctionInfo().downloadLanguage) {
            BLEManager.getCanDownloadLangInfo();
        } else if (getSupportFunctionInfo().ex_table_main10_v3_get_lang_library) {
            BLEManager.getCanDownloadLangInfoV3();
        } else {
            sendCityNameToDevice(getLocalLanguage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLocalLanguage() {
        if (LocalDataManager.getUnits() != null) {
            return LocalDataManager.getUnits().language;
        }
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendCityNameToDevice(int i) {
        final String languageCodeByDeviceCode = LanguageCodeHelper.getLanguageCodeByDeviceCode(i);
        if (BLEManager.isConnected()) {
            WeatherSunTime weatherSunTime = SPHelper.getWeatherSunTime();
            final List<WeatherSunTime> threeDayWeatherSunTime = SPHelper.getThreeDayWeatherSunTime();
            SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
            if (supportFunctionInfo != null && !supportFunctionInfo.V3_support_set_v3_weather) {
                printLog("sendCityNameToDevice，设置v2天气到设备 ===> " + GsonUtil.toJson(mWeatherInfo));
                updateWeatherSetTime();
                BLEManager.setWeatherData(mWeatherInfo);
            }
            if (supportFunctionInfo != null && weatherSunTime != null && supportFunctionInfo.V3_support_set_weather_sun_time) {
                printLog("sendCityNameToDevice，设置日出日落到设备 ===> " + GsonUtil.toJson(weatherSunTime));
                BLEManager.setWeatherSunTime(weatherSunTime);
            }
            new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.util.WeatherHelper.2
                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public void onPostExecute(Object obj) {
                }

                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public Object doInBackground(String... strArr) {
                    LatLngBean location = SPHelper.getLocation();
                    String address = WeatherHelper.getAddress(location.latitude, location.longitude, languageCodeByDeviceCode);
                    WeatherHelper.printLog("城市名称cityName==" + address);
                    String strConvertChinese2Pinyin = AppNameLanguageManager.convertChinese2Pinyin(address);
                    if (TextUtils.isEmpty(strConvertChinese2Pinyin)) {
                        strConvertChinese2Pinyin = "";
                    }
                    WeatherHelper.printLog("sendCityNameToDevice，local data ===>城市名称 " + strConvertChinese2Pinyin + AppInfo.DELIM + languageCodeByDeviceCode);
                    WeatherInfoV3 weatherInfoV3 = SPHelper.getWeatherInfoV3();
                    SupportFunctionInfo supportFunctionInfo2 = LocalDataManager.getSupportFunctionInfo();
                    if (weatherInfoV3 == null || supportFunctionInfo2 == null || !supportFunctionInfo2.V3_support_set_v3_weather) {
                        WeatherHelper.printLog("sendCityNameToDevice，只设置天气名称到设备");
                        BLEManager.setWeatherCityName(strConvertChinese2Pinyin);
                        return null;
                    }
                    WeatherHelper.printLog("sendCityNameToDevice，设置v3天气到设备 ===> " + GsonUtil.toJson(weatherInfoV3));
                    WeatherHelper.updateWeatherSetTime();
                    weatherInfoV3.city_name = strConvertChinese2Pinyin;
                    if (supportFunctionInfo2.v3_support_set_v3_weatcher_add_sunrise) {
                        WeatherHelper.printLog("sendCityNameToDevice，设置三天日出日落: " + threeDayWeatherSunTime);
                        weatherInfoV3.sunrise_item = new ArrayList<>();
                        for (int i2 = 0; i2 < threeDayWeatherSunTime.size(); i2++) {
                            WeatherInfoV3.SunRiseSet sunRiseSet = new WeatherInfoV3.SunRiseSet();
                            sunRiseSet.sunrise_hour = ((WeatherSunTime) threeDayWeatherSunTime.get(i2)).sunrise_hour;
                            sunRiseSet.sunrise_min = ((WeatherSunTime) threeDayWeatherSunTime.get(i2)).sunrise_min;
                            sunRiseSet.sunset_hour = ((WeatherSunTime) threeDayWeatherSunTime.get(i2)).sunset_hour;
                            sunRiseSet.sunset_min = ((WeatherSunTime) threeDayWeatherSunTime.get(i2)).sunset_min;
                            weatherInfoV3.sunrise_item.add(sunRiseSet);
                        }
                        weatherInfoV3.sunrise_item_num = threeDayWeatherSunTime.size();
                        WeatherHelper.printLog("sendCityNameToDevice，设置3天日出日落到设备 ===> " + weatherInfoV3.sunrise_item.toString());
                    }
                    BLEManager.setWeatherDataV3(weatherInfoV3);
                    return null;
                }
            }).execute("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateWeatherSetTime() {
        SPHelper.saveTimeOfWeatherSend(System.currentTimeMillis());
    }

    public static SupportFunctionInfo getSupportFunctionInfo() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo == null ? new SupportFunctionInfo() : supportFunctionInfo;
    }
}