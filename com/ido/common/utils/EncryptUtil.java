package com.ido.common.utils;

import com.realsil.sdk.dfu.DfuConstants;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes2.dex */
public class EncryptUtil {
    public static String stringToSHA(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bytes);
            return toHexString(messageDigest.digest());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String toHexString(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int length = bArr.length;
        char[] cArr2 = new char[length * 2];
        for (int i = 0; i < length; i++) {
            byte b2 = bArr[i];
            int i2 = i * 2;
            cArr2[i2] = cArr[(b2 >>> 4) & 15];
            cArr2[i2 + 1] = cArr[b2 & DfuConstants.BANK_INFO_NOT_SUPPORTED];
        }
        return new String(cArr2);
    }
}