package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceExchangeDataStartAppReplyData implements Serializable {
    public static final int CODE_FAILED_APP_ALERDY_IN_SOPRT_MODE = 1;
    public static final int CODE_SUCCESS = 0;
    private static final long serialVersionUID = 1;
    public int ret_code;

    public String toString() {
        return "DeviceExchangeDataStartAppReplyData{ret_code=" + this.ret_code + '}';
    }
}