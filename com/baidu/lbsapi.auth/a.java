package com.baidu.lbsapi.auth;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f1989a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f1990b = "BaiduApiAuth";

    public static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return stackTraceElement.getFileName() + "[" + stackTraceElement.getLineNumber() + "]";
    }

    public static void a(String str) {
        if (!f1989a || Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.d(f1990b, a() + ";" + str);
    }

    public static void b(String str) {
        if (Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.i(f1990b, str);
    }

    public static void c(String str) {
        if (!f1989a || Thread.currentThread().getStackTrace().length == 0) {
            return;
        }
        Log.e(f1990b, a() + ";" + str);
    }
}