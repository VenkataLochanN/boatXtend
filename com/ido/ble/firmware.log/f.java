package com.ido.ble.firmware.log;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.firmware.log.b;
import com.ido.ble.logs.LogTool;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static f f4533g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f4534h = System.getProperty("line.separator");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Handler f4535a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private StringBuilder f4536b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f4537c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ICollectDeviceRebootLogListener f4538d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4539e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4540f = false;

    class a implements b.InterfaceC0084b {
        a() {
        }

        @Override // com.ido.ble.firmware.log.b.InterfaceC0084b
        public void onTimeOut() {
            if (f.this.f4540f) {
                f.this.e();
            } else {
                f.this.a();
            }
        }
    }

    private f() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogTool.d("DEVICE_REBOOT_LOG", "failed.");
        this.f4536b.append("-----not full, will collect logs next time---");
        f();
        ICollectDeviceRebootLogListener iCollectDeviceRebootLogListener = this.f4538d;
        if (iCollectDeviceRebootLogListener != null) {
            iCollectDeviceRebootLogListener.onFailed();
        }
        c();
    }

    private void a(boolean z) {
        if (this.f4535a == null) {
            this.f4535a = new Handler(Looper.getMainLooper());
        }
        this.f4536b = new StringBuilder();
        if (z) {
            this.f4536b.append("[reboot = true]" + f4534h);
        }
        if (this.f4537c == null) {
            this.f4537c = new b();
        }
    }

    public static f b() {
        if (f4533g == null) {
            f4533g = new f();
        }
        return f4533g;
    }

    private void c() {
        LogTool.d("DEVICE_REBOOT_LOG", "release.");
        this.f4538d = null;
        this.f4539e = false;
        this.f4540f = false;
    }

    private boolean c(byte[] bArr) {
        return e.c(bArr) || e.b(bArr) || e.d(bArr);
    }

    private void d() {
        LogTool.d("DEVICE_REBOOT_LOG", "start-->");
        ICollectDeviceRebootLogListener iCollectDeviceRebootLogListener = this.f4538d;
        if (iCollectDeviceRebootLogListener != null) {
            iCollectDeviceRebootLogListener.onStart();
        }
    }

    private void d(byte[] bArr) {
        String str = new String(Arrays.copyOfRange(bArr, 3, bArr.length - 1));
        StringBuilder sb = this.f4536b;
        sb.append(str);
        sb.append(f4534h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogTool.d("DEVICE_REBOOT_LOG", "success");
        this.f4537c.a();
        String strF = f();
        if (this.f4538d != null) {
            if (TextUtils.isEmpty(strF)) {
                this.f4538d.onFailed();
            } else {
                this.f4538d.onSuccess(strF);
            }
        }
        c();
    }

    private String f() {
        LogTool.d("DEVICE_REBOOT_LOG", "write log to file.");
        return g.a(this.f4536b.toString());
    }

    public boolean a(boolean z, ICollectDeviceRebootLogListener iCollectDeviceRebootLogListener) {
        if (this.f4539e) {
            LogTool.b("DEVICE_REBOOT_LOG", "[FirmwareRebootLogManager] start(), isDoing = true, ignore this action.");
            return false;
        }
        this.f4539e = true;
        this.f4540f = false;
        this.f4538d = iCollectDeviceRebootLogListener;
        a(z);
        d();
        e.b();
        this.f4537c.a(new a(), 10);
        return true;
    }

    public boolean a(byte[] bArr) {
        return this.f4539e && c(bArr);
    }

    public void b(byte[] bArr) {
        LogTool.d("DEVICE_REBOOT_LOG", "[handleDeviceRespond] value is " + com.ido.ble.common.c.b(bArr));
        this.f4537c.b();
        if (e.c(bArr)) {
            if (e.a(bArr)) {
                d(bArr);
                e.b();
                return;
            } else {
                this.f4540f = true;
                e.a();
                return;
            }
        }
        if (e.b(bArr)) {
            e.c();
        } else if (e.d(bArr)) {
            e();
        }
    }
}