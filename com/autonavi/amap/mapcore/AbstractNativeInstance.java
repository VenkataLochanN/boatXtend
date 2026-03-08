package com.autonavi.amap.mapcore;

/* JADX INFO: loaded from: classes.dex */
public class AbstractNativeInstance {
    protected long nativeInstance = 0;

    public void createNativeInstace() {
    }

    public final long getNativeInstance() {
        return this.nativeInstance;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }
}