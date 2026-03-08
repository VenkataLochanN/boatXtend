package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5777a = "CrashReport";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5778b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f5779c = "CrashReportInfo";

    private static boolean a(int i, String str, Object... objArr) {
        if (!f5778b) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
        }
        if (i == 0) {
            Log.i(f5777a, str);
            return true;
        }
        if (i == 1) {
            Log.d(f5777a, str);
            return true;
        }
        if (i == 2) {
            Log.w(f5777a, str);
            return true;
        }
        if (i == 3) {
            Log.e(f5777a, str);
            return true;
        }
        if (i != 5) {
            return false;
        }
        Log.i(f5779c, str);
        return true;
    }

    public static boolean a(String str, Object... objArr) {
        return a(0, str, objArr);
    }

    public static boolean a(Class cls, String str, Object... objArr) {
        return a(0, String.format(Locale.US, "[%s] %s", cls.getSimpleName(), str), objArr);
    }

    public static boolean b(String str, Object... objArr) {
        return a(5, str, objArr);
    }

    public static boolean c(String str, Object... objArr) {
        return a(1, str, objArr);
    }

    public static boolean b(Class cls, String str, Object... objArr) {
        return a(1, String.format(Locale.US, "[%s] %s", cls.getSimpleName(), str), objArr);
    }

    public static boolean d(String str, Object... objArr) {
        return a(2, str, objArr);
    }

    public static boolean a(Throwable th) {
        if (f5778b) {
            return a(2, ab.a(th), new Object[0]);
        }
        return false;
    }

    public static boolean e(String str, Object... objArr) {
        return a(3, str, objArr);
    }

    public static boolean b(Throwable th) {
        if (f5778b) {
            return a(3, ab.a(th), new Object[0]);
        }
        return false;
    }
}