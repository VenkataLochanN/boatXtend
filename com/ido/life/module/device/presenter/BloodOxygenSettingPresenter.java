package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.module.device.view.IBloodOxygenView;

/* JADX INFO: loaded from: classes2.dex */
public class BloodOxygenSettingPresenter extends BaseMonitoringPresenter<IBloodOxygenView> {
    public boolean isSupportLowValueRemind() {
        return getSupportFunctionInfo().V3_support_set_spo2_low_value_remind;
    }

    public boolean isSupportAllDayOnOff() {
        return getSupportFunctionInfo().V3_support_set_spo2_all_day_on_off;
    }

    public boolean isSupportSetBloodOxygenNotifyFlag() {
        return getSupportFunctionInfo().V3_spo2_add_notify_flag;
    }

    public SPO2Param getLocationBloodOxyData() {
        SPO2Param spO2Param = LocalDataManager.getSpO2Param();
        if (spO2Param == null) {
            spO2Param = new SPO2Param();
            spO2Param.notifyFlag = 1;
            spO2Param.onOff = 85;
            spO2Param.lowSpo2OnOff = 85;
            spO2Param.lowSpo2OnValue = 75;
        }
        if (isSupportSetBloodOxygenNotifyFlag() && spO2Param.notifyFlag == 0) {
            spO2Param.notifyFlag = 1;
        }
        CommonLogUtil.d("getLocationBloodOxyData spo2Param = " + spO2Param);
        return spO2Param;
    }

    public void setBloodOxy2Device(SPO2Param sPO2Param) {
        sPO2Param.startHour = 0;
        sPO2Param.startMinute = 0;
        sPO2Param.endHour = 23;
        sPO2Param.endMinute = 59;
        CommonLogUtil.d("setBloodOxy2Device spo2Param = " + sPO2Param);
        BLEManager.setSPO2Param(sPO2Param);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdSuccess(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdSuccess(settingType);
        if (settingType == OtherProtocolCallBack.SettingType.SPO2) {
            CommonLogUtil.d("setBloodOxy2Device onSetOtherCmdSuccess ");
            if (isAttachView()) {
                ((IBloodOxygenView) getView()).onBloodSettingSuccess();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdFailed(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdFailed(settingType);
        if (settingType == OtherProtocolCallBack.SettingType.SPO2) {
            CommonLogUtil.d("setBloodOxy2Device onSetOtherCmdFailed ");
            if (isAttachView()) {
                ((IBloodOxygenView) getView()).onBloodSettingFailed();
            }
        }
    }
}