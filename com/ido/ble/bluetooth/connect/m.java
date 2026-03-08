package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.logs.LogTool;
import com.realsil.sdk.dfu.DfuConstants;

/* JADX INFO: loaded from: classes2.dex */
class m {
    private static final int j = 30000;
    private static m k;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4074c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4075d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f4076e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private b f4078g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4072a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4073b = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4077f = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Handler f4079h = new Handler(Looper.getMainLooper());
    private ScanCallBack.ICallBack i = new a();

    class a implements ScanCallBack.ICallBack {

        /* JADX INFO: renamed from: com.ido.ble.bluetooth.connect.m$a$a, reason: collision with other inner class name */
        class RunnableC0054a implements Runnable {
            RunnableC0054a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                m.this.h();
            }
        }

        a() {
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onFindDevice(BLEDevice bLEDevice) {
            String str;
            String str2;
            if (m.this.c()) {
                if (m.this.f4074c.equals(bLEDevice.mDeviceAddress)) {
                    str = com.ido.ble.bluetooth.e.b.f4128a;
                    str2 = "[ScanTargetDeviceTask] has find target device, start to connect";
                } else {
                    if (!com.ido.ble.bluetooth.e.d.a(m.this.f4075d, m.this.f4076e, bLEDevice)) {
                        return;
                    }
                    str = com.ido.ble.bluetooth.e.b.f4128a;
                    str2 = "[ScanTargetDeviceTask] has find target device(mac + 1)";
                }
                LogTool.d(str, str2);
                m.this.f4072a = true;
                l.g().d();
                m.this.f4078g.a(bLEDevice);
            }
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onScanFinished() {
            if (m.this.c()) {
                if (m.this.f4072a) {
                    m.this.g();
                    return;
                }
                m.j(m.this);
                int i = m.this.f4077f < 5 ? 10000 : m.this.f4077f < 30 ? DfuConstants.MAX_CALLBACK_LOCK_WAIT_TIME : 45000;
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] not find target device");
                m.this.f4078g.a();
                if (!m.this.d() && m.this.f4078g.a(m.this.f4074c)) {
                    m.this.g();
                    return;
                }
                LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] will retry after " + i + "ms, retry times = " + m.this.f4077f);
                m.this.f4079h.postDelayed(new RunnableC0054a(), (long) i);
            }
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onStart() {
        }
    }

    public interface b {
        void a();

        void a(BLEDevice bLEDevice);

        boolean a(String str);

        void b();

        void c();
    }

    private m(String str, b bVar) {
        this.f4074c = "";
        this.f4075d = "";
        this.f4076e = "";
        this.f4074c = str;
        this.f4075d = com.ido.ble.bluetooth.e.d.a(str);
        this.f4076e = com.ido.ble.bluetooth.e.d.b(str);
        this.f4078g = bVar;
    }

    public static void a(String str, b bVar) {
        f();
        k = new m(str, bVar);
        k.e();
    }

    public static boolean a() {
        m mVar = k;
        if (mVar == null) {
            return false;
        }
        mVar.b();
        return true;
    }

    private void b() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] cancelDelayTimer. ");
        this.f4079h.removeCallbacksAndMessages(null);
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        if (this.f4073b) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] check not allowed, mIsStopTask = true.");
        } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] check not allowed, phone bluetooth switch is closed.");
            this.f4078g.b();
        } else {
            if (!com.ido.ble.bluetooth.a.h()) {
                return true;
            }
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] check not allowed, isConnected = true.");
            this.f4078g.c();
        }
        g();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        int i = this.f4077f;
        if (i != 0 && i % 2 == 0) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] isNeedScan1 = false");
            return false;
        }
        PowerManager powerManager = (PowerManager) com.ido.ble.b.b().getSystemService("power");
        if (powerManager == null) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] isNeedScan2 = true");
            return true;
        }
        boolean zIsInteractive = Build.VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] isNeedScan3 = " + zIsInteractive);
        return zIsInteractive;
    }

    private void e() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] startTask()");
        l.g().d();
        if (!d()) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] startTask():isNeedScan = false");
            if (this.f4078g.a(this.f4074c)) {
                g();
                return;
            }
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] startTask(): try connect direct failed, will start scan task.");
        }
        com.ido.ble.callback.b.K().b(this.i);
        com.ido.ble.callback.b.K().a(this.i);
        l.g().a(30000L);
    }

    public static void f() {
        m mVar = k;
        if (mVar != null) {
            mVar.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.f4073b) {
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] stopTask()");
        this.f4073b = true;
        this.f4079h.removeCallbacksAndMessages(null);
        l.g().d();
        com.ido.ble.callback.b.K().b(this.i);
        this.f4077f = 0;
        k = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (c()) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[ScanTargetDeviceTask] start again ...");
            l.g().a(30000L);
        }
    }

    static /* synthetic */ int j(m mVar) {
        int i = mVar.f4077f;
        mVar.f4077f = i + 1;
        return i;
    }
}