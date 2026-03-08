package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class BindAuthDeviceReply implements Serializable {
    public static final int SUCCESS = 0;
    private static final long serialVersionUID = 1;
    public int auth_ret_code;

    public String toString() {
        return "BindAuthDeviceReply{auth_ret_code=" + this.auth_ret_code + '}';
    }
}