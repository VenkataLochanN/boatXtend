package com.ido.ble.bluetooth.e;

import android.text.TextUtils;
import androidx.core.view.MotionEventCompat;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class d {
    private static byte a(char c2) {
        return (byte) "0123456789ABCDEF".indexOf(c2);
    }

    public static String a(String str) {
        String upperCase = str.substring(str.length() - 1).toUpperCase(Locale.getDefault());
        String upperCase2 = str.substring(str.length() - 2, str.length() - 1).toUpperCase(Locale.getDefault());
        String strSubstring = str.substring(0, str.length() - 2);
        boolean zEquals = "F".equals(upperCase);
        String upperCase3 = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        if (zEquals) {
            upperCase2 = "F".equals(upperCase2) ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : Integer.toHexString(Integer.parseInt(upperCase2, 16) + 1).toUpperCase(Locale.getDefault());
        } else {
            upperCase3 = Integer.toHexString(Integer.parseInt(upperCase, 16) + 1).toUpperCase(Locale.getDefault());
        }
        LogTool.d(b.f4128a, "macAddressAdd1:" + strSubstring + upperCase2 + upperCase3);
        return strSubstring + upperCase2 + upperCase3;
    }

    public static boolean a(String str, String str2, BLEDevice bLEDevice) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(bLEDevice.mDeviceAddress) || (!bLEDevice.mDeviceAddress.equalsIgnoreCase(str) && !bLEDevice.mDeviceAddress.equalsIgnoreCase(str2))) ? false : true;
    }

    public static String b(String str) {
        if (str.isEmpty()) {
            return "00:00:00:00:00:00";
        }
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length != 6) {
            return "00:00:00:00:00:00";
        }
        byte[] bArr = new byte[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            char[] charArray = strArrSplit[i].toCharArray();
            if (charArray.length == 1) {
                bArr[5 - i] = a(charArray[0]);
            } else {
                bArr[5 - i] = (byte) (a(charArray[1]) | (a(charArray[0]) << 4));
            }
            System.out.println(String.format("%02x", Byte.valueOf(bArr[5 - i])));
        }
        long j = ((bArr[5] << 16) & 16711680) + ((bArr[4] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[3] & UByte.MAX_VALUE);
        long j2 = (16711680 & (bArr[2] << 16)) + (65280 & (bArr[1] << 8)) + (bArr[0] & UByte.MAX_VALUE);
        System.out.println(j2);
        long j3 = j2 + 1;
        System.out.println(j3);
        if (j3 > 16777215) {
            j++;
        }
        bArr[5] = (byte) ((j >> 16) & 255);
        bArr[4] = (byte) ((j >> 8) & 255);
        bArr[3] = (byte) (j & 255);
        System.out.println(j3);
        bArr[2] = (byte) ((j3 >> 16) & 255);
        bArr[1] = (byte) ((j3 >> 8) & 255);
        bArr[0] = (byte) (j3 & 255);
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[5]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[0]));
    }
}