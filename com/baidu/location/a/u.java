package com.baidu.location.a;

import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static HandlerThread f2178a;

    public static synchronized HandlerThread a() {
        if (f2178a == null) {
            try {
                f2178a = new HandlerThread("ServiceStartArguments", 10);
                f2178a.start();
            } catch (Throwable th) {
                th.printStackTrace();
                f2178a = null;
            }
        }
        return f2178a;
    }
}