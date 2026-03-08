package com.ido.life.ble;

import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BatteryInfo;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.ble.protocol.model.HIDInfo;
import com.ido.ble.protocol.model.LiveData;
import com.ido.ble.protocol.model.MacAddressInfo;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.NoticeSwitchInfo;
import com.ido.ble.protocol.model.SNInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.SystemTime;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.constants.Constants;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDeviceInfoCallback implements GetDeviceInfoCallBack.ICallBack {
    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetActivityCount(ActivityDataCount activityDataCount) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetBasicInfo(BasicInfo basicInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetBatteryInfo(BatteryInfo batteryInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetCanDownloadLangInfo(CanDownLangInfo canDownLangInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetCanDownloadLangInfoV3(CanDownLangInfoV3 canDownLangInfoV3) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetFlashBinInfo(FlashBinInfo flashBinInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetHIDInfo(HIDInfo hIDInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetLiveData(LiveData liveData) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetMacAddress(MacAddressInfo macAddressInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetNoticeCenterSwitchStatus(NoticeSwitchInfo noticeSwitchInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetNoticeReminderSwitchStatus(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetSNInfo(SNInfo sNInfo) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetTime(SystemTime systemTime) {
    }

    @Override // com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
    public void onGetDeviceSummarySoftVersionInfo(DeviceSummarySoftVersionInfo deviceSummarySoftVersionInfo) {
        if (deviceSummarySoftVersionInfo != null) {
            SPUtils.put(Constants.PERIPHERAL_VERSION, Integer.valueOf(deviceSummarySoftVersionInfo.peripheralsVersion));
            CommonLogUtil.printAndSave("onGetDeviceSummarySoftVersionInfo--->peripheral的值是" + deviceSummarySoftVersionInfo.peripheralsVersion);
            return;
        }
        SPUtils.put(Constants.PERIPHERAL_VERSION, 0);
        CommonLogUtil.printAndSave("peripheral的值是0");
    }
}