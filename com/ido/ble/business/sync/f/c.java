package com.ido.ble.business.sync.f;

import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.SupportFunctionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class c extends f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f4191f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private SyncCallBack.IConfigCallBack f4192g = new a();

    class a implements SyncCallBack.IConfigCallBack {
        a() {
        }

        @Override // com.ido.ble.callback.SyncCallBack.IConfigCallBack
        public void onFailed() {
            LogTool.b(com.ido.ble.business.sync.c.f4177a, "[SyncConfigTask] onFailed");
            c.this.e();
            c.this.f4199a.onFailed();
            c.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IConfigCallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncConfigTask] onStart");
            c.this.f4199a.onProgress(50);
        }

        @Override // com.ido.ble.callback.SyncCallBack.IConfigCallBack
        public void onStop() {
            if (c.this.f4201c) {
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncConfigTask] onStop");
            c.this.e();
            c.this.f4199a.onFailed();
            c.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IConfigCallBack
        public void onSuccess() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncConfigTask] onSuccess");
            c.this.f();
        }
    }

    private class b extends com.ido.ble.callback.a {
        private b() {
        }

        /* synthetic */ b(c cVar, a aVar) {
            this();
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
            c.this.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.f4201c = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncConfigTask] finished");
        com.ido.ble.callback.b.K().b(this.f4192g);
        if (this.f4191f != null) {
            com.ido.ble.callback.b.K().b(this.f4191f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f4191f = new b(this, null);
        com.ido.ble.callback.b.K().a(this.f4191f);
        com.ido.ble.i.a.a.R();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.f4201c) {
            return;
        }
        com.ido.ble.business.sync.f.a aVar = this.f4199a;
        if (aVar != null) {
            aVar.onProgress(100);
        }
        e();
        com.ido.ble.business.sync.f.a aVar2 = this.f4199a;
        if (aVar2 != null) {
            aVar2.onSuccess();
        }
        b();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.f4202d = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncConfigTask] start...");
        com.ido.ble.callback.b.K().a(this.f4192g);
        com.ido.ble.i.a.a.s0();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.f4201c || (!this.f4202d)) {
            return;
        }
        com.ido.ble.i.a.a.z0();
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncConfigTask] stop!");
        e();
    }
}