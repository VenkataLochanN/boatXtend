package com.ido.alexa.util;

import android.text.TextUtils;
import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class AES256Encrypt {
    public static String encode(String str, String str2) throws NullPointerException {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            SecretKeySpec key = getKey(str2);
            byte[] bytes = str.getBytes("UTF8");
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, key, ivParameterSpec);
            return Base64.encodeToString(cipher.doFinal(bytes), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String decrypt(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            SecretKeySpec key = getKey(str2);
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            byte[] bArrDecode = Base64.decode(str, 0);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key, ivParameterSpec);
            return new String(cipher.doFinal(bArrDecode), StandardCharsets.UTF_8);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private static SecretKeySpec getKey(String str) {
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 0);
        byte[] bArr2 = toByte(str);
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length < bArr.length ? bArr2.length : bArr.length);
        return new SecretKeySpec(bArr, "AES");
    }

    private static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }
}