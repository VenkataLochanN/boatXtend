package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class AppExchangeDataPausePara implements Serializable {
    private static final long serialVersionUID = 1;
    public int day;
    public int hour;
    public int minute;
    public int second;

    public String toString() {
        return "AppExchangeDataPausePara{day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + '}';
    }
}