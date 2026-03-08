package com.ido.life.module.device.view;

import com.ido.ble.callback.SettingCallBack;
import com.ido.life.base.IBaseView;
import com.ido.life.data.api.entity.TopDialPlateEntity;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IFunctionListView extends IBaseView {
    void onRequestDialSuccess(List<TopDialPlateEntity.DialPlate> list);

    void onRestartFailed();

    void onRestartSuccess();

    void onSetCmdFailed(SettingCallBack.SettingType settingType);

    void onSetCmdSuccess(SettingCallBack.SettingType settingType);

    void onUnbindFailed();

    void onUnbindSuccess();
}