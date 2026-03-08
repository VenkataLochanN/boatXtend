package com.realsil.sdk.core.bluetooth.scanner;

import android.bluetooth.BluetoothDevice;
import com.realsil.sdk.core.bluetooth.BluetoothUuid;

/* JADX INFO: loaded from: classes3.dex */
public class ExtendedBluetoothDevice {
    public static final boolean DEVICE_IS_BONDED = true;
    public static final boolean DEVICE_NOT_BONDED = false;
    public static final int NO_RSSI = -1000;
    public int Ta;
    public boolean Ua;
    public BluetoothDevice device;
    public boolean isBonded;
    public boolean isConnected;
    public String name;
    public int rssi;
    public byte[] scanRecord;
    public SpecScanRecord specScanRecord;

    public static class AddressComparator {
        public String address;

        public boolean equals(Object obj) {
            return obj instanceof ExtendedBluetoothDevice ? this.address.equals(((ExtendedBluetoothDevice) obj).device.getAddress()) : super.equals(obj);
        }
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str) {
        this(bluetoothDevice, str, -1000, false, false, null);
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i) {
        this(bluetoothDevice, str, i, false, false, null);
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i, boolean z, boolean z2) {
        this(bluetoothDevice, str, i, z, z2, null);
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i, boolean z, boolean z2, byte[] bArr) {
        this.device = bluetoothDevice;
        this.name = str;
        this.rssi = i;
        this.isBonded = z;
        this.isConnected = z2;
        setScanRecord(bArr);
    }

    public boolean equals(Object obj) {
        return obj instanceof ExtendedBluetoothDevice ? this.device.getAddress().equals(((ExtendedBluetoothDevice) obj).device.getAddress()) : super.equals(obj);
    }

    public int getConnectState() {
        return this.Ta;
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public String getName() {
        return this.name;
    }

    public int getRssi() {
        return this.rssi;
    }

    public byte[] getScanRecord() {
        return this.scanRecord;
    }

    public boolean isBonded() {
        return this.isBonded;
    }

    public boolean isConnect() {
        return this.isConnected;
    }

    public boolean isHogp() {
        return this.Ua;
    }

    public void setBonded(boolean z) {
        this.isBonded = z;
    }

    public void setConnect(boolean z) {
        this.isConnected = z;
    }

    public void setConnectState(int i) {
        this.Ta = i;
        this.isConnected = i == 2;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public void setHogp(boolean z) {
        this.Ua = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setScanRecord(byte[] bArr) {
        this.scanRecord = bArr;
        this.specScanRecord = SpecScanRecord.parseFromBytes(bArr);
        SpecScanRecord specScanRecord = this.specScanRecord;
        if (specScanRecord == null || specScanRecord.getServiceUuids() == null) {
            return;
        }
        this.Ua = this.specScanRecord.getServiceUuids().contains(BluetoothUuid.HOGP);
    }
}