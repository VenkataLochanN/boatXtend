package com.ido.life.transmitter.task;

import com.ido.ble.BLEManager;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.life.transmitter.callback.OnFileTransferCallback;
import com.ido.life.util.RemoteLanguageHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LanguagePackTransferTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\tH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0014J\b\u0010\u0010\u001a\u00020\tH\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/ido/life/transmitter/task/LanguagePackTransferTask;", "Lcom/ido/life/transmitter/task/TransferTask;", "Lcom/ido/ble/file/transfer/IFileTransferListener;", "filePath", "", "(Ljava/lang/String;)V", "getFilePath", "()Ljava/lang/String;", "onFailed", "", "p0", "onProgress", "", "onStart", "onSuccess", "onTransferStart", "onTransferStop", "app_release"}, k = 1, mv = {1, 1, 16})
public final class LanguagePackTransferTask extends TransferTask implements IFileTransferListener {
    private final String filePath;

    public final String getFilePath() {
        return this.filePath;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LanguagePackTransferTask(String filePath) {
        super(10, 0, 6, 0L, 8, null);
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        this.filePath = filePath;
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStart() {
        FileTransferConfig defaultTransLangFileConfig = FileTransferConfig.getDefaultTransLangFileConfig(this.filePath, this);
        defaultTransLangFileConfig.maxRetryTimes = 0;
        String str = RemoteLanguageHelper.LANGUAGE_FILE_SUFFIX_2;
        try {
            try {
                str = "." + ((String) StringsKt.split$default((CharSequence) this.filePath, new String[]{"."}, false, 0, 6, (Object) null).get(r3.size() - 1));
            } catch (Exception e2) {
                e2.printStackTrace();
                RemoteLanguageHelper.saveLanguageLog("installLanguage2Device get specName error : " + e2);
            }
            defaultTransLangFileConfig.firmwareSpecName = str;
            RemoteLanguageHelper.saveLanguageLog("installLanguage2Device，specName : " + defaultTransLangFileConfig.firmwareSpecName);
            if (Intrinsics.areEqual(str, RemoteLanguageHelper.LANGUAGE_FILE_SUFFIX_1)) {
                defaultTransLangFileConfig.zipType = 2;
            }
            RemoteLanguageHelper.saveLanguageLog("installLanguage2Device start");
            BLEManager.startTranCommonFile(defaultTransLangFileConfig);
        } catch (Throwable th) {
            defaultTransLangFileConfig.firmwareSpecName = RemoteLanguageHelper.LANGUAGE_FILE_SUFFIX_2;
            throw th;
        }
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStop() {
        BLEManager.stopTranCommonFile();
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onStart() {
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onStart(this);
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onProgress(int p0) {
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onProgress(this, p0);
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onSuccess() {
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onSuccess(this);
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onFailed(String p0) {
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onFailed(this, p0);
        }
    }
}