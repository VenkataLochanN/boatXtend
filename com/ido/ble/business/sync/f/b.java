package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class b extends f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SyncCallBack.IActivityCallBack f4189f = new a();

    class a implements SyncCallBack.IActivityCallBack {
        a() {
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onFailed() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncActivityTask] onFailed");
            b.this.e();
            b.this.f4199a.onFailed();
            b.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onGetActivityData(HealthActivity healthActivity) {
            ISyncDataListener iSyncDataListener = b.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetActivityData(healthActivity);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncActivityTask] onStart");
            b.this.f4199a.onProgress(50);
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onStop() {
            if (b.this.f4201c) {
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncActivityTask] onStop");
            b.this.e();
            b.this.f4199a.onFailed();
            b.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IActivityCallBack
        public void onSuccess() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncActivityTask] onSuccess");
            b.this.f4199a.onProgress(100);
            b.this.e();
            b.this.f4199a.onSuccess();
            b.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.f4201c = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncActivityTask] finished!");
        com.ido.ble.callback.b.K().b(this.f4189f);
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.f4202d = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncActivityTask] start...");
        com.ido.ble.callback.b.K().a(this.f4189f);
        com.ido.ble.i.a.a.r0();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.f4201c || !this.f4202d) {
            return;
        }
        com.ido.ble.i.a.a.y0();
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncActivityTask] stop!");
        e();
    }
}