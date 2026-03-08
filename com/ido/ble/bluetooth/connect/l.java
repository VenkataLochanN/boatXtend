package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.logs.LogTool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class l extends com.ido.ble.bluetooth.connect.p.b {
    private static final long j = 30000;
    private static l k;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BluetoothManager f4060b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Handler f4064f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ExecutorService f4066h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4061c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4062d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f4063e = "";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Map<String, BLEDevice> f4065g = new HashMap();
    private Runnable i = new b();

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ byte[] f4067a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ BluetoothDevice f4068b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4069c;

        a(byte[] bArr, BluetoothDevice bluetoothDevice, int i) {
            this.f4067a = bArr;
            this.f4068b = bluetoothDevice;
            this.f4069c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.ido.ble.bluetooth.d.a.a(this.f4067a)) {
                l.this.b(this.f4068b, this.f4069c, this.f4067a);
            } else {
                l.this.c(this.f4068b, this.f4069c, this.f4067a);
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l.this.e();
            l.this.j();
        }
    }

    private l() {
        i();
    }

    private void a(BLEDevice bLEDevice) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] find device:" + bLEDevice.toString());
        ScanCallBack.a(bLEDevice);
    }

    private void a(boolean z, long j2) {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] startScanDevices()");
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] " + com.ido.ble.bluetooth.e.e.a());
        String strB = com.ido.ble.common.l.b();
        if (!TextUtils.isEmpty(strB)) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] printPhoneEnvInfo, " + strB);
        }
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] bluetooth switch is closed, can not scan device.");
            ScanCallBack.a();
            return;
        }
        if (this.f4061c) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] at state of scanning, ignore this action");
            return;
        }
        this.f4066h = Executors.newSingleThreadExecutor();
        this.f4062d = z;
        if (j2 < 0) {
            j2 = 30000;
        }
        Handler handler = this.f4064f;
        if (handler != null) {
            handler.removeCallbacks(this.i);
        }
        h().postDelayed(this.i, j2);
        this.f4061c = true;
        this.f4065g.clear();
        a();
        ScanCallBack.b();
        f();
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.isEmpty(this.f4063e) || str.toLowerCase().contains(this.f4063e.toLowerCase());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        byte[] bArrB = com.ido.ble.bluetooth.d.b.b(bArr);
        if (bArrB == null || bArrB.length < 2) {
            return;
        }
        BLEDevice bLEDevice = new BLEDevice();
        String name = bluetoothDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = com.ido.ble.bluetooth.d.b.a(bArr);
            if (TextUtils.isEmpty(name)) {
                return;
            }
        }
        if (this.f4062d) {
            if (!com.ido.ble.bluetooth.d.b.c(bArr)) {
                return;
            }
        } else if (!a(name)) {
            return;
        }
        String address = bluetoothDevice.getAddress();
        if (this.f4065g.containsKey(address)) {
            return;
        }
        bLEDevice.mDeviceName = name;
        bLEDevice.mDeviceAddress = address;
        bLEDevice.mRssi = i;
        bLEDevice.mDeviceId = (bArrB[0] & UByte.MAX_VALUE) | ((bArrB[1] & UByte.MAX_VALUE) << 8);
        bLEDevice.mIsInDfuMode = true;
        this.f4065g.put(address, bLEDevice);
        a(bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        String name = bluetoothDevice.getName();
        byte[] bArrB = com.ido.ble.bluetooth.d.b.b(bArr);
        int i2 = 0;
        if (bArrB != null && bArrB.length > 2) {
            i2 = (bArrB[0] & UByte.MAX_VALUE) | ((bArrB[1] & UByte.MAX_VALUE) << 8);
        }
        if (TextUtils.isEmpty(name)) {
            name = com.ido.ble.bluetooth.d.b.a(bArr);
            if (TextUtils.isEmpty(name)) {
                return;
            }
        }
        if (this.f4062d) {
            if (!com.ido.ble.bluetooth.d.b.d(bArr)) {
                return;
            }
        } else if (!a(name)) {
            return;
        }
        String address = bluetoothDevice.getAddress();
        if (this.f4065g.containsKey(address)) {
            return;
        }
        BLEDevice bLEDevice = new BLEDevice();
        bLEDevice.mDeviceName = name;
        bLEDevice.mDeviceAddress = address;
        bLEDevice.mRssi = i;
        bLEDevice.mDeviceId = i2;
        if (bArrB != null) {
            if (bArrB.length >= 10) {
                bLEDevice.type = bArrB[9] & UByte.MAX_VALUE;
            }
            bLEDevice.otaFactoryDeviceInfo = new BLEDevice.OTAFactoryDeviceInfo();
            if (bArrB.length >= 14) {
                BLEDevice.OTAFactoryDeviceInfo oTAFactoryDeviceInfo = bLEDevice.otaFactoryDeviceInfo;
                oTAFactoryDeviceInfo.version = bArrB[10] & UByte.MAX_VALUE;
                oTAFactoryDeviceInfo.bootload_version = bArrB[11] & UByte.MAX_VALUE;
                oTAFactoryDeviceInfo.special_version = bArrB[12] & UByte.MAX_VALUE;
                oTAFactoryDeviceInfo.flash_bin_version = bArrB[13] & UByte.MAX_VALUE;
            }
            if (bArrB.length > 17) {
                BLEDevice.OTAFactoryDeviceInfo oTAFactoryDeviceInfo2 = bLEDevice.otaFactoryDeviceInfo;
                oTAFactoryDeviceInfo2.version = (bArrB[9] << 8) | bArrB[8];
                oTAFactoryDeviceInfo2.bootload_version = bArrB[10] | (bArrB[11] << 8);
                oTAFactoryDeviceInfo2.special_version = bArrB[12] | (bArrB[13] << 8);
                oTAFactoryDeviceInfo2.flash_bin_version = bArrB[14] | (bArrB[15] << 8);
                oTAFactoryDeviceInfo2.internal_version = (bArrB[16] & UByte.MAX_VALUE) | (bArrB[17] << 8);
            }
        }
        this.f4065g.put(address, bLEDevice);
        a(bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] to get bond with phone list.");
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (bluetoothDevice != null && bluetoothDevice.getType() == 2) {
                BLEDevice bLEDevice = new BLEDevice();
                bLEDevice.mDeviceAddress = bluetoothDevice.getAddress();
                bLEDevice.mDeviceName = bluetoothDevice.getName();
                bLEDevice.mDeviceId = -111;
                bLEDevice.mRssi = -1;
                bLEDevice.type = -1;
                if (!this.f4065g.containsKey(bLEDevice.mDeviceAddress)) {
                    this.f4065g.put(bLEDevice.mDeviceAddress, bLEDevice);
                    a(bLEDevice);
                }
            }
        }
    }

    private void f() {
        List<BluetoothDevice> connectedDevices = this.f4060b.getConnectedDevices(7);
        if (connectedDevices == null || connectedDevices.size() == 0) {
            return;
        }
        BLEDevice bLEDeviceC = com.ido.ble.f.a.f.b.e().c();
        String str = "";
        for (BluetoothDevice bluetoothDevice : connectedDevices) {
            str = str + bluetoothDevice.getAddress() + "/" + bluetoothDevice.getName();
            if (!this.f4065g.containsKey(bluetoothDevice.getAddress())) {
                if (bLEDeviceC == null || !bLEDeviceC.mDeviceAddress.equals(bluetoothDevice.getAddress())) {
                    BLEDevice bLEDevice = new BLEDevice();
                    if (!TextUtils.isEmpty(bluetoothDevice.getName())) {
                        bLEDevice.mDeviceName = bluetoothDevice.getName();
                        bLEDevice.mDeviceAddress = bluetoothDevice.getAddress();
                        bLEDevice.mRssi = -1;
                        bLEDevice.mDeviceId = -111;
                        this.f4065g.put(bluetoothDevice.getAddress(), bLEDevice);
                        a(bLEDevice);
                    }
                } else {
                    this.f4065g.put(bluetoothDevice.getAddress(), bLEDeviceC);
                    a(bLEDeviceC);
                }
            }
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] gattConnectedList=" + str);
    }

    public static l g() {
        if (k == null) {
            k = new l();
        }
        return k;
    }

    private Handler h() {
        if (this.f4064f == null) {
            this.f4064f = new Handler(Looper.getMainLooper());
        }
        return this.f4064f;
    }

    private void i() {
        this.f4060b = (BluetoothManager) com.ido.ble.common.e.a().getSystemService("bluetooth");
        this.f4064f = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] this task of scan is finished");
        Handler handler = this.f4064f;
        if (handler != null) {
            handler.removeCallbacks(this.i);
        }
        b();
        this.f4061c = false;
        k();
    }

    private void k() {
        ScanCallBack.a();
    }

    public void a(long j2) {
        a(true, j2);
    }

    public void a(long j2, String str) {
        this.f4063e = str;
        a(false, j2);
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        this.f4066h.execute(new a(bArr, bluetoothDevice, i));
    }

    public void d() {
        if (this.f4061c) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] stopScanDevices()");
            j();
            return;
        }
        Handler handler = this.f4064f;
        if (handler != null) {
            handler.removeCallbacks(this.i);
        }
        b();
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanManager] stopScanDevices(), mIsScanning = false");
    }
}