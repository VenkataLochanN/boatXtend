package com.ido.life.transmitter;

import androidx.core.app.NotificationCompat;
import com.ido.life.transmitter.task.TransferTask;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IconTransmitterListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\"\u0010\u0007\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u001a\u0010\r\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u001a\u0010\u000e\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u000f"}, d2 = {"Lcom/ido/life/transmitter/IconTransmitterListener;", "", "onTransferFailed", "", "module", "", "type", "onTransferProgress", NotificationCompat.CATEGORY_PROGRESS, "onTransferShutdown", "remainList", "", "Lcom/ido/life/transmitter/task/TransferTask;", "onTransferStart", "onTransferSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IconTransmitterListener {

    /* JADX INFO: compiled from: IconTransmitterListener.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static void onTransferProgress(IconTransmitterListener iconTransmitterListener, @ModuleType int i, int i2, int i3) {
        }

        public static void onTransferShutdown(IconTransmitterListener iconTransmitterListener, List<TransferTask> remainList) {
            Intrinsics.checkParameterIsNotNull(remainList, "remainList");
        }

        public static void onTransferStart(IconTransmitterListener iconTransmitterListener, @ModuleType int i, int i2) {
        }
    }

    void onTransferFailed(@ModuleType int module, int type);

    void onTransferProgress(@ModuleType int module, int type, int progress);

    void onTransferShutdown(List<TransferTask> remainList);

    void onTransferStart(@ModuleType int module, int type);

    void onTransferSuccess(@ModuleType int module, int type);
}