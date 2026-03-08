package com.ido.life.module.device.presenter;

import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.module.device.view.IHealthMonitoringView;
import com.ido.life.util.SPHelper;

/* JADX INFO: loaded from: classes2.dex */
public class HealthMonitoringPresenter extends BaseMonitoringPresenter<IHealthMonitoringView> {
    public boolean isSupportHeartRateDetection() {
        return getSupportFunctionInfo().heartRateMonitor || getSupportFunctionInfo().ex_main4_v3_hr_data;
    }

    public boolean isSupportPressureDetection() {
        return getSupportFunctionInfo().ex_main3_pressure || getSupportFunctionInfo().ex_main3_v3_pressure;
    }

    public boolean isSupportWaterClock() {
        return getSupportFunctionInfo().ex_main4_drink_water_reminder;
    }

    public boolean isSupportWalkReminder() {
        return getSupportFunctionInfo().walk_reminder;
    }

    public boolean isSupportMenstruation() {
        return getSupportFunctionInfo().ex_main3_menstruation;
    }

    public boolean isSupportBloodOxygenDetection() {
        return getSupportFunctionInfo().V3_support_set_spo2_all_day_on_off;
    }

    public boolean isSupportWashHandReminder() {
        return getSupportFunctionInfo().ex_table_main10_set_hand_washing_reminder;
    }

    public boolean isSupportScienceSleep() {
        return getSupportFunctionInfo().V3_support_set_scientific_sleep_switch;
    }

    public boolean isSupportVolume() {
        return getSupportFunctionInfo().V3_health_sync_noise;
    }

    public boolean getSleepMonitoringSwitch() {
        SleepMonitoringPara sleepMonitoringPara = LocalDataManager.getSleepMonitoringPara();
        if (sleepMonitoringPara == null) {
            sleepMonitoringPara = new SleepMonitoringPara();
            sleepMonitoringPara.mode = 170;
        }
        return sleepMonitoringPara.mode == 170;
    }

    public boolean getSmartHeartMonitoringSwitch() {
        return SPHelper.getSmartHeartRateMode().mode == 170;
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter
    protected void onGetHeartRateMode(int i) {
        if (isAttachView()) {
            ((IHealthMonitoringView) getView()).onGetHeartRateModeSuccess(i);
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter
    protected void onGetHeartRateModeV3(int i, int i2) {
        CommonLogUtil.printAndSave("V3返回onGetHeartRateModeV3 mode = " + i);
        super.onGetHeartRateModeV3(i, i2);
        if (isAttachView()) {
            ((IHealthMonitoringView) getView()).onGetHeartRateModeV3Success(i, i2);
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdSuccess(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdSuccess(settingType);
        if (isAttachView()) {
            int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[settingType.ordinal()];
            if (i == 1) {
                ((IHealthMonitoringView) getView()).onSetPressureModeSuccess();
            } else if (i == 2) {
                ((IHealthMonitoringView) getView()).onSetMenstrualSuccess();
            } else {
                if (i != 3) {
                    return;
                }
                ((IHealthMonitoringView) getView()).onSetBloodOxygenSuccess();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdFailed(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdFailed(settingType);
        if (isAttachView()) {
            int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[settingType.ordinal()];
            if (i == 1 || i == 2) {
                ((IHealthMonitoringView) getView()).onsetCmdFailed();
            }
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter, com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (isAttachView()) {
            int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
            if (i == 1) {
                ((IHealthMonitoringView) getView()).onSetWaterClockSuccess();
                return;
            }
            if (i == 2) {
                ((IHealthMonitoringView) getView()).onSetWalkReminderSuccess();
                return;
            }
            if (i == 3) {
                ((IHealthMonitoringView) getView()).onSetHeartRateModeSuccess();
                return;
            }
            if (i != 4) {
                if (i != 5) {
                    return;
                }
                ((IHealthMonitoringView) getView()).onSetScienceSleepSuccess();
                return;
            }
            CommonLogUtil.printAndSave("V3返回的o = " + obj.toString());
            HeartRateMeasureModeV3 heartRateMeasureModeV3 = (HeartRateMeasureModeV3) obj;
            CommonLogUtil.printAndSave("V3返回的(HeartRateMeasureModeV3) o = " + heartRateMeasureModeV3.toString());
            ((IHealthMonitoringView) getView()).onGetHeartRateModeV3Success(heartRateMeasureModeV3.mode, heartRateMeasureModeV3.measurementInterval);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.HealthMonitoringPresenter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType;
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType = new int[SettingCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.DRINK_WATER_REMINDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.WALK_REMINDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.SLEEP_MONITORING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType = new int[OtherProtocolCallBack.SettingType.values().length];
            try {
                $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[OtherProtocolCallBack.SettingType.PRESSURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[OtherProtocolCallBack.SettingType.MENSTRUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[OtherProtocolCallBack.SettingType.SPO2.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView()) {
            int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
            if (i == 1 || i == 2 || i == 3 || i == 4) {
                ((IHealthMonitoringView) getView()).onsetCmdFailed();
            }
        }
    }

    public boolean getVolumeReminderSwitch() {
        NoisePara noiseMode;
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        return (currentDeviceInfo == null || (noiseMode = SPHelper.getNoiseMode(currentDeviceInfo.mDeviceAddress)) == null || noiseMode.mode != 170) ? false : true;
    }
}