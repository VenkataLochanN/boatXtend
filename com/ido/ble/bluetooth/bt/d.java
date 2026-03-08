package com.ido.ble.bluetooth.bt;

import android.text.TextUtils;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.MacAddressInfo;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static d f3982d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f3983a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3984b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private GetDeviceInfoCallBack.ICallBack f3985c = new a();

    class a extends com.ido.ble.callback.a {
        a() {
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetMacAddress(MacAddressInfo macAddressInfo) {
            if (d.this.f3984b) {
                com.ido.ble.callback.b.K().b(d.this.f3985c);
                if (macAddressInfo != null) {
                    LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] getBtAddress from device is " + macAddressInfo.btAddress);
                    com.ido.ble.f.a.f.a.c0().c(macAddressInfo.btAddress);
                    d.this.f3983a.a(macAddressInfo.btAddress);
                } else {
                    d.this.f3983a.a("");
                }
                d.this.a();
            }
        }
    }

    public interface b {
        void a(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] finished");
        this.f3983a = null;
        this.f3984b = false;
    }

    public static d b() {
        if (f3982d == null) {
            f3982d = new d();
        }
        return f3982d;
    }

    public void a(b bVar) {
        LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] getBtAddress start");
        String strL = com.ido.ble.f.a.f.a.c0().l();
        if (!TextUtils.isEmpty(strL)) {
            LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] getBtAddress from cache is " + strL);
            bVar.a(strL);
            return;
        }
        this.f3984b = true;
        this.f3983a = bVar;
        com.ido.ble.callback.b.K().b(this.f3985c);
        com.ido.ble.callback.b.K().a(this.f3985c);
        LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] getBtAddress from device");
        com.ido.ble.i.a.a.G();
    }
}