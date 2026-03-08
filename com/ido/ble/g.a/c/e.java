package com.ido.ble.g.a.c;

import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.ScanCallBack;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static e f4565f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4566a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4567b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ArrayList<BLEDevice> f4568c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ScanCallBack.ICallBack f4569d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ConnectCallBack.ICallBack f4570e = new b();

    class a implements ScanCallBack.ICallBack {
        a() {
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onFindDevice(BLEDevice bLEDevice) {
            e.this.f4568c.add(bLEDevice);
            Collections.sort(e.this.f4568c);
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onScanFinished() {
            if (e.this.f4568c.size() == 0) {
                d.e();
            }
            e.this.f4566a = true;
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onStart() {
            e.this.f4568c.clear();
            if (e.this.f4566a) {
                d.f();
            }
            e.this.f4566a = false;
            e.this.f4567b = true;
        }
    }

    class b implements ConnectCallBack.ICallBack {
        b() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
            if (e.this.f4567b) {
                d.g();
                e.this.f4567b = false;
            }
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
        }
    }

    private e() {
    }

    private void a() {
        com.ido.ble.callback.b.K().b(this.f4569d);
        com.ido.ble.callback.b.K().b(this.f4570e);
    }

    public static e b() {
        if (f4565f == null) {
            f4565f = new e();
        }
        return f4565f;
    }

    private void c() {
        com.ido.ble.callback.b.K().a(this.f4569d);
        com.ido.ble.callback.b.K().a(this.f4570e);
    }

    public void a(boolean z) {
        if (z) {
            c();
        } else {
            a();
        }
    }
}