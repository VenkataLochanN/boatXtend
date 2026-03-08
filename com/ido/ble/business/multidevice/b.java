package com.ido.ble.business.multidevice;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.UnbindCallBack;
import com.realsil.sdk.dfu.model.DfuConfig;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    static class a implements UnbindCallBack.ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ICommonListener f4143a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f4144b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f4145c;

        a(ICommonListener iCommonListener, String str, Handler handler) {
            this.f4143a = iCommonListener;
            this.f4144b = str;
            this.f4145c = handler;
        }

        @Override // com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onFailed() {
            com.ido.ble.callback.b.K().b(this);
            this.f4143a.onFailed(this.f4144b);
            this.f4145c.removeCallbacksAndMessages(null);
        }

        @Override // com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onSuccess() {
            com.ido.ble.bluetooth.e.c.g().e("");
            com.ido.ble.callback.b.K().b(this);
            this.f4143a.onSuccess(this.f4144b);
            this.f4145c.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.business.multidevice.b$b, reason: collision with other inner class name */
    static class RunnableC0059b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ICommonListener f4146a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f4147b;

        RunnableC0059b(ICommonListener iCommonListener, String str) {
            this.f4146a = iCommonListener;
            this.f4147b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4146a.onFailed(this.f4147b);
        }
    }

    private static void a(String str) {
        com.ido.ble.f.a.f.a.f(str).c();
        com.ido.ble.bluetooth.e.c.g(str).b();
        com.ido.ble.f.a.f.b.e().d(str);
    }

    public static void a(String str, ICommonListener iCommonListener) {
        BLEDevice lastConnectedDeviceInfo = LocalDataManager.getLastConnectedDeviceInfo();
        if (lastConnectedDeviceInfo == null) {
            iCommonListener.onFailed(str);
        } else if (str.equals(lastConnectedDeviceInfo.mDeviceAddress)) {
            b(str, iCommonListener);
        } else {
            a(str);
            iCommonListener.onSuccess(str);
        }
    }

    private static void b(String str, ICommonListener iCommonListener) {
        Handler handler = new Handler(Looper.getMainLooper());
        com.ido.ble.callback.b.K().a(new a(iCommonListener, str, handler));
        com.ido.ble.i.a.a.D0();
        handler.postDelayed(new RunnableC0059b(iCommonListener, str), DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
    }
}