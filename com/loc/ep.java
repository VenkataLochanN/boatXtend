package com.loc;

import android.app.Application;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.view.MotionEventCompat;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.bumptech.glide.load.Key;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import kotlin.UByte;
import org.json.JSONObject;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ep {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f5179b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String[] f5180c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String[] f5181d = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String f5182e = "android.permission.ACCESS_BACKGROUND_LOCATION";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static WifiManager f5178a = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f5183f = false;

    public static double a(double d2) {
        return b(d2);
    }

    public static float a(float f2) {
        return (float) (((long) (((double) f2) * 100.0d)) / 100.0d);
    }

    public static float a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        return a(new double[]{aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()});
    }

    public static float a(DPoint dPoint, DPoint dPoint2) {
        return a(new double[]{dPoint.getLatitude(), dPoint.getLongitude(), dPoint2.getLatitude(), dPoint2.getLongitude()});
    }

    public static float a(double[] dArr) {
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    public static int a(int i) {
        return (i * 2) - 113;
    }

    public static int a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return networkInfo.getType();
        }
        return -1;
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    public static Object a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getApplicationContext().getSystemService(str);
        } catch (Throwable th) {
            ej.a(th, "Utils", "getServ");
            return null;
        }
    }

    public static String a(long j, String str) {
        SimpleDateFormat simpleDateFormat;
        if (TextUtils.isEmpty(str)) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            simpleDateFormat = new SimpleDateFormat(str, Locale.CHINA);
        } catch (Throwable th) {
            th = th;
            simpleDateFormat = null;
        }
        try {
            simpleDateFormat.applyPattern(str);
        } catch (Throwable th2) {
            th = th2;
            ej.a(th, "Utils", "formatUTC");
        }
        if (j <= 0) {
            j = System.currentTimeMillis();
        }
        return simpleDateFormat == null ? "NULL" : simpleDateFormat.format(Long.valueOf(j));
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return c() < 17 ? c(context, "android.provider.Settings$System") : c(context, "android.provider.Settings$Global");
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.database.sqlite.SQLiteDatabase r13, java.lang.String r14) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r14)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.lang.String r0 = "2.0.201501131131"
            java.lang.String r2 = "."
            java.lang.String r3 = ""
            java.lang.String r0 = r0.replace(r2, r3)
            r2 = 0
            if (r13 == 0) goto L62
            r3 = 1
            boolean r4 = r13.isOpen()     // Catch: java.lang.Throwable -> L5e
            if (r4 != 0) goto L1d
            goto L62
        L1d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5e
            java.lang.String r5 = "type = 'table' AND name = '"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L5e
            java.lang.String r14 = r14.trim()     // Catch: java.lang.Throwable -> L5e
            r4.append(r14)     // Catch: java.lang.Throwable -> L5e
            r4.append(r0)     // Catch: java.lang.Throwable -> L5e
            java.lang.String r14 = "'"
            r4.append(r14)     // Catch: java.lang.Throwable -> L5e
            java.lang.String r8 = r4.toString()     // Catch: java.lang.Throwable -> L5e
            java.lang.String r6 = "sqlite_master"
            java.lang.String r14 = "count(*) as c"
            java.lang.String[] r7 = new java.lang.String[]{r14}     // Catch: java.lang.Throwable -> L5e
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r5 = r13
            android.database.Cursor r2 = r5.query(r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L5e
            if (r2 == 0) goto L57
            boolean r13 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L5e
            if (r13 == 0) goto L57
            int r13 = r2.getInt(r1)     // Catch: java.lang.Throwable -> L5e
            if (r13 <= 0) goto L57
            goto L58
        L57:
            r3 = r1
        L58:
            if (r2 == 0) goto L61
        L5a:
            r2.close()
            goto L61
        L5e:
            if (r2 == 0) goto L61
            goto L5a
        L61:
            return r3
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ep.a(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    public static boolean a(Location location, int i) {
        Boolean bool = false;
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                bool = (Boolean) em.a(location, "isFromMockProvider", new Object[0]);
            } catch (Throwable unused) {
            }
        }
        if (bool.booleanValue()) {
            return true;
        }
        Bundle extras = location.getExtras();
        if ((extras != null ? extras.getInt("satellites") : 0) <= 0) {
            return true;
        }
        return i == 0 && location.getAltitude() == 0.0d && location.getBearing() == 0.0f && location.getSpeed() == 0.0f;
    }

    public static boolean a(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            return b(aMapLocation);
        }
        return false;
    }

    public static boolean a(ds dsVar) {
        if (dsVar == null || AlexaCustomSkillConstant.EVENT_BRIGHTNESS.equals(dsVar.d()) || "5".equals(dsVar.d()) || "6".equals(dsVar.d())) {
            return false;
        }
        return b(dsVar);
    }

    public static boolean a(String str) {
        return (TextUtils.isEmpty(str) || "00:00:00:00:00:00".equals(str) || str.contains(" :")) ? false : true;
    }

    public static boolean a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            ArrayList<String> arrayListD = d(str);
            String[] strArrSplit = str2.toString().split("#");
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < strArrSplit.length; i3++) {
                if (strArrSplit[i3].contains(",nb") || strArrSplit[i3].contains(",access")) {
                    i++;
                    if (arrayListD.contains(strArrSplit[i3])) {
                        i2++;
                    }
                }
            }
            if (i2 * 2 >= ((double) (arrayListD.size() + i)) * 0.618d) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return u.a(jSONObject, str);
    }

    public static byte[] a(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            bArr = new byte[2];
        }
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        return bArr;
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((j >> (i * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr) {
        return u.b(bArr);
    }

    public static String[] a(TelephonyManager telephonyManager) {
        int i;
        String[] strArr;
        String networkOperator = telephonyManager != null ? telephonyManager.getNetworkOperator() : null;
        String[] strArr2 = {AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE};
        if (!TextUtils.isEmpty(networkOperator) && TextUtils.isDigitsOnly(networkOperator) && networkOperator.length() > 4) {
            strArr2[0] = networkOperator.substring(0, 3);
            char[] charArray = networkOperator.substring(3).toCharArray();
            int i2 = 0;
            while (i2 < charArray.length && Character.isDigit(charArray[i2])) {
                i2++;
            }
            strArr2[1] = networkOperator.substring(3, i2 + 3);
        }
        try {
            i = Integer.parseInt(strArr2[0]);
        } catch (Throwable th) {
            ej.a(th, "Utils", "getMccMnc");
            i = 0;
        }
        if (i == 0) {
            strArr2[0] = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        if (!AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(strArr2[0]) && !AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(strArr2[1])) {
            f5180c = strArr2;
        } else if (AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(strArr2[0]) && AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(strArr2[1]) && (strArr = f5180c) != null) {
            return strArr;
        }
        return strArr2;
    }

    public static double b(double d2) {
        return ((long) (d2 * 1000000.0d)) / 1000000.0d;
    }

    public static int b(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            i |= (bArr[i2] & UByte.MAX_VALUE) << ((1 - i2) * 8);
        }
        return i;
    }

    public static long b() {
        return SystemClock.elapsedRealtime();
    }

    public static String b(int i) {
        switch (i) {
            case 0:
                return "success";
            case 1:
                return "重要参数为空";
            case 2:
                return "WIFI信息不足";
            case 3:
                return "请求参数获取出现异常";
            case 4:
                return "网络连接异常";
            case 5:
                return "解析数据异常";
            case 6:
                return "定位结果错误";
            case 7:
                return "KEY错误";
            case 8:
            case 16:
            case 17:
            default:
                return "其他错误";
            case 9:
                return "初始化异常";
            case 10:
                return "定位服务启动失败";
            case 11:
                return "错误的基站信息，请检查是否插入SIM卡";
            case 12:
                return "缺少定位权限";
            case 13:
                return "网络定位失败，请检查设备是否插入sim卡，是否开启移动网络或开启了wifi模块";
            case 14:
                return "GPS 定位失败，由于设备当前 GPS 状态差,建议持设备到相对开阔的露天场所再次尝试";
            case 15:
                return "当前返回位置为模拟软件返回，请关闭模拟软件，或者在option中设置允许模拟";
            case 18:
                return "定位失败，飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关";
            case 19:
                return "定位失败，没有检查到SIM卡，并且关闭了WIFI开关，请打开WIFI开关或者插入SIM卡";
        }
    }

    public static String b(Context context) {
        PackageInfo packageInfo;
        if (!TextUtils.isEmpty(ej.f5166h)) {
            return ej.f5166h;
        }
        if (context == null) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(k.c(context), 64);
        } catch (Throwable th) {
            ej.a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(ej.i)) {
                ej.i = null;
            }
        } catch (Throwable th2) {
            ej.a(th2, "Utils", "getAppName");
        }
        StringBuilder sb = new StringBuilder();
        if (packageInfo != null) {
            CharSequence charSequenceLoadLabel = packageInfo.applicationInfo != null ? packageInfo.applicationInfo.loadLabel(context.getPackageManager()) : null;
            if (charSequenceLoadLabel != null) {
                sb.append(charSequenceLoadLabel.toString());
            }
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                sb.append(packageInfo.versionName);
            }
        }
        String strC = k.c(context);
        if (!TextUtils.isEmpty(strC)) {
            sb.append(AppInfo.DELIM);
            sb.append(strC);
        }
        if (!TextUtils.isEmpty(ej.i)) {
            sb.append(AppInfo.DELIM);
            sb.append(ej.i);
        }
        String string = sb.toString();
        ej.f5166h = string;
        return string;
    }

    public static String b(TelephonyManager telephonyManager) {
        int networkType = 0;
        if (telephonyManager != null) {
            try {
                networkType = telephonyManager.getNetworkType();
            } catch (Throwable unused) {
            }
        }
        switch (networkType) {
            case 0:
                return "UNKWN";
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "EVDO_0";
            case 6:
                return "EVDO_A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "IDEN";
            case 12:
                return "EVDO_B";
            case 13:
                return "LTE";
            case 14:
                return "EHRPD";
            case 15:
                return "HSPAP";
            default:
                return "UNKWN";
        }
    }

    public static boolean b(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(str, 256);
        } catch (Throwable unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static boolean b(AMapLocation aMapLocation) {
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }

    public static byte[] b(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            bArr = new byte[4];
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] b(String str) {
        return a(Integer.parseInt(str), (byte[]) null);
    }

    public static double c(double d2) {
        return ((long) (d2 * 100.0d)) / 100.0d;
    }

    public static int c() {
        int i = f5179b;
        if (i > 0) {
            return i;
        }
        try {
            try {
                return em.b("android.os.Build$VERSION", "SDK_INT");
            } catch (Throwable unused) {
                return Integer.parseInt(em.a("android.os.Build$VERSION", "SDK").toString());
            }
        } catch (Throwable unused2) {
            return 0;
        }
    }

    public static NetworkInfo c(Context context) {
        try {
            return n.r(context);
        } catch (Throwable th) {
            ej.a(th, "Utils", "getNetWorkInfo");
            return null;
        }
    }

    private static boolean c(Context context, String str) throws Throwable {
        return ((Integer) em.a(str, "getInt", new Object[]{context.getContentResolver(), ((String) em.a(str, "AIRPLANE_MODE_ON")).toString()}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    public static byte[] c(String str) {
        return b(Integer.parseInt(str), (byte[]) null);
    }

    public static int d() {
        return new Random().nextInt(65536) - 32768;
    }

    public static ArrayList<String> d(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            String[] strArrSplit = str.split("#");
            for (int i = 0; i < strArrSplit.length; i++) {
                if (strArrSplit[i].contains(",nb") || strArrSplit[i].contains(",access")) {
                    arrayList.add(strArrSplit[i]);
                }
            }
        }
        return arrayList;
    }

    public static boolean d(Context context) {
        try {
            NetworkInfo networkInfoC = c(context);
            if (networkInfoC != null) {
                if (networkInfoC.isConnectedOrConnecting()) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static double e(String str) throws NumberFormatException {
        return Double.parseDouble(str);
    }

    public static String e() {
        try {
            return o.b("S128DF1572465B890OE3F7A13167KLEI".getBytes(Key.STRING_CHARSET_NAME)).substring(20);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
    
        if (r2.importance == 100) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean e(android.content.Context r6) {
        /*
            r0 = 1
            java.lang.String r1 = "activity"
            java.lang.Object r1 = r6.getSystemService(r1)     // Catch: java.lang.Throwable -> L32
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1     // Catch: java.lang.Throwable -> L32
            java.util.List r1 = r1.getRunningAppProcesses()     // Catch: java.lang.Throwable -> L32
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L32
        L11:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L32
            r3 = 0
            if (r2 == 0) goto L31
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L32
            android.app.ActivityManager$RunningAppProcessInfo r2 = (android.app.ActivityManager.RunningAppProcessInfo) r2     // Catch: java.lang.Throwable -> L32
            java.lang.String r4 = r2.processName     // Catch: java.lang.Throwable -> L32
            java.lang.String r5 = com.loc.k.c(r6)     // Catch: java.lang.Throwable -> L32
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Throwable -> L32
            if (r4 == 0) goto L11
            int r6 = r2.importance     // Catch: java.lang.Throwable -> L32
            r1 = 100
            if (r6 == r1) goto L31
            return r0
        L31:
            return r3
        L32:
            r6 = move-exception
            java.lang.String r1 = "Utils"
            java.lang.String r2 = "isApplicationBroughtToBackground"
            com.loc.ej.a(r6, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ep.e(android.content.Context):boolean");
    }

    public static float f(String str) throws NumberFormatException {
        return Float.parseFloat(str);
    }

    public static boolean f(Context context) {
        int iB;
        if (Build.VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) {
            for (String str : f5181d) {
                if (context.checkCallingOrSelfPermission(str) != 0) {
                    return false;
                }
            }
        } else {
            Application application = (Application) context;
            for (String str2 : f5181d) {
                try {
                    iB = em.b(application.getBaseContext(), "checkSelfPermission", str2);
                } catch (Throwable unused) {
                    iB = 0;
                }
                if (iB != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int g(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }

    public static boolean g(Context context) {
        int iB;
        if (context.getApplicationInfo().targetSdkVersion < 29) {
            return true;
        }
        try {
            iB = em.b(((Application) context).getBaseContext(), "checkSelfPermission", f5182e);
        } catch (Throwable unused) {
            iB = 0;
        }
        return iB == 0;
    }

    public static int h(String str) throws NumberFormatException {
        return Integer.parseInt(str, 16);
    }

    public static boolean h(Context context) {
        boolean zIsWifiEnabled;
        if (context == null) {
            return true;
        }
        if (f5178a == null) {
            f5178a = (WifiManager) a(context, "wifi");
        }
        try {
            zIsWifiEnabled = f5178a.isWifiEnabled();
        } catch (Throwable unused) {
            zIsWifiEnabled = false;
        }
        if (zIsWifiEnabled || c() <= 17) {
            return zIsWifiEnabled;
        }
        try {
            return "true".equals(String.valueOf(em.a(f5178a, "isScanAlwaysAvailable", new Object[0])));
        } catch (Throwable unused2) {
            return zIsWifiEnabled;
        }
    }

    public static byte i(String str) throws NumberFormatException {
        return Byte.parseByte(str);
    }

    public static String i(Context context) {
        NetworkInfo networkInfoC = c(context);
        if (networkInfoC == null || !networkInfoC.isConnectedOrConnecting()) {
            return "DISCONNECTED";
        }
        int type = networkInfoC.getType();
        if (type == 1) {
            return "WIFI";
        }
        if (type != 0) {
            return "UNKNOWN";
        }
        String subtypeName = networkInfoC.getSubtypeName();
        switch (networkInfoC.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                break;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return "3G";
            case 13:
                return "4G";
            default:
                if (!"GSM".equalsIgnoreCase(subtypeName)) {
                    return ("TD-SCDMA".equalsIgnoreCase(subtypeName) || "WCDMA".equalsIgnoreCase(subtypeName) || "CDMA2000".equalsIgnoreCase(subtypeName)) ? "3G" : subtypeName;
                }
                break;
        }
        return "2G";
    }

    public static String j(Context context) {
        String strM = n.m(context);
        if (TextUtils.isEmpty(strM) || strM.equals("00:00:00:00:00:00")) {
            strM = context == null ? "00:00:00:00:00:00" : eo.a(context, "pref", "smac", "00:00:00:00:00:00");
        }
        if (TextUtils.isEmpty(strM)) {
            strM = "00:00:00:00:00:00";
        }
        if (!f5183f) {
            if (context != null && !TextUtils.isEmpty(strM)) {
                SharedPreferences.Editor editorA = eo.a(context, "pref");
                eo.a(editorA, "smac", strM);
                eo.a(editorA);
            }
            f5183f = true;
        }
        return strM;
    }

    public static boolean k(Context context) {
        return Build.VERSION.SDK_INT >= 28 && context.getApplicationInfo().targetSdkVersion >= 28;
    }

    public static boolean l(Context context) {
        ServiceInfo serviceInfo;
        try {
            serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, "com.amap.api.location.APSService"), 128);
        } catch (Throwable unused) {
            serviceInfo = null;
        }
        return serviceInfo != null;
    }
}