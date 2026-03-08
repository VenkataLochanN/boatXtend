package com.realsil.sdk.core.bluetooth.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelUuid;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class ScannerPresenter {
    public static final int STATE_DISCOVERY_FINISHED = 2;
    public static final int STATE_DISCOVERY_STARTED = 1;
    public static final int STATE_IDLE = 0;
    public BluetoothAdapter B;
    public LeScannerCompat ib;
    public ScannerCallback jb;
    public ScannerParams kb;
    public BaseLeScanner.RetkBleScannerListener lb;
    public Context mContext;
    public Handler mHandler;
    public final BroadcastReceiver mReceiver;
    public int mState;
    public Runnable mb;
    public Runnable nb;
    public boolean u;

    public ScannerPresenter(Context context) {
        this(context, null, null);
    }

    public ScannerPresenter(Context context, Handler handler, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        String str;
        BluetoothAdapter defaultAdapter;
        this.u = false;
        this.mState = 0;
        this.mHandler = new Handler();
        this.lb = new BaseLeScanner.RetkBleScannerListener() { // from class: com.realsil.sdk.core.bluetooth.scanner.ScannerPresenter.1
            @Override // com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner.RetkBleScannerListener
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                ScannerPresenter.this.a(bluetoothDevice, i, bArr);
            }

            @Override // com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner.RetkBleScannerListener
            public void onLeScanStart() {
            }

            @Override // com.realsil.sdk.core.bluetooth.scanner.BaseLeScanner.RetkBleScannerListener
            public void onLeScanStop() {
                ZLogger.v("onLeScanStop");
                ScannerPresenter.this.d(2);
            }
        };
        this.mb = new Runnable() { // from class: com.realsil.sdk.core.bluetooth.scanner.ScannerPresenter.2
            @Override // java.lang.Runnable
            public void run() {
                ZLogger.v("le delay time reached");
                ScannerPresenter.this.scanDevice(false);
            }
        };
        this.mReceiver = new BroadcastReceiver() { // from class: com.realsil.sdk.core.bluetooth.scanner.ScannerPresenter.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.bluetooth.device.action.FOUND".equals(action) || "android.bluetooth.device.action.CLASS_CHANGED".equals(action) || "android.bluetooth.device.action.NAME_CHANGED".equals(action)) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", (short) 0);
                    ZLogger.d(bluetoothDevice != null ? String.format("%s %s/%s", action, bluetoothDevice.getName(), bluetoothDevice.toString()) : String.format("%s", action));
                    ScannerPresenter.this.a(bluetoothDevice, shortExtra, null);
                    return;
                }
                if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(action)) {
                    ScannerPresenter.this.d(1);
                    return;
                }
                if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                    ScannerPresenter.this.d(2);
                    return;
                }
                if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                    ZLogger.d(String.format(Locale.US, "[%s] %d -> %d", action, Integer.valueOf(intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1)), Integer.valueOf(intExtra)));
                    if (intExtra == 10 && ScannerPresenter.this.isScanning()) {
                        new Thread(new Runnable() { // from class: com.realsil.sdk.core.bluetooth.scanner.ScannerPresenter.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ScannerPresenter.this.scanDevice(false);
                            }
                        }).start();
                    }
                }
            }
        };
        this.nb = new Runnable() { // from class: com.realsil.sdk.core.bluetooth.scanner.ScannerPresenter.4
            @Override // java.lang.Runnable
            public void run() {
                if (ScannerPresenter.this.jb != null) {
                    ScannerPresenter.this.jb.onAutoScanTrigger();
                } else {
                    ZLogger.w("no callback registed");
                }
                ScannerPresenter.this.scanDevice(true);
            }
        };
        this.u = RtkCore.DEBUG;
        this.mContext = context.getApplicationContext();
        this.mHandler = handler;
        if (this.mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("ScannerPresenter");
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper());
        }
        this.kb = scannerParams;
        this.jb = scannerCallback;
        if (this.jb == null) {
            ZLogger.w("callback is null");
        }
        if (this.kb == null) {
            ZLogger.d("create new ScannerParams");
            this.kb = new ScannerParams();
        }
        if (this.kb.getServiceParcelUuids() != null) {
            str = "scannerParams.getServiceParcelUuids().length=" + this.kb.getServiceParcelUuids().length;
        } else {
            str = "scannerParams.getServiceParcelUuids() == null";
        }
        ZLogger.d(str);
        if (Build.VERSION.SDK_INT >= 18) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
            defaultAdapter = bluetoothManager != null ? bluetoothManager.getAdapter() : defaultAdapter;
            this.ib = new LeScannerCompat(this.mContext);
        }
        defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.B = defaultAdapter;
        this.ib = new LeScannerCompat(this.mContext);
    }

    public ScannerPresenter(Context context, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        this(context, null, scannerParams, scannerCallback);
    }

    public final void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (this.mState != 1) {
            ZLogger.w("ignore, mState =" + this.mState);
            return;
        }
        if (bluetoothDevice == null) {
            ZLogger.w("ignore, device is null");
            return;
        }
        if (a(bluetoothDevice, i)) {
            ExtendedBluetoothDevice extendedBluetoothDevice = new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), i, bluetoothDevice.getBondState() == 12, false, bArr);
            if (this.kb.getServiceParcelUuids() != null && this.kb.getServiceParcelUuids().length > 0) {
                SpecScanRecord specScanRecord = extendedBluetoothDevice.specScanRecord;
                List<ParcelUuid> serviceUuids = specScanRecord != null ? specScanRecord.getServiceUuids() : null;
                if (serviceUuids == null || serviceUuids.size() <= 0) {
                    ZLogger.v(this.u, "no serviceUuids to filter");
                    return;
                }
                for (ParcelUuid parcelUuid : this.kb.getServiceParcelUuids()) {
                    ZLogger.v(parcelUuid.toString());
                    if (!serviceUuids.contains(parcelUuid)) {
                        ZLogger.w("filter, not found uuid:" + parcelUuid.toString());
                        return;
                    }
                }
            }
            ScannerCallback scannerCallback = this.jb;
            if (scannerCallback != null) {
                scannerCallback.onNewDevice(extendedBluetoothDevice);
            } else {
                ZLogger.w("no callback registed");
            }
            if (this.kb.getScanMechanism() == 1) {
                ZLogger.d("SCAN_MECHANISM_FILTER_ONE > scanDevice(false)");
                scanDevice(false);
            }
        }
    }

    public final boolean a(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            ZLogger.w("filter, device is null");
            return false;
        }
        if (this.kb.getRssiFilter() > -1000 && this.kb.getRssiFilter() > i) {
            ZLogger.w("filter, low rssi:" + i);
            return false;
        }
        if (this.kb.getScanMode() == 18) {
            if (Build.VERSION.SDK_INT >= 18 && bluetoothDevice.getType() != 2) {
                ZLogger.w("filter, invalid type:" + bluetoothDevice.getType());
                return false;
            }
        } else if (this.kb.getScanMode() == 17) {
            if (Build.VERSION.SDK_INT >= 18 && bluetoothDevice.getType() != 2 && bluetoothDevice.getType() != 3) {
                ZLogger.w("filter, invalid type:" + bluetoothDevice.getType());
                return false;
            }
        } else if (this.kb.getScanMode() == 33) {
            if (Build.VERSION.SDK_INT >= 18 && bluetoothDevice.getType() != 1) {
                ZLogger.w("filter, invalid type:" + bluetoothDevice.getType());
                return false;
            }
        } else if (this.kb.getScanMode() == 32 && Build.VERSION.SDK_INT >= 18 && bluetoothDevice.getType() != 1 && bluetoothDevice.getType() != 3) {
            ZLogger.w("filter, invalid type:" + bluetoothDevice.getType());
            return false;
        }
        if (TextUtils.isEmpty(bluetoothDevice.getName())) {
            if (!this.kb.isNameNullable()) {
                if (this.u) {
                    ZLogger.v("name is null, ignore");
                }
                return false;
            }
        } else if (!TextUtils.isEmpty(this.kb.getNameFilter())) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (!Objects.equals(this.kb.getNameFilter(), bluetoothDevice.getName())) {
                    ZLogger.v("name not match:" + bluetoothDevice.getName());
                    return false;
                }
            } else if (!ObjectsCompat.equals(this.kb.getNameFilter(), bluetoothDevice.getName())) {
                ZLogger.v("name not match:" + bluetoothDevice.getName());
                return false;
            }
        }
        if (!TextUtils.isEmpty(this.kb.getAddressFilter())) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (!Objects.equals(this.kb.getAddressFilter(), bluetoothDevice.getAddress())) {
                    ZLogger.v("address not match:" + bluetoothDevice.getAddress());
                    return false;
                }
            } else if (!ObjectsCompat.equals(this.kb.getAddressFilter(), bluetoothDevice.getAddress())) {
                ZLogger.v("address not match:" + bluetoothDevice.getAddress());
                return false;
            }
        }
        return true;
    }

    public final boolean b(BluetoothDevice bluetoothDevice) {
        StringBuilder sb;
        String name;
        if (bluetoothDevice == null) {
            return false;
        }
        if (this.kb.getScanMode() == 18) {
            if (Build.VERSION.SDK_INT >= 18 && bluetoothDevice.getType() != 2) {
                return false;
            }
        } else if (this.kb.getScanMode() == 33 && Build.VERSION.SDK_INT >= 18 && bluetoothDevice.getType() != 1) {
            return false;
        }
        if (!TextUtils.isEmpty(bluetoothDevice.getName())) {
            if (!TextUtils.isEmpty(this.kb.getNameFilter())) {
                if (Build.VERSION.SDK_INT >= 19) {
                    if (!Objects.equals(this.kb.getNameFilter(), bluetoothDevice.getName())) {
                        sb = new StringBuilder();
                        sb.append("name not match:");
                        name = bluetoothDevice.getName();
                    }
                } else if (!ObjectsCompat.equals(this.kb.getNameFilter(), bluetoothDevice.getName())) {
                    sb = new StringBuilder();
                    sb.append("name not match:");
                    name = bluetoothDevice.getName();
                }
            }
            sb.append(name);
            ZLogger.v(sb.toString());
            return false;
        }
        if (!this.kb.isNameNullable()) {
            if (this.u) {
                ZLogger.v("name is null, ignore");
            }
            return false;
        }
        if (!TextUtils.isEmpty(this.kb.getAddressFilter())) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (!Objects.equals(this.kb.getAddressFilter(), bluetoothDevice.getAddress())) {
                    sb = new StringBuilder();
                    sb.append("address not match:");
                    name = bluetoothDevice.getAddress();
                }
            } else if (!ObjectsCompat.equals(this.kb.getAddressFilter(), bluetoothDevice.getAddress())) {
                sb = new StringBuilder();
                sb.append("address not match:");
                name = bluetoothDevice.getAddress();
            }
            sb.append(name);
            ZLogger.v(sb.toString());
            return false;
        }
        return true;
    }

    public final void d(int i) {
        ZLogger.v(String.format(Locale.US, "ScanState 0x%02X >> 0x%02X", Integer.valueOf(this.mState), Integer.valueOf(i)));
        this.mState = i;
        ScannerCallback scannerCallback = this.jb;
        if (scannerCallback != null) {
            scannerCallback.onScanStateChanged(i);
        } else {
            ZLogger.w("no callback registed");
        }
        if (this.mState == 1 || !isAutoDiscovery()) {
            return;
        }
        this.mHandler.postDelayed(this.nb, this.kb.getAutoScanDelay());
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return this.B;
    }

    public List<ExtendedBluetoothDevice> getPairedDevices() {
        if (this.B == null) {
            return null;
        }
        if (!this.kb.isReusePaiedDeviceEnabled()) {
            ZLogger.w("don't reuse paired device");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (BluetoothDevice bluetoothDevice : this.B.getBondedDevices()) {
            if (b(bluetoothDevice)) {
                arrayList.add(new ExtendedBluetoothDevice(bluetoothDevice, bluetoothDevice.getName(), -1000, bluetoothDevice.getBondState() == 12, false));
            }
        }
        return arrayList;
    }

    public int getState() {
        return this.mState;
    }

    public void init() {
        BluetoothAdapter defaultAdapter;
        if (this.B == null) {
            if (Build.VERSION.SDK_INT >= 18) {
                BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
                if (bluetoothManager != null) {
                    defaultAdapter = bluetoothManager.getAdapter();
                }
            } else {
                defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            }
            this.B = defaultAdapter;
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        if (this.kb.getScanMode() == 0 || this.kb.getScanMode() == 32 || this.kb.getScanMode() == 33) {
            intentFilter.addAction("android.bluetooth.device.action.FOUND");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
            intentFilter.addAction("android.bluetooth.device.action.NAME_CHANGED");
        }
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
        if (this.jb == null) {
            ZLogger.w("callback is null");
        }
    }

    public boolean isAutoDiscovery() {
        return this.kb.isAutoDiscovery();
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.B;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public boolean isBluetoothSupported() {
        return this.B != null;
    }

    public boolean isScanning() {
        return this.mState == 1;
    }

    public void onDestroy() {
        Context context = this.mContext;
        if (context != null) {
            try {
                context.unregisterReceiver(this.mReceiver);
            } catch (Exception e2) {
                ZLogger.e(e2.toString());
            }
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mb);
            this.mHandler.removeCallbacks(this.nb);
        }
        scanDevice(false);
    }

    public synchronized void scanDevice(boolean z) {
        if (z) {
            if (this.mState == 1) {
                ZLogger.d("mState == STATE_DISCOVERY_STARTED");
                return;
            }
            if (this.kb.getScanMode() == 0 || this.kb.getScanMode() == 32 || this.kb.getScanMode() == 33) {
                if (this.B.isDiscovering()) {
                    this.B.cancelDiscovery();
                }
                ZLogger.v("startDiscovery ...");
                if (!this.B.startDiscovery()) {
                    return;
                }
            }
            if (this.kb.getScanMode() == 0 || this.kb.getScanMode() == 17 || this.kb.getScanMode() == 18) {
                if (this.ib == null) {
                    ZLogger.w("mRetkLeScannerCompat is null");
                    d(0);
                    return;
                }
                ZLogger.v("start the le scan, on time is " + this.kb.getScanPeriod() + "ms");
                this.ib.addBleScannerListener(this.lb);
                if (!this.ib.scanLeDevice(this.kb, true)) {
                    return;
                }
                if (this.kb.isAutoDiscovery()) {
                    this.mHandler.removeCallbacks(this.mb);
                } else {
                    this.mHandler.postDelayed(this.mb, this.kb.getScanPeriod());
                }
            }
            d(1);
        } else {
            if (this.mState != 1) {
                ZLogger.d("mState == STATE_DISCOVERY_FINISHED | STATE_IDLE");
                return;
            }
            if ((this.kb.getScanMode() == 0 || this.kb.getScanMode() == 32 || this.kb.getScanMode() == 33) && this.B.isDiscovering()) {
                ZLogger.v("cancelDiscovery");
                this.B.cancelDiscovery();
            }
            if (this.kb.getScanMode() == 0 || this.kb.getScanMode() == 17 || this.kb.getScanMode() == 18) {
                this.mHandler.removeCallbacks(this.mb);
                if (this.ib != null) {
                    ZLogger.v("stop the le scan");
                    this.ib.removeBleScannerListener(this.lb);
                    if (!this.ib.scanLeDevice(false)) {
                        return;
                    }
                } else {
                    ZLogger.w("mRetkLeScannerCompat is null");
                }
            }
            d(0);
        }
    }

    public void setScanMode(int i) {
        this.kb.setScanMode(i);
    }

    public void setScannerCallback(ScannerCallback scannerCallback) {
        this.jb = scannerCallback;
        if (this.jb == null) {
            ZLogger.w("callback is null");
        }
    }

    public void setScannerParams(ScannerParams scannerParams) {
        this.kb = scannerParams;
    }
}