package com.baidu.mapsdkplatform.comapi.synchronization.d;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3699a = true;

    private static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return stackTraceElement.getFileName() + ": Line " + stackTraceElement.getLineNumber();
    }

    public static void a(String str, String str2) {
        if (f3699a) {
            Log.d(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f3699a) {
            Log.e(str, str2, th);
        }
    }

    public static void a(boolean z) {
        f3699a = z;
    }

    private static String b() {
        return new Throwable().getStackTrace()[2].getMethodName();
    }

    public static void b(String str, String str2) {
        if (f3699a) {
            Log.e(str, str2);
        }
    }

    public static void c(String str, String str2) {
        Log.d(str + "-" + b() + "-" + a(), str2);
    }
}