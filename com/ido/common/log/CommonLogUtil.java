package com.ido.common.log;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class CommonLogUtil {
    private static boolean LOG_ENABLE;
    private static boolean SAVE_LOG_ENABLE;
    private static String mLogPath;

    public static void setSaveLogEnable(boolean z) {
        SAVE_LOG_ENABLE = z;
    }

    public static void setLogEnable(boolean z) {
        LOG_ENABLE = z;
    }

    public static void init(String str) {
        LogService.init();
        mLogPath = str;
        mkDirs();
    }

    private static void mkDirs() {
        if (TextUtils.isEmpty(mLogPath)) {
            return;
        }
        try {
            File file = new File(mLogPath);
            if (file.exists()) {
                return;
            }
            file.mkdirs();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void d(String str, String str2) {
        if (validData(str, str2)) {
            Log.i(str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (validData(str, str2)) {
            Log.v(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (validData(str, str2)) {
            Log.i(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (validData(str, str2)) {
            Log.e(str, str2);
        }
    }

    public static void d(String str) {
        if (validData(str)) {
            LogHelpUtil.getMethodNames(new Throwable().getStackTrace());
            Log.d(LogHelpUtil.className, LogHelpUtil.createLog(str));
        }
    }

    public static void i(String str) {
        if (validData(str)) {
            LogHelpUtil.getMethodNames(new Throwable().getStackTrace());
            Log.i(LogHelpUtil.className, LogHelpUtil.createLog(str));
        }
    }

    public static void w(String str) {
        if (validData(str)) {
            LogHelpUtil.getMethodNames(new Throwable().getStackTrace());
            Log.w(LogHelpUtil.className, LogHelpUtil.createLog(str));
        }
    }

    public static void printAndSave(String str, String str2, String str3) {
        if (validData(str2, str3)) {
            Log.i(str2, str3);
        }
        if (SAVE_LOG_ENABLE) {
            LogService.p(str, str2, str3);
        }
    }

    public static void printAndSave(String str, String str2) {
        LogHelpUtil.getMethodNames(new Throwable().getStackTrace());
        if (validData(LogHelpUtil.className, str2)) {
            Log.i(LogHelpUtil.className, str2);
        }
        if (SAVE_LOG_ENABLE) {
            LogService.p(str, LogHelpUtil.className, str2);
        }
    }

    public static void printAndSave(String str) {
        LogHelpUtil.getMethodNames(new Throwable().getStackTrace());
        if (validData(LogHelpUtil.className, str)) {
            Log.i(LogHelpUtil.className, str);
        }
        if (SAVE_LOG_ENABLE) {
            LogService.p(mLogPath, LogHelpUtil.className, str);
        }
    }

    public static void e(String str, String str2, String str3) {
        if (validData(str2, str3)) {
            Log.e(str2, str3);
        }
        if (SAVE_LOG_ENABLE) {
            LogService.e(str, str2, str3);
        }
    }

    private static boolean validData(String... strArr) {
        if (!LOG_ENABLE || strArr == null || strArr.length <= 0) {
            return false;
        }
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
        }
        return true;
    }
}