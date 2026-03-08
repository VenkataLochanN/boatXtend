package com.ido.alexa;

import android.content.Context;
import android.text.TextUtils;
import com.ido.alexa.log.AlexaLogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaApp {
    private static Context appContext;
    private static boolean isTest;
    private static String mDefaultLanguage;

    public static void init(Context context) {
        initParams(context, AlexaConstant.LANGUAGE_EN_US);
    }

    public static void init(Context context, String str) {
        initParams(context, str);
    }

    private static void initParams(Context context, String str) {
        appContext = context;
        mDefaultLanguage = str;
        AlexaLogUtil.init(appContext);
    }

    public static void setAppContext(Context context) {
        appContext = context;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static String getDefaultLanguage() {
        return TextUtils.isEmpty(mDefaultLanguage) ? AlexaConstant.LANGUAGE_EN_US : mDefaultLanguage;
    }

    public static void setIsTest(boolean z) {
        isTest = z;
    }

    public static boolean isIsTest() {
        return isTest;
    }
}