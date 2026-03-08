package com.ido.life.transmitter.callback;

import com.ido.life.transmitter.task.TransferTask;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BaseFileTransferCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\n\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\u000b\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u000e\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\u0012\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0014"}, d2 = {"Lcom/ido/life/transmitter/callback/BaseFileTransferCallback;", "Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "mIconTransmitter", "(Lcom/ido/life/transmitter/callback/OnFileTransferCallback;)V", "getMIconTransmitter", "()Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "onBusy", "", "task", "Lcom/ido/life/transmitter/task/TransferTask;", "onCancel", "onFailed", "msg", "", "onProgress", "var2", "", "onStart", "onSuccess", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseFileTransferCallback implements OnFileTransferCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int MAX_PRIORITY = 1000;
    private final OnFileTransferCallback mIconTransmitter;

    public BaseFileTransferCallback(OnFileTransferCallback onFileTransferCallback) {
        this.mIconTransmitter = onFileTransferCallback;
    }

    public final OnFileTransferCallback getMIconTransmitter() {
        return this.mIconTransmitter;
    }

    /* JADX INFO: compiled from: BaseFileTransferCallback.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ido/life/transmitter/callback/BaseFileTransferCallback$Companion;", "", "()V", "MAX_PRIORITY", "", "getMAX_PRIORITY", "()I", "setMAX_PRIORITY", "(I)V", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getMAX_PRIORITY() {
            return BaseFileTransferCallback.MAX_PRIORITY;
        }

        public final void setMAX_PRIORITY(int i) {
            BaseFileTransferCallback.MAX_PRIORITY = i;
        }
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onStart(TransferTask task) {
        OnFileTransferCallback onFileTransferCallback = this.mIconTransmitter;
        if (onFileTransferCallback != null) {
            onFileTransferCallback.onStart(task);
        }
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onProgress(TransferTask task, int var2) {
        OnFileTransferCallback onFileTransferCallback = this.mIconTransmitter;
        if (onFileTransferCallback != null) {
            onFileTransferCallback.onProgress(task, var2);
        }
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onSuccess(TransferTask task) {
        OnFileTransferCallback onFileTransferCallback = this.mIconTransmitter;
        if (onFileTransferCallback != null) {
            onFileTransferCallback.onSuccess(task);
        }
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onFailed(TransferTask task, String msg) {
        OnFileTransferCallback onFileTransferCallback = this.mIconTransmitter;
        if (onFileTransferCallback != null) {
            onFileTransferCallback.onFailed(task, msg);
        }
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onBusy(TransferTask task) {
        OnFileTransferCallback onFileTransferCallback = this.mIconTransmitter;
        if (onFileTransferCallback != null) {
            onFileTransferCallback.onBusy(task);
        }
    }

    @Override // com.ido.life.transmitter.callback.OnFileTransferCallback
    public void onCancel(TransferTask task) {
        OnFileTransferCallback onFileTransferCallback = this.mIconTransmitter;
        if (onFileTransferCallback != null) {
            onFileTransferCallback.onCancel(task);
        }
    }
}