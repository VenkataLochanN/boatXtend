package com.ido.common.log;

import android.content.Context;
import android.text.TextUtils;
import com.ido.common.IdoApp;
import com.ido.common.utils.FileUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LogPathImpl implements LogPath {
    private static LogPathImpl mInstance = new LogPathImpl();
    private static String sRootPath;

    public static LogPath getInstance() {
        return mInstance;
    }

    public static void initLogPath(Context context) {
        if (context == null) {
            return;
        }
        sRootPath = context.getFilesDir().getAbsolutePath() + "/boatWave/";
    }

    @Override // com.ido.common.log.LogPath
    public String getRootPath() {
        if (TextUtils.isEmpty(sRootPath) && IdoApp.getAppContext() != null) {
            sRootPath = IdoApp.getAppContext().getFilesDir().getAbsolutePath() + "/boatWave/";
        }
        return sRootPath;
    }

    @Override // com.ido.common.log.LogPath
    public String getInfoPath() {
        return getRootPath().concat("info/");
    }

    @Override // com.ido.common.log.LogPath
    public String getLogPath() {
        return getRootPath().concat("log/");
    }

    @Override // com.ido.common.log.LogPath
    public String getLogZipNamePath() {
        return FileUtil.getSdcard().concat("/boatWave/%s_log.zip");
    }

    @Override // com.ido.common.log.LogPath
    public String getPicPath() {
        return getRootPath().concat("pic/");
    }

    @Override // com.ido.common.log.LogPath
    public String getLanguagePath() {
        return getRootPath().concat("multi_language/");
    }

    @Override // com.ido.common.log.LogPath
    public String getCrashLogPath() {
        return getLogPath().concat("crash/");
    }

    @Override // com.ido.common.log.LogPath
    public String getCachePath() {
        return getRootPath().concat("cache/");
    }

    @Override // com.ido.common.log.LogPath
    public String getNormalFilePath() {
        return getRootPath().concat("file/");
    }

    @Override // com.ido.common.log.LogPath
    public String getLocationInfoPath() {
        return getRootPath().concat("location/");
    }

    @Override // com.ido.common.log.LogPath
    public String getApkPath() {
        return getRootPath().concat("apk/");
    }

    @Override // com.ido.common.log.LogPath
    public String getBugLogPath() {
        return getLogPath().concat("bug/");
    }

    @Override // com.ido.common.log.LogPath
    public String getCameraPicPath() {
        return getRootPath().concat("DCIM/photo/");
    }

    @Override // com.ido.common.log.LogPath
    public String getServerLogPath() {
        return getLogPath().concat("server/");
    }

    @Override // com.ido.common.log.LogPath
    public String getBleSdkLogPath() {
        return getLogPath().concat("ble_sdk/");
    }

    @Override // com.ido.common.log.LogPath
    public String getOtaFilePath() {
        return getRootPath().concat("ota/");
    }

    @Override // com.ido.common.log.LogPath
    public String getHtmlFilePath() {
        return getRootPath().concat("h5/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDialFilePath() {
        return getRootPath().concat("dial/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDialDefinedFilePath() {
        return getRootPath().concat("dialdefined/");
    }

    @Override // com.ido.common.log.LogPath
    public String getWallpaperDialFilePath() {
        return getRootPath().concat("wallpaper/");
    }

    @Override // com.ido.common.log.LogPath
    public String getWeatherLogPath() {
        return getLogPath().concat("weather/");
    }

    @Override // com.ido.common.log.LogPath
    public String getNotificationLogPath() {
        return getLogPath().concat("notification/");
    }

    @Override // com.ido.common.log.LogPath
    public String getStravaLogPath() {
        return getLogPath().concat("strava/");
    }

    @Override // com.ido.common.log.LogPath
    public String getGoogleFitLogPath() {
        return getLogPath().concat("googlefit/");
    }

    @Override // com.ido.common.log.LogPath
    public String getGoogleMapLogPath() {
        return getLogPath().concat("googlemap/");
    }

    @Override // com.ido.common.log.LogPath
    public String getLanguageFilePath() {
        return getRootPath().concat("language/");
    }

    @Override // com.ido.common.log.LogPath
    public String getSportLogPath() {
        return getLogPath().concat("sport/");
    }

    @Override // com.ido.common.log.LogPath
    public String getGpsLogPath() {
        return getLogPath().concat("gps/");
    }

    @Override // com.ido.common.log.LogPath
    public String getLoginRegisterLogPath() {
        return getLogPath().concat("login_register/");
    }

    @Override // com.ido.common.log.LogPath
    public String getOtaLogPath() {
        return getLogPath().concat("ota/");
    }

    @Override // com.ido.common.log.LogPath
    public String getKeepLiveLogPath() {
        return getLogPath().concat("keep_live/");
    }

    @Override // com.ido.common.log.LogPath
    public String getSleepDataLogPath() {
        return getLogPath().concat("sleep/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDialLogPath() {
        return getLogPath().concat("dial/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDialPicPath() {
        return getRootPath().concat("dialpic/");
    }

    @Override // com.ido.common.log.LogPath
    public String getRemoteLanguageLogPath() {
        return getLogPath().concat("language/");
    }

    @Override // com.ido.common.log.LogPath
    public String getBindLogPath() {
        return getLogPath().concat("bind/");
    }

    @Override // com.ido.common.log.LogPath
    public String getSoPath() {
        return getLogPath().concat("so/");
    }

    @Override // com.ido.common.log.LogPath
    public String getFlashPath() {
        return getLogPath().concat("flash/");
    }

    @Override // com.ido.common.log.LogPath
    public String getAgpsFilePath() {
        return getRootPath().concat("agps/");
    }

    @Override // com.ido.common.log.LogPath
    public String getAgpsLogPath() {
        return getLogPath().concat("agps/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDataBaseUpgradePath() {
        return getLogPath().concat("databaseUpgrade/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDeviceLogPath() {
        return getLogPath().concat("deviceLog/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDeviceRestartLogPath() {
        return getLogPath().concat("restartLog/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDeviceRestartLogZipPath() {
        return getLogPath().concat(LogPath.RESTART_LOG_NAME);
    }

    @Override // com.ido.common.log.LogPath
    public String getAlexaLogPath() {
        return getLogPath().concat("alexa/");
    }

    @Override // com.ido.common.log.LogPath
    public String getConnectLogPath() {
        return getLogPath().concat("connect/");
    }

    @Override // com.ido.common.log.LogPath
    public String getMainQuicklySettingLogPath() {
        return getLogPath().concat("quicklySetting/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDialPicLogPath() {
        return getLogPath().concat("dialpiclog/");
    }

    @Override // com.ido.common.log.LogPath
    public String getDeviceFragmentLogPath() {
        return getLogPath().concat("device_fragment");
    }

    @Override // com.ido.common.log.LogPath
    public List<String> getAllLogPath() {
        return new ArrayList<String>() { // from class: com.ido.common.log.LogPathImpl.1
            {
                add(LogPathImpl.this.getInfoPath());
                add(LogPathImpl.this.getLogPath());
                add(LogPathImpl.this.getPicPath());
                add(LogPathImpl.this.getLanguagePath());
                add(LogPathImpl.this.getCrashLogPath());
                add(LogPathImpl.this.getCachePath());
                add(LogPathImpl.this.getNormalFilePath());
                add(LogPathImpl.this.getLocationInfoPath());
                add(LogPathImpl.this.getApkPath());
                add(LogPathImpl.this.getBugLogPath());
                add(LogPathImpl.this.getConnectLogPath());
                add(LogPathImpl.this.getCameraPicPath());
                add(LogPathImpl.this.getServerLogPath());
                add(LogPathImpl.this.getBleSdkLogPath());
                add(LogPathImpl.this.getOtaFilePath());
                add(LogPathImpl.this.getHtmlFilePath());
                add(LogPathImpl.this.getDialFilePath());
                add(LogPathImpl.this.getWallpaperDialFilePath());
                add(LogPathImpl.this.getWeatherLogPath());
                add(LogPathImpl.this.getNotificationLogPath());
                add(LogPathImpl.this.getStravaLogPath());
                add(LogPathImpl.this.getGoogleFitLogPath());
                add(LogPathImpl.this.getGoogleMapLogPath());
                add(LogPathImpl.this.getLanguageFilePath());
                add(LogPathImpl.this.getSportLogPath());
                add(LogPathImpl.this.getLoginRegisterLogPath());
                add(LogPathImpl.this.getOtaLogPath());
                add(LogPathImpl.this.getKeepLiveLogPath());
                add(LogPathImpl.this.getSleepDataLogPath());
                add(LogPathImpl.this.getDialLogPath());
                add(LogPathImpl.this.getRemoteLanguageLogPath());
                add(LogPathImpl.this.getBindLogPath());
                add(LogPathImpl.this.getSoPath());
                add(LogPathImpl.this.getFlashPath());
                add(LogPathImpl.this.getAgpsFilePath());
                add(LogPathImpl.this.getAgpsLogPath());
                add(LogPathImpl.this.getDataBaseUpgradePath());
                add(LogPathImpl.this.getDeviceLogPath());
                add(LogPathImpl.this.getDeviceRestartLogPath());
                add(LogPathImpl.this.getAlexaLogPath());
            }
        };
    }
}