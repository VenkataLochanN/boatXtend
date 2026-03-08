package com.ido.life.util;

import com.amap.api.mapcore.util.hl;
import com.loc.z;
import com.realsil.sdk.bbpro.core.protocol.params.Parameters;

/* JADX INFO: loaded from: classes3.dex */
public class ChineseToFirstCharUtil {
    public static String convertTo(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            str2 = (cCharAt >= '!' && cCharAt <= '~') ? str2 + String.valueOf(cCharAt) : str2 + getPYChar(String.valueOf(cCharAt));
        }
        return str2;
    }

    public static String convertAndClear(String str) {
        return convertTo(str).replace("*", "");
    }

    public static String convertAndClearAll(String str) {
        String strReplace = convertTo(str).replace("*", "");
        StringBuilder sb = new StringBuilder();
        for (char c2 : strReplace.toCharArray()) {
            if ((c2 >= '0' && c2 <= '9') || ((c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z'))) {
                sb.append(c2);
            }
        }
        return sb.toString();
    }

    private static String getPYChar(String str) {
        byte[] bytes = String.valueOf(str).getBytes();
        int i = (((short) ((bytes[0] - 0) + 256)) * Parameters.EQ_INDEX.BUILD_IN_EQ_5) + ((short) ((bytes[1] - 0) + 256));
        return i < 45217 ? "*" : i < 45253 ? "a" : i < 45761 ? "b" : i < 46318 ? "c" : i < 46826 ? "d" : i < 47010 ? "e" : i < 47297 ? "f" : i < 47614 ? "g" : i < 48119 ? "h" : i < 49062 ? z.j : i < 49324 ? hl.k : i < 49896 ? "l" : i < 50371 ? "m" : i < 50614 ? "n" : i < 50622 ? "o" : i < 50906 ? "p" : i < 51387 ? "q" : i < 51446 ? "r" : i < 52218 ? "s" : i < 52698 ? "t" : i < 52980 ? "w" : i < 53689 ? "x" : i < 54481 ? "y" : i < 55290 ? "z" : "*";
    }
}