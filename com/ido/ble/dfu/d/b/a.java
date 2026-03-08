package com.ido.ble.dfu.d.b;

import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f4270a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4271b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ConnectCallBack.ICallBack f4272c = new C0065a();

    /* JADX INFO: renamed from: com.ido.ble.dfu.d.b.a$a, reason: collision with other inner class name */
    class C0065a implements ConnectCallBack.ICallBack {
        C0065a() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            LogTool.b(com.ido.ble.dfu.a.f4245a, "[DFUConnectTask] onConnectBreak");
            a.this.b();
            a.this.f4270a.b();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            LogTool.b(com.ido.ble.dfu.a.f4245a, "[DFUConnectTask] onConnectFailed");
            a.this.b();
            a.this.f4270a.b();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4245a, "[DFUConnectTask] onConnectSuccess");
            a.this.b();
            a.this.f4270a.a();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            LogTool.b(com.ido.ble.dfu.a.f4245a, "[DFUConnectTask] onInDfuMode");
            a.this.b();
            a.this.f4270a.c();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
        }
    }

    public interface b {
        void a();

        void b();

        void c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[DFUConnectTask] finished");
        c();
    }

    private void c() {
        this.f4271b = false;
        com.ido.ble.callback.b.K().b(this.f4272c);
    }

    public void a() {
        if (this.f4271b) {
            LogTool.d(com.ido.ble.dfu.a.f4245a, "[DFUConnectTask] stop");
            c();
        }
    }

    public void a(b bVar, BLEDevice bLEDevice) {
        if (this.f4271b) {
            LogTool.b(com.ido.ble.dfu.a.f4245a, "[DFUConnectTask] is in doing state, ignore this action");
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[DFUConnectTask] start");
        this.f4270a = bVar;
        this.f4271b = true;
        com.ido.ble.callback.b.K().a(this.f4272c);
        com.ido.ble.bluetooth.a.a(bLEDevice);
    }
}