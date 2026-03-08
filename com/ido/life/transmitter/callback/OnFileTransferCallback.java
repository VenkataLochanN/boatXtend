package com.ido.life.transmitter.callback;

import com.ido.life.transmitter.task.TransferTask;
import kotlin.Metadata;

/* JADX INFO: compiled from: OnFileTransferCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u001c\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u001a\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH&J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u000f"}, d2 = {"Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "", "onBusy", "", "task", "Lcom/ido/life/transmitter/task/TransferTask;", "onCancel", "onFailed", "msg", "", "onProgress", "var2", "", "onStart", "onSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface OnFileTransferCallback {
    void onBusy(TransferTask task);

    void onCancel(TransferTask task);

    void onFailed(TransferTask task, String msg);

    void onProgress(TransferTask task, int var2);

    void onStart(TransferTask task);

    void onSuccess(TransferTask task);
}