package com.ido.common.log;

import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class LogHelpUtil {
    private static final int JSON_INDENT = 4;
    static String className;
    static int lineNumber;
    static String methodName;

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
        stringBuffer.append(printIfJson(str));
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
}