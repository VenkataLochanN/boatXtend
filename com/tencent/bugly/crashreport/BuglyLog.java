package com.tencent.bugly.crashreport;

import android.util.Log;
import com.tencent.bugly.b;
import com.tencent.bugly.proguard.aa;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class BuglyLog {
    public static void v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.f5373c) {
            Log.v(str, str2);
        }
        aa.a("V", str, str2);
    }

    public static void d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.f5373c) {
            Log.d(str, str2);
        }
        aa.a("D", str, str2);
    }

    public static void i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.f5373c) {
            Log.i(str, str2);
        }
        aa.a("I", str, str2);
    }

    public static void w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.f5373c) {
            Log.w(str, str2);
        }
        aa.a("W", str, str2);
    }

    public static void e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.f5373c) {
            Log.e(str, str2);
        }
        aa.a("E", str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (b.f5373c) {
            Log.e(str, str2, th);
        }
        aa.a("E", str, th);
    }

    public static void setCache(int i) {
        aa.a(i);
    }
}