package com.ido.ble.dfu.e.b;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.common.e;
import com.ido.ble.logs.LogTool;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static final long l = 30000;
    private static boolean m = false;
    private static final int n = 3;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BluetoothAdapter f4328a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f4329b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private d f4330c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4331d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f4335h;
    private String i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4332e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4333f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4334g = 0;
    private BluetoothAdapter.LeScanCallback j = new a();
    private Runnable k = new b();

    class a implements BluetoothAdapter.LeScanCallback {
        a() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (c.this.f4331d.equals(bluetoothDevice.getAddress()) || c.this.f4335h.equals(bluetoothDevice.getAddress()) || c.this.i.equals(bluetoothDevice.getAddress())) {
                LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] -------onLeScan :" + bluetoothDevice.getAddress());
                if (!com.ido.ble.bluetooth.d.a.a(bArr)) {
                    BLEDevice bLEDeviceA = c.this.a(bluetoothDevice, i, bArr);
                    if (bLEDeviceA == null) {
                        LogTool.b(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] has find target device, but device para is null");
                        return;
                    }
                    c.this.f4332e = true;
                    LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] has find target device, is not in dfu mode");
                    c.this.c();
                    c.this.f4330c.c(bLEDeviceA);
                    return;
                }
                c.this.f4332e = true;
                LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] has find target device, is in dfu mode:" + bluetoothDevice.getAddress());
                c.this.c();
                BLEDevice bLEDevice = new BLEDevice();
                bLEDevice.mDeviceAddress = bluetoothDevice.getAddress();
                c.this.f4330c.b(bLEDevice);
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f();
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.dfu.e.b.c$c, reason: collision with other inner class name */
    class RunnableC0074c implements Runnable {
        RunnableC0074c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.h();
        }
    }

    public interface d {
        void a();

        void a(BLEDevice bLEDevice);

        void b(BLEDevice bLEDevice);

        void c(BLEDevice bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BLEDevice a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        byte[] bArrB = com.ido.ble.bluetooth.d.b.b(bArr);
        BLEDevice bLEDevice = new BLEDevice();
        if (bArrB != null && bArrB.length > 2) {
            bLEDevice.mDeviceId = ((bArrB[1] & UByte.MAX_VALUE) << 8) | (bArrB[0] & UByte.MAX_VALUE);
        }
        String name = bluetoothDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = com.ido.ble.bluetooth.d.b.a(bArr);
            if (TextUtils.isEmpty(name)) {
                return null;
            }
        }
        String address = bluetoothDevice.getAddress();
        bLEDevice.mDeviceName = name;
        bLEDevice.mDeviceAddress = address;
        bLEDevice.mRssi = i;
        if (!com.ido.ble.bluetooth.d.b.d(bArr)) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] has find target device, but broadcast data is invalid");
        }
        return bLEDevice;
    }

    private boolean b() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] task finished.");
        g();
    }

    private void d() {
        this.f4328a = BluetoothAdapter.getDefaultAdapter();
        this.f4329b = new Handler(Looper.getMainLooper());
    }

    private boolean e() {
        BLEDevice bLEDeviceC;
        List<BluetoothDevice> connectedDevices = ((BluetoothManager) e.a().getSystemService("bluetooth")).getConnectedDevices(7);
        if (connectedDevices != null && connectedDevices.size() != 0) {
            Iterator<BluetoothDevice> it = connectedDevices.iterator();
            while (it.hasNext()) {
                if (this.f4331d.equals(it.next().getAddress()) && (bLEDeviceC = com.ido.ble.f.a.f.b.e().c()) != null && this.f4331d.equals(bLEDeviceC.mDeviceAddress)) {
                    LogTool.b(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] target device is connected by other app");
                    this.f4330c.a(bLEDeviceC);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] a scan work finished.");
        this.f4329b.removeCallbacks(this.k);
        this.f4328a.stopLeScan(this.j);
        if (this.f4332e) {
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] not find target device,  wait for restart....");
        this.f4329b.postDelayed(new RunnableC0074c(), 3000L);
    }

    private void g() {
        this.f4329b.removeCallbacksAndMessages(null);
        this.f4328a.stopLeScan(this.j);
        m = false;
        this.f4333f = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            c();
            this.f4330c.a();
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] bluetooth switch is closed, task finished!");
            return;
        }
        this.f4333f++;
        LogTool.b(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] restart times is " + this.f4333f);
        if (this.f4333f < this.f4334g) {
            i();
            return;
        }
        c();
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] out of max retry times, task finished!");
        this.f4330c.a();
    }

    private void i() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] startScanDevices()");
        if (!Build.MANUFACTURER.equalsIgnoreCase("xiaomi")) {
            Build.MANUFACTURER.equalsIgnoreCase("meizu");
        }
        this.f4329b.removeCallbacks(this.k);
        this.f4329b.postDelayed(this.k, 30000L);
        this.f4328a.startLeScan(this.j);
    }

    public void a() {
        if (m) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] stop task");
            g();
        }
    }

    public void a(d dVar, String str) {
        a(dVar, str, 3);
    }

    public void a(d dVar, String str, int i) {
        this.f4334g = i;
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] start");
        if (m) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[ScanTargetDFUDeviceTask] at state of scanning, ignore this action");
            return;
        }
        this.f4330c = dVar;
        this.f4331d = str;
        this.f4335h = com.ido.ble.bluetooth.e.d.a(this.f4331d);
        this.i = com.ido.ble.bluetooth.e.d.b(this.f4331d);
        if (!b()) {
            m = false;
            return;
        }
        d();
        if (e()) {
            return;
        }
        i();
        m = true;
    }
}