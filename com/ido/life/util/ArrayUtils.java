package com.ido.life.util;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ArrayUtils {
    public static int getNumInArray(String str, String[] strArr) {
        if (!TextUtils.isEmpty(str) && strArr != null && strArr.length != 0) {
            for (int i = 0; i < strArr.length; i++) {
                if (str.equals(strArr[i])) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static int getNumInArray(int i, int[] iArr) {
        if (iArr != null && iArr.length != 0) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (i == iArr[i2]) {
                    return i2;
                }
            }
        }
        return 0;
    }
}