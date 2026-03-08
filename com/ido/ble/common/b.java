package com.ido.ble.common;

import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public static boolean a(CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
            if (copyOnWriteArrayList.get(i).contains("Rego")) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(byte[] bArr, byte b2) {
        for (byte b3 : bArr) {
            if (b3 == b2) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}