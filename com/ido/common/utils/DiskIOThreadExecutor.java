package com.ido.common.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class DiskIOThreadExecutor implements Executor {
    private final Executor mDiskIO = Executors.newSingleThreadExecutor();

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.mDiskIO.execute(runnable);
    }
}