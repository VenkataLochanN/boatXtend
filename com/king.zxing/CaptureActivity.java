package com.king.zxing;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import com.king.zxing.camera.CameraManager;

/* JADX INFO: loaded from: classes3.dex */
public class CaptureActivity extends Activity implements OnCaptureCallback {
    public static final String KEY_RESULT = "SCAN_RESULT";
    private View ivTorch;
    private CaptureHelper mCaptureHelper;
    private SurfaceView surfaceView;
    private ViewfinderView viewfinderView;

    public boolean isContentView(int i) {
        return true;
    }

    @Override // com.king.zxing.OnCaptureCallback
    public boolean onResultCallback(String str) {
        return false;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int layoutId = getLayoutId();
        if (isContentView(layoutId)) {
            setContentView(layoutId);
        }
        initUI();
    }

    public void initUI() {
        this.surfaceView = (SurfaceView) findViewById(getSurfaceViewId());
        this.viewfinderView = (ViewfinderView) findViewById(getViewfinderViewId());
        int ivTorchId = getIvTorchId();
        if (ivTorchId != 0) {
            this.ivTorch = findViewById(ivTorchId);
            this.ivTorch.setVisibility(4);
        }
        this.mCaptureHelper = new CaptureHelper(this, this.surfaceView, this.viewfinderView, this.ivTorch);
        this.mCaptureHelper.setOnCaptureCallback(this);
        this.mCaptureHelper.onCreate();
    }

    public int getLayoutId() {
        return R.layout.zxl_capture;
    }

    public int getViewfinderViewId() {
        return R.id.viewfinderView;
    }

    public int getSurfaceViewId() {
        return R.id.surfaceView;
    }

    public int getIvTorchId() {
        return R.id.ivTorch;
    }

    public CaptureHelper getCaptureHelper() {
        return this.mCaptureHelper;
    }

    public CameraManager getCameraManager() {
        return this.mCaptureHelper.getCameraManager();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.mCaptureHelper.onResume();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mCaptureHelper.onPause();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mCaptureHelper.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mCaptureHelper.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}