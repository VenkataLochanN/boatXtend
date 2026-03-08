package com.ido.common.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.bumptech.glide.load.Key;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class AES256 {
    public static String AES256Encode(String str, String str2) throws NullPointerException {
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

    public static String AES256Decrypt(String str, String str2) {
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
            return new String(cipher.doFinal(bArrDecode), Key.STRING_CHARSET_NAME);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private static SecretKeySpec getKey(String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 0);
        byte[] bArr2 = toByte(str);
        System.arraycopy(bArr2, 0, bArr, 0, Math.min(bArr2.length, bArr.length));
        return new SecretKeySpec(bArr, "AES");
    }

    public static String getRawKey() {
        KeyGenerator keyGenerator;
        SecureRandom secureRandom = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            try {
                if (Build.VERSION.SDK_INT >= 17) {
                    secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
                } else {
                    secureRandom = SecureRandom.getInstance("SHA1PRNG");
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (Exception e3) {
            e = e3;
            keyGenerator = null;
        }
        keyGenerator.init(256, secureRandom);
        return bytes2Hex(keyGenerator.generateKey().getEncoded());
    }

    public static String bytes2Hex(byte[] bArr) {
        String str = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                str = str + AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            }
            str = str + hexString;
        }
        return str;
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