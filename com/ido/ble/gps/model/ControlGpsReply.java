package com.ido.ble.gps.model;

/* JADX INFO: loaded from: classes2.dex */
public class ControlGpsReply {
    public int errorCode;
    public int status;
    public int type;

    public String toString() {
        return "ControlGpsReply{type=" + this.type + ", status=" + this.status + ", errorCode=" + this.errorCode + '}';
    }
}