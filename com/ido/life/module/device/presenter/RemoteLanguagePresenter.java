package com.ido.life.module.device.presenter;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.Units;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.FileDialDefinedUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseMessage;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.constants.Constants;
import com.ido.life.constants.LanguageCodeHelper;
import com.ido.life.data.DownloadManager;
import com.ido.life.data.api.entity.RemoteLanguage;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IRemoteLanguageView;
import com.ido.life.transmitter.FileTransmitter;
import com.ido.life.transmitter.IconTransmitterListener;
import com.ido.life.transmitter.task.LanguagePackTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.FileUtil;
import com.ido.life.util.RemoteLanguageHelper;
import com.ido.life.util.SPHelper;
import com.ido.life.util.ZipUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteLanguagePresenter extends BaseCmdPresenter<IRemoteLanguageView> implements DownloadManager.DownloadListener, IFileTransferListener, IconTransmitterListener {
    private CanDownLangInfo mCanDownLangInfo;
    private CanDownLangInfoV3 mCanDownLangInfoV3;
    private int mCurrentLanguage;
    private Handler mHandler;
    private RemoteLanguage.LanguageInfo mLanguageInfo;
    private List<RemoteLanguage.LanguageInfo> mServerLanguageList;
    private boolean needUploadLog;
    private final Runnable mTimeoutRunnable = new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$RemoteLanguagePresenter$el-bmQvz5Ald0fJxybDh8f906W4
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$RemoteLanguagePresenter();
        }
    };
    private final BaseDeviceInfoCallback mICallBack = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.device.presenter.RemoteLanguagePresenter.1
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetCanDownloadLangInfo(CanDownLangInfo canDownLangInfo) {
            super.onGetCanDownloadLangInfo(canDownLangInfo);
            RemoteLanguageHelper.saveLanguageLog("onGetCanDownloadLangInfo ：" + canDownLangInfo);
            RemoteLanguagePresenter.this.mHandler.removeCallbacks(RemoteLanguagePresenter.this.mTimeoutRunnable);
            if (RemoteLanguagePresenter.this.isAttachView()) {
                ((IRemoteLanguageView) RemoteLanguagePresenter.this.getView()).onGetDeviceLanguageInfoSuccess();
            }
            RemoteLanguagePresenter.this.mCanDownLangInfo = canDownLangInfo;
            if (!RemoteLanguagePresenter.this.isFollowSys()) {
                RemoteLanguagePresenter.this.mCurrentLanguage = canDownLangInfo.useLang;
            }
            RemoteLanguagePresenter.this.formatLanguageList();
        }

        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetCanDownloadLangInfoV3(CanDownLangInfoV3 canDownLangInfoV3) {
            super.onGetCanDownloadLangInfoV3(canDownLangInfoV3);
            RemoteLanguageHelper.saveLanguageLog("onGetCanDownloadLangInfoV3 ：" + canDownLangInfoV3);
            RemoteLanguagePresenter.this.mHandler.removeCallbacks(RemoteLanguagePresenter.this.mTimeoutRunnable);
            if (RemoteLanguagePresenter.this.isAttachView()) {
                ((IRemoteLanguageView) RemoteLanguagePresenter.this.getView()).onGetDeviceLanguageInfoSuccess();
            }
            RemoteLanguagePresenter.this.mCanDownLangInfoV3 = canDownLangInfoV3;
            if (!RemoteLanguagePresenter.this.isFollowSys()) {
                RemoteLanguagePresenter.this.mCurrentLanguage = canDownLangInfoV3.use_lang;
            }
            RemoteLanguagePresenter.this.formatLanguageListV3();
        }
    };

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferShutdown(List<TransferTask> list) {
    }

    public /* synthetic */ void lambda$new$0$RemoteLanguagePresenter() {
        if (isAttachView()) {
            ((IRemoteLanguageView) getView()).onGetDeviceLanguageInfoFailed();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        RemoteLanguageHelper.isLoading = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        BLEManager.registerGetDeviceInfoCallBack(this.mICallBack);
    }

    public boolean isFollowSys() {
        return SPHelper.isLanguageFollowSys();
    }

    public void getDeviceLanguageInfo() {
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mHandler.postDelayed(this.mTimeoutRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        if (getSupportFunctionInfo().downloadLanguage) {
            BLEManager.getCanDownloadLangInfo();
        } else if (getSupportFunctionInfo().ex_table_main10_v3_get_lang_library) {
            BLEManager.getCanDownloadLangInfoV3();
        }
    }

    public void requestLanguageList() {
        DeviceManager.requestLanguageList(deviceThirdVersion, new DeviceManager.OnDeviceCallback<List<RemoteLanguage.LanguageInfo>>() { // from class: com.ido.life.module.device.presenter.RemoteLanguagePresenter.2
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<RemoteLanguage.LanguageInfo> list) {
                RemoteLanguageHelper.saveLanguageLog("requestLanguageList onSuccess ：" + GsonUtil.toJson(list));
                RemoteLanguagePresenter.this.mServerLanguageList = list;
                if (RemoteLanguagePresenter.this.getSupportFunctionInfo().downloadLanguage) {
                    RemoteLanguagePresenter.this.formatLanguageList();
                } else if (RemoteLanguagePresenter.this.getSupportFunctionInfo().ex_table_main10_v3_get_lang_library) {
                    RemoteLanguagePresenter.this.formatLanguageListV3();
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                RemoteLanguageHelper.saveLanguageLog("requestLanguageList onFailed ：" + str);
                RemoteLanguagePresenter.this.mServerLanguageList = null;
                if (RemoteLanguagePresenter.this.getSupportFunctionInfo().downloadLanguage) {
                    RemoteLanguagePresenter.this.formatLanguageList();
                } else if (RemoteLanguagePresenter.this.getSupportFunctionInfo().ex_table_main10_v3_get_lang_library) {
                    RemoteLanguagePresenter.this.formatLanguageListV3();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void formatLanguageList() {
        if (isAttachView()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(0, new RemoteLanguage.LanguageInfo(0, LanguageUtil.getLanguageText(R.string.device_follow_system_language), true));
            if (this.mCanDownLangInfo != null) {
                List<CanDownLangInfo.LangArray> list = this.mCanDownLangInfo.langArray;
                if (this.mServerLanguageList == null) {
                    this.mServerLanguageList = new ArrayList();
                }
                if (list != null && list.size() > 0) {
                    for (CanDownLangInfo.LangArray langArray : list) {
                        if (langArray != null) {
                            int i = langArray.value;
                            String languageCode2Name = LanguageCodeHelper.formatLanguageCode2Name(i);
                            linkedHashMap.put(Integer.valueOf(i), new RemoteLanguage.LanguageInfo(i, languageCode2Name, languageCode2Name, true));
                        }
                    }
                }
                if (this.mServerLanguageList != null && this.mServerLanguageList.size() > 0) {
                    for (RemoteLanguage.LanguageInfo languageInfo : this.mServerLanguageList) {
                        if (languageInfo != null && !linkedHashMap.containsKey(Integer.valueOf(languageInfo.getCodeId()))) {
                            linkedHashMap.put(Integer.valueOf(languageInfo.getCodeId()), languageInfo);
                        }
                    }
                }
            }
            if (isAttachView()) {
                ((IRemoteLanguageView) getView()).onRequestLanguageSuccess(this.mCurrentLanguage, new ArrayList(linkedHashMap.values()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0110 A[Catch: all -> 0x012c, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0009, B:9:0x0027, B:11:0x0033, B:13:0x003c, B:15:0x0042, B:16:0x0046, B:18:0x004c, B:20:0x0054, B:23:0x0059, B:24:0x0075, B:26:0x007c, B:28:0x0082, B:29:0x0086, B:31:0x008c, B:34:0x0095, B:35:0x009f, B:37:0x00a3, B:39:0x00ab, B:40:0x00b1, B:42:0x00b7, B:45:0x00c0, B:48:0x00cf, B:50:0x00d9, B:52:0x00e5, B:54:0x00f7, B:57:0x0104, B:58:0x0108, B:59:0x0110, B:61:0x0116), top: B:67:0x0001, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0116 A[Catch: all -> 0x012c, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0009, B:9:0x0027, B:11:0x0033, B:13:0x003c, B:15:0x0042, B:16:0x0046, B:18:0x004c, B:20:0x0054, B:23:0x0059, B:24:0x0075, B:26:0x007c, B:28:0x0082, B:29:0x0086, B:31:0x008c, B:34:0x0095, B:35:0x009f, B:37:0x00a3, B:39:0x00ab, B:40:0x00b1, B:42:0x00b7, B:45:0x00c0, B:48:0x00cf, B:50:0x00d9, B:52:0x00e5, B:54:0x00f7, B:57:0x0104, B:58:0x0108, B:59:0x0110, B:61:0x0116), top: B:67:0x0001, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void formatLanguageListV3() {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.device.presenter.RemoteLanguagePresenter.formatLanguageListV3():void");
    }

    public void downloadLanguageFile(RemoteLanguage.LanguageInfo languageInfo) {
        this.mLanguageInfo = languageInfo;
        RemoteLanguageHelper.isLoading = true;
        String strConcat = getFileFolderPath().concat(languageInfo.getCode().concat(FileDialDefinedUtil.FILE_ZIP));
        if (FileUtil.fileExists(strConcat)) {
            if (isAttachView()) {
                ((IRemoteLanguageView) getView()).onLanguageProgress(1, 100);
            }
            RemoteLanguageHelper.saveLanguageLog("downloadLanguageFile 文件存在，开始解压");
            unZipFile(strConcat);
            return;
        }
        DownloadManager.download(languageInfo.getUrl(), strConcat, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getFileFolderPath() {
        return LogPathImpl.getInstance().getLanguageFilePath().concat(getDeviceInfo().mDeviceId + "/").concat(this.mLanguageInfo.getCode() + "/");
    }

    private void unZipFile(final String str) {
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.device.presenter.RemoteLanguagePresenter.3
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                return Boolean.valueOf(ZipUtils.unzip(str, RemoteLanguagePresenter.this.getFileFolderPath()));
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                if (((Boolean) obj).booleanValue()) {
                    RemoteLanguageHelper.saveLanguageLog("unZipFile 解压成功，开始传输语言");
                    RemoteLanguagePresenter.this.installLanguage2Device();
                } else {
                    RemoteLanguageHelper.saveLanguageLog("unZipFile 解压失败");
                    RemoteLanguagePresenter.this.progressComplete(2, false);
                    RemoteLanguagePresenter.this.deleteAllLanguageFile();
                }
            }
        }).execute("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void progressComplete(int i, boolean z) {
        RemoteLanguageHelper.isLoading = false;
        if (isAttachView()) {
            ((IRemoteLanguageView) getView()).onProgressComplete(i, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installLanguage2Device() {
        String localLanguageFilePath = getLocalLanguageFilePath();
        if (TextUtils.isEmpty(localLanguageFilePath)) {
            progressComplete(2, false);
            return;
        }
        FileTransmitter.getInstance().removeListener(this);
        FileTransmitter.getInstance().addListener(this);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LanguagePackTransferTask(localLanguageFilePath));
        FileTransmitter.getInstance().execute(arrayList);
    }

    public void switchDeviceLanguage(int i) {
        Units units = LocalDataManager.getUnits();
        if (units == null) {
            units = new Units();
        }
        boolean z = i == 0;
        SPHelper.setLanguageMode(z);
        if (z) {
            units.language = LanguageCodeHelper.getDeviceLanguageCode();
        } else {
            units.language = i;
        }
        BLEManager.setUnit(units);
        EventBusHelper.postSticky(new BaseMessage(Constants.EventConstants.EVENT_SWITCH_LANGUAGE, Integer.valueOf(units.language)));
    }

    private String getLocalLanguageFilePath() {
        File[] fileArrListFiles;
        File file = new File(getFileFolderPath());
        if (!file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return null;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile() && (file2.getName().endsWith(RemoteLanguageHelper.LANGUAGE_FILE_SUFFIX_1) || file2.getName().endsWith(RemoteLanguageHelper.LANGUAGE_FILE_SUFFIX_2))) {
                return file2.getAbsolutePath();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteAllLanguageFile() {
        FileUtil.deleteDirectory(getFileFolderPath());
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadStart() {
        RemoteLanguageHelper.saveLanguageLog("onDownloadStart");
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$RemoteLanguagePresenter$FDXiUHYl0wYH8j6-wi4OYfIScs0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadStart$1$RemoteLanguagePresenter();
                }
            });
        }
        RemoteLanguageHelper.uploadRemoteLanguageLog(this.mLanguageInfo, 0);
    }

    public /* synthetic */ void lambda$onDownloadStart$1$RemoteLanguagePresenter() {
        ((IRemoteLanguageView) getView()).onLanguageProgress(1, 0);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadProgress(final int i) {
        RemoteLanguageHelper.saveLanguageLog("onDownloadProgress progress ：" + i);
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$RemoteLanguagePresenter$k-q62kV33DlsFSfxcl5NgO70m0I
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadProgress$2$RemoteLanguagePresenter(i);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onDownloadProgress$2$RemoteLanguagePresenter(int i) {
        ((IRemoteLanguageView) getView()).onLanguageProgress(1, i);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFinish(String str) {
        RemoteLanguageHelper.saveLanguageLog("onDownloadFinish path ：" + str);
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$RemoteLanguagePresenter$hjY4URYp0J3JEUtHkjxmZKgzqNc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadFinish$3$RemoteLanguagePresenter();
                }
            });
        }
        RemoteLanguageHelper.uploadRemoteLanguageLog(this.mLanguageInfo, 1);
        unZipFile(str);
    }

    public /* synthetic */ void lambda$onDownloadFinish$3$RemoteLanguagePresenter() {
        ((IRemoteLanguageView) getView()).onLanguageProgress(1, 100);
    }

    @Override // com.ido.life.data.DownloadManager.DownloadListener
    public void onDownloadFailed(int i, String str) {
        RemoteLanguageHelper.saveLanguageLog("onDownloadFinish errInfo ：" + str);
        if (isAttachView()) {
            this.mHandler.post(new Runnable() { // from class: com.ido.life.module.device.presenter.-$$Lambda$RemoteLanguagePresenter$jbMZoOHswEIoVKo6VomfDBRpWVw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDownloadFailed$4$RemoteLanguagePresenter();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onDownloadFailed$4$RemoteLanguagePresenter() {
        progressComplete(1, false);
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onStart() {
        RemoteLanguageHelper.saveLanguageLog("installLanguage2Device onStart");
        this.needUploadLog = true;
        RemoteLanguageHelper.uploadRemoteLanguageLog(this.mLanguageInfo, 2);
        if (isAttachView()) {
            ((IRemoteLanguageView) getView()).onLanguageProgress(2, 0);
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onProgress(int i) {
        RemoteLanguageHelper.saveLanguageLog("installLanguage2Device onProgress：" + i);
        if (i > 0 && this.needUploadLog) {
            this.needUploadLog = false;
            RemoteLanguageHelper.uploadRemoteLanguageLog(this.mLanguageInfo, 3);
        }
        if (isAttachView()) {
            ((IRemoteLanguageView) getView()).onLanguageProgress(2, i);
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onSuccess() {
        RemoteLanguageHelper.saveLanguageLog("installLanguage2Device onSuccess");
        RemoteLanguageHelper.uploadRemoteLanguageLog(this.mLanguageInfo, 4);
        switchDeviceLanguage(this.mLanguageInfo.getCodeId());
        if (isAttachView()) {
            ((IRemoteLanguageView) getView()).onLanguageProgress(2, 100);
        }
        progressComplete(2, true);
        SPHelper.setLanguageMode(false);
        deleteAllLanguageFile();
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onFailed(String str) {
        RemoteLanguageHelper.saveLanguageLog("installLanguage2Device onFailed : " + str);
        RemoteLanguageHelper.uploadRemoteLanguageLog(this.mLanguageInfo, 5);
        progressComplete(2, false);
        deleteAllLanguageFile();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceInfoCallBack(this.mICallBack);
        FileTransmitter.getInstance().removeListener(this);
        BLEManager.stopTranCommonFile();
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferStart(int i, int i2) {
        if (i == 10) {
            onStart();
        }
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferProgress(int i, int i2, int i3) {
        if (i == 10) {
            onProgress(i3);
        }
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferSuccess(int i, int i2) {
        if (i == 10) {
            onSuccess();
        }
    }

    @Override // com.ido.life.transmitter.IconTransmitterListener
    public void onTransferFailed(int i, int i2) {
        if (i == 10) {
            onFailed("");
        }
    }
}