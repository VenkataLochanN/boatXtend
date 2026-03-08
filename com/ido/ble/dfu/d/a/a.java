package com.ido.ble.dfu.d.a;

import android.text.TextUtils;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.dfu.BleDFUConfig;
import com.ido.ble.dfu.BleDFUState;

/* JADX INFO: loaded from: classes2.dex */
public class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f4262a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ido.ble.event.stat.one.b f4263b = new com.ido.ble.event.stat.one.b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4264c;

    public a(BleDFUConfig bleDFUConfig) {
        if (bleDFUConfig == null || TextUtils.isEmpty(bleDFUConfig.getDeviceId()) || TextUtils.isEmpty(bleDFUConfig.getMacAddress())) {
            return;
        }
        this.f4264c = bleDFUConfig.getDeviceId() + bleDFUConfig.getMacAddress();
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void a() {
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.NOT_FIND_TARGET_DEVICE + "");
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.NOT_FIND_TARGET_DEVICE + "");
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.NOT_FIND_TARGET_DEVICE);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void a(int i) {
        com.ido.ble.dfu.b.f().b(i);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void a(int i, String str) {
        String str2 = "[" + i + "]" + str;
        this.f4263b.a(str2);
        com.ido.ble.event.stat.one.c.e(str2);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void a(EnterDfuModeCallback.DfuError dfuError) {
        com.ido.ble.dfu.b bVarF;
        BleDFUState.FailReason failReason;
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.ENTER_DFU_MODE_FAILED + ":" + dfuError);
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.ENTER_DFU_MODE_FAILED + ":" + dfuError);
        if (dfuError == EnterDfuModeCallback.DfuError.LOW_BATTERY) {
            bVarF = com.ido.ble.dfu.b.f();
            failReason = BleDFUState.FailReason.DEVICE_IN_LOW_BATTERY;
        } else {
            bVarF = com.ido.ble.dfu.b.f();
            failReason = BleDFUState.FailReason.ENTER_DFU_MODE_FAILED;
        }
        bVarF.a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void b() {
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.DEVICE_NOT_REBOOT + "");
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.DEVICE_NOT_REBOOT + "");
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.DEVICE_NOT_REBOOT);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void c() {
        com.ido.ble.event.stat.one.c.f(this.f4263b.b());
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.OTHER);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void d() {
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.ENTER_DFU_MODE_FAILED + ":timeout");
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.ENTER_DFU_MODE_FAILED + ":timeout");
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.ENTER_DFU_MODE_FAILED);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void e() {
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.OPERATION_FAILED + "");
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.OPERATION_FAILED + "");
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.OPERATION_FAILED);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void f() {
        com.ido.ble.event.stat.one.c.f(this.f4263b.b());
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.OTHER);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void g() {
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.OPERATION_NOT_PERMITTED + "");
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.OPERATION_NOT_PERMITTED + "");
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.OPERATION_NOT_PERMITTED);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void h() {
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.PHONE_BLUETOOTH_ERROR + "");
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.PHONE_BLUETOOTH_ERROR + "");
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.PHONE_BLUETOOTH_ERROR);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void i() {
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.CONFIG_PARAS_ERROR + "");
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.CONFIG_PARAS_ERROR + "");
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.CONFIG_PARAS_ERROR);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void j() {
        com.ido.ble.event.stat.one.c.f(BleDFUState.FailReason.FILE_ERROR + "");
        com.ido.ble.event.stat.one.c.e(BleDFUState.FailReason.FILE_ERROR + "");
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.FILE_ERROR);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onCancel() {
        com.ido.ble.dfu.b.f().a();
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onDeviceInDFUMode() {
        com.ido.ble.dfu.b.f().b();
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onPrepare() {
        this.f4262a = System.currentTimeMillis();
        com.ido.ble.dfu.b.f().c();
        if (TextUtils.isEmpty(this.f4264c)) {
            return;
        }
        com.ido.ble.event.stat.one.c.h(this.f4264c);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onProgress(int i) {
        com.ido.ble.dfu.b.f().a(i);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onSuccess() {
        com.ido.ble.event.stat.one.c.a((System.currentTimeMillis() - this.f4262a) / 1000, this.f4263b.b());
        this.f4262a = 0L;
        if (!TextUtils.isEmpty(this.f4264c)) {
            com.ido.ble.event.stat.one.c.g(this.f4264c);
        }
        com.ido.ble.dfu.b.f().d();
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onSuccessAndNeedToPromptUser() {
        com.ido.ble.event.stat.one.c.a((System.currentTimeMillis() - this.f4262a) / 1000, this.f4263b.b());
        this.f4262a = 0L;
        if (!TextUtils.isEmpty(this.f4264c)) {
            com.ido.ble.event.stat.one.c.g(this.f4264c);
        }
        com.ido.ble.dfu.b.f().e();
    }
}