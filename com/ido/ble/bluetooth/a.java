package com.ido.ble.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.connect.g;
import com.ido.ble.bluetooth.connect.l;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.custom.connect.CustomBytesDataSendManager;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3963a = false;

    /* JADX INFO: renamed from: com.ido.ble.bluetooth.a$a, reason: collision with other inner class name */
    static class RunnableC0048a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BLEDevice f3964a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f3965b;

        RunnableC0048a(BLEDevice bLEDevice, long j) {
            this.f3964a = bLEDevice;
            this.f3965b = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.f.a.f.a.c0().e(this.f3964a.mDeviceAddress);
            com.ido.ble.bluetooth.e.c.g().f(this.f3964a.mDeviceAddress);
            if (this.f3965b > 0) {
                com.ido.ble.bluetooth.connect.e.n().a(this.f3964a, this.f3965b);
            } else {
                com.ido.ble.bluetooth.connect.e.n().b(this.f3964a);
            }
        }
    }

    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BLEDevice f3966a;

        b(BLEDevice bLEDevice) {
            this.f3966a = bLEDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.f.a.f.a.c0().e(this.f3966a.mDeviceAddress);
            com.ido.ble.bluetooth.e.c.g().f(this.f3966a.mDeviceAddress);
            com.ido.ble.bluetooth.connect.e.n().a(this.f3966a);
        }
    }

    static class c implements g.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f3967a;

        c(String str) {
            this.f3967a = str;
        }

        @Override // com.ido.ble.bluetooth.connect.g.c
        public void a() {
            a.e(this.f3967a);
        }
    }

    static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f3968a;

        d(String str) {
            this.f3968a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.bluetooth.connect.e.n().a(this.f3968a);
        }
    }

    static class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ido.ble.bluetooth.connect.e.n().g();
        }
    }

    static class f implements g.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f3969a;

        f(Runnable runnable) {
            this.f3969a = runnable;
        }

        @Override // com.ido.ble.bluetooth.connect.g.c
        public void a() {
            boolean unused = a.f3963a = false;
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[disconnect] finished.");
            this.f3969a.run();
        }
    }

    public static void a() {
        if (!h()) {
            e(d());
        } else {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[DeviceManager] autoConnect. in connect state, ignore");
            ConnectCallBack.c(d());
        }
    }

    public static void a(long j) {
        l.g().a(j);
    }

    public static void a(long j, String str) {
        l.g().a(j, str);
    }

    public static void a(BLEDevice bLEDevice) {
        com.ido.ble.common.e.a(new b(bLEDevice));
    }

    public static void a(BLEDevice bLEDevice, long j) {
        if (e().equals(bLEDevice.mDeviceAddress)) {
            if (h()) {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[DeviceManager] straightConnect. in connect state, ignore");
                ConnectCallBack.c(bLEDevice.mDeviceAddress);
                return;
            } else if (i()) {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[DeviceManager] straightConnect. in connecting state, ignore");
                ConnectCallBack.d(e());
                return;
            }
        }
        com.ido.ble.common.e.a(new RunnableC0048a(bLEDevice, j));
    }

    public static void a(Runnable runnable) {
        if (f3963a) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[disconnect] isDisconnecting = true.");
        } else {
            f3963a = true;
            new g().a(new f(runnable));
        }
    }

    public static void a(String str, String str2) {
        String[] strArrSplit = str.split(str2);
        byte[] bArr = new byte[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            bArr[i] = (byte) Integer.parseInt(strArrSplit[i], 16);
        }
        b(bArr);
    }

    public static void a(byte[] bArr) {
        if (CustomConfig.getConfig().isCustomManageConnection()) {
            CustomBytesDataSendManager.getManager().addCmdData(bArr, false);
        } else {
            com.ido.ble.bluetooth.connect.e.n().a(bArr);
        }
    }

    public static void b() {
        com.ido.ble.common.e.a(new e());
    }

    public static void b(BLEDevice bLEDevice) {
        a(bLEDevice, 0L);
    }

    public static void b(String str) {
        com.ido.ble.f.a.f.a.c0().e(str);
        com.ido.ble.bluetooth.e.c.g().f(str);
        new g().a(new c(str));
    }

    public static void b(boolean z) {
        com.ido.ble.bluetooth.e.c.g().a(z);
    }

    public static void b(byte[] bArr) {
        com.ido.ble.bluetooth.connect.e.n().a(bArr);
    }

    public static String c() {
        return com.ido.ble.bluetooth.e.c.g().c();
    }

    public static boolean c(String str) {
        if (h() && !TextUtils.isEmpty(str)) {
            return str.equals(e());
        }
        return false;
    }

    public static String d() {
        return com.ido.ble.bluetooth.e.c.g().d();
    }

    public static void d(String str) {
        com.ido.ble.bluetooth.e.c.g(str).b();
    }

    public static String e() {
        return com.ido.ble.bluetooth.connect.e.n().b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str) {
        com.ido.ble.f.a.f.a.c0().e(str);
        com.ido.ble.bluetooth.e.c.g().f(str);
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[DeviceManager] autoConnect.");
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[autoConnect()] phone bluetooth switch is closed.");
            ConnectCallBack.a(ConnectFailedReason.BLUETOOTH_SWITCH_CLOSED, str);
        } else if (!g()) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[autoConnect()] failed, is not bind!");
            ConnectCallBack.a(ConnectFailedReason.NOT_IN_BIND_STATUS, str);
        } else if (TextUtils.isEmpty(str)) {
            ConnectCallBack.a(ConnectFailedReason.MAC_ADDRESS_INVALID, str);
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[autoConnect()] failed, mac address is empty");
        } else {
            u.b(1);
            com.ido.ble.common.e.a(new d(str));
        }
    }

    public static String f() {
        return com.ido.ble.bluetooth.e.c.g().e();
    }

    public static void f(String str) {
        if (e().equals(str)) {
            if (h()) {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[DeviceManager] scanAndConnect. in connect state, ignore");
                ConnectCallBack.c(str);
                return;
            } else if (i()) {
                LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[DeviceManager] scanAndConnect. in connecting state, ignore");
                ConnectCallBack.d(e());
                return;
            }
        }
        com.ido.ble.e.b.c().a(str);
    }

    public static void g(String str) {
        com.ido.ble.bluetooth.e.c.g().c(str);
    }

    public static boolean g() {
        return com.ido.ble.bluetooth.e.c.g().f();
    }

    public static void h(String str) {
        com.ido.ble.bluetooth.e.c.g().d(str);
    }

    public static boolean h() {
        return CustomConfig.getConfig().isCustomManageConnection() ? CustomConfig.getConfig().getIEnableNotifyCallback().isConnectedAndReady() : com.ido.ble.bluetooth.connect.e.n().isConnectedAndReady();
    }

    public static void i(String str) {
        com.ido.ble.bluetooth.e.c.g().e(str);
    }

    public static boolean i() {
        return com.ido.ble.bluetooth.connect.e.n().a();
    }

    public static void j() {
        com.ido.ble.bluetooth.e.c.g().f(e());
        b(true);
        h(e());
    }

    public static void j(String str) {
        a(-1L, str);
    }

    public static void k() {
    }

    public static void l() {
        com.ido.ble.bluetooth.e.c.g().b();
    }

    public static void m() {
        a(-1L);
    }

    public static void n() {
        l.g().d();
    }
}