package com.baidu.mapsdkvi;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import com.ido.life.module.device.activity.NightLightActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class VDeviceAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static PowerManager.WakeLock f3871a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static BroadcastReceiver f3872b;

    public static String getAppVersion() {
        try {
            return b.a().getPackageManager().getPackageInfo(b.a().getApplicationInfo().packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static long getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) b.a().getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / 1024;
    }

    public static String getCachePath() {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    public static String getCellId() {
        TelephonyManager telephonyManager = (TelephonyManager) b.a().getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (!(cellLocation instanceof GsmCellLocation)) {
            return " ";
        }
        return " " + ((GsmCellLocation) cellLocation).getCid();
    }

    public static int getCurrentNetworkType() {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) b.a().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type != 0) {
            return type != 1 ? 1 : 2;
        }
        return 3;
    }

    public static long getFreeSpace() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return (((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1024;
    }

    public static String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) b.a().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return null;
    }

    public static String getImsi() {
        TelephonyManager telephonyManager = (TelephonyManager) b.a().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getSubscriberId();
        }
        return null;
    }

    public static String getLac() {
        TelephonyManager telephonyManager = (TelephonyManager) b.a().getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (!(cellLocation instanceof GsmCellLocation)) {
            return "";
        }
        return "" + ((GsmCellLocation) cellLocation).getLac();
    }

    public static String getModuleFileName() {
        return b.a().getFilesDir().getAbsolutePath();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0024 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.baidu.mapsdkvi.c getNetworkInfo(int r3) {
        /*
            android.content.Context r0 = com.baidu.mapsdkvi.b.a()
            java.lang.String r1 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            r1 = 2
            r2 = 0
            if (r3 == r1) goto L17
            r1 = 3
            if (r3 == r1) goto L15
            r3 = r2
            goto L1c
        L15:
            r3 = 0
            goto L18
        L17:
            r3 = 1
        L18:
            android.net.NetworkInfo r3 = r0.getNetworkInfo(r3)
        L1c:
            if (r3 == 0) goto L24
            com.baidu.mapsdkvi.c r0 = new com.baidu.mapsdkvi.c
            r0.<init>(r3)
            return r0
        L24:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkvi.VDeviceAPI.getNetworkInfo(int):com.baidu.mapsdkvi.c");
    }

    public static String getOsVersion() {
        return "android";
    }

    public static int getScreenBrightness() {
        int i;
        ContentResolver contentResolver = b.a().getContentResolver();
        try {
            i = Settings.System.getInt(contentResolver, "screen_brightness_mode");
        } catch (Settings.SettingNotFoundException unused) {
            i = 0;
        }
        if (i == 1) {
            return -1;
        }
        try {
            return Settings.System.getInt(contentResolver, NightLightActivity.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException unused2) {
            return -1;
        }
    }

    public static float getScreenDensity() {
        if (b.a() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) b.a().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.density;
    }

    public static int getScreenDensityDpi() {
        if (b.a() == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) b.a().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.densityDpi;
    }

    public static long getSdcardFreeSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1024;
    }

    public static String getSdcardPath() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            return externalStorageDirectory.getAbsolutePath();
        }
        return null;
    }

    public static long getSdcardTotalSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount())) / 1024;
    }

    public static float getSystemMetricsX() {
        if (b.a() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) b.a().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static float getSystemMetricsY() {
        if (b.a() == null) {
            return 0.0f;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) b.a().getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }

    public static long getTotalMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            jIntValue = bufferedReader.readLine() != null ? Integer.valueOf(r3.split("\\s+")[1]).intValue() : 0L;
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return jIntValue;
    }

    public static long getTotalSpace() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return (((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount())) / 1024;
    }

    public static boolean isWifiConnected() {
        NetworkInfo networkInfo = ((ConnectivityManager) b.a().getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isConnected();
    }

    public static void makeCall(String str) {
        b.a().startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    public static native void onNetworkStateChanged();

    public static void openUrl(String str) {
        b.a().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static int sendMMS(String str, String str2, String str3, String str4) {
        if (!PhoneNumberUtils.isWellFormedSmsAddress(str)) {
            return 1;
        }
        try {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(str4)).toString()));
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("address", str);
            intent.putExtra("subject", str2);
            intent.putExtra("sms_body", str3);
            intent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + str4));
            intent.setType(mimeTypeFromExtension);
            b.a().startActivity(intent);
            return 0;
        } catch (Exception unused) {
            return 2;
        }
    }

    public static void sendSMS(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", str2);
        b.a().startActivity(intent);
    }

    public static void setNetworkChangedCallback() {
        unsetNetworkChangedCallback();
        f3872b = new a();
        b.a().registerReceiver(f3872b, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void setScreenAlwaysOn(boolean z) {
        if (z) {
            if (f3871a == null) {
                f3871a = ((PowerManager) b.a().getSystemService("power")).newWakeLock(10, "VDeviceAPI");
            }
            f3871a.acquire();
        } else {
            PowerManager.WakeLock wakeLock = f3871a;
            if (wakeLock == null || !wakeLock.isHeld()) {
                return;
            }
            f3871a.release();
            f3871a = null;
        }
    }

    public static void setupSoftware(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        b.a().startActivity(intent);
    }

    public static void unsetNetworkChangedCallback() {
        if (f3872b != null) {
            b.a().unregisterReceiver(f3872b);
            f3872b = null;
        }
    }
}