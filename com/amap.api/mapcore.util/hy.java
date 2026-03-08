package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeCrashHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class hy {

    /* JADX INFO: compiled from: NativeCrashHandler.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static hy f1300a = new hy();
    }

    private static gs a(String str, List<gs> list) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (int i = 0; list != null && i < list.size(); i++) {
            gs gsVar = list.get(i);
            if (gsVar != null) {
                String[] strArrG = gsVar.g();
                for (String str2 : strArrG) {
                    if (!TextUtils.isEmpty(strArrG[i]) && str.contains(str2)) {
                        return gsVar;
                    }
                }
            }
        }
        return null;
    }

    private JSONArray a(String str) {
        if (str == null) {
            str = "";
        }
        String[] strArr = {"AMapPboRenderThread", "GLThread", "AMapGlRenderThread", "AMapThreadUtil", "GNaviMap", "main"};
        JSONArray jSONArray = new JSONArray();
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (thread != null && !str.equals(thread.getName())) {
                for (int i = 0; i < 6; i++) {
                    String str2 = strArr[i];
                    String name = thread.getName();
                    if (((TextUtils.isEmpty(str2) || TextUtils.isEmpty(name) || (!str2.contains(name) && !name.contains(str2))) ? false : true) && a(thread) != null) {
                        jSONArray.put(a(thread));
                    }
                }
            }
        }
        return jSONArray;
    }

    private static JSONObject a(Thread thread) {
        if (thread == null || thread.getStackTrace() == null) {
            return null;
        }
        StackTraceElement[] stackTrace = thread.getStackTrace();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("threadId", thread.getId());
            jSONObject.put("threadName", thread.getName());
            jSONObject.put("threadGroup", thread.getThreadGroup());
            StringBuffer stringBuffer = new StringBuffer();
            for (StackTraceElement stackTraceElement : stackTrace) {
                stringBuffer.append(stackTraceElement);
                stringBuffer.append("<br />");
            }
            jSONObject.put("stacks", stringBuffer.toString());
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public final boolean a(Context context, String str, String str2, List<gs> list, boolean z, gs gsVar) {
        String string = "";
        if (str2 != null) {
            for (Thread thread : Thread.getAllStackTraces().keySet()) {
                if (thread != null && !TextUtils.isEmpty(thread.getName()) && (str2.contains(thread.getName()) || thread.getName().contains(str2))) {
                    StackTraceElement[] stackTrace = thread.getStackTrace();
                    if (stackTrace != null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (StackTraceElement stackTraceElement : stackTrace) {
                            stringBuffer.append("at ");
                            stringBuffer.append(stackTraceElement);
                            stringBuffer.append("<br />");
                        }
                        string = stringBuffer.toString();
                    }
                }
            }
            string = null;
        }
        gs gsVarA = a(string, list);
        if (z && gsVarA == null) {
            return false;
        }
        String str3 = str + "<br />" + string;
        JSONArray jSONArrayA = a(str2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("crashStack", str3);
            jSONObject.put("backStacks", jSONArrayA);
        } catch (Throwable unused) {
        }
        String string2 = jSONObject.toString();
        if (TextUtils.isEmpty(string2)) {
            return false;
        }
        if (!z && gsVarA == null) {
            hn.b(context, gsVar, string2, "NATIVE_APP_CRASH_CLS_NAME", "NATIVE_CRASH_MHD_NAME");
            return true;
        }
        hn.a(context, gsVarA, string2, "NATIVE_CRASH_CLS_NAME", "NATIVE_CRASH_MHD_NAME");
        return true;
    }
}