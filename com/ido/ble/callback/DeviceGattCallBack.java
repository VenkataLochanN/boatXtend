package com.ido.ble.callback;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceGattCallBack {

    public interface ICallBack {
        void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

        void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);
    }

    public static void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Iterator<ICallBack> it = b.K().k().iterator();
        while (it.hasNext()) {
            it.next().onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        }
    }

    public static void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        Iterator<ICallBack> it = b.K().k().iterator();
        while (it.hasNext()) {
            it.next().onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        }
    }
}