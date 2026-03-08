package com.ido.ble.bluetooth.e;

import android.content.Context;
import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class c extends com.ido.ble.common.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f4129d = "bind_info_";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4130e = "bind_device_address";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f4131f = "is_bind";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f4132g = "bind_auth";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f4133h = "encrypted_auth";
    private static final String i = "default";
    private static c j;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4134c;

    private c() {
    }

    public static c g() {
        if (j == null) {
            BLEDevice bLEDeviceC = com.ido.ble.f.a.f.b.e().c();
            j = new c();
            if (bLEDeviceC == null || TextUtils.isEmpty(bLEDeviceC.mDeviceAddress)) {
                j.a(com.ido.ble.common.e.a(), i);
            } else {
                j.a(com.ido.ble.common.e.a(), bLEDeviceC.mDeviceAddress);
            }
            LogTool.d("DeviceSharedPreferences", "sp_name = " + j.f4134c);
        }
        return j;
    }

    public static c g(String str) {
        c cVar = new c();
        cVar.a(com.ido.ble.common.e.a(), str);
        LogTool.d("DeviceSharedPreferences", "[createInstanceWithMacAddress] sp_name = " + cVar.f4134c);
        return cVar;
    }

    @Override // com.ido.ble.common.d
    public void a(Context context, String str) {
        this.f4134c = f4129d + str;
        super.a(context, this.f4134c);
    }

    public void a(boolean z) {
        LogTool.d("DeviceSharedPreferences", "setIsBind" + z + ",sp_name = " + j.f4134c);
        b(f4131f, z);
    }

    public void b() {
        LogTool.d("DeviceSharedPreferences", "clearDataIfUnbind" + j.f4134c);
        b(f4131f);
        b(f4132g);
        b(f4130e);
    }

    public String c() {
        return a(f4132g, "");
    }

    public void c(String str) {
        LogTool.d("DeviceSharedPreferences", "setBindAuth" + str + ",sp_name = " + j.f4134c);
        b(f4132g, str);
    }

    public String d() {
        return a(f4130e, "");
    }

    public void d(String str) {
        b(f4130e, str);
    }

    public String e() {
        LogTool.d("DeviceSharedPreferences", "getEncryptedAuth,sp_name = " + j.f4134c);
        return a(f4133h, "");
    }

    public void e(String str) {
        LogTool.d("DeviceSharedPreferences", "setEncryptedAuth" + str + ",sp_name = " + j.f4134c);
        b(f4133h, str);
    }

    public void f(String str) {
        j.a(com.ido.ble.common.e.a(), str);
        LogTool.d("DeviceSharedPreferences", "switchToDevice = " + j.f4134c);
    }

    public boolean f() {
        return a(f4131f, false);
    }
}