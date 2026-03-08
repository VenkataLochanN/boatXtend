package com.ido.life.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.env.LanguagePreference;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.Language;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class LanguageManager {
    private static final String TAG = LanguageManager.class.getSimpleName();

    private static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static void persistenceLanguage(Context context, String str) {
        LanguagePreference.getInstance(LanguagePreference.Language_Region);
        LanguagePreference.saveLanguageName(context, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String toLanguage(java.util.Locale r11) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.LanguageManager.toLanguage(java.util.Locale):java.lang.String");
    }

    public static Context setLanguage(Context context) {
        return setLanguage(context, getLanguage(context));
    }

    public static Context setLanguage(Context context, String str) {
        persistenceLanguage(context, str);
        return LanguageUtil.updateResources(context, str);
    }

    public static String getLanguage(Context context) {
        LanguagePreference.getInstance(LanguagePreference.Language_Region);
        return LanguagePreference.getLanguageName(context);
    }

    public static String getSystemLang(Context context) {
        return context == null ? "" : toLanguage(LanguageUtil.getLocale(context.getResources())).toLowerCase();
    }

    public static String getLanguagePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return LogPathImpl.getInstance().getLanguagePath() + str + ".txt";
    }

    public static List<Language> getSupportLanguageList(SupportFunctionInfo supportFunctionInfo) {
        ArrayList arrayList = new ArrayList();
        if (supportFunctionInfo == null) {
            return arrayList;
        }
        arrayList.clear();
        if (supportFunctionInfo.lang_ch) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_cn), 1));
        }
        if (supportFunctionInfo.lang_eng) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_en), 2));
        }
        if (supportFunctionInfo.lang_japanese) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_ja), 7));
        }
        if (supportFunctionInfo.lang_german) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_de), 4));
        }
        if (supportFunctionInfo.lang_italian) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_it), 5));
        }
        if (supportFunctionInfo.lang_french) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_fr), 3));
        }
        if (supportFunctionInfo.lang_spanish) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_es), 6));
        }
        if (supportFunctionInfo.lang_czech) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_cz), 9));
        }
        if (supportFunctionInfo.ex_lang_hungarian) {
            arrayList.add(new Language("Magyar", 14));
        }
        if (supportFunctionInfo.ex_lang_slovenian) {
            arrayList.add(new Language("Slovenija", 13));
        }
        if (supportFunctionInfo.ex_lang_dutch) {
            arrayList.add(new Language("Nederlands", 12));
        }
        if (supportFunctionInfo.ex_lang_lithuanian) {
            arrayList.add(new Language("Lietuva", 11));
        }
        if (supportFunctionInfo.ex_lang_romanian) {
            arrayList.add(new Language("România", 10));
        }
        if (supportFunctionInfo.ex_lang_russian) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_ru), 15));
        }
        if (supportFunctionInfo.ex_lang_ukrainian) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_uk), 16));
        }
        if (supportFunctionInfo.ex_lang1_slovak) {
            arrayList.add(new Language("Slovenského jazyk", 17));
        }
        if (supportFunctionInfo.ex_lang1_danish) {
            arrayList.add(new Language("Dansk", 18));
        }
        if (supportFunctionInfo.ex_lang2_croatian) {
            arrayList.add(new Language("Hrvatski", 19));
        }
        if (supportFunctionInfo.ex_lang_polish) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_pl), 8));
        }
        if (supportFunctionInfo.ex_lang2_indonesian) {
            arrayList.add(new Language("Indonesia", 20));
        }
        if (supportFunctionInfo.ex_lang2_korean) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_ko), 21));
        }
        if (supportFunctionInfo.ex_lang2_hindi) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_hi), 22));
        }
        if (supportFunctionInfo.ex_lang2_portuguese) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_pt), 23));
        }
        if (supportFunctionInfo.ex_lang3_greek) {
            arrayList.add(new Language(LanguageUtil.getLanguageText(R.string.language_gr), 30));
        }
        if (arrayList.size() > 0) {
            arrayList.add(0, new Language(LanguageUtil.getLanguageText(R.string.device_follow_system_language), 0));
        }
        return arrayList;
    }
}