package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.dfu.BleDFUConfig;
import com.ido.ble.dfu.BleDFUState;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.DownloadManager;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IUpgradeView;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.FileUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradePresenter extends BaseCmdPresenter<IUpgradeView> implements DownloadManager.DownloadListener {
    private static final long BLE_CMD_CALLBACK_INTERVAL = 3000;
    private static final String FLASH_FILE_SUFFIX = ".fzbin";
    private static final int OTA_LOWEST_POWER = 30;
    private static final int OTA_STATUS_DOWNLOAD = 1;
    private static final int OTA_STATUS_FAILED = 5;
    private static final int OTA_STATUS_START = 2;
    private static final int OTA_STATUS_SUCCESS = 4;
    private static final int OTA_STATUS_UPGRADING = 3;
    private static final String SPEC_NAME_APOLLO = ".fw";
    private static final String SPEC_NAME_OTHER = ".zip";
    public static final int TYPE_BASIC_INFO = -1;
    private static final long UPGRADE_INTERVAL = 1200000;
    private boolean isUpgrading;
    private int mCurrentFlashVersion;
    private int mCurrentOtaVersion;
    private BLEDevice mDevice;
    private String mDeviceAddress;
    private int mDeviceId;
    private int mDownloadType;
    private String mFirmwareFilePath;
    private OtaEntity.OtaInfo mFirmwareInfo;
    private String mFlashFilePath;
    private OtaEntity.OtaInfo mFlashInfo;
    private boolean mIsRequestFailed;
    private int mLastProgress;
    private File mOtaDirFile;
    public int mOtaType;
    private int mTargetFlashVersion;
    private boolean needUpdateOtaStatus;
    private String otaFileDirPath;
    private boolean sendBasicInfoCmd;
    private String suffix;
    private int newVersion = -1;
    private boolean isInDfuMode = false;
    private Handler mHandler = new Handler();
    private IFileTransferListener mOtaFileTransferListener = new IFileTransferListener() { // from class: com.ido.life.module.device.presenter.DeviceUpgradePresenter.1
        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onStart() {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaStart(deviceUpgradePresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onProgress(int i) {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaProgress(deviceUpgradePresenter.mFirmwareInfo, i);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onSuccess() {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaSuccess(deviceUpgradePresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onFailed(String str) {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaFailed(deviceUpgradePresenter.mFirmwareInfo);
        }
    };
    private BleDFUState.IListener mDFUStateListener = new BleDFUState.IListener() { // from class: com.ido.life.module.device.presenter.DeviceUpgradePresenter.2
        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onPrepare() {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaStart(deviceUpgradePresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onDeviceInDFUMode() {
            CommonLogUtil.d("onDeviceInDFUMode");
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onProgress(int i) {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaProgress(deviceUpgradePresenter.mFirmwareInfo, i);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onSuccess() {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaSuccess(deviceUpgradePresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onSuccessAndNeedToPromptUser() {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaSuccess(deviceUpgradePresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onFailed(BleDFUState.FailReason failReason) {
            CommonLogUtil.d("BleDFUState.FailReason = " + failReason.toString());
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaFailed(deviceUpgradePresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onCanceled() {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaFailed(deviceUpgradePresenter.mFirmwareInfo);
        }

        @Override // com.ido.ble.dfu.BleDFUState.IListener
        public void onRetry(int i) {
            CommonLogUtil.d("onRetry = " + i);
        }
    };
    private IFileTransferListener mFlashFileTransferListener = new IFileTransferListener() { // from class: com.ido.life.module.device.presenter.DeviceUpgradePresenter.3
        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onStart() {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaStart(deviceUpgradePresenter.mFlashInfo);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onProgress(int i) {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaProgress(deviceUpgradePresenter.mFlashInfo, i);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onSuccess() {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaSuccess(deviceUpgradePresenter.mFlashInfo);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onFailed(String str) {
            DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
            deviceUpgradePresenter.otaFailed(deviceUpgradePresenter.mFlashInfo);
        }
    };
    private Runnable mOtaTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$dPYciI_tEpkM2lvULkusccwJ_lk
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$DeviceUpgradePresenter();
        }
    };
    private BaseDeviceInfoCallback mDeviceInfoCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.device.presenter.DeviceUpgradePresenter.4
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetBasicInfo(BasicInfo basicInfo) {
            super.onGetBasicInfo(basicInfo);
            if (DeviceUpgradePresenter.this.sendBasicInfoCmd) {
                DeviceUpgradePresenter.this.mHandler.removeCallbacks(DeviceUpgradePresenter.this.mBasicInfoTimeoutRunnable);
                DeviceUpgradePresenter.this.sendBasicInfoCmd = false;
                if (basicInfo == null) {
                    DeviceUpgradePresenter.this.isUpgrading = false;
                    if (DeviceUpgradePresenter.this.isAttachView()) {
                        ((IUpgradeView) DeviceUpgradePresenter.this.getView()).onLowPower();
                        return;
                    }
                    return;
                }
                DeviceUpgradePresenter.this.saveOtaLog("获取到电量 = " + basicInfo.energe);
                if (basicInfo.energe < 30) {
                    DeviceUpgradePresenter.this.isUpgrading = false;
                    if (DeviceUpgradePresenter.this.isAttachView()) {
                        ((IUpgradeView) DeviceUpgradePresenter.this.getView()).onLowPower();
                        return;
                    }
                    return;
                }
                if (DeviceUpgradePresenter.this.mOtaType == 1) {
                    DeviceUpgradePresenter.this.upgradeFirmware();
                } else {
                    DeviceUpgradePresenter.this.upgradeFlash();
                }
            }
        }

        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFlashBinInfo(FlashBinInfo flashBinInfo) {
            super.onGetFlashBinInfo(flashBinInfo);
            DeviceUpgradePresenter.this.mHandler.removeCallbacks(DeviceUpgradePresenter.this.mGetFlashTimeoutRunnable);
            BLEManager.unregisterGetDeviceInfoCallBack(DeviceUpgradePresenter.this.mDeviceInfoCallback);
            if (flashBinInfo != null) {
                DeviceUpgradePresenter.this.mCurrentFlashVersion = flashBinInfo.version;
                CommonLogUtil.d("onGetFlashBinInfo = " + flashBinInfo.toString());
                if (DeviceUpgradePresenter.this.isAttachView()) {
                    ((IUpgradeView) DeviceUpgradePresenter.this.getView()).onGetFlashInfo(flashBinInfo);
                }
            }
        }
    };
    private Runnable mBasicInfoTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$wG4Lq7mKwD6qdBexkJAdQ9be4IU
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$DeviceUpgradePresenter();
        }
    };
    private Runnable mGetFlashTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$0gM1FXrLa4p-EYJCZ5v_4iVpBR8
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$2$DeviceUpgradePresenter();
        }
    };

    public /* synthetic */ void lambda$new$0$DeviceUpgradePresenter() {
        saveOtaLog("---------------otaTimeoutRunnable--------------");
        if (SPEC_NAME_APOLLO.equals(this.suffix)) {
            BLEManager.stopTranCommonFile();
        } else {
            BLEManager.cancelDFU();
        }
        otaFailed(this.mFirmwareInfo);
    }

    public /* synthetic */ void lambda$new$1$DeviceUpgradePresenter() {
        if (isAttachView()) {
            BasicInfo basicInfo = LocalDataManager.getBasicInfo();
            if (basicInfo == null) {
                saveOtaLog("---------------获取电量失败--------------");
                this.isUpgrading = false;
                ((IUpgradeView) getView()).onFailed(-1);
                return;
            }
            saveOtaLog("---------------当前电量--------------" + basicInfo.energe);
            if (basicInfo.energe >= 30) {
                if (this.mOtaType == 1) {
                    upgradeFirmware();
                    return;
                } else {
                    upgradeFlash();
                    return;
                }
            }
            this.isUpgrading = false;
            ((IUpgradeView) getView()).onLowPower();
        }
    }

    public /* synthetic */ void lambda$new$2$DeviceUpgradePresenter() {
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        if (isAttachView()) {
            ((IUpgradeView) getView()).onCheckedFailed();
        }
    }

    public boolean isApollo() {
        return SPEC_NAME_APOLLO.equals(this.suffix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void otaStart(OtaEntity.OtaInfo otaInfo) {
        saveOtaLog("---------------otaStart--------------");
        updateOtaLog(otaInfo, 2);
        this.needUpdateOtaStatus = true;
        this.mLastProgress = 0;
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$U5ijDd3ygQLfqdQj-N6AZTFuceg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$otaStart$3$DeviceUpgradePresenter();
                }
            });
        }
    }

    public /* synthetic */ void lambda$otaStart$3$DeviceUpgradePresenter() {
        ((IUpgradeView) getView()).onStart(200);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveOtaLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath().concat(this.mDeviceId + "/"), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void otaProgress(OtaEntity.OtaInfo otaInfo, final int i) {
        saveOtaLog("---------------otaProgress--------------" + i);
        if (i > 0) {
            this.isInDfuMode = true;
            if (this.needUpdateOtaStatus) {
                updateOtaLog(otaInfo, 3);
                this.needUpdateOtaStatus = false;
            }
        }
        if (i <= this.mLastProgress) {
            return;
        }
        this.mLastProgress = i;
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$WjcqP0sBleYPbTgwyps_hZBUcUI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$otaProgress$4$DeviceUpgradePresenter(i);
                }
            });
        }
    }

    public /* synthetic */ void lambda$otaProgress$4$DeviceUpgradePresenter(int i) {
        ((IUpgradeView) getView()).onProgress(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void otaFailed(OtaEntity.OtaInfo otaInfo) {
        saveOtaLog("---------------otaFailed--------------");
        updateOtaLog(otaInfo, 5);
        this.isUpgrading = false;
        if (".zip".equals(this.suffix)) {
            BLEManager.removeDFUStateListener(this.mDFUStateListener);
        }
        FileUtil.deleteFile(this.mOtaType == 1 ? this.mFirmwareFilePath : this.mFlashFilePath);
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$-8rSMX2kLI4tDqzlpPJTqKC8_mM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$otaFailed$5$DeviceUpgradePresenter();
                }
            });
        }
        autoConnectDevice();
    }

    public /* synthetic */ void lambda$otaFailed$5$DeviceUpgradePresenter() {
        ((IUpgradeView) getView()).onFailed(200);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void otaSuccess(OtaEntity.OtaInfo otaInfo) {
        saveOtaLog("---------------otaSuccess--------------");
        updateOtaLog(otaInfo, 4);
        this.isInDfuMode = false;
        this.isUpgrading = false;
        SPHelper.setConfigSynced(false);
        if (".zip".equals(this.suffix)) {
            BLEManager.removeDFUStateListener(this.mDFUStateListener);
        }
        EventBusHelper.post(103);
        EventBusHelper.post(Constants.EventConstants.EVENT_OTA_SUCCESS);
        FileUtil.deleteFile(this.mOtaType == 1 ? this.mFirmwareFilePath : this.mFlashFilePath);
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$uxp9IlArRTjYsAVr0lQedt56et8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$otaSuccess$6$DeviceUpgradePresenter();
                }
            });
        }
        autoConnectDevice();
    }

    public /* synthetic */ void lambda$otaSuccess$6$DeviceUpgradePresenter() {
        ((IUpgradeView) getView()).onSuccess(200);
    }

    public void requestFirmwareInfo(BLEDevice bLEDevice) {
        initDeviceInfo(bLEDevice);
        this.suffix = obtainOtaFileSuffix(bLEDevice);
        this.mOtaDirFile = initOtaDir();
        DeviceManager.checkFirmwareInfo(getAppBleDevice(), new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradePresenter.5
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                if (DeviceUpgradePresenter.this.isAttachView()) {
                    boolean z = false;
                    if (otaInfo != null) {
                        DeviceUpgradePresenter.this.mFirmwareInfo = otaInfo;
                        IUpgradeView iUpgradeView = (IUpgradeView) DeviceUpgradePresenter.this.getView();
                        if (Integer.valueOf(otaInfo.getVersion()).intValue() > DeviceUpgradePresenter.this.mDevice.version && !TextUtils.isEmpty(otaInfo.getFileUrl())) {
                            z = true;
                        }
                        iUpgradeView.onCheckedFirmwareInfo(z, otaInfo);
                        return;
                    }
                    DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
                    deviceUpgradePresenter.checkLocalFirmwareFile(deviceUpgradePresenter.mDevice, false);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
                deviceUpgradePresenter.checkLocalFirmwareFile(deviceUpgradePresenter.mDevice, true);
            }
        });
    }

    public void getFlashInfoFromDevice() {
        if (isConnected()) {
            this.mHandler.removeCallbacks(this.mGetFlashTimeoutRunnable);
            this.mHandler.postDelayed(this.mGetFlashTimeoutRunnable, 3000L);
            BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
            BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
            BLEManager.getFlashBinInfo();
            return;
        }
        if (isAttachView()) {
            ((IUpgradeView) getView()).onCheckedFailed();
        }
    }

    public void requestFlashInfo(int i) {
        this.mTargetFlashVersion = i;
        DeviceManager.checkFlashInfo(getAppBleDevice(), i, new DeviceManager.OnDeviceCallback<OtaEntity.OtaInfo>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradePresenter.6
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(OtaEntity.OtaInfo otaInfo) {
                if (DeviceUpgradePresenter.this.isAttachView()) {
                    if (otaInfo != null) {
                        DeviceUpgradePresenter.this.mFlashInfo = otaInfo;
                        ((IUpgradeView) DeviceUpgradePresenter.this.getView()).onCheckedFlashInfo(!TextUtils.isEmpty(otaInfo.getFileUrl()), otaInfo);
                    } else {
                        DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
                        deviceUpgradePresenter.checkLocalFlashFile(deviceUpgradePresenter.mDevice, false);
                    }
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i2, String str) {
                DeviceUpgradePresenter deviceUpgradePresenter = DeviceUpgradePresenter.this;
                deviceUpgradePresenter.checkLocalFlashFile(deviceUpgradePresenter.mDevice, true);
            }
        });
    }

    private void initDeviceInfo(BLEDevice bLEDevice) {
        if (bLEDevice == null) {
            bLEDevice = getDeviceInfo();
            this.isInDfuMode = false;
        } else {
            this.isInDfuMode = true;
        }
        this.mDevice = bLEDevice;
        this.mDeviceId = bLEDevice.mDeviceId;
        this.mDeviceAddress = bLEDevice.mDeviceAddress;
        this.mCurrentOtaVersion = bLEDevice.version;
    }

    private String obtainOtaFileSuffix(BLEDevice bLEDevice) {
        BasicInfo basicInfo;
        return ((bLEDevice == null || bLEDevice.mDeviceId == getDeviceInfo().mDeviceId) && (basicInfo = LocalDataManager.getBasicInfo()) != null && basicInfo.platform == 30) ? SPEC_NAME_APOLLO : ".zip";
    }

    private File initOtaDir() {
        File file = new File(LogPathImpl.getInstance().getOtaFilePath() + this.mDevice.mDeviceId);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        this.otaFileDirPath = file.getAbsolutePath();
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLocalFirmwareFile(BLEDevice bLEDevice, boolean z) {
        int versionFromOtaFile;
        if (this.mOtaDirFile == null) {
            return;
        }
        OtaEntity.OtaInfo otaInfo = new OtaEntity.OtaInfo();
        otaInfo.setVersion(String.valueOf(bLEDevice.version));
        otaInfo.setDeviceModel(bLEDevice.mDeviceName);
        File[] fileArrListFiles = this.mOtaDirFile.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            int length = fileArrListFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file = fileArrListFiles[i];
                String name = file.getName();
                if (name.endsWith(this.suffix) && (versionFromOtaFile = getVersionFromOtaFile(name)) > bLEDevice.version) {
                    this.newVersion = versionFromOtaFile;
                    this.mFirmwareFilePath = file.getAbsolutePath();
                    otaInfo.setVersion(String.valueOf(this.newVersion));
                    break;
                }
                i++;
            }
        }
        if (isAttachView()) {
            if (this.newVersion > 0 && !TextUtils.isEmpty(this.mFirmwareFilePath)) {
                ((IUpgradeView) getView()).onCheckedFirmwareInfo(true, otaInfo);
            } else {
                this.mIsRequestFailed = z;
                ((IUpgradeView) getView()).onCheckedFirmwareInfo(false, otaInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLocalFlashFile(BLEDevice bLEDevice, boolean z) {
        int versionFromOtaFile;
        if (this.mOtaDirFile == null) {
            return;
        }
        OtaEntity.OtaInfo otaInfo = new OtaEntity.OtaInfo();
        otaInfo.setVersion(String.valueOf(this.mTargetFlashVersion));
        otaInfo.setDeviceModel(bLEDevice.mDeviceName);
        File[] fileArrListFiles = this.mOtaDirFile.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            int length = fileArrListFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file = fileArrListFiles[i];
                String name = file.getName();
                if (name.endsWith(".fzbin") && (versionFromOtaFile = getVersionFromOtaFile(name)) == this.mTargetFlashVersion) {
                    this.newVersion = versionFromOtaFile;
                    this.mFlashFilePath = file.getAbsolutePath();
                    otaInfo.setVersion(String.valueOf(this.newVersion));
                    break;
                }
                i++;
            }
        }
        if (isAttachView()) {
            if (this.newVersion == this.mTargetFlashVersion && !TextUtils.isEmpty(this.mFlashFilePath)) {
                ((IUpgradeView) getView()).onCheckedFlashInfo(true, otaInfo);
            } else if (z || this.mIsRequestFailed) {
                ((IUpgradeView) getView()).onCheckedFailed();
            } else {
                ((IUpgradeView) getView()).onCheckedFlashInfo(false, otaInfo);
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

    public int getNewVersion() {
        return this.newVersion;
    }

    public boolean isUpgrading() {
        return this.isUpgrading;
    }

    public void autoConnectDevice() {
        if (isConnected()) {
            return;
        }
        BLEManager.autoConnect();
        ConnectLogHelper.saveLog("DeviceUpgradePresenter", "autoConnect()");
    }

    public boolean isRequestFailed() {
        return this.mIsRequestFailed;
    }

    public void startUpgrade() {
        this.isUpgrading = true;
        if (!this.isInDfuMode) {
            this.sendBasicInfoCmd = true;
            this.mHandler.postDelayed(this.mBasicInfoTimeoutRunnable, 3000L);
            BLEManager.getBasicInfo();
        } else if (this.mOtaType == 1) {
            upgradeFirmware();
        } else {
            upgradeFlash();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeFirmware() {
        if (TextUtils.isEmpty(this.mFirmwareFilePath) || !FileUtil.fileExists(this.mFirmwareFilePath)) {
            downloadFile(this.mFirmwareInfo, 1);
            return;
        }
        if (SPEC_NAME_APOLLO.equals(this.suffix)) {
            FileTransferConfig defaultApolloOTAConfig = FileTransferConfig.getDefaultApolloOTAConfig(this.mFirmwareFilePath, this.mOtaFileTransferListener);
            defaultApolloOTAConfig.maxRetryTimes = 0;
            BLEManager.startTranCommonFile(defaultApolloOTAConfig);
            return;
        }
        BLEManager.removeDFUStateListener(this.mDFUStateListener);
        BleDFUConfig bleDFUConfig = new BleDFUConfig();
        bleDFUConfig.setDeviceId(String.valueOf(this.mDeviceId));
        bleDFUConfig.setMacAddress(this.mDeviceAddress);
        bleDFUConfig.setFilePath(this.mFirmwareFilePath);
        bleDFUConfig.setMaxRetryTime(0);
        BLEManager.addDFUStateListener(this.mDFUStateListener);
        BLEManager.startDFU(bleDFUConfig);
    }

    public void upgradeFlash() {
        if (TextUtils.isEmpty(this.mFlashFilePath) || !FileUtil.fileExists(this.mFlashFilePath)) {
            downloadFile(this.mFlashInfo, 2);
            return;
        }
        FileTransferConfig defaultTransLangFileConfig = FileTransferConfig.getDefaultTransLangFileConfig(this.mFlashFilePath, this.mFlashFileTransferListener);
        defaultTransLangFileConfig.maxRetryTimes = 0;
        BLEManager.startTranCommonFile(defaultTransLangFileConfig);
    }

    private void downloadFile(OtaEntity.OtaInfo otaInfo, int i) {
        if (otaInfo == null || TextUtils.isEmpty(otaInfo.getFileUrl())) {
            saveOtaLog("下载url异常，不升级");
            if (isAttachView()) {
                ((IUpgradeView) getView()).onFailed(-1);
                return;
            }
            return;
        }
        this.mDownloadType = i;
        if (i == 1) {
            DownloadManager.download(otaInfo.getFileUrl(), new File(this.otaFileDirPath, otaInfo.getVersion() + this.suffix).getAbsolutePath(), this);
            return;
        }
        DownloadManager.download(otaInfo.getFileUrl(), new File(this.otaFileDirPath, otaInfo.getVersion() + ".fzbin").getAbsolutePath(), this);
    }

    private void stopUpgrade() {
        if (this.isUpgrading) {
            if (SPEC_NAME_APOLLO.equals(this.suffix)) {
                BLEManager.stopTranCommonFile();
            } else {
                BLEManager.cancelDFU();
            }
        }
        this.isUpgrading = false;
    }

    private void updateOtaLog(OtaEntity.OtaInfo otaInfo, final int i) {
        if (this.mOtaType == 1) {
            DeviceManager.updateOtaLog(otaInfo, this.mDeviceAddress, this.mCurrentOtaVersion + "", i, 0, false, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradePresenter.7
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(Boolean bool) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath().concat(DeviceUpgradePresenter.this.mDeviceId + "/"), "上传OTA状态 < " + i + " > " + bool);
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i2, String str) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath().concat(DeviceUpgradePresenter.this.mDeviceId + "/"), "上传OTA状态 < " + i + " > 失败：" + str);
                }
            });
            return;
        }
        DeviceManager.updateFlashLog(otaInfo, this.mDeviceAddress, this.mCurrentFlashVersion, i, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DeviceUpgradePresenter.8
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath().concat(DeviceUpgradePresenter.this.mDeviceId + "/"), "上传Flash状态 < " + i + " > " + bool);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i2, String str) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath().concat(DeviceUpgradePresenter.this.mDeviceId + "/"), "上传Flash状态 < " + i + " > 失败：" + str);
            }
        });
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadStart() {
        saveOtaLog("------------onDownloadStart-----------");
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$t3fGHffWsZFv-irbzYQZYULjEuk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadStart$7$DeviceUpgradePresenter();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onDownloadStart$7$DeviceUpgradePresenter() {
        ((IUpgradeView) getView()).onStart(100);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadProgress(final int i) {
        saveOtaLog("------------onDownloadProgress-----------" + i);
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$Rye23gTKG46qUWP4qE4Zogcs1aA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadProgress$8$DeviceUpgradePresenter(i);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onDownloadProgress$8$DeviceUpgradePresenter(int i) {
        ((IUpgradeView) getView()).onProgress(i);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFinish(String str) {
        saveOtaLog("------------onDownloadFinish-----------" + str);
        if (this.mDownloadType == 1) {
            this.mFirmwareFilePath = str;
            updateOtaLog(this.mFirmwareInfo, 1);
            this.newVersion = Integer.valueOf(this.mFirmwareInfo.getVersion()).intValue();
        } else {
            this.mFlashFilePath = str;
            updateOtaLog(this.mFlashInfo, 1);
        }
        startUpgrade();
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFailed(int i, String str) {
        saveOtaLog("------------onDownloadFinish-----------" + str);
        this.isUpgrading = false;
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DeviceUpgradePresenter$ZQbYu6F2Y_Qhu4M9UsnGJRwCOOw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadFailed$9$DeviceUpgradePresenter();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onDownloadFailed$9$DeviceUpgradePresenter() {
        ((IUpgradeView) getView()).onFailed(100);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        this.mHandler.removeCallbacksAndMessages(null);
        stopUpgrade();
    }
}