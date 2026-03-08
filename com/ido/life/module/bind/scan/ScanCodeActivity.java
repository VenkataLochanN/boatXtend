package com.ido.life.module.bind.scan;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.ble.BleHelper;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.bind.BindActivity;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;
import com.ido.life.module.bind.scan.ScanContract;
import com.ido.life.module.device.activity.CommonWebViewActivity;
import com.king.zxing.CaptureHelper;
import com.king.zxing.OnCaptureCallback;
import com.king.zxing.ViewfinderView;

/* JADX INFO: loaded from: classes2.dex */
public class ScanCodeActivity extends BaseCoreActivity implements ScanContract.View, PermissionUtil.RequestResult, OnCaptureCallback {
    static final int CAMERA_REQUEST_CODE = 10002;
    private static final String TAG = "ScanCodeActivity";
    private CaptureHelper mCaptureHelper;

    @BindView(R.id.iv_torch)
    ImageView mIvTorch;
    private ScanContract.Presenter mPresenter;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.tv_light)
    TextView mTvLight;

    @BindView(R.id.tv_scan_not_find)
    TextView mTvScanNotFind;
    private PermissionUtil permissionUtil;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private ViewfinderView viewfinderView;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_scan_code;
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void onSearchFailed() {
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void onSearchFinished() {
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initUI();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mTvLight.setText(LanguageUtil.getLanguageText(R.string.scan_touch_light));
        this.mTvScanNotFind.setText(LanguageUtil.getLanguageText(R.string.scan_no_find));
        setBindFailedSpannable(this.mTvScanNotFind.getText().toString());
    }

    private void setBindFailedSpannable(String str) {
        String string = getString(R.string.scan_blue_search);
        if (str.contains(string)) {
            int iIndexOf = str.indexOf(string);
            if (iIndexOf == -1) {
                iIndexOf = 0;
            }
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.module.bind.scan.ScanCodeActivity.1
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    ScanCodeActivity.this.startActivity(new Intent(ScanCodeActivity.this, (Class<?>) ChoiceBlueTypeActivity.class));
                    ActivityCompat.finishAfterTransition(ScanCodeActivity.this);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(ScanCodeActivity.this.getColor(R.color.color_027AFF));
                    textPaint.setUnderlineText(false);
                }
            }, iIndexOf, string.length() + iIndexOf, 33);
            this.mTvScanNotFind.setText(spannableString);
            this.mTvScanNotFind.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
    }

    private void initCapture() {
        this.mCaptureHelper.onCreate();
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    public void initUI() {
        this.surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        this.viewfinderView = (ViewfinderView) findViewById(R.id.viewfinderView);
        this.mIvTorch.setVisibility(0);
        if (this.surfaceHolder == null) {
            this.surfaceHolder = this.surfaceView.getHolder();
        }
        this.mCaptureHelper = new CaptureHelper(this, this.surfaceView, this.viewfinderView, this.mIvTorch);
        this.mCaptureHelper.setOnCaptureCallback(this);
        initCapture();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        if (this.mPresenter == null) {
            this.mPresenter = new ScanPresenter(this);
        }
        this.permissionUtil = new PermissionUtil();
        this.permissionUtil.setRequestResult(this);
        CommonLogUtil.d(TAG, "initData: " + checkPermission(PermissionUtil.getCameraPermission()));
        if (checkPermission(PermissionUtil.getCameraPermission())) {
            return;
        }
        CommonLogUtil.d(TAG, "initData: " + isDeniedByNoAsk(PermissionUtil.getOnlyCameraPermission()) + AppInfo.DELIM + isDeniedByNoAsk(PermissionUtil.getStoragePermission()));
        if (isDeniedByNoAsk(PermissionUtil.getOnlyCameraPermission())) {
            requestPermissions(10002, PermissionUtil.getCameraPermission());
        } else {
            showOpenCameraDialog();
        }
    }

    private void showOpenCameraDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.tips), LanguageUtil.getLanguageText(R.string.scan_no_camera_permission), LanguageUtil.getLanguageText(R.string.i_see), LanguageUtil.getLanguageText(R.string.public_tip_cancel), false);
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.scan.-$$Lambda$ScanCodeActivity$OzlLkytjwsRf_NSM4eqO4pPtHo8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showOpenCameraDialog$0$ScanCodeActivity(commBottomConfirmDialogNewInstance, view);
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showOpenCameraDialog$0$ScanCodeActivity(CommBottomConfirmDialog commBottomConfirmDialog, View view) {
        commBottomConfirmDialog.dismissAllowingStateLoss();
        goBack();
    }

    private boolean isDeniedByNoAsk(String... strArr) {
        for (String str : strArr) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void requestPermissions(int i, String... strArr) {
        PermissionUtil.requestPermissions(this, i, strArr);
    }

    public boolean checkPermission(String... strArr) {
        return PermissionUtil.checkSelfPermission(this, strArr);
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void goBindActivity(BLEDevice bLEDevice) {
        Intent intent = new Intent(this, (Class<?>) BindActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.INTENT_DATA_KEY, bLEDevice);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void goBack() {
        CommonLogUtil.d(TAG, "goBack: ");
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        CaptureHelper captureHelper;
        super.onResume();
        if (!checkSelfPermission("android.permission.CAMERA") || (captureHelper = this.mCaptureHelper) == null) {
            return;
        }
        captureHelper.onResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        CaptureHelper captureHelper = this.mCaptureHelper;
        if (captureHelper != null) {
            captureHelper.onPause();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CaptureHelper captureHelper = this.mCaptureHelper;
        if (captureHelper != null) {
            captureHelper.onDestroy();
        }
        this.mPresenter.stopScan();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mCaptureHelper.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void onNeedOpenBle() {
        BleHelper.openBLE(this);
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void goBind(BLEDevice bLEDevice) {
        Intent intent = new Intent(this, (Class<?>) BindActivity.class);
        intent.putExtra(Constants.INTENT_DATA_KEY, bLEDevice);
        startActivity(intent);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void toWebView(String str) {
        Intent intent = new Intent(this, (Class<?>) CommonWebViewActivity.class);
        intent.putExtra(CommonWebViewActivity.FORM_URL, str);
        startActivity(intent);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.View
    public void showErrorCode() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.scan_error), LanguageUtil.getLanguageText(R.string.scan_blue_search), LanguageUtil.getLanguageText(R.string.sport_device_add_no), true);
        commBottomConfirmDialogNewInstance.setCancelable(false);
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.scan.ScanCodeActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                ScanCodeActivity.this.mCaptureHelper.restartPreviewAndDecode();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.scan.ScanCodeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                ScanCodeActivity.this.startActivity(new Intent(ScanCodeActivity.this, (Class<?>) ChoiceBlueTypeActivity.class));
                ActivityCompat.finishAfterTransition(ScanCodeActivity.this);
            }
        });
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(ScanContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick({R.id.title_leftBtn})
    public void back(View view) {
        goBack();
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.permissionUtil.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsSuccess(int i) {
        CommonLogUtil.d(TAG, "requestPermissionsSuccess: " + i);
        initCapture();
        this.mCaptureHelper.onResume();
        this.mCaptureHelper.initCamera(this.surfaceHolder);
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsFail(int i) {
        goBack();
    }

    @Override // com.king.zxing.OnCaptureCallback
    public boolean onResultCallback(String str) {
        CommonLogUtil.d(TAG, "onResultCallback: " + str);
        this.mPresenter.doAnalyzeSuccess(str);
        return false;
    }
}