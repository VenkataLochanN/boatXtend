package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.AppBLEDevice;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.DeviceInfo;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.bind.BindPresenter;
import com.ido.life.module.device.view.IDeviceView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.BluetoothUtils;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DeviceUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceFragmentPresenter extends BindPresenter<IDeviceView> {
    private boolean isRequestDeviceInfo;
    private Map<String, String> mDeviceImageMap;
    private List<DeviceListEntity.DeviceInfo> mDeviceList = new ArrayList();
    public final Handler mHandler = new Handler();
    private int retryCount = 0;
    private final BaseDeviceInfoCallback mDeviceInfoCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.device.presenter.DeviceFragmentPresenter.1
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetBasicInfo(BasicInfo basicInfo) {
            DeviceFragmentPresenter.this.refreshDeviceList();
        }
    };
    private BaseDeviceParaCallBack mSystemConstituentCallback = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.DeviceFragmentPresenter.3
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetFirmwareAndBt3Version(FirmwareAndBt3Version firmwareAndBt3Version) {
            super.onGetFirmwareAndBt3Version(firmwareAndBt3Version);
            DeviceFragmentPresenter.this.setDeviceLog("onGetFirmwareAndBt3Version succes:");
            DeviceFragmentPresenter.this.retryCount = 0;
            DeviceFragmentPresenter.this.mHandler.removeCallbacks(DeviceFragmentPresenter.this.mGetSystemConstituentTimeoutRunnable);
            BLEManager.unregisterGetDeviceParaCallBack(DeviceFragmentPresenter.this.mSystemConstituentCallback);
            BaseCmdPresenter.deviceThirdVersion = DeviceUtil.getDeviceThirdVersion(firmwareAndBt3Version);
            SPHelper.saveDeviceThirdVersion(BaseCmdPresenter.deviceThirdVersion);
            DeviceFragmentPresenter.this.refreshDeviceList();
        }
    };
    private final Runnable mGetSystemConstituentTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceFragmentPresenter$ZnC81oJE9UojhT3TMmeblOxY9RM
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$DeviceFragmentPresenter();
        }
    };

    public int getBatteryIconResByPower(int i) {
        return i >= 100 ? R.mipmap.icon_battery_5 : i > 60 ? R.mipmap.icon_battery_4 : i > 30 ? R.mipmap.icon_battery_3 : i > 10 ? R.mipmap.icon_battery_2 : R.mipmap.icon_battery_1;
    }

    @Override // com.ido.life.module.bind.BindPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        setDeviceInfoCacheData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceInfoCacheData() {
        if (this.mDeviceImageMap == null) {
            this.mDeviceImageMap = new HashMap();
        }
        List<DeviceInfo> deviceInfoList = SPHelper.getDeviceInfoList();
        if (deviceInfoList == null) {
            return;
        }
        for (DeviceInfo deviceInfo : deviceInfoList) {
            if (deviceInfo != null) {
                if (TextUtils.isEmpty(deviceInfo.getImageUrl2())) {
                    this.mDeviceImageMap.put(deviceInfo.getDeviceId(), deviceInfo.getImageUrl());
                } else {
                    this.mDeviceImageMap.put(deviceInfo.getDeviceId(), deviceInfo.getImageUrl2());
                }
            }
        }
    }

    public boolean isSupportAlex() {
        String deviceFragmentLogPath = LogPathImpl.getInstance().getDeviceFragmentLogPath();
        StringBuilder sb = new StringBuilder();
        sb.append("Alexa显示状态：mDeviceList = ");
        List<DeviceListEntity.DeviceInfo> list = this.mDeviceList;
        sb.append(list == null ? "null" : list.toString());
        CommonLogUtil.printAndSave(deviceFragmentLogPath, sb.toString());
        boolean z = false;
        for (DeviceListEntity.DeviceInfo deviceInfo : this.mDeviceList) {
            if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getMac())) {
                SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(deviceInfo.getMac());
                String deviceFragmentLogPath2 = LogPathImpl.getInstance().getDeviceFragmentLogPath();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Alexa显示状态：");
                sb2.append(deviceInfo.getMac());
                sb2.append(" isSupportAlexa = ");
                sb2.append(supportFunctionInfo == null ? "null" : Boolean.valueOf(supportFunctionInfo.ex_table_main7_voice_transmission));
                CommonLogUtil.printAndSave(deviceFragmentLogPath2, sb2.toString());
                if (supportFunctionInfo != null && supportFunctionInfo.ex_table_main7_voice_transmission) {
                    z = true;
                }
            } else {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceFragmentLogPath(), "Alexa显示状态：getMac = 空");
            }
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceFragmentLogPath(), "Alexa显示状态：support = " + z);
        return z;
    }

    public boolean isSupportAlexConnected() {
        SupportFunctionInfo supportFunctionInfo;
        if (!BLEManager.isConnected() || (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) == null) {
            return false;
        }
        return supportFunctionInfo.ex_table_main7_voice_transmission;
    }

    public boolean isCurrentDeviceSupportAlex() {
        BLEDevice currentDeviceInfo;
        SupportFunctionInfo supportFunctionInfo;
        if (!BLEManager.isConnected() || (currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo()) == null || TextUtils.isEmpty(currentDeviceInfo.mDeviceAddress) || (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo(currentDeviceInfo.mDeviceAddress)) == null) {
            return false;
        }
        return supportFunctionInfo.ex_table_main7_voice_transmission;
    }

    public String getDeviceImageUrl(String str) {
        Map<String, String> map = this.mDeviceImageMap;
        return (map != null && map.containsKey(str)) ? this.mDeviceImageMap.get(str) : "";
    }

    public boolean isLogin() {
        return SPHelper.isLogin();
    }

    public void getDeviceList() {
        List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
        formatDeviceList(deviceList);
        queryDeviceInfo(deviceList);
    }

    private void queryDeviceInfo(List<DeviceListEntity.DeviceInfo> list) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext()) || this.isRequestDeviceInfo || list == null || list.isEmpty()) {
            return;
        }
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            DeviceListEntity.DeviceInfo deviceInfo = list.get(i);
            if (deviceInfo != null) {
                strArr[i] = deviceInfo.getDeviceId();
            }
        }
        this.isRequestDeviceInfo = true;
        DeviceManager.queryDeviceInfoList(new DeviceManager.OnDeviceCallback<List<DeviceInfo>>() { // from class: com.ido.life.module.device.presenter.DeviceFragmentPresenter.2
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<DeviceInfo> list2) {
                DeviceFragmentPresenter.this.isRequestDeviceInfo = false;
                SPHelper.saveDeviceInfoList(list2);
                DeviceFragmentPresenter.this.setDeviceInfoCacheData();
                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_REQUEST_DEVICE_INFO_COMPLETE, true));
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i2, String str) {
                DeviceFragmentPresenter.this.isRequestDeviceInfo = false;
                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_REQUEST_DEVICE_INFO_COMPLETE, false));
            }
        }, strArr);
    }

    private void formatDeviceList(List<DeviceListEntity.DeviceInfo> list) {
        this.mDeviceList.clear();
        BLEDevice currentDeviceInfo = isBind() ? LocalDataManager.getCurrentDeviceInfo() : null;
        if (list != null) {
            for (DeviceListEntity.DeviceInfo deviceInfo : list) {
                if (deviceInfo != null) {
                    if (deviceInfo.type == 2 || deviceInfo.type == 3) {
                        deviceInfo.type = 3;
                    } else {
                        deviceInfo.type = 4;
                    }
                    if (currentDeviceInfo == null) {
                        this.mDeviceList.add(deviceInfo);
                    } else if (currentDeviceInfo.mDeviceAddress.equals(deviceInfo.getMac())) {
                        this.mDeviceList.add(0, deviceInfo);
                    } else {
                        this.mDeviceList.add(deviceInfo);
                    }
                }
            }
        }
        if (currentDeviceInfo != null && (this.mDeviceList.size() == 0 || !this.mDeviceList.get(0).getMac().equals(currentDeviceInfo.mDeviceAddress))) {
            currentDeviceInfo.type = currentDeviceInfo.type == 2 ? 3 : 4;
            this.mDeviceList.add(0, new DeviceListEntity.DeviceInfo(currentDeviceInfo));
        }
        if (isAttachView()) {
            ((IDeviceView) getView()).onGetDeviceList(this.mDeviceList, true);
        }
    }

    public boolean isCurrentConnectedDevice(String str) {
        BLEDevice currentDeviceInfo;
        return !TextUtils.isEmpty(str) && BLEManager.isConnected() && (currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo()) != null && str.equals(currentDeviceInfo.mDeviceAddress);
    }

    public void refreshDeviceList() {
        if (isAttachView()) {
            setDeviceInfoCacheData();
            AppBLEDevice appBleDevice = isBindAndConnected() ? getAppBleDevice() : null;
            if (appBleDevice != null) {
                int i = 0;
                while (true) {
                    if (i >= this.mDeviceList.size()) {
                        break;
                    }
                    DeviceListEntity.DeviceInfo deviceInfo = this.mDeviceList.get(i);
                    if (deviceInfo != null) {
                        if (deviceInfo.type == 2 || deviceInfo.type == 3) {
                            deviceInfo.type = 3;
                        } else {
                            deviceInfo.type = 4;
                        }
                        if (deviceInfo.getMac().equals(appBleDevice.mDeviceAddress)) {
                            this.mDeviceList.remove(i);
                            DeviceListEntity.DeviceInfo deviceInfo2 = new DeviceListEntity.DeviceInfo(appBleDevice);
                            deviceInfo2.setCustomName(deviceInfo.getCustomName());
                            deviceInfo2.type = deviceInfo.type;
                            deviceInfo2.setOsVersion(appBleDevice.getDeviceThirdVersion());
                            this.mDeviceList.add(0, deviceInfo2);
                            if (TextUtils.isEmpty(deviceInfo.getDeviceName())) {
                                SPHelper.saveDevice(deviceInfo2);
                            }
                        }
                    }
                    i++;
                }
            }
            ((IDeviceView) getView()).onGetDeviceList(this.mDeviceList, false);
        }
    }

    public void getBasicInfo() {
        if (isConnected() && !HomeFragmentPresenter.mIsSyncing) {
            BLEManager.getBasicInfo();
            BLEManager.getFunctionTables();
        }
        logP("设备未连接或正在同步中，不刷新基本信息！");
    }

    public int getDeviceBattery() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            return basicInfo.energe;
        }
        return 0;
    }

    public void removeDevice(DeviceListEntity.DeviceInfo deviceInfo) {
        BLEManager.forceUnbind(deviceInfo.getMac());
        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LIST_CHANGED, deviceInfo.getMac()));
        SPHelper.removeDevice(deviceInfo);
        BluetoothUtils.INSTANCE.removeBondDevice(deviceInfo.getMac());
        if (isAttachView()) {
            ((IDeviceView) getView()).onDeleteDeviceSuccess(deviceInfo.getMac());
        }
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            DeviceManager.deleteDevice(deviceInfo.getMac(), null);
        }
        removeDeviceFromList(deviceInfo.getMac());
        if (BLEManager.isBind()) {
            return;
        }
        List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
        if (deviceList.isEmpty()) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "DeviceFragmentPresenter BLEManager.autoConnect");
        String mac = deviceList.get(0).getMac();
        BLEManager.autoConnect(mac);
        ConnectLogHelper.saveLog("DeviceFragmentPresenter", "autoConnect(".concat(mac).concat(")"));
    }

    private void removeDeviceFromList(String str) {
        Iterator<DeviceListEntity.DeviceInfo> it = this.mDeviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceListEntity.DeviceInfo next = it.next();
            if (next != null && str.equals(next.getMac())) {
                this.mDeviceList.remove(next);
                break;
            }
        }
        refreshDeviceList();
    }

    @Override // com.ido.life.module.bind.BindPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
    }

    public String getDeviceThirdVersion() {
        if (getSupportFunctionInfo().V3_v2_02_EB_firmware_bt_version_01_create) {
            setDeviceLog("support 三级版本号");
            this.mHandler.postDelayed(this.mGetSystemConstituentTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
            BLEManager.unregisterGetDeviceParaCallBack(this.mSystemConstituentCallback);
            BLEManager.registerGetDeviceParaCallBack(this.mSystemConstituentCallback);
            BLEManager.getFirmwareAndBt3Version();
            return "";
        }
        return String.valueOf(getDeviceInfo().version);
    }

    public /* synthetic */ void lambda$new$0$DeviceFragmentPresenter() {
        setDeviceLog("mGetSystemConstituentTimeoutRunnable timeout");
        BLEManager.unregisterGetDeviceParaCallBack(this.mSystemConstituentCallback);
        this.retryCount++;
        if (this.retryCount <= 3) {
            setDeviceLog("mGetSystemConstituent  rety:" + this.retryCount);
            getDeviceThirdVersion();
            return;
        }
        deviceThirdVersion = SPHelper.getDeviceThirdVersion();
        setDeviceLog("mGetSystemConstituent: 从缓存获取三级版本：" + deviceThirdVersion);
        this.retryCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), str);
    }
}