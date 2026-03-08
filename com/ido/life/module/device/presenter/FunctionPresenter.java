package com.ido.life.module.device.presenter;

import android.os.Handler;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.business.sync.SyncPara;
import com.ido.ble.callback.RebootCallback;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.UserInfo;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.Language;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.ble.BaseSyncDataCallback;
import com.ido.life.ble.BaseSyncProgressCallback;
import com.ido.life.ble.BaseUnbindCallback;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.TopDialPlateEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IFunctionListView;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.LanguageManager;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.WeatherHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FunctionPresenter extends BaseCmdPresenter<IFunctionListView> {
    private static final long RESTART_DURATION = 60000;
    private static final long SYNC_DURATION = 45000;
    private boolean mIsSyncing;
    private Handler mTimeOutHandler = new Handler();
    private BaseUnbindCallback mUnbindCallback = new BaseUnbindCallback() { // from class: com.ido.life.module.device.presenter.FunctionPresenter.1
        @Override // com.ido.life.ble.BaseUnbindCallback, com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onSuccess() {
            SPHelper.removeDevice(new DeviceListEntity.DeviceInfo(FunctionPresenter.this.getDeviceInfo()));
            BLEManager.unregisterUnbindCallBack(FunctionPresenter.this.mUnbindCallback);
            if (FunctionPresenter.this.isAttachView()) {
                ((IFunctionListView) FunctionPresenter.this.getView()).onUnbindSuccess();
            }
        }

        @Override // com.ido.life.ble.BaseUnbindCallback, com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onFailed() {
            super.onFailed();
            BLEManager.unregisterUnbindCallBack(FunctionPresenter.this.mUnbindCallback);
            if (FunctionPresenter.this.isAttachView()) {
                ((IFunctionListView) FunctionPresenter.this.getView()).onUnbindFailed();
            }
        }
    };
    private BaseSyncProgressCallback mSyncProgressListener = new BaseSyncProgressCallback() { // from class: com.ido.life.module.device.presenter.FunctionPresenter.2
        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onStart() {
            super.onStart();
            FunctionPresenter.this.mIsSyncing = true;
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onProgress(int i) {
            super.onProgress(i);
            CommonLogUtil.d("onDownloadProgress = " + i);
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onSuccess() {
            super.onSuccess();
            FunctionPresenter.this.restart();
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onFailed() {
            super.onFailed();
            FunctionPresenter.this.restart();
        }
    };
    private Runnable mSyncRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$FunctionPresenter$vQp3nkbdCpjuuXJXdIlpUcMDyHg
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$FunctionPresenter();
        }
    };
    private Runnable mRestartRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.FunctionPresenter.3
        @Override // java.lang.Runnable
        public void run() {
            BLEManager.unregisterRebootCallBack(FunctionPresenter.this.mRebootCallback);
            if (FunctionPresenter.this.isAttachView()) {
                if (FunctionPresenter.this.isConnected()) {
                    ((IFunctionListView) FunctionPresenter.this.getView()).onRestartSuccess();
                } else {
                    ((IFunctionListView) FunctionPresenter.this.getView()).onRestartFailed();
                }
            }
        }
    };
    private RebootCallback.ICallBack mRebootCallback = new RebootCallback.ICallBack() { // from class: com.ido.life.module.device.presenter.FunctionPresenter.4
        @Override // com.ido.ble.callback.RebootCallback.ICallBack
        public void onSuccess() {
            FunctionPresenter.this.mTimeOutHandler.removeCallbacks(FunctionPresenter.this.mRestartRunnable);
            BLEManager.unregisterRebootCallBack(FunctionPresenter.this.mRebootCallback);
            if (!BLEManager.isConnected()) {
                BLEManager.autoConnect();
                ConnectLogHelper.saveLog("FunctionPresenter", "autoConnect()");
            }
            if (FunctionPresenter.this.isAttachView()) {
                ((IFunctionListView) FunctionPresenter.this.getView()).onRestartSuccess();
            }
        }

        @Override // com.ido.ble.callback.RebootCallback.ICallBack
        public void onFailed() {
            FunctionPresenter.this.mTimeOutHandler.removeCallbacks(FunctionPresenter.this.mRestartRunnable);
            BLEManager.unregisterRebootCallBack(FunctionPresenter.this.mRebootCallback);
            if (FunctionPresenter.this.isAttachView()) {
                ((IFunctionListView) FunctionPresenter.this.getView()).onRestartFailed();
            }
        }
    };

    public boolean getGoalAchievementSwitch() {
        return false;
    }

    public void setGoalAchievementSwitch(boolean z) {
    }

    public /* synthetic */ void lambda$new$0$FunctionPresenter() {
        if (isAttachView() && this.mIsSyncing) {
            restart();
        }
    }

    public SwitchStatus getSwitchStatus() {
        return SPHelper.getSwitchStatus();
    }

    public void requestTopDialPlate() {
        if (SPHelper.isLogin()) {
            DeviceManager.requestTopDialPlate(getAppBleDevice(), LanguageUtil.getSystemLanguage(), AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, new DeviceManager.OnDeviceCallback<List<TopDialPlateEntity.DialPlate>>() { // from class: com.ido.life.module.device.presenter.FunctionPresenter.5
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(List<TopDialPlateEntity.DialPlate> list) {
                    if (FunctionPresenter.this.isAttachView()) {
                        ((IFunctionListView) FunctionPresenter.this.getView()).onRequestDialSuccess(list);
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str) {
                    CommonLogUtil.d("requestTopDialPlate onFailed = " + i + "___" + str);
                }
            });
        }
    }

    public void setCallReminderSwitch(SwitchStatus switchStatus) {
        SPHelper.setSwitchStatus(switchStatus);
    }

    public boolean getWeatherSwitch() {
        return LocalDataManager.getWeatherSwitch();
    }

    public void setWeatherSwitch(boolean z) {
        BLEManager.setWeatherSwitch(z);
    }

    public boolean getWristScreenSwitch() {
        UpHandGesture upHandGesture = LocalDataManager.getUpHandGesture();
        return upHandGesture != null && upHandGesture.onOff == 170;
    }

    public void setWristScreenSwitch(boolean z) {
        UpHandGesture upHandGesture = LocalDataManager.getUpHandGesture();
        if (upHandGesture == null) {
            upHandGesture = new UpHandGesture();
        }
        upHandGesture.onOff = z ? 170 : 85;
        BLEManager.setUpHandGesture(upHandGesture);
    }

    public boolean getMusicControlSwitch() {
        return LocalDataManager.getMusicSwitch();
    }

    public void setMusicSwitch(boolean z) {
        BLEManager.setMusicSwitch(z);
    }

    public boolean getFindPhoneSwitch() {
        return LocalDataManager.getMusicSwitch();
    }

    public void setFindPhoneSwitch(boolean z) {
        if (!z) {
            EventBusHelper.post(500);
        }
        BLEManager.setFindPhoneSwitch(z);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView()) {
            ((IFunctionListView) getView()).onSetCmdFailed(settingType);
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.WEATHER_SWITCH && getWeatherSwitch()) {
            WeatherHelper.requestWeatherFromServer();
        }
        if (isAttachView()) {
            ((IFunctionListView) getView()).onSetCmdSuccess(settingType);
        }
    }

    public List<Language> getSupportLanguageList() {
        return LanguageManager.getSupportLanguageList(LocalDataManager.getSupportFunctionInfo());
    }

    public boolean isWalkReminderOn() {
        WalkReminder walkReminder = LocalDataManager.getWalkReminder();
        return walkReminder != null && walkReminder.getOnOff() == 0;
    }

    public boolean isWaterClockOn() {
        DrinkWaterReminder drinkWaterReminder = LocalDataManager.getDrinkWaterReminder();
        return drinkWaterReminder != null && drinkWaterReminder.isOnOff();
    }

    public void preRestartDevice() {
        UserInfo userInfo;
        this.mTimeOutHandler.postDelayed(this.mSyncRunnable, SYNC_DURATION);
        com.ido.life.database.model.UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        UserInfo userInfo2 = new UserInfo();
        if (userInfoQueryUserInfo != null) {
            userInfo2.gender = userInfoQueryUserInfo.getGender() == 2 ? 1 : 0;
            userInfo2.height = userInfoQueryUserInfo.getHeight() > 0.0f ? (int) userInfoQueryUserInfo.getHeight() : 175;
            userInfo2.weight = userInfoQueryUserInfo.getWeight() > 0.0f ? (int) userInfoQueryUserInfo.getWeight() : 60;
            int[] iArrYearMonthDay = DateUtil.yearMonthDay(userInfoQueryUserInfo.getBirthday());
            userInfo2.year = iArrYearMonthDay[0];
            userInfo2.month = iArrYearMonthDay[1];
            userInfo2.day = iArrYearMonthDay[2];
        } else {
            userInfo = LocalDataManager.getUserInfo();
            if (userInfo == null) {
                userInfo2.gender = 0;
                userInfo2.height = 175;
                userInfo2.weight = 60;
                userInfo2.year = DateUtil.getCurrentDate()[0] - 20;
                userInfo2.month = 1;
                userInfo2.day = 1;
            }
            BLEManager.setUserInfo(userInfo);
            SyncPara syncPara = new SyncPara();
            syncPara.isNeedSyncConfigData = false;
            syncPara.iSyncProgressListener = this.mSyncProgressListener;
            syncPara.iSyncDataListener = new BaseSyncDataCallback();
            BLEManager.syncAllData(syncPara);
        }
        userInfo = userInfo2;
        BLEManager.setUserInfo(userInfo);
        SyncPara syncPara2 = new SyncPara();
        syncPara2.isNeedSyncConfigData = false;
        syncPara2.iSyncProgressListener = this.mSyncProgressListener;
        syncPara2.iSyncDataListener = new BaseSyncDataCallback();
        BLEManager.syncAllData(syncPara2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restart() {
        this.mTimeOutHandler.removeCallbacks(this.mSyncRunnable);
        this.mTimeOutHandler.postDelayed(this.mRestartRunnable, 60000L);
        this.mIsSyncing = false;
        BLEManager.unregisterRebootCallBack(this.mRebootCallback);
        BLEManager.registerRebootCallBack(this.mRebootCallback);
        BLEManager.reBoot();
    }

    public void unbind() {
        BLEManager.registerUnbindCallBack(this.mUnbindCallback);
        BLEManager.unbind();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        this.mTimeOutHandler.removeCallbacksAndMessages(null);
        BLEManager.registerUnbindCallBack(this.mUnbindCallback);
    }
}