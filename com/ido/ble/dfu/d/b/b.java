package com.ido.ble.dfu.d.b;

import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.common.m;
import com.ido.ble.logs.LogTool;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f4274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4275b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4276c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ConnectCallBack.ICallBack f4277d = new a();

    class a implements ConnectCallBack.ICallBack {

        /* JADX INFO: renamed from: com.ido.ble.dfu.d.b.b$a$a, reason: collision with other inner class name */
        class C0066a implements m.b {
            C0066a() {
            }

            @Override // com.ido.ble.common.m.b
            public void onTimeOut() {
                b.this.f4274a.a();
            }
        }

        a() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4245a, "[DFUDisconnectTask] onConnectBreak");
            m.a(b.this.f4276c);
            b.this.b();
            m.a(new C0066a(), 1000L);
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
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

    /* JADX INFO: renamed from: com.ido.ble.dfu.d.b.b$b, reason: collision with other inner class name */
    class C0067b implements m.b {
        C0067b() {
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            LogTool.b(com.ido.ble.dfu.a.f4245a, "[DFUDisconnectTask] onTimeOut");
            b.this.b();
            b.this.f4274a.a();
        }
    }

    public interface c {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[DFUDisconnectTask] finished");
        c();
    }

    private void c() {
        this.f4275b = false;
        com.ido.ble.callback.b.K().b(this.f4277d);
    }

    private void d() {
        this.f4276c = m.a(new C0067b(), BootloaderScanner.TIMEOUT);
    }

    public void a() {
        if (this.f4275b) {
            LogTool.d(com.ido.ble.dfu.a.f4245a, "[DFUDisconnectTask] stop");
            m.a(this.f4276c);
            c();
        }
    }

    public void a(c cVar) {
        if (this.f4275b) {
            LogTool.b(com.ido.ble.dfu.a.f4245a, "[DFUDisconnectTask] is in doing state, ignore this action");
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4245a, "[DFUDisconnectTask] start");
        this.f4274a = cVar;
        this.f4275b = true;
        com.ido.ble.callback.b.K().a(this.f4277d);
        d();
        com.ido.ble.bluetooth.a.b();
    }
}