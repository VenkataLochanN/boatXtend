package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class NoticeReminderSwitchStatus implements Serializable {
    public static final int INVALID = 0;
    public static final int NOTIFY_88 = 136;
    public static final int SWITCH_OFF = 170;
    public static final int SWITCH_ON = 85;
    private static final long serialVersionUID = 1;
    public int call_switch;
    public int notify_switch;

    public String toString() {
        return "NoticeReminderSwitchStatus{notify_switch=" + this.notify_switch + ", call_switch=" + this.call_switch + '}';
    }
}