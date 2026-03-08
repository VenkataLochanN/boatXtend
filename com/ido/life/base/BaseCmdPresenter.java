package com.ido.life.base;

import android.text.TextUtils;
import android.text.format.DateFormat;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.IBaseView;
import com.ido.life.bean.AppBLEDevice;
import com.ido.life.ble.BleHelper;
import com.ido.life.module.main.MainPresenter;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.SPHelper;
import com.ido.life.util.TimeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class BaseCmdPresenter<V extends IBaseView> extends BasePresenter<V> {
    protected static final int CMD_TIMEOUT = 10000;
    public static AppBLEDevice appBLEDevice;
    public static String deviceThirdVersion;
    private final SettingCallBack.ICallBack mSettingCallback = new SettingCallBack.ICallBack() { // from class: com.ido.life.base.BaseCmdPresenter.1
        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onSuccess(SettingCallBack.SettingType settingType, Object obj) {
            BaseCmdPresenter.this.onSetCmdSuccess(settingType, obj);
        }

        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onFailed(SettingCallBack.SettingType settingType) {
            BaseCmdPresenter.this.onSetCmdFailed(settingType);
        }
    };
    private final OtherProtocolCallBack.ICallBack mOtherSettingCallback = new OtherProtocolCallBack.ICallBack() { // from class: com.ido.life.base.BaseCmdPresenter.2
        @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
        public void onSuccess(OtherProtocolCallBack.SettingType settingType) {
            BaseCmdPresenter.this.onSetOtherCmdSuccess(settingType);
        }

        @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
        public void onFailed(OtherProtocolCallBack.SettingType settingType) {
            BaseCmdPresenter.this.onSetOtherCmdFailed(settingType);
        }
    };

    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
    }

    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
    }

    protected void onSetOtherCmdFailed(OtherProtocolCallBack.SettingType settingType) {
    }

    protected void onSetOtherCmdSuccess(OtherProtocolCallBack.SettingType settingType) {
    }

    @Override // com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerSettingCallBack(this.mSettingCallback);
        BLEManager.registerOtherProtocolCallBack(this.mOtherSettingCallback);
    }

    public void removeCallBack() {
        BLEManager.unregisterSettingCallBack(this.mSettingCallback);
        BLEManager.unregisterOtherProtocolCallBack(this.mOtherSettingCallback);
    }

    public void addCallBack() {
        BLEManager.unregisterSettingCallBack(this.mSettingCallback);
        BLEManager.unregisterOtherProtocolCallBack(this.mOtherSettingCallback);
        BLEManager.registerSettingCallBack(this.mSettingCallback);
        BLEManager.registerOtherProtocolCallBack(this.mOtherSettingCallback);
    }

    public SupportFunctionInfo getSupportFunctionInfo() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo == null ? new SupportFunctionInfo() : supportFunctionInfo;
    }

    public int getDeviceShape() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            return basicInfo.shape;
        }
        return 2;
    }

    public boolean isBleEnable() {
        return BleHelper.isBluetoothOpen();
    }

    public boolean isBind() {
        return BLEManager.isBind();
    }

    public boolean isConnected() {
        return BLEManager.isConnected();
    }

    public boolean isBindAndConnected() {
        return isBind() && isConnected();
    }

    public void disConnect() {
        if (isConnected()) {
            BLEManager.disConnect();
        }
    }

    public BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "获取设备信息--basicInfo", basicInfo.toString());
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
            currentDeviceInfo.type = basicInfo.dev_type == 1 ? 3 : 4;
            if (currentDeviceInfo.mDeviceId <= 0) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "DeviceId异常，device.mDeviceId=" + currentDeviceInfo.mDeviceId + " ,basicInfo.deivceId=" + basicInfo.deivceId);
                currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            }
        }
        currentDeviceInfo.type = getDeviceType(currentDeviceInfo);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "获取设备信息--device", currentDeviceInfo.toString());
        return currentDeviceInfo;
    }

    public AppBLEDevice getAppBleDevice() {
        if (appBLEDevice == null) {
            appBLEDevice = new AppBLEDevice();
        }
        BLEDevice deviceInfo = getDeviceInfo();
        appBLEDevice.setBLEDevice(deviceInfo);
        if (isSupportDeviceThirdVersion()) {
            if (TextUtils.isEmpty(deviceThirdVersion)) {
                deviceThirdVersion = SPHelper.getDeviceThirdVersion();
                ConnectLogHelper.saveLog("DeviceUpgradePresenter", "从缓存取出三级版本号：" + deviceThirdVersion);
            }
            appBLEDevice.setDeviceThirdVersion(deviceThirdVersion);
        } else {
            appBLEDevice.setDeviceThirdVersion(String.valueOf(deviceInfo.version));
            deviceThirdVersion = String.valueOf(deviceInfo.version);
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "deviceThirdVersion", deviceThirdVersion);
        return appBLEDevice;
    }

    private int getDeviceType(BLEDevice bLEDevice) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo == null) {
            return bLEDevice.type == 2 ? 3 : 4;
        }
        if (basicInfo.dev_type == 1) {
            return 3;
        }
        return basicInfo.shape == 1 ? 5 : 4;
    }

    public boolean isBracelet() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        return basicInfo != null && basicInfo.dev_type == 1;
    }

    public float getDialImageAspectRatio() {
        Float f2;
        BLEDevice deviceInfo = getDeviceInfo();
        if (MainPresenter.screenRatioMap.containsKey(deviceInfo.mDeviceAddress) && (f2 = MainPresenter.screenRatioMap.get(deviceInfo.mDeviceAddress)) != null && f2.floatValue() != 0.0f) {
            return f2.floatValue();
        }
        if (isBindAndConnected()) {
            BLEManager.getWatchPlateScreenInfo();
        }
        return isBracelet() ? 2.0f : 1.128f;
    }

    public boolean isTimeFormat24() {
        return DateFormat.is24HourFormat(IdoApp.getAppContext());
    }

    public String formatTimeByTimeMode(int i, int i2) {
        return TimeUtil.timeFormatter(i, i2, isTimeFormat24(), new String[]{LanguageUtil.getLanguageText(R.string.public_am), LanguageUtil.getLanguageText(R.string.public_pm)});
    }

    @Override // com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterSettingCallBack(this.mSettingCallback);
        BLEManager.unregisterOtherProtocolCallBack(this.mOtherSettingCallback);
    }

    public boolean isSupportDeviceThirdVersion() {
        return getSupportFunctionInfo().V3_v2_02_EB_firmware_bt_version_01_create;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logP(String str) {
        CommonLogUtil.printAndSave(getClass().getSimpleName() + ": " + str);
    }
}