package com.ido.ble.custom.connect;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

/* JADX INFO: loaded from: classes2.dex */
public class CustomGattCallBack {

    public interface ICallBack {
        void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

        void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

        void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i);

        void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i);
    }

    public interface IEnableNotifyCallback {
        BluetoothGatt getConnectedGatt();

        boolean isConnectedAndReady();

        void onEnableNotify(boolean z);
    }
}