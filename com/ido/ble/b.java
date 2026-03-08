package com.ido.ble;

import android.app.Application;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.logs.LogTool;
import com.realsil.sdk.core.RtkConfigure;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.dfu.RtkDfu;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Application f3961a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f3962b;

    static {
        System.loadLibrary("VeryFitMulti");
        f3962b = false;
    }

    static void a() {
        LogTool.d();
        com.ido.ble.bluetooth.a.b();
        com.ido.ble.bluetooth.c.c(f3961a);
    }

    static void a(Application application) {
        if (application == null) {
            throw new RuntimeException("application can not be null !");
        }
        f3961a = application;
    }

    private static void a(InitParam initParam) {
        CustomConfig.getConfig().setEnableLog(initParam.isEnableLog).setIsSaveDeviceDataToDB(initParam.isSaveDeviceDataToDB).setEncryptedDBData(initParam.isEncryptedDBData).setEncryptedSPData(initParam.isEncryptedSPData).setLogSavePath(initParam.log_save_path).setLogSaveDays(initParam.log_save_days).setDatabaseName(initParam.databaseName);
        com.ido.ble.i.a.a.b(initParam.isNeedSoLibAutoSyncConfigIfReboot);
    }

    public static Application b() {
        return f3961a;
    }

    static void b(InitParam initParam) {
        a(initParam);
        com.ido.ble.f.a.f.c.c.a();
        d();
        LogTool.i();
        if (!f3962b) {
            LogTool.d("SDK", "solib init ok.");
            d.a(initParam);
            f3962b = true;
        }
        e();
        com.ido.ble.event.stat.one.c.d();
        LogTool.d("SDK", "ido-ble-sdk init ok, version is 2.65.89,2022/0416/10:12");
        LogTool.d("SDK", "initParam " + initParam.toString());
    }

    static void c() {
        b(new InitParam());
    }

    private static void d() {
        RtkCore.initialize(f3961a.getApplicationContext(), new RtkConfigure.Builder().debugEnabled(true).logTag("RTK").build());
        RtkDfu.initialize(f3961a.getApplicationContext(), true);
    }

    private static void e() {
        com.ido.ble.bluetooth.c.a(f3961a);
    }
}