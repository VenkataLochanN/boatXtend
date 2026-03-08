package com.ido.life.module.device.presenter;

import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BasePresenter;
import com.ido.life.data.api.entity.DialMarketDetail;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IDialTypeView;
import com.ido.life.module.main.MainPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class DialTypePresenter extends BasePresenter<IDialTypeView> {
    public static final int TYPE_FINE_RECOMMENDED = -2;
    public static final int TYPE_LATEST_ONLINE = -1;

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

    public boolean isBracelet() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        return basicInfo != null && basicInfo.dev_type == 1;
    }

    public void saveDialLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialTypePresenter", str);
    }

    public float getDialImageAspectRatio() {
        BLEDevice deviceInfo = getDeviceInfo();
        if (MainPresenter.screenRatioMap.containsKey(deviceInfo.mDeviceAddress)) {
            Float f2 = MainPresenter.screenRatioMap.get(deviceInfo.mDeviceAddress);
            if (f2.floatValue() != 0.0f) {
                return f2.floatValue();
            }
        }
        if (BLEManager.isBind() && BLEManager.isConnected()) {
            BLEManager.getWatchPlateScreenInfo();
        }
        return isBracelet() ? 2.0f : 1.128f;
    }

    public int getDeviceShape() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            return basicInfo.shape;
        }
        return 2;
    }

    public void requestDialListById(int i, int i2, int i3) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialTypePresenter", "requestDialListById: id：" + i + ", pageid: " + i2 + ", pagesize: " + i3);
        DeviceManager.getDeviceTypeByIdDialMarketDetail(getDeviceInfo(), new DeviceManager.OnDeviceCallback<DialMarketDetail.DialInfoDetail>() { // from class: com.ido.life.module.device.presenter.DialTypePresenter.1
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(DialMarketDetail.DialInfoDetail dialInfoDetail) {
                if (DialTypePresenter.this.isAttachView()) {
                    ((IDialTypeView) DialTypePresenter.this.getView()).onGetDialListById(dialInfoDetail);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i4, String str) {
                if (DialTypePresenter.this.isAttachView()) {
                    ((IDialTypeView) DialTypePresenter.this.getView()).onGetDialListById(null);
                }
            }
        }, i, i2, i3);
    }

    public void requestDialUseRecord(int i, int i2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialTypePresenter", "requestDialUseRecord,pageid:" + i + ",pagesize:" + i2);
        DeviceManager.getMyDialRecord(getDeviceInfo(), new DeviceManager.OnDeviceCallback<DialMarketDetail.DialInfoDetail>() { // from class: com.ido.life.module.device.presenter.DialTypePresenter.2
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(DialMarketDetail.DialInfoDetail dialInfoDetail) {
                if (DialTypePresenter.this.isAttachView()) {
                    ((IDialTypeView) DialTypePresenter.this.getView()).onGetDialListById(dialInfoDetail);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i3, String str) {
                if (DialTypePresenter.this.isAttachView()) {
                    ((IDialTypeView) DialTypePresenter.this.getView()).onGetDialListById(null);
                }
            }
        }, i, i2);
    }

    public void requestDialCollect(int i, int i2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialTypePresenter", "requestDialCollect: id,pageid:" + i + ",pagesize:" + i2);
        DeviceManager.requestDialCollectList(getDeviceInfo(), new DeviceManager.OnDeviceCallback<DialMarketDetail.DialInfoDetail>() { // from class: com.ido.life.module.device.presenter.DialTypePresenter.3
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(DialMarketDetail.DialInfoDetail dialInfoDetail) {
                if (DialTypePresenter.this.isAttachView()) {
                    ((IDialTypeView) DialTypePresenter.this.getView()).onGetDialListById(dialInfoDetail);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i3, String str) {
                if (DialTypePresenter.this.isAttachView()) {
                    ((IDialTypeView) DialTypePresenter.this.getView()).onGetDialListById(null);
                }
            }
        }, i, i2);
    }
}