package com.ido.life.transmitter.callback;

import com.ido.ble.icon.transfer.IIconTransferListener;
import com.ido.ble.icon.transfer.IconTranConfig;
import com.ido.life.transmitter.task.IconTransferTask;
import com.ido.life.transmitter.task.TransferTask;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseIconTransferCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\r\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010\u000e\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\u0012\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\u0013"}, d2 = {"Lcom/ido/life/transmitter/callback/BaseIconTransferCallback;", "Lcom/ido/life/transmitter/callback/BaseFileTransferCallback;", "Lcom/ido/ble/icon/transfer/IIconTransferListener;", "mIconTransmitter", "Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "(Lcom/ido/life/transmitter/callback/OnFileTransferCallback;)V", "convert", "Lcom/ido/life/transmitter/task/TransferTask;", "config", "Lcom/ido/ble/icon/transfer/IconTranConfig;", "onBusy", "", "p0", "onFailed", "onProgress", "p1", "", "onStart", "onSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseIconTransferCallback extends BaseFileTransferCallback implements IIconTransferListener {
    public BaseIconTransferCallback(OnFileTransferCallback onFileTransferCallback) {
        super(onFileTransferCallback);
    }

    private final TransferTask convert(IconTranConfig config) {
        if (config != null) {
            return new IconTransferTask(config.type, config.index, 1);
        }
        return null;
    }

    @Override // com.ido.ble.icon.transfer.IIconTransferListener
    public void onStart(IconTranConfig p0) {
        onStart(convert(p0));
    }

    @Override // com.ido.ble.icon.transfer.IIconTransferListener
    public void onProgress(IconTranConfig p0, int p1) {
        onProgress(convert(p0), p1);
    }

    @Override // com.ido.ble.icon.transfer.IIconTransferListener
    public void onSuccess(IconTranConfig p0) {
        onSuccess(convert(p0));
    }

    @Override // com.ido.ble.icon.transfer.IIconTransferListener
    public void onFailed(IconTranConfig p0) {
        onFailed(convert(p0), "");
    }

    @Override // com.ido.ble.icon.transfer.IIconTransferListener
    public void onBusy(IconTranConfig p0) {
        onBusy(convert(p0));
    }
}