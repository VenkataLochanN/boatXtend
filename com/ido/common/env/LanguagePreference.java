package com.ido.common.env;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ido.common.base.BasePreference;
import com.ido.common.utils.AES256;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class LanguagePreference extends BasePreference {
    public static final String APP_LANGUAGE = "app_language";
    public static final String KEY = "language_region";
    public static final String Language_Region = "region";
    public static final String Language_Version = "lan_version";
    public static final String NAME = "language_";
    private static LanguagePreference mInstance = new LanguagePreference();
    private static String mRegion;

    private LanguagePreference() {
    }

    public static LanguagePreference getInstance(String str) {
        mRegion = str;
        return mInstance;
    }

    public void saveLanguage(Context context, String str, Map<String, String> map) {
        if (context == null || TextUtils.isEmpty(mRegion) || TextUtils.isEmpty(str) || map == null || map.size() == 0) {
            return;
        }
        Set<String> setKeySet = map.keySet();
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(NAME + mRegion, 0).edit();
        editorEdit.clear().commit();
        if (mEncryEnable && !TextUtils.isEmpty(str)) {
            str = AES256.AES256Encode(str, BasePreference.PASSWORD);
        }
        editorEdit.putString(Language_Version, str);
        for (String str2 : setKeySet) {
            if (!TextUtils.isEmpty(str2)) {
                String strAES256Encode = map.get(str2);
                if (mEncryEnable && !TextUtils.isEmpty(strAES256Encode)) {
                    strAES256Encode = AES256.AES256Encode(strAES256Encode, BasePreference.PASSWORD);
                }
                editorEdit.putString(str2, strAES256Encode);
            }
        }
        editorEdit.apply();
    }

    public String getString(Context context, String str) {
        return getString(context, NAME + mRegion, str);
    }

    public String getStringValue(Context context, String str, String str2) {
        return getString(context, NAME + mRegion, str, str2);
    }

    public String getLanguageVersion(Context context) {
        return getString(context, NAME + mRegion, Language_Version, "");
    }

    public String getLanguageVersion(Context context, String str) {
        return getString(context, NAME + mRegion, Language_Version, str);
    }

    public static String getLanguageName(Context context) {
        return getString(context, KEY, APP_LANGUAGE);
    }

    public static void saveLanguageName(Context context, String str) {
        saveString(context, KEY, APP_LANGUAGE, str);
    }

    public void clear(Context context) {
        clear(context, NAME + mRegion);
    }
}