package com.ido.ble.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.connect.e;
import com.ido.ble.bluetooth.connect.l;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.SupportFunctionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final BroadcastReceiver f4004a = new a();

    static class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                if (intExtra == 10) {
                    LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "bluetooth switch is turn off");
                    e.n().k();
                    l.g().d();
                } else if (intExtra == 12) {
                    LogTool.d(com.ido.ble.bluetooth.e.b.f4128a, "bluetooth switch is turn on");
                    e.n().i();
                }
            }
        }
    }

    static class b implements BindCallBack.ICallBack {
        b() {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onCancel() {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onFailed(BindCallBack.BindFailedError bindFailedError) {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onNeedAuth(int i) {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onReject() {
        }

        @Override // com.ido.ble.callback.BindCallBack.ICallBack
        public void onSuccess() {
            c.a();
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.bluetooth.c$c, reason: collision with other inner class name */
    static class C0050c implements ConnectCallBack.ICallBack {
        C0050c() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            if (com.ido.ble.bluetooth.e.c.g(str).f()) {
                c.a();
            }
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

    public static void a() {
        String str;
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        if (supportFunctionInfoV == null) {
            str = "supportFunctionInfo = null";
        } else {
            if (supportFunctionInfoV.v2_get_bt_addr) {
                com.ido.ble.bluetooth.bt.c.a();
                return;
            }
            str = "v2_get_bt_addr_02_04 is falese";
        }
        LogTool.d(com.ido.ble.logs.a.q, str);
    }

    public static void a(Context context) {
        b(context);
        c();
        b();
    }

    private static void b() {
        com.ido.ble.callback.b.K().a(new C0050c());
    }

    private static void b(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        context.registerReceiver(f4004a, intentFilter);
    }

    private static void c() {
        com.ido.ble.callback.b.K().a(new b());
    }

    public static void c(Context context) {
        context.unregisterReceiver(f4004a);
    }
}