package com.realsil.sdk.dfu.image;

/* JADX INFO: loaded from: classes3.dex */
public class SubFileIndicator {
    public static final int INDICATOR_FULL = -1;
    public static final int MASK = 1;

    public static boolean isIndicatorEnabled(int i, int i2) {
        return i == -1 || ((i >> i2) & 1) != 0;
    }
}