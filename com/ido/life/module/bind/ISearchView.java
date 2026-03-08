package com.ido.life.module.bind;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.life.base.IBaseView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
interface ISearchView extends IBaseView {
    void OnNeedOpenBle();

    void onGetDeviceWhiteList();

    void onNeedLocationPermission();

    void onNeedOpenGps();

    void onSearchFailed();

    void onSearchFinished();

    void onSearchStart();

    void onSearchedDFUDevice(BLEDevice bLEDevice);

    void onSearchedDevice(BLEDevice bLEDevice, List<BLEDevice> list);
}