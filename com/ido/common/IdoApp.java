package com.ido.common;

import android.app.Application;
import android.content.Context;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class IdoApp {
    public static void init(AppInitPara appInitPara) {
        IdoConfig.init(appInitPara);
        CommonLogUtil.init(LogPathImpl.getInstance().getLogPath());
    }

    public static void init(Application application) {
        init(new AppInitPara().setAppContext(application.getApplicationContext()).setNormalLogFileDir(LogPathImpl.getInstance().getNormalFilePath()).setMouldLogDirPathList(LogPathImpl.getInstance().getAllLogPath()));
    }

    public static Context getAppContext() {
        return IdoConfig.getAppContext();
    }

    public static void resetContextIfNull(Context context) {
        IdoConfig.resetContextIfNull(context);
    }

    public static String getNormalLogFilePath() {
        return IdoConfig.getNormalLogFilePath();
    }

    public static int getLogFileSaveDays() {
        return IdoConfig.getLogFileSaveDays();
    }

    public static List<String> getAllLogFileDirPathList() {
        return IdoConfig.getAllLogFileDirPathList();
    }

    public static String getString(int i) {
        return getAppContext().getResources().getString(i);
    }

    public static String[] getStringArray(int i) {
        return getAppContext().getResources().getStringArray(i);
    }

    public static String getString(int i, Object... objArr) {
        return getAppContext().getResources().getString(i, objArr);
    }
}