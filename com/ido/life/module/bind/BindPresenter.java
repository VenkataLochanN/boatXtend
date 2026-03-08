package com.ido.life.module.bind;

import android.os.Handler;
import android.text.TextUtils;
import com.ido.alexa.AlexaApi;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.AppBLEDevice;
import com.ido.life.ble.BaseBindCallback;
import com.ido.life.ble.BaseConnCallback;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.database.model.DeviceWhiteListEntity;
import com.ido.life.module.bind.IBindView;
import com.ido.life.module.main.MainPresenter;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import com.veryfit.multi.nativeprotocol.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class BindPresenter<T extends IBindView> extends BaseCmdPresenter<T> {
    private static final long DELAY_MILLIS = 1500;
    private static final long TIMEOUT_INPUT_AUTH_CODE = 45000;
    private static final long TIMEOUT_MILLIS_BIND = 30000;
    private static final long TIMEOUT_MILLIS_CONNECT = 40000;
    public boolean isBinding;
    private boolean mBindSuccess;
    protected BLEDevice mDevice;
    private int mDeviceShape;
    private boolean mIsSupportBindConfirm;
    private BLEDevice mLastBindDevice;
    private final Handler mHandler = new Handler();
    private boolean mNeedBind = true;
    private final Map<Integer, String> mWhiteListMap = new HashMap();
    private final BaseBindCallback mBindCallback = new BaseBindCallback() { // from class: com.ido.life.module.bind.BindPresenter.1
        @Override // com.ido.life.ble.BaseBindCallback, com.ido.ble.callback.BindCallBack.ICallBack
        public void onSuccess() {
            super.onSuccess();
            BindPresenter.this.bindSuccess();
        }

        @Override // com.ido.life.ble.BaseBindCallback, com.ido.ble.callback.BindCallBack.ICallBack
        public void onFailed(BindCallBack.BindFailedError bindFailedError) {
            super.onFailed(bindFailedError);
            BindPresenter.this.saveBindLog("bindDevice onFailed ：" + bindFailedError);
            BindPresenter.this.isBinding = false;
            if (bindFailedError == BindCallBack.BindFailedError.ERROR_DEVICE_ALREADY_IN_BIND_STATE) {
                BindPresenter.this.bindFailed(Constants.BindErrorCode.BIND_FAILED_DEVICE_ALREADY_IN_BIND_STATE, true);
            } else if (bindFailedError == BindCallBack.BindFailedError.ERROR_ENCRYPTED) {
                BindPresenter.this.bindFailed(Constants.BindErrorCode.BIND_FAILED_ERROR_ENCRYPTED, false);
            } else {
                BindPresenter.this.bindFailed(Constants.BindErrorCode.BIND_FAILED_ERROR_OTHER, false);
            }
        }

        @Override // com.ido.life.ble.BaseBindCallback, com.ido.ble.callback.BindCallBack.ICallBack
        public void onCancel() {
            super.onCancel();
            BindPresenter.this.saveBindLog("bindDevice onCancel");
            BindPresenter.this.bindFailed(Constants.BindErrorCode.BIND_FAILED_CANCELLED, false);
        }

        @Override // com.ido.life.ble.BaseBindCallback, com.ido.ble.callback.BindCallBack.ICallBack
        public void onReject() {
            super.onReject();
            BindPresenter.this.saveBindLog("bindDevice onReject");
            BindPresenter.this.bindFailed(Constants.BindErrorCode.BIND_FAILED_REJECTED, false);
        }

        @Override // com.ido.life.ble.BaseBindCallback, com.ido.ble.callback.BindCallBack.ICallBack
        public void onNeedAuth(int i) {
            super.onNeedAuth(i);
            BindPresenter.this.saveBindLog("bindDevice onNeedAuth");
            BindPresenter.this.mHandler.removeCallbacks(BindPresenter.this.mBindTimeoutRunnable);
            BindPresenter.this.mHandler.postDelayed(BindPresenter.this.mBindTimeoutRunnable, BindPresenter.TIMEOUT_INPUT_AUTH_CODE);
            if (BindPresenter.this.isAttachView()) {
                ((IBindView) BindPresenter.this.getView()).onNeedAuthCode(i);
            }
        }
    };
    private final BaseDeviceInfoCallback mDeviceInfoCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.bind.BindPresenter.4
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetBasicInfo(BasicInfo basicInfo) {
            super.onGetBasicInfo(basicInfo);
            if (basicInfo == null) {
                BindPresenter.this.mHandler.removeCallbacks(BindPresenter.this.mDeviceInfoTimeoutRunnable);
                BindPresenter bindPresenter = BindPresenter.this;
                bindPresenter.isBinding = false;
                bindPresenter.saveBindLog("onGetBasicInfo basicInfo null，errorCode ：412");
                if (BindPresenter.this.isAttachView()) {
                    ((IBindView) BindPresenter.this.getView()).onConnectFailed(412);
                    return;
                }
                return;
            }
            BindPresenter.this.saveBindLog("onGetBasicInfo success ：" + basicInfo.toString());
            if (basicInfo.dev_type == 0 && !BindPresenter.this.isDeviceSupported(basicInfo.deivceId, BindPresenter.this.mDevice.mDeviceName)) {
                BindPresenter.this.saveBindLog("onGetBasicInfo success , need connect by VeryFitPro");
                BindPresenter.this.unregisterCallback();
                BindPresenter.this.mHandler.removeCallbacks(BindPresenter.this.mDeviceInfoTimeoutRunnable);
                if (BindPresenter.this.isAttachView()) {
                    BindPresenter.this.bindFailed(Constants.BindErrorCode.BIND_FAILED_ERROR_OTHER, false);
                    ((IBindView) BindPresenter.this.getView()).onBindWrongDevice(BindPresenter.this.mDevice);
                    return;
                }
                return;
            }
            BindPresenter.this.mIsSupportBindConfirm = basicInfo.show_bind_choice_ui != 0;
            BindPresenter.this.mDeviceShape = basicInfo.shape;
            BindPresenter.this.saveBindLog("getFunctionTables start...");
            BLEManager.getFunctionTables();
        }

        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
            super.onGetFunctionTable(supportFunctionInfo);
            BindPresenter.this.mHandler.removeCallbacks(BindPresenter.this.mDeviceInfoTimeoutRunnable);
            if (supportFunctionInfo == null) {
                BindPresenter bindPresenter = BindPresenter.this;
                bindPresenter.isBinding = false;
                bindPresenter.saveBindLog("onGetFunctionTable supportFunctionInfo null ，errorCode ：413");
                if (BindPresenter.this.isAttachView()) {
                    ((IBindView) BindPresenter.this.getView()).onConnectFailed(413);
                    return;
                }
                return;
            }
            BindPresenter.this.saveBindLog("onGetFunctionTable success ：" + supportFunctionInfo.toString());
            BLEManager.unregisterGetDeviceInfoCallBack(BindPresenter.this.mDeviceInfoCallback);
            if (BindPresenter.this.isAttachView()) {
                ((IBindView) BindPresenter.this.getView()).onGetDeviceInfoSuccess();
            }
        }
    };
    private final BaseConnCallback mConnCallback = new BaseConnCallback() { // from class: com.ido.life.module.bind.BindPresenter.5
        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
            super.onConnectStart(str);
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            BindPresenter.this.saveBindLog("onConnectSuccess");
            BLEManager.unregisterConnectCallBack(BindPresenter.this.mConnCallback);
            BindPresenter.this.mHandler.removeCallbacks(BindPresenter.this.mConnectTimeoutRunnable);
            BindPresenter.this.mHandler.postDelayed(BindPresenter.this.mConnectSuccessRunnable, BindPresenter.DELAY_MILLIS);
            if (BindPresenter.this.mNeedBind || BindPresenter.this.mDevice.mDeviceId == BindPresenter.this.mLastBindDevice.mDeviceId) {
                return;
            }
            BindPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.bind.BindPresenter.5.1
                @Override // java.lang.Runnable
                public void run() {
                    AlexaApi.switchDeviceForAlexa();
                }
            });
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            int i;
            super.onConnectFailed(connectFailedReason, str);
            BindPresenter bindPresenter = BindPresenter.this;
            bindPresenter.isBinding = false;
            BLEManager.unregisterConnectCallBack(bindPresenter.mConnCallback);
            BindPresenter.this.mHandler.removeCallbacks(BindPresenter.this.mConnectTimeoutRunnable);
            if (BindPresenter.this.isAttachView()) {
                switch (AnonymousClass6.$SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[connectFailedReason.ordinal()]) {
                    case 1:
                        i = 401;
                        break;
                    case 2:
                        i = 402;
                        break;
                    case 3:
                        i = 403;
                        break;
                    case 4:
                        i = 404;
                        break;
                    case 5:
                        i = 405;
                        break;
                    case 6:
                        i = Constants.BindErrorCode.CONNECTED_FAILED_DISCOVER_SERVICE_FAILED;
                        break;
                    case 7:
                        i = 407;
                        break;
                    case 8:
                        i = 408;
                        break;
                    default:
                        i = Constants.BindErrorCode.CONNECTED_FAILED_ERROR_OTHER;
                        break;
                }
                BindPresenter.this.saveBindLog("onConnectFailed errorCode ：" + i);
                ((IBindView) BindPresenter.this.getView()).onConnectFailed(i);
            }
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            super.onInDfuMode(bLEDevice);
            BindPresenter.this.saveBindLog("onInDfuMode ：" + bLEDevice.toString());
            BindPresenter bindPresenter = BindPresenter.this;
            bindPresenter.isBinding = false;
            BLEManager.unregisterConnectCallBack(bindPresenter.mConnCallback);
            BindPresenter.this.mHandler.removeCallbacks(BindPresenter.this.mConnectTimeoutRunnable);
            if (BindPresenter.this.isAttachView()) {
                ((IBindView) BindPresenter.this.getView()).onConnectFailed(404);
                ((IBindView) BindPresenter.this.getView()).onInDfuMode(bLEDevice);
            }
        }
    };
    private final Runnable mConnectTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.bind.-$$Lambda$BindPresenter$tpuUQmrSVnVUb9Y91lfL3wkQDtI
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$BindPresenter();
        }
    };
    private final Runnable mDeviceInfoTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.bind.-$$Lambda$BindPresenter$PCKRKT4hQaCa14jE3H4BJOFy00c
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$2$BindPresenter();
        }
    };
    private final Runnable mBindTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.bind.-$$Lambda$BindPresenter$JylcRuwuLXq_xM7poTiX3J-4b1w
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$3$BindPresenter();
        }
    };
    private final Runnable mConnectSuccessRunnable = new Runnable() { // from class: com.ido.life.module.bind.-$$Lambda$BindPresenter$ACIXugk1aRFqFN2A4VWXe46Y7Dk
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$4$BindPresenter();
        }
    };

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        initDeviceWhiteList();
    }

    public void saveUserDevice(BLEDevice bLEDevice) {
        SPHelper.saveUserDevice(bLEDevice);
    }

    private void initDeviceWhiteList() {
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
                    this.mWhiteListMap.put(Integer.valueOf(Integer.parseInt(deviceInfo.getDeviceId())), deviceInfo.getBluetoothName());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public boolean isDeviceSupported(int i, String str) {
        if (this.mWhiteListMap.containsKey(Integer.valueOf(i))) {
            return true;
        }
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

    public void disconnect() {
        if (BLEManager.isConnected()) {
            BLEManager.disConnect();
        }
    }

    public void connectDevice(DeviceListEntity.DeviceInfo deviceInfo) {
        BLEDevice bLEDevice = new BLEDevice();
        bLEDevice.mDeviceName = deviceInfo.getDeviceName();
        bLEDevice.mDeviceAddress = deviceInfo.getMac();
        this.mNeedBind = false;
        connectDevice(bLEDevice, false);
    }

    public void connectDevice(final BLEDevice bLEDevice, boolean z) {
        saveBindLog("connectDevice ：" + bLEDevice.toString());
        if (isBind()) {
            this.mLastBindDevice = getDeviceInfo();
        } else {
            this.mLastBindDevice = new BLEDevice();
        }
        this.mDevice = bLEDevice;
        this.mBindSuccess = false;
        saveBindLog("connectDevice isBleEnable ：" + isBleEnable());
        if (isBleEnable()) {
            this.isBinding = true;
            BLEManager.registerConnectCallBack(this.mConnCallback);
            saveBindLog("onConnectStart");
            String str = bLEDevice.mDeviceAddress;
            this.mHandler.removeCallbacks(this.mConnectTimeoutRunnable);
            this.mHandler.postDelayed(this.mConnectTimeoutRunnable, TIMEOUT_MILLIS_CONNECT);
            if (isAttachView()) {
                ((IBindView) getView()).onConnectStart(this.mDevice);
            }
            if (this.mNeedBind) {
                if (TextUtils.isEmpty(bLEDevice.mDeviceName) || z || bLEDevice.type == -1) {
                    BLEManager.scanAndConnect(str);
                    return;
                } else {
                    BLEManager.disConnect(new Runnable() { // from class: com.ido.life.module.bind.-$$Lambda$BindPresenter$h0n16APbFUt4rOOOyqjNGn6HxwI
                        @Override // java.lang.Runnable
                        public final void run() {
                            BLEManager.connect(bLEDevice);
                        }
                    });
                    return;
                }
            }
            if (!str.equals(this.mLastBindDevice.mDeviceAddress)) {
                resetCacheData();
            }
            saveBindLog("BindPresenter BLEManager.autoConnect");
            BLEManager.autoConnect(str);
            ConnectLogHelper.saveLog("BindPresenter", "autoConnect(".concat(str).concat(")"));
            return;
        }
        saveBindLog("connectDevice onNeedOpenBle");
        if (isAttachView()) {
            ((IBindView) getView()).onNeedOpenBle();
        }
    }

    private void resetCacheData() {
        SPHelper.saveLastOtaReminderDate("");
        SPHelper.saveLastFlashReminderDate("");
        SPHelper.saveAgpsOnlineUpgradeTime(0L);
        SPHelper.saveAgpsOfflineUpgradeTime(0L);
        SPHelper.saveBatteryLogTimestamp(0L);
        MainPresenter.needShowWrongBindDialog = true;
    }

    public void getBasicInfoFromDevice() {
        saveBindLog("getBasicInfoFromDevice start...");
        this.isBinding = true;
        this.mHandler.removeCallbacks(this.mDeviceInfoTimeoutRunnable);
        this.mHandler.postDelayed(this.mDeviceInfoTimeoutRunnable, TIMEOUT_MILLIS_CONNECT);
        BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        BLEManager.getBasicInfo();
    }

    public void bindDevice() {
        saveBindLog("bindDevice start...");
        if (BLEManager.isConnected()) {
            this.mHandler.postDelayed(this.mBindTimeoutRunnable, 30000L);
            BLEManager.registerBindCallBack(this.mBindCallback);
            saveBindLog("bindDevice mIsSupportBindConfirm ：" + this.mIsSupportBindConfirm);
            if (this.mIsSupportBindConfirm && isAttachView()) {
                ((IBindView) getView()).onNeedConfirm(this.mDeviceShape, this.mDevice.mDeviceAddress);
            }
            BLEManager.bindWithNoRetry();
            return;
        }
        connectDevice(this.mDevice, false);
    }

    public boolean isBindSuccess() {
        return this.mBindSuccess && BLEManager.isConnected();
    }

    public void clearBTpairedInfo() {
        String btMacAddress = BLEManager.getBtMacAddress();
        if (TextUtils.isEmpty(btMacAddress)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "SearchPresenter", "clearBTpairedInfo :" + btMacAddress);
        BLEManager.clearBtMacAddress();
        BLEManager.removeBondStatusFromPhoneBluetoothPairedList(btMacAddress);
    }

    public void sendAuthCode2Device(int[] iArr) {
        if (!isConnected()) {
            ((IBindView) getView()).onBindFailed(Constants.BindErrorCode.BIND_FAILED_DISCONNECT, false);
        } else {
            BLEManager.setBindAuth(iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindSuccess() {
        MainPresenter.needShowWrongBindDialog = true;
        saveBindLog("bindDevice onSuccess..." + this.mDevice.toString());
        saveBindLog("------------------------------------------------------------------");
        this.mHandler.removeCallbacks(this.mBindTimeoutRunnable);
        AlexaApi.switchDeviceForAlexa();
        this.isBinding = false;
        initConfig();
        saveDevice(this.mDevice);
        getDialImageAspectRatio();
        EventBusHelper.post(103);
        this.mBindSuccess = true;
        BLEManager.unregisterBindCallBack(this.mBindCallback);
        uploadDevice();
        if (isAttachView()) {
            ((IBindView) getView()).onBindSuccess();
        }
        EventBusHelper.postSticky(new BaseMessage(Constants.EventConstants.EVENT_REQUEST_OTA_INFO, this.mDevice.mDeviceAddress));
    }

    private void uploadDevice() {
        final AppBLEDevice appBleDevice = getAppBleDevice();
        SPHelper.saveActivatedDevice(appBleDevice);
        DeviceManager.uploadDevice(appBleDevice.mDeviceId, appBleDevice.mDeviceName, appBleDevice.getDeviceThirdVersion(), appBleDevice.mDeviceAddress, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.bind.BindPresenter.2
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                if (bool.booleanValue()) {
                    SPHelper.removeActivatedDevice(appBleDevice);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.d("uploadDevice onFailed message = " + str);
            }
        });
    }

    private void requestFirmwareInfo() {
        if (isBindAndConnected()) {
            final AppBLEDevice appBleDevice = getAppBleDevice();
            DeviceManager.checkFirmwareInfo(appBleDevice, new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.bind.BindPresenter.3
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                    if (BindPresenter.this.isBindAndConnected() && otaInfo != null) {
                        try {
                            if (Integer.valueOf(otaInfo.getVersion()).intValue() > appBleDevice.version) {
                                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_CHECKED_NEW_FIRMWARE, otaInfo));
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str) {
                    CommonLogUtil.printAndSave("checkFirmwareInfo onFailed ：" + str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindFailed(int i, boolean z) {
        saveBindLog("bindDevice onFailed ，errorCode ：" + i);
        this.isBinding = false;
        if (BLEManager.isConnected()) {
            BLEManager.disConnect();
        }
        BLEManager.unregisterBindCallBack(this.mBindCallback);
        this.mHandler.removeCallbacks(this.mBindTimeoutRunnable);
        if (isAttachView()) {
            ((IBindView) getView()).onBindFailed(i, z);
        }
    }

    private void saveDevice(BLEDevice bLEDevice) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            if (basicInfo.dev_type == 1) {
                bLEDevice.type = 3;
            } else {
                bLEDevice.type = 4;
            }
            if (bLEDevice.mDeviceId <= 0) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "bindSuccess-DeviceId异常，device.mDeviceId=" + bLEDevice.mDeviceId + " ,basicInfo.deivceId=" + basicInfo.deivceId);
                bLEDevice.mDeviceId = basicInfo.deivceId;
            }
        } else if (bLEDevice.type == 2) {
            bLEDevice.type = 3;
        } else {
            bLEDevice.type = 4;
        }
        SPHelper.saveDevice(new DeviceListEntity.DeviceInfo(bLEDevice));
    }

    private void initConfig() {
        SPHelper.setBindNewDevice(true);
        resetCacheData();
        DeviceConfigHelper.setUnitsFlowSystem(false);
        DeviceConfigHelper.initHeartRateMode();
        DeviceConfigHelper.initPressureMode();
        DeviceConfigHelper.initDNDMode();
        DeviceConfigHelper.initNightLight();
        DeviceConfigHelper.initWalkReminder();
        DeviceConfigHelper.initMotionType();
        DeviceConfigHelper.initWaterClock();
        DeviceConfigHelper.initMotionRecognition();
        DeviceConfigHelper.initMusicControl();
        DeviceConfigHelper.initFindPhone();
        DeviceConfigHelper.initMenuLists();
        DeviceConfigHelper.initUpHandGesture();
        DeviceConfigHelper.initGoalValue();
        DeviceConfigHelper.initWeatherForecastSwitch();
        DeviceConfigHelper.initMenstrualCycle();
        DeviceConfigHelper.initBloodOxygenDetection();
        DeviceConfigHelper.initScienceSleep();
        DeviceConfigHelper.initNoiseMonitoring();
    }

    /* JADX INFO: renamed from: com.ido.life.module.bind.BindPresenter$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason = new int[ConnectFailedReason.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[ConnectFailedReason.MAC_ADDRESS_INVALID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[ConnectFailedReason.NOT_IN_BIND_STATUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[ConnectFailedReason.BLUETOOTH_SWITCH_CLOSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[ConnectFailedReason.IN_UPGRADING_STATUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[ConnectFailedReason.SYSTEM_GATT_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[ConnectFailedReason.DISCOVER_SERVICE_FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[ConnectFailedReason.ENABLE_NOTIFY_FAILED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$ido$ble$bluetooth$connect$ConnectFailedReason[ConnectFailedReason.ENCRYPTED_FAILED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public /* synthetic */ void lambda$new$1$BindPresenter() {
        unregisterCallback();
        BLEManager.disConnect();
        if (isAttachView()) {
            ((IBindView) getView()).onConnectFailed(410);
        }
        saveBindLog("onConnectFailed timeout，errorCode ：410");
    }

    public /* synthetic */ void lambda$new$2$BindPresenter() {
        saveBindLog("getBasicInfoFromDevice timeout errorCode ：411");
        unregisterCallback();
        if (BLEManager.isConnected()) {
            BLEManager.disConnect();
        }
        if (isAttachView()) {
            ((IBindView) getView()).onConnectFailed(411);
        }
    }

    public /* synthetic */ void lambda$new$3$BindPresenter() {
        saveBindLog("bindDevice timeout errorCode ：414");
        unregisterCallback();
        if (BLEManager.isConnected()) {
            BLEManager.disConnect();
        }
        if (isAttachView()) {
            ((IBindView) getView()).onBindTimeOut(414);
        }
    }

    public /* synthetic */ void lambda$new$4$BindPresenter() {
        if (isAttachView()) {
            if (this.mDevice.mDeviceAddress.equals(this.mLastBindDevice.mDeviceAddress)) {
                this.isBinding = false;
            }
            if (!this.mNeedBind) {
                this.isBinding = false;
                SPHelper.setConfigSynced(false);
                EventBusHelper.post(103);
            }
            ((IBindView) getView()).onConnectSuccess(this.mNeedBind);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterCallback() {
        this.isBinding = false;
        BLEManager.unregisterConnectCallBack(this.mConnCallback);
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        BLEManager.unregisterBindCallBack(this.mBindCallback);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        unregisterCallback();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void saveBindLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "bindLog", str);
    }
}