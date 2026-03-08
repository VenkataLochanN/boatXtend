package com.baidu.location.g;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public class k {
    public static float A = 2.2f;
    public static float B = 2.3f;
    public static float C = 3.8f;
    public static int D = 3;
    public static int E = 10;
    public static int F = 2;
    public static int G = 7;
    public static int H = 20;
    public static int I = 70;
    public static int J = 120;
    public static float K = 2.0f;
    public static float L = 10.0f;
    public static float M = 50.0f;
    public static float N = 200.0f;
    public static int O = 16;
    public static int P = 32;
    public static float Q = 0.9f;
    public static int R = 10000;
    public static float S = 0.5f;
    public static float T = 0.0f;
    public static float U = 0.1f;
    public static int V = 30;
    public static int W = 100;
    public static int X = 0;
    public static int Y = 0;
    public static int Z = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2500a = false;
    private static String aA = "http://loc.map.baidu.com/oqur.php";
    private static String aB = "http://loc.map.baidu.com/tcu.php";
    private static String aC = "http://loc.map.baidu.com/rtbu.php";
    private static String aD = "http://loc.map.baidu.com/iofd.php";
    private static String aE = "http://loc.map.baidu.com/wloc";
    public static int aa = 420000;
    public static boolean ab = true;
    public static boolean ac = true;
    public static int ad = 20;
    public static int ae = 300;
    public static int af = 1000;
    public static int ag = Integer.MAX_VALUE;
    public static long ah = 900000;
    public static long ai = 420000;
    public static long aj = 180000;
    public static long ak = 0;
    public static long al = 15;
    public static long am = 300000;
    public static int an = 1000;
    public static int ao = 0;
    public static int ap = 30000;
    public static int aq = 30000;
    public static float ar = 10.0f;
    public static float as = 6.0f;
    public static float at = 10.0f;
    public static int au = 60;
    public static int av = 70;
    public static int aw = 6;
    public static String ax = null;
    private static String ay = "http://loc.map.baidu.com/sdk.php";
    private static String az = "http://loc.map.baidu.com/user_err.php";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f2501b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f2502c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f2503d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f2504e = "http://loc.map.baidu.com/sdk_ep.php";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f2505f = "https://loc.map.baidu.com/sdk.php";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static String f2506g = "no";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f2507h = false;
    public static boolean i = false;
    public static boolean j = false;
    public static boolean k = false;
    public static boolean l = false;
    public static boolean m = false;
    public static String n = "gcj02";
    public static String o = "";
    public static boolean p = true;
    public static int q = 3;
    public static double r = 0.0d;
    public static double s = 0.0d;
    public static double t = 0.0d;
    public static double u = 0.0d;
    public static int v = 0;
    public static byte[] w = null;
    public static boolean x = false;
    public static int y = 0;
    public static float z = 1.1f;

    public static int a(Context context, String str) {
        return !(context.checkPermission(str, Process.myPid(), Process.myUid()) == 0) ? 0 : 1;
    }

    public static int a(String str, String str2, String str3) {
        int iIndexOf;
        int length;
        int iIndexOf2;
        String strSubstring;
        if (str != null && !str.equals("") && (iIndexOf = str.indexOf(str2)) != -1 && (iIndexOf2 = str.indexOf(str3, (length = iIndexOf + str2.length()))) != -1 && (strSubstring = str.substring(length, iIndexOf2)) != null && !strSubstring.equals("")) {
            try {
                return Integer.parseInt(strSubstring);
            } catch (NumberFormatException unused) {
            }
        }
        return Integer.MIN_VALUE;
    }

    public static String a() {
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(5);
        return String.format(Locale.CHINA, "%d-%02d-%02d %02d:%02d:%02d", Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(i2), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)), Integer.valueOf(calendar.get(13)));
    }

    public static String a(com.baidu.location.e.a aVar, com.baidu.location.e.h hVar, Location location, String str, int i2) {
        return a(aVar, hVar, location, str, i2, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00d4 A[Catch: Exception -> 0x00d7, TRY_LEAVE, TryCatch #0 {Exception -> 0x00d7, blocks: (B:43:0x0097, B:47:0x00b3, B:50:0x00b9, B:51:0x00bc, B:56:0x00c8, B:58:0x00cc, B:60:0x00d0, B:61:0x00d4), top: B:65:0x0097 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(com.baidu.location.e.a r3, com.baidu.location.e.h r4, android.location.Location r5, java.lang.String r6, int r7, boolean r8) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.k.a(com.baidu.location.e.a, com.baidu.location.e.h, android.location.Location, java.lang.String, int, boolean):java.lang.String");
    }

    public static String a(File file, String str) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i2 = fileInputStream.read(bArr, 0, 1024);
                if (i2 == -1) {
                    fileInputStream.close();
                    return new BigInteger(1, messageDigest.digest()).toString(16);
                }
                messageDigest.update(bArr, 0, i2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(String str) {
        return Jni.en1(o + ";" + str);
    }

    public static boolean a(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(BDLocation bDLocation) {
        int locType = bDLocation.getLocType();
        return (locType > 100 && locType < 200) || locType == 62;
    }

    public static int b(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Exception unused) {
            return 2;
        }
    }

    public static boolean b() {
        return false;
    }

    public static boolean b(String str, String str2, String str3) {
        try {
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(com.baidu.android.bbalbs.common.a.b.a(str3.getBytes())));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(publicKeyGeneratePublic);
            signature.update(str.getBytes());
            return signature.verify(com.baidu.android.bbalbs.common.a.b.a(str2.getBytes()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int c(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return -2;
        }
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode", -1);
        } catch (Exception unused) {
            return -1;
        }
    }

    public static String c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        byte[] address = inetAddressNextElement.getAddress();
                        String str = "";
                        for (byte b2 : address) {
                            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
                            if (hexString.length() == 1) {
                                hexString = '0' + hexString;
                            }
                            str = str + hexString;
                        }
                        return str;
                    }
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String d() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet6Address) && inetAddressNextElement.getHostAddress() != null && !inetAddressNextElement.getHostAddress().startsWith("fe80:")) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String d(Context context) {
        return "&per=" + a(context, "android.permission.ACCESS_COARSE_LOCATION") + "|" + a(context, "android.permission.ACCESS_FINE_LOCATION") + "|" + a(context, "android.permission.READ_PHONE_STATE");
    }

    public static String e() {
        return ay;
    }

    public static String e(Context context) {
        int type = -1;
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    type = activeNetworkInfo.getType();
                }
            } catch (Exception unused) {
            }
        }
        return "&netc=" + type;
    }

    public static String f() {
        return aB;
    }

    public static String g() {
        return "https://daup.map.baidu.com/cltr/rcvr";
    }

    public static String h() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                String path = Environment.getExternalStorageDirectory().getPath();
                File file = new File(path + "/baidu/tempdata");
                if (!file.exists()) {
                    file.mkdirs();
                }
                return path;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static String i() {
        String strH = h();
        if (strH == null) {
            return null;
        }
        return strH + "/baidu/tempdata";
    }

    public static String j() {
        try {
            File file = new File(com.baidu.location.f.getServiceContext().getFilesDir() + File.separator + "lldt");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String k() {
        try {
            File file = new File(com.baidu.location.f.getServiceContext().getFilesDir() + File.separator + "/baidu/tempdata");
            if (!file.exists()) {
                file.mkdirs();
            }
            return com.baidu.location.f.getServiceContext().getFilesDir().getPath();
        } catch (Exception unused) {
            return null;
        }
    }
}