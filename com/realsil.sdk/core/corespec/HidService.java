package com.realsil.sdk.core.corespec;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.realsil.sdk.core.bluetooth.BluetoothUuid;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class HidService {
    public OnServiceListener mCallback;
    public BluetoothGattService mService;
    public BluetoothGattCharacteristic wb;
    public String yb;
    public static final UUID zb = UUID.fromString("00001812-0000-1000-8000-00805f9b34fb");
    public static final UUID Ab = BluetoothUuid.fromString("2a4d");
    public static final UUID Bb = BluetoothUuid.fromString("2a22");
    public static final UUID Cb = BluetoothUuid.fromString("2a33");
    public static final UUID CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR = UUID.fromString(GlobalGatt.CLIENT_CHARACTERISTIC_CONFIG);
    public int xb = -1;
    public final BluetoothGattCallback ta = new BluetoothGattCallback() { // from class: com.realsil.sdk.core.corespec.HidService.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            bluetoothGattCharacteristic.getValue();
            BluetoothGattCharacteristic unused = HidService.this.wb;
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            bluetoothGattCharacteristic.getValue();
            if (i == 0) {
                return;
            }
            ZLogger.e(true, "Characteristic read error: " + i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                return;
            }
            ZLogger.w(true, "Descriptor write error: " + i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i == 0) {
                HidService.this.mService = bluetoothGatt.getService(HidService.zb);
                if (HidService.this.mService == null) {
                    ZLogger.w("HOGP_SERVICE_UUID not supported");
                }
            }
        }
    };

    public interface OnServiceListener {
        void onBatteryLevelChanged(int i, boolean z);
    }

    public HidService(String str, OnServiceListener onServiceListener) {
        this.mCallback = onServiceListener;
        this.yb = str;
        k();
    }

    public static boolean memcmp(byte[] bArr, byte[] bArr2, int i) {
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null) {
            return false;
        }
        if (bArr == bArr2) {
            return true;
        }
        for (int i2 = 0; i2 < bArr.length && i2 < bArr2.length && i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public void close() {
        GlobalGatt.getInstance().unRegisterCallback(this.yb, this.ta);
    }

    public int getBatteryValue() {
        return this.xb;
    }

    public final void k() {
        GlobalGatt.getInstance().registerCallback(this.yb, this.ta);
    }

    public boolean readBatteryLevel() {
        if (this.wb != null) {
            return GlobalGatt.getInstance().readCharacteristicSync(this.yb, this.wb);
        }
        ZLogger.w(true, "BATTERY_LEVEL_CHARACTERISTIC not supported");
        return false;
    }

    public boolean setNotificationEnabled(boolean z) {
        if (this.wb != null) {
            return GlobalGatt.getInstance().setCharacteristicNotificationSync(this.yb, this.wb, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, z);
        }
        ZLogger.w(true, "BATTERY_LEVEL_CHARACTERISTIC not supported");
        return false;
    }
}