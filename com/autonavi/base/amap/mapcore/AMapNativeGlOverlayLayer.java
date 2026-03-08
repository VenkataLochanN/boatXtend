package com.autonavi.base.amap.mapcore;

import android.graphics.Bitmap;
import android.util.Log;
import com.amap.api.mapcore.util.de;
import com.amap.api.mapcore.util.ec;
import com.amap.api.maps.model.BaseOptions;
import com.amap.api.maps.model.LatLng;
import com.autonavi.base.amap.mapcore.annotations.ParameterIsClass;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public class AMapNativeGlOverlayLayer extends NativeBase {
    private setRunLowFrameListener lowFrameListener;
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public interface setRunLowFrameListener {
        void onSetRunLowFrame(boolean z);
    }

    private native void nativeAddTexture(String str, Object obj, String str2);

    private native void nativeClear(String str);

    private native String nativeContain(Object obj);

    private native void nativeCreate();

    private native void nativeCreateOverlay(String str, Object obj);

    private native void nativeDestroy();

    private native void nativeFinalize();

    private native int nativeGetCurrentParticleNum(String str);

    private native Object nativeGetNativeOverlayProperties(String str, String str2, Object[] objArr);

    private native void nativeInitDefaultBitmapSymbols(String str, String str2, String str3, String str4);

    private native void nativeInitDefaultTextureIds(String str, int i, int i2, int i3);

    private native void nativeRemoveOverlay(String str);

    private native void nativeRemoveTexture(String str);

    private native void nativeRender(boolean z, int i);

    private native void nativeSetAMapEngine(long j);

    private native void nativeSetShaderManager(long j);

    private native void nativeSetTextureIds(String str, int[] iArr);

    private native void nativeUpdateConfig(Object obj, float f2);

    private native void nativeUpdateOptions(String str, Object obj);

    public void createOverlay(final String str, final BaseOptions baseOptions) {
        if (!isReady()) {
            storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.1
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.createOverlay(str, baseOptions);
                }
            }, str, baseOptions);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeCreateOverlay(str, baseOptions);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @ParameterIsClass
    public void removeOverlay(final String str) {
        if (!isReady()) {
            storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.2
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.removeOverlay(str);
                }
            }, str);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeRemoveOverlay(str);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @ParameterIsClass
    public void clear(final String str) {
        if (!isReady()) {
            storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.3
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.clear(str);
                }
            }, str);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeClear(str);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @ParameterIsClass
    public void setConfig(final MapConfig mapConfig, final Float f2) {
        if (!isReady()) {
            storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.4
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.setConfig(mapConfig, f2);
                }
            }, mapConfig, f2);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeUpdateConfig(mapConfig, f2.floatValue());
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @ParameterIsClass
    public void updateOptions(final String str, final BaseOptions baseOptions) {
        try {
            if (!isReady()) {
                storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.5
                    @Override // java.lang.Runnable
                    public void run() {
                        AMapNativeGlOverlayLayer.this.updateOptions(str, baseOptions);
                    }
                }, str, baseOptions);
                return;
            }
            callAllFunction();
            try {
                this.readWriteLock.readLock().lock();
                nativeUpdateOptions(str, baseOptions);
                this.readWriteLock.readLock().unlock();
            } catch (Throwable th) {
                this.readWriteLock.readLock().unlock();
                throw th;
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
            Log.d("amapApi", "AMapNativeGlOverlayLayer updateOptions error:" + th2.getMessage());
        }
    }

    @ParameterIsClass
    public void initDefaultTextureIds(final String str, final Integer num, final Integer num2, final Integer num3) {
        if (!isReady()) {
            storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.6
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.initDefaultTextureIds(str, num, num2, num3);
                }
            }, str, num, num2, num3);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeInitDefaultTextureIds(str, num.intValue(), num2.intValue(), num3.intValue());
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @ParameterIsClass
    public void initDefaultBitmapSymbols(final String str, final String str2, final String str3, final String str4) {
        if (!isReady()) {
            storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.7
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.initDefaultBitmapSymbols(str, str2, str3, str4);
                }
            }, str, str2, str3, str4);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeInitDefaultBitmapSymbols(str, str2, str3, str4);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public void setTextureIds(final String str, final int[] iArr) {
        if (!isReady()) {
            storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.8
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.setTextureIds(str, iArr);
                }
            }, str, iArr);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeSetTextureIds(str, iArr);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @ParameterIsClass
    public void addTexture(final String str, final Bitmap bitmap, final String str2) {
        if (!isReady()) {
            storeUncallFunction(this, new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.9
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.addTexture(str, bitmap, str2);
                }
            }, str, bitmap);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeAddTexture(str, bitmap, str2);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @ParameterIsClass
    public void removeTexture(final String str) {
        if (!isReady()) {
            new Runnable() { // from class: com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.10
                @Override // java.lang.Runnable
                public void run() {
                    AMapNativeGlOverlayLayer.this.removeTexture(str);
                }
            };
            storeUncallFunction(this, str, new Object[0]);
            return;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeRemoveTexture(str);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public String contain(LatLng latLng) {
        if (!isReady()) {
            return "";
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            return nativeContain(latLng);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public int getCurrentParticleNum(String str) {
        if (!isReady()) {
            return 0;
        }
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            return nativeGetCurrentParticleNum(str);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public Object getNativeProperties(String str, String str2, Object[] objArr) {
        if (!isReady() || str == null) {
            return null;
        }
        try {
            this.readWriteLock.readLock().lock();
            return nativeGetNativeOverlayProperties(str, str2, objArr);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public void render(boolean z, int i) {
        callAllFunction();
        try {
            this.readWriteLock.readLock().lock();
            nativeRender(z, i);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public void setLowFrameListener(setRunLowFrameListener setrunlowframelistener) {
        this.lowFrameListener = setrunlowframelistener;
    }

    public void setShaderManager(de deVar) {
        if (this.mNative == 0) {
            return;
        }
        try {
            this.readWriteLock.readLock().lock();
            if (deVar != null) {
                nativeSetShaderManager(deVar.a());
            }
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @Override // com.autonavi.base.amap.mapcore.NativeBase
    public void destroy() {
        try {
            super.destroy();
            this.readWriteLock.writeLock().lock();
            nativeDestroy();
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    private void setRunLowFrame(boolean z) {
        setRunLowFrameListener setrunlowframelistener = this.lowFrameListener;
        if (setrunlowframelistener != null) {
            setrunlowframelistener.onSetRunLowFrame(z);
        }
    }

    public void setAMapEngine(long j) {
        try {
            this.readWriteLock.readLock().lock();
            nativeSetAMapEngine(j);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @Override // com.autonavi.base.amap.mapcore.NativeBase
    public void createNative() {
        ReentrantReadWriteLock.WriteLock writeLock;
        try {
            if (this.mNative == 0) {
                try {
                    if (this.readWriteLock != null) {
                        this.readWriteLock.writeLock().lock();
                    }
                    nativeCreate();
                } catch (Throwable unused) {
                    if (this.readWriteLock == null) {
                        return;
                    } else {
                        writeLock = this.readWriteLock.writeLock();
                    }
                }
                if (this.readWriteLock != null) {
                    writeLock = this.readWriteLock.writeLock();
                    writeLock.unlock();
                }
            }
        } catch (UnsatisfiedLinkError e2) {
            ec.a(111, getClass().getSimpleName(), "execute error: ", e2.toString());
        }
    }

    @Override // com.autonavi.base.amap.mapcore.NativeBase
    protected void finalizeNative() {
        nativeFinalize();
    }
}