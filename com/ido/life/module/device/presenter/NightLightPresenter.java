package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.module.device.view.INightLightView;

/* JADX INFO: loaded from: classes2.dex */
public class NightLightPresenter extends BaseCmdPresenter<INightLightView> {
    private final BaseDeviceParaCallBack mICallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.NightLightPresenter.1
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
            super.onGetScreenBrightness(screenBrightness);
            BLEManager.unregisterGetDeviceParaCallBack(NightLightPresenter.this.mICallBack);
            if (NightLightPresenter.this.isAttachView()) {
                if (screenBrightness == null) {
                    screenBrightness = new ScreenBrightness();
                    screenBrightness.autoAdjustNight = 3;
                    screenBrightness.startHour = 19;
                    screenBrightness.startMinute = 0;
                    screenBrightness.endHour = 6;
                    screenBrightness.endMinute = 0;
                }
                ((INightLightView) NightLightPresenter.this.getView()).onGetScreenBrightness(screenBrightness);
            }
        }
    };

    public ScreenBrightness getNightLightState() {
        ScreenBrightness screenBrigthnessConfig = LocalDataManager.getScreenBrigthnessConfig();
        if (screenBrigthnessConfig == null) {
            screenBrigthnessConfig = new ScreenBrightness();
            screenBrigthnessConfig.autoAdjustNight = 3;
            screenBrigthnessConfig.startHour = 19;
            screenBrigthnessConfig.startMinute = 0;
            screenBrigthnessConfig.endHour = 6;
            screenBrigthnessConfig.endMinute = 0;
        }
        if (getSupportFunctionInfo().ex_table_main9_get_screen_brightness && isConnected()) {
            BLEManager.registerGetDeviceParaCallBack(this.mICallBack);
            BLEManager.getScreenBrightness();
        }
        return screenBrigthnessConfig;
    }

    public void sendNightLightMode2Device(ScreenBrightness screenBrightness) {
        BLEManager.setSCreenBrightnessConfig(screenBrightness);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView() && settingType == SettingCallBack.SettingType.SCREEN_BRIGHTNESS) {
            ((INightLightView) getView()).onSetNightLightFailed();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (isAttachView() && settingType == SettingCallBack.SettingType.SCREEN_BRIGHTNESS) {
            ((INightLightView) getView()).onSetNightLightSuccess();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceParaCallBack(this.mICallBack);
    }
}