package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: MD5.java */
/* JADX INFO: loaded from: classes.dex */
public class gq {
    public static String a(String str) {
        FileInputStream fileInputStream;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                byte[] bArr = new byte[2048];
                MessageDigest messageDigest = MessageDigest.getInstance(gt.c("ETUQ1"));
                fileInputStream = new FileInputStream(file);
                while (true) {
                    try {
                        int i = fileInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, i);
                    } catch (Throwable th) {
                        th = th;
                    }
                }
                String strE = gt.e(messageDigest.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    hk.a(e2, "MD5", "gfm");
                }
                return strE;
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            hk.a(th, "MD5", "gfm");
            return null;
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    hk.a(e3, "MD5", "gfm");
                }
            }
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        return gt.e(d(str));
    }

    public static String a(byte[] bArr) {
        return gt.e(b(bArr));
    }

    public static String c(String str) {
        return gt.f(e(str));
    }

    public static byte[] a(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Throwable th) {
            hk.a(th, "MD5", "gmb");
            return null;
        }
    }

    private static byte[] b(byte[] bArr) {
        return a(bArr, gt.c("ETUQ1"));
    }

    public static byte[] d(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            hk.a(th, "MD5", "gmb");
            return new byte[0];
        }
    }

    private static byte[] e(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] f(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(gt.c("ETUQ1"));
        messageDigest.update(gt.a(str));
        return messageDigest.digest();
    }
}