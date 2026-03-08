package com.ido.common.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.KeyCharacterMap;
import com.ido.common.utils.AES256;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
public class BasePreference {
    public static final String PASSWORD = "A12cDAD5EF90";
    public static boolean mEncryEnable = true;

    protected static void saveString(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        if (mEncryEnable && !TextUtils.isEmpty(str3)) {
            str3 = AES256.AES256Encode(str3, PASSWORD);
        }
        editorEdit.putString(str2, str3);
        editorEdit.apply();
    }

    protected static String getString(Context context, String str, String str2) {
        return getString(context, str, str2, null);
    }

    protected static String getString(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return str3;
        }
        String string = context.getSharedPreferences(str, 0).getString(str2, "");
        return TextUtils.isEmpty(string) ? str3 : mEncryEnable ? AES256.AES256Decrypt(string, PASSWORD) : string;
    }

    protected static void saveLong(Context context, String str, String str2, long j) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.putLong(str2, j);
        editorEdit.apply();
    }

    protected static long getLong(Context context, String str, String str2) {
        return getLong(context, str, str2, 0L);
    }

    protected static long getLong(Context context, String str, String str2, long j) {
        return (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? j : context.getSharedPreferences(str, 0).getLong(str2, j);
    }

    protected static void saveInt(Context context, String str, String str2, int i) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.putInt(str2, i);
        editorEdit.apply();
    }

    protected static int getInt(Context context, String str, String str2) {
        return getInt(context, str, str2, 0);
    }

    protected static int getInt(Context context, String str, String str2, int i) {
        return (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? i : context.getSharedPreferences(str, 0).getInt(str2, i);
    }

    protected static void saveBoolean(Context context, String str, String str2, boolean z) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.putBoolean(str2, z);
        editorEdit.apply();
    }

    protected static boolean getBoolean(Context context, String str, String str2) {
        return getBoolean(context, str, str2, false);
    }

    protected static boolean getBoolean(Context context, String str, String str2, boolean z) {
        return (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? z : context.getSharedPreferences(str, 0).getBoolean(str2, z);
    }

    protected static void saveFloat(Context context, String str, String str2, float f2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.putFloat(str2, f2);
        editorEdit.apply();
    }

    protected static float getFloat(Context context, String str, String str2) {
        return getFloat(context, str, str2, 0.0f);
    }

    protected static float getFloat(Context context, String str, String str2, float f2) {
        return (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? f2 : context.getSharedPreferences(str, 0).getFloat(str2, f2);
    }

    protected static void saveStringSet(Context context, String str, String str2, Set<String> set) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        Set<String> treeSet = new TreeSet<>();
        if (set == null || set.size() <= 0 || !mEncryEnable) {
            treeSet = set;
        } else {
            for (String str3 : set) {
                if (!TextUtils.isEmpty(str3)) {
                    treeSet.add(AES256.AES256Encode(str3, PASSWORD));
                } else {
                    treeSet.add(str3);
                }
            }
        }
        editorEdit.putStringSet(str2, treeSet);
        editorEdit.apply();
    }

    protected static Set<String> getStringSet(Context context, String str, String str2) {
        return getStringSet(context, str, str2, null);
    }

    protected static Set<String> getStringSet(Context context, String str, String str2, Set<String> set) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return set;
        }
        Set<String> stringSet = context.getSharedPreferences(str, 0).getStringSet(str2, null);
        Set<String> treeSet = new TreeSet<>();
        if (!mEncryEnable || stringSet == null || stringSet.size() <= 0) {
            treeSet = stringSet;
        } else {
            for (String str3 : stringSet) {
                if (!TextUtils.isEmpty(str3)) {
                    treeSet.add(AES256.AES256Decrypt(str3, PASSWORD));
                } else {
                    treeSet.add(str3);
                }
            }
        }
        return (stringSet == null || stringSet.size() == 0) ? set : treeSet;
    }

    protected static void saveMulValue(Context context, String str, Map<String, Object> map) throws KeyCharacterMap.UnavailableException {
        if (context == null || TextUtils.isEmpty(str) || map == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        for (String str2 : map.keySet()) {
            Object obj = map.get(str2);
            if (obj instanceof Boolean) {
                editorEdit.putBoolean(str2, ((Boolean) obj).booleanValue());
            } else if (obj instanceof String) {
                if (mEncryEnable && !TextUtils.isEmpty((CharSequence) obj)) {
                    editorEdit.putString(str2, AES256.AES256Encode((String) obj, PASSWORD));
                } else {
                    editorEdit.putString(str2, (String) obj);
                }
            } else if (obj instanceof Integer) {
                editorEdit.putInt(str2, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                editorEdit.putLong(str2, ((Long) obj).longValue());
            } else if (obj instanceof Float) {
                editorEdit.putFloat(str2, ((Float) obj).floatValue());
            } else if (obj instanceof Set) {
                editorEdit.putStringSet(str2, (Set) obj);
            } else {
                throw new KeyCharacterMap.UnavailableException("unvalid param:keyValue");
            }
        }
        editorEdit.apply();
    }

    protected static void remove(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.remove(str2);
        editorEdit.apply();
    }

    protected static void clear(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }
}