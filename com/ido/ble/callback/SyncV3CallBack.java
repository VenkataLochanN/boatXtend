package com.ido.ble.callback;

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
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SyncV3CallBack {

    public interface ICallBack {
        void onFailed();

        void onGetHealthActivityV3Data(HealthActivityV3 healthActivityV3);

        void onGetHealthBloodPressure(HealthBloodPressureV3 healthBloodPressureV3);

        void onGetHealthGpsV3Data(HealthGpsV3 healthGpsV3);

        void onGetHealthHeartRateSecondData(HealthHeartRateSecond healthHeartRateSecond, boolean z);

        void onGetHealthNoiseData(HealthNoise healthNoise);

        void onGetHealthPressureData(HealthPressure healthPressure, List<HealthPressureItem> list, boolean z);

        void onGetHealthSleepV3Data(HealthSleepV3 healthSleepV3);

        void onGetHealthSpO2Data(HealthSpO2 healthSpO2, List<HealthSpO2Item> list, boolean z);

        void onGetHealthSportV3Data(HealthSportV3 healthSportV3);

        void onGetHealthSwimmingData(HealthSwimming healthSwimming);

        void onGetHealthTemperature(HealthTemperature healthTemperature);

        void onProgress(int i);

        void onStart();

        void onStop();

        void onSuccess();
    }

    public static void onGetHealthActivityV3Data(final HealthActivityV3 healthActivityV3) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.10
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthActivityV3Data(healthActivityV3);
                }
            }
        });
    }

    public static void onGetHealthBloodPressure(final HealthBloodPressureV3 healthBloodPressureV3) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.16
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthBloodPressure(healthBloodPressureV3);
                }
            }
        });
    }

    public static void onGetHealthGpsV3Data(final HealthGpsV3 healthGpsV3) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.13
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthGpsV3Data(healthGpsV3);
                }
            }
        });
    }

    public static void onGetHealthHeartRateSecondData(final HealthHeartRateSecond healthHeartRateSecond, final boolean z) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthHeartRateSecondData(healthHeartRateSecond, z);
                }
            }
        });
    }

    public static void onGetHealthNoiseData(final HealthNoise healthNoise) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.14
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthNoiseData(healthNoise);
                }
            }
        });
    }

    public static void onGetHealthPressureData(final HealthPressure healthPressure, final List<HealthPressureItem> list, final boolean z) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.7
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthPressureData(healthPressure, list, z);
                }
            }
        });
    }

    public static void onGetHealthSleepV3Data(final HealthSleepV3 healthSleepV3) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.12
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthSleepV3Data(healthSleepV3);
                }
            }
        });
    }

    public static void onGetHealthSpO2Data(final HealthSpO2 healthSpO2, final List<HealthSpO2Item> list, final boolean z) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthSpO2Data(healthSpO2, list, z);
                }
            }
        });
    }

    public static void onGetHealthSportV3Data(final HealthSportV3 healthSportV3) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.11
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthSportV3Data(healthSportV3);
                }
            }
        });
    }

    public static void onGetHealthSwimmingData(final HealthSwimming healthSwimming) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.9
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthSwimmingData(healthSwimming);
                }
            }
        });
    }

    public static void onGetHealthTemperature(final HealthTemperature healthTemperature) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.15
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onGetHealthTemperature(healthTemperature);
                }
            }
        });
    }

    public static void onSyncV3HealthFailed() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onFailed();
                }
            }
        });
    }

    public static void onSyncV3HealthProgress(final int i) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(i);
                }
            }
        });
    }

    public static void onSyncV3HealthStart() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onStart();
                }
            }
        });
    }

    public static void onSyncV3HealthStop() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onStop();
                }
            }
        });
    }

    public static void onSyncV3HealthSuccess() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().F().iterator();
                while (it.hasNext()) {
                    it.next().onSuccess();
                }
            }
        });
    }
}