package com.ido.ble.dfu.e;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceUpgradeEventListener;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.dfu.BleDFUConfig;
import com.ido.ble.dfu.DFUService;
import com.ido.ble.dfu.d.a.b;
import com.ido.ble.dfu.d.b.a;
import com.ido.ble.dfu.d.b.b;
import com.ido.ble.dfu.d.c.a;
import com.ido.ble.dfu.e.b.a;
import com.ido.ble.dfu.e.b.b;
import com.ido.ble.dfu.e.b.c;
import com.ido.ble.dfu.e.b.d;
import com.ido.ble.logs.LogTool;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.lang.reflect.Method;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final int v = 6;
    private static a w;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BleDFUConfig f4288b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.ido.ble.dfu.d.a.c f4289c;
    private com.ido.ble.dfu.e.b.a j;
    private com.ido.ble.dfu.d.a.d k;
    private com.ido.ble.dfu.e.b.c n;
    private com.ido.ble.dfu.d.b.a o;
    private com.ido.ble.dfu.e.b.b p;
    private com.ido.ble.dfu.d.b.b q;
    private DfuProgressListener r;
    private String s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4287a = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4290d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4291e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4292f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f4293g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4294h = false;
    private int i = 0;
    private boolean l = true;
    private Handler m = new Handler(Looper.getMainLooper());
    private boolean t = false;
    private int u = 0;

    /* JADX INFO: renamed from: com.ido.ble.dfu.e.a$a, reason: collision with other inner class name */
    class C0069a implements d.b {
        C0069a() {
        }

        @Override // com.ido.ble.dfu.e.b.d.b
        public void a(int i) {
            a.this.u = i;
            a.this.b(i);
        }
    }

    class b implements a.b {
        b() {
        }

        @Override // com.ido.ble.dfu.e.b.a.b
        public void a() {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:mCheckDFUResultTask] onDeviceInDfuState     upgradeSuccess();\n");
            a.this.t();
            a.this.f4289c.onSuccessAndNeedToPromptUser();
        }

        @Override // com.ido.ble.dfu.e.b.a.b
        public void b() {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:mCheckDFUResultTask] onCannotCheckDeviceStatus     upgradeSuccess();\n");
            a.this.t();
            a.this.f4289c.onSuccessAndNeedToPromptUser();
        }

        @Override // com.ido.ble.dfu.e.b.a.b
        public void c() {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:mCheckDFUResultTask] onDeviceInNormalState    upgradeSuccess();\n");
            a.this.t();
            a.this.f4289c.onSuccess();
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f4297a;

        /* JADX INFO: renamed from: com.ido.ble.dfu.e.a$c$a, reason: collision with other inner class name */
        class RunnableC0070a implements Runnable {
            RunnableC0070a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                a.this.b(cVar.f4297a);
            }
        }

        c(boolean z) {
            this.f4297a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.o();
            a.this.m.postDelayed(new RunnableC0070a(), BootloaderScanner.TIMEOUT);
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.h();
        }
    }

    class e implements a.d {
        e() {
        }

        @Override // com.ido.ble.dfu.d.c.a.d
        public void a() {
            a.this.c(true);
        }
    }

    class f implements c.d {
        f() {
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void a() {
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                a.this.s();
                a.this.f4289c.h();
            } else {
                if (!a.this.g()) {
                    a.this.b(false);
                    return;
                }
                a aVar = a.this;
                if (aVar.b(aVar.s)) {
                    return;
                }
                a.this.b(false);
            }
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void a(BLEDevice bLEDevice) {
            if (com.ido.ble.bluetooth.a.h()) {
                a.this.q();
            } else {
                a.this.a(bLEDevice);
            }
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void b(BLEDevice bLEDevice) {
            a.this.s = bLEDevice.mDeviceAddress;
            a.this.t = true;
            a.this.r();
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void c(BLEDevice bLEDevice) {
            a.this.a(bLEDevice);
        }
    }

    class g implements a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BLEDevice f4303a;

        g(BLEDevice bLEDevice) {
            this.f4303a = bLEDevice;
        }

        @Override // com.ido.ble.dfu.d.b.a.b
        public void a() {
            a.this.q();
        }

        @Override // com.ido.ble.dfu.d.b.a.b
        public void b() {
            a.this.l();
        }

        @Override // com.ido.ble.dfu.d.b.a.b
        public void c() {
            a.this.s = this.f4303a.mDeviceAddress;
            a.this.r();
        }
    }

    class h implements b.c {
        h() {
        }

        @Override // com.ido.ble.dfu.e.b.b.c
        public void a() {
            a.this.s();
            a.this.f4289c.d();
        }

        @Override // com.ido.ble.dfu.e.b.b.c
        public void a(EnterDfuModeCallback.DfuError dfuError) {
            a.this.s();
            a.this.f4289c.a(dfuError);
        }

        @Override // com.ido.ble.dfu.e.b.b.c
        public void b() {
            a.this.l();
        }

        @Override // com.ido.ble.dfu.e.b.b.c
        public void onSuccess() {
            a.this.f4289c.onDeviceInDFUMode();
            if (com.ido.ble.bluetooth.a.h()) {
                a.this.p();
            } else {
                a.this.l();
            }
        }
    }

    class i implements b.c {
        i() {
        }

        @Override // com.ido.ble.dfu.d.b.b.c
        public void a() {
            a.this.l();
        }
    }

    class j implements b.InterfaceC0064b {
        j() {
        }

        @Override // com.ido.ble.dfu.d.a.b.InterfaceC0064b
        public void onTimeOut() {
            a.this.f4293g = true;
            a.this.f();
        }
    }

    private class k implements DfuProgressListener {
        private k() {
        }

        /* synthetic */ k(a aVar, b bVar) {
            this();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceConnected(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDeviceConnected");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceConnecting(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDeviceConnecting");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceDisconnected(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDeviceDisconnected");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceDisconnecting(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDeviceDisconnecting");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuAborted(String str) {
            if (!a.this.l) {
                LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDfuAborted");
                a.this.h();
            } else {
                LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDfuAborted by nodic-dfu-lib");
                a.this.k.b();
                a.this.a(false);
            }
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuCompleted(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDfuCompleted");
            a.this.a(100);
            a.this.k.b();
            DfuServiceListenerHelper.unregisterProgressListener(com.ido.ble.common.e.a(), a.this.r);
            a aVar = a.this;
            aVar.a(aVar.f4288b.getMacAddress());
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuProcessStarted(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDfuProcessStarted");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuProcessStarting(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onDfuProcessStarting");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onEnablingDfuMode(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onEnablingDfuMode");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onError(String str, int i, int i2, String str2) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] error=" + i + ", errorType=" + i2 + AppInfo.DELIM + str2);
            a.this.k.b();
            a.this.a(i, i2, str2);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onFirmwareValidating(String str) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onFirmwareValidating");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onProgressChanged(String str, int i, float f2, float f3, int i2, int i3) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager:DfuProgressListener] onProgressChanged, progress = " + i);
            DeviceUpgradeEventListener.NODIC_onProgress(i);
            if (i <= 99) {
                a.this.a(i);
            }
            if (i == 100) {
                a.this.f4294h = true;
            }
            a.this.k.a();
        }
    }

    private a() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        com.ido.ble.dfu.e.b.d.b().a();
        if (i2 > this.u) {
            this.f4289c.onProgress(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3, String str) {
        if (i2 == 262) {
            s();
            this.f4289c.e();
            return;
        }
        boolean z = true;
        if (i2 != 520 && i2 != 4100) {
            if (i2 == 4102) {
                this.i++;
                int i4 = this.i;
                this.f4289c.a(i2, str);
                a(z);
            }
            if (i2 != 4108) {
                if (i2 != 4096) {
                    if (i2 != 4097 && i2 != 4105) {
                        if (i2 == 4106) {
                            s();
                            this.f4289c.h();
                            return;
                        }
                    }
                } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                    e();
                    return;
                }
            }
            s();
            this.f4289c.j();
            return;
        }
        this.f4293g = true;
        z = false;
        this.f4289c.a(i2, str);
        a(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BLEDevice bLEDevice) {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] (hasFindDeviceAndToConnectDevice) to connect Device");
        b(bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogTool.b(com.ido.ble.dfu.a.f4246b, "to check dfu result:" + str);
        com.ido.ble.dfu.e.b.a aVar = this.j;
        if (aVar != null) {
            aVar.a();
        }
        this.j = new com.ido.ble.dfu.e.b.a();
        this.j.a(new b(), str);
    }

    private void a(DfuServiceInitiator dfuServiceInitiator, boolean z) {
        String str;
        try {
            for (Method method : dfuServiceInitiator.getClass().getMethods()) {
                if ("disableResume".equals(method.getName()) && z) {
                    method.invoke("disableResume", new Object[0]);
                    str = "[NodicDFUManager] upgrade... initiator.disableResume()";
                } else if ("setForceSendInitFile".equals(method.getName())) {
                    method.invoke(dfuServiceInitiator, Boolean.valueOf(z));
                    str = "[NodicDFUManager] upgrade... setForceSendInitFile" + z;
                }
                LogTool.d(com.ido.ble.dfu.a.f4246b, str);
                return;
            }
        } catch (Exception e2) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] upgrade..." + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        j();
        LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] wait for restart ..." + (this.f4290d + 1));
        m();
        this.m.postDelayed(new c(z), DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        this.f4289c.onProgress(i2);
    }

    private void b(BLEDevice bLEDevice) {
        com.ido.ble.dfu.d.b.a aVar = this.o;
        if (aVar != null) {
            aVar.a();
        }
        this.o = new com.ido.ble.dfu.d.b.a();
        this.o.a(new g(bLEDevice), bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (!this.f4287a) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] is not in doing state, don't reStart.");
            return;
        }
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] bluetooth switch is closed, upgrade failed, exit!");
            s();
            this.f4289c.h();
            return;
        }
        this.f4290d++;
        if (this.f4290d > this.f4288b.getMaxRetryTime()) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] out of max retry times, upgrade failed, exit!");
            s();
            this.f4289c.f();
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] restart, times is " + this.f4290d);
        this.f4289c.a(this.f4290d);
        int i2 = this.f4290d;
        if (i2 % 2 == 0) {
            l();
            return;
        }
        if (i2 != 3 || !this.f4288b.isNeedReOpenBluetoothSwitchIfFailed()) {
            c(z);
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] findDecive " + this.t + "updatemac:" + this.s);
        if (this.t && this.s.equals(this.f4288b.getMacAddress())) {
            com.ido.ble.bluetooth.e.e.d(this.s);
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] removeBondState " + this.s);
        }
        new com.ido.ble.dfu.d.c.a().a(new e());
    }

    private boolean b(BleDFUConfig bleDFUConfig) {
        String str;
        if (bleDFUConfig == null) {
            str = "[NodicDFUManager] mDfuConfig is null";
        } else {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] dfuConfig is " + com.ido.ble.common.k.a(bleDFUConfig));
            if (TextUtils.isEmpty(bleDFUConfig.getFilePath())) {
                str = "[NodicDFUManager] file path is null";
            } else if (TextUtils.isEmpty(bleDFUConfig.getMacAddress())) {
                str = "[NodicDFUManager] mac address is null";
            } else {
                if (!TextUtils.isEmpty(bleDFUConfig.getDeviceId())) {
                    this.f4288b = bleDFUConfig;
                    this.s = bleDFUConfig.getMacAddress();
                    if (this.f4288b.getMaxRetryTime() != 0) {
                        return true;
                    }
                    this.f4288b.setMaxRetryTime(6);
                    return true;
                }
                str = "[NodicDFUManager] device_id is null";
            }
        }
        LogTool.b(com.ido.ble.dfu.a.f4246b, str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] (notFindDeviceAndTryToConnectDirect) to connect device direct");
        BLEDevice bLEDeviceC = com.ido.ble.f.a.f.b.e().c();
        if (bLEDeviceC == null || !str.equals(bLEDeviceC.mDeviceAddress)) {
            return false;
        }
        b(bLEDeviceC);
        return true;
    }

    private void c() {
        LogTool.c(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] cancelDfuAction");
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(com.ido.ble.common.e.a());
        Intent intent = new Intent(DfuBaseService.BROADCAST_ACTION);
        intent.putExtra(DfuBaseService.EXTRA_ACTION, 2);
        localBroadcastManager.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (!z) {
            r();
        } else if (com.ido.ble.bluetooth.a.h()) {
            q();
        } else {
            l();
        }
    }

    public static a d() {
        if (w == null) {
            w = new a();
        }
        return w;
    }

    private void e() {
        LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] handPhoneBluetoothSwitchOff");
        if (!this.f4287a) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] handPhoneBluetoothSwitchOff, mIsDoing = false.");
        } else {
            s();
            this.f4289c.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!this.f4287a) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] handleNoResponseScene, mIsDoing = false.");
        } else if (!this.f4294h) {
            a(false);
        } else {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] handleNoResponseScene, mOtaFileHasTranFinished = true.");
            a(this.f4288b.getMacAddress());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        this.f4291e++;
        return this.f4291e <= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.f4287a) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] upgrade canceled, exit!");
            i();
            this.f4289c.onCancel();
            this.k.b();
        }
    }

    private void i() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] release");
        com.ido.ble.dfu.e.b.d.b().a();
        this.f4287a = false;
        this.f4290d = 0;
        this.f4292f = 0;
        this.f4291e = 0;
        this.u = 0;
        this.m.removeCallbacksAndMessages(null);
        this.l = true;
        this.i = 0;
        this.f4294h = false;
        this.f4293g = false;
        n();
        DfuServiceListenerHelper.unregisterProgressListener(com.ido.ble.common.e.a(), this.r);
        o();
    }

    private void j() {
        LogTool.c(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] release to prepare to restart");
        n();
        DfuServiceListenerHelper.unregisterProgressListener(com.ido.ble.common.e.a(), this.r);
        c();
    }

    private void k() {
        BluetoothDevice bluetoothDeviceA;
        if (com.ido.ble.bluetooth.e.e.b() || (bluetoothDeviceA = com.ido.ble.bluetooth.e.e.a(this.f4288b.getMacAddress())) == null) {
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] remove bond state.");
        com.ido.ble.bluetooth.e.e.b(bluetoothDeviceA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] to scan target dfu Device.");
        com.ido.ble.dfu.e.b.c cVar = this.n;
        if (cVar != null) {
            cVar.a();
        }
        this.n = new com.ido.ble.dfu.e.b.c();
        this.n.a(new f(), this.f4288b.getMacAddress());
    }

    private void m() {
        com.ido.ble.dfu.e.b.d.b().a(new C0069a());
    }

    private void n() {
        com.ido.ble.dfu.e.b.a aVar = this.j;
        if (aVar != null) {
            aVar.a();
            this.j = null;
        }
        com.ido.ble.dfu.d.b.a aVar2 = this.o;
        if (aVar2 != null) {
            aVar2.a();
            this.o = null;
        }
        com.ido.ble.dfu.e.b.b bVar = this.p;
        if (bVar != null) {
            bVar.a();
            this.p = null;
        }
        com.ido.ble.dfu.e.b.c cVar = this.n;
        if (cVar != null) {
            cVar.a();
            this.n = null;
        }
        com.ido.ble.dfu.d.b.b bVar2 = this.q;
        if (bVar2 != null) {
            bVar2.a();
            this.q = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        com.ido.ble.common.e.a().stopService(new Intent(com.ido.ble.common.e.a(), (Class<?>) DFUService.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        com.ido.ble.dfu.d.b.b bVar = this.q;
        if (bVar != null) {
            bVar.a();
        }
        this.q = new com.ido.ble.dfu.d.b.b();
        this.q.a(new i());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] to enter dfu mode");
        com.ido.ble.dfu.e.b.b bVar = this.p;
        if (bVar != null) {
            bVar.a();
        }
        this.p = new com.ido.ble.dfu.e.b.b();
        this.p.a(new h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] upgrade...mac:" + this.s);
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] upgrade...findDecive:" + this.t);
        if (this.t && this.s.equals(this.f4288b.getMacAddress())) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] removeBondState " + this.f4288b.getMacAddress());
            com.ido.ble.bluetooth.e.e.d(this.f4288b.getMacAddress());
        }
        DfuServiceListenerHelper.registerProgressListener(com.ido.ble.common.e.a(), this.r);
        DfuServiceInitiator dfuServiceInitiator = new DfuServiceInitiator(this.s);
        dfuServiceInitiator.setDisableNotification(true);
        dfuServiceInitiator.setZip(this.f4288b.getFilePath());
        dfuServiceInitiator.setKeepBond(com.ido.ble.bluetooth.e.e.b());
        a(dfuServiceInitiator, this.f4293g);
        if (this.f4288b.getPRN() > 0) {
            dfuServiceInitiator.setPacketsReceiptNotificationsEnabled(true);
            dfuServiceInitiator.setPacketsReceiptNotificationsValue(this.f4288b.getPRN());
        }
        dfuServiceInitiator.start(com.ido.ble.common.e.a(), DFUService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            DfuServiceInitiator.createDfuNotificationChannel(com.ido.ble.b.b());
        }
        this.f4294h = false;
        this.k.a(new j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] upgrade failed, exit!");
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] upgrade success");
        i();
    }

    public void a() {
        if (this.f4287a) {
            LogTool.d(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] start to cancel...");
            this.l = false;
            c();
            this.m.postDelayed(new d(), DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        }
    }

    public boolean a(BleDFUConfig bleDFUConfig) {
        LogTool.c(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] ----start-------------->");
        if (this.f4287a) {
            LogTool.b(com.ido.ble.dfu.a.f4246b, "[NodicDFUManager] is doing ,ignore this action.");
            return false;
        }
        this.f4289c = new com.ido.ble.dfu.d.a.a(bleDFUConfig);
        this.k = new com.ido.ble.dfu.d.a.b();
        this.r = new k(this, null);
        this.t = false;
        this.f4289c.onPrepare();
        if (!b(bleDFUConfig)) {
            this.f4289c.i();
            return false;
        }
        this.f4287a = true;
        m();
        if (com.ido.ble.bluetooth.a.h()) {
            q();
        } else {
            l();
        }
        return true;
    }

    public boolean b() {
        return this.f4287a;
    }
}