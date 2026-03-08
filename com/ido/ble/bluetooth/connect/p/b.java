package com.ido.ble.bluetooth.connect.p;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import com.ido.ble.logs.LogTool;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends com.ido.ble.bluetooth.connect.p.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ScanCallback f4094a = new a();

    class a extends ScanCallback {
        a() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            if (i != 1) {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[NewScanner]LE Scan has already started");
                return;
            }
            ScanRecord scanRecord = scanResult.getScanRecord();
            if (scanRecord == null) {
                return;
            }
            b.this.a(scanResult.getDevice(), scanResult.getRssi(), scanRecord.getBytes());
        }
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void a() {
        BluetoothLeScanner bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[NewScanner]startLeScan: cannot get BluetoothLeScanner");
            return;
        }
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[NewScanner]startLeScan: bluetooth switch closed");
            return;
        }
        ScanSettings scanSettingsBuild = new ScanSettings.Builder().setCallbackType(1).setScanMode(2).build();
        ArrayList arrayList = new ArrayList();
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[NewScanner]  NORMAL scan");
        bluetoothLeScanner.startScan(arrayList, scanSettingsBuild, this.f4094a);
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void b() {
        BluetoothLeScanner bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[NewScanner]stopLeScan: cannot get BluetoothLeScanner");
        } else if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            bluetoothLeScanner.stopScan(this.f4094a);
        } else {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[NewScanner]stopLeScan: bluetooth switch closed");
        }
    }

    public ScanSettings c() {
        ScanSettings.Builder scanMode = new ScanSettings.Builder().setScanMode(2);
        if (Build.VERSION.SDK_INT >= 23) {
            scanMode.setCallbackType(1);
            scanMode.setMatchMode(1);
        }
        if (BluetoothAdapter.getDefaultAdapter().isOffloadedScanBatchingSupported()) {
            scanMode.setReportDelay(0L);
        }
        return scanMode.build();
    }
}