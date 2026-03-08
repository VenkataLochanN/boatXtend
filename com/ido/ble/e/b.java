package com.ido.ble.e;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.connect.g;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.bluetooth.e.d;
import com.ido.ble.bluetooth.e.e;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    private static final String k = "b";
    private static b l = null;
    private static final int m = 3;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4375b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BLEDevice f4376c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4377d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f4378e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f4379f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4374a = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4380g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4381h = false;
    private ConnectCallBack.ICallBack i = new a();
    private ScanCallBack.ICallBack j = new C0077b();

    class a implements ConnectCallBack.ICallBack {
        a() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            if (b.this.f4381h) {
                b.this.b();
            }
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            b.this.d();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            b.this.f();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            b.this.e();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.e.b$b, reason: collision with other inner class name */
    class C0077b implements ScanCallBack.ICallBack {
        C0077b() {
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onFindDevice(BLEDevice bLEDevice) {
            if (TextUtils.isEmpty(b.this.f4375b)) {
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + b.k, "targetMacAddress null");
                BLEDevice bLEDeviceC = com.ido.ble.f.a.f.b.e().c();
                if (bLEDeviceC == null || TextUtils.isEmpty(bLEDeviceC.mDeviceAddress)) {
                    LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + b.k, "get targetMacAddress is null");
                    return;
                }
                b.this.f4375b = bLEDeviceC.mDeviceAddress;
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + b.k, "targetMacAddress :" + b.this.f4375b);
            }
            if (bLEDevice != null && !TextUtils.isEmpty(bLEDevice.mDeviceAddress) && bLEDevice.mDeviceAddress.endsWith(b.this.f4375b)) {
                b.this.f4376c = bLEDevice;
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + b.k, "find target device, mac =" + bLEDevice.mDeviceAddress);
            } else {
                if (!d.a(b.this.f4377d, b.this.f4378e, bLEDevice)) {
                    return;
                }
                b.this.f4376c = bLEDevice;
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + b.k, "find target device(mac +1)");
            }
            b.this.j();
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onScanFinished() {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + b.k, "scan finished.");
            b.this.g();
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onStart() {
        }
    }

    class c implements g.c {
        c() {
        }

        @Override // com.ido.ble.bluetooth.connect.g.c
        public void a() {
            b.this.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "failed");
        e();
    }

    private void b(String str) {
        BluetoothDevice bluetoothDeviceA = BluetoothAdapter.checkBluetoothAddress(str) ? e.a(str) : e.b(str);
        if (bluetoothDeviceA == null) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "not paired!");
            return;
        }
        boolean zA = e.a(bluetoothDeviceA);
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "has paired, isConnectedByPhone=" + zA);
        if (!CustomConfig.getConfig().isNeedRemoveBondBeforeConnect() || e.b()) {
            return;
        }
        boolean zB = e.b(bluetoothDeviceA);
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "remove bond status is " + zB);
    }

    public static b c() {
        if (l == null) {
            l = new b();
        }
        return l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f4380g++;
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "reconnect times is " + this.f4380g);
        if (this.f4380g <= 3) {
            h();
            return;
        }
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "out of max retry times.");
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "release");
        this.f4374a = false;
        this.f4381h = false;
        this.f4375b = "";
        this.f4376c = null;
        this.f4380g = 0;
        com.ido.ble.callback.b.K().b(this.j);
        com.ido.ble.callback.b.K().b(this.i);
        l = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "success");
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "toConnect");
        this.f4381h = true;
        com.ido.ble.callback.b.K().b(this.i);
        com.ido.ble.callback.b.K().a(this.i);
        if (this.f4376c == null) {
            this.f4376c = new BLEDevice();
            this.f4376c.mDeviceAddress = this.f4375b;
        }
        com.ido.ble.bluetooth.a.b(this.f4376c);
    }

    private void h() {
        new g().a(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "toStartScan");
        com.ido.ble.bluetooth.a.n();
        com.ido.ble.callback.b.K().b(this.j);
        com.ido.ble.callback.b.K().a(this.j);
        com.ido.ble.bluetooth.a.m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "toStopScan");
        com.ido.ble.bluetooth.a.n();
    }

    public void a(String str) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "[connect] macAddress = " + str);
        if (this.f4374a) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "is doing...");
            return;
        }
        this.f4374a = true;
        this.f4381h = false;
        this.f4375b = str;
        this.f4377d = d.a(str);
        this.f4378e = d.b(str);
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "targetMacAddressAdd1:" + this.f4377d);
        LogTool.b(com.ido.ble.bluetooth.e.b.f4128a + "_" + k, "targetMacAddressAdd2:" + this.f4378e);
        b(str);
        h();
    }
}