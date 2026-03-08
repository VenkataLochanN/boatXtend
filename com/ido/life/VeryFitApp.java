package com.ido.life;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.log.AlexaLogPathImpl;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.InitParam;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.Goal;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.NetConfiguration;
import com.ido.common.net.RetrofitService;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.CrashHandlerUtil;
import com.ido.common.utils.FileUtil;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.data.cache.DataManagerService;
import com.ido.life.data.health.local.UserTargetPreference;
import com.ido.life.database.GreenDaoManager;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.UserInfoDao;
import com.ido.life.database.model.UserTargetNewDao;
import com.ido.life.module.BleReceiver;
import com.ido.life.module.sport.setting.SportSettingPreference;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import com.ido.life.module.user.set.data.GoogleFitPreference;
import com.ido.life.module.user.set.data.StravaPreference;
import com.ido.life.module.user.userdata.UserDataPreference;
import com.ido.life.net.BaseUrl;
import com.ido.life.net.BaseUrlInterceptor;
import com.ido.life.net.RequestInterceptor;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import com.ido.ntf.NotificationAndCallManager;
import com.tencent.bugly.crashreport.CrashReport;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

/* JADX INFO: loaded from: classes2.dex */
public class VeryFitApp extends MultiDexApplication {
    private static final String TAG = "Veryfit";
    private static VeryFitApp app;
    public static AtomicInteger isFirstTokenInvalid = new AtomicInteger(0);
    public static boolean isThreeGetUserinfoFail = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private BroadcastReceiver systemReceiver = new BroadcastReceiver() { // from class: com.ido.life.VeryFitApp.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            TimeSet timeSetQueryTimeSet;
            String action = intent.getAction();
            if ("android.intent.action.LOCALE_CHANGED".equals(action)) {
                if (BLEManager.isConnected()) {
                    VeryFitApp.this.setUnits();
                }
                VeryFitApp.this.exitApp();
                System.exit(0);
                return;
            }
            if ("android.intent.action.TIMEZONE_CHANGED".equals(action) && (timeSetQueryTimeSet = GreenDaoUtil.queryTimeSet(RunTimeUtil.getInstance().getUserId())) != null && timeSetQueryTimeSet.getTimeFormat() == 0) {
                SPHelper.setTimeFormat(timeSetQueryTimeSet.getTimeFormat());
            }
            if (BLEManager.isConnected()) {
                if ("android.intent.action.TIME_SET".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action)) {
                    BLEManager.setTime();
                    Goal goal = LocalDataManager.getGoal();
                    if (goal != null) {
                        BLEManager.setGoal(goal);
                    }
                    VeryFitApp.this.setUnits();
                    return;
                }
                if ("android.intent.action.CONFIGURATION_CHANGED".equals(action)) {
                    VeryFitApp.this.setUnits();
                    return;
                }
                if ("android.intent.action.TIME_TICK".equals(action)) {
                    Date todayDate = DateUtil.getTodayDate();
                    if (todayDate.getHours() == 0 && todayDate.getMinutes() == 0) {
                        EventBusHelper.post(105);
                    }
                }
            }
        }
    };

    public interface ClearCacheCallBack {
        void clearSuccess();
    }

    private void initFacebook() {
    }

    private void setNightMode() {
        AppCompatDelegate.setDefaultNightMode(2);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        app = this;
        String processName = AppUtil.getProcessName(this, Process.myPid());
        LogPathImpl.initLogPath(this);
        AlexaLogPathImpl.initLogPath(this);
        if (getPackageName().equals(processName)) {
            initApp();
        }
        NotificationAndCallManager.init(getApplicationContext());
        ConnectLogHelper.saveLog("VeryFitApp", "app launched，app version is : V" + AppUtil.getVersionName(app));
    }

    public static VeryFitApp getApp() {
        return app;
    }

    private IntentFilter addIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        return intentFilter;
    }

    private void initApp() {
        CommonLogUtil.d("initApp");
        GreenDaoManager.getInstance().init(app);
        BLEManager.onApplicationCreate(this);
        intIdoAppSDK();
        initDirs();
        initBleSdk();
        init7_0_Camera();
        initFacebook();
        initCrashHandler();
        registerReceiver(this.systemReceiver, addIntentFilter());
        initBleReceive();
        registerActivityLifecycleCallbacks(VeryFitLifecycleCallbacks.getInstance());
        CommonLogUtil.setLogEnable(false);
        CommonLogUtil.setSaveLogEnable(true);
        configNetConfig();
        initAlexaLog();
        DataManagerService.INSTANCE.getInstance().init();
    }

    private void initBugly() {
        CrashReport.initCrashReport(app, Constants.BUGLY_ID, false);
        CrashReport.setUserSceneTag(app, 184824);
    }

    private void initAlexaLog() {
        AlexaApp.init(getApplicationContext(), AlexaConstant.LANGUAGE_EN_IN);
        AlexaLogUtil.setLogEnable(false);
        AlexaLogUtil.setSaveLogEnable(true);
    }

    private void configNetConfig() {
        NetConfiguration.Builder builderAddRequestInterceptorList = new NetConfiguration.Builder(BaseUrl.DEFAULT_HOST).Appkey(Constants.APP_KEY).addRequestInterceptorList(new BaseUrlInterceptor()).addRequestInterceptorList(new RequestInterceptor(Constants.APP_KEY, ""));
        builderAddRequestInterceptorList.Authorization("");
        RetrofitService.getInstance().init(builderAddRequestInterceptorList.build());
    }

    private void initDirs() {
        File file = new File(LogPathImpl.getInstance().getRootPath());
        CommonLogUtil.d(TAG, "initDirs: " + file.toString());
        file.mkdirs();
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(LogPathImpl.getInstance().getPicPath());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(LogPathImpl.getInstance().getLogPath());
        if (!file3.exists()) {
            file3.mkdirs();
        }
        File file4 = new File(FileUtil.getSdcard() + "/boatWave");
        if (file4.exists()) {
            return;
        }
        file4.mkdirs();
    }

    private void initBleReceive() {
        BleReceiver bleReceiver = new BleReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(bleReceiver, intentFilter);
    }

    private void initCrashHandler() {
        CommonLogUtil.d("BuildConfig.DEBUG:false");
        CrashHandlerUtil.getInstance().init(this);
        CrashHandlerUtil.getInstance().setCrashDir(LogPathImpl.getInstance().getCrashLogPath());
    }

    private void intIdoAppSDK() {
        IdoApp.init(this);
    }

    private void initBleSdk() {
        InitParam initParam = new InitParam();
        initParam.log_save_path = LogPathImpl.getInstance().getBleSdkLogPath();
        initParam.isSaveDeviceDataToDB = false;
        initParam.isNeedSoLibAutoSyncConfigIfReboot = false;
        initParam.isEncryptedSPData = true;
        initParam.databaseName = "boAtWaveSdk.db";
        initParam.soJinLogSavePath = "";
        BLEManager.init(initParam);
    }

    private void init7_0_Camera() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnits() {
        DeviceConfigHelper.setUnitsFlowSystem(true);
    }

    public void exitApp() {
        for (Activity activity : VeryFitLifecycleCallbacks.getInstance().getActivities()) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNormalCache() {
        AuthorizationPreference.clear();
        UserTargetPreference.clear();
        GoogleFitPreference.clear();
        StravaPreference.clear();
        UserDataPreference.clear();
        SportSettingPreference.clear();
    }

    public void quitClearCacheThread(final ClearCacheCallBack clearCacheCallBack) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "清除数据开始");
        new Thread(new Runnable() { // from class: com.ido.life.VeryFitApp.2
            @Override // java.lang.Runnable
            public void run() {
                VeryFitApp.this.clearNormalCache();
                Collection<AbstractDao<?, ?>> allDaos = GreenDaoUtil.getDaoSession().getAllDaos();
                if (allDaos != null && allDaos.size() > 0) {
                    for (AbstractDao<?, ?> abstractDao : allDaos) {
                        if (!(abstractDao instanceof UserInfoDao) && !(abstractDao instanceof UserTargetNewDao)) {
                            try {
                                Database database = abstractDao.getDatabase();
                                if (database.isDbLockedByCurrentThread()) {
                                    abstractDao.deleteAll();
                                } else {
                                    database.beginTransaction();
                                    try {
                                        database.execSQL("DELETE FROM '" + abstractDao.getTablename() + "'");
                                        database.setTransactionSuccessful();
                                        database.endTransaction();
                                    } catch (Throwable th) {
                                        database.endTransaction();
                                        throw th;
                                    }
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
                VeryFitApp.this.handler.post(new Runnable() { // from class: com.ido.life.VeryFitApp.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GreenDaoUtil.deleteUserInfo(-1L);
                        GreenDaoUtil.deleteUserTarget(-1L);
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), VeryFitApp.TAG, "清除数据结束");
                        clearCacheCallBack.clearSuccess();
                    }
                });
            }
        }).start();
    }

    public void notManualClearCache() {
        clearNormalCache();
        Collection<AbstractDao<?, ?>> allDaos = GreenDaoUtil.getDaoSession().getAllDaos();
        if (allDaos == null || allDaos.size() <= 0) {
            return;
        }
        for (AbstractDao<?, ?> abstractDao : allDaos) {
            try {
                if (abstractDao instanceof UserInfoDao) {
                    GreenDaoUtil.deleteAllLoginUserInfo((UserInfoDao) abstractDao);
                } else if (abstractDao instanceof UserTargetNewDao) {
                    GreenDaoUtil.deleteAllLoginUserTarget((UserTargetNewDao) abstractDao);
                } else {
                    abstractDao.deleteAll();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void logout() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ido.life.VeryFitApp.3
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) PreLoginAndRegisterActivity.class);
                intent.addFlags(32768);
                intent.addFlags(268435456);
                intent.putExtra(Constants.LOGIN_OTHER_PHONE, true);
                IdoApp.getAppContext().startActivity(intent);
                EventBusHelper.post(305);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), VeryFitApp.TAG, "数据库加密异常退出登录,跳转到登录注册页 ");
            }
        }, 1500L);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "跳转到登录页 ");
        clearCache();
    }

    public void clearCache() {
        AuthorizationPreference.clear();
        UserTargetPreference.clear();
        GoogleFitPreference.clear();
        StravaPreference.clear();
        UserDataPreference.clear();
        SportSettingPreference.clear();
        GreenDaoUtil.clearUserData(RunTimeUtil.getInstance().getUserId());
        RunTimeUtil.getInstance().setUserId(-1L);
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(this.systemReceiver);
        unregisterActivityLifecycleCallbacks(VeryFitLifecycleCallbacks.getInstance());
    }
}