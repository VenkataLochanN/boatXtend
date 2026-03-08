package com.ido.life.module.device.activity;

import android.widget.ImageView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.AppBLEDevice;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.module.device.presenter.DeviceAboutPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceAboutActivity extends BaseActivity<DeviceAboutPresenter> {
    private DeviceListEntity.DeviceInfo mDeviceInfo;

    @BindView(R.id.iv_device_img)
    ImageView mIvDeviceImg;

    @BindView(R.id.mtv_ble_name_label)
    MediumTextView mMtvBleNameLabel;

    @BindView(R.id.mtv_data_sync_time_label)
    MediumTextView mMtvDataSyncTimeLabel;

    @BindView(R.id.mtv_mac_label)
    MediumTextView mMtvMacLabel;

    @BindView(R.id.mtv_model_label)
    MediumTextView mMtvModelLabel;

    @BindView(R.id.mtv_tag_line)
    MediumTextView mMtvTagLine;

    @BindView(R.id.mtv_version_label)
    MediumTextView mMtvVersionLabel;

    @BindView(R.id.rtv_ble_name)
    RegularTextView mRtvBleName;

    @BindView(R.id.rtv_data_sync_time)
    RegularTextView mRtvDataSyncTime;

    @BindView(R.id.rtv_mac)
    RegularTextView mRtvMac;

    @BindView(R.id.rtv_model)
    RegularTextView mRtvModel;

    @BindView(R.id.rtv_version)
    RegularTextView mRtvVersion;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_device_about;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mDeviceInfo = (DeviceListEntity.DeviceInfo) getIntent().getSerializableExtra("device");
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        AppBLEDevice appBleDevice = ((DeviceAboutPresenter) this.mPresenter).getAppBleDevice();
        this.mRtvModel.setText(appBleDevice.mDeviceName);
        this.mRtvVersion.setText("V " + appBleDevice.getDeviceThirdVersion());
        this.mRtvBleName.setText(appBleDevice.mDeviceName);
        this.mRtvMac.setText(appBleDevice.mDeviceAddress);
        this.mRtvDataSyncTime.setText(((DeviceAboutPresenter) this.mPresenter).getDeviceSyncTime());
        loadDeviceImage(this.mDeviceInfo.getImageUrl());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_about));
        this.mMtvTagLine.setText(getLanguageText(R.string.device_tag_line));
        this.mMtvModelLabel.setText(getLanguageText(R.string.device_model));
        this.mMtvVersionLabel.setText(getLanguageText(R.string.device_version));
        this.mMtvBleNameLabel.setText(getLanguageText(R.string.device_ble_name));
        this.mMtvMacLabel.setText(getLanguageText(R.string.device_device_mac));
        this.mMtvDataSyncTimeLabel.setText(getLanguageText(R.string.device_data_sync_time));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        if (baseMessage.getType() != 846) {
            return;
        }
        if (((Boolean) baseMessage.getData()).booleanValue()) {
            loadDeviceImage(((DeviceAboutPresenter) this.mPresenter).getDeviceImageUrl(this.mDeviceInfo.getDeviceId()));
        } else {
            ((DeviceAboutPresenter) this.mPresenter).queryDeviceInfo();
        }
    }

    private void loadDeviceImage(String str) {
        ImageLoaderUtil.loadImgFillet(this.mIvDeviceImg, str, 0, this.mDeviceInfo.type == 3 ? R.mipmap.icon_type_bracelet : R.mipmap.icon_type_watch);
    }
}