package com.ido.ble.bluetooth.e;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static BluetoothGattCharacteristic a(BluetoothGatt bluetoothGatt) {
        return a(bluetoothGatt, f.f4142h, f.k);
    }

    private static BluetoothGattCharacteristic a(BluetoothGatt bluetoothGatt, UUID uuid, UUID uuid2) {
        BluetoothGattService service;
        if (bluetoothGatt == null || (service = bluetoothGatt.getService(uuid)) == null) {
            return null;
        }
        return service.getCharacteristic(uuid2);
    }

    private boolean a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 32) == 0) {
            return false;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(f.f4137c);
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            return bluetoothGatt.writeDescriptor(descriptor);
        }
        return false;
    }

    private static boolean a(BluetoothGatt bluetoothGatt, UUID uuid, boolean z) {
        BluetoothGattDescriptor descriptor;
        BluetoothGattCharacteristic bluetoothGattCharacteristicA = a(bluetoothGatt, f.f4142h, uuid);
        if (bluetoothGattCharacteristicA == null || (bluetoothGattCharacteristicA.getProperties() | 16) <= 0) {
            return false;
        }
        String str = "uuid:" + uuid + ",enable:" + z + ",setCharacteristicNotification:" + bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristicA, z);
        if (!uuid.equals(bluetoothGattCharacteristicA.getUuid()) || (descriptor = bluetoothGattCharacteristicA.getDescriptor(f.f4137c)) == null) {
            return false;
        }
        descriptor.setValue(z ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        return bluetoothGatt.writeDescriptor(descriptor);
    }

    public static boolean a(BluetoothGatt bluetoothGatt, boolean z) {
        return a(bluetoothGatt, f.l, z);
    }

    public static BluetoothGattCharacteristic b(BluetoothGatt bluetoothGatt) {
        return a(bluetoothGatt, f.f4142h, f.i);
    }

    private boolean b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || (bluetoothGattCharacteristic.getProperties() & 16) == 0) {
            return false;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(f.f4137c);
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            return bluetoothGatt.writeDescriptor(descriptor);
        }
        return false;
    }

    public static boolean b(BluetoothGatt bluetoothGatt, boolean z) {
        return a(bluetoothGatt, f.j, z);
    }

    public static BluetoothGattCharacteristic c(BluetoothGatt bluetoothGatt) {
        return a(bluetoothGatt, f.f4142h, f.f4136b);
    }

    public static boolean c(BluetoothGatt bluetoothGatt, boolean z) {
        return a(bluetoothGatt, f.f4136b, z);
    }
}