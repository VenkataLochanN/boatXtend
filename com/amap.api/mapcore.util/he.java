package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: AMapLogEntity.java */
/* JADX INFO: loaded from: classes.dex */
public class he {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1194a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f1195b = 2;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1196c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1197d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f1198e = System.currentTimeMillis();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f1199f;

    private he(int i, String str, String str2) {
        this.f1196c = str2;
        this.f1197d = i;
        this.f1199f = str;
    }

    public static he a(String str, String str2) {
        return new he(f1194a, str, str2);
    }

    public static he b(String str, String str2) {
        return new he(f1195b, str, str2);
    }

    public int a() {
        return this.f1197d;
    }

    public String b() {
        new JSONObject();
        return this.f1196c;
    }

    public String c() {
        return this.f1199f;
    }

    public String d() {
        return a(this.f1197d);
    }

    public static String a(int i) {
        return i == f1195b ? AuthorizationResponseParser.ERROR : "info";
    }

    public static boolean a(he heVar) {
        return (heVar == null || TextUtils.isEmpty(heVar.b())) ? false : true;
    }

    public static String a(Context context, he heVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("info", heVar.b());
            jSONObject.put("session", heVar.c());
            jSONObject.put("timestamp", heVar.f1198e);
            return jSONObject.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(Context context, List<he> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<he> it = list.iterator();
                    while (it.hasNext()) {
                        String strA = a(context, it.next());
                        if (!TextUtils.isEmpty(strA)) {
                            jSONArray.put(strA);
                        }
                    }
                    return jSONArray.toString();
                }
            } catch (Throwable unused) {
            }
        }
        return "";
    }
}