package com.ido.ble.watch.custom.model;

/* JADX INFO: loaded from: classes2.dex */
public class WatchPlateFileMakeConfig {
    public int blockSize;
    public String filePath;
    public int format;
    public String outFileName;

    public String toString() {
        return "WatchPlateFileMakeConfig{filePath='" + this.filePath + "', outFileName='" + this.outFileName + "', format=" + this.format + ", blockSize=" + this.blockSize + '}';
    }
}