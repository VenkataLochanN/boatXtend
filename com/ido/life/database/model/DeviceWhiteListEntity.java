package com.ido.life.database.model;

import com.ido.common.net.BaseEntity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceWhiteListEntity extends BaseEntity implements Serializable {
    private List<DeviceInfo> result;

    public List<DeviceInfo> getResult() {
        return this.result;
    }

    public void setResult(List<DeviceInfo> list) {
        this.result = list;
    }

    public String toString() {
        return "DeviceWhiteListEntity{result=" + this.result + '}';
    }

    public static class DeviceInfo implements Serializable {
        private String bluetoothName;
        private String deviceId;
        private String deviceName;
        private int id;
        private String imageUrl;
        private int type;

        public DeviceInfo() {
        }

        public DeviceInfo(String str) {
            this.deviceName = str;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getDeviceName() {
            return this.deviceName;
        }

        public void setDeviceName(String str) {
            this.deviceName = str;
        }

        public String getBluetoothName() {
            return this.bluetoothName;
        }

        public void setBluetoothName(String str) {
            this.bluetoothName = str;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public String toString() {
            return "DeviceInfo{id=" + this.id + ", deviceName='" + this.deviceName + "', bluetoothName='" + this.bluetoothName + "', imageUrl='" + this.imageUrl + "', deviceId='" + this.deviceId + "', type=" + this.type + '}';
        }
    }
}