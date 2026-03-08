package com.baidu.lbsapi.auth;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Base64;
import com.realsil.sdk.dfu.DfuConstants;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class b {

    static class a {
        public static String a(byte[] bArr) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder sb = new StringBuilder(bArr.length * 2);
            for (int i = 0; i < bArr.length; i++) {
                sb.append(cArr[(bArr[i] & 240) >> 4]);
                sb.append(cArr[bArr[i] & DfuConstants.BANK_INFO_NOT_SUPPORTED]);
            }
            return sb.toString();
        }
    }

    static String a() {
        return Locale.getDefault().getLanguage();
    }

    protected static String a(Context context) {
        String packageName = context.getPackageName();
        return a(context, packageName) + ";" + packageName;
    }

    private static String a(Context context, String str) {
        String strA;
        try {
            strA = a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray())));
        } catch (PackageManager.NameNotFoundException | CertificateException unused) {
            strA = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strA.length(); i++) {
            stringBuffer.append(strA.charAt(i));
            if (i > 0 && i % 2 == 1 && i < strA.length() - 1) {
                stringBuffer.append(":");
            }
        }
        return stringBuffer.toString();
    }

    static String a(X509Certificate x509Certificate) {
        try {
            return a.a(a(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException unused) {
            return null;
        }
    }

    static byte[] a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    protected static String[] b(Context context) {
        String packageName = context.getPackageName();
        String[] strArrB = b(context, packageName);
        if (strArrB == null || strArrB.length <= 0) {
            return null;
        }
        String[] strArr = new String[strArrB.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArrB[i] + ";" + packageName;
            if (com.baidu.lbsapi.auth.a.f1989a) {
                com.baidu.lbsapi.auth.a.a("mcode" + strArr[i]);
            }
        }
        return strArr;
    }

    private static String[] b(Context context, String str) {
        String[] strArr;
        Signature[] signatureArr;
        String[] strArr2 = null;
        try {
            signatureArr = context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (PackageManager.NameNotFoundException | CertificateException unused) {
        }
        if (signatureArr == null || signatureArr.length <= 0) {
            strArr = null;
        } else {
            strArr = new String[signatureArr.length];
            for (int i = 0; i < signatureArr.length; i++) {
                try {
                    strArr[i] = a((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray())));
                } catch (PackageManager.NameNotFoundException | CertificateException unused2) {
                }
            }
        }
        if (strArr != null && strArr.length > 0) {
            strArr2 = new String[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i3 = 0; i3 < strArr[i2].length(); i3++) {
                    stringBuffer.append(strArr[i2].charAt(i3));
                    if (i3 > 0 && i3 % 2 == 1 && i3 < strArr[i2].length() - 1) {
                        stringBuffer.append(":");
                    }
                }
                strArr2[i2] = stringBuffer.toString();
            }
        }
        return strArr2;
    }

    static String c(Context context) {
        String string = context.getSharedPreferences("mac", 0).getString("macaddr", null);
        if (string == null) {
            String strD = d(context);
            if (strD != null) {
                string = Base64.encodeToString(strD.getBytes(), 0);
                if (!TextUtils.isEmpty(string)) {
                    context.getSharedPreferences("mac", 0).edit().putString("macaddr", string).commit();
                }
            } else {
                string = "";
            }
        }
        if (com.baidu.lbsapi.auth.a.f1989a) {
            com.baidu.lbsapi.auth.a.a("getMacID mac_adress: " + string);
        }
        return string;
    }

    private static boolean c(Context context, String str) {
        boolean z = context.checkCallingOrSelfPermission(str) != -1;
        if (com.baidu.lbsapi.auth.a.f1989a) {
            com.baidu.lbsapi.auth.a.a("hasPermission " + z + " | " + str);
        }
        return z;
    }

    static String d(Context context) {
        String str;
        String macAddress = null;
        try {
            if (c(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                macAddress = connectionInfo.getMacAddress();
                if (!TextUtils.isEmpty(macAddress)) {
                    Base64.encode(macAddress.getBytes(), 0);
                }
                if (com.baidu.lbsapi.auth.a.f1989a) {
                    str = String.format("ssid=%s mac=%s", connectionInfo.getSSID(), connectionInfo.getMacAddress());
                    com.baidu.lbsapi.auth.a.a(str);
                }
            } else if (com.baidu.lbsapi.auth.a.f1989a) {
                str = "You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add:android.permission.ACCESS_WIFI_STATE";
                com.baidu.lbsapi.auth.a.a(str);
            }
        } catch (Exception e2) {
            if (com.baidu.lbsapi.auth.a.f1989a) {
                com.baidu.lbsapi.auth.a.a(e2.toString());
            }
        }
        return macAddress;
    }
}