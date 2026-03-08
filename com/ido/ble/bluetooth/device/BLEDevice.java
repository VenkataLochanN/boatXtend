package com.ido.ble.bluetooth.device;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class BLEDevice implements Serializable, Comparable<BLEDevice> {
    public static final int TYPE_BRACELET = 2;
    public static final int TYPE_FROM_PHONE_PAIRED = -1;
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_WATCH = 1;
    private static final long serialVersionUID = -5217710157640312976L;
    public String mDeviceAddress;
    public int mDeviceId;
    public String mDeviceName;
    public boolean mIsInDfuMode;
    public int mRssi;

    @Deprecated
    public OTAFactoryDeviceInfo otaFactoryDeviceInfo;
    public int type;

    @Deprecated
    public int version = -1;

    public static class OTAFactoryDeviceInfo implements Serializable {
        public int version = 0;
        public int bootload_version = 0;
        public int special_version = 0;
        public int flash_bin_version = 0;
        public int internal_version = 0;

        public String toString() {
            return "OTAFactoryDeviceInfo{version=" + this.version + ", bootload_version=" + this.bootload_version + ", special_version=" + this.special_version + ", flash_bin_version=" + this.flash_bin_version + ", internal_version=" + this.internal_version + '}';
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(BLEDevice bLEDevice) {
        return Integer.compare(bLEDevice.mRssi, this.mRssi);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.mDeviceAddress.equals(((BLEDevice) obj).mDeviceAddress);
    }

    public String toFactoryString() {
        return "BLEDevice{mDeviceName='" + this.mDeviceName + "', mDeviceAddress='" + this.mDeviceAddress + "', mRssi=" + this.mRssi + ", mDeviceId=" + this.mDeviceId + ", mIsInDfuMode=" + this.mIsInDfuMode + ", type=" + this.type + ", otaFactoryDeviceInfo=" + this.otaFactoryDeviceInfo + '}';
    }

    public String toString() {
        return "BLEDevice{mDeviceName='" + this.mDeviceName + "', mDeviceAddress='" + this.mDeviceAddress + "', mRssi=" + this.mRssi + ", mDeviceId=" + this.mDeviceId + ", mIsInDfuMode=" + this.mIsInDfuMode + ", type=" + this.type + ", version=" + this.version + '}';
    }
}