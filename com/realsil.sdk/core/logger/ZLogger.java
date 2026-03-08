package com.realsil.sdk.core.logger;

import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ZLogger {
    public static final int ASSET = 6;
    public static final int DEBUG = 2;
    public static final int ERROR = 5;
    public static int GLOBAL_LOG_LEVEL = 1;
    public static final int INFO = 3;
    public static boolean LOG_ENABLED = false;
    public static String TAG = "Realtek";
    public static final int VERBOSE = 1;
    public static final int WARN = 4;

    public static String a(Object... objArr) {
        if (objArr.length <= 1) {
            Object obj = objArr[0];
            return obj == null ? "null" : obj.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        for (int i = 0; i < objArr.length; i++) {
            Object obj2 = objArr[i];
            sb.append("Param");
            sb.append("[");
            sb.append(i);
            sb.append("]");
            sb.append(" = ");
            if (obj2 == null) {
                sb.append("null");
            } else {
                sb.append(obj2.toString());
            }
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        return sb.toString();
    }

    public static void a(int i, String str, String str2) {
        int iMax = Math.max(i, GLOBAL_LOG_LEVEL);
        if ((iMax & 6) == 6) {
            Log.wtf(str, str2);
            return;
        }
        if ((iMax & 5) == 5) {
            Log.e(str, str2);
            return;
        }
        if ((iMax & 4) == 4) {
            Log.w(str, str2);
            return;
        }
        if ((iMax & 3) == 3) {
            Log.i(str, str2);
        } else if ((iMax & 2) == 2) {
            Log.d(str, str2);
        } else {
            Log.v(str, str2);
        }
    }

    public static void a(boolean z, int i, String str, Object obj) {
        if (z) {
            String[] strArrA = a(str, obj);
            if (strArrA == null || strArrA.length < 3) {
                a(i, str, (String) obj);
                return;
            }
            String str2 = strArrA[0];
            String str3 = strArrA[1];
            a(i, str2, strArrA[2] + str3);
        }
    }

    public static String[] a(String str, Object... objArr) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length < 6) {
            return null;
        }
        String className = stackTrace[5].getClassName();
        String[] strArrSplit = className.split("\\.");
        if (strArrSplit.length > 0) {
            className = strArrSplit[strArrSplit.length - 1] + ".java";
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0] + ".java";
        }
        String methodName = stackTrace[5].getMethodName();
        int lineNumber = stackTrace[5].getLineNumber();
        if (lineNumber < 0) {
            lineNumber = 0;
        }
        String str2 = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        if (str == null) {
            str = className;
        }
        if (TextUtils.isEmpty(str)) {
            str = TAG;
        }
        return new String[]{str, objArr == null ? "" : a(objArr), String.format(Locale.US, "[ (%s:%d)#%s ]", className, Integer.valueOf(lineNumber), str2)};
    }

    public static void d(String str) {
        a(LOG_ENABLED, 2, TAG, str);
    }

    public static void d(boolean z, String str) {
        a(z, 2, TAG, str);
    }

    public static void d(boolean z, String str, String str2) {
        a(z, 2, str, str2);
    }

    public static void e(String str) {
        a(LOG_ENABLED, 5, TAG, str);
    }

    public static void e(boolean z, String str) {
        a(z, 5, TAG, str);
    }

    public static void e(boolean z, String str, String str2) {
        a(z, 5, str, str2);
    }

    public static void i(String str) {
        a(LOG_ENABLED, 3, TAG, str);
    }

    public static void i(boolean z, String str) {
        a(z, 3, TAG, str);
    }

    public static void i(boolean z, String str, String str2) {
        a(z, 3, str, str2);
    }

    public static void initialize(String str, boolean z) {
        TAG = str;
        LOG_ENABLED = z;
    }

    public static void v(String str) {
        a(LOG_ENABLED, 1, TAG, str);
    }

    public static void v(boolean z, String str) {
        a(z, 1, TAG, str);
    }

    public static void v(boolean z, String str, String str2) {
        a(z, 1, str, str2);
    }

    public static void w(String str) {
        a(LOG_ENABLED, 4, TAG, str);
    }

    public static void w(boolean z, String str) {
        a(z, 4, TAG, str);
    }

    public static void w(boolean z, String str, String str2) {
        a(z, 4, str, str2);
    }
}