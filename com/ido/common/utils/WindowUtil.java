package com.ido.common.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.ido.life.module.device.activity.NightLightActivity;

/* JADX INFO: loaded from: classes2.dex */
public class WindowUtil {
    public static Size getWindowSize(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new Size(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static Size getRealWindowSize(Context context) {
        if (context == null) {
            return null;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return getWindowSize(context);
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        return new Size(point.x, point.y);
    }

    public static boolean isHorizontalScreen(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean isVerticalScreen(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static Size getFullScreenSize(Context context) {
        if (context == null) {
            return null;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        return new Size(point.x, point.y);
    }

    public static void setFullScreen(Activity activity) {
        ActionBar actionBar = activity.getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(4871);
    }

    public static void setTurlyFullScreen(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(4102);
    }

    public static void setCameraActivityWindow(AppCompatActivity appCompatActivity) {
        setTurlyFullScreen(appCompatActivity);
        enableKeepScreen(appCompatActivity, true);
        enableShowWhenLocked(appCompatActivity, false);
        setBrightnessForCamera(appCompatActivity, false);
    }

    public static void hideNavigationBar(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(6);
    }

    public static void setScreenOrientationToHorizontal(Activity activity) {
        activity.setRequestedOrientation(8);
    }

    public static void setScreenOrientationToPortrait(Activity activity) {
        activity.setRequestedOrientation(9);
    }

    public static void setScreenOrientationToHorizontalServer(Activity activity) {
        activity.setRequestedOrientation(0);
    }

    public static void setScreenOrientationToPortraitServer(Activity activity) {
        activity.setRequestedOrientation(1);
    }

    public static boolean isAutoRotate(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation", 0) == 1;
    }

    public static void enableAutoRotate(Context context) {
        if (1 != Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation", 0)) {
            Settings.System.putInt(context.getContentResolver(), "accelerometer_rotation", 1);
        }
    }

    public static void enableKeepScreen(Activity activity, boolean z) {
        if (z) {
            activity.getWindow().addFlags(128);
        } else {
            activity.getWindow().clearFlags(128);
        }
    }

    public static void enableShowWhenLocked(Activity activity, boolean z) {
        if (z) {
            activity.getWindow().addFlags(524288);
        } else {
            activity.getWindow().clearFlags(524288);
        }
    }

    public static void setBrightnessForCamera(Activity activity, boolean z) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        if (z) {
            attributes.screenBrightness = 1.0f;
        } else {
            attributes.screenBrightness = -1.0f;
        }
        activity.getWindow().setAttributes(attributes);
    }

    public static void setBrightnessForCameraMin(Activity activity, boolean z) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        if (z) {
            attributes.screenBrightness = 0.0f;
        } else {
            attributes.screenBrightness = -1.0f;
        }
        activity.getWindow().setAttributes(attributes);
    }

    public static void changeAppBrightness(Context context, int i) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (i == -1) {
            attributes.screenBrightness = -1.0f;
        } else {
            if (i <= 0) {
                i = 1;
            }
            attributes.screenBrightness = i / 255.0f;
        }
        window.setAttributes(attributes);
    }

    public static int getSystemBrightness(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), NightLightActivity.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
            return 127;
        }
    }

    public static void showStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5890);
        }
    }

    public static void hideStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    public static boolean isNotchScreen(Context context) {
        if (context == null) {
            return false;
        }
        if (RomUtils.isXiaomi()) {
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
                return ((Integer) clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, "ro.miui.notch", 0)).intValue() == 1;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (RomUtils.isHuawei()) {
            try {
                Class<?> clsLoadClass2 = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                return ((Boolean) clsLoadClass2.getMethod("hasNotchInScreen", new Class[0]).invoke(clsLoadClass2, new Object[0])).booleanValue();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } else if (RomUtils.isVivo()) {
            try {
                Class<?> clsLoadClass3 = context.getClassLoader().loadClass("android.util.FtFeature");
                return ((Boolean) clsLoadClass3.getMethod("isFeatureSupport", Integer.TYPE).invoke(clsLoadClass3, 32)).booleanValue();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        } else {
            if (RomUtils.isOppo()) {
                return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
            }
            if (RomUtils.isMeizu()) {
                try {
                    return ((Boolean) Class.forName("flyme.config.FlymeFeature").getDeclaredField("IS_FRINGE_DEVICE").get(null)).booleanValue();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
        return false;
    }
}