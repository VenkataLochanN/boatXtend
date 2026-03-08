package com.ido.ble.dfu.e.b;

import android.util.Log;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.common.m;
import com.ido.ble.logs.LogTool;
import com.realsil.sdk.dfu.model.DfuConfig;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f4320e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f4321f = 5;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f4323b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4322a = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4324c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private EnterDfuModeCallback.ICallBack f4325d = new a();

    class a implements EnterDfuModeCallback.ICallBack {
        a() {
        }

        @Override // com.ido.ble.callback.EnterDfuModeCallback.ICallBack
        public void onError(EnterDfuModeCallback.DfuError dfuError) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] error is " + dfuError);
            b.this.a(dfuError);
        }

        @Override // com.ido.ble.callback.EnterDfuModeCallback.ICallBack
        public void onSuccess() {
            b.this.g();
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.dfu.e.b.b$b, reason: collision with other inner class name */
    class C0073b implements m.b {
        C0073b() {
        }

        @Override // com.ido.ble.common.m.b
        public void onTimeOut() {
            Log.e(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] onTimeOut, retry...");
            b.this.d();
        }
    }

    public interface c {
        void a();

        void a(EnterDfuModeCallback.DfuError dfuError);

        void b();

        void onSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(EnterDfuModeCallback.DfuError dfuError) {
        LogTool.b(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] enter dfu mode failed!");
        b();
        this.f4323b.a(dfuError);
    }

    private void b() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] finished!");
        m.a(this.f4324c);
        c();
    }

    private void c() {
        f4320e = false;
        com.ido.ble.callback.b.K().b(this.f4325d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.f4322a > 5) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] out of max retry times.");
            e();
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] restart...");
        this.f4322a++;
        if (com.ido.ble.bluetooth.a.h()) {
            f();
            com.ido.ble.i.a.a.e();
        } else {
            b();
            this.f4323b.b();
        }
    }

    private void e() {
        b();
        this.f4323b.a();
    }

    private void f() {
        this.f4324c = m.a(new C0073b(), DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] enter dfu mode success!");
        b();
        this.f4323b.onSuccess();
    }

    public void a() {
        if (f4320e) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] stop task!");
            c();
        }
    }

    public void a(c cVar) {
        if (f4320e) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] is doing, ignore this action!");
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[EnterDFUModeTask] start...");
        this.f4323b = cVar;
        com.ido.ble.callback.b.K().a(this.f4325d);
        f4320e = true;
        if (com.ido.ble.bluetooth.a.h()) {
            f();
            com.ido.ble.i.a.a.e();
        } else {
            b();
            cVar.b();
        }
    }
}