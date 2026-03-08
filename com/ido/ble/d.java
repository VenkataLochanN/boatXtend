package com.ido.ble;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.text.TextUtils;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.common.e;
import com.ido.ble.firmware.log.f;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;

/* JADX INFO: loaded from: classes2.dex */
class d {

    static class a implements DeviceGattCallBack.ICallBack {
        a() {
        }

        @Override // com.ido.ble.callback.DeviceGattCallBack.ICallBack
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (value == null || value.length == 0) {
                return;
            }
            if (f.b().a(value)) {
                f.b().b(value);
                return;
            }
            if (value.length >= 20 || value[0] == 51) {
                u.a(value);
                return;
            }
            byte[] bArr = new byte[20];
            e.a(value, bArr);
            u.a(bArr);
        }

        @Override // com.ido.ble.callback.DeviceGattCallBack.ICallBack
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            u.B();
        }
    }

    d() {
    }

    private static String a() {
        return e.a().getFilesDir().getAbsolutePath();
    }

    static void a(InitParam initParam) {
        a(initParam.soJinLogSavePath);
        u.a();
        c();
        b();
    }

    private static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            u.a(true, false, "".getBytes());
        } else {
            u.a(true, true, str.getBytes());
        }
        u.b(a());
    }

    private static void b() {
        com.ido.ble.callback.b.K().a(new a());
    }

    private static void c() {
        int i;
        if (com.ido.ble.bluetooth.a.g()) {
            LogTool.d(com.ido.ble.logs.a.f4634b, "[SoLibInit] setMode(SYS_MODE_SET_BIND)");
            i = 1;
        } else {
            LogTool.d(com.ido.ble.logs.a.f4634b, "[SoLibInit] setMode(SYS_MODE_SET_NOBIND)");
            i = 0;
        }
        u.b(i);
    }
}