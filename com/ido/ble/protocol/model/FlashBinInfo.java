package com.ido.ble.protocol.model;

/* JADX INFO: loaded from: classes2.dex */
public class FlashBinInfo {
    public long checkCode;
    public int matchVersion;
    public int status;
    public int version;

    public String toString() {
        return "FlashBinInfo{status=" + this.status + ", version=" + this.version + ", matchVersion=" + this.matchVersion + ", checkCode=" + this.checkCode + '}';
    }
}