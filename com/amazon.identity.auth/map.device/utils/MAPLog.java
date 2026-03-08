package com.amazon.identity.auth.map.device.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.utils.DefaultLibraryInfo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class MAPLog {
    private static final String ENG = "eng";
    private static final int FLAVOR_INDEX = 2;
    private static final String LOG_TAG_PII = "com.amazon.identity.pii";
    private static final String MATCHER_PATTERN = "^(?:(.*?)_)??(?:([^_]*)_)?([0-9]+)$";
    private static final String NO_MESSAGE = "N/A";
    private static final int NUM_GROUPS = 3;
    private static final String OBSCURED = "<obscured>";
    private static final String PII_STRING = ".PII";
    private static final String SEPARATOR = ":";
    private static final String USERDEBUG = "userdebug";
    private static final String LOG_TAG = MAPLog.class.getName();
    public static boolean IS_FIRST_PARTY_DEBUG_BUILD = isFirstPartyDebugBuild();

    public static int pii(String str, String str2, String str3) {
        return pii(str, str2, str3, null);
    }

    public static int pii(String str, String str2, String str3, Throwable th) {
        if (str == null) {
            str = "NULL_TAG";
        }
        String str4 = str + PII_STRING;
        char c2 = 3;
        if (IS_FIRST_PARTY_DEBUG_BUILD) {
            c2 = 4;
        } else {
            if (DefaultLibraryInfo.isProd() && Log.isLoggable(LOG_TAG_PII, 3)) {
                return logPii(str4, str2, str3, th, 3);
            }
            if (!DefaultLibraryInfo.isProd()) {
                return logPii(str4, str2, str3, th, 3);
            }
            str3 = OBSCURED;
        }
        if (th != null) {
            if (c2 == 4) {
                return Log.i(str4, getUpdatedMessage(str2, str3), th);
            }
            return Log.d(str4, getUpdatedMessage(str2, str3), th);
        }
        if (c2 == 4) {
            return Log.i(str4, getUpdatedMessage(str2, str3));
        }
        return Log.d(str4, getUpdatedMessage(str2, str3));
    }

    private static int logPii(String str, String str2, String str3, Throwable th, int i) {
        if (th != null) {
            if (i == 4) {
                return Log.i(str, getUpdatedMessage(str2, str3), th);
            }
            return Log.d(str, getUpdatedMessage(str2, str3), th);
        }
        if (i == 4) {
            return Log.i(str, getUpdatedMessage(str2, str3));
        }
        return Log.d(str, getUpdatedMessage(str2, str3));
    }

    private static String getUpdatedMessage(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer(str);
        if (!TextUtils.isEmpty(str2)) {
            stringBuffer.append(SEPARATOR);
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    public static int d(String str, String str2) {
        return Log.d(str, str2);
    }

    public static int d(String str, String str2, Throwable th) {
        return Log.d(str, str2, th);
    }

    public static int e(String str, String str2) {
        return Log.e(str, str2);
    }

    public static int e(String str, String str2, Throwable th) {
        return Log.e(str, str2, th);
    }

    public static int i(String str, String str2) {
        return Log.i(str, str2);
    }

    public static int i(String str, String str2, Throwable th) {
        return Log.i(str, str2, th);
    }

    public static int v(String str, String str2) {
        return Log.v(str, str2);
    }

    public static int v(String str, String str2, Throwable th) {
        return Log.v(str, str2, th);
    }

    public static int w(String str, String str2) {
        return Log.w(str, str2);
    }

    public static int w(String str, Throwable th) {
        return Log.w(str, NO_MESSAGE, th);
    }

    public static int w(String str, String str2, Throwable th) {
        return Log.w(str, str2, th);
    }

    public static String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static boolean isLoggable(String str, int i) {
        return Log.isLoggable(str, i);
    }

    public static int println(int i, String str, String str2) {
        return Log.println(i, str, str2);
    }

    public static int wtf(String str, Throwable th) {
        return Log.wtf(str, th);
    }

    public static int wtf(String str, String str2) {
        return Log.wtf(str, str2);
    }

    public static int wtf(String str, String str2, Throwable th) {
        return Log.wtf(str, str2, th);
    }

    private static boolean isFirstPartyDebugBuild() {
        String str;
        try {
            str = Build.VERSION.INCREMENTAL;
        } catch (Exception e2) {
            e(LOG_TAG, e2.getMessage(), e2);
        }
        if (TextUtils.isEmpty(str)) {
            w(LOG_TAG, "Incremental version was empty");
            return false;
        }
        Pattern patternCompile = Pattern.compile(MATCHER_PATTERN);
        pii(LOG_TAG, "Extracting verison incremental", "Build.VERSION.INCREMENTAL: " + str);
        Matcher matcher = patternCompile.matcher(str);
        if (!matcher.find()) {
            pii(LOG_TAG, "Incremental version '%s' was in invalid format.", "ver=" + str);
            return false;
        }
        if (matcher.groupCount() < 3) {
            e(LOG_TAG, "Error parsing build version string.");
            return false;
        }
        String strGroup = matcher.group(2);
        pii(LOG_TAG, "Extracting flavor", "Build flavor: " + strGroup);
        if (!TextUtils.isEmpty(strGroup) && (strGroup.equals(USERDEBUG) || strGroup.equals(ENG))) {
            i(LOG_TAG, "MAP is running on 1st party debug");
            return true;
        }
        return false;
    }
}