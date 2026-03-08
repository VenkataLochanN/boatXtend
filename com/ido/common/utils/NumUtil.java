package com.ido.common.utils;

import android.text.TextUtils;
import android.util.Pair;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes2.dex */
public class NumUtil {
    public static int decimal2Binary(int i, int i2, int i3) {
        return (i << i3) | i2;
    }

    public static boolean isInteger(float f2) {
        return ((float) ((int) f2)) == f2;
    }

    public static boolean isNumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static Pair<Integer, Integer> binary2Decimal(int i, int i2) {
        return new Pair<>(Integer.valueOf(i >> i2), Integer.valueOf((int) (((long) (Math.pow(2.0d, i2) - 1.0d)) & ((long) i))));
    }

    public static String float2String(float f2, int i) {
        double d2 = i;
        float fRound = Math.round(((int) Math.pow(10.0d, d2)) * f2) / ((int) Math.pow(10.0d, d2));
        if (fRound == Math.round(f2)) {
            return String.valueOf(Math.round(f2));
        }
        String strValueOf = String.valueOf(fRound);
        while (strValueOf.endsWith(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE)) {
            strValueOf = strValueOf.substring(0, strValueOf.lastIndexOf(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE));
        }
        System.out.println("str=" + strValueOf);
        return strValueOf;
    }

    public static boolean strIsChangeInt(String str) {
        return str.matches("[0-9]+");
    }

    public static String[] getStringArray(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                i = -1;
                break;
            }
            int i2 = i + 1;
            String strSubstring = str.substring(i, i2);
            if (!strIsChangeInt(strSubstring)) {
                break;
            }
            sb.append(strSubstring);
            i = i2;
        }
        return new String[]{sb.toString(), i + ""};
    }

    public static String format2Point(double d2) {
        return String.valueOf(formatPoint(d2, 2));
    }

    public static double formatPoint(double d2, int i) {
        double dPow = Math.pow(10.0d, i);
        return ((double) ((int) (d2 * dPow))) / dPow;
    }

    public static int getIsNumIndex(String str) {
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            if (!strIsChangeInt(str.substring(i, i2))) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    public static float float2float(float f2) {
        return new BigDecimal(f2).setScale(2, 6).floatValue();
    }

    public static String save2Point(float f2) {
        try {
            return new DecimalFormat("##0.00").format(f2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Integer isEmptyInt(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        return parseInt(str);
    }

    public static Integer parseInt(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String isEmptyStr(String str) {
        return (str == null || str.equals("")) ? "" : str;
    }

    public static Float isEmptyFloat(String str) {
        return Float.valueOf(parseFloat(str));
    }

    public static float parseFloat(String str) {
        if (str != null && !str.equals("")) {
            if (str.contains(AppInfo.DELIM)) {
                str = str.replaceAll(AppInfo.DELIM, ".");
            }
            try {
                return Float.parseFloat(str);
            } catch (Exception unused) {
            }
        }
        return -1.0f;
    }

    public static float valueOf(String str) {
        return parseFloat(str);
    }

    public static int range(int i, int i2, int i3) {
        return Math.max(Math.min(i2, i), i3);
    }

    public static String numberFormat(int i) {
        if (i < 10 && i >= 0) {
            return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i;
        }
        return String.valueOf(i);
    }
}