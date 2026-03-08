package com.ido.common.log;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface LogPath {
    public static final String RESTART_LOG_NAME = "restartLog.zip";
    public static final String TXT_SUFFIX = ".txt";

    String getAgpsFilePath();

    String getAgpsLogPath();

    String getAlexaLogPath();

    List<String> getAllLogPath();

    String getApkPath();

    String getBindLogPath();

    String getBleSdkLogPath();

    String getBugLogPath();

    String getCachePath();

    String getCameraPicPath();

    String getConnectLogPath();

    String getCrashLogPath();

    String getDataBaseUpgradePath();

    String getDeviceFragmentLogPath();

    String getDeviceLogPath();

    String getDeviceRestartLogPath();

    String getDeviceRestartLogZipPath();

    String getDialDefinedFilePath();

    String getDialFilePath();

    String getDialLogPath();

    String getDialPicLogPath();

    String getDialPicPath();

    String getFlashPath();

    String getGoogleFitLogPath();

    String getGoogleMapLogPath();

    String getGpsLogPath();

    String getHtmlFilePath();

    String getInfoPath();

    String getKeepLiveLogPath();

    String getLanguageFilePath();

    String getLanguagePath();

    String getLocationInfoPath();

    String getLogPath();

    String getLogZipNamePath();

    String getLoginRegisterLogPath();

    String getMainQuicklySettingLogPath();

    String getNormalFilePath();

    String getNotificationLogPath();

    String getOtaFilePath();

    String getOtaLogPath();

    String getPicPath();

    String getRemoteLanguageLogPath();

    String getRootPath();

    String getServerLogPath();

    String getSleepDataLogPath();

    String getSoPath();

    String getSportLogPath();

    String getStravaLogPath();

    String getWallpaperDialFilePath();

    String getWeatherLogPath();
}