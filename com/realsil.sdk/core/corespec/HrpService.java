package com.realsil.sdk.core.corespec;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import androidx.core.view.MotionEventCompat;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class HrpService {
    public BluetoothGattCharacteristic Fb;
    public OnServiceListener mCallback;
    public BluetoothGattService mService;
    public String yb;
    public static final UUID Db = UUID.fromString("0000180d-0000-1000-8000-00805f9b34fb");
    public static final UUID Eb = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString(GlobalGatt.CLIENT_CHARACTERISTIC_CONFIG);
    public int Gb = -1;
    public final BluetoothGattCallback ta = new BluetoothGattCallback() { // from class: com.realsil.sdk.core.corespec.HrpService.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (HrpService.this.Fb != null && HrpService.Eb.equals(bluetoothGattCharacteristic.getUuid())) {
                ZLogger.d(true, "HRP DATA: " + Arrays.toString(value));
                HrpService.this.Gb = (value[1] & UByte.MAX_VALUE) | ((value[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
                if (HrpService.this.mCallback != null) {
                    HrpService.this.mCallback.onHrpValueReceive(HrpService.this.Gb);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i != 0) {
                ZLogger.e(true, "Descriptor write error: " + i);
                return;
            }
            if (HrpService.this.Fb != null && HrpService.Eb.equals(bluetoothGattDescriptor.getCharacteristic().getUuid()) && HrpService.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                ZLogger.d(true, "Descriptor write ok.");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i == 0) {
                HrpService.this.mService = bluetoothGatt.getService(HrpService.Db);
                if (HrpService.this.mService == null) {
                    ZLogger.w(true, "HEART_RATE_SERVICE not supported");
                    return;
                }
                HrpService hrpService = HrpService.this;
                hrpService.Fb = hrpService.mService.getCharacteristic(HrpService.Eb);
                if (HrpService.this.Fb == null) {
                    ZLogger.e(true, "HEART_RATE_MEASUREMENT_CHARACTERISTIC not supported");
                    return;
                }
                ZLogger.d(true, "find HEART_RATE_MEASUREMENT_CHARACTERISTIC : " + HrpService.Eb);
                List<BluetoothGattDescriptor> descriptors = HrpService.this.Fb.getDescriptors();
                if (descriptors == null || descriptors.size() <= 0) {
                    return;
                }
                Iterator<BluetoothGattDescriptor> it = descriptors.iterator();
                while (it.hasNext()) {
                    ZLogger.d("descriptor : " + it.next().getUuid().toString());
                }
            }
        }
    };

    public interface OnServiceListener {
        void onHrpValueReceive(int i);
    }

    public HrpService(String str, OnServiceListener onServiceListener) {
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

    public boolean enableNotification(boolean z) {
        return GlobalGatt.getInstance().setCharacteristicNotificationSync(this.yb, this.Fb, CLIENT_CHARACTERISTIC_CONFIG, z);
    }

    public int getValue() {
        return this.Gb;
    }

    public boolean isNotifyEnable() {
        byte[] value = this.Fb.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG).getValue();
        ZLogger.d(true, "data: " + Arrays.toString(value));
        return memcmp(value, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE, 2);
    }

    public final void k() {
        GlobalGatt.getInstance().registerCallback(this.yb, this.ta);
    }
}