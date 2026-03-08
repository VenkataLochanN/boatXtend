package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.business.multidevice.ICommonListener;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.RebootCallback;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.base.BaseMessage;
import com.ido.life.ble.BaseSyncProgressCallback;
import com.ido.life.ble.BaseUnbindCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IDeviceSettingView;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.BluetoothUtils;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.SPHelper;
import com.ido.life.util.WeatherHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceSettingPresenter extends BaseCmdPresenter<IDeviceSettingView> implements DeviceParaChangedCallBack.ICallBack {
    private static final long DELETE_DEVICE_DURATION = 15000;
    private static final long FACTORY_RESET_DURATION = 15000;
    private static final long RESTART_DURATION = 60000;
    private static final long SYNC_DURATION = 45000;
    private BLEDevice mDeviceInfo;
    private boolean mIsSyncing;
    private final Handler mTimeOutHandler = new Handler();
    private final BaseUnbindCallback mUnbindCallback = new BaseUnbindCallback() { // from class: com.ido.life.module.device.presenter.DeviceSettingPresenter.1
        @Override // com.ido.life.ble.BaseUnbindCallback, com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onSuccess() {
            DeviceSettingPresenter.this.mTimeOutHandler.removeCallbacks(DeviceSettingPresenter.this.mDeleteRunnable);
            if (DeviceSettingPresenter.this.mDeviceInfo == null) {
                DeviceSettingPresenter deviceSettingPresenter = DeviceSettingPresenter.this;
                deviceSettingPresenter.mDeviceInfo = deviceSettingPresenter.getDeviceInfo();
            }
            BluetoothUtils.INSTANCE.removeBondDevice(DeviceSettingPresenter.this.mDeviceInfo.mDeviceAddress);
            SPHelper.removeDevice(new DeviceListEntity.DeviceInfo(DeviceSettingPresenter.this.mDeviceInfo));
            BLEManager.unregisterUnbindCallBack(DeviceSettingPresenter.this.mUnbindCallback);
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                DeviceManager.deleteDevice(DeviceSettingPresenter.this.mDeviceInfo.mDeviceAddress, null);
            }
            BLEManager.removeBondStatusFromPhoneBluetoothPairedList(DeviceSettingPresenter.this.mDeviceInfo.mDeviceAddress);
            DeviceSettingPresenter.this.saveLog("unbind onSuccess");
            if (DeviceSettingPresenter.this.isAttachView()) {
                ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onDeleteSuccess();
            }
        }

        @Override // com.ido.life.ble.BaseUnbindCallback, com.ido.ble.callback.UnbindCallBack.ICallBack
        public void onFailed() {
            super.onFailed();
            DeviceSettingPresenter.this.saveLog("unbind onFailed");
            DeviceSettingPresenter.this.mTimeOutHandler.removeCallbacks(DeviceSettingPresenter.this.mDeleteRunnable);
            BLEManager.unregisterUnbindCallBack(DeviceSettingPresenter.this.mUnbindCallback);
            if (DeviceSettingPresenter.this.isAttachView()) {
                ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onDeleteFailed();
            }
        }
    };
    private final Runnable mRestartRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.DeviceSettingPresenter.2
        @Override // java.lang.Runnable
        public void run() {
            DeviceSettingPresenter.this.saveLog("restart timeout");
            BLEManager.unregisterRebootCallBack(DeviceSettingPresenter.this.mRebootCallback);
            if (DeviceSettingPresenter.this.isAttachView()) {
                if (DeviceSettingPresenter.this.isConnected()) {
                    ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onRestartSuccess();
                } else {
                    ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onRestartFailed();
                }
            }
        }
    };
    private final RebootCallback.ICallBack mRebootCallback = new RebootCallback.ICallBack() { // from class: com.ido.life.module.device.presenter.DeviceSettingPresenter.3
        @Override // com.ido.ble.callback.RebootCallback.ICallBack
        public void onSuccess() {
            DeviceSettingPresenter.this.saveLog("restart onSuccess");
            SPHelper.setConfigSynced(false);
            DeviceSettingPresenter.this.mTimeOutHandler.removeCallbacks(DeviceSettingPresenter.this.mRestartRunnable);
            BLEManager.unregisterRebootCallBack(DeviceSettingPresenter.this.mRebootCallback);
            if (!BLEManager.isConnected()) {
                BLEManager.autoConnect();
                ConnectLogHelper.saveLog("DeviceSettingPresenter", "autoConnect()");
            }
            if (DeviceSettingPresenter.this.isAttachView()) {
                ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onRestartSuccess();
            }
        }

        @Override // com.ido.ble.callback.RebootCallback.ICallBack
        public void onFailed() {
            DeviceSettingPresenter.this.saveLog("restart onFailed");
            DeviceSettingPresenter.this.mTimeOutHandler.removeCallbacks(DeviceSettingPresenter.this.mRestartRunnable);
            BLEManager.unregisterRebootCallBack(DeviceSettingPresenter.this.mRebootCallback);
            if (DeviceSettingPresenter.this.isAttachView()) {
                ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onRestartFailed();
            }
        }
    };
    private final Runnable mSyncRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceSettingPresenter$XoV087SfEIhpA1EDg6I4IGNS3ls
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$DeviceSettingPresenter();
        }
    };
    private final BaseSyncProgressCallback mSyncProgressListener = new BaseSyncProgressCallback() { // from class: com.ido.life.module.device.presenter.DeviceSettingPresenter.4
        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onStart() {
            super.onStart();
            DeviceSettingPresenter.this.mIsSyncing = true;
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onProgress(int i) {
            super.onProgress(i);
            CommonLogUtil.d("onDownloadProgress = " + i);
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onSuccess() {
            super.onSuccess();
            DeviceSettingPresenter.this.restart();
        }

        @Override // com.ido.life.ble.BaseSyncProgressCallback, com.ido.ble.business.sync.ISyncProgressListener
        public void onFailed() {
            super.onFailed();
            DeviceSettingPresenter.this.restart();
        }
    };
    private final Runnable mFactoryResetRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceSettingPresenter$rZZTTLcgTHoA0deQdLCOa3-7X50
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$DeviceSettingPresenter();
        }
    };
    private final Runnable mDeleteRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceSettingPresenter$lfZ9tb_2QfGU8Ymb0KOPii2g6v0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$2$DeviceSettingPresenter();
        }
    };
    private final BaseDeviceParaCallBack mICallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.DeviceSettingPresenter.6
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetDoNotDisturbPara(NotDisturbPara notDisturbPara) {
            super.onGetDoNotDisturbPara(notDisturbPara);
            if (!DeviceSettingPresenter.this.isAttachView() || notDisturbPara == null) {
                return;
            }
            ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onGetDNDMode(notDisturbPara);
        }

        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetUpHandGesture(UpHandGesture upHandGesture) {
            super.onGetUpHandGesture(upHandGesture);
            if (!DeviceSettingPresenter.this.isAttachView() || upHandGesture == null) {
                return;
            }
            UpHandGesture upHandGesture2 = LocalDataManager.getUpHandGesture();
            if (upHandGesture2 != null && upHandGesture2.onOff != upHandGesture.onOff) {
                DeviceSettingPresenter.this.logP("onGetUpHandGesture, sdk缓存与设备端不一致，重新下发");
                DeviceSettingPresenter.this.setWristScreenSwitch(upHandGesture.onOff == 170);
            }
            ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onGetUpHandGesture(upHandGesture);
        }

        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
            super.onGetScreenBrightness(screenBrightness);
            if (!DeviceSettingPresenter.this.isAttachView() || screenBrightness == null) {
                return;
            }
            ((IDeviceSettingView) DeviceSettingPresenter.this.getView()).onGetScreenBrightness(screenBrightness);
        }
    };

    public /* synthetic */ void lambda$new$0$DeviceSettingPresenter() {
        if (isAttachView() && this.mIsSyncing) {
            restart();
        }
    }

    public /* synthetic */ void lambda$new$1$DeviceSettingPresenter() {
        saveLog("factoryReset timeout");
        if (isAttachView()) {
            ((IDeviceSettingView) getView()).onResetFailed();
        }
    }

    public /* synthetic */ void lambda$new$2$DeviceSettingPresenter() {
        saveLog("unbind timeout");
        if (isAttachView()) {
            ((IDeviceSettingView) getView()).onDeleteFailed();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void preRestartDevice() {
        /*
            r9 = this;
            boolean r0 = com.ido.ble.BLEManager.isSyncAllDataIng()
            if (r0 == 0) goto L16
            boolean r0 = r9.isAttachView()
            if (r0 == 0) goto L15
            com.ido.life.base.IBaseView r0 = r9.getView()
            com.ido.life.module.device.view.IDeviceSettingView r0 = (com.ido.life.module.device.view.IDeviceSettingView) r0
            r0.onSyncing()
        L15:
            return
        L16:
            android.os.Handler r0 = r9.mTimeOutHandler
            java.lang.Runnable r1 = r9.mSyncRunnable
            r0.removeCallbacks(r1)
            android.os.Handler r0 = r9.mTimeOutHandler
            java.lang.Runnable r1 = r9.mSyncRunnable
            r2 = 45000(0xafc8, double:2.2233E-319)
            r0.postDelayed(r1, r2)
            com.ido.life.util.RunTimeUtil r0 = com.ido.life.util.RunTimeUtil.getInstance()
            long r0 = r0.getUserId()
            com.ido.life.database.model.UserInfo r0 = com.ido.life.util.GreenDaoUtil.queryUserInfo(r0)
            com.ido.ble.protocol.model.UserInfo r1 = new com.ido.ble.protocol.model.UserInfo
            r1.<init>()
            r2 = 60
            r3 = 175(0xaf, float:2.45E-43)
            r4 = 1
            r5 = 0
            if (r0 == 0) goto L80
            int r6 = r0.getGender()
            r7 = 2
            if (r6 != r7) goto L49
            r6 = r4
            goto L4a
        L49:
            r6 = r5
        L4a:
            r1.gender = r6
            float r6 = r0.getHeight()
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L5a
            float r3 = r0.getHeight()
            int r3 = (int) r3
        L5a:
            r1.height = r3
            float r3 = r0.getWeight()
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 <= 0) goto L69
            float r2 = r0.getWeight()
            int r2 = (int) r2
        L69:
            r1.weight = r2
            java.lang.String r0 = r0.getBirthday()
            int[] r0 = com.ido.life.util.DateUtil.yearMonthDay(r0)
            r2 = r0[r5]
            r1.year = r2
            r2 = r0[r4]
            r1.month = r2
            r0 = r0[r7]
            r1.day = r0
            goto L9a
        L80:
            com.ido.ble.protocol.model.UserInfo r0 = com.ido.ble.LocalDataManager.getUserInfo()
            if (r0 != 0) goto L9b
            r1.gender = r5
            r1.height = r3
            r1.weight = r2
            int[] r0 = com.ido.life.util.DateUtil.getCurrentDate()
            r0 = r0[r5]
            int r0 = r0 + (-20)
            r1.year = r0
            r1.month = r4
            r1.day = r4
        L9a:
            r0 = r1
        L9b:
            com.ido.ble.BLEManager.setUserInfo(r0)
            com.ido.ble.business.sync.SyncPara r0 = new com.ido.ble.business.sync.SyncPara
            r0.<init>()
            r0.isNeedSyncConfigData = r5
            com.ido.life.ble.BaseSyncProgressCallback r1 = r9.mSyncProgressListener
            r0.iSyncProgressListener = r1
            com.ido.life.ble.BaseSyncDataCallback r1 = new com.ido.life.ble.BaseSyncDataCallback
            r1.<init>()
            r0.iSyncDataListener = r1
            boolean r1 = r9.isAttachView()
            if (r1 == 0) goto Lbf
            com.ido.life.base.IBaseView r1 = r9.getView()
            com.ido.life.module.device.view.IDeviceSettingView r1 = (com.ido.life.module.device.view.IDeviceSettingView) r1
            r1.onRestartStart()
        Lbf:
            com.ido.ble.BLEManager.syncAllData(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.device.presenter.DeviceSettingPresenter.preRestartDevice():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restart() {
        this.mTimeOutHandler.removeCallbacks(this.mSyncRunnable);
        this.mTimeOutHandler.removeCallbacks(this.mRestartRunnable);
        this.mTimeOutHandler.postDelayed(this.mRestartRunnable, 60000L);
        this.mIsSyncing = false;
        BLEManager.unregisterRebootCallBack(this.mRebootCallback);
        BLEManager.registerRebootCallBack(this.mRebootCallback);
        BLEManager.reBoot();
    }

    @Override // com.ido.life.base.BasePresenter
    public void deleteDevice() {
        this.mTimeOutHandler.postDelayed(this.mDeleteRunnable, 15000L);
        this.mDeviceInfo = getDeviceInfo();
        BLEManager.registerUnbindCallBack(this.mUnbindCallback);
        if (isConnected()) {
            BLEManager.unbind(this.mDeviceInfo.mDeviceAddress, new ICommonListener() { // from class: com.ido.life.module.device.presenter.DeviceSettingPresenter.5
                @Override // com.ido.ble.business.multidevice.ICommonListener
                public void onSuccess(String str) {
                    DeviceSettingPresenter.this.saveLog("unbind onSuccess ：" + str);
                    EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LIST_CHANGED, str));
                }

                @Override // com.ido.ble.business.multidevice.ICommonListener
                public void onFailed(String str) {
                    DeviceSettingPresenter.this.saveLog("unbind onFailed ：" + str);
                }
            });
        } else {
            BLEManager.forceUnbind(this.mDeviceInfo.mDeviceAddress);
            EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LIST_CHANGED, this.mDeviceInfo.mDeviceAddress));
        }
    }

    public void deviceFactoryReset() {
        this.mTimeOutHandler.postDelayed(this.mFactoryResetRunnable, 15000L);
        BLEManager.setRestoreFactory();
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        switch (settingType) {
            case MENU_LIST_SET:
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_QUICK_APPLICATION_SUCCESS, "", null);
                break;
            case UNIT:
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_DEVICE_LANG_SUCCESS, "", null);
                break;
            case RESTORE_FACTORY:
                this.mTimeOutHandler.removeCallbacks(this.mFactoryResetRunnable);
                saveLog("factoryReset onSuccess");
                BLEManager.forceUnbind();
                BLEDevice deviceInfo = getDeviceInfo();
                if (deviceInfo != null) {
                    EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_DEVICE_LIST_CHANGED, deviceInfo.mDeviceAddress));
                }
                SPHelper.removeDevice(new DeviceListEntity.DeviceInfo(deviceInfo));
                if (!BLEManager.isBind()) {
                    List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
                    if (!deviceList.isEmpty()) {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "DeviceSettingPresenter BLEManager.autoConnect");
                        String mac = deviceList.get(0).getMac();
                        BLEManager.autoConnect(mac);
                        ConnectLogHelper.saveLog("DeviceSettingPresenter", "autoConnect(".concat(mac).concat(")"));
                    }
                }
                if (isAttachView()) {
                    ((IDeviceSettingView) getView()).onResetSuccess();
                }
                break;
            case WEATHER_SWITCH:
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_WEATHER_SUCCESS, "", null);
                if (isWeatherSwitchOn()) {
                    WeatherHelper.requestWeatherFromServer();
                }
                break;
            case MUSIC_SWITCH:
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MUSIC_SUCCESS, "", null);
                if (isAttachView()) {
                    ((IDeviceSettingView) getView()).onMusicControlSwitched(LocalDataManager.getMusicSwitch());
                }
                break;
            case UP_HAND_GESTURE:
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_BRIGHTEN_THE_SCREEN_SUCCESS, "", null);
                break;
            case SCREEN_BRIGHTNESS:
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_AUTO_NIGHT_BRIGHTEN_THE_SCREEN_SUCCESS, "", null);
                getScreenLuminance();
                break;
            default:
                if (isAttachView()) {
                    ((IDeviceSettingView) getView()).onSetCmdSuccess(settingType);
                }
                break;
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView()) {
            int i = AnonymousClass7.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
            if (i == 1) {
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_QUICK_APPLICATION_FAILURE, "", null);
                ((IDeviceSettingView) getView()).onSetCmdFailed();
                return;
            }
            if (i == 2) {
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_DEVICE_LANG_FAILURE, "", null);
                ((IDeviceSettingView) getView()).onSetCmdFailed();
            } else if (i == 3) {
                saveLog("factoryReset onFailed");
                this.mTimeOutHandler.removeCallbacks(this.mFactoryResetRunnable);
                ((IDeviceSettingView) getView()).onResetFailed();
            } else if (i == 7) {
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_AUTO_NIGHT_BRIGHTEN_THE_SCREEN_FAILURE, "", null);
                ((IDeviceSettingView) getView()).onSetCmdFailed();
            } else {
                ((IDeviceSettingView) getView()).onSetCmdFailed(settingType);
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdFailed(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdFailed(settingType);
        if (settingType == OtherProtocolCallBack.SettingType.SPORT_MODE_SORT) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MANY_SPORTS_FAILURE, "", null);
            if (isAttachView()) {
                ((IDeviceSettingView) getView()).onSetCmdFailed();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdSuccess(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdSuccess(settingType);
        if (settingType == OtherProtocolCallBack.SettingType.SPORT_MODE_SORT) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MANY_SPORTS_SUCCESS, "", null);
        }
    }

    private List<Integer> getQuickAppItemList() {
        MenuList menuList = LocalDataManager.getMenuList();
        if (menuList != null && menuList.items != null) {
            return menuList.items;
        }
        return new ArrayList();
    }

    private void resetMusicControlSwitch() {
        List<Integer> quickAppItemList = getQuickAppItemList();
        if (isMusicControlSwitchOn() == quickAppItemList.contains(6)) {
            return;
        }
        BLEManager.setMusicSwitch(quickAppItemList.contains(6));
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerGetDeviceParaCallBack(this.mICallBack);
        BLEManager.registerDeviceParaChangedCallBack(this);
    }

    public boolean isDndModeSwitchOn() {
        NotDisturbPara notDisturbPara = LocalDataManager.getNotDisturbPara();
        if (getSupportFunctionInfo().ex_main3_get_do_not_disturb && isConnected()) {
            BLEManager.getDoNotDisturbPara();
        }
        return notDisturbPara != null && (notDisturbPara.onOFf == 170 || notDisturbPara.noontimeRestOnOff == 170);
    }

    public boolean isFindPhoneSwitchOn() {
        return LocalDataManager.getFindPhoneSwitch();
    }

    public boolean isMusicControlSwitchOn() {
        return LocalDataManager.getMusicSwitch();
    }

    public boolean isWristScreenSwitchOn() {
        UpHandGesture upHandGesture = LocalDataManager.getUpHandGesture();
        if (getSupportFunctionInfo().ex_table_main9_get_up_hand_gesture) {
            BLEManager.getUpHandGesture();
        }
        return upHandGesture != null && upHandGesture.onOff == 170;
    }

    public boolean isWeatherSwitchOn() {
        return LocalDataManager.getWeatherSwitch();
    }

    public void getScreenLuminance() {
        if (isAttachView()) {
            if (getSupportFunctionInfo().ex_table_main9_get_screen_brightness && isConnected()) {
                BLEManager.getScreenBrightness();
            } else {
                ((IDeviceSettingView) getView()).onGetScreenBrightness(LocalDataManager.getScreenBrigthnessConfig());
            }
        }
    }

    public void setFindPhoneSwitch(boolean z) {
        if (!z) {
            EventBusHelper.post(500);
        }
        BLEManager.setFindPhoneSwitch(z);
    }

    public void setMusicSwitch(boolean z) {
        BLEManager.setMusicSwitch(z);
    }

    public void setWristScreenSwitch(boolean z) {
        UpHandGesture upHandGesture = LocalDataManager.getUpHandGesture();
        if (upHandGesture == null) {
            upHandGesture = new UpHandGesture();
        }
        upHandGesture.onOff = z ? 170 : 85;
        BLEManager.setUpHandGesture(upHandGesture);
    }

    public void setWeatherSwitch(boolean z) {
        BLEManager.setWeatherSwitch(z);
    }

    private void resetQuickAppList() {
        if (getSupportFunctionInfo().ex_main7_menu_list) {
            List<Integer> quickAppItemList = getQuickAppItemList();
            if (isMusicControlSwitchOn()) {
                if (quickAppItemList.contains(6)) {
                    return;
                } else {
                    quickAppItemList.add(6);
                }
            } else {
                if (!quickAppItemList.contains(6)) {
                    return;
                }
                int i = 0;
                while (true) {
                    if (i >= quickAppItemList.size()) {
                        break;
                    }
                    if (quickAppItemList.get(i).intValue() == 6) {
                        quickAppItemList.remove(i);
                        break;
                    }
                    i++;
                }
            }
            MenuList menuList = new MenuList();
            menuList.items = quickAppItemList;
            BLEManager.setMenuList(menuList);
        }
    }

    @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
    public void onChanged(DeviceChangedPara deviceChangedPara) {
        if (deviceChangedPara == null || deviceChangedPara.doNotDisturb <= 0 || !getSupportFunctionInfo().ex_main3_get_do_not_disturb) {
            return;
        }
        BLEManager.getDoNotDisturbPara();
    }

    public int formatValue2Level(int i) {
        if (i > 100) {
            i = 100;
        }
        int i2 = 100 / (getSupportFunctionInfo().ex_table_main8_screen_brightness_3_level ? 3 : 5);
        return Math.max((i / i2) + ((i % i2) * 2 >= i2 ? 1 : 0), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "DeviceSettingPresenter", str);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        this.mTimeOutHandler.removeCallbacksAndMessages(null);
        BLEManager.unregisterUnbindCallBack(this.mUnbindCallback);
        BLEManager.unregisterGetDeviceParaCallBack(this.mICallBack);
        BLEManager.unregisterDeviceParaChangedCallBack(this);
    }
}