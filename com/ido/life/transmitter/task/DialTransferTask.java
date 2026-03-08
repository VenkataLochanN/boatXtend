package com.ido.life.transmitter.task;

import com.amap.api.maps.model.MyLocationStyle;
import com.ido.ble.BLEManager;
import com.ido.ble.watch.custom.WatchPlateSetConfig;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import com.ido.life.transmitter.callback.OnFileTransferCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DialTransferTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 \u001b2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\u001bB)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\nH\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u0013H\u0014J\b\u0010\u001a\u001a\u00020\u0013H\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/ido/life/transmitter/task/DialTransferTask;", "Lcom/ido/life/transmitter/task/TransferTask;", "Lcom/ido/ble/watch/custom/callback/WatchPlateCallBack$IAutoSetPlateCallBack;", "Lcom/ido/ble/watch/custom/callback/WatchPlateCallBack$ISetPlatErrorCallback;", "filepath", "", "filename", "onlyTranslate", "", "dataType", "", "(Ljava/lang/String;Ljava/lang/String;ZI)V", "getFilename", "()Ljava/lang/String;", "getFilepath", "mIsDeviceSpaceNotEnough", "getOnlyTranslate", "()Z", "onFailed", "", MyLocationStyle.ERROR_CODE, "onProgress", "p0", "onStart", "onSuccess", "onTransferStart", "onTransferStop", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DialTransferTask extends TransferTask implements WatchPlateCallBack.IAutoSetPlateCallBack, WatchPlateCallBack.ISetPlatErrorCallback {
    public static final String DEVICE_SPACE_NOT_ENOUGH = "21";
    private final String filename;
    private final String filepath;
    private boolean mIsDeviceSpaceNotEnough;
    private final boolean onlyTranslate;

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStop() {
    }

    public final String getFilepath() {
        return this.filepath;
    }

    public final String getFilename() {
        return this.filename;
    }

    public final boolean getOnlyTranslate() {
        return this.onlyTranslate;
    }

    public /* synthetic */ DialTransferTask(String str, String str2, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? true : z, (i2 & 8) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DialTransferTask(String filepath, String filename, boolean z, int i) {
        super(7, i, 4, 0L, 8, null);
        Intrinsics.checkParameterIsNotNull(filepath, "filepath");
        Intrinsics.checkParameterIsNotNull(filename, "filename");
        this.filepath = filepath;
        this.filename = filename;
        this.onlyTranslate = z;
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStart() {
        WatchPlateSetConfig watchPlateSetConfig = new WatchPlateSetConfig();
        watchPlateSetConfig.filePath = this.filepath;
        watchPlateSetConfig.uniqueID = this.filename;
        watchPlateSetConfig.stateListener = this;
        watchPlateSetConfig.errorCallback = this;
        watchPlateSetConfig.isOnlyTranslateWatchFile = this.onlyTranslate;
        if (BLEManager.isConnected()) {
            BLEManager.startSetPlateFileToWatch(watchPlateSetConfig);
            return;
        }
        printAndSave("startTransfer device is disconnected");
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onFailed(this, "device is disconnected");
        }
    }

    @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IAutoSetPlateCallBack
    public void onStart() {
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onStart(this);
        }
    }

    @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IAutoSetPlateCallBack
    public void onProgress(int p0) {
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onProgress(this, p0);
        }
    }

    @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IAutoSetPlateCallBack
    public void onSuccess() {
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onSuccess(this);
        }
    }

    @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IAutoSetPlateCallBack
    public void onFailed() {
        if (this.mIsDeviceSpaceNotEnough) {
            printAndSave("onFailed：设备空间不足，不提示失败！");
            return;
        }
        OnFileTransferCallback callback = getCallback();
        if (callback != null) {
            callback.onFailed(this, "");
        }
    }

    @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.ISetPlatErrorCallback
    public void onFailed(int errorCode) {
        if (Intrinsics.areEqual(String.valueOf(errorCode), DEVICE_SPACE_NOT_ENOUGH)) {
            this.mIsDeviceSpaceNotEnough = true;
            printAndSave("设备空间不足，提示用户删除表盘！");
            OnFileTransferCallback callback = getCallback();
            if (callback != null) {
                callback.onFailed(this, String.valueOf(errorCode));
                return;
            }
            return;
        }
        this.mIsDeviceSpaceNotEnough = false;
    }
}