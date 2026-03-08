package com.ido.life.module.device.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.google.gson.Gson;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.FileDialDefinedUtil;
import com.ido.common.utils.FileUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.DownloadManager;
import com.ido.life.data.api.entity.DialDefinedEntityNew;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.DialDefinedView;
import com.ido.life.transmitter.FileTransmitter;
import com.ido.life.transmitter.IconTransmitterListener;
import com.ido.life.transmitter.task.DialTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.util.DialDefinedUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedPresenter extends BaseDialPresenter<DialDefinedView> implements DownloadManager.DownloadListener {
    public static final String DIAL_NAME_SPEC_NAME = ".iwf";
    private static final String DIAL_PKG_SPEC_NAME = ".zip";
    private static final long DIAL_TIMEOUT = 30000;
    public static final int PROGRESS_TYPE_DIAL_SETTING = 2;
    public static final int PROGRESS_TYPE_DOWNLOAD = 1;
    private String dialPicPath;
    private boolean isDownDial;
    private boolean isInstallDial;
    public boolean isSetting;
    private boolean isUse;
    private boolean mDeleteServerData;
    private String mDialFilePath;
    private long mDialId;
    private String mDialName;
    private String mSid;
    private boolean needUploadLog;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final IconTransmitterListener mPlateCallBack = new AnonymousClass1();
    private Runnable mSwitchDialRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$bG4ljy3jP3JNhy40eKMbe1ecSVM
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$3$DialDefinedPresenter();
        }
    };
    private Runnable mDialRemoveRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$0Xc5H7i3cNGgk2fisI7UiCyeP84
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$4$DialDefinedPresenter();
        }
    };

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.DialDefinedPresenter$1, reason: invalid class name */
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
                DialDefinedPresenter.this.onFailed(-1);
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferFailed(int i, int i2) {
            if (i == 7) {
                DialDefinedPresenter.this.onFailed(i2);
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferSuccess(int i, int i2) {
            if (i == 7) {
                DialDefinedPresenter.this.saveDialLog("~~~~~~~~~translateDial~~~~~~~~~onSuccess:" + i2);
                DialDefinedPresenter.this.uploadDialLog2Server(4);
                DialDefinedPresenter dialDefinedPresenter = DialDefinedPresenter.this;
                dialDefinedPresenter.isSetting = false;
                dialDefinedPresenter.getInstalledDialInfo();
                DialDefinedPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$1$qGKy8aTfm3P9OQpmMjOqrPBz9b0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTransferSuccess$0$DialDefinedPresenter$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onTransferSuccess$0$DialDefinedPresenter$1() {
            if (DialDefinedPresenter.this.isAttachView()) {
                ((DialDefinedView) DialDefinedPresenter.this.getView()).onDialInstallSuccess();
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferProgress(int i, int i2, final int i3) {
            if (i == 7) {
                DialDefinedPresenter.this.saveDialLog("~~~~~~~~~translateDial~~~~~~~~~onProgress : " + i3);
                if (i3 > 0 && DialDefinedPresenter.this.needUploadLog) {
                    DialDefinedPresenter.this.needUploadLog = false;
                    DialDefinedPresenter.this.uploadDialLog2Server(3);
                }
                DialDefinedPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$1$UXWGiIAQZguHbX7tNOmniu3W6ME
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTransferProgress$1$DialDefinedPresenter$1(i3);
                    }
                });
                if (i3 >= 100) {
                    DialDefinedPresenter.this.isSetting = false;
                }
            }
        }

        public /* synthetic */ void lambda$onTransferProgress$1$DialDefinedPresenter$1(int i) {
            if (DialDefinedPresenter.this.isAttachView()) {
                ((DialDefinedView) DialDefinedPresenter.this.getView()).onProgress(2, i);
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferStart(int i, int i2) {
            if (i == 7) {
                DialDefinedPresenter.this.saveDialLog("~~~~~~~~~translateDial~~~~~~~~~onStart");
                DialDefinedPresenter.this.needUploadLog = true;
                DialDefinedPresenter.this.uploadDialLog2Server(2);
                DialDefinedPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$1$NE9VZJ6DHPr93HS-zYAAjDa4srE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onTransferStart$2$DialDefinedPresenter$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onTransferStart$2$DialDefinedPresenter$1() {
            if (DialDefinedPresenter.this.isAttachView()) {
                ((DialDefinedView) DialDefinedPresenter.this.getView()).onProgress(2, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFailed(int i) {
        saveDialLog("~~~~~~~~~translateDial~~~~~~~~~onFailed：" + i);
        this.isSetting = false;
        if (i == DIAL_INSTALL_FAIL_NOT_MEMORY) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$ot1o_9oQ6alpKXX9unyTGwuR8BY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFailed$0$DialDefinedPresenter();
                }
            });
            return;
        }
        uploadDialLog2Server(5);
        getInstalledDialInfo();
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$VwMLJSkDU06qX49JEaFRHmPsZo0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFailed$1$DialDefinedPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onFailed$0$DialDefinedPresenter() {
        if (isAttachView()) {
            ((DialDefinedView) getView()).onInstallFailByNotMemory();
        }
    }

    public /* synthetic */ void lambda$onFailed$1$DialDefinedPresenter() {
        if (isAttachView()) {
            ((DialDefinedView) getView()).onDialInstallFailed();
        }
    }

    private void onDeviceSpaceNotEnough() {
        saveDialLog("~~~~~~~~~translateDial~~~~~~~~~onDeviceSpaceNotEnough");
        this.isSetting = false;
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$PBu6FC9XT7vczhdnaZlQ_498hvM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeviceSpaceNotEnough$2$DialDefinedPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceSpaceNotEnough$2$DialDefinedPresenter() {
        if (isAttachView()) {
            ((DialDefinedView) getView()).onInstallFailByNotMemory();
        }
    }

    public /* synthetic */ void lambda$new$3$DialDefinedPresenter() {
        if (this.isSetting) {
            saveDialLog("~~~~~~~~~切换表盘超时~~~~~~~~~");
            onSetPlateComplete(false);
        }
        this.isSetting = false;
    }

    public void setIsDownDial(boolean z) {
        this.isDownDial = z;
    }

    public /* synthetic */ void lambda$new$4$DialDefinedPresenter() {
        this.isSetting = false;
        saveDialLog("~~~~~~~~~删除表盘超时~~~~~~~~~");
        if (isAttachView()) {
            ((DialDefinedView) getView()).onDeleteDial(false);
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    protected void onSetPlateComplete(boolean z) {
        super.onSetPlateComplete(z);
        saveDialLog("~~~~~~~~~onSetPlateComplete~~~~~~~~~" + z);
        this.mHandler.removeCallbacks(this.mSwitchDialRunnable);
        getInstalledDialInfo();
        CommonLogUtil.d("onDialSwitched isAttachView : " + isAttachView() + "，isSetting ：" + this.isSetting);
        if (isAttachView() && this.isSetting) {
            this.isSetting = false;
            ((DialDefinedView) getView()).onDialSwitched(z);
        }
        this.isSetting = false;
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    protected void deleteDialResult(boolean z) {
        super.deleteDialResult(z);
        saveDialLog("~~~~~~~~~deleteDialResult~~~~~~~~~" + z);
        this.mHandler.removeCallbacks(this.mDialRemoveRunnable);
        if (this.isInstallDial) {
            realTranslateDial(this.mDialFilePath, this.mDialName);
            return;
        }
        if (z) {
            getInstalledDialInfo();
            this.mDialNameList.remove(this.mDialName);
        }
        if (this.mDeleteServerData && z) {
            deleteDialFromServer();
            return;
        }
        this.isSetting = false;
        if (isAttachView()) {
            ((DialDefinedView) getView()).onDeleteDial(z);
        }
    }

    public void getDefinedEntity(Context context) {
        BLEDevice deviceInfo = getDeviceInfo();
        File file = new File(FileDialDefinedUtil.jsonDir(context, deviceInfo.mDeviceId) + File.separator + FileDialDefinedUtil.APP_JSON);
        if (!file.exists()) {
            ((DialDefinedView) getView()).getDefinedEntity(null);
            return;
        }
        if (FileUtil.isDirExist(FileDialDefinedUtil.jsonDir(context, deviceInfo.mDeviceId))) {
            String str = new String(FileUtil.readFile(file));
            if (isAttachView()) {
                try {
                    ((DialDefinedView) getView()).getDefinedEntity((DialDefinedEntityNew) new Gson().fromJson(str, DialDefinedEntityNew.class));
                    return;
                } catch (Exception unused) {
                    saveDialLog("json格式化错误或不是DialDefinedEntityNew固定的格式类型");
                    NormalToast.showToast(context.getString(R.string.device_install_failed));
                    return;
                }
            }
            return;
        }
        saveDialLog("json文件不存在");
        NormalToast.showToast(context.getString(R.string.device_install_failed));
    }

    public void unpackZip(Context context, MyDialListEntity.DialInfo dialInfo) {
        BLEDevice deviceInfo = getDeviceInfo();
        if (FileDialDefinedUtil.checkFileExist(FileDialDefinedUtil.saveZipDir(context), dialInfo.getOtaFaceName())) {
            FileDialDefinedUtil.removeFile(FileDialDefinedUtil.jsonDir(context, deviceInfo.mDeviceId));
            FileDialDefinedUtil.unpackCopyZip(FileDialDefinedUtil.jsonDir(context, deviceInfo.mDeviceId) + File.separator, FileDialDefinedUtil.saveZipDir(context) + dialInfo.getOtaFaceName() + ".zip");
        }
        getDefinedEntity(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x056e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void saveDialDefined(android.content.Context r19, com.ido.life.data.api.entity.MyDialListEntity.DialInfo r20, com.ido.life.data.api.entity.DialDefinedEntityNew r21, int r22, int r23, android.view.View r24) {
        /*
            Method dump skipped, instruction units count: 1578
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.device.presenter.DialDefinedPresenter.saveDialDefined(android.content.Context, com.ido.life.data.api.entity.MyDialListEntity$DialInfo, com.ido.life.data.api.entity.DialDefinedEntityNew, int, int, android.view.View):void");
    }

    public void addDefinedDial2Account(boolean z) {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            DeviceManager.addDial(getDeviceInfo(), this.mDialId, new AnonymousClass2(z));
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.DialDefinedPresenter$2, reason: invalid class name */
    class AnonymousClass2 implements DeviceManager.OnDeviceCallback<Boolean> {
        final /* synthetic */ boolean val$installed;

        AnonymousClass2(boolean z) {
            this.val$installed = z;
        }

        @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
        public void onSuccess(Boolean bool) {
            DialDefinedPresenter.this.saveDialLog("自定义表盘addDefinedDial2Account success");
            DialDefinedPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$2$jfT4a-8pU-fvzLHkh0UzvEdy16k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSuccess$0$DialDefinedPresenter$2();
                }
            });
            EventBusHelper.post(400);
        }

        public /* synthetic */ void lambda$onSuccess$0$DialDefinedPresenter$2() {
            if (DialDefinedPresenter.this.isAttachView()) {
                ((DialDefinedView) DialDefinedPresenter.this.getView()).onAddAccountsuccess();
            }
        }

        @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
        public void onFailed(int i, String str) {
            DialDefinedPresenter.this.saveDialLog("自定义表盘addDefinedDial2Account fail:" + str);
            if (this.val$installed || !DialDefinedPresenter.this.isAttachView()) {
                return;
            }
            DialDefinedPresenter dialDefinedPresenter = DialDefinedPresenter.this;
            dialDefinedPresenter.isSetting = false;
            ((DialDefinedView) dialDefinedPresenter.getView()).onDialInstallFailed();
        }
    }

    private void addDial2Account(boolean z) {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            DeviceManager.addDial(getDeviceInfo(), this.mDialId, new AnonymousClass3(z));
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.DialDefinedPresenter$3, reason: invalid class name */
    class AnonymousClass3 implements DeviceManager.OnDeviceCallback<Boolean> {
        final /* synthetic */ boolean val$installed;

        AnonymousClass3(boolean z) {
            this.val$installed = z;
        }

        @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
        public void onSuccess(Boolean bool) {
            if (bool.booleanValue()) {
                if (DialDefinedPresenter.this.isDownDial) {
                    DialDefinedPresenter dialDefinedPresenter = DialDefinedPresenter.this;
                    dialDefinedPresenter.isSetting = false;
                    dialDefinedPresenter.isDownDial = false;
                    DialDefinedPresenter.this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$3$HpEgLYKiFhW3eJXUOrBTwn42wUg
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onSuccess$0$DialDefinedPresenter$3();
                        }
                    });
                }
                EventBusHelper.post(400);
                return;
            }
            if (this.val$installed || !DialDefinedPresenter.this.isAttachView()) {
                return;
            }
            DialDefinedPresenter dialDefinedPresenter2 = DialDefinedPresenter.this;
            dialDefinedPresenter2.isSetting = false;
            ((DialDefinedView) dialDefinedPresenter2.getView()).onDialInstallFailed();
        }

        public /* synthetic */ void lambda$onSuccess$0$DialDefinedPresenter$3() {
            if (DialDefinedPresenter.this.isAttachView()) {
                ((DialDefinedView) DialDefinedPresenter.this.getView()).onDownloadsuccess();
            }
        }

        @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
        public void onFailed(int i, String str) {
            if (this.val$installed || !DialDefinedPresenter.this.isAttachView()) {
                return;
            }
            DialDefinedPresenter dialDefinedPresenter = DialDefinedPresenter.this;
            dialDefinedPresenter.isSetting = false;
            ((DialDefinedView) dialDefinedPresenter.getView()).onDialInstallFailed();
        }
    }

    public void updateDialCollect(int i) {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            if (i == 1) {
                DeviceManager.addDialCollect(getDeviceInfo(), this.mDialId, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDefinedPresenter.4
                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onSuccess(Boolean bool) {
                        if (DialDefinedPresenter.this.isAttachView()) {
                            ((DialDefinedView) DialDefinedPresenter.this.getView()).onCollectDial(bool.booleanValue());
                            EventBusHelper.post(Constants.EventConstants.EVENT_DIAL_COLLECT_CHANGED);
                        }
                    }

                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onFailed(int i2, String str) {
                        if (DialDefinedPresenter.this.isAttachView()) {
                            ((DialDefinedView) DialDefinedPresenter.this.getView()).onCollectDial(false);
                        }
                    }
                });
            } else {
                DeviceManager.cancelDialCollect(getDeviceInfo(), this.mDialId, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDefinedPresenter.5
                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onSuccess(Boolean bool) {
                        if (DialDefinedPresenter.this.isAttachView()) {
                            ((DialDefinedView) DialDefinedPresenter.this.getView()).onCollectDial(bool.booleanValue());
                            EventBusHelper.post(Constants.EventConstants.EVENT_DIAL_COLLECT_CHANGED);
                        }
                    }

                    @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                    public void onFailed(int i2, String str) {
                        if (DialDefinedPresenter.this.isAttachView()) {
                            ((DialDefinedView) DialDefinedPresenter.this.getView()).onCollectDial(false);
                        }
                    }
                });
            }
        }
    }

    private void deleteDialFromServer() {
        EventBusHelper.post(400);
        if (isAttachView()) {
            ((DialDefinedView) getView()).onDeleteDial(true);
        }
    }

    public void requestDialState(int i) {
        DeviceManager.requestDialState(getDeviceInfo(), i, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDefinedPresenter.6
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                if (DialDefinedPresenter.this.isAttachView()) {
                    ((DialDefinedView) DialDefinedPresenter.this.getView()).onGetDialState(bool.booleanValue());
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i2, String str) {
                if (DialDefinedPresenter.this.isAttachView()) {
                    ((DialDefinedView) DialDefinedPresenter.this.getView()).onGetDialState(false);
                }
            }
        });
    }

    public void requestDialInfo(long j) {
        this.mDialId = j;
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        DeviceManager.requestDialInfo(LanguageUtil.getSystemLanguage(), j, DialDefinedUtil.appFaceVersion, basicInfo == null ? null : String.valueOf(basicInfo.user_defined_dial_main_version), getDeviceInfo().version, new DeviceManager.OnDeviceCallback<MyDialListEntity.DialInfo>() { // from class: com.ido.life.module.device.presenter.DialDefinedPresenter.7
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(MyDialListEntity.DialInfo dialInfo) {
                if (dialInfo != null) {
                    DialDefinedPresenter.this.mSid = dialInfo.getSid();
                    if (DialDefinedPresenter.this.isAttachView()) {
                        ((DialDefinedView) DialDefinedPresenter.this.getView()).onGetDialInfo(dialInfo);
                    }
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.d("requestDialInfo failed : " + str);
            }
        });
    }

    public void downDialZip(MyDialListEntity.DialInfo dialInfo, boolean z, Context context) {
        this.mDialId = dialInfo.getId();
        this.isSetting = false;
        this.mDialFilePath = initFilePath(dialInfo.getOtaFaceName(), context);
        this.isUse = z;
        if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveDialLog("downloadDialFile~~~~~~~~~网络不可用，下载失败");
            if (isAttachView()) {
                this.isSetting = false;
                ((DialDefinedView) getView()).onDownloadFailed();
                return;
            }
            return;
        }
        MyDialListEntity.DialInfo.OtaFaceVersion otaFaceVersion = dialInfo.getOtaFaceVersion();
        if (otaFaceVersion == null) {
            this.isSetting = false;
            ((DialDefinedView) getView()).onDownloadFailed();
            requestDialInfo(this.mDialId);
        } else {
            String linkUrl = otaFaceVersion.getLinkUrl();
            if (TextUtils.isEmpty(linkUrl)) {
                saveDialLog("downloadDialFile~~~~~~~~~url不存在，重新获取");
                requestDialInfo(this.mDialId);
            }
            DownloadManager.download(linkUrl, this.mDialFilePath, this);
        }
    }

    public void installDial2Device(String str, String str2, boolean z) {
        if (this.isSetting) {
            this.mDialName = str2;
            this.mDialFilePath = str;
            if (z) {
                this.isInstallDial = true;
                this.mHandler.postDelayed(this.mDialRemoveRunnable, 30000L);
                BLEManager.deleteWatchPlate(str2.concat(".iwf"));
                return;
            }
            if (mAvailableCount < 1 && !this.mDialNameList.isEmpty()) {
                saveDialLog("~~~~~~~~~installDial2Device~~~~~~~~~果空间不足，安装新表盘，先删除当前表盘");
                for (String str3 : this.mDialNameList) {
                    if (!TextUtils.isEmpty(str3) && !str3.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START) && !str3.startsWith(BaseDialPresenter.BUILT_IN_DIAL_NAME_START_OLD)) {
                        this.isInstallDial = true;
                        this.mHandler.postDelayed(this.mDialRemoveRunnable, 30000L);
                        BLEManager.deleteWatchPlate(str3);
                        return;
                    }
                }
            }
            realTranslateDial(str, str2);
        }
    }

    public void realTranslateDial(String str, String str2) {
        FileTransmitter.getInstance().removeListener(this.mPlateCallBack);
        FileTransmitter.getInstance().addListener(this.mPlateCallBack);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DialTransferTask(str, this.mDialName, false, 0));
        FileTransmitter.getInstance().execute(arrayList);
    }

    public void removeDialFromDevice(String str, boolean z) {
        this.isInstallDial = false;
        this.isSetting = true;
        this.mDialName = str;
        this.mDeleteServerData = z;
        if (this.mDialNameList.contains(str)) {
            this.mHandler.postDelayed(this.mDialRemoveRunnable, 30000L);
            BLEManager.deleteWatchPlate(str);
        } else {
            if (z) {
                deleteDialFromServer();
                return;
            }
            this.isSetting = false;
            if (isAttachView()) {
                ((DialDefinedView) getView()).onDeleteDial(true);
            }
        }
    }

    private void cancelInstall() {
        if (this.isSetting) {
            BLEManager.stopTranCommonFile();
            this.isSetting = false;
        }
    }

    private String initFilePath(String str, Context context) {
        getDeviceInfo();
        String strSaveZipDir = FileDialDefinedUtil.saveZipDir(context);
        File file = new File(strSaveZipDir);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        String str2 = str + ".zip";
        this.mDialName = str;
        return strSaveZipDir + File.separator + str2;
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadStart() {
        saveDialLog("~~~~~~~~~onDownloadStart~~~~~~~~~");
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$cSnVEN01V4RCu8rk0Feb4nmbeKU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadStart$5$DialDefinedPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadStart$5$DialDefinedPresenter() {
        if (isAttachView()) {
            ((DialDefinedView) getView()).onProgress(1, 0);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadProgress(final int i) {
        saveDialLog("~~~~~~~~~onDownloadProgress~~~~~~~~~" + i);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$rbGY4aZQvsxKUfoqMNYeLq9-mwU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadProgress$6$DialDefinedPresenter(i);
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadProgress$6$DialDefinedPresenter(int i) {
        if (isAttachView()) {
            ((DialDefinedView) getView()).onProgress(1, i);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFinish(String str) {
        saveDialLog("~~~~~~~~~onDownloadFinish~~~~~~~~~" + str);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$DS8uQ70PEKoPCg4fYVBUnQ69IKw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadFinish$7$DialDefinedPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadFinish$7$DialDefinedPresenter() {
        if (isAttachView()) {
            ((DialDefinedView) getView()).onProgress(1, 100);
            ((DialDefinedView) getView()).onDownloadsuccess();
        }
    }

    public void setDialData(String str) {
        saveDialLog("~~~~~~~~~onDownloadFinish~~~~~~~~~" + str);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$ZRBsJnulZg-0gzN0YhirjY60qXY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setDialData$8$DialDefinedPresenter();
            }
        });
        addDial2Account(false);
        uploadDialLog2Server(1);
    }

    public /* synthetic */ void lambda$setDialData$8$DialDefinedPresenter() {
        if (isAttachView()) {
            ((DialDefinedView) getView()).onProgress(1, 100);
        }
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFailed(int i, String str) {
        saveDialLog("~~~~~~~~~onDownloadFailed~~~~~~~~~" + str);
        this.isSetting = false;
        this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$DialDefinedPresenter$t_dh2zeTq_ccU1OUN9cqAEf1Xpo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDownloadFailed$9$DialDefinedPresenter();
            }
        });
    }

    public /* synthetic */ void lambda$onDownloadFailed$9$DialDefinedPresenter() {
        if (isAttachView()) {
            ((DialDefinedView) getView()).onDownloadFailed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadDialLog2Server(final int i) {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            DeviceManager.uploadDialLog(this.mDialId, getDeviceInfo().mDeviceAddress, i, this.mSid, new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.module.device.presenter.DialDefinedPresenter.8
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(Boolean bool) {
                    DialDefinedPresenter.this.saveDialLog("上传表盘状态 < " + i + " > " + bool);
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i2, String str) {
                    DialDefinedPresenter.this.saveDialLog("上传表盘状态 < " + i + " > 失败：" + str);
                }
            });
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter
    public void saveDialLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), str);
    }

    @Override // com.ido.life.module.device.presenter.BaseDialPresenter, com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        cancelInstall();
        FileTransmitter.getInstance().removeListener(this.mPlateCallBack);
    }
}