package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceUpgradeEventListener;
import com.ido.ble.dfu.BleDFUConfig;
import com.ido.ble.dfu.BleDFUState;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.base.BaseMessage;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.DownloadManager;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.api.entity.RemoteLanguage;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IDeviceUpgradeNewView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.transmitter.FileTransmitter;
import com.ido.life.transmitter.IconTransmitterListener;
import com.ido.life.transmitter.task.FirewareAPOTransferTask;
import com.ido.life.transmitter.task.FlashTransferTask;
import com.ido.life.transmitter.task.SystemFileTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.DeviceUtil;
import com.ido.life.util.FileUtil;
import com.ido.life.util.RemoteLanguageHelper;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SPUtils;
import com.ido.life.util.ZipUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeNewPresenter extends BaseCmdPresenter<IDeviceUpgradeNewView> implements DownloadManager.DownloadListener, DeviceUpgradeEventListener.IListener {
    private static final String FLASH_FILE_SUFFIX = ".fzbin";
    public static String HAD_RESET_ALEXA = "had_reset_alexa";
    private static final int OTA_LOWEST_POWER = 30;
    private static final String SPEC_NAME_BIN = ".bin";
    private static final String SPEC_NAME_FW = ".fw";
    private static final String SPEC_NAME_ZIP = ".zip";
    private static final String SYSTEM_FILE_SUFFIX = ".bt";
    public static final int TYPE_BASIC_INFO = 300;
    private static final int TYPE_DETECT_FIRMWARE = 1;
    public static final int TYPE_LOWEST_POWER = 400;
    public static final int UPGRADE_STATUS_DOWNLOAD_START = 0;
    public static final int UPGRADE_STATUS_DOWNLOAD_SUCCESS = 1;
    public static final int UPGRADE_STATUS_FAILED = 5;
    private static final int UPGRADE_STATUS_FIRMWARE_CHANGE_BRICK = 6;
    public static final int UPGRADE_STATUS_START = 2;
    public static final int UPGRADE_STATUS_SUCCESS = 4;
    public static final int UPGRADE_STATUS_UPGRADING = 3;
    private boolean isComeFromDfuMode;
    private boolean isHuiDingPlatform;
    public boolean isUpgradeSuccess;
    private boolean isUpgrading;
    public FirmwareAndBt3Version mCurrentFirmwareAndBt3Version;
    public FlashBinInfo mCurrentFlashInfo;
    public CanDownLangInfoV3.Item mCurrentLanguageInfo;
    private String mCurrentOtaVersion;
    private BLEDevice mDevice;
    private String mDeviceAddress;
    private int mDeviceId;
    private int mDownloadType;
    private String mFirmwareFilePath;
    public OtaEntity.OtaInfo mFirmwareInfo;
    private String mFlashFilePath;
    private OtaEntity.OtaInfo mFlashInfo;
    private RemoteLanguage.LanguageInfo mLanguageInfo;
    private int mLastProgress;
    private String mModuleSystemFilePath;
    private OtaEntity.OtaInfo mModuleSystemInfo;
    private boolean mNeedDetectFlashInfo;
    private boolean mNeedDetectLanguageInfo;
    private boolean mNeedDetectSystemConstituentInfo;
    private File mOtaDirFile;
    public int mOtaType;
    public int mode;
    private boolean needUpdateUpgradeStatus;
    private String otaFileDirPath;
    private OtaEntity.OtaInfo.FlashInfo otaFont;
    private OtaEntity.OtaInfo.SystemConstituentInfo otaModuleSystem;
    private boolean sendBasicInfoCmd;
    private String suffix;
    public boolean isInDfuMode = false;
    private final Handler mHandler = new Handler();
    private boolean isNotApplo = false;
    private final BaseDeviceInfoCallback mDeviceInfoCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.1
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetBasicInfo(BasicInfo basicInfo) {
            super.onGetBasicInfo(basicInfo);
            if (DeviceUpgradeNewPresenter.this.sendBasicInfoCmd) {
                DeviceUpgradeNewPresenter.this.mHandler.removeCallbacks(DeviceUpgradeNewPresenter.this.mBasicInfoTimeoutRunnable);
                DeviceUpgradeNewPresenter.this.sendBasicInfoCmd = false;
                if (basicInfo == null) {
                    DeviceUpgradeNewPresenter.this.isUpgrading = false;
                    if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                        DeviceUpgradeNewPresenter.this.saveOtaLog("basicInfo == null");
                        return;
                    }
                    return;
                }
                DeviceUpgradeNewPresenter.this.saveOtaLog("get power = " + basicInfo.energe);
                if (basicInfo.energe <= 30) {
                    DeviceUpgradeNewPresenter.this.isUpgrading = false;
                    if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                        DeviceUpgradeNewPresenter.this.saveOtaLog("get power = " + basicInfo.energe);
                        ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onUpgradeFailed(400);
                        return;
                    }
                    return;
                }
                if (DeviceUpgradeNewPresenter.this.mOtaType == 1) {
                    DeviceUpgradeNewPresenter.this.upgradeFirmware();
                    return;
                } else if (DeviceUpgradeNewPresenter.this.mOtaType == 2) {
                    DeviceUpgradeNewPresenter.this.upgradeFlash();
                    return;
                } else {
                    if (DeviceUpgradeNewPresenter.this.mOtaType == 4) {
                        DeviceUpgradeNewPresenter.this.upgradeSystemContuent();
                        return;
                    }
                    return;
                }
            }
            DeviceUpgradeNewPresenter.this.saveOtaLog("不是通过点击“升级”获取到的设备信息，不处理");
        }

        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFlashBinInfo(FlashBinInfo flashBinInfo) {
            super.onGetFlashBinInfo(flashBinInfo);
            DeviceUpgradeNewPresenter.this.saveOtaLog("onGetFlashBinInfo 接受数据 ");
            DeviceUpgradeNewPresenter.this.mHandler.removeCallbacks(DeviceUpgradeNewPresenter.this.mGetFlashTimeoutRunnable);
            BLEManager.unregisterGetDeviceInfoCallBack(DeviceUpgradeNewPresenter.this.mDeviceInfoCallback);
            if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                if (flashBinInfo == null) {
                    DeviceUpgradeNewPresenter.this.saveOtaLog("onGetFlashBinInfo null   ;mNeedDetectFlashInfo:" + DeviceUpgradeNewPresenter.this.mNeedDetectFlashInfo);
                    if (DeviceUpgradeNewPresenter.this.mNeedDetectFlashInfo) {
                        ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionFailed();
                        DeviceUpgradeNewPresenter.this.mNeedDetectFlashInfo = false;
                        return;
                    }
                    return;
                }
                DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
                deviceUpgradeNewPresenter.mCurrentFlashInfo = flashBinInfo;
                deviceUpgradeNewPresenter.saveOtaLog("onGetFlashBinInfo = " + flashBinInfo.toString());
                ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onGetCurrentFlashVersion(String.valueOf(flashBinInfo.version));
                if (DeviceUpgradeNewPresenter.this.mNeedDetectFlashInfo) {
                    DeviceUpgradeNewPresenter.this.detectFlashInfo();
                    DeviceUpgradeNewPresenter.this.mNeedDetectFlashInfo = false;
                }
            }
        }
    };
    private final Runnable mBasicInfoTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$7dMuxH4WBClZ9ESUIgW0r-35p5A
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$DeviceUpgradeNewPresenter();
        }
    };
    private final Runnable mGetFlashTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$DqFC23TAhHonIDKryJwPgiwio4E
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$DeviceUpgradeNewPresenter();
        }
    };
    private final BaseDeviceInfoCallback mLanguageCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.2
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetCanDownloadLangInfoV3(CanDownLangInfoV3 canDownLangInfoV3) {
            super.onGetCanDownloadLangInfoV3(canDownLangInfoV3);
            DeviceUpgradeNewPresenter.this.saveOtaLog("onGetCanDownloadLangInfoV3：" + canDownLangInfoV3);
            DeviceUpgradeNewPresenter.this.mHandler.removeCallbacks(DeviceUpgradeNewPresenter.this.mGetLanguageTimeoutRunnable);
            BLEManager.unregisterGetDeviceInfoCallBack(DeviceUpgradeNewPresenter.this.mLanguageCallback);
            DeviceUpgradeNewPresenter.this.getCurrentLanguageVersion(canDownLangInfoV3);
        }
    };
    private final Runnable mGetLanguageTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$QPkieL2fZTWzv6V1HdnrVXNyl8Y
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$2$DeviceUpgradeNewPresenter();
        }
    };
    private final BaseDeviceParaCallBack mSystemConstituentCallback = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.3
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetFirmwareAndBt3Version(FirmwareAndBt3Version firmwareAndBt3Version) {
            super.onGetFirmwareAndBt3Version(firmwareAndBt3Version);
            DeviceUpgradeNewPresenter.this.saveOtaLog("onGetCanDownloadLangInfoV3：");
            DeviceUpgradeNewPresenter.this.mHandler.removeCallbacks(DeviceUpgradeNewPresenter.this.mGetSystemConstituentTimeoutRunnable);
            BLEManager.unregisterGetDeviceParaCallBack(DeviceUpgradeNewPresenter.this.mSystemConstituentCallback);
            DeviceUpgradeNewPresenter.this.getCurrentSystemConstituentVersion(firmwareAndBt3Version);
        }
    };
    private final Runnable mGetSystemConstituentTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$ZiCt9IGJFuQ1MuidxCbugXKlWdA
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$3$DeviceUpgradeNewPresenter();
        }
    };
    private final IconTransmitterListener mFileTransferListener = new IconTransmitterListener() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.4
        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferShutdown(List<TransferTask> list) {
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferFailed(int i, int i2) {
            if (i == 9) {
                if (DeviceUpgradeNewPresenter.this.mOtaType == 1) {
                    DeviceUpgradeNewPresenter.this.upgradeFailed();
                } else if (DeviceUpgradeNewPresenter.this.mOtaType == 2) {
                    DeviceUpgradeNewPresenter.this.upgradeFailed();
                } else if (DeviceUpgradeNewPresenter.this.mOtaType == 4) {
                    DeviceUpgradeNewPresenter.this.upgradeFailed();
                }
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferSuccess(int i, int i2) {
            if (i == 9) {
                if (DeviceUpgradeNewPresenter.this.mOtaType == 1) {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter.upgradeSuccess(deviceUpgradeNewPresenter.mFirmwareInfo);
                } else if (DeviceUpgradeNewPresenter.this.mOtaType == 2) {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter2 = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter2.upgradeSuccess(deviceUpgradeNewPresenter2.mFlashInfo);
                } else if (DeviceUpgradeNewPresenter.this.mOtaType == 4) {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter3 = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter3.upgradeSuccess(deviceUpgradeNewPresenter3.mModuleSystemInfo);
                } else {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter4 = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter4.upgradeSuccess(deviceUpgradeNewPresenter4.mLanguageInfo);
                }
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferProgress(int i, int i2, int i3) {
            if (i == 9) {
                if (DeviceUpgradeNewPresenter.this.mOtaType == 1) {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter.updateOtaInProgressLog(deviceUpgradeNewPresenter.mFirmwareInfo, i3);
                    DeviceUpgradeNewPresenter.this.upgradeProgress(i3);
                } else if (DeviceUpgradeNewPresenter.this.mOtaType == 2) {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter2 = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter2.updateOtaInProgressLog(deviceUpgradeNewPresenter2.mFlashInfo, i3);
                    DeviceUpgradeNewPresenter.this.upgradeProgress(i3);
                } else if (DeviceUpgradeNewPresenter.this.mOtaType == 4) {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter3 = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter3.updateOtaInProgressLog(deviceUpgradeNewPresenter3.mModuleSystemInfo, i3);
                    DeviceUpgradeNewPresenter.this.upgradeProgress(i3);
                } else {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter4 = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter4.upgradeProgress(deviceUpgradeNewPresenter4.mLanguageInfo, i3);
                }
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferStart(int i, int i2) {
            if (i == 9) {
                if (DeviceUpgradeNewPresenter.this.mOtaType == 1) {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter.upgradeStart(deviceUpgradeNewPresenter.mFirmwareInfo);
                } else if (DeviceUpgradeNewPresenter.this.mOtaType == 2) {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter2 = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter2.upgradeStart(deviceUpgradeNewPresenter2.mFlashInfo);
                } else {
                    DeviceUpgradeNewPresenter deviceUpgradeNewPresenter3 = DeviceUpgradeNewPresenter.this;
                    deviceUpgradeNewPresenter3.upgradeStart(deviceUpgradeNewPresenter3.mLanguageInfo);
                }
            }
        }
    };
    private final BleDFUState.IListener mDFUStateListener = new BleDFUState.IListener() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.5
        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onPrepare() {
            DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
            deviceUpgradeNewPresenter.upgradeStart(deviceUpgradeNewPresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onDeviceInDFUMode() {
            DeviceUpgradeNewPresenter.this.saveOtaLog("onDeviceInDFUMode");
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onProgress(int i) {
            DeviceUpgradeNewPresenter.this.upgradeProgress(i);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onSuccess() {
            DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
            deviceUpgradeNewPresenter.upgradeSuccess(deviceUpgradeNewPresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onSuccessAndNeedToPromptUser() {
            DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
            deviceUpgradeNewPresenter.upgradeSuccess(deviceUpgradeNewPresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onFailed(BleDFUState.FailReason failReason) {
            DeviceUpgradeNewPresenter.this.saveOtaLog("BleDFUState.FailReason = " + failReason.toString());
            DeviceUpgradeNewPresenter.this.upgradeFailed();
            DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
            deviceUpgradeNewPresenter.updateOtaLog(deviceUpgradeNewPresenter.mFirmwareInfo, 5, DeviceUpgradeNewPresenter.this.formFailReason2ErrorCode(failReason));
            if (failReason == BleDFUState.FailReason.DEVICE_IN_LOW_BATTERY && DeviceUpgradeNewPresenter.this.isAttachView()) {
                ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onUpgradeFailed(400);
            }
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onCanceled() {
            DeviceUpgradeNewPresenter.this.saveOtaLog("mDFUStateListener onCanceled");
            DeviceUpgradeNewPresenter.this.upgradeFailed();
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onRetry(int i) {
            DeviceUpgradeNewPresenter.this.saveOtaLog("onClickRetry = " + i);
        }
    };
    private Runnable getFlashTimeOut = new Runnable() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.14
        @Override // java.lang.Runnable
        public void run() {
            DeviceUpgradeNewPresenter.this.saveOtaLog("getflashinfo ：超时le");
        }
    };

    public /* synthetic */ void lambda$new$0$DeviceUpgradeNewPresenter() {
        if (isAttachView()) {
            BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
            BasicInfo basicInfo = LocalDataManager.getBasicInfo();
            if (basicInfo == null) {
                saveOtaLog("mBasicInfoTimeoutRunnable basicInfo is null");
                this.isUpgrading = false;
                return;
            }
            saveOtaLog("mBasicInfoTimeoutRunnable power :" + basicInfo.energe);
            if (basicInfo.energe > 30) {
                int i = this.mOtaType;
                if (i == 1) {
                    upgradeFirmware();
                    return;
                } else if (i == 2) {
                    upgradeFlash();
                    return;
                } else {
                    if (i == 4) {
                        upgradeSystemContuent();
                        return;
                    }
                    return;
                }
            }
            saveOtaLog("mBasicInfoTimeoutRunnable power :" + basicInfo.energe);
            this.isUpgrading = false;
        }
    }

    public /* synthetic */ void lambda$new$1$DeviceUpgradeNewPresenter() {
        saveOtaLog("onGetFlashBinInfo 超时...");
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
            this.mNeedDetectFlashInfo = false;
        }
    }

    public /* synthetic */ void lambda$new$2$DeviceUpgradeNewPresenter() {
        saveOtaLog("getLanguageInfo timeout");
        BLEManager.unregisterGetDeviceInfoCallBack(this.mLanguageCallback);
        if (isAttachView() && this.mNeedDetectLanguageInfo) {
            ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
            this.mNeedDetectLanguageInfo = false;
        }
    }

    public /* synthetic */ void lambda$new$3$DeviceUpgradeNewPresenter() {
        saveOtaLog("mGetSystemConstituentTimeoutRunnable timeout");
        BLEManager.unregisterGetDeviceParaCallBack(this.mSystemConstituentCallback);
        if (isAttachView() && this.mNeedDetectSystemConstituentInfo) {
            ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
            this.mNeedDetectSystemConstituentInfo = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int formFailReason2ErrorCode(BleDFUState.FailReason failReason) {
        switch (failReason) {
            case ENTER_DFU_MODE_FAILED:
                return 5;
            case NOT_FIND_TARGET_DEVICE:
                return 6;
            case CONFIG_PARAS_ERROR:
                return 4;
            case FILE_ERROR:
                return 8;
            case PHONE_BLUETOOTH_ERROR:
                return 7;
            case DEVICE_IN_LOW_BATTERY:
                return 2;
            default:
                return 15;
        }
    }

    @Override // com.ido.ble.callback.DeviceUpgradeEventListener.IListener
    public void NODIC_onProgress(int i) {
        this.isInDfuMode = true;
        updateOtaInProgressLog(this.mFirmwareInfo, i);
    }

    @Override // com.ido.ble.callback.DeviceUpgradeEventListener.IListener
    public void APOLLO_onSOLibError(int i) {
        int i2 = this.mOtaType;
        if (i2 == 1) {
            updateOtaLog(this.mFirmwareInfo, 5, 15);
        } else if (i2 == 2) {
            updateOtaLog(this.mFlashInfo, 5, 15);
        } else if (i2 == 4) {
            updateOtaLog(this.mModuleSystemInfo, 5, 15);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOtaInProgressLog(OtaEntity.OtaInfo otaInfo, int i) {
        if (i <= 0 || !this.needUpdateUpgradeStatus) {
            return;
        }
        updateOtaLog(otaInfo, 3);
        this.needUpdateUpgradeStatus = false;
    }

    public void autoConnectDevice() {
        if (isConnected()) {
            return;
        }
        BLEManager.autoConnect();
        ConnectLogHelper.saveLog("DeviceUpgradeNewPresenter", "autoConnect()");
    }

    public void getFirmwareVersion() {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onGetCurrentFirmwareVersion(String.valueOf(this.mCurrentOtaVersion));
        }
    }

    public void getFlashInfo() {
        if (isAttachView()) {
            saveOtaLog("getflashinfo");
            if (isConnected()) {
                saveOtaLog("BLEManager.getFlashBinInfo");
                this.mHandler.removeCallbacks(this.mGetFlashTimeoutRunnable);
                this.mHandler.postDelayed(this.mGetFlashTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
                BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
                BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
                BLEManager.getFlashBinInfo();
                return;
            }
            if (this.isInDfuMode) {
                saveOtaLog("getFlashInfo-----isInDfuMode");
            } else {
                saveOtaLog("getFlashInfo-----isconnected   false");
                ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
            }
        }
    }

    public void getLanguageInfo() {
        if (isAttachView()) {
            if (getSupportFunctionInfo().ex_table_main10_v3_get_lang_library) {
                ((IDeviceUpgradeNewView) getView()).onGetCurrentLanguageVersion("--");
                if (!isConnected()) {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                    return;
                }
                saveOtaLog("support V3 language, start getLanguageInfo from device");
                this.mHandler.postDelayed(this.mGetLanguageTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
                BLEManager.unregisterGetDeviceInfoCallBack(this.mLanguageCallback);
                BLEManager.registerGetDeviceInfoCallBack(this.mLanguageCallback);
                BLEManager.getCanDownloadLangInfoV3();
                return;
            }
            if (this.mNeedDetectLanguageInfo) {
                ((IDeviceUpgradeNewView) getView()).onDetectionLanguageInfo(false, null);
            } else {
                ((IDeviceUpgradeNewView) getView()).onGetCurrentLanguageVersion("1");
            }
        }
    }

    public void getSystemConstituentInfo() {
        if (isAttachView()) {
            if (getSupportFunctionInfo().V3_v2_02_EB_firmware_bt_version_01_create) {
                if (!isConnected()) {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                    return;
                }
                saveOtaLog("support V3 SystemConstituent, start SystemConstituent from device");
                this.mHandler.postDelayed(this.mGetSystemConstituentTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
                BLEManager.unregisterGetDeviceParaCallBack(this.mSystemConstituentCallback);
                BLEManager.registerGetDeviceParaCallBack(this.mSystemConstituentCallback);
                BLEManager.getFirmwareAndBt3Version();
                return;
            }
            ((IDeviceUpgradeNewView) getView()).onDetectionSystemInfo(false, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCurrentSystemConstituentVersion(FirmwareAndBt3Version firmwareAndBt3Version) {
        if (isAttachView()) {
            if (firmwareAndBt3Version == null) {
                if (this.mNeedDetectSystemConstituentInfo) {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                    this.mNeedDetectSystemConstituentInfo = false;
                    return;
                }
                return;
            }
            saveOtaLog("onGetCanDownloadLangInfoV3：" + firmwareAndBt3Version.toString());
            this.mCurrentFirmwareAndBt3Version = firmwareAndBt3Version;
            OtaEntity.OtaInfo otaInfo = this.mFirmwareInfo;
            if (otaInfo != null) {
                OtaEntity.OtaInfo.SystemConstituentInfo systemConstituentInfo = otaInfo.getSystemConstituentInfo();
                if (systemConstituentInfo != null) {
                    saveOtaLog("onGetCanDownloadLangInfoV3：systemConstituentInfo_fir:" + systemConstituentInfo.toString());
                    ((IDeviceUpgradeNewView) getView()).onGetCurrentSystemInfoVersion(this.mCurrentFirmwareAndBt3Version);
                    return;
                }
                saveOtaLog("onGetCanDownloadLangInfoV3：systemConstituentInfo_fir==null");
                detectSystemConstituentInfo();
                return;
            }
            saveOtaLog("onGetCanDownloadLangInfoV3：mFirmwareInfo==null");
            detectSystemConstituentInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCurrentLanguageVersion(CanDownLangInfoV3 canDownLangInfoV3) {
        if (isAttachView()) {
            if (canDownLangInfoV3 == null) {
                if (this.mNeedDetectLanguageInfo) {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                    this.mNeedDetectLanguageInfo = false;
                    return;
                }
                return;
            }
            List<CanDownLangInfoV3.Item> list = canDownLangInfoV3.items_user;
            if (list == null || list.isEmpty()) {
                ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                return;
            }
            for (CanDownLangInfoV3.Item item : list) {
                if (item != null && item.language_type == canDownLangInfoV3.use_lang) {
                    this.mCurrentLanguageInfo = item;
                    ((IDeviceUpgradeNewView) getView()).onGetCurrentLanguageVersion(String.valueOf(item.language_version));
                    if (this.mNeedDetectLanguageInfo) {
                        detectLanguageInfo();
                        this.mNeedDetectLanguageInfo = false;
                        return;
                    }
                    return;
                }
            }
            ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
        }
    }

    public void updateMotaType(int i) {
        this.mOtaType = i;
    }

    public void detectFirmwareInfo(BLEDevice bLEDevice) {
        if (isAttachView()) {
            saveOtaLog("detectFirmwareInfo，device ：" + bLEDevice);
            initDeviceInfo(bLEDevice);
            this.suffix = obtainOtaFileSuffix(bLEDevice);
            this.mOtaDirFile = initOtaDir();
            saveOtaLog("detectFirmwareInfo mDevice：" + this.mDevice.toString());
            if (this.isComeFromDfuMode) {
                detectDfuFirmwareInfo();
            } else {
                detectNormalFirmwareInfo();
            }
        }
    }

    private void detectDfuFirmwareInfo() {
        saveOtaLog("detectDfuFirmwareInfo start");
        DeviceManager.checkDfuFirmwareInfo(getAppBleDevice(), 1, new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.6
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                DeviceUpgradeNewPresenter.this.detectFirmwareSuccess(otaInfo);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                DeviceUpgradeNewPresenter.this.detectFirmwareFailed(str);
            }
        });
    }

    private void detectNormalFirmwareInfo() {
        saveOtaLog("detectNormalFirmwareInfo start");
        DeviceManager.checkFirmwareInfo(getAppBleDevice(), new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.7
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                DeviceUpgradeNewPresenter.this.detectFirmwareSuccess(otaInfo);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                DeviceUpgradeNewPresenter.this.detectFirmwareFailed(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detectFirmwareFailed(String str) {
        saveOtaLog("detectFirmwareInfo onFailed：" + str);
        if (isAttachView()) {
            checkLocalFirmwareFile(this.mDevice, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detectFirmwareSuccess(OtaEntity.OtaInfo otaInfo) {
        if (isAttachView()) {
            if (otaInfo != null) {
                saveOtaLog("detectFirmwareInfo onSuccess：" + otaInfo.toString());
                this.mFirmwareInfo = otaInfo;
                try {
                    this.otaFont = otaInfo.getFlashInfo();
                    this.otaModuleSystem = otaInfo.getSystemConstituentInfo();
                    if (TextUtils.isEmpty(otaInfo.getVersion())) {
                        saveOtaLog("detectFirmwareInfo onSuccess Exception：otaInfo.getVersion  null");
                        ((IDeviceUpgradeNewView) getView()).onDetectionFirmwareInfo(false, otaInfo, true);
                    } else if (otaInfo.getVersion().contains(".")) {
                        saveOtaLog("detectFirmwareInfo onSuccess  三级版本号：" + deviceThirdVersion);
                        ((IDeviceUpgradeNewView) getView()).onDetectionFirmwareInfo(otaInfo.getVersion().equals(deviceThirdVersion) ? false : true, otaInfo, true);
                    } else {
                        saveOtaLog("detectFirmwareInfo onSuccess  非三级版本号：" + this.mDevice.version);
                        ((IDeviceUpgradeNewView) getView()).onDetectionFirmwareInfo(Integer.parseInt(otaInfo.getVersion()) > this.mDevice.version, otaInfo, true);
                    }
                    return;
                } catch (Exception e2) {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                    saveOtaLog("detectFirmwareInfo onSuccess Exception：" + e2.toString());
                    return;
                }
            }
            saveOtaLog("detectFirmwareInfo onSuccess，otaInfo is null");
            checkLocalFirmwareFile(this.mDevice, false);
            detectFlashInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detectFlashInfo() {
        saveOtaLog("detectFlashInfo = ");
        FlashBinInfo flashBinInfo = this.mCurrentFlashInfo;
        if (flashBinInfo == null) {
            this.mNeedDetectFlashInfo = true;
            getFlashInfo();
        } else {
            detectFlashInfo(flashBinInfo.matchVersion);
        }
    }

    public void detectSystemConstituentInfo() {
        if (isAttachView()) {
            saveOtaLog("mCurrentFirmwareAndBt3Version，mCurrentFlashInfo ： " + this.mCurrentFlashInfo);
            if (this.mCurrentFirmwareAndBt3Version.BT_version1 == this.mCurrentFirmwareAndBt3Version.BT_match_version1 && this.mCurrentFirmwareAndBt3Version.BT_version2 == this.mCurrentFirmwareAndBt3Version.BT_match_version2 && this.mCurrentFirmwareAndBt3Version.BT_version3 == this.mCurrentFirmwareAndBt3Version.BT_match_version3) {
                ((IDeviceUpgradeNewView) getView()).onDetectionSystemInfo(false, null);
                return;
            }
            saveOtaLog("mCurrentFirmwareAndBt3Version，mCurrentFirmwareAndBt3Version ：hasnewversion " + this.mCurrentFirmwareAndBt3Version.toString());
            String deviceBTMatchVersion = DeviceUtil.getDeviceBTMatchVersion(this.mCurrentFirmwareAndBt3Version);
            saveOtaLog("请求系统组件：" + deviceBTMatchVersion);
            DeviceManager.checkModuleSystemInfo(this.mDevice, deviceBTMatchVersion, new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.8
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                    if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                        if (otaInfo != null) {
                            DeviceUpgradeNewPresenter.this.saveOtaLog("mCurrentFirmwareAndBt3Version onSuccess ：" + otaInfo.toString());
                            DeviceUpgradeNewPresenter.this.mModuleSystemInfo = otaInfo;
                            try {
                                ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionSystemInfo(!TextUtils.isEmpty(DeviceUpgradeNewPresenter.this.mModuleSystemInfo.getFileUrl()), DeviceUpgradeNewPresenter.this.mModuleSystemInfo);
                                return;
                            } catch (Exception e2) {
                                DeviceUpgradeNewPresenter.this.saveOtaLog("mCurrentFirmwareAndBt3Version onSuccess Exception：" + e2.toString());
                                ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionFailed();
                                return;
                            }
                        }
                        DeviceUpgradeNewPresenter.this.saveOtaLog("mCurrentFirmwareAndBt3Version onSuccess ：null");
                        DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
                        deviceUpgradeNewPresenter.checkLocalModuleSystemFile(deviceUpgradeNewPresenter.mDevice);
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str) {
                    DeviceUpgradeNewPresenter.this.saveOtaLog("detectFlashInfo onFailed");
                    if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                        ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionFailed();
                    }
                }
            });
        }
    }

    public void detectFlashInfo(int i) {
        if (isAttachView()) {
            saveOtaLog("detectFlashInfo，mCurrentFlashInfo ： " + this.mCurrentFlashInfo);
            if (this.mCurrentFlashInfo.version == i) {
                ((IDeviceUpgradeNewView) getView()).onDetectionFlashInfo(false, null);
                return;
            }
            saveOtaLog("detectFlashInfo mDevice：" + this.mDevice);
            DeviceManager.checkFlashInfo(getAppBleDevice(), i, new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.9
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                    if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                        if (otaInfo != null) {
                            DeviceUpgradeNewPresenter.this.saveOtaLog("detectFlashInfo onSuccess ：" + otaInfo.toString());
                            DeviceUpgradeNewPresenter.this.mFlashInfo = otaInfo;
                            try {
                                ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionFlashInfo(!TextUtils.isEmpty(otaInfo.getFileUrl()), otaInfo);
                                return;
                            } catch (Exception e2) {
                                DeviceUpgradeNewPresenter.this.saveOtaLog("detectFlashInfo onSuccess Exception：" + e2.toString());
                                ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionFailed();
                                return;
                            }
                        }
                        DeviceUpgradeNewPresenter.this.saveOtaLog("detectFlashInfo onSuccess ：null");
                        DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
                        deviceUpgradeNewPresenter.checkLocalFlashFile(deviceUpgradeNewPresenter.mDevice);
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i2, String str) {
                    DeviceUpgradeNewPresenter.this.saveOtaLog("detectFlashInfo onFailed");
                    if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                        DeviceUpgradeNewPresenter deviceUpgradeNewPresenter = DeviceUpgradeNewPresenter.this;
                        deviceUpgradeNewPresenter.checkLocalFlashFile(deviceUpgradeNewPresenter.mDevice);
                        ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionFailed();
                    }
                }
            });
        }
    }

    public void detectLanguageInfo() {
        if (this.mCurrentLanguageInfo == null) {
            saveOtaLog("detectLanguageInfo getLanguageInfo");
            this.mNeedDetectLanguageInfo = true;
            getLanguageInfo();
        } else {
            saveOtaLog("detectLanguageInfo mDevice：" + this.mDevice);
            DeviceManager.requestLanguageList(deviceThirdVersion, new DeviceManager.OnDeviceCallback<List<RemoteLanguage.LanguageInfo>>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.10
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(List<RemoteLanguage.LanguageInfo> list) {
                    if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                        if (list == null || list.size() == 0) {
                            DeviceUpgradeNewPresenter.this.saveOtaLog("detectLanguageInfo onSuccess languageInfoList is null or empty");
                            ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionLanguageInfo(false, null);
                            return;
                        }
                        for (RemoteLanguage.LanguageInfo languageInfo : list) {
                            if (languageInfo != null && languageInfo.getCodeId() == DeviceUpgradeNewPresenter.this.mCurrentLanguageInfo.language_type) {
                                DeviceUpgradeNewPresenter.this.mLanguageInfo = languageInfo;
                                try {
                                    ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionLanguageInfo(Integer.valueOf(languageInfo.getLangVersion()).intValue() > DeviceUpgradeNewPresenter.this.mCurrentLanguageInfo.language_version, languageInfo);
                                    return;
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionFailed();
                                    DeviceUpgradeNewPresenter.this.saveOtaLog("detectLanguageInfo onSuccess Exception：" + e2.toString());
                                    return;
                                }
                            }
                        }
                        ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionLanguageInfo(false, null);
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str) {
                    DeviceUpgradeNewPresenter.this.saveOtaLog("detectLanguageInfo onFailed = " + str);
                    if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                        ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onDetectionFailed();
                    }
                }
            });
        }
    }

    private void checkLocalFirmwareFile(BLEDevice bLEDevice, boolean z) {
        if (isAttachView()) {
            if (this.mOtaDirFile == null) {
                if (z) {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                    return;
                } else {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFirmwareInfo(false, null, false);
                    return;
                }
            }
            OtaEntity.OtaInfo otaInfo = new OtaEntity.OtaInfo();
            otaInfo.setVersion(String.valueOf(bLEDevice.version));
            otaInfo.setDeviceModel(bLEDevice.mDeviceName);
            File[] fileArrListFiles = this.mOtaDirFile.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                if (z) {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                    return;
                } else {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFirmwareInfo(false, null, false);
                    return;
                }
            }
            int length = fileArrListFiles.length;
            int i = 0;
            int versionFromOtaFile = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file = fileArrListFiles[i];
                String name = file.getName();
                if (name.endsWith(this.suffix) && (versionFromOtaFile = getVersionFromOtaFile(name)) > bLEDevice.version) {
                    this.mFirmwareFilePath = file.getAbsolutePath();
                    otaInfo.setVersion(String.valueOf(versionFromOtaFile));
                    break;
                }
                i++;
            }
            if (isAttachView()) {
                if (versionFromOtaFile > 0 && !TextUtils.isEmpty(this.mFirmwareFilePath)) {
                    this.mFirmwareInfo = otaInfo;
                    ((IDeviceUpgradeNewView) getView()).onDetectionFirmwareInfo(true, otaInfo, false);
                } else if (z) {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFailed();
                } else {
                    ((IDeviceUpgradeNewView) getView()).onDetectionFirmwareInfo(false, otaInfo, false);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLocalFlashFile(BLEDevice bLEDevice) {
        saveOtaLog("checkLocalFlashFile");
        if (isAttachView()) {
            if (this.mOtaDirFile == null) {
                ((IDeviceUpgradeNewView) getView()).onDetectionFlashInfo(false, null);
                return;
            }
            OtaEntity.OtaInfo otaInfo = new OtaEntity.OtaInfo();
            int i = this.mCurrentFlashInfo.matchVersion;
            otaInfo.setVersion(String.valueOf(i));
            otaInfo.setDeviceModel(bLEDevice.mDeviceName);
            File[] fileArrListFiles = this.mOtaDirFile.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                saveOtaLog("checkLocalFlashFile    onDetectionFlashInfo:false");
                ((IDeviceUpgradeNewView) getView()).onDetectionFlashInfo(false, null);
                return;
            }
            int length = fileArrListFiles.length;
            int i2 = 0;
            int versionFromOtaFile = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                File file = fileArrListFiles[i2];
                String name = file.getName();
                if (name.endsWith(".fzbin") && (versionFromOtaFile = getVersionFromOtaFile(name)) == i) {
                    this.mFlashFilePath = file.getAbsolutePath();
                    otaInfo.setVersion(String.valueOf(versionFromOtaFile));
                    break;
                }
                i2++;
            }
            if (isAttachView()) {
                if (versionFromOtaFile == i && !TextUtils.isEmpty(this.mFlashFilePath)) {
                    saveOtaLog("checkLocalFlashFile    onDetectionFlashInfo: true");
                    this.mFlashInfo = otaInfo;
                    ((IDeviceUpgradeNewView) getView()).onDetectionFlashInfo(true, otaInfo);
                } else {
                    saveOtaLog("checkLocalFlashFile    onDetectionFlashInfo: false");
                    ((IDeviceUpgradeNewView) getView()).onDetectionFlashInfo(false, otaInfo);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLocalModuleSystemFile(BLEDevice bLEDevice) {
        saveOtaLog("checkLocalModuleSystemFile");
        if (isAttachView()) {
            if (this.mOtaDirFile == null) {
                ((IDeviceUpgradeNewView) getView()).onDetectionSystemInfo(false, null);
                return;
            }
            OtaEntity.OtaInfo otaInfo = new OtaEntity.OtaInfo();
            String str = this.mCurrentFirmwareAndBt3Version.BT_version1 + "." + this.mCurrentFirmwareAndBt3Version.BT_version2 + "." + this.mCurrentFirmwareAndBt3Version.BT_match_version3;
            String str2 = this.mCurrentFirmwareAndBt3Version.BT_version1 + "_" + this.mCurrentFirmwareAndBt3Version.BT_version2 + "_" + this.mCurrentFirmwareAndBt3Version.BT_match_version3;
            otaInfo.setVersion(str);
            otaInfo.setDeviceModel(bLEDevice.mDeviceName);
            File[] fileArrListFiles = this.mOtaDirFile.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                saveOtaLog("checkLocalModuleSystemFile    onDetectionModuleSystemInfo:false");
                ((IDeviceUpgradeNewView) getView()).onDetectionSystemInfo(false, null);
                return;
            }
            int length = fileArrListFiles.length;
            String moduleSystemNameFromOtaFile = "defaut";
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file = fileArrListFiles[i];
                String name = file.getName();
                if (name.endsWith(this.suffix)) {
                    moduleSystemNameFromOtaFile = getModuleSystemNameFromOtaFile(name);
                    if (moduleSystemNameFromOtaFile.equals(str2)) {
                        this.mModuleSystemFilePath = file.getAbsolutePath();
                        otaInfo.setVersion(str);
                        break;
                    }
                }
                i++;
            }
            if (isAttachView()) {
                if (moduleSystemNameFromOtaFile.equals(str2) && !TextUtils.isEmpty(this.mModuleSystemFilePath)) {
                    saveOtaLog("checkLocalModuleSystemFile    onDetectionModuleSystemInfo: true");
                    this.mModuleSystemInfo = otaInfo;
                    ((IDeviceUpgradeNewView) getView()).onDetectionSystemInfo(true, otaInfo);
                } else {
                    saveOtaLog("checkLocalModuleSystemFile    onDetectionModuleSystemInfo: false");
                    ((IDeviceUpgradeNewView) getView()).onDetectionSystemInfo(false, otaInfo);
                }
            }
        }
    }

    private int getVersionFromOtaFile(String str) {
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length < 2) {
            return 0;
        }
        try {
            return Integer.valueOf(strArrSplit[0]).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private String getModuleSystemNameFromOtaFile(String str) {
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length >= 2) {
            try {
                return strArrSplit[0];
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return AppMeasurementSdk.ConditionalUserProperty.NAME;
    }

    public boolean isApollo() {
        return TextUtils.equals(SPEC_NAME_FW, this.suffix);
    }

    private void initDeviceInfo(BLEDevice bLEDevice) {
        if (bLEDevice == null) {
            bLEDevice = getDeviceInfo();
            this.isInDfuMode = false;
        } else {
            this.isComeFromDfuMode = true;
            this.isInDfuMode = true;
        }
        this.mDevice = bLEDevice;
        this.mDeviceId = bLEDevice.mDeviceId;
        this.mDeviceAddress = bLEDevice.mDeviceAddress;
        if (getSupportFunctionInfo().V3_v2_02_EB_firmware_bt_version_01_create) {
            this.mCurrentOtaVersion = deviceThirdVersion;
        } else {
            this.mCurrentOtaVersion = String.valueOf(this.isComeFromDfuMode ? 0 : bLEDevice.version);
            this.mDevice.version = this.isComeFromDfuMode ? 0 : bLEDevice.version;
        }
        saveOtaLog("initDeviceInfo isbind" + BLEManager.isBind());
        saveOtaLog("initDeviceInfo，isInDfuMode ：" + this.isInDfuMode);
        saveOtaLog("initDeviceInfo，device ：" + bLEDevice);
    }

    private String obtainOtaFileSuffix(BLEDevice bLEDevice) {
        BLEDevice deviceInfo = getDeviceInfo();
        if ((bLEDevice != null && bLEDevice.mDeviceId != deviceInfo.mDeviceId) || this.isComeFromDfuMode) {
            saveOtaLog("obtainOtaFileSuffix: 设备信息和当前绑定的设备id不同，则视为是从dfu模式进入的，则OTA文件后缀名为.zip格式");
            return ".zip";
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        saveOtaLog("basicInfo = " + basicInfo);
        if (basicInfo == null) {
            saveOtaLog("LocalDataManager.getBasicInfo() = null");
            return ".zip";
        }
        int i = basicInfo.platform;
        if (i == 10) {
            return SPEC_NAME_BIN;
        }
        if (i == 30) {
            return SPEC_NAME_FW;
        }
        if (i == 40) {
            this.isHuiDingPlatform = true;
        } else if (i == 80) {
            return SPEC_NAME_FW;
        }
        return ".zip";
    }

    private File initOtaDir() {
        File file = new File(LogPathImpl.getInstance().getOtaFilePath() + this.mDevice.mDeviceId);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        this.otaFileDirPath = file.getAbsolutePath();
        return file;
    }

    public void startUpgrade(int i, int i2) {
        if (this.isUpgrading) {
            return;
        }
        this.mode = i2;
        this.mOtaType = i;
        this.isUpgrading = true;
        saveOtaLog("ota升级 模式：" + this.mode + "type:" + i);
        if (i == 3) {
            upgradeLanguage();
            return;
        }
        if (!this.isInDfuMode) {
            this.sendBasicInfoCmd = true;
            this.mHandler.postDelayed(this.mBasicInfoTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
            BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
            BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
            BLEManager.getBasicInfo();
            return;
        }
        int i3 = this.mOtaType;
        if (i3 == 1) {
            upgradeFirmware();
        } else if (i3 == 2) {
            upgradeFlash();
        } else if (i3 == 4) {
            upgradeSystemContuent();
        }
    }

    public void doUpgrade() {
        int i = this.mOtaType;
        if (i == 3) {
            upgradeLanguage();
        } else {
            startUpgrade(i, this.mode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeFirmware() {
        if (isAttachView()) {
            saveOtaLog("upgradeFirmware start, suffix = " + this.suffix + ", isHuiDingPlatform = " + this.isHuiDingPlatform);
            ((IDeviceUpgradeNewView) getView()).onUpgradePrepare(this.mOtaType);
            if (TextUtils.isEmpty(this.mFirmwareFilePath) || !FileUtil.fileExists(this.mFirmwareFilePath)) {
                downloadOtaFile(this.mFirmwareInfo, 1);
                return;
            }
            ((IDeviceUpgradeNewView) getView()).onUpgradeSuccess(100);
            BLEManager.removeDeviceUpgradeEventListener(this);
            BLEManager.addDeviceUpgradeEventListener(this);
            if (TextUtils.equals(SPEC_NAME_FW, this.suffix) || this.isHuiDingPlatform) {
                FileTransmitter.getInstance().removeListener(this.mFileTransferListener);
                FileTransmitter.getInstance().addListener(this.mFileTransferListener);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new FirewareAPOTransferTask(this.mFirmwareFilePath, 0));
                FileTransmitter.getInstance().execute(arrayList);
                return;
            }
            this.isNotApplo = true;
            saveOtaLog("开始升级固件信息：" + this.isNotApplo);
            BLEManager.removeDFUStateListener(this.mDFUStateListener);
            BleDFUConfig bleDFUConfig = new BleDFUConfig();
            bleDFUConfig.setDeviceId(String.valueOf(this.mDeviceId));
            bleDFUConfig.setMacAddress(this.mDeviceAddress);
            bleDFUConfig.setFilePath(this.mFirmwareFilePath);
            if (this.isComeFromDfuMode) {
                bleDFUConfig.setPlatform(0);
            }
            bleDFUConfig.setMaxRetryTime(0);
            BLEManager.addDFUStateListener(this.mDFUStateListener);
            BLEManager.startDFU(bleDFUConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeFlash() {
        if (isAttachView()) {
            saveOtaLog("upgradeFlash start");
            ((IDeviceUpgradeNewView) getView()).onUpgradePrepare(this.mOtaType);
            if (TextUtils.isEmpty(this.mFlashFilePath) || !FileUtil.fileExists(this.mFlashFilePath)) {
                if (this.otaFont != null) {
                    saveOtaLog("字库下载，从固件接口拿到的信息：" + this.otaFont);
                    downloadOtaFoneFile(this.otaFont, 2);
                    return;
                }
                saveOtaLog("字库下载" + this.mFlashInfo.getFileUrl());
                downloadOtaFile(this.mFlashInfo, 2);
                return;
            }
            saveOtaLog("开始升级字库信息：");
            ((IDeviceUpgradeNewView) getView()).onUpgradeSuccess(100);
            FileTransmitter.getInstance().removeListener(this.mFileTransferListener);
            FileTransmitter.getInstance().addListener(this.mFileTransferListener);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new FlashTransferTask(this.mFlashFilePath, isApollo(), 0));
            FileTransmitter.getInstance().execute(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeSystemContuent() {
        if (isAttachView()) {
            saveOtaLog("upgradeSystemContuent start");
            ((IDeviceUpgradeNewView) getView()).onUpgradePrepare(this.mOtaType);
            if (TextUtils.isEmpty(this.mModuleSystemFilePath) || !FileUtil.fileExists(this.mModuleSystemFilePath)) {
                if (this.otaModuleSystem != null) {
                    saveOtaLog("组件下载，从固件接口拿到的信息：" + this.otaModuleSystem);
                    downloadModuleSystemFile(this.otaModuleSystem, 4);
                    return;
                }
                saveOtaLog("组件下载" + this.mModuleSystemInfo.getFileUrl());
                downloadOtaFile(this.mModuleSystemInfo, 4);
                return;
            }
            saveOtaLog("开始升级系统组件信息：");
            ((IDeviceUpgradeNewView) getView()).onUpgradeSuccess(100);
            String str = this.mModuleSystemFilePath.split("/")[r0.length - 1];
            FileTransmitter.getInstance().removeListener(this.mFileTransferListener);
            FileTransmitter.getInstance().addListener(this.mFileTransferListener);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SystemFileTransferTask(this.mModuleSystemFilePath, str, isApollo(), 0));
            FileTransmitter.getInstance().execute(arrayList);
        }
    }

    private void upgradeLanguage() {
        if (isAttachView()) {
            if (this.mLanguageInfo == null) {
                ((IDeviceUpgradeNewView) getView()).onUpgradeFailed(-1);
                return;
            }
            ((IDeviceUpgradeNewView) getView()).onUpgradePrepare(this.mOtaType);
            String strConcat = getLanguageFileFolderPath().concat(this.mLanguageInfo.getCode().concat(".zip"));
            if (FileUtil.fileExists(strConcat)) {
                ((IDeviceUpgradeNewView) getView()).onUpgradeSuccess(100);
                RemoteLanguageHelper.saveLanguageLog("downloadLanguageFile，文件存在，开始解压");
                unZipFile(strConcat);
                return;
            }
            DownloadManager.download(this.mLanguageInfo.getUrl(), strConcat, this);
        }
    }

    private void unZipFile(final String str) {
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.11
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                return Boolean.valueOf(ZipUtils.unzip(str, DeviceUpgradeNewPresenter.this.getLanguageFileFolderPath()));
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                if (DeviceUpgradeNewPresenter.this.isAttachView()) {
                    if (!((Boolean) obj).booleanValue()) {
                        DeviceUpgradeNewPresenter.this.isUpgrading = false;
                        RemoteLanguageHelper.saveLanguageLog("unZipFile 解压失败");
                        ((IDeviceUpgradeNewView) DeviceUpgradeNewPresenter.this.getView()).onUpgradeFailed(200);
                        DeviceUpgradeNewPresenter.this.deleteAllLanguageFile();
                        return;
                    }
                    RemoteLanguageHelper.saveLanguageLog("unZipFile 解压成功，开始传输语言");
                }
            }
        }).execute("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLanguageFileFolderPath() {
        return LogPathImpl.getInstance().getLanguageFilePath().concat(getDeviceInfo().mDeviceId + "/").concat(this.mLanguageInfo.getCode() + "/");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteAllLanguageFile() {
        FileUtil.deleteDirectory(getLanguageFileFolderPath());
    }

    private void downloadOtaFile(OtaEntity.OtaInfo otaInfo, int i) {
        if (otaInfo == null || TextUtils.isEmpty(otaInfo.getFileUrl())) {
            this.isUpgrading = false;
            saveOtaLog("下载url异常，不升级");
            if (isAttachView()) {
                ((IDeviceUpgradeNewView) getView()).onUpgradeFailed(-1);
                return;
            }
            return;
        }
        this.mDownloadType = i;
        saveOtaLog("开始下载文件：mDownloadType = " + this.mDownloadType);
        if (i == 1) {
            DownloadManager.download(otaInfo.getFileUrl(), new File(this.otaFileDirPath, otaInfo.getVersion() + this.suffix).getAbsolutePath(), this);
            return;
        }
        if (i == 2) {
            DownloadManager.download(otaInfo.getFileUrl(), new File(this.otaFileDirPath, otaInfo.getVersion() + ".fzbin").getAbsolutePath(), this);
            return;
        }
        if (i == 4) {
            DownloadManager.download(otaInfo.getFileUrl(), new File(this.otaFileDirPath, otaInfo.getFirmwareVersion().replace(".", "_") + SYSTEM_FILE_SUFFIX).getAbsolutePath(), this);
        }
    }

    private void downloadOtaFoneFile(OtaEntity.OtaInfo.FlashInfo flashInfo, int i) {
        if (flashInfo == null || TextUtils.isEmpty(flashInfo.getFileUrl())) {
            this.isUpgrading = false;
            saveOtaLog("下载url异常，不升级");
            if (isAttachView()) {
                ((IDeviceUpgradeNewView) getView()).onUpgradeFailed(-1);
                return;
            }
            return;
        }
        this.mDownloadType = i;
        DownloadManager.download(flashInfo.getFileUrl(), new File(this.otaFileDirPath, flashInfo.getFontVersion() + ".fzbin").getAbsolutePath(), this);
    }

    private void downloadModuleSystemFile(OtaEntity.OtaInfo.SystemConstituentInfo systemConstituentInfo, int i) {
        if (systemConstituentInfo == null || TextUtils.isEmpty(systemConstituentInfo.getFileUrl())) {
            this.isUpgrading = false;
            saveOtaLog("下载url异常，不升级");
            if (isAttachView()) {
                ((IDeviceUpgradeNewView) getView()).onUpgradeFailed(-1);
                return;
            }
            return;
        }
        this.mDownloadType = i;
        DownloadManager.download(systemConstituentInfo.getFileUrl(), new File(this.otaFileDirPath, systemConstituentInfo.getFirmwareVersion().replace(".", "_") + SYSTEM_FILE_SUFFIX).getAbsolutePath(), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeStart(OtaEntity.OtaInfo otaInfo) {
        saveOtaLog("upgradeStart");
        updateOtaLog(otaInfo, 2);
        resetUpgradeState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeStart(RemoteLanguage.LanguageInfo languageInfo) {
        RemoteLanguageHelper.saveLanguageLog("installLanguage2Device onStart");
        RemoteLanguageHelper.uploadRemoteLanguageLog(languageInfo, 2);
        resetUpgradeState();
    }

    private void resetUpgradeState() {
        this.mLastProgress = 0;
        this.needUpdateUpgradeStatus = true;
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$quvpL3McmoYoNcj0EJTAFb1IE10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$resetUpgradeState$4$DeviceUpgradeNewPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$resetUpgradeState$4$DeviceUpgradeNewPresenter() {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeStart(200);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeProgress(final int i) {
        saveOtaLog("upgradeProgress：" + i);
        if (i <= this.mLastProgress) {
            return;
        }
        this.mLastProgress = i;
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$VaT5s3GwC4V1YR252FCCgN7AKLk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$upgradeProgress$5$DeviceUpgradeNewPresenter(i);
            }
        });
    }

    public /* synthetic */ void lambda$upgradeProgress$5$DeviceUpgradeNewPresenter(int i) {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeProgress(200, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeProgress(RemoteLanguage.LanguageInfo languageInfo, final int i) {
        RemoteLanguageHelper.saveLanguageLog("installLanguage2Device onProgress ：" + i);
        if (i > 0 && this.needUpdateUpgradeStatus) {
            this.needUpdateUpgradeStatus = false;
            RemoteLanguageHelper.uploadRemoteLanguageLog(languageInfo, 3);
        }
        if (i <= this.mLastProgress) {
            return;
        }
        this.mLastProgress = i;
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$KWr7PfgGWcYNlzqSCBCSLEE_zHE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$upgradeProgress$6$DeviceUpgradeNewPresenter(i);
            }
        });
    }

    public /* synthetic */ void lambda$upgradeProgress$6$DeviceUpgradeNewPresenter(int i) {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeProgress(200, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeSuccess(OtaEntity.OtaInfo otaInfo) {
        this.isUpgrading = false;
        this.isUpgradeSuccess = true;
        saveOtaLog("upgradeSuccess");
        updateOtaLog(otaInfo, 4);
        this.isInDfuMode = false;
        SPHelper.setConfigSynced(false);
        if (TextUtils.equals(".zip", this.suffix) || TextUtils.equals(SPEC_NAME_BIN, this.suffix)) {
            BLEManager.removeDFUStateListener(this.mDFUStateListener);
        }
        HomeFragmentPresenter.hasNewDeviceVersion = false;
        EventBusHelper.post(103);
        EventBusHelper.post(Constants.EventConstants.EVENT_OTA_SUCCESS);
        SPUtils.put(HAD_RESET_ALEXA, false);
        Log.d("alexa test", "发送EVENT_OTA_SUCCESS事件");
        CommonLogUtil.printAndSave("发送EVENT_OTA_SUCCESS事件");
        EventBusHelper.postSticky(new BaseMessage(Constants.EventConstants.EVENT_REQUEST_OTA_INFO, this.mDeviceAddress));
        FileUtil.deleteFile(this.mOtaType == 1 ? this.mFirmwareFilePath : this.mFlashFilePath);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$ui8ludVQwhejR9hMSO-uY3r0bcY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$upgradeSuccess$7$DeviceUpgradeNewPresenter();
            }
        });
        autoConnectDevice();
    }

    public /* synthetic */ void lambda$upgradeSuccess$7$DeviceUpgradeNewPresenter() {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeSuccess(200);
            if (this.mode == 2) {
                saveOtaLog("固件 upgradeSuccess  升级下一个");
                ((IDeviceUpgradeNewView) getView()).onUpgradeFont();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeSuccess(RemoteLanguage.LanguageInfo languageInfo) {
        HomeFragmentPresenter.hasNewDeviceVersion = false;
        this.isUpgrading = false;
        this.isUpgradeSuccess = true;
        RemoteLanguageHelper.saveLanguageLog("installLanguage2Device，upgradeSuccess onSuccess");
        RemoteLanguageHelper.uploadRemoteLanguageLog(languageInfo, 4);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$BTpdJaoYYPJ3-MJMroW2DYNcEzg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$upgradeSuccess$8$DeviceUpgradeNewPresenter();
            }
        });
        deleteAllLanguageFile();
    }

    public /* synthetic */ void lambda$upgradeSuccess$8$DeviceUpgradeNewPresenter() {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeSuccess(200);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeFailed() {
        this.isUpgrading = false;
        saveOtaLog("upgradeFailed");
        if (".zip".equals(this.suffix) || TextUtils.equals(SPEC_NAME_BIN, this.suffix)) {
            BLEManager.removeDFUStateListener(this.mDFUStateListener);
        }
        FileUtil.deleteFile(this.mOtaType == 1 ? this.mFirmwareFilePath : this.mFlashFilePath);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$ZWVfyEuscp03mf64-8s-t2wLhUg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$upgradeFailed$9$DeviceUpgradeNewPresenter();
            }
        });
        autoConnectDevice();
    }

    public /* synthetic */ void lambda$upgradeFailed$9$DeviceUpgradeNewPresenter() {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeFailed(200);
        }
    }

    private void upgradeFailed(RemoteLanguage.LanguageInfo languageInfo) {
        this.isUpgrading = false;
        RemoteLanguageHelper.uploadRemoteLanguageLog(languageInfo, 5);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$xfJx7D-q3QqARweZslzX7ygiaYg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$upgradeFailed$10$DeviceUpgradeNewPresenter();
            }
        });
        deleteAllLanguageFile();
    }

    public /* synthetic */ void lambda$upgradeFailed$10$DeviceUpgradeNewPresenter() {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeFailed(200);
        }
    }

    private String getLocalLanguageFilePath() {
        File[] fileArrListFiles;
        File file = new File(getLanguageFileFolderPath());
        if (!file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return null;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile() && (file2.getName().endsWith(RemoteLanguageHelper.LANGUAGE_FILE_SUFFIX_1) || file2.getName().endsWith(".fzbin"))) {
                return file2.getAbsolutePath();
            }
        }
        return null;
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadStart() {
        saveOtaLog("onDownloadStart");
        int i = this.mDownloadType;
        if (i == 1) {
            updateOtaLog(this.mFirmwareInfo, 0);
        } else if (i == 2) {
            updateOtaLog(this.mFlashInfo, 0);
        }
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$zAW63kK-5hQeNXBwaUhzRHcP9jw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadStart$11$DeviceUpgradeNewPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadStart$11$DeviceUpgradeNewPresenter() {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeStart(100);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadProgress(final int i) {
        saveOtaLog("onDownloadProgress：" + i);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$bpLegyUZrh5LUkCwYZdymq5k5HI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadProgress$12$DeviceUpgradeNewPresenter(i);
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadProgress$12$DeviceUpgradeNewPresenter(int i) {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeProgress(100, i);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFinish(final String str) {
        saveOtaLog("onDownloadFinish path：" + str);
        this.isUpgrading = false;
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$_wadaXNl3iH9tzhO3VUeXsW6mQI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadFinish$13$DeviceUpgradeNewPresenter(str);
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadFinish$13$DeviceUpgradeNewPresenter(String str) {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeSuccess(100);
            int i = this.mDownloadType;
            if (i == 1) {
                this.mFirmwareFilePath = str;
                updateOtaLog(this.mFirmwareInfo, 1);
                doUpgrade();
            } else if (i == 2) {
                this.mFlashFilePath = str;
                updateOtaLog(this.mFlashInfo, 1);
                doUpgrade();
            } else if (i == 4) {
                this.mModuleSystemFilePath = str;
                updateOtaLog(this.mModuleSystemInfo, 1);
                doUpgrade();
            } else {
                RemoteLanguageHelper.uploadRemoteLanguageLog(this.mLanguageInfo, 1);
                unZipFile(str);
            }
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFailed(int i, String str) {
        saveOtaLog("onDownloadFailed errInfo：" + str);
        this.isUpgrading = false;
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradeNewPresenter$UgpOvlxxFyidFuH0AnexuMJ4ZXw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadFailed$14$DeviceUpgradeNewPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadFailed$14$DeviceUpgradeNewPresenter() {
        if (isAttachView()) {
            ((IDeviceUpgradeNewView) getView()).onUpgradeFailed(100);
        }
    }

    private void updateOtaLog(OtaEntity.OtaInfo otaInfo, int i) {
        updateOtaLog(otaInfo, i, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOtaLog(OtaEntity.OtaInfo otaInfo, final int i, int i2) {
        if (this.mOtaType == 1) {
            DeviceManager.updateOtaLog(otaInfo, this.mDeviceAddress, this.mCurrentOtaVersion, i, i2, this.isComeFromDfuMode, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.12
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(Boolean bool) {
                    DeviceUpgradeNewPresenter.this.saveOtaLog("上传OTA状态 < " + i + " > " + bool + ";    isComeFromDfuMode ：" + DeviceUpgradeNewPresenter.this.isComeFromDfuMode);
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i3, String str) {
                    DeviceUpgradeNewPresenter.this.saveOtaLog("上传OTA状态 < " + i + " > 失败：" + str + ";    isComeFromDfuMode ：" + DeviceUpgradeNewPresenter.this.isComeFromDfuMode);
                }
            });
        } else {
            FlashBinInfo flashBinInfo = this.mCurrentFlashInfo;
            DeviceManager.updateFlashLog(otaInfo, this.mDeviceAddress, flashBinInfo == null ? 0 : flashBinInfo.matchVersion, i, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradeNewPresenter.13
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(Boolean bool) {
                    DeviceUpgradeNewPresenter.this.saveOtaLog("上传Flash状态 < " + i + " > " + bool);
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i3, String str) {
                    DeviceUpgradeNewPresenter.this.saveOtaLog("上传Flash状态 < " + i + " > 失败：" + str);
                }
            });
        }
    }

    public void saveOtaLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath(), "DeviceUpgradeNewPresenter", str);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        BLEManager.stopTranCommonFile();
        BLEManager.removeDeviceUpgradeEventListener(this);
        FileTransmitter.getInstance().removeListener(this.mFileTransferListener);
    }
}