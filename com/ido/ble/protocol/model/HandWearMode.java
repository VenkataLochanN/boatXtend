package com.ido.ble.protocol.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class HandWearMode implements Serializable {
    public static int HAND_MODE_LEFT = 0;
    public static int HAND_MODE_RIGHT = 1;
    private static final long serialVersionUID = 1;
    public int hand;

    public String toString() {
        return "HandWearMode{hand=" + this.hand + '}';
    }
}