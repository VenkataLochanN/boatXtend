package com.realsil.sdk.dfu.core;

import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.Throughput;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DfuThreadCallback {
    public void onDeviceInfoChanged(OtaDeviceInfo otaDeviceInfo) {
    }

    public void onError(int i) {
    }

    public void onProgressChanged(DfuProgressInfo dfuProgressInfo, Throughput throughput) {
    }

    public void onStateChanged(int i, Throughput throughput) {
    }

    public void onUsbProgressChanged(DfuProgressInfo dfuProgressInfo) {
    }
}