package com.ido.life.module.device.presenter;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.DialMarketDetail;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IMyDialView;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.WallpaperDialManager;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MyDialPresenter extends BaseDialPresenter<IMyDialView> {
    public static final int TITLE_INSTALLED = -100;
    private boolean hasNewVersion;
    public boolean isSetting;
    private final List<MyDialListEntity.DialInfo> mBuiltInDialList = new ArrayList();
    private List<String> mColorList;
    private boolean mIsLoading;
    private int new_count;

    public int getInstalledDialCount() {
        return this.installedDialSize;
    }

    public int getMaxDialCount() {
        return getInstalledDialCount() + mAvailableCount + ((!isSupportWallpaper() || this.mDialNameList.contains(BaseDialPresenter.WALLPAPER_DIAL_NAME)) ? 0 : 1);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        this.new_count = 0;
        this.hasNewVersion = false;
    }

    private MyDialListEntity.DialInfo getWallPaperDial() {
        if (!FunctionHelper.isSupportWallpaper()) {
            return null;
        }
        if (BitmapFactory.decodeFile(getWallPaperDialPath()) != null) {
            return new MyDialListEntity.DialInfo(BaseDialPresenter.WALLPAPER_DIAL_NAME, getWallPaperDialPath());
        }
        return new MyDialListEntity.DialInfo(BaseDialPresenter.WALLPAPER_DIAL_NAME, "");
    }

    public int getWatchCapacitySize() {
        return watch_capacity_size / 1024;
    }

    public int getWatchUseCapacitySize() {
        return user_watch_capacity_size / 1024;
    }

    public synchronized ArrayList<MyDialListEntity.DialInfo> formatMyDialList(List<MyDialListEntity.DialInfo> list, List<String> list2, List<DialPlateParam.PlateFileInfo> list3, boolean z) {
        ArrayList<MyDialListEntity.DialInfo> arrayList;
        this.hasNewVersion = false;
        this.new_count = 0;
        if (list == null) {
            list = new ArrayList<>();
        }
        ArrayList<MyDialListEntity.DialInfo> arrayList2 = new ArrayList(list);
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        ArrayList<String> arrayList3 = new ArrayList(list2);
        arrayList = new ArrayList<>();
        if ((arrayList3.size() > 0 || this.mBuiltInDialList.size() > 0) && z) {
            arrayList.add(new MyDialListEntity.DialInfo(-100));
        }
        if (!arrayList3.isEmpty()) {
            int i = 0;
            for (String str : arrayList3) {
                if (!TextUtils.isEmpty(str) && str.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START)) {
                    i++;
                }
            }
            int size = this.mBuiltInDialList.size();
            if (size < i) {
                while (size < i) {
                    size++;
                    MyDialListEntity.DialInfo dialInfo = new MyDialListEntity.DialInfo(BaseDialPresenter.BUILT_IN_DIAL_NAME_START.concat(String.valueOf(size)), "");
                    dialInfo.isInstalledDial = true;
                    dialInfo.setLocal(true);
                    this.mBuiltInDialList.add(dialInfo);
                }
            }
        }
        arrayList.addAll(this.mBuiltInDialList);
        if (arrayList2.size() > 0) {
            int i2 = 0;
            while (i2 < arrayList3.size()) {
                String str2 = (String) arrayList3.get(i2);
                if (!TextUtils.isEmpty(str2)) {
                    if (str2.equals(BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                        MyDialListEntity.DialInfo wallPaperDialNew = getWallPaperDialNew(str2);
                        if (wallPaperDialNew != null) {
                            wallPaperDialNew.isInstalledDial = true;
                        }
                        if (!arrayList.contains(wallPaperDialNew) && arrayList3.contains(BaseDialPresenter.WALLPAPER_DIAL_NAME)) {
                            arrayList.add(wallPaperDialNew);
                            arrayList3.remove(i2);
                            i2--;
                            break;
                        }
                    } else {
                        for (MyDialListEntity.DialInfo dialInfo2 : arrayList2) {
                            if (dialInfo2 != null) {
                                dialInfo2.isInstalledDial = false;
                                if (!TextUtils.isEmpty(dialInfo2.getOtaFaceName()) && TextUtils.equals(str2.split("\\.")[0], dialInfo2.getOtaFaceName().split("\\.")[0])) {
                                    dialInfo2.isInstalledDial = true;
                                    dialInfo2.setOtaFaceName(str2);
                                    arrayList.add(dialInfo2);
                                    arrayList2.remove(dialInfo2);
                                    arrayList3.remove(i2);
                                    i2--;
                                    break;
                                    break;
                                }
                            }
                        }
                    }
                }
                i2++;
            }
            if (arrayList3.size() > 0) {
                for (String str3 : arrayList3) {
                    if (!TextUtils.isEmpty(str3) && !str3.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START) && !str3.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START_OLD)) {
                        MyDialListEntity.DialInfo dialInfo3 = new MyDialListEntity.DialInfo(str3, "");
                        dialInfo3.setName(str3);
                        dialInfo3.setInstalledDial(true);
                        arrayList.add(dialInfo3);
                    }
                }
            }
        } else {
            ArrayList arrayList4 = new ArrayList();
            for (MyDialListEntity.DialInfo dialInfo4 : this.mBuiltInDialList) {
                if (dialInfo4 != null && !TextUtils.isEmpty(dialInfo4.getOtaFaceName()) && !arrayList4.contains(dialInfo4.getOtaFaceName())) {
                    arrayList4.add(dialInfo4.getOtaFaceName());
                }
            }
            for (String str4 : arrayList3) {
                if (!TextUtils.isEmpty(str4) && !arrayList4.contains(str4)) {
                    if (str4.endsWith(".iwf")) {
                        str4 = str4.split(".iwf")[0];
                    }
                    MyDialListEntity.DialInfo dialInfo5 = new MyDialListEntity.DialInfo(str4, "");
                    dialInfo5.isInstalledDial = true;
                    arrayList.add(dialInfo5);
                }
            }
        }
        if (this.installedDialSize == 0) {
            this.installedDialSize = arrayList.size() > 0 ? arrayList.size() - 1 : 0;
        }
        saveDialLog("formatMyDialList installedV3DialList : " + GsonUtil.toJson(list3));
        saveDialLog("formatMyDialList dialInfoList : " + GsonUtil.toJson(arrayList));
        if (list3 != null && !list3.isEmpty() && !arrayList.isEmpty()) {
            for (MyDialListEntity.DialInfo dialInfo6 : arrayList) {
                if (dialInfo6 != null) {
                    if (!dialInfo6.isInstalledDial) {
                        dialInfo6.hasNewVersion = false;
                    } else {
                        MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion = dialInfo6.getOtaFaceVersion();
                        if (otaFaceVersion != null) {
                            try {
                                int i3 = Integer.parseInt(otaFaceVersion.getVersion());
                                for (DialPlateParam.PlateFileInfo plateFileInfo : list3) {
                                    if (plateFileInfo != null) {
                                        saveDialLog("userDial.getOtaFaceName() = " + dialInfo6.getOtaFaceName() + ";   installedDial.getOtaFaceName() = " + plateFileInfo.name + ";   userDial.getLinkUrl() = " + otaFaceVersion.getLinkUrl());
                                        if (TextUtils.equals(dialInfo6.getOtaFaceName().concat(".iwf"), plateFileInfo.name) || TextUtils.equals(dialInfo6.getOtaFaceName(), plateFileInfo.name)) {
                                            dialInfo6.hasNewVersion = i3 > plateFileInfo.watch_version && !TextUtils.isEmpty(otaFaceVersion.getLinkUrl());
                                            if (dialInfo6.hasNewVersion) {
                                                this.new_count++;
                                            }
                                            if (!this.hasNewVersion) {
                                                this.hasNewVersion = dialInfo6.hasNewVersion;
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
            }
            saveDialLog("新的表盘：" + this.new_count);
            EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DIAL_NEW_VERSION, Integer.valueOf(this.new_count)));
        } else {
            saveDialLog("新的表盘：" + this.new_count);
            EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DIAL_NEW_VERSION, Integer.valueOf(this.new_count)));
        }
        return arrayList;
    }

    public void getWallpaperDialInfo() {
        if (!isConnected()) {
            saveDialLog("getWallpaperDialInfo, device disconnect");
            if (isAttachView()) {
                ((IMyDialView) getView()).onGetDialInfoFailed();
                return;
            }
            return;
        }
        BLEManager.registerPhotoWallpaperOperateCallBack(new PhotoWallpaperOperateCallBack.ICallBack() { // from class: com.ido.life.module.device.presenter.MyDialPresenter.1
            @Override // com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack.ICallBack
            public void onOperateResult(PhotoWallpaperOperation.ResponseInfo responseInfo) {
                if (responseInfo != null && responseInfo.operate == 0) {
                    MyDialPresenter.this.saveDialLog("getWallpaperDialInfo, onOperateResult : " + GsonUtil.toJson(responseInfo));
                    BLEManager.unregisterPhotoWallpaperOperateCallBack(this);
                    if (MyDialPresenter.this.isAttachView()) {
                        ((IMyDialView) MyDialPresenter.this.getView()).onGetWallpaperDialInfo(responseInfo);
                    }
                }
            }
        });
        PhotoWallpaperOperation.SetParams setParams = new PhotoWallpaperOperation.SetParams();
        setParams.operate = 0;
        BLEManager.photoWallpaperOperate(setParams);
    }

    public List<String> getWallpaperColorList() {
        if (this.mColorList == null) {
            this.mColorList = new ArrayList();
        }
        if (this.mColorList.isEmpty()) {
            this.mColorList.addAll(WallpaperDialManager.getWallpaperColorList());
        }
        return this.mColorList;
    }

    public boolean isSupportWallpaperFunctionSetting() {
        return getSupportFunctionInfo().V3_support_watch_photo_position_move || getSupportFunctionInfo().v2_support_wallpaper_watch_face_only_time_color;
    }

    public boolean isNotSupportWallpaperFunctionEdit() {
        return getSupportFunctionInfo().v2_support_wallpaper_watch_face_only_time_color;
    }

    public void requestDialUseRecord() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialTypePresenter", "requestDialUseRecord,pageid:1,pagesize:5");
        DeviceManager.getMyDialRecord(getDeviceInfo(), new DeviceManager.OnDeviceCallback<DialMarketDetail.DialInfoDetail>() { // from class: com.ido.life.module.device.presenter.MyDialPresenter.2
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(DialMarketDetail.DialInfoDetail dialInfoDetail) {
                if (MyDialPresenter.this.isAttachView()) {
                    if (dialInfoDetail == null) {
                        ((IMyDialView) MyDialPresenter.this.getView()).onGetMyDialNum(0);
                    } else {
                        ((IMyDialView) MyDialPresenter.this.getView()).onGetMyDialNum(dialInfoDetail.getNumRows());
                    }
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                if (MyDialPresenter.this.isAttachView()) {
                    ((IMyDialView) MyDialPresenter.this.getView()).onGetMyDialNum(0);
                }
            }
        }, 1, 5);
    }

    public void requestDialCollect() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialTypePresenter", "requestDialListById: id,pageid:1,pagesize:5");
        DeviceManager.requestDialCollectList(getDeviceInfo(), new DeviceManager.OnDeviceCallback<DialMarketDetail.DialInfoDetail>() { // from class: com.ido.life.module.device.presenter.MyDialPresenter.3
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(DialMarketDetail.DialInfoDetail dialInfoDetail) {
                if (MyDialPresenter.this.isAttachView()) {
                    if (dialInfoDetail == null) {
                        ((IMyDialView) MyDialPresenter.this.getView()).onGetCollectDialNum(0);
                    } else {
                        ((IMyDialView) MyDialPresenter.this.getView()).onGetCollectDialNum(dialInfoDetail.getNumRows());
                    }
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                if (MyDialPresenter.this.isAttachView()) {
                    ((IMyDialView) MyDialPresenter.this.getView()).onGetCollectDialNum(0);
                }
            }
        }, 1, 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void formatMyDialList(List<String> list, List<MyDialListEntity.DialInfo> list2) {
        MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion;
        boolean z;
        this.new_count = 0;
        if (isAttachView()) {
            ArrayList<MyDialListEntity.DialInfo> arrayList = new ArrayList();
            if (list == null) {
                ((IMyDialView) getView()).onGetMyDialList(arrayList);
                return;
            }
            for (String str : list) {
                if (list2 == null || list2.size() == 0) {
                    z = false;
                } else {
                    for (MyDialListEntity.DialInfo dialInfo : list2) {
                        if (dialInfo != null && !TextUtils.isEmpty(dialInfo.getOtaFaceName()) && TextUtils.equals(str.split("\\.")[0], dialInfo.getOtaFaceName().split("\\.")[0])) {
                            if (!TextUtils.isEmpty(dialInfo.getOtaFaceName()) && dialInfo.getOtaFaceName().startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START)) {
                                dialInfo.setLocal(true);
                            }
                            arrayList.add(dialInfo);
                            z = true;
                        }
                    }
                    z = false;
                }
                if (!z) {
                    MyDialListEntity.DialInfo dialInfo2 = new MyDialListEntity.DialInfo(str, "");
                    if (!TextUtils.isEmpty(str) && str.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START)) {
                        dialInfo2.setLocal(true);
                    }
                    dialInfo2.setName(str);
                    arrayList.add(dialInfo2);
                }
            }
            if (list2 == null) {
                new ArrayList();
            } else {
                if (this.installedV3DialList != null && !this.installedV3DialList.isEmpty() && !arrayList.isEmpty()) {
                    for (MyDialListEntity.DialInfo dialInfo3 : arrayList) {
                        if (dialInfo3 != null && (otaFaceVersion = dialInfo3.getOtaFaceVersion()) != null) {
                            try {
                                int i = Integer.parseInt(otaFaceVersion.getVersion());
                                for (DialPlateParam.PlateFileInfo plateFileInfo : this.installedV3DialList) {
                                    if (plateFileInfo != null && (TextUtils.equals(dialInfo3.getOtaFaceName().concat(".iwf"), plateFileInfo.name) || TextUtils.equals(dialInfo3.getOtaFaceName(), plateFileInfo.name))) {
                                        dialInfo3.hasNewVersion = i > plateFileInfo.watch_version && !TextUtils.isEmpty(otaFaceVersion.getLinkUrl());
                                        if (dialInfo3.hasNewVersion) {
                                            this.new_count++;
                                        }
                                        if (!this.hasNewVersion) {
                                            this.hasNewVersion = dialInfo3.hasNewVersion;
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
            ((IMyDialView) getView()).onGetMyDialList(arrayList);
        }
    }

    public void requestDialInfoFromNames(final List<String> list) {
        DeviceManager.requestDialInfosFromNames(getAppBleDevice(), list, new DeviceManager.OnDeviceCallback<List<MyDialListEntity.DialInfo>>() { // from class: com.ido.life.module.device.presenter.MyDialPresenter.4
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<MyDialListEntity.DialInfo> list2) {
                MyDialPresenter.this.saveDialLog("requestDialInfoFromNames onSuccess : ");
                MyDialPresenter.this.formatMyDialList(list, list2);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                MyDialPresenter.this.saveDialLog("requestDialInfoFromNames onFailed : " + str);
                MyDialPresenter.this.formatMyDialList(list, new ArrayList());
            }
        });
    }

    public boolean isLocalDial(String str) {
        return !TextUtils.isEmpty(str) && (str.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START) || str.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START_OLD));
    }
}