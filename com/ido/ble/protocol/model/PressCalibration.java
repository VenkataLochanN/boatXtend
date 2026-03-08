package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class PressCalibration implements Serializable {
    private static final long serialVersionUID = 1;
    public int status;
    public int stress_score;

    public String toString() {
        return "PressCalibration{stress_score=" + this.stress_score + ", status=" + this.status + '}';
    }
}