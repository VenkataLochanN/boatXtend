package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.module.device.view.IPressureView;

/* JADX INFO: loaded from: classes2.dex */
public class PressurePresenter extends BaseMonitoringPresenter<IPressureView> {
    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        removeCallBack();
    }

    public boolean isSupportOverstressReminder() {
        return getSupportFunctionInfo().ex_table_main11_pressure_high_threshold_reminder;
    }

    public boolean isSupportSetRemindMode() {
        return getSupportFunctionInfo().V3_pressure_add_notify_flag_and_mode && isSupportOverstressReminder();
    }

    public PressureParam getLocalPressureParam() {
        PressureParam pressureParam = LocalDataManager.getPressureParam();
        if (pressureParam == null) {
            pressureParam = new PressureParam();
            pressureParam.onOff = 170;
            if (getSupportFunctionInfo().ex_table_main11_pressure_high_threshold_reminder) {
                pressureParam.remindOnOff = 85;
                pressureParam.startHour = 9;
                pressureParam.startMinute = 0;
                pressureParam.endHour = 18;
                pressureParam.endMinute = 0;
                pressureParam.highThreshold = 80;
                pressureParam.interval = 60;
                pressureParam.setWeekRepeat(new boolean[]{true, true, true, true, true, false, false});
            }
        }
        if (isSupportSetRemindMode() && pressureParam.notifyFlag == 0) {
            pressureParam.notifyFlag = 1;
        }
        return pressureParam;
    }

    public void sendPressure2Device(PressureParam pressureParam) {
        addCallBack();
        BLEManager.setPressureParam(pressureParam);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdSuccess(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdSuccess(settingType);
        if (settingType == OtherProtocolCallBack.SettingType.PRESSURE) {
            CommonLogUtil.d("sendPressure2Device onSetOtherCmdSuccess");
            if (isAttachView()) {
                ((IPressureView) getView()).onSetPressureModeSuccess();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdFailed(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdFailed(settingType);
        if (settingType == OtherProtocolCallBack.SettingType.PRESSURE) {
            CommonLogUtil.d("sendPressure2Device onSetOtherCmdFailed");
            if (isAttachView()) {
                ((IPressureView) getView()).onSetPressureModeFailed();
            }
        }
    }
}