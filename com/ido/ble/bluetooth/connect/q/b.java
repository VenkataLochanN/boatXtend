package com.ido.ble.bluetooth.connect.q;

import android.text.TextUtils;
import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.i;
import com.ido.ble.protocol.model.BindEncrypted;
import com.ido.ble.protocol.model.SupportFunctionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static b f4104c = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private InterfaceC0057b f4105a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i.c f4106b = new a();

    class a implements i.c {
        a() {
        }

        @Override // com.ido.ble.protocol.handler.i.c
        public void a(boolean z) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [EncryptedTask]onEncrypted " + z);
            if (z) {
                b.this.f4105a.onSuccess();
            } else {
                b.this.f4105a.onFailed();
            }
        }

        @Override // com.ido.ble.protocol.handler.i.c
        public void a(int[] iArr) {
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.bluetooth.connect.q.b$b, reason: collision with other inner class name */
    public interface InterfaceC0057b {
        void onFailed();

        void onSuccess();
    }

    private b() {
        i.a(this.f4106b);
    }

    private void a() {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNull, supportFunctionInfo is null ");
        String strF = com.ido.ble.bluetooth.a.f();
        if (TextUtils.isEmpty(strF)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNull, jsonString is null");
            this.f4105a.onSuccess();
            return;
        }
        BindEncrypted bindEncrypted = (BindEncrypted) k.c(strF, BindEncrypted.class);
        if (bindEncrypted != null) {
            a(bindEncrypted.autu_data);
        } else {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNull, encrypted is null");
            this.f4105a.onFailed();
        }
    }

    private void a(SupportFunctionInfo supportFunctionInfo) {
        if (!supportFunctionInfo.ex_table_main8_encrypted_auth) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNotNull, device not support encrypt. ");
            this.f4105a.onSuccess();
            return;
        }
        String strF = com.ido.ble.bluetooth.a.f();
        if (TextUtils.isEmpty(strF)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNotNull, jsonString is null");
            this.f4105a.onFailed();
            return;
        }
        BindEncrypted bindEncrypted = (BindEncrypted) k.c(strF, BindEncrypted.class);
        if (bindEncrypted != null) {
            a(bindEncrypted.autu_data);
        } else {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNotNull, encrypted is null");
            this.f4105a.onFailed();
        }
    }

    private void a(int[] iArr) {
        BindEncrypted bindEncrypted = new BindEncrypted();
        bindEncrypted.autu_data = iArr;
        bindEncrypted.auth_length = (iArr == null || iArr.length <= 0) ? 0 : iArr.length;
        com.ido.ble.bluetooth.a.i(k.a(bindEncrypted));
        com.ido.ble.i.a.a.a(bindEncrypted);
    }

    public static b b() {
        return f4104c;
    }

    public void a(InterfaceC0057b interfaceC0057b) {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] start to encryptedAtConnected ");
        this.f4105a = interfaceC0057b;
        if (!com.ido.ble.bluetooth.a.g()) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnected, is not bind, not need. ");
            this.f4105a.onSuccess();
            return;
        }
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        if (supportFunctionInfoV == null) {
            a();
        } else {
            a(supportFunctionInfoV);
        }
    }

    public void a(int[] iArr, InterfaceC0057b interfaceC0057b) {
        this.f4105a = interfaceC0057b;
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] start to encryptedAtBind ");
        a(iArr);
    }
}