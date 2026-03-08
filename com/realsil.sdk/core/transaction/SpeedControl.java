package com.realsil.sdk.core.transaction;

import com.realsil.sdk.core.logger.ZLogger;

/* JADX INFO: loaded from: classes3.dex */
public class SpeedControl {
    public static boolean u = true;
    public volatile long _b = -1;
    public int ac;
    public int bc;
    public int cc;
    public boolean enabled;

    public SpeedControl(int i, int i2, boolean z) {
        this.cc = -1;
        this.enabled = false;
        this.ac = i;
        this.bc = i2;
        this.enabled = z;
        this.cc = (int) (((this.ac * 1000) / this.bc) * 1000.0f);
    }

    public void block() {
        if (this.enabled) {
            if (this._b == -1 || this.cc == -1) {
                ZLogger.w(u, "stop speed control with error, must initial first");
            } else {
                while ((System.nanoTime() - this._b) / 1000 < this.cc) {
                }
                ZLogger.v(u, "stop speed control");
            }
        }
    }

    public void flow() {
        if (this.enabled) {
            if (this._b == -1 || this.cc == -1) {
                ZLogger.w(u, "stop speed control with error, must initial first");
            } else {
                while ((System.nanoTime() - this._b) / 1000 < this.cc) {
                }
                ZLogger.v(u, "stop speed control");
            }
        }
    }

    public int getMaxSpeed() {
        return this.bc;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void setMaxSpeed(int i) {
        if (this.enabled) {
            if (i == this.bc) {
                ZLogger.w(u, "speed didn't change");
                return;
            }
            this.bc = i;
            this.cc = (int) (((this.ac * 1000) / this.bc) * 1000.0f);
            ZLogger.i(u, "time delta is: " + this.cc);
        }
    }

    public void setPacketSize(int i) {
        if (this.enabled) {
            if (i == this.ac) {
                ZLogger.w(u, "packet size didn't change");
                return;
            }
            this.ac = i;
            this.cc = (int) (((this.ac * 1000) / this.bc) * 1000.0f);
            ZLogger.i(u, "time delta is: " + this.cc);
        }
    }

    public void start() {
        if (this.enabled) {
            this._b = System.nanoTime();
            ZLogger.d(u, "start speed control");
        }
    }
}