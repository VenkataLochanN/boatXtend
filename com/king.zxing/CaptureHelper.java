package com.king.zxing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.king.zxing.camera.CameraManager;
import com.king.zxing.camera.FrontLightMode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class CaptureHelper implements CaptureLifecycle, CaptureTouchEvent, CaptureManager, SurfaceHolder.Callback {
    private static final int DEVIATION = 6;
    public static final String TAG = CaptureHelper.class.getSimpleName();
    private Activity activity;
    private AmbientLightManager ambientLightManager;
    private BeepManager beepManager;
    private float brightEnoughLux;
    private CameraManager cameraManager;
    private CaptureHandler captureHandler;
    private String characterSet;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, Object> decodeHints;
    private int framingRectHorizontalOffset;
    private float framingRectRatio;
    private int framingRectVerticalOffset;
    private boolean hasCameraFlash;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private boolean isAutoRestartPreviewAndDecode;
    private boolean isContinuousScan;
    private boolean isFullScreenScan;
    private boolean isPlayBeep;
    private boolean isReturnBitmap;
    private boolean isSupportAutoZoom;
    private boolean isSupportVerticalCode;
    private boolean isSupportZoom;
    private boolean isVibrate;
    private View ivTorch;
    private float oldDistance;
    private OnCaptureCallback onCaptureCallback;
    private OnCaptureListener onCaptureListener;
    private SurfaceHolder surfaceHolder;
    private float tooDarkLux;
    private ViewfinderView viewfinderView;

    private int clamp(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Deprecated
    public CaptureHelper(Fragment fragment, SurfaceView surfaceView, ViewfinderView viewfinderView) {
        this(fragment, surfaceView, viewfinderView, (View) null);
    }

    public CaptureHelper(Fragment fragment, SurfaceView surfaceView, ViewfinderView viewfinderView, View view) {
        this(fragment.getActivity(), surfaceView, viewfinderView, view);
    }

    @Deprecated
    public CaptureHelper(Activity activity, SurfaceView surfaceView, ViewfinderView viewfinderView) {
        this(activity, surfaceView, viewfinderView, (View) null);
    }

    public CaptureHelper(Activity activity, SurfaceView surfaceView, ViewfinderView viewfinderView, View view) {
        this.isSupportZoom = true;
        this.isSupportAutoZoom = true;
        this.isContinuousScan = false;
        this.isAutoRestartPreviewAndDecode = true;
        this.framingRectRatio = 0.9f;
        this.tooDarkLux = 45.0f;
        this.brightEnoughLux = 100.0f;
        this.activity = activity;
        this.viewfinderView = viewfinderView;
        this.ivTorch = view;
        this.surfaceHolder = surfaceView.getHolder();
        this.hasSurface = false;
    }

    @Override // com.king.zxing.CaptureLifecycle
    public void onCreate() {
        this.inactivityTimer = new InactivityTimer(this.activity);
        this.beepManager = new BeepManager(this.activity);
        this.ambientLightManager = new AmbientLightManager(this.activity);
        this.hasCameraFlash = this.activity.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        initCameraManager();
        this.onCaptureListener = new OnCaptureListener() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$M1LKX0hZL5VGLrV8hfodXcHppF8
            @Override // com.king.zxing.OnCaptureListener
            public final void onHandleDecode(Result result, Bitmap bitmap, float f2) {
                this.f$0.lambda$onCreate$0$CaptureHelper(result, bitmap, f2);
            }
        };
        this.beepManager.setPlayBeep(this.isPlayBeep);
        this.beepManager.setVibrate(this.isVibrate);
        this.ambientLightManager.setTooDarkLux(this.tooDarkLux);
        this.ambientLightManager.setBrightEnoughLux(this.brightEnoughLux);
    }

    public /* synthetic */ void lambda$onCreate$0$CaptureHelper(Result result, Bitmap bitmap, float f2) {
        this.inactivityTimer.onActivity();
        this.beepManager.playBeepSoundAndVibrate();
        onResult(result, bitmap, f2);
    }

    @Override // com.king.zxing.CaptureLifecycle
    public void onResume() {
        this.beepManager.updatePrefs();
        this.inactivityTimer.onResume();
        if (this.hasSurface) {
            initCamera(this.surfaceHolder);
        } else {
            this.surfaceHolder.addCallback(this);
        }
        this.ambientLightManager.start(this.cameraManager);
    }

    @Override // com.king.zxing.CaptureLifecycle
    public void onPause() {
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.quitSynchronously();
            this.captureHandler = null;
        }
        this.inactivityTimer.onPause();
        this.ambientLightManager.stop();
        this.beepManager.close();
        this.cameraManager.closeDriver();
        if (this.hasSurface) {
            return;
        }
        this.surfaceHolder.removeCallback(this);
    }

    @Override // com.king.zxing.CaptureLifecycle
    public void onDestroy() {
        this.inactivityTimer.shutdown();
    }

    @Override // com.king.zxing.CaptureTouchEvent
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Camera camera;
        if (!this.isSupportZoom || !this.cameraManager.isOpen() || (camera = this.cameraManager.getOpenCamera().getCamera()) == null || motionEvent.getPointerCount() <= 1) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 2) {
            float fCalcFingerSpacing = calcFingerSpacing(motionEvent);
            float f2 = this.oldDistance;
            if (fCalcFingerSpacing > f2 + 6.0f) {
                handleZoom(true, camera);
            } else if (fCalcFingerSpacing < f2 - 6.0f) {
                handleZoom(false, camera);
            }
            this.oldDistance = fCalcFingerSpacing;
        } else if (action == 5) {
            this.oldDistance = calcFingerSpacing(motionEvent);
        }
        return true;
    }

    private void initCameraManager() {
        this.cameraManager = new CameraManager(this.activity);
        this.cameraManager.setFullScreenScan(this.isFullScreenScan);
        this.cameraManager.setFramingRectRatio(this.framingRectRatio);
        this.cameraManager.setFramingRectVerticalOffset(this.framingRectVerticalOffset);
        this.cameraManager.setFramingRectHorizontalOffset(this.framingRectHorizontalOffset);
        View view = this.ivTorch;
        if (view == null || !this.hasCameraFlash) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$i8cvNEWL6OlZjVbzRDtr3lazGZc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$initCameraManager$1$CaptureHelper(view2);
            }
        });
        this.cameraManager.setOnSensorListener(new CameraManager.OnSensorListener() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$qZVOqVqKk6calUaIsNPo5S2Svww
            @Override // com.king.zxing.camera.CameraManager.OnSensorListener
            public final void onSensorChanged(boolean z, boolean z2, float f2) {
                this.f$0.lambda$initCameraManager$2$CaptureHelper(z, z2, f2);
            }
        });
        this.cameraManager.setOnTorchListener(new CameraManager.OnTorchListener() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$rpoAt43hjil8ox87CgThrHqtLBQ
            @Override // com.king.zxing.camera.CameraManager.OnTorchListener
            public final void onTorchChanged(boolean z) {
                this.f$0.lambda$initCameraManager$3$CaptureHelper(z);
            }
        });
    }

    public /* synthetic */ void lambda$initCameraManager$1$CaptureHelper(View view) {
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setTorch(!this.ivTorch.isSelected());
        }
    }

    public /* synthetic */ void lambda$initCameraManager$2$CaptureHelper(boolean z, boolean z2, float f2) {
        this.ivTorch.setVisibility(0);
    }

    public /* synthetic */ void lambda$initCameraManager$3$CaptureHelper(boolean z) {
        this.ivTorch.setSelected(z);
    }

    public void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (this.cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            this.cameraManager.openDriver(surfaceHolder);
            if (this.captureHandler == null) {
                this.captureHandler = new CaptureHandler(this.activity, this.viewfinderView, this.onCaptureListener, this.decodeFormats, this.decodeHints, this.characterSet, this.cameraManager);
                this.captureHandler.setSupportVerticalCode(this.isSupportVerticalCode);
                this.captureHandler.setReturnBitmap(this.isReturnBitmap);
                this.captureHandler.setSupportAutoZoom(this.isSupportAutoZoom);
            }
        } catch (IOException e2) {
            Log.w(TAG, e2);
        } catch (RuntimeException e3) {
            Log.w(TAG, "Unexpected error initializing camera", e3);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
        initCamera(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
    }

    private void handleZoom(boolean z, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        if (parameters.isZoomSupported()) {
            int maxZoom = parameters.getMaxZoom();
            int zoom = parameters.getZoom();
            if (z && zoom < maxZoom) {
                zoom++;
            } else if (zoom > 0) {
                zoom--;
            }
            parameters.setZoom(zoom);
            camera.setParameters(parameters);
            return;
        }
        Log.i(TAG, "zoom not supported");
    }

    @Deprecated
    private void focusOnTouch(MotionEvent motionEvent, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size previewSize = parameters.getPreviewSize();
        Rect rectCalcTapArea = calcTapArea(motionEvent.getRawX(), motionEvent.getRawY(), 1.0f, previewSize);
        Rect rectCalcTapArea2 = calcTapArea(motionEvent.getRawX(), motionEvent.getRawY(), 1.5f, previewSize);
        Camera.Parameters parameters2 = camera.getParameters();
        if (parameters2.getMaxNumFocusAreas() > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Camera.Area(rectCalcTapArea, 600));
            parameters2.setFocusAreas(arrayList);
        }
        if (parameters2.getMaxNumMeteringAreas() > 0) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new Camera.Area(rectCalcTapArea2, 600));
            parameters2.setMeteringAreas(arrayList2);
        }
        final String focusMode = parameters.getFocusMode();
        parameters.setFocusMode("macro");
        camera.setParameters(parameters);
        camera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$ylDXELNLTSPjWjiWiDjfyhgXJhc
            @Override // android.hardware.Camera.AutoFocusCallback
            public final void onAutoFocus(boolean z, Camera camera2) {
                CaptureHelper.lambda$focusOnTouch$4(focusMode, z, camera2);
            }
        });
    }

    static /* synthetic */ void lambda$focusOnTouch$4(String str, boolean z, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFocusMode(str);
        camera.setParameters(parameters);
    }

    private float calcFingerSpacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private Rect calcTapArea(float f2, float f3, float f4, Camera.Size size) {
        int i = (int) (((f2 / size.width) * 2000.0f) - 1000.0f);
        int i2 = (int) (((f3 / size.height) * 2000.0f) - 1000.0f);
        int iIntValue = Float.valueOf(f4 * 200.0f).intValue() / 2;
        RectF rectF = new RectF(clamp(i - iIntValue, -1000, 1000), clamp(i2 - iIntValue, -1000, 1000), r3 + r5, r4 + r5);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public void restartPreviewAndDecode() {
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.restartPreviewAndDecode();
        }
    }

    public void onResult(Result result, Bitmap bitmap, float f2) {
        onResult(result);
    }

    public void onResult(Result result) {
        final String text = result.getText();
        if (this.isContinuousScan) {
            OnCaptureCallback onCaptureCallback = this.onCaptureCallback;
            if (onCaptureCallback != null) {
                onCaptureCallback.onResultCallback(text);
            }
            if (this.isAutoRestartPreviewAndDecode) {
                restartPreviewAndDecode();
                return;
            }
            return;
        }
        if (this.isPlayBeep) {
            this.captureHandler.postDelayed(new Runnable() { // from class: com.king.zxing.-$$Lambda$CaptureHelper$qeCs8VHWSPAGjlauoPkYu9qs5NM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onResult$5$CaptureHelper(text);
                }
            }, 100L);
            return;
        }
        OnCaptureCallback onCaptureCallback2 = this.onCaptureCallback;
        if (onCaptureCallback2 == null || onCaptureCallback2.onResultCallback(text)) {
        }
    }

    public /* synthetic */ void lambda$onResult$5$CaptureHelper(String str) {
        OnCaptureCallback onCaptureCallback = this.onCaptureCallback;
        if (onCaptureCallback == null || onCaptureCallback.onResultCallback(str)) {
        }
    }

    public CaptureHelper continuousScan(boolean z) {
        this.isContinuousScan = z;
        return this;
    }

    public CaptureHelper autoRestartPreviewAndDecode(boolean z) {
        this.isAutoRestartPreviewAndDecode = z;
        return this;
    }

    public CaptureHelper playBeep(boolean z) {
        this.isPlayBeep = z;
        BeepManager beepManager = this.beepManager;
        if (beepManager != null) {
            beepManager.setPlayBeep(z);
        }
        return this;
    }

    public CaptureHelper vibrate(boolean z) {
        this.isVibrate = z;
        BeepManager beepManager = this.beepManager;
        if (beepManager != null) {
            beepManager.setVibrate(z);
        }
        return this;
    }

    public CaptureHelper supportZoom(boolean z) {
        this.isSupportZoom = z;
        return this;
    }

    public CaptureHelper decodeFormats(Collection<BarcodeFormat> collection) {
        this.decodeFormats = collection;
        return this;
    }

    public CaptureHelper decodeHints(Map<DecodeHintType, Object> map) {
        this.decodeHints = map;
        return this;
    }

    public CaptureHelper decodeHint(DecodeHintType decodeHintType, Object obj) {
        if (this.decodeHints == null) {
            this.decodeHints = new EnumMap(DecodeHintType.class);
        }
        this.decodeHints.put(decodeHintType, obj);
        return this;
    }

    public CaptureHelper characterSet(String str) {
        this.characterSet = str;
        return this;
    }

    public CaptureHelper supportVerticalCode(boolean z) {
        this.isSupportVerticalCode = z;
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.setSupportVerticalCode(this.isSupportVerticalCode);
        }
        return this;
    }

    public CaptureHelper frontLightMode(FrontLightMode frontLightMode) {
        FrontLightMode.put(this.activity, frontLightMode);
        if (this.ivTorch != null && frontLightMode != FrontLightMode.AUTO) {
            this.ivTorch.setVisibility(4);
        }
        return this;
    }

    public CaptureHelper tooDarkLux(float f2) {
        this.tooDarkLux = f2;
        AmbientLightManager ambientLightManager = this.ambientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.setTooDarkLux(f2);
        }
        return this;
    }

    public CaptureHelper brightEnoughLux(float f2) {
        this.brightEnoughLux = f2;
        AmbientLightManager ambientLightManager = this.ambientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.setTooDarkLux(this.tooDarkLux);
        }
        return this;
    }

    public CaptureHelper returnBitmap(boolean z) {
        this.isReturnBitmap = z;
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.setReturnBitmap(this.isReturnBitmap);
        }
        return this;
    }

    public CaptureHelper supportAutoZoom(boolean z) {
        this.isSupportAutoZoom = z;
        CaptureHandler captureHandler = this.captureHandler;
        if (captureHandler != null) {
            captureHandler.setSupportAutoZoom(this.isSupportAutoZoom);
        }
        return this;
    }

    public CaptureHelper fullScreenScan(boolean z) {
        this.isFullScreenScan = z;
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setFullScreenScan(this.isFullScreenScan);
        }
        return this;
    }

    public CaptureHelper framingRectRatio(float f2) {
        this.framingRectRatio = f2;
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setFramingRectRatio(f2);
        }
        return this;
    }

    public CaptureHelper framingRectVerticalOffset(int i) {
        this.framingRectVerticalOffset = i;
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setFramingRectVerticalOffset(i);
        }
        return this;
    }

    public CaptureHelper framingRectHorizontalOffset(int i) {
        this.framingRectHorizontalOffset = i;
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            cameraManager.setFramingRectHorizontalOffset(i);
        }
        return this;
    }

    public CaptureHelper setOnCaptureCallback(OnCaptureCallback onCaptureCallback) {
        this.onCaptureCallback = onCaptureCallback;
        return this;
    }

    @Override // com.king.zxing.CaptureManager
    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    @Override // com.king.zxing.CaptureManager
    public BeepManager getBeepManager() {
        return this.beepManager;
    }

    @Override // com.king.zxing.CaptureManager
    public AmbientLightManager getAmbientLightManager() {
        return this.ambientLightManager;
    }

    @Override // com.king.zxing.CaptureManager
    public InactivityTimer getInactivityTimer() {
        return this.inactivityTimer;
    }
}