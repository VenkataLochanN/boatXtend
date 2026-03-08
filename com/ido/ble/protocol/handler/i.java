package com.ido.ble.protocol.handler;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.BindEncryptedDeviceReply;
import com.ido.ble.protocol.model.EncryptedGetCode;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Handler f4641a = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static c f4642b;

    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ byte[] f4643a;

        a(byte[] bArr) {
            this.f4643a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            i.c(this.f4643a);
        }
    }

    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ byte[] f4644a;

        b(byte[] bArr) {
            this.f4644a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            i.d(this.f4644a);
        }
    }

    public interface c {
        void a(boolean z);

        void a(int[] iArr);
    }

    public static void a() {
        u.b(com.veryfit.multi.nativeprotocol.b.k5, com.veryfit.multi.nativeprotocol.b.J0);
    }

    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        Handler handler;
        Runnable bVar;
        if (i == 204) {
            handler = f4641a;
            bVar = new a(bArr);
        } else {
            if (i != 205) {
                return;
            }
            handler = f4641a;
            bVar = new b(bArr);
        }
        handler.post(bVar);
    }

    public static void a(c cVar) {
        f4642b = cVar;
    }

    static boolean a(int i) {
        return i == 204 || i == 205;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(byte[] bArr) {
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[BIND_UNBIND] [handleBindEncryptedJsonResult], jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [handleBindEncryptedJsonResult], jsonString is " + strD);
            BindEncryptedDeviceReply bindEncryptedDeviceReply = (BindEncryptedDeviceReply) com.ido.ble.common.k.c(strD, BindEncryptedDeviceReply.class);
            if (bindEncryptedDeviceReply != null) {
                if (bindEncryptedDeviceReply.auth_code == 0) {
                    f4642b.a(true);
                    return;
                } else {
                    f4642b.a(false);
                    return;
                }
            }
            str = "[BIND_UNBIND] [handleBindEncryptedJsonResult], bindEncryptedDeviceReply is null";
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
        f4642b.a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [handleGetEncryptedCode], jsonString is null");
            f4642b.a((int[]) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [handleGetEncryptedCode], jsonString is " + strD);
        EncryptedGetCode encryptedGetCode = (EncryptedGetCode) com.ido.ble.common.k.c(strD, EncryptedGetCode.class);
        if (encryptedGetCode != null) {
            f4642b.a(encryptedGetCode.encrypted_data);
        } else {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[BIND_UNBIND] [handleGetEncryptedCode], bindEncrypted is null");
            f4642b.a((int[]) null);
        }
    }
}