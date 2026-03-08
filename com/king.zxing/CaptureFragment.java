package com.king.zxing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.king.zxing.camera.CameraManager;

/* JADX INFO: loaded from: classes3.dex */
public class CaptureFragment extends Fragment implements OnCaptureCallback {
    public static final String KEY_RESULT = "SCAN_RESULT";
    private View ivTorch;
    private CaptureHelper mCaptureHelper;
    private View mRootView;
    private SurfaceView surfaceView;
    private ViewfinderView viewfinderView;

    public boolean isContentView(int i) {
        return true;
    }

    @Override // com.king.zxing.OnCaptureCallback
    public boolean onResultCallback(String str) {
        return false;
    }

    public static CaptureFragment newInstance() {
        Bundle bundle = new Bundle();
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setArguments(bundle);
        return captureFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (isContentView(getLayoutId())) {
            this.mRootView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        }
        initUI();
        return this.mRootView;
    }

    public void initUI() {
        this.surfaceView = (SurfaceView) this.mRootView.findViewById(getSurfaceViewId());
        this.viewfinderView = (ViewfinderView) this.mRootView.findViewById(getViewfinderViewId());
        int ivTorchId = getIvTorchId();
        if (ivTorchId != 0) {
            this.ivTorch = this.mRootView.findViewById(ivTorchId);
            this.ivTorch.setVisibility(4);
        }
        this.mCaptureHelper = new CaptureHelper(this, this.surfaceView, this.viewfinderView, this.ivTorch);
        this.mCaptureHelper.setOnCaptureCallback(this);
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

    public View getRootView() {
        return this.mRootView;
    }

    public void setRootView(View view) {
        this.mRootView = view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mCaptureHelper.onCreate();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mCaptureHelper.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mCaptureHelper.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCaptureHelper.onDestroy();
    }
}