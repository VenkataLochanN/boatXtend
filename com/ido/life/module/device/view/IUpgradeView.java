package com.ido.life.module.device.view;

import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.life.base.IBaseView;
import com.ido.life.data.api.entity.OtaEntity;

/* JADX INFO: loaded from: classes2.dex */
public interface IUpgradeView extends IBaseView {
    void onCheckedFailed();

    void onCheckedFirmwareInfo(boolean z, OtaEntity.OtaInfo otaInfo);

    void onCheckedFlashInfo(boolean z, OtaEntity.OtaInfo otaInfo);

    void onFailed(int i);

    void onGetFlashInfo(FlashBinInfo flashBinInfo);

    void onLowPower();

    void onProgress(int i);

    void onStart(int i);

    void onSuccess(int i);
}