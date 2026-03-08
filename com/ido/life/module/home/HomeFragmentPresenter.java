package com.ido.life.module.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Pair;
import com.boat.Xtend.two.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.AutoConnectErrorHappenListener;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.firmware.log.flash.ICollectFlashLogListener;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.DeviceLogClearPara;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.Units;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.net.http.Result;
import com.ido.common.utils.FileUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.DeviceRestartState;
import com.ido.life.bean.MainData;
import com.ido.life.ble.BaseConnCallback;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.ble.BaseSyncProgressCallback;
import com.ido.life.ble.BleHelper;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.AccountApi;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.api.entity.UploadMedal;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.ApiCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.HomeCard;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.TempUnitSetting;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WeekStartSetting;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.PressureEnum;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.enums.WeightBmiEnum;
import com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter;
import com.ido.life.module.home.chartdetail.vertical.ChartDetailActivity;
import com.ido.life.module.home.detail.DetailActivity;
import com.ido.life.module.home.fitness.FitnessHelperKt;
import com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailActivity;
import com.ido.life.module.home.menstrualcycle.guide.MenstrualCycleGuideActivity;
import com.ido.life.module.nodatapage.bind.HasBindNoDataActivity;
import com.ido.life.module.nodatapage.unbind.UnBindNoDataActivity;
import com.ido.life.module.user.sportrecord.SportRecordActivity;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.syncdownload.ITaskExecutedTotalCallBack;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.util.AgpsUpgradeHelper;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DateUtil;
import com.ido.life.util.DeviceUtil;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.HealthDataUtil;
import com.ido.life.util.MedalCaluteUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SPUtils;
import com.ido.life.util.UnitUtil;
import com.ido.life.util.WeatherHelper;
import com.ido.life.util.WeekReportUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class HomeFragmentPresenter extends BaseHomePresenter<IHomeView> implements SettingCallBack.ICallBack, ITaskExecutedTotalCallBack, DeviceParaChangedCallBack.ICallBack {
    private static final long AUTO_REFRESH_CARD_DURATION = 60000;
    private static final long AUTO_SYNC_DATA = 1800000;
    private static final int CMD_BATTERY_LOG = 5515;
    private static final int CMD_CLEAR_DEVICE_RESTART_LOG = 3;
    private static final int CMD_GET_DEVICE_RESTART_STATE = 330;
    private static final int CONNECT_BREAK_COUNT = 3;
    private static final long CONNECT_DURATION = 30000;
    private static final long CONNECT_FAILED_UNIT_TIME = 180000;
    private static final long LOG_INTERVAL = 43200000;
    private static final int RESTART_LOG_TIMEOUT_SECOND = 600;
    private static final long SYNC_DURATION = 300000;
    private static final int SYNC_FAILED_COUNT = 3;
    public static boolean hasNewDeviceVersion = false;
    public static final String synConnectTag = "连接同步流程-->";
    private int mConnectErrorCount;
    private boolean mNeedRequestFlashInfo;
    private Handler mTimeOutHandler;
    private static final String TAG = HomeFragmentPresenter.class.getSimpleName();
    public static boolean mIsSyncing = false;
    public static boolean mDeviceUpgrading = false;
    public static boolean mIsTelephone = false;
    private AtomicInteger mSyncFailedCount = new AtomicInteger(1);
    private long mLastestSyncTimeStamp = -1;
    private int connectBreakCount = 0;
    private long mFirstConnectBreakTimeStamp = -1;
    private Runnable mUpdateTimeRunnable = new Runnable() { // from class: com.ido.life.module.home.-$$Lambda$HomeFragmentPresenter$gHHIQURWeYIMSd9Nl_9l3IMqHlo
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$HomeFragmentPresenter();
        }
    };
    private float mHistoryDataTotalSize = 0.0f;
    private float mHistoryDataHasDownloadSize = 0.0f;
    private int mHistoryDataPullState = -1;
    private boolean mIsBreakSyn = false;
    private boolean mIsUsingAlexa = false;
    private final BaseSyncProgressCallback mSyncProgressListener = new AnonymousClass1();
    private final BaseConnCallback mConnCallback = new BaseConnCallback() { // from class: com.ido.life.module.home.HomeFragmentPresenter.8
        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            HomeFragmentPresenter.this.mConnectErrorCount = 0;
            if (HomeFragmentPresenter.this.mTimeOutHandler != null) {
                HomeFragmentPresenter.this.mTimeOutHandler.removeCallbacks(HomeFragmentPresenter.this.mConnectRunnable);
            }
            HomeFragmentPresenter.this.printAndSave("设备连接成功");
            BLEManager.getFunctionTables();
            BLEManager.unregisterGetDeviceInfoCallBack(HomeFragmentPresenter.this.mDeviceInfoCallback);
            BLEManager.registerGetDeviceInfoCallBack(HomeFragmentPresenter.this.mDeviceInfoCallback);
            BLEManager.getBasicInfo();
            BLEManager.getDeviceSummarySoftVersionInfo();
            if (DeviceConfigHelper.getSupportFunctionInfo().V3_support_get_main_sport_goal) {
                BLEManager.registerGetDeviceParaCallBack(HomeFragmentPresenter.this.mCallBack);
            }
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            if (HomeFragmentPresenter.this.mTimeOutHandler != null) {
                HomeFragmentPresenter.this.mTimeOutHandler.removeCallbacks(HomeFragmentPresenter.this.mConnectRunnable);
            }
            HomeFragmentPresenter.mIsTelephone = false;
            HomeFragmentPresenter.this.printAndSave("设备连接失败");
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            HomeFragmentPresenter.this.printAndSave("设备断开连接");
            HomeFragmentPresenter.this.mTimeOutHandler.removeCallbacks(HomeFragmentPresenter.this.mConnectRunnable);
            HomeFragmentPresenter.mIsTelephone = false;
            HomeFragmentPresenter.this.checkAndUploadBreakEvent();
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
            if (i == 5 || i == 10) {
                if (!PermissionUtil.checkSelfPermission(IdoApp.getAppContext(), PermissionUtil.getLocationPermission())) {
                    HomeFragmentPresenter.this.printAndSave("设备连接重试过程中，用户关闭了GPS定位权限，现在去请求定位权限。");
                    if (HomeFragmentPresenter.this.isAttachView()) {
                        ((IHomeView) HomeFragmentPresenter.this.getView()).onNeedLocationPermission();
                        return;
                    }
                    return;
                }
                if (BleHelper.isOpenGPS(IdoApp.getAppContext())) {
                    return;
                }
                HomeFragmentPresenter.this.printAndSave("设备连接重试过程中,Gps关闭,现在去提示用户开启GPS.");
                if (HomeFragmentPresenter.this.isAttachView()) {
                    ((IHomeView) HomeFragmentPresenter.this.getView()).onNeedOpenGps();
                    return;
                }
                return;
            }
            HomeFragmentPresenter.this.printAndSave("设备连接重试次数 i=" + i);
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            super.onInDfuMode(bLEDevice);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath(), "HomeFragmentPresenter", "onInDfuMode :" + bLEDevice.toString());
            if (HomeFragmentPresenter.this.isAttachView()) {
                ((IHomeView) HomeFragmentPresenter.this.getView()).onInDfuMode(bLEDevice);
            }
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
            super.onInitCompleted(str);
            HomeFragmentPresenter.this.printAndSave("设备功能表配置成功。");
        }
    };
    private Runnable mConnectRunnable = new Runnable() { // from class: com.ido.life.module.home.-$$Lambda$HomeFragmentPresenter$ruCq01LN2a_hh08ZOmFWHrBUFXs
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$HomeFragmentPresenter();
        }
    };
    private BaseDeviceInfoCallback mDeviceInfoCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.home.HomeFragmentPresenter.10
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFlashBinInfo(FlashBinInfo flashBinInfo) {
            super.onGetFlashBinInfo(flashBinInfo);
            HomeFragmentPresenter.this.printAndSave("设备基本信息获取成功 flashBinInfo=" + flashBinInfo);
            BLEManager.unregisterGetDeviceInfoCallBack(HomeFragmentPresenter.this.mDeviceInfoCallback);
            if (flashBinInfo != null) {
                if (flashBinInfo.version == flashBinInfo.matchVersion && flashBinInfo.status == 0) {
                    return;
                }
                HomeFragmentPresenter.this.requestFlashInfo(flashBinInfo.matchVersion);
            }
        }

        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetBasicInfo(BasicInfo basicInfo) {
            super.onGetBasicInfo(basicInfo);
            if (basicInfo == null) {
                return;
            }
            boolean zBooleanValue = ((Boolean) SPUtils.get(DeviceUpgradeNewPresenter.HAD_RESET_ALEXA, false)).booleanValue();
            Log.d("alexa test", zBooleanValue ? "isHadResetAlexa=true" : "isHadResetAlexa=false");
            CommonLogUtil.printAndSave(!zBooleanValue ? "isHadResetAlexa=false" : "isHadResetAlexa=true");
            if (zBooleanValue) {
                return;
            }
            Log.d("alexa test", "basicInfo.firmwareVersion = " + basicInfo.firmwareVersion);
            CommonLogUtil.printAndSave("basicInfo.firmwareVersion = " + basicInfo.firmwareVersion);
            HomeFragmentPresenter.this.resetAlexaInit(basicInfo.firmwareVersion);
        }
    };
    private BaseDeviceParaCallBack mSystemConstituentCallback = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.home.HomeFragmentPresenter.18
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetFirmwareAndBt3Version(FirmwareAndBt3Version firmwareAndBt3Version) {
            super.onGetFirmwareAndBt3Version(firmwareAndBt3Version);
            BLEManager.unregisterGetDeviceParaCallBack(HomeFragmentPresenter.this.mSystemConstituentCallback);
            HomeFragmentPresenter.this.printAndSave("onGetFirmwareAndBt3Version：" + firmwareAndBt3Version);
            BaseCmdPresenter.deviceThirdVersion = DeviceUtil.getDeviceThirdVersion(firmwareAndBt3Version);
            SPHelper.saveDeviceThirdVersion(BaseCmdPresenter.deviceThirdVersion);
            HomeFragmentPresenter.this.getFirmware();
            if (firmwareAndBt3Version != null) {
                if (firmwareAndBt3Version.BT_version1 == firmwareAndBt3Version.BT_match_version1 && firmwareAndBt3Version.BT_version2 == firmwareAndBt3Version.BT_match_version2 && firmwareAndBt3Version.BT_version3 == firmwareAndBt3Version.BT_match_version3) {
                    return;
                }
                HomeFragmentPresenter.this.requestSystemConstituentInfo(DeviceUtil.getDeviceBTMatchVersion(firmwareAndBt3Version));
            }
        }
    };
    private BaseDeviceParaCallBack mCallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.home.HomeFragmentPresenter.19
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetSportThreeCircleGoal(CalorieAndDistanceGoal calorieAndDistanceGoal, String str) {
            super.onGetSportThreeCircleGoal(calorieAndDistanceGoal, str);
            if (calorieAndDistanceGoal == null || TextUtils.isEmpty(str)) {
                HomeFragmentPresenter.this.printAndSave("三环目标设置信息获取成功，但是返回的信息异常不处理,calorieAndDistanceGoal=$calorieAndDistanceGoal,macAddress=$macAddress");
                return;
            }
            BLEManager.unregisterGetDeviceParaCallBack(this);
            HomeFragmentPresenter.this.printAndSave("三环目标获取成功calorieAndDistanceGoal=" + calorieAndDistanceGoal);
            String str2 = DateUtil.format(Calendar.getInstance(), DateUtil.DATE_FORMAT_YMD);
            UserTargetNew userTargetNewQueryUserTarget = GreenDaoUtil.queryUserTarget(RunTimeUtil.getInstance().getUserId(), str2);
            if (userTargetNewQueryUserTarget == null) {
                UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
                if (userTargetNewQueryUserLatestTarget == null) {
                    userTargetNewQueryUserTarget = RunTimeUtil.generateDefaultUserTargetNew(RunTimeUtil.getInstance().getUserId());
                } else {
                    userTargetNewQueryUserTarget = userTargetNewQueryUserLatestTarget.m28clone();
                }
                userTargetNewQueryUserTarget.setDate(str2);
                userTargetNewQueryUserTarget.setId(null);
                userTargetNewQueryUserTarget.setUserId(RunTimeUtil.getInstance().getUserId());
                userTargetNewQueryUserTarget.setCreateTime(System.currentTimeMillis());
            }
            userTargetNewQueryUserTarget.setUpdateTime(System.currentTimeMillis());
            userTargetNewQueryUserTarget.setCalories(calorieAndDistanceGoal.calorie);
            userTargetNewQueryUserTarget.setActivityTime((int) calorieAndDistanceGoal.mid_high_time_goal);
            userTargetNewQueryUserTarget.setWalk(calorieAndDistanceGoal.walk_goal_time * 3600);
            userTargetNewQueryUserTarget.setHasUpload(false);
            GreenDaoUtil.addUserTarget(userTargetNewQueryUserTarget);
            HomeFragmentPresenter.this.syncFitnessGoalSettingToDevice();
            if (HomeFragmentPresenter.this.getView() != null) {
                ((IHomeView) HomeFragmentPresenter.this.getView()).refreshPanelCard();
            }
        }
    };

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void onTaskExecutedFailed(NewTask.NewTaskInfo newTaskInfo) {
    }

    static /* synthetic */ int access$2508(HomeFragmentPresenter homeFragmentPresenter) {
        int i = homeFragmentPresenter.mConnectErrorCount;
        homeFragmentPresenter.mConnectErrorCount = i + 1;
        return i;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.HomeFragmentPresenter$1, reason: invalid class name */
    class AnonymousClass1 extends BaseSyncProgressCallback {
        AnonymousClass1() {
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onStart() {
            super.onStart();
            HomeFragmentPresenter.this.printAndSave("开始同步数据");
            HomeFragmentPresenter.mIsSyncing = true;
            if (HomeFragmentPresenter.this.isAttachView()) {
                ((IHomeView) HomeFragmentPresenter.this.getView()).onSyncProgress(0);
            }
            HomeFragmentPresenter.this.mLastestSyncTimeStamp = System.currentTimeMillis();
            EventBusHelper.postSticky(Constants.EventConstants.EVENT_DEVICE_SYNC_START);
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onProgress(int i) {
            super.onProgress(i);
            HomeFragmentPresenter.this.printAndSave("开始同步数据 " + i);
            if (HomeFragmentPresenter.this.isAttachView()) {
                ((IHomeView) HomeFragmentPresenter.this.getView()).onSyncProgress(i);
            }
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onSuccess() {
            super.onSuccess();
            SPHelper.setConfigSynced(true);
            BLEManager.setTime();
            HomeFragmentPresenter.this.resetPressure2Device();
            HomeFragmentPresenter.mIsSyncing = false;
            HomeFragmentPresenter.this.mSyncFailedCount.set(1);
            if (HomeFragmentPresenter.this.isAttachView()) {
                ((IHomeView) HomeFragmentPresenter.this.getView()).onSyncSuccess();
            }
            HomeFragmentPresenter.this.printAndSave("数据同步成功");
            if (HomeFragmentPresenter.this.mNeedRequestFlashInfo) {
                HomeFragmentPresenter.this.getFlashInfoFromDevice();
            }
            HomeFragmentPresenter.this.syncBatteryLog();
            AgpsUpgradeHelper.getInstance().startAgpsUpgrade();
            EventBusHelper.postSticky(Constants.EventConstants.EVENT_DEVICE_SYNC_FINISHED);
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onFailed() {
            super.onFailed();
            HomeFragmentPresenter.this.resetPressure2Device();
            int iIncrementAndGet = HomeFragmentPresenter.this.mSyncFailedCount.incrementAndGet();
            HomeFragmentPresenter.mIsSyncing = false;
            if (iIncrementAndGet > 3) {
                HomeFragmentPresenter.this.printAndSave("连续三次数据同步失败.");
                HomeFragmentPresenter.this.mSyncFailedCount.set(1);
                BLEManager.setTime();
                HomeFragmentPresenter.this.syncBatteryLog();
                if (HomeFragmentPresenter.this.isAttachView()) {
                    ((IHomeView) HomeFragmentPresenter.this.getView()).onSyncFailed();
                }
                AgpsUpgradeHelper.getInstance().startAgpsUpgrade();
                EventBusHelper.postSticky(Constants.EventConstants.EVENT_DEVICE_SYNC_FINISHED);
                return;
            }
            HomeFragmentPresenter.this.printAndSave("数据同步失败,第" + iIncrementAndGet + "次重试.");
            if (HomeFragmentPresenter.this.mTimeOutHandler == null) {
                return;
            }
            HomeFragmentPresenter.this.mTimeOutHandler.postDelayed(new Runnable() { // from class: com.ido.life.module.home.-$$Lambda$HomeFragmentPresenter$1$2gv3dAcOZZAUun_CZzErf3UDKqc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFailed$0$HomeFragmentPresenter$1();
                }
            }, 1000L);
            if (HomeFragmentPresenter.this.mNeedRequestFlashInfo) {
                HomeFragmentPresenter.this.getFlashInfoFromDevice();
            }
        }

        public /* synthetic */ void lambda$onFailed$0$HomeFragmentPresenter$1() {
            HomeFragmentPresenter.this.syncUserInfoToDevice(true);
        }
    }

    @Override // com.ido.life.base.BasePresenter
    public void attachView(IHomeView iHomeView) {
        super.attachView(iHomeView);
        DataDownLoadService.addAllTaskExecutedCallback(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncBatteryLog() {
        saveDeviceLog("syncBatteryLog device info : " + getDeviceInfo());
        if (!isConnected()) {
            saveDeviceLog("syncBatteryLog，device disconnected");
            return;
        }
        if (!getSupportFunctionInfo().V3_get_battery_log) {
            saveDeviceLog("syncBatteryLog，not support V3_get_battery_log");
            getDeviceRestartState();
        } else if (Math.abs(System.currentTimeMillis() - SPHelper.getBatteryLogTimestamp()) < LOG_INTERVAL) {
            saveDeviceLog("syncBatteryLog，Less than 12 hours since the last synchronization log");
            getDeviceRestartState();
        } else {
            BLEManager.registerDeviceResponseCommonCallBack(new DeviceResponseCommonCallBack.ICallBack() { // from class: com.ido.life.module.home.HomeFragmentPresenter.2
                @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
                public void onResponse(int i, String str) {
                    if (i != HomeFragmentPresenter.CMD_BATTERY_LOG) {
                        return;
                    }
                    HomeFragmentPresenter.this.saveDeviceLog("syncBatteryLog onResponse：" + str);
                    BLEManager.unregisterDeviceResponseCommonCallBack(this);
                    HomeFragmentPresenter.this.uploadBatteryLog2Server(str);
                    HomeFragmentPresenter.this.getDeviceRestartState();
                }
            });
            saveDeviceLog("syncBatteryLog start");
            BLEManager.getInfoFromDeviceByType(CMD_BATTERY_LOG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadBatteryLog2Server(String str) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            saveDeviceLog("uploadBatteryLog2Server，Network unavailable");
        } else {
            saveDeviceLog("uploadBatteryLog2Server start");
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_DEVICE_BATTERY_LOG, str, new AppLogUploadManager.LogUploadCallback<Boolean>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.3
                @Override // com.ido.life.util.AppLogUploadManager.LogUploadCallback
                public void onSuccess(Boolean bool) {
                    HomeFragmentPresenter.this.saveDeviceLog("uploadBatteryLog2Server，onSuccess：" + bool);
                    if (bool.booleanValue()) {
                        SPHelper.saveBatteryLogTimestamp(System.currentTimeMillis());
                        HomeFragmentPresenter.this.saveDeviceLog("uploadBatteryLog2Server，onSuccess，start clear device battery log");
                        HomeFragmentPresenter.this.clearDeviceLog(2);
                    }
                }

                @Override // com.ido.life.util.AppLogUploadManager.LogUploadCallback
                public void onFailed(int i, String str2) {
                    HomeFragmentPresenter.this.saveDeviceLog("uploadBatteryLog2Server，onFailed：" + str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDeviceLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), "device_log", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDeviceLog(int i) {
        if (isConnected()) {
            DeviceLogClearPara deviceLogClearPara = new DeviceLogClearPara();
            deviceLogClearPara.type = i;
            BLEManager.clearDeviceLog(deviceLogClearPara);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceRestartState() {
        saveDeviceLog("RESTART_LOG", "getDeviceRestartState");
        if (!isConnected()) {
            saveDeviceLog("RESTART_LOG", "getDeviceRestartState，device disconnected");
            uploadRestartLog();
        } else if (!getSupportFunctionInfo().V3_get_dev_log_state) {
            saveDeviceLog("RESTART_LOG", "getDeviceRestartState，not support V3_get_dev_log_state");
            uploadRestartLog();
        } else {
            BLEManager.registerDeviceResponseCommonCallBack(new DeviceResponseCommonCallBack.ICallBack() { // from class: com.ido.life.module.home.HomeFragmentPresenter.4
                @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
                public void onResponse(int i, String str) {
                    if (i != HomeFragmentPresenter.CMD_GET_DEVICE_RESTART_STATE) {
                        return;
                    }
                    BLEManager.unregisterDeviceResponseCommonCallBack(this);
                    HomeFragmentPresenter.this.formatRestartStateData(str);
                }
            });
            BLEManager.getInfoFromDeviceByType(CMD_GET_DEVICE_RESTART_STATE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void formatRestartStateData(String str) {
        saveDeviceLog("RESTART_LOG", "formatRestartStateData，device restart state data : " + str);
        DeviceRestartState deviceRestartState = (DeviceRestartState) GsonUtil.fromJson(str, DeviceRestartState.class);
        if (deviceRestartState != null && 1 == deviceRestartState.type) {
            syncDeviceRestartLog();
        } else {
            saveDeviceLog("RESTART_LOG", "formatRestartStateData，DeviceRestartState is null or device not restart");
            uploadRestartLog();
        }
    }

    private void syncDeviceRestartLog() {
        if (!isConnected()) {
            saveDeviceLog("RESTART_LOG", "syncDeviceRestartLog，device disconnected");
            uploadRestartLog();
            return;
        }
        String deviceRestartLogPath = LogPathImpl.getInstance().getDeviceRestartLogPath();
        File file = new File(deviceRestartLogPath);
        if (!file.exists() || !file.isDirectory()) {
            saveDeviceLog("RESTART_LOG", "syncDeviceRestartLog，directory not exists，to mkdirs");
            try {
                file.mkdirs();
            } catch (Exception e2) {
                saveDeviceLog("RESTART_LOG", "syncDeviceRestartLog，mkdirs exception ：" + e2.toString());
                return;
            }
        }
        String strConcat = deviceRestartLogPath.concat(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMDHms_2, System.currentTimeMillis())).concat(".txt");
        saveDeviceLog("RESTART_LOG", "Ready to start fetching the restart log，log path is ：" + strConcat);
        BLEManager.collectDeviceFlashLog(4, strConcat, 600, new ICollectFlashLogListener() { // from class: com.ido.life.module.home.HomeFragmentPresenter.5
            @Override // com.ido.ble.firmware.log.flash.ICollectFlashLogListener
            public void onStart() {
                HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "syncDeviceRestartLog onStart");
            }

            @Override // com.ido.ble.firmware.log.flash.ICollectFlashLogListener
            public void onFinish() {
                HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "syncDeviceRestartLog onFinish，start clear device restart log");
                HomeFragmentPresenter.this.clearDeviceLog(3);
                HomeFragmentPresenter.this.uploadRestartLog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadRestartLog() {
        saveDeviceLog("RESTART_LOG", "uploadRestartLog");
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            saveDeviceLog("RESTART_LOG", "uploadRestartLog，Network unavailable");
            return;
        }
        File file = new File(LogPathImpl.getInstance().getDeviceRestartLogPath());
        if (!file.exists() || !file.isDirectory()) {
            saveDeviceLog("RESTART_LOG", "uploadRestartLog，log directory is not exists");
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            saveDeviceLog("RESTART_LOG", "uploadRestartLog，log directory is empty");
        } else {
            final String deviceRestartLogZipPath = LogPathImpl.getInstance().getDeviceRestartLogZipPath();
            new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.home.HomeFragmentPresenter.6
                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public Object doInBackground(String... strArr) {
                    HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "uploadRestartLog，start compress restart logs");
                    FileUtil.zipFolder(LogPathImpl.getInstance().getDeviceRestartLogPath(), deviceRestartLogZipPath);
                    HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "uploadRestartLog，compress restart logs complete");
                    return null;
                }

                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public void onPostExecute(Object obj) {
                    HomeFragmentPresenter.this.uploadLogRestartLog2Server(deviceRestartLogZipPath);
                }
            }).execute("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadLogRestartLog2Server(final String str) {
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            saveDeviceLog("RESTART_LOG", "uploadLogRestartLog2Server，restart log zip is not exists");
        } else {
            saveDeviceLog("RESTART_LOG", "uploadLogRestartLog2Server start");
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_DEVICE_RESTART, "device restart log", str, new AppLogUploadManager.LogUploadCallback<Boolean>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.7
                @Override // com.ido.life.util.AppLogUploadManager.LogUploadCallback
                public void onSuccess(Boolean bool) {
                    HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "uploadLogRestartLog2Server onSuccess ：" + bool + "，delete zip file start");
                    com.ido.life.util.FileUtil.deleteFile(str);
                    HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "uploadLogRestartLog2Server，delete zip file complete");
                    if (bool.booleanValue()) {
                        HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "uploadLogRestartLog2Server，delete log file start");
                        com.ido.life.util.FileUtil.deleteDirectory(LogPathImpl.getInstance().getDeviceRestartLogPath());
                        HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "uploadLogRestartLog2Server，delete log file complete");
                    }
                }

                @Override // com.ido.life.util.AppLogUploadManager.LogUploadCallback
                public void onFailed(int i, String str2) {
                    HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "uploadLogRestartLog2Server onFailed ：" + str2 + "，delete zip file start");
                    com.ido.life.util.FileUtil.deleteFile(str);
                    HomeFragmentPresenter.this.saveDeviceLog("RESTART_LOG", "uploadLogRestartLog2Server，delete zip file complete");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDeviceLog(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetPressure2Device() {
        PressureParam pressureParam;
        if (!getSupportFunctionInfo().ex_table_main11_pressure_high_threshold_reminder || (pressureParam = LocalDataManager.getPressureParam()) == null || pressureParam.highThreshold == 80) {
            return;
        }
        pressureParam.highThreshold = 80;
        if (isConnected()) {
            BLEManager.setPressureParam(pressureParam);
        } else {
            BLEManager.setPressureParamPending(pressureParam);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndUploadBreakEvent() {
        if (System.currentTimeMillis() - this.mFirstConnectBreakTimeStamp <= CONNECT_FAILED_UNIT_TIME) {
            this.connectBreakCount++;
            if (this.connectBreakCount == 3) {
                this.connectBreakCount = 0;
                this.mFirstConnectBreakTimeStamp = -1L;
                printAndSave("发送1分钟内手环断连三次的事件给服务器");
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.BLUETOOTH_DISCONNECT_BREAK, "", new AppLogUploadManager.LogUploadCallback<Boolean>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.9
                    @Override // com.ido.life.util.AppLogUploadManager.LogUploadCallback
                    public void onSuccess(Boolean bool) {
                        HomeFragmentPresenter.this.printAndSave("上报给服务器成功");
                    }

                    @Override // com.ido.life.util.AppLogUploadManager.LogUploadCallback
                    public void onFailed(int i, String str) {
                        HomeFragmentPresenter.this.printAndSave("上报给服务器失败");
                    }
                });
                return;
            }
            return;
        }
        printAndSave("第一次断连");
        this.connectBreakCount = 1;
        this.mFirstConnectBreakTimeStamp = System.currentTimeMillis();
    }

    public /* synthetic */ void lambda$new$1$HomeFragmentPresenter() {
        BLEManager.stopScanDevices();
        if (isAttachView()) {
            ((IHomeView) getView()).onConnectFailed();
        }
        printAndSave("设备连接超时");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAlexaInit(int i) {
        SPUtils.put(DeviceUpgradeNewPresenter.HAD_RESET_ALEXA, true);
        ArrayList arrayList = new ArrayList();
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null && 7264 == basicInfo.deivceId && i >= 10) {
            CommonLogUtil.printAndSave("走V10alexa");
            Log.d("alexa test", "走V10alexa");
            arrayList.add(17);
            arrayList.add(2);
            arrayList.add(13);
            arrayList.add(18);
            arrayList.add(16);
            arrayList.add(20);
        } else {
            Log.d("alexa test", "走V9");
            CommonLogUtil.printAndSave("走V9");
            arrayList.add(17);
            arrayList.add(2);
            arrayList.add(13);
            arrayList.add(16);
            if (FunctionHelper.isSupportSpO2()) {
                arrayList.add(18);
            }
        }
        MenuList menuList = new MenuList();
        menuList.items = arrayList;
        BLEManager.setMenuList(menuList);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        this.mTimeOutHandler = new Handler();
        BLEManager.registerSettingCallBack(this);
        BLEManager.registerDeviceParaChangedCallBack(this);
        BLEManager.registerConnectCallBack(this.mConnCallback);
        BLEManager.addAutoConnectErrorOccurredListener(new AutoConnectErrorHappenListener.IListener() { // from class: com.ido.life.module.home.HomeFragmentPresenter.11
            @Override // com.ido.ble.callback.AutoConnectErrorHappenListener.IListener
            public void onErrorHappen(AutoConnectErrorHappenListener.ErrorHappenType errorHappenType, String str) {
                if (errorHappenType == AutoConnectErrorHappenListener.ErrorHappenType.GATT_ERROR_FIND_DEVICE) {
                    HomeFragmentPresenter.access$2508(HomeFragmentPresenter.this);
                    if (HomeFragmentPresenter.this.mConnectErrorCount == 5) {
                        HomeFragmentPresenter.this.printAndSave("蓝牙连接出现议程,提示用户去手动重启设备。");
                        EventBusHelper.post(Constants.EventConstants.EVENT_REBOOT_BLUETOOTH);
                    }
                }
            }
        });
        initBindDevice();
    }

    private void initBindDevice() {
        if (BLEManager.isBind()) {
            return;
        }
        List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
        if (deviceList.isEmpty()) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "HomeFragmentPresenter BLEManager.autoConnect");
        String mac = deviceList.get(0).getMac();
        BLEManager.autoConnect(mac);
        ConnectLogHelper.saveLog("HomeFragmentPresenter", "autoConnect(".concat(mac).concat(")"));
    }

    public boolean isBindDevice() {
        return BLEManager.isBind() || !SPHelper.getDeviceList().isEmpty();
    }

    public boolean isLogin() {
        return RunTimeUtil.getInstance().hasLogin();
    }

    public void disconnect() {
        if (BLEManager.isConnected()) {
            BLEManager.disConnect();
        }
    }

    public void setStepGoalToDevice() {
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
        if (userTargetNewQueryUserLatestTarget != null && !userTargetNewQueryUserLatestTarget.getHasSyncToDevice()) {
            Goal goal = LocalDataManager.getGoal();
            if (goal == null) {
                goal = new Goal();
            }
            int step = userTargetNewQueryUserLatestTarget != null ? userTargetNewQueryUserLatestTarget.getStep() : 0;
            if (step <= 0) {
                step = 10000;
            }
            if (goal.sport_step != step) {
                goal.sport_step = step;
                BLEManager.setGoalPending(goal);
                if (isConnected()) {
                    BLEManager.setGoal(goal);
                    printAndSave("设置设备步数目标");
                    return;
                }
                return;
            }
            return;
        }
        printAndSave("步数目标已经同步到设备");
    }

    public void connectDevice() {
        if (isBleEnable()) {
            if (BLEManager.isBind()) {
                this.mTimeOutHandler.removeCallbacks(this.mConnectRunnable);
                this.mTimeOutHandler.postDelayed(this.mConnectRunnable, 30000L);
                if (BLEManager.isConnected()) {
                    return;
                }
                BLEManager.autoConnect();
                ConnectLogHelper.saveLog("HomeFragmentPresenter", "autoConnect()");
                return;
            }
            return;
        }
        if (isAttachView()) {
            ((IHomeView) getView()).onNeedOpenBle();
        }
    }

    public Pair<ServerHeartRateDayData, List<BaseCharBean>> getHeartRateData() {
        ServerHeartRateDayData serverHeartRateDayDataQueryRecentHeartRate = GreenDaoUtil.queryRecentHeartRate(RunTimeUtil.getInstance().getUserId());
        if (serverHeartRateDayDataQueryRecentHeartRate == null || TextUtils.isEmpty(serverHeartRateDayDataQueryRecentHeartRate.getItems())) {
            return null;
        }
        printAndSave("查询到的最近心率数据是 heartRateDayData=" + serverHeartRateDayDataQueryRecentHeartRate.toString());
        if (!showData(ServerHeartRateDayData.class.getSimpleName(), serverHeartRateDayDataQueryRecentHeartRate.getTimestamp())) {
            printAndSave("不显示心率数据");
            return null;
        }
        return new Pair<>(serverHeartRateDayDataQueryRecentHeartRate, formatHeartRateItemList(serverHeartRateDayDataQueryRecentHeartRate.getItems()));
    }

    private DataPullConfigInfo getDataPullConfigInfo() {
        return GreenDaoUtil.queryDataPullConfigInfo(RunTimeUtil.getInstance().getUserId());
    }

    private List<BaseCharBean> formatHeartRateItemList(String str) {
        int i;
        List<int[]> list;
        int i2;
        int i3;
        int i4;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            List<int[]> listConvertHeartItems = GreenDaoUtil.convertHeartItems(str);
            if (listConvertHeartItems == null) {
                return null;
            }
            int size = listConvertHeartItems.size();
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            char c2 = 0;
            int i5 = 0;
            int i6 = 0;
            int iMax = 0;
            int iMin = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            while (true) {
                int i14 = 1;
                if (i5 >= size) {
                    break;
                }
                int[] iArr = listConvertHeartItems.get(i5);
                int i15 = iArr[c2];
                int i16 = iArr[1];
                i8 += i16;
                if (((i9 / 300) + 1) * 300 < i8) {
                    if (iMax <= 0 || iMin <= 0) {
                        list = listConvertHeartItems;
                        iMin = i11;
                        i4 = i13;
                    } else {
                        if (i7 == 0) {
                            i7 = (((i8 - i16) / 300) + 1) * 300;
                        }
                        int i17 = i8 - i16;
                        i4 = ((i17 / 300) + 1) * 300;
                        list = listConvertHeartItems;
                        int i18 = (i17 / 300) + (i17 % 300 > 0 ? 1 : 0);
                        if (i10 == 0 && i11 == 0) {
                            map.put(Integer.valueOf(i18), new BaseCharBean(-1, i18, i12 / i6));
                        } else if (iMax >= i10) {
                            map.put(Integer.valueOf(i18), new BaseCharBean(-1, i18, iMax));
                            iMin = Math.min(i11, iMin);
                        } else if (iMin <= i11) {
                            map.put(Integer.valueOf(i18), new BaseCharBean(-1, i18, iMin));
                            iMin = Math.min(i11, iMin);
                        } else {
                            map.put(Integer.valueOf(i18), new BaseCharBean(-1, i18, i12 / i6));
                            iMin = i11;
                        }
                        i10 = iMax;
                    }
                    i11 = iMin;
                    i13 = i4;
                    i6 = 0;
                    iMax = 0;
                    iMin = 0;
                    i12 = 0;
                    i9 = (i8 / 300) * 300;
                } else {
                    list = listConvertHeartItems;
                }
                if (i15 < 20 || i15 > 220) {
                    i2 = size;
                    i3 = i7;
                } else {
                    int i19 = (i8 / 300) + (i8 % 300 > 0 ? 1 : 0);
                    int i20 = i9 / 300;
                    i2 = size;
                    if (i9 % 300 <= 0) {
                        i14 = 0;
                    }
                    if (i19 - (i20 + i14) > 5) {
                        i3 = i7;
                        map.put(Integer.valueOf(i19), new BaseCharBean(-1, 0.0f, 0.0f));
                    } else {
                        i3 = i7;
                    }
                    i12 += i15;
                    i6++;
                    iMax = Math.max(iMax, i15);
                    iMin = iMin == 0 ? i15 : Math.min(iMin, i15);
                }
                i5++;
                i7 = i3;
                listConvertHeartItems = list;
                size = i2;
                c2 = 0;
            }
            if (i6 <= 0 || iMax <= 0 || iMin <= 0) {
                i = i13;
            } else {
                if (i7 == 0) {
                    i7 = (i8 / 300) * 300;
                }
                i = ((i8 / 300) + 1) * 300;
                int i21 = (i8 / 300) + (i8 % 300 > 0 ? 1 : 0);
                if (i10 == 0 && i11 == 0) {
                    map.put(Integer.valueOf(i21), new BaseCharBean(-1, i21, i12 / i6));
                } else if (iMax >= i10) {
                    map.put(Integer.valueOf(i21), new BaseCharBean(-1, i21, iMax));
                } else if (iMin <= i11) {
                    map.put(Integer.valueOf(i21), new BaseCharBean(-1, i21, iMin));
                } else {
                    map.put(Integer.valueOf(i21), new BaseCharBean(-1, i21, i12 / i6));
                }
            }
            int i22 = (i / 300) + (i % 300 > 0 ? 1 : 0);
            int i23 = (i7 / 300) + (i7 % 300 > 0 ? 1 : 0);
            float f2 = -1.0f;
            if (i22 - i23 > 143) {
                for (int i24 = (i22 - 144) + 1; i24 < i22 + 1; i24++) {
                    if (map.containsKey(Integer.valueOf(i24))) {
                        BaseCharBean baseCharBean = (BaseCharBean) map.get(Integer.valueOf(i24));
                        if (f2 < 0.0f) {
                            f2 = baseCharBean.x;
                        }
                        baseCharBean.x -= f2 - 1.0f;
                        arrayList.add(baseCharBean);
                    }
                }
            } else {
                while (i23 < i22 + 1) {
                    if (map.containsKey(Integer.valueOf(i23))) {
                        BaseCharBean baseCharBean2 = (BaseCharBean) map.get(Integer.valueOf(i23));
                        if (f2 < 0.0f) {
                            f2 = baseCharBean2.x;
                        }
                        baseCharBean2.x -= f2 - 1.0f;
                        arrayList.add(baseCharBean2);
                    }
                    i23++;
                }
            }
            return arrayList;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void syncDeviceData(boolean z) {
        if (this.mIsUsingAlexa) {
            this.mIsBreakSyn = true;
            printAndSave("alexa 使用中，停止数据下拉,待alexa结束再主动同步一次");
            if (isAttachView()) {
                ((IHomeView) getView()).onSyncFailed();
                return;
            }
            return;
        }
        if (z) {
            mIsSyncing = false;
            BLEManager.stopSyncAllData();
        } else {
            printAndSave("开始同步数据,mIsSyncing=" + mIsSyncing + ",BLEManager.isSyncAllDataIng()=" + BLEManager.isSyncAllDataIng());
            if (mIsSyncing || BLEManager.isSyncAllDataIng()) {
                return;
            }
        }
        BLEManager.connectBT();
        syncUserInfoToDevice(true);
        syncUserSettingToDevice();
        setStepGoalToDevice();
    }

    public void processEventBus(BaseMessage baseMessage) {
        UserTargetNew userTargetNewQueryUserLatestTarget;
        if (baseMessage == null || getView() == 0) {
            return;
        }
        long userId = RunTimeUtil.getInstance().getUserId();
        int type = baseMessage.getType();
        if (type == -101) {
            printAndSave("收到设备解绑成功通知。");
            ((IHomeView) getView()).onDeviceUnBindSuccess();
            return;
        }
        if (type == 813) {
            printAndSave("收到同步步数目标到设备通知。");
            StepDayData stepDailyDataByDate = LocalHealthDataManager.getInstance().getStepDailyDataByDate(DateUtil.format(Calendar.getInstance(Locale.CHINA), DateUtil.DATE_FORMAT_YMD));
            if (stepDailyDataByDate != null && (userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(userId)) != null) {
                stepDailyDataByDate.setTargetSteps(userTargetNewQueryUserLatestTarget.getStep());
                stepDailyDataByDate.update();
            }
            MedalCaluteUtil.caluteStep();
            setStepGoalToDevice();
            return;
        }
        if (type == 818) {
            this.mIsUsingAlexa = true;
            if (mIsSyncing) {
                mIsSyncing = false;
                this.mIsBreakSyn = true;
                if (BLEManager.isSyncAllDataIng()) {
                    BLEManager.stopSyncAllData();
                }
                if (isAttachView()) {
                    ((IHomeView) getView()).onSyncFailed();
                }
                AlexaLogUtil.printAndSave("Alexa break sync");
                return;
            }
            return;
        }
        if (type == 821) {
            this.mIsUsingAlexa = false;
            if (this.mIsBreakSyn) {
                this.mIsBreakSyn = false;
                if (isAttachView()) {
                    ((IHomeView) getView()).startRefresh();
                }
                AlexaLogUtil.printAndSave("Alexa end, start sync");
                return;
            }
            return;
        }
        if (type == 832) {
            printAndSave("收到连接设备中，设备鉴权失败，导致设备绑定失败通知。");
            if (isAttachView()) {
                ((IHomeView) getView()).onDeviceUnBindSuccess();
                return;
            }
            return;
        }
        if (type == 838) {
            printAndSave("收到请求固件信息通知。");
            final String str = (String) baseMessage.getData();
            Handler handler = this.mTimeOutHandler;
            if (handler != null) {
                handler.postDelayed(new Runnable() { // from class: com.ido.life.module.home.-$$Lambda$HomeFragmentPresenter$yFv3mtxZu9CLYadmV-S_a_4QBrg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$processEventBus$2$HomeFragmentPresenter(str);
                    }
                }, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
                return;
            }
            return;
        }
        if (type == 847) {
            printAndSave("收到用户首选项配置发生变化通知。");
            getCardData();
            return;
        }
        if (type == 105) {
            printAndSave("收到跨天通知。");
            SPHelper.saveBatteryLogTimestamp(0L);
            ((IHomeView) getView()).onDeviceBindCrossDay();
            WeatherHelper.requestWeatherFromServer();
            return;
        }
        if (type == 106) {
            printAndSave("收到用户设置目标改变通知。");
            getCardData();
            return;
        }
        if (type == 851) {
            printAndSave("收到停止首页数据下拉动画通知。");
            ((IHomeView) getView()).refreshAllCard();
            return;
        }
        if (type != 852) {
            switch (type) {
                case 101:
                    printAndSave("收到蓝牙连接成功通知。");
                    mIsSyncing = false;
                    ((IHomeView) getView()).onBlueToothConnect();
                    requestFirmwareInfo();
                    if (HomeHelperKt.adjustHomeCard(getUserId().longValue())) {
                        getCardData();
                    }
                    break;
                case 102:
                    printAndSave("收到蓝牙断开连接通知。");
                    hasNewDeviceVersion = false;
                    mIsSyncing = false;
                    ((IHomeView) getView()).onBlueToothDisconnect();
                    break;
                case 103:
                    printAndSave("收到设备绑定成功通知。");
                    ((IHomeView) getView()).onDeviceBindSuccess();
                    break;
                default:
                    switch (type) {
                        case 606:
                            printAndSave("收到用户获得步数达标勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.STEP_TARGET.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo != null && !userMedalInfoQueryUserMedalInfo.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo.update();
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo.getMedalId(), userMedalInfoQueryUserMedalInfo.getDate()));
                                saveUserMedal(new UploadMedal(arrayList));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.STEP_TARGET);
                                break;
                            }
                            break;
                        case 607:
                            printAndSave("收到用户获得连续7天步数达标勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo2 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.STEP_7.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo2 != null && !userMedalInfoQueryUserMedalInfo2.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo2.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo2.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo2.update();
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo2.getMedalId(), userMedalInfoQueryUserMedalInfo2.getDate()));
                                saveUserMedal(new UploadMedal(arrayList2));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.STEP_7);
                                break;
                            }
                            break;
                        case 608:
                            printAndSave("收到用户获得连续21天步数达标勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo3 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.STEP_21.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo3 != null && !userMedalInfoQueryUserMedalInfo3.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo3.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo3.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo3.update();
                                ArrayList arrayList3 = new ArrayList();
                                arrayList3.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo3.getMedalId(), userMedalInfoQueryUserMedalInfo3.getDate()));
                                saveUserMedal(new UploadMedal(arrayList3));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.STEP_21);
                                break;
                            }
                            break;
                        case 609:
                            printAndSave("收到用户获得活动卡路里首次达标勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo4 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.ACTIVE_TARGET.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo4 != null && !userMedalInfoQueryUserMedalInfo4.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo4.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo4.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo4.update();
                                ArrayList arrayList4 = new ArrayList();
                                arrayList4.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo4.getMedalId(), userMedalInfoQueryUserMedalInfo4.getDate()));
                                saveUserMedal(new UploadMedal(arrayList4));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.ACTIVE_TARGET);
                                break;
                            }
                            break;
                        case 610:
                            printAndSave("收到用户获得连续7天活动卡路里达标勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo5 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.ACTIVE_7.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo5 != null && !userMedalInfoQueryUserMedalInfo5.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo5.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo5.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo5.update();
                                ArrayList arrayList5 = new ArrayList();
                                arrayList5.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo5.getMedalId(), userMedalInfoQueryUserMedalInfo5.getDate()));
                                saveUserMedal(new UploadMedal(arrayList5));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.ACTIVE_7);
                                break;
                            }
                            break;
                        case 611:
                            printAndSave("收到用户获得连续21天活动卡路里达标勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo6 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.ACTIVE_21.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo6 != null && !userMedalInfoQueryUserMedalInfo6.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo6.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo6.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo6.update();
                                ArrayList arrayList6 = new ArrayList();
                                arrayList6.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo6.getMedalId(), userMedalInfoQueryUserMedalInfo6.getDate()));
                                saveUserMedal(new UploadMedal(arrayList6));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.ACTIVE_21);
                                break;
                            }
                            break;
                        case 612:
                            printAndSave("收到用户获得首次户外跑步勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo7 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.FIRST_RUN_OUTSIDE.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo7 != null && !userMedalInfoQueryUserMedalInfo7.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo7.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo7.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo7.update();
                                ArrayList arrayList7 = new ArrayList();
                                arrayList7.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo7.getMedalId(), userMedalInfoQueryUserMedalInfo7.getDate()));
                                saveUserMedal(new UploadMedal(arrayList7));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.FIRST_RUN_OUTSIDE);
                                break;
                            }
                            break;
                        case 613:
                            printAndSave("收到用户获得首次室内跑步勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo8 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.FIRST_RUN_HOME.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo8 != null && !userMedalInfoQueryUserMedalInfo8.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo8.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo8.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo8.update();
                                ArrayList arrayList8 = new ArrayList();
                                arrayList8.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo8.getMedalId(), userMedalInfoQueryUserMedalInfo8.getDate()));
                                saveUserMedal(new UploadMedal(arrayList8));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.FIRST_RUN_HOME);
                                break;
                            }
                            break;
                        case 614:
                            printAndSave("收到用户获得首次户外不行勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo9 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.FIRST_WALK_OUTSIDE.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo9 != null && !userMedalInfoQueryUserMedalInfo9.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo9.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo9.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo9.update();
                                ArrayList arrayList9 = new ArrayList();
                                arrayList9.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo9.getMedalId(), userMedalInfoQueryUserMedalInfo9.getDate()));
                                saveUserMedal(new UploadMedal(arrayList9));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.FIRST_WALK_OUTSIDE);
                                break;
                            }
                            break;
                        case 615:
                            printAndSave("收到用户获得首次室内步行勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo10 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.FIRST_WALK_HOME.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo10 != null && !userMedalInfoQueryUserMedalInfo10.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo10.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo10.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo10.update();
                                ArrayList arrayList10 = new ArrayList();
                                arrayList10.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo10.getMedalId(), userMedalInfoQueryUserMedalInfo10.getDate()));
                                saveUserMedal(new UploadMedal(arrayList10));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.FIRST_WALK_HOME);
                                break;
                            }
                            break;
                        case Constants.EventConstants.EVENT_FIRST_ONFOOT /* 616 */:
                            printAndSave("收到用户获得首次徒步勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo11 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.FIRST_WALK.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo11 != null && !userMedalInfoQueryUserMedalInfo11.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo11.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo11.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo11.update();
                                ArrayList arrayList11 = new ArrayList();
                                arrayList11.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo11.getMedalId(), userMedalInfoQueryUserMedalInfo11.getDate()));
                                saveUserMedal(new UploadMedal(arrayList11));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.FIRST_WALK);
                                break;
                            }
                            break;
                        case Constants.EventConstants.EVENT_DRIVING_OUT /* 617 */:
                            printAndSave("收到用户获得首次户外骑行勋章通知。");
                            UserMedalInfo userMedalInfoQueryUserMedalInfo12 = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), UserModelEnum.FIRST_DRIVE_OUTSIDE.getMedalId());
                            if (userMedalInfoQueryUserMedalInfo12 != null && !userMedalInfoQueryUserMedalInfo12.getShowToUser() && getView() != 0) {
                                userMedalInfoQueryUserMedalInfo12.setShowToUser(true);
                                userMedalInfoQueryUserMedalInfo12.setCreateTime(DateUtil.format(Calendar.getInstance(Locale.getDefault()).getTime(), "yyyy-MM-dd HH:mm:ss"));
                                userMedalInfoQueryUserMedalInfo12.update();
                                ArrayList arrayList12 = new ArrayList();
                                arrayList12.add(new UploadMedal.UploadMedalItem(userMedalInfoQueryUserMedalInfo12.getMedalId(), userMedalInfoQueryUserMedalInfo12.getDate()));
                                saveUserMedal(new UploadMedal(arrayList12));
                                ((IHomeView) getView()).showUserMedalDialog(UserModelEnum.FIRST_DRIVE_OUTSIDE);
                                break;
                            }
                            break;
                        default:
                            switch (type) {
                                case Constants.EventConstants.EVENT_SYNC_USER_SETTING_TO_DEVICE /* 803 */:
                                    printAndSave("开始同步用户设置到设备。");
                                    if (isBindAndConnected()) {
                                        syncUserSettingToDevice();
                                    }
                                    break;
                                case Constants.EventConstants.EVENT_BACK_FROM_BACK /* 804 */:
                                    printAndSave("收到APP从后台回到前台通知。");
                                    ((IHomeView) getView()).backFromBackground();
                                    break;
                                case Constants.EventConstants.EVENT_ENTER_BACK /* 805 */:
                                    printAndSave("收到APP从前台回到后台通知");
                                    break;
                                default:
                                    switch (type) {
                                        case Constants.EventConstants.EVENT_FAMILY_DEVICE_END_UPGRADE /* 808 */:
                                            printAndSave("OTA升级结束、或者结束升级页面");
                                            mDeviceUpgrading = false;
                                            break;
                                        case Constants.EventConstants.EVENT_UPLOAD_USER_INFO /* 809 */:
                                            printAndSave("收到上传用户信息通知。");
                                            upLoadUserInfo();
                                            break;
                                        case Constants.EventConstants.EVENT_SYNC_USER_INFO_TO_DEVICE /* 810 */:
                                            printAndSave("收到同步用户信息到设备通知。");
                                            syncUserInfoToDevice(false);
                                            syncFitnessGoalSettingToDevice();
                                            break;
                                        case Constants.EventConstants.EVENT_GET_USER_INFO_SUCCESS /* 811 */:
                                            printAndSave("收到用户信息获取成功或者发生改变通知。");
                                            ((IHomeView) getView()).onUserInfoChanged();
                                            break;
                                    }
                                    printAndSave("OTA开始升级");
                                    mDeviceUpgrading = true;
                                    break;
                            }
                            break;
                    }
                    break;
            }
            return;
        }
        printAndSave("收到设备重启过的通知。");
        ((IHomeView) getView()).onDeviceRestarted();
    }

    public /* synthetic */ void lambda$processEventBus$2$HomeFragmentPresenter(String str) {
        if (isAttachView() && TextUtils.equals(str, getDeviceInfo().mDeviceAddress)) {
            requestFirmwareInfo();
        }
    }

    private void requestFirmwareInfo() {
        if (isSupportDeviceThirdVersion()) {
            getThirdVersionFromDevice();
        } else {
            getFirmware();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getFirmware() {
        if (isBindAndConnected()) {
            printAndSave("getFirmware");
            hasNewDeviceVersion = false;
            DeviceManager.checkFirmwareInfo(getAppBleDevice(), new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.12
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                    if (HomeFragmentPresenter.this.isBindAndConnected()) {
                        if (otaInfo != null) {
                            try {
                                if (otaInfo.getVersion() != null) {
                                    if (otaInfo.getVersion().contains(".")) {
                                        if (!otaInfo.getVersion().equals(BaseCmdPresenter.deviceThirdVersion)) {
                                            HomeFragmentPresenter.hasNewDeviceVersion = true;
                                        }
                                    } else if (Integer.parseInt(otaInfo.getVersion()) > HomeFragmentPresenter.this.getDeviceInfo().version) {
                                        HomeFragmentPresenter.hasNewDeviceVersion = true;
                                    }
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                HomeFragmentPresenter.this.getFlashInfoFromDevice();
                                return;
                            }
                        }
                        if (HomeFragmentPresenter.hasNewDeviceVersion) {
                            HomeFragmentPresenter.this.printAndSave("requestFirmwareInfo 有新版本：");
                            if (otaInfo != null && otaInfo.isForced()) {
                                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_CHECKED_NEW_FIRMWARE, otaInfo));
                                return;
                            } else if (SPHelper.getLastOtaReminderDate().equals(DateUtil.format(DateUtil.getTodayDate(), DateUtil.DATE_FORMAT_YMD))) {
                                HomeFragmentPresenter.this.printAndSave("requestFirmwareInfo 今天已经提醒过了，不重复提醒");
                                HomeFragmentPresenter.this.getFlashInfoFromDevice();
                                return;
                            } else {
                                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_CHECKED_NEW_FIRMWARE, otaInfo));
                                return;
                            }
                        }
                        HomeFragmentPresenter.this.getFlashInfoFromDevice();
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str) {
                    HomeFragmentPresenter.this.printAndSave("checkFirmwareInfo onFailed ：" + str);
                    HomeFragmentPresenter.this.getFlashInfoFromDevice();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getFlashInfoFromDevice() {
        if (isConnected() && !isSyncing()) {
            this.mNeedRequestFlashInfo = false;
            BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
            BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
            BLEManager.getFlashBinInfo();
            return;
        }
        this.mNeedRequestFlashInfo = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFlashInfo(int i) {
        this.mNeedRequestFlashInfo = false;
        DeviceManager.checkFlashInfo(getAppBleDevice(), i, new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.13
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                if (HomeFragmentPresenter.this.isBindAndConnected() && otaInfo != null) {
                    HomeFragmentPresenter.hasNewDeviceVersion = true;
                    if (otaInfo.isForced()) {
                        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_CHECKED_NEW_FLASH, otaInfo));
                    } else if (SPHelper.getLastFlashReminderDate().equals(DateUtil.format(DateUtil.getTodayDate(), DateUtil.DATE_FORMAT_YMD))) {
                        HomeFragmentPresenter.this.printAndSave("requestFlashInfo 今天已经提醒过了，不重复提醒");
                    } else {
                        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_CHECKED_NEW_FLASH, otaInfo));
                    }
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i2, String str) {
                HomeFragmentPresenter.this.printAndSave("checkFlashInfo onFailed ：" + str);
            }
        });
    }

    public void syncUserSettingToDevice() {
        long userId = RunTimeUtil.getInstance().getUserId();
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(userId);
        WeekStartSetting weekStartSettingQueryWeekStart = GreenDaoUtil.queryWeekStart(userId);
        TimeSet timeSetQueryTimeSet = GreenDaoUtil.queryTimeSet(userId);
        TempUnitSetting tempUnitSettingQueryTempUnitSetting = GreenDaoUtil.queryTempUnitSetting(userId);
        int unit = unitSettingQueryUnitSetting != null ? unitSettingQueryUnitSetting.getUnit() : 1;
        int timeFormat = timeSetQueryTimeSet != null ? timeSetQueryTimeSet.getTimeFormat() : 0;
        int weekStart = weekStartSettingQueryWeekStart != null ? weekStartSettingQueryWeekStart.getWeekStart() : 1;
        int temp = tempUnitSettingQueryTempUnitSetting != null ? tempUnitSettingQueryTempUnitSetting.getTemp() : 1;
        Units units = LocalDataManager.getUnits();
        if (units == null) {
            units = new Units();
        }
        if (unit == 1) {
            units.dist = 1;
            units.weight = 1;
        } else {
            units.dist = 2;
            units.weight = 2;
        }
        if (timeFormat == 2) {
            units.timeMode = 2;
        } else if (timeFormat == 1) {
            units.timeMode = 1;
        } else {
            units.timeMode = DateFormat.is24HourFormat(IdoApp.getAppContext()) ? 1 : 2;
        }
        if (weekStart == 2) {
            units.weekStartDate = 0;
        } else if (weekStart == 7) {
            units.weekStartDate = 3;
        } else {
            units.weekStartDate = 1;
        }
        units.temp = temp;
        printAndSave("同步配置用户设置信息:" + units.toString());
        if (isConnected()) {
            BLEManager.setUnit(units);
        } else {
            printAndSave("设备未连接");
            BLEManager.setUnitPending(units);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void syncUserInfoToDevice(boolean r13) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.HomeFragmentPresenter.syncUserInfoToDevice(boolean):void");
    }

    private void upLoadUserInfo() {
        printAndSave("开始上传用户信息");
        long userId = RunTimeUtil.getInstance().getUserId();
        if (userId != -1 && RunTimeUtil.getInstance().enableUploadPrivateData()) {
            final UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(userId);
            if (userInfoQueryUserInfo != null) {
                UserInfo userInfoM27clone = userInfoQueryUserInfo.m27clone();
                if (userInfoM27clone.getHeightUnit() == 2) {
                    userInfoM27clone.setHeightUnit(1);
                    userInfoM27clone.setHeight(UnitUtil.inch2Cm(userInfoM27clone.getHeight()));
                }
                if (userInfoM27clone.getWeightUnit() == 2) {
                    userInfoM27clone.setWeightUnit(1);
                    userInfoM27clone.setWeight(Math.round(UnitUtil.getPound2Kg(userInfoM27clone.getWeight())));
                }
                if (!TextUtils.isEmpty(userInfoM27clone.getBirthday())) {
                    userInfoM27clone.setBirthday(userInfoM27clone.getBirthday().replaceAll("/", "-"));
                }
                AccountManager.updateUserInfo(userInfoM27clone, new OnResultCallback() { // from class: com.ido.life.module.home.HomeFragmentPresenter.14
                    @Override // com.ido.life.data.listener.OnResultCallback
                    public void onSuccess(Result result) {
                        HomeFragmentPresenter.this.printAndSave("用户信息上传成功");
                        try {
                            userInfoQueryUserInfo.setUploadSuccess(true);
                            userInfoQueryUserInfo.update();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            GreenDaoUtil.addUserInfo(userInfoQueryUserInfo);
                        }
                    }

                    @Override // com.ido.life.data.listener.OnResultCallback
                    public void onFailed(String str) {
                        HomeFragmentPresenter.this.printAndSave("用户信息上传失败");
                    }
                });
                return;
            }
            return;
        }
        printAndSave("上传用户信息,游客模式登陆或者禁用上传个人资料");
    }

    public void saveUserMedal(final UploadMedal uploadMedal) {
        if (uploadMedal == null || uploadMedal.getDatas() == null || uploadMedal.getDatas().size() == 0) {
            return;
        }
        AccountManager.saveUserMedal(uploadMedal, new AccountManager.OnCommCallback<BaseEntity>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.15
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(BaseEntity baseEntity) {
                HomeFragmentPresenter.this.printAndSave("用户勋章上传成功。");
                Iterator<UploadMedal.UploadMedalItem> it = uploadMedal.getDatas().iterator();
                while (it.hasNext()) {
                    UserMedalInfo userMedalInfoQueryUserMedalInfo = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), it.next().getMedalId());
                    if (userMedalInfoQueryUserMedalInfo != null) {
                        userMedalInfoQueryUserMedalInfo.setUploadSuccess(true);
                        userMedalInfoQueryUserMedalInfo.update();
                    }
                }
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str) {
                HomeFragmentPresenter.this.printAndSave("用户勋章上传失败。message=" + str);
            }
        });
    }

    public boolean isSyncing() {
        return mIsSyncing || BLEManager.isSyncAllDataIng();
    }

    public void setSyncing(boolean z) {
        mIsSyncing = z;
    }

    public void getCardData() {
        LinkedList<MainData> defaultHomeCardList;
        HomeCard homeCardQueryHomeCardInfo = GreenDaoUtil.queryHomeCardInfo(RunTimeUtil.getInstance().getUserId());
        if (homeCardQueryHomeCardInfo == null) {
            printAndSave("本地不存在当前用户的主页卡片设置信息，使用默认的卡片展示。");
            defaultHomeCardList = HomeCard.getDefaultHomeCardList();
            defaultHomeCardList.add(0, new MainData(2));
        } else {
            printAndSave("本地存在当前用户的主页卡片设置信息，使用本地设置。");
            LinkedList<MainData> linkedList = new LinkedList<>();
            linkedList.add(new MainData(2));
            List<Integer> valueList = homeCardQueryHomeCardInfo.getValueList();
            if (valueList != null && valueList.size() > 0) {
                int size = valueList.size();
                for (int i = 0; i < size; i++) {
                    int iIntValue = valueList.get(i).intValue();
                    if (iIntValue != 2) {
                        linkedList.add(new MainData(iIntValue));
                    }
                }
            }
            defaultHomeCardList = linkedList;
        }
        MainData mainData = new MainData();
        mainData.setViewType(10);
        defaultHomeCardList.addLast(mainData);
        if (isAttachView()) {
            ((IHomeView) getView()).onGetCardDataList(defaultHomeCardList);
        }
    }

    public void geneWeekReport() {
        long userId = RunTimeUtil.getInstance().getUserId();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        DataDownLoadState dataDownLoadStateQueryHomeDataDownloadState = GreenDaoUtil.queryHomeDataDownloadState(userId, MessageEntity.class.getSimpleName());
        if (dataDownLoadStateQueryHomeDataDownloadState == null || dataDownLoadStateQueryHomeDataDownloadState.getDownloadState() != 3) {
            return;
        }
        int i = calendar.get(7);
        if (i == 2 || i == 1 || i == 7) {
            printAndSave("开始生成健康周报");
            new WeekReportUtil().generateHealthWeekReportNewThread(userId);
        }
    }

    public boolean cardChanged(List<MainData> list, List<MainData> list2) {
        if (list == null && list2 == null) {
            return false;
        }
        if (list == null && list2 != null) {
            return true;
        }
        if ((list != null && list2 == null) || list.size() != list2.size()) {
            return true;
        }
        boolean z = false;
        for (MainData mainData : list) {
            Iterator<MainData> it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (mainData.getViewType() == it.next().getViewType()) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                break;
            }
        }
        return !z;
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        this.mTimeOutHandler.removeCallbacksAndMessages(null);
        BLEManager.unregisterSettingCallBack(this);
        BLEManager.unregisterConnectCallBack(this.mConnCallback);
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        BLEManager.unregisterDeviceParaChangedCallBack(this);
        BLEManager.unregisterGetDeviceParaCallBack(this.mCallBack);
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public Long getUserId() {
        return Long.valueOf(RunTimeUtil.getInstance().getUserId());
    }

    public Pair<Pair<Long, Integer>, Pair<Boolean, List<BarChartPoint>>> getNearPressureData() {
        char c2;
        HealthPressure healthPressureQueryNearestPressureData = GreenDaoUtil.queryNearestPressureData(getUserId().longValue());
        if (healthPressureQueryNearestPressureData == null) {
            return null;
        }
        if (!showData(HealthPressure.class.getSimpleName(), healthPressureQueryNearestPressureData.getTimeStamp())) {
            printAndSave("不显示压力数据");
            return null;
        }
        printAndSave("查询到的最近压力详情数据是pressure=" + healthPressureQueryNearestPressureData.toString());
        float f2 = ((float) PressureEnum.HIGH.Max) * 0.04f;
        List list = (List) new Gson().fromJson(healthPressureQueryNearestPressureData.getItems(), new TypeToken<List<int[]>>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.16
        }.getType());
        int i = 0;
        if (list != null && list.size() > 0) {
            boolean z = !healthPressureQueryNearestPressureData.getUploadSuccess();
            int lastestPressure = healthPressureQueryNearestPressureData.getLastestPressure();
            HashMap map = new HashMap();
            int size = list.size();
            int i2 = -1;
            int i3 = 0;
            int i4 = 0;
            int iMax = 0;
            int i5 = 1;
            int i6 = -1;
            int i7 = -1;
            while (true) {
                if (i3 >= size) {
                    break;
                }
                int[] iArr = (int[]) list.get(i3);
                i4 += iArr[i];
                if (i4 > 1440) {
                    int i8 = iArr[i];
                    break;
                }
                if (i4 > i5 * 30) {
                    int i9 = ((i4 - iArr[i]) / 30) + ((i4 - iArr[i]) % 30 > 0 ? 1 : i);
                    if (iMax > 0) {
                        if (i6 == i2) {
                            i6 = i9;
                        }
                        map.put(Integer.valueOf(i9), new BarChartPoint(i9, i9, iMax, PressureEnum.getPressureColor(iMax)));
                        i7 = i9;
                    }
                    c2 = 1;
                    i5 = (i4 / 30) + 1;
                    iMax = 0;
                } else {
                    c2 = 1;
                }
                iMax = Math.max(iMax, iArr[c2]);
                i3++;
                i2 = -1;
                i = 0;
            }
            if (iMax > 0) {
                if (i6 == -1) {
                    i6 = i5;
                }
                map.put(Integer.valueOf(i5), new BarChartPoint(i5, i5, iMax, PressureEnum.getPressureColor(iMax)));
                i7 = i5;
            }
            ArrayList arrayList = new ArrayList();
            if (i7 - i6 >= 15) {
                i6 = i7 - 15;
            }
            for (int i10 = i6; i10 < i6 + 16; i10++) {
                if (map.containsKey(Integer.valueOf(i10))) {
                    BarChartPoint barChartPoint = (BarChartPoint) map.get(Integer.valueOf(i10));
                    barChartPoint.x = i10 - i6;
                    if (barChartPoint.y < f2) {
                        barChartPoint.y = f2;
                    }
                    arrayList.add(barChartPoint);
                } else {
                    int i11 = i10 - i6;
                    arrayList.add(new BarChartPoint(i11, i11, f2));
                }
            }
            return new Pair<>(new Pair(Long.valueOf(healthPressureQueryNearestPressureData.getTimeStamp()), Integer.valueOf(lastestPressure)), new Pair(Boolean.valueOf(z), arrayList));
        }
        return new Pair<>(new Pair(Long.valueOf(healthPressureQueryNearestPressureData.getTimeStamp()), 0), null);
    }

    public List<WeightItemBean> getWeightList() {
        long userId = RunTimeUtil.getInstance().getUserId();
        WeightItemBean weightItemBeanQueryNewestWeightRecord = GreenDaoUtil.queryNewestWeightRecord(userId);
        if (weightItemBeanQueryNewestWeightRecord == null) {
            return null;
        }
        if (!showData(WeightItemBean.class.getSimpleName(), weightItemBeanQueryNewestWeightRecord.getTimeStamp())) {
            printAndSave("不显示体重数据");
            return null;
        }
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(userId);
        List<WeightItemBean> listQueryNewestWeightRecord = GreenDaoUtil.queryNewestWeightRecord(userId, 3);
        if (listQueryNewestWeightRecord != null && listQueryNewestWeightRecord.size() != 0) {
            Collections.sort(listQueryNewestWeightRecord);
        } else if (userInfoQueryUserInfo != null) {
            listQueryNewestWeightRecord = new ArrayList<>();
            WeightItemBean weightItemBean = new WeightItemBean();
            if (userInfoQueryUserInfo.getWeightUnit() == 1) {
                weightItemBean.setTotalWeight(userInfoQueryUserInfo.getWeight());
            } else {
                weightItemBean.setTotalWeight(UnitUtil.getPound2Kg(userInfoQueryUserInfo.getWeight()));
            }
            weightItemBean.setUserId(RunTimeUtil.getInstance().getUserId());
            weightItemBean.setDate(DateUtil.format(userInfoQueryUserInfo.getUpdateTime(), DateUtil.DATE_FORMAT_YMD));
            weightItemBean.setTimeStamp(userInfoQueryUserInfo.getUpdateTime());
            weightItemBean.setBmi(WeightBmiEnum.caluteBmi(weightItemBean.getTotalWeight(), userInfoQueryUserInfo.getHeightCm()));
            listQueryNewestWeightRecord.add(weightItemBean);
        }
        return listQueryNewestWeightRecord;
    }

    public Pair<Pair<Integer, Boolean>, List<Point>> getTodayStepData() {
        StepDayData stepDailyDataByDate = LocalHealthDataManager.getInstance().getStepDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        if (stepDailyDataByDate == null || stepDailyDataByDate.getNumSteps() == 0) {
            return null;
        }
        boolean z = !stepDailyDataByDate.isUploaded();
        float[] items2Array = HealthDataUtil.formatItems2Array(stepDailyDataByDate.getItems(), 24);
        ArrayList arrayList = new ArrayList();
        if (items2Array != null && items2Array.length > 0) {
            int length = items2Array.length;
            for (int i = 0; i < length; i++) {
                arrayList.add(new Point(i, Math.round(items2Array[i])));
            }
        }
        return new Pair<>(new Pair(Integer.valueOf(stepDailyDataByDate.getNumSteps()), Boolean.valueOf(z)), arrayList);
    }

    public WholeLifeCycleInfo getMenstrual() {
        WholeLifeCycleInfo wholeLifeCycleInfoQueryLatestWholeLifeCycle = HomeHelperKt.queryLatestWholeLifeCycle(RunTimeUtil.getInstance().getUserId());
        if (wholeLifeCycleInfoQueryLatestWholeLifeCycle != null && showData(LifeCycleItemBean.class.getSimpleName(), wholeLifeCycleInfoQueryLatestWholeLifeCycle.getTimeStamp())) {
            return wholeLifeCycleInfoQueryLatestWholeLifeCycle;
        }
        printAndSave("不显示生理周期数据");
        return null;
    }

    public HealthVolumeData getRecentVolume() {
        HealthVolumeData healthVolumeDataQueryRecentAmbientVolume = HomeHelperKt.queryRecentAmbientVolume(RunTimeUtil.getInstance().getUserId());
        if (healthVolumeDataQueryRecentAmbientVolume == null) {
            printAndSave("没有查找到最近一次的音量数据。");
            return null;
        }
        if (showData(HealthVolumeData.class.getSimpleName(), healthVolumeDataQueryRecentAmbientVolume.getTimestamp())) {
            return healthVolumeDataQueryRecentAmbientVolume;
        }
        printAndSave("不显示音量数据");
        return null;
    }

    public Pair<Long, Integer> getRecentOxygenUptake() {
        SportHealth sportHealthQueryRecentOxyUptake = GreenDaoUtil.queryRecentOxyUptake(getUserId().longValue());
        if (sportHealthQueryRecentOxyUptake == null) {
            return null;
        }
        return new Pair<>(Long.valueOf(sportHealthQueryRecentOxyUptake.getTimestamp()), Integer.valueOf(sportHealthQueryRecentOxyUptake.getVo2max()));
    }

    public ServerSleepDayData getNearestSleepData() {
        ServerSleepDayData nearestSleep = LocalHealthDataManager.getInstance().getNearestSleep();
        if (nearestSleep == null) {
            return null;
        }
        if (showData(ServerSleepDayData.class.getSimpleName(), nearestSleep.getTimestamp())) {
            return nearestSleep;
        }
        printAndSave("不显示睡眠数据");
        return null;
    }

    public float getTotalDistance() {
        SportDistanceBean sportDistanceBeanQuerySportDistanceByDate = GreenDaoUtil.querySportDistanceByDate(RunTimeUtil.getInstance().getUserId(), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        if (sportDistanceBeanQuerySportDistanceByDate != null) {
            return sportDistanceBeanQuerySportDistanceByDate.getTotalDistance() / 1000.0f;
        }
        return 0.0f;
    }

    public SportHealth getLatestSportRecord() {
        SportHealth sportHealthQueryLastestSportRecord = HealthManager.getInstance().queryLastestSportRecord();
        if (sportHealthQueryLastestSportRecord == null) {
            return null;
        }
        if (showData(SportHealth.class.getSimpleName(), sportHealthQueryLastestSportRecord.getTimestamp())) {
            return sportHealthQueryLastestSportRecord;
        }
        printAndSave("不显示运动数据");
        return null;
    }

    public Pair<Pair<Boolean, Integer>, Integer> getTodayActive() {
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
        int calories = (userTargetNewQueryUserLatestTarget == null || userTargetNewQueryUserLatestTarget.getCalories() <= 0) ? 500 : userTargetNewQueryUserLatestTarget.getCalories();
        CalorieDayData calorieDailyDataByDate = LocalHealthDataManager.getInstance().getCalorieDailyDataByDate(RunTimeUtil.getInstance().getUserId(), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        if (calorieDailyDataByDate != null && calorieDailyDataByDate.getTotalCalorie() > 0) {
            return new Pair<>(new Pair(Boolean.valueOf(!calorieDailyDataByDate.isUploaded()), Integer.valueOf(calorieDailyDataByDate.getActivityCalorie())), Integer.valueOf(calories));
        }
        return new Pair<>(new Pair(false, 0), Integer.valueOf(calories));
    }

    public ActiveTimeDayData getTodayTime() {
        ActiveTimeDayData activeTimeDailyDataByDate = LocalHealthDataManager.getInstance().getActiveTimeDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        if (activeTimeDailyDataByDate == null || activeTimeDailyDataByDate.getTotalSeconds() <= 0) {
            return null;
        }
        return activeTimeDailyDataByDate;
    }

    public Pair<Pair<Boolean, Integer>, Integer> getTodayWalkHour() {
        boolean z;
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
        int walk = (userTargetNewQueryUserLatestTarget == null || userTargetNewQueryUserLatestTarget.getWalk() <= 0) ? 12 : userTargetNewQueryUserLatestTarget.getWalk() / 3600;
        WalkDayData walkDayData = LocalHealthDataManager.getInstance().getWalkDayData(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        int i = 0;
        if (walkDayData != null) {
            z = !walkDayData.isUploaded();
            float[] walkItems = HealthDataUtil.formatWalkItems(walkDayData, true);
            if (walkItems.length > 0) {
                int length = walkItems.length;
                int i2 = 0;
                while (i < length) {
                    if (walkItems[i] == 2.0f) {
                        i2++;
                    }
                    i++;
                }
                i = i2;
            }
        } else {
            z = false;
        }
        return new Pair<>(new Pair(Boolean.valueOf(z), Integer.valueOf(i)), Integer.valueOf(walk));
    }

    public ServerBloodOxyDayData getNearBloodOxyDailyData() {
        ServerBloodOxyDayData nearBloodOxyDailyData = LocalHealthDataManager.getInstance().getNearBloodOxyDailyData();
        if (nearBloodOxyDailyData == null || nearBloodOxyDailyData.getMaxValue() == 0) {
            return null;
        }
        if (showData(ServerBloodOxyDayData.class.getSimpleName(), nearBloodOxyDailyData.getTimestamp())) {
            return nearBloodOxyDailyData;
        }
        printAndSave("不显示血氧数据");
        return null;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.HomeFragmentPresenter$21, reason: invalid class name */
    static /* synthetic */ class AnonymousClass21 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType = new int[SettingCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.GOAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.UNIT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.USER_INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.ido.ble.callback.SettingCallBack.ICallBack
    public void onSuccess(SettingCallBack.SettingType settingType, Object obj) {
        long userId = RunTimeUtil.getInstance().getUserId();
        int i = AnonymousClass21.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
        if (i == 1) {
            printAndSave("时间制式同步到设备成功");
            TimeSet timeSetQueryTimeSet = GreenDaoUtil.queryTimeSet(userId);
            if (timeSetQueryTimeSet != null) {
                timeSetQueryTimeSet.setHasSyncDeviceSuccess(true);
                timeSetQueryTimeSet.update();
                return;
            }
            return;
        }
        if (i == 2) {
            printAndSave("目标同步到设备成功");
            UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(userId);
            if (userTargetNewQueryUserLatestTarget != null) {
                userTargetNewQueryUserLatestTarget.setHasSyncToDevice(true);
                userTargetNewQueryUserLatestTarget.update();
                return;
            }
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            printAndSave("用户信息同步到设备成功");
            return;
        }
        printAndSave("单位同步到设备成功");
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(userId);
        if (unitSettingQueryUnitSetting != null) {
            unitSettingQueryUnitSetting.setHasSyncDeviceSuccess(true);
            unitSettingQueryUnitSetting.update();
        }
        WeekStartSetting weekStartSettingQueryWeekStart = GreenDaoUtil.queryWeekStart(userId);
        if (weekStartSettingQueryWeekStart != null) {
            weekStartSettingQueryWeekStart.setHasSyncDeviceSuccess(true);
            weekStartSettingQueryWeekStart.update();
        }
        printAndSave("时间制式同步到设备成功");
        TimeSet timeSetQueryTimeSet2 = GreenDaoUtil.queryTimeSet(userId);
        if (timeSetQueryTimeSet2 != null) {
            timeSetQueryTimeSet2.setHasSyncDeviceSuccess(true);
            timeSetQueryTimeSet2.update();
        }
    }

    public String getDateShowByTimeStamp(long j) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
        calendar2.setTimeInMillis(j);
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        int i4 = calendar2.get(1);
        int i5 = calendar2.get(2);
        int i6 = calendar2.get(5);
        if (i != i4 || i2 != i5) {
            if (i == i4) {
                return DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_DM_1);
            }
            return DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_YMD_2);
        }
        if (i3 == i6) {
            int i7 = calendar.get(11);
            int i8 = calendar2.get(11);
            if (i7 - i8 >= 1) {
                return String.format("%02d:%02d", Integer.valueOf(i8), Integer.valueOf(calendar2.get(12)));
            }
            int i9 = calendar.get(12) - calendar2.get(12);
            return i9 >= 1 ? String.format(LanguageUtil.getLanguageText(R.string.before_minute_format), Integer.valueOf(i9)) : LanguageUtil.getLanguageText(R.string.date_near);
        }
        calendar.add(5, -1);
        if (calendar.get(5) == i6) {
            return String.format(LanguageUtil.getLanguageText(R.string.before_day_hour_minute_format), Integer.valueOf(calendar2.get(11)), Integer.valueOf(calendar2.get(12)));
        }
        return DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_DM_1);
    }

    @Override // com.ido.ble.callback.SettingCallBack.ICallBack
    public void onFailed(SettingCallBack.SettingType settingType) {
        int i = AnonymousClass21.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
        if (i == 1) {
            printAndSave("时间制式同步到设备失败");
            return;
        }
        if (i == 2) {
            printAndSave("目标同步到设备失败");
        } else if (i == 3) {
            printAndSave("单位同步到设备失败");
        } else {
            if (i != 4) {
                return;
            }
            printAndSave("用户信息同步到设备失败");
        }
    }

    public void veryEmailHasBind() {
        AccountApi.api.verifyEmailHasBind().enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.17
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                HomeFragmentPresenter.this.printAndSave("用户邮箱验证成功。");
                if (HomeFragmentPresenter.this.isAttachView()) {
                    if (baseEntityNew == null) {
                        ((IHomeView) HomeFragmentPresenter.this.getView()).onGetUserEmailBindStateFailed();
                    } else {
                        ((IHomeView) HomeFragmentPresenter.this.getView()).updateUserEmailBindState(baseEntityNew.getResult().booleanValue());
                    }
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                HomeFragmentPresenter.this.printAndSave("用户邮箱验证失败 code=" + i + ",message=" + str);
                if (HomeFragmentPresenter.this.isAttachView()) {
                    ((IHomeView) HomeFragmentPresenter.this.getView()).onGetUserEmailBindStateFailed();
                }
            }
        });
    }

    public void startUpdateTime() {
        printAndSave("开始定时刷新主页卡片。");
        Handler handler = this.mTimeOutHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mUpdateTimeRunnable);
            this.mTimeOutHandler.postDelayed(this.mUpdateTimeRunnable, 60000L);
        }
    }

    public void stopUpdateTime() {
        printAndSave("停止定时刷新主页卡片。");
        Handler handler = this.mTimeOutHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mUpdateTimeRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reallyUpdateTime, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0$HomeFragmentPresenter() {
        Handler handler;
        if (isAttachView()) {
            boolean zRefreshRecordTime = ((IHomeView) getView()).refreshRecordTime();
            if (((IHomeView) getView()).refreshPressureTime()) {
                zRefreshRecordTime = true;
            }
            if (((IHomeView) getView()).refreshHeartRateTime()) {
                zRefreshRecordTime = true;
            }
            if (((IHomeView) getView()).refreshSleepTime()) {
                zRefreshRecordTime = true;
            }
            if (((IHomeView) getView()).refreshOxyTime()) {
                zRefreshRecordTime = true;
            }
            if (((IHomeView) getView()).refreshWeightTime()) {
                zRefreshRecordTime = true;
            }
            if (!zRefreshRecordTime || (handler = this.mTimeOutHandler) == null) {
                return;
            }
            handler.postDelayed(this.mUpdateTimeRunnable, 60000L);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void gotoDetailPage(Context context, int i) {
        Intent intent;
        Intent intent2;
        if (context == null) {
            return;
        }
        Intent intent3 = null;
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = RunTimeUtil.getInstance().hasLogin() ? GreenDaoUtil.queryDataPullConfigInfo(RunTimeUtil.getInstance().getUserId()) : null;
        switch (i) {
            case 0:
                if (hasStepData() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getStepCount() > 0)) {
                    intent = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(ChartDetailActivity.PAGE_TYPE, 0);
                    intent.putExtras(bundle);
                } else if (isBind()) {
                    intent = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 0);
                } else {
                    intent = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 0);
                }
                break;
            case 1:
                if (hasDistance() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getDistanceCount() > 0)) {
                    intent = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt(ChartDetailActivity.PAGE_TYPE, 1);
                    intent.putExtras(bundle2);
                } else if (isBind()) {
                    intent = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 1);
                } else {
                    intent = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 1);
                }
                break;
            case 2:
                if (hasCalorie() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getCalorieCount() > 0)) {
                    intent = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt(ChartDetailActivity.PAGE_TYPE, 2);
                    intent.putExtras(bundle3);
                } else if (isBind()) {
                    intent = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 2);
                } else {
                    intent = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 2);
                }
                break;
            case 3:
                if (hasActivityData() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getExerciseCount() > 0)) {
                    intent3 = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle4 = new Bundle();
                    bundle4.putInt(ChartDetailActivity.PAGE_TYPE, 15);
                    intent3.putExtras(bundle4);
                } else if (isBind()) {
                    intent3 = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 3);
                } else {
                    intent3 = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 3);
                }
                intent = intent3;
                break;
            case 4:
                if (hasWalkData() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getWalkCount() > 0)) {
                    intent3 = new Intent(context, (Class<?>) DetailActivity.class);
                    intent3.putExtra(DetailActivity.DATA_TYPE, i);
                } else if (isBind()) {
                    intent3 = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 4);
                } else {
                    intent3 = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 4);
                }
                intent = intent3;
                break;
            case 5:
            case 13:
            case 14:
            case 15:
            case 18:
            default:
                intent = intent3;
                break;
            case 6:
                if (hasSportRecord() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getSportCount() > 0)) {
                    intent3 = new Intent(context, (Class<?>) SportRecordActivity.class);
                } else {
                    intent3 = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 6);
                }
                intent = intent3;
                break;
            case 7:
                if (hasSleepData() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getSleepCount() > 0)) {
                    intent3 = new Intent(context, (Class<?>) DetailActivity.class);
                    intent3.putExtra(DetailActivity.DATA_TYPE, i);
                } else if (isBind()) {
                    intent3 = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 7);
                } else {
                    intent3 = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 7);
                }
                intent = intent3;
                break;
            case 8:
                if (hasHeartRate() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getHeartRateCount() > 0)) {
                    intent = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle5 = new Bundle();
                    bundle5.putInt(ChartDetailActivity.PAGE_TYPE, 8);
                    intent.putExtras(bundle5);
                } else if (isBind()) {
                    intent = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 8);
                } else {
                    intent = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 8);
                }
                break;
            case 9:
                if (hasPressure() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getPressureCount() > 0)) {
                    intent = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle6 = new Bundle();
                    bundle6.putInt(ChartDetailActivity.PAGE_TYPE, 9);
                    intent.putExtras(bundle6);
                } else if (isBind()) {
                    intent = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 9);
                } else {
                    intent = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 9);
                }
                break;
            case 10:
                if (hasBloodOxyData() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getBloodOxyCount() > 0)) {
                    intent3 = new Intent(context, (Class<?>) DetailActivity.class);
                    intent3.putExtra(DetailActivity.DATA_TYPE, i);
                } else if (isBind()) {
                    intent3 = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 10);
                } else {
                    intent3 = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 10);
                }
                intent = intent3;
                break;
            case 11:
                intent3 = new Intent(context, (Class<?>) ChartDetailActivity.class);
                Bundle bundle7 = new Bundle();
                bundle7.putInt(ChartDetailActivity.PAGE_TYPE, 11);
                intent3.putExtras(bundle7);
                intent = intent3;
                break;
            case 12:
                if (GreenDaoUtil.queryMenstruationConfig(RunTimeUtil.getInstance().getUserId()) == null) {
                    intent3 = new Intent(context, (Class<?>) MenstrualCycleGuideActivity.class);
                    intent3.putExtra(Constants.INTENT_USER_ID, RunTimeUtil.getInstance().getUserId());
                } else {
                    intent3 = new Intent(context, (Class<?>) MenstrualCycleDetailActivity.class);
                    intent3.putExtra(Constants.INTENT_USER_ID, RunTimeUtil.getInstance().getUserId());
                }
                intent = intent3;
                break;
            case 16:
                if (hasCalorie() || hasActivityData() || hasWalkData() || (dataPullConfigInfoQueryDataPullConfigInfo != null && (dataPullConfigInfoQueryDataPullConfigInfo.getCalorieCount() > 0 || dataPullConfigInfoQueryDataPullConfigInfo.getExerciseCount() > 0 || dataPullConfigInfoQueryDataPullConfigInfo.getWalkCount() > 0))) {
                    intent = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle8 = new Bundle();
                    bundle8.putInt(ChartDetailActivity.PAGE_TYPE, 16);
                    intent.putExtras(bundle8);
                } else if (isBind()) {
                    intent = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 16);
                } else {
                    intent = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 16);
                }
                break;
            case 17:
                if (hasAmbientVolume() || (dataPullConfigInfoQueryDataPullConfigInfo != null && dataPullConfigInfoQueryDataPullConfigInfo.getNoiseCount() > 0)) {
                    intent = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle9 = new Bundle();
                    bundle9.putInt(ChartDetailActivity.PAGE_TYPE, 17);
                    intent.putExtras(bundle9);
                } else {
                    if (isBind()) {
                        intent = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    } else {
                        intent = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    }
                    intent.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 17);
                }
                break;
            case 19:
                if (HomeHelperKt.hasOxygenUpdateData(RunTimeUtil.getInstance().getUserId(), null)) {
                    intent3 = new Intent(context, (Class<?>) ChartDetailActivity.class);
                    Bundle bundle10 = new Bundle();
                    bundle10.putInt(ChartDetailActivity.PAGE_TYPE, 19);
                    intent3.putExtras(bundle10);
                } else {
                    if (isBind()) {
                        intent2 = new Intent(context, (Class<?>) HasBindNoDataActivity.class);
                    } else {
                        intent2 = new Intent(context, (Class<?>) UnBindNoDataActivity.class);
                    }
                    intent3 = intent2;
                    intent3.putExtra(HasBindNoDataActivity.getKEY_TYPE(), 19);
                }
                intent = intent3;
                break;
        }
        if (intent != null) {
            context.startActivity(intent);
        }
    }

    private boolean showData(String str, long j) {
        DataPullConfigInfo dataPullConfigInfo;
        if (!isLogin()) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        DataDownLoadState dataDownLoadStateQueryHomeDataDownloadState = GreenDaoUtil.queryHomeDataDownloadState(getUserId().longValue(), str);
        if (dataDownLoadStateQueryHomeDataDownloadState != null && dataDownLoadStateQueryHomeDataDownloadState.getDownloadState() == 3) {
            return true;
        }
        dataPullConfigInfo = getDataPullConfigInfo();
        switch (str) {
            case "ServerBloodOxyDayData":
                return dataPullConfigInfo != null && dataPullConfigInfo.getBloodEndTime() <= j;
            case "CalorieDayData":
                return dataPullConfigInfo != null && dataPullConfigInfo.getCalorieEndTime() <= j;
            case "SportDistanceBean":
                return dataPullConfigInfo != null && dataPullConfigInfo.getDistanceEndTime() <= j;
            case "ActiveTimeDayData":
                return dataPullConfigInfo != null && dataPullConfigInfo.getExerciseEndTime() <= j;
            case "LifeCycleItemBean":
                return dataPullConfigInfo != null && dataPullConfigInfo.getMensesEndTime() <= j;
            case "ServerHeartRateDayData":
                return dataPullConfigInfo != null && dataPullConfigInfo.getHeartRateEndTime() <= j;
            case "HealthPressure":
                return dataPullConfigInfo != null && dataPullConfigInfo.getPressureEndTime() <= j;
            case "ServerSleepDayData":
                return dataPullConfigInfo != null && dataPullConfigInfo.getSleepEndTime() <= j;
            case "SportHealth":
                return dataPullConfigInfo != null && dataPullConfigInfo.getSportEndTime() <= j;
            case "StepDayData":
                return dataPullConfigInfo != null && dataPullConfigInfo.getStepEndTime() <= j;
            case "WalkDayData":
                return dataPullConfigInfo != null && dataPullConfigInfo.getWalkEndTime() <= j;
            case "WeightItemBean":
                return dataPullConfigInfo != null && dataPullConfigInfo.getWeightEndTime() <= j;
            case "UserTargetNew":
                return dataPullConfigInfo != null && dataPullConfigInfo.getTargetEndTime() <= j;
            case "HealthVolumeData":
                return dataPullConfigInfo != null && dataPullConfigInfo.getNoiseEndTime() <= j;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printAndSave(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.d(TAG, str);
        CommonLogUtil.printAndSave(str);
    }

    public boolean autoSyncDeviceData() {
        return System.currentTimeMillis() - this.mLastestSyncTimeStamp >= AUTO_SYNC_DATA;
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void onTaskExecutedSuccess(NewTask.NewTaskInfo newTaskInfo) {
        long userId;
        if (newTaskInfo.getTaskId() != 0) {
        }
        String taskTag = newTaskInfo.getBuilder().getTaskTag();
        if (!TextUtils.isEmpty(taskTag) && isAttachView()) {
            String strTrim = taskTag.trim();
            userId = RunTimeUtil.getInstance().getUserId();
            switch (strTrim) {
                case "SportHealth":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("最近一次运动数据下拉成功");
                        ((IHomeView) getView()).refreshPanelCard();
                        ((IHomeView) getView()).refreshSportCard();
                        ((IHomeView) getView()).refreshOxygenUptakeCard();
                        break;
                    } else {
                        printAndSave("运动数据下拉完成");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, SportHealth.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshPanelCard();
                            ((IHomeView) getView()).refreshSportCard();
                            ((IHomeView) getView()).refreshOxygenUptakeCard();
                        }
                        break;
                    }
                    break;
                case "ServerHeartRateDayData":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("心率最近一次数据下拉成功");
                        ((IHomeView) getView()).refreshHeartRateCard();
                        break;
                    } else {
                        printAndSave("心率数据下拉完成");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, ServerHeartRateDayData.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshHeartRateCard();
                        }
                        break;
                    }
                    break;
                case "CalorieDayData":
                case "ActiveTimeDayData":
                case "WalkDayData":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("活动面板最近一次数据下拉完成");
                        ((IHomeView) getView()).refreshPanelCard();
                        break;
                    } else {
                        printAndSave("活动面板数据下拉完成");
                        ((IHomeView) getView()).refreshPanelCard();
                        break;
                    }
                    break;
                case "ServerBloodOxyDayData":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("血氧最近一次数据下拉成功");
                        ((IHomeView) getView()).refreshBloodOxyCard();
                        break;
                    } else {
                        printAndSave("血氧数据下拉完成");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, ServerBloodOxyDayData.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshBloodOxyCard();
                        }
                        break;
                    }
                    break;
                case "SportDistanceBean":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("距离最近一次数据下拉成功");
                        ((IHomeView) getView()).refreshStepCard();
                        break;
                    } else {
                        printAndSave("距离数据下拉完成");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, SportDistanceBean.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshStepCard();
                        }
                        break;
                    }
                    break;
                case "StepDayData":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("步数最近一次数据下拉成功");
                        ((IHomeView) getView()).refreshStepCard();
                        break;
                    } else {
                        printAndSave("步数数据下拉完成");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, StepDayData.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshStepCard();
                        }
                        break;
                    }
                    break;
                case "HealthPressure":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("压力最近一次数据下拉成功");
                        ((IHomeView) getView()).refreshPressureCard();
                        break;
                    } else {
                        printAndSave("压力数据下拉完成");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, HealthPressure.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshPressureCard();
                        }
                        break;
                    }
                    break;
                case "ServerSleepDayData":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("睡眠最近一次数据下拉成功");
                        ((IHomeView) getView()).refreshSleepCard();
                        break;
                    } else {
                        printAndSave("睡眠数据下拉完成");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, ServerSleepDayData.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshSleepCard();
                        }
                        break;
                    }
                    break;
                case "WeightItemBean":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("最近一次体重数据下拉成功");
                        ((IHomeView) getView()).refreshWeightCard();
                        break;
                    } else {
                        printAndSave("体重数据下拉完成");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, WeightItemBean.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshWeightCard();
                        }
                        break;
                    }
                    break;
                case "LifeCycleItemBean":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("首次生理周期数据下拉成功");
                        ((IHomeView) getView()).refreshMenstrualCard();
                        break;
                    } else {
                        printAndSave("生理周期数据下拉成功");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, LifeCycleItemBean.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshMenstrualCard();
                        }
                        break;
                    }
                    break;
                case "HealthVolumeData":
                    if (newTaskInfo.getTaskId() == 0) {
                        printAndSave("最近一次环境音量数据下拉成功");
                        ((IHomeView) getView()).refreshVolumeCard();
                        break;
                    } else {
                        printAndSave("环境音量历史数据下拉成功");
                        if (GreenDaoUtil.queryAllDataLoadSuccess(userId, HealthVolumeData.class.getSimpleName())) {
                            ((IHomeView) getView()).refreshVolumeCard();
                        }
                        break;
                    }
                    break;
            }
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void onAllTaskCompleted(boolean z) {
        printAndSave("所有任务执行完成");
        if (this.mHistoryDataPullState != 2 || z) {
            return;
        }
        if (HomeHelperKt.historyDataPullSuccess(getUserId().longValue())) {
            CommonLogUtil.d(TAG, "数据下拉任务全部执行完成，所有任务都执行成功");
            this.mHistoryDataPullState = 3;
            if (getView() != 0) {
                ((IHomeView) getView()).historyDataLoadSuccess();
                return;
            }
            return;
        }
        CommonLogUtil.d(TAG, "数据下拉任务全部执行完成，有部分任务执行失败");
        this.mHistoryDataPullState = 4;
        if (getView() != 0) {
            ((IHomeView) getView()).historyDataLoadFailed();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void updateTaskProgress(float f2) {
        if (this.mHistoryDataPullState == 2) {
            if (this.mHistoryDataTotalSize <= 0.0f) {
                initHistoryDataTotalSize();
            }
            if (f2 >= this.mHistoryDataHasDownloadSize) {
                this.mHistoryDataHasDownloadSize = f2;
            }
            if (this.mHistoryDataTotalSize <= 0.0f || !isAttachView()) {
                return;
            }
            printAndSave("总体数据下拉进度progress=" + f2);
            ((IHomeView) getView()).updateHistoryPullProgress();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void startHomeTask() {
        printAndSave("开始下拉首页任务.");
        if (isAttachView()) {
            ((IHomeView) getView()).refreshAllCard();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void startHistoryTask() {
        printAndSave("开始下拉历史任务.");
        if (isLogin()) {
            if (SPHelper.autoShowHistoryDataPullState()) {
                ((IHomeView) getView()).startLoadHistoryData();
            }
            this.mHistoryDataPullState = 2;
        }
    }

    public int getHistoryDataPullState() {
        return this.mHistoryDataPullState;
    }

    public void setHistoryDataPullState(int i) {
        this.mHistoryDataPullState = i;
    }

    public int getHistoryDataDownloadProgress() {
        if (this.mHistoryDataTotalSize <= 0.0f) {
            initHistoryDataTotalSize();
        }
        float f2 = this.mHistoryDataTotalSize;
        if (f2 <= 0.0f) {
            return 100;
        }
        return Math.min(Math.round((this.mHistoryDataHasDownloadSize * 100.0f) / f2), 99);
    }

    private void initHistoryDataTotalSize() {
        if (GreenDaoUtil.queryDataPullConfigInfo(RunTimeUtil.getInstance().getUserId()) != null) {
            this.mHistoryDataTotalSize = r0.getDataTotalCount();
            this.mHistoryDataHasDownloadSize = r0.getHasDownloadCount().get();
        }
    }

    private void getThirdVersionFromDevice() {
        printAndSave("getThirdVersionFromDevice");
        BLEManager.unregisterGetDeviceParaCallBack(this.mSystemConstituentCallback);
        BLEManager.registerGetDeviceParaCallBack(this.mSystemConstituentCallback);
        BLEManager.getFirmwareAndBt3Version();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncFitnessGoalSettingToDevice() {
        long userId = RunTimeUtil.getInstance().getUserId();
        if (!BLEManager.isConnected()) {
            printAndSave("准备将数据三环目标数据发送到设备端，但是此时用户设备没有连接，终止同步操作：userId = " + userId);
            return;
        }
        CalorieAndDistanceGoal calorieAndDistanceGoal = LocalDataManager.getCalorieAndDistanceGoal();
        if (calorieAndDistanceGoal == null) {
            calorieAndDistanceGoal = new CalorieAndDistanceGoal();
        }
        int iCaluteBMR = FitnessHelperKt.caluteBMR(userId);
        UserTargetNew userTargetNewQueryUserLastestTarget = GreenDaoUtil.queryUserLastestTarget(userId);
        if (userTargetNewQueryUserLastestTarget == null) {
            userTargetNewQueryUserLastestTarget = RunTimeUtil.generateDefaultUserTargetNew(userId);
        }
        float f2 = iCaluteBMR;
        calorieAndDistanceGoal.calorie_max = FitnessHelperKt.caluteCalorieMax(f2);
        calorieAndDistanceGoal.calorie_min = FitnessHelperKt.caluteCalorieMin(f2);
        calorieAndDistanceGoal.calorie = userTargetNewQueryUserLastestTarget.getCalories() > 0 ? userTargetNewQueryUserLastestTarget.getCalories() : 500;
        calorieAndDistanceGoal.walk_goal_time = userTargetNewQueryUserLastestTarget.getWalk() >= 3600 ? userTargetNewQueryUserLastestTarget.getWalk() / 3600 : 12;
        calorieAndDistanceGoal.distance = userTargetNewQueryUserLastestTarget.getDistance() > 0 ? userTargetNewQueryUserLastestTarget.getDistance() : 5000;
        calorieAndDistanceGoal.mid_high_time_goal = userTargetNewQueryUserLastestTarget.getActivityTime() > 0 ? userTargetNewQueryUserLastestTarget.getActivityTime() : 1800L;
        printAndSave("开始将数据三环目标设置数据发送到设备端(" + userId + ")，calorieAndDistanceGoal=" + calorieAndDistanceGoal);
        BLEManager.setCalorieAndDistanceGoal(calorieAndDistanceGoal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSystemConstituentInfo(String str) {
        printAndSave("deviceinfo requestSystemConstituentInfo:" + str);
        if (isSupportDeviceThirdVersion()) {
            DeviceManager.checkModuleSystemInfo(getDeviceInfo(), str, new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.home.HomeFragmentPresenter.20
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                    HomeFragmentPresenter.this.printAndSave("requestSystemConstituentInfo onSuccess: " + otaInfo);
                    if (!BLEManager.isConnected() || otaInfo == null) {
                        return;
                    }
                    HomeFragmentPresenter.hasNewDeviceVersion = true;
                    if (otaInfo.isForced()) {
                        HomeFragmentPresenter.this.printAndSave("系统组件强制升级");
                        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_CHECKED_NEW_SYSTEMCOMP, otaInfo));
                    } else if (SPHelper.getLastOtaReminderDate().equals(DateUtil.format(DateUtil.getTodayDate(), DateUtil.DATE_FORMAT_YMD))) {
                        HomeFragmentPresenter.this.printAndSave("requestSystemConstituentInfo 今天已经提醒过了，不重复提醒");
                    } else {
                        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_CHECKED_NEW_SYSTEMCOMP, otaInfo));
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str2) {
                    HomeFragmentPresenter.this.printAndSave("requestSystemConstituentInfo onFailed code=" + i + "\tmessage==" + i);
                }
            });
        }
    }

    @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
    public void onChanged(DeviceChangedPara deviceChangedPara) {
        printAndSave("收到设备端发送的配置或者数据变更通知changedPara=" + deviceChangedPara);
        if (deviceChangedPara != null) {
            int i = deviceChangedPara.dataType;
            if (i == 17) {
                printAndSave("设备端三环目标设置发生改变,开始主动去固件端获取三环目标");
                BLEManager.unregisterGetDeviceParaCallBack(this.mCallBack);
                BLEManager.registerGetDeviceParaCallBack(this.mCallBack);
                BLEManager.getSportThreeCircleGoal();
            } else {
                if (i != 26) {
                    if (i != 27) {
                        return;
                    }
                }
                mIsTelephone = false;
                printAndSave("蓝牙结束通话");
            }
            mIsTelephone = true;
            printAndSave("蓝牙正在通话");
            mIsTelephone = false;
            printAndSave("蓝牙结束通话");
        }
    }
}