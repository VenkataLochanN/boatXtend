package com.ido.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.log.CommonLogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class AppUtil {
    public static boolean isGpsOpen(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    public static void openGPS(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        intent.addCategory("android.intent.category.ALTERNATIVE");
        intent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, intent, 0).send();
        } catch (PendingIntent.CanceledException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isInChina() {
        return Locale.getDefault().getCountry().equals("CN");
    }

    public static int getAppMaxMemory() {
        return (int) (Runtime.getRuntime().maxMemory() / 1048576);
    }

    public static String getLanguage(Context context) {
        return context == null ? "" : context.getResources().getConfiguration().locale.getLanguage();
    }

    public static boolean appIsRunning(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (TextUtils.equals(runningAppProcessInfo.processName, context.getPackageName())) {
                return runningAppProcessInfo.importance == 100 && runningAppProcessInfo.importanceReasonCode != 2;
            }
        }
        return true;
    }

    public static Drawable getAppIconByPackageName(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationInfo(str, 0).loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getVersionName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            Context applicationContext = context.getApplicationContext();
            return applicationContext.getResources().getString(applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    public static String getPhoneModel() {
        return Build.MODEL;
    }

    public static String getSystemVersionName() {
        return Build.VERSION.RELEASE;
    }

    public int getSystemVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    public static String getPhoneInfo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("IMEI:" + telephonyManager.getDeviceId());
        stringBuffer.append(",type:" + Build.MODEL);
        stringBuffer.append(",SDK_INT:" + Build.VERSION.SDK_INT);
        stringBuffer.append(",RELEASE:" + Build.VERSION.RELEASE);
        stringBuffer.append(",number:" + telephonyManager.getLine1Number());
        return stringBuffer.toString();
    }

    public static String getPhoneLogInfo(Context context) {
        if (context == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("品牌:" + Build.MANUFACTURER);
        stringBuffer.append("\n设备名称:" + Build.MANUFACTURER + Build.DEVICE);
        StringBuilder sb = new StringBuilder();
        sb.append("\n型号:");
        sb.append(Build.MODEL);
        stringBuffer.append(sb.toString());
        stringBuffer.append("\n版本号:" + Build.DISPLAY);
        stringBuffer.append("\n处理器:" + getCpuInfo()[0]);
        stringBuffer.append("\n运行内存:" + getAvailMemory(context));
        String[] sDTotalSize = getSDTotalSize();
        stringBuffer.append("\n手机存储: 可用空间:" + sDTotalSize[1] + ",总容量:" + sDTotalSize[0] + ",已使用空间:" + sDTotalSize[2]);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n分辨率:");
        sb2.append(displayMetrics.widthPixels);
        sb2.append("*");
        sb2.append(displayMetrics.heightPixels);
        stringBuffer.append(sb2.toString());
        stringBuffer.append("\nandroid版本:" + Build.VERSION.SDK_INT);
        stringBuffer.append("\napp版本:" + getVersionName(context));
        CommonLogUtil.d("" + Build.BOARD);
        CommonLogUtil.d(Build.BOOTLOADER);
        CommonLogUtil.d(Build.BRAND);
        CommonLogUtil.d(Build.DEVICE);
        CommonLogUtil.d(Build.DISPLAY);
        CommonLogUtil.d(Build.FINGERPRINT);
        CommonLogUtil.d(Build.HARDWARE);
        CommonLogUtil.d(Build.ID);
        CommonLogUtil.d(Build.MANUFACTURER);
        CommonLogUtil.d(Build.MODEL);
        CommonLogUtil.d(Build.PRODUCT);
        CommonLogUtil.d(Build.SERIAL);
        CommonLogUtil.d(Build.TAGS);
        CommonLogUtil.d(Build.USER);
        CommonLogUtil.d(Build.CPU_ABI);
        CommonLogUtil.d("" + Build.VERSION.SDK_INT);
        CommonLogUtil.d("" + getCpuInfo()[0] + AppInfo.DELIM + getCpuInfo()[1]);
        CommonLogUtil.d(stringBuffer.toString());
        return stringBuffer.toString();
    }

    private static String[] getCpuInfo() {
        String[] strArr = {"", ""};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            String[] strArrSplit = bufferedReader.readLine().split("\\s+");
            for (int i = 2; i < strArrSplit.length; i++) {
                strArr[0] = strArr[0] + strArrSplit[i] + " ";
            }
            strArr[1] = strArr[1] + bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.d("cpuinfo:" + strArr[0] + " " + strArr[1]);
        return strArr;
    }

    public static String getMemoryInfo(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = statFs.getBlockSize();
        long blockCount = statFs.getBlockCount();
        long availableBlocks = statFs.getAvailableBlocks();
        return "总空间: " + Formatter.formatFileSize(context, blockCount * blockSize) + "\n可用空间: " + Formatter.formatFileSize(context, availableBlocks * blockSize);
    }

    private static String[] getSDTotalSize() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long blockSize = statFs.getBlockSize();
        long blockCount = statFs.getBlockCount();
        long availableBlocks = statFs.getAvailableBlocks();
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setGroupingSize(3);
        long j = (blockSize * blockCount) / 1024;
        if (j >= 1024) {
            sb = new StringBuilder();
            sb.append(decimalFormat.format(j / 1024));
            sb.append("MB");
        } else {
            sb = new StringBuilder();
            sb.append(decimalFormat.format(j));
            sb.append("KB");
        }
        String string = sb.toString();
        long j2 = (blockSize * availableBlocks) / 1024;
        if (j2 >= 1024) {
            sb2 = new StringBuilder();
            sb2.append(decimalFormat.format(j2 / 1024));
            sb2.append("MB");
        } else {
            sb2 = new StringBuilder();
            sb2.append(decimalFormat.format(j2));
            sb2.append("KB");
        }
        String string2 = sb2.toString();
        long j3 = (blockSize * (blockCount - availableBlocks)) / 1024;
        if (j3 >= 1024) {
            sb3 = new StringBuilder();
            sb3.append(decimalFormat.format(j3 / 1024));
            sb3.append("MB");
        } else {
            sb3 = new StringBuilder();
            sb3.append(decimalFormat.format(j3));
            sb3.append("KB");
        }
        return new String[]{string, string2, sb3.toString()};
    }

    public static String getTotalMemory(Context context) {
        long jIntValue = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String line = bufferedReader.readLine();
            String[] strArrSplit = line.split("\\s+");
            for (String str : strArrSplit) {
                Log.i(line, str + "\t");
            }
            jIntValue = Integer.valueOf(strArrSplit[1]).intValue() / 1024;
            bufferedReader.close();
        } catch (IOException unused) {
        }
        CommonLogUtil.d("initial_memory:" + jIntValue);
        return Formatter.formatFileSize(context, jIntValue);
    }

    private static String getAvailMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(context, memoryInfo.availMem);
    }

    public static String getIMEI(Context context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        try {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            return TextUtils.isEmpty(deviceId) ? Settings.System.getString(context.getContentResolver(), "android_id") : deviceId;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void installApp(Context context, String str) {
        File file = new File(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            intent.addFlags(1);
            intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            if (Build.VERSION.SDK_INT >= 26 && !context.getPackageManager().canRequestPackageInstalls()) {
                Intent intent2 = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
                intent2.addFlags(268435456);
                context.startActivity(intent2);
                return;
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            context.startActivity(intent);
        }
    }

    public static boolean isInstallAppByPackageName(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static String getProcessName(Context context, int i) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static boolean isInstallFackbook(Context context) {
        return isInstallAppByPackageName(context, "com.facebook.katana");
    }

    public static boolean isInstallQQ(Context context) {
        return isInstallAppByPackageName(context, "com.tencent.mqq");
    }

    public static boolean isInstallQQ2012(Context context) {
        return isInstallAppByPackageName(context, "com.tencent.mobileqq");
    }

    public static boolean isInstall91ReadBook(Context context) {
        return isInstallAppByPackageName(context, "com.nd.android.pandareader");
    }

    public static boolean isInstallWeChat(Context context) {
        return isInstallAppByPackageName(context, "com.tencent.mm");
    }

    public static boolean isInstallChrome(Context context) {
        return isInstallAppByPackageName(context, "com.android.chrome");
    }

    public static boolean isInstallOpera(Context context) {
        return isInstallAppByPackageName(context, "com.opera.browser");
    }

    public static boolean isInstallFireFox(Context context) {
        return isInstallAppByPackageName(context, "org.mozilla.firefox");
    }

    public static boolean isInstallVeryFit(Context context) {
        return isInstallAppByPackageName(context, "com.veryfit2hr.second");
    }

    public static void jumpSetting(Activity activity) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        }
        activity.startActivity(intent);
    }

    public static boolean isNotificationEnabled(Context context) {
        return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners"));
    }

    public static boolean isServiceRunning(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningServices = activityManager.getRunningServices(300)) == null) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (TextUtils.equals(runningServices.get(i).service.getClassName(), str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAppRunning(Context context, String str) {
        return isProcessRunning(context, getPackageUid(context, str));
    }

    public static int getPackageUid(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
        } catch (Exception unused) {
        }
        return -1;
    }

    public static boolean isProcessRunning(Context context, int i) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(200);
        if (runningServices.size() <= 0) {
            return false;
        }
        Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
        while (it.hasNext()) {
            if (i == it.next().uid) {
                return true;
            }
        }
        return false;
    }

    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.WirelessSettings"));
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    public static void toAPPMarket(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(Uri.parse("market://details?id=" + context.getPackageName()));
            intent.setPackage(context.getPackageName());
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName()));
                intent2.addFlags(268435456);
                if (intent2.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent2);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void toAPPMarket(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(Uri.parse("market://details?id=".concat(str)));
            intent.setPackage(context.getPackageName());
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse("http://play.google.com/store/apps/details?id=".concat(str)));
                intent2.addFlags(268435456);
                if (intent2.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent2);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isHuawei() {
        return "huawei".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isZte() {
        return "zte".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isToGoogleMarket(Context context, String str) {
        try {
            if (!isInstallGooglePlay(context)) {
                return false;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(Uri.parse("market://details?id=".concat(str)));
            intent.setPackage("com.android.vending");
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isInstallGooglePlay(Context context) {
        return isInstallAppByPackageName(context, "com.android.vending");
    }

    public static String getDefaultBrowserList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://"));
        StringBuffer stringBuffer = new StringBuffer();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (listQueryIntentActivities != null) {
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                if (resolveInfo != null && resolveInfo.activityInfo != null) {
                    stringBuffer.append(resolveInfo.activityInfo.packageName + " , ");
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String getAmazonAppName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("com.amazon.identity.auth.device.authorization.MapAuthorizationService");
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        StringBuffer stringBuffer = new StringBuffer();
        if (listQueryIntentServices != null && listQueryIntentServices.size() > 0) {
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (resolveInfo != null && resolveInfo.serviceInfo != null && !TextUtils.isEmpty(resolveInfo.serviceInfo.packageName)) {
                    try {
                        stringBuffer.append(packageManager.getApplicationLabel(packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 128)).toString() + AppInfo.DELIM);
                    } catch (PackageManager.NameNotFoundException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

    public static boolean notificationEnabled(Context context) {
        if (context == null) {
            return false;
        }
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.contains(context.getPackageName());
    }

    public static void toSelfSetting(Context context) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        try {
            if (Build.VERSION.SDK_INT >= 9) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.setAction("android.intent.action.VIEW");
                intent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
            context.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int needReversal() {
        /*
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.lang.String r0 = r0.getLanguage()
            java.lang.String r0 = r0.toLowerCase()
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r1 = r1.getCountry()
            java.lang.String r1 = r1.toLowerCase()
            int r2 = r0.hashCode()
            r3 = 3241(0xca9, float:4.542E-42)
            r4 = 0
            r5 = 3
            r6 = 1
            r7 = 2
            if (r2 == r3) goto L50
            r3 = 3383(0xd37, float:4.74E-42)
            if (r2 == r3) goto L46
            r3 = 3428(0xd64, float:4.804E-42)
            if (r2 == r3) goto L3c
            r3 = 3886(0xf2e, float:5.445E-42)
            if (r2 == r3) goto L31
            goto L5a
        L31:
            java.lang.String r2 = "zh"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5a
            r0 = r4
            goto L5b
        L3c:
            java.lang.String r2 = "ko"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5a
            r0 = r6
            goto L5b
        L46:
            java.lang.String r2 = "ja"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5a
            r0 = r7
            goto L5b
        L50:
            java.lang.String r2 = "en"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5a
            r0 = r5
            goto L5b
        L5a:
            r0 = -1
        L5b:
            if (r0 == 0) goto L6f
            if (r0 == r6) goto L6f
            if (r0 == r7) goto L6f
            if (r0 == r5) goto L64
            return r7
        L64:
            java.lang.String r0 = "us"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L6e
            return r6
        L6e:
            return r7
        L6f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.utils.AppUtil.needReversal():int");
    }
}