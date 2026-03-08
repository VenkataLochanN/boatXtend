package com.ido.life.util.systembar;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public class OSUtils {
    private static final String KEY_DISPLAY = "ro.build.display.id";
    private static final String KEY_EMUI_VERSION_NAME = "ro.build.version.emui";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";

    public static boolean isMIUI() {
        return !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_VERSION_NAME, ""));
    }

    public static boolean isMIUI6Later() {
        String mIUIVersion = getMIUIVersion();
        return !mIUIVersion.isEmpty() && Integer.valueOf(mIUIVersion.substring(1)).intValue() >= 6;
    }

    public static String getMIUIVersion() {
        return isMIUI() ? getSystemProperty(KEY_MIUI_VERSION_NAME, "") : "";
    }

    public static boolean isEMUI() {
        return !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_VERSION_NAME, ""));
    }

    public static String getEMUIVersion() {
        return isEMUI() ? getSystemProperty(KEY_EMUI_VERSION_NAME, "") : "";
    }

    public static boolean isEMUI3_1() {
        String eMUIVersion = getEMUIVersion();
        return "EmotionUI 3".equals(eMUIVersion) || eMUIVersion.contains("EmotionUI_3.1");
    }

    public static boolean isFlymeOS() {
        return getFlymeOSFlag().toLowerCase().contains("flyme");
    }

    public static boolean isFlymeOS4Later() {
        int iIntValue;
        String flymeOSVersion = getFlymeOSVersion();
        if (flymeOSVersion.isEmpty()) {
            return false;
        }
        if (flymeOSVersion.toLowerCase().contains("os")) {
            iIntValue = Integer.valueOf(flymeOSVersion.substring(9, 10)).intValue();
        } else {
            iIntValue = Integer.valueOf(flymeOSVersion.substring(6, 7)).intValue();
        }
        return iIntValue >= 4;
    }

    public static boolean isFlymeOS5() {
        int iIntValue;
        String flymeOSVersion = getFlymeOSVersion();
        if (flymeOSVersion.isEmpty()) {
            return false;
        }
        if (flymeOSVersion.toLowerCase().contains("os")) {
            iIntValue = Integer.valueOf(flymeOSVersion.substring(9, 10)).intValue();
        } else {
            iIntValue = Integer.valueOf(flymeOSVersion.substring(6, 7)).intValue();
        }
        return iIntValue == 5;
    }

    public static String getFlymeOSVersion() {
        return isFlymeOS() ? getSystemProperty(KEY_DISPLAY, "") : "";
    }

    private static String getFlymeOSFlag() {
        return getSystemProperty(KEY_DISPLAY, "");
    }

    private static String getSystemProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }
}