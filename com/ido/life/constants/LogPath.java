package com.ido.life.constants;

import com.ido.common.IdoApp;
import java.io.File;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public interface LogPath {
    public static final String AGPS_PATH;
    public static final String BLE_INFO_PATH;
    public static final String BLE_SDK_LOG_PATH;
    public static final String BUG_PATH;
    public static final String DIAL_FILE_PATH;
    public static final String FEED_BACK_PATH;
    public static final String GOOGLEFIT_PATH;
    public static final String H5_FILE_PATH;
    public static final String MULTIL_LANGUAGE_SUFFIX = ".txt";
    public static final String NOTIFICATION_PATH;
    public static final String OTA_FILE_PATH;
    public static final String SERVER_PATH;
    public static final String STRAVA_GPX_DIR;
    public static final String THIRD_PLARTORM_PATH;
    public static final String WALLPAPER_DIAL_FILE_PATH;
    public static final String imageDir;
    public static final String APP_ROOT_PATH = ((File) Objects.requireNonNull(IdoApp.getAppContext().getExternalFilesDir(null))).getPath() + "/boatWave";
    public static final String INFO_PATH = APP_ROOT_PATH + "/info";
    public static final String LOG_PATH = APP_ROOT_PATH + "/log";
    public static final String PIC_PATH = APP_ROOT_PATH + "/pic";
    public static final String MULTIL_LANGUAGE_PATH = APP_ROOT_PATH + "/multil_language/";
    public static final String CACHE_PATH = APP_ROOT_PATH + "/cache";
    public static final String LANGUAGE_PATH = APP_ROOT_PATH + "/language";
    public static final String FILE_PATH = APP_ROOT_PATH + "/file/";
    public static final String CRASH_PATH = LOG_PATH + "/crash/";
    public static final String LOCATION_PATH = LOG_PATH + "/location/";
    public static final String GOOGLE_MAP_PATH = LOG_PATH + "/googlemap/";
    public static final String WEATHER_PATH = LOG_PATH + "/weather/";
    public static final String WEATHERLOCATION_PATH = LOG_PATH + "/weather_location/";
    public static final String SYCN_PATH = APP_ROOT_PATH + "/sync/";
    public static final String DEVICE_UPDATE_PATH = LOG_PATH + "/device_update/";
    public static final String APK_PATH = APP_ROOT_PATH + File.separator + "/apk/";

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(APP_ROOT_PATH);
        sb.append("/pictures");
        FEED_BACK_PATH = sb.toString();
        STRAVA_GPX_DIR = APP_ROOT_PATH + "/strava";
        BUG_PATH = APP_ROOT_PATH + "/bug";
        NOTIFICATION_PATH = LOG_PATH + "/notification/";
        BLE_INFO_PATH = APP_ROOT_PATH + "/ble/";
        imageDir = APP_ROOT_PATH + "/DCIM/photo/";
        GOOGLEFIT_PATH = LOG_PATH + "/googlefit/";
        THIRD_PLARTORM_PATH = LOG_PATH + "/thirdplatform/";
        AGPS_PATH = APP_ROOT_PATH + File.separator + "/agps/";
        SERVER_PATH = APP_ROOT_PATH + File.separator + "/server/";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(LOG_PATH);
        sb2.append("/ble_sdk/");
        BLE_SDK_LOG_PATH = sb2.toString();
        OTA_FILE_PATH = APP_ROOT_PATH + File.separator + "ota/";
        H5_FILE_PATH = APP_ROOT_PATH + File.separator + "h5/";
        DIAL_FILE_PATH = APP_ROOT_PATH + File.separator + "dial/";
        StringBuilder sb3 = new StringBuilder();
        sb3.append(DIAL_FILE_PATH);
        sb3.append("wallpaper/");
        WALLPAPER_DIAL_FILE_PATH = sb3.toString();
    }
}