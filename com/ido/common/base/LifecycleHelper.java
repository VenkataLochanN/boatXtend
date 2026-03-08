package com.ido.common.base;

/* JADX INFO: loaded from: classes2.dex */
public class LifecycleHelper {
    private static final boolean ENABLE = false;
    private final String TAG;
    private boolean isInteractive;

    protected void logLifecycle(String str, boolean z) {
    }

    public LifecycleHelper(String str) {
        this.TAG = str;
    }

    protected void setInteractive(boolean z) {
        this.isInteractive = z;
    }

    protected boolean isInteractive() {
        return this.isInteractive;
    }
}