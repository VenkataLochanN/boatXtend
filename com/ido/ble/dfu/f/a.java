package com.ido.ble.dfu.f;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.dfu.BleDFUConfig;
import com.ido.ble.dfu.d.a.b;
import com.ido.ble.dfu.d.b.b;
import com.ido.ble.dfu.rtk.auth.RtkAuthTask;
import com.ido.ble.logs.LogTool;
import com.ido.common.utils.FileDialDefinedUtil;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.image.BinFactory;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.realsil.sdk.dfu.utils.DfuHelper;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final int m = 6;
    private static a n;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BleDFUConfig f4348b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.ido.ble.dfu.d.a.c f4352f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private DfuHelper f4354h;
    private RtkAuthTask i;
    private com.ido.ble.dfu.d.a.d j;
    private com.ido.ble.dfu.d.b.b l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4347a = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4349c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4350d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4351e = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Handler f4353g = new Handler(Looper.getMainLooper());
    private DfuAdapter.DfuHelperCallback k = new C0075a();

    /* JADX INFO: renamed from: com.ido.ble.dfu.f.a$a, reason: collision with other inner class name */
    class C0075a extends DfuAdapter.DfuHelperCallback {
        C0075a() {
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onError(int i, int i2) {
            super.onError(i, i2);
            a.this.j.b();
            LogTool.b(com.ido.ble.dfu.a.f4247c, "onError, type = " + i + ",code = " + i2);
            a.this.l();
            a.this.f4352f.a(i2, "" + i);
            a.this.f4352f.c();
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onProcessStateChanged(int i, Throughput throughput) {
            super.onProcessStateChanged(i, throughput);
            LogTool.d(com.ido.ble.dfu.a.f4247c, "onProcessStateChanged, state = " + i);
            a.this.j.a();
            if (i == 258) {
                a.this.j.b();
                a.this.m();
                a.this.f4352f.onSuccess();
            }
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
            super.onProgressChanged(dfuProgressInfo);
            a.this.j.a();
            if (a.this.f4350d == dfuProgressInfo.getProgress() || dfuProgressInfo.getProgress() == 0) {
                return;
            }
            LogTool.d(com.ido.ble.dfu.a.f4247c, "onProgressChanged, progress = " + dfuProgressInfo.getProgress());
            a.this.f4352f.onProgress(dfuProgressInfo.getProgress());
            a.this.f4350d = dfuProgressInfo.getProgress();
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onStateChanged(int i) {
            super.onStateChanged(i);
            a.this.j.a();
            LogTool.d(com.ido.ble.dfu.a.f4247c, "onStateChanged = " + i);
            if (i == 2) {
                a.this.k();
            }
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
        public void onTargetInfoChanged(OtaDeviceInfo otaDeviceInfo) {
            super.onTargetInfoChanged(otaDeviceInfo);
            a.this.j.a();
            try {
                BinInfo binInfoLoadImageBinInfo = BinFactory.loadImageBinInfo(a.this.f4348b.getFilePath(), otaDeviceInfo, false);
                if (binInfoLoadImageBinInfo != null) {
                    LogTool.d(com.ido.ble.dfu.a.f4247c, "onTargetInfoChanged.mBinInfo = " + binInfoLoadImageBinInfo.toString());
                }
            } catch (DfuException e2) {
                LogTool.b(com.ido.ble.dfu.a.f4247c, e2.getMessage());
            }
            LogTool.d(com.ido.ble.dfu.a.f4247c, "onTargetInfoChanged.otaDeviceInfo = " + otaDeviceInfo.toString());
        }
    }

    class b implements b.c {
        b() {
        }

        @Override // com.ido.ble.dfu.d.b.b.c
        public void a() {
            a.this.e();
        }
    }

    class c implements RtkAuthTask.IResult {
        c() {
        }

        @Override // com.ido.ble.dfu.rtk.auth.RtkAuthTask.IResult
        public void onFailed(String str) {
            a.this.l();
            a.this.f4352f.c();
        }

        @Override // com.ido.ble.dfu.rtk.auth.RtkAuthTask.IResult
        public void onSuccess() {
            if (com.ido.ble.bluetooth.a.h()) {
                a.this.j();
            } else {
                com.ido.ble.bluetooth.a.b();
                a.this.e();
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f4354h.initialize(a.this.k);
        }
    }

    class e implements b.InterfaceC0064b {
        e() {
        }

        @Override // com.ido.ble.dfu.d.a.b.InterfaceC0064b
        public void onTimeOut() {
            a.this.c();
        }
    }

    private a() {
    }

    private Context a() {
        return com.ido.ble.b.b().getApplicationContext();
    }

    private void a(int i, boolean z) {
        if (z) {
            this.f4351e += 100;
        }
        this.f4352f.onProgress(((i + this.f4351e) * 100) / 300);
    }

    private void a(DfuProgressInfo dfuProgressInfo) {
        a(dfuProgressInfo.getProgress(), dfuProgressInfo.getProgress() <= this.f4350d);
    }

    public static a b() {
        if (n == null) {
            n = new a();
        }
        return n;
    }

    private boolean b(BleDFUConfig bleDFUConfig) {
        String str;
        if (bleDFUConfig == null) {
            str = "mDfuConfig is null";
        } else {
            LogTool.d(com.ido.ble.dfu.a.f4247c, "mDfuConfig is " + bleDFUConfig.toString());
            if (TextUtils.isEmpty(bleDFUConfig.getFilePath())) {
                str = "file path is null";
            } else if (TextUtils.isEmpty(bleDFUConfig.getMacAddress())) {
                str = "mac address is null";
            } else {
                if (!TextUtils.isEmpty(bleDFUConfig.getDeviceId())) {
                    if (bleDFUConfig.getFilePath().endsWith(FileDialDefinedUtil.FILE_ZIP)) {
                        String strReplace = bleDFUConfig.getFilePath().replace(FileDialDefinedUtil.FILE_ZIP, ".bin");
                        if (new File(bleDFUConfig.getFilePath()).renameTo(new File(strReplace))) {
                            bleDFUConfig.setFilePath(strReplace);
                        } else {
                            str = "rename failed";
                        }
                    }
                    this.f4348b = bleDFUConfig;
                    return true;
                }
                str = "device_id is null";
            }
        }
        LogTool.b(com.ido.ble.dfu.a.f4247c, str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f4352f.c();
        h();
    }

    private void d() {
        this.f4354h = DfuHelper.getInstance(a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        g();
        this.f4353g.postDelayed(new d(), 3000L);
    }

    private void f() {
        LogTool.d(com.ido.ble.dfu.a.f4247c, "release");
        this.f4347a = false;
        this.f4349c = 0;
        this.f4350d = 0;
        this.f4351e = 0;
        Handler handler = this.f4353g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        RtkAuthTask rtkAuthTask = this.i;
        if (rtkAuthTask != null) {
            rtkAuthTask.stop();
            this.i = null;
        }
        DfuHelper dfuHelper = this.f4354h;
        if (dfuHelper != null) {
            dfuHelper.abort();
            this.f4354h.close();
        }
    }

    private void g() {
        this.j.a(new e());
    }

    private void h() {
        LogTool.b(com.ido.ble.dfu.a.f4247c, "timeout, upgrade failed!");
        f();
    }

    private void i() {
        LogTool.d(com.ido.ble.dfu.a.f4247c, "[RtkDFUManager] to auth");
        RtkAuthTask rtkAuthTask = this.i;
        if (rtkAuthTask != null) {
            rtkAuthTask.stop();
        }
        this.i = new RtkAuthTask(this.f4348b.getFilePath());
        this.i.start(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        com.ido.ble.dfu.d.b.b bVar = this.l;
        if (bVar != null) {
            bVar.a();
        }
        this.l = new com.ido.ble.dfu.d.b.b();
        this.l.a(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogTool.d(com.ido.ble.dfu.a.f4247c, "upgrade...");
        DfuConfig dfuConfig = new DfuConfig();
        dfuConfig.setAddress(this.f4348b.getMacAddress());
        dfuConfig.setFileIndicator(-1);
        dfuConfig.setAutomaticActiveEnabled(true);
        dfuConfig.setBatteryCheckEnabled(true);
        dfuConfig.setOtaWorkMode(this.f4348b.getOtaWorkMode());
        dfuConfig.setFilePath(this.f4348b.getFilePath());
        if (this.f4354h.startOtaProcess(dfuConfig)) {
            return;
        }
        LogTool.b(com.ido.ble.dfu.a.f4247c, "mDfuHelper.startOtaProcess return false.");
        l();
        this.f4352f.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogTool.b(com.ido.ble.dfu.a.f4247c, "upgrade failed, exit!");
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogTool.d(com.ido.ble.dfu.a.f4247c, "upgrade success");
        f();
    }

    public boolean a(BleDFUConfig bleDFUConfig) {
        LogTool.d(com.ido.ble.dfu.a.f4247c, " ----start-------------->");
        if (this.f4347a) {
            LogTool.b(com.ido.ble.dfu.a.f4247c, "is doing ,ignore this action.");
            return false;
        }
        this.f4352f = new com.ido.ble.dfu.d.a.a(bleDFUConfig);
        this.f4352f.onPrepare();
        if (!b(bleDFUConfig)) {
            this.f4352f.i();
            return false;
        }
        this.j = new com.ido.ble.dfu.d.a.b();
        this.f4347a = true;
        d();
        if (this.f4348b.isNeedAuth()) {
            i();
        } else if (com.ido.ble.bluetooth.a.h()) {
            j();
        } else {
            com.ido.ble.bluetooth.a.b();
            e();
        }
        return true;
    }
}