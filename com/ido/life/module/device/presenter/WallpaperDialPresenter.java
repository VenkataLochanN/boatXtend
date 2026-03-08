package com.ido.life.module.device.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;
import com.boat.Xtend.two.R;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.FileSizeUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.bean.CwdAppBean;
import com.ido.life.bean.CwdIwfBean;
import com.ido.life.bean.UsageDialBean;
import com.ido.life.bean.WallPaperDialInfo;
import com.ido.life.bean.Wallpaper;
import com.ido.life.ble.BaseDialOperateCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.DownloadManager;
import com.ido.life.data.ExecutorDispatcher;
import com.ido.life.data.Func;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.IWallPaperDialView;
import com.ido.life.transmitter.FileTransmitter;
import com.ido.life.transmitter.IconTransmitterListener;
import com.ido.life.transmitter.task.DialTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.transmitter.task.WallpaperDialTransferTask;
import com.ido.life.util.DevicePicUtils;
import com.ido.life.util.DialDefinedUtil;
import com.ido.life.util.FileUtil;
import com.ido.life.util.ImageUtil;
import com.ido.life.util.ListUtils;
import com.ido.life.util.WallpaperDialManager;
import com.ido.life.util.eventbus.EventBusHelper;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WallpaperDialPresenter extends BaseDialPresenter<IWallPaperDialView> implements IFileTransferListener, IconTransmitterListener, DownloadManager.DownloadListener {
    private static final String DIAL_PKG_SPEC_NAME = ".zip";
    private static final long DIAL_TIMEOUT = 30000;
    public static final int PROGRESS_TYPE_DIAL_SETTING = 2;
    public static final int PROGRESS_TYPE_DOWNLOAD = 1;
    private static final String TEMP_PHOTO_NAME = "temp.png";
    public static int cloud_watch_num;
    protected static int mAvailableCount;
    private static final String setPhotoPath = LogPathImpl.getInstance().getWallpaperDialFilePath() + "wallpaper_to_set.png";
    private static final String setPhotoPathBmp = LogPathImpl.getInstance().getWallpaperDialFilePath() + "wallpaper_to_set.bmp";
    public static int usable_max_download_space_size;
    public static int user_cloud_watch_num;
    public static int user_watch_capacity_size;
    public static int watch_capacity_size;
    private int functionColorIndex;
    private boolean hasImageChanged;
    private int hide_type;
    public boolean installed;
    private boolean isCircleDial;
    public boolean isCurrentDial;
    public boolean isCwd;
    private boolean isDownDial;
    private boolean isInstallDial;
    private boolean isPrepareCwdAppResource;
    public boolean isSetting;
    private boolean isUpdateDial;
    private boolean isUse;
    private List<String> mColorList;
    private CwdAppBean mCwdAppBean;
    private BLEDevice mDevice;
    private int mDeviceId;
    private long mDialId;
    private MyDialListEntity.DialInfo mDialInfo;
    private String mDialOtaFaceName;
    private int mRadius;
    private String mSid;
    private File tempWallpaperFile;
    private int timeColorIndex;
    private int timeFuncLocation;
    private List<Integer> widget_type_list = new ArrayList();
    private Handler mHandler = new Handler();
    private int mCurrentVersion = Integer.MAX_VALUE;
    private boolean justInstalledDialInfo = false;
    private String mWallpaperSetPath = setPhotoPathBmp;
    private BaseDialOperateCallback mDialOperateCallback = new BaseDialOperateCallback() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.1
        @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetScreenInfo(WatchPlateScreenInfo watchPlateScreenInfo) {
            super.onGetScreenInfo(watchPlateScreenInfo);
            if (watchPlateScreenInfo == null) {
                WallpaperDialPresenter.this.saveDialLog("onGetScreenInfo watchPlateScreenInfo is null");
                return;
            }
            WallpaperDialPresenter.this.saveDialLog("onGetScreenInfo：" + watchPlateScreenInfo.toString());
            if (WallpaperDialPresenter.this.isAttachView()) {
                ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetDeviceScreenSize(new Point(watchPlateScreenInfo.width, watchPlateScreenInfo.height));
            }
        }

        @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetCurrentPlate(String str) {
            WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
            wallpaperDialPresenter.isCurrentDial = TextUtils.equals(str, wallpaperDialPresenter.getWallpaperOtaName());
            WallpaperDialPresenter.this.saveDialLog("onGetCurrentPlate：" + str + ", isCurrentDial = " + WallpaperDialPresenter.this.isCurrentDial);
            WallpaperDialPresenter.this.mHandler.removeCallbacks(WallpaperDialPresenter.this.mGetDialInfoTimeoutRunnable);
            if (WallpaperDialPresenter.this.isAttachView()) {
                ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetInstalledDialInfo(WallpaperDialPresenter.this.isSupportWallpaperSetting());
            }
        }

        @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetPlateFileInfo(WatchPlateFileInfo watchPlateFileInfo) {
            WallpaperDialPresenter.this.saveDialLog("onGetPlateFileInfo：" + watchPlateFileInfo);
            WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
            wallpaperDialPresenter.installed = false;
            if (wallpaperDialPresenter.isAttachView()) {
                if (watchPlateFileInfo == null) {
                    WallpaperDialPresenter.this.mHandler.removeCallbacks(WallpaperDialPresenter.this.mGetDialInfoTimeoutRunnable);
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetDialInfoFailed(true);
                    return;
                }
                List<String> list = watchPlateFileInfo.fileNameList;
                if (list == null || list.isEmpty()) {
                    WallpaperDialPresenter.this.mHandler.removeCallbacks(WallpaperDialPresenter.this.mGetDialInfoTimeoutRunnable);
                    WallpaperDialPresenter.this.saveDialLog("onGetPlateFileInfo, fileNameList is null or empty");
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetDialInfoFailed(false);
                    return;
                }
                WallpaperDialPresenter wallpaperDialPresenter2 = WallpaperDialPresenter.this;
                wallpaperDialPresenter2.installed = list.contains(wallpaperDialPresenter2.getWallpaperOtaName());
                WallpaperDialPresenter.this.saveDialLog("onGetPlateFileInfo：" + WallpaperDialPresenter.this.installed);
            }
        }

        @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetDialPlateParam(DialPlateParam dialPlateParam) {
            WallpaperDialPresenter.this.saveDialLog("onGetDialPlateParam：" + dialPlateParam);
            WallpaperDialPresenter.this.removeDialOperateCallback();
            WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
            wallpaperDialPresenter.installed = false;
            wallpaperDialPresenter.isCurrentDial = false;
            wallpaperDialPresenter.mCurrentVersion = Integer.MAX_VALUE;
            WallpaperDialPresenter.this.mHandler.removeCallbacks(WallpaperDialPresenter.this.mGetDialInfoTimeoutRunnable);
            if (WallpaperDialPresenter.this.isAttachView()) {
                if (dialPlateParam == null) {
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetDialInfoFailed(true);
                    return;
                }
                WallpaperDialPresenter.mAvailableCount = dialPlateParam.cloud_watch_num - dialPlateParam.user_cloud_watch_num;
                WallpaperDialPresenter.this.saveDialLog("onGetDialPlateParam mAvailableCount:" + WallpaperDialPresenter.mAvailableCount);
                WallpaperDialPresenter.watch_capacity_size = dialPlateParam.watch_capacity_size;
                WallpaperDialPresenter.user_watch_capacity_size = dialPlateParam.user_watch_capacity_size;
                WallpaperDialPresenter.user_cloud_watch_num = dialPlateParam.user_cloud_watch_num;
                WallpaperDialPresenter.usable_max_download_space_size = dialPlateParam.usable_max_download_space_size;
                WallpaperDialPresenter.cloud_watch_num = dialPlateParam.cloud_watch_num;
                WallpaperDialPresenter.this.isCurrentDial = TextUtils.equals(dialPlateParam.now_show_watch_name, WallpaperDialPresenter.this.getWallpaperOtaName());
                List<DialPlateParam.PlateFileInfo> list = dialPlateParam.item;
                if (list == null || list.isEmpty()) {
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetDialInfoFailed(false);
                    return;
                }
                Iterator<DialPlateParam.PlateFileInfo> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DialPlateParam.PlateFileInfo next = it.next();
                    if (next != null && TextUtils.equals(next.name, WallpaperDialPresenter.this.getWallpaperOtaName())) {
                        WallpaperDialPresenter wallpaperDialPresenter2 = WallpaperDialPresenter.this;
                        wallpaperDialPresenter2.installed = true;
                        wallpaperDialPresenter2.mCurrentVersion = next.watch_version;
                        break;
                    }
                }
                WallpaperDialPresenter.this.saveDialLog("onGetDialPlateParam：installed = " + WallpaperDialPresenter.this.installed + ", isCurrentDial = " + WallpaperDialPresenter.this.isCurrentDial + ", mCurrentVersion = " + WallpaperDialPresenter.this.mCurrentVersion + ", justInstalledDialInfo = " + WallpaperDialPresenter.this.justInstalledDialInfo);
                if (!WallpaperDialPresenter.this.justInstalledDialInfo) {
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetInstalledDialInfo(WallpaperDialPresenter.this.isSupportWallpaperSetting());
                } else {
                    WallpaperDialPresenter.this.justInstalledDialInfo = false;
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onCwdInstallStatusUpdate();
                }
            }
        }

        @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onSetPlate(boolean z) {
            WallpaperDialPresenter.this.saveDialLog("onSetPlate ：" + z);
            WallpaperDialPresenter.this.removeDialOperateCallback();
            WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
            wallpaperDialPresenter.isSetting = false;
            if (wallpaperDialPresenter.isAttachView()) {
                ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onSwitchDialComplete(z);
            }
        }

        @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onDeletePlate(boolean z) {
            super.onDeletePlate(z);
            if (WallpaperDialPresenter.this.isCwd) {
                WallpaperDialPresenter.this.saveDialLog("onDeletePlate");
                WallpaperDialPresenter.this.removeDialOperateCallback();
                WallpaperDialPresenter.this.mHandler.removeCallbacks(WallpaperDialPresenter.this.mDeleteDialTimeoutRunnable);
                if (WallpaperDialPresenter.this.isUpdateDial) {
                    WallpaperDialPresenter.this.setCwd2Device();
                    return;
                }
                if (z) {
                    WallpaperDialPresenter.this.deleteLocalCwdDir();
                } else {
                    WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
                    wallpaperDialPresenter.isSetting = false;
                    ((IWallPaperDialView) wallpaperDialPresenter.getView()).onDialDeleteComplete(false);
                }
                EventBusHelper.post(Constants.EventConstants.EVENT_CLOUD_WALLPAPER_DIAL_CHANGED);
            }
        }
    };
    private Runnable mGetDialInfoTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$XGEAe_OIMqJ5ruKgHMYSEhAfJYI
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$WallpaperDialPresenter();
        }
    };
    private Runnable mSetDialColorTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$SEpT3iJZ9v08Bvp_JRzWKHPLkL4
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$WallpaperDialPresenter();
        }
    };
    private Runnable mDeleteDialTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.4
        @Override // java.lang.Runnable
        public void run() {
            WallpaperDialPresenter.this.saveDialLog("deleteWallpaperDial timeout");
            WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
            wallpaperDialPresenter.isSetting = false;
            if (wallpaperDialPresenter.isAttachView()) {
                ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onDialDeleteComplete(false);
            }
        }
    };
    private Runnable mSwitchDialTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$erPOwRW84E3e3kwa7hJAjO0m6uo
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$2$WallpaperDialPresenter();
        }
    };
    private Runnable mGetWallpaperDialInfoTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$PFHscMf5cPvko4lMGLqslxgCvfg
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$3$WallpaperDialPresenter();
        }
    };

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @interface CwdConfigSetStatus {
        public static final int DOWNLOAD = 2;
        public static final int FAILED = 3;
        public static final int SUCCESS = 1;
    }

    private boolean isSupportBmpWallpaper() {
        return true;
    }

    public boolean isSupportWallpaperUsageHistory() {
        return true;
    }

    private void initCwdConfig() {
        this.functionColorIndex = -1;
        this.timeColorIndex = -1;
        this.timeFuncLocation = -1;
        this.hide_type = -1;
        this.hasImageChanged = false;
        if (this.widget_type_list == null) {
            this.widget_type_list = new ArrayList();
        }
        this.widget_type_list.clear();
    }

    private void addDialOperateCallback() {
        BLEManager.unregisterWatchOperateCallBack(this.mDialOperateCallback);
        BLEManager.registerWatchOperateCallBack(this.mDialOperateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDialOperateCallback() {
        BLEManager.unregisterWatchOperateCallBack(this.mDialOperateCallback);
    }

    public boolean checkDial(String str) {
        return str.equals(this.mDialOtaFaceName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteLocalCwdDir() {
        saveDialLog("deleteLocalCwdDir");
        addSubscriber(new Func<Boolean>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ido.life.data.Func
            public Boolean call() {
                try {
                    return Boolean.valueOf(WallpaperDialManager.deleteDeviceCwdFile(WallpaperDialPresenter.this.mDialOtaFaceName));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
        }, new Callback<Boolean>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.3
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Boolean bool) {
                WallpaperDialPresenter.this.saveDialLog("deleteLocalCwdDir：" + bool);
                WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
                wallpaperDialPresenter.isSetting = false;
                if (wallpaperDialPresenter.isAttachView()) {
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onDialDeleteComplete(true);
                    if (bool.booleanValue()) {
                        return;
                    }
                    WallpaperDialPresenter.this.saveDialLog("deleteLocalCwdDir Failed，add to the background queue to delete later!");
                }
            }
        });
    }

    public /* synthetic */ void lambda$new$0$WallpaperDialPresenter() {
        saveDialLog("getInstalledDialInfo timeout");
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onGetDialInfoFailed(true);
        }
    }

    public /* synthetic */ void lambda$new$1$WallpaperDialPresenter() {
        saveDialLog("setDialColor2Device timeout");
        this.isSetting = false;
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onSetDialColorComplete(false);
        }
    }

    public /* synthetic */ void lambda$new$2$WallpaperDialPresenter() {
        saveDialLog("switch2CurrentDial timeout");
        this.isSetting = false;
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onSwitchDialComplete(false);
        }
    }

    public /* synthetic */ void lambda$new$3$WallpaperDialPresenter() {
        saveDialLog("getWallpaperDialInfo timeout");
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onGetDialInfoFailed(true);
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
    }

    public boolean isSupportWallpaperSetting() {
        return getSupportFunctionInfo().V3_set_wallpaper_dial || isCwdSupportFunction();
    }

    public boolean isSupportWallpaperTimeLocationSetting() {
        return getSupportFunctionInfo().V3_support_watch_photo_position_move || getSupportFunctionInfo().v2_support_wallpaper_watch_face_only_time_color || isCwdSupportFunction();
    }

    public boolean isSupportWallpaperFunction() {
        return getSupportFunctionInfo().V3_support_watch_photo_position_move || isCwdSupportFunction();
    }

    public boolean isSupportWallpaperFunctionColorSetting() {
        return getSupportFunctionInfo().V3_support_watch_photo_position_move || getSupportFunctionInfo().v2_support_wallpaper_watch_face_only_time_color || isCwdSupportFunction();
    }

    private boolean isCwdSupportFunction() {
        CwdAppBean cwdAppBean;
        return this.isCwd && (cwdAppBean = this.mCwdAppBean) != null && cwdAppBean.getFunction_support() == 1;
    }

    public List<Integer> getTimeLocationIndex() {
        ArrayList arrayList = new ArrayList();
        for (int i : ResourceUtil.getIntegerArray(R.array.dial_location)) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    public void getFunctionList() {
        if (isAttachView()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(1);
            arrayList.add(2);
            arrayList.add(3);
            arrayList.add(5);
            arrayList.add(4);
            arrayList.add(6);
            ((IWallPaperDialView) getView()).onGetFunctionListSuccess(WallpaperDialManager.sortFunctionByGroup(arrayList));
        }
    }

    public void getUsageDialList() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (!BLEManager.isConnected() || currentDeviceInfo == null) {
            CommonLogUtil.printAndSave("getUsageDialList no device info!");
            if (isAttachView()) {
                ((IWallPaperDialView) getView()).onGetUsageDialListFailed();
                return;
            }
            return;
        }
        CommonLogUtil.printAndSave("getUsageDialList start");
        DeviceManager.getWallpaperUsageList(currentDeviceInfo.mDeviceId + "", currentDeviceInfo.mDeviceName, currentDeviceInfo.mDeviceAddress, new DeviceManager.OnDeviceCallback<List<UsageDialBean>>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.5
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<UsageDialBean> list) {
                CommonLogUtil.printAndSave("getUsageDialList onSuccess：" + list);
                if (WallpaperDialPresenter.this.isAttachView()) {
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetUsageDialListSuccess(list);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.printAndSave("getUsageDialList onFailed：" + str);
                if (WallpaperDialPresenter.this.isAttachView()) {
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetUsageDialListFailed();
                }
            }
        });
    }

    public void updateWallpaperUsage(long j) {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (!BLEManager.isConnected() || currentDeviceInfo == null) {
            CommonLogUtil.printAndSave("updateWallpaperUsage no device info!");
            return;
        }
        DeviceManager.updateWallpaperUsage(currentDeviceInfo.mDeviceId + "", currentDeviceInfo.mDeviceName, currentDeviceInfo.mDeviceAddress, j, new DeviceManager.OnDeviceCallback<Object>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.6
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Object obj) {
                CommonLogUtil.printAndSave("updateWallpaperUsage onSuccess!");
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.printAndSave("updateWallpaperUsage onFailed: " + str);
            }
        });
    }

    public List<String> getWallpaperColorList() {
        if (isSupportWallpaperFunctionColorSetting() || this.isCwd) {
            if (this.mColorList == null) {
                this.mColorList = new ArrayList();
            }
            this.mColorList.clear();
            this.mColorList.addAll(WallpaperDialManager.getWallpaperColorList());
            return this.mColorList;
        }
        return getOldWallpaperColorList();
    }

    public List<String> getOldWallpaperColorList() {
        if (this.mColorList == null) {
            this.mColorList = new ArrayList();
        }
        this.mColorList.clear();
        this.mColorList.add("#F2F2F2");
        this.mColorList.add("#000000");
        this.mColorList.add("#FC1E58");
        this.mColorList.add("#FF9501");
        this.mColorList.add("#0091FF");
        this.mColorList.add("#44D7B6");
        return this.mColorList;
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    public String getWallPaperDialPath() {
        String strConcat = BaseDialPresenter.WALL_PAPER_DIAL_FOLDER_PATH.concat(getDeviceInfo().mDeviceAddress);
        File file = new File(strConcat);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return strConcat.concat("/wallpaper.png");
    }

    public void getDeviceScreenInfo() {
        if (isBindAndConnected()) {
            addDialOperateCallback();
            BLEManager.getWatchPlateScreenInfo();
        }
    }

    public void getWallpaperDialInfo() {
        if (!isConnected()) {
            saveDialLog("getWallpaperDialInfo, device disconnect");
            if (isAttachView()) {
                ((IWallPaperDialView) getView()).onGetDialInfoFailed(true);
                return;
            }
            return;
        }
        this.mHandler.removeCallbacks(this.mGetWallpaperDialInfoTimeoutRunnable);
        this.mHandler.postDelayed(this.mGetWallpaperDialInfoTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        BLEManager.registerPhotoWallpaperOperateCallBack(new PhotoWallpaperOperateCallBack.ICallBack() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.7
            @Override // com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack.ICallBack
            public void onOperateResult(PhotoWallpaperOperation.ResponseInfo responseInfo) {
                WallpaperDialPresenter.this.saveDialLog("getWallpaperDialInfo, onOperateResult : " + responseInfo);
                if (responseInfo != null && responseInfo.operate == 0) {
                    BLEManager.unregisterPhotoWallpaperOperateCallBack(this);
                    WallpaperDialPresenter.this.mHandler.removeCallbacks(WallpaperDialPresenter.this.mGetWallpaperDialInfoTimeoutRunnable);
                    if (WallpaperDialPresenter.this.isAttachView()) {
                        ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetWallpaperDialInfo(responseInfo);
                    }
                }
            }
        });
        PhotoWallpaperOperation.SetParams setParams = new PhotoWallpaperOperation.SetParams();
        setParams.operate = 0;
        BLEManager.photoWallpaperOperate(setParams);
    }

    public File getTempPhoto() {
        File file = new File(LogPathImpl.getInstance().getWallpaperDialFilePath());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        if (this.tempWallpaperFile == null) {
            this.tempWallpaperFile = new File(LogPathImpl.getInstance().getWallpaperDialFilePath(), TEMP_PHOTO_NAME);
        }
        return this.tempWallpaperFile;
    }

    public String getTempWallpaperPath() {
        if (this.isCwd) {
            return WallpaperDialManager.getTempBgImagePath();
        }
        return LogPathImpl.getInstance().getWallpaperDialFilePath() + "wallpaper_t.png";
    }

    public String getTempCircleWallpaperPath() {
        if (this.isCwd) {
            return WallpaperDialManager.getTempBgImagePath();
        }
        return LogPathImpl.getInstance().getWallpaperDialFilePath() + "wallpaper_t_circle.png";
    }

    public String getWholeWallpaperPath() {
        return LogPathImpl.getInstance().getWallpaperDialFilePath() + "wallpaper_whole.png";
    }

    public void createWallpaper(boolean z) throws Throwable {
        String tempWallpaperPath = getTempWallpaperPath();
        if (z) {
            BitmapUtil.saveBitmap(BitmapUtil.formatCircle2Rectangle(BitmapFactory.decodeFile(tempWallpaperPath)), getTempCircleWallpaperPath());
            tempWallpaperPath = getTempCircleWallpaperPath();
        }
        if (this.isCwd) {
            return;
        }
        Wallpaper wallpaper = new Wallpaper();
        wallpaper.setFileName(tempWallpaperPath);
        this.mWallpaperSetPath = setPhotoPath;
        if (isSupportBmpWallpaper()) {
            try {
                if (WallpaperDialManager.convertImage2Bmp(tempWallpaperPath, setPhotoPathBmp)) {
                    this.mWallpaperSetPath = setPhotoPathBmp;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        wallpaper.setSaveFileName(this.mWallpaperSetPath);
        wallpaper.setFormat(5);
        BLEManager.setParaToDeviceByTypeAndJson(5500, GsonUtil.toJson(wallpaper));
    }

    public void sendWallPaper2Device() {
        FileTransmitter.getInstance().removeListener(this);
        FileTransmitter.getInstance().addListener(this);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WallpaperDialTransferTask(this.mWallpaperSetPath, 0));
        FileTransmitter.getInstance().execute(arrayList);
    }

    public void saveView(RelativeLayout relativeLayout) throws Throwable {
        BitmapUtil.saveBitmap(BitmapUtil.view2Bitmap(relativeLayout, relativeLayout.getWidth(), relativeLayout.getHeight()), new File(getWholeWallpaperPath()), false);
        DevicePicUtils.FileCopy(getWholeWallpaperPath(), getDeviceInfo().mDeviceAddress, BaseDialPresenter.WALLPAPER_DIAL_NAME);
        EventBusHelper.post(412);
    }

    private void savePreview(View view, String str) throws Throwable {
        BitmapUtil.saveBitmap(BitmapUtil.view2Bitmap(view, view.getWidth(), view.getHeight()), new File(str), false);
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferShutdown(List<TransferTask> list) {
        Iterator<TransferTask> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().getModuleType() == 7) {
                z = true;
            }
        }
        if (z) {
            onFailed("-1");
        }
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferStart(int i, int i2) {
        if (i == 7) {
            saveDialLog("sendWallPaper2Device onTransferStart, module = " + i + ", type = " + i2);
            onStart();
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onStart() {
        saveDialLog("sendWallPaper2Device onStart");
        this.isSetting = true;
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onDialInstallStart();
        }
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferProgress(int i, int i2, int i3) {
        if (i == 7) {
            saveDialLog("sendWallPaper2Device onTransferProgress, module = " + i + ", type = " + i2 + ", progress = " + i3);
            onProgress(i3);
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onProgress(final int i) {
        saveDialLog("sendWallPaper2Device onProgress：" + i);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$cWXzciFSJDtpcM0BMlnNjz_Q93w
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProgress$4$WallpaperDialPresenter(i);
            }
        });
    }

    public /* synthetic */ void lambda$onProgress$4$WallpaperDialPresenter(int i) {
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onDialInstallProgress(i);
        }
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferSuccess(int i, int i2) {
        if (i == 7) {
            saveDialLog("sendWallPaper2Device onTransferSuccess, module = " + i + ", type = " + i2);
            onSuccess();
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onSuccess() {
        saveDialLog("sendWallPaper2Device onSuccess");
        if (this.isCwd) {
            copyAndReplaceOldCwd();
            return;
        }
        this.isSetting = false;
        getInstalledDialInfo();
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(getTempWallpaperPath());
        String wallPaperDialPath = getWallPaperDialPath();
        ImageUtil.saveWallPaper(bitmapDecodeFile, wallPaperDialPath);
        EventBusHelper.post(401);
        if (isSupportWallpaperUsageHistory()) {
            uploadWallpaperDial(wallPaperDialPath);
        }
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onDialInstallSuccess();
        }
    }

    private void copyAndReplaceOldCwd() {
        addSubscriber(new Func() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$QHI78JvfD2p99OlmXLZ1Lj95RXo
            @Override // com.ido.life.data.Func
            public final Object call() {
                return this.f$0.lambda$copyAndReplaceOldCwd$5$WallpaperDialPresenter();
            }
        }, new Callback<Boolean>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.8
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Boolean bool) {
                WallpaperDialPresenter.this.saveDialLog("onCwdInstallSuccess");
                if (bool.booleanValue() && WallpaperDialPresenter.this.isSupportWallpaperUsageHistory()) {
                    WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
                    wallpaperDialPresenter.uploadWallpaperDial(WallpaperDialManager.getDeviceBackgroundImagePath(wallpaperDialPresenter.mDialOtaFaceName));
                }
                WallpaperDialPresenter.this.isSetting = false;
                EventBusHelper.post(Constants.EventConstants.EVENT_CLOUD_WALLPAPER_DIAL_CHANGED);
                if (WallpaperDialPresenter.this.isAttachView()) {
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onCwdInstallSuccess();
                }
            }
        });
    }

    public /* synthetic */ Boolean lambda$copyAndReplaceOldCwd$5$WallpaperDialPresenter() {
        try {
            saveDialLog("copyAndReplaceOldCwd start ");
            this.mCwdAppBean.getSelect().setTimeColorIndex(this.timeColorIndex);
            this.mCwdAppBean.getSelect().setFuncColorIndex(this.functionColorIndex);
            this.mCwdAppBean.getSelect().setFunction(this.widget_type_list);
            if (!saveCwdAppBean(this.mCwdAppBean)) {
                throw new Exception("saveCwdAppBean Failed!");
            }
            try {
                saveDialLog("替换表盘包中子包");
                WallpaperDialManager.copyFile(WallpaperDialManager.getTempZipPath(), WallpaperDialManager.getDeviceCwdPackPath(this.mDialOtaFaceName));
            } catch (Exception e2) {
                e2.printStackTrace();
                saveDialLog("替换表盘包中子包失败：" + e2);
            }
            if (this.hasImageChanged) {
                saveDialLog("拷贝背景图");
                WallpaperDialManager.copyFile(WallpaperDialManager.getTempBgImagePath(), WallpaperDialManager.getDeviceBackgroundImagePath(this.mDialOtaFaceName));
                WallpaperDialManager.copyFile(WallpaperDialManager.getTempCwdPreviewImagePath(), WallpaperDialManager.getDevicePreviewImagePath(this.mDialOtaFaceName));
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            saveDialLog("copyAndReplaceOldCwd failed：" + e3);
        }
        return true;
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferFailed(int i, int i2) {
        if (i == 7) {
            saveDialLog("sendWallPaper2Device onTransferFailed, module = " + i + ", type = " + i2);
            onFailed(String.valueOf(i2));
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onFailed(String str) {
        saveDialLog("sendWallPaper2Device onFailed：" + str);
        boolean z = true;
        if (this.isCwd) {
            if (DialTransferTask.DEVICE_SPACE_NOT_ENOUGH.equals(str)) {
                saveDialLog("sendWallPaper2Device onFailed：device space not enough");
                if (getView() != 0) {
                    ((IWallPaperDialView) getView()).onDeviceNotEnoughStorage();
                }
                z = false;
            }
            notifyDialInstallFailed(z);
            return;
        }
        this.isSetting = false;
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onDialInstallFailed(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadWallpaperDial(String str) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            saveDialLog("uploadWallpaperDial, 网络不可用");
            return;
        }
        saveDialLog("uploadWallpaperDial path = " + str);
        DeviceManager.uploadWallpaperDial(str, this.isCwd ? this.mDialOtaFaceName : BaseDialPresenter.WALLPAPER_DIAL_NAME, getDeviceInfo(), new DeviceManager.OnDeviceCallback<WallPaperDialInfo>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.9
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(WallPaperDialInfo wallPaperDialInfo) {
                WallpaperDialPresenter.this.saveDialLog("uploadWallpaperDial onSuccess ：" + wallPaperDialInfo);
                EventBusHelper.post(400);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str2) {
                WallpaperDialPresenter.this.saveDialLog("uploadWallpaperDial onFailed ：" + str2);
            }
        });
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    public void getInstalledDialInfo() {
        if (!isConnected()) {
            saveDialLog("getInstalledDialInfo, device disconnect");
            if (isAttachView()) {
                ((IWallPaperDialView) getView()).onGetDialInfoFailed(true);
                return;
            }
            return;
        }
        this.mHandler.removeCallbacks(this.mGetDialInfoTimeoutRunnable);
        this.mHandler.postDelayed(this.mGetDialInfoTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        addDialOperateCallback();
        if (getSupportFunctionInfo().V3_get_watch_list_new) {
            saveDialLog("getInstalledDialInfo start, V3_get_watch_list_new");
            BLEManager.getDialPlateParam();
        } else {
            saveDialLog("getInstalledDialInfo start");
            BLEManager.getWatchPlateList();
        }
    }

    public void refreshInstalledDialInfo() {
        this.justInstalledDialInfo = true;
        getInstalledDialInfo();
    }

    public void switch2CurrentDial() {
        saveDialLog("switch2CurrentDial start");
        if (isAttachView()) {
            this.isSetting = true;
            this.mHandler.removeCallbacks(this.mSwitchDialTimeoutRunnable);
            this.mHandler.postDelayed(this.mSwitchDialTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
            ((IWallPaperDialView) getView()).onSwitchDialStart();
            addDialOperateCallback();
            BLEManager.setWatchPlate(BaseDialPresenter.WALLPAPER_DIAL_NAME);
        }
    }

    public void setDialConfig2Device(int i, int i2, int i3, int i4, int i5) {
        this.isSetting = true;
        saveDialLog("setDialColor2Device, colorIndex = " + i2);
        if (!isConnected()) {
            this.isSetting = false;
            saveDialLog("setDialColor2Device, device disconnect");
            if (isAttachView()) {
                ((IWallPaperDialView) getView()).onSetDialColorComplete(false);
                return;
            }
            return;
        }
        if (isAttachView()) {
            this.mHandler.removeCallbacks(this.mSetDialColorTimeoutRunnable);
            this.mHandler.postDelayed(this.mSetDialColorTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
            ((IWallPaperDialView) getView()).onSetDialColorStart();
            BLEManager.registerPhotoWallpaperOperateCallBack(new PhotoWallpaperOperateCallBack.ICallBack() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.10
                @Override // com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack.ICallBack
                public void onOperateResult(PhotoWallpaperOperation.ResponseInfo responseInfo) {
                    WallpaperDialPresenter.this.saveDialLog("getWallpaperDialInfo, onOperateResult : " + responseInfo);
                    if (responseInfo == null) {
                        return;
                    }
                    if (responseInfo.operate != 1) {
                        return;
                    }
                    WallpaperDialPresenter.this.mHandler.removeCallbacks(WallpaperDialPresenter.this.mSetDialColorTimeoutRunnable);
                    BLEManager.unregisterPhotoWallpaperOperateCallBack(this);
                    WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
                    wallpaperDialPresenter.isSetting = false;
                    if (wallpaperDialPresenter.isAttachView()) {
                        ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onSetDialColorComplete(responseInfo.err_code == 0);
                    }
                }
            });
            PhotoWallpaperOperation.SetParams setParams = new PhotoWallpaperOperation.SetParams();
            setParams.operate = 1;
            Integer numValueOf = Integer.valueOf(Integer.parseInt(getWallpaperColorList().get(i2).replace("#", ""), 16));
            setParams.time_color = numValueOf.intValue();
            if (isSupportWallpaperFunctionColorSetting()) {
                Integer numValueOf2 = Integer.valueOf(Integer.parseInt(getWallpaperColorList().get(i).replace("#", ""), 16));
                setParams.widget_icon_color = numValueOf2.intValue();
                setParams.widget_num_color = numValueOf2.intValue();
            } else {
                setParams.widget_icon_color = numValueOf.intValue();
                setParams.widget_num_color = numValueOf.intValue();
            }
            if (isSupportWallpaperFunction()) {
                if (i5 > 0) {
                    setParams.hide_type = i5;
                }
                if (i4 > 0) {
                    setParams.widget_type = i4;
                }
            }
            if (isSupportWallpaperTimeLocationSetting() && i3 > 0) {
                setParams.location = i3;
            }
            BLEManager.photoWallpaperOperate(setParams);
        }
    }

    public void deleteDial() {
        if (this.isCwd) {
            deleteCwd();
        } else {
            deleteWallpaperDial();
        }
    }

    private void deleteCwd() {
        if (isAttachView()) {
            this.isSetting = true;
            this.mHandler.removeCallbacks(this.mDeleteDialTimeoutRunnable);
            this.mHandler.postDelayed(this.mDeleteDialTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
            if (!this.isUpdateDial) {
                ((IWallPaperDialView) getView()).onDialDeleteStart();
            }
            String wallpaperOtaName = getWallpaperOtaName();
            saveDialLog("deleteCwd：" + wallpaperOtaName);
            addDialOperateCallback();
            BLEManager.deleteWatchPlate(wallpaperOtaName);
        }
    }

    private void deleteWallpaperDial() {
        saveDialLog("deleteWallpaperDial start");
        if (isAttachView()) {
            this.isSetting = true;
            this.mHandler.removeCallbacks(this.mDeleteDialTimeoutRunnable);
            this.mHandler.postDelayed(this.mDeleteDialTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
            ((IWallPaperDialView) getView()).onDialDeleteStart();
            BLEManager.registerPhotoWallpaperOperateCallBack(new PhotoWallpaperOperateCallBack.ICallBack() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.11
                @Override // com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack.ICallBack
                public void onOperateResult(PhotoWallpaperOperation.ResponseInfo responseInfo) {
                    WallpaperDialPresenter.this.saveDialLog("getWallpaperDialInfo, onOperateResult : " + responseInfo);
                    if (responseInfo != null && responseInfo.operate == 2) {
                        BLEManager.unregisterPhotoWallpaperOperateCallBack(this);
                        WallpaperDialPresenter.this.mHandler.removeCallbacks(WallpaperDialPresenter.this.mDeleteDialTimeoutRunnable);
                        WallpaperDialPresenter wallpaperDialPresenter = WallpaperDialPresenter.this;
                        wallpaperDialPresenter.isSetting = false;
                        if (wallpaperDialPresenter.isAttachView()) {
                            ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onDialDeleteComplete(responseInfo.err_code == 0);
                            if (responseInfo.err_code == 0) {
                                WallpaperDialPresenter.this.deleteLocalWallpaper();
                            }
                        }
                    }
                }
            });
            PhotoWallpaperOperation.SetParams setParams = new PhotoWallpaperOperation.SetParams();
            setParams.operate = 2;
            BLEManager.photoWallpaperOperate(setParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteLocalWallpaper() {
        addSubscriber(new Func() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$Q3dfhkmIdDQOmecs_ssOOEyB0n0
            @Override // com.ido.life.data.Func
            public final Object call() {
                return this.f$0.lambda$deleteLocalWallpaper$6$WallpaperDialPresenter();
            }
        }, new Callback<Boolean>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.12
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Boolean bool) {
                WallpaperDialPresenter.this.saveDialLog("deleteLocalWallpaper：" + bool);
            }
        });
    }

    public /* synthetic */ Boolean lambda$deleteLocalWallpaper$6$WallpaperDialPresenter() {
        try {
            String wallPaperDialPath = getWallPaperDialPath();
            if (!TextUtils.isEmpty(wallPaperDialPath)) {
                File file = new File(wallPaperDialPath);
                if (file.exists()) {
                    file.delete();
                }
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void cancelSendWallPaper() {
        BLEManager.stopTranCommonFile();
        this.isSetting = false;
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    public void saveDialLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "WallpaperDialPresenter", "【" + this.mDeviceId + "】【" + this.mDialOtaFaceName + "】：" + str);
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterWatchOperateCallBack(this.mDialOperateCallback);
        FileTransmitter.getInstance().removeListener(this);
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void setDialInfo(MyDialListEntity.DialInfo dialInfo) {
        this.mDialInfo = dialInfo;
        this.isCwd = this.mDialInfo != null;
        if (this.isCwd) {
            this.mDialOtaFaceName = this.mDialInfo.getOtaFaceName();
            this.mDialId = dialInfo.getId();
            this.mDevice = getDeviceInfo();
            BLEDevice bLEDevice = this.mDevice;
            if (bLEDevice != null) {
                this.mDeviceId = bLEDevice.mDeviceId;
            }
            this.isCircleDial = 1 == getDeviceShape();
            this.mRadius = ResourceUtil.getDimens(isBracelet() ? R.dimen.sw_dp_16 : R.dimen.sw_dp_18);
        }
    }

    public boolean hasNewVersion() {
        MyDialListEntity.DialInfo dialInfo;
        saveDialLog("hasNewVersion：mCurrentVersion = " + this.mCurrentVersion);
        return this.isCwd && (dialInfo = this.mDialInfo) != null && dialInfo.getOtaFaceVersion() != null && !TextUtils.isEmpty(this.mDialInfo.getOtaFaceVersion().getLinkUrl()) && NumUtil.isNumeric(this.mDialInfo.getOtaFaceVersion().getVersion()) && Integer.parseInt(this.mDialInfo.getOtaFaceVersion().getVersion()) > this.mCurrentVersion;
    }

    public boolean hasNewLocalVersion(int i) {
        MyDialListEntity.DialInfo dialInfo;
        return this.isCwd && (dialInfo = this.mDialInfo) != null && dialInfo.getOtaFaceVersion() != null && !TextUtils.isEmpty(this.mDialInfo.getOtaFaceVersion().getLinkUrl()) && NumUtil.isNumeric(this.mDialInfo.getOtaFaceVersion().getVersion()) && Integer.parseInt(this.mDialInfo.getOtaFaceVersion().getVersion()) > i;
    }

    public void requestDialDetailInfo() {
        requestDialInfo();
    }

    private boolean isCwdPackExist() {
        return WallpaperDialManager.isCwdPackFileExist(this.mDialOtaFaceName);
    }

    public String getWallpaperName() {
        if (!this.isCwd) {
            return LanguageUtil.getLanguageText(R.string.device_dial_photo);
        }
        MyDialListEntity.DialInfo dialInfo = this.mDialInfo;
        return dialInfo != null ? dialInfo.getOtaFaceName() : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getWallpaperOtaName() {
        if (!this.isCwd) {
            return BaseDialPresenter.WALLPAPER_DIAL_NAME;
        }
        return this.mDialOtaFaceName + ".iwf";
    }

    public String getWallpaperSize() {
        if (this.isCwd) {
            return this.mDialInfo.getOtaFaceVersion() != null ? FileSizeUtil.byteConvert(this.mDialInfo.getOtaFaceVersion().getSize()) : "";
        }
        try {
            return FileSizeUtil.byteConvert(new File(getTempWallpaperPath()).length());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private String getImagePathFromDialPackage() {
        return WallpaperDialManager.getDeviceBackgroundImagePath(this.mDialOtaFaceName);
    }

    public void loadWallpaperImage(final Context context) {
        addSubscriber(new Func() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$ycQgUqzhXWd3IzjzFeHhbfweHdQ
            @Override // com.ido.life.data.Func
            public final Object call() {
                return this.f$0.lambda$loadWallpaperImage$7$WallpaperDialPresenter();
            }
        }, new Callback<String>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.13
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            /* JADX INFO: renamed from: com.ido.life.module.device.presenter.WallpaperDialPresenter$13$1, reason: invalid class name */
            class AnonymousClass1 extends SimpleTarget<Bitmap> {
                AnonymousClass1() {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
                }

                public void onResourceReady(final Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                    CommonLogUtil.printAndSave("onResourceReady：" + bitmap);
                    ExecutorDispatcher.getInstance().dispatchOnMainThread(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$13$1$mBBdYDZQ_fMbWJIhupMBr-O5eCI
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onResourceReady$0$WallpaperDialPresenter$13$1(bitmap);
                        }
                    });
                }

                public /* synthetic */ void lambda$onResourceReady$0$WallpaperDialPresenter$13$1(Bitmap bitmap) {
                    if (WallpaperDialPresenter.this.isAttachView()) {
                        ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onLoadWallpaperImageSuccess(bitmap);
                    }
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Exception exc, Drawable drawable) {
                    super.onLoadFailed(exc, drawable);
                    CommonLogUtil.printAndSave("onLoadFailed：" + exc);
                    ExecutorDispatcher.getInstance().dispatchOnMainThread(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$13$1$jCyH1GwIAOF_ZcTHege42cvgXm4
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onLoadFailed$1$WallpaperDialPresenter$13$1();
                        }
                    });
                }

                public /* synthetic */ void lambda$onLoadFailed$1$WallpaperDialPresenter$13$1() {
                    if (WallpaperDialPresenter.this.isAttachView()) {
                        ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onLoadWallpaperImageFailed();
                    }
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(String str) {
                ImageLoaderUtil.loadImgFillet(context, str, new AnonymousClass1());
            }
        });
    }

    public /* synthetic */ String lambda$loadWallpaperImage$7$WallpaperDialPresenter() {
        if (this.isCwd && this.mDialInfo != null) {
            String imagePathFromDialPackage = getImagePathFromDialPackage();
            if (TextUtils.isEmpty(imagePathFromDialPackage)) {
                String imageUrl = this.mDialInfo.getImageUrl();
                saveDialLog("获取云端图片");
                return imageUrl;
            }
            saveDialLog("从本地表盘包中获取图片：" + imagePathFromDialPackage);
            return imagePathFromDialPackage;
        }
        String wallPaperDialPath = getWallPaperDialPath();
        saveDialLog("普通壁纸表盘：" + wallPaperDialPath);
        return wallPaperDialPath;
    }

    public void getDialConfigInfo() {
        addSubscriber(new Func<Pair<Integer, CwdAppBean>>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ido.life.data.Func
            public Pair<Integer, CwdAppBean> call() {
                int i;
                CwdAppBean cwdAppBean = WallpaperDialPresenter.this.getCwdAppBean();
                WallpaperDialPresenter.this.saveDialLog("dial info: " + GsonUtil.toJson(cwdAppBean));
                if (WallpaperDialPresenter.this.installed || cwdAppBean == null || !WallpaperDialPresenter.this.hasNewLocalVersion(cwdAppBean.getVersion())) {
                    i = 0;
                } else {
                    WallpaperDialPresenter.this.saveDialLog("getDialConfigInfo hasNewVersion, newVersion: " + WallpaperDialPresenter.this.mDialInfo.getOtaFaceVersion().getVersion() + ", oldVersion: " + cwdAppBean.getVersion());
                    WallpaperDialManager.clearCwd(WallpaperDialPresenter.this.mDialOtaFaceName);
                    i = -1;
                }
                return new Pair<>(Integer.valueOf(i), cwdAppBean);
            }
        }, new Callback<Pair<Integer, CwdAppBean>>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.15
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Pair<Integer, CwdAppBean> pair) {
                if (WallpaperDialPresenter.this.isAttachView()) {
                    WallpaperDialPresenter.this.mCwdAppBean = (CwdAppBean) pair.second;
                    if (WallpaperDialPresenter.this.mCwdAppBean == null) {
                        WallpaperDialPresenter.this.saveDialLog("显示默认配置");
                        ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onCwdInfoGetFailed(false);
                    } else if (((Integer) pair.first).intValue() == -1) {
                        WallpaperDialPresenter.this.saveDialLog("更新本地版本");
                        WallpaperDialPresenter.this.prepareCwdAppResource();
                    } else {
                        WallpaperDialPresenter.this.saveDialLog("使用本地表盘包中配置");
                        ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onGetCwdConfigSuccess(WallpaperDialPresenter.this.mCwdAppBean);
                    }
                }
            }
        });
    }

    private boolean saveCwdAppBean(CwdAppBean cwdAppBean) {
        try {
            saveDialLog("saveCwdAppBean：" + GsonUtil.toJson(cwdAppBean.getSelect()));
            return FileUtil.writeStringToFile(WallpaperDialManager.getAppJsonPath(this.mDialOtaFaceName), GsonUtil.toJson(cwdAppBean));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CwdAppBean getCwdAppBean() {
        try {
            String stringFromFile = FileUtil.readStringFromFile(WallpaperDialManager.getAppJsonPath(this.mDialOtaFaceName));
            if (TextUtils.isEmpty(stringFromFile)) {
                return null;
            }
            return (CwdAppBean) GsonUtil.fromJson(stringFromFile, CwdAppBean.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private CwdIwfBean getTempCwdIwfBean() {
        try {
            String stringFromFile = FileUtil.readStringFromFile(WallpaperDialManager.getDeviceTempJsonPath());
            if (TextUtils.isEmpty(stringFromFile)) {
                return null;
            }
            return (CwdIwfBean) GsonUtil.fromJson(stringFromFile, CwdIwfBean.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private boolean saveTempCwdIwf(CwdIwfBean cwdIwfBean) {
        try {
            saveDialLog("saveTempCwdIwf: " + cwdIwfBean.getName());
            return FileUtil.writeStringToFile(WallpaperDialManager.getDeviceTempJsonPath(), GsonUtil.toJson(cwdIwfBean));
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void requestDialInfo() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        DeviceManager.requestDialInfo(LanguageUtil.getSystemLanguage(), this.mDialId, DialDefinedUtil.appFaceVersion, basicInfo == null ? null : String.valueOf(basicInfo.user_defined_dial_main_version), getDeviceInfo().version, new DeviceManager.OnDeviceCallback<MyDialListEntity.DialInfo>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.16
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(MyDialListEntity.DialInfo dialInfo) {
                WallpaperDialPresenter.this.saveDialLog("requestDialInfo：" + dialInfo);
                if (dialInfo == null) {
                    if (WallpaperDialPresenter.this.isAttachView()) {
                        ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onCwdInfoGetFailed(true);
                    }
                } else {
                    WallpaperDialPresenter.this.mSid = dialInfo.getSid();
                    WallpaperDialPresenter.this.mDialInfo = dialInfo;
                    WallpaperDialPresenter.this.prepareCwdAppResource();
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.d("requestDialInfo failed : " + str);
                if (WallpaperDialPresenter.this.isAttachView()) {
                    ((IWallPaperDialView) WallpaperDialPresenter.this.getView()).onCwdInfoGetFailed(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareCwdAppResource() {
        saveDialLog("prepareCwdAppResource start, mDialOtaFaceName = " + this.mDialOtaFaceName);
        addSubscriber(new Func<Boolean>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.17
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ido.life.data.Func
            public Boolean call() {
                if (!WallpaperDialManager.isDeviceCwdDirExist(WallpaperDialPresenter.this.mDialOtaFaceName)) {
                    if (WallpaperDialManager.isCwdPackFileExist(WallpaperDialPresenter.this.mDialOtaFaceName)) {
                        WallpaperDialPresenter.this.saveDialLog("prepareCwdAppResource 表盘包不存在，但压缩包存在，解压");
                        return Boolean.valueOf(WallpaperDialPresenter.this.unpackCwdShell());
                    }
                    return false;
                }
                WallpaperDialPresenter.this.saveDialLog("prepareCwdAppResource 表盘包存在");
                return true;
            }
        }, new Callback<Boolean>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.18
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Boolean bool) {
                WallpaperDialPresenter.this.saveDialLog("prepareCwdAppResource result = " + bool);
                if (!bool.booleanValue()) {
                    MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion = WallpaperDialPresenter.this.mDialInfo.getOtaFaceVersion();
                    if (otaFaceVersion == null || TextUtils.isEmpty(otaFaceVersion.getLinkUrl())) {
                        return;
                    }
                    WallpaperDialPresenter.this.downloadCwdPack(otaFaceVersion.getLinkUrl(), true);
                    return;
                }
                WallpaperDialPresenter.this.getDialConfigInfo();
            }
        });
    }

    private boolean checkIfDataChanged() {
        CwdAppBean cwdAppBean;
        return this.hasImageChanged || !((cwdAppBean = this.mCwdAppBean) == null || (cwdAppBean.getSelect().getFuncColorIndex() == this.functionColorIndex && this.mCwdAppBean.getSelect().getTimeColorIndex() == this.timeColorIndex && this.mCwdAppBean.getSelect().getTimeFuncLocation() == this.timeFuncLocation && !this.widget_type_list.equals(this.mCwdAppBean.getSelect().getFunction())));
    }

    public void setCwdDeviceConfig(boolean z, boolean z2, int i, int i2, int i3, int i4, int i5) {
        initCwdConfig();
        this.isSetting = true;
        this.isUpdateDial = z;
        this.hasImageChanged = z2;
        this.functionColorIndex = i;
        this.timeColorIndex = i2;
        if (!isSupportWallpaperFunctionColorSetting()) {
            this.functionColorIndex = i2;
        }
        this.timeFuncLocation = i3;
        this.widget_type_list.add(Integer.valueOf(i4));
        this.hide_type = i5;
        saveDialLog("setCwdDeviceConfig：isUpdate = " + z + "，hasImageChanged = " + z2 + "，functionColorIndex = " + i + "，colorIndex = " + i2 + "，location = " + i3 + "，widget_type = " + i4 + "，hide_type = " + i5);
        if (checkIfDataChanged()) {
            if (this.isUpdateDial) {
                deleteCwd();
                return;
            } else {
                setCwd2Device();
                return;
            }
        }
        saveDialLog("参数为发生变化！");
        this.isSetting = false;
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onNoDataChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCwd2Device() {
        addSubscriber(new Func() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$sxem_bTwioMIikbb5sFNYwbpjiE
            @Override // com.ido.life.data.Func
            public final Object call() {
                return this.f$0.lambda$setCwd2Device$8$WallpaperDialPresenter();
            }
        }, new Callback<Boolean>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.19
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Boolean bool) {
                WallpaperDialPresenter.this.doSetCwd2Device();
            }
        });
    }

    public /* synthetic */ Boolean lambda$setCwd2Device$8$WallpaperDialPresenter() {
        try {
            if (!this.isUpdateDial && hasNewVersion()) {
                saveDialLog("更新版本，删除");
                saveDialLog("删除旧表盘包结果：" + WallpaperDialManager.clearCwd(this.mDialOtaFaceName));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            saveDialLog("删除旧表盘包出错！");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSetCwd2Device() {
        saveDialLog("setCwdDeviceConfig start ");
        addSubscriber(new Func() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$xS3Yxj1ZFqjAFT0gSoQduIHA6fQ
            @Override // com.ido.life.data.Func
            public final Object call() {
                return this.f$0.lambda$doSetCwd2Device$9$WallpaperDialPresenter();
            }
        }, new Callback<Integer>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.20
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Integer num) {
                int iIntValue = num.intValue();
                if (iIntValue == 1) {
                    WallpaperDialPresenter.this.saveDialLog("setCwdDeviceConfig success！");
                    WallpaperDialPresenter.this.checkDeviceMemory();
                    return;
                }
                if (iIntValue != 2) {
                    if (iIntValue != 3) {
                        return;
                    }
                    WallpaperDialPresenter.this.saveDialLog("setCwdDeviceConfig failed！");
                    WallpaperDialPresenter.this.notifyDialInstallFailed(true);
                    return;
                }
                WallpaperDialPresenter.this.saveDialLog("setCwdDeviceConfig need download！");
                if (NetworkUtil.isConnected(VeryFitApp.getApp())) {
                    MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion = WallpaperDialPresenter.this.mDialInfo.getOtaFaceVersion();
                    if (otaFaceVersion != null && !TextUtils.isEmpty(otaFaceVersion.getLinkUrl())) {
                        WallpaperDialPresenter.this.downloadCwdPack(otaFaceVersion.getLinkUrl(), false);
                        return;
                    } else {
                        WallpaperDialPresenter.this.saveDialLog("setCwdDeviceConfig 连接不存在");
                        WallpaperDialPresenter.this.notifyDialInstallFailed(true);
                        return;
                    }
                }
                WallpaperDialPresenter.this.saveDialLog("setCwdDeviceConfig 网络不可用，下载失败");
                WallpaperDialPresenter.this.notifyDialInstallFailed(true);
            }
        });
    }

    public /* synthetic */ Integer lambda$doSetCwd2Device$9$WallpaperDialPresenter() {
        try {
            if (WallpaperDialManager.isDeviceCwdPackExist(this.mDialOtaFaceName)) {
                onProgress(0);
                saveDialLog("表盘包存在，解压、替换、下发、覆盖");
                prepareCwdResource(false);
                replaceTempCwdImage();
                replaceTempCwdConfig();
                return 1;
            }
            if (WallpaperDialManager.isCwdPackFileExist(this.mDialOtaFaceName)) {
                saveDialLog("表盘压缩包存在，解压外壳、解压、替换、下发、拷贝");
                prepareCwdResource(true);
                replaceTempCwdImage();
                replaceTempCwdConfig();
                return 1;
            }
            saveDialLog("表盘包不存在，下载表盘");
            return 2;
        } catch (Exception e2) {
            e2.printStackTrace();
            saveDialLog("setCwd2Device，" + e2);
            return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDialInstallFailed(boolean z) {
        this.isSetting = false;
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onCwdInstallFailed(z);
        }
    }

    public boolean hasMemoryInstall(long j) {
        if (LocalDataManager.getSupportFunctionInfo() != null && LocalDataManager.getSupportFunctionInfo().V3_support_watch_capacity_size_display) {
            boolean z = ((long) usable_max_download_space_size) - j > 0;
            int i = cloud_watch_num - user_cloud_watch_num;
            saveDialLog("usable_max_download_space_size:" + usable_max_download_space_size + ",size:" + j + "--cloud_watch_num:" + cloud_watch_num + ",user_cloud_watch_num:" + user_cloud_watch_num);
            return z && i >= 1;
        }
        saveDialLog("mAvailableCount:" + mAvailableCount);
        return mAvailableCount >= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDeviceMemory() {
        long tempCwdSize = WallpaperDialManager.getTempCwdSize();
        saveDialLog("isUpdateDial = " + this.isUpdateDial);
        if (this.isUpdateDial || hasMemoryInstall(tempCwdSize)) {
            saveDialLog("checkDeviceMemory: cwdSize = " + tempCwdSize);
            packAndTransfer();
            return;
        }
        if (isAttachView()) {
            notifyDialInstallFailed(false);
            ((IWallPaperDialView) getView()).onDeviceNotEnoughStorage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean unpackCwdShell() {
        saveDialLog("unpackCwdShell");
        return WallpaperDialManager.unpackCwdPackage(this.mDialOtaFaceName);
    }

    private void prepareCwdResource(boolean z) throws Exception {
        saveDialLog("prepareCwdResource start,  unpackShell = " + z);
        if (z && !unpackCwdShell()) {
            throw new Exception("unpackeCwdShell Failed");
        }
        if (!unpackTempCwd()) {
            throw new Exception("unpackTempCwd Failed");
        }
        saveDialLog("prepareCwdResource end！");
    }

    private void packAndTransfer() {
        saveDialLog("packAndTransfer start");
        addSubscriber(new Func<String>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.21
            @Override // com.ido.life.data.Func
            public String call() {
                return WallpaperDialManager.packTempCwdPackage(WallpaperDialPresenter.this.mDialOtaFaceName);
            }
        }, new Callback<String>() { // from class: com.ido.life.module.device.presenter.WallpaperDialPresenter.22
            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str) {
            }

            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(String str) {
                WallpaperDialPresenter.this.saveDialLog("packTempCwdPackage: " + str);
                if (!TextUtils.isEmpty(str)) {
                    WallpaperDialPresenter.this.saveDialLog("packAndTransfer 压缩完成，开始传输表盘包，mDialOtaFaceName = " + WallpaperDialPresenter.this.mDialOtaFaceName);
                    FileTransmitter.getInstance().removeListener(WallpaperDialPresenter.this);
                    FileTransmitter.getInstance().addListener(WallpaperDialPresenter.this);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new DialTransferTask(str, WallpaperDialPresenter.this.mDialOtaFaceName, false, 0));
                    FileTransmitter.getInstance().execute(arrayList);
                    return;
                }
                WallpaperDialPresenter.this.saveDialLog("packTempCwdPackage failed!");
                WallpaperDialPresenter.this.notifyDialInstallFailed(true);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x015f A[EDGE_INSN: B:50:0x015f->B:34:0x015f BREAK  A[LOOP:0: B:27:0x0134->B:52:?]] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void replaceTempCwdImage() throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 499
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.device.presenter.WallpaperDialPresenter.replaceTempCwdImage():void");
    }

    private CwdAppBean.Location getTimeFuncLocation() {
        List<CwdAppBean.Location> locations = this.mCwdAppBean.getLocations();
        if (this.timeFuncLocation == this.mCwdAppBean.getSelect().getTimeFuncLocation() || !ListUtils.INSTANCE.isNotEmpty(locations)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int iIndexOf = locations.indexOf(new CwdAppBean.Location(this.timeFuncLocation, arrayList, arrayList, arrayList, arrayList));
        if (iIndexOf >= 0) {
            return locations.get(iIndexOf);
        }
        return null;
    }

    private String getColor(int i) {
        List<String> wallpaperColorList = getWallpaperColorList();
        if (i < 0 || i >= wallpaperColorList.size()) {
            i = 0;
        }
        return wallpaperColorList.get(i);
    }

    private void replaceTempCwdConfig() {
        saveDialLog("replaceCwdConfig start");
        CwdIwfBean tempCwdIwfBean = getTempCwdIwfBean();
        if (tempCwdIwfBean != null && ListUtils.INSTANCE.isNotEmpty(tempCwdIwfBean.getItem())) {
            boolean z = false;
            for (CwdIwfBean.Item item : tempCwdIwfBean.getItem()) {
                if ("icon".equals(item.getType())) {
                    if (this.hasImageChanged) {
                        item.setBg(item.getBg());
                    }
                } else {
                    if ("time".equals(item.getType())) {
                        item.setFgcolor(WallpaperDialManager.colorTo16(getColor(this.timeColorIndex)));
                        CwdAppBean.Location timeFuncLocation = getTimeFuncLocation();
                        if (timeFuncLocation != null) {
                            item.setX(timeFuncLocation.getTime().get(0).intValue());
                            item.setY(timeFuncLocation.getTime().get(1).intValue());
                        }
                    } else if ("day".equals(item.getType())) {
                        item.setFgcolor(WallpaperDialManager.colorTo16(getColor(this.functionColorIndex)));
                        CwdAppBean.Location timeFuncLocation2 = getTimeFuncLocation();
                        if (timeFuncLocation2 != null) {
                            item.setX(timeFuncLocation2.getDay().get(0).intValue());
                            item.setY(timeFuncLocation2.getDay().get(1).intValue());
                        }
                    } else if ("week".equals(item.getType())) {
                        item.setFgcolor(WallpaperDialManager.colorTo16(getColor(this.functionColorIndex)));
                        CwdAppBean.Location timeFuncLocation3 = getTimeFuncLocation();
                        if (timeFuncLocation3 != null) {
                            item.setX(timeFuncLocation3.getWeek().get(0).intValue());
                            item.setY(timeFuncLocation3.getWeek().get(1).intValue());
                        }
                    }
                    z = true;
                }
            }
            if (z) {
                saveDialLog("saveTempCwdIwf，result = " + saveTempCwdIwf(tempCwdIwfBean));
            } else {
                saveDialLog("saveTempCwdIwf，has not changed!");
            }
        }
        saveDialLog("replaceCwdConfig end");
    }

    private boolean unpackTempCwd() {
        saveDialLog("unzip start ");
        try {
            WallpaperDialManager.deleteTempCwdPack(this.mDialOtaFaceName);
            saveDialLog("unzip，zip 存在,开始解压");
            WallpaperDialManager.unpackTempCwdPackage(this.mDialOtaFaceName);
            saveDialLog("unzip 成功");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            saveDialLog("unzip 失败");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadCwdPack(String str, boolean z) {
        this.isPrepareCwdAppResource = z;
        DownloadManager.download(str, WallpaperDialManager.getCwdPackFilePath(this.mDialOtaFaceName), this);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadStart() {
        saveDialLog("~~~~~~~~~onDownloadStart~~~~~~~~~, isPrepareCwdAppResource = " + this.isPrepareCwdAppResource);
        if (this.isPrepareCwdAppResource) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$LB1MpwBG1Vb0pRWrcplsjkJsBKQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadStart$10$WallpaperDialPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadStart$10$WallpaperDialPresenter() {
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onProgress(1, 0);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadProgress(final int i) {
        saveDialLog("~~~~~~~~~onDownloadProgress~~~~~~~~~" + i);
        if (this.isPrepareCwdAppResource) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$wP1tOfAps5-xsbwfO0jC_kHe-vI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadProgress$11$WallpaperDialPresenter(i);
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadProgress$11$WallpaperDialPresenter(int i) {
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onProgress(1, i);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFailed(int i, String str) {
        saveDialLog("~~~~~~~~~onDownloadFailed~~~~~~~~~" + str);
        if (this.isPrepareCwdAppResource) {
            saveDialLog("下载失败");
            this.isPrepareCwdAppResource = false;
        } else {
            this.isSetting = false;
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$WaGOc98dOSvKu6AKGG7jLTqcp3U
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadFailed$12$WallpaperDialPresenter();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onDownloadFailed$12$WallpaperDialPresenter() {
        if (isAttachView()) {
            ((IWallPaperDialView) getView()).onDownloadFailed();
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFinish(String str) {
        saveDialLog("~~~~~~~~~onDownloadFinish~~~~~~~~~" + str);
        if (this.isPrepareCwdAppResource) {
            saveDialLog("下载完成");
            this.isPrepareCwdAppResource = false;
            prepareCwdAppResource();
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$WallpaperDialPresenter$49gKxygR6pQf2i-TdKlbI3_opNA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadFinish$13$WallpaperDialPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadFinish$13$WallpaperDialPresenter() {
        if (isAttachView()) {
            doSetCwd2Device();
            ((IWallPaperDialView) getView()).onProgress(1, 100);
            ((IWallPaperDialView) getView()).onDownloadsuccess();
        }
    }

    public boolean isTelephone() {
        return WallpaperDialManager.INSTANCE.isTelephone();
    }
}