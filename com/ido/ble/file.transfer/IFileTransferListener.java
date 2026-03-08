package com.ido.ble.file.transfer;

/* JADX INFO: loaded from: classes2.dex */
public interface IFileTransferListener {
    void onFailed(String str);

    void onProgress(int i);

    void onStart();

    void onSuccess();
}