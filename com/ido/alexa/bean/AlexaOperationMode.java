package com.ido.alexa.bean;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaOperationMode {
    public static final int OFF = 1;
    public static final int ON = 0;
    public static final int OPERATION_TYPE_BRIGHTNESS_LEVEL = 4;
    public static final int OPERATION_TYPE_BRIGHTNESS_LEVEL_OF_PERCENT = 8;
    public static final int TYPE_BRIGHTNESS = 1;
    public static final int TYPE_DND = 2;
    public static final int TYPE_WAKEUP_GESTURE = 3;
    private int cmd;
    private int operation_type;
    private int ui_type;

    public int getCmd() {
        return this.cmd;
    }

    public void setCmd(int i) {
        this.cmd = i;
    }

    public int getUi_type() {
        return this.ui_type;
    }

    public void setUi_type(int i) {
        this.ui_type = i;
    }

    public int getOperation_type() {
        return this.operation_type;
    }

    public void setOperation_type(int i) {
        this.operation_type = i;
    }

    public String toString() {
        return "AlexaOperationMode{ui_type=" + this.ui_type + ", operation_type=" + this.operation_type + ", cmd=" + this.cmd + '}';
    }
}