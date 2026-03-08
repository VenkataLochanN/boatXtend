package com.ido.ble.icon.transfer;

/* JADX INFO: loaded from: classes2.dex */
public interface IIconTransferListener {
    void onBusy(IconTranConfig iconTranConfig);

    void onFailed(IconTranConfig iconTranConfig);

    String onHandlePicFile(IconTranConfig iconTranConfig, int i, int i2);

    void onProgress(IconTranConfig iconTranConfig, int i);

    void onStart(IconTranConfig iconTranConfig);

    void onSuccess(IconTranConfig iconTranConfig);
}