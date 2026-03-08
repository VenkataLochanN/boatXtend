package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.ido.ble.bluetooth.connect.q.b;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.BindAuthDeviceReply;
import com.ido.ble.protocol.model.BindDeviceReply;

/* JADX INFO: loaded from: classes2.dex */
final class c {

    static class a implements b.InterfaceC0057b {
        a() {
        }

        @Override // com.ido.ble.bluetooth.connect.q.b.InterfaceC0057b
        public void onFailed() {
            c.d();
        }

        @Override // com.ido.ble.bluetooth.connect.q.b.InterfaceC0057b
        public void onSuccess() {
            c.e();
        }
    }

    c() {
    }

    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 200) {
            b(bArr);
        } else if (i == 202) {
            a(bArr);
        } else if (i == 203) {
            c(bArr);
        }
    }

    private static void a(BindDeviceReply bindDeviceReply) {
        com.ido.ble.bluetooth.connect.q.b.b().a(bindDeviceReply.encrypted_data, new a());
    }

    private static void a(String str) {
        LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.f4634b + str);
        com.ido.ble.event.stat.one.c.a(str);
        BindCallBack.b();
    }

    private static void a(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            a("return bind auth jsonData is null ,bind failed!");
            return;
        }
        BindAuthDeviceReply bindAuthDeviceReply = (BindAuthDeviceReply) com.ido.ble.common.k.c(strD, BindAuthDeviceReply.class);
        if (com.ido.ble.bluetooth.a.g()) {
            if (bindAuthDeviceReply.auth_ret_code == 0) {
                LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] auto set bind auth success!");
                return;
            } else {
                LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] auto set bind auth failed!");
                return;
            }
        }
        if (bindAuthDeviceReply.auth_ret_code == 0) {
            e();
            return;
        }
        a("auth return code = " + bindAuthDeviceReply.auth_ret_code + " ,bind failed!");
    }

    static boolean a(int i) {
        return i == 200 || i == 202 || i == 203;
    }

    private static void b(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            a("return bind jsonData is null ,bind failed!");
            return;
        }
        BindDeviceReply bindDeviceReply = (BindDeviceReply) com.ido.ble.common.k.c(strD, BindDeviceReply.class);
        int i = bindDeviceReply.bind_ret_code;
        if (i != 0) {
            if (i == 2) {
                c();
                return;
            }
            a("bind return code = " + bindDeviceReply.bind_ret_code + " ,bind failed!");
            return;
        }
        boolean z = bindDeviceReply.auth_length > 0;
        int[] iArr = bindDeviceReply.encrypted_data;
        boolean z2 = iArr != null && iArr.length >= 12;
        if (z2) {
            z = false;
        }
        if (z) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] need bind auth code!, length =" + bindDeviceReply.auth_length);
            BindCallBack.a(bindDeviceReply.auth_length);
            return;
        }
        if (!z2) {
            e();
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] need encrypted auth code!");
            a(bindDeviceReply);
        }
    }

    private static void c() {
        LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] device already in bind state");
        BindCallBack.a(BindCallBack.BindFailedError.ERROR_DEVICE_ALREADY_IN_BIND_STATE);
    }

    private static void c(byte[] bArr) {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] bind reject!");
        BindCallBack.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encrypted failed");
        BindCallBack.a(BindCallBack.BindFailedError.ERROR_ENCRYPTED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] bind ok!");
        com.ido.ble.bluetooth.b.a();
        BindCallBack.c();
    }
}