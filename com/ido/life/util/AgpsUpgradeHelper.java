package com.ido.life.util;

import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.gps.model.GpsStatus;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.ble.BaseGpsInfoCallback;
import com.ido.life.data.DownloadManager;

/* JADX INFO: loaded from: classes3.dex */
public class AgpsUpgradeHelper implements DownloadManager.DownloadListener, IFileTransferListener {
    private static final String AGPS_OFFLINE_FILE_NAME = "agps.ubx";
    private static final String AGPS_OFFLINE_FILE_URL = "http://offline-live1.services.u-blox.com/GetOfflineData.ashx?token=vB6zs0P4F0ayAYBMCzx4rw&gnss=gps,glo&period=1&resolution=1";
    private static final String AGPS_ONLINE_FILE_NAME = "online.ubx";
    private static final String AGPS_ONLINE_FILE_URL = "http://online-live1.services.u-blox.com/GetOnlineData.ashx?token=vB6zs0P4F0ayAYBMCzx4rw&gnss=gps,qzss,glo,bds,gal&datatype=eph&format=mga";
    private static final int TIME_MILLIONS_OF_4_HOUR = 14400000;
    private static final int TIME_MILLIONS_OF_DAY = 86400000;
    public static final int TYPE_DOWNLOAD_FAILED = 2;
    public static final int TYPE_DOWNLOAD_SUCCESS = 1;
    public static final int TYPE_UPGRADE_FAILED = 3;
    public static final int TYPE_UPGRADE_SUCCESS = 0;
    public static final int UPGRADE_OFFLINE = 2;
    public static final int UPGRADE_ONLINE = 1;
    private static int mRetryCount = 2;
    private static AgpsUpgradeHelper sAgpsUpgradeHelper;
    private boolean isOfflineUpgrade;
    private AgpsUpgradeListener mAgpsUpgradeListener;
    private BaseGpsInfoCallback mGpsInfoCallBack = new BaseGpsInfoCallback() { // from class: com.ido.life.util.AgpsUpgradeHelper.1
        @Override // com.ido.life.ble.BaseGpsInfoCallback, com.ido.ble.gps.callback.GpsCallBack.IGetGpsInfoCallBack
        public void onGetGpsStatus(GpsStatus gpsStatus) {
            super.onGetGpsStatus(gpsStatus);
            BLEManager.unregisterGetGpsInfoCallBack(AgpsUpgradeHelper.this.mGpsInfoCallBack);
            AgpsUpgradeHelper.this.saveAgpsLog("onGetGpsStatus ：" + gpsStatus.gps_run_status);
            if (gpsStatus.gps_run_status != 0) {
                AgpsUpgradeHelper.this.saveAgpsLog("checkDeviceGpsStatus , gps is not in idle status");
            } else {
                AgpsUpgradeHelper.this.downloadAgpsFile();
            }
        }
    };

    public interface AgpsUpgradeListener {
        void onAgpsUpgradeComplete(int i);
    }

    private AgpsUpgradeHelper() {
    }

    public static synchronized AgpsUpgradeHelper getInstance() {
        if (sAgpsUpgradeHelper == null) {
            sAgpsUpgradeHelper = new AgpsUpgradeHelper();
        }
        return sAgpsUpgradeHelper;
    }

    public boolean isSupportAgpsUpgrade() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.ex_gps && (supportFunctionInfo.agps_online || supportFunctionInfo.agps_offline);
    }

    private boolean isSupportOfflineUpgrade() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.ex_gps && supportFunctionInfo.agps_offline;
    }

    private boolean isSupportOnlineUpgrade() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.ex_gps && supportFunctionInfo.agps_online;
    }

    public void startAgpsUpgrade() {
        mRetryCount = 2;
        checkDeviceGpsStatus();
    }

    public void startAgpsUpgrade(int i, AgpsUpgradeListener agpsUpgradeListener) {
        this.mAgpsUpgradeListener = agpsUpgradeListener;
        if (i == 1) {
            DownloadManager.download(AGPS_ONLINE_FILE_URL, getAgpsOnlineFilePath(), this);
        } else {
            DownloadManager.download(AGPS_OFFLINE_FILE_URL, getAgpsOfflineFilePath(), this);
        }
    }

    private void checkDeviceGpsStatus() {
        if (!isSupportAgpsUpgrade()) {
            saveAgpsLog("checkDeviceGpsStatus , device not support Agps upgrade");
            return;
        }
        if (!BLEManager.isBind() || !BLEManager.isConnected()) {
            saveAgpsLog("checkDeviceGpsStatus , device not bind or disConnected");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(SPHelper.getLastAgpsOfflineUpgradeTime() - jCurrentTimeMillis) < DateUtil.DAY && Math.abs(SPHelper.getLastAgpsOnlineUpgradeTime() - jCurrentTimeMillis) < 14400000) {
            saveAgpsLog("checkDeviceGpsStatus , Agps update time not reached");
            return;
        }
        BLEManager.unregisterGetGpsInfoCallBack(this.mGpsInfoCallBack);
        BLEManager.registerGetGpsInfoCallBack(this.mGpsInfoCallBack);
        BLEManager.getGpsStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadAgpsFile() {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            saveAgpsLog("downloadAgpsFile , Network unavailable");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (isSupportOfflineUpgrade() && Math.abs(SPHelper.getLastAgpsOfflineUpgradeTime() - jCurrentTimeMillis) >= DateUtil.DAY) {
            saveAgpsLog("downloadAgpsFile , offline");
            DownloadManager.download(AGPS_OFFLINE_FILE_URL, getAgpsOfflineFilePath(), this);
        } else {
            if (!isSupportOnlineUpgrade() || Math.abs(SPHelper.getLastAgpsOnlineUpgradeTime() - jCurrentTimeMillis) < 14400000) {
                return;
            }
            saveAgpsLog("downloadAgpsFile , online");
            DownloadManager.download(AGPS_ONLINE_FILE_URL, getAgpsOnlineFilePath(), this);
        }
    }

    private String getAgpsOfflineFilePath() {
        return LogPathImpl.getInstance().getAgpsFilePath().concat(AGPS_OFFLINE_FILE_NAME);
    }

    private String getAgpsOnlineFilePath() {
        return LogPathImpl.getInstance().getAgpsFilePath().concat(AGPS_ONLINE_FILE_NAME);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadStart() {
        saveAgpsLog("Agps file onDownloadStart");
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadProgress(int i) {
        saveAgpsLog("Agps file onDownloadProgress ：" + i);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFinish(String str) {
        saveAgpsLog("Agps file onDownloadFinish ：" + str);
        transferAgpsFile2Device(str);
        AgpsUpgradeListener agpsUpgradeListener = this.mAgpsUpgradeListener;
        if (agpsUpgradeListener != null) {
            agpsUpgradeListener.onAgpsUpgradeComplete(1);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFailed(int i, String str) {
        saveAgpsLog("Agps file onDownloadFailed ：" + str);
        saveAgpsLog("Agps file onDownloadFailed retryCount ：" + mRetryCount);
        int i2 = mRetryCount;
        if (i2 <= 0) {
            AgpsUpgradeListener agpsUpgradeListener = this.mAgpsUpgradeListener;
            if (agpsUpgradeListener != null) {
                agpsUpgradeListener.onAgpsUpgradeComplete(2);
                return;
            }
            return;
        }
        mRetryCount = i2 - 1;
        checkDeviceGpsStatus();
    }

    private void transferAgpsFile2Device(String str) {
        if (TextUtils.isEmpty(str)) {
            saveAgpsLog("Agps file path is null ");
            return;
        }
        if (!BLEManager.isBind() || !BLEManager.isConnected()) {
            saveAgpsLog("transferAgpsFile2Device , device not bind or disConnected");
            return;
        }
        FileTransferConfig defaultUbloxAGpsFileConfig = FileTransferConfig.getDefaultUbloxAGpsFileConfig(str, this);
        defaultUbloxAGpsFileConfig.firmwareSpecName = com.ido.common.utils.FileUtil.getFileNameFromPath(str);
        defaultUbloxAGpsFileConfig.maxRetryTimes = 0;
        defaultUbloxAGpsFileConfig.zipType = 0;
        this.isOfflineUpgrade = AGPS_OFFLINE_FILE_NAME.equals(defaultUbloxAGpsFileConfig.firmwareSpecName);
        BLEManager.startTranCommonFile(defaultUbloxAGpsFileConfig);
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onStart() {
        saveAgpsLog("transferAgpsFile2Device onStart");
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onProgress(int i) {
        saveAgpsLog("transferAgpsFile2Device onProgress ：" + i);
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onSuccess() {
        saveAgpsLog("transferAgpsFile2Device onSuccess，isOfflineUpgrade ：" + this.isOfflineUpgrade);
        deleteAgpsFile();
        mRetryCount = 2;
        if (this.isOfflineUpgrade) {
            SPHelper.saveAgpsOfflineUpgradeTime(System.currentTimeMillis());
        } else {
            SPHelper.saveAgpsOnlineUpgradeTime(System.currentTimeMillis());
        }
        AgpsUpgradeListener agpsUpgradeListener = this.mAgpsUpgradeListener;
        if (agpsUpgradeListener != null) {
            agpsUpgradeListener.onAgpsUpgradeComplete(0);
        }
        checkDeviceGpsStatus();
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onFailed(String str) {
        saveAgpsLog("transferAgpsFile2Device onFailed ：" + str);
        saveAgpsLog("transferAgpsFile2Device onFailed retryCount ：" + mRetryCount);
        deleteAgpsFile();
        int i = mRetryCount;
        if (i <= 0) {
            AgpsUpgradeListener agpsUpgradeListener = this.mAgpsUpgradeListener;
            if (agpsUpgradeListener != null) {
                agpsUpgradeListener.onAgpsUpgradeComplete(3);
                return;
            }
            return;
        }
        mRetryCount = i - 1;
        checkDeviceGpsStatus();
    }

    private void deleteAgpsFile() {
        FileUtil.deleteDirectory(LogPathImpl.getInstance().getAgpsFilePath());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAgpsLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getAgpsLogPath(), "AgpsUpgradeHelper", str);
    }

    public void removeAgpsUpgradeListener() {
        this.mAgpsUpgradeListener = null;
    }
}