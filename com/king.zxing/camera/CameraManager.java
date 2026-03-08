package com.king.zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.king.zxing.camera.open.OpenCamera;
import com.king.zxing.camera.open.OpenCameraInterface;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class CameraManager {
    private static final int MAX_FRAME_HEIGHT = 675;
    private static final int MAX_FRAME_WIDTH = 1200;
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MIN_FRAME_WIDTH = 240;
    private static final String TAG = CameraManager.class.getSimpleName();
    private AutoFocusManager autoFocusManager;
    private OpenCamera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private int framingRectHorizontalOffset;
    private Rect framingRectInPreview;
    private float framingRectRatio;
    private int framingRectVerticalOffset;
    private boolean initialized;
    private boolean isFullScreenScan;
    private boolean isTorch;
    private OnSensorListener onSensorListener;
    private OnTorchListener onTorchListener;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private int requestedCameraId = -1;
    private int requestedFramingRectHeight;
    private int requestedFramingRectWidth;

    public interface OnSensorListener {
        void onSensorChanged(boolean z, boolean z2, float f2);
    }

    public interface OnTorchListener {
        void onTorchChanged(boolean z);
    }

    public CameraManager(Context context) {
        this.context = context.getApplicationContext();
        this.configManager = new CameraConfigurationManager(context);
        this.previewCallback = new PreviewCallback(this.configManager);
    }

    public void openDriver(SurfaceHolder surfaceHolder) throws IOException {
        int i;
        OpenCamera openCameraOpen = this.camera;
        if (openCameraOpen == null) {
            openCameraOpen = OpenCameraInterface.open(this.requestedCameraId);
            if (openCameraOpen == null) {
                throw new IOException("Camera.open() failed to return object from driver");
            }
            this.camera = openCameraOpen;
        }
        if (!this.initialized) {
            this.initialized = true;
            this.configManager.initFromCameraParameters(openCameraOpen);
            int i2 = this.requestedFramingRectWidth;
            if (i2 > 0 && (i = this.requestedFramingRectHeight) > 0) {
                setManualFramingRect(i2, i);
                this.requestedFramingRectWidth = 0;
                this.requestedFramingRectHeight = 0;
            }
        }
        Camera camera = openCameraOpen.getCamera();
        Camera.Parameters parameters = camera.getParameters();
        String strFlatten = parameters == null ? null : parameters.flatten();
        try {
            this.configManager.setDesiredCameraParameters(openCameraOpen, false);
        } catch (RuntimeException unused) {
            Log.w(TAG, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(TAG, "Resetting to saved camera params: " + strFlatten);
            if (strFlatten != null) {
                Camera.Parameters parameters2 = camera.getParameters();
                parameters2.unflatten(strFlatten);
                try {
                    camera.setParameters(parameters2);
                    this.configManager.setDesiredCameraParameters(openCameraOpen, true);
                } catch (RuntimeException unused2) {
                    Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
        camera.setPreviewDisplay(surfaceHolder);
    }

    public synchronized boolean isOpen() {
        return this.camera != null;
    }

    public OpenCamera getOpenCamera() {
        return this.camera;
    }

    public void closeDriver() {
        OpenCamera openCamera = this.camera;
        if (openCamera != null) {
            openCamera.getCamera().release();
            this.camera = null;
            this.framingRect = null;
            this.framingRectInPreview = null;
        }
    }

    public void startPreview() {
        OpenCamera openCamera = this.camera;
        if (openCamera == null || this.previewing) {
            return;
        }
        openCamera.getCamera().startPreview();
        this.previewing = true;
        this.autoFocusManager = new AutoFocusManager(this.context, openCamera.getCamera());
    }

    public void stopPreview() {
        AutoFocusManager autoFocusManager = this.autoFocusManager;
        if (autoFocusManager != null) {
            autoFocusManager.stop();
            this.autoFocusManager = null;
        }
        OpenCamera openCamera = this.camera;
        if (openCamera == null || !this.previewing) {
            return;
        }
        openCamera.getCamera().stopPreview();
        this.previewCallback.setHandler(null, 0);
        this.previewing = false;
    }

    public synchronized void setTorch(boolean z) {
        OpenCamera openCamera = this.camera;
        if (openCamera != null && z != this.configManager.getTorchState(openCamera.getCamera())) {
            boolean z2 = this.autoFocusManager != null;
            if (z2) {
                this.autoFocusManager.stop();
                this.autoFocusManager = null;
            }
            this.isTorch = z;
            this.configManager.setTorch(openCamera.getCamera(), z);
            if (z2) {
                this.autoFocusManager = new AutoFocusManager(this.context, openCamera.getCamera());
                this.autoFocusManager.start();
            }
            if (this.onTorchListener != null) {
                this.onTorchListener.onTorchChanged(z);
            }
        }
    }

    public synchronized void requestPreviewFrame(Handler handler, int i) {
        OpenCamera openCamera = this.camera;
        if (openCamera != null && this.previewing) {
            this.previewCallback.setHandler(handler, i);
            openCamera.getCamera().setOneShotPreviewCallback(this.previewCallback);
        }
    }

    public synchronized Rect getFramingRect() {
        if (this.framingRect == null) {
            if (this.camera == null) {
                return null;
            }
            Point cameraResolution = this.configManager.getCameraResolution();
            if (cameraResolution == null) {
                return null;
            }
            int i = cameraResolution.x;
            int i2 = cameraResolution.y;
            if (this.isFullScreenScan) {
                this.framingRect = new Rect(0, 0, i, i2);
            } else {
                int iMin = (int) (Math.min(i, i2) * this.framingRectRatio);
                int i3 = ((i - iMin) / 2) + this.framingRectHorizontalOffset;
                int i4 = ((i2 - iMin) / 2) + this.framingRectVerticalOffset;
                this.framingRect = new Rect(i3, i4, i3 + iMin, iMin + i4);
            }
        }
        return this.framingRect;
    }

    public synchronized Rect getFramingRectInPreview() {
        if (this.framingRectInPreview == null) {
            Rect framingRect = getFramingRect();
            if (framingRect == null) {
                return null;
            }
            Rect rect = new Rect(framingRect);
            Point cameraResolution = this.configManager.getCameraResolution();
            Point screenResolution = this.configManager.getScreenResolution();
            if (cameraResolution != null && screenResolution != null) {
                rect.left = (rect.left * cameraResolution.y) / screenResolution.x;
                rect.right = (rect.right * cameraResolution.y) / screenResolution.x;
                rect.top = (rect.top * cameraResolution.x) / screenResolution.y;
                rect.bottom = (rect.bottom * cameraResolution.x) / screenResolution.y;
                this.framingRectInPreview = rect;
            }
            return null;
        }
        return this.framingRectInPreview;
    }

    public void setFullScreenScan(boolean z) {
        this.isFullScreenScan = z;
    }

    public void setFramingRectRatio(float f2) {
        this.framingRectRatio = f2;
    }

    public void setFramingRectVerticalOffset(int i) {
        this.framingRectVerticalOffset = i;
    }

    public void setFramingRectHorizontalOffset(int i) {
        this.framingRectHorizontalOffset = i;
    }

    public Point getCameraResolution() {
        return this.configManager.getCameraResolution();
    }

    public Point getScreenResolution() {
        return this.configManager.getScreenResolution();
    }

    public synchronized void setManualCameraId(int i) {
        this.requestedCameraId = i;
    }

    public synchronized void setManualFramingRect(int i, int i2) {
        if (this.initialized) {
            Point screenResolution = this.configManager.getScreenResolution();
            if (i > screenResolution.x) {
                i = screenResolution.x;
            }
            if (i2 > screenResolution.y) {
                i2 = screenResolution.y;
            }
            int i3 = (screenResolution.x - i) / 2;
            int i4 = (screenResolution.y - i2) / 2;
            this.framingRect = new Rect(i3, i4, i + i3, i2 + i4);
            Log.d(TAG, "Calculated manual framing rect: " + this.framingRect);
            this.framingRectInPreview = null;
        } else {
            this.requestedFramingRectWidth = i;
            this.requestedFramingRectHeight = i2;
        }
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        if (getFramingRectInPreview() == null) {
            return null;
        }
        if (this.isFullScreenScan) {
            return new PlanarYUVLuminanceSource(bArr, i, i2, 0, 0, i, i2, false);
        }
        int iMin = (int) (Math.min(i, i2) * this.framingRectRatio);
        return new PlanarYUVLuminanceSource(bArr, i, i2, ((i - iMin) / 2) + this.framingRectHorizontalOffset, ((i2 - iMin) / 2) + this.framingRectVerticalOffset, iMin, iMin, false);
    }

    public void setOnTorchListener(OnTorchListener onTorchListener) {
        this.onTorchListener = onTorchListener;
    }

    public void setOnSensorListener(OnSensorListener onSensorListener) {
        this.onSensorListener = onSensorListener;
    }

    public void sensorChanged(boolean z, float f2) {
        OnSensorListener onSensorListener = this.onSensorListener;
        if (onSensorListener != null) {
            onSensorListener.onSensorChanged(this.isTorch, z, f2);
        }
    }
}