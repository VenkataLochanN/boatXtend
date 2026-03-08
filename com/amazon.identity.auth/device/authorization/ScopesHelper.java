package com.amazon.identity.auth.device.authorization;

import android.text.TextUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public final class ScopesHelper {
    private static final String ESCAPE_LITERAL_REGEX_PLUS = "\\+";
    private static final String LOG_TAG = ScopesHelper.class.getName();
    private static final String SEPARATOR = " ";

    private ScopesHelper() {
    }

    public static String getScopesString(String[] strArr) {
        return TextUtils.join(SEPARATOR, strArr);
    }

    public static String[] getScopesFromString(String str) {
        MAPLog.i(LOG_TAG, "Extracting scope string array from " + str);
        if (str.contains(SEPARATOR)) {
            return TextUtils.split(str, SEPARATOR);
        }
        return TextUtils.split(str, ESCAPE_LITERAL_REGEX_PLUS);
    }
}