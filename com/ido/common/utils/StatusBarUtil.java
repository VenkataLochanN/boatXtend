package com.ido.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import com.veryfit.multi.nativeprotocol.b;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class StatusBarUtil {
    private static int sStatus_bar_height;

    public static boolean FlymeSetStatusBarLightMode(Window window, boolean z) {
        return false;
    }

    public static int getStatusBarHeight(Context context) {
        int identifier;
        if (context == null) {
            return 0;
        }
        if (sStatus_bar_height <= 0 && (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            sStatus_bar_height = context.getResources().getDimensionPixelSize(identifier);
        }
        if (sStatus_bar_height <= 0) {
            sStatus_bar_height = DipPixelUtil.dip2px(25.0f);
        }
        return sStatus_bar_height;
    }

    public static int StatusBarLightMode(Activity activity) {
        return StatusBarLightMode(activity, true);
    }

    public static int StatusBarLightMode(Activity activity, boolean z) {
        transparencyBar(activity, z);
        if (Build.VERSION.SDK_INT >= 19) {
            if (MIUISetStatusBarLightMode(activity, true, z)) {
                return 1;
            }
            if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
                return 2;
            }
            if (Build.VERSION.SDK_INT >= 23) {
                activity.getWindow().getDecorView().setSystemUiVisibility(z ? b.j5 : 8192);
                return 3;
            }
        }
        return 0;
    }

    public static void transparencyBar(Activity activity, boolean z) {
        int i;
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.clearFlags(67108864);
            if (z) {
                i = 1280;
                window.setStatusBarColor(0);
            } else {
                i = 256;
                window.setStatusBarColor(-1);
            }
            window.getDecorView().setSystemUiVisibility(i);
            window.addFlags(Integer.MIN_VALUE);
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().setFlags(67108864, 67108864);
        }
    }

    public static void StatusBarLightMode(Activity activity, int i) {
        if (i == 1) {
            MIUISetStatusBarLightMode(activity, true, true);
        } else if (i == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), true);
        } else if (i == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(b.j5);
        }
    }

    public static void StatusBarDarkMode(Activity activity, int i) {
        if (i == 1) {
            MIUISetStatusBarLightMode(activity, false, true);
        } else if (i == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), false);
        } else if (i == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(1024);
        }
    }

    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean z, boolean z2) {
        Window window = activity.getWindow();
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                if (z) {
                    method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i));
                }
                try {
                    if (Build.VERSION.SDK_INT < 23) {
                        return true;
                    }
                    activity.getWindow().getDecorView().setSystemUiVisibility(z ? z2 ? b.j5 : 8192 : 0);
                    return true;
                } catch (Exception unused) {
                    return true;
                }
            } catch (Exception unused2) {
            }
        }
        return false;
    }
}