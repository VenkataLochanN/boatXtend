package com.ido.life.module.bind;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface IBindView extends IBaseView {
    void onBindFailed(int i, boolean z);

    void onBindSuccess();

    void onBindTimeOut(int i);

    void onBindWrongDevice(BLEDevice bLEDevice);

    void onConnectFailed(int i);

    void onConnectStart(BLEDevice bLEDevice);

    void onConnectSuccess(boolean z);

    void onGetDeviceInfoSuccess();

    void onInDfuMode(BLEDevice bLEDevice);

    void onNeedAuthCode(int i);

    void onNeedConfirm(int i, String str);

    void onNeedOpenBle();
}