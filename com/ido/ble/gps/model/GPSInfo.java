package com.ido.ble.gps.model;

/* JADX INFO: loaded from: classes2.dex */
public class GPSInfo {
    public int agpsErrCode;
    public int agpsInfo;
    public int errCode;
    public int fwVersion;

    public String toString() {
        return "GPSInfo{errCode=" + this.errCode + ", fwVersion=" + this.fwVersion + ", agpsInfo=" + this.agpsInfo + ", agpsErrCode=" + this.agpsErrCode + '}';
    }
}