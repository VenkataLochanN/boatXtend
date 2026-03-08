package com.amazon.identity.auth.device.utils;

import java.security.InvalidParameterException;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MAPVersionHelper {
    private static final String DELIMETER = "\\.";

    private MAPVersionHelper() {
    }

    public static String getVersionAsString(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : iArr) {
            stringBuffer.append(i);
            stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public static int[] getVersionInfo(String str) {
        String[] strArrSplit = str.split(DELIMETER);
        if (strArrSplit.length >= 1) {
            int[] iArr = new int[strArrSplit.length];
            try {
                int length = strArrSplit.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    iArr[i2] = Integer.parseInt(strArrSplit[i]);
                    i++;
                    i2 = i3;
                }
                return iArr;
            } catch (NumberFormatException unused) {
                throw new InvalidParameterException("getVersionInfo: version (" + str + ") must be in format of X[.X.X]... - where X must be an integer");
            }
        }
        throw new InvalidParameterException("version (" + str + ") must be in format of X.X.X");
    }
}