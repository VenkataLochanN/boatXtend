package com.ido.common.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.ido.common.IdoApp;
import com.ido.common.constant.LanguageRegion;
import com.ido.common.env.LanguagePreference;
import com.ido.common.log.CommonLogUtil;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageUtil {
    public static boolean isZh(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        CommonLogUtil.d("获取当前语言language=" + language);
        return language.endsWith(LanguageRegion.ZH);
    }

    public static boolean isEn(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        CommonLogUtil.d("获取当前语言language=" + language);
        return language.endsWith("en");
    }

    public static String getLanguageText(int i) {
        return LanguagePreference.getInstance(LanguagePreference.getLanguageName(IdoApp.getAppContext())).getStringValue(IdoApp.getAppContext(), ResourceUtil.getEntryName(i), ResourceUtil.getString(i));
    }

    public static String getSystemLanguage() {
        String language = IdoApp.getAppContext().getResources().getConfiguration().locale.getLanguage();
        if (!LanguageRegion.ZH.equals(language)) {
            return language;
        }
        String country = Locale.getDefault().getCountry();
        return TextUtils.isEmpty(country) ? language : country.toLowerCase();
    }

    public static Context updateResources(Context context, String str) {
        Locale locale;
        try {
            if ("auto".equals(str)) {
                locale = getLocale(context.getResources());
            } else if (str.contains("_")) {
                String[] strArrSplit = TextUtils.split(str, "_");
                locale = new Locale(strArrSplit[0], strArrSplit[1]);
            } else {
                locale = new Locale(str);
            }
            Locale.setDefault(locale);
            Resources resources = context.getResources();
            Configuration configuration = new Configuration(resources.getConfiguration());
            if (Build.VERSION.SDK_INT >= 17) {
                configuration.setLocale(locale);
                return context.createConfigurationContext(configuration);
            }
            configuration.locale = locale;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            return context;
        } catch (Exception e2) {
            e2.printStackTrace();
            return context;
        }
    }

    public static Locale getLocale(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale;
    }

    public static String getLocaleCountry(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return (Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale).getCountry();
    }

    public static boolean hasLowIntervalCountry(String str) {
        return Arrays.asList("44", "33", "49").contains(str);
    }
}