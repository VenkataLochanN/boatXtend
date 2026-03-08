package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class IncomingCallInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public String name;
    public String phoneNumber;

    public String toString() {
        return "IncomingCallInfo{name='" + this.name + "', phoneNumber='" + this.phoneNumber + "'}";
    }
}