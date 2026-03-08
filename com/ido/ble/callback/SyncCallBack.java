package com.ido.ble.callback;

import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SyncCallBack {

    public interface IActivityCallBack {
        void onFailed();

        void onGetActivityData(HealthActivity healthActivity);

        void onStart();

        void onStop();

        void onSuccess();
    }

    public interface IConfigCallBack {
        void onFailed();

        void onStart();

        void onStop();

        void onSuccess();
    }

    public interface IHealthCallBack {
        void onFailed();

        void onGetBloodPressureData(HealthBloodPressed healthBloodPressed, List<HealthBloodPressedItem> list, boolean z);

        void onGetHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> list, boolean z);

        void onGetSleepData(HealthSleep healthSleep, List<HealthSleepItem> list);

        void onGetSportData(HealthSport healthSport, List<HealthSportItem> list, boolean z);

        void onProgress(int i);

        void onStart();

        void onStop();

        void onSuccess();
    }

    public static void a() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.10
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IActivityCallBack> it = b.K().C().iterator();
                while (it.hasNext()) {
                    it.next().onFailed();
                }
            }
        });
    }

    public static void a(final int i) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(i);
                }
            }
        });
    }

    public static void a(final HealthActivity healthActivity) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.18
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IActivityCallBack> it = b.K().C().iterator();
                while (it.hasNext()) {
                    it.next().onGetActivityData(healthActivity);
                }
            }
        });
    }

    public static void a(final HealthBloodPressed healthBloodPressed, final List<HealthBloodPressedItem> list, final boolean z) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.17
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onGetBloodPressureData(healthBloodPressed, list, z);
                }
            }
        });
    }

    public static void a(final HealthHeartRate healthHeartRate, final List<HealthHeartRateItem> list, final boolean z) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.15
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onGetHeartRateData(healthHeartRate, list, z);
                }
            }
        });
    }

    public static void a(final HealthSleep healthSleep, final List<HealthSleepItem> list) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.16
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onGetSleepData(healthSleep, list);
                }
            }
        });
    }

    public static void a(final HealthSport healthSport, final List<HealthSportItem> list, final boolean z) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onGetSportData(healthSport, list, z);
                }
            }
        });
    }

    public static void b() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IActivityCallBack> it = b.K().C().iterator();
                while (it.hasNext()) {
                    it.next().onStart();
                }
            }
        });
    }

    public static void c() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IActivityCallBack> it = b.K().C().iterator();
                while (it.hasNext()) {
                    it.next().onStop();
                }
            }
        });
    }

    public static void d() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.9
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IActivityCallBack> it = b.K().C().iterator();
                while (it.hasNext()) {
                    it.next().onSuccess();
                }
            }
        });
    }

    public static void e() {
        Protocol.IS_SYNC_CONFIG = false;
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.14
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IConfigCallBack> it = b.K().D().iterator();
                while (it.hasNext()) {
                    it.next().onFailed();
                }
            }
        });
    }

    public static void f() {
        Protocol.IS_SYNC_CONFIG = true;
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.11
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IConfigCallBack> it = b.K().D().iterator();
                while (it.hasNext()) {
                    it.next().onStart();
                }
            }
        });
    }

    public static void g() {
        Protocol.IS_SYNC_CONFIG = false;
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.12
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IConfigCallBack> it = b.K().D().iterator();
                while (it.hasNext()) {
                    it.next().onStop();
                }
            }
        });
    }

    public static void h() {
        Protocol.IS_SYNC_CONFIG = false;
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.13
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IConfigCallBack> it = b.K().D().iterator();
                while (it.hasNext()) {
                    it.next().onSuccess();
                }
            }
        });
    }

    public static void i() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onFailed();
                }
            }
        });
    }

    public static void j() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onStart();
                }
            }
        });
    }

    public static void k() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onStop();
                }
            }
        });
    }

    public static void l() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IHealthCallBack> it = b.K().E().iterator();
                while (it.hasNext()) {
                    it.next().onSuccess();
                }
            }
        });
    }
}