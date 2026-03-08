package com.king.zxing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.king.zxing.camera.CameraManager;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class CaptureHandler extends Handler implements ResultPointCallback {
    private static final String TAG = CaptureHandler.class.getSimpleName();
    private final Activity activity;
    private final CameraManager cameraManager;
    private final DecodeThread decodeThread;
    private boolean isReturnBitmap;
    private boolean isSupportAutoZoom;
    private boolean isSupportVerticalCode;
    private final OnCaptureListener onCaptureListener;
    private State state;
    private final ViewfinderView viewfinderView;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    CaptureHandler(Activity activity, ViewfinderView viewfinderView, OnCaptureListener onCaptureListener, Collection<BarcodeFormat> collection, Map<DecodeHintType, Object> map, String str, CameraManager cameraManager) {
        this.activity = activity;
        this.viewfinderView = viewfinderView;
        this.onCaptureListener = onCaptureListener;
        this.decodeThread = new DecodeThread(activity, cameraManager, this, collection, map, str, this);
        this.decodeThread.start();
        this.state = State.SUCCESS;
        this.cameraManager = cameraManager;
        cameraManager.startPreview();
        restartPreviewAndDecode();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == R.id.restart_preview) {
            restartPreviewAndDecode();
            return;
        }
        if (message.what == R.id.decode_succeeded) {
            this.state = State.SUCCESS;
            Bundle data = message.getData();
            float f2 = 1.0f;
            if (data != null) {
                byte[] byteArray = data.getByteArray(DecodeThread.BARCODE_BITMAP);
                bitmapCopy = byteArray != null ? BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, null).copy(Bitmap.Config.ARGB_8888, true) : null;
                f2 = data.getFloat(DecodeThread.BARCODE_SCALED_FACTOR);
            }
            this.onCaptureListener.onHandleDecode((Result) message.obj, bitmapCopy, f2);
            return;
        }
        if (message.what == R.id.decode_failed) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), R.id.decode);
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        this.cameraManager.stopPreview();
        Message.obtain(this.decodeThread.getHandler(), R.id.quit).sendToTarget();
        try {
            this.decodeThread.join(100L);
        } catch (InterruptedException unused) {
        }
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    public void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), R.id.decode);
            this.viewfinderView.drawViewfinder();
        }
    }

    @Override // com.google.zxing.ResultPointCallback
    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        if (this.viewfinderView != null) {
            this.viewfinderView.addPossibleResultPoint(transform(resultPoint));
        }
    }

    private boolean isScreenPortrait(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x < point.y;
    }

    private ResultPoint transform(ResultPoint resultPoint) {
        float x;
        float y;
        int iMax;
        Point screenResolution = this.cameraManager.getScreenResolution();
        Point cameraResolution = this.cameraManager.getCameraResolution();
        if (screenResolution.x < screenResolution.y) {
            x = (resultPoint.getX() * ((screenResolution.x * 1.0f) / cameraResolution.y)) - (Math.max(screenResolution.x, cameraResolution.y) / 2);
            y = resultPoint.getY() * ((screenResolution.y * 1.0f) / cameraResolution.x);
            iMax = Math.min(screenResolution.y, cameraResolution.x) / 2;
        } else {
            x = (resultPoint.getX() * ((screenResolution.x * 1.0f) / cameraResolution.x)) - (Math.min(screenResolution.y, cameraResolution.y) / 2);
            y = resultPoint.getY() * ((screenResolution.y * 1.0f) / cameraResolution.y);
            iMax = Math.max(screenResolution.x, cameraResolution.x) / 2;
        }
        return new ResultPoint(x, y - iMax);
    }

    public boolean isSupportVerticalCode() {
        return this.isSupportVerticalCode;
    }

    public void setSupportVerticalCode(boolean z) {
        this.isSupportVerticalCode = z;
    }

    public boolean isReturnBitmap() {
        return this.isReturnBitmap;
    }

    public void setReturnBitmap(boolean z) {
        this.isReturnBitmap = z;
    }

    public boolean isSupportAutoZoom() {
        return this.isSupportAutoZoom;
    }

    public void setSupportAutoZoom(boolean z) {
        this.isSupportAutoZoom = z;
    }
}