package com.ido.ble.protocol.handler;

import com.ido.ble.callback.UnbindCallBack;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class v {
    static void a() {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] unbind ok!");
        com.ido.ble.bluetooth.b.c();
        UnbindCallBack.b();
    }

    private static void a(int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i)) {
            a();
            return;
        }
        a("error code = " + i);
    }

    public static void a(int i, int i2, int i3) {
        if (i == 201) {
            a(i2);
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
    }

    private static void a(String str) {
        LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.f4634b + str);
        com.ido.ble.event.stat.one.c.j(str);
        UnbindCallBack.a();
    }

    static boolean b(int i) {
        return i == 201;
    }
}