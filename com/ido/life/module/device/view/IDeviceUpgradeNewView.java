package com.ido.life.module.device.view;

import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.life.base.IBaseView;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.api.entity.RemoteLanguage;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceUpgradeNewView extends IBaseView {
    void onDetectionFailed();

    void onDetectionFirmwareInfo(boolean z, OtaEntity.OtaInfo otaInfo, boolean z2);

    void onDetectionFlashInfo(boolean z, OtaEntity.OtaInfo otaInfo);

    void onDetectionLanguageInfo(boolean z, RemoteLanguage.LanguageInfo languageInfo);

    void onDetectionSystemInfo(boolean z, OtaEntity.OtaInfo otaInfo);

    void onGetCurrentFirmwareVersion(String str);

    void onGetCurrentFlashVersion(String str);

    void onGetCurrentLanguageVersion(String str);

    void onGetCurrentSystemInfoVersion(FirmwareAndBt3Version firmwareAndBt3Version);

    void onUpgradeFailed(int i);

    void onUpgradeFont();

    void onUpgradePrepare(int i);

    void onUpgradeProgress(int i, int i2);

    void onUpgradeStart(int i);

    void onUpgradeSuccess(int i);
}