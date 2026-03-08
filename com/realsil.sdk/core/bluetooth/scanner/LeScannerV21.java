package com.realsil.sdk.core.bluetooth.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.ParcelUuid;
import android.text.TextUtils;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class LeScannerV21 extends BaseLeScanner {
    public BluetoothLeScanner Ra;
    public ScanCallback Sa;

    public LeScannerV21(Context context) {
        super(context);
        this.Sa = new ScanCallback() { // from class: com.realsil.sdk.core.bluetooth.scanner.LeScannerV21.1
            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> list) {
                super.onBatchScanResults(list);
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int i) {
                super.onScanFailed(i);
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int i, ScanResult scanResult) {
                super.onScanResult(i, scanResult);
                if (Build.VERSION.SDK_INT >= 26 && !scanResult.isConnectable()) {
                    if (LeScannerV21.this.u) {
                        ZLogger.v("onScanResult 1 >> " + scanResult.toString());
                        return;
                    }
                    return;
                }
                if (LeScannerV21.this.u) {
                    ZLogger.v("onScanResult 2 >> " + scanResult.toString());
                }
                ScanRecord scanRecord = scanResult.getScanRecord();
                LeScannerV21.this.notifyLeScan(scanResult.getDevice(), scanResult.getRssi(), scanRecord != null ? scanRecord.getBytes() : new byte[0]);
            }
        };
        ZLogger.v("LeScannerV21 init");
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter != null) {
            this.Ra = bluetoothAdapter.getBluetoothLeScanner();
        }
        if (this.Ra == null) {
            ZLogger.w("mBluetoothLeScanner == null");
        }
    }

    @Override // com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner
    public boolean startScan(ScannerParams scannerParams) {
        if (!super.startScan(scannerParams)) {
            return false;
        }
        if (this.Ra == null) {
            ZLogger.d("getBluetoothLeScanner...");
            this.Ra = this.B.getBluetoothLeScanner();
        }
        if (this.Ra == null) {
            ZLogger.w("mBluetoothLeScanner is null");
            stopScan();
            return false;
        }
        ArrayList arrayList = new ArrayList();
        if (scannerParams.getServiceParcelUuids() != null) {
            for (ParcelUuid parcelUuid : scannerParams.getServiceParcelUuids()) {
                arrayList.add(new ScanFilter.Builder().setServiceUuid(parcelUuid).build());
            }
        }
        if (!TextUtils.isEmpty(scannerParams.getNameFilter())) {
            arrayList.add(new ScanFilter.Builder().setDeviceName(scannerParams.getNameFilter()).build());
        }
        if (!TextUtils.isEmpty(scannerParams.getAddressFilter())) {
            arrayList.add(new ScanFilter.Builder().setDeviceAddress(scannerParams.getAddressFilter()).build());
        }
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ZLogger.v(this.u, ((ScanFilter) it.next()).toString());
            }
        } else {
            ZLogger.d("no scanFilter set");
        }
        ScanSettings.Builder scanMode = new ScanSettings.Builder().setScanMode(2);
        if (Build.VERSION.SDK_INT >= 26) {
            scanMode.setPhy(scannerParams.getPhy()).setLegacy(false);
        }
        try {
            this.Ra.startScan(arrayList, scanMode.build(), this.Sa);
            return true;
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
            return false;
        }
    }

    @Override // com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner
    public boolean stopScan() {
        BluetoothLeScanner bluetoothLeScanner;
        super.stopScan();
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled() || (bluetoothLeScanner = this.Ra) == null) {
            return true;
        }
        bluetoothLeScanner.stopScan(this.Sa);
        return true;
    }
}