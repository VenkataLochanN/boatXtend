package com.king.zxing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.king.zxing.camera.CameraManager;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class DecodeHandler extends Handler {
    private static final String TAG = DecodeHandler.class.getSimpleName();
    private final CameraManager cameraManager;
    private final Context context;
    private final CaptureHandler handler;
    private long lastZoomTime;
    private boolean running = true;
    private boolean isInvert = false;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();

    DecodeHandler(Context context, CameraManager cameraManager, CaptureHandler captureHandler, Map<DecodeHintType, Object> map) {
        this.multiFormatReader.setHints(map);
        this.context = context;
        this.cameraManager = cameraManager;
        this.handler = captureHandler;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) throws NotFoundException {
        if (message == null || !this.running) {
            return;
        }
        if (message.what == R.id.decode) {
            decode((byte[]) message.obj, message.arg1, message.arg2, isScreenPortrait(), this.handler.isSupportVerticalCode());
        } else if (message.what == R.id.quit) {
            this.running = false;
            Looper.myLooper().quit();
        }
    }

    private boolean isScreenPortrait() {
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x < point.y;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0121  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void decode(byte[] r10, int r11, int r12, boolean r13, boolean r14) throws com.google.zxing.NotFoundException {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.king.zxing.DecodeHandler.decode(byte[], int, int, boolean, boolean):void");
    }

    private PlanarYUVLuminanceSource buildPlanarYUVLuminanceSource(byte[] bArr, int i, int i2, boolean z) {
        if (z) {
            byte[] bArr2 = new byte[bArr.length];
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
                }
            }
            return this.cameraManager.buildLuminanceSource(bArr2, i2, i);
        }
        return this.cameraManager.buildLuminanceSource(bArr, i, i2);
    }

    private static void bundleThumbnail(PlanarYUVLuminanceSource planarYUVLuminanceSource, Bundle bundle) {
        int[] iArrRenderThumbnail = planarYUVLuminanceSource.renderThumbnail();
        int thumbnailWidth = planarYUVLuminanceSource.getThumbnailWidth();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArrRenderThumbnail, 0, thumbnailWidth, thumbnailWidth, planarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray(DecodeThread.BARCODE_BITMAP, byteArrayOutputStream.toByteArray());
        bundle.putFloat(DecodeThread.BARCODE_SCALED_FACTOR, thumbnailWidth / planarYUVLuminanceSource.getWidth());
    }

    private boolean handleAutoZoom(int i, int i2) {
        Camera camera;
        if (this.lastZoomTime > System.currentTimeMillis() - 1000) {
            return true;
        }
        if (i >= i2 / 5 || (camera = this.cameraManager.getOpenCamera().getCamera()) == null) {
            return false;
        }
        Camera.Parameters parameters = camera.getParameters();
        if (parameters.isZoomSupported()) {
            int maxZoom = parameters.getMaxZoom();
            parameters.setZoom(Math.min(parameters.getZoom() + (maxZoom / 5), maxZoom));
            camera.setParameters(parameters);
            this.lastZoomTime = System.currentTimeMillis();
            return true;
        }
        Log.i(TAG, "Zoom not supported");
        return false;
    }
}