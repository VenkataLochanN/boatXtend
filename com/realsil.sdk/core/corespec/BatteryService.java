package com.realsil.sdk.core.corespec;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class BatteryService {
    public OnServiceListener mCallback;
    public BluetoothGattService mService;
    public BluetoothGattCharacteristic wb;
    public String yb;
    public static final UUID BATTERY_SERVICE = UUID.fromString("0000180f-0000-1000-8000-00805f9b34fb");
    public static final UUID vb = UUID.fromString("00002a19-0000-1000-8000-00805f9b34fb");
    public static final UUID CHARACTERISTIC_PRESENTATION_FORMAT_DESCRIPTOR = UUID.fromString("00002904-0000-1000-8000-00805f9b34fb");
    public static final UUID CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR = UUID.fromString(GlobalGatt.CLIENT_CHARACTERISTIC_CONFIG);
    public int xb = -1;
    public final BluetoothGattCallback ta = new BluetoothGattCallback() { // from class: com.realsil.sdk.core.corespec.BatteryService.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (BatteryService.this.wb != null && BatteryService.vb.equals(bluetoothGattCharacteristic.getUuid())) {
                BatteryService.this.xb = value[0];
                ZLogger.d(true, "mBatteryValue=" + BatteryService.this.xb);
                if (BatteryService.this.mCallback != null) {
                    BatteryService.this.mCallback.onBatteryLevelChanged(BatteryService.this.xb, true);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (i != 0) {
                ZLogger.e(true, "Characteristic read error: " + i);
                return;
            }
            if (BatteryService.vb.equals(bluetoothGattCharacteristic.getUuid())) {
                BatteryService.this.xb = value[0];
                ZLogger.d(true, "mBatteryValue=" + BatteryService.this.xb);
                if (BatteryService.this.mCallback != null) {
                    BatteryService.this.mCallback.onBatteryLevelChanged(BatteryService.this.xb, false);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i == 0) {
                if (BatteryService.vb.equals(bluetoothGattDescriptor.getCharacteristic().getUuid()) && BatteryService.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR.equals(bluetoothGattDescriptor.getUuid())) {
                    ZLogger.d(true, "CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR write OK.");
                    return;
                }
                return;
            }
            ZLogger.w(true, "Descriptor write error: " + i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            String str;
            if (i == 0) {
                BatteryService.this.mService = bluetoothGatt.getService(BatteryService.BATTERY_SERVICE);
                if (BatteryService.this.mService == null) {
                    str = "BATTERY_SERVICE not supported";
                } else {
                    BatteryService batteryService = BatteryService.this;
                    batteryService.wb = batteryService.mService.getCharacteristic(BatteryService.vb);
                    if (BatteryService.this.wb != null) {
                        ZLogger.d(true, "find BATTERY_LEVEL_CHARACTERISTIC: " + BatteryService.vb);
                        List<BluetoothGattDescriptor> descriptors = BatteryService.this.wb.getDescriptors();
                        if (descriptors == null || descriptors.size() <= 0) {
                            return;
                        }
                        Iterator<BluetoothGattDescriptor> it = descriptors.iterator();
                        while (it.hasNext()) {
                            ZLogger.d("descriptor : " + it.next().getUuid().toString());
                        }
                        return;
                    }
                    str = "BATTERY_LEVEL_CHARACTERISTIC not supported";
                }
                ZLogger.w(str);
            }
        }
    };

    public interface OnServiceListener {
        void onBatteryLevelChanged(int i, boolean z);
    }

    public BatteryService(String str, OnServiceListener onServiceListener) {
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