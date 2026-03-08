package com.ido.ble.protocol.handler;

import com.ido.ble.callback.RebootCallback;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
final class q {
    q() {
    }

    public static void a(int i, int i2, int i3) {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [handle Device reboot] error = " + i2);
        RebootCallback.a(Boolean.valueOf(i2 == 0));
    }

    public static void a(int i, byte[] bArr, int i2) {
    }

    public static boolean a(int i) {
        return i == 403;
    }
}