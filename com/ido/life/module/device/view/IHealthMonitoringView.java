package com.ido.life.module.device.view;

/* JADX INFO: loaded from: classes2.dex */
public interface IHealthMonitoringView extends IBaseMonitoringView {
    void onGetHeartRateModeSuccess(int i);

    void onGetHeartRateModeV3Success(int i, int i2);

    void onSetBloodOxygenSuccess();

    void onSetFitnessGuidanceSuccess();

    void onSetHeartRateModeSuccess();

    void onSetMenstrualSuccess();

    void onSetPressureModeSuccess();

    void onSetScienceSleepSuccess();

    void onSetSmartHeartRateModeSuccess();

    void onSetWalkReminderSuccess();

    void onSetWaterClockSuccess();

    void onsetCmdFailed();
}