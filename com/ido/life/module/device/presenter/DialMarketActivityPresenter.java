package com.ido.life.module.device.presenter;

import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IDialMarketActivityView;
import com.ido.life.module.main.MainPresenter;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialMarketActivityPresenter extends BaseDialPresenter<IDialMarketActivityView> {
    private boolean hasNewVersion;
    private int new_count;

    @Override // com.ido.life.base.BaseCmdPresenter
    public int getDeviceShape() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            return basicInfo.shape;
        }
        return 2;
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        this.new_count = 0;
        this.hasNewVersion = false;
        this.installedV3DialList = null;
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    public float getDialImageAspectRatio() {
        Float f2;
        BLEDevice deviceInfo = getDeviceInfo();
        if (MainPresenter.screenRatioMap.containsKey(deviceInfo.mDeviceAddress) && (f2 = MainPresenter.screenRatioMap.get(deviceInfo.mDeviceAddress)) != null && f2.floatValue() != 0.0f) {
            return f2.floatValue();
        }
        if (isBindAndConnected()) {
            BLEManager.getWatchPlateScreenInfo();
        }
        return isBracelet() ? 2.0f : 1.128f;
    }

    public int getInstalledDialCount() {
        return this.installedDialSize;
    }

    public int getMaxDialCount() {
        return getInstalledDialCount() + mAvailableCount + ((!isSupportWallpaper() || this.mDialNameList.contains(BaseDialPresenter.WALLPAPER_DIAL_NAME)) ? 0 : 1);
    }

    public int getWatchCapacitySize() {
        return watch_capacity_size / 1024;
    }

    public int getWatchUseCapacitySize() {
        return user_watch_capacity_size / 1024;
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    public void saveDialLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void formatMyDialList(List<String> list, List<MyDialListEntity.DialInfo> list2) {
        MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion;
        this.new_count = 0;
        if (isAttachView()) {
            ArrayList<MyDialListEntity.DialInfo> arrayList = new ArrayList();
            if (list == null) {
                ((IDialMarketActivityView) getView()).onGetMyDialList(arrayList);
                return;
            }
            Iterator<String> it = list.iterator();
            while (true) {
                boolean z = true;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (list2 == null || list2.size() == 0) {
                    z = false;
                } else {
                    for (MyDialListEntity.DialInfo dialInfo : list2) {
                        if (dialInfo != null && !TextUtils.isEmpty(dialInfo.getOtaFaceName()) && TextUtils.equals(next.split("\\.")[0], dialInfo.getOtaFaceName().split("\\.")[0])) {
                            arrayList.add(dialInfo);
                            break;
                        }
                    }
                    z = false;
                }
                if (!z) {
                    arrayList.add(new MyDialListEntity.DialInfo(next, ""));
                }
            }
            if (list2 == null) {
                new ArrayList();
            } else {
                if (this.installedV3DialList != null && !this.installedV3DialList.isEmpty() && !arrayList.isEmpty()) {
                    for (MyDialListEntity.DialInfo dialInfo2 : arrayList) {
                        if (dialInfo2 != null && (otaFaceVersion = dialInfo2.getOtaFaceVersion()) != null) {
                            try {
                                int i = Integer.parseInt(otaFaceVersion.getVersion());
                                for (DialPlateParam.PlateFileInfo plateFileInfo : this.installedV3DialList) {
                                    if (plateFileInfo != null) {
                                        saveDialLog("userDial.getOtaFaceName() = " + dialInfo2.getOtaFaceName() + ";   installedDial.getOtaFaceName() = " + plateFileInfo.name + ";   userDial.getLinkUrl() = " + otaFaceVersion.getLinkUrl());
                                        if (TextUtils.equals(dialInfo2.getOtaFaceName().concat(".iwf"), plateFileInfo.name) || TextUtils.equals(dialInfo2.getOtaFaceName(), plateFileInfo.name)) {
                                            dialInfo2.hasNewVersion = i > plateFileInfo.watch_version && !TextUtils.isEmpty(otaFaceVersion.getLinkUrl());
                                            if (dialInfo2.hasNewVersion) {
                                                this.new_count++;
                                            }
                                            if (!this.hasNewVersion) {
                                                this.hasNewVersion = dialInfo2.hasNewVersion;
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e2) {
                                saveDialLog("formatMyDialList，dial version format exception : " + e2.toString());
                            }
                        }
                    }
                }
                saveDialLog("dialmarketActivitypresenter新的表盘：" + this.new_count);
                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DIAL_NEW_VERSION, Integer.valueOf(this.new_count)));
            }
            ((IDialMarketActivityView) getView()).onGetMyDialList(arrayList);
        }
    }

    public void requestDialInfoFromNames(final List<String> list) {
        DeviceManager.requestDialInfosFromNames(getAppBleDevice(), list, new DeviceManager.OnDeviceCallback<List<MyDialListEntity.DialInfo>>() { // from class: com.ido.life.module.device.presenter.DialMarketActivityPresenter.1
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<MyDialListEntity.DialInfo> list2) {
                DialMarketActivityPresenter.this.saveDialLog("requestDialInfoFromNames onSuccess : " + list2);
                DialMarketActivityPresenter.this.formatMyDialList(list, list2);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                DialMarketActivityPresenter.this.saveDialLog("requestDialInfoFromNames onFailed : " + str);
                DialMarketActivityPresenter.this.formatMyDialList(list, new ArrayList());
            }
        });
    }
}