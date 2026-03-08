package com.ido.common.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class AppExecutors {
    private static final int THREAD_COUNT = 3;
    private static volatile AppExecutors sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    private AppExecutors(Executor executor, Executor executor2, Executor executor3) {
        this.diskIO = executor;
        this.networkIO = executor2;
        this.mainThread = executor3;
    }

    private AppExecutors() {
        this(new DiskIOThreadExecutor(), Executors.newFixedThreadPool(3), new MainThreadExecutor());
    }

    public static AppExecutors globalAppExecutors() {
        if (sInstance == null) {
            synchronized (AppExecutors.class) {
                if (sInstance == null) {
                    sInstance = new AppExecutors();
                }
            }
        }
        return sInstance;
    }

    public Executor diskIO() {
        return this.diskIO;
    }

    public Executor networkIO() {
        return this.networkIO;
    }

    public Executor mainThread() {
        return this.mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler;

        private MainThreadExecutor() {
            this.mainThreadHandler = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.mainThreadHandler.post(runnable);
        }
    }
}