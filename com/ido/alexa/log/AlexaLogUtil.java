package com.ido.alexa.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaLogUtil {
    private static final int JSON_INDENT = 4;
    private static boolean LOG_ENABLE = false;
    private static boolean SAVE_LOG_ENABLE;
    static String className;
    static int lineNumber;
    static String methodName;

    public static void setSaveLogEnable(boolean z) {
        SAVE_LOG_ENABLE = z;
    }

    public static void setLogEnable(boolean z) {
        LOG_ENABLE = z;
    }

    public static void init(Context context) {
        AlexaLogWriter.init(context);
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

    public static void getMethodNames(StackTraceElement[] stackTraceElementArr) {
        className = stackTraceElementArr[1].getFileName();
        methodName = stackTraceElementArr[1].getMethodName();
        lineNumber = stackTraceElementArr[1].getLineNumber();
    }

    public static String createLog(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append("(");
        stringBuffer.append(className);
        stringBuffer.append(":");
        stringBuffer.append(lineNumber);
        stringBuffer.append(")");
        stringBuffer.append("#");
        stringBuffer.append(methodName);
        stringBuffer.append("]");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String printIfJson(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            int iIndexOf = (str.contains("{") || str.contains("[")) ? str.indexOf("{") : -1;
            if (iIndexOf != -1) {
                stringBuffer.append(str.substring(0, iIndexOf));
                str = str.substring(iIndexOf);
            }
            if (str.startsWith("{")) {
                str = new JSONObject(str).toString(4);
            } else if (str.startsWith("[")) {
                str = new JSONArray(str).toString(4);
            }
        } catch (Exception unused) {
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static void d(String str) {
        if (validData(str)) {
            getMethodNames(new Throwable().getStackTrace());
            Log.d(className, createLog(str));
        }
    }

    public static void printAndSave(String str, String str2, String str3) {
        if (validData(str2, str3)) {
            Log.d(str2, str3);
        }
        if (SAVE_LOG_ENABLE) {
            AlexaLogWriter.p(str, str2, str3);
        }
    }

    public static void printAndSave(String str) {
        getMethodNames(new Throwable().getStackTrace());
        if (validData(str)) {
            Log.d(className, createLog(str));
        }
        if (SAVE_LOG_ENABLE) {
            AlexaLogWriter.p(AlexaLogPathImpl.getInstance().getAlexaPath(), className, str);
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