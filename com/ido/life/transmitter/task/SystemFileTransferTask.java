package com.ido.life.transmitter.task;

import com.ido.ble.BLEManager;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.life.transmitter.callback.OnFileTransferCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemFileTransferTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0014J\b\u0010\u0016\u001a\u00020\u0010H\u0014R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/ido/life/transmitter/task/SystemFileTransferTask;", "Lcom/ido/life/transmitter/task/TransferTask;", "Lcom/ido/ble/file/transfer/IFileTransferListener;", "filepath", "", "filename", "isApollo", "", "dataType", "", "(Ljava/lang/String;Ljava/lang/String;ZI)V", "getFilename", "()Ljava/lang/String;", "getFilepath", "()Z", "onFailed", "", "p0", "onProgress", "onStart", "onSuccess", "onTransferStart", "onTransferStop", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SystemFileTransferTask extends TransferTask implements IFileTransferListener {
    private final String filename;
    private final String filepath;
    private final boolean isApollo;

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStop() {
    }

    public /* synthetic */ SystemFileTransferTask(String str, String str2, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, (i2 & 8) != 0 ? 0 : i);
    }

    public final String getFilename() {
        return this.filename;
    }

    public final String getFilepath() {
        return this.filepath;
    }

    /* JADX INFO: renamed from: isApollo, reason: from getter */
    public final boolean getIsApollo() {
        return this.isApollo;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemFileTransferTask(String filepath, String filename, boolean z, int i) {
        super(9, i, 5, 0L, 8, null);
        Intrinsics.checkParameterIsNotNull(filepath, "filepath");
        Intrinsics.checkParameterIsNotNull(filename, "filename");
        this.filepath = filepath;
        this.filename = filename;
        this.isApollo = z;
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStart() {
        FileTransferConfig defaultTransLangFileConfig = FileTransferConfig.getDefaultTransLangFileConfig(this.filepath, this);
        defaultTransLangFileConfig.maxRetryTimes = 0;
        defaultTransLangFileConfig.firmwareSpecName = this.filename;
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