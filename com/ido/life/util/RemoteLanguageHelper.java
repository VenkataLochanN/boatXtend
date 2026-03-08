package com.ido.life.util;

import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.Units;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.FileDialDefinedUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.constants.Constants;
import com.ido.life.constants.LanguageCodeHelper;
import com.ido.life.data.DownloadManager;
import com.ido.life.data.api.entity.RemoteLanguage;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.database.model.RemoteLanguageMsg;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.transmitter.FileTransmitter;
import com.ido.life.transmitter.IconTransmitterListener;
import com.ido.life.transmitter.task.LanguagePackTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RemoteLanguageHelper {
    public static final String LANGUAGE_FILE_SUFFIX_1 = ".lang";
    public static final String LANGUAGE_FILE_SUFFIX_2 = ".fzbin";
    public static final int TYPE_DOWNLOAD = 1;
    public static final int TYPE_INSTALL = 2;
    public static boolean isLoading;
    private static boolean needUploadLog;
    private static RemoteLanguage.LanguageInfo sLanguageInfo;
    private static OnRemoteLanguageListener sRemoteLanguageListener;
    private static final BaseDeviceInfoCallback mCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.util.RemoteLanguageHelper.1
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetCanDownloadLangInfo(CanDownLangInfo canDownLangInfo) {
            super.onGetCanDownloadLangInfo(canDownLangInfo);
            BLEManager.unregisterGetDeviceInfoCallBack(RemoteLanguageHelper.mCallback);
            RemoteLanguageHelper.requestLanguageList(SPHelper.getDeviceThirdVersion(), canDownLangInfo);
        }

        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetCanDownloadLangInfoV3(CanDownLangInfoV3 canDownLangInfoV3) {
            super.onGetCanDownloadLangInfoV3(canDownLangInfoV3);
            BLEManager.unregisterGetDeviceInfoCallBack(RemoteLanguageHelper.mCallback);
            RemoteLanguageHelper.requestLanguageList(SPHelper.getDeviceThirdVersion(), canDownLangInfoV3);
        }
    };
    private static final IFileTransferListener sFileTransferListener = new IFileTransferListener() { // from class: com.ido.life.util.RemoteLanguageHelper.2
        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onStart() {
            RemoteLanguageHelper.saveLanguageLog("IFileTransferListener onStart");
            boolean unused = RemoteLanguageHelper.needUploadLog = true;
            RemoteLanguageHelper.progress(2, 0);
            RemoteLanguageHelper.uploadRemoteLanguageLog(RemoteLanguageHelper.sLanguageInfo, 2);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onProgress(int i) {
            RemoteLanguageHelper.saveLanguageLog("IFileTransferListener onProgress : " + i);
            RemoteLanguageHelper.progress(2, i);
            if (i <= 0 || !RemoteLanguageHelper.needUploadLog) {
                return;
            }
            boolean unused = RemoteLanguageHelper.needUploadLog = false;
            RemoteLanguageHelper.uploadRemoteLanguageLog(RemoteLanguageHelper.sLanguageInfo, 3);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onSuccess() {
            RemoteLanguageHelper.saveLanguageLog("IFileTransferListener onSuccess");
            RemoteLanguageHelper.switchDeviceLanguage(RemoteLanguageHelper.sLanguageInfo.getCodeId());
            RemoteLanguageHelper.progress(2, 100);
            RemoteLanguageHelper.complete(2, true);
            RemoteLanguageHelper.uploadRemoteLanguageLog(RemoteLanguageHelper.sLanguageInfo, 4);
            RemoteLanguageHelper.deleteAllLanguageFile();
            HomeFragmentPresenter.hasNewDeviceVersion = false;
            EventBusHelper.postSticky(new BaseMessage(Constants.EventConstants.EVENT_REQUEST_OTA_INFO, RemoteLanguageHelper.getDeviceInfo().mDeviceAddress));
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onFailed(String str) {
            RemoteLanguageHelper.saveLanguageLog("IFileTransferListener onFailed : " + str);
            RemoteLanguageHelper.complete(2, false);
            RemoteLanguageHelper.uploadRemoteLanguageLog(RemoteLanguageHelper.sLanguageInfo, 5);
            RemoteLanguageHelper.deleteAllLanguageFile();
        }
    };
    private static final IconTransmitterListener mFileTransferListener = new IconTransmitterListener() { // from class: com.ido.life.util.RemoteLanguageHelper.3
        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferShutdown(List<TransferTask> list) {
            Iterator<TransferTask> it = list.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (it.next().getModuleType() == 10) {
                    z = true;
                }
            }
            if (z) {
                RemoteLanguageHelper.saveLanguageLog("onTransferShutdown");
                FileTransmitter.getInstance().removeListener(RemoteLanguageHelper.mFileTransferListener);
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferFailed(int i, int i2) {
            if (i == 10) {
                FileTransmitter.getInstance().removeListener(RemoteLanguageHelper.mFileTransferListener);
                RemoteLanguageHelper.sFileTransferListener.onFailed("");
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferSuccess(int i, int i2) {
            if (i == 10) {
                FileTransmitter.getInstance().removeListener(RemoteLanguageHelper.mFileTransferListener);
                RemoteLanguageHelper.sFileTransferListener.onSuccess();
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferProgress(int i, int i2, int i3) {
            if (i == 10) {
                RemoteLanguageHelper.sFileTransferListener.onProgress(i3);
            }
        }

        @Override // com.ido.life.transmitter.IconTransmitterListener
        public void onTransferStart(int i, int i2) {
            if (i == 10) {
                RemoteLanguageHelper.sFileTransferListener.onStart();
            }
        }
    };

    public interface OnRemoteLanguageListener {
        void onRemoteLanguageComplete(int i, boolean z);

        void onRemoteLanguageProgress(int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void progress(int i, int i2) {
        OnRemoteLanguageListener onRemoteLanguageListener = sRemoteLanguageListener;
        if (onRemoteLanguageListener != null) {
            onRemoteLanguageListener.onRemoteLanguageProgress(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void complete(int i, boolean z) {
        isLoading = false;
        OnRemoteLanguageListener onRemoteLanguageListener = sRemoteLanguageListener;
        if (onRemoteLanguageListener != null) {
            onRemoteLanguageListener.onRemoteLanguageComplete(i, z);
        }
    }

    public static void getDeviceLanguage() {
        SupportFunctionInfo supportFunctionInfo;
        if (BLEManager.isConnected() && (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) != null) {
            if (supportFunctionInfo.downloadLanguage) {
                BLEManager.registerGetDeviceInfoCallBack(mCallback);
                BLEManager.getCanDownloadLangInfo();
            } else if (supportFunctionInfo.ex_table_main10_v3_get_lang_library) {
                BLEManager.registerGetDeviceInfoCallBack(mCallback);
                BLEManager.getCanDownloadLangInfoV3();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void requestLanguageList(String str, CanDownLangInfoV3 canDownLangInfoV3) {
        saveLanguageLog("requestLanguageList V3 CanDownLangInfoV3 : " + canDownLangInfoV3);
        saveLanguageLog("requestLanguageList V3， isLoading : " + isLoading);
        if (isLoading) {
            return;
        }
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            saveLanguageLog("requestLanguageList V3， net work error");
            return;
        }
        if (canDownLangInfoV3 == null || canDownLangInfoV3.items == null || canDownLangInfoV3.items.size() == 0) {
            return;
        }
        final CanDownLangInfoV3.Item item = new CanDownLangInfoV3.Item();
        boolean zIsLanguageFollowSys = SPHelper.isLanguageFollowSys();
        saveLanguageLog("requestLanguageList V3， LanguageFollowSys : " + zIsLanguageFollowSys);
        if (zIsLanguageFollowSys) {
            item.language_type = LanguageCodeHelper.getDeviceLanguageCode();
            saveLanguageLog("requestLanguageList V3， targetLanguage : " + item.language_type);
            boolean z = false;
            boolean z2 = item.language_type == canDownLangInfoV3.use_lang;
            if (canDownLangInfoV3.items_user != null && canDownLangInfoV3.items_user.size() > 0) {
                Iterator<CanDownLangInfoV3.Item> it = canDownLangInfoV3.items_user.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CanDownLangInfoV3.Item next = it.next();
                    if (next != null && next.language_type == item.language_type) {
                        item.language_version = next.language_version;
                        saveLanguageLog("requestLanguageList V3， targetLanguage version : " + item.language_version);
                        z2 = true;
                        break;
                    }
                }
            }
            saveLanguageLog("requestLanguageList V3，targetLanguage ：" + item);
            if (z2 && item.language_type != canDownLangInfoV3.use_lang) {
                saveLanguageLog("requestLanguageList V3，switchDeviceLanguage ：" + item.language_type);
                switchDeviceLanguage(item.language_type);
            }
            if (!z2) {
                Iterator<CanDownLangInfoV3.Item> it2 = canDownLangInfoV3.items.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    CanDownLangInfoV3.Item next2 = it2.next();
                    if (next2 != null && next2.language_type == item.language_type) {
                        z = true;
                        break;
                    }
                }
                saveLanguageLog("requestLanguageList V3，isSupport ：" + z);
                if (!z) {
                    return;
                }
            }
        } else {
            item.language_type = canDownLangInfoV3.use_lang;
            if (canDownLangInfoV3.items_user != null && canDownLangInfoV3.items_user.size() > 0) {
                Iterator<CanDownLangInfoV3.Item> it3 = canDownLangInfoV3.items_user.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    CanDownLangInfoV3.Item next3 = it3.next();
                    if (next3 != null && next3.language_type == item.language_type) {
                        item.language_version = next3.language_version;
                        break;
                    }
                }
            }
            saveLanguageLog("requestLanguageList V3，targetLanguage ：" + item);
        }
        DeviceManager.requestLanguageList(str, new DeviceManager.OnDeviceCallback<List<RemoteLanguage.LanguageInfo>>() { // from class: com.ido.life.util.RemoteLanguageHelper.4
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<RemoteLanguage.LanguageInfo> list) {
                RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onSuccess，languageInfoList ：" + GsonUtil.toJson(list));
                if (!BLEManager.isConnected()) {
                    RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onSuccess，device disconnect");
                    return;
                }
                if (list == null || list.size() == 0) {
                    return;
                }
                for (RemoteLanguage.LanguageInfo languageInfo : list) {
                    if (languageInfo != null && languageInfo.getCodeId() == item.language_type) {
                        try {
                            int i = Integer.parseInt(languageInfo.getLangVersion());
                            RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onSuccess，targetLanguage version : " + i);
                            if (i > item.language_version) {
                                HomeFragmentPresenter.hasNewDeviceVersion = true;
                            }
                            RemoteLanguage.LanguageInfo lastRemindLanguageInfo = SPHelper.getLastRemindLanguageInfo();
                            RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onSuccess，lastRemindLanguageInfo : " + lastRemindLanguageInfo);
                            if (lastRemindLanguageInfo != null && TextUtils.equals(languageInfo.toString(), lastRemindLanguageInfo.toString())) {
                                RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onSuccess，have been reminded");
                                return;
                            }
                            if (item.language_version == 0) {
                                RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onSuccess，remind to install language : " + languageInfo);
                                EventBusHelper.post(new RemoteLanguageMsg(Constants.EventConstants.TYPE_LANGUAGE_DIFFERENT, languageInfo));
                                return;
                            }
                            if (i > item.language_version) {
                                RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onSuccess，remind to update language : " + languageInfo);
                                EventBusHelper.post(new RemoteLanguageMsg(Constants.EventConstants.TYPE_LANGUAGE_UPDATE, languageInfo));
                                return;
                            }
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onSuccess，format version error : " + e2.toString());
                            return;
                        }
                    }
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str2) {
                RemoteLanguageHelper.saveLanguageLog("requestLanguageList V3 onFailed = " + str2);
            }
        });
    }

    public static void requestLanguageList(String str, CanDownLangInfo canDownLangInfo) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            saveLanguageLog("requestLanguageList，net work error");
            return;
        }
        saveLanguageLog("requestLanguageList，CanDownLangInfo : " + canDownLangInfo);
        if (canDownLangInfo == null) {
            return;
        }
        if (!SPHelper.isLanguageFollowSys()) {
            saveLanguageLog("requestLanguageList，language not follow system");
            return;
        }
        final int deviceLanguageCode = LanguageCodeHelper.getDeviceLanguageCode();
        saveLanguageLog("requestLanguageList，phoneLanguage : " + deviceLanguageCode);
        if (deviceLanguageCode == canDownLangInfo.useLang) {
            return;
        }
        Iterator<CanDownLangInfo.LangArray> it = canDownLangInfo.langArray.iterator();
        while (it.hasNext()) {
            if (it.next().value == deviceLanguageCode) {
                saveLanguageLog("requestLanguageList，switchDeviceLanguage : " + deviceLanguageCode);
                switchDeviceLanguage(deviceLanguageCode);
                return;
            }
        }
        saveLanguageLog("requestLanguageList start");
        DeviceManager.requestLanguageList(str, new DeviceManager.OnDeviceCallback<List<RemoteLanguage.LanguageInfo>>() { // from class: com.ido.life.util.RemoteLanguageHelper.5
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<RemoteLanguage.LanguageInfo> list) {
                RemoteLanguageHelper.saveLanguageLog("requestLanguageList onSuccess，languageInfoList : " + GsonUtil.toJson(list));
                if (!BLEManager.isConnected()) {
                    RemoteLanguageHelper.saveLanguageLog("requestLanguageList onSuccess，device disconnect");
                    return;
                }
                if (list == null || list.isEmpty()) {
                    return;
                }
                RemoteLanguage.LanguageInfo lastRemindLanguageInfo = SPHelper.getLastRemindLanguageInfo();
                RemoteLanguageHelper.saveLanguageLog("requestLanguageList onSuccess，lastRemindLanguageInfo : " + lastRemindLanguageInfo);
                for (RemoteLanguage.LanguageInfo languageInfo : list) {
                    if (languageInfo != null) {
                        if (lastRemindLanguageInfo != null && TextUtils.equals(languageInfo.toString(), lastRemindLanguageInfo.toString())) {
                            RemoteLanguageHelper.saveLanguageLog("requestLanguageList onSuccess，have been reminded");
                            return;
                        } else if (languageInfo.getCodeId() == deviceLanguageCode) {
                            RemoteLanguageHelper.saveLanguageLog("requestLanguageList onSuccess，remind to install");
                            EventBusHelper.post(new RemoteLanguageMsg(Constants.EventConstants.TYPE_LANGUAGE_DIFFERENT, languageInfo));
                            return;
                        }
                    }
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str2) {
                RemoteLanguageHelper.saveLanguageLog("requestLanguageList onFailed = " + str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void switchDeviceLanguage(int i) {
        if (BLEManager.isConnected()) {
            Units units = LocalDataManager.getUnits();
            if (units == null) {
                units = new Units();
            }
            units.language = i;
            BLEManager.setUnit(units);
        }
    }

    public static void downloadLanguageFile(RemoteLanguage.LanguageInfo languageInfo, OnRemoteLanguageListener onRemoteLanguageListener) {
        saveLanguageLog("downloadLanguageFile, LanguageInfo : " + languageInfo);
        if (languageInfo == null) {
            complete(1, false);
            return;
        }
        isLoading = true;
        sLanguageInfo = languageInfo;
        sRemoteLanguageListener = onRemoteLanguageListener;
        String strConcat = getFileFolderPath().concat(languageInfo.getCode().concat(FileDialDefinedUtil.FILE_ZIP));
        if (FileUtil.fileExists(strConcat)) {
            saveLanguageLog("downloadLanguageFile, file exist");
            progress(1, 100);
            unZipFile(strConcat);
            return;
        }
        DownloadManager.download(languageInfo.getUrl(), strConcat, new DownloadManager.DownloadListener() { // from class: com.ido.life.util.RemoteLanguageHelper.6
            @Override // com.ido.life.data.DownloadManager.DownloadListener
            public void onDownloadStart() {
                RemoteLanguageHelper.saveLanguageLog("downloadLanguageFile onDownloadStart");
                RemoteLanguageHelper.progress(1, 0);
                RemoteLanguageHelper.uploadRemoteLanguageLog(RemoteLanguageHelper.sLanguageInfo, 0);
            }

            @Override // com.ido.life.data.DownloadManager.DownloadListener
            public void onDownloadProgress(int i) {
                RemoteLanguageHelper.saveLanguageLog("downloadLanguageFile onDownloadProgress : " + i);
                RemoteLanguageHelper.progress(1, i);
            }

            @Override // com.ido.life.data.DownloadManager.DownloadListener
            public void onDownloadFinish(String str) {
                RemoteLanguageHelper.saveLanguageLog("downloadLanguageFile onDownloadFinish : " + str);
                RemoteLanguageHelper.progress(1, 100);
                RemoteLanguageHelper.unZipFile(str);
                RemoteLanguageHelper.uploadRemoteLanguageLog(RemoteLanguageHelper.sLanguageInfo, 1);
            }

            @Override // com.ido.life.data.DownloadManager.DownloadListener
            public void onDownloadFailed(int i, String str) {
                RemoteLanguageHelper.saveLanguageLog("downloadLanguageFile onDownloadFailed : " + str);
                RemoteLanguageHelper.complete(1, false);
                RemoteLanguageHelper.deleteAllLanguageFile();
            }
        });
    }

    public static String getFileFolderPath() {
        return LogPathImpl.getInstance().getLanguageFilePath().concat(getDeviceInfo().mDeviceId + "/").concat(sLanguageInfo.getCode() + "/");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void unZipFile(final String str) {
        saveLanguageLog("downloadLanguageFile unZipFile");
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.util.RemoteLanguageHelper.7
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                return Boolean.valueOf(ZipUtils.unzip(str, RemoteLanguageHelper.getFileFolderPath()));
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                RemoteLanguageHelper.saveLanguageLog("downloadLanguageFile unZipFile success : " + obj);
                if (((Boolean) obj).booleanValue()) {
                    RemoteLanguageHelper.installLanguage2Device();
                } else {
                    RemoteLanguageHelper.complete(2, false);
                    RemoteLanguageHelper.deleteAllLanguageFile();
                }
            }
        }).execute("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void installLanguage2Device() {
        saveLanguageLog("installLanguage2Device");
        String localLanguageFilePath = getLocalLanguageFilePath();
        saveLanguageLog("installLanguage2Device，filePath : " + localLanguageFilePath);
        if (TextUtils.isEmpty(localLanguageFilePath)) {
            complete(2, false);
            return;
        }
        FileTransmitter.getInstance().removeListener(mFileTransferListener);
        FileTransmitter.getInstance().addListener(mFileTransferListener);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LanguagePackTransferTask(localLanguageFilePath));
        FileTransmitter.getInstance().execute(arrayList);
    }

    private static String getLocalLanguageFilePath() {
        File file = new File(getFileFolderPath());
        if (!file.exists() || !file.isDirectory()) {
            saveLanguageLog("getLocalLanguageFilePath folder not exist");
            return null;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            saveLanguageLog("getLocalLanguageFilePath folder is empty");
            return null;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile() && (file2.getName().endsWith(LANGUAGE_FILE_SUFFIX_1) || file2.getName().endsWith(LANGUAGE_FILE_SUFFIX_2))) {
                return file2.getAbsolutePath();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void deleteAllLanguageFile() {
        FileUtil.deleteDirectory(getFileFolderPath());
        sRemoteLanguageListener = null;
        sLanguageInfo = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
        }
        return currentDeviceInfo;
    }

    public static void uploadRemoteLanguageLog(RemoteLanguage.LanguageInfo languageInfo, final int i) {
        if (languageInfo == null) {
            return;
        }
        DeviceManager.uploadLanguageLog(languageInfo.getId(), getDeviceInfo(), i, languageInfo.getSid(), new DeviceManager.OnDeviceCallback<Boolean>() { // from class: com.ido.life.util.RemoteLanguageHelper.8
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(Boolean bool) {
                RemoteLanguageHelper.saveLanguageLog("上传语言状态 < " + i + " > " + bool);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i2, String str) {
                RemoteLanguageHelper.saveLanguageLog("上传语言状态 < " + i + " > 失败：" + str);
            }
        });
    }

    public static void saveLanguageLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getRemoteLanguageLogPath(), str);
    }

    public static void saveLanguageLog(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getRemoteLanguageLogPath(), str, str2);
    }
}