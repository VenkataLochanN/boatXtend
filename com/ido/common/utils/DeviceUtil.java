package com.ido.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.ido.ble.event.stat.one.d;
import com.ido.common.log.CommonLogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUtil {
    private static final String MODEL_REGEX = "^([Hh][Uu][Aa][Ww][Ee][Ii]\\s|HW-|LC\\s|LGM-|LG-|LG\\s|LM-|LG|HTC(?:[\\s_])*|QK|360[\\s]?|ASUS[\\s_]*|[Ll][Ee](?:2|tv[_\\s-]*|\\s)?|ONEPLUS[\\s]*)?(.+)$";
    private static String sCPU;
    private static String sKernel;
    private static int sScreenHeight;
    private static int sScreenWidth;
    private static String sSlaveSDCard;
    private static int sStatusHeight;

    public static String getResolution(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels + "x" + context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static int getScreenWidth(Context context) {
        if (sScreenWidth == 0) {
            sScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        }
        return sScreenWidth;
    }

    public static int getScreenHeight(Context context) {
        if (sScreenHeight == 0) {
            sScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
        }
        return sScreenHeight;
    }

    public static int getStatusHeight(Activity activity) {
        if (sStatusHeight == 0) {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            sStatusHeight = rect.top;
        }
        return sStatusHeight;
    }

    public static int getStatusHeightNew(Context context) {
        if (sStatusHeight != 0 || context == null) {
            return sStatusHeight;
        }
        int dimensionPixelSize = 38;
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            dimensionPixelSize = context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0])).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        sStatusHeight = dimensionPixelSize;
        return dimensionPixelSize;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getModel() {
        String strReplace = Build.MODEL.replace("SAMSUNG-", "").replace("ZTE ", "").replace("Lenovo ", "").replace(" (AOSP)", "");
        Matcher matcher = Pattern.compile(MODEL_REGEX).matcher(strReplace);
        return (!matcher.matches() || matcher.groupCount() < 2) ? strReplace : matcher.group(2);
    }

    public static String getSerial() {
        return Build.SERIAL;
    }

    public static String getSystemId() {
        return Build.VERSION.RELEASE;
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getCPU() {
        String str = sCPU;
        if (str != null) {
            return str;
        }
        sCPU = "";
        String[] strArr = {"", ""};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            String[] strArrSplit = bufferedReader.readLine().split("\\s+");
            for (int i = 2; i < strArrSplit.length; i++) {
                strArr[0] = strArr[0] + strArrSplit[i] + " ";
            }
            strArr[1] = strArr[1] + bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
            sCPU = strArr[0];
        } catch (IOException unused) {
        }
        return sCPU;
    }

    public static String getKernel() {
        String str = sKernel;
        if (str != null) {
            return str;
        }
        sKernel = "";
        Process processExec = null;
        try {
            processExec = Runtime.getRuntime().exec("cat /proc/version");
        } catch (IOException unused) {
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream(), Charset.defaultCharset()), 8192);
        String str2 = "";
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                str2 = str2 + line;
            } catch (IOException unused2) {
            }
        }
        if (!TextUtils.equals(str2, "")) {
            String strSubstring = str2.substring(str2.indexOf("version ") + 8);
            sKernel = strSubstring.substring(0, strSubstring.indexOf(" "));
        }
        return sKernel;
    }

    public static String getIMEI(Context context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getMobileNum(Context context) {
        String line1Number = ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        return line1Number == null ? "" : line1Number;
    }

    public static String getSDCardDir() {
        String path = Environment.getExternalStorageDirectory().getPath();
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        if (path == null || path.endsWith("/")) {
            return path;
        }
        return path + "/";
    }

    public static String getPrimarySDCard() {
        String path = Environment.getExternalStorageDirectory().getPath();
        if (path == null || path.endsWith("/")) {
            return path;
        }
        return path + "/";
    }

    public static String getSlaveSDCard() {
        String str;
        String str2 = sSlaveSDCard;
        if (str2 != null) {
            return str2;
        }
        String primarySDCard = getPrimarySDCard();
        ArrayList<String> mountedDevicesList = getMountedDevicesList();
        mountedDevicesList.add("/mnt/external1");
        long j = 0;
        int i = -1;
        for (int i2 = 0; i2 < mountedDevicesList.size(); i2++) {
            String str3 = mountedDevicesList.get(i2);
            if (!FileUtil.isPathEqual(str3, primarySDCard) && FileUtil.isDirExist(str3)) {
                long availableSizeOf = getAvailableSizeOf(str3);
                if (availableSizeOf > j) {
                    i = i2;
                    j = availableSizeOf;
                }
            }
        }
        if (i == -1) {
            str = null;
        } else {
            str = mountedDevicesList.get(i);
            if (!str.endsWith("/")) {
                str = str + "/";
            }
        }
        sSlaveSDCard = str;
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<java.lang.String> getMountedDevicesList() {
        /*
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.File r2 = android.os.Environment.getRootDirectory()
            java.io.File r2 = r2.getAbsoluteFile()
            r1.append(r2)
            java.lang.String r2 = java.io.File.separator
            r1.append(r2)
            java.lang.String r2 = "etc"
            r1.append(r2)
            java.lang.String r2 = java.io.File.separator
            r1.append(r2)
            java.lang.String r2 = "vold.fstab"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            boolean r2 = r0.exists()
            if (r2 != 0) goto L3a
            return r1
        L3a:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r2.clear()     // Catch: java.io.IOException -> L64
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.io.IOException -> L64
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.io.IOException -> L64
            r4.<init>(r0)     // Catch: java.io.IOException -> L64
            r3.<init>(r4)     // Catch: java.io.IOException -> L64
        L4c:
            java.lang.String r0 = r3.readLine()     // Catch: java.io.IOException -> L64
            if (r0 == 0) goto L5e
            java.lang.String r4 = "dev_mount"
            boolean r4 = r0.startsWith(r4)     // Catch: java.io.IOException -> L64
            if (r4 == 0) goto L4c
            r2.add(r0)     // Catch: java.io.IOException -> L64
            goto L4c
        L5e:
            r3.close()     // Catch: java.io.IOException -> L64
            r2.trimToSize()     // Catch: java.io.IOException -> L64
        L64:
            java.util.Iterator r0 = r2.iterator()
        L68:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L9a
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L77
            goto L68
        L77:
            java.lang.String r3 = " "
            java.lang.String[] r2 = r2.split(r3)
            if (r2 == 0) goto L68
            int r3 = r2.length
            r4 = 4
            if (r3 >= r4) goto L84
            goto L68
        L84:
            r3 = 2
            r2 = r2[r3]
            if (r2 != 0) goto L8a
            goto L68
        L8a:
            java.io.File r3 = new java.io.File
            r3.<init>(r2)
            boolean r3 = r3.exists()
            if (r3 != 0) goto L96
            goto L68
        L96:
            r1.add(r2)
            goto L68
        L9a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.utils.DeviceUtil.getMountedDevicesList():java.util.ArrayList");
    }

    public static long getAvailableSizeOf(String str) {
        StatFs statFs = new StatFs(str);
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static long getTotalExtSdCardMemorySize() {
        String slaveSDCard = getSlaveSDCard();
        if (slaveSDCard == null) {
            return 0L;
        }
        StatFs statFs = new StatFs(slaveSDCard);
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static long getFreeExtSdCardMemorySize() {
        String sDCardDir = getSDCardDir();
        if (sDCardDir == null) {
            return 0L;
        }
        StatFs statFs = new StatFs(sDCardDir);
        return (((long) statFs.getBlockCount()) - ((long) statFs.getAvailableBlocks())) * ((long) statFs.getBlockSize());
    }

    public static long getTotalExternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static long getAvailableExternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static long getFreeExternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getFreeBlocks()) * ((long) statFs.getBlockSize());
    }

    public static long getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static long getTotalInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static boolean isExternalStorageEmulated() {
        if (Build.VERSION.SDK_INT >= 11) {
            return Environment.isExternalStorageEmulated();
        }
        return false;
    }

    public static String getSerialId(Context context) {
        String str;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class).invoke(cls, "ro.serialno");
        } catch (Exception unused) {
            str = null;
        }
        if (str != null && !str.equals("")) {
            return str;
        }
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        return deviceId != null ? deviceId : "unknown";
    }

    public static String getCpuType() throws Throwable {
        InputStream inputStream;
        Throwable th;
        Process processExec;
        String str;
        IOException e2;
        InputStream inputStream2 = null;
        try {
            processExec = Runtime.getRuntime().exec("getprop ro.board.platform");
        } catch (IOException e3) {
            str = null;
            e2 = e3;
            processExec = null;
        } catch (Throwable th2) {
            inputStream = null;
            th = th2;
            processExec = null;
        }
        try {
            try {
                inputStream = processExec.getInputStream();
                try {
                    try {
                        byte[] bArr = new byte[128];
                        inputStream.read(bArr);
                        str = new String(bArr);
                    } catch (IOException e4) {
                        e2 = e4;
                        str = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (processExec != null) {
                        processExec.destroy();
                        throw th;
                    }
                    throw th;
                }
            } catch (IOException e6) {
                str = null;
                e2 = e6;
            }
            try {
                int iIndexOf = str.indexOf(IOUtils.LINE_SEPARATOR_UNIX);
                if (iIndexOf != -1) {
                    str = str.substring(0, iIndexOf);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
            } catch (IOException e8) {
                e2 = e8;
                inputStream2 = inputStream;
                e2.printStackTrace();
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
                if (processExec != null) {
                }
                return str;
            }
            if (processExec != null) {
                processExec.destroy();
            }
            return str;
        } catch (Throwable th4) {
            inputStream = inputStream2;
            th = th4;
        }
    }

    public static long getTotalMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (activityManager == null) {
            return 0L;
        }
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    public static long getTotalMemorySize(Context context) {
        return getTotalMemory(context);
    }

    public static long getHasMemorySize(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static String getCurCpuFreq() throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"));
                    try {
                        String strTrim = bufferedReader2.readLine().trim();
                        try {
                            bufferedReader2.close();
                            return strTrim;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return strTrim;
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return "N/A";
                    } catch (IOException e4) {
                        e = e4;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return "N/A";
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                } catch (IOException e7) {
                    e = e7;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        }
    }

    public static long getMaxCpuFreq() throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"));
                try {
                    long jLongValue = Long.valueOf(bufferedReader2.readLine().trim()).longValue();
                    try {
                        bufferedReader2.close();
                        return jLongValue;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return jLongValue;
                    }
                } catch (IOException e3) {
                    e = e3;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return -1L;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e = e6;
        }
    }

    public static int getCpuNum() {
        return new File("/sys/devices/system/cpu/cpu1").exists() ? 2 : 1;
    }

    public static boolean supportsOpenGLES2(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public static boolean isMeizu() {
        new Build();
        return Build.BRAND.toLowerCase().contains("meizu");
    }

    public static boolean isSamsung() {
        new Build();
        return Build.BRAND.toLowerCase().contains("samsung");
    }

    public static boolean isHuawei() {
        String manufacturer = getManufacturer();
        return !TextUtils.isEmpty(manufacturer) && manufacturer.toLowerCase().contains("huawei");
    }

    public static String getDeviceName(Context context) {
        return Settings.Global.getString(context.getContentResolver(), d.i);
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String getMacAddress(Context context) {
        String macAddressWithWifiManager = getMacAddressWithWifiManager(context);
        return TextUtils.isEmpty(macAddressWithWifiManager) ? getMacAddressWithNetworkInterface() : macAddressWithWifiManager;
    }

    private static String getMacAddressWithWifiManager(Context context) {
        String macAddress;
        if (Build.VERSION.SDK_INT >= 23) {
            return null;
        }
        try {
            macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception unused) {
            macAddress = null;
        }
        try {
        } catch (Exception unused2) {
            CommonLogUtil.w("getMacAddressWithWifiManager: ");
        }
        if ("02:00:00:00:00:00".equals(macAddress)) {
            return null;
        }
        return macAddress;
    }

    private static String getMacAddressWithNetworkInterface() {
        try {
            ArrayList list = Collections.list(NetworkInterface.getNetworkInterfaces());
            String hardwareAddress = getHardwareAddress(list, "wlan0");
            return TextUtils.isEmpty(hardwareAddress) ? getHardwareAddress(list, "eth1") : hardwareAddress;
        } catch (SocketException unused) {
            return null;
        }
    }

    private static String getHardwareAddress(List<NetworkInterface> list, String str) {
        byte[] hardwareAddress;
        if (list == null) {
            return null;
        }
        try {
            for (NetworkInterface networkInterface : list) {
                String name = networkInterface.getName();
                if (!TextUtils.isEmpty(name) && name.equalsIgnoreCase(str) && (hardwareAddress = networkInterface.getHardwareAddress()) != null) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b2)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getDeviceId(Context context) {
        Context applicationContext = context.getApplicationContext();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.avos.avoscloud.analysis1", 0);
        String string = null;
        String string2 = sharedPreferences.getString("com.avos.avoscloud.deviceId1", null);
        if (!TextUtils.isEmpty(string2)) {
            return string2;
        }
        String packageName = applicationContext.getPackageName();
        String androidId = getAndroidId(applicationContext);
        String str = Build.ID;
        String macAddress = getMacAddress(applicationContext);
        String imei = getIMEI(applicationContext);
        if (TextUtils.isEmpty(macAddress) && TextUtils.isEmpty(imei)) {
            string = UUID.randomUUID().toString();
        }
        String lowerCase = Md5Util.md5(packageName + androidId + str + macAddress + imei + string).toLowerCase();
        sharedPreferences.edit().putString("com.avos.avoscloud.deviceId1", lowerCase).apply();
        return lowerCase;
    }
}