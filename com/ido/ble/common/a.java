package com.ido.ble.common;

import android.os.Build;
import android.util.Base64;
import com.bumptech.glide.load.Key;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static String a() {
        KeyGenerator keyGenerator;
        SecureRandom secureRandom = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            try {
                secureRandom = Build.VERSION.SDK_INT >= 17 ? SecureRandom.getInstance("SHA1PRNG", "Crypto") : SecureRandom.getInstance("SHA1PRNG");
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (Exception e3) {
            e = e3;
            keyGenerator = null;
        }
        keyGenerator.init(256, secureRandom);
        return a(keyGenerator.generateKey().getEncoded());
    }

    public static String a(String str, String str2) {
        if (str2.length() == 0 || str2 == null || str.length() == 0 || str == null) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpecA = a(str2);
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            byte[] bArrDecode = Base64.decode(str, 0);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, secretKeySpecA, ivParameterSpec);
            return new String(cipher.doFinal(bArrDecode), Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvalidAlgorithmParameterException e3) {
            e3.printStackTrace();
            return "";
        } catch (InvalidKeyException e4) {
            e4.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e5) {
            e5.printStackTrace();
            return "";
        } catch (BadPaddingException e6) {
            e6.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e7) {
            e7.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e8) {
            e8.printStackTrace();
            return "";
        }
    }

    public static String a(byte[] bArr) {
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

    private static SecretKeySpec a(String str) {
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 0);
        byte[] bArrB = b(str);
        System.arraycopy(bArrB, 0, bArr, 0, bArrB.length < bArr.length ? bArrB.length : bArr.length);
        return new SecretKeySpec(bArr, "AES");
    }

    public static String b(String str, String str2) {
        if (str2.length() == 0 || str2 == null || str.length() == 0 || str == null) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpecA = a(str2);
            byte[] bytes = str.getBytes("UTF8");
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, secretKeySpecA, ivParameterSpec);
            return Base64.encodeToString(cipher.doFinal(bytes), 0);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvalidAlgorithmParameterException e3) {
            e3.printStackTrace();
            return "";
        } catch (InvalidKeyException e4) {
            e4.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e5) {
            e5.printStackTrace();
            return "";
        } catch (BadPaddingException e6) {
            e6.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e7) {
            e7.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e8) {
            e8.printStackTrace();
            return "";
        }
    }

    private static byte[] b(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }
}