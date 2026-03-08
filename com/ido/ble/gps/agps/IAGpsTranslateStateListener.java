package com.ido.ble.gps.agps;

/* JADX INFO: loaded from: classes2.dex */
public interface IAGpsTranslateStateListener {
    void onFailed(String str);

    void onProgress(int i);

    void onStart();

    void onSuccess();
}