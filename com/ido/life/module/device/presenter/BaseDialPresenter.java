package com.ido.life.module.device.presenter;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.ble.BaseDialOperateCallback;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.module.device.view.BaseDialView;
import com.ido.life.util.DevicePicUtils;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDialPresenter<T extends BaseDialView> extends BaseCmdPresenter<T> implements DeviceParaChangedCallBack.ICallBack {
    public static final String BUILT_IN_DIAL_NAME_START = "local_";
    public static final String BUILT_IN_DIAL_NAME_START_OLD = "time_plate";
    public static final String DIAL_NAME_SPEC_NAME = ".iwf";
    public static final String DIAL_TYPE_BUILTIN = "DEFAULT";
    public static final String WALLPAPER_DIAL_NAME = "wallpaper.z";
    public static int cloud_watch_num;
    private static long lastTimeStamp;
    protected static int mAvailableCount;
    public static int usable_max_download_space_size;
    public static int user_cloud_watch_num;
    public static int user_watch_capacity_size;
    public static int watch_capacity_size;
    protected int installedDialSize;
    private BaseDialOperateCallback mDialOperateCallback;
    public boolean wallPaperInstalled;
    public static final String WALL_PAPER_DIAL_FOLDER_PATH = LogPathImpl.getInstance().getWallpaperDialFilePath();
    public static int DIAL_INSTALL_FAIL_NOT_MEMORY = 21;
    public static boolean isTelephone = false;
    protected List<String> mDialNameList = new ArrayList();
    public List<DialPlateParam.PlateFileInfo> installedV3DialList = null;

    protected void deleteDialResult(boolean z) {
    }

    protected void onSetPlateComplete(boolean z) {
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        initDialOperateCallback();
        BLEManager.registerWatchOperateCallBack(this.mDialOperateCallback);
        BLEManager.registerDeviceParaChangedCallBack(this);
    }

    private void initDialOperateCallback() {
        this.mDialOperateCallback = new BaseDialOperateCallback() { // from class: com.ido.life.module.device.presenter.BaseDialPresenter.1
            @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onGetCurrentPlate(String str) {
                super.onGetCurrentPlate(str);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialDetailPresenter", "当前表盘：" + str);
                BaseDialPresenter.this.onGetCurrentDial(str);
            }

            @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onSetPlate(boolean z) {
                super.onSetPlate(z);
                if (z) {
                    EventBusHelper.post(400);
                }
                CommonLogUtil.d("onDialSwitched onSetPlate : " + z);
                BaseDialPresenter.this.onSetPlateComplete(z);
            }

            @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onGetPlateFileInfo(WatchPlateFileInfo watchPlateFileInfo) {
                super.onGetPlateFileInfo(watchPlateFileInfo);
                BaseDialPresenter.mAvailableCount = watchPlateFileInfo.availableCount;
                BaseDialPresenter.this.onGetInstalledDialInfo(watchPlateFileInfo);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - BaseDialPresenter.lastTimeStamp > 1000) {
                    BaseDialPresenter.this.getCurrentDial();
                    long unused = BaseDialPresenter.lastTimeStamp = jCurrentTimeMillis;
                }
            }

            @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onGetDialPlateParam(DialPlateParam dialPlateParam) {
                super.onGetDialPlateParam(dialPlateParam);
                BaseDialPresenter.mAvailableCount = dialPlateParam.cloud_watch_num - dialPlateParam.user_cloud_watch_num;
                BaseDialPresenter.watch_capacity_size = dialPlateParam.watch_capacity_size;
                BaseDialPresenter.user_watch_capacity_size = dialPlateParam.user_watch_capacity_size;
                BaseDialPresenter.user_cloud_watch_num = dialPlateParam.user_cloud_watch_num;
                BaseDialPresenter.usable_max_download_space_size = dialPlateParam.usable_max_download_space_size;
                BaseDialPresenter.cloud_watch_num = dialPlateParam.cloud_watch_num;
                System.currentTimeMillis();
                BaseDialPresenter.this.onGetInstalledDialInfo(dialPlateParam);
                BaseDialPresenter.this.onGetCurrentDial(dialPlateParam.now_show_watch_name);
            }

            @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
            public void onDeletePlate(boolean z) {
                super.onDeletePlate(z);
                if (z) {
                    EventBusHelper.post(400);
                }
                BaseDialPresenter.this.deleteDialResult(z);
            }
        };
    }

    public String getWallPaperDialPath() {
        String strConcat = WALL_PAPER_DIAL_FOLDER_PATH.concat(getDeviceInfo().mDeviceAddress);
        File file = new File(strConcat);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return strConcat.concat("/wallpaper.png");
    }

    public String getWallPaperDialPathNew(String str) {
        return DevicePicUtils.getDialPicPath(getDeviceInfo().mDeviceAddress, str);
    }

    public void getInstalledDialInfo() {
        if (isConnected()) {
            if (getSupportFunctionInfo().V3_get_watch_list_new) {
                saveDialLog("getInstalledDialInfo start, V3_get_watch_list_new");
                BLEManager.getDialPlateParam();
            } else {
                saveDialLog("getInstalledDialInfo start");
                BLEManager.getWatchPlateList();
            }
        }
    }

    public void getCurrentDial() {
        if (isConnected()) {
            BLEManager.getCurrentWatchPlate();
        }
    }

    protected void onGetCurrentDial(String str) {
        saveDialLog("当前表盘：" + str);
        if (isAttachView()) {
            ((BaseDialView) getView()).onGetCurrentDial(str);
        }
    }

    protected void onGetInstalledDialInfo(WatchPlateFileInfo watchPlateFileInfo) {
        if (watchPlateFileInfo == null) {
            watchPlateFileInfo = new WatchPlateFileInfo();
        }
        if (watchPlateFileInfo.fileNameList == null) {
            watchPlateFileInfo.fileNameList = new ArrayList();
        }
        this.installedDialSize = watchPlateFileInfo.fileNameList.size();
        this.mDialNameList.clear();
        this.mDialNameList.addAll(watchPlateFileInfo.fileNameList);
        this.wallPaperInstalled = this.mDialNameList.contains(WALLPAPER_DIAL_NAME);
        if (isAttachView()) {
            ((BaseDialView) getView()).onGetInstalledDialList(watchPlateFileInfo.fileNameList);
        }
    }

    protected void onGetInstalledDialInfo(DialPlateParam dialPlateParam) {
        if (dialPlateParam == null) {
            return;
        }
        if (dialPlateParam.item == null) {
            dialPlateParam.item = new ArrayList();
        }
        this.installedDialSize = dialPlateParam.item.size();
        this.mDialNameList.clear();
        for (DialPlateParam.PlateFileInfo plateFileInfo : dialPlateParam.item) {
            if (plateFileInfo != null) {
                this.mDialNameList.add(plateFileInfo.name);
            }
        }
        this.wallPaperInstalled = this.mDialNameList.contains(WALLPAPER_DIAL_NAME);
        this.installedV3DialList = dialPlateParam.item;
        if (isAttachView()) {
            ((BaseDialView) getView()).onGetInstalledDialListV3(dialPlateParam.item);
        }
    }

    public boolean canDelete() {
        return this.installedDialSize > 1;
    }

    public boolean isSupportWallpaper() {
        return FunctionHelper.isSupportWallpaper();
    }

    public void saveDialLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), getClass().getSimpleName(), str);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterWatchOperateCallBack(this.mDialOperateCallback);
        BLEManager.unregisterDeviceParaChangedCallBack(this);
    }

    public boolean hasMemoryInstall(int i) {
        saveDialLog("hasMemoryInstall，dial size = " + i);
        if (LocalDataManager.getSupportFunctionInfo() != null && LocalDataManager.getSupportFunctionInfo().V3_support_watch_capacity_size_display) {
            if (i > 1048576) {
                i /= 4;
            }
            boolean z = usable_max_download_space_size - i > 0;
            int i2 = cloud_watch_num - user_cloud_watch_num;
            saveDialLog("usable_max_download_space_size:" + usable_max_download_space_size + ",size:" + i + "--cloud_watch_num:" + cloud_watch_num + ",user_cloud_watch_num:" + user_cloud_watch_num);
            return z && i2 >= 1;
        }
        saveDialLog("mAvailableCount:" + mAvailableCount);
        return mAvailableCount >= 1;
    }

    public MyDialListEntity.DialInfo getWallPaperDialNew(String str) {
        if (!FunctionHelper.isSupportWallpaper()) {
            return null;
        }
        if (BitmapFactory.decodeFile(getWallPaperDialPath()) != null) {
            return new MyDialListEntity.DialInfo(WALLPAPER_DIAL_NAME, getWallPaperDialPathNew(str));
        }
        return new MyDialListEntity.DialInfo(WALLPAPER_DIAL_NAME, "");
    }

    @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
    public void onChanged(DeviceChangedPara deviceChangedPara) {
        if (deviceChangedPara != null) {
            if (deviceChangedPara.dataType == 26) {
                isTelephone = true;
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialDetailPresenter", "蓝牙正在通话：");
            }
            if (deviceChangedPara.dataType == 27) {
                isTelephone = false;
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DialDetailPresenter", "蓝牙结束通话：");
            }
        }
    }

    public boolean isOffline() {
        return !NetworkUtil.isConnected(VeryFitApp.getApp());
    }
}