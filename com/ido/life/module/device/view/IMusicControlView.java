package com.ido.life.module.device.view;

import com.ido.ble.callback.SettingCallBack;
import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface IMusicControlView extends IBaseView {
    void onMusicControlSwitched(boolean z);

    void onSetCmdFailed(SettingCallBack.SettingType settingType);

    void onSetCmdSuccess(SettingCallBack.SettingType settingType);
}