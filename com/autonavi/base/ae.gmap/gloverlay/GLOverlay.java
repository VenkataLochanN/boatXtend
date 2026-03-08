package com.autonavi.base.ae.gmap.gloverlay;

import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: loaded from: classes.dex */
public abstract class GLOverlay {
    protected int mCode;
    protected int mEngineID;
    protected IAMapDelegate mGLMapView;
    protected int mItemPriority;
    protected long mNativeInstance;
    protected boolean isNightStyle = false;
    boolean mIsInBundle = false;

    public enum EAMapOverlayTpye {
        AMAPOVERLAY_POINT,
        AMAPOVERLAY_POLYLINE,
        AMAPOVERLAY_POLYGON,
        AMAPOVERLAY_ARC,
        AMAPOVERLAY_ARROW,
        AMAPOVERLAY_VECTOR,
        AMAPOVERLAY_GROUP,
        AMAPOVERLAY_MODEL,
        AMAPOVERLAY_PLANE
    }

    private static native int nativeGetCount(long j);

    private static native int nativeGetOverlayPriority(long j);

    private static native int nativeGetSubType(long j);

    private static native int nativeGetType(long j);

    private static native boolean nativeIsClickable(long j);

    private static native boolean nativeIsVisible(long j);

    private static native void nativeRemoveAll(long j);

    private static native void nativeRemoveItem(long j, int i);

    private static native void nativeSetClickable(long j, boolean z);

    private static native void nativeSetMaxDisplayLevel(long j, float f2);

    private static native void nativeSetMinDisplayLevel(long j, float f2);

    private static native void nativeSetOverlayItemPriority(long j, int i);

    private static native void nativeSetOverlayOnTop(long j, boolean z);

    private static native void nativeSetOverlayPriority(long j, int i);

    private static native void nativeSetShownMaxCount(long j, int i);

    protected static native void nativeSetVisible(long j, boolean z);

    public void clearFocus() {
    }

    public GLOverlay(int i, IAMapDelegate iAMapDelegate, int i2) {
        this.mNativeInstance = 0L;
        this.mItemPriority = 0;
        this.mEngineID = i;
        this.mGLMapView = iAMapDelegate;
        this.mCode = i2;
        this.mNativeInstance = 0L;
        this.mItemPriority = 0;
    }

    public long getNativeInstatnce() {
        return this.mNativeInstance;
    }

    public int getCode() {
        return this.mCode;
    }

    public int getType() {
        long j = this.mNativeInstance;
        if (j == 0) {
            return -1;
        }
        return nativeGetType(j);
    }

    public int getSubType() {
        long j = this.mNativeInstance;
        if (j == 0) {
            return -1;
        }
        return nativeGetSubType(j);
    }

    public void removeItem(int i) {
        long j = this.mNativeInstance;
        if (j == 0) {
            return;
        }
        nativeRemoveItem(j, i);
    }

    public void removeAll() {
        long j = this.mNativeInstance;
        if (j == 0) {
            return;
        }
        nativeRemoveAll(j);
        IAMapDelegate iAMapDelegate = this.mGLMapView;
    }

    public int getSize() {
        long j = this.mNativeInstance;
        if (j == 0) {
            return 0;
        }
        return nativeGetCount(j);
    }

    public void setVisible(boolean z) {
        long j = this.mNativeInstance;
        if (j == 0) {
            return;
        }
        nativeSetVisible(j, z);
    }

    public boolean isVisible() {
        long j = this.mNativeInstance;
        if (j == 0) {
            return false;
        }
        return nativeIsVisible(j);
    }

    public void setClickable(boolean z) {
        long j = this.mNativeInstance;
        if (j == 0) {
            return;
        }
        nativeSetClickable(j, z);
    }

    public boolean isClickable() {
        long j = this.mNativeInstance;
        if (j == 0) {
            return false;
        }
        return nativeIsClickable(j);
    }

    public boolean getIsInBundle() {
        return this.mIsInBundle;
    }

    public void setMaxCountShown(int i) {
        nativeSetShownMaxCount(this.mNativeInstance, i);
    }

    public void setOverlayOnTop(boolean z) {
        nativeSetOverlayOnTop(this.mNativeInstance, z);
    }

    public void setMinDisplayLevel(float f2) {
        nativeSetMinDisplayLevel(this.mNativeInstance, f2);
    }

    public void setMaxDisplayLevel(float f2) {
        nativeSetMaxDisplayLevel(this.mNativeInstance, f2);
    }

    public void setOverlayPriority(int i) {
        GLOverlayBundle overlayBundle;
        nativeSetOverlayPriority(this.mNativeInstance, i);
        IAMapDelegate iAMapDelegate = this.mGLMapView;
        if (iAMapDelegate == null || iAMapDelegate.getGLMapEngine() == null || (overlayBundle = this.mGLMapView.getGLMapEngine().getOverlayBundle(this.mEngineID)) == null) {
            return;
        }
        overlayBundle.sortOverlay();
    }

    public int getOverlayPriority() {
        return nativeGetOverlayPriority(this.mNativeInstance);
    }

    public void setOverlayItemPriority(int i) {
        this.mItemPriority = i;
    }

    void releaseInstance() {
        if (this.mNativeInstance != 0) {
            this.mNativeInstance = 0L;
        }
    }

    public void useNightStyle(boolean z) {
        this.isNightStyle = z;
    }
}