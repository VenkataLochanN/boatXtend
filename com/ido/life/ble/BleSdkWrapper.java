package com.ido.life.ble;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.AppExchangeDataCallBack;
import com.ido.ble.callback.V3AppExchangeDataCallBack;
import com.ido.ble.protocol.model.AppExchangeDataIngPara;
import com.ido.ble.protocol.model.AppExchangeDataPausePara;
import com.ido.ble.protocol.model.AppExchangeDataResumePara;
import com.ido.ble.protocol.model.AppExchangeDataStartPara;
import com.ido.ble.protocol.model.AppExchangeDataStopPara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPauseAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumeAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopAppReplyData;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.V3AppExchangeDataIngPara;
import com.ido.common.constant.LanguageRegion;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class BleSdkWrapper {
    public void a() {
        BLEManager.startSyncConfigInfo();
    }

    public static boolean isConnected() {
        return BLEManager.isConnected() && BLEManager.isBind();
    }

    public static boolean isBind() {
        return BLEManager.isBind();
    }

    public static void registerAppExchangeDataCallBack(AppExchangeDataCallBack.ICallBack iCallBack) {
        unregisterAppExchangeDataCallBack(iCallBack);
        BLEManager.registerAppExchangeDataCallBack(iCallBack);
    }

    public static void unregisterAppExchangeDataCallBack(AppExchangeDataCallBack.ICallBack iCallBack) {
        BLEManager.unregisterAppExchangeDataCallBack(iCallBack);
    }

    public static void registerV3AppExchangeDataCallBack(V3AppExchangeDataCallBack.ICallBack iCallBack) {
        unregisterV3AppExchangeDataCallBack(iCallBack);
        BLEManager.registerV3AppExchangeDataCallBack(iCallBack);
    }

    public static void unregisterV3AppExchangeDataCallBack(V3AppExchangeDataCallBack.ICallBack iCallBack) {
        BLEManager.unregisterV3AppExchangeDataCallBack(iCallBack);
    }

    public static void registerConnectCallBack(BaseConnCallback baseConnCallback) {
        unregisterConnectCallBack(baseConnCallback);
        BLEManager.registerConnectCallBack(baseConnCallback);
    }

    public static void unregisterConnectCallBack(BaseConnCallback baseConnCallback) {
        BLEManager.unregisterConnectCallBack(baseConnCallback);
    }

    public static void appSwitchDataStart(AppExchangeDataStartPara appExchangeDataStartPara) {
        BLEManager.appExchangeDataStart(appExchangeDataStartPara);
    }

    public static void v3AppSwitchDataStart(AppExchangeDataStartPara appExchangeDataStartPara) {
        BLEManager.appExchangeDataStart(appExchangeDataStartPara);
    }

    public static void appSwitchDataIng(AppExchangeDataIngPara appExchangeDataIngPara) {
        BLEManager.appExchangeDataIng(appExchangeDataIngPara);
    }

    public static void v3AppSwitchDataIng(V3AppExchangeDataIngPara v3AppExchangeDataIngPara) {
        BLEManager.v3AppExchangeDataIng(v3AppExchangeDataIngPara);
    }

    public static void getExChangeV3DataHeartRateInterval() {
        BLEManager.v3AppExchangeDataGetHeartRate();
    }

    public static void replyDeviceNoticeAppExchangeDataStop(DeviceNoticeAppExchangeDataStopAppReplyData deviceNoticeAppExchangeDataStopAppReplyData) {
        BLEManager.replyDeviceNoticeAppExchangeDataStop(deviceNoticeAppExchangeDataStopAppReplyData);
    }

    public static void appSwitchPause(int i, int i2, int i3, int i4) {
        AppExchangeDataPausePara appExchangeDataPausePara = new AppExchangeDataPausePara();
        appExchangeDataPausePara.day = i;
        appExchangeDataPausePara.hour = i2;
        appExchangeDataPausePara.minute = i3;
        appExchangeDataPausePara.second = i4;
        BLEManager.appExchangeDataPause(appExchangeDataPausePara);
    }

    public static void appSwitchRestore(int i, int i2, int i3, int i4) {
        AppExchangeDataResumePara appExchangeDataResumePara = new AppExchangeDataResumePara();
        appExchangeDataResumePara.day = i;
        appExchangeDataResumePara.hour = i2;
        appExchangeDataResumePara.minute = i3;
        appExchangeDataResumePara.second = i4;
        BLEManager.appExchangeDataResume(appExchangeDataResumePara);
    }

    public static void appSwitchDataEnd(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        AppExchangeDataStopPara appExchangeDataStopPara = new AppExchangeDataStopPara();
        appExchangeDataStopPara.day = i;
        appExchangeDataStopPara.hour = i2;
        appExchangeDataStopPara.minute = i3;
        appExchangeDataStopPara.second = i4;
        appExchangeDataStopPara.sport_type = i5;
        appExchangeDataStopPara.durations = i6;
        appExchangeDataStopPara.calories = i7;
        appExchangeDataStopPara.distance = i8;
        appExchangeDataStopPara.is_save = i9;
        BLEManager.appExchangeDataStop(appExchangeDataStopPara);
    }

    public static void v3AppSwitchDataEnd() {
        BLEManager.v3getEndActivityData();
    }

    public static void replyDeviceNoticeAppExchangeDataPause(DeviceNoticeAppExchangeDataPauseAppReplyData deviceNoticeAppExchangeDataPauseAppReplyData) {
        BLEManager.replyDeviceNoticeAppExchangeDataPause(deviceNoticeAppExchangeDataPauseAppReplyData);
    }

    public static void replyDeviceNoticeAppExchangeDataResume(DeviceNoticeAppExchangeDataResumeAppReplyData deviceNoticeAppExchangeDataResumeAppReplyData) {
        BLEManager.replyDeviceNoticeAppExchangeDataResume(deviceNoticeAppExchangeDataResumeAppReplyData);
    }

    public static boolean isDistUnitKm() {
        Units units = getUnits();
        return units == null || units.dist == 1;
    }

    public static Units getUnits() {
        Units units = LocalDataManager.getUnits();
        if (units == null) {
            units = new Units();
            boolean zEquals = Locale.getDefault().getLanguage().equals(LanguageRegion.ZH);
            units.dist = zEquals ? 1 : 2;
            units.weight = zEquals ? 1 : 2;
            units.temp = zEquals ? 1 : 2;
            units.stride = 75;
            units.language = zEquals ? 1 : 2;
            units.timeMode = 0;
        }
        return units;
    }

    public static String getBindMac() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        return currentDeviceInfo != null ? currentDeviceInfo.mDeviceAddress : "";
    }
}