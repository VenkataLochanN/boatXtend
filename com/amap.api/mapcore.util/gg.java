package com.amap.api.mapcore.util;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.bumptech.glide.load.Key;

/* JADX INFO: compiled from: AESMD5Util.java */
/* JADX INFO: loaded from: classes.dex */
public class gg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f1040a = 6;

    private static byte[] a() {
        try {
            return a("16,99,86,77,511,98,86,97,511,99,86,77,511,18,48,97,511,99,86,77,511,58,601,77,511,58,48,77,511,58,86,87,511,18,48,97,511,58,86,87,511,18,48,97,511,98,48,87,511,98,48,97,511,99,86,77,511,58,221,77,511,98,601,87");
        } catch (Throwable th) {
            hn.c(th, "AMU", "grk");
            return null;
        }
    }

    private static byte[] b() {
        try {
            return a("16,18,86,97,511,18,48,97,511,18,86,97,511,58,86,77,511,18,86,97,511,58,48,77,511,18,86,97,511,58,601,77,511,18,86,97,511,58,221,77,511,18,86,97,511,58,86,87,511,18,86,97,511,58,48,87,511,18,86,97,511,58,601,87");
        } catch (Throwable th) {
            hn.c(th, "AMU", "giv");
            return null;
        }
    }

    private static byte[] a(String str) {
        try {
            String[] strArrSplit = new StringBuffer(str).reverse().toString().split(AppInfo.DELIM);
            byte[] bArr = new byte[strArrSplit.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = Byte.parseByte(strArrSplit[i]);
            }
            String[] strArrSplit2 = new StringBuffer(new String(gx.a(new String(bArr)), Key.STRING_CHARSET_NAME)).reverse().toString().split(AppInfo.DELIM);
            byte[] bArr2 = new byte[strArrSplit2.length];
            for (int i2 = 0; i2 < strArrSplit2.length; i2++) {
                bArr2[i2] = Byte.parseByte(strArrSplit2[i2]);
            }
            return bArr2;
        } catch (Throwable th) {
            hn.c(th, "AMU", "rcs");
            return null;
        }
    }

    public static byte[] a(byte[] bArr) {
        try {
            return gn.b(a(), bArr, b());
        } catch (Throwable unused) {
            return new byte[0];
        }
    }

    public static byte[] b(byte[] bArr) {
        try {
            return gn.a(a(), bArr, b());
        } catch (Exception unused) {
            return new byte[0];
        }
    }
}