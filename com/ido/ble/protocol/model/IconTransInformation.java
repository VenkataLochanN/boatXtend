package com.ido.ble.protocol.model;

/* JADX INFO: loaded from: classes2.dex */
public class IconTransInformation {
    public static final int END_TRAN = 16;
    public static final int START_TRAN = 0;
    public int icon_num;
    public int states;

    public String toString() {
        return "IconTransInformation{states=" + this.states + ", icon_num=" + this.icon_num + '}';
    }
}