package com.ido.life.module.device.view;

import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface IMoreView extends IBaseView {
    void onGetDNDMode(NotDisturbPara notDisturbPara);

    void onGetScreenBrightness(ScreenBrightness screenBrightness);

    void onGetUpHandGesture(UpHandGesture upHandGesture);

    void onSetCmdFailed(SettingCallBack.SettingType settingType);

    void onSetCmdSuccess(SettingCallBack.SettingType settingType);
}