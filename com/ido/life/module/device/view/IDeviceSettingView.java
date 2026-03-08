package com.ido.life.module.device.view;

import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceSettingView extends IBaseView {
    void onDeleteFailed();

    void onDeleteSuccess();

    void onGetDNDMode(NotDisturbPara notDisturbPara);

    void onGetScreenBrightness(ScreenBrightness screenBrightness);

    void onGetUpHandGesture(UpHandGesture upHandGesture);

    void onMusicControlSwitched(boolean z);

    void onResetFailed();

    void onResetSuccess();

    void onRestartFailed();

    void onRestartStart();

    void onRestartSuccess();

    void onSetCmdFailed();

    void onSetCmdFailed(SettingCallBack.SettingType settingType);

    void onSetCmdSuccess(SettingCallBack.SettingType settingType);

    void onSyncing();
}