package com.ido.life.data.api.entity;

import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.net.BaseEntity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceListEntity extends BaseEntity implements Serializable {
    private List<DeviceInfo> result;

    public List<DeviceInfo> getResult() {
        return this.result;
    }

    public void setResult(List<DeviceInfo> list) {
        this.result = list;
    }

    public static class DeviceInfo implements Serializable, Comparable<DeviceInfo> {
        private boolean connected;
        private String customName;
        private String deviceId;
        private String deviceName;
        private float faceHeight;
        private float faceOffesetLeft;
        private float faceOffsetTop;
        private float faceWidth;
        private String imageUrl;
        private String imageUrl3;
        private boolean isHostDevice;
        private String mac;
        private String osVersion;
        private String otaVersion;
        private String phoneImei;
        private String phoneModel;
        private String phoneOs;
        private String phonePower;
        public int type;

        public String getImageUrl3() {
            return this.imageUrl3;
        }

        public void setImageUrl3(String str) {
            this.imageUrl3 = str;
        }

        public float getFaceHeight() {
            return this.faceHeight;
        }

        public void setFaceHeight(float f2) {
            this.faceHeight = f2;
        }

        public float getFaceWidth() {
            return this.faceWidth;
        }

        public void setFaceWidth(float f2) {
            this.faceWidth = f2;
        }

        public float getFaceOffsetTop() {
            return this.faceOffsetTop;
        }

        public void setFaceOffsetTop(float f2) {
            this.faceOffsetTop = f2;
        }

        public float getFaceOffesetLeft() {
            return this.faceOffesetLeft;
        }

        public void setFaceOffesetLeft(float f2) {
            this.faceOffesetLeft = f2;
        }

        public DeviceInfo() {
        }

        public DeviceInfo(BLEDevice bLEDevice) {
            if (bLEDevice == null) {
                return;
            }
            this.deviceId = String.valueOf(bLEDevice.mDeviceId);
            this.deviceName = bLEDevice.mDeviceName;
            this.mac = bLEDevice.mDeviceAddress;
            this.type = bLEDevice.type;
            this.otaVersion = String.valueOf(bLEDevice.version);
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public boolean isConnected() {
            return this.connected;
        }

        public void setConnected(boolean z) {
            this.connected = z;
        }

        public String getDeviceName() {
            return this.deviceName;
        }

        public void setDeviceName(String str) {
            this.deviceName = str;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public String getMac() {
            return this.mac;
        }

        public void setMac(String str) {
            this.mac = str;
        }

        public String getOsVersion() {
            return this.osVersion;
        }

        public void setOsVersion(String str) {
            this.osVersion = str;
        }

        public String getOtaVersion() {
            return this.otaVersion;
        }

        public void setOtaVersion(String str) {
            this.otaVersion = str;
        }

        public String getPhoneImei() {
            return this.phoneImei;
        }

        public void setPhoneImei(String str) {
            this.phoneImei = str;
        }

        public String getPhoneModel() {
            return this.phoneModel;
        }

        public void setPhoneModel(String str) {
            this.phoneModel = str;
        }

        public String getPhoneOs() {
            return this.phoneOs;
        }

        public void setPhoneOs(String str) {
            this.phoneOs = str;
        }

        public String getPhonePower() {
            return this.phonePower;
        }

        public void setPhonePower(String str) {
            this.phonePower = str;
        }

        public String getCustomName() {
            return TextUtils.isEmpty(this.customName) ? this.deviceName : this.customName;
        }

        public void setCustomName(String str) {
            this.customName = str;
        }

        public boolean isHostDevice() {
            return this.isHostDevice;
        }

        public void setHostDevice(boolean z) {
            this.isHostDevice = z;
        }

        public String toString() {
            return "DeviceInfo{deviceId='" + this.deviceId + "', connected=" + this.connected + ", deviceName='" + this.deviceName + "', imageUrl='" + this.imageUrl + "', mac='" + this.mac + "', osVersion='" + this.osVersion + "', otaVersion='" + this.otaVersion + "', phoneImei='" + this.phoneImei + "', phoneModel='" + this.phoneModel + "', phoneOs='" + this.phoneOs + "', phonePower='" + this.phonePower + "', type=" + this.type + ", customName='" + this.customName + "', isHostDevice='" + this.isHostDevice + "'}";
        }

        @Override // java.lang.Comparable
        public int compareTo(DeviceInfo deviceInfo) {
            boolean zIsHostDevice = isHostDevice();
            if (deviceInfo.isHostDevice() ^ zIsHostDevice) {
                return zIsHostDevice ? -1 : 2;
            }
            return 0;
        }
    }
}