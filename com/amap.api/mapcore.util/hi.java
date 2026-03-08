package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: SPConfigUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class hi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private gs f1225a;

    /* JADX INFO: compiled from: SPConfigUtil.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Map<String, hi> f1226a = new HashMap();
    }

    public static hi a(gs gsVar) {
        if (gsVar == null || TextUtils.isEmpty(gsVar.a())) {
            return null;
        }
        if (a.f1226a.get(gsVar.a()) == null) {
            a.f1226a.put(gsVar.a(), new hi(gsVar));
        }
        return a.f1226a.get(gsVar.a());
    }

    public hi(gs gsVar) {
        this.f1225a = gsVar;
    }

    public String a(Context context, String str, String str2, String str3) {
        gs gsVar;
        if (context == null || (gsVar = this.f1225a) == null || TextUtils.isEmpty(gsVar.a())) {
            return null;
        }
        List<b> listA = b.a(a(context, this.f1225a.a(), str3));
        if (listA == null || listA.size() == 0) {
            return "";
        }
        for (int i = 0; i < listA.size(); i++) {
            b bVar = listA.get(i);
            if (bVar.a(str, str2)) {
                return bVar.f1229c;
            }
        }
        return null;
    }

    public void a(Context context, String str, String str2, String str3, String str4) {
        gs gsVar;
        if (context == null || (gsVar = this.f1225a) == null || TextUtils.isEmpty(gsVar.a())) {
            return;
        }
        List<b> listA = b.a(a(context, this.f1225a.a(), str3));
        for (int i = 0; listA != null && i < listA.size(); i++) {
            b bVar = listA.get(i);
            if (bVar.a(str, str2)) {
                bVar.f1229c = str4;
                b(context, this.f1225a.a(), str3, b.a(listA).toString());
                return;
            }
        }
        listA.add(new b(str, str2, str4));
        b(context, this.f1225a.a(), str3, b.a(listA).toString());
    }

    private static void b(Context context, String str, String str2, String str3) {
        if (str3 == null || TextUtils.isEmpty(str)) {
            return;
        }
        c(context, "C7ADB20F22F238708BA5EE26D0401DB9" + gq.b(str), "ik" + str2, str3);
    }

    private static String a(Context context, String str, String str2) {
        return b(context, "C7ADB20F22F238708BA5EE26D0401DB9" + gq.b(str), "ik" + str2);
    }

    private static void c(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return;
        }
        String strG = gt.g(gg.a(gt.a(str3)));
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.putString(str2, strG);
        editorEdit.commit();
    }

    private static String b(Context context, String str, String str2) {
        return (context == null || TextUtils.isEmpty(str2)) ? "" : gt.a(gg.b(gt.e(context.getSharedPreferences(str, 0).getString(str2, ""))));
    }

    /* JADX INFO: compiled from: SPConfigUtil.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1227a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f1228b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f1229c;

        public b(String str, String str2, String str3) {
            this.f1227a = str;
            this.f1228b = str2;
            this.f1229c = str3;
        }

        public boolean a(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                str = this.f1227a;
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = this.f1228b;
            }
            return this.f1227a.equals(str) && this.f1228b.equals(str2);
        }

        public static boolean a(b bVar) {
            return (bVar == null || TextUtils.isEmpty(bVar.f1229c)) ? false : true;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("sdkVersion", this.f1227a);
                jSONObject.put("cpuType", this.f1228b);
                jSONObject.put(FirebaseAnalytics.Param.CONTENT, this.f1229c);
                return jSONObject;
            } catch (Throwable unused) {
                return new JSONObject();
            }
        }

        public static JSONArray a(List<b> list) {
            if (list == null) {
                return new JSONArray();
            }
            JSONArray jSONArray = new JSONArray();
            for (b bVar : list) {
                if (bVar != null && a(bVar)) {
                    jSONArray.put(bVar.a());
                }
            }
            return jSONArray;
        }

        public static b a(JSONObject jSONObject) {
            try {
                return new b(jSONObject.optString("sdkVersion"), jSONObject.optString("cpuType"), jSONObject.optString(FirebaseAnalytics.Param.CONTENT));
            } catch (Throwable unused) {
                return null;
            }
        }

        public static List<b> a(String str) {
            if (TextUtils.isEmpty(str)) {
                return new ArrayList();
            }
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(a(jSONArray.getJSONObject(i)));
                }
                return arrayList;
            } catch (Throwable unused) {
                return new ArrayList();
            }
        }
    }
}