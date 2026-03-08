package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.life.module.device.view.IWaterClockView;

/* JADX INFO: loaded from: classes2.dex */
public class WaterClockPresenter extends BaseMonitoringPresenter<IWaterClockView> {
    public DrinkWaterReminder getWaterClockState() {
        DrinkWaterReminder drinkWaterReminder = LocalDataManager.getDrinkWaterReminder();
        if (drinkWaterReminder != null) {
            return drinkWaterReminder;
        }
        DrinkWaterReminder drinkWaterReminder2 = new DrinkWaterReminder();
        drinkWaterReminder2.setStartHour(9);
        drinkWaterReminder2.setStartMinute(0);
        drinkWaterReminder2.setEndHour(18);
        drinkWaterReminder2.setEndMinute(0);
        drinkWaterReminder2.setInterval(30);
        drinkWaterReminder2.setWeeks(new boolean[]{true, true, true, true, true, false, false});
        return drinkWaterReminder2;
    }

    public void sendWaterClock2Device(DrinkWaterReminder drinkWaterReminder) {
        BLEManager.setDrinkWaterReminder(drinkWaterReminder);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView() && settingType == SettingCallBack.SettingType.DRINK_WATER_REMINDER) {
            ((IWaterClockView) getView()).onSetWaterClockFailed();
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter, com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.DRINK_WATER_REMINDER && isAttachView()) {
            ((IWaterClockView) getView()).onSetWaterClockSuccess();
        }
    }
}