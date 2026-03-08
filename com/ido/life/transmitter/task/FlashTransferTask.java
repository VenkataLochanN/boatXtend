package com.ido.life.transmitter.task;

import com.ido.ble.BLEManager;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.life.transmitter.callback.OnFileTransferCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlashTransferTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/ido/life/transmitter/task/FlashTransferTask;", "Lcom/ido/life/transmitter/task/TransferTask;", "Lcom/ido/ble/file/transfer/IFileTransferListener;", "filepath", "", "isApollo", "", "dataType", "", "(Ljava/lang/String;ZI)V", "getFilepath", "()Ljava/lang/String;", "()Z", "onFailed", "", "p0", "onProgress", "onStart", "onSuccess", "onTransferStart", "onTransferStop", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FlashTransferTask extends TransferTask implements IFileTransferListener {
    private final String filepath;
    private final boolean isApollo;

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStop() {
    }

    public /* synthetic */ FlashTransferTask(String str, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getFilepath() {
        return this.filepath;
    }

    /* JADX INFO: renamed from: isApollo, reason: from getter */
    public final boolean getIsApollo() {
        return this.isApollo;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlashTransferTask(String filepath, boolean z, int i) {
        super(9, i, 5, 0L, 8, null);
        Intrinsics.checkParameterIsNotNull(filepath, "filepath");
        this.filepath = filepath;
        this.isApollo = z;
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStart() {
        FileTransferConfig defaultTransLangFileConfig = FileTransferConfig.getDefaultTransLangFileConfig(this.filepath, this);
        defaultTransLangFileConfig.maxRetryTimes = 0;
        defaultTransLangFileConfig.zipType = this.isApollo ? 0 : 2;
        if (BLEManager.isConnected()) {
            BLEManager.startTranCommonFile(defaultTransLangFileConfig);
            return;
        }
        printAndSave("startTransfer device is disconnected");
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onFailed(this, "device is disconnected");
        }
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
            callback.onFailed(this, "");
        }
    }
}