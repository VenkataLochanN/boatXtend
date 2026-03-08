package com.ido.life.bean;

import com.ido.ble.bluetooth.device.BLEDevice;

/* JADX INFO: loaded from: classes2.dex */
public class AppBLEDevice extends BLEDevice {
    private String deviceThirdVersion;

    public String getDeviceThirdVersion() {
        return this.deviceThirdVersion;
    }

    public void setDeviceThirdVersion(String str) {
        this.deviceThirdVersion = str;
    }

    public AppBLEDevice() {
    }

    public AppBLEDevice(String str) {
        this.deviceThirdVersion = str;
    }

    public void setBLEDevice(BLEDevice bLEDevice) {
        this.mDeviceId = bLEDevice.mDeviceId;
        this.type = bLEDevice.type;
        this.mDeviceAddress = bLEDevice.mDeviceAddress;
        this.mDeviceName = bLEDevice.mDeviceName;
        this.mIsInDfuMode = bLEDevice.mIsInDfuMode;
        this.mRssi = bLEDevice.mRssi;
    }
}