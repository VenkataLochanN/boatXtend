package com.realsil.sdk.core.bluetooth.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseLeScanner {
    public BluetoothAdapter B;
    public boolean Oa;
    public List<RetkBleScannerListener> Pa;
    public boolean u;

    public interface RetkBleScannerListener {
        void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);

        void onLeScanStart();

        void onLeScanStop();
    }

    public BaseLeScanner(Context context) {
        this.u = false;
        this.u = RtkCore.DEBUG;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.B = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
        this.Pa = new CopyOnWriteArrayList();
    }

    public synchronized void addBleScannerCallback(RetkBleScannerListener retkBleScannerListener) {
        List<RetkBleScannerListener> list;
        List<RetkBleScannerListener> list2 = this.Pa;
        if (list2 == null) {
            this.Pa = new CopyOnWriteArrayList();
            list = this.Pa;
        } else if (!list2.contains(retkBleScannerListener)) {
            list = this.Pa;
        }
        list.add(retkBleScannerListener);
    }

    public void config(UUID[] uuidArr) {
    }

    public boolean isScanning() {
        return this.Oa;
    }

    public void notifyLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        List<RetkBleScannerListener> list = this.Pa;
        if (list == null || list.size() <= 0) {
            ZLogger.w("no listeners register");
            return;
        }
        for (RetkBleScannerListener retkBleScannerListener : this.Pa) {
            if (retkBleScannerListener != null) {
                retkBleScannerListener.onLeScan(bluetoothDevice, i, bArr);
            }
        }
    }

    public void notifyScanStart() {
        List<RetkBleScannerListener> list = this.Pa;
        if (list != null) {
            for (RetkBleScannerListener retkBleScannerListener : list) {
                if (retkBleScannerListener != null) {
                    retkBleScannerListener.onLeScanStart();
                }
            }
        }
    }

    public void notifyScanStop() {
        List<RetkBleScannerListener> list = this.Pa;
        if (list != null) {
            for (RetkBleScannerListener retkBleScannerListener : list) {
                if (retkBleScannerListener != null) {
                    retkBleScannerListener.onLeScanStop();
                }
            }
        }
    }

    public synchronized void removeBleScannerCallback(RetkBleScannerListener retkBleScannerListener) {
        List<RetkBleScannerListener> list = this.Pa;
        if (list != null && list.contains(retkBleScannerListener)) {
            this.Pa.remove(retkBleScannerListener);
        }
    }

    public boolean scanLeDevice(ScannerParams scannerParams, boolean z) {
        if (!z) {
            return stopScan();
        }
        if (this.B.isEnabled()) {
            return startScan(scannerParams);
        }
        ZLogger.d("BT Adapter is not enable");
        return false;
    }

    public boolean scanLeDevice(boolean z) {
        return scanLeDevice(null, z);
    }

    public boolean startScan(ScannerParams scannerParams) {
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT Adapter is not turned ON");
            return false;
        }
        ZLogger.v("LeScanner--startScan");
        notifyScanStart();
        this.Oa = true;
        return true;
    }

    public boolean stopScan() {
        notifyScanStop();
        this.Oa = false;
        return true;
    }
}