package com.realsil.sdk.core.bluetooth.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;

/* JADX INFO: loaded from: classes3.dex */
public class LeScannerV19 extends BaseLeScanner {
    public BluetoothAdapter.LeScanCallback Qa;

    public LeScannerV19(Context context) {
        super(context);
        this.Qa = new BluetoothAdapter.LeScanCallback() { // from class: com.realsil.sdk.core.bluetooth.scanner.LeScannerV19.1
            @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                LeScannerV19.this.notifyLeScan(bluetoothDevice, i, bArr);
            }
        };
        ZLogger.v("LeScannerV19 init");
    }

    @Override // com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner
    public boolean startScan(ScannerParams scannerParams) {
        if (super.startScan(scannerParams)) {
            return this.B.startLeScan(scannerParams.getServiceUuids(), this.Qa);
        }
        return false;
    }

    @Override // com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner
    public boolean stopScan() {
        super.stopScan();
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return true;
        }
        ZLogger.w("BT Adapter is not turned ON");
        this.B.stopLeScan(this.Qa);
        return true;
    }
}