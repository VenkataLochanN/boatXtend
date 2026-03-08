package com.realsil.sdk.core.utility;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import com.realsil.sdk.core.logger.ZLogger;

/* JADX INFO: loaded from: classes3.dex */
public class AccessibilityHelper {
    public static boolean enableAccessibilityService(Context context, String str) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("auto enable :");
            sb.append(str);
            ZLogger.v(sb.toString());
            Settings.Secure.putInt(context.getContentResolver(), "accessibility_enabled", 1);
            Settings.Secure.putString(context.getContentResolver(), "enabled_accessibility_services", str);
            return true;
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
            return false;
        }
    }

    public static boolean isAccessibilitySettingsOn(Context context, String str) {
        int i;
        try {
            i = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), "accessibility_enabled");
        } catch (Settings.SettingNotFoundException e2) {
            e = e2;
            i = 0;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("accessibilityEnabled = ");
            sb.append(i);
            ZLogger.v(sb.toString());
        } catch (Settings.SettingNotFoundException e3) {
            e = e3;
            ZLogger.e("Error finding setting, default audio_accessibility to not found: " + e.getMessage());
        }
        if (i == 1) {
            ZLogger.v("***ACCESSIBILITY IS ENABLED***");
            String string = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
            if (string != null) {
                TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
                simpleStringSplitter.setString(string);
                while (simpleStringSplitter.hasNext()) {
                    String next = simpleStringSplitter.next();
                    if (next.equalsIgnoreCase(str)) {
                        ZLogger.d("We've found the accessibilityService: " + str);
                        return true;
                    }
                    ZLogger.v("-------------- > accessibilityService :: " + next + " " + str);
                }
            }
        } else {
            ZLogger.v("***ACCESSIBILITY IS DISABLED***");
        }
        return false;
    }

    public static void openAccessibilityService(Context context, Class<?> cls) {
        String str = context.getPackageName() + "/" + cls.getCanonicalName();
        if (isAccessibilitySettingsOn(context, str)) {
            ZLogger.d("辅助功能已经开启");
        } else {
            if (enableAccessibilityService(context, str)) {
                return;
            }
            redirect2AccessibilitySettingsActivity(context);
        }
    }

    public static void redirect2AccessibilitySettingsActivity(Context context) {
        ZLogger.d("redirect 2 Settings$AccessibilitySettingsActivity");
        Intent intent = new Intent();
        intent.setAction("android.settings.ACCESSIBILITY_SETTINGS");
        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity"));
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(268435456);
        try {
            if (context.getPackageManager().resolveActivity(intent, 65536) != null) {
                context.startActivity(intent);
            } else {
                ZLogger.d("cannot resolve permission activity");
            }
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
        }
    }
}