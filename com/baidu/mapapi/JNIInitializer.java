package com.baidu.mapapi;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class JNIInitializer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Context f2706a;

    public static Context getCachedContext() {
        return f2706a;
    }

    public static void setContext(Application application) {
        if (application == null) {
            throw new RuntimeException();
        }
        if (f2706a == null) {
            f2706a = application;
        }
    }
}