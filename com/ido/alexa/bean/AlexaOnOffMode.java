package com.ido.alexa.bean;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaOnOffMode {
    public static final int STATUS_OFF = 85;
    public static final int STATUS_ON = 170;
    private int on_off;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getOn_off() {
        return this.on_off;
    }

    public void setOn_off(int i) {
        this.on_off = i;
    }

    public String toString() {
        return "AlexaOnOffMode{type=" + this.type + ", on_off=" + this.on_off + '}';
    }
}