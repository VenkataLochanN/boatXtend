package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.logs.LogTool;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class e extends f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SyncCallBack.IHealthCallBack f4197f = new a();

    class a implements SyncCallBack.IHealthCallBack {
        a() {
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onFailed() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncHealthTask]failed");
            e.this.e();
            e.this.f4199a.onFailed();
            e.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onGetBloodPressureData(HealthBloodPressed healthBloodPressed, List<HealthBloodPressedItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = e.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetBloodPressureData(healthBloodPressed, list, z);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onGetHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = e.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHeartRateData(healthHeartRate, list, z);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onGetSleepData(HealthSleep healthSleep, List<HealthSleepItem> list) {
            ISyncDataListener iSyncDataListener = e.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetSleepData(healthSleep, list);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onGetSportData(HealthSport healthSport, List<HealthSportItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = e.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetSportData(healthSport, list, z);
            }
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onProgress(int i) {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncHealthTask] progress = " + i);
            e.this.f4199a.onProgress(i);
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncHealthTask]onStart");
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onStop() {
            if (e.this.f4201c) {
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncHealthTask]onStop");
            e.this.e();
            e.this.f4199a.onFailed();
            e.this.b();
        }

        @Override // com.ido.ble.callback.SyncCallBack.IHealthCallBack
        public void onSuccess() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncHealthTask] onSuccess");
            e.this.e();
            e.this.f4199a.onSuccess();
            e.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.f4201c = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncHealthTask] finished");
        com.ido.ble.callback.b.K().b(this.f4197f);
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.f4202d = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncHealthTask] start...");
        com.ido.ble.callback.b.K().a(this.f4197f);
        com.ido.ble.i.a.a.t0();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.f4201c || !this.f4202d) {
            return;
        }
        com.ido.ble.i.a.a.A0();
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncHealthTask] stop!");
        e();
    }
}