package com.ido.ble.bluetooth.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static b f3977d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private e f3978a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3979b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BroadcastReceiver f3980c = new a();

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            byte b2 = -1;
            int iHashCode = action.hashCode();
            if (iHashCode != -1780914469) {
                if (iHashCode != 6759640) {
                    if (iHashCode == 1167529923 && action.equals("android.bluetooth.device.action.FOUND")) {
                        b2 = 2;
                    }
                } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                    b2 = 0;
                }
            } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                b2 = 1;
            }
            if (b2 == 0) {
                LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] scanReceiver. discovery start");
                return;
            }
            if (b2 == 1) {
                LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] scanReceiver. discovery finished");
                b.this.f3978a.a();
                b.this.a();
            } else {
                if (b2 != 2) {
                    return;
                }
                b.this.a((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
            }
        }
    }

    private b() {
    }

    private BluetoothDevice a(String str) {
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (str.equals(bluetoothDevice.getAddress())) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] finished.");
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] find one device[" + bluetoothDevice.getAddress() + "--" + bluetoothDevice.getName() + "]");
        if (bluetoothDevice == null || !this.f3979b.equals(bluetoothDevice.getAddress())) {
            return;
        }
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] has find device.");
        this.f3978a.a(bluetoothDevice);
        a();
    }

    public static b b() {
        if (f3977d == null) {
            f3977d = new b();
        }
        return f3977d;
    }

    private void c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        com.ido.ble.b.b().registerReceiver(this.f3980c, intentFilter);
    }

    private void d() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] release.");
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        com.ido.ble.b.b().unregisterReceiver(this.f3980c);
    }

    private void e() {
        if (BluetoothAdapter.getDefaultAdapter().isDiscovering()) {
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        }
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] startDiscovery. result=" + BluetoothAdapter.getDefaultAdapter().startDiscovery());
    }

    public void a(String str, e eVar) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] scan " + str);
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] bluetooth is disEnable ");
            eVar.a();
            return;
        }
        BluetoothDevice bluetoothDeviceA = a(str);
        if (bluetoothDeviceA != null) {
            LogTool.d(com.ido.ble.logs.a.q, "[BTScanManager] has find device from bondList.");
            eVar.a(bluetoothDeviceA);
        } else {
            this.f3979b = str;
            this.f3978a = eVar;
            c();
            e();
        }
    }
}