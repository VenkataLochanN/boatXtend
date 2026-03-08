package com.ido.ble.firmware.log;

import com.ido.ble.logs.LogTool;
import com.realsil.sdk.bbpro.core.protocol.params.Parameters;

/* JADX INFO: loaded from: classes2.dex */
class e {
    e() {
    }

    static void a() {
        LogTool.d("DEVICE_REBOOT_LOG", "clearLog...");
        byte[] bArr = new byte[20];
        bArr[0] = Parameters.RWS_CHANNEL_1;
        bArr[1] = 7;
        com.ido.ble.bluetooth.a.b(bArr);
    }

    static boolean a(byte[] bArr) {
        return bArr[2] == 85;
    }

    static void b() {
        LogTool.d("DEVICE_REBOOT_LOG", "getLog...");
        byte[] bArr = new byte[20];
        bArr[0] = Parameters.RWS_CHANNEL_1;
        bArr[1] = 6;
        com.ido.ble.bluetooth.a.b(bArr);
    }

    static boolean b(byte[] bArr) {
        return bArr[0] == 33 && bArr[1] == 7;
    }

    static void c() {
        LogTool.d("DEVICE_REBOOT_LOG", "openLog...");
        byte[] bArr = new byte[20];
        bArr[0] = -14;
        bArr[1] = -10;
        com.ido.ble.bluetooth.a.b(bArr);
    }

    static boolean c(byte[] bArr) {
        return bArr[0] == 33 && bArr[1] == 6;
    }

    static boolean d(byte[] bArr) {
        return bArr[0] == -14 && bArr[1] == -10;
    }
}