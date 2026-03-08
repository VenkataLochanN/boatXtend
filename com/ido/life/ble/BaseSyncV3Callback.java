package com.ido.life.ble;

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
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseSyncV3Callback implements SyncV3CallBack.ICallBack {
    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onFailed() {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthActivityV3Data(HealthActivityV3 healthActivityV3) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthBloodPressure(HealthBloodPressureV3 healthBloodPressureV3) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthGpsV3Data(HealthGpsV3 healthGpsV3) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthHeartRateSecondData(HealthHeartRateSecond healthHeartRateSecond, boolean z) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthNoiseData(HealthNoise healthNoise) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthPressureData(HealthPressure healthPressure, List<HealthPressureItem> list, boolean z) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthSleepV3Data(HealthSleepV3 healthSleepV3) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthSpO2Data(HealthSpO2 healthSpO2, List<HealthSpO2Item> list, boolean z) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthSportV3Data(HealthSportV3 healthSportV3) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthSwimmingData(HealthSwimming healthSwimming) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onGetHealthTemperature(HealthTemperature healthTemperature) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onProgress(int i) {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onStart() {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onStop() {
    }

    @Override // com.ido.ble.callback.SyncV3CallBack.ICallBack
    public void onSuccess() {
    }
}