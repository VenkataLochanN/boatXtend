package com.amap.api.mapcore.util;

import android.content.Context;
import java.lang.Thread;

/* JADX INFO: compiled from: BasicLogHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class hk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static hk f1238a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Thread.UncaughtExceptionHandler f1239b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f1240c = true;

    protected void a() {
    }

    protected void a(Context context, gs gsVar, boolean z) {
    }

    protected void a(gs gsVar, String str, String str2) {
    }

    protected void a(Throwable th, int i, String str, String str2) {
    }

    public static void a(Throwable th, String str, String str2) {
        hk hkVar = f1238a;
        if (hkVar != null) {
            hkVar.a(th, 1, str, str2);
        }
    }
}