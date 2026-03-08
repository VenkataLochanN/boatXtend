package com.ido.life.util;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class AESUtils {
    private static final String ALGORITHM = "AES";
    private static final String CHARSET = "UTF-8";
    private static final String IV_STRING = "IDOOCLOUD-16Byte";
    private static final int KEY_SIZE = 128;

    public static String encrypt(String str, String str2, String str3) {
        return toHex(encrypt(str.getBytes(), str2));
    }

    public static String decrypt(String str, String str2, String str3) {
        return new String(decrypt(fromHex(str), str2));
    }

    public static String getSecretKey() {
        return getSecretKey(null);
    }

    public static String getSecretKey(String str) {
        SecureRandom secureRandom;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            if (str != null && !"".equals(str)) {
                secureRandom = new SecureRandom(str.getBytes());
            } else {
                secureRandom = new SecureRandom();
            }
            keyGenerator.init(128, secureRandom);
            return toHex(keyGenerator.generateKey().getEncoded());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] encrypt(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(toKey(fromHex(str)).getEncoded(), ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_STRING.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] decrypt(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(toKey(fromHex(str)).getEncoded(), ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_STRING.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Key toKey(byte[] bArr) throws Exception {
        return new SecretKeySpec(bArr, ALGORITHM);
    }

    public static String toHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static byte[] fromHex(String str) {
        if (str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }

    public static String getUrlEncoderString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static void main(String[] strArr) throws Exception {
        System.out.println("446483B863BE6090B754F5C3CFF71B1637DD392371A1DFDB46048D630AFF646CF0CED18ED266B68532A69DB25644AE12F0A9294D111361C05770A9B956854F1EB78901324EB095392EFB80A47045CA0E40BAD765F6BC83D0EF1496E1AD25D584EB76495B33D2E54235CA4DA799A89DF6F704CA1EC24A7D891167D131DA9578873BF141FB801B431397317A22290295ECC9716AE608153EF97A1FFFB09AC4A3F45C3B752F5FF20D16DFC1D3ED1F50004A8C239F175F18A9781BE1985029F881EC88BE755E98FB1DD20725028475890CF0027EC02542C7580921FF24FE56498C887E8C98403DD7E57949E63EA688EDF3CF");
        System.out.println(decrypt("446483B863BE6090B754F5C3CFF71B1637DD392371A1DFDB46048D630AFF646CF0CED18ED266B68532A69DB25644AE12F0A9294D111361C05770A9B956854F1EB78901324EB095392EFB80A47045CA0E40BAD765F6BC83D0EF1496E1AD25D584EB76495B33D2E54235CA4DA799A89DF6F704CA1EC24A7D891167D131DA9578873BF141FB801B431397317A22290295ECC9716AE608153EF97A1FFFB09AC4A3F45C3B752F5FF20D16DFC1D3ED1F50004A8C239F175F18A9781BE1985029F881EC88BE755E98FB1DD20725028475890CF0027EC02542C7580921FF24FE56498C887E8C98403DD7E57949E63EA688EDF3CF", "B273BD38E266E80B4B477A875060628A7EBD7CF16171186C676E7E6C1CC9D8AA", null));
    }
}