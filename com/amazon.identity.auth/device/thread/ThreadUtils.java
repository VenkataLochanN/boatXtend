package com.amazon.identity.auth.device.thread;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final class ThreadUtils {
    public static final Executor THREAD_POOL = Executors.newCachedThreadPool(new ThreadFactory() { // from class: com.amazon.identity.auth.device.thread.ThreadUtils.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AmazonAuthorzationLibrary#" + ThreadUtils.access$004());
        }
    });
    private static int sThreadNum;

    static /* synthetic */ int access$004() {
        int i = sThreadNum + 1;
        sThreadNum = i;
        return i;
    }

    private ThreadUtils() {
    }

    public static boolean isRunningOnMainThread() {
        return Looper.getMainLooper() != null && Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnMainThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public static void submitToBackgroundThread(Runnable runnable) {
        THREAD_POOL.execute(runnable);
    }

    public static void runOffMainThread(Runnable runnable) {
        if (!isRunningOnMainThread()) {
            runnable.run();
        } else {
            submitToBackgroundThread(runnable);
        }
    }
}