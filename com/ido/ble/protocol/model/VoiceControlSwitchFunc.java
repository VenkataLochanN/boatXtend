package com.ido.ble.protocol.model;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceControlSwitchFunc {
    public static final int SWITCH_CLOSE = 0;
    public static final int SWITCH_OPEN = 1;
    public static final int TYPE_NOT_DISTURB = 3;
    public static final int TYPE_REAL_TIME_HEART_RATE = 1;
    public static final int TYPE_WRIST_BRIGHT_SCREEN = 2;
    public int open_close;
    public int type;

    public String toString() {
        return "VoiceControlSwitchFunc{open_close=" + this.open_close + ", type=" + this.type + '}';
    }
}