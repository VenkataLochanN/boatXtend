package com.ido.life.module.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.DialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.NumUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.DeviceExceptionModel;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import com.ido.life.bean.ServerBean;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.ble.BaseConnCallback;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.ble.BaseDialOperateCallback;
import com.ido.life.ble.BaseUnbindCallback;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.data.api.entity.AppInfoEntity;
import com.ido.life.data.api.entity.DataSyncCountItem;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.UserTargetEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.DeviceWhiteListEntity;
import com.ido.life.database.model.HomeCard;
import com.ido.life.database.model.MapEngine;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.database.model.ServerMenstrual;
import com.ido.life.database.model.ServerMenstrualItem;
import com.ido.life.database.model.SportCard;
import com.ido.life.database.model.TempUnitSetting;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WeekStartSetting;
import com.ido.life.dialog.NotificationPermissionSettingDialog;
import com.ido.life.keeplive.JobHandlerService;
import com.ido.life.keeplive.LocalService;
import com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.main.MainPresenter;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DateUtil;
import com.ido.life.util.DialogHelper;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SPUtils;
import com.ido.life.util.TimeUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import com.veryfit.multi.nativeprotocol.b;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class MainPresenter extends BasePresenter<MainView> implements SettingCallBack.ICallBack, DeviceParaChangedCallBack.ICallBack {
    public static boolean dfuOtaCacheLogUploading;
    public static boolean flashCacheLogUploading;
    public static boolean languageCacheLogUploading;
    public static boolean otaCacheLogUploading;
    private BaseDeviceInfoCallback mDeviceInfoCallback;
    DialogFragment mNotificationPermissionDialog;
    private BaseUnbindCallback mUnbindCallback;
    public boolean needInitConfig;
    private static final String TAG = MainPresenter.class.getSimpleName();
    public static boolean needShowWrongBindDialog = true;
    public static final Map<String, Float> screenRatioMap = new HashMap();
    private AtomicInteger current_request_count = new AtomicInteger(1);
    private final Handler mHandler = new Handler();
    private final Map<Integer, String> mWhiteListMap = new HashMap();
    private final BaseDialOperateCallback mDialOperateCallback = new BaseDialOperateCallback() { // from class: com.ido.life.module.main.MainPresenter.1
        @Override // com.ido.life.ble.BaseDialOperateCallback, com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetScreenInfo(WatchPlateScreenInfo watchPlateScreenInfo) {
            super.onGetScreenInfo(watchPlateScreenInfo);
            if (watchPlateScreenInfo == null) {
                return;
            }
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo != null) {
                MainPresenter.screenRatioMap.put(currentDeviceInfo.mDeviceAddress, Float.valueOf((watchPlateScreenInfo.height * 1.0f) / watchPlateScreenInfo.width));
            }
            EventBusHelper.post(Constants.EventConstants.EVENT_GET_DEVICE_SCREEN_INFO);
        }
    };
    private final BaseConnCallback mConnCallback = new AnonymousClass2();

    /* JADX INFO: renamed from: com.ido.life.module.main.MainPresenter$2, reason: invalid class name */
    class AnonymousClass2 extends BaseConnCallback {
        AnonymousClass2() {
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onConnectStart ");
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onConnecting ");
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onRetry ：" + i);
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            Log.d("alexa test", "连接成功");
            CommonLogUtil.printAndSave("MainPresenter连接成功");
            final boolean zBooleanValue = ((Boolean) SPUtils.get(DeviceUpgradeNewPresenter.HAD_RESET_ALEXA, false)).booleanValue();
            if (!zBooleanValue) {
                MainPresenter.this.mHandler.postDelayed(new Runnable() { // from class: com.ido.life.module.main.-$$Lambda$MainPresenter$2$_q69CgTUnVTS1GRUppe9oZ_1PMc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onConnectSuccess$0$MainPresenter$2(zBooleanValue);
                    }
                }, 1200L);
            }
            MainPresenter.this.mHandler.postDelayed(new Runnable() { // from class: com.ido.life.module.main.-$$Lambda$MainPresenter$2$349Dr9rJXsim8F9q_BDtluH6uHs
                @Override // java.lang.Runnable
                public final void run() {
                    MainPresenter.AnonymousClass2.lambda$onConnectSuccess$1();
                }
            }, 30000L);
            DeviceConfigHelper.setUnitsFlowSystem(true);
            MainPresenter.this.getDeviceScreenInfo();
            EventBusHelper.post(101);
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onConnectSuccess ");
            MainPresenter.this.checkNotificationDialog();
        }

        public /* synthetic */ void lambda$onConnectSuccess$0$MainPresenter$2(boolean z) {
            Log.d("alexa test", z ? "onConnectSuccess-isHadResetAlexa=true" : "isHadResetAlexa=false");
            CommonLogUtil.printAndSave(z ? "onConnectSuccess-isHadResetAlexa=true" : "isHadResetAlexa=false");
            if (LocalDataManager.getBasicInfo() != null && LocalDataManager.getBasicInfo().firmwareVersion >= 10) {
                MainPresenter.this.resetAlexaInit(10);
            } else {
                MainPresenter.this.otaSuccessRestDeviceCallback();
                BLEManager.getBasicInfo();
            }
        }

        static /* synthetic */ void lambda$onConnectSuccess$1() {
            if (BLEManager.isConnected()) {
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.BLUETOOTH_CONNECT_SUCCESS, "", null);
            }
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.BLUETOOTH_CONNECT_FAILURE, connectFailedReason == null ? "" : connectFailedReason.toString(), null);
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onConnectFailed: " + str + ", connectFailedReason = " + connectFailedReason);
            if (connectFailedReason == ConnectFailedReason.ENCRYPTED_FAILED || connectFailedReason == ConnectFailedReason.NOT_IN_BIND_STATUS) {
                EventBusHelper.post(Constants.EventConstants.EVENT_TYPE_BLE_ENCRYPTED_FAILED);
                ConnectLogHelper.saveLog(MainPresenter.TAG, "蓝牙连接鉴权失败了，删除绑定");
                MainPresenter.this.deleteDevice();
            }
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            MainPresenter.this.mHandler.removeCallbacksAndMessages(null);
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.BLUETOOTH_DISCONNECT_AUTO, "", null);
            EventBusHelper.post(102);
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onConnectBreak ");
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onInDfuMode:" + bLEDevice.toString());
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onDeviceInNotBindStatus，删除绑定");
            EventBusHelper.post(Constants.EventConstants.EVENT_TYPE_BLE_ENCRYPTED_FAILED);
            MainPresenter.this.deleteDevice();
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
            ConnectLogHelper.saveLog(MainPresenter.TAG, "onInitCompleted ");
        }
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

    public void addBleConnectStatusListener() {
        BLEManager.registerConnectCallBack(this.mConnCallback);
    }

    public SwitchStatus getSwitchStatus() {
        return SPHelper.getSwitchStatus();
    }

    public void initConfigForFirstLogin() {
        if (isAttachView()) {
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(RunTimeUtil.getInstance().getUserId());
                if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
                    printLoginRegisterLog("开始获取数据下拉配置信息");
                    getDataPullConfigInfo();
                    return;
                }
                PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
                HomeCard homeCardQueryHomeCardInfo = GreenDaoUtil.queryHomeCardInfo(RunTimeUtil.getInstance().getUserId());
                SportCard sportCardQuerySportCard = GreenDaoUtil.querySportCard(RunTimeUtil.getInstance().getUserId());
                if (privateSafeSettingQueryPrivateSafeSetting == null || homeCardQueryHomeCardInfo == null || sportCardQuerySportCard == null) {
                    printLoginRegisterLog("开始获取用户首选项配置信息");
                    getPriveteSafeSetting();
                    return;
                }
                printLoginRegisterLog("准备首页数据下拉");
                if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                    if (dataPullConfigInfoQueryDataPullConfigInfo.getAutoDownload()) {
                        printLoginRegisterLog("开始自动执行历史数据下拉");
                        if (!NetworkUtil.isWifi(IdoApp.getAppContext()) && dataPullConfigInfoQueryDataPullConfigInfo.isShowTipDialog()) {
                            DataDownLoadService.loadSingleHomeData(RunTimeUtil.getInstance().getUserId());
                            getView().showHistoryDataOverLoadTip();
                        } else {
                            SPHelper.saveAutoShowHistoryDataPullState(true);
                            dataPullConfigInfoQueryDataPullConfigInfo.setAutoDownload(false);
                            dataPullConfigInfoQueryDataPullConfigInfo.update();
                            this.needInitConfig = false;
                            getView().initConfigSuccess();
                            DataDownLoadService.startSingleUserTask(RunTimeUtil.getInstance().getUserId());
                        }
                    } else {
                        printLoginRegisterLog("不自动执行历史数据下拉");
                        this.needInitConfig = false;
                        getView().initConfigSuccess();
                    }
                    checkWhenPageCreate();
                    return;
                }
                getView().initConfigFailed();
                printLoginRegisterLog("开始自动执行首页数据下拉，但是此时没有网络");
                return;
            }
            getView().initConfigFailed();
            printLoginRegisterLog("获取数据下拉配置信息，没有网络");
        }
    }

    private boolean initConfigSuccess() {
        if (!RunTimeUtil.getInstance().hasLogin()) {
            return false;
        }
        long userId = RunTimeUtil.getInstance().getUserId();
        return (GreenDaoUtil.queryUserInfo(userId) == null || GreenDaoUtil.queryUserLatestTarget(userId) == null || GreenDaoUtil.queryDataPullConfigInfo(userId) == null || GreenDaoUtil.queryPrivateSafeSetting(userId) == null) ? false : true;
    }

    public void startLoadHistoryData() {
        printLoginRegisterLog("开始下拉历史数据");
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(RunTimeUtil.getInstance().getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo != null) {
            dataPullConfigInfoQueryDataPullConfigInfo.setAutoDownload(false);
            dataPullConfigInfoQueryDataPullConfigInfo.setShowTipDialog(false);
            dataPullConfigInfoQueryDataPullConfigInfo.update();
        }
        SPHelper.saveAutoShowHistoryDataPullState(true);
        DataDownLoadService.loadSingleHistoryData(RunTimeUtil.getInstance().getUserId());
    }

    public void cancelLoadHistoryData() {
        SPHelper.saveAutoShowHistoryDataPullState(false);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(RunTimeUtil.getInstance().getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo != null) {
            dataPullConfigInfoQueryDataPullConfigInfo.setAutoDownload(false);
            dataPullConfigInfoQueryDataPullConfigInfo.setShowTipDialog(false);
            dataPullConfigInfoQueryDataPullConfigInfo.update();
        }
    }

    public void delayLoadHistoryData() {
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(RunTimeUtil.getInstance().getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo != null) {
            dataPullConfigInfoQueryDataPullConfigInfo.setAutoDownload(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoPreLoginPage(String str) {
        Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) PreLoginAndRegisterActivity.class);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra(str, true);
        }
        IdoApp.getAppContext().startActivity(intent);
        VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.module.main.MainPresenter.3
            @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
            public void clearSuccess() {
                EventBusHelper.post(305);
            }
        });
    }

    public boolean isBindNewDevice() {
        return SPHelper.isBindNewDevice();
    }

    public void initConfig() {
        if (!TextUtils.isEmpty(AccountRepository.getToken(IdoApp.getAppContext()))) {
            if (AccountRepository.getIsNewUser()) {
                printLoginRegisterLog("新注册用户进入主页。");
                AccountRepository.setIsNewUser(false);
                SPHelper.saveAutoShowHistoryDataPullState(false);
                GreenDaoUtil.addDataPullConfigInfo(DataPullConfigInfo.generateDefaultPullConfigInfo(RunTimeUtil.getInstance().getUserId()));
                DataPullConfigInfo dataPullConfigInfo = new DataPullConfigInfo();
                dataPullConfigInfo.setUserId(RunTimeUtil.getInstance().getUserId());
                dataPullConfigInfo.setAutoDownload(false);
                dataPullConfigInfo.setShowTipDialog(false);
                GreenDaoUtil.addDataPullConfigInfo(dataPullConfigInfo);
                EventBusHelper.post(Constants.EventConstants.EVENT_HOME_STOP_LOADING_ANIMATOR);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "MainActivity initData()，由于是新注册用户，所以不去获取个人信息");
                checkAndFillPreferenceConfig();
                checkWhenPageCreate();
            } else {
                printLoginRegisterLog("非新注册用户进入主页。");
            }
            getUserInfo();
            return;
        }
        if (TextUtils.isEmpty(AuthorizationPreference.getUUID(IdoApp.getAppContext()))) {
            AuthorizationPreference.setUuid(IdoApp.getAppContext(), AppUtil.getIMEI(IdoApp.getAppContext()));
        }
        checkWhenPageCreate();
        printLoginRegisterLog("用户以游客模式进入主页。");
        checkAndFillPreferenceConfig();
    }

    public void getUserInfo() {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
            if (userInfoQueryLatestUserInfo == null) {
                printLoginRegisterLog("进入到主页没有网络并且本地没有用户信息,即将退出到登录注册页面.");
                gotoPreLoginPage(Constants.LOGIN_GET_USERINFO_FAIL_THREE);
                return;
            }
            printLoginRegisterLog("进入到主页没有网络但是获取到了最新用户信息localUserInfo=" + userInfoQueryLatestUserInfo.toString());
            updateUserOpenAppCount(userInfoQueryLatestUserInfo.getUserId());
            RunTimeUtil.getInstance().setUserId(userInfoQueryLatestUserInfo.getUserId());
            GreenDaoUtil.switchDataToUser(userInfoQueryLatestUserInfo.getUserId());
            EventBusHelper.post(Constants.EventConstants.EVENT_GET_USER_INFO_SUCCESS);
            if (!initConfigSuccess()) {
                getView().initConfigFailed();
                printLoginRegisterLog("进入到主页没有网络但是获取到了最新用户信息localUserInfo,初始化配置没有成功");
                return;
            } else {
                checkWhenPageCreate();
                return;
            }
        }
        if (!initConfigSuccess() && isAttachView()) {
            this.needInitConfig = true;
            getView().startInitConfig();
        }
        AccountRepository.getInstance().getUserInfo(new AccountManager.OnUserCallback() { // from class: com.ido.life.module.main.MainPresenter.4
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onSuccess(UserInfo userInfo) {
                MainPresenter.this.current_request_count.set(1);
                if (userInfo == null) {
                    MainPresenter.this.printLoginRegisterLog("从服务器上面获取的用户信息为空");
                    UserInfo userInfoQueryLatestUserInfo2 = GreenDaoUtil.queryLatestUserInfo();
                    if (userInfoQueryLatestUserInfo2 == null) {
                        if (MainPresenter.this.isAttachView()) {
                            MainPresenter.this.printLoginRegisterLog("服务器和本地的用户信息都为空，跳转到填写个人资料页面");
                            ((MainView) MainPresenter.this.getView()).jump2UserDataActivity();
                            return;
                        }
                    } else {
                        MainPresenter.this.printLoginRegisterLog("服务器用户信息为空，并且本地用户信息不为空localUserInfo=" + userInfoQueryLatestUserInfo2.toString());
                        if (!TextUtils.isEmpty(userInfoQueryLatestUserInfo2.getAreaCode())) {
                            SPUtils.put(Constants.CHOOSE_COUNTRY_CODE, userInfoQueryLatestUserInfo2.getAreaCode());
                        }
                        userInfoQueryLatestUserInfo2.update();
                        RunTimeUtil.getInstance().setUserId(userInfoQueryLatestUserInfo2.getUserId());
                        EventBusHelper.post(Constants.EventConstants.EVENT_GET_USER_INFO_SUCCESS);
                        MainPresenter.this.getUserTarget(userInfoQueryLatestUserInfo2);
                    }
                } else {
                    RunTimeUtil.getInstance().setUserId(userInfo.getUserId());
                    UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(userInfo.getUserId());
                    MainPresenter.this.printLoginRegisterLog("进入主页后获取服务器的用户信息 userInfo " + userInfo.toString());
                    if (userInfo.getWeightUnit() == 0) {
                        MainPresenter.this.printLoginRegisterLog("从服务器上面获取到的用户信息无效.");
                        if (userInfoQueryUserInfo == null || userInfoQueryUserInfo.getWeightUnit() == 0 || userInfoQueryUserInfo.getWeight() == -1.0d) {
                            if (MainPresenter.this.isAttachView()) {
                                GreenDaoUtil.addUserInfo(userInfo);
                                MainPresenter.this.printLoginRegisterLog("服务器用户信息无效,同时本地的用户信息无效,就是插入服务器返回的用户信息，就跳到填写个人资料页面");
                                ((MainView) MainPresenter.this.getView()).jump2UserDataActivity();
                                return;
                            }
                        } else {
                            MainPresenter.this.printLoginRegisterLog("服务器上面的用户信息无效，本地用户信息有效,采用本地的用户信息");
                            userInfoQueryUserInfo.update();
                            MainPresenter.this.getUserTarget(userInfoQueryUserInfo);
                        }
                    } else {
                        String areaCode = null;
                        if (userInfoQueryUserInfo == null || userInfo.getUpdateTime() > userInfoQueryUserInfo.getUpdateTime()) {
                            MainPresenter.this.printLoginRegisterLog("本地用户信息为空或者服务器上面的用户信息比本地的用户信息新就采用服务器上面的用户信息");
                            if (!TextUtils.isEmpty(userInfo.getAreaCode())) {
                                areaCode = userInfo.getAreaCode();
                            } else if (userInfoQueryUserInfo != null && !TextUtils.isEmpty(userInfoQueryUserInfo.getAreaCode())) {
                                areaCode = userInfoQueryUserInfo.getAreaCode();
                            }
                            if (!TextUtils.isEmpty(areaCode)) {
                                SPUtils.put(Constants.CHOOSE_COUNTRY_CODE, areaCode);
                                userInfo.setAreaCode(areaCode);
                                MainPresenter.this.printLoginRegisterLog("筛选后国家码为AreaCode=" + areaCode);
                            }
                            MainPresenter.this.saveUnitAndUserInfo(userInfo);
                            MainPresenter.this.getUserTarget(userInfo);
                        } else {
                            MainPresenter.this.printLoginRegisterLog("优先使用本地用户信息");
                            if (!TextUtils.isEmpty(userInfoQueryUserInfo.getAreaCode())) {
                                areaCode = userInfoQueryUserInfo.getAreaCode();
                            } else if (!TextUtils.isEmpty(userInfo.getAreaCode()) && TextUtils.isEmpty((CharSequence) SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, ""))) {
                                areaCode = userInfo.getAreaCode();
                            }
                            if (!TextUtils.isEmpty(areaCode)) {
                                SPUtils.put(Constants.CHOOSE_COUNTRY_CODE, areaCode);
                                userInfoQueryUserInfo.setAreaCode(areaCode);
                                MainPresenter.this.printLoginRegisterLog("筛选后国家码为AreaCode=" + areaCode);
                            }
                            userInfoQueryUserInfo.update();
                            MainPresenter.this.getUserTarget(userInfoQueryUserInfo);
                        }
                    }
                    EventBusHelper.post(Constants.EventConstants.EVENT_GET_USER_INFO_SUCCESS);
                }
                MainPresenter.this.updateUserOpenAppCount(RunTimeUtil.getInstance().getUserId());
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onFailed(String str) {
                MainPresenter.this.printLoginRegisterLog("进入主页后获取服务器的用户信息失败信息： message=" + str + ",失败的次数：" + MainPresenter.this.current_request_count.get());
                if (MainPresenter.this.current_request_count.incrementAndGet() > 3) {
                    MainPresenter.this.current_request_count.set(1);
                    MainPresenter.this.printLoginRegisterLog("在主页请求个人信息3次都失败，将current_request_count恢复为：" + MainPresenter.this.current_request_count.get());
                    UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo((String) SPUtils.get(Constants.LOGIN_SUCCESS_ACCOUNT, ""));
                    if (userInfoQueryUserInfo == null) {
                        MainPresenter.this.printLoginRegisterLog("用户信息获取失败并且本地不存在对应的用户信息，跳向登录注册页面.");
                        MainPresenter.this.gotoPreLoginPage(Constants.LOGIN_GET_USERINFO_FAIL_THREE);
                        return;
                    }
                    if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                        MainPresenter.this.getUserTarget(userInfoQueryUserInfo);
                        MainPresenter.this.printLoginRegisterLog("获取个人信息失败，但本地UserInfo不为空，去获取用户目标");
                        return;
                    } else {
                        if (GreenDaoUtil.queryUserLatestTarget(userInfoQueryUserInfo.getUserId()) == null) {
                            MainPresenter.this.printLoginRegisterLog("用户信息获取失败，本地存在对应的用户信息，但是用户目标不存在，跳向填写用户目标页面");
                            if (MainPresenter.this.isAttachView()) {
                                ((MainView) MainPresenter.this.getView()).jump2UserTargetActivity();
                                return;
                            }
                            return;
                        }
                        MainPresenter.this.printLoginRegisterLog("用户信息获取失败，但是本地存在对应的用户信息和用户目标，开始初始化历史数据下拉配置项");
                        if (MainPresenter.this.isAttachView()) {
                            ((MainView) MainPresenter.this.getView()).initConfigFailed();
                            return;
                        }
                        return;
                    }
                }
                MainPresenter.this.printLoginRegisterLog("请求用户个人信息失败后第 " + MainPresenter.this.current_request_count.get() + " 次重新请求");
                MainPresenter.this.getUserInfo();
            }
        });
    }

    public void getPriveteSafeSetting() {
        AccountManager.getPriveteSafeSetting(new AccountManager.OnCommCallback<BaseEntityNew<List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem>>>() { // from class: com.ido.life.module.main.MainPresenter.5
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(BaseEntityNew<List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem>> baseEntityNew) {
                List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem> result;
                SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem savePrivateSafeSettingBeanItem;
                ServerBean.MensConfigReponse mensConfigReponse;
                if (baseEntityNew != null && (result = baseEntityNew.getResult()) != null && result.size() > 0) {
                    int size = result.size();
                    PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
                    PrivateSafeSetting privateSafeSettingGenerateDefaultPrivateSafeSetting = null;
                    if (privateSafeSettingQueryPrivateSafeSetting == null) {
                        privateSafeSettingGenerateDefaultPrivateSafeSetting = RunTimeUtil.getInstance().generateDefaultPrivateSafeSetting();
                        Calendar calendar = Calendar.getInstance(Locale.CHINA);
                        calendar.add(1, -100);
                        privateSafeSettingGenerateDefaultPrivateSafeSetting.setCreateTime(calendar.getTimeInMillis());
                        privateSafeSettingGenerateDefaultPrivateSafeSetting.setUpdateTime(calendar.getTimeInMillis());
                        privateSafeSettingGenerateDefaultPrivateSafeSetting.setUploadSuccess(true);
                    }
                    for (int i = 0; i < size; i++) {
                        savePrivateSafeSettingBeanItem = result.get(i);
                        switch (savePrivateSafeSettingBeanItem.getAttrName()) {
                            case "UPLOAD_USER_INFO":
                                if (privateSafeSettingQueryPrivateSafeSetting == null) {
                                    privateSafeSettingGenerateDefaultPrivateSafeSetting.setSavePrivateData(SavePrivateSafeSettingBean.ON.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()));
                                    break;
                                } else {
                                    privateSafeSettingQueryPrivateSafeSetting.setSavePrivateData(SavePrivateSafeSettingBean.ON.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()));
                                    break;
                                }
                                break;
                            case "UPLOAD_HEALTH_DATA":
                                if (privateSafeSettingQueryPrivateSafeSetting == null) {
                                    privateSafeSettingGenerateDefaultPrivateSafeSetting.setSaveHealthData(SavePrivateSafeSettingBean.ON.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()));
                                    break;
                                } else {
                                    privateSafeSettingQueryPrivateSafeSetting.setSaveHealthData(SavePrivateSafeSettingBean.ON.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()));
                                    break;
                                }
                                break;
                            case "UPLOAD_SPORT_DATA":
                                if (privateSafeSettingQueryPrivateSafeSetting == null) {
                                    privateSafeSettingGenerateDefaultPrivateSafeSetting.setSaveSportData(SavePrivateSafeSettingBean.ON.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()));
                                    break;
                                } else {
                                    privateSafeSettingQueryPrivateSafeSetting.setSaveSportData(SavePrivateSafeSettingBean.ON.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()));
                                    break;
                                }
                                break;
                            case "HOUR_TIME_UNIT":
                                TimeSet timeSetQueryTimeSet = GreenDaoUtil.queryTimeSet(RunTimeUtil.getInstance().getUserId());
                                if (timeSetQueryTimeSet == null) {
                                    TimeSet timeSetGenerateDefaultTimeSet = RunTimeUtil.generateDefaultTimeSet(RunTimeUtil.getInstance().getUserId());
                                    timeSetGenerateDefaultTimeSet.setHasUpload(true);
                                    if (TextUtils.equals(TimeSet.KEY_HOUR_12, savePrivateSafeSettingBeanItem.getAttrValue())) {
                                        timeSetGenerateDefaultTimeSet.setTimeFormat(2);
                                    } else if (TextUtils.equals(TimeSet.KEY_HOUR_24, savePrivateSafeSettingBeanItem.getAttrValue())) {
                                        timeSetGenerateDefaultTimeSet.setTimeFormat(1);
                                    } else {
                                        timeSetGenerateDefaultTimeSet.setTimeFormat(0);
                                    }
                                    GreenDaoUtil.addTimeSet(timeSetGenerateDefaultTimeSet);
                                    break;
                                } else {
                                    timeSetQueryTimeSet.setHasSyncDeviceSuccess(false);
                                    timeSetQueryTimeSet.setHasUpload(true);
                                    timeSetQueryTimeSet.setTimeFormat(SavePrivateSafeSettingBean.ON.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()) ? 1 : 2);
                                    timeSetQueryTimeSet.update();
                                    break;
                                }
                                break;
                            case "SYSTEM_UNIT":
                                UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(RunTimeUtil.getInstance().getUserId());
                                if (unitSettingQueryUnitSetting == null) {
                                    UnitSetting unitSettingGenerateDefaultUnitSetting = RunTimeUtil.generateDefaultUnitSetting(RunTimeUtil.getInstance().getUserId());
                                    unitSettingGenerateDefaultUnitSetting.setHasUpload(true);
                                    unitSettingGenerateDefaultUnitSetting.setUnit(UnitSetting.BRITISH_SYSTEM_UNIT.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()) ? 2 : 1);
                                    GreenDaoUtil.addUnitSetting(unitSettingGenerateDefaultUnitSetting);
                                    break;
                                } else {
                                    unitSettingQueryUnitSetting.setHasUpload(true);
                                    unitSettingQueryUnitSetting.setUnit(UnitSetting.BRITISH_SYSTEM_UNIT.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()) ? 2 : 1);
                                    unitSettingQueryUnitSetting.setHasSyncDeviceSuccess(false);
                                    unitSettingQueryUnitSetting.update();
                                    break;
                                }
                                break;
                            case "TEMPERATURE":
                                TempUnitSetting tempUnitSettingQueryTempUnitSetting = GreenDaoUtil.queryTempUnitSetting(RunTimeUtil.getInstance().getUserId());
                                if (tempUnitSettingQueryTempUnitSetting == null) {
                                    TempUnitSetting tempUnitSettingGenerateDefaultTempUnitSetting = RunTimeUtil.generateDefaultTempUnitSetting(RunTimeUtil.getInstance().getUserId());
                                    tempUnitSettingGenerateDefaultTempUnitSetting.setTemp(TempUnitSetting.KEY_C.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()) ? 1 : 2);
                                    GreenDaoUtil.saveTempUnitSetting(tempUnitSettingGenerateDefaultTempUnitSetting);
                                    break;
                                } else {
                                    tempUnitSettingQueryTempUnitSetting.setTemp(TempUnitSetting.KEY_C.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()) ? 1 : 2);
                                    tempUnitSettingQueryTempUnitSetting.update();
                                    break;
                                }
                                break;
                            case "WEEK_START":
                                WeekStartSetting weekStartSettingQueryWeekStart = GreenDaoUtil.queryWeekStart(RunTimeUtil.getInstance().getUserId());
                                if (weekStartSettingQueryWeekStart == null) {
                                    WeekStartSetting weekStartSettingGenerateDefaultWeekSetting = RunTimeUtil.generateDefaultWeekSetting(RunTimeUtil.getInstance().getUserId());
                                    weekStartSettingGenerateDefaultWeekSetting.setHasUpload(true);
                                    if (WeekStartSetting.MONDAY.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue())) {
                                        weekStartSettingGenerateDefaultWeekSetting.setWeekStart(2);
                                    } else if (WeekStartSetting.SATURDAY.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue())) {
                                        weekStartSettingGenerateDefaultWeekSetting.setWeekStart(7);
                                    } else {
                                        weekStartSettingGenerateDefaultWeekSetting.setWeekStart(1);
                                    }
                                    GreenDaoUtil.addWeekStart(weekStartSettingGenerateDefaultWeekSetting);
                                    break;
                                } else {
                                    weekStartSettingQueryWeekStart.setHasUpload(true);
                                    if (WeekStartSetting.MONDAY.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue())) {
                                        weekStartSettingQueryWeekStart.setWeekStart(2);
                                    } else if (WeekStartSetting.SATURDAY.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue())) {
                                        weekStartSettingQueryWeekStart.setWeekStart(7);
                                    } else {
                                        weekStartSettingQueryWeekStart.setWeekStart(1);
                                    }
                                    weekStartSettingQueryWeekStart.setHasSyncDeviceSuccess(false);
                                    weekStartSettingQueryWeekStart.update();
                                    break;
                                }
                                break;
                            case "MAP_ENGINE":
                                MapEngine mapEngineQueryMapEngine = GreenDaoUtil.queryMapEngine(RunTimeUtil.getInstance().getUserId());
                                if (mapEngineQueryMapEngine == null) {
                                    MapEngine mapEngineGenerateDefaultMapEngine = RunTimeUtil.generateDefaultMapEngine(RunTimeUtil.getInstance().getUserId(), RunTimeUtil.getDefaultMapEngine());
                                    mapEngineGenerateDefaultMapEngine.setHasUpload(true);
                                    if (MapEngine.TYPE_GOOGLE_MAP.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()) && RunTimeUtil.supportGoogleService(IdoApp.getAppContext())) {
                                        mapEngineGenerateDefaultMapEngine.setMapEngine(1);
                                    } else {
                                        mapEngineGenerateDefaultMapEngine.setMapEngine(2);
                                    }
                                    GreenDaoUtil.addMapEngine(mapEngineGenerateDefaultMapEngine);
                                    break;
                                } else {
                                    mapEngineQueryMapEngine.setHasUpload(true);
                                    if (MapEngine.TYPE_GOOGLE_MAP.contentEquals(savePrivateSafeSettingBeanItem.getAttrValue()) && RunTimeUtil.supportGoogleService(IdoApp.getAppContext())) {
                                        mapEngineQueryMapEngine.setMapEngine(1);
                                    } else {
                                        mapEngineQueryMapEngine.setMapEngine(2);
                                    }
                                    mapEngineQueryMapEngine.update();
                                    break;
                                }
                                break;
                            case "INDEX_CARD_POSITION":
                                HomeCard homeCardQueryHomeCardInfo = GreenDaoUtil.queryHomeCardInfo(RunTimeUtil.getInstance().getUserId());
                                List<Integer> listResolveHomeCardFromServer = MainPresenter.this.resolveHomeCardFromServer(savePrivateSafeSettingBeanItem.getAttrValue());
                                if (listResolveHomeCardFromServer.size() == 0) {
                                    listResolveHomeCardFromServer = HomeCard.getDefaultHomeShowCardValueList();
                                }
                                if (homeCardQueryHomeCardInfo == null) {
                                    HomeCard homeCard = new HomeCard();
                                    homeCard.setUploadSuccess(true);
                                    homeCard.setUserId(Long.valueOf(RunTimeUtil.getInstance().getUserId()));
                                    homeCard.setValueList(listResolveHomeCardFromServer);
                                    GreenDaoUtil.addHomeCardInfo(homeCard);
                                    break;
                                } else {
                                    homeCardQueryHomeCardInfo.setUploadSuccess(true);
                                    homeCardQueryHomeCardInfo.setValueList(listResolveHomeCardFromServer);
                                    homeCardQueryHomeCardInfo.update();
                                    break;
                                }
                                break;
                            case "INDEX_CARD_HIDDEN_POSITION":
                                HomeCard homeCardQueryHomeCardInfo2 = GreenDaoUtil.queryHomeCardInfo(RunTimeUtil.getInstance().getUserId());
                                List<Integer> listResolveHomeCardFromServer2 = MainPresenter.this.resolveHomeCardFromServer(savePrivateSafeSettingBeanItem.getAttrValue());
                                if (homeCardQueryHomeCardInfo2 == null) {
                                    HomeCard homeCard2 = new HomeCard();
                                    homeCard2.setUploadSuccess(true);
                                    homeCard2.setUserId(Long.valueOf(RunTimeUtil.getInstance().getUserId()));
                                    homeCard2.setHideValueList(listResolveHomeCardFromServer2);
                                    GreenDaoUtil.addHomeCardInfo(homeCard2);
                                    break;
                                } else {
                                    homeCardQueryHomeCardInfo2.setUploadSuccess(true);
                                    homeCardQueryHomeCardInfo2.setHideValueList(listResolveHomeCardFromServer2);
                                    try {
                                        homeCardQueryHomeCardInfo2.update();
                                    } catch (Exception unused) {
                                        GreenDaoUtil.addHomeCardInfo(homeCardQueryHomeCardInfo2);
                                    }
                                    break;
                                }
                                break;
                            case "SPORT_TYPE_POSITION":
                                SportCard sportCardQuerySportCard = GreenDaoUtil.querySportCard(RunTimeUtil.getInstance().getUserId());
                                if (sportCardQuerySportCard == null) {
                                    SportCard sportCard = new SportCard();
                                    sportCard.setUploadSuccess(true);
                                    sportCard.setUserId(RunTimeUtil.getInstance().getUserId());
                                    sportCard.setValueList(MainPresenter.this.resolveSportCardFromServer(savePrivateSafeSettingBeanItem.getAttrValue()));
                                    GreenDaoUtil.addSportCard(sportCard);
                                    break;
                                } else {
                                    sportCardQuerySportCard.setUploadSuccess(true);
                                    sportCardQuerySportCard.setValueList(MainPresenter.this.resolveSportCardFromServer(savePrivateSafeSettingBeanItem.getAttrValue()));
                                    sportCardQuerySportCard.update();
                                    break;
                                }
                                break;
                            case "MENCES_CONFIG":
                                if (!TextUtils.isEmpty(savePrivateSafeSettingBeanItem.getAttrValue()) && (mensConfigReponse = (ServerBean.MensConfigReponse) GsonUtil.fromJson(savePrivateSafeSettingBeanItem.getAttrValue(), ServerBean.MensConfigReponse.class)) != null) {
                                    MenstruationConfig menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(RunTimeUtil.getInstance().getUserId());
                                    if (menstruationConfigQueryMenstruationConfig == null) {
                                        menstruationConfigQueryMenstruationConfig = new MenstruationConfig();
                                        menstruationConfigQueryMenstruationConfig.setUserId(RunTimeUtil.getInstance().getUserId());
                                    }
                                    menstruationConfigQueryMenstruationConfig.setMensLength(mensConfigReponse.getMENSLENGTH());
                                    menstruationConfigQueryMenstruationConfig.setMensCycle(mensConfigReponse.getMENSCYCLE());
                                    menstruationConfigQueryMenstruationConfig.setUpdateTimeStamp(System.currentTimeMillis());
                                    menstruationConfigQueryMenstruationConfig.setUploadSuccess(true);
                                    GreenDaoUtil.addMenstruationConfig(menstruationConfigQueryMenstruationConfig);
                                    break;
                                } else {
                                    break;
                                }
                                break;
                        }
                    }
                    if (privateSafeSettingQueryPrivateSafeSetting != null) {
                        privateSafeSettingQueryPrivateSafeSetting.update();
                    } else {
                        GreenDaoUtil.addPrivateSafeSetting(privateSafeSettingGenerateDefaultPrivateSafeSetting);
                    }
                }
                MainPresenter.this.checkAndFillPreferenceConfig();
                MainPresenter.this.initConfigForFirstLogin();
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str) {
                if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                    MainPresenter.this.checkAndFillPreferenceConfig();
                    MainPresenter.this.initConfigForFirstLogin();
                    return;
                }
                ((MainView) MainPresenter.this.getView()).initConfigFailed();
                MainPresenter.this.printLoginRegisterLog("获取数据下拉配置信息，服务器返回失败==" + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<java.lang.Integer> resolveHomeCardFromServer(java.lang.String r18) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.main.MainPresenter.resolveHomeCardFromServer(java.lang.String):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public List<Integer> resolveSportCardFromServer(String str) {
        List list;
        ArrayList arrayList = new ArrayList();
        try {
            list = (List) new Gson().fromJson(str, new TypeToken<List<String>>() { // from class: com.ido.life.module.main.MainPresenter.7
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
            list = null;
        }
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String str2 = (String) list.get(i);
                if (!TextUtils.isEmpty(str2)) {
                    String upperCase = str2.trim().toUpperCase(Locale.CHINA);
                    byte b2 = -1;
                    switch (upperCase.hashCode()) {
                        case 734341000:
                            if (upperCase.equals(SportCard.OUTDOOR_RUN)) {
                                b2 = 0;
                            }
                            break;
                        case 949924917:
                            if (upperCase.equals(SportCard.INDOOR_WALK)) {
                                b2 = 5;
                            }
                            break;
                        case 1277564543:
                            if (upperCase.equals(SportCard.INDOOR_RUN)) {
                                b2 = 1;
                            }
                            break;
                        case 1289864268:
                            if (upperCase.equals(SportCard.OUTDOOR_WALK)) {
                                b2 = 2;
                            }
                            break;
                        case 1313322659:
                            if (upperCase.equals(SportCard.OUTDOOR_CYCLE)) {
                                b2 = 4;
                            }
                            break;
                        case 2131022872:
                            if (upperCase.equals(SportCard.HIKING)) {
                                b2 = 3;
                            }
                            break;
                    }
                    if (b2 == 0) {
                        arrayList.add(48);
                    } else if (b2 == 1) {
                        arrayList.add(49);
                    } else if (b2 == 2) {
                        arrayList.add(52);
                    } else if (b2 == 3) {
                        arrayList.add(4);
                    } else if (b2 == 4) {
                        arrayList.add(50);
                    } else if (b2 == 5) {
                        arrayList.add(53);
                    }
                }
            }
        }
        return arrayList;
    }

    public void getDataPullConfigInfo() {
        HealthManager.getDataSyncCount(new HealthManager.DataSyncCountCallback() { // from class: com.ido.life.module.main.MainPresenter.8
            @Override // com.ido.life.data.health.remote.HealthManager.DataSyncCountCallback
            public void onSuccess(List<DataSyncCountItem> list) {
                int i;
                if (list == null || list.size() == 0) {
                    if (MainPresenter.this.isAttachView()) {
                        ((MainView) MainPresenter.this.getView()).initConfigFailed();
                        MainPresenter.this.printLoginRegisterLog("获取数据下拉配置信息，获取健康数据不同类型总条数为空");
                        return;
                    }
                    return;
                }
                DataPullConfigInfo dataPullConfigInfo = new DataPullConfigInfo();
                dataPullConfigInfo.setUserId(RunTimeUtil.getInstance().getUserId());
                dataPullConfigInfo.setAutoDownload(true);
                dataPullConfigInfo.setShowTipDialog(true);
                int size = list.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    DataSyncCountItem dataSyncCountItem = list.get(i3);
                    if (dataSyncCountItem != null) {
                        String str = dataSyncCountItem.healthType;
                        if (!TextUtils.isEmpty(str)) {
                            switch (str.trim().toUpperCase()) {
                                case "BLOOD_OXY":
                                    dataPullConfigInfo.setBloodOxyCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setBloodStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setBloodEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "CALORIE":
                                    dataPullConfigInfo.setCalorieCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setCalorieStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setCalorieEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "DISTANCE":
                                    dataPullConfigInfo.setDistanceCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setDistanceStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setDistanceEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "EXERCISE":
                                    dataPullConfigInfo.setExerciseCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setExerciseStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setExerciseEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "MENSES":
                                    dataPullConfigInfo.setMensesCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setMensesStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setMensesEndTime(dataSyncCountItem.endTime);
                                    continue;
                                    break;
                                case "HEART_RATE":
                                    dataPullConfigInfo.setHeartRateCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setHeartRateStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setHeartRateEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "PRESSURE":
                                    dataPullConfigInfo.setPressureCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setPressureStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setPressureEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "SLEEP":
                                    dataPullConfigInfo.setSleepCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setSleepStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setSleepEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "SPORTS":
                                    dataPullConfigInfo.setSportCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setSportStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setSportEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "STEPS":
                                    dataPullConfigInfo.setStepCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setStepStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setStepEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "WALK":
                                    dataPullConfigInfo.setWalkCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setWalkStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setWalkEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "WEIGHT":
                                    dataPullConfigInfo.setWeightCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setWeightStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setWeightEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                                case "NOISE":
                                    dataPullConfigInfo.setNoiseCount(dataSyncCountItem.count);
                                    dataPullConfigInfo.setNoiseStartTime(dataSyncCountItem.startTime);
                                    dataPullConfigInfo.setNoiseEndTime(dataSyncCountItem.endTime);
                                    i = dataSyncCountItem.count;
                                    break;
                            }
                            i2 += i;
                        }
                    }
                }
                dataPullConfigInfo.setDataTotalCount(i2);
                if (i2 > 0) {
                    GreenDaoUtil.addDataPullConfigInfo(dataPullConfigInfo);
                    MainPresenter.this.initConfigForFirstLogin();
                    return;
                }
                MainPresenter mainPresenter = MainPresenter.this;
                mainPresenter.needInitConfig = false;
                ((MainView) mainPresenter.getView()).initConfigSuccess();
                SPHelper.saveAutoShowHistoryDataPullState(false);
                dataPullConfigInfo.setAutoDownload(false);
                dataPullConfigInfo.setShowTipDialog(false);
                GreenDaoUtil.addDataPullConfigInfo(dataPullConfigInfo);
                GreenDaoUtil.addDataPullConfigInfo(DataPullConfigInfo.generateDefaultPullConfigInfo(RunTimeUtil.getInstance().getUserId()));
                EventBusHelper.post(Constants.EventConstants.EVENT_HOME_STOP_LOADING_ANIMATOR);
                MainPresenter.this.initConfigForFirstLogin();
            }

            @Override // com.ido.life.data.health.remote.HealthManager.DataSyncCountCallback
            public void onFailed(String str) {
                if (MainPresenter.this.isAttachView()) {
                    ((MainView) MainPresenter.this.getView()).initConfigFailed();
                    MainPresenter.this.printLoginRegisterLog("获取数据下拉配置信息失败message==" + str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndFillPreferenceConfig() {
        long userId = RunTimeUtil.getInstance().getUserId();
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(userId);
        if (privateSafeSettingQueryPrivateSafeSetting == null) {
            PrivateSafeSetting privateSafeSettingGenerateDefaultPrivateSafeSetting = RunTimeUtil.getInstance().generateDefaultPrivateSafeSetting();
            if (SPUtils.contains(IdoApp.getAppContext(), Constants.AGREEN_CLOUD_SYNC)) {
                boolean zBooleanValue = ((Boolean) SPUtils.get(Constants.AGREEN_CLOUD_SYNC, false)).booleanValue();
                privateSafeSettingGenerateDefaultPrivateSafeSetting.setSavePrivateData(zBooleanValue);
                privateSafeSettingGenerateDefaultPrivateSafeSetting.setSaveSportData(zBooleanValue);
                privateSafeSettingGenerateDefaultPrivateSafeSetting.setSaveHealthData(zBooleanValue);
            }
            GreenDaoUtil.addPrivateSafeSetting(privateSafeSettingGenerateDefaultPrivateSafeSetting);
        } else if (SPUtils.contains(IdoApp.getAppContext(), Constants.AGREEN_CLOUD_SYNC)) {
            boolean zBooleanValue2 = ((Boolean) SPUtils.get(Constants.AGREEN_CLOUD_SYNC, false)).booleanValue();
            privateSafeSettingQueryPrivateSafeSetting.setSavePrivateData(zBooleanValue2);
            privateSafeSettingQueryPrivateSafeSetting.setSaveSportData(zBooleanValue2);
            privateSafeSettingQueryPrivateSafeSetting.setSaveHealthData(zBooleanValue2);
            privateSafeSettingQueryPrivateSafeSetting.update();
        }
        if (GreenDaoUtil.queryHomeCardInfo(userId) == null) {
            HomeCard homeCard = new HomeCard();
            homeCard.setUserId(Long.valueOf(userId));
            homeCard.setUploadSuccess(false);
            homeCard.setValueList(HomeCard.getDefaultHomeShowCardValueList());
            homeCard.setHideValueList(HomeCard.getDefaultHomeHideCardValueList());
            GreenDaoUtil.addHomeCardInfo(homeCard);
        }
        HomeHelperKt.adjustHomeCard(RunTimeUtil.getInstance().getUserId());
        if (GreenDaoUtil.querySportCard(userId) == null) {
            SportCard sportCard = new SportCard();
            sportCard.setUploadSuccess(false);
            sportCard.setValueList(SportCard.getDefaultSportCard());
            sportCard.setUserId(userId);
            GreenDaoUtil.addSportCard(sportCard);
        }
        if (GreenDaoUtil.queryTimeSet(userId) == null) {
            GreenDaoUtil.addTimeSet(RunTimeUtil.generateDefaultTimeSet(userId));
        }
        if (GreenDaoUtil.queryUnitSetting(userId) == null) {
            GreenDaoUtil.addUnitSetting(RunTimeUtil.generateDefaultUnitSetting(userId));
        }
        if (GreenDaoUtil.queryWeekStart(userId) == null) {
            GreenDaoUtil.addWeekStart(RunTimeUtil.generateDefaultWeekSetting(userId));
        }
        if (GreenDaoUtil.queryMapEngine(userId) == null) {
            GreenDaoUtil.addMapEngine(RunTimeUtil.generateDefaultMapEngine(userId, RunTimeUtil.getDefaultMapEngine()));
        }
        if (GreenDaoUtil.queryTempUnitSetting(userId) == null) {
            GreenDaoUtil.saveTempUnitSetting(RunTimeUtil.generateDefaultTempUnitSetting(userId));
        }
        EventBusHelper.post(Constants.EventConstants.EVENT_PRIVATE_CONFIG_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveUnitAndUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        int i = 2;
        if (userInfo.getWeightUnit() != 2 && userInfo.getHeightUnit() != 2) {
            i = 1;
        }
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(userInfo.getUserId());
        if (unitSettingQueryUnitSetting != null) {
            unitSettingQueryUnitSetting.setUnit(i);
            unitSettingQueryUnitSetting.update();
        } else {
            UnitSetting unitSetting = new UnitSetting();
            unitSetting.setUserId(userInfo.getUserId());
            unitSetting.setUnit(i);
            GreenDaoUtil.addUnitSetting(unitSetting);
        }
        userInfo.setWeightUnit(1);
        userInfo.setHeightUnit(1);
        userInfo.setUpdateTime(System.currentTimeMillis());
        AccountRepository.getInstance().saveUserInfo(userInfo);
    }

    private void saveMenstrualData(ServerMenstrual serverMenstrual) {
        ServerMenstrualItem serverMenstrualItem;
        if (serverMenstrual == null || TextUtils.isEmpty(serverMenstrual.getItems())) {
            return;
        }
        ServerMenstrual menstrualCycleData = SPHelper.getMenstrualCycleData();
        if ((menstrualCycleData == null || menstrualCycleData.getTimestamp() < serverMenstrual.getTimestamp()) && (serverMenstrualItem = (ServerMenstrualItem) GsonUtil.fromJson(serverMenstrual.getItems(), ServerMenstrualItem.class)) != null) {
            serverMenstrual.setUploaded(true);
            SPHelper.saveMenstrualCycleData(serverMenstrual);
            Menstrual menstrual = LocalDataManager.getMenstrual();
            if (menstrual == null) {
                menstrual = new Menstrual();
            }
            menstrual.last_menstrual_year = serverMenstrualItem.getLastMenstrualYear();
            menstrual.last_menstrual_month = serverMenstrualItem.getLastMenstrualMonth();
            menstrual.last_menstrual_day = serverMenstrualItem.getLastMenstrualDay();
            menstrual.menstrual_cycle = serverMenstrualItem.getMenstrualCycle();
            menstrual.menstrual_length = serverMenstrualItem.getMenstrualLength();
            SPHelper.saveMenstrualData(menstrual);
            MenstrualRemind menstrualRemind = LocalDataManager.getMenstrualRemind();
            if (menstrualRemind == null) {
                menstrualRemind = new MenstrualRemind();
            }
            menstrualRemind.start_day = serverMenstrualItem.getMenstrualRemindBeforeDays();
            menstrualRemind.ovulation_day = serverMenstrualItem.getOvulationRemindBeforeDays();
            menstrualRemind.hour = serverMenstrualItem.getHour();
            menstrualRemind.minute = serverMenstrualItem.getMinute();
            EventBusHelper.post(Constants.EventConstants.EVENT_MENSTRUAL_PULL_SUCCESS);
            sendMenstrual2Device(menstrual, menstrualRemind);
        }
    }

    private void sendMenstrual2Device(final Menstrual menstrual, MenstrualRemind menstrualRemind) {
        if (BLEManager.isConnected()) {
            BLEManager.setMenstrualRemind(menstrualRemind);
            new Handler().postDelayed(new Runnable() { // from class: com.ido.life.module.main.-$$Lambda$MainPresenter$vy_ZUyn4KcS4zlVxgOs32P2kDFc
                @Override // java.lang.Runnable
                public final void run() {
                    BLEManager.setMenstrual(menstrual);
                }
            }, 500L);
        } else {
            BLEManager.setMenstrualPending(menstrual);
            BLEManager.setMenstrualRemindPending(menstrualRemind);
            SPHelper.setConfigSynced(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserTarget(final UserInfo userInfo) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            if (GreenDaoUtil.queryUserLatestTarget(userInfo.getUserId()) != null) {
                printLoginRegisterLog("无网络但本地目标已经设置");
                if (initConfigSuccess()) {
                    return;
                }
                getView().initConfigFailed();
                printLoginRegisterLog("无网络但本地目标已经设置,初始化配置没有成功");
                return;
            }
            if (isAttachView()) {
                printLoginRegisterLog("无网络且本地目标没设置，jump2UserTargetActivity");
                getView().jump2UserTargetActivity();
                return;
            }
            return;
        }
        HealthRepository.getInstance().getUserTarget(new HealthManager.OnUserTargetCallback() { // from class: com.ido.life.module.main.MainPresenter.9
            @Override // com.ido.life.data.health.remote.HealthManager.OnUserTargetCallback
            public void onSuccess(UserTargetEntity userTargetEntity) {
                if (userTargetEntity != null && userTargetEntity.getResult() != null) {
                    MainPresenter.this.printLoginRegisterLog("获取用户目标成功：userTarget=" + userTargetEntity.getResult().toString());
                    int weightInt = userTargetEntity.getResult().getWeightInt();
                    long userId = RunTimeUtil.getInstance().getUserId();
                    MainPresenter.this.printLoginRegisterLog("getUserTarget(),运行时userId：" + userId);
                    String str = DateUtil.format(Calendar.getInstance(Locale.CHINA), DateUtil.DATE_FORMAT_YMD);
                    UserTargetNew userTargetNewQueryUserTarget = GreenDaoUtil.queryUserTarget(userId, str);
                    if (userTargetNewQueryUserTarget == null) {
                        MainPresenter.this.printLoginRegisterLog("getUserTarget(),target==null");
                        UserTargetNew userTargetNew = new UserTargetNew();
                        userTargetNew.setCreateTime(userTargetEntity.getResult().getCreateTime());
                        userTargetNew.setUpdateTime(userTargetEntity.getResult().getUpdateTime());
                        userTargetNew.setStep(userTargetEntity.getResult().getStep());
                        userTargetNew.setWeight(weightInt);
                        userTargetNew.setDate(str);
                        userTargetNew.setWeightUnit(1);
                        userTargetNew.setHasUpload(true);
                        userTargetNew.setHasSyncToDevice(false);
                        userTargetNew.setUserId(userId);
                        GreenDaoUtil.addUserTarget(userTargetNew);
                    } else {
                        MainPresenter.this.printLoginRegisterLog("getUserTarget(),target不为null，userInfo：" + userInfo.toString());
                        if (userTargetNewQueryUserTarget.getUpdateTime() > userTargetEntity.getResult().getUpdateTime()) {
                            userTargetNewQueryUserTarget.setCreateTime(userTargetEntity.getResult().getCreateTime());
                            userTargetNewQueryUserTarget.setUpdateTime(userTargetEntity.getResult().getUpdateTime());
                            userTargetNewQueryUserTarget.setStep(userTargetEntity.getResult().getStep());
                            userTargetNewQueryUserTarget.setWeight(weightInt);
                            userTargetNewQueryUserTarget.setWeightUnit(1);
                            userTargetNewQueryUserTarget.setHasUpload(true);
                            userTargetNewQueryUserTarget.setHasSyncToDevice(false);
                            userTargetNewQueryUserTarget.update();
                            MainPresenter.this.printLoginRegisterLog("服务器的目标比本地的目标数据更新，保存服务器用户目标到本地数据库成功：" + userTargetNewQueryUserTarget.toString());
                        } else {
                            MainPresenter.this.printLoginRegisterLog("服务器的目标不比本地的目标数据新，不保存服务器用户目标到本地数据库了，本地用户目标为：" + userTargetNewQueryUserTarget.toString());
                        }
                    }
                    MainPresenter.this.initConfigForFirstLogin();
                    return;
                }
                if (GreenDaoUtil.queryUserLatestTarget(userInfo.getUserId()) == null) {
                    if (MainPresenter.this.isAttachView()) {
                        MainPresenter.this.printLoginRegisterLog("isAttachView()，jump2UserTargetActivity");
                        ((MainView) MainPresenter.this.getView()).jump2UserTargetActivity();
                        return;
                    }
                    return;
                }
                MainPresenter.this.initConfigForFirstLogin();
            }

            @Override // com.ido.life.data.health.remote.HealthManager.OnUserTargetCallback
            public void onFailed(String str) {
                MainPresenter.this.printLoginRegisterLog("获取用户目标失败：message=" + str);
                if (GreenDaoUtil.queryUserLatestTarget(userInfo.getUserId()) == null) {
                    if (MainPresenter.this.isAttachView()) {
                        MainPresenter.this.printLoginRegisterLog("isAttachView()，jump2UserDataActivityUserFailed 2");
                        ((MainView) MainPresenter.this.getView()).jump2UserDataActivityUserFailed(false);
                        return;
                    }
                    return;
                }
                MainPresenter.this.initConfigForFirstLogin();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUserOpenAppCount(long j) {
        String str = (String) SPUtils.get(j + "OpenApp", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        saveDeviceLog("uploadUserActiveData" + str + TimeUtil.getCurrentDateBefore(0));
        if (str.equals(TimeUtil.getCurrentDateBefore(0))) {
            return;
        }
        saveDeviceLog("uploadUserActiveData" + j);
        SPUtils.put(j + "OpenApp", TimeUtil.getCurrentDateBefore(0));
        AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.USER_ACTIVE_DATA, "", null);
    }

    public void addDeviceInfoCallback() {
        if (this.mDeviceInfoCallback == null) {
            this.mDeviceInfoCallback = getDeviceInfoCallback();
        }
        BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        BLEManager.registerWatchOperateCallBack(this.mDialOperateCallback);
        getDeviceScreenInfo();
    }

    public void otaSuccessRestDeviceCallback() {
        if (this.mDeviceInfoCallback == null) {
            this.mDeviceInfoCallback = getDeviceInfoCallback();
        }
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceScreenInfo() {
        if (BLEManager.isBind() && BLEManager.isConnected()) {
            BLEManager.getWatchPlateScreenInfo();
        }
    }

    private BaseDeviceInfoCallback getDeviceInfoCallback() {
        return new BaseDeviceInfoCallback() { // from class: com.ido.life.module.main.MainPresenter.10
            @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
            public void onGetBasicInfo(BasicInfo basicInfo) {
                if (basicInfo == null) {
                    return;
                }
                boolean zBooleanValue = ((Boolean) SPUtils.get(DeviceUpgradeNewPresenter.HAD_RESET_ALEXA, false)).booleanValue();
                Log.d("alexa test", zBooleanValue ? "isHadResetAlexa=true" : "isHadResetAlexa=false");
                CommonLogUtil.printAndSave(!zBooleanValue ? "isHadResetAlexa=false" : "isHadResetAlexa=true");
                if (!zBooleanValue) {
                    Log.d("alexa test", "basicInfo.firmwareVersion = " + basicInfo.firmwareVersion);
                    CommonLogUtil.printAndSave("basicInfo.firmwareVersion = " + basicInfo.firmwareVersion);
                    MainPresenter.this.resetAlexaInit(basicInfo.firmwareVersion);
                }
                BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
                if (currentDeviceInfo != null) {
                    currentDeviceInfo.version = basicInfo.firmwareVersion;
                }
                if (basicInfo.dev_type == 0 && !MainPresenter.this.isDeviceSupported(basicInfo.deivceId)) {
                    if (MainPresenter.needShowWrongBindDialog && MainPresenter.this.isAttachView()) {
                        AppLogUploadManager.uploadLog(currentDeviceInfo, AppLogUploadManager.LogInfo.DEVICE_MISTAKE_DEVICE_BINDING, "", "", null);
                        MainPresenter.needShowWrongBindDialog = false;
                        ((MainView) MainPresenter.this.getView()).onBindWrongDevice();
                        return;
                    }
                    return;
                }
                if (currentDeviceInfo != null && !TextUtils.isEmpty(currentDeviceInfo.mDeviceAddress)) {
                    Iterator<DeviceListEntity.DeviceInfo> it = SPHelper.getDeviceList().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DeviceListEntity.DeviceInfo next = it.next();
                        if (next != null && currentDeviceInfo.mDeviceAddress.equals(next.getMac())) {
                            next.type = basicInfo.dev_type == 1 ? 3 : 4;
                            if (!TextUtils.isEmpty(next.getDeviceId()) && NumUtil.parseInt(next.getDeviceId()).intValue() <= 0) {
                                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "MainPresenter-DeviceId异常，info.mDeviceId=" + next.getDeviceId() + " ,basicInfo.deivceId=" + basicInfo.deivceId);
                                StringBuilder sb = new StringBuilder();
                                sb.append(basicInfo.deivceId);
                                sb.append("");
                                next.setDeviceId(sb.toString());
                            }
                            SPHelper.saveDevice(next);
                        }
                    }
                }
                if (basicInfo.reboot == 1) {
                    SPHelper.setConfigSynced(false);
                    EventBusHelper.post(Constants.EventConstants.EVENT_DEVICE_RESTARTED);
                }
            }
        };
    }

    public void addUnbindCallback() {
        if (this.mUnbindCallback == null) {
            this.mUnbindCallback = new BaseUnbindCallback();
        }
        BLEManager.registerUnbindCallBack(this.mUnbindCallback);
    }

    @Override // com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BaseConnCallback baseConnCallback = this.mConnCallback;
        if (baseConnCallback != null) {
            BLEManager.unregisterConnectCallBack(baseConnCallback);
        }
        BaseDeviceInfoCallback baseDeviceInfoCallback = this.mDeviceInfoCallback;
        if (baseDeviceInfoCallback != null) {
            BLEManager.unregisterGetDeviceInfoCallBack(baseDeviceInfoCallback);
        }
        BaseUnbindCallback baseUnbindCallback = this.mUnbindCallback;
        if (baseUnbindCallback != null) {
            BLEManager.unregisterUnbindCallBack(baseUnbindCallback);
        }
        BLEManager.unregisterWatchOperateCallBack(this.mDialOperateCallback);
    }

    @Override // com.ido.ble.callback.SettingCallBack.ICallBack
    public void onSuccess(SettingCallBack.SettingType settingType, Object obj) {
        BLEManager.unregisterSettingCallBack(this);
        printLoginRegisterLog("成功-同步用户设置信息到设备");
    }

    @Override // com.ido.ble.callback.SettingCallBack.ICallBack
    public void onFailed(SettingCallBack.SettingType settingType) {
        BLEManager.unregisterSettingCallBack(this);
        printLoginRegisterLog("失败-同步用户设置信息到设备");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printLoginRegisterLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, str);
    }

    public void autoConnectDevice() {
        if (BLEManager.isConnected()) {
            return;
        }
        if (BLEManager.isBind()) {
            BLEManager.autoConnect();
            ConnectLogHelper.saveLog("MainPresenter", "autoConnect()");
            return;
        }
        List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
        if (deviceList.isEmpty()) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "MainPresenter BLEManager.autoConnect");
        String mac = deviceList.get(0).getMac();
        BLEManager.autoConnect(mac);
        ConnectLogHelper.saveLog("MainPresenter", "autoConnect(".concat(mac).concat(")"));
    }

    public void uploadDevices() {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            List<BLEDevice> activatedDeviceList = SPHelper.getActivatedDeviceList();
            if (activatedDeviceList.isEmpty()) {
                return;
            }
            uploadDevices(activatedDeviceList);
        }
    }

    public void uploadDevices(final List<BLEDevice> list) {
        if (NetworkUtil.isConnected(IdoApp.getAppContext()) && !list.isEmpty()) {
            final BLEDevice bLEDevice = list.get(0);
            if (bLEDevice == null) {
                list.remove(0);
                uploadDevices(list);
            } else {
                DeviceManager.uploadDevice(bLEDevice.mDeviceId, bLEDevice.mDeviceName, String.valueOf(bLEDevice.version), bLEDevice.mDeviceAddress, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.main.MainPresenter.11
                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onSuccess(Boolean bool) {
                        CommonLogUtil.d("uploadDevices onSuccess = " + bool);
                        if (bool.booleanValue()) {
                            SPHelper.removeActivatedDevice(bLEDevice);
                        }
                        list.remove(0);
                        MainPresenter.this.uploadDevices(list);
                    }

                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onFailed(int i, String str) {
                        CommonLogUtil.d("uploadDevices onFailed message = " + str);
                        list.remove(0);
                        MainPresenter.this.uploadDevices(list);
                    }
                });
            }
        }
    }

    public void initDeviceWhiteList() {
        this.mWhiteListMap.clear();
        List<DeviceWhiteListEntity.DeviceInfo> deviceWhiteList = SPHelper.getDeviceWhiteList();
        if (deviceWhiteList.isEmpty()) {
            this.mWhiteListMap.put(Integer.valueOf(b.o1), "GT01");
            this.mWhiteListMap.put(7142, "Cubitt CT2 Pro");
            this.mWhiteListMap.put(379, "ID206");
            this.mWhiteListMap.put(7130, "Active+II");
            this.mWhiteListMap.put(932, "GT01");
            this.mWhiteListMap.put(7131, "Ulefone watch Pro");
            this.mWhiteListMap.put(7136, "HEALFI WATCh");
            this.mWhiteListMap.put(Integer.valueOf(b.r1), "ID217G");
            this.mWhiteListMap.put(354, "GTband");
            this.mWhiteListMap.put(381, "ID217");
            return;
        }
        for (DeviceWhiteListEntity.DeviceInfo deviceInfo : deviceWhiteList) {
            if (deviceInfo != null) {
                try {
                    this.mWhiteListMap.put(Integer.valueOf(deviceInfo.getDeviceId()), deviceInfo.getBluetoothName());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public boolean isDeviceSupported(int i) {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null || this.mWhiteListMap.containsKey(Integer.valueOf(i))) {
            return true;
        }
        String str = currentDeviceInfo.mDeviceName;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.replaceAll(" ", "").toLowerCase();
        for (String str2 : this.mWhiteListMap.values()) {
            if (!TextUtils.isEmpty(str2) && TextUtils.equals(lowerCase, str2.replaceAll(" ", "").toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void requestDeviceWhiteList() {
        DeviceManager.requestDeviceWhiteList(new DeviceManager.OnDeviceCallback<List<DeviceWhiteListEntity.DeviceInfo>>() { // from class: com.ido.life.module.main.MainPresenter.12
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<DeviceWhiteListEntity.DeviceInfo> list) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "MainPresenter", "requestDeviceWhiteList onSuccess : " + GsonUtil.toJson(list));
                SPHelper.saveDeviceWhiteList(list);
                MainPresenter.this.initDeviceWhiteList();
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "MainPresenter", "requestDeviceWhiteList onFailed :" + str);
            }
        });
    }

    public void uploadCacheLog() {
        saveOtaLog("uploadCacheLog");
        if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveOtaLog("uploadCacheLog，network is error");
            return;
        }
        uploadOtaCacheLog();
        uploadDfuOtaCacheLog();
        uploadFlashCacheLog();
        uploadLanguageCacheLog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadLanguageCacheLog() {
        if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveOtaLog("uploadLanguageCacheLog，network is error");
            return;
        }
        List<String> languageCacheLogList = SPHelper.getLanguageCacheLogList();
        if (languageCacheLogList.isEmpty()) {
            saveOtaLog("uploadLanguageCacheLog，no cache data");
            return;
        }
        if (languageCacheLogUploading) {
            saveOtaLog("languageCacheLogUploading，is uploading");
            return;
        }
        languageCacheLogUploading = true;
        saveOtaLog("uploadLanguageCacheLog，cache data count ：" + languageCacheLogList.size());
        DeviceManager.uploadLanguageCacheLog(languageCacheLogList.get(0), new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.main.MainPresenter.13
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                MainPresenter.this.saveOtaLog("uploadLanguageCacheLog，onSuccess ：" + bool);
                MainPresenter.languageCacheLogUploading = false;
                if (bool.booleanValue()) {
                    MainPresenter.this.uploadLanguageCacheLog();
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                MainPresenter.this.saveOtaLog("uploadLanguageCacheLog，onFailed ：" + str);
                MainPresenter.languageCacheLogUploading = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadFlashCacheLog() {
        if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveOtaLog("uploadFlashCacheLog，network is error");
            return;
        }
        List<String> flashCacheLogList = SPHelper.getFlashCacheLogList();
        if (flashCacheLogList.isEmpty()) {
            saveOtaLog("uploadFlashCacheLog，no cache data");
            return;
        }
        if (flashCacheLogUploading) {
            saveOtaLog("flashCacheLogUploading，is uploading");
            return;
        }
        flashCacheLogUploading = true;
        saveOtaLog("uploadFlashCacheLog，cache data count ：" + flashCacheLogList.size());
        DeviceManager.updateFlashCacheLog(flashCacheLogList.get(0), new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.main.MainPresenter.14
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                MainPresenter.this.saveOtaLog("uploadFlashCacheLog，onSuccess ：" + bool);
                MainPresenter.flashCacheLogUploading = false;
                if (bool.booleanValue()) {
                    MainPresenter.this.uploadFlashCacheLog();
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                MainPresenter.this.saveOtaLog("uploadFlashCacheLog，onFailed ：" + str);
                MainPresenter.flashCacheLogUploading = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadDfuOtaCacheLog() {
        if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveOtaLog("uploadDfuOtaCacheLog，network is error");
            return;
        }
        List<String> dfuOtaCacheLogList = SPHelper.getDfuOtaCacheLogList();
        if (dfuOtaCacheLogList.isEmpty()) {
            saveOtaLog("uploadDfuOtaCacheLog，no cache data");
            return;
        }
        if (dfuOtaCacheLogUploading) {
            saveOtaLog("uploadDfuOtaCacheLog，is uploading");
            return;
        }
        dfuOtaCacheLogUploading = true;
        saveOtaLog("uploadDfuOtaCacheLog，cache data count ：" + dfuOtaCacheLogList.size());
        DeviceManager.updateOtaCacheLog(true, dfuOtaCacheLogList.get(0), new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.main.MainPresenter.15
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                MainPresenter.this.saveOtaLog("uploadDfuOtaCacheLog，onSuccess ：" + bool);
                MainPresenter.dfuOtaCacheLogUploading = false;
                if (bool.booleanValue()) {
                    MainPresenter.this.uploadDfuOtaCacheLog();
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                MainPresenter.this.saveOtaLog("uploadDfuOtaCacheLog，onFailed ：" + str);
                MainPresenter.dfuOtaCacheLogUploading = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadOtaCacheLog() {
        if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveOtaLog("uploadOtaCacheLog，network is error");
            return;
        }
        List<String> otaCacheLogList = SPHelper.getOtaCacheLogList();
        if (otaCacheLogList.isEmpty()) {
            saveOtaLog("uploadOtaCacheLog，no cache data");
            return;
        }
        if (otaCacheLogUploading) {
            saveOtaLog("uploadOtaCacheLog，is uploading");
            return;
        }
        otaCacheLogUploading = true;
        saveOtaLog("uploadOtaCacheLog，cache data count ：" + otaCacheLogList.size());
        DeviceManager.updateOtaCacheLog(false, otaCacheLogList.get(0), new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.main.MainPresenter.16
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                MainPresenter.this.saveOtaLog("uploadOtaCacheLog，onSuccess ：" + bool);
                MainPresenter.otaCacheLogUploading = false;
                if (bool.booleanValue()) {
                    MainPresenter.this.uploadOtaCacheLog();
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                MainPresenter.this.saveOtaLog("uploadOtaCacheLog，onFailed ：" + str);
                MainPresenter.otaCacheLogUploading = false;
            }
        });
    }

    public void uploadDeviceExceptionLog() {
        saveDeviceLog("uploadDeviceExceptionLog");
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            saveDeviceLog("Network is error");
            return;
        }
        String deviceExceptionLogCacheJson = SPHelper.getDeviceExceptionLogCacheJson();
        saveDeviceLog("DeviceExceptionLogCache : " + deviceExceptionLogCacheJson);
        if (TextUtils.isEmpty(deviceExceptionLogCacheJson)) {
            saveDeviceLog("DeviceExceptionLogCache is empty");
        } else {
            saveDeviceLog("uploadDeviceExceptionLog to server，start");
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_DEVICE_EXCEPTION, deviceExceptionLogCacheJson, new AppLogUploadManager.LogUploadCallback<Boolean>() { // from class: com.ido.life.module.main.MainPresenter.17
                @Override // com.ido.life.util.AppLogUploadManager.LogUploadCallback
                public void onSuccess(Boolean bool) {
                    MainPresenter.this.saveDeviceLog("uploadDeviceExceptionLog onSuccess : " + bool);
                    if (bool.booleanValue()) {
                        MainPresenter.this.saveDeviceLog("clear Device Exception Log Cache");
                        SPHelper.clearDeviceExceptionLogCache();
                    }
                }

                @Override // com.ido.life.util.AppLogUploadManager.LogUploadCallback
                public void onFailed(int i, String str) {
                    MainPresenter.this.saveDeviceLog("uploadDeviceExceptionLog onFailed : " + str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveOtaLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath(), "MainPresenter", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDeviceLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), "DEVICE_ERROR_LOG", str);
    }

    @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
    public void onChanged(DeviceChangedPara deviceChangedPara) {
        saveDeviceLog("onChanged，deviceChangedPara ：" + deviceChangedPara);
        if (deviceChangedPara != null) {
            saveDeviceLog("onChanged, errorIndex ：" + deviceChangedPara.errorIndex);
        }
        if (deviceChangedPara == null || deviceChangedPara.errorIndex == 0) {
            return;
        }
        saveDeviceExceptionLog(deviceChangedPara.errorIndex);
        uploadDeviceExceptionLog();
    }

    public void saveDeviceExceptionLog(int i) {
        BLEDevice deviceInfo = getDeviceInfo();
        DeviceExceptionModel deviceExceptionModel = new DeviceExceptionModel(deviceInfo.mDeviceId, deviceInfo.mDeviceName, deviceInfo.mDeviceAddress, deviceInfo.version, i, System.currentTimeMillis());
        saveDeviceLog("saveDeviceExceptionLog, DeviceExceptionModel ：" + deviceExceptionModel);
        SPHelper.saveDeviceExceptionLog(deviceExceptionModel);
    }

    public BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
            currentDeviceInfo.type = basicInfo.dev_type != 1 ? 4 : 3;
        } else {
            currentDeviceInfo.type = currentDeviceInfo.type != 2 ? 4 : 3;
        }
        return currentDeviceInfo;
    }

    private void checkWhenPageCreate() {
        checkVersion();
    }

    public void checkVersion() {
        printLoginRegisterLog("开始检查APP新版本");
        if (TimeUtil.isSameDay(((Long) SPUtils.get(Constants.APP_UPDATE_TIME, 0L)).longValue(), System.currentTimeMillis())) {
            printLoginRegisterLog("同一天内只检查一次，不做网络请求");
        } else {
            AccountManager.requestAppVersionInfo(new AccountManager.OnCommCallback<AppInfoEntity.AppInfo>() { // from class: com.ido.life.module.main.MainPresenter.18
                @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                public void onSuccess(AppInfoEntity.AppInfo appInfo) {
                    if (appInfo != null) {
                        if (appInfo.isForceUpdate()) {
                            DialogHelper.showAppForceUpdateDialog(null);
                        } else {
                            DialogHelper.showAppNormalUpdateDialog(null, appInfo);
                        }
                    }
                }

                @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                public void onFailed(String str) {
                    MainPresenter.this.printLoginRegisterLog("检查APP版本message=" + str);
                }
            });
        }
    }

    public void checkNotificationDialog() {
        if (!BLEManager.isConnected() || !BLEManager.isBind()) {
            printLoginRegisterLog("未连接设备，不需要提示开启通知功能");
            return;
        }
        final SwitchStatus.NotificationSwitch notificationStatus = SPHelper.getNotificationStatus();
        if (!notificationStatus.masterSwitched || AppUtil.notificationEnabled(IdoApp.getAppContext())) {
            return;
        }
        this.mNotificationPermissionDialog = DialogHelper.showNotificationPermissionDialog(null);
        DialogFragment dialogFragment = this.mNotificationPermissionDialog;
        if (dialogFragment instanceof NotificationPermissionSettingDialog) {
            ((NotificationPermissionSettingDialog) dialogFragment).setOnClickListener(new NotificationPermissionSettingDialog.OnClickListener() { // from class: com.ido.life.module.main.MainPresenter.19
                @Override // com.ido.life.dialog.NotificationPermissionSettingDialog.OnClickListener
                public void onConfirmClicked() {
                    MsgNotificationHelper.saveLog("PermissionSettingDialog，onOpenNotificationClicked");
                    if (MainPresenter.this.isAttachView()) {
                        ((MainView) MainPresenter.this.getView()).jumpNotificationSettingPage();
                    }
                }

                @Override // com.ido.life.dialog.NotificationPermissionSettingDialog.OnClickListener
                public void onCancelClicked() {
                    MsgNotificationHelper.saveLog("PermissionSettingDialog，onCloseNotificationClicked");
                    notificationStatus.masterSwitched = false;
                    MainPresenter.this.mNotificationPermissionDialog = null;
                    AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MESSAGE_NOTIFICATION_SUCCESS, "", null);
                    SPHelper.setNotificationStatus(notificationStatus);
                    DialogHelper.dismissDialogFragment(DialogHelper.TYPE_NOTIFICATION_PERMISSION());
                    MainPresenter.this.mNotificationPermissionDialog = null;
                }
            });
        }
    }

    public void checkService() {
        Context appContext = IdoApp.getAppContext();
        if (!AppUtil.isServiceRunning(appContext, LocalService.class.getName())) {
            printLoginRegisterLog("启动LocalService");
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    appContext.startForegroundService(new Intent(appContext, (Class<?>) LocalService.class));
                } else {
                    appContext.startService(new Intent(appContext, (Class<?>) LocalService.class));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (AppUtil.isServiceRunning(appContext, JobHandlerService.class.getName()) || Build.VERSION.SDK_INT < 21) {
            return;
        }
        try {
            if (AppUtil.isHuawei() && Build.VERSION.SDK_INT == 28) {
                return;
            }
            printLoginRegisterLog("检测到JobHandlerService服务未启动，开始启动JobHandlerService服务");
            appContext.startService(new Intent(appContext, (Class<?>) JobHandlerService.class));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}