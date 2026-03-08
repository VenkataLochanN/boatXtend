package com.ido.ble.bluetooth.bt;

/* JADX INFO: loaded from: classes2.dex */
public interface ISPPConnectStateListener {
    void onBreak();

    void onFailed();

    void onStart();

    void onSuccess();
}