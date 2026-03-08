package com.ido.life.transmitter.task;

import com.ido.ble.BLEManager;
import com.ido.ble.icon.transfer.IIconTransferListener;
import com.ido.ble.icon.transfer.IconTranConfig;
import com.ido.life.transmitter.ModuleType;
import com.ido.life.transmitter.callback.MotionIconTransferCallback;
import com.ido.life.transmitter.callback.NotificationIconTransferCallback;
import com.ido.life.transmitter.callback.OnFileTransferCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: IconTransferTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/ido/life/transmitter/task/IconTransferTask;", "Lcom/ido/life/transmitter/task/TransferTask;", "moduleType", "", "dataType", "priority", "(III)V", "adaptCallback", "Lcom/ido/ble/icon/transfer/IIconTransferListener;", "callback", "Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "onTransferStart", "", "onTransferStop", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class IconTransferTask extends TransferTask {
    public /* synthetic */ IconTransferTask(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public IconTransferTask(int i, int i2, int i3) {
        super(i, i2, i3, 0L, 8, null);
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStart() {
        IconTranConfig iconTranConfig = new IconTranConfig();
        iconTranConfig.type = getModuleType();
        iconTranConfig.index = getChildType();
        iconTranConfig.maxRetryTimes = 3;
        BLEManager.startTranIcon(iconTranConfig, adaptCallback(getCallback()));
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    protected void onTransferStop() {
        BLEManager.stopTranIcon();
    }

    private final IIconTransferListener adaptCallback(OnFileTransferCallback callback) {
        if (callback == null) {
            return null;
        }
        int moduleType = getModuleType();
        if (moduleType == 1) {
            return new NotificationIconTransferCallback(callback);
        }
        if (ModuleType.INSTANCE.getGROUP_TYPE_MOTION().contains(Integer.valueOf(moduleType))) {
            return new MotionIconTransferCallback(callback);
        }
        return null;
    }

    @Override // com.ido.life.transmitter.task.TransferTask
    public String toString() {
        return "IconTransferTask(moduleType=" + getModuleType() + ", childType=" + getChildType() + ", priority=" + getPriority() + ')';
    }
}