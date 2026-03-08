package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.module.device.view.IMoreView;
import com.ido.life.util.WeatherHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MorePresenter extends BaseCmdPresenter<IMoreView> implements DeviceParaChangedCallBack.ICallBack {
    private BaseDeviceParaCallBack mICallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.MorePresenter.1
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetDoNotDisturbPara(NotDisturbPara notDisturbPara) {
            super.onGetDoNotDisturbPara(notDisturbPara);
            if (!MorePresenter.this.isAttachView() || notDisturbPara == null) {
                return;
            }
            ((IMoreView) MorePresenter.this.getView()).onGetDNDMode(notDisturbPara);
        }

        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetUpHandGesture(UpHandGesture upHandGesture) {
            super.onGetUpHandGesture(upHandGesture);
            if (!MorePresenter.this.isAttachView() || upHandGesture == null) {
                return;
            }
            ((IMoreView) MorePresenter.this.getView()).onGetUpHandGesture(upHandGesture);
        }

        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
            super.onGetScreenBrightness(screenBrightness);
            if (!MorePresenter.this.isAttachView() || screenBrightness == null) {
                return;
            }
            ((IMoreView) MorePresenter.this.getView()).onGetScreenBrightness(screenBrightness);
        }
    };

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerGetDeviceParaCallBack(this.mICallBack);
        BLEManager.registerDeviceParaChangedCallBack(this);
    }

    private List<Integer> getQuickAppItemList() {
        MenuList menuList = LocalDataManager.getMenuList();
        if (menuList != null && menuList.items != null) {
            return menuList.items;
        }
        return new ArrayList();
    }

    public boolean isDndModeSwitchOn() {
        NotDisturbPara notDisturbPara = LocalDataManager.getNotDisturbPara();
        if (getSupportFunctionInfo().ex_main3_get_do_not_disturb) {
            BLEManager.getDoNotDisturbPara();
        }
        return notDisturbPara != null && (notDisturbPara.onOFf == 170 || notDisturbPara.noontimeRestOnOff == 170);
    }

    public boolean isFindPhoneSwitchOn() {
        return LocalDataManager.getFindPhoneSwitch();
    }

    public boolean isMusicControlSwitchOn() {
        return LocalDataManager.getMusicSwitch();
    }

    public boolean isWristScreenSwitchOn() {
        UpHandGesture upHandGesture = LocalDataManager.getUpHandGesture();
        if (getSupportFunctionInfo().ex_table_main9_get_up_hand_gesture) {
            BLEManager.getUpHandGesture();
        }
        return upHandGesture != null && upHandGesture.onOff == 170;
    }

    public boolean isWeatherSwitchOn() {
        return LocalDataManager.getWeatherSwitch();
    }

    public boolean isNightLightSwitchOn() {
        ScreenBrightness screenBrigthnessConfig = LocalDataManager.getScreenBrigthnessConfig();
        if (getSupportFunctionInfo().ex_table_main9_get_screen_brightness) {
            BLEManager.getScreenBrightness();
        }
        return screenBrigthnessConfig != null && screenBrigthnessConfig.autoAdjustNight == 3;
    }

    public void setFindPhoneSwitch(boolean z) {
        if (!z) {
            EventBusHelper.post(500);
        }
        BLEManager.setFindPhoneSwitch(z);
    }

    public void setMusicSwitch(boolean z) {
        BLEManager.setMusicSwitch(z);
    }

    public void setWristScreenSwitch(boolean z) {
        UpHandGesture upHandGesture = LocalDataManager.getUpHandGesture();
        if (upHandGesture == null) {
            upHandGesture = new UpHandGesture();
        }
        upHandGesture.onOff = z ? 170 : 85;
        BLEManager.setUpHandGesture(upHandGesture);
    }

    public void setWeatherSwitch(boolean z) {
        BLEManager.setWeatherSwitch(z);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView()) {
            ((IMoreView) getView()).onSetCmdFailed(settingType);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.MorePresenter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType = new int[SettingCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.WEATHER_SWITCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.MUSIC_SWITCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        int i = AnonymousClass2.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                resetQuickAppList();
            }
        } else if (isWeatherSwitchOn()) {
            WeatherHelper.requestWeatherFromServer();
        }
        if (isAttachView()) {
            ((IMoreView) getView()).onSetCmdSuccess(settingType);
        }
    }

    private void resetQuickAppList() {
        List<Integer> quickAppItemList = getQuickAppItemList();
        if (isMusicControlSwitchOn()) {
            if (!quickAppItemList.contains(6)) {
                quickAppItemList.add(6);
            }
        } else if (quickAppItemList.contains(6)) {
            int i = 0;
            while (true) {
                if (i >= quickAppItemList.size()) {
                    break;
                }
                if (quickAppItemList.get(i).intValue() == 6) {
                    quickAppItemList.remove(i);
                    break;
                }
                i++;
            }
        }
        MenuList menuList = new MenuList();
        menuList.items = quickAppItemList;
        BLEManager.setMenuList(menuList);
    }

    @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
    public void onChanged(DeviceChangedPara deviceChangedPara) {
        if (deviceChangedPara == null || deviceChangedPara.doNotDisturb <= 0 || !getSupportFunctionInfo().ex_main3_get_do_not_disturb) {
            return;
        }
        BLEManager.getDoNotDisturbPara();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceParaCallBack(this.mICallBack);
        BLEManager.unregisterDeviceParaChangedCallBack(this);
    }
}