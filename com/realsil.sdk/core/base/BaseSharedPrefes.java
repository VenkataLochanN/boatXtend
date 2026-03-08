package com.realsil.sdk.core.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import com.realsil.sdk.core.logger.ZLogger;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseSharedPrefes {
    public static PowerManager.WakeLock s;
    public Context mContext;
    public SharedPreferences t;

    public BaseSharedPrefes(Context context) {
        this.mContext = context;
        this.t = PreferenceManager.getDefaultSharedPreferences(this.mContext);
    }

    public BaseSharedPrefes(Context context, String str) {
        this.mContext = context;
        this.t = this.mContext.getSharedPreferences(str, 0);
    }

    public void acquireWakeLock() {
        if (s == null) {
            ZLogger.v(this.mContext.getClass().getCanonicalName());
            PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
            if (powerManager != null) {
                s = powerManager.newWakeLock(6, this.mContext.getClass().getCanonicalName());
                s.acquire();
            }
        }
    }

    public void clear() {
        SharedPreferences.Editor editor = getEditor();
        editor.clear();
        editor.apply();
    }

    public boolean contains(String str) {
        SharedPreferences sharedPreferences = this.t;
        return sharedPreferences != null && sharedPreferences.contains(str);
    }

    public boolean getBoolean(String str, boolean z) {
        SharedPreferences sharedPreferences = this.t;
        return sharedPreferences == null ? z : sharedPreferences.getBoolean(str, z);
    }

    public final SharedPreferences.Editor getEditor() {
        if (this.t == null) {
            this.t = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        }
        return this.t.edit();
    }

    public int getInt(String str, int i) {
        SharedPreferences sharedPreferences = this.t;
        return sharedPreferences == null ? i : sharedPreferences.getInt(str, i);
    }

    public Long getLong(String str, Long l) {
        SharedPreferences sharedPreferences = this.t;
        return sharedPreferences == null ? l : Long.valueOf(sharedPreferences.getLong(str, l.longValue()));
    }

    public SharedPreferences getSharedPreferences() {
        return this.t;
    }

    public String getString(String str, String str2) {
        SharedPreferences sharedPreferences = this.t;
        return sharedPreferences == null ? str2 : sharedPreferences.getString(str, str2);
    }

    public void releaseWakeLock() {
        PowerManager.WakeLock wakeLock = s;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        s.release();
        s = null;
    }

    public void set(String str, int i) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(str, i);
        editor.commit();
    }

    public void set(String str, long j) {
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(str, j);
        editor.apply();
    }

    public void set(String str, String str2) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(str, str2);
        editor.apply();
    }

    public void set(String str, boolean z) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(str, z);
        editor.apply();
    }
}