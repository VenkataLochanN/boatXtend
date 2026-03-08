package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.bumptech.glide.load.Key;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AuthManager.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class gk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1092a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f1093b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static gs f1094c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f1095d = "http://apiinit.amap.com/v3/log/init";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String f1096e;

    private static boolean a(Context context, gs gsVar, boolean z) {
        f1094c = gsVar;
        try {
            String strA = a();
            HashMap map = new HashMap();
            map.put("Content-Type", "application/x-www-form-urlencoded");
            map.put("Accept-Encoding", "gzip");
            map.put("Connection", "Keep-Alive");
            map.put("User-Agent", f1094c.d());
            map.put("X-INFO", gl.b(context));
            map.put("logversion", "2.1");
            map.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", f1094c.b(), f1094c.a()));
            ij ijVarA = ij.a();
            gu guVar = new gu();
            guVar.setProxy(gr.a(context));
            guVar.a(map);
            guVar.b(a(context));
            guVar.a(strA);
            return a(ijVarA.b(guVar));
        } catch (Throwable th) {
            hk.a(th, "Auth", "getAuth");
            return true;
        }
    }

    @Deprecated
    public static synchronized boolean a(Context context, gs gsVar) {
        return a(context, gsVar, false);
    }

    private static String a() {
        return f1095d;
    }

    private static boolean a(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(gt.a(bArr));
            if (jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                int i = jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
                if (i == 1) {
                    f1092a = 1;
                } else if (i == 0) {
                    f1092a = 0;
                }
            }
            if (jSONObject.has("info")) {
                f1093b = jSONObject.getString("info");
            }
            if (f1092a == 0) {
                Log.i("AuthFailure", f1093b);
            }
            return f1092a == 1;
        } catch (JSONException e2) {
            hk.a(e2, "Auth", "lData");
            return false;
        } catch (Throwable th) {
            hk.a(th, "Auth", "lData");
            return false;
        }
    }

    private static Map<String, String> a(Context context) {
        HashMap map = new HashMap();
        try {
            map.put("resType", "json");
            map.put("encode", Key.STRING_CHARSET_NAME);
            String strA = gl.a();
            map.put("ts", strA);
            map.put("key", gi.f(context));
            map.put("scode", gl.a(context, strA, gt.d("resType=json&encode=UTF-8&key=" + gi.f(context))));
        } catch (Throwable th) {
            hk.a(th, "Auth", "gParams");
        }
        return map;
    }
}