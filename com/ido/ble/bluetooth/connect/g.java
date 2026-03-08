package com.ido.ble.bluetooth.connect;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.common.m;
import com.ido.ble.logs.LogTool;
import com.ido.life.data.Constant;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f4046a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4047b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4048c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ConnectCallBack.ICallBack f4049d = new a();

    class a implements ConnectCallBack.ICallBack {

        /* JADX INFO: renamed from: com.ido.ble.bluetooth.connect.g$a$a, reason: collision with other inner class name */
        class C0053a implements m.b {
            C0053a() {
            }

            @Override // com.ido.ble.common.m.b
            public void onTimeOut() {
                g.this.f4046a.a();
            }
        }

        a() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[DisconnectTask] disconnect success");
            com.ido.ble.common.m.a(g.this.f4048c);
            g.this.b();
            com.ido.ble.common.m.a(new C0053a(), 1000L);
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

    class b implements m.b {
        b() {
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[DisconnectTask] onTimeOut");
            g.this.b();
            g.this.f4046a.a();
        }
    }

    public interface c {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[DisconnectTask] finished");
        c();
    }

    private void c() {
        this.f4047b = false;
        com.ido.ble.callback.b.K().b(this.f4049d);
    }

    private void d() {
        this.f4048c = com.ido.ble.common.m.a(new b(), Constant.Delay.WORLD_TIME);
    }

    public void a() {
        if (this.f4047b) {
            LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[DisconnectTask] stop");
            com.ido.ble.common.m.a(this.f4048c);
            c();
        }
    }

    public void a(c cVar) {
        if (this.f4047b) {
            LogTool.b(com.ido.ble.bluetooth.e.b.f4128a, "[DisconnectTask] is in doing state, ignore this action");
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "[DisconnectTask] start");
        this.f4046a = cVar;
        this.f4047b = true;
        com.ido.ble.callback.b.K().a(this.f4049d);
        d();
        com.ido.ble.bluetooth.a.b();
    }
}