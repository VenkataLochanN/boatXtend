package com.ido.life.base;

import kotlin.Metadata;

/* JADX INFO: compiled from: IDeviceDataListener.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/ido/life/base/IDeviceDataListener;", "", "syncActiveDataSuccess", "", "syncBloodDataSuccess", "syncHeartRateDataSuccess", "syncPressureDataSuccess", "syncSleepDataSuccess", "syncSportDataSuccess", "syncSwimmingDataSuccess", "syncVolumeDataSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IDeviceDataListener {
    void syncActiveDataSuccess();

    void syncBloodDataSuccess();

    void syncHeartRateDataSuccess();

    void syncPressureDataSuccess();

    void syncSleepDataSuccess();

    void syncSportDataSuccess();

    void syncSwimmingDataSuccess();

    void syncVolumeDataSuccess();
}