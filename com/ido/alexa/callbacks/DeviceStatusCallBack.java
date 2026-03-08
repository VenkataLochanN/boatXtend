package com.ido.alexa.callbacks;

/* JADX INFO: loaded from: classes2.dex */
public interface DeviceStatusCallBack {
    void onConnectBreak();

    void onConnectFailed();

    void onConnectSuccess(String str);

    void onHornVoiceChanged(int i);

    void onSetDevicefirmwareVersion(long j);

    void reboot();
}