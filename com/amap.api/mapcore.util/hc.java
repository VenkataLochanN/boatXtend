package com.amap.api.mapcore.util;

import com.bumptech.glide.load.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* JADX INFO: compiled from: SecurityUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class hc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static byte[] f1190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String[] f1191b = {"kp6SsA", "cHE4dQ", "JKekrA", "XBxOHQ", "CSnpKw", "VwcThw", "wkp6Sg", "1cHE4Q"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int[] f1192c = null;

    private static int b(int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 >> 1) | Integer.MIN_VALUE;
        }
        return (i << i2) | ((i & i3) >>> (32 - i2));
    }

    private static int c(int i) {
        int i2 = 1;
        for (int i3 = 0; i3 < 15; i3++) {
            i2 = (i2 << 2) | 1;
        }
        return ((i & i2) << 1) | (((i2 << 1) & i) >>> 1);
    }

    private static byte[] b() {
        if (f1190a == null) {
            f1190a = gt.c("YAAAAAAAAAAAAAAAAAAAAAA").getBytes();
        }
        return f1190a;
    }

    private static int[] c() {
        int[] iArr = f1192c;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[8];
        int i = 0;
        while (true) {
            String[] strArr = f1191b;
            if (i >= strArr.length) {
                return iArr2;
            }
            iArr2[i] = b(gn.b(strArr[i]));
            i++;
        }
    }

    public static String a(String str) {
        return gq.b(str);
    }

    public static String a() {
        SecureRandom secureRandom = new SecureRandom();
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(gt.c("EQUVT"));
            keyGenerator.init(128, secureRandom);
            return gx.a(keyGenerator.generateKey().getEncoded());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String b(String str) {
        try {
            return gx.a(a(str.getBytes(Key.STRING_CHARSET_NAME)));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] a(byte[] bArr) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(b());
            SecretKeySpec secretKeySpec = new SecretKeySpec(a(c()).getBytes(Key.STRING_CHARSET_NAME), gt.c("EQUVT"));
            Cipher cipher = Cipher.getInstance(gt.c("CQUVTL0NCQy9QS0NTNVBhZGRpbmc"));
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String a(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        if (iArr != null) {
            for (int i = 0; i < iArr.length; i++) {
                sb.append(a(iArr[i], i));
            }
        }
        return sb.toString();
    }

    private static String a(int i, int i2) {
        return a(b(c(i), i2));
    }

    private static String a(int i) {
        char[] cArr = new char[4];
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = (4 - i2) - 1;
            cArr[i3] = (char) ((i >>> (i2 * 8)) & 255);
            b(cArr[i3]);
        }
        return new String(cArr);
    }

    private static String b(int i) {
        String str = " ";
        for (int i2 = 0; i2 < 32; i2++) {
            str = str + (((Integer.MIN_VALUE >>> i2) & i) >>> (31 - i2));
        }
        return str;
    }

    public static int b(byte[] bArr) {
        return ((bArr[0] & UByte.MAX_VALUE) << 24) | (bArr[3] & UByte.MAX_VALUE) | ((bArr[2] & UByte.MAX_VALUE) << 8) | ((bArr[1] & UByte.MAX_VALUE) << 16);
    }
}