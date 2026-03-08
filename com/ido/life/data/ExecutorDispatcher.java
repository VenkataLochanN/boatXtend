package com.ido.life.data;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
public class ExecutorDispatcher {
    private final ExecutorService executor;
    private final Handler mHandler;

    public static ExecutorDispatcher getInstance() {
        return SingleInstanceHolder.instance;
    }

    private ExecutorDispatcher() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.executor = Executors.newFixedThreadPool(4);
    }

    private static class SingleInstanceHolder {
        private static final ExecutorDispatcher instance = new ExecutorDispatcher();

        private SingleInstanceHolder() {
        }
    }

    public void dispatchOnMainThread(Runnable runnable) {
        if (runnable != null) {
            this.mHandler.post(runnable);
        }
    }

    public Future dispatch(Runnable runnable) {
        if (runnable != null) {
            return this.executor.submit(runnable);
        }
        return null;
    }
}