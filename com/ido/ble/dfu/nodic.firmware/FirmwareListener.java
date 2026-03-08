package com.ido.ble.dfu.nodic.firmware;

import com.ido.ble.dfu.nodic.firmware.CheckNewVersionResponse;

/* JADX INFO: loaded from: classes2.dex */
public class FirmwareListener {

    public interface ICheckNewVersionListener {
        void onCheckFailed();

        void onHasNewVersion(CheckNewVersionResponse.NewVersionInfo newVersionInfo);

        void onNoNewVersion();
    }

    public interface IDownloadListener {
        void onFailed();

        void onProgress(int i);

        void onStart();

        void onSuccess();
    }
}