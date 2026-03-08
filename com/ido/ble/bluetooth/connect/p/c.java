package com.ido.ble.bluetooth.connect.p;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c extends com.ido.ble.bluetooth.connect.p.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BluetoothAdapter.LeScanCallback f4096a = new a();

    class a implements BluetoothAdapter.LeScanCallback {
        a() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            c.this.a(bluetoothDevice, i, bArr);
        }
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void a() {
        BluetoothAdapter.getDefaultAdapter().startLeScan(this.f4096a);
    }

    @Override // com.ido.ble.bluetooth.connect.p.a
    public void b() {
        BluetoothAdapter.getDefaultAdapter().stopLeScan(this.f4096a);
    }
}