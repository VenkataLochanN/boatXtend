package com.ido.ble.bluetooth;

import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static void a() {
        com.ido.ble.event.stat.one.c.c();
        a.j();
        u.b(1);
        BLEDevice bLEDeviceC = com.ido.ble.f.a.f.b.e().c();
        if (bLEDeviceC != null && !TextUtils.isEmpty(bLEDeviceC.mDeviceAddress)) {
            com.ido.ble.f.a.f.b.e().c(bLEDeviceC.mDeviceAddress);
        }
        u.m();
    }

    private static void a(BLEDevice bLEDevice) {
        BLEDevice bLEDeviceC = com.ido.ble.f.a.f.b.e().c();
        if (bLEDeviceC != null && !bLEDevice.mDeviceAddress.equals(bLEDeviceC.mDeviceAddress)) {
            u.m();
        }
        com.ido.ble.f.a.f.b.e().a(bLEDevice);
    }

    public static void a(String str) {
        com.ido.ble.event.stat.one.c.e();
        a.d(str);
        com.ido.ble.f.a.f.b.e().d(str);
        com.ido.ble.f.a.f.a.f(str).c();
        u.b(0);
        u.C();
        u.m();
    }

    public static void b() {
        com.ido.ble.event.stat.one.c.e();
        a.l();
        BLEDevice bLEDeviceC = com.ido.ble.f.a.f.b.e().c();
        if (bLEDeviceC != null && !TextUtils.isEmpty(bLEDeviceC.mDeviceAddress)) {
            com.ido.ble.f.a.f.b.e().d(bLEDeviceC.mDeviceAddress);
        }
        com.ido.ble.f.a.f.a.c0().c();
        u.b(0);
        u.C();
        u.m();
    }

    public static void b(BLEDevice bLEDevice) {
        if (bLEDevice != null && !TextUtils.isEmpty(bLEDevice.mDeviceAddress) && "tlwbootota".equals(bLEDevice.mDeviceAddress)) {
            LogTool.d(com.ido.ble.logs.a.f4634b, "[onConnectSuccess] dfu mode not switch device");
            return;
        }
        a(bLEDevice);
        c(bLEDevice);
        com.ido.ble.bluetooth.e.c.g().f(bLEDevice.mDeviceAddress);
        com.ido.ble.f.a.f.a.c0().e(bLEDevice.mDeviceAddress);
        if (a.g()) {
            LogTool.d(com.ido.ble.logs.a.f4634b, "[onConnectSuccess] setMode(SYS_MODE_SET_BIND)");
            u.b(1);
        } else {
            LogTool.d(com.ido.ble.logs.a.f4634b, "[onConnectSuccess] setMode(SYS_MODE_SET_NOBIND)");
            u.b(0);
        }
        u.b(com.veryfit.multi.nativeprotocol.b.j5, 1);
    }

    public static void c() {
        com.ido.ble.event.stat.one.c.e();
        a.l();
        LogTool.d(com.ido.ble.logs.a.f4634b, "[onUnbindSuccess] to disconnect.");
        a.b();
        com.ido.ble.f.a.f.a.c0().c();
        BLEDevice bLEDeviceC = com.ido.ble.f.a.f.b.e().c();
        if (bLEDeviceC != null && !TextUtils.isEmpty(bLEDeviceC.mDeviceAddress)) {
            com.ido.ble.f.a.f.b.e().d(bLEDeviceC.mDeviceAddress);
        }
        u.b(0);
        u.C();
        u.m();
    }

    private static void c(BLEDevice bLEDevice) {
        int i;
        int i2;
        if (bLEDevice == null) {
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4640h, "[saveCurrentDeviceInfo] bleDevice=" + bLEDevice.toString());
        BLEDevice bLEDeviceN = com.ido.ble.f.a.f.a.c0().n();
        if (bLEDeviceN == null) {
            LogTool.d(com.ido.ble.logs.a.f4640h, "[saveCurrentDeviceInfo] exist=null");
            com.ido.ble.f.a.f.a.c0().a(bLEDevice);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4640h, "[saveCurrentDeviceInfo] exist=" + bLEDeviceN.toString());
        if (!bLEDevice.mDeviceAddress.equals(bLEDeviceN.mDeviceAddress)) {
            LogTool.b(com.ido.ble.logs.a.f4640h, "[saveCurrentDeviceInfo] macAddress is not equals");
            return;
        }
        boolean z = false;
        if (TextUtils.isEmpty(bLEDeviceN.mDeviceName) && !TextUtils.isEmpty(bLEDevice.mDeviceName)) {
            bLEDeviceN.mDeviceName = bLEDevice.mDeviceName;
            z = true;
        }
        if (!TextUtils.isEmpty(bLEDeviceN.mDeviceName) && !TextUtils.isEmpty(bLEDevice.mDeviceName) && !bLEDeviceN.mDeviceName.equals(bLEDevice.mDeviceName)) {
            LogTool.b(com.ido.ble.logs.a.f4640h, "[saveCurrentDeviceInfo] mDeviceName is not equals");
            bLEDeviceN.mDeviceName = bLEDevice.mDeviceName;
            z = true;
        }
        if (bLEDeviceN.mDeviceId <= 10 && (i2 = bLEDevice.mDeviceId) >= 10) {
            bLEDeviceN.mDeviceId = i2;
            z = true;
        }
        int i3 = bLEDeviceN.type;
        if ((i3 == 0 || i3 == -1) && (i = bLEDevice.type) != 0 && i != -1) {
            bLEDeviceN.type = i;
            z = true;
        }
        if (z) {
            LogTool.d(com.ido.ble.logs.a.f4640h, "[saveCurrentDeviceInfo] update.");
            com.ido.ble.f.a.f.a.c0().a(bLEDeviceN);
        }
    }
}