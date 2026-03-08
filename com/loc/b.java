package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: GeoFenceNetManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    aq f4825a;

    public b(Context context) {
        this.f4825a = null;
        try {
            p.a().a(context);
        } catch (Throwable unused) {
        }
        this.f4825a = aq.a();
    }

    private static String a(Context context, String str, Map<String, String> map) {
        byte[] bArrB;
        try {
            HashMap map2 = new HashMap(16);
            ed edVar = new ed();
            map2.clear();
            map2.put("Content-Type", "application/x-www-form-urlencoded");
            map2.put("Connection", "Keep-Alive");
            map2.put("User-Agent", "AMAP_Location_SDK_Android 5.2.0");
            String strA = m.a();
            String strA2 = m.a(context, strA, u.b(map));
            map.put("ts", strA);
            map.put("scode", strA2);
            edVar.b(map);
            edVar.a(map2);
            edVar.a(str);
            edVar.a(s.a(context));
            edVar.a(ej.f5165g);
            edVar.b(ej.f5165g);
            try {
                if (ep.k(context)) {
                    edVar.a(str.replace("http:", "https:"));
                    bArrB = aq.a(edVar);
                } else {
                    bArrB = aq.b(edVar);
                }
                return new String(bArrB, "utf-8");
            } catch (Throwable th) {
                ej.a(th, "GeoFenceNetManager", "post");
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Map<String, String> b(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap map = new HashMap(16);
        map.put("key", k.f(context));
        if (!TextUtils.isEmpty(str)) {
            map.put("keywords", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("types", str2);
        }
        if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str6)) {
            map.put(FirebaseAnalytics.Param.LOCATION, str6 + AppInfo.DELIM + str5);
        }
        if (!TextUtils.isEmpty(str3)) {
            map.put("city", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            map.put("offset", str4);
        }
        if (!TextUtils.isEmpty(str7)) {
            map.put("radius", str7);
        }
        return map;
    }

    public final String a(Context context, String str, String str2) {
        Map<String, String> mapB = b(context, str2, null, null, null, null, null, null);
        mapB.put("extensions", "all");
        mapB.put("subdistrict", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        return a(context, str, mapB);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5) {
        Map<String, String> mapB = b(context, str2, str3, str4, str5, null, null, null);
        mapB.put("children", "1");
        mapB.put("page", "1");
        mapB.put("extensions", "base");
        return a(context, str, mapB);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map<String, String> mapB = b(context, str2, str3, null, str4, str5, str6, str7);
        mapB.put("children", "1");
        mapB.put("page", "1");
        mapB.put("extensions", "base");
        return a(context, str, mapB);
    }
}