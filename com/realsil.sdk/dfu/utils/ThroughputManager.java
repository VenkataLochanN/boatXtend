package com.realsil.sdk.dfu.utils;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.model.Throughput;
import java.text.DecimalFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class ThroughputManager {
    public static final long KB = 1024;
    public static final long MB = 1048576;
    public static DecimalFormat SPEED_FORMAT = new DecimalFormat("###,###,###.00");
    public static final int UNIT_BYTE = 0;
    public static final int UNIT_KB = 1;
    public static final int UNIT_MB = 2;
    public long Pd;
    public long Qd;
    public long Rd;
    public long ff;
    public byte[] gf;
    public long packetSize;
    public long startTime;

    public ThroughputManager() {
        prepare();
    }

    public byte[] getBufferData() {
        return this.gf;
    }

    public long getDeltaTime() {
        return Math.max(0L, this.Pd - this.startTime);
    }

    public long getPacketSize() {
        return this.packetSize;
    }

    public Throughput getThroughput() {
        long deltaTime = getDeltaTime();
        float f2 = deltaTime > 0 ? (this.ff * 1000.0f) / deltaTime : 0.0f;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.ff - this.Rd;
        long j2 = jCurrentTimeMillis - this.Qd;
        float f3 = j2 > 0 ? (j * 1000.0f) / j2 : 0.0f;
        ZLogger.v(String.format(Locale.US, "tpSpeed=%.2f=%d/%d\ntransferSpeed=%.2f=%d/%d", Float.valueOf(f3), Long.valueOf(j), Long.valueOf(j2), Float.valueOf(f2), Long.valueOf(this.ff), Long.valueOf(deltaTime)));
        this.Qd = jCurrentTimeMillis;
        long j3 = this.ff;
        this.Rd = j3;
        return new Throughput(this.packetSize, j3, deltaTime, f2, f3);
    }

    public long getTransferSize() {
        return this.ff;
    }

    public float getTransferSpeed() {
        long deltaTime = getDeltaTime();
        if (deltaTime > 0) {
            return (this.ff / deltaTime) * 1000.0f;
        }
        return 0.0f;
    }

    public void prepare() {
        this.startTime = System.currentTimeMillis();
        this.ff = 0L;
        this.Qd = this.startTime;
        this.Rd = 0L;
        this.gf = null;
    }

    public void sendOver() {
        this.Pd = System.currentTimeMillis();
        this.ff = this.packetSize;
        ZLogger.v("sendOver: " + getThroughput().toString());
    }

    public void setBufferData(byte[] bArr) {
        this.gf = bArr;
    }

    public void setPacketSize(long j) {
        this.packetSize = j;
        ZLogger.v("setPacketSize: " + getThroughput().toString());
    }

    public void start() {
        prepare();
    }

    public void transfer(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (this.ff <= 0) {
            this.startTime = System.currentTimeMillis();
        }
        this.Pd = System.currentTimeMillis();
        this.ff += (long) bArr.length;
        setBufferData(bArr);
    }
}