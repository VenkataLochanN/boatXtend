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
public class ImmediateAlertService {
    public static final byte ALERT_LEVEL_DISABLE = 0;
    public static final byte ALERT_LEVEL_ENABLE_WITH_LED = 1;
    public static final byte ALERT_LEVEL_ENABLE_WITH_LED_BUZZER = 2;
    public static final UUID Hb = UUID.fromString("00001802-0000-1000-8000-00805f9b34fb");
    public static final UUID Ib = UUID.fromString("00002a06-0000-1000-8000-00805f9b34fb");
    public BluetoothGattCharacteristic Kb;
    public BluetoothGattService mService;
    public String yb;
    public byte Jb = 0;
    public Object mLock = new Object();
    public volatile boolean Lb = false;
    public final int Mb = 5000;
    public final BluetoothGattCallback ta = new BluetoothGattCallback() { // from class: com.realsil.sdk.core.corespec.ImmediateAlertService.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            String str;
            if (i == 0) {
                ImmediateAlertService.this.mService = bluetoothGatt.getService(ImmediateAlertService.Hb);
                if (ImmediateAlertService.this.mService == null) {
                    str = "IMMEDIATE_ALERT_SERVICE not supported";
                } else {
                    ImmediateAlertService immediateAlertService = ImmediateAlertService.this;
                    immediateAlertService.Kb = immediateAlertService.mService.getCharacteristic(ImmediateAlertService.Ib);
                    if (ImmediateAlertService.this.Kb != null) {
                        ZLogger.d(true, "find ALERT_LEVEL_CHARACTERISTIC : " + ImmediateAlertService.Ib);
                        ImmediateAlertService.this.Kb.setWriteType(1);
                        List<BluetoothGattDescriptor> descriptors = ImmediateAlertService.this.Kb.getDescriptors();
                        if (descriptors == null || descriptors.size() <= 0) {
                            return;
                        }
                        Iterator<BluetoothGattDescriptor> it = descriptors.iterator();
                        while (it.hasNext()) {
                            ZLogger.d("descriptor : " + it.next().getUuid().toString());
                        }
                        return;
                    }
                    str = "ALERT_LEVEL_CHARACTERISTIC not supported";
                }
                ZLogger.w(true, str);
            }
        }
    };

    public ImmediateAlertService(String str) {
        this.yb = str;
        k();
    }

    public void close() {
        GlobalGatt.getInstance().unRegisterCallback(this.yb, this.ta);
    }

    public final void k() {
        GlobalGatt.getInstance().registerCallback(this.yb, this.ta);
    }

    public boolean setAlertLevel(byte b2) {
        return setAlertLevel(this.yb, b2);
    }

    public boolean setAlertLevel(String str, byte b2) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.Kb;
        if (bluetoothGattCharacteristic == null) {
            ZLogger.e(true, "ALERT_LEVEL_CHARACTERISTIC not supported");
            return false;
        }
        bluetoothGattCharacteristic.setValue(new byte[]{b2});
        this.Jb = b2;
        return GlobalGatt.getInstance().writeCharacteristicSync(str, this.Kb);
    }

    public boolean setAlertLevelEnabled(String str, boolean z) {
        ZLogger.d(true, "enabled=" + z);
        return setAlertLevel(str, z ? (byte) 2 : (byte) 0);
    }

    public boolean setAlertLevelEnabled(boolean z) {
        return setAlertLevelEnabled(this.yb, z);
    }
}