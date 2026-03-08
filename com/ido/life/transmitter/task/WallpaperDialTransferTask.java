package com.ido.life.transmitter.task;

import com.ido.ble.BLEManager;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.life.transmitter.callback.OnFileTransferCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WallpaperDialTransferTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0014J\b\u0010\u0013\u001a\u00020\rH\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/ido/life/transmitter/task/WallpaperDialTransferTask;", "Lcom/ido/life/transmitter/task/TransferTask;", "Lcom/ido/ble/file/transfer/IFileTransferListener;", "setPhotoPath", "", "dataType", "", "(Ljava/lang/String;I)V", "isTransferring", "", "getSetPhotoPath", "()Ljava/lang/String;", "onFailed", "", "p0", "onProgress", "onStart", "onSuccess", "onTransferStart", "onTransferStop", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WallpaperDialTransferTask extends TransferTask implements IFileTransferListener {
    private boolean isTransferring;
    private final String setPhotoPath;

    public /* synthetic */ WallpaperDialTransferTask(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0 : i);
    }

    public final String getSetPhotoPath() {
        return this.setPhotoPath;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WallpaperDialTransferTask(String setPhotoPath, int i) {
        super(7, i, 4, 0L, 8, null);
        Intrinsics.checkParameterIsNotNull(setPhotoPath, "setPhotoPath");
        this.setPhotoPath = setPhotoPath;
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStart() {
        if (BLEManager.isConnected()) {
            printAndSave("startTransfer " + this.setPhotoPath);
            BLEManager.startTranCommonFile(FileTransferConfig.getDefaultTransPictureConfig(this.setPhotoPath + ".lz", this));
            return;
        }
        printAndSave("startTransfer device is disconnected");
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onFailed(this, "device is disconnected");
        }
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStop() {
        if (this.isTransferring) {
            BLEManager.stopTranCommonFile();
            this.isTransferring = false;
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onStart() {
        this.isTransferring = true;
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
        this.isTransferring = false;
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onSuccess(this);
        }
    }

    @Override // com.ido.ble.file.transfer.IFileTransferListener
    public void onFailed(String p0) {
        this.isTransferring = false;
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onFailed(this, "");
        }
    }
}