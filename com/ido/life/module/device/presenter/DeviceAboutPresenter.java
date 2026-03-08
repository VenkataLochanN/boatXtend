package com.ido.life.module.device.presenter;

import android.text.TextUtils;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.DeviceInfo;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.util.DateUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceAboutPresenter extends BaseCmdPresenter {
    @Override // com.ido.life.base.BaseCmdPresenter
    public BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
        }
        return currentDeviceInfo;
    }

    public String getDeviceSyncTime() {
        long deviceSyncTime = SPHelper.getDeviceSyncTime();
        return deviceSyncTime == 0 ? "--" : DateUtil.format(deviceSyncTime, DateUtil.DATE_FORMAT_DMYHm);
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
            DeviceManager.queryDeviceInfoList(new DeviceManager.OnDeviceCallback<List<DeviceInfo>>() { // from class: com.ido.life.module.device.presenter.DeviceAboutPresenter.1
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
}