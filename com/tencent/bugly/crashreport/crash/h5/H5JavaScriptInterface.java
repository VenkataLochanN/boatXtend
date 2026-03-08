package com.tencent.bugly.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.y;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class H5JavaScriptInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static HashSet<Integer> f5548a = new HashSet<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5549b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Thread f5550c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5551d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Map<String, String> f5552e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(CrashReport.WebViewInterface webViewInterface) {
        String string = null;
        if (webViewInterface == null || f5548a.contains(Integer.valueOf(webViewInterface.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        f5548a.add(Integer.valueOf(webViewInterface.hashCode()));
        h5JavaScriptInterface.f5550c = Thread.currentThread();
        Thread thread = h5JavaScriptInterface.f5550c;
        if (thread != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            for (int i = 2; i < thread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = thread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString());
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
            }
            string = sb.toString();
        }
        h5JavaScriptInterface.f5551d = string;
        HashMap map = new HashMap();
        StringBuilder sb2 = new StringBuilder();
        sb2.append((Object) webViewInterface.getContentDescription());
        map.put("[WebView] ContentDescription", sb2.toString());
        h5JavaScriptInterface.f5552e = map;
        return h5JavaScriptInterface;
    }

    private static a a(String str) {
        String string;
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                a aVar = new a();
                aVar.f5553a = jSONObject.getString("projectRoot");
                if (aVar.f5553a == null) {
                    return null;
                }
                aVar.f5554b = jSONObject.getString("context");
                if (aVar.f5554b == null) {
                    return null;
                }
                aVar.f5555c = jSONObject.getString("url");
                if (aVar.f5555c == null) {
                    return null;
                }
                aVar.f5556d = jSONObject.getString("userAgent");
                if (aVar.f5556d == null) {
                    return null;
                }
                aVar.f5557e = jSONObject.getString("language");
                if (aVar.f5557e == null) {
                    return null;
                }
                aVar.f5558f = jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                if (aVar.f5558f == null || aVar.f5558f.equals("null") || (string = jSONObject.getString("stacktrace")) == null) {
                    return null;
                }
                int iIndexOf = string.indexOf(IOUtils.LINE_SEPARATOR_UNIX);
                if (iIndexOf < 0) {
                    y.d("H5 crash stack's format is wrong!", new Object[0]);
                    return null;
                }
                aVar.f5560h = string.substring(iIndexOf + 1);
                aVar.f5559g = string.substring(0, iIndexOf);
                int iIndexOf2 = aVar.f5559g.indexOf(":");
                if (iIndexOf2 > 0) {
                    aVar.f5559g = aVar.f5559g.substring(iIndexOf2 + 1);
                }
                aVar.i = jSONObject.getString("file");
                if (aVar.f5558f == null) {
                    return null;
                }
                aVar.j = jSONObject.getLong("lineNumber");
                if (aVar.j < 0) {
                    return null;
                }
                aVar.k = jSONObject.getLong("columnNumber");
                if (aVar.k < 0) {
                    return null;
                }
                y.a("H5 crash information is following: ", new Object[0]);
                y.a("[projectRoot]: " + aVar.f5553a, new Object[0]);
                y.a("[context]: " + aVar.f5554b, new Object[0]);
                y.a("[url]: " + aVar.f5555c, new Object[0]);
                y.a("[userAgent]: " + aVar.f5556d, new Object[0]);
                y.a("[language]: " + aVar.f5557e, new Object[0]);
                y.a("[name]: " + aVar.f5558f, new Object[0]);
                y.a("[message]: " + aVar.f5559g, new Object[0]);
                y.a("[stacktrace]: \n" + aVar.f5560h, new Object[0]);
                y.a("[file]: " + aVar.i, new Object[0]);
                y.a("[lineNumber]: " + aVar.j, new Object[0]);
                y.a("[columnNumber]: " + aVar.k, new Object[0]);
                return aVar;
            } catch (Throwable th) {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    @JavascriptInterface
    public void printLog(String str) {
        y.d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            y.d("Payload from JS is null.", new Object[0]);
            return;
        }
        String strA = ab.a(str.getBytes());
        String str2 = this.f5549b;
        if (str2 != null && str2.equals(strA)) {
            y.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
            return;
        }
        this.f5549b = strA;
        y.d("Handling JS exception ...", new Object[0]);
        a aVarA = a(str);
        if (aVarA == null) {
            y.d("Failed to parse payload.", new Object[0]);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (aVarA.f5553a != null) {
            linkedHashMap2.put("[JS] projectRoot", aVarA.f5553a);
        }
        if (aVarA.f5554b != null) {
            linkedHashMap2.put("[JS] context", aVarA.f5554b);
        }
        if (aVarA.f5555c != null) {
            linkedHashMap2.put("[JS] url", aVarA.f5555c);
        }
        if (aVarA.f5556d != null) {
            linkedHashMap2.put("[JS] userAgent", aVarA.f5556d);
        }
        if (aVarA.i != null) {
            linkedHashMap2.put("[JS] file", aVarA.i);
        }
        if (aVarA.j != 0) {
            linkedHashMap2.put("[JS] lineNumber", Long.toString(aVarA.j));
        }
        linkedHashMap.putAll(linkedHashMap2);
        linkedHashMap.putAll(this.f5552e);
        linkedHashMap.put("Java Stack", this.f5551d);
        Thread thread = this.f5550c;
        if (aVarA != null) {
            InnerApi.postH5CrashAsync(thread, aVarA.f5558f, aVarA.f5559g, aVarA.f5560h, linkedHashMap);
        }
    }
}