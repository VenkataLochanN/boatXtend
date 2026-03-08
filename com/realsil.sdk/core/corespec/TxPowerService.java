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
public class TxPowerService {
    public static final UUID Pb = UUID.fromString("00001804-0000-1000-8000-00805f9b34fb");
    public static final UUID Qb = UUID.fromString("00002a07-0000-1000-8000-00805f9b34fb");
    public BluetoothGattCharacteristic Rb;
    public OnServiceListener mCallback;
    public BluetoothGattService mService;
    public int sb = -1;
    public final BluetoothGattCallback ta = new BluetoothGattCallback() { // from class: com.realsil.sdk.core.corespec.TxPowerService.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (i != 0) {
                ZLogger.e(true, "Characteristic read error: " + i);
                return;
            }
            if (TxPowerService.Qb.equals(bluetoothGattCharacteristic.getUuid())) {
                TxPowerService.this.sb = value[0];
                ZLogger.d(true, "mTxPowerLevel=" + TxPowerService.this.sb);
                if (TxPowerService.this.mCallback != null) {
                    TxPowerService.this.mCallback.onTxPowerLevelRead(TxPowerService.this.sb);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            if (i == 0) {
                TxPowerService.this.mService = bluetoothGatt.getService(TxPowerService.Pb);
                if (TxPowerService.this.mService == null) {
                    ZLogger.w(true, "TX_POWER_SERVICE not supported");
                    return;
                }
                TxPowerService txPowerService = TxPowerService.this;
                txPowerService.Rb = txPowerService.mService.getCharacteristic(TxPowerService.Qb);
                if (TxPowerService.this.Rb == null) {
                    ZLogger.e(true, "TX_POWER_LEVEL_CHARACTERISTIC not supported");
                    return;
                }
                ZLogger.d(true, "find TX_POWER_LEVEL_CHARACTERISTIC : " + TxPowerService.Qb);
                List<BluetoothGattDescriptor> descriptors = TxPowerService.this.Rb.getDescriptors();
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
    public String yb;

    public interface OnServiceListener {
        void onTxPowerLevelRead(int i);
    }

    public TxPowerService(String str, OnServiceListener onServiceListener) {
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

    public final void k() {
        GlobalGatt.getInstance().registerCallback(this.yb, this.ta);
    }

    public boolean readTxPowerLevel() {
        if (this.Rb != null) {
            return GlobalGatt.getInstance().readCharacteristicSync(this.yb, this.Rb);
        }
        ZLogger.w(true, "TX_POWER_LEVEL_CHARACTERISTIC not supported");
        return false;
    }
}