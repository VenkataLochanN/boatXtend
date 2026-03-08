package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenLuminance implements Serializable {
    public int level;
    public int value;

    public ScreenLuminance() {
    }

    public ScreenLuminance(int i, int i2) {
        this.level = i;
        this.value = i2;
    }

    public String toString() {
        return "ScreenLuminance{level=" + this.level + ", value=" + this.value + '}';
    }
}