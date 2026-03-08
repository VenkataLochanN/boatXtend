package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.constants.Constants;
import com.ido.life.data.DownloadManager;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.DialDetailView;
import com.ido.life.transmitter.FileTransmitter;
import com.ido.life.transmitter.IconTransmitterListener;
import com.ido.life.transmitter.task.DialTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.util.AESUtils;
import com.ido.life.util.DialDefinedUtil;
import com.ido.life.util.FileUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialDetailPresenter extends BaseDialPresenter<DialDetailView> implements DownloadManager.DownloadListener {
    private static final String DIAL_PKG_SPEC_NAME = ".zip";
    private static final long DIAL_TIMEOUT = 30000;
    public static final int PROGRESS_TYPE_DIAL_SETTING = 2;
    public static final int PROGRESS_TYPE_DOWNLOAD = 1;
    private boolean isInstallDial;
    public boolean isSetting;
    private boolean isUse;
    private boolean mDeleteServerData;
    private String mDialFilePath;
    private long mDialId;
    private String mDialName;
    private boolean mIsUpdate;
    private String mSid;
    private String mTargetDialName;
    private boolean needUploadLog;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final IconTransmitterListener mPlateCallBack = new AnonymousClass1();
    private final Runnable mSwitchDialRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$1RRA6A_YRh1xT5TzaUBZQ6OKMUU
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$2$DialDetailPresenter();
        }
    };
    private final Runnable mDialRemoveRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$GoAD-1M3TjpYUANFdayngdFw1FU
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$3$DialDetailPresenter();
        }
    };

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.DialDetailPresenter$1, reason: invalid class name */
    class AnonymousClass1 implements IconTransmitterListener {
        AnonymousClass1() {
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
                DialDetailPresenter.this.onFailed(-1);
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferFailed(int i, int i2) {
            if (i == 7) {
                DialDetailPresenter.this.onFailed(i2);
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferSuccess(int i, int i2) {
            if (i == 7) {
                DialDetailPresenter.this.saveDialLog("translateDial onSuccess");
                DialDetailPresenter.this.uploadDialLog2Server(4);
                DialDetailPresenter dialDetailPresenter = DialDetailPresenter.this;
                dialDetailPresenter.isSetting = false;
                FileUtil.deleteFile(dialDetailPresenter.mDialFilePath);
                DialDetailPresenter.this.getInstalledDialInfo();
                DialDetailPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$1$SXjHrFVgBfuEPALH5bcQIOTh35w
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTransferSuccess$0$DialDetailPresenter$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onTransferSuccess$0$DialDetailPresenter$1() {
            if (DialDetailPresenter.this.isAttachView()) {
                ((DialDetailView) DialDetailPresenter.this.getView()).onDialInstallSuccess();
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferProgress(int i, int i2, final int i3) {
            if (i == 7) {
                DialDetailPresenter.this.saveDialLog("translateDial onProgress : " + i3);
                if (i3 > 0 && DialDetailPresenter.this.needUploadLog) {
                    DialDetailPresenter.this.needUploadLog = false;
                    DialDetailPresenter.this.uploadDialLog2Server(3);
                }
                DialDetailPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$1$j_3Qlre7TAIN8sbyFW5IIWfFDxk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTransferProgress$1$DialDetailPresenter$1(i3);
                    }
                });
                if (i3 >= 100) {
                    DialDetailPresenter.this.isSetting = false;
                }
            }
        }

        public /* synthetic */ void lambda$onTransferProgress$1$DialDetailPresenter$1(int i) {
            if (DialDetailPresenter.this.isAttachView()) {
                ((DialDetailView) DialDetailPresenter.this.getView()).onProgress(2, i);
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferStart(int i, int i2) {
            if (i == 7) {
                DialDetailPresenter.this.saveDialLog("translateDial onStart");
                DialDetailPresenter.this.needUploadLog = true;
                DialDetailPresenter.this.uploadDialLog2Server(2);
                DialDetailPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$1$8WEC0k4CMZzMse9Xg5uQq7zfLRI
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTransferStart$2$DialDetailPresenter$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onTransferStart$2$DialDetailPresenter$1() {
            if (DialDetailPresenter.this.isAttachView()) {
                ((DialDetailView) DialDetailPresenter.this.getView()).onProgress(2, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFailed(int i) {
        saveDialLog("translateDial onFailed:" + i);
        this.isSetting = false;
        if (i == DIAL_INSTALL_FAIL_NOT_MEMORY) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$i0vRJ9kvCQtpU8XNOI08Ni0mZGo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFailed$0$DialDetailPresenter();
                }
            });
            return;
        }
        uploadDialLog2Server(5);
        FileUtil.deleteFile(this.mDialFilePath);
        getInstalledDialInfo();
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$JCzPyuZtUhRa3fas51nlTIulxcw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFailed$1$DialDetailPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onFailed$0$DialDetailPresenter() {
        if (isAttachView()) {
            ((DialDetailView) getView()).onInstallFailByNotMemory();
        }
    }

    public /* synthetic */ void lambda$onFailed$1$DialDetailPresenter() {
        if (isAttachView()) {
            ((DialDetailView) getView()).onDialInstallFailed();
        }
    }

    public /* synthetic */ void lambda$new$2$DialDetailPresenter() {
        if (this.isSetting) {
            saveDialLog("切换表盘超时");
            onSetPlateComplete(false);
        }
        this.isSetting = false;
    }

    public /* synthetic */ void lambda$new$3$DialDetailPresenter() {
        this.isSetting = false;
        saveDialLog("删除表盘超时");
        if (isAttachView()) {
            if (this.mDeleteServerData) {
                ((DialDetailView) getView()).onDeleteDial(false);
            } else {
                ((DialDetailView) getView()).onRemoveDial(false);
            }
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    protected void onSetPlateComplete(boolean z) {
        super.onSetPlateComplete(z);
        saveDialLog("onSetPlateComplete ：" + z);
        this.mHandler.removeCallbacks(this.mSwitchDialRunnable);
        getInstalledDialInfo();
        CommonLogUtil.d("onDialSwitched isAttachView : " + isAttachView() + "，isSetting ：" + this.isSetting);
        if (isAttachView() && this.isSetting) {
            this.isSetting = false;
            ((DialDetailView) getView()).onDialSwitched(z);
        }
        this.isSetting = false;
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    protected void deleteDialResult(boolean z) {
        super.deleteDialResult(z);
        saveDialLog("deleteDialResult ：" + z);
        this.mHandler.removeCallbacks(this.mDialRemoveRunnable);
        if (z) {
            getInstalledDialInfo();
            this.mDialNameList.remove(this.mDialName);
        }
        saveDialLog("isInstallDial ：" + this.isInstallDial);
        if (this.isInstallDial) {
            translateDial2Device(this.mDialFilePath, this.mDialName, !this.isUse);
            return;
        }
        if (this.mDeleteServerData && z) {
            deleteDialFromServer();
            return;
        }
        this.isSetting = false;
        if (isAttachView()) {
            if (this.mDeleteServerData) {
                ((DialDetailView) getView()).onDeleteDial(z);
            } else {
                ((DialDetailView) getView()).onRemoveDial(z);
            }
        }
    }

    private void addDial2Account(final boolean z) {
        DeviceManager.addDial(getDeviceInfo(), this.mDialId, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDetailPresenter.2
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                DialDetailPresenter.this.saveDialLog("addDial2Account onSuccess : " + bool + ", installed = " + z);
                if (bool.booleanValue()) {
                    if (!z) {
                        DialDetailPresenter dialDetailPresenter = DialDetailPresenter.this;
                        dialDetailPresenter.installDial2Device(dialDetailPresenter.mDialFilePath);
                    }
                    EventBusHelper.post(400);
                    return;
                }
                if (z || !DialDetailPresenter.this.isAttachView()) {
                    return;
                }
                DialDetailPresenter dialDetailPresenter2 = DialDetailPresenter.this;
                dialDetailPresenter2.isSetting = false;
                ((DialDetailView) dialDetailPresenter2.getView()).onDialInstallFailed();
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                DialDetailPresenter.this.saveDialLog("addDial2Account onFailed : " + str + ";    installed : " + z);
                if (z || !DialDetailPresenter.this.isAttachView()) {
                    return;
                }
                DialDetailPresenter dialDetailPresenter = DialDetailPresenter.this;
                dialDetailPresenter.isSetting = false;
                ((DialDetailView) dialDetailPresenter.getView()).onDialInstallFailed();
            }
        });
    }

    private void deleteDialFromServer() {
        saveDialLog("deleteDialFromServer onSuccess");
        DeviceManager.deleteDial(getDeviceInfo(), this.mDialId, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDetailPresenter.3
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                DialDetailPresenter.this.saveDialLog("deleteDialFromServer onSuccess : " + bool);
                DialDetailPresenter.this.isSetting = false;
                if (bool.booleanValue()) {
                    EventBusHelper.post(400);
                }
                if (DialDetailPresenter.this.isAttachView()) {
                    ((DialDetailView) DialDetailPresenter.this.getView()).onDeleteDial(bool.booleanValue());
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                DialDetailPresenter.this.saveDialLog("deleteDialFromServer onFailed : " + str);
                DialDetailPresenter dialDetailPresenter = DialDetailPresenter.this;
                dialDetailPresenter.isSetting = false;
                if (dialDetailPresenter.isAttachView()) {
                    ((DialDetailView) DialDetailPresenter.this.getView()).onDeleteDial(false);
                }
            }
        });
    }

    public void switchBuiltInDial(MyDialListEntity.DialInfo dialInfo) {
        String otaFaceName;
        this.isSetting = true;
        if (isAttachView()) {
            ((DialDetailView) getView()).onSwitchDialStart();
        }
        this.mHandler.removeCallbacks(this.mSwitchDialRunnable);
        this.mHandler.postDelayed(this.mSwitchDialRunnable, 30000L);
        if (dialInfo.getOtaFaceName().contains(BaseDialPresenter.BUILT_IN_DIAL_NAME_START) || dialInfo.getOtaFaceName().endsWith(".iwf")) {
            otaFaceName = dialInfo.getOtaFaceName();
        } else {
            otaFaceName = dialInfo.getOtaFaceName() + ".iwf";
        }
        saveDialLog("switchBuiltInDial，开始切换表盘:" + otaFaceName);
        BLEManager.setWatchPlate(otaFaceName);
    }

    public void requestDialInfo(long j) {
        this.mDialId = j;
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        saveDialLog("requestDialInfo id : " + j + ";    basicInfo : " + basicInfo);
        DeviceManager.requestDialInfo(LanguageUtil.getSystemLanguage(), j, DialDefinedUtil.appFaceVersion, basicInfo == null ? null : String.valueOf(basicInfo.user_defined_dial_main_version), getDeviceInfo().version, new DeviceManager.OnDeviceCallback<MyDialListEntity.DialInfo>() { // from class: com.ido.life.module.device.presenter.DialDetailPresenter.4
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(MyDialListEntity.DialInfo dialInfo) {
                DialDetailPresenter.this.saveDialLog("requestDialInfo onSuccess : " + dialInfo);
                if (dialInfo != null) {
                    DialDetailPresenter.this.mSid = dialInfo.getSid();
                    if (DialDetailPresenter.this.isAttachView()) {
                        ((DialDetailView) DialDetailPresenter.this.getView()).onGetDialInfo(dialInfo);
                    }
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                DialDetailPresenter.this.saveDialLog("requestDialInfo failed : " + str);
            }
        });
    }

    public void downloadDialFile(MyDialListEntity.DialInfo dialInfo, boolean z, boolean z2) {
        this.mDialId = dialInfo.getId();
        this.mIsUpdate = z2;
        this.isSetting = true;
        this.mDialFilePath = initFilePath(dialInfo.getOtaFaceName());
        if (!TextUtils.isEmpty(this.mDialName) && this.mDialName.contains(".iwf")) {
            this.mTargetDialName = this.mDialName;
        } else {
            this.mTargetDialName = this.mDialName + ".iwf";
        }
        this.isUse = z;
        saveDialLog("downloadDialFile，isUpdate dial : " + z2);
        if (!z2 && this.mDialNameList.contains(this.mTargetDialName)) {
            saveDialLog("downloadDialFile 手环中已包含该表盘，开始切换表盘 isUse=" + z);
            dialInfo.setOtaFaceName(this.mTargetDialName);
            switchBuiltInDial(dialInfo);
            addDial2Account(true);
            return;
        }
        File file = new File(this.mDialFilePath);
        if (file.exists() && file.length() > 0) {
            if (isAttachView()) {
                ((DialDetailView) getView()).onProgress(1, 100);
            }
            saveDialLog("downloadDialFile 文件存在，直接切换");
            installDial2Device(this.mDialFilePath);
            return;
        }
        if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveDialLog("downloadDialFile 网络不可用，下载失败");
            if (isAttachView()) {
                this.isSetting = false;
                ((DialDetailView) getView()).onDownloadFailed();
                return;
            }
            return;
        }
        MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion = dialInfo.getOtaFaceVersion();
        String linkUrl = otaFaceVersion != null ? otaFaceVersion.getLinkUrl() : "";
        if (TextUtils.isEmpty(linkUrl)) {
            saveDialLog("downloadDialFile url不存在，重新获取");
            requestDialInfo(this.mDialId);
        }
        DownloadManager.download(linkUrl, this.mDialFilePath, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installDial2Device(String str) {
        if (this.isSetting) {
            if (this.mIsUpdate) {
                saveDialLog("installDial2Device 更新表盘，先删除已安装的表盘");
                this.isInstallDial = true;
                this.mHandler.postDelayed(this.mDialRemoveRunnable, 30000L);
                BLEManager.deleteWatchPlate(this.mTargetDialName);
                return;
            }
            if (mAvailableCount < 1 && !this.mDialNameList.isEmpty()) {
                saveDialLog("installDial2Device 空间不足，安装新表盘，先删除已安装的表盘");
                for (String str2 : this.mDialNameList) {
                    if (!TextUtils.isEmpty(str2) && !str2.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START) && !str2.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START_OLD) && !BaseDialPresenter.WALLPAPER_DIAL_NAME.equals(str2)) {
                        this.isInstallDial = true;
                        this.mHandler.postDelayed(this.mDialRemoveRunnable, 30000L);
                        BLEManager.deleteWatchPlate(str2);
                        return;
                    }
                }
            }
            translateDial2Device(str, this.mDialName, !this.isUse);
        }
    }

    private void translateDial2Device(String str, String str2, boolean z) {
        FileTransmitter.getInstance().removeListener(this.mPlateCallBack);
        FileTransmitter.getInstance().addListener(this.mPlateCallBack);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DialTransferTask(str, str2, z, 0));
        FileTransmitter.getInstance().execute(arrayList);
    }

    public void removeDialFromDevice(String str, boolean z) {
        this.isInstallDial = false;
        this.isSetting = true;
        this.mDialName = str;
        this.mDeleteServerData = this.mDialId != 0 && z;
        if (this.mDialNameList.contains(str)) {
            saveDialLog("removeDialFromDevice : " + str);
            this.mHandler.postDelayed(this.mDialRemoveRunnable, 30000L);
            BLEManager.deleteWatchPlate(str);
            return;
        }
        if (z) {
            saveDialLog("deleteServerData");
            deleteDialFromServer();
            return;
        }
        saveDialLog("设备中无改表盘，不需要删除");
        this.isSetting = false;
        if (isAttachView()) {
            ((DialDetailView) getView()).onRemoveDial(true);
        }
    }

    private void cancelInstall() {
        if (this.isSetting) {
            BLEManager.stopTranCommonFile();
            FileUtil.deleteFile(this.mDialFilePath);
            this.isSetting = false;
        }
    }

    private String initFilePath(String str) {
        String str2 = LogPathImpl.getInstance().getDialFilePath() + getDeviceInfo().mDeviceId;
        File file = new File(str2);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        if (str.contains(".iwf")) {
            str = str.replace(".iwf", "");
        }
        String strConcat = AESUtils.getUrlEncoderString(str).concat(".zip");
        this.mDialName = str;
        return str2 + File.separator + strConcat;
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadStart() {
        saveDialLog("onDownloadStart");
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$Cugqcr8ueJtZQUb9Ki5biIIDIdY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadStart$4$DialDetailPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadStart$4$DialDetailPresenter() {
        if (isAttachView()) {
            ((DialDetailView) getView()).onProgress(1, 0);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadProgress(final int i) {
        saveDialLog("onDownloadProgress ：" + i);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$x4EDHeyrYMYLHDlKlGK_ALtB-wk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadProgress$5$DialDetailPresenter(i);
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadProgress$5$DialDetailPresenter(int i) {
        if (isAttachView()) {
            ((DialDetailView) getView()).onProgress(1, i);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFinish(String str) {
        saveDialLog("onDownloadFinish ：" + str);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$tvfbFfgJn5N3eIMNqMAmXPzFiM8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadFinish$6$DialDetailPresenter();
            }
        });
        addDial2Account(false);
        uploadDialLog2Server(1);
    }

    public /* synthetic */ void lambda$onDownloadFinish$6$DialDetailPresenter() {
        if (isAttachView()) {
            ((DialDetailView) getView()).onProgress(1, 100);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFailed(int i, String str) {
        saveDialLog("onDownloadFailed ：" + str);
        this.isSetting = false;
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDetailPresenter$VpRoTYBaL2pk3ouyp-h2RBJichI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadFailed$7$DialDetailPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadFailed$7$DialDetailPresenter() {
        if (isAttachView()) {
            ((DialDetailView) getView()).onDownloadFailed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadDialLog2Server(final int i) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            saveDialLog("uploadDialLog2Server < " + i + " > 网络不可用");
            return;
        }
        DeviceManager.uploadDialLog(this.mDialId, getDeviceInfo().mDeviceAddress, i, this.mSid, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDetailPresenter.5
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                DialDetailPresenter.this.saveDialLog("uploadDialLog2Server < " + i + " > " + bool);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i2, String str) {
                DialDetailPresenter.this.saveDialLog("uploadDialLog2Server < " + i + " > 失败：" + str);
            }
        });
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        cancelInstall();
        FileTransmitter.getInstance().removeListener(this.mPlateCallBack);
    }

    public void updateDialCollect(int i) {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            if (i == 1) {
                DeviceManager.addDialCollect(getDeviceInfo(), this.mDialId, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDetailPresenter.6
                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onSuccess(Boolean bool) {
                        if (DialDetailPresenter.this.isAttachView()) {
                            ((DialDetailView) DialDetailPresenter.this.getView()).onCollectDial(bool.booleanValue());
                            EventBusHelper.post(Constants.EventConstants.EVENT_DIAL_COLLECT_CHANGED);
                        }
                    }

                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onFailed(int i2, String str) {
                        if (DialDetailPresenter.this.isAttachView()) {
                            ((DialDetailView) DialDetailPresenter.this.getView()).onCollectDial(false);
                        }
                    }
                });
            } else {
                DeviceManager.cancelDialCollect(getDeviceInfo(), this.mDialId, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDetailPresenter.7
                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onSuccess(Boolean bool) {
                        if (DialDetailPresenter.this.isAttachView()) {
                            ((DialDetailView) DialDetailPresenter.this.getView()).onCollectDial(bool.booleanValue());
                            EventBusHelper.post(Constants.EventConstants.EVENT_DIAL_COLLECT_CHANGED);
                        }
                    }

                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onFailed(int i2, String str) {
                        if (DialDetailPresenter.this.isAttachView()) {
                            ((DialDetailView) DialDetailPresenter.this.getView()).onCollectDial(false);
                        }
                    }
                });
            }
        }
    }
}