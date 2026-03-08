package com.ido.life.module.device.activity;

import android.view.View;
import androidx.core.widget.NestedScrollView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.WaveLoadingView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.module.device.presenter.DeviceUpgradePresenter;
import com.ido.life.module.device.view.IUpgradeView;
import com.ido.life.module.main.MainActivity;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeActivity extends BaseActivity<DeviceUpgradePresenter> implements IUpgradeView {
    public static final String DEVICE_INFO = "device_info";
    private DeviceUpgradeActivity mActivity;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private int mProgressType;

    @BindView(R.id.rtv_upgrade_attention)
    RegularTextView mRtvAttention;

    @BindView(R.id.rtv_ota_status)
    RegularTextView mRtvOtaStatus;

    @BindView(R.id.rtv_start_upgrade)
    RegularTextView mRtvStartUpgrade;

    @BindView(R.id.rtv_upgrade_instructions)
    RegularTextView mRtvUpgradeInstructions;

    @BindView(R.id.rtv_version)
    RegularTextView mRtvVersion;

    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;

    @BindView(R.id.wave_view)
    WaveLoadingView mWaveView;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_device_upgrade;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mActivity = this;
        refreshUiByStatus(true);
        ((DeviceUpgradePresenter) this.mPresenter).requestFirmwareInfo((BLEDevice) getIntent().getSerializableExtra("device_info"));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceUpgradeActivity$qg5iwi4DeqST9Lqv_DUiLW-h_s8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$DeviceUpgradeActivity(view);
            }
        });
        this.mRtvVersion.setText(String.format(getString(R.string.device_current_version_x_android), String.valueOf(((DeviceUpgradePresenter) this.mPresenter).getDeviceInfo().version)));
        this.mRtvStartUpgrade.setText(getLanguageText(R.string.device_start_upgrade));
        this.mRtvAttention.setText(getLanguageText(R.string.device_ota_attention));
        this.mTitleBar.setTitle(getLanguageText(R.string.firmware_upgrade));
    }

    public /* synthetic */ void lambda$initEvent$0$DeviceUpgradeActivity(View view) {
        onBackPressed();
    }

    @OnClick({R.id.rtv_start_upgrade})
    public void onViewClicked() {
        if (((DeviceUpgradePresenter) this.mPresenter).isApollo()) {
            if (((DeviceUpgradePresenter) this.mPresenter).isConnected()) {
                this.mRtvStartUpgrade.setVisibility(8);
                ((DeviceUpgradePresenter) this.mPresenter).startUpgrade();
                return;
            } else {
                showToast(getLanguageText(R.string.device_pls_connect_device));
                ((DeviceUpgradePresenter) this.mPresenter).autoConnectDevice();
                return;
            }
        }
        this.mRtvStartUpgrade.setVisibility(8);
        ((DeviceUpgradePresenter) this.mPresenter).startUpgrade();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (((DeviceUpgradePresenter) this.mPresenter).isUpgrading()) {
            if (100 == this.mProgressType) {
                showToast(getLanguageText(R.string.device_downloading_not_exit));
                return;
            } else {
                showToast(getLanguageText(R.string.device_upgrading_not_exit));
                return;
            }
        }
        finish();
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onCheckedFirmwareInfo(boolean z, OtaEntity.OtaInfo otaInfo) {
        this.mRtvAttention.setVisibility(z ? 0 : 8);
        this.mRtvStartUpgrade.setEnabled(z);
        this.mRtvVersion.setVisibility(0);
        if (z) {
            refreshUiByStatus(false);
            this.mTitleBar.setTitle(getLanguageText(R.string.firmware_upgrade));
            ((DeviceUpgradePresenter) this.mPresenter).mOtaType = 1;
            this.mRtvOtaStatus.setText(getLanguageText(R.string.me_about_check_version_ios));
            this.mRtvUpgradeInstructions.setText(otaInfo.getDescription());
            this.mRtvVersion.setText(String.format(getLanguageText(R.string.device_upgrade_version_x_android), otaInfo.getVersion()));
            return;
        }
        this.mRtvOtaStatus.setText(getLanguageText(R.string.device_tip_version_newest));
        this.mRtvVersion.setText(String.format(getLanguageText(R.string.device_current_version_x_android), otaInfo.getVersion()));
        ((DeviceUpgradePresenter) this.mPresenter).getFlashInfoFromDevice();
    }

    private void refreshUiByStatus(boolean z) {
        this.mCommLoadingView.setVisibility(z ? 0 : 8);
        this.mScrollView.setVisibility(z ? 8 : 0);
        this.mRtvStartUpgrade.setVisibility(z ? 8 : 0);
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onGetFlashInfo(FlashBinInfo flashBinInfo) {
        if (flashBinInfo.version != flashBinInfo.matchVersion || flashBinInfo.status != 0) {
            ((DeviceUpgradePresenter) this.mPresenter).requestFlashInfo(flashBinInfo.matchVersion);
        } else if (((DeviceUpgradePresenter) this.mPresenter).isRequestFailed()) {
            onCheckedFailed();
        } else {
            onCheckedFlashInfo(false, null);
        }
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onCheckedFlashInfo(boolean z, OtaEntity.OtaInfo otaInfo) {
        this.mRtvAttention.setVisibility(z ? 0 : 8);
        this.mRtvStartUpgrade.setEnabled(z);
        refreshUiByStatus(false);
        if (z) {
            this.mRtvVersion.setVisibility(8);
            this.mTitleBar.setTitle(getLanguageText(R.string.resource_upgrade));
            ((DeviceUpgradePresenter) this.mPresenter).mOtaType = 2;
            this.mRtvOtaStatus.setText(getLanguageText(R.string.me_about_check_version_ios));
            this.mRtvUpgradeInstructions.setText(otaInfo.getDescription());
            this.mRtvVersion.setText(String.format(getLanguageText(R.string.device_upgrade_version_x_android), otaInfo.getVersion()));
        }
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onCheckedFailed() {
        showToast(getLanguageText(R.string.me_request_error));
        this.mRtvOtaStatus.setVisibility(4);
        refreshUiByStatus(false);
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onLowPower() {
        CommBottomConfirmDialog.newInstance(getLanguageText(R.string.sport_low_power_title), getLanguageText(R.string.ota_low_power), getLanguageText(R.string.sync_ok), "", false).show(getSupportFragmentManager());
        this.mRtvStartUpgrade.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onStart(int i) {
        this.mProgressType = i;
        this.mWaveView.setStatus(1, true);
        this.mWaveView.setProgressValue(0);
        if (100 == i) {
            this.mRtvOtaStatus.setText(getLanguageText(R.string.public_downloading));
            this.mWaveView.setProgressValue(1);
        } else {
            this.mRtvOtaStatus.setText(getLanguageText(R.string.device_ota_installing));
        }
        this.mRtvStartUpgrade.setVisibility(8);
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onProgress(int i) {
        this.mWaveView.setProgressValue(i);
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onSuccess(int i) {
        if (200 == i) {
            this.mWaveView.setStatus(2, true);
            this.mRtvOtaStatus.setText(getLanguageText(R.string.device_install_finish));
            this.mRtvAttention.setText(getLanguageText(R.string.ota_install_finish_attention));
            this.mRtvVersion.setText(String.format(getLanguageText(R.string.device_current_version_x_android), Integer.valueOf(((DeviceUpgradePresenter) this.mPresenter).getNewVersion())));
        }
        this.mRtvVersion.postDelayed(new Runnable() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceUpgradeActivity$qbVe3aj7GE6dqVzQNQXLoSzbVyE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSuccess$1$DeviceUpgradeActivity();
            }
        }, 500L);
    }

    public /* synthetic */ void lambda$onSuccess$1$DeviceUpgradeActivity() {
        DeviceUpgradeActivity deviceUpgradeActivity = this.mActivity;
        if (deviceUpgradeActivity != null) {
            MainActivity.startActivity(deviceUpgradeActivity, 301);
            finish();
        }
    }

    @Override // com.ido.life.module.device.view.IUpgradeView
    public void onFailed(int i) {
        if (i == 100) {
            this.mRtvOtaStatus.setText(getLanguageText(R.string.download_failed));
        } else {
            this.mRtvOtaStatus.setText(getLanguageText(R.string.device_install_failed));
        }
        this.mWaveView.setStatus(-1, true);
        this.mRtvStartUpgrade.setVisibility(0);
        this.mRtvStartUpgrade.setText(getLanguageText(R.string.device_retry));
    }
}