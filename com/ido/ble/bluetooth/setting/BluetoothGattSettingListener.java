package com.ido.ble.bluetooth.setting;

import android.bluetooth.BluetoothGattCharacteristic;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothGattSettingListener {
    private static IListener bluetoothGattSettingListener;

    public interface IListener {
        BluetoothGattCharacteristic addParaToCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);
    }

    public static IListener getBluetoothGattSettingListener() {
        return bluetoothGattSettingListener;
    }

    public static void setBluetoothGattSettingListener(IListener iListener) {
        bluetoothGattSettingListener = iListener;
    }
}