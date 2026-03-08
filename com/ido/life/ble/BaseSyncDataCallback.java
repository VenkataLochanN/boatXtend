package com.ido.life.ble;

import com.ido.ble.business.sync.ISyncDataListener;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthBloodPressureV3;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthNoise;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSleepV3;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.data.manage.database.HealthSportV3;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthTemperature;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseSyncDataCallback implements ISyncDataListener {
    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthBloodPressure(HealthBloodPressureV3 healthBloodPressureV3) {
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthTemperature(HealthTemperature healthTemperature) {
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetSportData(HealthSport healthSport, List<HealthSportItem> list, boolean z) {
        SyncDeviceDataProxy.getInstance().processV2SportData(healthSport, list);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetSleepData(HealthSleep healthSleep, List<HealthSleepItem> list) {
        SyncDeviceDataProxy.getInstance().processV2SleepData(healthSleep, list);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> list, boolean z) {
        SyncDeviceDataProxy.getInstance().processV2HeartRateData(healthHeartRate, list);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetBloodPressureData(HealthBloodPressed healthBloodPressed, List<HealthBloodPressedItem> list, boolean z) {
        SyncDeviceDataProxy.getInstance().processBloodPressureData(healthBloodPressed, list);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetActivityData(HealthActivity healthActivity) {
        SyncDeviceDataProxy.getInstance().processV2ActivityData(healthActivity);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetGpsData(HealthGps healthGps, List<HealthGpsItem> list, boolean z) {
        SyncDeviceDataProxy.getInstance().processGpsData(healthGps, list);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthSpO2Data(HealthSpO2 healthSpO2, List<HealthSpO2Item> list, boolean z) {
        SyncDeviceDataProxy.getInstance().processHealthSpO2Data(healthSpO2, list);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthPressureData(HealthPressure healthPressure, List<HealthPressureItem> list, boolean z) {
        SyncDeviceDataProxy.getInstance().processHealthPressureData(healthPressure, list);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthHeartRateSecondData(HealthHeartRateSecond healthHeartRateSecond, boolean z) {
        SyncDeviceDataProxy.getInstance().processV3HealthHeartRateSecondData(healthHeartRateSecond);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthSwimmingData(HealthSwimming healthSwimming) {
        SyncDeviceDataProxy.getInstance().processHealthSwimmingData(healthSwimming);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthActivityV3Data(HealthActivityV3 healthActivityV3) {
        SyncDeviceDataProxy.getInstance().processV3HealthActivityV3Data(healthActivityV3);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthSportV3Data(HealthSportV3 healthSportV3) {
        SyncDeviceDataProxy.getInstance().processV3HealthSportV3Data(healthSportV3);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthSleepV3Data(HealthSleepV3 healthSleepV3) {
        SyncDeviceDataProxy.getInstance().processV3HealthSleepV3Data(healthSleepV3);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthGpsV3Data(HealthGpsV3 healthGpsV3) {
        SyncDeviceDataProxy.getInstance().processGpsDataV3(healthGpsV3);
    }

    @Override // com.ido.ble.business.sync.ISyncDataListener
    public void onGetHealthNoiseData(HealthNoise healthNoise) {
        try {
            SyncDeviceDataProxy.getInstance().processHealthNoiseData(healthNoise);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}