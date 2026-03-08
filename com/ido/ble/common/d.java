package com.ido.ble.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.ido.ble.custom.CustomConfig;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4215b = "A12cDAD5EF90";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected SharedPreferences f4216a;

    protected float a(String str, float f2) {
        return this.f4216a.getFloat(str, f2);
    }

    protected int a(String str, int i) {
        return this.f4216a.getInt(str, i);
    }

    protected long a(String str, long j) {
        return this.f4216a.getLong(str, j);
    }

    protected <T> T a(String str, Class<T> cls) {
        String strA = a(str, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return (T) new Gson().fromJson(strA, (Class) cls);
    }

    protected String a(String str, String str2) {
        return CustomConfig.getConfig().isEncryptedSPData() ? a.a(this.f4216a.getString(str, str2), "A12cDAD5EF90") : this.f4216a.getString(str, str2);
    }

    protected Set<String> a(String str, Set<String> set) {
        return this.f4216a.getStringSet(str, set);
    }

    protected void a() {
        this.f4216a.edit().clear().commit();
    }

    public void a(Context context, String str) {
        Log.i("CommonPreferences", "context  = :" + context + "   spName : " + str);
        this.f4216a = context.getSharedPreferences(str, 0);
    }

    protected boolean a(String str) {
        return this.f4216a.contains(str);
    }

    protected boolean a(String str, boolean z) {
        return this.f4216a.getBoolean(str, z);
    }

    protected void b(String str, float f2) {
        this.f4216a.edit().putFloat(str, f2).commit();
    }

    protected void b(String str, int i) {
        this.f4216a.edit().putInt(str, i).commit();
    }

    protected void b(String str, long j) {
        this.f4216a.edit().putLong(str, j).commit();
    }

    protected void b(String str, String str2) {
        if (CustomConfig.getConfig().isEncryptedSPData()) {
            str2 = a.b(str2, "A12cDAD5EF90");
        }
        this.f4216a.edit().putString(str, str2).commit();
    }

    protected void b(String str, Set<String> set) {
        this.f4216a.edit().putStringSet(str, set).commit();
    }

    protected void b(String str, boolean z) {
        this.f4216a.edit().putBoolean(str, z).commit();
    }

    public boolean b(String str) {
        return this.f4216a.edit().remove(str).commit();
    }
}