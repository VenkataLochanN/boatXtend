package com.ido.common.utils;

import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class Md5Util {
    public static String md5(String str) {
        try {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes(Charset.defaultCharset());
            messageDigest.update(bytes, 0, bytes.length);
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder(32);
            for (byte b2 : bArrDigest) {
                sb.append(cArr[(b2 >> 4) & 15]);
                sb.append(cArr[(b2 >> 0) & 15]);
            }
            str = sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        return str.toUpperCase();
    }

    public static String md5(File file) throws Throwable {
        FileInputStream fileInputStream;
        MessageDigest messageDigest;
        int i;
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }
        FileInputStream fileInputStream2 = null;
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[8192];
        try {
            try {
                try {
                    messageDigest = MessageDigest.getInstance("MD5");
                    fileInputStream = new FileInputStream(file);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        while (true) {
            try {
                int i2 = fileInputStream.read(bArr);
                if (i2 == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i2);
            } catch (Exception e4) {
                e = e4;
                fileInputStream2 = fileInputStream;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return sb.toString().toUpperCase();
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
            return sb.toString().toUpperCase();
        }
        for (byte b2 : messageDigest.digest()) {
            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                hexString = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + hexString;
            }
            sb.append(hexString);
        }
        fileInputStream.close();
        return sb.toString().toUpperCase();
    }
}