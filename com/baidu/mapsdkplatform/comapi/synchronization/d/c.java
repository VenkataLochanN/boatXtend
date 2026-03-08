package com.baidu.mapsdkplatform.comapi.synchronization.d;

import android.text.TextUtils;
import com.realsil.sdk.dfu.DfuConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3700a = c.class.getSimpleName();

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException e2) {
            a.a(f3700a, "NoSuchAlgorithmException happened when get MD5 string", e2);
            return null;
        }
    }

    private static String a(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b2 >>> 4) & 15];
            i = i2 + 1;
            cArr2[i2] = cArr[b2 & DfuConstants.BANK_INFO_NOT_SUPPORTED];
        }
        return new String(cArr2).toLowerCase();
    }
}