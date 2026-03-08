package com.ido.life.base;

import com.ido.ble.callback.GetDeviceParaCallBack;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.ble.protocol.model.AllHealthMonitorSwitch;
import com.ido.ble.protocol.model.BtA2dpHfpStatus;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DeviceUpgradeState;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.PressCalibrationValue;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.SupportSportInfoV3;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.WalkReminder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDeviceParaCallBack implements GetDeviceParaCallBack.ICallBack {
    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetActivitySwitch(ActivitySwitch activitySwitch) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetAlarmV3(List<AlarmV3> list) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetAllHealthMonitorSwitch(AllHealthMonitorSwitch allHealthMonitorSwitch) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetBtA2dpHfpStatus(BtA2dpHfpStatus btA2dpHfpStatus) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetContactReceiveTime(boolean z) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetDeviceUpgradeState(DeviceUpgradeState deviceUpgradeState) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetDoNotDisturbPara(NotDisturbPara notDisturbPara) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetFirmwareAndBt3Version(FirmwareAndBt3Version firmwareAndBt3Version) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetMenuList(MenuList.DeviceReturnInfo deviceReturnInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetPressCalibrationValue(PressCalibrationValue pressCalibrationValue) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetScheduleReminderV3(List<ScheduleReminderV3> list) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetSportThreeCircleGoal(CalorieAndDistanceGoal calorieAndDistanceGoal, String str) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetSupportSportInfoV3(SupportSportInfoV3 supportSportInfoV3) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetUpHandGesture(UpHandGesture upHandGesture) {
    }

    @Override // com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
    public void onGetWalkReminder(WalkReminder walkReminder) {
    }
}