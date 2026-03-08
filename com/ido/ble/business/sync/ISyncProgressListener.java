package com.ido.ble.business.sync;

/* JADX INFO: loaded from: classes2.dex */
public interface ISyncProgressListener {
    void onFailed();

    void onProgress(int i);

    void onStart();

    void onSuccess();
}