package com.realsil.sdk.core.utility;

import android.text.TextUtils;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuConstants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class DataConverter {
    public static String bytes2Hex(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr != null) {
            for (byte b2 : bArr) {
                String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    hexString = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + hexString;
                }
                sb.append(hexString);
            }
        }
        return sb.toString().toUpperCase().trim();
    }

    public static String bytes2HexWithSeparate(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr != null) {
            for (byte b2 : bArr) {
                String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    hexString = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + hexString;
                }
                sb.append(hexString);
                sb.append(" ");
            }
        }
        return sb.toString().toUpperCase().trim();
    }

    public static String bytes2Str(byte[] bArr) {
        int length = bArr.length;
        String str = "";
        for (byte b2 : bArr) {
            str = str + ((char) b2);
        }
        return str;
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        if (inputStream == null || outputStream == null) {
            return;
        }
        byte[] bArr = new byte[i];
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 < 0) {
                return;
            } else {
                outputStream.write(bArr, 0, i2);
            }
        }
    }

    public static String ellipsize(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() < 3) {
            return str;
        }
        return str.charAt(0) + "⋯" + str.charAt(str.length() - 1);
    }

    public static byte[] hex2BigBytes(String str) {
        int length = str.length();
        int i = length / 2;
        byte[] bArr = new byte[i];
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        if (length % 2 == 1) {
            return null;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if ((charArray[i2] < '0' || charArray[i2] > '9') && (charArray[i2] < 'A' || charArray[i2] > 'F')) {
                return null;
            }
        }
        for (int i3 = 0; i3 < length; i3 += 2) {
            bArr[(i - (i3 / 2)) - 1] = (byte) ((Character.digit(upperCase.charAt(i3), 16) << 4) + Character.digit(upperCase.charAt(i3 + 1), 16));
        }
        return bArr;
    }

    public static byte[] hex2Bytes(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            int length = str.length();
            byte[] bArr = new byte[length / 2];
            String upperCase = str.toUpperCase();
            char[] charArray = upperCase.toCharArray();
            if (length % 2 == 1) {
                return null;
            }
            for (int i = 0; i < length; i++) {
                if ((charArray[i] < '0' || charArray[i] > '9') && (charArray[i] < 'A' || charArray[i] > 'F')) {
                    return null;
                }
            }
            for (int i2 = 0; i2 < length; i2 += 2) {
                bArr[i2 / 2] = (byte) ((Character.digit(upperCase.charAt(i2), 16) << 4) + Character.digit(upperCase.charAt(i2 + 1), 16));
            }
            return bArr;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String hex2Str(String str) {
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[upperCase.length() / 2];
        if (upperCase.length() % 2 == 1) {
            return "";
        }
        for (int i = 0; i < upperCase.length(); i++) {
            if ((charArray[i] < '0' || charArray[i] > '9') && (charArray[i] < 'A' || charArray[i] > 'F')) {
                return "";
            }
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) ((("0123456789ABCDEF".indexOf(charArray[i3]) * 16) + "0123456789ABCDEF".indexOf(charArray[i3 + 1])) & 255);
        }
        return new String(bArr);
    }

    public static int littleEndianByteArrayToInt(byte[] bArr) {
        int length = bArr.length;
        int iUnsignedByteToInt = 0;
        if (length == 0) {
            return 0;
        }
        for (int i = length - 1; i >= 0; i--) {
            iUnsignedByteToInt += unsignedByteToInt(bArr[i]) << (i * 8);
        }
        return iUnsignedByteToInt;
    }

    public static byte[] reverse(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            bArr2[i] = bArr[length];
            i++;
        }
        return bArr2;
    }

    public static void safeCloseStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable th) {
                ZLogger.d("Error closing stream: " + th.toString());
            }
        }
    }

    public static void safeCloseStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable th) {
                ZLogger.d("Error closing stream: " + th.toString());
            }
        }
    }

    public static byte[] str2Bytes(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Argument str ( String ) is null! ");
        }
        byte[] bArr = new byte[str.length() / 2];
        try {
            return str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return bArr;
        }
    }

    public static String str2Hex(String str) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (byte b2 : str.getBytes()) {
            sb.append(charArray[(b2 & 240) >> 4]);
            sb.append(charArray[b2 & DfuConstants.BANK_INFO_NOT_SUPPORTED]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static String str2Unicode(String str) {
        StringBuilder sb;
        String str2;
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            String hexString = Integer.toHexString(cCharAt);
            if (cCharAt > 128) {
                sb = new StringBuilder();
                str2 = "\\u";
            } else {
                sb = new StringBuilder();
                str2 = "\\u00";
            }
            sb.append(str2);
            sb.append(hexString);
            sb2.append(sb.toString());
        }
        return sb2.toString();
    }

    public static String unicode2Str(String str) {
        int length = str.length() / 6;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < length) {
            int i2 = i * 6;
            i++;
            String strSubstring = str.substring(i2, i * 6);
            sb.append(new String(Character.toChars(Integer.valueOf(strSubstring.substring(2, 4) + "00", 16).intValue() + Integer.valueOf(strSubstring.substring(4), 16).intValue())));
        }
        return sb.toString();
    }

    public static int unsignedByteToInt(byte b2) {
        return b2 & UByte.MAX_VALUE;
    }
}