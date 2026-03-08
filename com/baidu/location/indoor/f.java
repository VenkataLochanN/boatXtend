package com.baidu.location.indoor;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;

/* JADX INFO: loaded from: classes.dex */
class f extends ScanCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f2528a;

    f(d dVar) {
        this.f2528a = dVar;
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, ScanResult scanResult) {
        if (this.f2528a.l != null) {
            this.f2528a.l.put(scanResult.getDevice().getAddress(), scanResult);
        }
    }
}