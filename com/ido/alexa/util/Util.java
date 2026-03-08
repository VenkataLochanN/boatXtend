package com.ido.alexa.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.ido.alexa.AlexaApp;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class Util {
    public static final String IDENTIFIER = "identifier";
    private static final String PASSWORD = "A12cDAD5EF90";
    private static SharedPreferences mPreferences;

    public static SharedPreferences getPreferences(Context context) {
        if (mPreferences == null) {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return mPreferences;
    }

    public static String getIdentifier() {
        SharedPreferences sharedPreferences = mPreferences;
        return sharedPreferences != null ? sharedPreferences.getString(IDENTIFIER, "") : "";
    }

    public static String getUuid() {
        String str;
        if (TextUtils.isEmpty(getIdentifier())) {
            str = "";
        } else {
            str = getIdentifier() + ".";
        }
        return str + UUID.randomUUID().toString();
    }

    public static void put(String str, Object obj) {
        put(AlexaApp.getAppContext(), str, obj);
    }

    public static Object get(String str, Object obj) {
        return get(AlexaApp.getAppContext(), str, obj);
    }

    public static void put(Context context, String str, Object obj) {
        if (obj == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = getPreferences(context).edit();
        if (obj instanceof String) {
            editorEdit.putString(str, AES256Encrypt.encode((String) obj, "A12cDAD5EF90"));
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        } else if (obj != null) {
            editorEdit.putString(str, AES256Encrypt.encode(obj.toString(), "A12cDAD5EF90"));
        }
        editorEdit.apply();
    }

    public static Object get(Context context, String str, Object obj) {
        if (context == null) {
            return null;
        }
        SharedPreferences preferences = getPreferences(context);
        if (obj instanceof String) {
            return AES256Encrypt.decrypt(preferences.getString(str, (String) obj), "A12cDAD5EF90");
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(preferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(preferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(preferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(preferences.getLong(str, ((Long) obj).longValue()));
        }
        if (obj == null) {
            return null;
        }
        return AES256Encrypt.decrypt(preferences.getString(str, obj.toString()), "A12cDAD5EF90");
    }
}