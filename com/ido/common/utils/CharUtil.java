package com.ido.common.utils;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class CharUtil {
    public static boolean isLetter(char c2) {
        return (c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z');
    }

    public static int calculateCharNum(String str) {
        if (str == null) {
            return 0;
        }
        char[] charArray = str.toCharArray();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < charArray.length; i3++) {
            if (((char) ((byte) charArray[i3])) != charArray[i3]) {
                i2++;
            } else {
                i++;
            }
        }
        if (i % 2 == 0) {
            return i2 + (i / 2);
        }
        return i2 + (i / 2) + 1;
    }

    public static int calulateCharRow(String str) {
        if (str == null) {
            return 0;
        }
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) == '\n') {
                i++;
            }
        }
        return i + 1;
    }

    public static String getHeadChar(String str) {
        if (TextUtils.isEmpty(str)) {
            return "#unknow";
        }
        String selling = CharacterParser.getInstance().getSelling(str);
        return TextUtils.isEmpty(selling) ? "#unknow" : selling.toUpperCase().substring(0, 1);
    }

    public static Object[] transInteger2Object(int[] iArr) {
        Object[] objArr = new Object[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            objArr[i] = Integer.valueOf(iArr[i]);
        }
        return objArr;
    }
}