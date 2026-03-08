package com.realsil.sdk.core.bluetooth.scanner;

import android.content.Context;
import android.os.Build;
import com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner;

/* JADX INFO: loaded from: classes3.dex */
public class LeScannerCompat {
    public BaseLeScanner Va;

    public LeScannerCompat(Context context) {
        this.Va = getLeScanner(context, Build.VERSION.SDK_INT);
    }

    public LeScannerCompat(Context context, int i) {
        this.Va = getLeScanner(context, i);
    }

    public void addBleScannerListener(BaseLeScanner.RetkBleScannerListener retkBleScannerListener) {
        BaseLeScanner baseLeScanner = this.Va;
        if (baseLeScanner != null) {
            baseLeScanner.addBleScannerCallback(retkBleScannerListener);
        }
    }

    public BaseLeScanner getLeScanner(Context context, int i) {
        if (i >= 21) {
            return new LeScannerV21(context);
        }
        if (i >= 18) {
            return new LeScannerV19(context);
        }
        return null;
    }

    public boolean isScanning() {
        return this.Va.isScanning();
    }

    public synchronized void removeBleScannerListener(BaseLeScanner.RetkBleScannerListener retkBleScannerListener) {
        BaseLeScanner baseLeScanner = this.Va;
        if (baseLeScanner != null) {
            baseLeScanner.removeBleScannerCallback(retkBleScannerListener);
        }
    }

    public boolean scanLeDevice(ScannerParams scannerParams, boolean z) {
        return this.Va.scanLeDevice(scannerParams, z);
    }

    public boolean scanLeDevice(boolean z) {
        return scanLeDevice(null, z);
    }
}