package com.ido.life.ble;

import com.ido.ble.callback.DeviceControlAppCallBack;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDeviceControlAppCallBack implements DeviceControlAppCallBack.ICallBack {
    @Override // com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
    public void onAntiLostNotice(boolean z, long j) {
    }

    @Override // com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
    public void onControlEvent(DeviceControlAppCallBack.DeviceControlEventType deviceControlEventType, int i) {
    }

    @Override // com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
    public void onFindPhone(boolean z, long j) {
    }

    @Override // com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
    public void onOneKeySOS(boolean z, long j) {
    }
}