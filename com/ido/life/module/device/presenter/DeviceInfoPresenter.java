package com.ido.life.module.device.presenter;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.business.multidevice.ICommonListener;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.AppBLEDevice;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.ble.BaseUnbindCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.DeviceInfo;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.api.entity.TopDialPlateEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.StepDayData;
import com.ido.life.module.bind.BindPresenter;
import com.ido.life.module.device.view.IDeviceInfoView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.BluetoothUtils;
import com.ido.life.util.DateUtil;
import com.ido.life.util.DialDefinedUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfoPresenter extends BindPresenter<IDeviceInfoView> {
    private boolean executeTask;
    private DeviceListEntity.DeviceInfo mDeviceInfo;
    private Timer mTimer;
    private final BaseDeviceInfoCallback mDeviceInfoCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.1
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFlashBinInfo(FlashBinInfo flashBinInfo) {
            super.onGetFlashBinInfo(flashBinInfo);
            if (flashBinInfo != null) {
                if (flashBinInfo.version == flashBinInfo.matchVersion && flashBinInfo.status == 0) {
                    return;
                }
                DeviceInfoPresenter.this.requestFlashInfo(flashBinInfo.matchVersion);
            }
        }

        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetBasicInfo(BasicInfo basicInfo) {
            if (!DeviceInfoPresenter.this.isAttachView() || basicInfo == null) {
                return;
            }
            ((IDeviceInfoView) DeviceInfoPresenter.this.getView()).onGetBattery(basicInfo.energe);
        }
    };
    private final BaseUnbindCallback mUnbindCallback = new BaseUnbindCallback() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.8
        @Override // com.ido.life.ble.BaseUnbindCallback, com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onSuccess() {
            if (DeviceInfoPresenter.this.mDeviceInfo == null) {
                DeviceInfoPresenter deviceInfoPresenter = DeviceInfoPresenter.this;
                deviceInfoPresenter.mDeviceInfo = new DeviceListEntity.DeviceInfo(deviceInfoPresenter.getDeviceInfo());
            }
            BluetoothUtils.INSTANCE.removeBondDevice(DeviceInfoPresenter.this.mDeviceInfo.getMac());
            SPHelper.removeDevice(DeviceInfoPresenter.this.mDeviceInfo);
            BLEManager.unregisterUnbindCallBack(DeviceInfoPresenter.this.mUnbindCallback);
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                DeviceManager.deleteDevice(DeviceInfoPresenter.this.mDeviceInfo.getMac(), null);
            }
            BLEManager.removeBondStatusFromPhoneBluetoothPairedList(DeviceInfoPresenter.this.mDeviceInfo.getMac());
            if (DeviceInfoPresenter.this.isAttachView()) {
                ((IDeviceInfoView) DeviceInfoPresenter.this.getView()).onDeleteDeviceSuccess();
            }
        }

        @Override // com.ido.life.ble.BaseUnbindCallback, com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onFailed() {
            super.onFailed();
            BLEManager.unregisterUnbindCallBack(DeviceInfoPresenter.this.mUnbindCallback);
            if (DeviceInfoPresenter.this.isAttachView()) {
                ((IDeviceInfoView) DeviceInfoPresenter.this.getView()).onDeleteDeviceFailed();
            }
        }
    };

    public int getBatteryIconResByPower(int i) {
        return i >= 100 ? R.mipmap.icon_battery_5 : i > 60 ? R.mipmap.icon_battery_4 : i > 30 ? R.mipmap.icon_battery_3 : i > 10 ? R.mipmap.icon_battery_2 : R.mipmap.icon_battery_1;
    }

    @Override // com.ido.life.module.bind.BindPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
    }

    public boolean isSupportCallAndRemind() {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.V3_support_sync_contact;
    }

    public void queryDeviceInfo() {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
            if (deviceList.isEmpty()) {
                return;
            }
            String[] strArr = new String[deviceList.size()];
            for (int i = 0; i < deviceList.size(); i++) {
                DeviceListEntity.DeviceInfo deviceInfo = deviceList.get(i);
                if (deviceInfo != null) {
                    strArr[i] = deviceInfo.getDeviceId();
                }
            }
            DeviceManager.queryDeviceInfoList(new DeviceManager.OnDeviceCallback<List<DeviceInfo>>() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.2
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(List<DeviceInfo> list) {
                    SPHelper.saveDeviceInfoList(list);
                    EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_REQUEST_DEVICE_INFO_COMPLETE, true));
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i2, String str) {
                    CommonLogUtil.d("queryDeviceInfo onFailed ：" + str);
                }
            }, strArr);
        }
    }

    public String getDeviceImageUrl(String str) {
        for (DeviceInfo deviceInfo : SPHelper.getDeviceInfoList()) {
            if (deviceInfo != null && str.equals(deviceInfo.getDeviceId())) {
                if (TextUtils.isEmpty(deviceInfo.getImageUrl2())) {
                    return deviceInfo.getImageUrl();
                }
                return deviceInfo.getImageUrl2();
            }
        }
        return "";
    }

    public void getFlashInfoFromDevice(DeviceListEntity.DeviceInfo deviceInfo) {
        if (deviceInfo != null && getDeviceInfo().mDeviceAddress.equals(deviceInfo.getMac()) && isConnected()) {
            BLEManager.getFlashBinInfo();
        }
    }

    public void startGetBatteryTask() {
        this.executeTask = true;
        if (this.mTimer != null) {
            return;
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (DeviceInfoPresenter.this.executeTask && DeviceInfoPresenter.this.isBindAndConnected()) {
                    if (HomeFragmentPresenter.mIsSyncing || HomeFragmentPresenter.mDeviceUpgrading) {
                        DeviceInfoPresenter.this.logP("正在同步或ota，不刷新基本信息, mIsSyncing = " + HomeFragmentPresenter.mIsSyncing + ", mDeviceUpgrading = " + HomeFragmentPresenter.mDeviceUpgrading);
                        return;
                    }
                    BLEManager.getBasicInfo();
                }
            }
        }, BootloaderScanner.TIMEOUT, DateUtil.MINUTE);
    }

    public void pauseGetBatteryTask() {
        this.executeTask = false;
    }

    public boolean isSupportHealthMonitoring() {
        return getSupportFunctionInfo().heartRateMonitor || getSupportFunctionInfo().ex_main4_v3_hr_data || getSupportFunctionInfo().ex_main3_pressure || getSupportFunctionInfo().ex_main3_v3_pressure || getSupportFunctionInfo().ex_main4_drink_water_reminder || getSupportFunctionInfo().walk_reminder || getSupportFunctionInfo().ex_main3_menstruation;
    }

    public boolean isSupportAlarm() {
        return getSupportFunctionInfo().alarmClock || getSupportFunctionInfo().ex_table_main8_v3_sync_alarm;
    }

    public void requestFirmwareInfo(DeviceListEntity.DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            saveOtaLog("requestFirmwareInfo， device is null");
            return;
        }
        if (!deviceInfo.getMac().equals(getDeviceInfo().mDeviceAddress)) {
            saveOtaLog("requestFirmwareInfo， is not current device");
            return;
        }
        if (!getSupportFunctionInfo().deviceUpdate) {
            saveOtaLog("requestFirmwareInfo，device not support deviceUpdate");
        } else if (!isBindAndConnected()) {
            saveOtaLog("requestFirmwareInfo，not not bind or Connected");
        } else {
            DeviceManager.checkFirmwareInfo(getAppBleDevice(), new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.4
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                    DeviceInfoPresenter.this.saveOtaLog("requestFirmwareInfo，onSuccess ：" + otaInfo);
                    if (!DeviceInfoPresenter.this.isAttachView() || otaInfo == null) {
                        return;
                    }
                    try {
                        if (otaInfo.getVersion() != null) {
                            if (otaInfo.getVersion().contains(".")) {
                                if (!otaInfo.getVersion().equals(BaseCmdPresenter.deviceThirdVersion)) {
                                    HomeFragmentPresenter.hasNewDeviceVersion = true;
                                    ((IDeviceInfoView) DeviceInfoPresenter.this.getView()).onRequestedNewOtaInfo();
                                }
                            } else if (Integer.parseInt(otaInfo.getVersion()) > DeviceInfoPresenter.this.getDeviceInfo().version) {
                                HomeFragmentPresenter.hasNewDeviceVersion = true;
                                ((IDeviceInfoView) DeviceInfoPresenter.this.getView()).onRequestedNewOtaInfo();
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str) {
                    DeviceInfoPresenter.this.saveOtaLog("requestFirmwareInfo onFailed ：" + str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveOtaLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath().concat(getDeviceInfo().mDeviceId + "/"), "DeviceUpgradeNewPresenter", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFlashInfo(int i) {
        if (!getSupportFunctionInfo().deviceUpdate) {
            saveOtaLog("requestFlashInfo，device not support deviceUpdate");
        } else {
            DeviceManager.checkFlashInfo(getAppBleDevice(), i, new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.5
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                    DeviceInfoPresenter.this.saveOtaLog("requestFlashInfo onSuccess otaInfo ：" + otaInfo);
                    if (!DeviceInfoPresenter.this.isAttachView() || otaInfo == null) {
                        return;
                    }
                    try {
                        HomeFragmentPresenter.hasNewDeviceVersion = true;
                        ((IDeviceInfoView) DeviceInfoPresenter.this.getView()).onRequestedNewOtaInfo();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i2, String str) {
                    DeviceInfoPresenter.this.saveOtaLog("requestFlashInfo onFailed ：" + str);
                }
            });
        }
    }

    public boolean isCurrentConnectedDevice(String str) {
        BLEDevice currentDeviceInfo;
        return !TextUtils.isEmpty(str) && BLEManager.isConnected() && (currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo()) != null && str.equals(currentDeviceInfo.mDeviceAddress);
    }

    public boolean isCurrentBindDevice(String str) {
        BLEDevice currentDeviceInfo;
        return BLEManager.isBind() && (currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo()) != null && str.equals(currentDeviceInfo.mDeviceAddress);
    }

    public int getDeviceBattery() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            return basicInfo.energe;
        }
        return 0;
    }

    public void saveDeviceInfo(DeviceListEntity.DeviceInfo deviceInfo) {
        SPHelper.saveDevice(deviceInfo);
    }

    public HealthSport getTodayHealthSportData() {
        int[] currentDate = DateUtil.getCurrentDate();
        HealthSport healthSportByDay = LocalDataManager.getHealthSportByDay(currentDate[0], currentDate[1], currentDate[2]);
        return healthSportByDay == null ? new HealthSport() : healthSportByDay;
    }

    public StepDayData getTodayStep() {
        return LocalHealthDataManager.getInstance().getStepDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
    }

    public CalorieDayData getTodayCalories() {
        return LocalHealthDataManager.getInstance().getCalorieDailyDataByDate(RunTimeUtil.getInstance().getUserId(), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
    }

    public int getTodayLastHeartRate() {
        ServerHeartRateDayData todayLatestHeartRateDailyData = LocalHealthDataManager.getInstance().getTodayLatestHeartRateDailyData(getDeviceInfo().mDeviceAddress);
        if (todayLatestHeartRateDailyData == null) {
            return 0;
        }
        return todayLatestHeartRateDailyData.getLatestValue();
    }

    public void requestTopDialPlate(DeviceListEntity.DeviceInfo deviceInfo) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DeviceInfoPresenter", "requestTopDialPlate deviceInfo : " + deviceInfo);
        if (deviceInfo == null) {
            return;
        }
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DeviceInfoPresenter", "requestTopDialPlate network error");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceName())) {
            String str = getDeviceInfo().mDeviceName;
        }
        final BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo == null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DeviceInfoPresenter", "requestTopDialPlate, basicInfo is null");
        } else {
            DialDefinedUtil.getDialDefinedVersion(new DeviceManager.OnDeviceCallback<String>() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.6
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(String str2) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DeviceInfoPresenter", "get appFaceVersion onSuccess : " + str2);
                    DeviceInfoPresenter deviceInfoPresenter = DeviceInfoPresenter.this;
                    deviceInfoPresenter.requestTopDial(deviceInfoPresenter.getAppBleDevice(), str2, DialDefinedUtil.appFaceVersion);
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str2) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DeviceInfoPresenter", "get appFaceVersion onFailed : " + str2);
                    DeviceInfoPresenter deviceInfoPresenter = DeviceInfoPresenter.this;
                    deviceInfoPresenter.requestTopDial(deviceInfoPresenter.getAppBleDevice(), String.valueOf(basicInfo.user_defined_dial_main_version), DialDefinedUtil.appFaceVersion);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<TopDialPlateEntity.DialPlate> formatBuiltinDialName(List<TopDialPlateEntity.DialPlate> list) {
        if (list != null && !list.isEmpty()) {
            int i = 1;
            for (TopDialPlateEntity.DialPlate dialPlate : list) {
                if (dialPlate != null && TextUtils.equals(dialPlate.getFaceType(), BaseDialPresenter.DIAL_TYPE_BUILTIN)) {
                    dialPlate.setOtaFaceName(BaseDialPresenter.BUILT_IN_DIAL_NAME_START.concat(String.valueOf(i)));
                    i++;
                }
            }
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestTopDial(AppBLEDevice appBLEDevice, String str, String str2) {
        DeviceManager.requestTopDialPlate(appBLEDevice, LanguageUtil.getSystemLanguage(), str2, str, new DeviceManager.OnDeviceCallback<List<TopDialPlateEntity.DialPlate>>() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.7
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<TopDialPlateEntity.DialPlate> list) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DeviceInfoPresenter", "requestTopDialPlate onSuccess，dialPlates ：" + GsonUtil.toJson(list));
                if (DeviceInfoPresenter.this.isAttachView()) {
                    ((IDeviceInfoView) DeviceInfoPresenter.this.getView()).onRequestDialSuccess(DeviceInfoPresenter.this.formatBuiltinDialName(list));
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str3) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DeviceInfoPresenter", "requestTopDialPlate onFailed = " + i + "___" + str3);
            }
        });
    }

    public void deleteDevice(DeviceListEntity.DeviceInfo deviceInfo) {
        this.mDeviceInfo = deviceInfo;
        saveBindLog("deleteDevice deviceInfo : " + deviceInfo);
        BLEManager.registerUnbindCallBack(this.mUnbindCallback);
        if (BLEManager.isConnected()) {
            if (this.mDeviceInfo.getMac().equals(getDeviceInfo().mDeviceAddress)) {
                saveBindLog("deleteDevice，unbind start");
                BLEManager.unbind(this.mDeviceInfo.getMac(), new ICommonListener() { // from class: com.ido.life.module.device.presenter.DeviceInfoPresenter.9
                    @Override // com.ido.ble.business.multidevice.ICommonListener
                    public void onSuccess(String str) {
                        DeviceInfoPresenter.this.saveBindLog("unbind onSuccess ：" + str);
                        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LIST_CHANGED, str));
                    }

                    @Override // com.ido.ble.business.multidevice.ICommonListener
                    public void onFailed(String str) {
                        DeviceInfoPresenter.this.saveBindLog("unbind onFailed ：" + str);
                    }
                });
                return;
            }
            saveBindLog("deleteDevice，forceUnbind mac=" + deviceInfo.getMac());
            BLEManager.forceUnbind(deviceInfo.getMac());
            EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LIST_CHANGED, deviceInfo.getMac()));
            return;
        }
        saveBindLog("deleteDevice，forceUnbind mac=" + deviceInfo.getMac());
        BLEManager.forceUnbind(deviceInfo.getMac());
        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LIST_CHANGED, deviceInfo.getMac()));
    }

    @Override // com.ido.life.module.bind.BindPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    public SwitchStatus getSwitchStatus() {
        return SPHelper.getSwitchStatus();
    }
}