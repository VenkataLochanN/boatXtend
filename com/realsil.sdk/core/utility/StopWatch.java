package com.realsil.sdk.core.utility;

/* JADX INFO: loaded from: classes3.dex */
public class StopWatch {
    public volatile long fc;
    public int gc = 0;

    public static StopWatch getInstance() {
        return new StopWatch();
    }

    public void lapEnd() {
        this.gc++;
        long jNanoTime = (System.nanoTime() - this.fc) / 1000;
        this.fc = System.nanoTime();
    }

    public void lapStart() {
        this.gc++;
        long jNanoTime = (System.nanoTime() - this.fc) / 1000;
        this.fc = System.nanoTime();
    }

    public void reset() {
        this.fc = System.nanoTime();
        this.gc = 0;
    }

    public void start() {
        this.fc = System.nanoTime();
        this.gc = 0;
    }
}