package com.ido.life.module.device.view;

import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.HeartRateSmartMode;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IHeartRateView extends IBaseMonitoringView {
    void onGetHeartRateHighReminder(int i);

    void onGetHeartRateLowReminder(int i);

    void onGetHeartRateMeasureModeV3(HeartRateMeasureModeV3 heartRateMeasureModeV3);

    void onGetHeartRateModeFailed();

    void onGetHeartRateModeSuccess(int i);

    void onGetHeartRateModeV3Success(int i, int i2);

    void onGetHeartRateRemindValues(List<String> list, List<String> list2);

    void onGetSmartHeartRateMode(HeartRateSmartMode heartRateSmartMode);

    void onSetHeartRateModeFailed();

    void onSetHeartRateModeSuccess();

    void onSetSmartHeartRateModeFailed();

    void onSetSmartHeartRateModeSuccess();
}