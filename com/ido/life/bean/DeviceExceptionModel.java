package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceExceptionModel implements Serializable {
    public int deviceId;
    public String deviceName;
    public int errorIndex;
    public String mac;
    public int otaVersion;
    public long timestamp;

    public DeviceExceptionModel() {
    }

    public DeviceExceptionModel(int i, String str, String str2, int i2, int i3, long j) {
        this.deviceId = i;
        this.deviceName = str;
        this.mac = str2;
        this.otaVersion = i2;
        this.errorIndex = i3;
        this.timestamp = j;
    }

    public String toString() {
        return "DeviceExceptionModel{deviceId=" + this.deviceId + ", deviceName='" + this.deviceName + "', mac='" + this.mac + "', otaVersion=" + this.otaVersion + ", errorIndex=" + this.errorIndex + ", timestamp=" + this.timestamp + '}';
    }
}