package com.ido.ble.bluetooth.connect.q;

import android.text.TextUtils;
import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.i;
import com.ido.ble.protocol.model.BindEncrypted;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static a f4098d = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f4099a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ido.ble.callback.a f4100b = new C0056a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i.c f4101c = new b();

    /* JADX INFO: renamed from: com.ido.ble.bluetooth.connect.q.a$a, reason: collision with other inner class name */
    class C0056a extends com.ido.ble.callback.a {
        C0056a() {
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
            a.this.a(supportFunctionInfo);
        }
    }

    class b implements i.c {
        b() {
        }

        @Override // com.ido.ble.protocol.handler.i.c
        public void a(boolean z) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [EncryptedTask]onEncrypted " + z);
            if (z) {
                a.this.f4099a.onSuccess();
            } else {
                LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [EncryptedTask]re get code. ");
                a.this.b();
            }
        }

        @Override // com.ido.ble.protocol.handler.i.c
        public void a(int[] iArr) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [EncryptedTask]onGetCode " + Arrays.toString(iArr));
            a.this.b(iArr);
        }
    }

    public interface c {
        void onFailed();

        void onSuccess();
    }

    private a() {
        i.a(this.f4101c);
    }

    private void a() {
        com.ido.ble.callback.b.K().a(this.f4100b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SupportFunctionInfo supportFunctionInfo) {
        com.ido.ble.callback.b.K().b(this.f4100b);
        if (supportFunctionInfo == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [EncryptedTask]return1Func, supportFunctionInfo is null");
            this.f4099a.onFailed();
        } else if (supportFunctionInfo.ex_table_main8_encrypted_auth) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [EncryptedTask]return1Func, support encrypted. to get code");
            b();
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [EncryptedTask]return1Func, is not support encrypted.");
            this.f4099a.onSuccess();
        }
    }

    private void a(int[] iArr) {
        BindEncrypted bindEncrypted = new BindEncrypted();
        bindEncrypted.autu_data = iArr;
        bindEncrypted.auth_length = (iArr == null || iArr.length <= 0) ? 0 : iArr.length;
        com.ido.ble.bluetooth.a.i(k.a(bindEncrypted));
        com.ido.ble.i.a.a.a(bindEncrypted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        i.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int[] iArr) {
        if (iArr == null) {
            this.f4099a.onFailed();
        } else {
            a(iArr);
        }
    }

    public static a c() {
        return f4098d;
    }

    public void a(c cVar) {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] start to encryptedAtConnected ");
        this.f4099a = cVar;
        if (!com.ido.ble.bluetooth.a.g()) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnected, is not bind, not need. ");
            this.f4099a.onSuccess();
            return;
        }
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        if (supportFunctionInfoV != null && !supportFunctionInfoV.ex_table_main8_encrypted_auth) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnected, device not support encrypt. ");
            this.f4099a.onSuccess();
            return;
        }
        String strF = com.ido.ble.bluetooth.a.f();
        if (TextUtils.isEmpty(strF)) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnected, jsonString is null, to get func...");
            a();
            return;
        }
        BindEncrypted bindEncrypted = (BindEncrypted) k.c(strF, BindEncrypted.class);
        if (bindEncrypted != null) {
            a(bindEncrypted.autu_data);
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] encryptedAtConnected, encrypted is null, to re get code...");
            b();
        }
    }

    public void a(int[] iArr, c cVar) {
        this.f4099a = cVar;
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] start to encryptedAtBind ");
        a(iArr);
    }
}