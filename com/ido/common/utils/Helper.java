package com.ido.common.utils;

import android.app.Activity;
import android.os.Build;
import com.ido.common.utils.statusbar.AndroidMHelper;
import com.ido.common.utils.statusbar.FlymeHelper;
import com.ido.common.utils.statusbar.MIUIHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
class Helper {
    public static final int ANDROID_M = 3;
    public static final int FLYME = 2;
    public static final int MIUI = 1;
    public static final int OTHER = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SystemType {
    }

    Helper() {
    }

    public static int statusBarLightMode(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            if ("xiaomi".equals(getPhoneManufacturer())) {
                if (new MIUIHelper().setStatusBarLightMode(activity, true)) {
                    return Build.VERSION.SDK_INT >= 24 ? 3 : 1;
                }
            } else if ("meizu".equals(getPhoneManufacturer())) {
                if (new FlymeHelper().setStatusBarLightMode(activity, true)) {
                    return 2;
                }
            } else if (new AndroidMHelper().setStatusBarLightMode(activity, true)) {
                return 3;
            }
        }
        return 0;
    }

    private static String getPhoneManufacturer() {
        return Build.MANUFACTURER.toLowerCase();
    }

    public static void statusBarLightMode(Activity activity, int i) {
        statusBarMode(activity, i, true);
    }

    public static void statusBarDarkMode(Activity activity, int i) {
        statusBarMode(activity, i, false);
    }

    private static void statusBarMode(Activity activity, int i, boolean z) {
        if (i == 1) {
            new MIUIHelper().setStatusBarLightMode(activity, z);
        } else if (i == 2) {
            new FlymeHelper().setStatusBarLightMode(activity, z);
        } else if (i == 3) {
            new AndroidMHelper().setStatusBarLightMode(activity, z);
        }
    }
}