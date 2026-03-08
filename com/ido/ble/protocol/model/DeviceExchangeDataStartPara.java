package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceExchangeDataStartPara extends SportType implements Serializable {
    private static final long serialVersionUID = 1;
    public int day;
    public int hour;
    public int minute;
    public int second;
    public int type;

    public String toString() {
        return "DeviceExchangeDataStartPara{day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", type=" + this.type + '}';
    }
}