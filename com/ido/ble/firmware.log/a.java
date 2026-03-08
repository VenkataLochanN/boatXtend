package com.ido.ble.firmware.log;

import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.BasicInfo;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static a f4521b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4522a = false;

    /* JADX INFO: renamed from: com.ido.ble.firmware.log.a$a, reason: collision with other inner class name */
    class C0083a implements ICollectDeviceRebootLogListener {
        C0083a() {
        }

        @Override // com.ido.ble.firmware.log.ICollectDeviceRebootLogListener
        public void onFailed() {
            com.ido.ble.f.a.f.a.c0().e(true);
            LogTool.d("DEVICE_REBOOT_LOG", "[AutoCollectFirmwareLogPresenter] collect reboot log failed, will collect next.");
        }

        @Override // com.ido.ble.firmware.log.ICollectDeviceRebootLogListener
        public void onStart() {
            a.this.f4522a = false;
            com.ido.ble.f.a.f.a.c0().e(true);
        }

        @Override // com.ido.ble.firmware.log.ICollectDeviceRebootLogListener
        public void onSuccess(String str) {
            com.ido.ble.f.a.f.a.c0().e(false);
            LogTool.d("DEVICE_REBOOT_LOG", "[AutoCollectFirmwareLogPresenter] collect reboot log success.");
        }
    }

    private a() {
    }

    public static a c() {
        if (f4521b == null) {
            f4521b = new a();
        }
        return f4521b;
    }

    private boolean d() {
        BasicInfo basicInfoH = com.ido.ble.f.a.f.a.c0().h();
        if (basicInfoH == null || basicInfoH.deivceId != 301) {
            return this.f4522a || com.ido.ble.f.a.f.a.c0().b0();
        }
        LogTool.b("DEVICE_REBOOT_LOG", "[CollectDeviceRebootLogPresenter] did = 301");
        return false;
    }

    public void a() {
        if (!d()) {
            LogTool.d("DEVICE_REBOOT_LOG", "[AutoCollectFirmwareLogPresenter] is not need to collect reboot log.");
            return;
        }
        LogTool.d("DEVICE_REBOOT_LOG", "[AutoCollectFirmwareLogPresenter] collect log...,[reboot=" + this.f4522a + ",isNeed=" + com.ido.ble.f.a.f.a.c0().b0() + "]");
        d.a(true, new C0083a());
    }

    public void a(boolean z) {
        LogTool.d("DEVICE_REBOOT_LOG", "[AutoCollectFirmwareLogPresenter] set reboot = " + z);
        this.f4522a = z;
        if (this.f4522a) {
            com.ido.ble.f.a.f.a.c0().e(true);
        }
    }

    public boolean b() {
        return this.f4522a;
    }
}