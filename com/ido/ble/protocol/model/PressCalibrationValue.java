package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class PressCalibrationValue implements Serializable {
    private static final long serialVersionUID = 1;
    public int stress_val;
    public int threshold;

    public String toString() {
        return "PressCalibrationValue{stress_val=" + this.stress_val + ", threshold=" + this.threshold + '}';
    }
}