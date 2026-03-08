package com.autonavi.base.ae.gmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.amap.api.mapcore.util.eq;
import com.amap.api.mapcore.util.er;
import com.amap.api.mapcore.util.hn;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.autonavi.amap.api.mapcore.IGLMapEngine;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.autonavi.base.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.base.ae.gmap.glanimation.AdglMapAnimFling;
import com.autonavi.base.ae.gmap.glanimation.AdglMapAnimGroup;
import com.autonavi.base.ae.gmap.glanimation.AdglMapAnimationMgr;
import com.autonavi.base.ae.gmap.gloverlay.BaseMapOverlay;
import com.autonavi.base.ae.gmap.gloverlay.GLOverlayBundle;
import com.autonavi.base.ae.gmap.gloverlay.GLTextureProperty;
import com.autonavi.base.ae.gmap.style.StyleItem;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.FileUtil;
import com.autonavi.base.amap.mapcore.IAMapEngineCallback;
import com.autonavi.base.amap.mapcore.interfaces.IAMapListener;
import com.autonavi.base.amap.mapcore.maploader.AMapLoader;
import com.autonavi.base.amap.mapcore.maploader.NetworkState;
import com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.HoverGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.MoveGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.RotateGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.ScaleGestureMapMessage;
import com.autonavi.base.amap.mapcore.tools.GLConvertUtil;
import com.autonavi.base.amap.mapcore.tools.TextTextureGenerator;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class GLMapEngine implements IGLMapEngine, IAMapEngineCallback, NetworkState.NetworkChangeListener {
    private Context context;
    private IAMapDelegate mGlMapView;
    private IAMapListener mMapListener;
    boolean mRequestDestroy;
    private TextTextureGenerator mTextTextureGenerator;
    private AdglMapAnimationMgr mapAnimationMgr;
    GLMapState state;
    private String userAgent;
    private long mNativeMapengineInstance = 0;
    private List<AbstractCameraUpdateMessage> mStateMessageList = new Vector();
    private List<AbstractGestureMapMessage> mGestureMessageList = new Vector();
    private List<AbstractGestureMapMessage> mGestureEndMessageList = new Vector();
    private List<AbstractCameraUpdateMessage> mAnimateStateMessageList = new Vector();
    boolean isMoveCameraStep = false;
    boolean isGestureStep = false;
    private int mapGestureCount = 0;
    private GLMapState copyGLMapState = null;
    private Lock mLock = new ReentrantLock();
    private Object mutLock = new Object();
    private NetworkState mNetworkState = null;
    GLOverlayBundle<BaseMapOverlay<?, ?>> bundle = null;
    private boolean isEngineRenderComplete = false;
    Hashtable<Long, AMapLoader> aMapLoaderHashtable = new Hashtable<>();
    private AtomicInteger mRequestID = new AtomicInteger(1);

    public static class InitParam {
        public String mRootPath = "";
        public String mConfigPath = "";
        public String mConfigContent = "";
        public String mOfflineDataPath = "";
        public String mP3dCrossPath = "";
    }

    public static class MapViewInitParam {
        public int engineId;
        public int height;
        public float mapZoomScale;
        public int screenHeight;
        public float screenScale;
        public int screenWidth;
        public int taskThreadCount = 8;
        public float textScale;
        public int width;
        public int x;
        public int y;
    }

    protected static native String nativeAddNativeOverlay(int i, long j, int i2, int i3);

    private static native boolean nativeAddOverlayTexture(int i, long j, int i2, int i3, float f2, float f3, Bitmap bitmap, boolean z, boolean z2);

    private static native void nativeCancelDownLoad(int i, long j, long j2);

    private static native void nativeCreateAMapEngineWithFrame(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, float f2, float f3, float f4, int i8);

    private static native long nativeCreateAMapInstance(String str, String str2, String str3, float f2, float f3, float f4);

    protected static native long nativeCreateOverlay(int i, long j, int i2);

    private static native void nativeDestroy(long j);

    private static native void nativeDestroyCurrentState(long j, long j2);

    protected static native void nativeDestroyOverlay(int i, long j);

    private static native void nativeFailedDownLoad(int i, long j, long j2, int i2);

    private static native void nativeFinishDownLoad(int i, long j, long j2);

    private static native void nativeGetCurTileIDs(int i, long j, int[] iArr, int i2);

    private static native long nativeGetCurrentMapState(int i, long j);

    private static native long nativeGetGlOverlayMgrPtr(int i, long j);

    public static native String nativeGetMapEngineVersion(int i);

    private static native int[] nativeGetMapModeState(int i, long j, boolean z);

    public static native long nativeGetNativeMapController(int i, long j);

    private static native boolean nativeGetSrvViewStateBoolValue(int i, long j, int i2);

    private static native void nativeInitAMapEngineCallback(long j, Object obj);

    private static native void nativeInitParam(String str, String str2, String str3, String str4);

    private static native boolean nativeIsEngineCreated(long j, int i);

    private static native void nativePopRenderState(int i, long j);

    private static native void nativePostRenderAMap(long j, int i);

    private static native void nativePushRendererState(int i, long j);

    private static native void nativeReceiveNetData(int i, long j, byte[] bArr, long j2, int i2);

    protected static native void nativeRemoveNativeAllOverlay(int i, long j);

    protected static native void nativeRemoveNativeOverlay(int i, long j, String str);

    private static native void nativeRenderAMap(long j, int i);

    private static native void nativeSelectMapPois(int i, long j, int i2, int i3, int i4, byte[] bArr);

    private static native void nativeSetAllContentEnable(int i, long j, boolean z);

    private static native void nativeSetBuildingEnable(int i, long j, boolean z);

    private static native void nativeSetBuildingTextureEnable(int i, long j, boolean z);

    private static native void nativeSetCustomStyleData(int i, long j, byte[] bArr, byte[] bArr2);

    private static native void nativeSetCustomStyleTexture(int i, long j, byte[] bArr);

    private static native void nativeSetHighlightSubwayEnable(int i, long j, boolean z);

    private static native void nativeSetIndoorBuildingToBeActive(int i, long j, String str, int i2, String str2);

    private static native void nativeSetIndoorEnable(int i, long j, boolean z);

    private static native void nativeSetLabelEnable(int i, long j, boolean z);

    private static native boolean nativeSetMapModeAndStyle(int i, long j, int[] iArr, boolean z, boolean z2, StyleItem[] styleItemArr);

    private static native void nativeSetNaviLabelEnable(int i, long j, boolean z, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetNetStatus(long j, int i);

    private static native void nativeSetOfflineDataEnable(int i, long j, boolean z);

    private static native void nativeSetParameter(int i, long j, int i2, int i3, int i4, int i5, int i6);

    private static native void nativeSetProjectionCenter(int i, long j, float f2, float f3);

    private static native void nativeSetRenderListenerStatus(int i, long j);

    private static native void nativeSetRoadArrowEnable(int i, long j, boolean z);

    private static native void nativeSetServiceViewRect(int i, long j, int i2, int i3, int i4, int i5, int i6, int i7);

    private static native void nativeSetSetBackgroundTexture(int i, long j, byte[] bArr);

    private static native void nativeSetSimple3DEnable(int i, long j, boolean z);

    private static native void nativeSetSkyTexture(int i, long j, byte[] bArr);

    private static native void nativeSetSrvViewStateBoolValue(int i, long j, int i2, boolean z);

    private static native void nativeSetStyleChangeGradualEnable(int i, long j, boolean z);

    private static native void nativeSetTrafficEnable(int i, long j, boolean z);

    private static native void nativeSetTrafficTexture(int i, long j, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    private static native void nativeSetTrafficTextureAllInOne(int i, long j, byte[] bArr);

    protected static native void nativeUpdateNativeArrowOverlay(int i, long j, String str, int[] iArr, int[] iArr2, int i2, int i3, int i4, float f2, boolean z, int i5, int i6, int i7);

    private static native void nativesetMapOpenLayer(int i, long j, byte[] bArr);

    public void changeSurface(int i, int i2) {
    }

    public void clearAllMessages(int i) {
    }

    public int getEngineIDWithType(int i) {
        return 1;
    }

    public boolean getIsProcessBuildingMark(int i) {
        return false;
    }

    public long getMapStateInstance(int i) {
        return 0L;
    }

    public boolean isInMapAction(int i) {
        return false;
    }

    public void onClearCache(int i) {
    }

    public void putResourceData(int i, byte[] bArr) {
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public void reloadMapResource(int i, String str, int i2) {
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public void requireMapData(int i, byte[] bArr) {
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public void requireMapRender(int i, int i2, int i3) {
    }

    public void setInternaltexture(int i, byte[] bArr, int i2) {
    }

    public void setMapLoaderToTask(int i, long j, AMapLoader aMapLoader) {
    }

    public void startPivotZoomRotateAnim(int i, Point point, float f2, int i2, int i3) {
        if (f2 != -9999.0f || i2 == -9999) {
        }
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public byte[] requireMapResource(int i, String str) {
        byte[] fileContentsFromAssets;
        if (str == null) {
            return null;
        }
        String str2 = "map_assets/" + str;
        try {
            if (this.mGlMapView.getMapConfig().isCustomStyleEnable()) {
                if (str.startsWith("icons_5")) {
                    fileContentsFromAssets = FileUtil.readFileContents(this.mGlMapView.getMapConfig().getCustomTextureResourcePath());
                } else if (str.startsWith("bktile")) {
                    fileContentsFromAssets = FileUtil.readFileContentsFromAssets(this.context, str2);
                    int customBackgroundColor = this.mGlMapView.getMapConfig().getCustomBackgroundColor();
                    if (customBackgroundColor != 0) {
                        fileContentsFromAssets = er.a(fileContentsFromAssets, customBackgroundColor);
                    }
                } else {
                    fileContentsFromAssets = null;
                }
                if (fileContentsFromAssets != null) {
                    return fileContentsFromAssets;
                }
            }
            return FileUtil.readFileContentsFromAssets(this.context, str2);
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public int generateRequestId() {
        return this.mRequestID.incrementAndGet();
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public int requireMapDataAsyn(int i, byte[] bArr) {
        if (!this.mRequestDestroy && bArr != null) {
            AMapLoader.ADataRequestParam aDataRequestParam = new AMapLoader.ADataRequestParam();
            int i2 = GLConvertUtil.getInt(bArr, 0);
            aDataRequestParam.requestBaseUrl = GLConvertUtil.getString(bArr, 4, i2);
            int i3 = i2 + 4;
            int i4 = GLConvertUtil.getInt(bArr, i3);
            int i5 = i3 + 4;
            aDataRequestParam.requestUrl = GLConvertUtil.getString(bArr, i5, i4);
            int i6 = i5 + i4;
            aDataRequestParam.handler = GLConvertUtil.getLong(bArr, i6);
            int i7 = i6 + 8;
            aDataRequestParam.nRequestType = GLConvertUtil.getInt(bArr, i7);
            int i8 = i7 + 4;
            int i9 = GLConvertUtil.getInt(bArr, i8);
            int i10 = i8 + 4;
            aDataRequestParam.enCodeString = GLConvertUtil.getSubBytes(bArr, i10, i9);
            aDataRequestParam.nCompress = GLConvertUtil.getInt(bArr, i10 + i9);
            final AMapLoader aMapLoader = new AMapLoader(i, this, aDataRequestParam);
            synchronized (this) {
                this.aMapLoaderHashtable.put(Long.valueOf(aDataRequestParam.handler), aMapLoader);
            }
            aMapLoader.isFinish = false;
            try {
                eq.a().a(new Runnable() { // from class: com.autonavi.base.ae.gmap.GLMapEngine.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (GLMapEngine.this.mRequestDestroy) {
                                AMapLoader aMapLoader2 = aMapLoader;
                                if (aMapLoader2 == null || aMapLoader2.isFinish) {
                                    return;
                                }
                                synchronized (aMapLoader) {
                                    if (!aMapLoader.isFinish) {
                                        aMapLoader.notify();
                                        aMapLoader.isFinish = true;
                                    }
                                }
                                return;
                            }
                            if (aMapLoader == null) {
                                AMapLoader aMapLoader3 = aMapLoader;
                                if (aMapLoader3 == null || aMapLoader3.isFinish) {
                                    return;
                                }
                                synchronized (aMapLoader) {
                                    if (!aMapLoader.isFinish) {
                                        aMapLoader.notify();
                                        aMapLoader.isFinish = true;
                                    }
                                }
                                return;
                            }
                            aMapLoader.doRequest();
                            AMapLoader aMapLoader4 = aMapLoader;
                            if (aMapLoader4 == null || aMapLoader4.isFinish) {
                                return;
                            }
                            synchronized (aMapLoader) {
                                if (!aMapLoader.isFinish) {
                                    aMapLoader.notify();
                                    aMapLoader.isFinish = true;
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                hn.c(th, "download Thread", "AMapLoader doRequest");
                                er.a(th);
                                AMapLoader aMapLoader5 = aMapLoader;
                                if (aMapLoader5 == null || aMapLoader5.isFinish) {
                                    return;
                                }
                                synchronized (aMapLoader) {
                                    if (!aMapLoader.isFinish) {
                                        aMapLoader.notify();
                                        aMapLoader.isFinish = true;
                                    }
                                }
                            } catch (Throwable th2) {
                                AMapLoader aMapLoader6 = aMapLoader;
                                if (aMapLoader6 != null && !aMapLoader6.isFinish) {
                                    synchronized (aMapLoader) {
                                        if (!aMapLoader.isFinish) {
                                            aMapLoader.notify();
                                            aMapLoader.isFinish = true;
                                        }
                                    }
                                }
                                throw th2;
                            }
                        }
                    }
                });
                synchronized (aMapLoader) {
                    while (!aMapLoader.isFinish) {
                        aMapLoader.wait();
                    }
                }
            } catch (Throwable th) {
                hn.c(th, "download Thread", "requireMapData");
                er.a(th);
            }
        }
        return 0;
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public void cancelRequireMapData(Object obj) {
        if (obj == null || !(obj instanceof AMapLoader)) {
            return;
        }
        ((AMapLoader) obj).doCancel();
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public byte[] requireCharBitmap(int i, int i2, int i3) {
        return this.mTextTextureGenerator.getTextPixelBuffer(i2, i3);
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public byte[] requireCharsWidths(int i, int[] iArr, int i2, int i3) {
        return this.mTextTextureGenerator.getCharsWidths(iArr);
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public void onMapRender(int i, int i2) {
        try {
            if (i2 != 5) {
                if (i2 != 6) {
                    if (i2 != 7) {
                        if (i2 != 13) {
                        } else {
                            this.isEngineRenderComplete = true;
                        }
                    } else if (this.mMapListener != null) {
                        this.mMapListener.afterRendererOver(i, getMapState(i));
                    }
                } else if (this.mMapListener != null) {
                    this.mMapListener.afterDrawLabel(i, getMapState(i));
                }
            } else if (this.mMapListener != null) {
                this.mMapListener.beforeDrawLabel(i, getMapState(i));
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.base.amap.mapcore.IAMapEngineCallback
    public void OnIndoorBuildingActivity(int i, byte[] bArr) {
        IAMapDelegate iAMapDelegate = this.mGlMapView;
        if (iAMapDelegate != null) {
            try {
                iAMapDelegate.onIndoorBuildingActivity(i, bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public synchronized void receiveNetData(int i, long j, byte[] bArr, int i2) {
        if (this.mRequestDestroy) {
            return;
        }
        if (this.aMapLoaderHashtable.containsKey(Long.valueOf(j))) {
            if (this.mNativeMapengineInstance != 0) {
                nativeReceiveNetData(i, this.mNativeMapengineInstance, bArr, j, i2);
            }
        }
    }

    public boolean getMapDataTaskIsCancel(int i, long j) {
        return !this.aMapLoaderHashtable.containsKey(Long.valueOf(j));
    }

    public synchronized void finishDownLoad(int i, long j) {
        if (this.aMapLoaderHashtable.containsKey(Long.valueOf(j))) {
            if (this.mNativeMapengineInstance != 0) {
                nativeFinishDownLoad(i, this.mNativeMapengineInstance, j);
            }
            this.aMapLoaderHashtable.remove(Long.valueOf(j));
        }
    }

    public synchronized void netStop(int i, long j, int i2) {
        if (this.aMapLoaderHashtable.containsKey(Long.valueOf(j))) {
            if (this.mNativeMapengineInstance != 0) {
                nativeCancelDownLoad(i, this.mNativeMapengineInstance, j);
            }
            this.aMapLoaderHashtable.remove(Long.valueOf(j));
        }
    }

    public synchronized void netError(int i, long j, int i2, int i3) {
        if (this.aMapLoaderHashtable.containsKey(Long.valueOf(j))) {
            if (this.mNativeMapengineInstance != 0) {
                nativeFailedDownLoad(i, this.mNativeMapengineInstance, j, i3);
            }
            this.aMapLoaderHashtable.remove(Long.valueOf(j));
            try {
                if (MapsInitializer.getExceptionLogger() != null) {
                    MapsInitializer.getExceptionLogger().onDownloaderException(i2, i3);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public Context getContext() {
        return this.context;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setParamater(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mLock.lock();
        try {
            if (this.mNativeMapengineInstance != 0) {
                nativeSetParameter(i, this.mNativeMapengineInstance, i2, i3, i4, i5, i6);
            }
        } finally {
            this.mLock.unlock();
        }
    }

    public long getNativeInstance() {
        return this.mNativeMapengineInstance;
    }

    public boolean canStopMapRender(int i) {
        return this.isEngineRenderComplete;
    }

    public boolean isEngineCreated(int i) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            return nativeIsEngineCreated(j, i);
        }
        return false;
    }

    public int getEngineIDWithGestureInfo(EAMapPlatformGestureInfo eAMapPlatformGestureInfo) {
        long j = this.mNativeMapengineInstance;
        return 1;
    }

    public void setServiceViewRect(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        nativeSetServiceViewRect(i, this.mNativeMapengineInstance, i2, i3, i4, i5, i6, i7);
    }

    public void setSrvViewStateBoolValue(int i, int i2, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetSrvViewStateBoolValue(i, j, i2, z);
        }
    }

    public boolean getSrvViewStateBoolValue(int i, int i2) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            return nativeGetSrvViewStateBoolValue(i, j, i2);
        }
        return false;
    }

    public void setIndoorBuildingToBeActive(int i, String str, int i2, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetIndoorBuildingToBeActive(i, j, str, i2, str2);
        }
    }

    public void setMapListener(IAMapListener iAMapListener) {
        this.mMapListener = iAMapListener;
    }

    public GLMapState getMapState(int i) {
        this.mLock.lock();
        try {
            if (this.mNativeMapengineInstance != 0 && this.state == null) {
                long jNativeGetCurrentMapState = nativeGetCurrentMapState(i, this.mNativeMapengineInstance);
                if (jNativeGetCurrentMapState != 0) {
                    this.state = new GLMapState(this.mNativeMapengineInstance, jNativeGetCurrentMapState);
                }
            }
            this.mLock.unlock();
            return this.state;
        } catch (Throwable th) {
            this.mLock.unlock();
            throw th;
        }
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapEngine
    public IGLMapState getNewMapState(int i) {
        this.mLock.lock();
        try {
            if (this.mNativeMapengineInstance != 0) {
                return new GLMapState(i, this.mNativeMapengineInstance);
            }
            this.mLock.unlock();
            return null;
        } finally {
            this.mLock.unlock();
        }
    }

    public synchronized GLMapState getCloneMapState() {
        this.mLock.lock();
        try {
            if (this.mNativeMapengineInstance != 0) {
                if (this.copyGLMapState == null) {
                    this.copyGLMapState = new GLMapState(1, this.mNativeMapengineInstance);
                }
                this.copyGLMapState.setMapZoomer(this.mGlMapView.getMapConfig().getSZ());
                this.copyGLMapState.setCameraDegree(this.mGlMapView.getMapConfig().getSC());
                this.copyGLMapState.setMapAngle(this.mGlMapView.getMapConfig().getSR());
                this.copyGLMapState.setMapGeoCenter(this.mGlMapView.getMapConfig().getSX(), this.mGlMapView.getMapConfig().getSY());
            }
            this.mLock.unlock();
        } catch (Throwable th) {
            this.mLock.unlock();
            throw th;
        }
        return this.copyGLMapState;
    }

    public void setMapState(int i, GLMapState gLMapState) {
        setMapState(i, gLMapState, true);
    }

    public void setMapState(int i, GLMapState gLMapState, boolean z) {
        IAMapDelegate iAMapDelegate;
        if (this.mNativeMapengineInstance != 0) {
            if (z && (iAMapDelegate = this.mGlMapView) != null && iAMapDelegate.getMapConfig() != null) {
                this.mGlMapView.checkMapState(gLMapState);
            }
            this.mLock.lock();
            try {
                gLMapState.setNativeMapengineState(i, this.mNativeMapengineInstance);
            } finally {
                this.mLock.unlock();
            }
        }
    }

    private void initAnimation() {
        AbstractCameraUpdateMessage abstractCameraUpdateMessageRemove;
        if (this.mStateMessageList.size() <= 0 && this.mAnimateStateMessageList.size() > 0 && (abstractCameraUpdateMessageRemove = this.mAnimateStateMessageList.remove(0)) != null) {
            abstractCameraUpdateMessageRemove.generateMapAnimation(this);
        }
    }

    public synchronized void addGestureMessage(int i, AbstractGestureMapMessage abstractGestureMapMessage, boolean z, int i2, int i3) {
        if (abstractGestureMapMessage == null) {
            return;
        }
        abstractGestureMapMessage.isGestureScaleByMapCenter = z;
        this.mGestureMessageList.add(abstractGestureMapMessage);
    }

    private boolean processMessage() {
        try {
            GLMapState gLMapState = (GLMapState) getNewMapState(1);
            boolean zProcessGestureMessage = processGestureMessage(gLMapState);
            if (this.mGestureMessageList.size() <= 0) {
                zProcessGestureMessage = zProcessGestureMessage || processStateMapMessage(gLMapState);
            } else if (this.mStateMessageList.size() > 0) {
                this.mStateMessageList.clear();
            }
            boolean z = zProcessGestureMessage || processAnimations(gLMapState);
            if (z) {
                gLMapState.setCameraDegree(er.a(this.mGlMapView.getMapConfig(), gLMapState.getCameraDegree(), gLMapState.getMapZoomer()));
                setMapState(1, gLMapState);
            }
            gLMapState.recycle();
            return z;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean processGestureMessage(GLMapState gLMapState) {
        AbstractGestureMapMessage abstractGestureMapMessageRemove;
        if (this.mGestureMessageList.size() <= 0) {
            if (this.isGestureStep) {
                this.isGestureStep = false;
            }
            return false;
        }
        this.isGestureStep = true;
        if (gLMapState == null) {
            return false;
        }
        while (this.mGestureMessageList.size() > 0 && (abstractGestureMapMessageRemove = this.mGestureMessageList.remove(0)) != null) {
            if (abstractGestureMapMessageRemove.width == 0) {
                abstractGestureMapMessageRemove.width = this.mGlMapView.getMapWidth();
            }
            if (abstractGestureMapMessageRemove.height == 0) {
                abstractGestureMapMessageRemove.height = this.mGlMapView.getMapHeight();
            }
            int mapGestureState = abstractGestureMapMessageRemove.getMapGestureState();
            if (mapGestureState == 100) {
                gestureBegin();
            } else if (mapGestureState == 101) {
                abstractGestureMapMessageRemove.runCameraUpdate(gLMapState);
            } else if (mapGestureState == 102) {
                gestureEnd();
            }
            this.mGestureEndMessageList.add(abstractGestureMapMessageRemove);
        }
        if (this.mGestureEndMessageList.size() >= 1) {
            recycleMessage();
        }
        return true;
    }

    private boolean processAnimations(GLMapState gLMapState) {
        try {
            if (this.mapAnimationMgr.getAnimationsCount() <= 0) {
                return false;
            }
            gLMapState.recalculate();
            this.mapAnimationMgr.doAnimations(gLMapState);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void interruptAnimation() {
        if (isInMapAnimation(1)) {
            try {
                doMapAnimationCancelCallback(this.mapAnimationMgr.getCancelCallback());
                clearAnimations(1, false);
            } catch (Throwable th) {
                hn.c(th, getClass().getName(), "CancelableCallback.onCancel");
                th.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapEngine
    public void addGroupAnimation(int i, int i2, float f2, int i3, int i4, int i5, int i6, AMap.CancelableCallback cancelableCallback) {
        AdglMapAnimGroup adglMapAnimGroup = new AdglMapAnimGroup(i2);
        adglMapAnimGroup.setToCameraDegree(i4, 0);
        adglMapAnimGroup.setToMapAngle(i3, 0);
        adglMapAnimGroup.setToMapLevel(f2, 0);
        adglMapAnimGroup.setToMapCenterGeo(i5, i6, 0);
        if (this.mapAnimationMgr == null || !adglMapAnimGroup.isValid()) {
            return;
        }
        this.mapAnimationMgr.addAnimation(adglMapAnimGroup, cancelableCallback);
    }

    private void doMapAnimationCancelCallback(final AMap.CancelableCallback cancelableCallback) {
        IAMapDelegate iAMapDelegate;
        if (cancelableCallback == null || (iAMapDelegate = this.mGlMapView) == null) {
            return;
        }
        iAMapDelegate.getMainHandler().post(new Runnable() { // from class: com.autonavi.base.ae.gmap.GLMapEngine.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    cancelableCallback.onCancel();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doMapAnimationFinishCallback(final AMap.CancelableCallback cancelableCallback) {
        IAMapDelegate iAMapDelegate;
        IAMapListener iAMapListener = this.mMapListener;
        if (iAMapListener != null) {
            iAMapListener.afterAnimation();
        }
        if (cancelableCallback == null || (iAMapDelegate = this.mGlMapView) == null) {
            return;
        }
        iAMapDelegate.getMainHandler().post(new Runnable() { // from class: com.autonavi.base.ae.gmap.GLMapEngine.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    cancelableCallback.onFinish();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public boolean isInMapAnimation(int i) {
        return getAnimateionsCount() > 0;
    }

    public int getAnimateionsCount() {
        if (this.mNativeMapengineInstance != 0) {
            return this.mapAnimationMgr.getAnimationsCount();
        }
        return 0;
    }

    public void clearAnimations(int i, boolean z) {
        this.mapAnimationMgr.clearAnimations();
    }

    public void clearAnimations(int i, boolean z, int i2) {
        this.mapAnimationMgr.clearAnimations();
    }

    public void startMapSlidAnim(int i, Point point, float f2, float f3) {
        if (point == null) {
            return;
        }
        try {
            clearAnimations(i, true);
            GLMapState cloneMapState = getCloneMapState();
            cloneMapState.reset();
            cloneMapState.recalculate();
            float fAbs = Math.abs(f2);
            float fAbs2 = Math.abs(f3);
            float f4 = 12000;
            if ((fAbs > fAbs2 ? fAbs : fAbs2) > f4) {
                if (fAbs > fAbs2) {
                    f2 = f2 > 0.0f ? f4 : -12000;
                    f3 *= f4 / fAbs;
                } else {
                    f2 *= f4 / fAbs2;
                    f3 = f3 > 0.0f ? f4 : -12000;
                }
            }
            int mapWidth = this.mGlMapView.getMapWidth() >> 1;
            int mapHeight = this.mGlMapView.getMapHeight() >> 1;
            if (this.mGlMapView.isUseAnchor()) {
                mapWidth = this.mGlMapView.getMapConfig().getAnchorX();
                mapHeight = this.mGlMapView.getMapConfig().getAnchorY();
            }
            AdglMapAnimFling adglMapAnimFling = new AdglMapAnimFling(500, mapWidth, mapHeight);
            adglMapAnimFling.setPositionAndVelocity(f2, f3);
            adglMapAnimFling.commitAnimation(cloneMapState);
            this.mapAnimationMgr.addAnimation(adglMapAnimFling, null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void gestureBegin() {
        this.mapGestureCount++;
    }

    private void gestureEnd() {
        this.mapGestureCount--;
        if (this.mapGestureCount == 0) {
            recycleMessage();
        }
    }

    public int getStateMessageCount() {
        return this.mStateMessageList.size();
    }

    public void addMessage(AbstractCameraUpdateMessage abstractCameraUpdateMessage, boolean z) {
        if (z) {
            List<AbstractCameraUpdateMessage> list = this.mAnimateStateMessageList;
            if (list != null) {
                list.clear();
                this.mAnimateStateMessageList.add(abstractCameraUpdateMessage);
                return;
            }
            return;
        }
        List<AbstractCameraUpdateMessage> list2 = this.mStateMessageList;
        if (list2 != null) {
            list2.add(abstractCameraUpdateMessage);
        }
    }

    public synchronized AbstractCameraUpdateMessage getStateMessage() {
        if (this.mStateMessageList != null && this.mStateMessageList.size() != 0) {
            AbstractCameraUpdateMessage abstractCameraUpdateMessage = this.mStateMessageList.get(0);
            this.mStateMessageList.remove(abstractCameraUpdateMessage);
            return abstractCameraUpdateMessage;
        }
        return null;
    }

    private void recycleMessage() {
        AbstractGestureMapMessage abstractGestureMapMessageRemove;
        while (this.mGestureEndMessageList.size() > 0 && (abstractGestureMapMessageRemove = this.mGestureEndMessageList.remove(0)) != null) {
            if (abstractGestureMapMessageRemove instanceof MoveGestureMapMessage) {
                ((MoveGestureMapMessage) abstractGestureMapMessageRemove).recycle();
            } else if (abstractGestureMapMessageRemove instanceof HoverGestureMapMessage) {
                ((HoverGestureMapMessage) abstractGestureMapMessageRemove).recycle();
            } else if (abstractGestureMapMessageRemove instanceof RotateGestureMapMessage) {
                ((RotateGestureMapMessage) abstractGestureMapMessageRemove).recycle();
            } else if (abstractGestureMapMessageRemove instanceof ScaleGestureMapMessage) {
                ((ScaleGestureMapMessage) abstractGestureMapMessageRemove).recycle();
            }
        }
    }

    private boolean processStateMapMessage(GLMapState gLMapState) {
        AbstractCameraUpdateMessage abstractCameraUpdateMessageRemove;
        if (this.mStateMessageList.size() <= 0) {
            if (this.isMoveCameraStep) {
                this.isMoveCameraStep = false;
            }
            return false;
        }
        this.isMoveCameraStep = true;
        if (gLMapState == null) {
            return false;
        }
        while (this.mStateMessageList.size() > 0 && (abstractCameraUpdateMessageRemove = this.mStateMessageList.remove(0)) != null) {
            if (abstractCameraUpdateMessageRemove.width == 0) {
                abstractCameraUpdateMessageRemove.width = this.mGlMapView.getMapWidth();
            }
            if (abstractCameraUpdateMessageRemove.height == 0) {
                abstractCameraUpdateMessageRemove.height = this.mGlMapView.getMapHeight();
            }
            gLMapState.recalculate();
            abstractCameraUpdateMessageRemove.runCameraUpdate(gLMapState);
        }
        return true;
    }

    public void setMapOpenLayer(String str) {
        long j = this.mNativeMapengineInstance;
        if (j == 0 || str == null) {
            return;
        }
        nativesetMapOpenLayer(1, j, str.getBytes());
    }

    public void pushRendererState() {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativePushRendererState(1, j);
        }
    }

    public void popRendererState() {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativePopRenderState(1, j);
        }
    }

    public int[] getMapModeState(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j == 0) {
            return null;
        }
        nativeGetMapModeState(i, j, z);
        return null;
    }

    public boolean setNativeMapModeAndStyle(int i, int i2, int i3, int i4, boolean z, boolean z2, StyleItem[] styleItemArr) {
        long j = this.mNativeMapengineInstance;
        if (j == 0) {
            return false;
        }
        return nativeSetMapModeAndStyle(i, j, new int[]{i2, i3, i4, 0, 0}, z, z2, styleItemArr);
    }

    public boolean setMapModeAndStyle(int i, int i2, int i3, int i4, boolean z, boolean z2, StyleItem[] styleItemArr) {
        if (this.mNativeMapengineInstance == 0) {
            return false;
        }
        boolean nativeMapModeAndStyle = setNativeMapModeAndStyle(i, i2, i3, i4, z, z2, styleItemArr);
        if (styleItemArr != null && z2) {
            int customBackgroundColor = this.mGlMapView.getMapConfig().getCustomBackgroundColor();
            if (customBackgroundColor != 0) {
                setBackgroundTexture(i, er.a(FileUtil.readFileContentsFromAssets(this.context, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_BACKGROUND_NAME), customBackgroundColor));
            }
            String customTextureResourcePath = this.mGlMapView.getMapConfig().getCustomTextureResourcePath();
            if (this.mGlMapView.getMapConfig().isProFunctionAuthEnable() && !TextUtils.isEmpty(customTextureResourcePath)) {
                this.mGlMapView.getMapConfig().setUseProFunction(true);
                setCustomStyleTexture(i, FileUtil.readFileContents(customTextureResourcePath));
            }
        } else if (i2 == 0 && i3 == 0 && i4 == 0) {
            setBackgroundTexture(i, FileUtil.readFileContentsFromAssets(this.context, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_BACKGROUND_NAME));
            setCustomStyleTexture(i, FileUtil.readFileContentsFromAssets(this.context, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_ICON_5_NAME));
        }
        return nativeMapModeAndStyle;
    }

    @Override // com.autonavi.base.amap.mapcore.maploader.NetworkState.NetworkChangeListener
    public void networkStateChanged(Context context) {
        if (this.mRequestDestroy || this.mNativeMapengineInstance == 0 || this.mGlMapView == null) {
            return;
        }
        final boolean zIsNetworkConnected = NetworkState.isNetworkConnected(context);
        this.mGlMapView.queueEvent(new Runnable() { // from class: com.autonavi.base.ae.gmap.GLMapEngine.4
            @Override // java.lang.Runnable
            public void run() {
                GLMapEngine.nativeSetNetStatus(GLMapEngine.this.mNativeMapengineInstance, zIsNetworkConnected ? 1 : 0);
            }
        });
    }

    public byte[] getLabelBuffer(int i, int i2, int i3, int i4) {
        this.mLock.lock();
        try {
            byte[] bArr = new byte[3072];
            if (this.mNativeMapengineInstance != 0) {
                nativeSelectMapPois(i, this.mNativeMapengineInstance, i2, i3, i4, bArr);
            }
            return bArr;
        } finally {
            this.mLock.unlock();
        }
    }

    public long createOverlay(int i, int i2) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            return nativeCreateOverlay(i, j, i2);
        }
        return 0L;
    }

    public String addNativeOverlay(int i, int i2, int i3) {
        long j = this.mNativeMapengineInstance;
        if (j == 0) {
            return null;
        }
        String strNativeAddNativeOverlay = nativeAddNativeOverlay(i, j, i2, i3);
        if (TextUtils.isEmpty(strNativeAddNativeOverlay)) {
            return null;
        }
        return strNativeAddNativeOverlay;
    }

    public long getGlOverlayMgrPtr(int i) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            return nativeGetGlOverlayMgrPtr(i, j);
        }
        return 0L;
    }

    public void setOvelayBundle(int i, GLOverlayBundle<BaseMapOverlay<?, ?>> gLOverlayBundle) {
        this.bundle = gLOverlayBundle;
    }

    public void addOverlayTexture(int i, GLTextureProperty gLTextureProperty) {
        if (this.mNativeMapengineInstance == 0 || gLTextureProperty == null || gLTextureProperty.mBitmap == null || gLTextureProperty.mBitmap.isRecycled()) {
            return;
        }
        nativeAddOverlayTexture(i, this.mNativeMapengineInstance, gLTextureProperty.mId, gLTextureProperty.mAnchor, gLTextureProperty.mXRatio, gLTextureProperty.mYRatio, gLTextureProperty.mBitmap, gLTextureProperty.isGenMimps, gLTextureProperty.isRepeat);
    }

    public GLOverlayBundle getOverlayBundle(int i) {
        return this.bundle;
    }

    public static void destroyOverlay(int i, long j) {
        synchronized (GLMapEngine.class) {
            nativeDestroyOverlay(i, j);
        }
    }

    public void setSimple3DEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetSimple3DEnable(i, j, z);
        }
    }

    public void setStyleChangeGradualEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetStyleChangeGradualEnable(i, j, z);
        }
    }

    public void setRoadArrowEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetRoadArrowEnable(i, j, z);
        }
    }

    public void setNaviLabelEnable(int i, boolean z, int i2, int i3) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetNaviLabelEnable(i, j, z, i2, i3);
        }
    }

    public void setSkyTexture(int i, byte[] bArr) {
        if (bArr == null) {
            return;
        }
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetSkyTexture(i, j, bArr);
        }
    }

    public void setBackgroundTexture(int i, byte[] bArr) {
        if (bArr == null) {
            return;
        }
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetSetBackgroundTexture(i, j, bArr);
        }
    }

    public void setCustomStyleTexture(int i, byte[] bArr) {
        if (bArr == null) {
            return;
        }
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetCustomStyleTexture(i, j, bArr);
        }
    }

    public void setCustomStyleData(int i, byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return;
        }
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetCustomStyleData(i, j, bArr, bArr2);
        }
    }

    public void setTrafficEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetTrafficEnable(i, j, z);
        }
    }

    public void setBuildingEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetBuildingEnable(i, j, z);
        }
    }

    public void setLabelEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetLabelEnable(i, j, z);
        }
    }

    public void setAllContentEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            if (z) {
                nativeSetAllContentEnable(i, j, true);
            } else {
                nativeSetAllContentEnable(i, j, false);
            }
            setSimple3DEnable(i, false);
        }
    }

    public void setProjectionCenter(int i, int i2, int i3) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetProjectionCenter(i, j, i2, i3);
        }
    }

    public void setTrafficStyle(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (this.mNativeMapengineInstance != 0) {
            byte[] fileContentsFromAssets = FileUtil.readFileContentsFromAssets(this.context, AMapEngineUtils.MAP_MAP_ASSETS_NAME + File.separator + AMapEngineUtils.MAP_MAP_ASSETS_TRL_NAME);
            if (z) {
                nativeSetTrafficTextureAllInOne(i, this.mNativeMapengineInstance, er.a(fileContentsFromAssets, new int[]{i4, i5, i3, i2}));
            } else {
                nativeSetTrafficTextureAllInOne(i, this.mNativeMapengineInstance, fileContentsFromAssets);
            }
        }
    }

    public void setTrafficStyle(int i, int i2, int i3, int i4, int i5) {
        setTrafficStyle(i, i2, i3, i4, i5, true);
    }

    public void startCheckEngineRenderComplete() {
        this.isEngineRenderComplete = false;
    }

    public void getCurTileIDs(int i, int[] iArr) {
        if (iArr != null) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr[i2] = 0;
            }
            nativeGetCurTileIDs(i, this.mNativeMapengineInstance, iArr, iArr.length);
        }
    }

    public void setIndoorEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetIndoorEnable(i, j, z);
        }
    }

    public void setOfflineDataEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetOfflineDataEnable(i, j, z);
        }
    }

    public void setHighlightSubwayEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetHighlightSubwayEnable(i, j, z);
        }
    }

    public void setBuildingTextureEnable(int i, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetBuildingTextureEnable(i, j, z);
        }
    }

    public void initNativeTexture(int i) {
        try {
            BitmapDescriptor bitmapDescriptorFromAsset = BitmapDescriptorFactory.fromAsset("arrow/arrow_line_inner.png");
            Bitmap bitmap = bitmapDescriptorFromAsset != null ? bitmapDescriptorFromAsset.getBitmap() : null;
            BitmapDescriptor bitmapDescriptorFromAsset2 = BitmapDescriptorFactory.fromAsset("arrow/arrow_line_outer.png");
            Bitmap bitmap2 = bitmapDescriptorFromAsset2 != null ? bitmapDescriptorFromAsset2.getBitmap() : null;
            BitmapDescriptor bitmapDescriptorFromAsset3 = BitmapDescriptorFactory.fromAsset("arrow/arrow_line_shadow.png");
            Bitmap bitmap3 = bitmapDescriptorFromAsset3 != null ? bitmapDescriptorFromAsset3.getBitmap() : null;
            addOverlayTexture(i, bitmap, 111, 4);
            addOverlayTexture(i, bitmap2, AMapEngineUtils.ARROW_LINE_OUTER_TEXTURE_ID, 4);
            addOverlayTexture(i, bitmap3, 333, 4);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void addOverlayTexture(int i, Bitmap bitmap, int i2, int i3) {
        GLTextureProperty gLTextureProperty = new GLTextureProperty();
        gLTextureProperty.mId = i2;
        gLTextureProperty.mAnchor = i3;
        gLTextureProperty.mBitmap = bitmap;
        gLTextureProperty.mXRatio = 0.0f;
        gLTextureProperty.mYRatio = 0.0f;
        gLTextureProperty.isGenMimps = true;
        addOverlayTexture(i, gLTextureProperty);
    }

    public void updateNativeArrowOverlay(int i, String str, int[] iArr, int[] iArr2, int i2, int i3, int i4, float f2, int i5, int i6, int i7, boolean z) {
        long j = this.mNativeMapengineInstance;
        if (j == 0 || str == null) {
            return;
        }
        nativeUpdateNativeArrowOverlay(i, j, str, iArr, iArr2, i2, i3, i4, f2, z, i5, i6, i7);
    }

    public void removeNativeOverlay(int i, String str) {
        long j = this.mNativeMapengineInstance;
        if (j == 0 || str == null) {
            return;
        }
        nativeRemoveNativeOverlay(i, j, str);
    }

    public void removeNativeAllOverlay(int i) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeRemoveNativeAllOverlay(i, j);
        }
    }

    public GLMapEngine(Context context, IAMapDelegate iAMapDelegate) {
        this.mGlMapView = null;
        this.mapAnimationMgr = null;
        this.mRequestDestroy = false;
        this.mRequestDestroy = false;
        if (context == null) {
            return;
        }
        this.context = context.getApplicationContext();
        this.mGlMapView = iAMapDelegate;
        this.mTextTextureGenerator = new TextTextureGenerator();
        this.mapAnimationMgr = new AdglMapAnimationMgr();
        this.mapAnimationMgr.setMapAnimationListener(new AdglMapAnimationMgr.MapAnimationListener() { // from class: com.autonavi.base.ae.gmap.GLMapEngine.5
            @Override // com.autonavi.base.ae.gmap.glanimation.AdglMapAnimationMgr.MapAnimationListener
            public void onMapAnimationFinish(AMap.CancelableCallback cancelableCallback) {
                GLMapEngine.this.doMapAnimationFinishCallback(cancelableCallback);
            }
        });
        this.userAgent = System.getProperty("http.agent") + " amap/" + GlMapUtil.getAppVersionName(context);
    }

    public void createAMapInstance(InitParam initParam) {
        if (initParam == null) {
            return;
        }
        synchronized (GLMapEngine.class) {
            nativeInitParam(initParam.mRootPath, initParam.mConfigContent, initParam.mOfflineDataPath, initParam.mP3dCrossPath);
            DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
            this.mNativeMapengineInstance = nativeCreateAMapInstance("", "http://mpsapi.amap.com/", "http://m5.amap.com/", displayMetrics.densityDpi, displayMetrics.density, adapterDpiScale(displayMetrics, displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.densityDpi));
            nativeInitAMapEngineCallback(this.mNativeMapengineInstance, this);
            initNetworkState();
        }
    }

    private static String getEMUI() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, "ro.build.version.emui");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private float adapterDpiScale(DisplayMetrics displayMetrics, int i, int i2, int i3) {
        int i4;
        int i5;
        String emui = getEMUI();
        if (emui == null || emui.isEmpty()) {
            return 1.0f;
        }
        if ((emui.indexOf("EmotionUI_8") == -1 && emui.indexOf("EmotionUI_9") == -1) || i3 <= 0) {
            return 1.0f;
        }
        int i6 = 0;
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField("noncompatWidthPixels");
            declaredField.setAccessible(true);
            i4 = declaredField.getInt(displayMetrics);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            i4 = 0;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            i4 = 0;
        }
        try {
            Field declaredField2 = DisplayMetrics.class.getDeclaredField("noncompatHeightPixels");
            declaredField2.setAccessible(true);
            i5 = declaredField2.getInt(displayMetrics);
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            i5 = 0;
        } catch (NoSuchFieldException e5) {
            e5.printStackTrace();
            i5 = 0;
        }
        try {
            Field declaredField3 = DisplayMetrics.class.getDeclaredField("noncompatDensityDpi");
            declaredField3.setAccessible(true);
            i6 = declaredField3.getInt(displayMetrics);
        } catch (IllegalAccessException e6) {
            e6.printStackTrace();
        } catch (NoSuchFieldException e7) {
            e7.printStackTrace();
        }
        if (i6 <= i3 && i4 <= i && i5 <= i2) {
            return 1.0f;
        }
        float f2 = i6 / i3;
        if (f2 > 2.0f) {
            f2 = 2.0f;
        }
        if (f2 < 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    private void initNetworkState() {
        this.mNetworkState = new NetworkState();
        this.mNetworkState.setNetworkListener(this);
        this.mNetworkState.registerNetChangeReceiver(this.context.getApplicationContext(), true);
        boolean zIsNetworkConnected = NetworkState.isNetworkConnected(this.context.getApplicationContext());
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            nativeSetNetStatus(j, zIsNetworkConnected ? 1 : 0);
        }
    }

    public void createAMapEngineWithFrame(MapViewInitParam mapViewInitParam) {
        if (this.mNativeMapengineInstance != 0) {
            synchronized (GLMapEngine.class) {
                nativeCreateAMapEngineWithFrame(this.mNativeMapengineInstance, mapViewInitParam.engineId, mapViewInitParam.x, mapViewInitParam.y, mapViewInitParam.width, mapViewInitParam.height, mapViewInitParam.screenWidth, mapViewInitParam.screenHeight, mapViewInitParam.screenScale, mapViewInitParam.textScale, mapViewInitParam.mapZoomScale, mapViewInitParam.taskThreadCount);
            }
        }
    }

    public void renderAMap() {
        if (this.mNativeMapengineInstance != 0) {
            boolean zProcessMessage = processMessage();
            synchronized (GLMapEngine.class) {
                nativeRenderAMap(this.mNativeMapengineInstance, 1);
                nativePostRenderAMap(this.mNativeMapengineInstance, 1);
            }
            initAnimation();
            if (zProcessMessage) {
                startCheckEngineRenderComplete();
            }
            if (this.isEngineRenderComplete) {
                return;
            }
            nativeSetRenderListenerStatus(1, this.mNativeMapengineInstance);
        }
    }

    public void releaseNetworkState() {
        NetworkState networkState = this.mNetworkState;
        if (networkState != null) {
            networkState.registerNetChangeReceiver(this.context.getApplicationContext(), false);
            this.mNetworkState.setNetworkListener(null);
            this.mNetworkState = null;
        }
    }

    public void cancelAllAMapDownload() {
        try {
            synchronized (this.aMapLoaderHashtable) {
                Iterator<Map.Entry<Long, AMapLoader>> it = this.aMapLoaderHashtable.entrySet().iterator();
                while (it.hasNext()) {
                    AMapLoader value = it.next().getValue();
                    value.doCancel();
                    if (!value.isFinish) {
                        synchronized (value) {
                            if (!value.isFinish) {
                                value.notify();
                                value.isFinish = true;
                            }
                        }
                    }
                }
                this.aMapLoaderHashtable.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroyAMapEngine() {
        try {
            this.mRequestDestroy = true;
            cancelAllAMapDownload();
            synchronized (this.mutLock) {
                if (this.mNativeMapengineInstance != 0) {
                    synchronized (this) {
                        if (this.copyGLMapState != null) {
                            this.copyGLMapState.recycle();
                        }
                    }
                    nativeDestroyCurrentState(this.mNativeMapengineInstance, this.state.getNativeInstance());
                    nativeDestroy(this.mNativeMapengineInstance);
                }
                this.mNativeMapengineInstance = 0L;
            }
            this.mGlMapView = null;
            this.mStateMessageList.clear();
            this.mAnimateStateMessageList.clear();
            this.mGestureEndMessageList.clear();
            this.mGestureMessageList.clear();
            this.mMapListener = null;
            this.bundle = null;
            eq.b();
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
    }

    public long getNativeMapController(int i) {
        long j = this.mNativeMapengineInstance;
        if (j != 0) {
            return nativeGetNativeMapController(i, j);
        }
        return 0L;
    }
}