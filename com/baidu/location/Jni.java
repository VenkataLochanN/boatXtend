package com.baidu.location;

import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.bumptech.glide.load.Key;

/* JADX INFO: loaded from: classes.dex */
public class Jni {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f2026a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f2027b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f2028c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f2029d = 11;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f2030e = 12;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f2031f = 13;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f2032g = 14;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static int f2033h = 15;
    private static int i = 1024;
    private static boolean j = false;

    static {
        try {
            System.loadLibrary("locSDK7b");
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            j = true;
        }
    }

    private static native String a(byte[] bArr, int i2);

    private static native String b(double d2, double d3, int i2, int i3);

    private static native String c(byte[] bArr, int i2);

    public static double[] coorEncrypt(double d2, double d3, String str) {
        double[] dArr = {0.0d, 0.0d};
        if (j) {
            return dArr;
        }
        int i2 = -1;
        if (str.equals(BDLocation.BDLOCATION_GCJ02_TO_BD09)) {
            i2 = f2026a;
        } else if (str.equals("bd09ll")) {
            i2 = f2027b;
        } else if (str.equals(CoordinateType.GCJ02)) {
            i2 = f2028c;
        } else if (str.equals(BDLocation.BDLOCATION_WGS84_TO_GCJ02)) {
            i2 = f2029d;
        } else if (str.equals(BDLocation.BDLOCATION_BD09_TO_GCJ02)) {
            i2 = f2030e;
        } else if (str.equals(BDLocation.BDLOCATION_BD09LL_TO_GCJ02)) {
            i2 = f2031f;
        } else if (str.equals("wgs842mc")) {
            i2 = f2033h;
        }
        if (str.equals("gcj2wgs")) {
            i2 = 16;
        }
        try {
            String[] strArrSplit = b(d2, d3, i2, 132456).split(":");
            dArr[0] = Double.parseDouble(strArrSplit[0]);
            dArr[1] = Double.parseDouble(strArrSplit[1]);
        } catch (UnsatisfiedLinkError unused) {
        }
        return dArr;
    }

    private static native String ee(String str, int i2);

    public static String en1(String str) {
        if (j) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[i];
        int length = bytes.length;
        if (length > 740) {
            length = 740;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (bytes[i3] != 0) {
                bArr[i2] = bytes[i3];
                i2++;
            }
        }
        try {
            return a(bArr, 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "err!";
        }
    }

    public static String encode(String str) {
        if (j) {
            return "err!";
        }
        return en1(str) + "|tp=3";
    }

    public static String encode2(String str) {
        if (j) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        try {
            return c(str.getBytes(), 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "err!";
        }
    }

    public static Long encode3(String str) {
        String str2;
        if (j) {
            return null;
        }
        try {
            str2 = new String(str.getBytes(), Key.STRING_CHARSET_NAME);
        } catch (Exception unused) {
            str2 = "";
        }
        try {
            return Long.valueOf(murmur(str2));
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static native String encodeNotLimit(String str, int i2);

    public static String encodeOfflineLocationUpdateRequest(String str) {
        String str2;
        String strEncodeNotLimit = "err!";
        if (j) {
            return "err!";
        }
        try {
            str2 = new String(str.getBytes(), Key.STRING_CHARSET_NAME);
        } catch (Exception unused) {
            str2 = "";
        }
        try {
            strEncodeNotLimit = encodeNotLimit(str2, 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
        return strEncodeNotLimit + "|tp=3";
    }

    public static String encodeTp4(String str) {
        String str2;
        String strEe = "err!";
        if (j) {
            return "err!";
        }
        try {
            str2 = new String(str.getBytes(), Key.STRING_CHARSET_NAME);
        } catch (Exception unused) {
            str2 = "";
        }
        try {
            strEe = ee(str2, 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
        return strEe + "|tp=4";
    }

    public static double getGpsSwiftRadius(float f2, double d2, double d3) {
        if (j) {
            return 0.0d;
        }
        try {
            return gsr(f2, d2, d3);
        } catch (UnsatisfiedLinkError unused) {
            return 0.0d;
        }
    }

    private static native double gsr(float f2, double d2, double d3);

    private static native long murmur(String str);
}