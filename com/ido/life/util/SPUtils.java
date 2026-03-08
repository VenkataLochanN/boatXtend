package com.ido.life.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ido.common.IdoApp;
import com.ido.common.base.BasePreference;
import com.ido.common.utils.AES256;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class SPUtils {
    private static String FILE_NAME = "very_fit";

    public static void put(Context context, String str, Object obj) {
        if (obj == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        if (obj instanceof String) {
            if (BasePreference.mEncryEnable && !TextUtils.isEmpty((CharSequence) obj)) {
                editorEdit.putString(str, AES256.AES256Encode((String) obj, BasePreference.PASSWORD));
            } else {
                editorEdit.putString(str, (String) obj);
            }
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        } else if (obj != null) {
            editorEdit.putString(str, obj.toString());
        }
        editorEdit.apply();
    }

    public static void put(String str, Object obj) {
        put(IdoApp.getAppContext(), str, obj);
    }

    public static Object get(String str, Object obj) {
        return get(IdoApp.getAppContext(), str, obj);
    }

    public static Object get(Context context, String str, Object obj) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        if (obj instanceof String) {
            if (BasePreference.mEncryEnable) {
                String string = sharedPreferences.getString(str, "");
                return TextUtils.isEmpty(string) ? obj : AES256.AES256Decrypt(string, BasePreference.PASSWORD);
            }
            return sharedPreferences.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        if (obj == null) {
            return null;
        }
        return sharedPreferences.getString(str, obj.toString());
    }

    @Deprecated
    public static void remove(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        editorEdit.remove(str);
        editorEdit.apply();
    }

    @Deprecated
    public static void remove(String str) {
        remove(IdoApp.getAppContext(), str);
    }

    public static void clear(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    public static boolean contains(Context context, String str) {
        return context.getSharedPreferences(FILE_NAME, 0).contains(str);
    }

    public static Map<String, ?> getAll(Context context) {
        return context.getSharedPreferences(FILE_NAME, 0).getAll();
    }
}