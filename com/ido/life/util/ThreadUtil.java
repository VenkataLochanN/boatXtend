package com.ido.life.util;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes3.dex */
public class ThreadUtil {
    public static void runOnMainThread(Runnable runnable) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            handler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public static void delayTask(Runnable runnable, int i) {
        new Handler(Looper.getMainLooper()).postDelayed(runnable, i);
    }
}