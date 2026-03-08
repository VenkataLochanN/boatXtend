package com.ido.common;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class IdoConfig {
    private static String LOG_PATH_SDCARD_DIR = "";
    private static AppInitPara mAppInitPara;

    IdoConfig() {
    }

    static void init(AppInitPara appInitPara) {
        if (appInitPara == null) {
            throw new RuntimeException("init para can not be null");
        }
        if (appInitPara.getAppContext() == null) {
            throw new RuntimeException("app context can not be null");
        }
        mAppInitPara = appInitPara;
    }

    static Context getAppContext() {
        return mAppInitPara.getAppContext();
    }

    static void resetContextIfNull(Context context) {
        AppInitPara appInitPara = mAppInitPara;
        if (appInitPara == null) {
            init(new AppInitPara().setAppContext(context));
        } else if (appInitPara.getAppContext() == null) {
            mAppInitPara.setAppContext(context);
        }
    }

    static String getNormalLogFilePath() {
        if (LOG_PATH_SDCARD_DIR.equals("")) {
            if (!TextUtils.isEmpty(mAppInitPara.getNormalLogFileDir())) {
                LOG_PATH_SDCARD_DIR = mAppInitPara.getNormalLogFileDir();
            } else if (Environment.getExternalStorageState().equals("mounted")) {
                LOG_PATH_SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Veryfit" + File.separator + "Log" + File.separator + "ble_sdk";
            }
        }
        return LOG_PATH_SDCARD_DIR;
    }

    static int getLogFileSaveDays() {
        if (mAppInitPara.getLogFileSaveDays() <= 0) {
            return 2;
        }
        return mAppInitPara.getLogFileSaveDays();
    }

    static List<String> getAllLogFileDirPathList() {
        ArrayList arrayList = new ArrayList();
        if (mAppInitPara.getMouldLogDirPathList() != null && mAppInitPara.getMouldLogDirPathList().size() > 0) {
            arrayList.addAll(mAppInitPara.getMouldLogDirPathList());
        }
        if (!TextUtils.isEmpty(LOG_PATH_SDCARD_DIR)) {
            arrayList.add(LOG_PATH_SDCARD_DIR);
        }
        return arrayList;
    }
}