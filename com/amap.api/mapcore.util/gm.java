package com.amap.api.mapcore.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Xml;
import android.view.WindowManager;
import com.bumptech.glide.load.Key;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.UByte;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: DeviceInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class gm {
    private static String A = "";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f1105a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String f1106b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static volatile boolean f1107c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f1108d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static String f1109e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static boolean f1110f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static a f1111g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static int f1112h = -1;
    static String i = "";
    static String j = "";
    private static String k = null;
    private static boolean l = false;
    private static volatile boolean m = false;
    private static String n = "";
    private static boolean o = false;
    private static String p = "";
    private static String q = "";
    private static String r = "";
    private static String s = "";
    private static String t = "";
    private static String u = "";
    private static boolean v = false;
    private static String w = "";
    private static long x;
    private static int y;
    private static String z;

    /* JADX INFO: compiled from: DeviceInfo.java */
    public interface a {
        iq a(byte[] bArr, Map<String, String> map);

        String a();

        String a(Context context, String str);

        String a(String str, String str2, String str3, String str4);

        Map<String, String> b();
    }

    public static String a() {
        return k;
    }

    public static String a(Context context) {
        try {
            if (TextUtils.isEmpty(f1109e)) {
                return f1111g == null ? "" : f1111g.a();
            }
            return f1109e;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void a(a aVar) {
        if (f1111g == null) {
            f1111g = aVar;
        }
    }

    public static a b() {
        return f1111g;
    }

    public static String b(final Context context) {
        if (!TextUtils.isEmpty(f1106b)) {
            return f1106b;
        }
        if (context == null) {
            return "";
        }
        f1106b = O(context);
        if (!TextUtils.isEmpty(f1106b)) {
            return f1106b;
        }
        if (b() == null || m) {
            return "";
        }
        m = true;
        hn.d().submit(new Runnable() { // from class: com.amap.api.mapcore.util.gm.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Map<String, String> mapB = gm.f1111g.b();
                    String strA = gm.f1111g.a(gm.i(context), "", "", gm.A(context));
                    if (TextUtils.isEmpty(strA)) {
                        return;
                    }
                    String strA2 = gm.f1111g.a(context, new String(ij.a().a(gm.f1111g.a(strA.getBytes(), mapB))));
                    if (TextUtils.isEmpty(strA2)) {
                        return;
                    }
                    gm.f1106b = strA2;
                } catch (Throwable unused) {
                }
            }
        });
        return "";
    }

    public static String c(Context context) {
        try {
            return H(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String d(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        try {
            return L(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int e(Context context) {
        try {
            return M(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static int f(Context context) {
        try {
            return J(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String g(Context context) {
        try {
            return G(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String E(Context context) {
        FileInputStream fileInputStream = null;
        try {
            if (gt.a(context, "android.permission.READ_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    xmlPullParserNewPullParser.setInput(fileInputStream2, "utf-8");
                    boolean z2 = false;
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); 1 != eventType; eventType = xmlPullParserNewPullParser.next()) {
                        if (eventType != 0) {
                            if (eventType != 2) {
                                if (eventType == 3) {
                                    z2 = false;
                                } else if (eventType == 4 && z2) {
                                    String text = xmlPullParserNewPullParser.getText();
                                    try {
                                        fileInputStream2.close();
                                    } catch (Throwable unused) {
                                    }
                                    return text;
                                }
                            } else if (xmlPullParserNewPullParser.getAttributeCount() > 0) {
                                int attributeCount = xmlPullParserNewPullParser.getAttributeCount();
                                boolean z3 = z2;
                                for (int i2 = 0; i2 < attributeCount; i2++) {
                                    String attributeValue = xmlPullParserNewPullParser.getAttributeValue(i2);
                                    if ("UTDID2".equals(attributeValue) || "UTDID".equals(attributeValue)) {
                                        z3 = true;
                                    }
                                }
                                z2 = z3;
                            }
                        }
                    }
                    fileInputStream = fileInputStream2;
                } catch (Throwable unused2) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream == null) {
                        return "";
                    }
                }
            }
            if (fileInputStream == null) {
                return "";
            }
        } catch (Throwable unused3) {
        }
        try {
            fileInputStream.close();
            return "";
        } catch (Throwable unused4) {
            return "";
        }
    }

    /* JADX INFO: compiled from: DeviceInfo.java */
    static class b implements ServiceConnection {
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                iBinder.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                String unused = gm.n = parcelObtain2.readString();
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String F(Context context) {
        int i2 = 0;
        if (gt.c("IeGlhb21p").equalsIgnoreCase(Build.MANUFACTURER)) {
            try {
                Class<?> cls = Class.forName(gt.c("WY29tLmFuZHJvaWQuaWQuaW1wbC5JZFByb3ZpZGVySW1wbA"));
                Object objInvoke = cls.getMethod(gt.c("MZ2V0T0FJRA"), Context.class).invoke(cls.newInstance(), context);
                if (objInvoke != null) {
                    n = (String) objInvoke;
                    return n;
                }
            } catch (Throwable unused) {
                o = true;
            }
        } else if (gt.c("IaHVhd2Vp").equalsIgnoreCase(Build.MANUFACTURER)) {
            try {
                Intent intent = new Intent();
                intent.setAction(gt.c("WY29tLnVvZGlzLm9wZW5kZXZpY2UuT1BFTklEU19TRVJWSUNF"));
                intent.setPackage(gt.c("UY29tLmh1YXdlaS5od2lk"));
                b bVar = new b();
                if (context.bindService(intent, bVar, 1)) {
                    while (i2 < 100 && TextUtils.isEmpty(n)) {
                        i2++;
                        Thread.sleep(15L);
                    }
                    context.unbindService(bVar);
                }
                return n;
            } catch (Throwable th) {
                hk.a(th, "oa", "hw");
                o = true;
            }
        } else if ("OPPO".equalsIgnoreCase(Build.MANUFACTURER)) {
            o = true;
        } else {
            o = true;
        }
        return n;
    }

    public static String h(final Context context) {
        if (o) {
            return "";
        }
        if (!TextUtils.isEmpty(n)) {
            return n;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            hn.d().submit(new Runnable() { // from class: com.amap.api.mapcore.util.gm.2
                @Override // java.lang.Runnable
                public void run() {
                    gm.F(context);
                }
            });
            return n;
        }
        return F(context);
    }

    public static String i(Context context) {
        if (f1105a != null && !"".equals(f1105a)) {
            return f1105a;
        }
        if (a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFVFRJTkdT"))) {
            f1105a = Settings.System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
        }
        if (f1105a != null && !"".equals(f1105a)) {
            return f1105a;
        }
        try {
            f1105a = E(context);
        } catch (Throwable unused) {
        }
        String str = f1105a;
        return str == null ? "" : str;
    }

    public static String j(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if (!TextUtils.isEmpty(q)) {
            return q;
        }
        if (!a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return (String) gt.a(Build.class, "MZ2V0U2VyaWFs", (Class<?>[]) new Class[0]).invoke(Build.class, new Object[0]);
        }
        if (Build.VERSION.SDK_INT >= 9) {
            q = Build.SERIAL;
        }
        String str = q;
        return str == null ? "" : str;
    }

    public static String k(Context context) {
        if (!TextUtils.isEmpty(p)) {
            return p;
        }
        try {
            p = Settings.Secure.getString(context.getContentResolver(), gt.c(new String(hj.a(13))));
            return p == null ? "" : p;
        } catch (Throwable unused) {
            return p;
        }
    }

    private static boolean a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    static String l(Context context) {
        WifiManager wifiManager;
        if (context == null) {
            return "";
        }
        try {
            return (a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF")) && (wifiManager = (WifiManager) context.getSystemService("wifi")) != null && wifiManager.isWifiEnabled()) ? wifiManager.getConnectionInfo().getBSSID() : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    static String m(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            if (a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                if (wifiManager.isWifiEnabled()) {
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() != 0) {
                        List<ScanResult> listA = a(scanResults);
                        boolean z2 = true;
                        for (int i2 = 0; i2 < listA.size() && i2 < 7; i2++) {
                            ScanResult scanResult = listA.get(i2);
                            if (z2) {
                                z2 = false;
                            } else {
                                sb.append(";");
                            }
                            sb.append(scanResult.BSSID);
                        }
                    }
                    return sb.toString();
                }
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public static String n(Context context) {
        if (r != null && !"".equals(r)) {
            return r;
        }
        if (!a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
            return r;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        r = wifiManager.getConnectionInfo().getMacAddress();
        if (gt.c("YMDI6MDA6MDA6MDA6MDA6MDA").equals(r) || gt.c("YMDA6MDA6MDA6MDA6MDA6MDA").equals(r)) {
            r = e();
        }
        return r;
    }

    private static String e() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = Build.VERSION.SDK_INT >= 9 ? networkInterface.getHardwareAddress() : null;
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        String upperCase = Integer.toHexString(b2 & UByte.MAX_VALUE).toUpperCase();
                        if (upperCase.length() == 1) {
                            sb.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                        }
                        sb.append(upperCase);
                        sb.append(":");
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    static String[] o(Context context) {
        return new String[]{"", ""};
    }

    static String p(Context context) {
        try {
            TelephonyManager telephonyManagerN = N(context);
            if (telephonyManagerN == null) {
                return "";
            }
            String networkOperator = telephonyManagerN.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
                return networkOperator.substring(3);
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int q(Context context) {
        try {
            return M(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static int r(Context context) {
        try {
            return J(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static NetworkInfo s(Context context) {
        ConnectivityManager connectivityManagerK;
        if (a(context, gt.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) && (connectivityManagerK = K(context)) != null) {
            return connectivityManagerK.getActiveNetworkInfo();
        }
        return null;
    }

    static String t(Context context) {
        try {
            NetworkInfo networkInfoS = s(context);
            if (networkInfoS == null) {
                return null;
            }
            return networkInfoS.getExtraInfo();
        } catch (Throwable unused) {
            return null;
        }
    }

    static String u(Context context) {
        StringBuilder sb;
        if (s != null && !"".equals(s)) {
            return s;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return "";
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        if (i3 > i2) {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append("*");
            sb.append(i3);
        } else {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append("*");
            sb.append(i2);
        }
        s = sb.toString();
        return s;
    }

    public static String v(Context context) {
        try {
            return I(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(Context context, String str, boolean z2) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        try {
            if (Build.VERSION.SDK_INT < 21) {
                return "";
            }
            if (!TextUtils.isEmpty(i)) {
                return i;
            }
            if (!z2 && f1112h >= 0 && f1112h < 2) {
                return "";
            }
            TelephonyManager telephonyManagerN = N(context);
            if (f1112h == -1) {
                Method methodA = gt.a(TelephonyManager.class, "UZ2V0UGhvbmVDb3VudA=", (Class<?>[]) new Class[0]);
                if (methodA != null) {
                    try {
                        f1112h = ((Integer) methodA.invoke(telephonyManagerN, new Object[0])).intValue();
                    } catch (Throwable unused) {
                        f1112h = 0;
                    }
                } else {
                    f1112h = 0;
                }
            }
            if (!z2 && f1112h <= 1) {
                return "";
            }
            Method methodA2 = gt.a(TelephonyManager.class, "MZ2V0SW1laQ=", (Class<?>[]) new Class[]{Integer.TYPE});
            if (methodA2 == null) {
                f1112h = 0;
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < f1112h; i2++) {
                try {
                    sb.append((String) methodA2.invoke(telephonyManagerN, Integer.valueOf(i2)));
                    sb.append(str);
                } catch (Throwable unused2) {
                }
            }
            String string = sb.toString();
            if (string.length() == 0) {
                f1112h = 0;
                return "";
            }
            i = string.substring(0, string.length() - 1);
            return i;
        } catch (Throwable unused3) {
            return "";
        }
    }

    public static String w(Context context) {
        try {
            String strY = y(context);
            try {
                if (TextUtils.isEmpty(strY)) {
                    strY = b(context);
                }
                if (TextUtils.isEmpty(strY)) {
                    strY = i(context);
                }
                if (TextUtils.isEmpty(strY)) {
                    strY = h(context);
                }
                if (TextUtils.isEmpty(strY)) {
                    strY = k(context);
                }
                return TextUtils.isEmpty(strY) ? x(context) : strY;
            } catch (Throwable unused) {
                return strY;
            }
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String x(Context context) {
        if (!TextUtils.isEmpty(w)) {
            return w;
        }
        try {
            String strA = hp.a(context, "open_common", "a1", "");
            if (TextUtils.isEmpty(strA)) {
                w = "amap" + UUID.randomUUID().toString().replace("_", "").toLowerCase();
                SharedPreferences.Editor editorB = hp.b(context, "open_common");
                hp.a(editorB, "a1", gt.b(w));
                hp.a(editorB);
            } else {
                w = gt.c(strA);
            }
            return w;
        } catch (Throwable unused) {
            return w;
        }
    }

    public static String y(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if (t != null && !"".equals(t)) {
            return t;
        }
        if (!a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return t;
        }
        TelephonyManager telephonyManagerN = N(context);
        if (telephonyManagerN == null) {
            return "";
        }
        Method methodA = gt.a(telephonyManagerN.getClass(), "QZ2V0RGV2aWNlSWQ", (Class<?>[]) new Class[0]);
        if (Build.VERSION.SDK_INT >= 26) {
            methodA = gt.a(telephonyManagerN.getClass(), "QZ2V0SW1laQ==", (Class<?>[]) new Class[0]);
        }
        if (methodA != null) {
            t = (String) methodA.invoke(telephonyManagerN, new Object[0]);
        }
        if (t == null) {
            t = "";
        }
        return t;
    }

    public static String z(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if (u != null && !"".equals(u)) {
            return u;
        }
        if (!a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return u;
        }
        TelephonyManager telephonyManagerN = N(context);
        if (telephonyManagerN == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 26) {
            Method methodA = gt.a(telephonyManagerN.getClass(), "QZ2V0TWVpZA==", (Class<?>[]) new Class[0]);
            if (methodA != null) {
                u = (String) methodA.invoke(telephonyManagerN, new Object[0]);
            }
            if (u == null) {
                u = "";
            }
        }
        return u;
    }

    public static String A(Context context) {
        try {
            return G(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static long c() {
        long blockCount;
        long blockCount2;
        long j2 = x;
        if (j2 != 0) {
            return j2;
        }
        try {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (Build.VERSION.SDK_INT >= 18) {
                blockCount = (statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / 1048576;
                blockCount2 = (statFs2.getBlockCountLong() * statFs2.getBlockSizeLong()) / 1048576;
            } else {
                blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1048576;
                blockCount2 = (((long) statFs2.getBlockCount()) * ((long) statFs2.getBlockSize())) / 1048576;
            }
            x = blockCount + blockCount2;
        } catch (Throwable unused) {
        }
        return x;
    }

    public static int B(Context context) {
        BufferedReader bufferedReader;
        int i2 = y;
        if (i2 != 0) {
            return i2;
        }
        int iIntValue = 0;
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return 0;
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            iIntValue = (int) (memoryInfo.totalMem / 1024);
        } else {
            BufferedReader bufferedReader2 = null;
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(new File("/proc/meminfo")));
                } catch (Throwable unused) {
                }
            } catch (IOException unused2) {
            }
            try {
                iIntValue = Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue();
                bufferedReader.close();
            } catch (Throwable unused3) {
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                y = iIntValue / 1024;
                return y;
            }
        }
        y = iIntValue / 1024;
        return y;
    }

    public static String d() {
        if (!TextUtils.isEmpty(z)) {
            return z;
        }
        z = System.getProperty("os.arch");
        return z;
    }

    static String C(Context context) {
        try {
            return H(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String G(Context context) throws IllegalAccessException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        String str = A;
        if (str != null && !"".equals(str)) {
            return A;
        }
        if (!a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return A;
        }
        TelephonyManager telephonyManagerN = N(context);
        if (telephonyManagerN == null) {
            return "";
        }
        Method methodA = gt.a(telephonyManagerN.getClass(), "UZ2V0U3Vic2NyaWJlcklk", (Class<?>[]) new Class[0]);
        if (methodA != null) {
            A = (String) methodA.invoke(telephonyManagerN, new Object[0]);
        }
        if (A == null) {
            A = "";
        }
        return A;
    }

    private static String H(Context context) {
        if (!a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return null;
        }
        TelephonyManager telephonyManagerN = N(context);
        if (telephonyManagerN == null) {
            return "";
        }
        String simOperatorName = telephonyManagerN.getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? telephonyManagerN.getNetworkOperatorName() : simOperatorName;
    }

    private static String I(Context context) {
        ConnectivityManager connectivityManagerK;
        NetworkInfo activeNetworkInfo;
        return (!a(context, gt.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) || (connectivityManagerK = K(context)) == null || (activeNetworkInfo = connectivityManagerK.getActiveNetworkInfo()) == null) ? "" : activeNetworkInfo.getTypeName();
    }

    private static int J(Context context) {
        ConnectivityManager connectivityManagerK;
        NetworkInfo activeNetworkInfo;
        if (context == null || !a(context, gt.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) || (connectivityManagerK = K(context)) == null || (activeNetworkInfo = connectivityManagerK.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    private static ConnectivityManager K(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static String L(Context context) {
        String strA = A(context);
        return (strA == null || strA.length() < 5) ? "" : strA.substring(3, 5);
    }

    private static int M(Context context) {
        TelephonyManager telephonyManagerN;
        if (a(context, gt.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU=")) && (telephonyManagerN = N(context)) != null) {
            return telephonyManagerN.getNetworkType();
        }
        return -1;
    }

    private static TelephonyManager N(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static List<ScanResult> a(List<ScanResult> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size - 1; i2++) {
            for (int i3 = 1; i3 < size - i2; i3++) {
                int i4 = i3 - 1;
                if (list.get(i4).level > list.get(i3).level) {
                    ScanResult scanResult = list.get(i4);
                    list.set(i4, list.get(i3));
                    list.set(i3, scanResult);
                }
            }
        }
        return list;
    }

    private static String O(Context context) {
        if (!f1107c) {
            return "";
        }
        String strP = null;
        try {
            strP = P(context);
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(strP)) {
            f1107c = false;
            return "";
        }
        try {
            byte[] bytes = gt.c("MAAAAAAAAAAAAAAAAAAAAAA").getBytes(Key.STRING_CHARSET_NAME);
            return new String(gn.a(gt.c("HYW1hcGFkaXVhbWFwYWRpdWFtYXBhZGl1YW1hcGFkaXU").getBytes(Key.STRING_CHARSET_NAME), gn.b(strP), bytes), Key.STRING_CHARSET_NAME);
        } catch (Throwable unused2) {
            f1107c = false;
            return "";
        }
    }

    private static String P(Context context) {
        String strR;
        try {
            strR = R(context);
        } catch (Throwable unused) {
            strR = "";
        }
        return !TextUtils.isEmpty(strR) ? strR : Q(context);
    }

    private static String Q(Context context) {
        return context == null ? "" : context.getSharedPreferences(gt.c("SU2hhcmVkUHJlZmVyZW5jZUFkaXU"), 0).getString(gq.b(gt.c("RYW1hcF9kZXZpY2VfYWRpdQ")), "");
    }

    private static String R(Context context) {
        RandomAccessFile randomAccessFile;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        String[] strArrSplit;
        String strB = gq.b(gt.c("LYW1hcF9kZXZpY2VfYWRpdQ"));
        String strA = a(context, false);
        if (TextUtils.isEmpty(strA)) {
            return "";
        }
        File file = new File(strA + File.separator + gt.c("KYmFja3Vwcw"), gt.c("MLmFkaXU"));
        if (file.exists() && file.canRead()) {
            if (file.length() == 0) {
                file.delete();
                return "";
            }
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    bArr = new byte[1024];
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Throwable unused) {
                    byteArrayOutputStream = null;
                }
            } catch (Throwable unused2) {
                randomAccessFile = null;
                byteArrayOutputStream = null;
            }
            while (true) {
                try {
                    int i2 = randomAccessFile.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                } catch (Throwable unused3) {
                }
                a(byteArrayOutputStream);
                a(randomAccessFile);
            }
            String str = new String(byteArrayOutputStream.toByteArray(), Key.STRING_CHARSET_NAME);
            if (!TextUtils.isEmpty(str) && str.contains(gt.c("SIw")) && (strArrSplit = str.split(gt.c("SIw"))) != null && strArrSplit.length == 2 && TextUtils.equals(strB, strArrSplit[0])) {
                String str2 = strArrSplit[1];
                a(byteArrayOutputStream);
                a(randomAccessFile);
                return str2;
            }
            a(byteArrayOutputStream);
            a(randomAccessFile);
        }
        return "";
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    private static String a(Context context, boolean z2) {
        if (Build.VERSION.SDK_INT < 9) {
            return null;
        }
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            Class<?> cls = Class.forName(gt.c("SYW5kcm9pZC5vcy5zdG9yYWdlLlN0b3JhZ2VWb2x1bWU"));
            Method method = storageManager.getClass().getMethod(gt.c("MZ2V0Vm9sdW1lTGlzdA"), new Class[0]);
            Method method2 = cls.getMethod(gt.c("FZ2V0UGF0aA"), new Class[0]);
            Method method3 = cls.getMethod(gt.c("DaXNSZW1vdmFibGU"), new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i2 = 0; i2 < length; i2++) {
                Object obj = Array.get(objInvoke, i2);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (z2 == ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    return str;
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }
}