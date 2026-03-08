package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.logs.LogTool;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class d extends f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private GpsCallBack.ISyncGpsDataCallBack f4195f = new a();

    class a implements GpsCallBack.ISyncGpsDataCallBack {
        a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onFailed() {
            d.this.e();
            d.this.f4199a.onFailed();
            d.this.b();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onFinish() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncGpsTask] onFinish");
            d.this.f4199a.onProgress(100);
            d.this.e();
            d.this.f4199a.onSuccess();
            d.this.b();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onGetGpsData(HealthGps healthGps, List<HealthGpsItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = d.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetGpsData(healthGps, list, z);
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onProgress(int i) {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncGpsTask] progress = " + i);
            d.this.f4199a.onProgress(i);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISyncGpsDataCallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncGpsTask] onStart");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.f4201c = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncGpsTask] finished!");
        com.ido.ble.gps.callback.a.h().b(this.f4195f);
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.f4202d = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncGpsTask] start...");
        com.ido.ble.gps.callback.a.h().a(this.f4195f);
        com.ido.ble.h.a.g();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.f4201c || !this.f4202d) {
            return;
        }
        com.ido.ble.h.a.i();
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncGpsTask] stop!");
        e();
    }
}