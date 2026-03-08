package com.ido.ble.firmware.log;

/* JADX INFO: loaded from: classes2.dex */
public interface ICollectDeviceRebootLogListener {
    void onFailed();

    void onStart();

    void onSuccess(String str);
}