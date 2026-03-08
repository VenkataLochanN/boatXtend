package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthBloodPressureV3;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthNoise;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthSleepV3;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSportV3;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthTemperature;
import com.ido.ble.logs.LogTool;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class g extends f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SyncV3CallBack.ICallBack f4204f = new a();

    class a implements SyncV3CallBack.ICallBack {
        a() {
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onFailed() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncV3HealthTask] failed");
            g.this.e();
            g.this.f4199a.onFailed();
            g.this.b();
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthActivityV3Data(HealthActivityV3 healthActivityV3) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthActivityV3Data(healthActivityV3);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthBloodPressure(HealthBloodPressureV3 healthBloodPressureV3) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthBloodPressure(healthBloodPressureV3);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthGpsV3Data(HealthGpsV3 healthGpsV3) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthGpsV3Data(healthGpsV3);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthHeartRateSecondData(HealthHeartRateSecond healthHeartRateSecond, boolean z) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthHeartRateSecondData(healthHeartRateSecond, z);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthNoiseData(HealthNoise healthNoise) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthNoiseData(healthNoise);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthPressureData(HealthPressure healthPressure, List<HealthPressureItem> list, boolean z) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthPressureData(healthPressure, list, z);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthSleepV3Data(HealthSleepV3 healthSleepV3) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthSleepV3Data(healthSleepV3);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthSpO2Data(HealthSpO2 healthSpO2, List<HealthSpO2Item> list, boolean z) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthSpO2Data(healthSpO2, list, z);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthSportV3Data(HealthSportV3 healthSportV3) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthSportV3Data(healthSportV3);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthSwimmingData(HealthSwimming healthSwimming) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthSwimmingData(healthSwimming);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onGetHealthTemperature(HealthTemperature healthTemperature) {
            ISyncDataListener iSyncDataListener = g.this.f4200b;
            if (iSyncDataListener != null) {
                iSyncDataListener.onGetHealthTemperature(healthTemperature);
            }
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onProgress(int i) {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncV3HealthTask] progress = " + i);
            g.this.f4199a.onProgress(i);
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onStart() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncV3HealthTask] onStart");
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onStop() {
            if (g.this.f4201c) {
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncV3HealthTask] onStop");
            g.this.e();
            g.this.f4199a.onFailed();
            g.this.b();
        }

        @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
        public void onSuccess() {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncV3HealthTask] onSuccess");
            g.this.e();
            g.this.f4199a.onSuccess();
            g.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.f4201c = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncV3HealthTask] finished!");
        com.ido.ble.callback.b.K().b(this.f4204f);
    }

    @Override // com.ido.ble.business.sync.f.f
    public void c() {
        this.f4202d = true;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncV3HealthTask] start...");
        com.ido.ble.callback.b.K().a(this.f4204f);
        com.ido.ble.i.a.a.u0();
    }

    @Override // com.ido.ble.business.sync.f.f
    public void d() {
        if (this.f4201c || !this.f4202d) {
            return;
        }
        com.ido.ble.i.a.a.B0();
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncV3HealthTask] stop!");
        e();
    }
}