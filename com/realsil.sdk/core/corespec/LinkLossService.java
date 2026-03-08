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
public class LinkLossService {
    public static final byte ALERT_LEVEL_DISABLE = 0;
    public static final byte ALERT_LEVEL_ENABLE_WITH_LED = 1;
    public static final byte ALERT_LEVEL_ENABLE_WITH_LED_BUZZER = 2;
    public BluetoothGattCharacteristic Ob;
    public OnServiceListener mCallback;
    public BluetoothGattService mService;
    public String yb;
    public static final UUID Nb = UUID.fromString("00001803-0000-1000-8000-00805f9b34fb");
    public static final UUID Ib = UUID.fromString("00002a06-0000-1000-8000-00805f9b34fb");
    public byte Jb = 0;
    public final BluetoothGattCallback ta = new BluetoothGattCallback() { // from class: com.realsil.sdk.core.corespec.LinkLossService.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (i != 0) {
                ZLogger.e(true, "Characteristic read error: " + i);
                return;
            }
            if (LinkLossService.this.Ob == null || !LinkLossService.Ib.equals(bluetoothGattCharacteristic.getUuid())) {
                return;
            }
            LinkLossService.this.Jb = value[0];
            ZLogger.d(true, "mAlertLevel=" + ((int) LinkLossService.this.Jb));
            if (LinkLossService.this.mCallback != null) {
                LinkLossService.this.mCallback.onLinkLossAlertLevelChanged(LinkLossService.this.Jb);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            String str;
            if (i == 0) {
                LinkLossService.this.mService = bluetoothGatt.getService(LinkLossService.Nb);
                if (LinkLossService.this.mService == null) {
                    str = "LINK_LOSS_SERVICE not supported";
                } else {
                    LinkLossService linkLossService = LinkLossService.this;
                    linkLossService.Ob = linkLossService.mService.getCharacteristic(LinkLossService.Ib);
                    if (LinkLossService.this.Ob != null) {
                        ZLogger.d(true, "ALERT_LEVEL_CHARACTERISTIC_UUID: " + LinkLossService.this.Ob.getUuid());
                        LinkLossService.this.Ob.setWriteType(2);
                        List<BluetoothGattDescriptor> descriptors = LinkLossService.this.Ob.getDescriptors();
                        if (descriptors == null || descriptors.size() <= 0) {
                            return;
                        }
                        Iterator<BluetoothGattDescriptor> it = descriptors.iterator();
                        while (it.hasNext()) {
                            ZLogger.d("descriptor : " + it.next().getUuid().toString());
                        }
                        return;
                    }
                    str = "ALERT_LEVEL_CHARACTERISTIC_UUID not supported";
                }
                ZLogger.e(true, str);
            }
        }
    };

    public interface OnServiceListener {
        void onLinkLossAlertLevelChanged(byte b2);
    }

    public LinkLossService(String str, OnServiceListener onServiceListener) {
        this.mCallback = onServiceListener;
        this.yb = str;
        k();
    }

    public void close() {
        GlobalGatt.getInstance().unRegisterCallback(this.yb, this.ta);
    }

    public byte getAlertLevel() {
        return this.Jb;
    }

    public boolean isAlertEnabled() {
        return (this.mService == null || this.Ob == null || this.Jb == 0) ? false : true;
    }

    public boolean isHighAlertEnabled() {
        return (this.mService == null || this.Ob == null || this.Jb != 2) ? false : true;
    }

    public boolean isMidAlertEnabled() {
        return (this.mService == null || this.Ob == null || this.Jb != 1) ? false : true;
    }

    public final void k() {
        GlobalGatt.getInstance().registerCallback(this.yb, this.ta);
    }

    public boolean readAlertLevel() {
        if (this.Ob == null) {
            ZLogger.w(true, "ALERT_LEVEL_CHARACTERISTIC_UUID not supported");
            return false;
        }
        ZLogger.d(true, "read alert info.");
        return GlobalGatt.getInstance().readCharacteristic(this.yb, this.Ob);
    }

    public boolean setAlertEnabled(boolean z) {
        ZLogger.d(true, "enable: " + z);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.Ob;
        if (bluetoothGattCharacteristic == null) {
            ZLogger.w(true, "ALERT_LEVEL_CHARACTERISTIC_UUID not supported");
            return false;
        }
        if (z) {
            bluetoothGattCharacteristic.setValue(new byte[]{2});
        } else {
            bluetoothGattCharacteristic.setValue(new byte[]{0});
        }
        return GlobalGatt.getInstance().writeCharacteristicSync(this.yb, this.Ob);
    }

    public boolean setAlertLevel(byte b2) {
        return setAlertLevel(this.yb, b2);
    }

    public boolean setAlertLevel(String str, byte b2) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.Ob;
        if (bluetoothGattCharacteristic == null) {
            ZLogger.e(true, "ALERT_LEVEL_CHARACTERISTIC not supported");
            return false;
        }
        bluetoothGattCharacteristic.setValue(new byte[]{b2});
        this.Jb = b2;
        return GlobalGatt.getInstance().writeCharacteristicSync(str, this.Ob);
    }
}