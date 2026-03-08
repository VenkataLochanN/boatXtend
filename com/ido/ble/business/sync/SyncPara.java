package com.ido.ble.business.sync;

/* JADX INFO: loaded from: classes2.dex */
public class SyncPara {
    public ISyncDataListener iSyncDataListener;
    public ISyncProgressListener iSyncProgressListener;
    public boolean isNeedSyncConfigData;
    public long timeoutMillisecond = 180000;

    public String toString() {
        return "SyncPara{timeoutMillisecond=" + this.timeoutMillisecond + ", iSyncDataListener=" + this.iSyncDataListener + ", iSyncProgressListener=" + this.iSyncProgressListener + ", isNeedSyncConfigData=" + this.isNeedSyncConfigData + '}';
    }
}