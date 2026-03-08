package com.ido.ble.bluetooth.bt;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.bt.d;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static a f3970c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f3971d = 5;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3972a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3973b = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: com.ido.ble.bluetooth.bt.a$a, reason: collision with other inner class name */
    class C0049a implements d.b {
        C0049a() {
        }

        @Override // com.ido.ble.bluetooth.bt.d.b
        public void a(String str) {
            a.this.a(str);
        }
    }

    class b implements e {
        b() {
        }

        @Override // com.ido.ble.bluetooth.bt.e
        public void a() {
            LogTool.b(com.ido.ble.logs.a.q, "[BTConnectPresenter] scanBtDevice. not find device");
            a.this.b();
        }

        @Override // com.ido.ble.bluetooth.bt.e
        public void a(BluetoothDevice bluetoothDevice) {
            a.this.a(bluetoothDevice);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] scanBtDevice ok, connect " + bluetoothDevice.getName());
        bluetoothDevice.createBond();
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] getBTMacAddressOK, mac is " + str);
        if (TextUtils.isEmpty(str)) {
            b();
        } else if (!com.ido.ble.bluetooth.e.e.c(str)) {
            b(str);
        } else {
            LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] has paired.");
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] failed.");
        int i = this.f3972a;
        if (i < f3971d) {
            this.f3972a = i + 1;
            LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] retry connect currentTryTimes:" + this.f3972a);
            this.f3973b.postDelayed(new c(), 2000L);
        }
    }

    private void b(String str) {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] scanBtDevice");
        com.ido.ble.bluetooth.bt.b.b().a(str, new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] getBTMacAddress. ");
        d.b().a(new C0049a());
    }

    public static a d() {
        if (f3970c == null) {
            f3970c = new a();
        }
        return f3970c;
    }

    private void e() {
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] sucess.");
    }

    public void a() {
        this.f3972a = 0;
        LogTool.d(com.ido.ble.logs.a.q, "[BTConnectPresenter] connect. currentTryTimes:" + this.f3972a);
        c();
    }
}